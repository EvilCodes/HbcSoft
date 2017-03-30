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
<title>Excel导入</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/fileinput.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/fileinput.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/html5shiv.min.js"></script>
<script type="text/javascript">
	//下载模版
	function openIns(flag){
		var val = flag;
		window.open('${pageContext.request.contextPath}/createExcel/excelDownload.hbc?fileName='+flag);
	}
	//excel文件导入
	function onUpload(value){
		var val = value;
		$("#fromFile").attr("action","${pageContext.request.contextPath}/createExcel/upload.hbc?buttonName="+val);
		$("#fromFile").submit();
	}
</script>
<style type="text/css">
	.file-preview {
			display: none;
		}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>Excel导入</h2>
			<hr>
			<!-- <div class="fileup" style="width: 50%;">
				<form action="#" enctype="multipart/form-data"  method="post" id="fromFile">
					<input id="input-2" name="input2[]" type="file" class="file" multiple data-show-upload="false" data-show-caption="true">
				</form>
			</div> -->
			<form action="#" enctype="multipart/form-data"  method="post" id="fromFile">
					<input id="input-2" name="file" type="file" class="file" multiple data-show-upload="false" data-show-caption="true">
			</form>
			<hr>
			<div class="col-lg-3 col-md-3 col-sm-4 dl-item">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<h3 class="panel-title">创建数据库表</h3>
					</div>
					<div class="panel-body">
						<ol>
							<li><a href="#" onClick="openIns('CREATETABLEENTITY')">下载模板</a></li>
							<li><a href="#" onClick="onUpload('创建数据库表')">导入模板</a></li>
						</ol>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-4 dl-item">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<h3 class="panel-title">创建表单</h3>
					</div>
					<div class="panel-body">
						<ol>
							<li><a href="#" onClick="openIns('CREATEFROM')">下载模板</a></li>
							<li><a href="#" onClick="onUpload('创建表单')">导入模板</a></li>
						</ol>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-4 dl-item">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<h3 class="panel-title">合并</h3>
					</div>
					<div class="panel-body">
						<ol>
							<li><a href="#" onClick="openIns('CREATETABLEANDFROM')">下载模板</a></li>
							<li><a href="#" onClick="onUpload('合并')">导入模板</a></li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function () {
		    var message = '${message}';
		    if(message != "")
		    	alert(message);
		});
	</script>
</body>
</html>