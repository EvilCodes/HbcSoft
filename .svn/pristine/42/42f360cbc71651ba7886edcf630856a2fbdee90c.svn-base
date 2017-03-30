<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "${ctx}/sys/dictType/treegrid.hbc"
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#dataDict_datagrid').datagrid('options').url = "${ctx}/sys/dataDict/pageDictList.hbc?dtId="
						+ treeNode.id;
				$('#dataDict_datagrid').datagrid('reload', {
					dictCode : '',
					dictName : ''
				});
				if (!treeNode.isParent) {
					$('#addDataDict').linkbutton('enable');
				} else {
					$('#addDataDict').linkbutton('disable');
				}
			}
		}
	};

	var dataDict_datagrid;
	var dataDict_form;
	var dataDict_dialog;
	var dataDict_searchDialog;

	$(function() {
		$.fn.zTree.init($("#dataDict_tree"), setting);
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		dataDict_datagrid = $('#dataDict_datagrid').datagrid({
			url : "${ctx}/sys/dataDict/pageDictList.hbc",
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			striped : true,
			pageSize : iPageSize,
			pageList : getPageListObj(iPageSize),
			remoteSort : false,
			idField : 'id',
			iconCls : 'icon-application-view-columns',
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				sortable : true
			}, {
				field : 'id',
				title : '主键',
				hidden : true,
				sortable : true
			}, {
				field : 'dictType.dtName',
				title : '字典类型',
				width : 120,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'dictCode',
				title : '字典编码',
				width : 120,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'dictName',
				title : '字典名称',
				width : 200,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'sort',
				title : '排序',
				width : 120,
				halign : 'center',
				align : "center",
				sortable : true
			}, {
				field : 'enable',
				title : '状态',
				width : 120,
				halign : 'center',
				align : "center",
				sortable : true,
				formatter : function(value, row, index) {
					if (value == 0) {
						return "启用";
					} else {
						return "停用";
					}
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				$(this).datagrid('clearSelections');
				$(this).datagrid('unselectAll');
			},
			onClickRow : function(rowIndex, rowData) {
				$(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow : function(rowIndex, rowData) {
				showDialog(5, rowData);
			}
		}).datagrid('showTooltip');
		$('#addDataDict').linkbutton('disable');
	});

	function showSearchDialog() {
		dataDict_searchDialog = $("<div/>")
				.dialog(
						{
							title : '查询条件',
							top : 150,
							width : 700,
							modal : true,
							maximizable : false,
							iconCls : 'icon-find',
							href : "${ctx}/sys/dataDict/search.hbc",
							buttons : [
									{
										text : '查询',
										iconCls : 'icon-search',
										handler : function() {
											dataDict_datagrid
													.datagrid(
															'reload',
															$
																	.serializeObject($("#dataDict_search_form")));
											dataDict_searchDialog
													.dialog('destroy');
										}
									},
									{
										text : '关闭',
										iconCls : 'icon-cancel',
										handler : function() {
											dataDict_searchDialog
													.dialog('destroy');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								formInit();
								if (row) {
									dataDict_form.form('myLoad',
											'${ctx}/sys/dataDict/dataDict!getDataDictById.action?id='
													+ row.id);
								}
							}
						});
	}

	function showDialog(operateType, row) {
		var hrefUrl = '';
		if (row) {
			hrefUrl = '${ctx}/sys/dataDict/input.hbc?id=' + row.id;
		} else {
			var dictType = $.fn.zTree.getZTreeObj("dataDict_tree")
					.getSelectedNodes();

			if (dictType == undefined || dictType.length <= 0
					|| dictType[0].id == "-1" || dictType[0].id == "") {
				alert("请选择数据字典类型");
				return false;
			}
			hrefUrl = '${ctx}/sys/dataDict/input.hbc?dictTypeId='
					+ dictType[0].id;
		}
		var title = '';
		var icon = '';
		if (operateType == 1) {
			title = '添加';
			icon = 'icon-table';
		} else if (operateType == 2) {
			title = '修改';
			icon = 'icon-table';
		} else if (operateType == 5) {
			title = '查看';
			icon = 'icon-table';
		}
		dataDict_dialog = $("<div/>").dialog(
				{
					title : title,
					top : 150,
					width : 700,
					modal : true,
					maximizable : false,
					href : hrefUrl,
					iconCls : icon,
					maximizable : true,
					buttons : eval('(' + editToolbar(operateType) + ')'),
					onClose : function() {
						$(this).dialog('destroy');
					},
					onLoad : function() {
						formInit();
						if (row) {
						} else {
							var dictType = $.fn.zTree.getZTreeObj(
									"dataDict_tree").getSelectedNodes();
							$('#dictTypeId').val(dictType[0].id);
							$('#dictTypeName').val(dictType[0].name);
							//$("#enable").combobox('setValue', "1");
						}
						if (operateType == 5) {
							$("form input").each(function() {
								$(this).attr("disabled", true);
							});
							$("form textarea").each(function() {
								$(this).attr("disabled", true);
							});
						}
					}
				});
	}

	function editToolbar(operateType) {
		var toolbarJson = "[";
		if (operateType == 1 || operateType == 2) {
			//toolbarJson=toolbarJson+"{text:'保存',iconCls:'icon-save',handler:function(){dataDict_form.submit();}},"
			toolbarJson = toolbarJson
					+ "{text:'保存',iconCls:'icon-save',handler:function(){isSimpleName();}},"
		}
		toolbarJson = toolbarJson
				+ "{text:'关闭',iconCls:'icon-cancel',handler:function(){dataDict_dialog.dialog('destroy');}} ";
		toolbarJson = toolbarJson + "]";

		return toolbarJson;
	}
	/**
	数据字典名称是否重复
	 */
	function isSimpleName() {
		var tt = false;
		$.ajax({
			type : 'post',
			data : 'json',
			async : false,
			url : '${ctx}/sys/dataDict/checkName.hbc?dictName='
					+ encodeURIComponent($("#dictName").val()) + '&id='
					+ $("#id").val(),
			success : function(msg) {
				if (msg == "\"0\"") {// 0：不存在，  1：存在
					tt = true;
				} else {
					tt = false;
				}
			}
		});
		if (tt == false) {
			alert("名称【" + $("#dictName").val() + "】已经存在，请修改！");
			return false;
		} else {
			dataDict_form.submit();
		}
	}
	function formInit() {
		dataDict_form = $('#dataDict_form').form(
				{
					url : '${ctx}/sys/dataDict/save.hbc',
					onSubmit : function(param) {
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
							dataDict_dialog.dialog('destroy');
							dataDict_datagrid.datagrid('reload');
							eu.showMsg(json.msg);
						} else if (json.code == 2) {
							$.messager.alert('提示信息！', json.msg, 'warning',
									function() {
										if (json.obj) {
											$(
													'#dataDict_form input[name="'
															+ json.obj + '"]')
													.focus();
										}
									});
						} else {
							eu.showAlertMsg(json.msg, 'error');
						}
					}
				});
	}

	function edit(operateType, rowIndex, rowData) {
		if (typeof rowIndex != 'undefined') {
			showDialog(operateType, rowData);
			return;
		}
		var rows = dataDict_datagrid.datagrid('getSelections');
		var row = dataDict_datagrid.datagrid('getSelected');
		if (row) {
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showMsg("您选择了多个操作对象，默认操作最后一次被选中的记录！");
			}
			showDialog(operateType, row);
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
	}
	function del() {
		var rows = dataDict_datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('确认提示！', '您确定要删除选中的所有行?', function(r) {
				if (r) {
					var ids = "";
					for (var i = 0; i < rows.length; i++) {
						ids = ids + rows[i].id + ",";
						if (ids.substr(0, 1) == ',') {
							ids = ids.substr(1);
						}
					}
					$.post('${ctx}/sys/dataDict/remove.hbc', {
						ids : ids
					}, function(data) {
						if (data.code == 1) {
							dataDict_datagrid.datagrid('load');
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%;">
	<div data-options="region:'north',split:false,border:false"
		class="mainTitlet">
		<h2>
			<span>系统管理</span><em>&gt;</em><span>基础数据</span><em>&gt;</em><span>数据字典</span>
		</h2>
	</div>
	<div data-options="region:'west',split:true,border:true"
		style="padding: 0px; width: 350px;">
		<ul id="dataDict_tree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="dataDict_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'addDataDict')}"> --%>
			<td><a id="addDataDict" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'"
				onclick="showDialog(1);">添加</a></td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'delDataDict')}"> --%>
			<td><a id="delDataDict" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if>
			<c:if test="${fn:contains(sessionInfo.resourceList,'editDataDict')}"> --%>
			<td><a id="editDataDict" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="edit(2)">编辑</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if>
			<c:if test="${fn:contains(sessionInfo.resourceList,'searchDataDict')}"> --%>
			<td><a id="searchDataDict" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'"
				onclick="showSearchDialog()">查询</a></td>
			<%-- </c:if> --%>
		</tr>
	</table>
</div>
