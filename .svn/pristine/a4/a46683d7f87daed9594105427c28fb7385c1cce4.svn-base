<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div data-option="region:'center'"
	style="padding: 2px; overflow: hidden;">
	<form id="org_form" method="post" novalidate>

		<table class="table">
			<tr>
				<td align="right" width="15%">上级机构：</td>
				<td width="35%"><input type="hidden" id="id" name="id"
					value="${org.id}"> <input type="hidden" id="parentId"
					name="parentOrg.id" value="${org.parentOrg.id }"> <input
					id="parentName" name="parentOrg.name" value="${org.parentOrg.name}"
					class="easyui-textbox input" disabled style="width: 95%" /></td>
				<td align="right" width="15%">机构编码：</td>
				<td width="35%"><input id="code" name="code"
					value="${org.code}" class="easyui-textbox input"
					required="required" style="width: 95%" /></td>
			</tr>
			<tr>
				
				<td align="right" width="15%">机构名称：</td>
				<td width="35%"><input id="name" name="name"
					value="${org.name}" class="easyui-textbox input"
					required="required" style="width: 95%" /></td>
					<td align="right" width="15%">排序：</td>
				<td width="35%"><input id="orderNo" name="orderNo"
					value="${org.orderNo}" class="easyui-numberbox input"
					required="required" style="width: 95%" /></td>
			</tr>
			<tr>
				<td align="right">状态：</td>
				<td width="40%">
					<%-- <input id="enable" name="enable" class="easyui-combobox comboInput" required="required" value="${org.enable}"
					data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=enable',editable:false,panelHeight:'auto'" /> --%>
					<select id="enable" name="enable" required="required"
					style="width: 100%;" class="easyui-combobox"
					data-options="panelHeight:'auto',editable:false">
						<option value="0"
							<c:if test="${dictType.enable=='0' }">selected</c:if>>启用</option>
						<option value="1"
							<c:if test="${dictType.enable=='1' }">selected</c:if>>停用</option>
				</select>
				</td>
				<td align="right">操作员：</td>
				<td><input type="hidden" id="userId" name="createUser.id"
					value="${org.createUser.id}"> <input id="userName"
					name="createUser.name" value="${org.createUser.name}"
					class="easyui-textbox input" disabled style="width: 95%" />
					<input type="hidden" id="isClone" name="isClone" value="${org.isClone}">
				</td>
			</tr>
		</table>
	</form>
</div>
