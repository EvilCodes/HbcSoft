<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>忘记密码</title>
	<meta http-equiv="content-language" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<form role="form" class="form-horizontal col-md-9 col-sm-12  col-md-offset-1" 
			action="${pageContext.request.contextPath}/modifyPwd/loginPwdSubmit.hbc" autocomplete="off">
				<h3>忘记密码</h3>
				<hr>
				<div class="form-group">
					<label for="yourid" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>帐号：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" maxlength="12"  onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value) name="yourid" id="yourid" placeholder="请输入密码" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p id="youridtxt" class="txt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>真实姓名：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="username" id="username" required placeholder="请输入姓名">
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="usernametxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="mail" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>邮箱：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="mail" id="mail" placeholder="请输入邮箱" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="mailtxt"></p>
					</div>
				</div>
				<div class="form-group">
					<label for="company" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>公司名称：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="text" class="form-control" name="company" id="company" placeholder="请输入公司名称" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="companytxt"></p>
					</div>
				</div>
				<hr>
				<button type="submit" onclick="submit()" class="btn btn-primary  col-md-6 col-sm-5  col-md-offset-2 col-sm-offset-2 col-xs-offset-3" id="register" style="margin-bottom: 20px; text-align: center;">确认</button>
				<input type="hidden" class="form-control" name="message" id="message" value="${message}"/>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap/bootstrap.min.js"></script>
	<script>
		$(function(){
			var icons = '<span class="glyphicon glyphicon-minus-sign"></span>';
			var iconright = '<span class="glyphicon glyphicon-ok-sign" style="color: #7cde68"></span>';
			var iconwrong = '<span class="glyphicon glyphicon-remove-sign" style="color: #ff0000;">';
			var yourid = true;
			var username = true;
			var mail = true;
			var company = true;
			var message=document.getElementById("message").value;
			if(message==1){
				alert("填写的部分信息错误！请核对后重新输入......");
			}else if(message==2){
				alert("我们将以发邮件的方式来进行修改密码的操作,请查看您的邮箱,谢谢！");
				window.location.href="${pageContext.request.contextPath}/login.hbc";
			}
			//账号验证
			$("input[name='yourid']").focus(function(){		
				document.getElementById("youridtxt").innerHTML = icons + "&nbsp;" + "请输入帐号";
				}).blur(function(){
					var reg1 = /^[A-Za-z0-9]\w{4,11}$/;
					var key1 = document.getElementById("yourid").value;
					if(reg1.test(key1) == true){
						document.getElementById("youridtxt").innerHTML = iconright + "&nbsp;" ;
						yourid = true;
					}else{
						document.getElementById("youridtxt").innerHTML = iconwrong + "&nbsp;"  + "您输入的帐号有误";
						yourid = false;
					}
				})
			//真实姓名验证
			$("input[name='username']").focus(function(){		
				document.getElementById("usernametxt").innerHTML = icons + "&nbsp;" + "请输入真实姓名"
				}).blur(function(){
					var reg3 = /^[\u4E00-\u9FFFa-zA_Z]+$/;
					var key3 = document.getElementById("username").value;
					if(reg3.test(key3) == true){
						document.getElementById("usernametxt").innerHTML = iconright + "&nbsp;" ;
						username = true;
					}else{
						document.getElementById("usernametxt").innerHTML = iconwrong + "&nbsp;"  + "请输入真实姓名";
						username = false;
					}
				})
			//邮箱验证
			$("input[name='mail']").focus(function(){
				document.getElementById("mailtxt").innerHTML = icons + "&nbsp;" + "请输入邮箱地址"
				}).blur(function(){
					var reg4 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.)(com|com\.cn|cn)$/;
					var key4 = document.getElementById("mail").value;
					if(reg4.test(key4) == true){
						document.getElementById("mailtxt").innerHTML = iconright;
						mail = true;
					}else{
						document.getElementById("mailtxt").innerHTML =  iconwrong + "&nbsp;"  + "邮箱格式错误";
						mail = false;
					}
				})
			//公司名称
			$("input[name='company']").focus(function(){
				document.getElementById("companytxt").innerHTML = icons + "&nbsp;" + "请输入公司名称"
				}).blur(function(){
					//var reg5 = /^[\u4E00-\u9FA5]{4,}$/;
					var reg5 = /^[\(\)\（\）\u4e00-\u9fa5_a-zA-Z0-9]{4,}$/;
					var key5 = document.getElementById("company").value;
					if(reg5.test(key5) == true){
						document.getElementById("companytxt").innerHTML = iconright;
						company = true;
					}else{
						document.getElementById("companytxt").innerHTML =  iconwrong + "&nbsp;"  + "请输入正确的公司名称";
						company = false;
					}
				})
			$("form").submit(function(e){
				if(!yourid){
					alert("您输入的帐号有误!");
					return false;
				}else if(!username){
					alert("真实姓名格式错误!");
					return false;
				}else if(!mail){
					alert("邮箱格式错误!");
					return false;
				}else if(!company){
					alert("公司名称格式错误!");
					return false;
				}else{
					return true;
				}
			});
		})
	</script>
</body>
</html>