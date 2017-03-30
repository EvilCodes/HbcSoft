/************************************************************************************
函数名称								函数说明
popupObject							点选返回值对象
setValue							设置值
************************************************************************************/


function uploadify(filePath,sessionId){
	$("#upload").uploadify({
		swf:ctx+'/js/jquery/uploadify/scripts/uploadify.swf', //指定的swf文件
		uploader:ctx+'/uploadAction.action?filePath='+filePath+'&jsessionid='+sessionId,	
		fileDesc: 'Wrod文档(*.doc,*.docx)|Excel文件(*.xls,*.xlsx)|图片文件(*.jpg;*.png;*.gif;*.bmp;*.jpeg;*.tiff;*.dwg)|压缩文件(*.zip;*.rar;*.7z)',    //提示选择哪些文件，‘|’可以把提示改为下拉列表多个选项
		fileExt: '*.doc;*.docx|*.xls,*.xlsx|*.jpg;*.png;*.gif;*.bmp;*.jpeg;*.tiff;*.dwg|*.zip;*.rar;*.7z',   //与fileDesc合用，一一对应，选择不同的选项可以过滤出来不同类型文件
		queueID: 'fileQueue',  //选择后文件所放位置
		auto:true,  //是否自动上传
		multi: true,   //是否支持多文件上传
		removeCompleted: false,  //上传完成后是否自动移除，默认为true
		fileObjName:'file',
		sizeLimit: '41943041',   //上传文件大小限制单位字节
		buttonText:'选择文件',
		buttonCursor: 'pointer',
		onError: function (event, queueId, fileObj, errorObj) {
			if (errorObj.type === 'File Size') {
				alert("《" + fileObj.name.split('.')[0] + "》超出大小限制,请让传小于40M的文件");
			}
		},
		onQueueFull: function (event, queueLimit) {
			return false;
		},
		onFallback: function () {
			alert("您未安装FLASH控件，无法上传！请安装FLASH控件后再试。");
		},
		onComplete: function (event, ID, fileObj, response, data) {

		}, 
		onAllCompleted:function(){
			
		},
		onUploadStart: function(file) {

		},
		onUploadSuccess:function(file,data,response) {
			addAttachment(1,data);
		}
	});
}

function uploadifyOnIndex(divIndex,filePath,sessionId){
	$("#upload_"+divIndex).uploadify({
		swf:ctx+'/js/jquery/uploadify/scripts/uploadify.swf', //指定的swf文件
		uploader:ctx+'/uploadAction.action?filePath='+filePath+'&jsessionid='+sessionId,	
		fileDesc: 'Wrod文档(*.doc,*.docx)|Excel文件(*.xls,*.xlsx)|图片文件(*.jpg;*.png;*.gif;*.bmp;*.jpeg;*.tiff;*.dwg)|压缩文件(*.zip;*.rar;*.7z)',    //提示选择哪些文件，‘|’可以把提示改为下拉列表多个选项
		fileExt: '*.doc;*.docx|*.xls,*.xlsx|*.jpg;*.png;*.gif;*.bmp;*.jpeg;*.tiff;*.dwg|*.zip;*.rar;*.7z',   //与fileDesc合用，一一对应，选择不同的选项可以过滤出来不同类型文件
		queueID: 'fileQueue_'+divIndex,  //选择后文件所放位置
		auto:true,  //是否自动上传
		multi: true,   //是否支持多文件上传
		removeCompleted: false,  //上传完成后是否自动移除，默认为true
		fileObjName:'file',
		sizeLimit: '41943041',   //上传文件大小限制单位字节
		buttonText:'选择文件',
		buttonCursor: 'pointer',
		onError: function (event, queueId, fileObj, errorObj) {
			if (errorObj.type === 'File Size') {
				alert("《" + fileObj.name.split('.')[0] + "》超出大小限制,请让传小于40M的文件");
			}
		},
		onQueueFull: function (event, queueLimit) {
			return false;
		},
		onFallback: function () {
			alert("您未安装FLASH控件，无法上传！请安装FLASH控件后再试。");
		},
		onComplete: function (event, ID, fileObj, response, data) {

		}, 
		onAllCompleted:function(){
			
		},
		onUploadStart: function(file) {

		},
		onUploadSuccess:function(file,data,response) {
			addAttachmentOnIndex(divIndex,1,data);
		}
	});
}

