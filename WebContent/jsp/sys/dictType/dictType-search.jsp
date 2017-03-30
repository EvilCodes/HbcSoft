<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询字典类型</title>

	</head>
<body> --%>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="dictType_search_form">
		<table class="grid" style="width: 100%">
			<tr>
				<td align="right" width="10%">编码：</td>
				<td width="40%"><input name="dtCode" class="easyui-textbox input" style="width:95%"/></td>
				<td align="right" width="10%">名称：</td>
				<td width="40%"><input name="dtName" class="easyui-textbox input" style="width:95%"/></td>
			</tr>
		</table>
	</form>
</div>
<!-- </body> -->