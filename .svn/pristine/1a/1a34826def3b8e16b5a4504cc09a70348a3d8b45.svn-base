/* 全选全消 */
function checkAll(obj){
	//$("checkbox").prop('checked',$(obj).prop('checked'));
	var checkbox = document.getElementsByName("checkbox");
	var temp = true;
	if(obj.checked==false){
		temp = false;
	}
	for(var i =0 ;i<checkbox.length;i++){
		checkbox[i].checked=temp;
	}
}
/* 修改 */
function updatedefine(){
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
		window.location.href =ctx+"/workflow/showInput.hbc?wfId="+ id;
	}else{
		alert("请选择一条流程");
		return ;
	}
}

/* 删除 */
function deletedefine(){
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
		return;
	}
	if(confirm("是否删除？")){
		window.location.href =ctx+"/workflow/delData.hbc?id="+ id;
	}
}
/* 新增跳转 */
function adddefine(){
	window.location.href = ctx+"/workflow/showInput.hbc";
}
/* 翻页 */
function gopage(obj){
	//window.location.href = ctx+"/workflow/manage/showData.hbc?page="+obj;
	$('#page').val(obj);
	$('#form').submit();
}

/* 返回主页 */
function gohome(){
	window.location.href = ctx+"/indexInit.hbc";
}

/*查询记录*/
function searchWorkflow(){
	fm.submit();
}