function displayAttachment(operateType,businessKey){
	$.ajax({
		 dataType: "text",
		 url: ctx+"/attachment!datagrid.action?businessKey="+businessKey,	  
		 success:function(data){		
			$.each($.parseJSON(data),function(i,item){
				addAttachment(operateType,item.filePath);
			});
		 }
	});
}

function displayAttachmentOnIndex(divIndex,operateType,businessKey){
	$.ajax({
		 dataType: "text",
		 url: ctx+"/attachment!datagridOnIndex.action?businessKey="+businessKey+"&attaType="+divIndex,	  
		 success:function(data){		
			$.each($.parseJSON(data),function(i,item){
				addAttachmentOnIndex(divIndex,operateType,item.filePath);
			});
		 }
	});
}

function addAttachment(operateType,data){
	var extension=data.substring(data.lastIndexOf(".")+1,data.length);
	switch(extension){
		case 'jpg':
		case 'JPG':
		case 'png':
		case 'gif':
		case 'bmp':
		case 'jpeg':
		case 'tiff':
		case 'dwg':
			var html="<li><div style='position:relative;'>";
			html=html+"<img src='"+ctx+"/"+decodeURI(data)+"' width='60px' height='60px' style='cursor:hand;'>";
			html=html+"<div style='margin-left:1px;'>"; 
			if(operateType!=5){
				html=html+"<a style='cursor:pointer;' align='center' id='"+data+"' class='del'>删除</a>";
			}
			html=html+"<a style='margin-left:5px;cursor:pointer' align='center' id='"+data+"' class='down'>下载</a>";
			html=html+"</div>"; 
			html=html+"</div></li>";
			$("#pics").append(html);
			if(operateType!=5){
				$("#pics").off("click",".del").on('click','.del',function(){
					var obj =$(this);
	                $.ajax({
	                    dataType: "text",
	                    url: "${ctx}/removeAction.action?filePath="+encodeURI(obj.attr('id')),	                   
	                    success: function(data){
	                    	 var json = $.parseJSON(data);
	                    	 if(json.code==1){
	                    		 obj.parent().parent().parent().remove(); 
	                    	 }else{
	                    		 $.messager.alert('提示信息！', json.msg, 'warning',function(){});
	                    	 }
	                    } 
	                });
				});
			}	
			$("#pics").off("click",".down").on("click",".down",function(){
				var filePath=$(this).attr('id');
				var fileName= filePath.substring(filePath.lastIndexOf("/")+1);
//				window.location="${ctx}/downloadAction.action?filePath="+encodeURI(encodeURI(filePath))+"&fileName="+encodeURI(encodeURI(fileName));
				window.location="${ctx}/downloadAction.action?filePath="+filePath+"&fileName="+fileName;
			});
			break;
		case 'doc':
		case 'docx':
		case 'xls':
		case 'xlsx':
		case 'txt':
		case 'pdf':
			var html = "<li id='"+ctx+'/'+data+"'>附件：<a style='cursor:pointer;' class='docDown' id='"+data+"'>"+data.substring(data.lastIndexOf("/")+1,data.length)+"</a>";  
			if(operateType!=5){
				html=html+"<a style='margin-left:5px;cursor:pointer;' id='"+data+"' class='docDel'>删除</a>";
			}
			html=html+"</li>";
			$("#docs").append(html);
			if(operateType!=5){
				$('#docs').off("click",".docDel").on('click','.docDel',function(){
					var obj =$(this);
	                $.ajax({
	                    dataType: "text",
	                    url: "${ctx}/removeAction.action?filePath="+encodeURI($(this).attr('id')),	                   
	                    success: function(data){
	                    	var json = $.parseJSON(data);
	                    	if(json.code==1){
	                    		 obj.parent().remove();  
	                    	}else{
	                    		 $.messager.alert('提示信息！', json.msg, 'warning',function(){});
	                    	}
	                    } 
	                });
				});
			}
			
			$("#docs").off("click",".docDown").on("click",".docDown",function(){
				var filePath=$(this).attr('id');
				var fileName= filePath.substring(filePath.lastIndexOf("/")+1);
				window.location="${ctx}/downloadAction.action?filePath="+encodeURI(encodeURI(filePath))+"&fileName="+encodeURI(encodeURI(fileName));
			});
			break;	
	}
}

