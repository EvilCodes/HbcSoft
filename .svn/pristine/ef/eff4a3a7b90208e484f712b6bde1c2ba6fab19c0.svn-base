<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	//$(function() {
		/* if ("${user.id}" == "") { //新增
			$("input[name=enable]:eq(0)").attr("checked", 'checked');//状态 初始化值
		} */
		//document.getElementById("isManager").value = 1;
	//});
	function chooseOrg() {
		var orgID = popupObject($('#deptId'), TYPE_OTHER);
		var orgName = popupObject($('#deptName'), TYPE_TEXTBOX);
		popupOrgTreeMenu(orgID, orgName, 1);

	};
</script>
<style>
.table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
	width: 100%;
}

.table td {
	border-width: 1px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>

<div data-option="region:'center'"
	style="padding: 2px; overflow: hidden;">
	<form id="user_form" method="post" novalidate>
		<table class="table">
			<tr>
				<td width="15%" align="right">所属机构：</td>
				<td width="40%"> 
				<input type="hidden" id="id" name="id" value="${user.id}">
				<input type="hidden" id="createID"
					name="createID" value="${user.createID }" /> <input type="hidden"
					id="deptId" name="org.id" value="${user.org.id}"> <input
					type="hidden" id="password" name="password"
					value="${user.password}"> <c:choose>
						<c:when test="${sessioninfo.user.isManager==0}">
							<input id="deptName" name="org.name" value="${user.org.name}"
								class="easyui-textbox input" style="width: 95%"
								data-options="editable:false,prompt:'选择输入...',missingMessage:'选择输入...',buttonIcon:'icon-select',onClickButton:function(){chooseOrg();}" />
						</c:when>
						<c:otherwise>
							<input id="deptName" name="org.name" disabled
								value="${user.org.name}" class="easyui-textbox input"
								style="width: 95%"
								data-options="editable:false,prompt:'选择输入...',missingMessage:'选择输入...',buttonIcon:'icon-select',onClickButton:function(){chooseOrg();}" />
						</c:otherwise>
					</c:choose></td>
				<td width="15%" align="right">登录名：</td>
				<td width="40%"><c:choose>
						<c:when test="${sessioninfo.user.isManager==0}">
							<input id="companyNameHk" name="companyNameHk"
								value="${sessioninfo.company.companyNameHk}_" type="hidden" />
							<c:if test="${user.loginName == null }">
								<input id="loginName" name="loginName"
									value="${sessioninfo.company.companyNameHk}_"
									class="easyui-textbox input" required="required"
									style="width: 95%" />
							</c:if>
							<c:if test="${user.loginName != null }">
								<input id="loginName" name="loginName" value="${user.loginName}"
									class="easyui-textbox input" required="required"
									style="width: 95%" />
							</c:if>
						</c:when>
						<c:otherwise>
							<input id="companyNameHk" name="companyNameHk"
								value="${sessioninfo.company.companyNameHk}_" type="hidden" />
							<c:if test="${user.loginName == null }">
								<input id="loginName" name="loginName" disabled
									value="${sessioninfo.company.companyNameHk}_"
									class="easyui-textbox input" required="required"
									style="width: 95%" />
							</c:if>
							<c:if test="${user.loginName != null }">
								<input id="loginName" name="loginName" disabled
									value="${user.loginName}" class="easyui-textbox input"
									required="required" style="width: 95%" />
							</c:if>
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>

			</tr>
			<tr>
				<td width="15%" align="right">姓名：</td>
				<td width="40%"><c:choose>
						<c:when test="${sessioninfo.user.isManager==0}">
							<input id=name name="name" style="width: 95%"
								value="${user.name}" data-options="required:true"
								class="easyui-textbox input">
						</c:when>
						<c:otherwise>
							<input id=name name="name" disabled style="width: 95%"
								value="${user.name}" data-options="required:true"
								class="easyui-textbox input">
						</c:otherwise>
					</c:choose></td>
				<td width="15%" align="right">性别：</td>
				<td width="40%">
					<!--  <input id="sex" name="sex"  value="${user.sex}"  style="width:95%" class="easyui-combobox comboInput" required="required" 
						data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=sex',editable:false,panelHeight:'auto'"/> -->
					<select id="sex" name="sex" required="required"
					style="width: 95%;" class="easyui-combobox"
					data-options="panelHeight:'auto',editable:false">
						<option value="0"
							<c:if test="${user.sex=='0' }">selected</c:if>>男</option>
						<option value="1"
							<c:if test="${user.sex=='1' }">selected</c:if>>女</option>
				</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">身份证号：</td>
				<td width="40%"><input type="text" id=identityNumber
					name="identityNumber" style="width: 95%"
					value="${user.identityNumber}" class="easyui-textbox input"
					required="required"></td>
				<td width="15%" align="right">email：</td>
				<td width="40%"><input id="email" name="email"
					style="width: 95%" value="${user.email}"
					class="easyui-textbox input"></td>
			</tr>
			<tr>
				<td width="15%" align="right">办公电话：</td>
				<td width="40%"><input id="tel" name="tel" value="${user.tel}"
					style="width: 95%" class="easyui-textbox input"></td>
				<td width="15%" align="right">移动电话：</td>
				<td width="40%"><input id="mobilephone" name="mobilephone"
					style="width: 95%" value="${user.mobilephone}"
					class="easyui-textbox input"></td>
			</tr>
			<tr>
				<td width="15%" align="right">普通借记卡开户行：</td>
				<td width="40%"><input id="depositBank" name="depositBank"
					style="width: 95%" value="${user.depositBank}"
					class="easyui-textbox input"></td>
				<td width="15%" align="right">普通借记卡：</td>
				<td width="40%"><input id="cardNumber" name="cardNumber"
					style="width: 95%" value="${user.cardNumber}"
					class="easyui-textbox input"></td>
			</tr>
			<tr>
				<td width="15%" align="right">公务卡开户行：</td>
				<td width="40%"><input id="pubServiceBank"
					name="pubServiceBank" style="width: 95%"
					value="${user.pubServiceBank}" class="easyui-textbox input">
				</td>
				<td width="15%" align="right">公务卡：</td>
				<td width="40%"><input id="businessCardNumber"
					name="businessCardNumber" style="width: 95%"
					value="${user.businessCardNumber}" class="easyui-textbox input">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">职务/职称：</td>
				<td width="40%">
					<input id="duty" name="duty"
					value="${user.duty}" class="easyui-combobox comboInput"
					style="width: 95%"
					data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=duty',editable:false,panelHeight:'auto'" />
				</td>
				<td width="15%" align="right">是否管理员：</td>
				<td width="40%">
					 <select id="isManager" name="isManager" class="easyui-combobox" style="width:95%;" data-options="panelHeight:'auto',editable:false" required="required">
								<option value="1"
									<c:if test="${user.isManager=='1' }">selected</c:if>>否</option>
								<option value="0"
									<c:if test="${user.isManager=='0' }">selected</c:if>>是</option>
				        </select> </td>
			</tr>
			<tr>
				<td width="15%" align="right">人员类型：</td>
				<td width="40%"><c:choose>
						<c:when test="${sessioninfo.user.isManager==0}">
							<input id="personType" name="personType"
								value="${user.personType}" class="easyui-combobox comboInput"
								style="width: 95%"
								data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=personType',editable:false,panelHeight:'auto'" />
						</c:when>
						<c:otherwise>
							<input id="personType" name="personType" disabled
								value="${user.personType}" class="easyui-combobox comboInput"
								style="width: 95%"
								data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=personType',editable:false,panelHeight:'auto'" />
						</c:otherwise>
					</c:choose></td>
				<td width="15%" align="right">所学专业：</td>
				<td width="40%"><input id="major" name="major"
					value="${user.major}" class="easyui-textbox input"
					style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%" align="right">状态：</td>
				<td width="40%"><c:choose>
						<c:when test="${sessioninfo.user.isManager==0}">
							<%--  <input id="enable" name="enable" value="${user.enable}" class="easyui-combobox comboInput" required="required" style="width:95%"
						data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=enable',editable:false,panelHeight:'auto'"/>  --%>
							<select id="enable" name="enable" required="required"
								style="width: 95%;" class="easyui-combobox"
								data-options="panelHeight:'auto',editable:false">
								<option value="0"
									<c:if test="${user.enable=='0' }">selected</c:if>>启用</option>
								<option value="1"
									<c:if test="${user.enable=='1' }">selected</c:if>>停用</option>
							</select>
						</c:when>
						<c:otherwise>
							<%-- <input id="enable" name="enable" disabled value="${user.enable}" class="easyui-combobox comboInput" required="required" style="width:95%"
						data-options="valueField:'dictCode',textField:'dictName',url:'${ctx}/sys/dataDict/getDataDictList.hbc?dictCode=enable',editable:false,panelHeight:'auto'"/> --%>
							<select id="enable" name="enable" required="required"
								style="width: 95%;" class="easyui-combobox"
								data-options="panelHeight:'auto',editable:false">
								<option value="0"
									<c:if test="${user.enable=='0' }">selected</c:if>>启用</option>
								<option value="1"
									<c:if test="${user.enable=='1' }">selected</c:if>>停用</option>
							</select>
						</c:otherwise>
					</c:choose></td>
				<td width="15%" align="right">列表条数：</td>
				<td width="40%"><input id="pageSize" name="pageSize"
					value="${user.pageSize}" style="width: 95%"
					class="easyui-numberbox input">
					<input type="hidden" id="state" name="state" value="${user.state}">
					<input type="hidden" id="isclone" name="isclone" value="${user.isclone}">
					<input type="hidden" id="orgName" name="orgName" value="${user.orgName}">
					<input type="hidden" id="cssPath" name="cssPath" value="${user.cssPath}">
					</td>
			</tr>
		</table>
	</form>
</div>

