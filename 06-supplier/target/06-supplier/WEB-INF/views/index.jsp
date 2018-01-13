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
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/datatables/jquery.dataTables.min.css"></script>
<script type="text/javascript" src="static/datatables/jquery.dataTables.min.js"></script>
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
<!-- 显示分页信息 -->
<div class="row">
	<!-- 分页文字信息 -->
	<div class="col-md-6" id="page_info_area">
	</div>
	<!-- 分页条信息 -->
	<div class="col-md-6" id="page_info_nav">
		
	</div>
</div>
</div>
<script type="text/javascript">
	//1、页面加载完成以后，直接去发送ajax请求，要到分页数据
	$(function(){
		//去首页
		to_page(1);
	});
	
	function to_page(pn) {
		$.ajax({
			url:"suppliers",
			data:"pn="+pn,
			type:"get",
			success:function(result){
				//console.log(result);
				//解析并显示员工数据
				build_suppliers_table(result);
				//接续并显示分页信息
				build_page_info(result);
				//解析并显示分页条
				build_page_nav(result);
			}
		});
	}
	function build_suppliers_table(result){
		var suppliers = result.data.list;
		$.each(suppliers, function(index,item){
			var supplierId = $("<td></td>").append(item.supplierId);
			var supplierShortname = $("<td></td>").append(item.supplierShortname);
			var legalPerson = $("<td></td>").append(item.legalPerson);
			var businessContacter = $("<td></td>").append(item.businessContacter);
			var telephone = $("<td></td>").append(item.telephone);
			var businessLicense = $("<td></td>").append(item.businessLicense);
			var taxCertificate = $("<td></td>").append(item.taxCertificate);
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm").attr("onclick","gotoEdit('"+item.supplierId+"')")
						.append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
						.append("编辑");
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm").attr("onclick","deleteSupplier('"+item.supplierId+"','"+result.data.pageNum+"')")
						.append($("<span></span>").addClass("glyphicon glyphicon-trash"))
						.append("删除");
			var btn = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(supplierId)
						  .append(supplierShortname)
						  .append(legalPerson)
						  .append(businessContacter)
						  .append(telephone)
						  .append(businessLicense)
						  .append(taxCertificate)
						  .append(btn)
						  .appendTo("#suppliers_table tbody");
		});
	}
	
	function build_page_info(result) {
		$("#page_info_area").empty();
		$("#page_info_area").append("当前第"+result.data.pageNum+"页，总共"
				+result.data.pages+"页，总共"+result.data.total+"条数据");
	}
	
	function build_page_nav(result) {
		//page_info_nav
		$("#page_info_nav").empty();
		var ul = $("<ul></ul>").addClass("pagination");
		var firstLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var preLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#").attr("aria-label","Previous"));
		if(result.data.hasPreviousPage == false) {
			firstLi.addClass("disabled");
			preLi.addClass("disabled");
		} else {
			//为元素添加翻页事件
			firstLi.click(function(){
				to_page(1);
			});
			preLi.click(function(){
				to_page(result.data.pageNum-1);
			});
		}
		ul.append(firstLi).append(preLi);
		var nextLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#").attr("aria-label","Next"));
		var lastLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		$.each(result.data.navigatepageNums, function(index,item){
			var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href","#"));
			if(item == result.data.pageNum) {
				numLi.addClass("active");
			}
			numLi.click(function(){
				to_page(item);
			});
			ul.append(numLi);
		});
		if(result.data.hasNextPage == false) {
			nextLi.addClass("disabled");
			lastLi.addClass("disabled");
		}else {
			nextLi.click(function(){
				to_page(result.data.pageNum+1);
			});
			lastLi.click(function(){
				to_page(result.data.pages);
			});
		}
		ul.append(nextLi).append(lastLi);
		$("<nav></nav>").attr("aria-label","Page navigation").append(ul).appendTo("#page_info_nav");
	}
	
	function gotoRegister() {
		window.location.href="gotoRegister";

	}
	
	function gotoEdit(supplierId) {
		window.location.href="gotoEdit?supplierId="+supplierId;
	}
	
	function deleteSupplier(supplierId,pageNum) {
		if(confirm("是否要删除该记录")){
			$.ajax({
				url:"deleteSupplier",
				type:"get",
				data:"supplierId="+supplierId,
				success:function(result){
					alert(result.msg);
					to_page(pageNum);
				}
			});
		} else {
			return false;
		}
		
	}
</script>
</body>
</html>