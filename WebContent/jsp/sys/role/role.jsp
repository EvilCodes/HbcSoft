<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
	var role_datagrid;
	var role_form=undefined;
	var role_dialog;
	var role_searchDialog;
	$(function() {
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		role_datagrid = $('#role_datagrid').datagrid({
			url : '${ctx}/sys/role/pageRoleList.hbc',
			fit:true,
			pagination:true,
			rownumbers:true,
			striped:true,
			fitColumns:true,
			singleSelect:true,
			pageSize:iPageSize,
			pageList:getPageListObj(iPageSize),
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				sortable:true
			}, {
				field : 'id',
				title : '主键',
				hidden : true,
				sortable:true
			}, {
				field : 'code',
				title : '角色编码',
				width : 100,
				halign : 'center',
				align : 'left',
				sortable:true
			}, {
				field : 'name',
				title : '角色名称',
				width : 150,
				halign : 'center',
				align : 'left',
				sortable:true
			}, {
				field : 'remark',
				title : '备注',
				width : 500,
				halign : 'center',
				align : 'left',
				sortable:true
			},{
				field : 'orderNo',
				title : '排序',
				width : 50,
				halign : 'center',
				align : 'left',
				sortable:true
			} ,{
				field : 'createTime',
				title : '创建时间',
				width : 120,
				halign : 'center',
				align : 'left',
				sortable:true,
				formatter : function(createTime) {
					var date = new Date(createTime);
					var y = date.getFullYear();
					var m = date.getMonth()+1;
					var d = date.getDate();
					 var hour = date.getHours();     
				    var minute = date.getMinutes();     
				    var second = date.getSeconds();
				    
					return y+"-"+m+"-"+d+" "+hour+":"+minute+":"+second ;
				}
			}
			]],
			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				$(this).datagrid('clearSelections');
				$(this).datagrid('unselectAll');
			},
			onClickRow : function(rowIndex, rowData) {
				$(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow:function(rowIndex,rowData){
				showDialog(5,rowData);
			}
		});
	});

	/**
	 * 查询
	 */
	 function showSearchDialog() {
			role_searchDialog = $("<div/>").dialog({
				title : '查询条件',
				top : 150,
				width : 700,
				modal : true,
				maximizable : false,
				iconCls : 'icon-find',
				href : "${ctx}/sys/role/roleSearch.hbc",
				buttons : [{
					text : '查询',
					iconCls : 'icon-search',
					handler : function() {
						role_datagrid.datagrid('reload',$.serializeObject($("#role_search_form")));//<form id="role_search_form">
						role_searchDialog.dialog('destroy');
					}
				},{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						role_searchDialog.dialog('destroy');
					}
				}],
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
	}
	/**
	 * 添加修改数据
	 */
	function showDialog(operateType,row){
		var hrefUrl='';
		if(row){
			hrefUrl='${ctx}/sys/role/roleInput.hbc?id='+row.id;
		}else{
			hrefUrl='${ctx}/sys/role/roleInput.hbc';
		}

		var title='';
		var icon='';
		if(operateType==1){
			title='添加';
			icon='icon-table';
		}else if(operateType==2){
			title='修改';
			icon='icon-table';
		}else if(operateType==5){
			title='查看';
			icon='icon-table';
		}
		
		role_dialog=$("<div/>").dialog(
				{
					title:title,
					top:150,
					width:700,
					modal:true,
					iconCls:icon,
					maximizable:false,
					href:hrefUrl,
					iconCls:'icon-userEdit',
					buttons:[{
						text:'保存',
						iconCls:'icon-save',
						handler:function() {
							role_form.submit();
						}
					},{
						text:'关闭',
						iconCls:'icon-cancel',
						handler:function() {
							role_dialog.dialog('destroy');
							$('#role_form').remove();
						}
					}],
					onBeforeLoad:function(){
					},
					onClose:function() {
						role_dialog.dialog('destroy');
					},
					onLoad : function() {
						formInit();
						if (operateType==5){
							$("form input").each(function(){
								$(this).attr("disabled",true);
							});
							$("form textarea").each(function(){
								$(this).attr("disabled",true);
							}); 
							$("#seeID").css('display', 'none');
						}
					}
				});
		//role_dialog.dialog('open');
	}

	/**
	 * 表单初始化
	 */
	function formInit() {
		role_form = $('#role_form').form(
				{
					url : '${ctx}/sys/role/roleSave.hbc',
					ajax : true,
					onSubmit : function() {
						$.messager.progress({
							title : '提示信息！',
							text : '数据处理中，请稍后....'
						});
						var isValid = $(this).form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(data) {
						$.messager.progress('close');
						var json = $.parseJSON(data);
						if (json.code == 1) {
							role_dialog.dialog('destroy');
							role_datagrid.datagrid('reload');
							eu.showMsg(json.msg);
						} else if (json.code == 2) {
							$.messager.alert('提示信息！', json.msg, 'warning',
								function() {
									if (json.obj) {
										$('#role_form input[name="'+json.obj+'"]').focus();
									}
								});
						} else {
							eu.showAlertMsg(json.msg, 'error');
						}
					}
				});
	}

	/**
	 * 修改数据
	 */
	function edit(operateType,rowIndex,rowData) {
		var rows=role_datagrid.datagrid('getSelections');
		var row=role_datagrid.datagrid('getSelected');
		if (row){
			if(rows.length>1) {
				row = rows[rows.length-1];
				eu.showMsg("您选择了多个操作对象，默认操作最后一次被选中的记录！");
			}
			showDialog(operateType,row);
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
	}
	//删除
	function del() {
			var rows = role_datagrid.datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确认提示！', '您确定要删除选中的所有行?', function(r) {
					if (r) {
	                     var ids="";
	             		for(var i = 0 ; i<rows.length; i++ ){
	             				ids=ids+ rows[i].id+",";
	             				 if (ids.substr(0,1)==','){
	             					ids=ids.substr(1);
	             				} 
	             		}
								$.post('${ctx}/sys/role/deleteRole.hbc', {
									"ids" : ids
								}, function(data) {
									if (data.code == 1) {
										role_datagrid.datagrid('load');
										refreshzTree();//刷新树
										eu.showMsg(data.msg);
									} else {
										eu.showAlertMsg(data.msg, 'error');
									}
								}, 'json');
					}
				});
			} else {
				eu.showMsg("请选择要操作的对象！");
			}
	}
	//设置资源
	function resourceMenu() {
		var rows = role_datagrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要操作的对象！', 'info');
			return false;
		}
		var updateRoleResourceUrl = "${ctx}/sys/role/updateRoleResource.hbc?id="+ rows[0].id;
		var resourceDialog = undefined;
		resourceDialog = $('<div/>').dialog(
				{
					title : '资源绑定',
					top : 20,
					width : 250,
					height : 400,
					modal : true,
					maximizable : false,
					href : '${ctx}/sys/role/roleResource.hbc?id='+ rows[0].id,
					buttons : [
							{
								id : 'optBtn',
								text : '选择',
								iconCls : 'icon-save',
								handler : function() {
									$(this).linkbutton('disable');
									var nodes = $.fn.zTree.getZTreeObj("resourceTree").getCheckedNodes(true);
									  var ids="";
					             		for(var i = 0 ; i<nodes.length; i++ ){
					             				ids=ids+ nodes[i].id+",";
					             				 if (ids.substr(0,1)==','){
					             					ids=ids.substr(1);
					             				} 
					             		}
									$.post(updateRoleResourceUrl, {
										ids : ids
									}, function(data) {
										if (data.code == 1) {
											role_datagrid.datagrid('load');
											eu.showMsg(data.msg);
										} else {
											eu.showAlertMsg(data.msg, 'error');
										}
									}, 'json');
									resourceDialog.dialog('destroy');
								}
							}, {
								text : '关闭',
								iconCls : 'icon-cancel',
								handler : function() {
									resourceDialog.dialog('destroy');
								}
							} ],
					onClose : function() {
						$(this).dialog('destroy');
					}
				}).dialog('open');
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="padding: 2px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>授权中心</span><em>&gt;</em><span>角色管理</span></h2>
	</div>
	<div data-options="region:'center',split:false,border:false" style="height: 100%; width: 100%; overflow-y: hidden;">
		<table id="role_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<td>
				<a id="addrole" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="showDialog();">添加</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="edit(2)">编辑</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user'" onclick="resourceMenu()">设置资源</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="showSearchDialog()">查询</a>
			</td>
		</tr>
	</table>
</div>