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
<title>查看表单字段表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
function backList(){
	$('#form').attr("action","${pageContext.request.contextPath}/formTable/selectAllForm.hbc");
	$("#form").submit();
}
</script>
</head>
<body>
	<!-- <div>
		<h3 align="center">增加数据表</h3>
	</div> -->
	<!-- <div style="padding: 10px 100px 10px;"> -->
	<div class="container">
		<div class="row">
			<h3>查看表单字段表</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
				action="" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div  class="captions">
							<h3 class="panel-title">字段属性</h3>
						</div>
						<div class="actions">
							<button type="button" class="btn btn-default" onclick="backList()">返回列表</button>
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="table-responsive"  id="xzbdzdb">
						<!-- <table class="table table-striped table-bordered"> -->
							<table class="table table-bordered table-striped table-hover formtable">
								<thead>
									<tr>
										<th>表名</th>
										<th>字段名称</th>
										<th>字段标题</th>
										<th>是否显示</th>
										<th>字段类型</th>
										<th style="display:none">字段长度</th>
										<th style="display:none">小数位数</th>
										<th style="display:none">是否允许为空</th>
										<th>下拉框状态</th>
										<th>内容</th>
										<th>是否为查询条件</th>
										<th>是否在查询列显示</th>
										<th>是否显示列</th>
										<th>是否可修改</th>
										<th>是否查询必录项</th>
										<th>是否录入必录项</th>
										<th>默认值</th>
										<th>字段排列顺序</th>
									</tr>
								</thead>
								<tbody id="tr_num">
									<c:if test="${not empty lstfie }">
										<c:forEach items="${lstfie }" var="entity" varStatus="status">
											<tr id="1">
												<td>
													<input type="text" class="form-control" placeholder="表名" name = "tableName" id="tableName"  value="${entity.tableName}" readonly>
													<input type="hidden" name = "tableId" id="tableId"  value="${entity.tableId}" readonly>
												</td>
												<td>
													<input type="text" class="form-control" name="fieldName" id="fieldName" value="${entity.fieldName}" readonly>
													<input type="hidden" name = "formid" id="formid"  value="${entity.formId}">
													<input type="hidden" name = "fieldId" id="fieldId" value="${entity.fieldId}">
												</td>
												<td><input type="text" class="form-control" placeholder="标题" name="title" id="title" value="${entity.title}" readonly></td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="inputIsDisplay" id="inputIsDisplay" title="${entity.inputIsDisplay=='0' ? '是' : '否' }" disabled="disabled">
															<option value="0" <c:if test="${entity.inputIsDisplay=='0' }">selected</c:if>>是</option>
															<option value="1" <c:if test="${entity.inputIsDisplay=='1' }">selected</c:if>>否</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="inputType" title="${entity.inputType}" id="${entity.fieldId}a" disabled="disabled">
															<option value="0" <c:if test="${entity.inputType=='0' }">selected</c:if>>输入框</option>
															<option value="1" <c:if test="${entity.inputType=='1' }">selected</c:if>>下拉框</option>
															<%-- <option value="2" <c:if test="${entity.inputType=='2' }">selected</c:if>>按钮</option> --%>
															<option value="3" <c:if test="${entity.inputType=='3' }">selected</c:if>>多选框</option>
															<option value="4" <c:if test="${entity.inputType=='4' }">selected</c:if>>点选</option>
															<option value="5" <c:if test="${entity.inputType=='5' }">selected</c:if>>日期</option>
															<option value="6" <c:if test="${entity.inputType=='6' }">selected</c:if>>文本域</option>
															<option value="7" <c:if test="${entity.inputType=='7' }">selected</c:if>>金额</option>
														</select>
													</div>
												</td>
												<td style="display:none"><input class="form-control" placeholder="100" name="number" value="${entity.number }" title="${entity.number }"></td>
												<td style="display:none"><input class="form-control" placeholder="0" name="decimalDigits" value="${entity.decimalDigits }" title="${entity.decimalDigits }"></td>
												<td style="display:none">
													<select class="form-control" name="isNull" title="${entity.isNull=='0' ? '是' : '否' }">
														<option value="0" <c:if test="${entity.isNull=='0' }">selected</c:if>>是</option>
														<option value="1" <c:if test="${entity.isNull=='1' }">selected</c:if>>否</option>
													</select>
												</td>
												<td>
													<select class="form-control" name="sourceMode"title="${entity.sourceMode}" id="${entity.fieldId}b" disabled="disabled">
														<option value="" <c:if test="${entity.sourceMode=='2' }">selected</c:if>></option>
														<option value="0" <c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定值</option>
														<option value="1" <c:if test="${entity.sourceMode=='1' }">selected</c:if>>SQL</option>
													</select>
												</td>
												<td>
													<input type="text" class="form-control" title="${entity.sourceContent}" name="sourceContent" id="${entity.fieldId}c" value="${entity.sourceContent}" readonly>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="queryisConditions" id="queryisConditions" title="${entity.queryisConditions=='0' ? '否' : '是' }" disabled="disabled">
															<option value="0" <c:if test="${entity.queryisConditions=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.queryisConditions=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="queryisColumn" id="queryisColumn" title="${entity.queryisColumn=='0' ? '否' : '是' }" disabled="disabled">
															<option value="0" <c:if test="${entity.queryisColumn=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.queryisColumn=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isShowColumn" id="isShowColumn" title="${entity.isShowColumn=='0' ? '否' : '是' }" disabled="disabled">
															<option value="0" <c:if test="${entity.isShowColumn=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.isShowColumn=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isModify" id="isModify" title="${entity.isModify=='0' ? '否' : '是' }" disabled="disabled">
															<option value="0" <c:if test="${entity.isModify=='0' }">selected</c:if>>是</option>
															<option value="1" <c:if test="${entity.isModify=='1' }">selected</c:if>>否</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="queryisRequired" id="queryisRequired" title="${entity.queryisRequired=='0' ? '否' : '是' }">
															<option value="0" <c:if test="${entity.queryisRequired=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.queryisRequired=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isRequired" id="isRequired" title="${entity.isRequired=='0' ? '否' : '是' }">
															<option value="0" <c:if test="${entity.isRequired=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.isRequired=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td><input type="text" class="form-control" name="inputDefaultValue" id="inputDefaultValue" value="${entity.inputDefaultValue}" readonly></td>
												<td>
													<input type="text" class="form-control" placeholder="sort" name="sort" id="sort${status.index+1}" value="${entity.sort}" readonly>
												</td> 
											</tr>
										</c:forEach>
									</c:if>
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