<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>点选页面管理修改页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(
		function() {
			var mess = '${message}';
			if (mess == "0") {
				alert("提交成功");
				window.location.href = "${pageContext.request.contextPath}/clickManage/queryClick.hbc";
			} else if (mess == "1") {
				alert("当前Key值已存在，请修改！");
			} else if (mess == "2") {
				alert("修改失败，请重新进入此页面进行修改！");
			}
		});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>修改点选页面管理数据</h3>
			<hr>
		<%-- <form class="bs-example bs-example-form" role="form"
				action="${pageContext.request.contextPath}/createTable.hbc" method="post"> --%>
		<form class="form-horizontal" role="form" id="form" 
			action="${pageContext.request.contextPath}/clickManage/editClickSubmit.hbc" method="post">
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="captions">
						<h3 class="panel-title">点选页面管理数据</h3>
					</div>
					<div class="actions">
						<div class="btn-group">
							<button type="submit" class="btn btn-default icons">保存</button>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">Key值</label>
							<div class="col-md-11 col-sm-10">
								<input type="hidden" id="cid" name="cid" value="${id}"/>
								<input type="hidden" id="key" name="key" value="${key}"/>
								<input type="text" class="form-control" name="keys" id="keys" value="${key}">
							</div>
					</div>
					<div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">Value值</label>
							<div class="col-md-11 col-sm-10">
								<input type="text" class="form-control" name="values" id="values" value="${values}">
							</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</div>
</body>
</html>