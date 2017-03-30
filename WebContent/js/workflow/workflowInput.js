
/* 页面主信息确定按钮 */
function add(){
	if($('#name').val() == "" || $('#name').val > 100){
		alert("请输入流程名称！");
		return;
	}
	
	fm.submit();
}

/* 页面主信息返回按钮 */
function flowReturn(){
	window.location=ctx+'/workflow/showData.hbc';
//	window.location.href = ctx+"/login!index.action";
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

/* 新增工作流节点列表信息 */
function addNode(wfId){
	if($('#baseId').val() == "" || $('#baseId').val() == null){
		alert("请添加并保存主信息页面的信息！");
		return;
	}
	window.location.href = ctx+"/workflow/nodeShowInput.hbc?wfId="+wfId;
}

/* 修改工作流节点列表信息 */
function updateNode(wfId){
	var checkbox = document.getElementsByName("checkbox");
	var temp = 0;
	var id = "";
	for(var i =0 ;i<checkbox.length;i++){
		if(checkbox[i].checked==true){
			temp++;
			id = checkbox[i].value;
		}
	}
	if(temp ==1){
		window.location.href =ctx+"/workflow/nodeShowInput.hbc?wfId="+wfId+"&nId="+ id;
	}else{
		alert("请选择一条流程");
		return ;
	}
}

/* 删除工作流节点列表信息  */
function delNode(wfId){
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
		return ;
	}
	
	window.location.href =ctx+"/workflow/delNode.hbc?wfId="+wfId+"&id="+ id;
}