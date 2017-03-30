/**
 *	ModuleName:工作流节点表
 *	Author:zhanghaijiao
 */

/* 保存节点设置信息 */
function save(){
	if($('#sort').val() == "" || $('#sort').val > 100){
		alert("请填写节点序号！");
		return;
	}
	if($('#name').val() == "" || $('#name').val > 100){
		alert("请填写节点名称！");
		return;
	}
	fm.submit();
}

/* 新增规则列表信息 */
function addRule(wfId,nId){
	if($('#baseId').val() == "" || $('#baseId').val() == null){
		alert("请添加并保存节点设置页面信息！");
		return;
	}
	window.location.href = ctx+"/workflow/nodeRuleInput.hbc?wfId="+wfId+"&nId="+nId;
}

/* 修改规则列表信息 */
function updaterule(wfId,nId) {
	var checkbox = document.getElementsByName("checkbox");
	var temp = 0;
	var id = "";
	for ( var i = 0; i < checkbox.length; i++) {
		if (checkbox[i].checked == true) {
			temp++;
			id = checkbox[i].value ;
		}
	}
	if (temp == 1) {
		window.location.href = ctx+"/workflow/nodeRuleInput.hbc?wfId="+wfId+"&nId="+nId+"&ruleId="+ id;
	}else{
		alert("请选择");
	}
}

/* 删除规则列表信息 */
function deletes(wfId,nId) {
	var checkbox = document.getElementsByName("checkbox");
	var temp = 0;
	var id = "";
	for ( var i = 0; i < checkbox.length; i++) {
		if (checkbox[i].checked == true) {
			temp++;
			id += checkbox[i].value + ",";
		}
	}
	if (temp == 0) {
		alert("请选择");
	}
	window.location.href = ctx+"/workflow/delRule.hbc?wfId="+wfId+"&nId="+nId+"&id=" + id;

}

/* 全选全消 */
function checkAll(obj) {
	var checkbox = document.getElementsByName("checkbox");
	var temp = true;
	if (obj.checked == false) {
		temp = false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		checkbox[i].checked = temp;
	}
}

