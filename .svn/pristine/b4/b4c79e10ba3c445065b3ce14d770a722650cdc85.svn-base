<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">/***人员列表*/
$(function (){
	var user_selectgrid;
	user_selectgrid=$('#user_selectgrid').datagrid({  
		url:'${ctx}/sys/user/checkUsersList.hbc',
		fit:true,//自适应父级容器的大小
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		striped:true,
		//singleSelect:true,
		pageSize:20,
		columns:[[
			{field:'ck',checkbox:true},
			{field:'id',hidden:true,},
			{field:'name',title:'姓名',width:80,halign:'center',align:'left'},
			{field:'loginName',title:'登录名',width:80,halign:'center',align:'left'},
			{field:'tel',title:'联系电话',width:80,halign:'center',align:'left'}
		]],
		toolbar:'#tb',
		onLoadSuccess:function(data){
			$(this).datagrid('clearSelections');
			$(this).datagrid('unselectAll');
		}
	});	
		//user_selectgrid.datagrid('reload', $.serializeObject($('#searchForm')));
});
	/**条件查询*/
	function searchForm(id) {
		alert("searchForm...");
		user_selectgrid.datagrid('reload', $.serializeObject($('#'+id)));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'center',border:false" style="overflow: hidden; height: 300px;padding: 2px;"><!-- 添加padding外边距 -->
		<table id="user_selectgrid"></table>
	</div>
	<%-- <div id="tb">
		<form id="searchForm">
			<input type="hidden" id="id" name="id" value="${user.id}">
			<table class="table">
				<tr>
					<td align="right" width="10%">姓名:</td>
					<td width="20%"><input class="easyui-textbox" style="width: 235px;" name="name" id="name"></td>
					<td width="10%" rowspan="2" align="left"><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-search'" onclick="searchForm('searchForm')">查询</a></td>
				</tr>
				<tr>
					<td align="right" width="10%">登录名:</td>
					<td width="20%"><input class="easyui-textbox" style="width: 235px;" name="loginName" id="loginName"></td>
					<td align="right" width="10%">手机号码:</td>
					<td width="30%"><input class="easyui-textbox" style="width: 150px;" name="tel" id="tel"></td>
				</tr>
			</table>
		</form>
	</div> --%>
</div>