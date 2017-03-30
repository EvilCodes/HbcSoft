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
<title>字段查询信息</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
function backList(){
	$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/queryAllReportConfigs.hbc");
	$("#form").submit();
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>字段查询信息</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
					 action="" method="POST">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
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
										<!-- <th class="check"><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th> -->
										<th>表名</th>
										<th>字段名称</th>
										<th>标题</th>
										<th>类型</th>
										<th>下拉框状态</th>
										<th>下拉框内容</th>
										<th>点选信息</th>
										<th>顺序</th>
										<!-- <th>操作</th> -->
									</tr>
								</thead>
								<tbody id="tr_num">
									<c:if test="${not empty listRCS }">
										<c:forEach items="${listRCS }" var="entity" varStatus="status">
											<tr id="1">
												<%-- <td class="check">
													<input type="checkbox" id="${status.index+1}" name="rowid" value="${entity.reportSearchId}">
												</td> --%>
												<td>
													<input type="text" class="form-control" placeholder="表名" name = "tableName" id="tableName"  value="${entity.tableName}" readonly>
													<input type="hidden" name = "id" id="tableId"  value="${entity.id}" readonly>
												</td>
												<td>
													<input type="text" class="form-control" name="reportSearchName" id="reportSearchName" value="${entity.reportSearchName}" readonly>
													<input type="hidden" class="form-control" name="reportId" id="reportId" value="${reportId}" readonly>
													<%-- <input type="hidden" name = "reportSearchId" id="reportSearchId" value="${entity.reportSearchId}"> --%>
												</td>
												<td><input type="text" class="form-control" placeholder="标题" name="title"  value="${entity.title}"></td>
											
												<td>
														<select class="form-control" name="inputType" title="${entity.inputType}" id="${typeid}a" disabled="disabled">
															<option value="0" <c:if test="${entity.inputType=='0' }">selected</c:if>>输入框</option>
															<option value="1" <c:if test="${entity.inputType=='1' }">selected</c:if>>下拉框</option>
															<option value="2" <c:if test="${entity.inputType=='2' }">selected</c:if>>按钮</option>
															<option value="3" <c:if test="${entity.inputType=='3' }">selected</c:if>>多选框</option>
															<option value="4" <c:if test="${entity.inputType=='4' }">selected</c:if>>点选</option>
															<option value="5" <c:if test="${entity.inputType=='5' }">selected</c:if>>日期</option>
															<option value="6" <c:if test="${entity.inputType=='6' }">selected</c:if>>文本域</option>
															<option value="7" <c:if test="${entity.inputType=='7' }">selected</c:if>>金额</option>
														</select>
												</td>
												<td>
													<c:if test="${entity.inputType == '1' }">
														<select class="form-control" name="sourceMode" id="${entity.id}b">
															<option value='0'<c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定</option>
															<option value='1'<c:if test="${entity.sourceMode=='1' }">selected</c:if>>sql</option>
														</select>
													</c:if>
													<c:if test="${entity.inputType != 1 }">
														<select class="form-control" name="sourceMode" id="${entity.id}b" disabled="disabled">
															<option value=''<c:if test="${entity.sourceMode=='' }">selected</c:if>></option>
															<%-- <option value='0'<c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定</option>
															<option value='1'<c:if test="${entity.sourceMode=='1' }">selected</c:if>>sql</option> --%>
														</select>
														<input type="hidden" name="sourceMode" title="sourceMode" id="${entity.id}b" value="${entity.sourceMode}">
													</c:if>
											    </td>
											    <td><input type='text' class='form-control' name='sourceContent' value="${entity.sourceContent }" id="${defaultid2}c" readonly></td>
												<%-- <td><input type="text" class="form-control" name="inputDefaultValue" id="inputDefaultValue" value="${entity.inputDefaultValue}" readonly></td> --%>
												<td>
													<c:if test="${entity.inputType == '4' }">
														<select class='form-control' name='clickInfo' style='width:100%' id="${defaultid3}d" disabled="disabled">
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}" ${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
													</c:if>
													<c:if test="${entity.inputType != '4' }">
														<select class='form-control' name='clickInfo' style='width:100%' id="${defaultid3}d" disabled="disabled">
															<option value=''<c:if test="${entity.clickInfo=='' }">selected</c:if>></option>
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}" ${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
														<input type="hidden" name="clickInfo" id="${defaultid3}d" value="${entity.clickInfo}">
													</c:if>
												</td>
												<td><input type='text' class='form-control' name='sort' value = "${entity.sort }" readonly ></td>
												<td style='display:none'><input type='hidden' name='reportId' value='0'></td>
												
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