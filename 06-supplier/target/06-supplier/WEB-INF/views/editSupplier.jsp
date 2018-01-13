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
<form class="form-horizontal" id="save_suplier" action="saveSupplier" method="post">
  <div class="form-group">
    <label for="supplierName_add_input" class="col-sm-3 control-label">供应商名称</label>
    <div class="col-sm-5">
    <input type="hidden" name="supplierId" value="${supplier.supplierId}">
      <input type="text" class="form-control" name="supplierName" id="supplierName_add_input" value="${supplier.supplierName}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="supplierShortname_add_input" class="col-sm-3 control-label">供应商简称</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="supplierShortname" id="supplierShortname_add_input" value="${supplier.supplierShortname}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="legalPerson_add_input" class="col-sm-3 control-label">法定代表人</label>
    <div class="col-sm-5">
	  <input type="text" class="form-control" name="legalPerson" id="legalPerson_add_input" value="${supplier.legalPerson}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="financing_add_input" class="col-sm-3 control-label">注册资金</label>
    <div class="col-sm-5">
	  <input type="text" class="form-control" name="financing" id="financing_add_input" value="${supplier.financing}">
    </div>
        <div class="col-sm-1"><span>*</span>(万元)
    </div>
  </div>
  <div class="form-group">
    <label for="businessContacter_add_input" class="col-sm-3 control-label">业务联系人</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="businessContacter" id="businessContacter_add_input" value="${supplier.businessContacter}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="telephone_add_input" class="col-sm-3 control-label">联系电话</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="telephone" id="telephone_add_input" value="${supplier.telephone}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="address_add_input" class="col-sm-3 control-label">联系地址</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="address" id="address_add_input" value="${supplier.address}">
    </div>
  </div>
  <div class="form-group">
    <label for="openBank_add_input" class="col-sm-3 control-label">开户行</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="openBank" id="openBank_add_input" value="${supplier.openBank}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="bankCode_add_input" class="col-sm-3 control-label">开户行账号</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="bankCode" id="bankCode_add_input" value="${supplier.bankCode}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="businessLicense_add_input" class="col-sm-3 control-label">营业执照编号</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="businessLicense" id="businessLicense_add_input" value="${supplier.businessLicense}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="taxCertificate_add_input" class="col-sm-3 control-label">税务登记证编号</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="taxCertificate" id="taxCertificate_add_input" value="${supplier.taxCertificate}">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="taxCertificate_add_input" class="col-sm-3 control-label">证书到期时间</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="certificateTime" id="certificate_time"
      		value="<fmt:formatDate value="${supplier.certificateTime }" pattern="yyyy-MM-dd"/>"
			onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
    </div>
        <div class="col-sm-1"><span>*</span>
    </div>
  </div>
  <div class="form-group">
    <label for="taxpayerProperty_add_input" class="col-sm-3 control-label">纳税人性质</label>
    <div class="col-sm-5">
    <select class="form-control" name="taxpayerProperty" id="taxpayerProperty_add_input">
    <c:if test="${supplier.taxpayerProperty==1}">
    	<option value="1" selected="selected">自然人</option>
	 	<option value="2">个体工商户</option>
	 	<option value="3">法人</option>
    </c:if>
	  <c:if test="${supplier.taxpayerProperty==2}">
    	<option value="1">自然人</option>
	 	<option value="2" selected="selected">个体工商户</option>
	 	<option value="3">法人</option>
    </c:if>
    <c:if test="${supplier.taxpayerProperty==3}">
    	<option value="1">自然人</option>
	 	<option value="2">个体工商户</option>
	 	<option value="3" selected="selected">法人</option>
    </c:if>
	</select>
    </div>
  </div>
  <div class="form-group">
    <label for="businessScope_add_input" class="col-sm-3 control-label">经营范围</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" name="businessScope" id="businessScope_add_input" value="${supplier.businessScope}">
    </div>
  </div>
  <div class="form-group">
    <label for="companyProfile_add_input" class="col-sm-3 control-label">公司简介</label>
    <div class="col-sm-6">
      <textarea rows="3" class="form-control" name="companyProfile" id="companyProfile_add_input">${supplier.companyProfile}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="servicePromise_add_input" class="col-sm-3 control-label">服务承诺</label>
    <div class="col-sm-6">
      <textarea rows="3" class="form-control" name="servicePromise" id="servicePromise_add_input">${supplier.servicePromise}</textarea>
    </div>
  </div>
  <div class="form-group">
    <label for="applicationOption_add_input" class="col-sm-3 control-label">申请意见</label>
    <div class="col-sm-6">
      <textarea rows="3" class="form-control" name="applicationOption" id="applicationOption_add_input">${supplier.applicationOption}</textarea>
    </div>
  </div>
   <div class="form-group">
   <div class="col-md-4 col-md-offset-4">
    	<button class="btn btn-primary" id="supplier_save_btn" type="submit">保存</button>
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