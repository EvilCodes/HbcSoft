/**
 * 新增保存
 */
function saveForm(url)
{
	$("#form").attr("action", url+"/template/addSave.hbc");
	if($("#form").valid()){
		$("#form").submit();
	}
}
/**
 * 修改保存
 */
function updateSave(url)
{
	$("#form").attr("action", url+"/template/updateSave.hbc");
	$("#form").submit();
}
/**
 * 关闭
 */
function closeForm(url)
{
	$("#form").attr("action", url+"/template/open.hbc");
	$("#form").submit();
}
/**
 * 删除
 */
function deleteForm(url)
{
	var selectIndex = getSelectIndex();
	
	if(!chdSelectIndex(selectIndex))
	{
		return;
	}
	
	$("#form").attr("action", url+"/template/delete.hbc?selectIndex=" + selectIndex);
	$("#form").submit();
}
/**
 * 修改
 */
function updateForm(url)
{
	var selectIndex = getSelectIndex();
	
	if(!chdSelectIndex(selectIndex))
	{
		return;
	}
	
	$("#form").attr("action", url+"/template/update.hbc?selectIndex=" + selectIndex);
	$("#form").submit();
}
/**
 * 新增
 */
function newForm(url)
{
	$("#form").attr("action", url+"/template/newForm.hbc");
	$("#form").submit();
}
/**
 * 查询
 */
function query(url)
{
	$("#form").attr("action", url+"/template/query.hbc");
	if($("#form").valid()){
		$("#form").submit();
	}
}
/**
 * 查看
 */
function showForm(url)
{
	var selectIndex = getSelectIndex();
	
	if(!chdSelectIndex(selectIndex))
	{
		return;
	}
	
	$("#form").attr("action", url+"/template/showForm.hbc?selectIndex=" + selectIndex);
	$("#form").submit();
}

function getSelectIndex()
{
	var selectIndex = -1;
	var count = 0;
	var allObj = $('[name=rowid]');
	for(var i=0;i<allObj.length;i++){
	    if(allObj.eq(i).is(':checked')){
	    	selectIndex = allObj.eq(i).val();
	    	count++;
	    }
	}
	
	if(count > 1)
	{
		selectIndex = -1;
	}
	return selectIndex;
}

function chdSelectIndex(selectIndex)
{
	if(selectIndex < 0){
		alert("请选择一条要编辑的数据！");
		return false;
	}
	
	return true;
}