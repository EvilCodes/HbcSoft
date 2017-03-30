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
			<form  role="form" class="form-horizontal col-md-9 col-sm-12  col-md-offset-1" 
			action="${pageContext.request.contextPath}/modifyPwd/updatePwd.hbc" autocomplete="off">
				<h3>修改密码</h3>
				<hr>
				<div class="form-group">
					<label for="password" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>密码：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="password" class="form-control" maxlength="12"  onKeyUp=pwStrength(this.value) 
						onBlur=pwStrength(this.value) name="password" id="password" placeholder="请输入密码" required>
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
					<label for="repassword" class="col-md-2 col-sm-2  control-label"><span class="must">*&nbsp;</span>确认密码：</label>
					<div class="col-md-6 col-sm-5 ">
						<input type="password" class="form-control" maxlength="12" name="repassword" id="repassword"  placeholder="请再次输入密码" required>
					</div>
					<div class="col-md-4 col-sm-4 ">
						<p class="txt" id="repasswordtxt"></p>
					</div>
				</div>
				<hr>
				<button type="submit" class="btn btn-primary  col-md-6 col-sm-5  col-md-offset-2 col-sm-offset-2 
				col-xs-offset-3" id="register" style="margin-bottom: 20px; text-align: center;">确认</button>
				<input type="hidden" class="form-control" name="userId" id="userId" value="${userId}"/>
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
		var password = true;
		var repassword = true;
		//密码验证

		$("input[name='password']").focus(function(){		
			document.getElementById("passwordtxt").innerHTML = icons + "&nbsp;" + "字母开头，含6-12位字母、数字或下划线"
			}).blur(function(){
				var reg1 = /^[a-zA-Z]\w{5,11}$/;
				var key1 = document.getElementById("password").value;
				if(reg1.test(key1) == true){
					document.getElementById("passwordtxt").innerHTML = iconright + "&nbsp;" ;
					password = true;
				}else{
					document.getElementById("passwordtxt").innerHTML = iconwrong + "&nbsp;"  + "密码格式错误";
					password = false;
				}
			})
		//确认密码
		$("input[name='repassword']").focus(function(){		
			document.getElementById("repasswordtxt").innerHTML = icons + "&nbsp;" + "请重新输入密码"
			}).blur(function(){
				var reg1 = /^[a-zA-Z]\w{5,11}$/;
				var key1 = document.getElementById("password").value;
				var key2 = document.getElementById("repassword").value;
				if(reg1.test(key2) == true && key1 == key2){
					document.getElementById("repasswordtxt").innerHTML = iconright + "&nbsp;" ;
					repassword = true;
				}else{
					document.getElementById("repasswordtxt").innerHTML = iconwrong + "&nbsp;"  + "两次密码输入不一致，请重新输入";
					repassword = false;
				}
			})
		$("form").submit(function(e){
			// if(password && repassword && username && mail && company && csimple && co){
			// 	return true;
			// }else{
			// 	alert("提交失败，请查检数据输入。");
			// 	return false;
			// }
			if(!password){
				alert("密码格式错误!");
				return false;
			}else if(!repassword){
				alert("确认密码格式错误!");
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
</html>