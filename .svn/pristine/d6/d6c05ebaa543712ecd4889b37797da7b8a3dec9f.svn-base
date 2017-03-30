<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="content-language" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width-device-width,initial-scale-1.0">
<title>编辑第三方数据库配置信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	//全选按钮
	function checkAll(obj,checkName){
		var checkboxs = document.getElementsByName(checkName);
		for(var i = 0; i<checkboxs.length; i++ ){
			if(obj.checked == true){
				checkboxs[i].checked = true;
			}else{
				checkboxs[i].checked = false;
			}
		}
	}
	$(document).ready(function(){
		$("#method").val('${forTab.method}'); 
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
		<h3 align="center">编辑第三方数据库配置信息</h3> 
		<form class="bs-example bs-example-form" role="form" id="form"
					action="${pageContext.request.contextPath}/outerDBLink/updateDBLinkPara.hbc" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">编辑第三方数据库配置信息</h3>
						</div>
							<div class="actions">
								<button type="submit" class="btn btn-default">保存</button>
							</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
					<!-- <div class="form-group"> -->
						<div class="table-responsive">
						<!-- <table class="table table-striped table-bordered"> -->
							<table class="table table-bordered table-striped table-hover">
								<thead>
									<tr class="active">
										<th class="table-checkbox">
											<input type="checkbox" name="all" onclick="checkAll(this,'rowid')">
										</th>
										<th>数据库IP</th>
										<th>数据库类型</th>
										<th>端口号</th>
										<th>数据库名称</th>
										<th>用户名</th>
										<th>密码</th>
										<th>备注信息</th>
										<th>是否启用</th>
									</tr>
								</thead>
								<tbody id="saveForm">
										<tr ondblclick="editTable('${outerDBLinkPara.id }')">
											<td>
												<input type="checkbox"  checked ='checked' name="rowid" value="${outerDBLinkPara.id }" id="rowid" >
												<input type="hidden" name="id" id="id" value="${outerDBLinkPara.id }">
												<input type="hidden" name="companyId" id="companyId" value="${outerDBLinkPara.companyId }">
											</td>
										<%-- 	<td>
												<input type="text" class="form-control" name="companyName" id="companyName" 
												value="${outerDBLinkPara.companyName }">
											</td> --%>
											<td>
												<input type="text" class="form-control" name="dbIp" id="dbIp" 
												value="${outerDBLinkPara.dbIp }">
											</td>
											<td>
												<select class='form-control'  name='dbType'>
													<option value='0' ${outerDBLinkPara.dbType=="0"?'selected':''}>Mysql</option>
													<option value='1' ${outerDBLinkPara.dbType=="1"?'selected':''}>sqlServer</option>
													<option value='2' ${outerDBLinkPara.dbType=="2"?'selected':''}>Oracle</option>
												</select>
											</td>
											<td>
												<input type="text" class="form-control" name="dbport" id="dbport" 
												value="${outerDBLinkPara.dbport }">
											</td>
											<td>
												<input type="text" class="form-control" name="dbsId" id="dbsId" 
												value="${outerDBLinkPara.dbsId }">
											</td>
											<td>
												<input type="text" class="form-control" name="dbUser" id="dbUser" 
												value="${outerDBLinkPara.dbUser }">
											</td>
											<td>
												<input type="text" class="form-control" name="dbPass" id="dbPass" 
												value="${outerDBLinkPara.dbPass }">
											</td>
											<td>
												<input type="text" class="form-control" name="remark" id="remark" 
												value="${outerDBLinkPara.remark }">
											</td>
											<td>
												<select class="form-control"  name='isEnabled'>
													<option value="1" ${outerDBLinkPara.isEnabled=="1"?'selected':''}>是</option>
													<option value="0"  ${outerDBLinkPara.isEnabled=="0"?'selected':''}>否</option>
												</select>
											</td>
										</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>