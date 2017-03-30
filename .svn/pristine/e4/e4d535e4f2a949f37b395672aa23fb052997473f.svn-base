/**
 *	ModuleName:工作流管理页面
 *	Author:zhangdengyu
 *	CreateDate:2015-09-05
 */
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
function updateConfig(){
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
		window.location.href =ctx+"/workflow/configInput.hbc?baseId="+ id;
	}else{
		alert("请选择一条配置");
		return ;
	}
}

/* 删除 */
function deleteConfig(){
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
		window.location.href =ctx+"/workflow/delConfig.hbc?id="+ id;
	}
}
/* 新增跳转 */
function addConfig(){
	window.location.href = ctx+"/workflow/configInput.hbc";
}
/* 翻页 */
function gopage(obj){
	//window.location.href = ctx+"/workflow/manage/configShow.hbc?page="+obj;
	$('#page').val(obj);
	$('#form').submit();
}
/* 返回主页 */
function home(){
	window.location.href = ctx+"/indexInit.hbc";
}
/*查询信息*/
function searchConfig(){
	fm.submit();
}