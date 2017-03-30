
var messages = new Object();

/**
 * 功能：弹出alert提示窗体
 */
messages.alert = function(message){
	var d = dialog({
		title: '提示',
		content: message,
		cancel: false,
		ok: function () {
		}
	});
	d.width(200);
	d.showModal();
}
/**
 * 功能：弹出alert提示窗体
 */
messages.alertToUrl = function(message,url){
	var d = dialog({
		title: '提示',
		content: message,
		cancel: false,
		ok: function () {	
			window.location.href=url;
		}
	});
	d.width(200);
	d.showModal();
}



/**
 * 功能：弹出询问提示窗体
 */
messages.confirm = function(message,callback){
	var d = dialog({
	    title: '提示',
	    content: message,
	    okValue: '确定',
	    cancelValue: '取消',
	    ok: function () {
			callback();
	    },	   
	    cancel: function () {
	    }
	});
	d.width(200);
	d.showModal();
}

