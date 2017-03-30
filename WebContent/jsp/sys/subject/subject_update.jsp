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
		<h3 align="center">编辑科目配置信息</h3> 
		<form class="bs-example bs-example-form" role="form" id="form"
					action="${pageContext.request.contextPath}/subjectSet/updateSubject.hbc" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">编辑科目配置信息</h3>
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
										<th>科目类型</th>
										<th>科目编码</th>
										<th>科目名称</th>
										<th>科目级次</th>
										<th>是否启用</th>
										<th>备注信息</th>
									</tr>
								</thead>
								<tbody id="saveForm">
										<tr ondblclick="editTable('${subject.id }')">
											<td>
												<input type="checkbox"  checked ='checked' name="rowid" value="${subject.id }" id="rowid" >
												<input type="hidden" name="id" id="id" value="${subject.id }">
												<input type="hidden" name="companyId" id="companyId" value="${subject.companyId }">
											</td>
											<td>
												<input type="text" class="form-control" name="subjectType" id="subjectType" 
												value="${subject.subjectType }">
											</td>
											<td>
												<input type="text" class="form-control" name="subjectCode" id="subjectCode"  readonly="readonly"
												value="${subject.subjectCode }">
											</td>
											<td>
												<input type="text" class="form-control" name="subjectName" id="subjectName" 
												value="${subject.subjectName }">
											</td>
											<td>
												<input type="text" class="form-control" name="subjectLevel" id="subjectLevel" 
												value="${subject.subjectLevel }">
											</td>
<%-- 											<td>
												<input type="text" class="form-control" name="parentId" id="parentId" 
												value="${subject.parentId }">
											</td>
 --%>											<td>
												<select class="form-control"  name='isEnable'>
													<option value="1" ${subject.isEnable=="1"?'selected':''}>是</option>
													<option value="0"  ${subject.isEnable=="0"?'selected':''}>否</option>
												</select>
											</td>											
											<td>
												<input type="text" class="form-control" name="remark" id="remark" 
												value="${subject.remark }">
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