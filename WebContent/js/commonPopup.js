//定义常量
var TYPE_NUMBERBOX = 1;
var TYPE_COMBOBOX = 2;
var TYPE_DATEBOX = 3;
var TYPE_DATETIMEBOX = 4;
var TYPE_TEXTBOX = 5;
var TYPE_OTHER = 99;

/**
 * 点选返回值对象
 */
function popupObject(filed,type)
{
	var obj = new Object();
	obj.filed = filed;
	obj.type = type;
	obj.getFiled = function()
	{
		return this.filed;
	};
	obj.getType = function()
	{
		return this.type;
	};
	return obj;
}

/**
 * 设置值
 * @param obj		对象
 * @param value		值
 * @author zhangdengyu
 */
function setValue(obj,value)
{
	if (obj) {
		if(TYPE_NUMBERBOX == obj.getType())
		{
			obj.getFiled().numberbox('setValue', value);
		}else if(TYPE_COMBOBOX == obj.getType())
		{
			obj.getFiled().combobox('setValue', value);
		}else if(TYPE_DATEBOX == obj.getType())
		{
			obj.getFiled().datebox('setValue', value);
		}else if(TYPE_DATETIMEBOX == obj.getType())
		{
			obj.getFiled().datetimebox('setValue', value);
		}else if(TYPE_TEXTBOX == obj.getType())
		{
			obj.getFiled().textbox('setValue',value);
		}else{
			obj.getFiled().val(value);
		}
	}
}

/**
 * 申请机构的选择窗口
 * 选择机构，带回机构id和机构名称
 * @author	wangjin
 * @param	strId					机构id输入框对象
 * @param	strName					机构name输入框对象
 * @param	isOnlySelectLeafNode	是否只能选择叶子节点
 *            true：只能选择叶子节点；false：可以选择非叶子节点
 * @param   fn                      回调函数
 * @param   notLeafNode				只能 选非叶子节点
 * 
 */