function addAttachmentOnIndex(divIndex,operateType,data){
	var extension=data.substring(data.lastIndexOf(".")+1,data.length);
	switch(extension){
		case 'jpg':
		case 'JPG':
		case 'png':
		case 'gif':
		case 'bmp':
		case 'jpeg':
		case 'tiff':
		case 'dwg':
			var html="<li><div style='position:relative;'>";
			html=html+"<img src="+ctx+"/"+data+" width='60px' height='60px' style='cursor:hand;'>";
			html=html+"<div style='margin-left:1px;'>"; 
			if(operateType!=5){
				html=html+"<a style='cursor:pointer;' align='center' id='"+data+"' class='del'>删除</a>";
			}
			html=html+"<a style='margin-left:5px;cursor:pointer' align='center' id='"+data+"' class='down'>下载</a>";
			html=html+"</div>"; 
			html=html+"</div></li>";
			$("#pics_"+divIndex).append(html);
			if(operateType!=5){
				$("#pics_"+divIndex).off("click",".del").on('click','.del',function(){
					var obj =$(this);
	                $.ajax({
	                    dataType: "text",
	                    url: "${ctx}/removeAction.action?filePath="+encodeURI(obj.attr('id')),
	                    success: function(data){
	                    	 var json = $.parseJSON(data);
	                    	 if(json.code==1){
	                    		 obj.parent().parent().parent().remove(); 
	                    	 }else{
	                    		 $.messager.alert('提示信息！', json.msg, 'warning',function(){});
	                    	 }
	                    } 
	                });
				});
			}	
			$("#pics_"+divIndex).off("click",".down").on("click",".down",function(){
				var filePath=$(this).attr('id');
				var fileName= filePath.substring(filePath.lastIndexOf("/")+1);
				window.location="${ctx}/downloadAction.action?filePath="+filePath+"&fileName="+fileName;
			});
			break;
		case 'doc':
		case 'docx':
		case 'xls':
		case 'xlsx':
		case 'txt':
		case 'pdf':
			var html = "<li id='"+ctx+'/'+data+"'>附件：<a style='cursor:pointer;' class='docDown' id='"+data+"'>"+data.substring(data.lastIndexOf("/")+1,data.length)+"</a>";  
			if(operateType!=5){
				html=html+"<a style='margin-left:5px;cursor:pointer;' id='"+data+"' class='docDel'>删除</a>";
			}
			html=html+"</li>";
			$("#docs_"+divIndex).append(html);
			if(operateType!=5){
				$('#docs_'+divIndex).off("click",".docDel").on('click','.docDel',function(){
					var obj =$(this);
	                $.ajax({
	                    dataType: "text",
	                    url: "${ctx}/removeAction.action?filePath="+encodeURI($(this).attr('id')),	                   
	                    success: function(data){
	                    	var json = $.parseJSON(data);
	                    	if(json.code==1){
	                    		 obj.parent().remove();  
	                    	}else{
	                    		 $.messager.alert('提示信息！', json.msg, 'warning',function(){});
	                    	}
	                    } 
	                });
				});
			}
			
			$("#docs_"+divIndex).off("click",".docDown").on("click",".docDown",function(){
				var filePath=$(this).attr('id');
				var fileName= filePath.substring(filePath.lastIndexOf("/")+1);
				window.location="${ctx}/downloadAction.action?filePath="+encodeURI(encodeURI(filePath))+"&fileName="+encodeURI(encodeURI(fileName));
			});
			break;	
	}
}

function installAttachment(){
	var attachmentStr='';
	$.each($('#pics').find('img'),function(i,n){
		attachmentStr+=n.src;
		attachmentStr+=",";
	});
	$.each($('#docs li'),function(i,n){
		attachmentStr+=n.id;
		attachmentStr+=",";
	});
	attachmentStr=attachmentStr.substring(0,attachmentStr.length-1);
	return attachmentStr;
}

function installAttachmentOnIndex(divIndex){
	var attachmentStr='';
	$.each($('#pics_'+divIndex).find('img'),function(i,n){
		attachmentStr+=n.src;
		attachmentStr+=",";
	});
	$.each($('#docs_'+divIndex+' li'),function(i,n){
		attachmentStr+=n.id;
		attachmentStr+=",";
	});
	attachmentStr=attachmentStr.substring(0,attachmentStr.length-1);
	return attachmentStr;
}

