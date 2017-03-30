//初始化
var yfgl = new Object();

//附件保存在FTP服务器上路径
yfgl.saveFtpPath = "bdgl\\yfgl\\work\\";

//附件保存在数据库中路径
yfgl.saveDataPath = "bdgl\\\\yfgl\\\\work\\\\";

//下载或在线打开时，自动创建本地目录
yfgl.saveLocalPath = "C:\\yfgltemp\\";

//大文件控件版权信息
yfgl.ftpInfo = "l%)f%)l#'l(-m$(q).n%)m',&lt;&nbsp;<br>      ";
//192.168.*网段
//yfgl.ftpSInfo = "fcqpiiasjgtohounfprjquogicwgjphhhufjmniqttigwm";
//10.*网段
//yfgl.ftpSInfo = "scjpticstgvojohnhpfbmaiptcilwj";
yfgl.ftpSInfo = "tccptivsjghohofnmpibtaipwc";

//初始化表头样式
yfgl.tableTitle = "Grid_2Title";

//初始化奇数行样式
yfgl.SingleTr = "Grid_2Content";

//初始化偶数行样式
yfgl.doubleTr = "Grid_2Content2";

//初始化鼠标经过样式
yfgl.outTr = "Grid_2Content3";

//初始化点击样式
yfgl.clickTr = "Grid_2Content4";

/**
 * 功能：拼接字符串
 * @来自于网络
 * @return
 */
function StringBuffer() {
	this.__strings__ = new Array();
};
StringBuffer.prototype.append = function (str) {
	this.__strings__.push(str);
};
StringBuffer.prototype.toString = function () {
	return this.__strings__.join("");
};

/**
 * 功能：过滤特殊字符，去空格
 */
