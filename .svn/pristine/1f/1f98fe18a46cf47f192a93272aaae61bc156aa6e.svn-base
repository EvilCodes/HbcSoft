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
		<title>工作流业务绑定列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/workflowBusinessMap.js" charset="utf-8"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".table tr").slice(1).each(function(){
					var p = this;  
					$(this).children().slice(1).click(function(){  
						$($(p).children()[0]).children().each(function(){  
							if(this.type=="checkbox"){  
								if(!this.checked){  
									this.checked = true;  
								}else{  
									this.checked = false;  
								}
							}
						});
					});
				}); 
			});
		</script>
	</head>
	<body>
	<div class="container">
	<form:form id = "form" method="post" name="fm" action="${ctx}/workflow/businessMapShowDatePost.hbc" commandName="workflowBusinessMap" class="form-horizontal">
		<div class="row">
			<h3>工作流业务绑定列表</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "adddefine()">新增</a>
							<a href = "#" class="btn btn-default" onclick = "updatedefine()">修改</a>
							<a href = "#" class="btn btn-default" onclick = "deletedefine()">删除</a>
							<a href = "#" class="btn btn-default" onclick = "gopage('${page-1}')">上一页</a>
							<a href = "#" class="btn btn-default" onclick = "gopage('${page+1}')">下一页</a>
							<a href = "#" class="btn btn-default" onclick = "returnhome()">返回主页面</a>
							<a href = "#" class="btn btn-default" onclick = "searchBusiness()">查询</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">备注查询：</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input type ="text" name = "remark" value = "${remark}" class="form-control" placeholder="">
								<input type ="hidden" name = "page" id = "page" value = "${page}" />
							</div>
						</div>
						<table class="table table-bordered table-striped table-hover formtable noborder longtable" id="zxxlb">
							<col width="40px;">
							<col width="90px;">
							<col width="150px;">
							<col width="60px;">
							<col width="180px;">
							<thead>
								<tr>
									<th class="check"><input type = "checkbox" onclick = "checkAll(this)"></th>
									<th>工作流ID</th>
									<th>业务类型</th>
									<th>单据标识</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list!=null}">
									<c:forEach items="${list}" var ="d" varStatus="i">
										<tr>
											<td class="check"><input type = "checkbox" name = "checkbox" value ="${d.baseId}"></td>
											<td>${d.workflowId}</td>
											<td>${d.actionType}</td>
											<td>${d.flag}</td>
											<td>${d.remark}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</form:form>
	</div>
	</body>
</html>
