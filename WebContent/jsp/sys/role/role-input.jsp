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
<title>编辑角色</title>

</head>
<body>
<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="role_form" method="post" novalidate>
		<table class="table">
			<tr>
				<td align="right" width="15%">角色编码：</td>
				<td width="40%">
				    <input type="hidden" id="id" name="id" value="${role.id}">
					<input id="code" name="code" value="${role.code}" class="easyui-textbox input" required="required" style="width:95%"/>
				</td>
				<td align="right" width="15%">角色名称：</td>
				<td width="40%">
					<input id="name" name="name" value="${role.name}" class="easyui-textbox input" required="required" style="width:95%"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">排序：</td>
				<td width="40%">
					<input id="orderNo" name="orderNo" value="${role.orderNo}" class="easyui-numberbox input" required="required" style="width:95%"/>
				</td>
				<%-- <td align="right" width="15%">创建时间：</td>
				<td width="40%">
					<input id="createTime" name="createTime" value="${role.createTime}" class="easyui-textbox input" disabled='disabled' style="width:95%"/>
				</td> --%>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td colspan="3">
					<textarea maxLength="255" name="remark" 
					style="position:relative;resize:none;height:75px;width:100%">${role.remark}</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
