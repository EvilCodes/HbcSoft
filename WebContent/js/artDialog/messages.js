/**
 * 作者：赵宁
 * 
 * 功能：在artDialog基础上进一步封装,以下弹出不能穿越iframe框架
 * 
 * 调用方法：
 * 		1、alert弹窗的调用方法：
 * 			示例：
 * 			messages.alert('内容');
 * 		2、操作正确弹窗的调用方法：
 * 			示例：
 * 			messages.success('内容',function(result){
 * 				//result == true 时，说明点确定按钮了，执行相应的方法
 * 				alert(result);
 * 			});
 * 		3、操作错误弹窗的调用方法：
 * 			示例：
 * 			messages.error('内容',function(result){
 * 				//result == true 时，说明点确定按钮了，执行相应的方法
 * 				alert(result);
 * 			});
 * 		4、询问弹窗的调用方法：
 * 			示例：
 * 			messages.confirm('内容',function(result){
 * 				//result返回的是true或false
 * 				if(result){
 * 					alert('点击确定按钮了，执行相应的操作');
 * 				}else{
 * 					alert('点击取消按钮了，执行相应的操作');
 * 				}
 * 			});
 * 
 * 		5、弹出窗口，并获得子页传的参数
 * 			示例：
 * 			5.1父页调用下面代码
 * 			messages.open('28','保存','../pages/save.jsp','pareamName',function(result){
 * 				//result是子页传过来的参数
 * 				alert(result);
 * 			});
 * 			5.2子页调用下面代码
 * 			messages.returnPaream('28','pareamName','子页返回参数');
 * 			
 * 		注释：如上1、2、3、4例弹出窗方法，需要弹出提示框在iframe框架之上，需将messages换成artDialog，其他格式及写法与上面示例一致。
 */
var messages = new Object();

//全局参数
var _lock = true;		//是否遮罩
var _esc = false;		//是否按ESC键关闭窗体
var _dblclick = false;	//是否双击关闭窗体
var _opacity = 0.2;		//遮罩透明度
var _color = '#BBFFFF';	//遮罩层颜色

var dialog = art.dialog;
//dialog = art.dialog.through;

/**
 * 功能：弹出alert提示窗体
 */
messages.alert = function(content){
	dialog({
	    title: '提示信息',
	    content: "<span style=\"font-size: 12px\">"+content+"</span>",
	    icon: 'warning',
	    lock:_lock,
	    dblclick_hide:_dblclick,
	    opacity:_opacity,
	    background: _color,
	    zIndex:300000,
	    esc:_esc,
	    fixed: true
	}).time(3);
}

/**
 * 功能：弹出操作正确提示窗体
 */
messages.success = function(content,callback){
	dialog({
	    title: '操作提示',
	    content: "<span style=\"font-size: 12px\">"+content+"</span>",
	    icon: 'succeed',
	    lock:_lock,
	    dblclick_hide:_dblclick,
	    opacity:_opacity,
	    background: _color,
	    esc:_esc,
	    fixed: true,
	    ok: function(){
			if( callback ) callback(true);
		},
		cancel:false
	});
}

/**
 * 功能：弹出操作正确提示窗体
 */
messages.error = function(content,callback){
	dialog({
	    title: '操作提示',
	    content: "<span style=\"font-size: 12px\">"+content+"</span>",
	    icon: 'error',
	    lock:_lock,
	    dblclick_hide:_dblclick,
	    opacity:_opacity,
	    background: _color,
	    zIndex:300000,
	    esc:_esc,
	    fixed: true,
	    ok: function(){
			if( callback ) callback(true);
	    },
		cancel:false
	});
}

/**
 * 功能：弹出询问提示窗体
 */
messages.confirm = function(content,callback){
	dialog({
	    title: '操作提示',
	    content: "<span style=\"font-size: 12px\">"+content+"</span>",
	    icon: 'question',
	    lock:_lock,
	    dblclick_hide:_dblclick,
	    opacity:_opacity,
	    background: _color,
	    zIndex:300000,
	    esc:_esc,
	    fixed: true,
	    ok: function(){
			if( callback ) callback(true);
	    },
	    cancel:function(){
			if( callback ) callback(false);
		}
	});
}

/**
 * 功能：操作成功后，提示用户信息
 */
messages.tips = function(content,time){
	if(time != "undefined" && time != ""){
		dialog.tips(content,time);
	}else{
		dialog.tips(content);
	}
}

/**
 * 功能：弹出窗体
 */
messages.openWinCallBack = function(id,title,url,pareamName,callback){
	art.dialog.open(url,{
		id:id,
		title:title,
		width:'70%',
		height:'70%',
		lock:_lock,
		dblclick_hide:_dblclick,
		opacity:_opacity,
		background: _color,
		esc:_esc,
		fixed: true,
		close:function(){
			var paream = art.dialog.data(''+pareamName+'');
			if(paream!=undefined && paream != ''){
				if( callback ) callback(paream);
			}
		}
	});
	art.dialog.removeData(''+pareamName+'');
}

/**
 * 功能：返回父页并给父页传参参数
 * 用法：与messages.open方法对称使用。closeId,pareamName两个参数需要与messages.open方法中的id及pareamName对应
 */
messages.returnPaream = function(closeId,pareamName,pareamContent){
	art.dialog.data(''+pareamName+'',''+pareamContent+'');//存储数据
	//关闭窗口
	messages.closeAll();
}

/**
 * 功能：关闭页面所有对话框
 */
messages.closeAll = function(){
	var list = parent.art.dialog.list;
	for (var i in list) {
	    list[i].close();
	};
}

/**
 * 功能：关闭指定父窗口ID的窗口页面
 */
messages.close = function(closeId){
	parent.art.dialog.list[''+closeId+''].close();
}

/**
 * 功能：弹出窗体
 */
messages.openWin = function(id,title,url,width,height){
	art.dialog.open(url,{
		id:id,
		title:title,
		width:width,
		height:height,
		lock:_lock,
		dblclick_hide:_dblclick,
		opacity:_opacity,
		background: _color,
		esc:_esc,
		fixed: true
	});
}

/**
 * 功能：校验时调用的窗体，10秒后自动关闭
 */
messages.checkAlert = function(content,type){
	var dialog;
	if(type=='iframe'){
		dialog = art.dialog.through;
	}else{
		dialog = art.dialog;
	}
	dialog({
	    content: content,
	    icon: 'warning',
	    lock:false,
	    dblclick_hide:true,
	    opacity:_opacity,
	    background: _color,
	    zIndex:300000,
	    esc:true,
	    fixed: true,
	    init: function () {
	    	var that = this, i = 10;
	        var fn = function () {
	            that.title('验证提示&nbsp;&nbsp;' + i + '秒后关闭');
	            !i && that.close();
	            i --;
	        };
	        timer = setInterval(fn, 1000);
	        fn();
	    },
	    close: function () {
	    	clearInterval(timer);
	    },
	    ok:true
	}).show();
}


















//var dialog = art.dialog({
//id: 'N3690',
//width:'70%',
//height:'70%',
//lock:true,
//dblclick_hide:false,
//opacity:0.2,
//background: '#BBFFFF',
//esc:false
//});
//
//jQuery.ajax({
//url: url,
//success: function (data) {
//    dialog.content(data);
//},
//cache: true
//});