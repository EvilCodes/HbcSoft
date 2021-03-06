<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/workflow.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/tagDemo" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<title>编辑页面</title>
	<!--[if lt IE 9]>
		<script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]--> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width-device-width,initial-scale-1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/My97DatePicker/skin/WdatePicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/ztree.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/themes/icon.css"/>
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath}/js/format-number.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/template/template.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/commonPopup.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/ztree.js" charset="utf-8"></script>
</head>
<body>
	<div class="container">
	<div class="row">
	<form id="form" action="${pageContext.request.contextPath}/template/save.hbc" method="post">
		<!-- 固定按钮区 -->
		<div class="btn-group">
			<a href="#" class="btn btn-default" onclick="closeForm('${pageContext.request.contextPath}')">关闭</a>
			<a href="#" class="btn btn-default" onclick="saveForm('${pageContext.request.contextPath}')">保存</a>
		</div>
		<!-- 自定义按钮区 -->
		<div class="btn-group">
			<c:forEach items="${lstButton }" var="lButton" varStatus="status">
				<a href="#" class="btn btn-default">关闭</a>
			</c:forEach>
		</div>
		<!-- 固定隐藏区 -->
		<div>
			<input type="hidden" id="formType" name="formType" value="${formType }">
		</div>
		<!-- 隐藏区 -->
		<div>
			<c:forEach items="${lstHidden }" var="lHidden" varStatus="status">
				<h:demo nameField="${lHidden.fieldName}" 
						valueField="${lHidden.inputValue}" 
						inputIsDisplay="${lHidden.inputIsDisplay}"
						isModify="${lHidden.isModify}"
						isRequired="${lHidden.isRequired}"
						sourceContent="${lHidden.sourceContent}"
						sourceMode="${lHidden.sourceMode}"
						tagType="${lHidden.inputType}"
						titleField="${lHidden.title}" 
						number="${lHidden.number}"
						clickInfo="${lHidden.clickInfo}"
						clickValue="${lHidden.clickValue}"
						decimalDigits="${lHidden.decimalDigits}"
						/>
						
			</c:forEach>
		</div>
		
		<!-- 编辑区 -->
		<div class="row">
			<c:forEach items="${lstEdit }" var="lEdit" varStatus="status">
				<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<h:demo nameField="${lEdit.fieldName}" 
						valueField="${lEdit.inputValue}" 
						inputIsDisplay="${lEdit.inputIsDisplay}"
						isModify="${lEdit.isModify}"
						isRequired="${lEdit.isRequired}"
						sourceContent="${lEdit.sourceContent}"
						sourceMode="${lEdit.sourceMode}"
						tagType="${lEdit.inputType}"
						titleField="${lEdit.title}" 
						number="${lEdit.number}"
						clickInfo="${lEdit.clickInfo}"
						clickValue="${lEdit.clickValue}"
						decimalDigits="${lEdit.decimalDigits}"
						className="form-control"
					/>
				</div>
			</c:forEach>
		</div>	
	</form>
	</div>
	</div>
</body>
</html>