yfgl.stringUtil = function(str,num){
	var temp = "";
	switch(num){
		case 0:
			temp = encodeURI(str.replace(/\ /g,"").replace(/\　/g,"")).replace(/\&/g, "%26").replace(/\+/g, "%2B").replace(/\?/g, "%3F").replace(/\#/g, "%23").replace(/\=/g, "%3D");
			break;
		case 1:
			temp = str.replace(/\ /g,"").replace(/\　/g,"");
			break;
		case 2:
			temp = encodeURI(str.replace(/\ /g,"").replace(/\　/g,"")).replace(/\&/g, "%26").replace(/\+/g, "%2B").replace(/\?/g, "%3F").replace(/\#/g, "%23").replace(/\=/g, "%3D");
			break;
		case 3:
			temp = str.replace(/\*/g,"/");
			break;
		case 4:
			temp = encodeURI(str).replace(/\&/g, "%26").replace(/\+/g, "%2B").replace(/\?/g, "%3F").replace(/\#/g, "%23").replace(/\=/g, "%3D");
			break;
	}
	return temp;
}

/**
 * 功能：去除查询条件前后空格，并重置条件值
 */
yfgl.queryUtil = function(obj){
	var temp = yfgl.stringUtil(obj.value,1);
	document.getElementById(obj.id).value = temp;
}

/**
 * 功能：初始化表格样式
 */
yfgl.tableChangeColor = function(objTable){
	//取指定table中所有行
	var t = document.getElementById(objTable).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		if (t[i].sectionRowIndex == 0) {
			t[i].className = yfgl.tableTitle;
		} else {
			t[i].className = (t[i].sectionRowIndex % 2 == 1) ? yfgl.SingleTr : yfgl.doubleTr;
			//给每一行增加点击事件，点击行选中checkbox或radio控件，并使行变色
//			t[i].onclick = function () {
//				var cols = this.getElementsByTagName("td");
//				if (cols[0].childNodes[0].type == "checkbox") {
//					if(cols[0].childNodes[0].disabled != true){
//						if (this.className == yfgl.clickTr) {
//							cols[0].childNodes[0].checked = false;
//							this.className = (this.sectionRowIndex % 2 == 1) ? yfgl.SingleTr : yfgl.doubleTr;
//						} else {
//							cols[0].childNodes[0].checked = true;
//							this.className = yfgl.clickTr;
//						}
//					}
//				}else if(cols[0].childNodes[0].type == "radio"){
//					cols[0].childNodes[0].checked = true;
//					this.className = yfgl.clickTr;
//					yfgl.getRadioTableClass(objTable);
//				}
//			};
			t[i].onmouseover = function () {
				if (this.className != yfgl.clickTr) {
					this.className = yfgl.outTr;
				}
				//只给第一列增加点击事件，点击第一列中的checkbox控件选中，并使行变色
				var cols = this.getElementsByTagName("td");
				cols[0].childNodes[0].onclick = function(){
					yfgl.clickTableTdClass(objTable);
				}
			};
			t[i].onmouseout = function () {
				if (this.className != yfgl.clickTr) {
					this.className = (this.sectionRowIndex % 2 == 1) ? yfgl.SingleTr : yfgl.doubleTr;
				}
			};
		}
	}
	yfgl.getTableCheck(objTable);
};

/**
 * 功能：点击点选按钮后，遍历表单添加样式
 */
yfgl.clickTableTdClass = function(objTable){
	var t2 = document.getElementById(objTable).getElementsByTagName("tr");
	for (var i = 0; i < t2.length; i++) {
		t2[i].id = i;
		if (t2[i].sectionRowIndex == 0) {
			t2[i].className = yfgl.tableTitle;
		} else {
			var cols = document.getElementById(i).childNodes;
			if(cols[0].childNodes[0].checked == false){
				t2[i].className = (t2[i].sectionRowIndex % 2 == 1) ? yfgl.SingleTr : yfgl.doubleTr;
			}else if(cols[0].childNodes[0].checked == true){
				t2[i].className = yfgl.clickTr;
			}
		}
	}
}
/**
 * 功能：初始化列表checkbox是否选中，如果选中赋予样式
 */
yfgl.getTableCheck = function(objTable){
	//取指定table中所有行
	var t = document.getElementById(objTable).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		if (t[i].sectionRowIndex != 0) {
			var cols = t[i].getElementsByTagName("td");
			if (cols[0].childNodes[0].type == "checkbox") {
				if(cols[0].childNodes[0].disabled != true){
					if (cols[0].childNodes[0].checked == false) {
						t[i].className = (t[i].sectionRowIndex % 2 == 1) ? yfgl.SingleTr : yfgl.doubleTr;
					} else {
						t[i].className = yfgl.clickTr;
					}
				}
			}
		}
	}
}

/**
 * 功能：获取checkbox选中一行某一列的值 
 */
yfgl.getColsValue = function(objTable,num){
	//取指定table中所有行
	var t = document.getElementById(objTable).getElementsByTagName("tr");
	var colsValue = "";
	for (var i = 0; i < t.length; i++) {
		if (t[i].rowIndex > 0 || t[i].sectionRowIndex > 0) {
			var cols = t[i].getElementsByTagName("td");
			for (var j = 0; j < cols.length; j++) {
				if (cols[j].childNodes[0].checked == true) {
					colsValue += cols[num-1].childNodes[0].innerText+",";
				}
			}
		}
	}
	colsValue = colsValue.substring(0, colsValue.length - 1);
	return colsValue;
}

//复选后单元格变色
yfgl.chkRow = function(obj){
	var r = obj.parentElement.parentElement;
	if (r.rowIndex == 0) {
		r.className = yfgl.tableTitle;
	} else {
		if (obj.checked) {
			r.className = yfgl.clickTr;
		} else {
			if (r.rowIndex % 2 == 1) {
				r.className = yfgl.SingleTr;
			} else {
				r.className = yfgl.doubleTr;//.style.backgroundColor="#F5F5F5";
			}
		}
	}
};

//全选及全不选
yfgl.chooseAll = function(sel, check){
	var objtb = document.getElementById(sel);
	var num = objtb.getElementsByTagName("input");
	var check = document.getElementById(check);
	for (i = 0; i < num.length; i++) {
		if (num[i].tagName == "INPUT") {
			if (check.checked == true) {
				if (num[i].disabled != true) {
					num[i].checked = true;
					//yfgl.chkRow(num[i]);
				}
			} else {
				num[i].checked = false;
				//yfgl.chkRow(num[i]);
			}
		}
	}
}

//Button全选及反选
yfgl.BtnchooseAll = function(sel, check){
	var objtb = document.getElementById(sel);
	var num = objtb.getElementsByTagName("input");
	for (i = 0; i < num.length; i++) {
		if (num[i].tagName == "INPUT") {
			if (check == true) {
				num[i].checked = true;
			} else {
				num[i].checked = false;
			}
		}
	}
}

/**
 * 功能：遍历页面checkbox控件，全选checkbox
 */
yfgl.checkBoxAllChecked = function(){
	var inputs = document.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++){
		var ck = inputs[i];
		if(ck.type == "checkbox"){
			ck.checked = true;
		}
	}
}

/**
 * 功能：遍历页面checkbox控件，反选checkbox
 */
