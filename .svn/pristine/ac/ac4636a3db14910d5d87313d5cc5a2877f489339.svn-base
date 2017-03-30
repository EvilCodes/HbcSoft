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
<title>创建数据库表名</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	//保存数据信息
	function testFT(){
		var filed = document.getElementById("formType");
		var formType = filed.value;
		$.ajax({
			url:'${pageContext.request.contextPath}/formTable/isFormTypeRepeat.hbc?formType='+formType,
			type: "post",
			async:false, 
			success: function(msg){
			var dataJson = JSON.parse(msg);
			if(dataJson.isExist){
				alert('该表单类型已经存在,请重新输入');
				filed.focus();
				filed.select();
			}
			},
			error : function(result){
				messages.alert("查询失败！");
			}
		});
	}
	
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
	
	//提交校验
	function TiJiao(){
		var tN = document.getElementById("tableName").value;
		var aU = document.getElementById("actionUrl").value;
		var md = document.getElementById("method").value;
		var fT = document.getElementById("formType").value;
		if(tN == ''){
			alert('表单名称不能为空');
			return false;
		}else if(aU == ''){
			alert('路径不能为空');
			return false;
		}else if(md == ''){
			alert('提交方式不能为空');
			return false;
		}else if(fT == ''){
			alert('表单类型不能为空');
			return false;
		}else {
			$("#form").submit();
		}
	}
	
</script>
</head>
<body>
	<!-- <div>
		<h3 align="center">增加数据表</h3>
	</div>
	<div style="padding: 10px 100px 10px;"> -->
	<div class="container">
		<div class="row">
			<h3>新增表单表</h3>
			<form class="form-horizontal" role="form" id="form"
					action="${pageContext.request.contextPath}/formTable/addFormName.hbc" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">表单名称</h3>
					</div>
					<div class="panel-body">
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表单名称</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="tableName" id="tableName">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">路径</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="actionUrl" id="actionUrl" value="/template/open.hbc?formType=" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">提交方式</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="method" name="method">
									<option value="get">get</option>
									<option value="post">post</option>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表单类型</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="表单类型" name="formType" id="formType" onblur="testFT()">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">备注</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="备注" name="memo" id="memo">
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">字段属性</h3>
						</div>
						<div class="actions">
							<button type="button" onclick="TiJiao()" class="btn btn-default icons">保存</button>
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
										<th>
											<input type="checkbox" name="all" onclick="checkAll(this,'rowid')">
										</th>
										<th>表单名称</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="saveForm">
									<c:forEach items="${listTab }" var="formTable" varStatus="tt">
										<tr ondblclick="editTable('${formTable.id }')">
											<td>
												<input type="checkbox" name="rowid" value="${formTable.id }" id="rowid">
											</td>
											<td>
												${formTable.tableName }	
											</td>
											<td>
												${formTable.memo }	
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