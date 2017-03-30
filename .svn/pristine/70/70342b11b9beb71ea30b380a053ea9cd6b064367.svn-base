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
<title>修改数据库表名</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
	<!-- <div>
		<h3 align="center">修改数据表</h3>
	</div>
	<div style="padding: 10px 100px 10px;"> -->
	<div class="container">
		<div class="row">
			<h3>修改表单表</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
					action="${pageContext.request.contextPath}/formTable/updateTableForm.hbc" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">表单名称</h3>
					</div>
					<div class="panel-body">
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表单名称</label>
							<div class="col-md-10 col-sm-8">
								<input type="hidden" class="form-control" name="tabId" id="tabId" value="${forTab.id}">
								<input type="text" class="form-control" name="tableName" id="tableName" value="${forTab.formNamef}">
								<input type="hidden" class="form-control" name="versNum" id="versNum" value="${forTab.versNum}">
							</div>
						</div>
						<%-- <div class="input-group">
							<span class="input-group-addon">表单名称</span>
							<input type="hidden" class="form-control" name="tabId" id="tabId" value="${forTab.id}">
							<input type="text" class="form-control" name="tableName" id="tableName" value="${forTab.formName}">
							<input type="hidden" class="form-control" name="versNum" id="versNum" value="${forTab.versNum}">
						</div> --%>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">路径</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="actionUrl" id="actionUrl" value="${forTab.actionUrl}" readonly>
							</div>
						</div>
						<%-- <div class="input-group">
							<span class="input-group-addon">路径</span>
							<input type="text" class="form-control" name="actionUrl" id="actionUrl" value="${forTab.actionUrl}">
						</div> --%>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">提交方式</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="method" name="method">
									<option value="get">get</option>
									<option value="post">post</option>
								</select>
							</div>
						</div>
						<!-- <div class="form-group" style="width: 300px">
							<label class="control-label col-sm-10" for="name">提交方式</label>
							<select class="form-control" id="method" name="method">
								<option value="get">get</option>
								<option value="post">post</option>
							</select>
						</div> -->
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表单类型</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="formType" id="formType" value="${forTab.formType}" readonly>
							</div>
						</div>
						<%-- <div class="input-group">
							<span class="input-group-addon">表单类型</span>
							<input type="text" class="form-control" name="formType" id="formType" value="${forTab.formType}">
						</div> --%>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">备注</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="备注" name="memo" id="memo" value="${forTab.memo}">
							</div>
						</div>
						<%-- <div class="input-group">
							<span class="input-group-addon">备注</span>
							<input type="text" class="form-control" placeholder="备注" name="memo" id="memo" value="${forTab.memo}">
						</div> --%>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">字段属性</h3>
						</div>
						<!-- <div class="btn-toolbar" role="toolbar" style="float: right;">
							<div class="btn-group btn-group-sm"> -->
							<div class="actions">
								<button type="submit" class="btn btn-default">保存</button>
								<!-- <button type="button" class="btn btn-default" onclick="saveForm()">保存</button> -->
								<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
								<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
								<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
								<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
								<input type="hidden" name="searchTable" id="searchTable" value="${searchTable }">
								<input type="hidden" name="searchMemo" id="searchMemo" value="${searchMemo }">
							</div>
						<!-- </div> -->
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
										<th>表名</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="saveForm">
									<c:forEach items="${forfie }" var="formTable" varStatus="tt">
										<tr ondblclick="editTable('${formTable.tableId }')">
											<td>
												<input type="checkbox"  checked ='checked' name="rowid" value="${formTable.tableId }" id="rowid" >
											</td>
											<td>
												${formTable.tableName }	
											</td>
											<td>
												${formTable.tableMemo }
											</td>
										</tr>
									</c:forEach>
									<c:forEach items="${lstft }" var="lstft" varStatus="ft">
										<tr ondblclick="editTable('${lstft.id }')">
											<td>
												<input type="checkbox"  name="rowid" value="${lstft.id }" id="rowid" >
											</td>
											<td>
												${lstft.tableName }	
											</td>
											<td>
												${lstft.memo }
											</td>
										</tr>
									</c:forEach>
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