/* 
* formatMoney(s,type) 
* 功能：金额按千位逗号分割 
* 参数：eValue，需要格式化的金额数值. 
* 参数：type,判断格式化后的金额是否需要小数位. 
* 返回：返回格式化后的数值字符串. 
*/ 
function formatMoney(eValue,type){
	if(/[^\-0-9\.]/.test(eValue))
	    return "0.00";
	if(eValue==null||eValue=="")
		return "0.00";
	
	var eValue = parseFloat(eValue);
	if (isNaN(eValue)){
		return "0.00";
	}
	eValue = eValue.toFixed(type);
	var intPart = "";
	var decPart = "";
	if (eValue!=""&&eValue.indexOf(",")>=0){
	    eValue=eValue.replace(/,/g,"");
	}
	if (eValue.indexOf(".")>=0){
		intPart=eValue.split(".")[0];
		decPart=eValue.split(".")[1];
		if(decPart.length==1){
			decPart+="0";
		}
	}else{
		intPart = eValue;
		if(eValue!=""){
			eValue+=".00";
			decPart="00";
		}
	}
	var num  =  intPart+"";  
	var  re=/(-?\d+)(\d{3})/;
	while(re.test(num)){  
	    num=num.replace(re,"$1,$2");
	}  
	if (eValue.indexOf(".")>=0){
	    eValue=num + "." + decPart;
	}else{
	    eValue=num ;
	}
	
	if (type == 0) {// 不带小数位(默认是有小数位) 
		var a = eValue.split("."); 
		if (a.length>1) { 
			eValue = a[0]; 
		} 
	}
	return eValue;
	
}

//去掉数字中的千分位
function formatNumber(eValue){
    return eValue.replace(/,/g,"");
}

//处理数字。如果数字为非法的格式，则转为0
function dealNumber(eValue){
	var val = eValue;
	if(isNaN(val)){
		val = 0.00;
	}
	return val;
}

//小写金额转换为大写金额
function AmountInWords(dValue, maxDec){
  // 验证输入金额数值或数值字符串：
  dValue = dValue.toString().replace(/,/g, ""); dValue = dValue.replace(/^0+/, ""); // 金额数值转字符、移除逗号、移除前导零
  if (dValue == "") { 
	  return "零元整";// （错误：金额为空！）
  } else if (isNaN(dValue)) { 
	  return "错误：金额不是合法的数值！"; 
  }  
    
  var minus = ""; // 负数的符号“-”的大写：“负”字。可自定义字符，如“（负）”。
  var CN_SYMBOL = ""; // 币种名称（如“人民币”，默认空）
  if (dValue.length > 1) {
	  if (dValue.indexOf('-') == 0) { dValue = dValue.replace("-", ""); minus = "负"; } // 处理负数符号“-”
	  if (dValue.indexOf('+') == 0) { dValue = dValue.replace("+", ""); } // 处理前导正数符号“+”（无实际意义）
  }
    
  // 变量定义：
  var vInt = ""; var vDec = ""; // 字符串：金额的整数部分、小数部分
  var resAIW; // 字符串：要输出的结果
  var parts; // 数组（整数部分.小数部分），length=1时则仅为整数。
  var digits, radices, bigRadices, decimals; // 数组：数字（0~9——零~玖）；基（十进制记数系统中每个数字位的基是10——拾,佰,仟）；大基（万,亿,兆,京,垓,杼,穰,沟,涧,正）；辅币（元以下，角/分/厘/毫/丝）。
  var zeroCount; // 零计数
  var i, p, d; // 循环因子；前一位数字；当前位数字。
  var quotient, modulus; // 整数部分计算用：商数、模数。

  // 金额数值转换为字符，分割整数部分和小数部分：整数、小数分开来搞（小数部分有可能四舍五入后对整数部分有进位）。
  var NoneDecLen = (typeof(maxDec) == "undefined" || maxDec == null || Number(maxDec) < 0 || Number(maxDec) > 5); // 是否未指定有效小数位（true/false）
  parts = dValue.split('.'); // 数组赋值：（整数部分.小数部分），Array的length=1则仅为整数。
  if (parts.length > 1){
	  vInt = parts[0]; vDec = parts[1]; // 变量赋值：金额的整数部分、小数部分
	  if(NoneDecLen) {
		  maxDec = vDec.length > 5 ? 5 : vDec.length;
	  } // 未指定有效小数位参数值时，自动取实际小数位长但不超5。
	  var rDec = Number("0." + vDec);   
	  rDec *= Math.pow(10, maxDec); rDec = Math.round(Math.abs(rDec)); rDec /= Math.pow(10, maxDec); // 小数四舍五入
	  var aIntDec = rDec.toString().split('.');
	  if(Number(aIntDec[0]) == 1) { 
		  vInt = (Number(vInt) + 1).toString();
	  } // 小数部分四舍五入后有可能向整数部分的个位进位（值1）
	  if(aIntDec.length > 1) { 
		  vDec = aIntDec[1]; 
	  } else { 
		  vDec = "";
	  }
  }else {
	  vInt = dValue; vDec = ""; 
	  if(NoneDecLen) { 
		  maxDec = 0; 
	  } 
  }  
  if(vInt.length > 44) { 
	  return "错误：金额值太大了！整数位长【" + vInt.length.toString() + "】超过了上限——44位/千正/10^43（注：1正=1万涧=1亿亿亿亿亿，10^40）！"; 	
  }
    
  // 准备各字符数组 Prepare the characters corresponding to the digits:
  digits = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); // 零~玖
  radices = new Array("", "拾", "佰", "仟"); // 拾,佰,仟
  bigRadices = new Array("", "万", "亿", "兆", "京", "垓", "杼", "穰" ,"沟", "涧", "正"); // 万,亿,兆,京,垓,杼,穰,沟,涧,正
  decimals = new Array("角", "分", "厘", "毫", "丝"); // 角/分/厘/毫/丝
    
  resAIW = ""; // 开始处理
    
  // 处理整数部分（如果有）
  if (Number(vInt) > 0){
	  zeroCount = 0;
	  for (i = 0; i < vInt.length; i++){
		  p = vInt.length - i - 1; d = vInt.substr(i, 1); quotient = p / 4; modulus = p % 4;
		  if (d == "0") { 
			  zeroCount++; 
		  } else {
			  if (zeroCount > 0) {
				  resAIW += digits[0];
			  }
			  zeroCount = 0; resAIW += digits[Number(d)] + radices[modulus];
		  }
		  if (modulus == 0 && zeroCount < 4) { 
			  resAIW += bigRadices[quotient];
		  }
	  }
	  resAIW += "元";
  }
    
  // 处理小数部分（如果有）
  for (i = 0; i < vDec.length; i++) {
	  d = vDec.substr(i, 1);
	  if (d != "0") { 
		  resAIW += digits[Number(d)] + decimals[i]; 
	  } 
  }
    
  // 处理结果
  if (resAIW == "") { resAIW = "零" + "元"; } // 零元
  if (vDec == "") { resAIW += "整"; } // ...元整
  resAIW = CN_SYMBOL + minus + resAIW; // 人民币/负......元角分/整
  return resAIW;
}
		
