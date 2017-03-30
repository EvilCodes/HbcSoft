<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>特殊格式配置</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
	$(document).ready(
		function() {
		var idFlag = 0;
		var idFlag2 = 0;
		$("#addRowConfig").click(
			function() {
				var str = "row" + idFlag;
				var isExtendId = str + idFlag;
				var conditionId = isExtendId + idFlag;
				idFlag++;
				$("#tr_rowNum").append(
			       "<tr id="+str+">"
			       + "<td><input type='text' class='form-control' name='specialRow'></td>"//特殊行
			       + "<td>"
			       		+"<input type='text' class='form-control' name='specialCellRow'>"
			       		+"<input type='hidden' class='form-control' name='specialRowsId' id='specialRowsId' value='0'></td>"
			       +"</td>"//特殊单元格行
			       + "<td><div class='form-group' style='width: 100px'>"
			       + //是否继承查询条件
			       "<select class='form-control'  name='isExtendQueryRow' id="+isExtendId+" onchange='conditionView(\""+isExtendId+"\",\""+conditionId+"\")'>"
			       + "<option value='0'>否</option><option value='1'>是</option>"
			       + "</select></div>"
			       + "</td>"
			       + "<td><input type='text' class='form-control' name='rowQueryConditions' id="+conditionId+" value=''></td>"//特殊行查询条件
			       + "<td><div class='btn-toolbar' role='toolbar'>"
			       + //操作
			       "<div class='btn-group btn-group-sm'>"
			       + "<button type='button' class='btn btn-default' onclick='deltr(\""
			       + str
			       + "\")'>删行</button></div>"
			       + "</div></td></tr>");
			       			});
		$("#addColConfig").click(
			function() {
				var str = "col" + idFlag2;
				var isExtendId = str + idFlag2;
				var conditionId = isExtendId + idFlag2;
				idFlag2++;
				$("#tr_colNum").append(
				"<tr id="+str+">"
				+ "<td><input type='text' class='form-control' name='specialCol'></td>"//特殊列
				+ "<td>"
		       		+"<input type='text' class='form-control' name='specialCellCol'>"
		       		+"<input type='hidden' class='form-control' name='specialColumnId' id='specialColumnId' value='0'></td>"
		       	+"</td>"//特殊单元格行
				+ "<td><div class='form-group' style='width: 100px'>"
				+ //是否继承查询条件
				"<select class='form-control'  name='isExtendQueryCol' id="+isExtendId+" onchange='conditionView(\""+isExtendId+"\",\""+conditionId+"\")'>"
				+ "<option value='0'>否</option><option value='1'>是</option>"
				+ "</select></div>"
				+ "</td>"
				+ "<td><input type='text' class='form-control' name='colQueryConditions' id="+conditionId+" value=''></td>"//特殊列查询条件
				+ "<td><div class='btn-toolbar' role='toolbar'>"
				+ //操作
				"<div class='btn-group btn-group-sm'>"
				+ "<button type='button' class='btn btn-default' onclick='deltr(\""
				+ str
				+ "\")'>删行</button></div>"
				+ "</div></td></tr>");
		});
	});
	
	function conditionView(isExtendId,conditionId){
		var isExtendVal = $("#"+isExtendId).val();
		if(isExtendVal=='1'){
			document.getElementById(conditionId).value
				= document.getElementById("queryConditions").value;
			$("#"+conditionId).attr("disabled","disabled");
		}else if (isExtendVal=='0'){
			$("#"+conditionId).removeAttr("disabled");
			document.getElementById(conditionId).value="";
		}
	}
	
	function deltr(str) {
		$("#" + str).remove();//删除当前行
	}
	function delRow(id) {
		if (confirm("字段已经存在，确认删除吗？")) {
			$('#form')
					.attr("action",
							"${pageContext.request.contextPath}/reportConfig/delSpecialRow.hbc");
			$("#form").submit();
		}
	}
	function delCol(id) {
		if (confirm("字段已经存在，确认删除吗？")) {
			$('#form')
					.attr("action",
							"${pageContext.request.contextPath}/reportConfig/delSpecialCol.hbc");
			$("#form").submit();
		}
	}
	function save() {
		$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/saveSpecialReportConfig.hbc");
		$("#form").submit();
	}
