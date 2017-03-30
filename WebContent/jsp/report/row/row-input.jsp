<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
<title>表体配置</title>
</head>
<body>
<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="row_form" method="post" >
		<input type="hidden" id="createID" name="createID" value="${reportRow.createID}"/>
		<input type="hidden" id="id" name="id" value="${reportRow.id}">
		<input type="hidden" id="levelcount" name="levelcount" value="${reportRow.levelcount}">
		<input type="hidden" id="sort" name="sort" value="${reportRow.sort}">
		<input type="hidden" id="status" name="status" value="${reportRow.status}">
		<input type="hidden" id="isEnd" name="isEnd" value="${reportRow.isEnd}">
		<table class="table">
			<tr>
				<td align="right" width="15%">上级类型：</td>
				<td width="30%">
				 <c:if test="${!empty reportRow.parentRepRow.id}">
					<input type="hidden" id="parentId" name="parentRepRow.id" value="${reportRow.parentRepRow.id}" >
					<input type="hidden" id="pLevelcount" name="parentRepRow.levelcount" value="${reportRow.parentRepRow.levelcount}">
					<input id="parentName" name="parentRepRow.hname" value="${reportRow.parentRepRow.hname}" disabled="disabled"
					style="width:95%" class="easyui-textbox input" data-options="required:true,editable:false" />
				</c:if>  
				 <c:if test="${!empty reportRow.config.id && empty reportRow.parentRepRow.id}">
					<input type="hidden" id="parentId" name="config.id" value="${reportRow.config.id}" >
					<input type="text" id="parentName" name="config.reportName" value="${reportRow.config.reportName}" disabled="disabled"
					style="width:95%" class="easyui-textbox input" data-options="required:true,editable:false" />
				</c:if>  
				</td>
				<td align="right"  width="15%">表体名称：</td>
				<td width="40%" colspan="3">
					<input id="name" name="hname" value="${reportRow.hname}" class="easyui-textbox input" data-options="required:true" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">字段名：</td>
				<td width="40%" >
					<input id="filed" name="filed" value="${reportRow.filed}" class="easyui-textbox input" data-options="required:true" />
				</td>
				<td align="right"  width="15%">值：</td>
				<td width="80%" colspan="3">
					<input id="value" name="value" value="${reportRow.value}" class="easyui-textbox input" data-options="required:false" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">报表名称：</td>
				<td width="40%">
				<input type="hidden"id="reportId" name="reportId" value="${reportRow.reportId}" />
				<input type="text" id="reportName" name="config.reportName"  value="${reportRow.config.reportName}" 
				disabled="disabled" style="width:95%;" class="easyui-textbox input" />
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
