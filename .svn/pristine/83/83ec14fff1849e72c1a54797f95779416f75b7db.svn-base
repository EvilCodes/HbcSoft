<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>注册帐号</title>
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
</head>
<!-- BEGIN BODY -->
<body>
	<div class="container" >
		<div class="row">
			<form id="form" role="form" class="form-horizontal col-md-8 col-sm-12 col-md-offset-1" 
			 		action="${pageContext.request.contextPath}/userRegister/userSubmit.hbc"  autocomplete="off">
				<h3>用户注册&nbsp;<span><span class="must">*&nbsp;</span>为必填项。</span></h3>
				<hr>
				<div class="form-group">
					<label for="password" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>密码：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="password" class="form-control" maxlength="12"  onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value) name="password" id="password" placeholder="请输入密码" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p id="passwordtxt" class="txt"></p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-6 col-sm-5 col-md-offset-2 col-sm-offset-2">
						<p class="qiangdu">密码强度：</p>
						<ul>
							<li width="20%" id="strength_L" bgcolor="#eeeeee"></li>    
			                <li width="20%" id="strength_M" bgcolor="#eeeeee"></li>    
			                <li width="20%" id="strength_H" bgcolor="#eeeeee"></li> 
						</ul>
					</div>
				</div>
				<div class="form-group">
					<label for="repassword" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>确认密码：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="password" class="form-control" maxlength="12" name="repassword" id="repassword"  placeholder="请再次输入密码" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="repasswordtxt"></p>
					</div>
				</div>
				<hr>
				<div class="form-group">
					<label for="username" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>真实姓名：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="username" id="username"  placeholder="请输入真实姓名" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="usernametxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="ages" class="col-md-2 col-sm-2 control-label">性别：</label>
					<div class="col-md-6 col-sm-5 ">
						<label>
							<input type="radio" name="sex" value="0" checked="checked">男
						</label>
						<label>
							<input type="radio" name="sex" value="1">女 
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="mail" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>邮箱：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="mail" id="mail" placeholder="请输入邮箱" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="mailtxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-md-2 col-sm-2 control-label">手机：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" maxlength="11" id="phone" name="phone">
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="phonetxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="bell" class="col-md-2 col-sm-2 control-label">电话：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" id="bell" name="bell">
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="belltxt"></p>
					</div>
				</div>
				<hr>
				<div class="form-group">
					<label for="company" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>公司名称：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="company" id="company" placeholder="请输入公司名称" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="companytxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="csimple" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>中文简称：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="csimple" id="csimple" placeholder="请输入公司简称" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="csimpletxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="co" class="col-md-2 col-sm-2 control-label"><span class="must">*&nbsp;</span>英文简称：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" maxlength="5" name="co" id="co" placeholder="请输入公司英文名称" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="cotxt"></p>
					</div>
				</div>
				<hr>
				<button type="submit" class="btn btn-primary  col-md-6 col-sm-5  col-md-offset-2 col-sm-offset-2 col-xs-offset-3" id="register" style="margin-bottom: 20px; text-align: center;">提交注册</button>
			</form>
		</div>
	</div>

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script>
	$(document).ready(
			function() {
				var mess = '${message}';
				if (mess == "0") {
					alert("提交失败！");
				}
			});
		$(function(){
			var icons = '<span class="glyphicon glyphicon-minus-sign"></span>';
			var iconright = '<span class="glyphicon glyphicon-ok-sign" style="color: #7cde68"></span>';
			var iconwrong = '<span class="glyphicon glyphicon-remove-sign" style="color: #ff0000;">';
			var password=true;
			//密码验证
			$("input[name='password']").focus(function(){		
				document.getElementById("passwordtxt").innerHTML = icons + "&nbsp;" + "字母开头，含6-12位字母、数字和下划线"
				}).blur(function(){
					var reg1 = /^[a-zA-Z]\w{5,11}$/;
					var key1 = document.getElementById("password").value;
					if(reg1.test(key1) == true){
						document.getElementById("passwordtxt").innerHTML = iconright + "&nbsp;" ;
						password=true;
					}else{
						document.getElementById("passwordtxt").innerHTML = iconwrong + "&nbsp;"  + "密码格式错误";
						password=false;
					}
				})
			//确认密码
			var repassword=true;
			$("input[name='repassword']").focus(function(){		
				document.getElementById("repasswordtxt").innerHTML = icons + "&nbsp;" + "请重新输入密码"
				}).blur(function(){
					var reg1 = /^[a-zA-Z]\w{5,11}$/;
					var key1 = document.getElementById("password").value;
					var key2 = document.getElementById("repassword").value;
					if(reg1.test(key2) == true && key1 == key2){
						document.getElementById("repasswordtxt").innerHTML = iconright + "&nbsp;" ;
						repassword=true;
					}else{
						document.getElementById("repasswordtxt").innerHTML = iconwrong + "&nbsp;"  + "两次密码输入不一致，请重新输入";
						repassword=false;
					}
				})
			//真实姓名验证
			var username=true;
			$("input[name='username']").focus(function(){		
				document.getElementById("usernametxt").innerHTML = icons + "&nbsp;" + "请输入真实姓名"
				}).blur(function(){
					var reg3 = /^[\u4E00-\u9FFFa-zA_Z]+$/;
					var key3 = document.getElementById("username").value;
					if(reg3.test(key3) == true){
						document.getElementById("usernametxt").innerHTML = iconright + "&nbsp;" ;
						username=true;
					}else{
						document.getElementById("usernametxt").innerHTML = iconwrong + "&nbsp;"  + "请输入真实姓名";
						username=false;
					}
				})
			//邮箱验证
			var email=true;
			$("input[name='mail']").focus(function(){
				document.getElementById("mailtxt").innerHTML = icons + "&nbsp;" + "请输入邮箱地址"
				}).blur(function(){
					var reg4 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.)(com|COM|cn|CN|com.cn|COM.CN|org|ORG|net|NET)$/;
					var key4 = document.getElementById("mail").value;
					if(reg4.test(key4) == true){
						document.getElementById("mailtxt").innerHTML = iconright;
						email=true;
					}else{
						document.getElementById("mailtxt").innerHTML =  iconwrong + "&nbsp;"  + "邮箱格式错误";
						email=false;
					}
				})
			//手机号验证
			var phone=true;
			$("input[name='phone']").focus(function(){		
				document.getElementById("phonetxt").innerHTML = icons + "&nbsp;" + "请输入手机号码"
				}).blur(function(){
					var reg10 = /^[1][3578][0-9]{9}$/;
					var key10 = document.getElementById("phone").value;
					if(reg10.test(key10) == true&&key10.length==11){
						document.getElementById("phonetxt").innerHTML = iconright + "&nbsp;" ;
						phone=true;
					}else if(key10==""){
						document.getElementById("phonetxt").innerHTML ="";
						phone=true;
					}else{
						document.getElementById("phonetxt").innerHTML = iconwrong + "&nbsp;"  + "请输入正确的手机号格式";
						phone=false;
					}
				})
			//电话验证
			var bell=true;
			$("input[name='bell']").focus(function(){		
				document.getElementById("belltxt").innerHTML = icons + "&nbsp;" + "请输入电话号码"
				}).blur(function(){
					var reg11 = /^0\d{2,3}-\d{7,8}$/;
					var key11 = document.getElementById("bell").value;
					if(reg11.test(key11) == true&&key11.length<=13){
						document.getElementById("belltxt").innerHTML = iconright + "&nbsp;" ;
						bell=true;
					}else if(key11==""){
						document.getElementById("belltxt").innerHTML ="";
						bell=true;
					}else{
						document.getElementById("belltxt").innerHTML = iconwrong + "&nbsp;"  + "请输入正确的电话格式";
						bell=false;
					}
				})
			//公司名称
			var company=true;
			$("input[name='company']").focus(function(){
				document.getElementById("companytxt").innerHTML = icons + "&nbsp;" + "请输入公司名称"
				}).blur(function(){
					var reg5 = /^[\(\)\（\）\u4e00-\u9fa5_a-zA-Z0-9]{4,}$/;
					var key5 = document.getElementById("company").value;
					if(reg5.test(key5) == true){
						$.ajax({
							type: 'post',
							data: 'json',
							async:false,
							url: '${pageContext.request.contextPath}/userRegister/company.hbc?tableName='+encodeURI(encodeURI(key5)),
							success: function(msg){
								if(msg=="\"0\""){
									document.getElementById("companytxt").innerHTML = iconright;
									company=true;
								}else{
									document.getElementById("companytxt").innerHTML =  iconwrong + "&nbsp;"  + "该公司名称已存在,";
									company=false;
								}
							},
							error: function(xhr,err,data){
								alert("请求失败！");
							}
						});
					}else{
						document.getElementById("companytxt").innerHTML =  iconwrong + "&nbsp;"  + "请输入正确的公司名称";
						company=false;
					}
				})
			//公司中文简称
			var csimple=true;
			$("input[name='csimple']").focus(function(){
				document.getElementById("csimpletxt").innerHTML = icons + "&nbsp;" + "请输入公司中文简称"
				}).blur(function(){
					var reg6 = /^[\u4e00-\u9fa5_a-zA-Z0-9]{2,4}$/;
					var key6 = document.getElementById("csimple").value;
					if(reg6.test(key6) == true){
						$.ajax({
							type: 'post',
							data: 'json',
							async:false,
							url: '${pageContext.request.contextPath}/userRegister/companyCn.hbc?tableName='+encodeURI(encodeURI(key6)),
							success: function(msg){
								if(msg=="\"0\""){
									document.getElementById("csimpletxt").innerHTML = iconright;
									csimple=true;
								}else{
									document.getElementById("csimpletxt").innerHTML =  iconwrong + "&nbsp;"  + "该公司中文简称已存在,";
									csimple=false;
								}
							},
							error: function(xhr,err,data){
								alert("请求失败！");
							}
						});
					}else{
						document.getElementById("csimpletxt").innerHTML =  iconwrong + "&nbsp;"  + "请输入正确的公司简称";
						csimple=false;
					}
				})
			//公司英文简称
			var co=true;
			$("input[name='co']").focus(function(){
				document.getElementById("cotxt").innerHTML = icons + "&nbsp;" + "请输入公司英文简称"
				}).blur(function(){
					var reg7 = /^[A-Za-z0-9]+$/;
					var key7 = document.getElementById("co").value;
					if(reg7.test(key7) == true){
						$.ajax({
							type: 'post',
							data: 'json',
							async:false,
							url: '${pageContext.request.contextPath}/userRegister/companyHk.hbc?tableName='+encodeURI(encodeURI(key7)),
							success: function(msg){
								if(msg=="\"0\""){
									document.getElementById("cotxt").innerHTML = iconright;
									co=true;
								}else{
									document.getElementById("cotxt").innerHTML =  iconwrong + "&nbsp;"  + "该公司英文简称已存在,";
									co=false;
								}
							},
							error: function(xhr,err,data){
								alert("请求失败！");
							}
						});
					}else{
						document.getElementById("cotxt").innerHTML =  iconwrong + "&nbsp;"  + "请输入正确的公司英文简称";
						co=false;
					}
				})
			$("form").submit(function(e){
				if(!password){
					alert("密码格式错误!");
					return false;
				}else if(!repassword){
					alert("确认密码格式错误!");
					return false;
				}else if(!username){
					alert("真实姓名格式错误!");
					return false;
				}else if(!mail){
					alert("邮箱格式错误!");
					return false;
				}else if(!phone){
					alert("手机号格式错误!");
					return false;
				}else if(!bell){
					alert("电话号格式错误!");
					return false;
				}else if(!company){
					alert("公司名称格式错误!");
					return false;
				}else if(!csimple){
					alert("公司中文简称格式错误!");
					return false;
				}else if(!co){
					alert("公司英文简称格式错误!");
					return false;
				}else{
					return true;
				}
			});
		})
		//判断输入密码的类型    
		function CharMode(iN){    
			if (iN>=48 && iN <=57) //数字    
				return 1;    
			if (iN>=65 && iN <=90) //大写    
				return 2;    
			if (iN>=97 && iN <=122) //小写    
				return 4;    
			else    
				return 8;     
		}  
		//bitTotal函数
		//计算密码模式
		function bitTotal(num){
			modes=0;    
			for (i=0;i<4;i++){    
				if (num & 1) modes++;    
				num>>>=1;    
			}  
 			return modes;    
		}  
		//返回强度级别    
		function checkStrong(sPW){    
			if (sPW.length<6)    
				return 0; //密码太短，不检测级别  
			Modes=0;    
			for (i=0;i<sPW.length;i++){    
				//密码模式    
				Modes|=CharMode(sPW.charCodeAt(i));    
			}  
			return bitTotal(Modes);    
		}  
    
		//显示颜色    
		function pwStrength(pwd){    
			Dfault_color="#eeeeee";     //默认颜色  
			L_color="#FF0000";      //低强度的颜色，且只显示在最左边的单元格中  
			M_color="#FF9900";      //中等强度的颜色，且只显示在左边两个单元格中  
			H_color="#33CC00";      //高强度的颜色，三个单元格都显示  
			if (pwd==null||pwd==''){    
				Lcolor=Mcolor=Hcolor=Dfault_color;  
			}    
			else{    
				S_level=checkStrong(pwd);    
				switch(S_level) {    
				case 0:    
					Lcolor=Mcolor=Hcolor=Dfault_color;  
					break;  
				case 1:    
					Lcolor=L_color;  
					Mcolor=Hcolor=Dfault_color;  
					break;    
				case 2:    
					Lcolor=Mcolor=M_color;    
					Hcolor=Dfault_color;    
					break;    
				default:    
					Lcolor=Mcolor=Hcolor=H_color;    
			}    
		}    
			document.getElementById("strength_L").style.background=Lcolor;    
			document.getElementById("strength_M").style.background=Mcolor;    
			document.getElementById("strength_H").style.background=Hcolor;    
			return;
		}  
	</script>
</body>
<!-- END BODY -->
</html>