yfgl.checkBoxAllUnChecked = function(){
	var inputs = document.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++){
		var ck = inputs[i];
		if(ck.type == "checkbox"){
			ck.checked = false;
		}
	}
}

/**
 * 功能：清空指定域中控件的值
 * 参数：域ID
 */
yfgl.resetForm = function(queryDiv){
	jQuery(':input','#'+queryDiv).not(':button, :submit, :reset') .val('').removeAttr('checked').removeAttr('selected');
}

/**
 * 功能：获取select Id&Text
 * 参数1：selectId ：selectId
 * 参数2：inputId  ：将选定的selectText值赋予隐藏域Input中      
 */
yfgl.selectValText = function(selectId,inputId){
	var objId = jQuery("#"+selectId+" option:selected").val();
	var objText = jQuery("#"+selectId+" option:selected").text();
	if(objId != null && objId != ""){
		jQuery("#"+inputId).val(objText);
	}else{
		jQuery("#"+inputId).val("");
	}
}

/**
 * 功能：页面客户端验证
 * 参数：被验证的表单Id
 */
yfgl.formValidate = function(formId){
	var myForm = Validator.setup({
		form : formId,
		configs : 'attribute,tag',
		warns : 'tips,color,class',//summary,follow, color,class, alert,tips
		color : {warn :'#ff0000', pass:'#000000'}
	});
	return myForm;
}

/**
 * 功能：select下拉框机构树
 */
yfgl.selectDeptTree = function(parameDeptId,parameDeptName){
	jQuery("#"+parameDeptName+"").orgnization({
		keyObj : '#'+parameDeptId,
		title : '选择机构'
	});
}

/**
 * 功能：	上传图片前预览图片
 * 参数：	inputFileId 上传文件控件名称
 *       	showInputId 预览图片显示位置
 */
var checkResult = false;
yfgl.showImagesView = function(inputFileId,showInputId,fjId,preview_fake){
	var inputObj = document.getElementById(inputFileId);
	var imageObj = document.getElementById(showInputId);
	var objPreviewFake = document.getElementById(preview_fake);
	if(inputObj.value == "" || inputObj.value == null){
		if(fjId == "" || fjId == null){
			objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = "/yfgl/images/showview.jpg";
			autoSizePreview(objPreviewFake);
			imageObj.style.display ='none';
		}else{
			var imgSrc = "/yfgl/accessories/accessoriesAction_queryById.action?fjid="+fjId;
			objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
			autoSizePreview(objPreviewFake);
			imageObj.style.display ='none';
		}
	}else{
		var exp = /.\.jpg|.\.jpeg|.\.gif|.\.png|.\.bmp|.\.JPG|.\.JPEG|.\.GIF|.\.PNG|.\.BMP/i;
		if (exp.test(inputObj.value)){//验证格式 
			if(inputObj.files && inputObj.files[0]){
				imageObj.style.display ='block';
				imageObj.src = inputObj.files[0].getAsDataURL();
				yfglCheckForm.checkFileSize(inputFileId);
			}else if(objPreviewFake.filters){
				inputObj.select();
				var imgSrc = document.selection.createRange().text;
				objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
				autoSizePreview(objPreviewFake);
				imageObj.style.display ='none';
				checkResult = yfglCheckForm.checkFileSize(inputFileId);
				if(!checkResult){
					if(fjId == "" || fjId == null){
						objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = "/yfgl/images/showview.jpg";
						autoSizePreview(objPreviewFake);
						imageObj.style.display ='none';
					}else{
						var imgSrc = "/yfgl/accessories/accessoriesAction_queryById.action?fjid="+fjId;
						objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
						autoSizePreview(objPreviewFake);
						imageObj.style.display ='none';
					}
				}
			}
		}
	}
}

/**
 * 功能：	清空上传图片前预览图片
 * 参数： 	inputFileId 上传文件控件名称
 *       	showInputId 预览图片显示位置
 */
yfgl.clearImagesView = function(inputFileId,showInputId,fjId,preview_fake){
	var inputObj = document.getElementById(inputFileId);
	var imageObj = document.getElementById(showInputId);
	var objPreviewFake = document.getElementById(preview_fake);
	if(inputObj.value == "" || inputObj.value == null){
		if(fjId == "" || fjId == null){
			objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = "/yfgl/images/showview.jpg";
			autoSizePreview(objPreviewFake);
			imageObj.style.display ='none';
		}
	}
}

function autoSizePreview(objPre, originalWidth, originalHeight){
	var zoomParam = clacImgZoomParam(230,230);
	objPre.style.width = zoomParam.width +'px';
	objPre.style.height = zoomParam.height +'px';
	objPre.style.marginTop = zoomParam.top +'px';
	objPre.style.marginLeft = zoomParam.left + 'px';
}

