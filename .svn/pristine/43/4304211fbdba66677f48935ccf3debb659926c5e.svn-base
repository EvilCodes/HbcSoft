
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
var reportHead_datagrid;
var reportHead_form;
var reportHead_dialog;
var reportHead_searchDialog;
//左侧树形结构
var setting = {
		async: {
			enable: true,
			url:"${ctx}/report/head/treegrid.hbc"
		},
		data:{
			simpleData : {
				enable: true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#reportHead_datagrid').datagrid('options').url="${ctx}/report/head/pageHeadList.hbc?id="+treeNode.id; 
				var queryParams = $('#reportHead_datagrid').datagrid('options').queryParams;
				queryParams.id=treeNode.id;
				$('#reportHead_datagrid').datagrid('reload',{hname:''});
				$('#addReportHead').attr('disabled',false);
			}
		}
	};
$(function(){
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		$.fn.zTree.init($("#reportHeadTree"), setting);
		reportHead_datagrid = $('#reportHead_datagrid').datagrid({
			url : '${ctx}/report/head/pageHeadList.hbc',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			striped : true,
			pageSize:iPageSize,		
			pageList:getPageListObj(iPageSize),
			//pageList:[10,20,30,40,50],
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
				field : 'hname',
				title : '名称',
				width : 120,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'field',
				title : '字段',
				width : 200,
				halign : 'center',
				align : "left",
				sortable:true
			}, 
			 {
				field : 'value',
				title : '值',
				width : 200,
				halign : 'center',
				align : "left",
				sortable:true
			}, 
			
			{
				field : 'levelcount',
				title : '级次',
				width : 120,
				halign : 'center',
				align : "center",
				sortable:true
			} ,
			{
				field : 'sort',
				title : '排序',
				width : 120,
				halign : 'center',
				align : "center",
				sortable:true
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
		$('#addReportHead').attr('disabled',true);
});

function showSearchDialog() {
	reportHead_searchDialog = $("<div/>").dialog({
			title : '查询条件',
			top : 150,
			width : 700,
			modal : true,
			maximizable : false,
			iconCls : 'icon-find',
			href : "${ctx}/report/head/search.hbc",
			buttons : [{
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					reportHead_datagrid.datagrid('reload',$.serializeObject($("#reportHead_search_form")));
					reportHead_searchDialog.dialog('destroy');
				}
			},{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					reportHead_searchDialog.dialog('destroy');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}

function showDialog(operateType, row) {
	if(row){
		hrefUrl='${ctx}/report/head/input.hbc?id='+row.id;
	}else{
		var reportHeadTreeMenu= $.fn.zTree.getZTreeObj("reportHeadTree").getSelectedNodes();
	/* 	if(reportHeadTreeMenu[0] == undefined){
			var hrefUrl='${ctx}/report/head/input.hbc?parentId=0';
		}else{ */
			var hrefUrl='${ctx}/report/head/input.hbc?parentId='+reportHeadTreeMenu[0].id+'&parentName='+reportHeadTreeMenu[0].name;
		//}
		
	}
		reportHead_dialog = $("<div/>").dialog({
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
				//reportHead_form.submit();
				isSimpleHname();
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				reportHead_dialog.dialog('destroy');
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
**判断头名称是否重复
*/
 function isSimpleHname(){
	var tt = false;
	$.ajax({
		type: 'post',
		data: 'json',
		async:false,
		url: '${ctx}/report/head/checkhName.hbc?hname='+encodeURIComponent($("#hname").val())+'&id='+$("#id").val(),
		success: function(msg){
			if(msg=="\"0\""){// 0：不存在，  1：存在
				tt = true;
			}else{
				tt = false;
			}
		}
	});
if(tt == false){
	alert("头名称【"+$("#hname").val()+"】已经存在，请修改！");
	return false;
}else{
	reportHead_form.submit();
}
	

} 

function formInit() {
	reportHead_form = $('#reportHead_form').form({
			url : '${ctx}/report/head/save.hbc',
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
					reportHead_dialog.dialog('destroy');
					$.fn.zTree.init($("#reportHeadTree"), setting);
					reportHead_datagrid.datagrid('reload');
					eu.showMsg(json.msg);
				} else if (json.code == 2) {
					$.messager.alert('提示信息！', json.msg, 'warning',
							function() {
								if (json.obj) {
									$('#reportHead_form input[name="'+ json.obj + '"]').focus();
								}
							});
				} else {
					eu.showAlertMsg(json.msg, 'error');
				}
			}
		});
}

function edit(operateType,rowIndex, rowData) {
		if (typeof rowIndex != 'undefined') {
			showDialog(rowData);
			return;
		}
		var rows = reportHead_datagrid.datagrid('getSelections');
		var row = reportHead_datagrid.datagrid('getSelected');
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
	
function del() {
		var rows = reportHead_datagrid.datagrid('getSelections');
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
					 $.post('${ctx}/report/head/haveChildNode.hbc', {
						ids : ids
					}, function(data) {
						if (data.code == 1) {//存在
							alert("在删除父节点之前，请先删除父节点下的子节点。");
							return false; 
						} else if (data.code == 2){
							$.post('${ctx}/report/head/delete.hbc', {
								"ids" : ids
							}, function(data) {
								if (data.code == 1) {
									reportHead_datagrid.datagrid('load');
									//refreshzTree();//刷新树
									$.fn.zTree.init($("#reportHeadTree"), setting);
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
	var t = $("#reportHeadTree");  
	$.ajax({  
		type:'post',
		url: "${ctx}/report/head/treegrid.hbc",  
		dataType: "text",  
		async: false,  
		success: function (data) {  
		zNodes=eval(data);  
		},  
		error: function (msg) { 
		}  
	});  
		t = $.fn.zTree.init($("#reportHeadTree"), setting, zNodes);  
};
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>报表管理</span><em>&gt;</em><span>表头配置</span></h2>
	</div>
	<div data-options="region:'west',split:false,border:false" style="width:250px;height:100%;">
		<ul id="reportHeadTree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:false,border:false" style="padding:0px;height:100%;width: 100%; overflow-y: hidden;">
		<table id="reportHead_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'addDictType')}"> --%>
				<td>
					<a id="addReportHead" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="showDialog(1);">添加</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'delDictType')}"> --%>
				<td>
					<a id="delReportHead" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'editDictType')}"> --%>
				<td>
					<a id="editReportHead" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit(2)">编辑</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if>
			<c:if test="${fn:contains(sessionInfo.resourceList,'searchDictType')}"> --%>
				<td>
					<a id="searchReportHead" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="showSearchDialog()">查询</a>
				</td>
			<%-- </c:if> --%>
		</tr>
	</table>
</div>