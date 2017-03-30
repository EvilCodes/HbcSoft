<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
	var org_datagrid;
	var org_form;
	var org_dialog;
	var org_searchDialog;
	var user_datagrid;
	var user_datagrid;
	var user_form;
	var user_dialog;
	var user_searchDialog;
	var setting = {
		async : {
			enable : true,
			url : "${ctx}/sys/org/treegrid.hbc"
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#addOrg').attr('disabled', false);
				$('#org_datagrid').datagrid('options').url = "${ctx}/sys/org/pageDictList.hbc?parentId="
						+ treeNode.id;
				var queryParams = $('#org_datagrid').datagrid('options').queryParams;
				queryParams.id = treeNode.id;
				$('#org_datagrid').datagrid('reload', {
					code : '',
					name : ''
				});
				$('#addOrg').linkbutton('enable');
			}
		}
	};

	$(function() {
		$.fn.zTree.init($("#orgTreeMenu1"), setting);
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		org_datagrid = $('#org_datagrid').datagrid({
			url : '${ctx}/sys/org/pageDictList.hbc',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			singleSelect : true,
			striped : true,
			pageSize : iPageSize,
			pageList : getPageListObj(iPageSize),
			columns : [ [ {
				field : 'id',
				title : '主键',
				hidden : true,
				align : 'center',
				sortable : true
			}, {
				field : 'code',
				title : '机构编码',
				width : 100,
				align : 'left',
				halign : 'center',
				sortable : true
			}, {
				field : 'name',
				title : '机构名称',
				width : 250,
				align : 'left',
				halign : 'center',
				sortable : true
			}, {
				field : 'parentOrg.name',
				title : '上级机构',
				width : 250,
				align : 'left',
				halign : 'center',
				sortable : true
			}/* , {
				field : 'orgType',
				title : '机构类型',
				width : 100,
				align : 'center',
				halign : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) {
						return "单位";
					} else {
						return "部门";
					}
				}
			} */, {
				field : 'orderNo',
				title : '排序',
				width : 100,
				align : 'left',
				halign : 'center',
				sortable : true
			}, {
				field : 'enable',
				title : '状态',
				width : 60,
				halign : 'center',
				align : "left",
				sortable : true,
				formatter : getlink,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "启用";
					} else {
						return "停用";
					}
				}
			}, {
				field : 'createUser.name',
				title : '操作员',
				width : 100,
				align : 'left',
				halign : 'center',
				sortable : true
			} ] ],

			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				$(this).datagrid('clearSelections');
				$(this).datagrid('unselectAll');
			},
			onClickRow : function(rowIndex, rowData) {
				//		$(this).datagrid('unselectRow', rowIndex);

			}
		});
		$('#addOrg').linkbutton('disable');
	});

	/**
	* 查询
	*/
	function showSearchDialog() {
		org_searchDialog = $("<div/>")
				.dialog(
						{
							title : '查询条件',
							top : 20,
							width : 500,
							modal : true,
							maximizable : false,
							iconCls : 'icon-find',
							href : "${ctx}/sys/org/search.hbc",
							buttons : [
									{
										text : '查询',
										iconCls : 'icon-search',
										handler : function() {
											org_datagrid
													.datagrid(
															'reload',
															$
																	.serializeObject($("#org_search_form")));
											org_searchDialog.dialog('destroy');
										}
									}, {
										text : '关闭',
										iconCls : 'icon-no',
										handler : function() {
											org_searchDialog.dialog('destroy');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							}
						}).dialog('open');
	}

	/**
	* 添加修改数据
	*/
	function showDialog(row) {
		var orgTreeMenu1 = undefined;
		if (!row) {
			orgTreeMenu1 = $.fn.zTree.getZTreeObj("orgTreeMenu1")
					.getSelectedNodes();
		}
		var hrefUrl = '';
		if (row) {
			hrefUrl = '${ctx}/sys/org/input.hbc?id=' + row.id;
		} else {
			hrefUrl = '${ctx}/sys/org/input.hbc';
		}
		org_dialog = $("<div/>").dialog({
			title : '组织机构信息',
			top : 150,
			width : 700,
			modal : true,
			maximizable : false,
			href : hrefUrl,
			iconCls : 'icon-table-edit',
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					//org_form.submit();
					isSimpleCode();
				}
			}, {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					org_dialog.dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				formInit();
				if (!row) {
					$('#parentId').val(orgTreeMenu1[0].id);
					$('#parentName').textbox('setValue', orgTreeMenu1[0].name);
				}
			}
		}).dialog('open');
	}
	/**
		机构编码是否重复
	 */
	function isSimpleCode() {
		var tt = false;
		$.ajax({
			type : 'post',
			data : 'json',
			async : false,
			url : '${ctx}/sys/org/checkCode.hbc?code='
					+ encodeURIComponent($("#code").val()) + '&id='
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
			alert("机构编号【" + $("#code").val() + "】已经存在，请修改！");
			return false;
		}
		//org_form.submit();
		isSimpleName();

	}
	/**
	机构名称是否重复
	 */
	function isSimpleName() {
		var tt = false;
		$.ajax({
			type : 'post',
			data : 'json',
			async : false,
			url : '${ctx}/sys/org/checkName.hbc?name='
					+ encodeURIComponent($("#name").val()) + '&id='
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
			alert("机构名称【" + $("#name").val() + "】已经存在，请修改！");
			return false;
		} else {
			org_form.submit();
		}
	}
	/**
	* 表单初始化
	*/
	function formInit() {
		org_form = $('#org_form').form(
				{
					url : '${ctx}/sys/org/save.hbc',
					ajax : true,
					onSubmit : function() {
						$("input[disabled]").each(function() {
							$(this).removeAttr("disabled");
						});
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
							org_dialog.dialog('destroy');
							$.fn.zTree.init($("#orgTreeMenu1"), setting);
							org_datagrid.datagrid('reload');
							$('#addOrg').linkbutton('disable');
							eu.showMsg(json.msg);
						} else if (json.code == 2) {
							$.messager.alert('提示信息！', json.msg, 'warning',
									function() {
										if (json.obj) {
											$(
													'#org_form input[name="'
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

	/**
	* 修改数据
	*/
	function edit() {
		var row = org_datagrid.datagrid('getSelected');
		if (row) {
			showDialog(row);
			return;
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
	}
	/**
	*删除
	*/
	function del() {
		var rows = org_datagrid.datagrid('getSelections');
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
					$.post('${ctx}/sys/user/haveUser.hbc', {
						ids : ids
					}, function(data) {
						if (data.code == 1) {//存在
							alert("选择的机构已经存在人员项,请先删除人员项。");
							return false;
						} else if (data.code == 2) {//不存在${ctx}/sys/dictType/dictType!remove.action
							$.post('${ctx}/sys/org/remove.hbc', {
								"ids" : ids
							}, function(data) {
								if (data.code == 1) {
									$.fn.zTree
											.init($("#orgTreeMenu1"), setting);
									org_datagrid.datagrid('load');
									eu.showMsg(data.msg);
								} else {
									eu.showAlertMsg(data.msg, 'error');
								}
							}, 'json');
						} else {
							eu.showAlertMsg(data.msg, 'error');
						}
						/* if (data.code == 1) {
							$.fn.zTree.init($("#orgTreeMenu1"), setting);
							org_datagrid.datagrid('load');
							eu.showMsg(data.msg);
						} else {
							eu.showAlertMsg(data.msg, 'error');
						} */
					}, 'json');
				}
			});
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false"
	style="padding: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false"
		class="mainTitlet">
		<h2>
			<span>系统管理</span><em>&gt;</em><span>授权中心</span><em>&gt;</em><span>机构管理</span>
		</h2>
	</div>
	<div data-options="region:'west',split:true,border:false"
		style="width: 250px; height: 100%; overflow-y: hidden;">
		<div class="easyui-panel" data-options="fit:true">
			<ul id="orgTreeMenu1" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="org_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'addOrg')}"> --%>
			<td><a id="addOrg" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'" onclick="showDialog();">添加</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'editOrg')}"> --%>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="edit()">编辑</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'delOrg')}"> --%>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'searchOrg')}"> --%>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'"
				onclick="showSearchDialog()">查询</a></td>
			<%-- </c:if> --%>
		</tr>
	</table>
</div>