function clacImgZoomParam( maxWidth, maxHeight){
	var param = { width:maxWidth, height:maxHeight, top:0, left:0 };
	param.width =maxWidth;
	param.height =maxHeight;
	param.left = (maxWidth - param.width) / 2;
	param.top = (maxHeight - param.height) /2;
	return param;
}

/**
 * 功能：清空file控件内容
 * @param file
 * @return
 */
yfgl.clearFileInput = function(file){
    var form=document.createElement('form');
    document.body.appendChild(form);
    //记住file在旧表单中的的位置
    var pos=file.nextSibling;
    form.appendChild(file);
    form.reset();
    pos.parentNode.insertBefore(file,pos);
    document.body.removeChild(form);
}

/**
 * 功能：获取file控件真实地址
 * 参数：file控件ID
 */
yfgl.getClientFilePath = function(inputFileId){
	var inputObj = document.getElementById(inputFileId);
	var path = "";
	if(inputObj != null){
		if(inputObj.files && inputObj.files[0]){
			path = inputObj.files[0].getAsDataURL();
		}else{
			inputObj.select();
			path = document.selection.createRange().text;
		}
	}
	return path
}

/**
 * 功能：增加附件上传控件
 */
var num = 1;
yfgl.addFileInput = function(obj){
	num++;
	if(num <= 5){
		var t = document.getElementById(obj);
		var tr=t.insertRow();
		var td=tr.insertCell();
		td.align = "right";
		td.innerHTML="<input type='file' name='file"+num+"' id='file"+num+"' style='width: 90%' align='center'/>";
	}else{
		messages.tips("最多只能上传5个大附件！", 1.5);
		return false;
	}
}

/**
 * 功能：大文件上传、下载
 * 参数1：(1)download是下载操作(2)downopen是下载完后直接打开(3)delete是删除附件 (4)deletefolder是删除文件夹
 * 参数2：(1)下载操作时是附件在FTP服务器上的保存路径，此路径保存在数据库中 (3)删除附件时传附件id (4)业务id
 * 参数3：(1)下载后直接打开传文件类型 (4)传模块代码，如工程项目中的123项目传gcxm
 * 参数4：(4)传业务名称,，如工程项目中的123项目传123
 * 参数5：
 * 参数6：系统逻辑部署id
 */
