<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

	</head>
<body>

<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="reportHead_form" method="post">
		<input type="hidden" id="createID" name="createID" value="${head.createID}"/>
		<input type="hidden" id="id" name="id" value="${head.id}">
			<input type="hidden" id="levelcount" name="levelcount" value="${head.levelcount}">
			<input type="hidden" id="sort" name="sort"  value="${head.sort}"/>
		<table class="table">
			<tr>
				<td align="right" width="15%">上级类型：</td>
				<td width="35%">
				 <c:if test="${!empty head.parentHeader.id}">
				    <input type="hidden" id="parentLevel" name="parentHeader.levelcount" value="${head.parentHeader.levelcount}" >
					<input type="hidden" id="parentId" name="parentHeader.id" value="${head.parentHeader.id}" >
					<input type="text" id="parentName" name="parentHeader.hname"  value="${head.parentHeader.hname}" disabled="disabled"  style="width:95%;" class="easyui-textbox input" />
				
				</c:if>  
				 <c:if test="${!empty head.rootConfig.id && empty head.parentHeader.id}"> 
					<input type="hidden" id="parentId" name="rootConfig.id" value="${head.rootConfig.id}" >
					<input type="text" id="parentName" name="rootConfig.reportName"  value="${head.rootConfig.reportName}" disabled="disabled"  style="width:95%;" class="easyui-textbox input" />
				 </c:if> 
				</td> 
				<td align="right" width="15%">头名称：</td>
				<td width="35%">
					<input id="hname" name="hname" value="${head.hname}" class="easyui-textbox input" data-options="required:true" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">字段：</td>
					<td width="35%" >
						<input id="field" name="field" value="${head.field}" class="easyui-textbox input" data-options="required:true" />
				</td>
				<td align="right"  width="15%">值：</td>
				<td width="35%" colspan="3">
					<input id="value" name="value" value="${head.value}" class="easyui-textbox input" data-options="required:true" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">报表名称：</td>
				<td width="35%">
					<%-- <input id="reportId" name="reportId"  class="easyui-combobox comboInput" value="${head.reportId}"
					data-options="valueField:'id',textField:'reportName',url:'${ctx}/report/head/getReportConfigList.hbc',editable:false,panelHeight:'auto',required:true"/> --%>
					<input type="hidden" id="reportId" name="reportId" value="${head.reportId}" >
					<input type="text" id="reportName" name="rootConfig.reportName"  value="${head.rootConfig.reportName}" disabled="disabled"  style="width:95%;" class="easyui-textbox input" />
				</td>
			</tr>
		</table>
	</form>
 </div>
 </body>

