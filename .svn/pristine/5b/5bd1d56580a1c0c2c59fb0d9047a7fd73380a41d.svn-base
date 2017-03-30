<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/common/workflow.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta http-equiv="content-language" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width-device-width,initial-scale-1.0">
		<title>工作流节点规则新增页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/workflowConfig.js" charset="utf-8"></script>
	</head>
		<script type="text/javascript">
			function add(){
				if($('#tableName').val() == "" || $('#tableName').val > 100){
					alert("请输入表中文名称");
					return;
				}
				if($('#tableId').val() == "" || $('#tableId').val > 100){
					alert("请输入表英文名称");
					return;
				}
				if($('#columnName').val() == "" || $('#columnName').val > 100){
					alert("请输入字段中文名");
					return;
				}
				if($('#columnId').val() == "" || $('#columnId').val > 100){
					alert("请输入字段英文名");
					return;
				}
				fm.submit();
			}
		</script>
	</head>
	<body>
	<div class="container">
		<div class="row">
			<h3>工作流节点规则新增页面</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn btn-default" onclick="add()">确定</a>
							<a href="${pageContext.request.contextPath}/workflow/configShow.hbc" class="btn btn-default">取消</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${pageContext.request.contextPath}/workflow/configAdd.hbc" name="fm" commandName="workflowConfig" class="form-horizontal">
					<input type = "hidden" name="baseId" id="baseId" value = "${workflowConfig.baseId }"/>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">表中文名：</label>
							<div class="col-md-8 col-sm-7">
								<input type ="text" id="tableName" name="tableName" maxlength="100" value = "${workflowConfig.tableName }" class="form-control"/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">表英文名：</label>
							<div class="col-md-8 col-sm-7">
								<input type='text' id="tableId" name ="tableId" maxlength="100" value = "${workflowConfig.tableId }" class="form-control"/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">字段中文名：</label>
							<div class="col-md-8 col-sm-7">
								<input type='text' id="columnName" name="columnName" maxlength="100"value = "${workflowConfig.columnName }" class="form-control"/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">字段英文名：</label>
							<div class="col-md-8 col-sm-7">
								<input type='text' id="columnId" name="columnId" maxlength="100"value = "${workflowConfig.columnId }" class="form-control"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
