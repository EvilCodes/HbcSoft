<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
		<title>工作流业务绑定新增页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/nodeWorkflowInput.js" charset="utf-8"></script>
		<script type="text/javascript">
		function add(){
			if($('#select1').val() == ""){
				alert("请输入工作流的id");
				return;
			}
			if($('#select2').val() == ""){
				alert("请输入业务类型");
				return;
			}
			var reg = /^\d*$/;
			if(reg.test($('#flag').val())!=true){
				alert("单据标识请添加整数");
				return;
			}
			fm.submit();
		}
		$(function (){
			var workflowId = '${workflowBusinessMap.workflowId}';
			var actionType ="${workflowBusinessMap.actionType}";
			$('#select1').val(workflowId);
			$('#select2').val(actionType);
		});
		</script>
	</head>
	<body>
		<div class="container">
		<div class="row">
			<h3>工作流业务绑定新增页面</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn btn-default" onclick="add()">确定</a>
							<a href="${ctx}/workflow/businessMapShowDate.hbc" class="btn btn-default">取消</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${ctx}/workflow/addBusinessMap.hbc" name="fm" commandName="workflowBusinessMap" class="form-horizontal">
					<input type = "hidden" name="baseId" id="id" value ="${workflowBusinessMap.baseId}"/>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">工作流ID：</label>
							<div class="col-md-8 col-sm-7">
								<form:select id = "select1" path="workflowId" items="${list}" itemLabel="name" itemValue="baseId" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">业务类型名称：</label>
							<div class="col-md-8 col-sm-7">
								<form:select id = "select2" path="actionType" items="${map}" itemLabel="text" itemValue="code" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">单据标识：</label>
							<div class="col-md-8 col-sm-7">
								<form:input path="flag" id="flag" name="flag" maxlength="100" value = "${workflowBusinessMap.flag}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">备注：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id = "remark" name = "remark" value = "${workflowBusinessMap.remark}" class="form-control" placeholder=""/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
