<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>警告异常和错误</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/warn.css">  <!-- 引用样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">  <!-- 引用bootstrap默认样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">  <!-- 引用font-awesome字体图标 -->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="top">
				<c:if test="${code >= 0 && code < 500}">
				<img src="${pageContext.request.contextPath}/image/warning.png" alt="">
				</c:if>
				<c:if test="${code >= 500 && code < 999}">
				<img src="${pageContext.request.contextPath}/image/wrong.png" alt="">
				</c:if>
				<c:if test="${code >= 999}">
				<img src="${pageContext.request.contextPath}/image/abnormal.png" alt="">
				</c:if>
				<div class="title">
					<h1>您的访问出错了!</h1>
					<h2>${message }</h2>
				</div>
				<div class="clear"></div>
			</div>
			<div class="describe">
				<h3>没有发现您要找的页面，经专家研究结果如下：</h3>
				<p>1、请检查您输入的网址是否正确。</p>
				<p>2、电信网通那头接口生锈了。</p>
				<p>3、出现了其他错误，请注意上面的错误提示，有问题请和我们联系。</p>
			</div>
		</div>
	</div>
</body>
</html>