function popupOrgTreeMenu(strIdKey, strNameKey, isOnlySelectLeafNode, fn,notLeafNode){
	if (typeof(isOnlySelectLeafNode) == "undefined") {
		isOnlySelectLeafNode = true;
	}
	var treeMenuDialog=undefined;
	    treeMenuDialog= $('<div/>').dialog({
		title:'机构菜单',
		width : 250,
		height:500,
		modal : true,
		maximizable:true,
		href : ctx+'/sys/org/menu.hbc',
		buttons : [ {
			id:'optBtn',
			text : '选择',
			iconCls : 'icon-save',
			handler : function() {
				var popupTree= $.fn.zTree.getZTreeObj("orgTreeMenu").getSelectedNodes();
				if(notLeafNode){
					alert("机构菜单");
					if(popupTree[0].name=='机构菜单'){
						alert('不能选择最顶级组织机构');
						return false;
					}
					if (popupTree[0].children==null||popupTree[0].children.length==null
							||popupTree[0].children.length==0){
						alert("不能选择最末级的组织机构");
						return false;
					}
				}else if (isOnlySelectLeafNode){
					if (popupTree[0].children!=null&&popupTree[0].children.length!=null
							&&popupTree[0].children.length>0){
						alert("只能选择最末级的组织机构");
						return false;
					}
				}
				if (popupTree){
					setValue(strIdKey, popupTree[0].id);
					setValue(strNameKey, popupTree[0].name);
					
					if (fn){
						fn();
					}
				}
				treeMenuDialog.dialog('destroy');
			}
		},{
			text : '清空',
			iconCls : 'icon-clear',
			handler : function() {
				setValue(strIdKey, "");
				setValue(strNameKey, "");
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

/**
 * 人员点选
 */	
var user_selectgrid;
var userDialog;
function popupUserDialog(id,name) {
	var gpUrl = ctx + '/sys/user/checkUsers.hbc';
	gpUrl = encodeURI(gpUrl);
	userDialog = $('<div/>').dialog({
		title : '系统人员',
		width : 800,
		height : 570,
		modal : true,
		top: 0,
		iconCls : 'icon-user',
		maximizable : false,
		href : gpUrl,
		buttons : [ {
			text : '选择',
			iconCls : 'icon-save',
			handler : function() {
				var rows = $('#user_selectgrid').datagrid('getSelections');
				var ids = "";
				var names = "";
				for (var i = 0; i < rows.length; i++) {
					ids += rows[i].id + ",";
					names += rows[i].name + ",";
				}
				if(ids.length>0){
					ids = ids.substring(0,(ids.length-1));
					names = names.substring(0,(names.length-1));
				}
				if(rows){
					setValue(id, ids);
					setValue(name, names);
					//alert("ids:" + ids);
				}
				userDialog.dialog('destroy');
			}
		}, {
			text : '清空',
			iconCls : 'icon-clear',
			handler : function() {
				setValue(id, null);
				userDialog.dialog('destroy');
			}
		}, {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				userDialog.dialog('destroy');
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

/**
 * 自定义标签中机构点选
 * @param strIdKey
 * @param strNameKey
 * @param isOnlySelectLeafNode
 * @param notLeafNode
 * @param fn
 */
function popupOrgTreeMenuCheck(strIdKey, strNameKey, isOnlySelectLeafNode,notLeafNode, fn){
	if (typeof(isOnlySelectLeafNode) == "undefined") {
		isOnlySelectLeafNode = true;
	}
	var treeMenuDialogCheck=undefined;
	treeMenuDialogCheck= $('<div/>').dialog({
		title:'机构菜单',
		width : 250,
		height:500,
		top:0,
		modal : true,
		maximizable:true,
		href : ctx+'/sys/org/menuCheck.hbc?ids='+strIdKey.getFiled().val(),
		buttons : [ {
			id:'optBtn',
			text : '选择',
			iconCls : 'icon-save',
			handler : function() {
				var ids = "";
				var names = "";
				var nodes = $.fn.zTree.getZTreeObj("orgTreeMenu").getCheckedNodes(true);
				for(var i=0; i<nodes.length; i++){
					if(nodes[i].children==null || nodes[i].children.length==null || nodes[i].children.length==0){
						//alert(nodes[i].code);
						ids += nodes[i].code+",";
						names += nodes[i].name+",";
					}
				}
				if(ids.length>0){
					ids = ids.substring(0,(ids.length-1));
					names = names.substring(0,(names.length-1));
				}

				if (nodes){
					setValue(strIdKey, ids);
					setValue(strNameKey, names);
					
					if (fn){
						fn();
					}
				}
				treeMenuDialogCheck.dialog('destroy');
			}
		},{
			text : '清空',
			iconCls : 'icon-clear',
			handler : function() {
				setValue(strIdKey, "");
				setValue(strNameKey, "");
				treeMenuDialogCheck.dialog('destroy');
			}
		},{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				treeMenuDialogCheck.dialog('destroy');
			}
		}],
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

/**
 * 自定义标签中角色点选
 * @param strIdKey
 * @param strNameKey
 * @param isOnlySelectLeafNode
 * @param notLeafNode
 * @param fn
 */
function popupRoleTreeCheck(strIdKey, strNameKey, isOnlySelectLeafNode,notLeafNode, fn){
	if (typeof(isOnlySelectLeafNode) == "undefined") {
		isOnlySelectLeafNode = true;
	}
	var treeRoleDialogCheck=undefined;
	treeRoleDialogCheck= $('<div/>').dialog({
		title:'角色管理',
		width : 250,
		height:500,
		top:0,
		modal : true,
		maximizable:true,
		href : ctx+'/sys/role/roleCheck.hbc?ids='+strIdKey.getFiled().val(),
		buttons : [ {
			id:'optBtn',
			text : '选择',
			iconCls : 'icon-save',
			handler : function() {
				var ids = "";
				var names = "";
				var nodes = $.fn.zTree.getZTreeObj("roleTree").getCheckedNodes(true);
				for(var i=0; i<nodes.length; i++){
					if(nodes[i].children==null || nodes[i].children.length==null || nodes[i].children.length==0){
						//alert(nodes[i].name);
						ids += nodes[i].id+",";
						names += nodes[i].name+",";
					}
				}
				if(ids.length>0){
					ids = ids.substring(0,(ids.length-1));
					names = names.substring(0,(names.length-1));
				}

				if (nodes){
					setValue(strIdKey, ids);
					setValue(strNameKey, names);
					
					if (fn){
						fn();
					}
				}
				treeRoleDialogCheck.dialog('destroy');
			}
		},{
			text : '清空',
			iconCls : 'icon-clear',
			handler : function() {
				setValue(strIdKey, "");
				setValue(strNameKey, "");
				treeRoleDialogCheck.dialog('destroy');
			}
		},{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				treeRoleDialogCheck.dialog('destroy');
			}
		}],
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

function chooseOrg(obj) {
	var orgID = popupObject(obj.parents('div').children('.hidden'), TYPE_OTHER);
	var orgName = popupObject(obj.parents('div').children('.form-control'), TYPE_OTHER);
	popupOrgTreeMenuCheck(orgID, orgName, 1);
}
function chooseRole(obj) {
	var roleID = popupObject(obj.parents('div').children('.hidden'), TYPE_OTHER);
	var roleName = popupObject(obj.parents('div').children('.form-control'), TYPE_OTHER);
	popupRoleTreeCheck(roleID, roleName, 1);
}
function chooseUser(obj) {
	var id = popupObject(obj.parents('div').children('.hidden'), TYPE_OTHER);
	var name = popupObject(obj.parents('div').children('.form-control'), TYPE_OTHER);
	popupUserDialog(id,name);
}
