<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
	var user_datagrid;
	var user_form;
	var user_dialog;
	var user_searchDialog;

	var setting = {
		async : {
			enable : true,
			url : "${ctx}/sys/user/treegrid.hbc"
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				if (!treeNode.isParent) {
					$('#addUser').linkbutton('enable');
					$('#deptId').val(treeNode.id);
					$('#deptName').val(treeNode.name);
					$('#user_datagrid').datagrid('options').url = "${ctx}/sys/user/pageDictList.hbc?orgId="
							+ treeNode.id;
					var queryParams = $('#user_datagrid').datagrid('options').queryParams;
					queryParams.id = treeNode.id;
					$('#user_datagrid').datagrid('reload', {
						loginName : '',
						name : '',
						identityNumber : '',
						mobilephone : '',
						cardNumber : '',
						businessCardNumber : ''
					});
				} else {
					$('#addUser').linkbutton('disable');
				}
			}
		}
	};

	$(function() {
		$.fn.zTree.init($("#deptTree"), setting);
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		user_datagrid = $('#user_datagrid').datagrid({
			url : '${ctx}/sys/user/pageDictList.hbc',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			striped : true,
			pageSize : iPageSize,
			pageList : getPageListObj(iPageSize),
			remoteSort : false,
			sortOrder : 'asc',
			idField : 'id',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'id',
				title : '主键',
				hidden : true,
				width : 120,
				halign : 'center',
				sortable : true
			}, {
				field : 'loginName',
				title : '登录名',
				width : 100,
				halign : 'center',
				sortable : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100,
				halign : 'center',
				sortable : true
			}, {
				field : 'identityNumber',
				title : '证件号码',
				width : 150,
				halign : 'center',
				sortable : true
			}, {
				field : 'mobilephone',
				title : '移动电话',
				width : 100,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'tel',
				title : '办公电话',
				width : 150,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'cardNumber',
				title : '借记卡',
				width : 150,
				halign : 'center',
				align : "left",
				sortable : true,
				formatter : function(value, row, index) {
					var valgroup = '';
					if (value) {
						for (var i = 0; i < value.length; i++) {
							if (i != 0 && i % 4 == 0) {
								valgroup = valgroup + ' ';
								valgroup = valgroup + value[i];
							} else {
								valgroup = valgroup + value[i];
							}
						}
					}
					return valgroup;
				}
			}, {
				field : 'businessCardNumber',
				title : '公务卡',
				width : 150,
				halign : 'center',
				align : "left",
				sortable : true,
				formatter : function(value, row, index) {
					var valgroup = '';
					if (value) {
						for (var i = 0; i < value.length; i++) {
							if (i != 0 && i % 4 == 0) {
								valgroup = valgroup + ' ';
								valgroup = valgroup + value[i];
							} else {
								valgroup = valgroup + value[i];
							}
						}
					}
					return valgroup;
				}
			}, {
				field : 'pageSize',
				title : '列表条数',
				width : 80,
				halign : 'center',
				align : "left",
				sortable : true
			}, {
				field : 'enable',
				title : '停启状态',
				width : 80,
				halign : 'center',
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					if (value == 0) {
						return "启用";
					} else {
						return "停用";
					}
				}
			}, {
				field : 'createTime',
				title : '建立时间',
				width : 120,
				halign : 'center',
				align : 'center',
				sortable : true
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				$(this).datagrid('clearSelections');
				$(this).datagrid('unselectAll');
			},
			onClickRow : function(rowIndex, rowData) {
				$(this).datagrid('unselectRow', rowIndex);
			}
		}).datagrid('showTooltip');
		$('#addUser').linkbutton('disable');
	});

	/**
	 * 查询
	 */

	function showSearchDialog() {
		var user_searchDialog = undefined;
		user_searchDialog = $("<div/>")
				.dialog(
						{
							title : '查询条件',
							top : 150,
							width : 700,
							modal : true,
							maximizable : false,
							href : "${ctx}/sys/user/search.hbc",
							iconCls : 'icon-find',
							buttons : [
									{
										text : '查询',
										iconCls : 'icon-search',
										handler : function() {
											user_datagrid
													.datagrid(
															'reload',
															$
																	.serializeObject($("#user_search_form")));
											user_searchDialog.dialog('destroy');
										}
									},
									{
										text : '关闭',
										iconCls : 'icon-no',
										handler : function() {
											user_searchDialog.dialog('destroy');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								formInit();
							}
						});
	}
	/**
	 * 添加修改数据
	 */

	function showDialog(row) {
		var hrefUrl = '';
		if (row) {
			hrefUrl = '${ctx}/sys/user/input.hbc?id=' + row.id;
		} else {
			hrefUrl = '${ctx}/sys/user/input.hbc';
		}
		user_dialog = $("<div/>")
				.dialog(
						{
							title : '用户信息',
							top : 150,
							width : 800,
							modal : true,
							maximizable : false,
							href : hrefUrl,
							iconCls : 'icon-user',
							buttons : [
									{
										text : '保存',
										iconCls : 'icon-save',
										handler : function() {
											var companyNameHk = $(
													"#companyNameHk").val();//公司简称+_
											var loginName = $("#loginName")
													.val();//输入的值
											var aa = loginName
													.substr(0, (loginName
															.indexOf("_") + 1));
											if (companyNameHk != aa) {
												alert("登录名称应以【" + companyNameHk
														+ "】为开头，请修改！");
												return false;
											}
											var bb = loginName
													.substr((loginName
															.indexOf("_") + 1),
															1);
											if (bb == "" || bb == ''
													|| bb == null) {
												alert("登录名称不能只有公司简称，请修改！");
												return false;
											}
											//user_form.submit();
											isSimpleName();
										}
									}, {
										text : '关闭',
										iconCls : 'icon-cancel',
										handler : function() {
											user_dialog.dialog('destroy');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								formInit();
								if (row) {
									//$('#password_tr').css('display','none');
									//$('#password_tr input').removeAttr("class");
									//$('#user_form').form('myLoad','${ctx}/sys/user/user!getUserById.action?id='+row.id);
								} else {
									var selDept = $.fn.zTree.getZTreeObj(
											"deptTree").getSelectedNodes();
									$('#deptId').val(selDept[0].id);
									$('#deptName').textbox('setValue',
											selDept[0].name);
								}
							}
						});
	}
	/**
	 登录名称是否重复
	 */
	function isSimpleName() {
		
		var tt = false;
		$.ajax({
			type : 'post',
			data : 'json',
			async : false,
			url : '${ctx}/sys/user/checkName.hbc?loginName='+ encodeURIComponent($("#loginName").val()) + '&id='+ ($("#id").val()) ,
			success : function(msg) {
				if (msg == "\"0\"") {// 0：不存在，  1：存在
					tt = true;
				} else {
					tt = false;
				}
			}
		});
		if (tt == false) {
			alert("登录名【" + $("#loginName").val() + "】已经存在，请修改！");
			return false;
		} else {
			user_form.submit();
		}
	}
	/**
	 * 表单初始化
	 */
	function formInit() {
		user_form = $('#user_form').form(
				{
					url : '${ctx}/sys/user/save.hbc',
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
							user_dialog.dialog('destroy');
							user_datagrid.datagrid('reload');
							eu.showMsg(json.msg);
						} else if (json.code == 2) {
							$.messager.alert('提示信息！', json.msg, 'warning',
									function() {
										if (json.obj) {
											$(
													'#user_form input[name="'
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
	function edit(rowIndex, rowData) {
		if (rowIndex != undefined) {
			showDialog(rowData);
			return;
		}

		var rows = user_datagrid.datagrid('getSelections');
		var row = user_datagrid.datagrid('getSelected');
		if (row) {
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showMsg("您选择了多个操作对象，默认操作最后一次被选中的记录！");
			}
			showDialog(row);
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
	}

	function del() {
		var rows = user_datagrid.datagrid('getSelections');
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
					$.post('${ctx}/sys/user/remove.hbc', {
						ids : ids
					}, function(data) {
						if (data.code == 1) {
							/**删除成功后刷新左侧树**/
							$.fn.zTree.init($("#userTree"), setting);
							user_datagrid.datagrid('load');
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

	function settingRole() {
		var roleDialog = undefined;
		var rows = user_datagrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要操作的对象！', 'info');
			return false;
		}
		var updateUserRoleUrl = "${ctx}/sys/user/updateUserRole.hbc?id="
				+ rows[0].id;
		var url = "${ctx}/sys/user/role.hbc?id=" + rows[0].id;
		roleDialog = $('<div/>').dialog(
				{
					title : '角色列表',
					top : 150,
					width : 300,
					height : 400,
					modal : true,
					maximizable : false,
					href : url,
					buttons : [
							{
								text : '选择',
								iconCls : 'icon-save',
								handler : function() {
									$(this).linkbutton('disable');
									var nodes = $.fn.zTree.getZTreeObj(
											"roleTree").getCheckedNodes(true);
									/* var ids = new Object();
									for(var i=0;i<nodes.length;i++){
										ids[i]=nodes[i].id;
									} */
									var ids = "";
									for (var i = 0; i < nodes.length; i++) {
										ids = ids + nodes[i].id + ",";
										if (ids.substr(0, 1) == ',') {
											ids = ids.substr(1);
										}
									}
									$.post(updateUserRoleUrl, {
										ids : ids
									}, function(data) {
										if (data.code == 1) {
											user_datagrid.datagrid('load');
											eu.showMsg(data.msg);
										} else {
											eu.showAlertMsg(data.msg, 'error');
										}
									}, 'json');
									roleDialog.dialog('destroy');
								}
							}, {
								text : '关闭',
								iconCls : 'icon-cancel',
								handler : function() {
									roleDialog.dialog('destroy');
								}
							} ],
					onClose : function() {
						$(this).dialog('destroy');
					}
				});
	}

	//设置部门行权限
	function settingDeptRowAuthority() {
		var rowDeptDialog = undefined;
		var rows = user_datagrid.datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要操作的对象！', 'info');
			return false;
		}
		var updateUserRoleUrl = "${ctx}/sys/user/user!updateUserRowAuthority.action?id="
				+ rows[0].id;
		var url = "${ctx}/sys/user/user!rowauthority.action?id=" + rows[0].id;
		rowDeptDialog = $('<div/>')
				.dialog(
						{
							title : '部门列表',
							top : 150,
							width : 300,
							height : 400,
							modal : true,
							maximizable : false,
							href : url,
							buttons : [
									{
										text : '选择',
										iconCls : 'icon-save',
										handler : function() {
											$(this).linkbutton('disable');
											var nodes = $.fn.zTree.getZTreeObj(
													"rowDeptTree")
													.getCheckedNodes(true);
											var ids = new Object();
											for (var i = 0; i < nodes.length; i++) {
												ids[i] = nodes[i].id;
												if (nodes[i] != null
														&& nodes[i].children != null
														&& nodes[i].children.length != null
														&& nodes[i].children.length > 0) {
													ids[i] += "_0";//非叶子
												} else {
													ids[i] += "_1";//叶子
												}
											}
											$.post(updateUserRoleUrl, {
												ids : ids
											}, function(data) {
												if (data.code == 1) {
													user_datagrid
															.datagrid('load');
													eu.showMsg(data.msg);
												} else {
													$(this)
															.linkbutton(
																	'enable');
													eu.showAlertMsg(data.msg,
															'error');
												}
											}, 'json');
											rowDeptDialog.dialog('destroy');
										}
									}, {
										text : '关闭',
										iconCls : 'icon-cancel',
										handler : function() {
											rowDeptDialog.dialog('destroy');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							}
						});
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false"
	style="overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false"
		class="mainTitlet">
		<h2>
			<span>系统管理</span><em>&gt;</em><span>授权中心</span><em>&gt;</em><span>用户管理</span>
		</h2>
	</div>
	<div data-options="region:'west',split:true,border:false"
		style="width: 280px; height: 100%; overflow-y: hidden;">
		<div class="easyui-panel" data-options="fit:true">
			<ul id="deptTree" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<!-- <input type="hidden" id="id" name="user.id" value="" /> <input
			type="hidden" id="name" name="user.name" value="" /> -->
		<table id="user_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<td><a id="addUser" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'" onclick="showDialog();">添加</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="edit()">编辑</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-user'"
				onclick="settingRole()">设置角色</a></td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<!-- <td>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-userEdit'" onclick="settingDeptRowAuthority()">设置部门行权限</a>
			</td> -->
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'"
				onclick="showSearchDialog()">查询</a></td>
			<%-- <c:if test="${sessionInfo.isManager==1}">
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-userEdit'" onclick="setPassword()">重置密码</a>
				</td>
			</c:if> --%>
		</tr>
	</table>
</div>