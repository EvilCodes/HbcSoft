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
	<title>用户登录</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width-device-width,initial-scale-1.0">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/login.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
	<script src="common/plugins/respond.min.js"></script>
	<script src="common/plugins/excanvas.min.js"></script>  
	<style>
		.form-signin{
			max-width: 400px;
			padding: 25px;
			margin: 0 auto;
		}
		input {
			margin-bottom: 10px;
		}
		label{
			padding-left: 15px;
		}
		button{
			margin-top: 30px;
		}
		h2{
			text-align: center;
			color: #333;
			line-height: 2.5em;
			font-size: 30px;
		}
		label{
			font-size: 14px;
			/*line-height: 2.5em;*/
			color: #555;
			font-weight: normal;
		}
		.input-group-addon{
			background: #fff;
		}
		.row a{
			color: #333;
			line-height: 36px;
			font-size: 14px;
		}
		.fujia,
		.footer{
			text-align: center;
		}
		.fujia p,
		.footer p{
			color: #333;
			line-height: 36px;
			margin-top: 20px;
			font-size: 16px;
		}
		.footer p{
			color: #555;
			font-size: 14px;
		}
		.fujia p a{
			padding-left: 20px;
		}
		.fujia a{
			color: #337ab7;
			line-height: 36px;
			margin-top: 20px;
			font-size: 16px;
		}
	</style>
	<script language=JavaScript> 
		//判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
		//这样就可以让登陆窗口显示在整个窗口了 
		function loadTopWindow(){
			if (window.top!=null && window.top.document.URL!=document.URL){ 
				window.top.location= document.URL; 
			}
		} 
	</script>
</head>
<!-- BEGIN BODY -->
<body class="login" onload="loadTopWindow()">
	<div class="container">
		<form class="form-signin" role="form" 
		action="${pageContext.request.contextPath}/loginSubmit.hbc" method="post">
			<h2>欢迎登录</h2>
			<div class="form-group input-group">
				<span class="input-group-addon">
					<span class="glyphicon glyphicon-user"></span>
				</span>
				<input type="text"  id="userName" name="userName" class="form-control" placeholder="请输入帐号" required>
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">
					<span class="glyphicon glyphicon-lock"></span>
				</span>
				<input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" required>
			</div><!-- 
			<label class="checkbox">
				<input type="checkbox" value="remeber-me">使我保持登录状态
			</label> -->
			<div class="form-group inline">
				<table>
					<tr>
						<td>验证码：</td>
						<td><input id="veryCode" name="veryCode" type="text"
							class="form-control" style="width: 100px" required /></td>
						<td></td>
						<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<img id="imgObj" alt=""
							src="${pageContext.request.contextPath }/verifyCode" /></td>
						<td></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="changeImg()">&nbsp;&nbsp;换一张</a></td>
					</tr>
				</table>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
		<div class="fujia">
			<p>没有帐户?<a href="${pageContext.request.contextPath}/loginInput.hbc">创建一个！</a></p>
			<a href="${pageContext.request.contextPath}/modifyPwd/loginpwd.hbc">忘记密码了</a>
		</div>
		<div class="footer">
			<p>&copy;北京鑫友伟业科技有限公司</p>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(
			function() {
			/* 	App.init();
				Login.init();
				jQuery('.register-form-temp').hide(); */

				var mess = '${message}';
				if (mess == "0") {
					alert("登录失败！当前用户已登录，不可同时登录该账户！");
					//window.location.href = "${pageContext.request.contextPath}/indexInit.hbc";
				} else if (mess == "1") {
					alert("登录失败！用户名或密码错误，请重新登录！");
				} else if (mess == "2") {
					alert("登录失败！验证码错误，请重新登录！");
				} else if (mess == "3") {
					alert("登录失败！验证码不能为空，请重新登录！");
				} else if (mess == "5"){
					alert("登录失败！当前用户名属于未激活状态，请查看邮箱并进行激活该用户名！");
				} else if(mess == "6"){
					alert("登录失败！当前用户为已停用状态，如想继续使用，请联系管理员");
				}
				var date = new Date();
				var year = date.getFullYear();
			});
		function changeImg() {
			var imgSrc = $("#imgObj");
			var src = imgSrc.attr("src");
			imgSrc.attr("src", chgUrl(src));
		}
		//时间戳     
		//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
		function chgUrl(url) {
			var timestamp = (new Date()).valueOf();
			urlurl = "${pageContext.request.contextPath }/verifyCode?timestamp=" + timestamp;
			return urlurl;
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>