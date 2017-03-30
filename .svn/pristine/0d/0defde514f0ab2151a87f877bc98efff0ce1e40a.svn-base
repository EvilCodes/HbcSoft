<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/meta.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
	var log_datagrid;
	var log_search_form;
	var log_keepTime_form;
	var log_keepTime_dialog;
	var log_searchDialog;
	$(function () {
		log_datagrid = $('#log_datagrid').datagrid({
			url: '${ctx}/sys/log/pageDictList.hbc',
			fit:true,
			pagination: true,//底部分页
			pagePosition: 'bottom',//'top','bottom','both'.
			fitColumns:true,
			striped: true,//显示条纹
			pageSize: 20,//每页记录数
			singleSelect: false,//单选模式
			rownumbers: true,//显示行数
			checkbox: true,
			nowrap: false,
			border: false,
			remoteSort : false,
			idField: 'id',
			frozenColumns:[[
				{field: 'type',title: '日志类型',width: 100,halign:'center',align:'center',
					formatter:function(value,rowData,rowIndex){
						if(value==0){
							return "安全日志";
						}else{
							return "操作日志";
						}
					}
				},
				{field: 'loginName',title: '登录名',width: 100,halign:'center'}
			]],
			columns: [ [
				{field: 'id',title: '主键',hidden: true,sortable: true,align: 'right', width: 80,halign:'center' },
				{field: 'action',title: '操作方法',width:200,halign:'center',sortable:true},
				{field: 'ip',title: '用户IP地址',width:250,halign:'center',sortable:true}, 
				{field: 'content',title: '操作内容',width:1000,halign:'center',sortable:true},
				{field: 'operTime',title: '操作时间',width: 200,halign:'center',align:'center',sortable:true}
			]],
			toolbar:[{
				text:'查询',
				iconCls:'icon-search',
				handler:function(){
					showSearchDialog();
				}
			}],
			onRowContextMenu: function (e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#log_menu').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		}).datagrid("showTooltip");
	});

	/*查询*/
	function showSearchDialog(){
		log_searchDialog=$("<div/>").dialog({
			title:'查询条件',
			top:20,
			width : 500,
			modal : true,
			maximizable:true,
			href : "${ctx}/sys/log/search.hbc",
			buttons : [ {
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					log_datagrid.datagrid('reload',$.serializeObject($("#log_search_form")));
					log_searchDialog.dialog('destroy');
				}
			},{
				text : '关闭',
				iconCls : 'icon-no',
				handler : function() {
					log_searchDialog.dialog('destroy');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		}).dialog('open');
	}
</script>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>系统设置</span><em>&gt;</em><span>日志管理</span></h2>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
	<table id="log_datagrid"></table>
	</div>
</div>