<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ taglib prefix="demo" uri="/tagDemo" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
var row_datagrid;
var row_form;
var row_dialog;
var row_searchDialog;
//左侧树形结构
var setting = {
		async: {
			enable: true,
			url:"${ctx}/reportRow/treegrid.hbc"
		},
		data:{
			simpleData : {
				enable: true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#row_datagrid').datagrid('options').url="${ctx}/reportRow/pageRepRowList.hbc?id="+treeNode.id; 
				var queryParams = $('#row_datagrid').datagrid('options').queryParams;
				
				queryParams.id=treeNode.id;
				$('#row_datagrid').datagrid('reload',{hname:''});
				$('#addResource').attr('disabled',true);
			}
		}
	};
$(function(){
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		$.fn.zTree.init($("#rowTree"), setting);
		row_datagrid = $('#row_datagrid').datagrid({
			url : '${ctx}/reportRow/pageRepRowList.hbc',
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
				sortable : true
			}, {
				field : 'hname',
				title : '名称',
				width : 100,
				halign : 'center',
				align : 'left',
				sortable : true
			},{
				field : 'filed',
				title : '字段',
				width : 50,
				halign : 'center',
				align : 'left',
				sortable : true
			}, {
				field : 'value',
				title : '值',
				width : 50,
				halign : 'center',
				align : 'left',
				sortable : true
			}, {
				field : 'isEnd',
				title : '是否末级节点',
				width : 50,
				halign : 'center',
				align : 'left',
				sortable : true,
				formatter : function(isEnd) {
					if (isEnd == 1){
						return '是';
					}else{
						return '否';
					}
				} 
			}, {
				field : 'levelcount',
				title : '层级',
				width : 20,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				field : 'sort',
				title : '排序',
				width : 20,
				halign : 'center',
				align : 'center',
				sortable : true
			}, {
				field : 'status',
				title : '标识',
				width : 20,
				halign : 'center',
				align : 'center',
				sortable : true
			} 
			] ],
			
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
function showSearchDialog() {
		row_searchDialog = $("<div/>").dialog({
			title : '查询条件',
			top : 150,
			width : 700,
			modal : true,
			maximizable : false,
			iconCls : 'icon-find',
			href : "${ctx}/reportRow/repRowSearch.hbc",
			buttons : [{
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					row_datagrid.datagrid('reload',$.serializeObject($("#row_search_form")));
					row_searchDialog.dialog('destroy');
				}
			},{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					row_searchDialog.dialog('destroy');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}
//打开添加和编辑窗口
function showDialog(operateType, row) {
	if(row){//修改
		hrefUrl='${ctx}/reportRow/repRowInput.hbc?id='+row.id;
	}else{//添加
		var resourceTreeMenu= $.fn.zTree.getZTreeObj("rowTree").getSelectedNodes();
		var hrefUrl='${ctx}/reportRow/repRowInput.hbc?parentId='+resourceTreeMenu[0].id+'&parentName='+resourceTreeMenu[0].name;
	}
	  row_dialog = $("<div/>").dialog({
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
				row_form.submit();//保存修改后的form表单
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				row_dialog.dialog('destroy');
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
//保存
function formInit() {
		row_form = $('#row_form').form({
			url : '${ctx}/reportRow/repRowSave.hbc',
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
					row_dialog.dialog('destroy');
					$.fn.zTree.init($("#rowTree"), setting);
					row_datagrid.datagrid('reload');
					eu.showMsg(json.msg);
				} else if (json.code == 2) {
					$.messager.alert('提示信息！', json.msg, 'warning',
							function() {
								if (json.obj) {
									$('#row_form input[name="'+ json.obj + '"]').focus();
								}
							});
				} else {
					eu.showAlertMsg(json.msg, 'error');
				}
			}
		});
}
//编辑
function edit(operateType,rowIndex, rowData) {
		if (typeof rowIndex != 'undefined') {
			showDialog(rowData);
			return;
		}
		var rows = row_datagrid.datagrid('getSelections');
		var row = row_datagrid.datagrid('getSelected');
		if (row) {
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showMsg("您选择了多个操作对象，默认操作最后一次被选中的记录！");
			}
			showDialog(operateType,row);
		} else {
			eu.showMsg("请选择要操作的对象！");
		}
}
//删除
function del() {
	var rows = row_datagrid.datagrid('getSelections');
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
				 $.post('${ctx}/reportRow/haveChildNode.hbc', {
					ids : ids
				}, function(data) {
					if (data.code == 1) {//存在
						alert("在删除父节点之前，请先删除父节点下的子节点。");
						return false; 
					} else if (data.code == 2){
						$.post('${ctx}/reportRow/deleteRepRow.hbc', {
							"ids" : ids
						}, function(data) {
							if (data.code == 1) {
								row_datagrid.datagrid('load');
								$.fn.zTree.init($("#rowTree"), setting);
								eu.showMsg(data.msg);
							} else {
								eu.showAlertMsg(data.msg, 'error');
							}
						}, 'json');
					 }else {
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
function refreshzTree(){  
    var t = $("#rowTree");  
    $.ajax({  
        type:'post',        
        url: "${ctx}/reportRow/treegrid.hbc",  
        dataType: "text",  
        async: false,  
        success: function (data) {  
            zNodes=eval(data);  
        },  
        error: function (msg) {              
        }  
     });  
    t = $.fn.zTree.init($("#rowTree"), setting, zNodes);  
};
/**
**人员点选
*/
 /* 	function checkUsers() {
		var id = popupObject($('#id'), TYPE_OTHER);
		var name = popupObject($('#name'), TYPE_OTHER);
		popupUserDialog(id, test);
	} */
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>报表管理</span><em>&gt;</em><span>表体配置</span></h2>
	</div>
	<div data-options="region:'west',split:false,border:false" style="width:250px;height:100%;">
		<ul id="rowTree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:false,border:false" style="padding:0px;height:100%;width: 100%; overflow-y: hidden;">
		<table id="row_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
<%-- 	<demo:demo tagType="4" nameField="test" valueField="test" titleField="人员点选测试" inputIsDisplay="0" ></demo:demo> --%>
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