yfgl.BigFileUpload = function(obj,obj1,obj2,obj3,obj4,obj5){
	var url = "";
	if(obj5 == "undefined" || obj5 == undefined){
		obj5 = "";
	}
	jQuery.ajax({
		type: "POST",
		url: "/yfgl/accessories/getFtpServiceInfo.action?ftpljbsid="+obj5+"&updownType="+obj,
		// 接受数据格式
		dataType : "json",
		data: "",
		success:function(result){
			//获取服务端返回FTP服务器信息
			var json = eval("("+result+")");
			var W = screen.width;//取得屏幕分辨率宽度
			var H = screen.height;//取得屏幕分辨率高度
			if(json){
//				if(obj == "upload"){//上传
//					url = "/yfgl/jsp/common/upload/upload.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&remotepath="+obj3+"&ywid="+obj1+"&maxSize=50"+"&method="+obj2+"&ywmc="+obj4;
//					window.open(url,'newwindow','height='+(H*0.35)+',width='+(W*0.65)+',top='+(H*0.35)+',left='+(W*0.2)+',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,directories=no,status=no');
//					//window.showModalDialog(url,new Object(),"resizeable: no,scrollbar=no,directories: no;location: no;status: no;dialogWidth="+(W*0.65)+"px;dialogHeight="+(H*0.35)+"px");
//				}else 
					
				if(obj == "download"){//下载
					url = "/yfgl/jsp/common/upload/download.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&remotepath="+obj1+"&maxSize=1000";
					window.showModalDialog(url,new Object(),"resizeable: no,scrollbar=no,directories: no;location: no;status: no;dialogWidth="+(W*0.65)+"px;dialogHeight="+(H*0.35)+"px");
				}else if(obj == "delete"){//删除附件
					url = "/yfgl/jsp/common/upload/deletefile.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&fjid="+obj1+"&filepath="+obj2;
					//document.getElementById("loading").style.display = "block";
					//jQuery("#loading").html("<img src='/yfgl/images/tag/loading.gif'></img>正在删除，请稍候...");
					window.loadingFrame.location.href = url;
					//window.open(url,'newwindow','height='+(H*0.2)+',width='+(W*0.3)+',top='+(H*0.5)+',left='+(W*0.45)+',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,directories=no,status=no');
					//window.showModalDialog(url,new Object(),"resizeable: no,scrollbars=no,directories: no;location: no;status: no;dialogWidth="+w+"px;dialogHeight="+h+"px");
				}else if(obj == "deletefolder"){//删除文件夹
					url = "/yfgl/jsp/common/upload/deletefolder.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&ywid="+obj1+"&mkdm="+obj2+"&ywmc="+obj3;
					//window.open(url,'newwindow','height='+(H*0.4)+',width='+(W*0.5)+',top='+(H*0.3)+',left='+(W*0.25)+',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,directories=no,status=no');
					var returnValue = window.showModalDialog(url,new Object(),"resizeable: no,scrollbars=no,directories: no;location: no;status: no;dialogWidth="+(W*0.5)+"px;dialogHeight="+(H*0.4)+"px");
					if(returnValue != "" || returnValue != null){
						if(jQuery(returnValue).filter('div#refurbish').html()==null) {
							document.getElementById('refurbish').innerHTML =jQuery(returnValue).find('div#refurbish').html();
						}else{
							document.getElementById('refurbish').innerHTML =jQuery(returnValue).filter('div#refurbish').html();
						}
						//加载表格样式
						yfgl.getTableStyle();
					}
				}else if(obj == "downopen"){//下载后直接打开
					if(obj2=="8"||obj2=="9"){
						var w=W*0.8;
						var h=H*0.8;
						url = "/yfgl/jsp/common/upload/downopen.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&remotepath="+obj1+"&fjlx="+obj2+"&width="+w+"&height="+h;
						//window.open(url,'newwindow','height='+h+',width='+w+',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,directories=no,status=no');
						window.showModalDialog(url,new Object(),"resizeable: no,scrollbars=no,directories: no;location: no;status: no;dialogWidth="+w+"px;dialogHeight="+h+"px");
						return;
					}else{
						var w=W*0.4;
						var h=H*0.2;
						url = "/yfgl/jsp/common/upload/downopenword.jsp?ip="+json.ip+"&userName="+json.user+"&password="+json.pwd+"&port="+json.port+"&remotepath="+obj1+"&fjlx="+obj2+"&width="+w+"&height="+h;
						//window.open(url,'newwindow','height='+h+',width='+w+',top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,directories=no,status=no');
						window.showModalDialog(url,new Object(),"resizeable: no,scrollbars=no,directories: no;location: no;status: no;dialogWidth="+w+"px;dialogHeight="+h+"px");
						return;
					}
				}
			}
		}
	});
}

/**
 * 功能：删除大附件刷新附件列表
 */
yfgl.BigFileDivRefurbish = function(strUrl){
	jQuery.ajax({
	   type: "POST",
	   url: strUrl,
	   data:"",
	   success: function(msg){
			if(jQuery(msg).filter('div#fj').html()==null) {
				document.getElementById('fj').innerHTML =jQuery(msg).find('div#fj').html();
			}else{
				document.getElementById('fj').innerHTML =jQuery(msg).filter('div#fj').html();
			}
			ymPrompt.close();
		}
	});
}

/**
 * 功能：帮助方法
 * @param num	参考HelpInfo类中设置的value
 * @return
 */
yfgl.helpInfo = function(num){
	var iWidth = screen.width-300;//取得屏幕分辨率宽度
	var iHeight = screen.height-300;//取得屏幕分辨率高度
	var iTop = (screen.availHeight-30-iHeight)/2;
	var iLeft = (screen.availWidth-10-iWidth)/2;
	var url = "";
	if(num>0){
		url = yfgl.getRootPath()+"/help/yfgl/index.html?num="+num;
	}else{
		url = yfgl.getRootPath()+"/help/yfgl/index.html";
	}
	//alert(${pageContext.request.contextPath}+":::::"+url);
	messages.openWin('help','帮助手册',url,'70%','70%');
	//window.open(url,"","toolbar=no,location=no,directories=no,top="+iTop+",left="+iLeft+",height="+iHeight+",width="+iWidth+",menubar=no,resizable=no");
}

/**
 * 功能：培训视频播放
 * @param strType
 * @return
 */
