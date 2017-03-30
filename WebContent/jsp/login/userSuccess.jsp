<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>注册成功</title>
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%-- <%@ include file="../../js/commOper_page.def"%> --%>
</head>
<!-- BEGIN BODY -->
<body class="login">
<h1>注册成功</h1>
<h1>您的用户名为：${loginName}<p style="color: red"> （注：用户名为公司英文简称+‘_admin’）</p></h1>
<c:if test="${state=='1'}">
	<h2>请查看您的邮箱 进行激活该账号！</h2>
</c:if>
<a href="${pageContext.request.contextPath}/login.hbc">首页</a>
</body>
<!-- END BODY -->
</html>