function banBackSpace(e){
	var ev = e || window.event;//获取event对象   
	var obj = ev.target || ev.srcElement;//获取事件源     
	var t = obj.type || obj.getAttribute('type');//获取事件源类型  
	
	//获取作为判断条件的事件类型 
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    
    //处理undefined值情况 
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
    //并且readOnly属性为true或disabled属性为true的，则退格键失效  
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效    
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
    if (flag2 || flag1) 
    	return false;//这里如果写 event.returnValue = false 无法实现效果 
    	
}

//设置指定输入框的金额为带千分位格式的
function setAmountStr(objId){
	var val = $("#"+objId).val();
	val = formatNumber(val);
	var strval = formatMoney(val,2);
	$("#"+objId).val(strval);
}

//设置指定输入框的金额为带千分位格式的.obj为对象
function formatMoneyObject(obj){
	var val = obj.value;
	val = formatMoney(val, 2);
	obj.value = val;
}

//设置指定输入框的金额为不带千分位格式的.obj为对象
function formatNumberObject(obj){
	var val = obj.value;
	val = formatNumber(val);
	obj.value = val;
}

//校验输入，只能输入数字
function inputRegExpMoney(obj){
	if(obj.value){
		var express = '(^0$)|(^(-)?0\\.\\d{0,2}$)|(^(-)?[1-9]?[0-9]{0,12}(\\.\\d{0,2})?$)';
		if(regExpNoTip(obj.value,express)){
			obj.value=obj.value.substring(0,obj.value.length-1);
			return inputRegExpMoney(obj,express);
		}
	}
}

/*function formatCardNo()
{
	var max;
	max = 10;
	var obj=$('#depositBnak').value.length;
	if (obj > max) {
			
		alert("不能超过10个字!");
	}
	
}*/

function regExpNoTip(value,express){
	if(!value){						
		return false;
	}
	value=value+"";
	var pattern = null;
	if("string"==typeof(express)){
		pattern=new RegExp(express);
	}
	else{
		pattern=express;
	}	
	return value.search(pattern)<0;
	
}

/**
 * 讲普通的对象转换为easyui combobox 对象
 * @param normalObject
 * @returns
 */
