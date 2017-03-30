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
<title>编辑字典类型</title>

	</head>
<body>
<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="dictType_form" method="post" >
	    <input type="hidden" id="createID" name="createID" value="${dictType.createID}"/>
		<table class="table">
			<tr>
				<td align="right" width="15%">上级类型：</td>
				<td width="35%">
					<input type="hidden" id="parentId" name="parentDictType.id" value="${dictType.parentDictType.id}" >
					<input type="text" id="parentName" name="parentDictType.dtName"  value="${dictType.parentDictType.dtName}" style="width:95%;" class="easyui-textbox input" />
				</td>
				<td align="right" width="15%">编码：</td>
				<td width="35%" >
					<input type="hidden" id="id" name="id" value="${dictType.id}">
					<input id="dtCode" name="dtCode" value="${dictType.dtCode}" class="easyui-textbox input" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td align="right"  width="15%">名称：</td>
				<td width="35%" colspan="3">
					<input id="dtName" name="dtName" value="${dictType.dtName}" class="easyui-textbox input" data-options="required:true" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">排序：</td>
				<td width="35%">
					<input id="sort" name="sort" maxLength="50" class="easyui-numberbox input" value="${dictType.sort}"
					data-options="required:true,min:0,precision:0,missingMessage:'数字类型,整数类型。'"/>
				</td>
				<td align="right" width="15%">状态：</td>
				<td width="35%">
				<%-- <input id="enable" name="enable" class="easyui-combobox comboInput" required="required" value="${dictType.enable}"
					data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=enable',editable:false,panelHeight:'auto'" /> --%>
				   <select id="enable" name="enable" required="required" style="width:100%;" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
					<option value="0"  <c:if test="${dictType.enable=='0' }">selected</c:if> >启用</option>
					 <option value="1"  <c:if test="${dictType.enable=='1' }">selected</c:if> >停用</option>
			       </select> 
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
