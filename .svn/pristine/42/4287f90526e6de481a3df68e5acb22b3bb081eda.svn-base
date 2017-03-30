/**
 * 新增保存
 */
function exportExcel(url)
{
	if(!notEmpty())
	{
		return;
	}
	
	//window.open(url+"/report/exportExcel.hbc?reportId="+$("#reportId").val(),'Conframe');
	$("#form").attr("action", url+"/report/exportExcel.hbc");
	$("#form").submit();
}
/**
 * 查询
 */
function query(url)
{
	if(!notEmpty())
	{
		return;
	}
	
	$("#form").attr("action", url+"/report/query.hbc");
	$("#form").submit();
}

function notEmpty()
{
	var obj = JSON.parse($("#checkParameter").val());
	for(var index = 0; index < obj.length; index++)
	{
		var val = $("#"+ obj[index].fieldName +"").val();
		
		if(val == '')
		{
			alert(obj[index].message);
			$("#"+ obj[index].fieldName +"").focus();
			return false;
		}
	}
	return true;
}