function normalObject2ComboObject(normalObject){
	if(!normalObject){
		return undefined;
	}
	
	var comboStr;
	
	if(getBrowserVersion() == 1)
	{
		if(jsonStringify(normalObject).indexOf(":{")>0)
		{
			var normalArray = jsonStringify(normalObject).split("},");
			comboStr="[";
			for(var i=0;i<normalArray.length;i++){
				if(i!=normalArray.length-1){
					comboStr+=normalArray[i].substring(normalArray[i].indexOf(':')+1,normalArray[i].length+1)+"},";
				}else{
					comboStr+=normalArray[i].substring(normalArray[i].indexOf(':')+1,normalArray[i].length+1);
				}
			}
			comboStr= comboStr.substring(0,comboStr.length-1);
			comboStr+=']';
		}else{
			comboStr = "[" + jsonStringify(normalObject) + "]";
		}
		if(eval('('+comboStr+')')){
			return eval('('+comboStr+')');
		}
	}else{
		if(JSON.stringify(normalObject).indexOf(":{")>0)
		{
			var normalArray = JSON.stringify(normalObject).split("},");
			comboStr="[";
			for(var i=0;i<normalArray.length;i++){
				if(i!=normalArray.length-1){
					comboStr+=normalArray[i].substring(normalArray[i].indexOf(':')+1,normalArray[i].length+1)+"},";
				}else{
					comboStr+=normalArray[i].substring(normalArray[i].indexOf(':')+1,normalArray[i].length+1);
				}
			}
			comboStr= comboStr.substring(0,comboStr.length-1);
			comboStr+=']';
		}else{
			comboStr = JSON.stringify(normalObject);
		}
		return eval('('+comboStr+')');
	}
}

//得到页数
function getPageSize(pageSize){
	var iPageSize = pageSize;
	if (iPageSize == undefined || iPageSize == "" || parseInt(iPageSize) <= 0){
		iPageSize = 10;
	}
	return iPageSize;
}
//得到pageList对象
function getPageListObj(pageSize){
	//eval(strPageList)
	var strPageList="[";
	var iNum = parseInt(pageSize);
    var haveInsert = false;
	for (var i=1;i<=10;i++){
		var iTemp = i*10;
		if (iNum<iTemp && !haveInsert){
			strPageList+=iNum+",";
			haveInsert = true;
		}
		if (iNum!=iTemp || iTemp == 50){
			strPageList+=iTemp;
			if (i<10){
				strPageList+=",";
			}
		}
	}
	if (iNum>50){
		strPageList+=","+iNum;
	}
	
	strPageList+="]";
	return eval("("+strPageList+")");
}

function jsonStringify(value, replacer, space) {

	var i;
	gap = '';
	indent = '';
	
	if (typeof space === 'number') {
		for (i = 0; i < space; i += 1) {
			indent += ' ';
		}
	
	} else if (typeof space === 'string') {
		indent = space;
	}
	
	rep = replacer;
	if (replacer && typeof replacer !== 'function' &&
			(typeof replacer !== 'object' ||
			typeof replacer.length !== 'number')) {
		throw new Error('JSON.stringify');
		}
	
	return str('', {'': value});
}

function str(key, holder) {

	var i,
		k,
		v,
		length,
		mind = gap,
		partial,
		value = holder[key];

	if (value && typeof value === 'object' &&
			typeof value.toJSON === 'function') {
		value = value.toJSON(key);
	}

	if (typeof rep === 'function') {
		value = rep.call(holder, key, value);
	}

	switch (typeof value) {
		case 'string':
			return quote(value);
		case 'number':
			return isFinite(value) ? String(value) : 'null';
		case 'boolean':
		case 'null':
			return String(value);
		case 'object':
			if (!value) {
				return 'null';
			}
			gap += indent;
			partial = [];
			if (Object.prototype.toString.apply(value) === '[object Array]') 
			{
				length = value.length;
				for (i = 0; i < length; i += 1) {
					partial[i] = str(i, value) || 'null';
				}
				v = partial.length === 0? '[]': gap?
						'[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
						: '[' + partial.join(',') + ']';
				gap = mind;
				return v;
			}
			if (rep && typeof rep === 'object')
			{
				length = rep.length;
				for (i = 0; i < length; i += 1)
				{
					if (typeof rep[i] === 'string')
					{
						k = rep[i];
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ': ' : ':') + v);
						}
					}
				}
			} else {
				for (k in value) {
					if (Object.prototype.hasOwnProperty.call(value, k)) {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap? ': ': ':') + v);
						}
					}
				}
			}
	
			v = partial.length === 0? '{}': gap
					? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
							: '{' + partial.join(',') + '}';
			gap = mind;
			return v;
	}
}

