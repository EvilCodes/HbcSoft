/**
 * 作者：zhaon
 */
var formParam = new Object();

/**
 * 功能：获取指定容器内所有控件的值，自动拼接参数
 */
formParam.getFormParam = function(containerId){
		var data="";
		if(document.getElementById(containerId)!=null){
			var d=document.getElementById(containerId).getElementsByTagName("input");
			if(d.length!=0){
				for(var i=0;i<d.length;i++){
					if(d[i].type=="text"){
						if(!d[i].disabled){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							data+="&"+d[i].name+"=";
							data+=''+ yfgl.stringUtil(d[i].value,2) +'';
						}
						}
					}else if(d[i].type=="file"){
						if(!d[i].disabled){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							data+="&"+d[i].name+"=";
							data+=''+d[i].value+'';
						}
						}
					}else if(d[i].type=="hidden"){
						if(!d[i].disabled){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							data+="&"+d[i].name+"=";
							data+=''+ yfgl.stringUtil(d[i].value,2) +'';
						}
						}
					}else if(d[i].type=="password"){
						if(!d[i].disabled){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							data+="&"+d[i].name+"=";
							data+=''+d[i].value+'';
						}
						}
					}else if(d[i].type=="radio"){
						if(!d[i].disabled){
						if(d[i].checked){
							if(data==""){
								data+=d[i].name+"=";
								data+=''+d[i].value+'';
							}else{
								data+="&"+d[i].name+"=";
								data+=''+d[i].value+'';
							}
						}
						}
					}else if(d[i].type=="checkbox"){
						if(!d[i].disabled){
						if(d[i].checked){
							if(data==""){
								data+=d[i].name+"=";
								data+=''+d[i].value+'';
							}else{
								if(data.indexOf(d[i].name)==-1){
									data+="&"+d[i].name+"=";
									data+=''+d[i].value+'';
								}else{
									var sz=data.split(d[i].name);
									var ssz=sz[sz.length-1];
									var sssz=ssz.split("&");
									var sszz=sssz[0];
									var ds=sszz.split('=');
									var dss=ds[1];
									var v=d[i].name+'='+dss+','+d[i].value+'';
									var vs=d[i].name+'='+dss+'';
									data=data.replace(vs,v);
								}
							}
						}
					}
					}
				}
			}
			var f=document.getElementById(containerId).getElementsByTagName("textarea");
			if(f.length!=0){
				for(var i=0;i<f.length;i++){
					if(!f[i].disabled){
					if(data==""){
						data+=f[i].name+"=";
						data+=''+encodeURI(f[i].value).replace(/\&/g, "%26")+'';
					}else{
						data+="&"+f[i].name+"=";
						data+=''+encodeURI(f[i].value).replace(/\&/g, "%26")+'';
					}
				}
				}
			}
			var sele=document.getElementById(containerId).getElementsByTagName("select");
			if(sele.length!=0){
				for(var i=0;i<sele.length;i++){
					if(!sele[i].disabled){
					if(data==""){
						data+=sele[i].name+"=";
						for(var s=0;s<sele[i].options.length;s++){
							if(sele[i].options[s].selected){
								data+=''+sele[i].options[s].value+'';
								break;
							}
						}	
					}else{
						data+="&"+sele[i].name+"=";
						for(var s=0;s<sele[i].options.length;s++){
							if(sele[i].options[s].selected){
								data+=''+sele[i].options[s].value+'';
								break;
							}
						}
					}
				}
				}
			}
		}
	return encodeURI(data);
}

function qxtj(containerId){	
	var data="";
	if(document.getElementById(containerId)!=null){
		var d=document.getElementById(containerId).getElementsByTagName("input");
		if(d.length!=0){
			for(var i=0;i<d.length;i++){
				if(d[i].type=="text"){
					if(!d[i].disabled){
					if(data==""){
						data+=d[i].name+"=";
						data+=''+d[i].value+'';
					}else{
						data+="&"+d[i].name+"=";
						data+=''+d[i].value+'';
					}
					}
				}else if(d[i].type=="file"){
					if(!d[i].disabled){
					if(data==""){
						data+=d[i].name+"=";
						data+=''+d[i].value+'';
					}else{
						data+="&"+d[i].name+"=";
						data+=''+d[i].value+'';
					}
					}
				}else if(d[i].type=="hidden"){
					if(!d[i].disabled){
					if(data==""){
						data+=d[i].name+"=";
						data+=''+d[i].value+'';
					}else{
						data+="&"+d[i].name+"=";
						data+=''+d[i].value+'';
					}
					}
				}else if(d[i].type=="password"){
					if(!d[i].disabled){
					if(data==""){
						data+=d[i].name+"=";
						data+=''+d[i].value+'';
					}else{
						data+="&"+d[i].name+"=";
						data+=''+d[i].value+'';
					}
					}
				}else if(d[i].type=="radio"){
					if(!d[i].disabled){
					if(d[i].checked){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							data+="&"+d[i].name+"=";
							data+=''+d[i].value+'';
						}
					}
					}
				}else if(d[i].type=="checkbox"){
					if(!d[i].disabled){
					if(d[i].checked){
						if(data==""){
							data+=d[i].name+"=";
							data+=''+d[i].value+'';
						}else{
							if(data.indexOf(d[i].name)==-1){
								data+="&"+d[i].name+"=";
								data+=''+d[i].value+'';
							}else{
								var sz=data.split(d[i].name);
								var ssz=sz[sz.length-1];
								var sssz=ssz.split("&");
								var sszz=sssz[0];
								var ds=sszz.split('=');
								var dss=ds[1];
								var v=d[i].name+'='+dss+','+d[i].value+'';
								var vs=d[i].name+'='+dss+'';
								data=data.replace(vs,v);
							}
						}
					}
				}
				}
			}
		}
		var f=document.getElementById(containerId).getElementsByTagName("textarea");
		if(f.length!=0){
			for(var i=0;i<f.length;i++){
				if(!f[i].disabled){
				if(data==""){
					data+=f[i].name+"=";
					data+=''+f[i].value+'';
				}else{
					data+="&"+f[i].name+"=";
					data+=''+f[i].value+'';
				}
			}
			}
		}
		var sele=document.getElementById(containerId).getElementsByTagName("select");
		if(sele.length!=0){
			for(var i=0;i<sele.length;i++){
				if(!sele[i].disabled){
				if(data==""){
					data+=sele[i].name+"=";
					for(var s=0;s<sele[i].options.length;s++){
						if(sele[i].options[s].selected){
							data+=''+sele[i].options[s].value+'';
							break;
						}
					}	
				}else{
					data+="&"+sele[i].name+"=";
					for(var s=0;s<sele[i].options.length;s++){
						if(sele[i].options[s].selected){
							data+=''+sele[i].options[s].value+'';
							break;
						}
					}
				}
			}
			}
		}
	}
	data = yfgl.stringUtil(data,0);
	return data;	
}