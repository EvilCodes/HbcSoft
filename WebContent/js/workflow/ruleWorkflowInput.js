/**
 *	ModuleName:工作流规则表
 *	Author:zhanghaijiao
 */




/* 保存规则设置信息 */
function save(){
	if($('#ruleName').val() == "" || $('#ruleName').val > 100){
		alert("请输入流程名称！");
		return;
	}
	fm.submit();
}

/* 新增或修改子规则列表信息按钮 */
function addRuleD(obj,wfId,nId,ruleId){
	if($('#ruleId').val() == "" || $('#ruleId').val() == null){
		alert("请添加并保存规则设置信息！");
		return;
	}
	var link = ctx+"/workflow/nodeRuleDInput.hbc?wfId="+wfId+"&nId="+nId+"&ruleId="+ruleId;
	if(obj =='1'){
		var checkbox = document.getElementsByName("checkbox");
		var temp = 0;
		var ruleDId = "";
		for ( var i = 0; i < checkbox.length; i++) {
			if (checkbox[i].checked == true) {
				temp++;
				ruleDId = checkbox[i].value;
			}
		}
		if (temp != 1) {
			alert("请选择");
			return ;
		}
		loop();
		link = ctx+"/workflow/nodeRuleDInput.hbc?wfId="+wfId+"&nId="+nId+"&ruleId="+ruleId+"&ruleDId="+ruleDId;
	}
	window.location.href = link;
	/*var ruleDetailDialog=$('<div/>').dialog(
			{
				title : '规则子表',
				width : 800,
				height : 400,
				modal : true,
				maximizable : false,
				href : link,
				buttons : [
				{
					text : '确定',
					iconCls : 'icon-save',
					handler : function() {
						$('#form').form('submit',{
							ajax:true,
							success: function(data){
								
								reload(nId,wfId,ruleId);
								ruleDetailDialog.dialog('destroy');
							}
						});
						
					}
				}, {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						ruleDetailDialog.dialog('destroy');
					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				}
			}).dialog('open');*/
}
var looptime;
//定时判断页面是否加载出来

function loop(){
	looptime = window.setInterval(setValue,1000);
}

function setValue(){
	var temp = $('#temptable').val();
	if(temp){
		window.clearInterval(looptime);
		$('#tableId').val(temp);
		updatecolumn();
	}
}
/* 删除子规则列表信息按钮 */
function deletes(ruleId,wfId,nId){
	var checkbox = document.getElementsByName("checkbox");
	var temp = 0;
	var id = "";
	for(var i =0 ;i<checkbox.length;i++){
		if(checkbox[i].checked==true){
			temp++;
			id += checkbox[i].value+",";
		}
	}
	if(temp==0){
		alert("请选择");
	}
	window.location.href =ctx + "/workflow/delNRD.hbc?ruleId="+ruleId+"&wfId="+wfId+"&nId="+nId+"&id="+ id;
}

/* 全选全消 */
function checkAll(obj){
	var checkbox = document.getElementsByName("checkbox");
	var temp = true;
	if(obj.checked==false){
		temp = false;
	}
	for(var i =0 ;i<checkbox.length;i++){
			checkbox[i].checked=temp;
	}
}

/* 子规则信息保存重新加载规则页面 */
function reload(nId,wfId,ruleId){
	window.location.href = ctx+'/workflow/nodeRuleInput.hbc?nId='+nId+'&wfId='+wfId+'&ruleId='+ruleId;
}

function add(){
	fm.submit();
}