function quote(string) {

	rx_escapable = /[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
	rx_escapable.lastIndex = 0;
	return rx_escapable.test(string)? '"' + string.replace(rx_escapable, function (a) {
	var c = meta[a];
	return typeof c === 'string'? c
		: '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
	}) + '"' 
	: '"' + string + '"';
}

function getBrowserVersion()
{
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/(msie\s|trident.*rv:)([\w.]+)/)) ? Sys.ie = s[1] :
	(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

	if(Sys.ie == "8.0")
	{
		return 1;
	}else{
		return 2;
	}

}

function formDisable(flag){
	$('.textbox-text').each(function(){//textbox-disabled
		$(this).attr('disabled',true);
	});
	$('.textbox-button').each(function(){
		$(this).linkbutton('disable');
	});
	
	$('.combo-arrow').each(function(i,item){
		$(this).addClass('textbox-icon-disabled');
	});
	if(flag){
		$("textarea").each(function(){
			$(this).attr("readonly",true);
		});
	}else{
		$("textarea").each(function(){
			$(this).attr("disabled",true);
		});
		
	}
}
function hiddenGridToolbar(operateType){
	if(operateType==5 || operateType==6|| operateType==7 || operateType==10 || operateType==11 || operateType==12|| operateType==8|| operateType==9){
		$('.datagrid-toolbar a').each(function(){
			$(this).linkbutton('disable');
		});
	}
}

function checkIsValid(obj){
	var isValid=obj.form('validate');
	if(!isValid){
		alert("请完善必填信息!");
		return false;
	}
	return isValid; 
}

/**
 * 更改预算类型时候清空项目和科目信息
 */
function clearItem(obj,itemNo,itemName,subjectId,subjectName,groupLeaderId,groupLeaderName,groupLeaderTel,budgetCode,budgetId){
	if(obj==1){
		$('#itemNo').textbox('setValue','');
		$('#itemName').textbox('setValue','');
		$('#subjectId').val('');
		$('#subjectName').textbox('setValue','');
	}else if(obj==2){
		if(itemNo){
			$('#'+itemNo).textbox('setValue','');
		}
		if(itemName){
			$('#'+itemName).textbox('setValue','');
		}
		if(subjectId){
			$('#'+subjectId).val('');
		}
		if(subjectName){
			$('#'+subjectName).textbox('setValue','');
		}
		if(groupLeaderId){
			$('#'+groupLeaderId).val('');
		}
		if(groupLeaderName){
			$('#'+groupLeaderName).textbox('setValue','');
		}
		if(groupLeaderTel){
			$('#'+groupLeaderTel).textbox('setValue','');
		}
		if(budgetCode){
			$('#'+budgetCode).val('');
		}
		if(budgetId){
			$('#'+budgetId).val('');
		}
	}
}



/**
 * 当预算类型更改时项目类型也更改
 * */
function alterForm(budgetType,projectType){
	var dictCode=null;
    if($('#'+budgetType).textbox('getValue')=='4'){
    	dictCode='horizontalBudgetItemType';
    }else if($('#'+budgetType).textbox('getValue')=='9'){
    	dictCode='operateFeesAbroadBudgetType';
    }
    $('#projectType').combobox('clear');
    var url=ctx+"/sys/dataDict/dataDict!getDataDictListInfo.action?dictCode="+dictCode+"&showType=input";
    $('#projectType').combobox('reload', url);
}
/**
 * 获取查看连接
 * @param value
 * @param row
 * @param index
 * @returns
 */
function getlink(value,row,index){
	if(row.status==0){
		return value;
	}
	var str = '<a href = "'+ctx+'/workflow/manage/showTable.hbc?isEnd=3&businessId='+row.id+'" target = "_blank">'+value+'</a>';
	return str;
}
/**
 * 父页面调用iframe中的datagrid方法
 * @param gridName
 * @param method
 * @returns
 * @author liang
 */
function datagridIframe(gridName,method){
	return $("#"+gridName).datagrid(method);
}


/**
 * 检验文本输入框字符长度
 * 中文2个长度，数字、字母一个长度
 * @param obj 校验的DOM 对象
 * @param length 限定长度
 * @author why
 * @since 20160330
 * */