yfgl.videoHelp = function(strType){
	var Url = "/yfgl/jsp/common/video/videoInfo_content.jsp?strType="+strType;
	//getCustomWinSize(Url,"培训视频","430","380");
	
	var iWidth = screen.width-520;//取得屏幕分辨率宽度
	var iHeight = screen.height-355;//取得屏幕分辨率高度
	var iTop = (screen.availHeight-30-iHeight)/2;
	var iLeft = (screen.availWidth-10-iWidth)/2;
	window.open(Url,"","toolbar=no,location=no,directories=no,top="+iTop+",left="+iLeft+",height="+iHeight+",width="+iWidth+",menubar=no,resizable=no");
}

//js获取项目根路径，如： http://localhost:8083/uimcardprj
yfgl.getRootPath = function(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}






//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//遍历表单控件拼接参数 字符串
yfgl.FormWidgetValues = function(){
	var params = "";
	var checkboxName = "";
	var checkboxValues = "";
	var checkBoxParams = "";
	var returnValue = "";
	jQuery("input[type=text]").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + encodeURI(yfgl.stringUtil(jQuery(this).val(),1)).replace(/\&/g, "%26");
	});
	jQuery("input[type=hidden]").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + encodeURI(yfgl.stringUtil(jQuery(this).val(),1)).replace(/\&/g, "%26");
	});
	jQuery("input:checked[type=radio]").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + jQuery(this).val();
	});
	jQuery("input:checked[type=checkbox]").each(function(){
		checkboxName = "&" + jQuery(this).attr("name") + "=";
		checkboxValues += jQuery(this).val() + ",";
	});
		checkboxValues = checkboxValues.substring(0,checkboxValues.length - 1);
		checkBoxParams = checkboxName + checkboxValues
	jQuery("select").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + encodeURI(jQuery(this).val()).replace(/\&/g, "%26");
	});
	jQuery("textarea").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + encodeURI(yfgl.stringUtil(jQuery(this).val(),1)).replace(/\&/g, "%26");
	});
	jQuery("input[type=file]").each(function(){
		params += "&" + jQuery(this).attr("name")+ "=" + jQuery(this).val();
	});
	returnValue = yfgl.stringUtil(params + checkBoxParams,1);
	return returnValue;
}

/**
 * 功能：页签
 * tabDiv :	显示容器DIV ID
 * active : 默认加载第几个也签，取页签下标
 * items  : 加载页签是个数组 格式：[{id:'页签ID必须唯一',title:'页签名称',html:'页签加载页面'},{id:'',title:'',html:''}] 
 */
var tabpanel;
yfgl.tabsPanel = function(tabDiv,active,items,width,height){
	tabpanel = new TabPanel({
		renderTo:tabDiv,
		active : active,
		width:width,
		height:height,
		autoResizable:true,
		items : items
	});
	
	return tabpanel;
}

//弹出showModalDialog窗口
function popNew(url, arguments, width, height) {
	var obj = showModalDialog(url, arguments, "dialogWidth:" + width + "px; dialogHeight:" + height + "px;help:0;status:0;resizeable:1");
	if (obj == "OK") {
		document.getElementById(arguments).click();
	}
}

/**
 * 功能：加载表格样式
 */
yfgl.getTableStyle = function(){
	jQuery('.mainTable2 thead tr th:first').css({ "border-left": "none" });
	jQuery('.mainTable2 tbody tr:even').css({ "background": "#FFF","text-align": "center" });	
	jQuery('.mainTable2 tbody tr:odd').css({ "background": "#F6F6FB","text-align": "center" });
	//鼠标移动样式
	jQuery('.scrollDiv table tr:odd,.mainTable2 tbody tr:odd').hover(function(){
		jQuery(this).css({ "background": "#C7D7EB" });
	},function(){
		jQuery(this).css({ "background": "#F6F6FB" });
	});
	jQuery('.scrollDiv table tr:even,.mainTable2 tbody tr:even').hover(function(){
		jQuery(this).css({ "background": "#C7D7EB" });
	},function(){
		jQuery(this).css({ "background": "#FFF" });
	});
}

/**
 * 功能：加载表格样式
 */
yfgl.getMainTable3TableStyle = function(){
	jQuery('.mainTable3 thead tr th:first').css({ "border-left": "none" });
	jQuery('.mainTable3 tbody tr:even').css({ "background": "#FFF","text-align": "center" });	
	jQuery('.mainTable3 tbody tr:odd').css({ "background": "#F6F6FB","text-align": "center" });
	//鼠标移动样式
	jQuery('.scrollDiv table tr:odd,.mainTable3 tbody tr:odd').hover(function(){
		jQuery(this).css({ "background": "#C7D7EB" });
	},function(){
		jQuery(this).css({ "background": "#F6F6FB" });
	});
	jQuery('.scrollDiv table tr:even,.mainTable3 tbody tr:even').hover(function(){
		jQuery(this).css({ "background": "#C7D7EB" });
	},function(){
		jQuery(this).css({ "background": "#FFF" });
	});
}

