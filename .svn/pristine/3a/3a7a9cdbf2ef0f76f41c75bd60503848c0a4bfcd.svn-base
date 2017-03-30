
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript">
var dictType_datagrid;
var dictType_form;
var dictType_dialog;
var dictType_searchDialog;
//左侧树形结构
var setting = {
		async: {
			enable: true,
			url:"${ctx}/sys/dictType/treegrid.hbc"
		},
		data:{
			simpleData : {
				enable: true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				$('#dictType_datagrid').datagrid('options').url="${ctx}/sys/dictType/pageDictList.hbc?id="+treeNode.id; 
				var queryParams = $('#dictType_datagrid').datagrid('options').queryParams;
				
				queryParams.id=treeNode.id;
				$('#dictType_datagrid').datagrid('reload',{dtCode:'',dtName:''});
				$('#addDictType').attr('disabled',false);
			}
		}
	};
$(function(){
		var iPageSize = getPageSize('${sessionInfo.pageSize}');
		$.fn.zTree.init($("#dictTypeTree"), setting);
		dictType_datagrid = $('#dictType_datagrid').datagrid({
			url : '${ctx}/sys/dictType/pageDictList.hbc',
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
				field : 'dtCode',
				title : '编码',
				width : 120,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'dtName',
				title : '名称',
				width : 200,
				halign : 'center',
				align : "left",
				sortable:true
			}, {
				field : 'enable',
				title : '状态',
				width : 120,
				halign : 'center',
				align : "center",
				sortable:true,
				formatter : function(enable) {
				if (enable == 0)
					return '启用';
				else
					return '停用';
		       }
			}, {
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
		$('#addDictType').attr('disabled',true);
});

function showSearchDialog() {
		dictType_searchDialog = $("<div/>").dialog({
			title : '查询条件',
			top : 150,
			width : 700,
			modal : true,
			maximizable : false,
			iconCls : 'icon-find',
			href : "${ctx}/sys/dictType/search.hbc",
			buttons : [{
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					dictType_datagrid.datagrid('reload',$.serializeObject($("#dictType_search_form")));
					dictType_searchDialog.dialog('destroy');
				}
			},{
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					dictType_searchDialog.dialog('destroy');
				}
			}],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}

function showDialog(operateType, row) {
	if(row){
		hrefUrl='${ctx}/sys/dictType/input.hbc?id='+row.id;
	}else{
		var dictTypeTreeMenu= $.fn.zTree.getZTreeObj("dictTypeTree").getSelectedNodes();
		var hrefUrl='${ctx}/sys/dictType/input.hbc?parentId='+dictTypeTreeMenu[0].id+'&parentName='+dictTypeTreeMenu[0].name;
	}
	  dictType_dialog = $("<div/>").dialog({
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
				//dictType_form.submit();
				isSimpleCode();
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				dictType_dialog.dialog('destroy');
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
机构编码是否重复
*/
function isSimpleCode(){
	var tt = false;
	
	$.ajax({
		type: 'post',
		data: 'json',
		async:false,
		url: '${ctx}/sys/dictType/checkCode.hbc?dtCode='+encodeURIComponent($("#dtCode").val())+'&id='+$("#id").val(),
		success: function(msg){
			if(msg=="\"0\""){// 0：不存在，  1：存在
				tt = true;
			}else{
				tt = false;
			}
		}
	});
if(tt == false){
	alert("编号【"+$("#dtCode").val()+"】已经存在，请修改！");
	return false;
}
	isSimpleName();

}
/**
机构名称是否重复
*/
function isSimpleName(){
	var tt = false;
	$.ajax({
		type: 'post',
		data: 'json',
		async:false,
		url: '${ctx}/sys/dictType/checkName.hbc?dtName='+encodeURIComponent($("#dtName").val())+'&id='+$("#id").val(),
		success: function(msg){
			if(msg=="\"0\""){// 0：不存在，  1：存在
				tt = true;
			}else{
				tt = false;
			}
		}
	});
if(tt == false){
	alert("名称【"+$("#dtName").val()+"】已经存在，请修改！");
	return false;
}else{
	dictType_form.submit();
}
}
function formInit() {
		dictType_form = $('#dictType_form').form({
			url : '${ctx}/sys/dictType/save.hbc',
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
					dictType_dialog.dialog('destroy');
					$.fn.zTree.init($("#dictTypeTree"), setting);
					dictType_datagrid.datagrid('reload');
					eu.showMsg(json.msg);
				} else if (json.code == 2) {
					$.messager.alert('提示信息！', json.msg, 'warning',
							function() {
								if (json.obj) {
									$('#dictType_form input[name="'+ json.obj + '"]').focus();
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
		var rows = dictType_datagrid.datagrid('getSelections');
		var row = dictType_datagrid.datagrid('getSelected');
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
		var rows = dictType_datagrid.datagrid('getSelections');
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
					 $.post('${ctx}/sys/dataDict/haveDictData.hbc', {
						ids : ids
					}, function(data) {
						if (data.code == 1) {//存在
							alert("选择的数据字典类型已经存在数据字典项,请先删除数据字典项。");
						    return false; 
						} else if (data.code == 2){//不存在${ctx}/sys/dictType/dictType!remove.action
							$.post('${ctx}/sys/dictType/deleteDictType.hbc', {
								"ids" : ids
							}, function(data) {
								if (data.code == 1) {
									dictType_datagrid.datagrid('load');
									refreshzTree();//刷新树
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
    var t = $("#dictTypeTree");  
    $.ajax({  
        type:'post',        
        url: "${ctx}/sys/dictType/treegrid.hbc",  
        dataType: "text",  
        async: false,  
        success: function (data) {  
            zNodes=eval(data);  
        },  
        error: function (msg) {              
        }  
     });  
    t = $.fn.zTree.init($("#dictTypeTree"), setting, zNodes);  
};
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'north',split:false,border:false" class="mainTitlet">
		<h2><span>系统管理</span><em>&gt;</em><span>基础数据</span><em>&gt;</em><span>字典类型</span></h2>
	</div>
	<div data-options="region:'west',split:false,border:false" style="width:250px;height:100%;">
		<ul id="dictTypeTree" class="ztree"></ul>
	</div>
	<div data-options="region:'center',split:false,border:false" style="padding:0px;height:100%;width: 100%; overflow-y: hidden;">
		<table id="dictType_datagrid"></table>
	</div>
</div>
<div id="toolbar" style="display: none;">
	<table>
		<tr>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'addDictType')}"> --%>
				<td>
					<a id="addDictType" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="showDialog(1);">添加</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'delDictType')}"> --%>
				<td>
					<a id="delDictType" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if> --%>
			<%-- <c:if test="${fn:contains(sessionInfo.resourceList,'editDictType')}"> --%>
				<td>
					<a id="editDictType" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit(2)">编辑</a>
				</td>
				<td>
					<div class="datagrid-btn-separator"></div>
				</td>
			<%-- </c:if>
			<c:if test="${fn:contains(sessionInfo.resourceList,'searchDictType')}"> --%>
				<td>
					<a id="searchDictType" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="showSearchDialog()">查询</a>
				</td>
			<%-- </c:if> --%>
		</tr>
	</table>
</div>