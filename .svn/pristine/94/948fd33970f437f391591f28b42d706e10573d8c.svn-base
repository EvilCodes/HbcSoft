<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<!--[if lt IE 9]>
		<script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]--> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width-device-width,initial-scale-1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
	<header>
			<div class="row" id="pc">
				<div class="title">
					<%-- <img src="${pageContext.request.contextPath}/image/logo.png" alt=""> --%>
					<p>办公管理平台</p>
				</div>
				<div class="fix">
					<ul class="nav nav-pills pull-right"> 
						<li><a href="#" class="default">欢迎 &nbsp;<%=request.getSession().getAttribute("userName") %>&nbsp;登录成功</a></li>
						<li><a href="${pageContext.request.contextPath}/indexInit.hbc"><span class="glyphicon glyphicon-user"></span> 首页</a></li> 
						<li><a href="${pageContext.request.contextPath}/exitAction.hbc"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li> 
						<li><a href="#" data-toggle="modal" data-target="#changPW"><span class="glyphicon glyphicon-cog"></span>修改密码</a></li>
						<li class="pifu"><a href="#" class="skin"><span class="glyphicon glyphicon-cog"></span> 皮肤</a>
							<ul class="theskin">
								<li><a href="#" class="btn skincolor purple" onclick="change('purple')"></a></li>
								<li><a href="#" class="btn skincolor blue" onclick="change('blue')"></a></li>
								<li><a href="#" class="btn skincolor red" onclick="change('red')"></a></li>
								<li><a href="#" class="btn skincolor black" onclick="change('black')"></a></li>
								<li><a href="#" class="btn skincolor white" onclick="change('white')"></a></li>
								<li><a href="#" class="btn skincolor green" onclick="change('green')"></a></li>
							</ul>
						</li>
					</ul> 
				</div>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="changPW" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">修改密码</h4>
							</div>
							<form  role="form" class="form-horizontal" autocomplete="off" action="${pageContext.request.contextPath}/modifyPwd/indexUpdatePwd.hbc">
							<div class="modal-body">
								<!-- <div class="container"> -->
									<div class="row">
											<div class="form-group">
												<label for="oldpassword" class="col-md-3 col-sm-3  control-label"><span class="must">*&nbsp;</span>旧密码：</label>
												<div class="col-md-8 col-sm-8 ">
													<input type="hidden" id="userId" name="userId" value="<%=request.getSession().getAttribute("userId") %>"/>
													<input type="password" class="form-control" maxlength="12"
													 onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value) name="oldpassword" id="oldpassword" placeholder="请输入旧密码" required>
												</div>
											</div>
											<div class="col-md-offset-3">
												<p id="oldpasswordtxt" class="txt">
													<span class="glyphicon glyphicon-minus-sign"></span>&nbsp;请输入旧密码
												</p>
											</div>
											<div class="form-group">
												<label for="newpassword" class="col-md-3 col-sm-3  control-label"><span class="must">*&nbsp;</span>新密码：</label>
												<div class="col-md-8 col-sm-8">
													<input type="password" class="form-control" maxlength="12"
													 onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value) name="newpassword" id="newpassword" placeholder="请输入新密码" required>
												</div><br>
											</div>
											<div class="col-md-offset-3">
												<p id="newpasswordtxt" class="txt">
													<span class="glyphicon glyphicon-minus-sign"></span>&nbsp;以字母开头，含6-12位字母、数字或下划线
												</p>
											</div>
											<div class="form-group">
												<label for="repassword" class="col-md-3 col-sm-3  control-label"><span class="must">*&nbsp;</span>确认密码：</label>
												<div class="col-md-8 col-sm-8">
													<input type="password" class="form-control" maxlength="12"
													 name="repassword" id="repassword"  placeholder="请再次输入新密码" required>
												</div><br>
											</div>
											<div class="col-md-offset-3">
												<p id="repasswordtxt" class="txt">
													<span class="glyphicon glyphicon-minus-sign"></span>&nbsp;请输入新密码
												</p>
											</div>
									</div>
	
								<!-- 原密码：<input type="text" class="form-control">
								新密码：<input type="text" class="form-control">
								确认新密码：<input type="text" class="form-control"> -->
							</div>
				 			<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="submit" class="btn btn-primary">确定</button>
							</div>
							</form>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
			</div>
			<div id="phone" class="row">
				<div class="logo">
					<img src="${pageContext.request.contextPath}/image/logo.png" alt="">
					<p>北京海博诚科技有限公司办公管理平台</p>
				</div>
				<div class="menubtn">
					<img src="${pageContext.request.contextPath}/image/logo.png" alt="">
				</div>
				<div id="aside">
					<nav>
						<ul class="subnav">
							<li><a href="#">导航元素1</a>
								<ul class="menu" style="display: none;">
									<li><a href="#">次级导航元素1</a>
										<ul class="submenu">
											<li><a href="#" target="Conframe">次级导航元素1</a></li>
											<li><a href="#" target="Conframe">次级导航元素2</a></li>
											<li><a href="#" target="Conframe">次级导航元素3</a></li>
											<li><a href="#" target="Conframe">次级导航元素4</a></li>
											<li><a href="#" target="Conframe">次级导航元素5</a></li>
											<li><a href="#" target="Conframe">次级导航元素6</a></li>
										</ul>
									</li>
									<li><a href="#" target="Conframe">次级导航元素2</a></li>
									<li><a href="#" target="Conframe">次级导航元素3</a></li>
									<li><a href="#" target="Conframe">次级导航元素4</a></li>
									<li><a href="#" target="Conframe">次级导航元素5</a></li>
									<li><a href="#" target="Conframe">次级导航元素6</a></li>
								</ul>
							</li>
							<li><a href="#">导航元素2</a></li>
							<li><a href="#">导航元素3</a></li>
							<li><a href="#">导航元素4</a></li>
							<li><a href="#">导航元素5</a></li>
							<li><a href="#">导航元素6</a></li>
							<li><a href="#">导航元素7</a></li>
							<li><a href="#">导航元素8</a></li>
						</ul>
						<div class="fix">
							<ul class="phonesubnav"> 
								<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li> 
								<li><a href="#"><span class="glyphicon glyphicon-cog"></span> 设置</a></li> 
							</ul> 
						</div>
					</nav>
				</div>
			</div>
	</header>	
	<section>
		<div id="aside">
			<nav>
				<ul class="subnav">
				<c:forEach items="${navMenu}" var="oNavMenu">
					<c:forEach var="sNavMenu" items="${oNavMenu.childrenResource}" varStatus="i">
						<c:if test="${fn:length(sNavMenu.childrenResource)>0}">
							<li><a href="#" class="inactive">
									<i class="fa fa-leaf"></i>${sNavMenu.name}
								</a>
								<ul class="menu">
									<c:forEach items="${sNavMenu.childrenResource}" var="tNavMenu">
										<c:if test="${fn:length(tNavMenu.childrenResource)>0}">
											<li><a href="#" class="inactive">${tNavMenu.name}</a>
												<ul class="submenu">
													<c:forEach items="${tNavMenu.childrenResource}" var="rNavMenu">
														<li><a href="${pageContext.request.contextPath}${rNavMenu.url}" target="Conframe">${rNavMenu.name}</a></li>
													</c:forEach>
												</ul>
											</li>
										</c:if>
										<c:if test="${fn:length(tNavMenu.childrenResource)<1}">
											<li><a href="${pageContext.request.contextPath}${tNavMenu.url}" target="Conframe">${tNavMenu.name}</a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					<c:if test="${fn:length(sNavMenu.childrenResource)<1}">
						<li>
							<a href="${pageContext.request.contextPath}${sNavMenu.url}" target="Conframe" class="inactive">
								<i class="fa fa-leaf"></i>${sNavMenu.name}
							</a>
						</li>
					</c:if>
					<%-- <li>
						<a href="${pageContext.request.contextPath}/createTable/tableList.hbc" target="Conframe">
							<span class="glyphicon glyphicon-user"></span>创建数据库表
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/formTable/selectAllForm.hbc" target="Conframe">
							<span class="glyphicon glyphicon-user"></span>创建表单
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/createExcel/tableList.hbc" target="Conframe">
							<span class="glyphicon glyphicon-user"></span>Excel导入
						</a>
					</li>
					
					<li><a href="#" ><span class="glyphicon glyphicon-user"></span>系统管理</a>
						<ul class="menu">
							<li><a href="#" >工作流管理</a>
								<ul class="submenu">
									<li><a href="${pageContext.request.contextPath}/workflow/configShow.hbc" target="Conframe">工作流基础配置</a></li>
									<li><a href="${pageContext.request.contextPath}/workflow/showData.hbc" target="Conframe">工作流配置</a></li>
									<li><a href="${pageContext.request.contextPath}/workflow/businessMapShowDate.hbc" target="Conframe">工作流业务绑定</a></li>
								</ul>
							</li>
						 	 <li><a href="#" >科目管理</a>
								<ul class="submenu">
									<li><a href="${pageContext.request.contextPath}/matchSubject/synchronousSubjects.hbc" target="Conframe">科目同步</a></li>
									<li><a href="#" target="Conframe">次级导航元素1</a></li>
									<li><a href="#" target="Conframe">次级导航元素2</a></li>
								</ul>
							</li> 
							<li><a href="${pageContext.request.contextPath}/jsp/sys/dictType/dictType-list.jsp" target="Conframe">数据字典</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/sys/dictType/dictType.jsp" target="Conframe">数据字典类型</a></li>						</ul>
					</li> 
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>导航元素</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>导航元素</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>导航元素</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>导航元素</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>导航元素</a></li> --%>
					</c:forEach>
				<%-- 	<li>
						<a href="${pageContext.request.contextPath}/report/head/open.hbc" target="Conframe">
							<span class="glyphicon glyphicon-user"></span>头
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/report/row/open.hbc" target="Conframe">
							<span class="glyphicon glyphicon-user"></span>行
						</a>
					</li> --%>
				</c:forEach>
				</ul>
			</nav>
		</div>
		<div id="contentWrap" class="contentWrap">
			<div class="Conframe">
				<iframe  id="Conframe" name="Conframe" src="" frameborder="0"></iframe>
				<iframe style="display: none;" id="frameDis" name="frameDis" src="" frameborder="0"></iframe>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		var conframe=document.getElementById('Conframe');
		var target=document.getElementById("frameDis");
		var URL=document.getElementById('Conframe').contentWindow.location;
		function change(type){
			$.ajax({
				type: 'post',
				data: 'json',
				async:false,
				url: '${pageContext.request.contextPath}/cssPath.hbc?type='+type,
				success: function(msg){
					if(msg=="\"0\""){
						document.getElementById("myCss").href="${pageContext.request.contextPath}/css/skin/"+type+".css";
						target.style.display="block";
						conframe.style.display="none";
						document.getElementById('Conframe').contentWindow.location.replace(URL); 
						alert("换肤成功！");
						target.style.display="none";
						conframe.style.display="block";
					}else{
						alert("换肤失败！");
					}
				},
				error: function(xhr,err,data){
					alert("请求失败！");
				}
			});
		}
	</script>
	<script>
		$(document).ready(
			function() {
				var mess = '${message}';
				if (mess == "0") {
					alert("旧密码错误！");
				}else if(mess == "1"){
					alert("修改失败！");
				}else if(mess == "2"){
					alert("修改成功！");
				}
			});
		$(document).ready(function(){
			$("#phone .logo img").click(function(){
				if($("#phone #aside").is(":hidden")){
					$("#phone #aside").slideDown();
				}else{
					$("#phone #aside").slideUp();
				}
			})
		})
		$(document).ready(function(){
			$(".theskin").stop().hide();
			$(".skin").click(function(){
				if($(".theskin").is(":hidden")){
					$(".theskin").stop().slideDown();
				}else{
					$(".theskin").stop().slideUp();
				}
			})
			$(".pifu").hover(function(){},function(){
				$(".theskin").slideUp()
			})

		})
		$(function(){
			$(".menu").hide()
			$(".submenu").hide()
			$(".subnav > li > a").click(function(){
				$(this).addClass("navActive").parents().siblings().find("a").removeClass("navActive");
				$(this).parents().siblings().find(".menu").hide(300);
				$(this).siblings(".menu").toggle(300);
				$(this).parents().siblings().find(".menu > li > .submenu").hide()
			})
			$(".menu > li > a").click(function(){
				$(this).addClass("menuActive").parents().siblings().find("a").removeClass("menuActive");
				$(this).parents().siblings().find(".submenu").hide(300);	
				$(this).siblings(".submenu").toggle(300);	
			})

			$(".submenu > li > a").click(function(){
				$(this).addClass("submenuActives").parents().siblings().find("a").removeClass("submenuActives");
			})
		})
		$(function(){
			var icons = '<span class="glyphicon glyphicon-minus-sign"></span>';
			var iconright = '<span class="glyphicon glyphicon-ok-sign" style="color: #7cde68"></span>';
			var iconwrong = '<span class="glyphicon glyphicon-remove-sign" style="color: #ff0000;">';
			var password = true;
			var repassword = true;
			//密码验证---旧密码
			$("input[name='oldpassword']").focus(function(){		
				// document.getElementById("oldpasswordtxt").innerHTML = icons + "&nbsp;" + "以字母开头，含6-12位字符、数字或下划线"
				}).blur(function(){
					var reg1 = /^(?!_)[a-zA-Z0-9_]{6,12}$/;
					var key1 = document.getElementById("oldpassword").value;
					if(reg1.test(key1) == true){
						document.getElementById("oldpasswordtxt").innerHTML = iconright + "&nbsp;" ;
						password = true;
					}else{
						document.getElementById("oldpasswordtxt").innerHTML = iconwrong + "&nbsp;"  + "密码格式错误";
						password = false;
					}
				})
			//密码验证---新密码

			$("input[name='newpassword']").focus(function(){		
				// document.getElementById("newpasswordtxt").innerHTML = icons + "&nbsp;" + "字母开头，含6-12位字符、数字或下划线"
				}).blur(function(){
					var reg2 = /^[a-zA-Z]\w{5,11}$/;
					var key2 = document.getElementById("newpassword").value;
					if(reg2.test(key2) == true){
						document.getElementById("newpasswordtxt").innerHTML = iconright + "&nbsp;" ;
						password = true;
					}else{
						document.getElementById("newpasswordtxt").innerHTML = iconwrong + "&nbsp;"  + "密码格式错误";
						password = false;
					}
				})
			//确认密码
			$("input[name='repassword']").focus(function(){		
				// document.getElementById("repasswordtxt").innerHTML = icons + "&nbsp;" + "请重新输入密码"
				}).blur(function(){
					var reg3 = /^[a-zA-Z]\w{5,11}$/;
					var key2 = document.getElementById("newpassword").value;
					var key3 = document.getElementById("repassword").value;
					if(reg3.test(key3) == true && key2 == key3){
						document.getElementById("repasswordtxt").innerHTML = iconright + "&nbsp;" ;
						repassword = true;
					}else{
						document.getElementById("repasswordtxt").innerHTML = iconwrong + "&nbsp;"  + "两次密码输入不一致，请重新输入";
						repassword = false;
					}
				})
			$("form").submit(function(e){
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
	</script>
</body>
</html>