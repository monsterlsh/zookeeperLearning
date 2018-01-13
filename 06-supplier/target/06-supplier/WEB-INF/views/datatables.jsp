<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<base href="<%=basePath%>">
<title>供应商信息</title>
<!-- web路径
不以/开始的相对路径，找资源，以当前的资源路径为基准，经常容易出问题
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:8080),经常加上项目名称
	http://localhost:8080/crud
	bootatrap.min.js依赖于jquery。min。js，所以应该放在其后面,jquery版本需要1.9以上
-->
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/datatables/jquery.dataTables.min.js"></script>
<link href="static/datatables/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="static/datatables/datatables-managed.js"></script>
</head>
<body>
<!-- 搭建显示页面 -->
<div class="container">
<!-- 标题栏 -->
<div class="row">
	<div class="col-md-12">
		<h1>内部供应商信息</h1>
	</div>
</div>
<!-- 按钮 -->
<div class="row">
	<div class="col-md-4 col-md-offset-10">
		<button class="btn btn-primary" id="supplier_add_model_btn" onclick="gotoRegister();">内部供应商注册</button>
	</div>
</div>
<!-- 显示表格数据 -->
<div class="row">
	<div class="col-md-12">
		<table class="table table-hover" id="suppliers_table">
		<thead>
			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>法定代表人</th>
				<th>业务联系人</th>
				<th>联系方式</th>
				<th>营业执照编号</th>
				<th>税务登记证编号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>
	</div>
</div>
</div>
<script type="text/javascript">
	//1、页面加载完成以后，直接去发送ajax请求，要到分页数据
	$(function(){
		//去首页
		build_suppliers_table();
	});
	
	var dataTable;
	function build_suppliers_table(){
		dataTable = $("#suppliers_table").MyDataTable([
            {"data": 'supplierId'},
            {"data": 'supplierShortname'},
            {"data": 'legalPerson'},
            {"data": 'businessContacter'},
            {"data": 'telephone'},
            {"data": 'businessLicense'},
            {"data": 'taxCertificate'},
            {"data": null}

        ], "suppliers",
        [
            {
                targets: [7],
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html('<button class="btn btn-primary btn-sm" onclick="gotoEdit(\''+oData.supplierId+'\')"><span class="glyphicon glyphicon-pencil"></span>编辑</button> <button class="btn btn-danger btn-sm" onclick="deleteSupplier(\''+oData.supplierId+'\')"><span class="glyphicon glyphicon-trash"></span>删除</button>');
                }
            }
        ],{},function(){});
	}
	
	function gotoRegister() {
		window.location.href="gotoRegister";

	}
	
	function gotoEdit(supplierId) {
		window.location.href="gotoEdit?supplierId="+supplierId;
	}
	
	function deleteSupplier(supplierId) {
		if(confirm("是否要删除该记录")){
			$.ajax({
				url:"deleteSupplier",
				type:"get",
				data:"supplierId="+supplierId,
				success:function(result){
					alert(result.msg);
					dataTable.fnDraw();
				}
			});
		} else {
			return false;
		}
		
	}
</script>
</body>
</html>