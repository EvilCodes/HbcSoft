<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/workflow.jsp"%>
<%@ taglib prefix="h" uri="/tagSearch" %>
<%@ taglib prefix="t" uri="/simpleTag" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<title>查询页面</title>
	<!--[if lt IE 9]>
		<script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]--> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width-device-width,initial-scale-1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/My97DatePicker/skin/WdatePicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/ztree.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/themes/icon.css"/>
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/template/report.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/commonPopup.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-1.3.6/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/ztree.js" charset="utf-8"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<form id="form" action="${pageContext.request.contextPath}/report/open.hbc" method="post" role="form" class="form-horizontal">
				<h3>查询页面</h3>
				<hr>
				<!-- 固定隐藏区 -->
				<div>
					<input type="hidden" id="reportId" value="${reportId }">
					<input type="hidden" id="checkParameter" value='${checkParameter }'>
				</div>
				<!-- 固定按钮区 -->
				<div class="btn-group">
					<a href="#" class="btn btn-default zsgc" onclick="query('${pageContext.request.contextPath}')">查询</a>
					<a href="#" class="btn btn-default zsgc" onclick="exportExcel('${pageContext.request.contextPath}')">Excel导出</a>
				</div>
				<!-- 自定义按钮区 -->
				
				<!-- 查询条件区 -->
				<div class="row">
					<c:forEach items="${lstEdit }" var="lEdit" varStatus="status">
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<h:search nameField="${lEdit.reportSearchName}" 
								valueField="${lEdit.inputValue}" 
								inputIsDisplay="1"
								tagType="${lEdit.inputType}"
								titleField="${lEdit.title}"
								className="form-control "
								endValueField="${lEdit.endValue}"
								startValueField="${lEdit.startValue}"
								clickInfo="${lEdit.clickInfo}"
								sourceMode="${lEdit.sourceMode}"
								sourceContent="${lEdit.sourceContent}"
								clickValue="${lEdit.clickValue}"
							/>
						</div>
					</c:forEach>
				</div>
				<!-- 结果列表区 -->
			 	<div class="">
					<div class="table-responsive verywidth">
						<table class="table table-bordered table-striped table-hover formtable">
							<thead>
							<c:forEach items="${fieldList }" var="list" varStatus="status">
								<c:if test="${status.index < headRow }">
								<tr class="active">
									<c:forEach items="${list}" var="field" varStatus="fstatus">
									<th colspan="${field.columnSpan }" rowspan="${field.rowSpan }">
										<a href="#">${field.value }</a>
									</th>
									</c:forEach>
								</tr>								
								</c:if>
							</c:forEach>
							</thead>
							<tbody>
							<c:forEach items="${fieldList }" var="list" varStatus="status">
								<c:if test="${status.index >= headRow }">
								<tr>
									<c:forEach items="${list}" var="field" varStatus="fstatus">
									<td colspan="${field.columnSpan }" rowspan="${field.rowSpan }">
										<a href="#">
										<c:if test="${fstatus.index < rowLength }">
											${field.value }
										</c:if>
										<c:if test="${fstatus.index >= rowLength }">
											<fmt:formatNumber type="number" value="${field.value }" pattern="#,##0.00" maxFractionDigits="2"/>
										</c:if>
										</a>
									</td>
									</c:forEach>
								</tr>								
								</c:if>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>