</script>
</head>
<body>
	<form id="formUp"
		action="${pageContext.request.contextPath}/formTable/moveUpForm.hbc"
		method="post">
		<input type="hidden" name="moveUp" id="moveUp">
	</form>
	<div class="container">
		<div class="row">
			<h3>特殊格式配置</h3>
			<hr>
			<form class="bs-example bs-example-form" role="form" id="form"
				action="" method="POST">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title"></h3>
						</div>
						<div class="actions">
							<button type="button" class="btn btn-default" onclick="save()">保存</button>
							<input type="hidden" class="form-control" name="reportId" id="reportId" value="${reportConfig.id}">
							<input type="hidden" class="form-control" name="queryConditions" id="queryConditions" value="${reportConfig.reportSQL}">
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<h3 class="panel-title">特殊行配置</h3>
							<hr class="col-md-11">
							&nbsp;&nbsp;
							<button type="button" class="btn btn-default" id="addRowConfig">增行</button>
							<table
								class="table table-bordered table-striped table-hover formtable"
								id="specialRowConfig" style="width: 150%;">
								<col width="60px;">
								<col width="60px;">
								<col width="60px;">
								<col width="180px;">
								<col width="50px;">
								<thead>
									<tr>
										<th>特殊行</th>
										<th>特殊单元格行</th>
										<th>是否继承查询条件</th>
										<th>查询条件</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tr_rowNum">
									<c:if test="${not empty specialRowList}">
										<c:forEach items="${specialRowList}" var="rowEntity" varStatus="rowStatus">
											<tr>
												<td>
													<input type="text" class="form-control" name="specialRow" value="${rowEntity.specialRow}">
													<input type="hidden" class="form-control" name="specialRowsId" id="specialRowsId" value="${rowEntity.id}">
												</td>
												<td><input type="text" class="form-control" name="specialCellRow" value="${rowEntity.specialCellRow}"></td>
												<td>
													<select class="form-control" name="isExtendQueryRow"  id='${rowEntity.id}a' onchange="conditionView('${rowEntity.id}a','${rowEntity.id}b')">
														<option value='0'<c:if test="${rowEntity.isExtendQuery=='0' }">selected</c:if>>否</option>
														<option value='1'<c:if test="${rowEntity.isExtendQuery=='1' }">selected</c:if>>是</option>
													</select>
												</td>
												<td><input type="text" class="form-control" name="rowQueryConditions"  id = '${rowEntity.id}b' value="${rowEntity.queryConditions}"></td>
												<td>
													<button type="button" class="btn btn-default" onclick="delRow('${rowEntity.id}')">删行</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<p></p>
							<p></p>
							<h3 class="panel-title">特殊列配置</h3>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-default" id="addColConfig">增行</button>
							<hr class="col-md-11">
							<table
								class="table table-bordered table-striped table-hover formtable"
								id="specialColConfig" style="width: 150%;">
								<col width="60px;">
								<col width="60px;">
								<col width="60px;">
								<col width="180px;">
								<col width="50px;">
								<thead>
									<tr>
										<th>特殊列</th>
										<th>特殊单元格列</th>
										<th>是否继承查询条件</th>
										<th>查询条件</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tr_colNum">
									<tr>
									<c:if test="${not empty specialColList}">
										<c:forEach items="${specialColList}" var="colEntity" varStatus="colStatus">
											<tr>
												<td>
													<input type="text" class="form-control" name="specialCol" value="${colEntity.specialColumn}">
													<input type="hidden" class="form-control" name="specialColumnId" id="specialColumnId" value="${colEntity.id}">
												</td>
												<td><input type="text" class="form-control" name="specialCellCol" value="${colEntity.specialCellCol}"></td>
												<td>
													<select class="form-control" name="isExtendQueryCol"  id='${colEntity.id}c' onchange="conditionView('${colEntity.id}c','${colEntity.id}d')">
														<option value='0'<c:if test="${colEntity.isExtendQuery=='0' }">selected</c:if>>否</option>
														<option value='1'<c:if test="${colEntity.isExtendQuery=='1' }">selected</c:if>>是</option>
													</select>
												</td>
												<td><input type="text" class="form-control" name="colQueryConditions"  id = '${colEntity.id}d' value="${colEntity.queryConditions}"></td>
												<td>
													<button type="button" class="btn btn-default" onclick="delCol('${colEntity.id}')">删行</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
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