/**
 * 功能：机构树(一)
 */
yfgl.showDeptTree = function(parameDeptId,parameDeptName,parame2,parame3){
	var obj =new Object();
	if( parame2 == "strengthMange"){
		var url = "/yfgl/jsp/common/tree/testTreeTJ.jsp?baobiaoType="+parame3;
	}else if((parame2 == "" || parame2 == null || parame2 == "undefined" || parame2 == undefined) || parame2 == "strengthMange"  || parame2 == "analyse" ){
		var url = "/yfgl/jsp/common/tree/testTree.html";
	}else{
		var url = "/yfgl/jsp/common/tree/tree.jsp";
	}
	str = window.showModalDialog(url,obj,"status: no;dialogWidth=400px;dialogHeight=500px");
//	if((parame2 == "" || parame2 == null || parame2 == "undefined" || parame2 == undefined) || parame2 == "strengthMange"  || parame2 == "analyse" ){
//		str = window.showModalDialog("/yfgl/jsp/common/tree/testTree.html",obj,"status: no;dialogWidth=400px;dialogHeight=500px");
//	}else{
//		str = window.showModalDialog("/yfgl/jsp/common/tree/tree.jsp",obj,"status: no;dialogWidth=400px;dialogHeight=500px");
//	}
	if(str != "undefined" && str != undefined){
		var returnvalue = str.split(",");
		if(parameDeptId != "" && parameDeptId != null){
			document.getElementById(parameDeptId).value = returnvalue[0];
			if(parame2 != null && parame2 != "" ){
				if(returnvalue[0] != null && returnvalue[0] != "" &&  parame2=="analyse"){
					//清空所有option项
					//jQuery("#jgjbDiv").empty();
					var params = "jgid="+returnvalue[0];
					jQuery.ajax({
						url: "/yfgl/strengthMange/Analyse_doQueryJgjb.action",
						type: "POST",
						data: params,
						success: function(msg){
							document.getElementById('jgjbDiv').innerHTML =jQuery(msg).find('div#jgjbDiv').html();
							document.getElementById('jgxzDiv').innerHTML =jQuery(msg).find('div#jgxzDiv').html();
						},
						error:function(msg){
							massagerErrorCallBack('获取坐落编号失败，请联系管理员！',function(){
								return false;
							});
						}
					});
				}else if(returnvalue[0] != null && returnvalue[0] != "" && parame2=="strengthMange"){
					if(document.getElementById('cdChangeLyDiv')!=null){
						//清空所有option项
						//jQuery("#jgjbDiv").empty();
						var params = "jgid="+returnvalue[0];
						jQuery.ajax({
							url: "/yfgl/strengthMange/StrengthmangeAction_doQueryJgxz.action",
							type: "POST",
							data: params,
							success: function(msg){
								document.getElementById('cdChangeLyDiv').innerHTML =jQuery(msg).find('div#cdChangeLyDiv').html();
							},
							error:function(msg){
								massagerErrorCallBack('获取坐落编号失败，请联系管理员！',function(){
									return false;
								});
							}
						});
					}
				}
			}else if(parame3 == "" || parame3 == null || parame3 == "undefined" || parame3 == undefined){
				if(returnvalue[0] != null && returnvalue[0] != ""){
					//清空所有option项
					jQuery("#zlbh").empty();
					var params = "jgid="+returnvalue[0];
					jQuery.ajax({
						url: "/yfgl/strengthMange/strength_ConditionStatistic.action?method='mapService'" ,
						type: "POST",
						data: params,
						success: function(result){
							window.document.getElementById('zlbhDiv1').innerHTML = jQuery(result)
							.find('div#zlbhDiv1').html();
						},
						error:function(msg){
							massagerErrorCallBack('获取坐落编号失败，请联系管理员！',function(){
								return false;
							});
						}
					});
				}else{
					jQuery("#zlbh").empty();
					jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
				}
			}else if(parame3 != null && parame3 != "" && parame3 == "2"){
				if(returnvalue[0] != null && returnvalue[0] != ""){
					//清空所有option项
					jQuery("#zlbh").empty();
					var params = "jgid="+returnvalue[0];
					jQuery.ajax({
						url: "/yfgl/barrackSit/barrackSitAction_QueryZlbhByjg.action",
						type: "POST",
						// 接受数据格式
						dataType : "json",
						data: params,
						success: function(result){
							if(result){
								jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
								jQuery(result).appendTo("#zlbh");
							}else{
								jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
							}
						},
						error:function(msg){
							massagerErrorCallBack('获取坐落编号失败，请联系管理员！',function(){
								return false;
							});
						}
					});
				}else{
					jQuery("#zlbh").empty();
					jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
				}
			}else{
				if(returnvalue[0] != null && returnvalue[0] != ""){
					//清空所有option项
					jQuery("#zlbh").empty();
					var params = "jgid="+returnvalue[0];
					jQuery.ajax({
						url: "/yfgl/barrackSit/barrackSitAction_QueryZlbh.action",
						type: "POST",
						// 接受数据格式
						dataType : "json",
						data: params,
						success: function(result){
							if(result){
								jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
								jQuery(result).appendTo("#zlbh");
							}else{
								jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
							}
						},
						error:function(msg){
							massagerErrorCallBack('获取坐落编号失败，请联系管理员！',function(){
								return false;
							});
						}
					});
				}else{
					jQuery("#zlbh").empty();
					jQuery("<option value=\"\">请选择坐落编号</option>").appendTo("#zlbh");
				}
			}
		}
		if(parameDeptName != "" && parameDeptName != null){
			document.getElementById(parameDeptName).value = returnvalue[1];
		}
	}
}

