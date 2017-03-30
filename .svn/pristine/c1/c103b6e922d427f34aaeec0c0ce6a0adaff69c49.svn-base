/**
 * 子表增行的公用方法
 * @param tableId <table></table>的id
 * @param tbodyId <tbody></tbody>的id
 * @param obj tableId+tbodyId为id值
 */
function test(tableId,tbodyId,obj){
		$.ajax({
			type: 'post',
			data: 'json',
			async:true,
			//url: ctx+'/tag/addTr.hbc?tableId='+tableId+'&tbodyId='+tbodyId+'&tag='+$("#"+obj).val(),
			url: ctx+'/tag/addTr.hbc?tag='+$("#"+obj).val(),
			success: function(msg){
				var ttt = eval("("+msg+")");
				$("#"+tbodyId).append(ttt.trString);
			},
			error: function(xhr,err,data){
				alert("增行失败，请重新登录或联系管理员！");
			}
		})
	}