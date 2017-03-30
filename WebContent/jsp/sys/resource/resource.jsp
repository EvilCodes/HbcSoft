
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
var resource_datagrid;
var resource_form;
var resource_dialog;
var resource_searchDialog;
//左侧树形结构
var setting = {
		async: {
			enable: true,
			url:"${pageContext.request.contextPath}/sys/resource/srcTreeGrid.hbc"
		},
		data:{
			simpleData : {
				enable: true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#resource_datagrid').datagrid('options').url="${ctx}/sys/resource/pageResourceList.hbc?id="+treeNode.id; 
				var queryParams = $('#resource_datagrid').datagrid('options').queryParams;
				
				queryParams.id=treeNode.id;
				$('#resource_datagrid').datagrid('reload',{code:'',name:''});
				$('#addResource').attr('disabled',true);
			}
		}
	};
$(function(){
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		$.fn.zTree.init($("#resourceTree"), setting);
		resource_datagrid = $('#resource_datagrid').datagrid({
			url : '${ctx}/sys/resource/pageResourceList.hbc',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			striped : true,
			pageSize:iPageSize,
			pageList:getPageListObj(iPageSize),
			remoteSort : false,
			idField : 'id',
			columns : [[{
				field : 'ck',
				checkbox : true,
				sortable:true
			}, {
				field : 'id',
				title : '主键',
				hidden : true,
				sortable:true,
				width : 120
			}, {
				field : 'groupId',
				title : '是否允许删除',
				hidden : true,
				sortable:true,
				width : 120
			}, {
				field : 'code',
				title : '资源编码',
				width : 120,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'name',
				title : '资源名称',
				width : 200,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'url',
				title : '链接路径',
				width : 200,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'orderNo',
				title : '排序',
				width : 120,
				halign : 'center',
				align : "center",
				sortable:true
			}, {
				field : 'resourceType',
				title : '资源类型',
				width : 120,
				halign : 'center',
				align : "center",
				sortable:true,
				formatter : function(resourceType) {
					if (resourceType == 0){
						return '菜单';
					}else{
						return '按钮';
					}
				} 
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				halign : 'center',
				align : "left",
				sortable:true,
				formatter : function(createTime) {
					var date = new Date(createTime);
					var y = date.getFullYear();
					var m = date.getMonth()+1;
					var d = date.getDate();
					var hour = date.getHours();
					var minute = date.getMinutes();
					var second = date.getSeconds();
					return y+"-"+m+"-"+d+" "+hour+":"+minute+":"+second;
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
			onDblClickRow:function(rowIndex,rowData){
				showDialog(5, rowData);
			}
		}).datagrid('showTooltip');
		$('#addResource').attr('disabled',false);
});
 //上级资源输入框---点选--资源菜单
function popupTreeMenu(){
	var treeMenuDialog=undefined;
	treeMenuDialog= $('<div/>').dialog({
		title:'资源菜单',
		top:150,
		width : 250,
		height:400,
		modal : true,
		maximizable:false,
		href :'${ctx}/sys/resource/resourceMenu.hbc',
		buttons :[{
			id:'optBtn',
			text : '选择',
			iconCls : 'icon-save',
			handler : function() {
				var popupTree= $.fn.zTree.getZTreeObj("resourceTreeMenu").getSelectedNodes();
				$('#parentResourceId').val(popupTree[0].id);
				$('#parentName').textbox('setValue',popupTree[0].name);
				treeMenuDialog.dialog('destroy');
			}
		},{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
			 treeMenuDialog.dialog('destroy');
			}
		}],
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}
function showSearchDialog() {
		resource_searchDialog = $("<div/>").dialog({
			title : '查询条件',
			top : 150,
			width : 700,
			modal : true,
			maximizable : false,
			iconCls : 'icon-find',
			href : "${ctx}/sys/resource/resourceSearch.hbc",
			buttons : [{
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					resource_datagrid.datagrid('reload',$.serializeObject($("#resource_search_form")));
					resource_searchDialog.dialog('destroy');
				}
			},{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					resource_searchDialog.dialog('destroy');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}
//打开添加和编辑窗口
function showDialog(operateType, row) {
	if(row){
		hrefUrl='${ctx}/sys/resource/resourceInput.hbc?id='+row.id;
	}else{
		var resourceTreeMenu= $.fn.zTree.getZTreeObj("resourceTree").getSelectedNodes();
		var hrefUrl='${ctx}/sys/resource/resourceInput.hbc?parentResourceId='+resourceTreeMenu[0].id+'&parentName='+resourceTreeMenu[0].name;
	}
	  resource_dialog = $("<div/>").dialog({
		title:'编辑',
		top:150,
		width:700,
		modal:true,
		iconCls:'icon-table',
		maximizable:false,
		href : hrefUrl,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				checkCode();//判断编码是否重复
				
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				resource_dialog.dialog('destroy');
			}
		}],
		onClose : function() {
			$(this).dialog('destroy');
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
			}
		}
	});
}
/**
判断编码是否重复
*/
	function checkCode() {
		var tt = false;
		$.ajax({
			type : 'post',
			data : 'json',
			async : false,
			url : '${ctx}/sys/resource/checkCodeName.hbc?code='+ encodeURIComponent($("#code").val()) + 
					'&name='+ encodeURIComponent($("#name").val()) +
					'&id='+ $("#id").val(),
			success : function(msg) {
				if (msg == "\"0\"") {// 0：不存在，  1：存在
					tt = true;
				} else {
					tt = false;
				}
			}
		});
		if (tt == false) {
			//alert("编号【" + $("#code").val() + "】已经存在，请修改！");
			alert("资源编码或者资源名称已经存在，请修改！");
			return false;
		}else{
			resource_form.submit();
		}
	}
	//保存
	function formInit() {
		resource_form = $('#resource_form').form(
				{
					url : '${ctx}/sys/resource/resourceSave.hbc',
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
							resource_dialog.dialog('destroy');
							$.fn.zTree.init($("#resourceTree"), setting);
							resource_datagrid.datagrid('reload');
							eu.showMsg(json.msg);
						} else if (json.code == 2) {
							$.messager.alert('提示信息！', json.msg, 'warning',
									function() {
										if (json.obj) {
											$(
													'#resource_form input[name="'
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
	//编辑
	function edit(operateType, rowIndex, rowData) {
		if (typeof rowIndex != 'undefined') {
			showDialog(rowData);
			return;
		}
		var rows = resource_datagrid.datagrid('getSelections');
		var row = resource_datagrid.datagrid('getSelected');
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
	//删除
	function del() {

		var rows = resource_datagrid.datagrid('getSelections');
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
					$.post('${ctx}/sys/resource/deleteResource.hbc', {
						"ids" : ids
					}, function(data) {
						if (data.code == 1) {
							resource_datagrid.datagrid('load');
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
	//刷新树  
	function refreshzTree() {
		var t = $("#resourceTree");
		$.ajax({
			type : 'post',
			url : "${ctx}/sys/resource/srcTreeGrid.hbc",
			dataType : "text",
			async : false,
			success : function(data) {
				zNodes = eval(data);
			},
			error : function(msg) {
			}
		});
		t = $.fn.zTree.init($("#resourceTree"), setting, zNodes);
	};
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>授权中心</span><em>&gt;</em><span>资源管理</span></h2>
	</div>
	<div data-options="region:'west',split:false,border:false" style="width:250px;height:100%;">
		<ul id="resourceTree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:false,border:false" style="padding:0px;height:100%;width: 100%; overflow-y: hidden;">
		<table id="resource_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'addResource')}"> --%>
				<td>
					<a id="addResource" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="showDialog(1);">添加</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'delResource')}"> --%>
				<td>
					<a id="delResource" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'editResource')}"> --%>
				<td>
					<a id="editResource" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit(2)">编辑</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if>
			<c:if test="${fn:contains(sessionInfo.resourceList,'searchResource')}"> --%>
				<td>
					<a id="searchResource" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="showSearchDialog()">查询</a>
				</td>
			<%-- </c:if> --%>
		</tr>
	</table>
</div>