/**
 * 功能：审核下级上报数据页面
 * 参数一：业务ID
 * 参数二：根据业务ID查询业务信息地址
 * 参数三：业务标题
 * 参数四：业务类型
 * 参数五：执行完退回操作，进行查询
 * 参数六：刷新数据
 * 参数七：提交审核或接收审核
 */
yfgl.openApprovePage = function(ywid,ywdz,cxdz,bt,lm,refurbishDiv,approveType,xjjgjlid){
	var obj =new Object();
	var W = screen.width-200;	//取得屏幕分辨率宽度
	var H = screen.height-200;	//取得屏幕分辨率高度
	var paream = "";
	var strType = "";
	if(lm == "SitmarkTempVo"){
		strType = "true";
	}
	if(approveType == undefined || approveType == "undefined"){
		approveType = "";
	}
	if(ywid != "" && ywdz != "" && bt != ""){
		paream = "?approve.phdxid="+ywid+"&approve.ymdz="+ywdz+"&approve.lm="+lm+"&approve.bt="+encodeURI(bt)+"&approve.strType="+strType+"&approve.approveType="+approveType+"&approve.xjjgjlid="+xjjgjlid;
	}
	var URL = "/yfgl/approve/getApproveUntreadPage.action"+paream;
	str = window.showModalDialog(URL,obj,"status: no;dialogWidth="+W+"px;dialogHeight="+H+"px");
	//alert(str);
	if(str != "undefined" && str != undefined){
		if(str == "true"){
			if(cxdz != ""){
				//alert(cxdz);
				if(approveType == "" || approveType == null || approveType == undefined){
//					//alert(approveType);
//					jQuery.ajax({
//						url: cxdz,
//						type: "POST",
//						data: null,
//						success: function(msg){
//							//alert(refurbishDiv);
//							//alert("aa:"+jQuery(msg).filter('div#'+refurbishDiv).html());
//							//alert("bb:"+jQuery(msg).find('div#'+refurbishDiv).html());
//							if(jQuery(msg).filter('div#'+refurbishDiv).html() == null || jQuery(msg).filter('div#'+refurbishDiv).html() == "") {
//								//alert("aa");
//								jQuery('div#'+refurbishDiv).html(jQuery(msg).find('div#'+refurbishDiv).html());
//								document.getElementById(refurbishDiv).innerHTML = jQuery(msg).find('div#'+refurbishDiv).html();
//							}else{
//								document.getElementById(refurbishDiv).innerHTML = jQuery(msg).filter('div#'+refurbishDiv).html();
//							}
//							//加载表格样式
//							yfgl.getTableStyle();
//						}
//					});
					window.parent.mainFrameZHYW.location.href = cxdz;
				}else{
					window.parent.mainFrameZHYW.location.href = cxdz;
				}
			}
		}
	}
}

/**
 * 功能：查看审批信息
 * 参数：业务ID
 */
yfgl.queryApproveInfo = function(obj){
	var objWindow =new Object();
	var W = screen.width-200;//取得屏幕分辨率宽度
	var H = screen.height-200;//取得屏幕分辨率高度
	
	var url = "/yfgl/approve/selecttauditing.action?approve.phdxid="+obj;
	window.showModalDialog(url,objWindow,"status: no;dialogWidth="+W+"px;dialogHeight="+H+"px");
}