function checkTextarea(obj, length, targetObj){
	var txt = obj.value;
	var len = 0;
	var lenZh = 0;
	for(var i = 0;i<txt.length;i++){
		if(txt.charAt(i).search("[\u4e00-\u9fa5]")==0){    //所有的中文 
			len += 2;                                      //是中文+2
		}else if(txt.charAt(i).search("[^\x00-\xff]")==0){
			len += 3;
		}else{
			len += 1;                                       //字符+1
		}
		if(len <= length){
			lenZh += 1;	                                    //用来SUBSTRING
		}
	}
	if( len > 0 && len <= length){	// 在字段长度之内
		if(targetObj)targetObj.disabled = null;	// 可以提交
	}else if(len > length){			// 大于字段长度了
		if(targetObj)targetObj.disabled = null;	// 可以提交
		obj.value = txt.substring(0,lenZh);                // 大于长度的字符的只要合适长度的
	}else if(len < 1){
		if(targetObj)targetObj.disabled = "disabled"; // 不可提交
	}
	var len2 = length - len;
	if(len > length) len2 = 0;
	// 创建一个提示框
	var infoDIV = document.getElementById("infoDIV");
	if (infoDIV == null) {
		infoDIV = document.createElement("DIV");
		infoDIV.index = "999";
		infoDIV.style.position = "absolute";
		infoDIV.style.backgroundColor = "white";
		infoDIV.style.color = "black";
		infoDIV.style.width = "119px";
		infoDIV.style.height = "16px";
		infoDIV.style.padding = "3px";
		infoDIV.style.whiteSpace = "normal";
		infoDIV.style.border = "1px solid ";
		infoDIV.id = "infoDIV";
	}
	infoDIV.style.display = "block";
	// 找提示框位置
	var p = obj;
	var eL = 0;
	var eT = 0;
	while (p && p.tagName != "BODY") {
		eT += p.offsetTop;
		eL += p.offsetLeft;
		p = p.offsetParent;
	}
	infoDIV.style.left = eL>120?eL-120:0;
	infoDIV.style.top  = eT;	  
	document.body.appendChild(infoDIV);
	infoDIV.innerHTML ="还能输入"+len2+"个字符串";
	var aa = function(){
		var infoDIV = document.getElementById("infoDIV");
		if (infoDIV != null) {
			infoDIV.style.display = "none";
		}
	};
	window.setTimeout(aa,2000);
}


/**
 * 检验文本输入框字符长度
 * 中文2个长度，数字、字母一个长度
 * @param obj 校验的DOM 对象
 * @param length 限定长度
 * @param isOneChar 字符按照1长度来计算
 * @author why
 * @since 20160330
 * */
function checkTextarea2(obj, length,isOneChar){
	var txt = obj.value;
	var len = 0;
	var lenZh = 0;
	if(isOneChar){
		len = txt.length;   
	}else{
		for(var i = 0;i<txt.length;i++){
			if(txt.charAt(i).search("[\u4e00-\u9fa5]")==0){    //所有的中文 
				len += 2;                                      //是中文+2
			}else if(txt.charAt(i).search("[^\x00-\xff]")==0){
				len += 3;
			}else{
				len += 1;                                       //字符+1
			}
			if(len <= length){
				lenZh += 1;	                                    //用来SUBSTRING
			}
		}
	}
	var len2 = length - len;
	if(len > length){
		len2 = 0;
		return false;
	}else{
		return true;
	}
	
}


//判断传入的id是否为空，为空则提示用户先暂存后再提交
function checkIdIsNull(strId){
	if (strId==undefined||strId==""){
		alert("请保存后再提交");
		return false;
	}
	return true;
}


//根据用途判断是否政采
function checkZC(dictCode,dictName,zcid,dictProperty,isNotControlDisabled){
	if(isNotControlDisabled == undefined || isNotControlDisabled == null){
		isNotControlDisabled = false;
	}
	$.ajax({
		url : ctx+'/cost/returnMoneyWipe/returnMoneyWipe!checkZC.action',
		data : {disCode : dictCode,dictName : dictName},
		dataType : 'json',
		type : 'post',
		success : function(data){
			var json=eval(data);
			if(json.flag==true){
				$("#"+zcid).combobox("setValue","1");
				$("#"+zcid).combobox('disable'); 
				$("#"+dictProperty).combobox("setValue",json.type);
			}else if(json.flag==false){
				$("#"+zcid).combobox('setValue','0');
				$("#"+zcid).combobox('disable'); 
				$("#"+dictProperty).combobox("setValue",'无');
			}
			
			if(isNotControlDisabled){
				$("#"+zcid).combobox('enable'); 
			}
			
		}
	});
}