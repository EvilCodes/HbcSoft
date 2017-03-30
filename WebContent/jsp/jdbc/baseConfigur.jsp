<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>海博诚架构</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<meta name="viewport" content="width-device-width,initial-scale-1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<%-- <%@ include file="../../js/commOper_page.def" %> --%>
</head>
<div class="container">
	<div class="row">
		<form role="form" class="form-horizontal col-md-8 col-sm-12 col-xs-12 col-md-offset-1" commandName="user" modelAttribute="user" id="baseConfigur"
				action="${pageContext.request.contextPath}/baseConfigurSubmit.hbc" method="post">
			<h3 class="form-title">创建链接数据库信息</h3>
			<hr>
			<div class="form-group">
				<label for="drive" class="control-label">driverClassName:</label>
				<input class="form-control" type="text" placeholder="请输入驱动" id="driverClassName" name="driverClassName" value="${driverClassName}" />
			</div>
			<div class="form-group">
				<label for="url" class="control-label">url:</label>
				<input class="form-control placeholder-no-fix" type="text" placeholder="请输入路径" id="url" name="url" value="${url}" />
			</div>
			<div class="form-group">
				<label for="user" class="control-label">username:</label>
				<input class="form-control" type="text" placeholder="请输入数据库用户名" id="username" name="username" value="${username}" />
			</div>
			<div class="form-group">
				<label for="password" class="control-label">password</label>
				<input class="form-control" type="password" autocomplete="off" placeholder="请输入数据库密码" id="password" name="password" value="${password}"/>
			</div>
			<div class="form-group">
				<label for="initial" class="control-label">initialSize:</label>
				<input class="form-control" type="text" placeholder="请输入初始连接数" id="initialSize" name="initialSize" value="${initialSize}" />
			</div>
			<div class="form-group">
				<label for="active" class="control-label">maxActive:</label>
				<input class="form-control" type="text" placeholder="请输入最大连接数" id="maxActive" name="maxActive" value="${maxActive}" />
			</div>
			<div class="form-group">
				<label for="maxldle" class="control-label">maxIdle:</label>
				<input class="form-control" type="text" placeholder="请输入最大空闲连接数" id="maxIdle" name="maxIdle" value="${maxIdle}" />
			</div>
			<div class="form-group">
				<label for="minldle" class="control-label">minIdle:</label>
				<input class="form-control" type="text" placeholder="请输入最小空闲连接数" id="minIdle" name="minIdle" value="${minIdle}" />
			</div>
			<div class="btnlast">
				<button type="submit" class="btn btn-primary">保存</button>
				<a href="javascript:void(0);" onclick="back();" class="btn btn-default">取消</a>
			</div>
		</form>
	</div>
	<!-- END LOGIN FORM -->
</div>
<!-- END LOGIN -->
</body>
<!-- END BODY -->
</html>
