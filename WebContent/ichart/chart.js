function chart(list,id,title,width,height){
	if(list!=null&&list!=''){
		
	
			var data1=[];
			var data2=[];
		    if(list!=null&&list!=''){
		       var lt = list.split(';');
		       for(var i=0;i<lt.length-1;i++){
		       data1[i]=[lt[i].split(',')[1]];
		       data2[i]=[lt[i].split(',')[0]] 
		       }
		    }
				var data = [
				        	{
				        		name : '',
				        		value:data1,
				        		color:'#1f7e92',
				        		line_width:2
				        	}
				       ];
				var chart = new iChart.Area2D({
						render : id,
						data: data,
						title : title,
						width : width,
						height : height,
						coordinate:{height:'90%',background_color:'#edf8fa'},
						sub_option:{
							hollow_inside:false,//设置一个点的亮色在外环的效果
							point_size:10
						},
						labels:data2
					});
				
				chart.draw();
}
}


function column(list,id,title,width,height){
	if(list!=null&&list!=''){
	var data=[];
    if(list!=null&&list!=''){
       var lt = list.split(';');
       for(var i=0;i<lt.length-1;i++){
       data[i]={
    		   name:lt[i].split(',')[0],
    		   value:lt[i].split(',')[1],
    		   color:color()
       };
       }
    }
    var chart = new iChart.Column2D({
		render : id,
		data: data,
		title : title,
		width : width,
		height : height,
		coordinate:{
			
		}
	});

	/**
	 *自定义组件,画平均线。
	 */
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				/**
				 *计算平均值的高度(坐标Y值)
				 */	
				var avg = chart.total/5,
					coo = chart.getCoordinate(),
					x = coo.get('originx'),
					W = coo.width,
					S = coo.getScale('left'),
					H = coo.height,
					h = (avg - S.start) * H / S.distance,
					y = chart.y + H - h;
				
				//chart.target.line(x,y,x+W,y,2,'#b32c0d')
				//.textAlign('start')
				//.textBaseline('middle')
				//.textFont('600 12px Verdana')
				//.fillText(avg,x+W+5,y,false,'#b32c0d');
			}
	}));	
		
	chart.draw();
	}
};

function pie2D(list,id,title,width,height){
	if(list!=null&&list!=''){
	
	var data=[];
    if(list!=null&&list!=''){
       var lt = list.split(';');
       for(var i=0;i<lt.length-1;i++){
       data[i]={
    		   name:lt[i].split(',')[0],
    		   value:lt[i].split(',')[1],
    		   color:color()
       };
       }
    }
	
	new iChart.Pie2D({
		render : id,
		data: data,
		title : title,
		legend : {
			enable : true
		},
		sub_option : {
			label : {
				background_color:null,
				sign:false,//设置禁用label的小图标
				padding:'0 4',
				border:{
					enable:false,
					color:'#666666'
				},
				fontsize:11,
				fontweight:600,
				width:50,
				color : '#4572a7'
			},
			border : {
				width : 2,
				color : '#ffffff'
			}
		},
		animation:true,
		showpercent:true,
		decimalsnum:2,
		width : width,
		height : height,
		radius:140
	}).draw();
	}
};

function chart1(list,id,title,width,height){
	
	var data1=[];
	var data2=[];
    if(list!=null&&list!=''){
       var lt = list.split(';');
       for(var i=0;i<lt.length-1;i++){
       data1[i]=lt[i].split(',')[1];
       data2[i]=lt[i].split(',')[0] 
       }
    }
	
	var flow=data1;
	/*for(var i=0;i<25;i++){
		flow.push(Math.floor(Math.random()*(10+((i%16)*5)))+10);
	}*/
	
	var data = [
	         	{
	         		name : 'PV',
	         		value:flow,
	         		color:'#ec4646',
	         		line_width:2
	         	}
	         ];
    
	//var labels = ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"];
	var labels = data2;
	var chart = new iChart.LineBasic2D({
		render : id,
		data: data,
		align:'center',
		title : title,
		subtitle : {
			text:'14:00-16:00访问量达到最大值',
			font : '微软雅黑',
			color:'#b4b4b4'
		},
		footnote : {
			text:'ichartjs.com',
			font : '微软雅黑',
			fontsize:11,
			fontweight:600,
			padding:'0 28',
			color:'#b4b4b4'
		},
		width : width,
		height : height,
		shadow:true,
		shadow_color : '#202020',
		shadow_blur : 8,
		shadow_offsetx : 0,
		shadow_offsety : 0,
		background_color:'#ffffff',
		animation : true,//开启过渡动画
		animation_duration:600,//600ms完成动画
		tip:{
			enable:true,
			shadow:true,
			listeners:{
				 //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
				parseText:function(tip,name,value,text,i){
					 return "<span style='color:#005268;font-size:12px;'>"+labels[i]+":00访问量约:<br/>"+
						"</span><span style='color:#005268;font-size:20px;'>"+value+"万</span>";
				}
			}
		},
		crosshair:{
			enable:true,
			line_color:'#ec4646'
		},
		sub_option : {
			smooth : true,
			label:false,
			hollow:false,
			hollow_inside:false,
			point_size:8
		},
		coordinate:{
			width:640,
			height:260,
			striped_factor : 0.18,
			grid_color:'#4e4e4e',
			axis:{
				color:'#252525',
				width:[0,0,4,4]
			},
			scale:[{
				 position:'left',	
				 start_scale:0,
				 end_scale:100,
				 scale_space:10,
				 scale_size:2,
				 scale_enable : false,
				 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
				 scale_color:'#9f9f9f'
			},{
				 position:'bottom',	
				 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
				 scale_enable : false,
				 labels:labels
			}]
		}
	});
	//利用自定义组件构造左侧说明文本
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				//计算位置
				var coo = chart.getCoordinate(),
					x = coo.get('originx'),
					y = coo.get('originy'),
					w = coo.width,
					h = coo.height;
				//在左上侧的位置，渲染一个单位的文字
				chart.target.textAlign('start')
				.textBaseline('bottom')
				.textFont('600 11px 微软雅黑')
				.fillText('访问量(万)',x-40,y-12,false,'#9d987a')
				.textBaseline('top')
				.fillText('(时间)',x+w+12,y+h+10,false,'#9d987a');
				
			}
	}));
//开始画图
chart.draw();
};



function color(){
	return '#'+('00000'+(Math.random()*0x1000000<<0).toString(16)).slice(-6);
}


/**
 * ,
			scale:[{
				 position:'left',	
				 start_scale:1000,
				 end_scale:5000,
				 scale_space:500
			}]*/
