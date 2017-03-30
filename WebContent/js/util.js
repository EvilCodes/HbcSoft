$(function(){
	asd();
	
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setNotifyServerOnPageUnload(true);
});

function asd(){
	$.ajax({
		  url:"http://192.168.0.253:8080/regulatory/messageController/messageList.do",
		  type:"post",
		  success:function(result){
		     var json = eval('(' + result + ')'); 
$("#header_inbox_bar .badge").html(json.num);
$("#header_inbox_bar #p").html("您有"+json.num+"条新信息。");
$("#header_inbox_bar #ulli").html(json.mes);
		  }
		});
}

function onPageLoad(id){
    var yhlx = id;
    MessagePush.onPageLoad(yhlx);
  }
 function showMessage(autoMessage){
	 $.ajax({
		  url:"http://192.168.0.253:8080/regulatory/messageController/messageList.do",
		  type:"post",
		  success:function(result){
		     var json = eval('(' + result + ')'); 
$("#header_inbox_bar .badge").html(json.num);
$("#header_inbox_bar #p").html("您有"+json.num+"条新信息。");
$("#header_inbox_bar #ulli").html(json.mes);
		  }
		});
 }
 
