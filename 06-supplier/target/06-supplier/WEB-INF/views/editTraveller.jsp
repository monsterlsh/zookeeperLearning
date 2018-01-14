<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>员工信息</title>
<!-- web路径
不以/开始的相对路径，找资源，以当前的资源路径为基准，经常容易出问题
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:8080),经常加上项目名称
	http://localhost:8080/crud
	bootatrap.min.js依赖于jquery。min。js，所以应该放在其后面,jquery版本需要1.9以上
-->
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
 span{
  color:red;
  font-weight: bold;
 }
</style>
</head>
<body>

<!-- 搭建显示页面 -->
<div class="container">
<!-- 标题栏 -->
<div class="row">
	<div class="col-md-12">
		<h1>内部供应商修改</h1>
	</div>
</div>
<!-- 供应商注册信息 -->
  <div class="row">
    <div class="col-md-12">
      <form class="form-horizontal" id="save_traveller" action="saveRegister" method="post">
        <div class="form-group">
          <label for="travellerName_add_input" class="col-sm-3 control-label">客户姓名</label>
          <div class="col-sm-5">
            <input type="hidden" name="travellerName" value="${traveller.travellerName}">
            <input type="text" class="form-control" name="travellerName" id="travellerName_add_input" value="${traveller.travellerName}">
          </div>
          <div class="col-sm-1"><span>*</span>
          </div>
        </div>

        <div class="form-group">
          <label for="email_add_input" class="col-sm-3 control-label">邮件</label>
          <div class="col-sm-5">
            <input type="hidden" name="email" value="${traveller.email}">
            <input type="text" class="form-control" name="email" id="email_add_input" value="${traveller.email}">
          </div>
          <div class="col-sm-1"><span>*</span>
          </div>
        </div>
        <div class="form-group">
          <label for="phone_add_input" class="col-sm-3 control-label">电话号码</label>
          <div class="col-sm-5">
          <input type="hidden" name="phone" value="${traveller.phone}">
          <input type="text" class="form-control" name="phone" id="phone_add_input" value="${traveller.phone}">
        </div>
          <div class="col-sm-1"><span>*</span>
          </div>
        </div>

        <div class="form-group">
          <label for="orderTime_add_input" class="col-sm-3 control-label">入住时间</label>
          <div class="col-sm-5">
            <input type="hidden" name="orderTime" value="${traveller.orderTime}">
            <input type="text" class="form-control" name="orderTime" id="orderTime_add_input" value="${traveller.orderTime}">
          </div>
          <div class="col-sm-1"><span>*</span>
          </div>
        </div>
        <div class="form-group">
          <label for="leaveTime_add_input" class="col-sm-3 control-label">离开时间</label>
          <div class="col-sm-5">
            <input type="hidden" name="leaveTime" value="${traveller.leaveTime}">
            <input type="text" class="form-control" name="leaveTime" id="leaveTime_add_input" value="${traveller.leaveTime}">
          </div>
          <div class="col-sm-1"><span>*</span>
          </div>
        </div>


        <!--div class="form-group">
          <label for="roomkind_add_input" class="col-sm-3 control-label">房间种类</label>
          <div class="col-sm-5">
            <select class="form-control" name="taxpayerProperty" id="roomkind_add_input" >
              <option value="1">标间</option>
              <option value="2">双人</option>
              <option value="3">大床</option>
            </select>
          </div>
        </div-->

        <div class="form-group">
          <div class="col-md-4 col-md-offset-4">
            <button class="btn btn-primary" id="traveller_save_btn" type="submit">保存</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
	
/* 	function savesupplier(){
		//2、发送ajax请求保存员工
		alert(11);
		$.ajax({
			url:"saveSupplier",
			type:"post",
			data:$("#save_suplier").serialize(),
			success:function(result){
				alert(result.msg);
			},
			error:function(){
				alert("error");
			}
		});
	}
	 */
</script>
</body>
</html>