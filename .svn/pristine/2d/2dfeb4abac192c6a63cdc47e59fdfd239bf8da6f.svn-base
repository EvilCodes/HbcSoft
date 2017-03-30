// JavaScript Document

//侧边栏内效果 start


//焦点项效果
$(function(){
	
	$('.mNav>.hd>ul>li').mouseover(function(){
		$(this).addClass('on').siblings().removeClass();
		$('.mNav>.bd>.ulWrap>ul').eq($(this).index()).addClass('on').fadeIn(300).siblings().removeClass().hide();
		var num=$(this).index();
	})

		
})


//二级菜单
$(function(){
	$('.siderBar .ulWrap .on li').mouseover(function(){
		$(this).children('.sub').css('display','block');
		$(this).children('.sub').children('.sub>ul').css('display','block');
		$(this).children('.sub').children('.sub>ul').children('.sub>ul>li').mouseover(function(){
			$(this).children('.sub>ul>li>dl').css('display','block');
			$(this).children('.sub>ul>li>dl').parent('.sub>ul>li').css('background','#a1daf4');
			//$(this).children('.sub>ul>li>dl').parent('.sub>ul>li').children('.sub>ul>li>strong').children('.sub>ul>li>strong>a').css('background-position','0 -47px;');
			})
		$(this).children('.sub').children('.sub>ul').children('.sub>ul>li').mouseout(function(){
			$(this).children('.sub>ul>li>dl').css('display','none');
			$(this).children('.sub>ul>li>dl').parent('.sub>ul>li').css('background','#fff');
		})
		//$(this).children('.sub').children('.sub>ul').children('.sub>ul>li').children('.sub>ul>li>dl').css('display','block');
		
	})
	$('.siderBar .ulWrap .on li').mouseout(function(){
		$(this).children('.sub').css('display','none');
		$(this).children('.sub').children('.sub>ul').css('display','none');
		$(this).children('.sub').children('.sub>ul').children('.sub>ul>li').children('.sub>ul>li>dl').css('display','none');
	})
})


//三级菜单
$(function(){
	$('.mNav .bd .ulWrap>ul.on>li>.sub>ul>li').mouseover(function(){
		$(this).children('dl').addClass('on');
	})
	$('.mNav .bd .ulWrap>ul.on>li>.sub>ul>li').mouseout(function(){
		$(this).children('dl').removeClass('on');
	})
})

//侧栏的宽窄
//siderIco-siderIco
$(function(){
	$("#siderIcoBtn").click(function(){
		if($("#siderIco").hasClass("siderIco")){
			$("#siderIco").removeClass("siderIco");
			//二级菜单
			$(function(){
				$('.siderBar .ulWrap .on li').mouseover(function(){
					$(this).children('.sub').css('display','block');
					$(this).children('.sub').children('.sub>ul').css('display','block');
					
				})
				$('.siderBar .ulWrap .on li').mouseout(function(){
					$(this).children('.sub').css('display','none');
					$(this).children('.sub').children('.sub>ul').css('display','none');
				})
			})
			
		}else{
			$("#siderIco").addClass("siderIco");
			//二级菜单
			$(function(){
				$('.siderBar .ulWrap .on li').mouseover(function(){
					$(this).children('.sub').css('display','none');
					$(this).children('.sub').children('.sub>ul').css('display','none');
					
				})
				$('.siderBar .ulWrap .on li').mouseout(function(){
					$(this).children('.sub').css('display','none');
					$(this).children('.sub').children('.sub>ul').css('display','none');
				})
			})
			
		}	
	})
})
//contentWrap-content
$(function(){
	$("#siderIcoBtn").click(function(){
		if($("#contentWrap").hasClass("content")){
			$("#contentWrap").removeClass("content");
		}else{
			$("#contentWrap").addClass("content");
		}	
	})
})
//侧边栏内效果 end