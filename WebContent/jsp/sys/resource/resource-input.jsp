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
<title>资源类型</title>
</head>
<body>
<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="resource_form" method="post" >
		<table class="table">
			<tr>
				<td align="right" width="15%">上级资源：</td>
				<td width="30%">
				<input type="hidden" id="groupId" name="groupId" value="${resource.groupId}">
					<input type="hidden" id="companyId" name="companyId" value="${resource.companyId}">
					<input type="hidden" id="pId" name="parentResourceId" value="${resource.parentResourceId}">
					<input type="hidden" id="id" name="id" value="${resource.id}">
					<input type="hidden" id="parentResourceId" name="parentResource.id" value="${resource.parentResource.id}" >
					<c:if test="${resource.companyId=='0'}">
						<input id="parentName" name="parentResource.name" value="${resource.parentResource.name}" disabled="disabled" style="width:95%" class="easyui-textbox input"
					    data-options="buttonIcon:'icon-select',editable:false,onClickButton:function(){popupTreeMenu();}">
				    </c:if>
				    <c:if test="${resource.companyId!='0' }">
						<input id="parentName" name="parentResource.name" value="${resource.parentResource.name}" style="width:95%" class="easyui-textbox input"
					    data-options="buttonIcon:'icon-select',editable:false,onClickButton:function(){popupTreeMenu();}">
				    </c:if>
				</td>
				<td align="right"  width="15%">资源类型：</td>
				<td width="40%">
					<%-- <input type="hidden" id="id" name="id" value="${resource.id}"> --%>
				  <select id="resourceType" name="resourceType" class="easyui-combobox comboInput"
				  style="width:100%;" data-options="panelHeight:'auto',editable:false" required="required">
					<option value="0" <c:if test="${resource.resourceType=='0' }">selected</c:if> >菜单</option>
					<option value="1" <c:if test="${resource.resourceType=='1' }">selected</c:if> >按钮</option>
			       </select>
			    </td>
			</tr>
			<tr>
				<td align="right" width="15%">资源编码：</td>
				<td width="40%" >
					<input id="code" name="code" value="${resource.code}" class="easyui-textbox input" data-options="required:true" />
				</td>
				<td align="right"  width="15%">资源名称：</td>
				<td width="40%" colspan="3">
					<input id="name" name="name" value="${resource.name}" class="easyui-textbox input" data-options="required:true" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right"  width="15%">资源路径：</td>
				<td width="80%" colspan="3">
					<input id="url" name="url" value="${resource.url}" class="easyui-textbox input" data-options="required:false" style="width:95%">
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">排序：</td>
				<td>
					<input id="orderNo" name="orderNo" maxLength="50" class="easyui-numberbox input" value="${resource.orderNo}"
					data-options="required:true,min:0,precision:0,missingMessage:'数字类型,整数类型。'"/>
				</td>
				<td align="right" width="45%">状态：</td>
				<td width="45%">
				   <select id="enable" name="enable" class="easyui-combobox" style="width:100%;" data-options="panelHeight:'auto',editable:false" required="required" >
					<option value="0"  <c:if test="${resource.enable=='0' }">selected</c:if> >启用</option>
					 <option value="1"  <c:if test="${resource.enable=='1' }">selected</c:if> >停用</option>
			       </select>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
