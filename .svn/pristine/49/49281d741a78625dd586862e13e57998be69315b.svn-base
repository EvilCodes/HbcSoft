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
		window.location.href =ctx+"/workflow/businessMapInput.hbc?baseId="+ id;
	}else{
		alert("请选择一条绑定数据");
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
		window.location.href =ctx+"/workflow/delBM.hbc?id="+ id;
	}
}
/* 新增跳转 */
function adddefine(){
	window.location.href = ctx+"/workflow/businessMapInput.hbc";
}
/* 翻页 */
function gopage(obj){
	//window.location.href = ctx+"/workflow/manage/businessMapShowDate.hbc?page="+obj;
	$('#page').val(obj);
	$('#form').submit();
}
/* 返回主页 */
function returnhome(){
	window.location.href = ctx+"/indexInit.hbc";
}
/*查询记录*/
function searchBusiness(){
	fm.submit();
}