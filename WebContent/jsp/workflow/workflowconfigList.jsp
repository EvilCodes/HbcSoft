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
		<title>工作流基本配置</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/workflowConfig.js" charset="utf-8"></script>
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
	<form:form id = "form" method="post" action="${pageContext.request.contextPath}/workflow/configShowPost.hbc" name = "fm"  commandName="data" class="form-horizontal">
		<div class="row">
			<h3>工作流基本配置</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "addConfig()">新增</a>
							<a href = "#" class="btn btn-default" onclick = "updateConfig()">修改</a>
							<a href = "#" class="btn btn-default" onclick = "deleteConfig()">删除</a>
							<a href = "#" class="btn btn-default" onclick = "gopage('${page-1}')">上一页</a>
							<a href = "#" class="btn btn-default" onclick = "gopage('${page+1}')">下一页</a>
							<a href = "#" class="btn btn-default" onclick = "home()">返回主页面</a>
							<a href = "#" class="btn btn-default" onclick = "searchConfig()">查询</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">表中文名：</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input type = "tableName" name = "tableName" value = "${tableName}" class="form-control" placeholder="">
								<input type ="hidden" name = "page" id = "page" value = "${page}" />
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">列中文名：</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input type = "columnName" name = "columnName" value = "${columnName}" class="form-control" placeholder="">
							</div>
						</div>
						<table class="table table-bordered table-striped table-hover formtable noborder longtable" id="zxxlb">
							<col width="40px;">
							<col width="60px;">
							<col width="90px;">
							<col width="150px;">
							<col width="180px;">
							<col width="180px;">
							<thead>
								<tr>
									<th><input type = "checkbox" onclick = "checkAll(this)"></th>
									<th>序号</th>
									<th>表中文名</th>
									<th>表英文名</th>
									<th>字段中文名</th>
									<th>字段英文名</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list!=null}">
									<c:forEach items="${list}" var ="d" varStatus="i">
										<tr>
											<td>
												<input type = "checkbox" name = "checkbox" value ="${d.baseId}">
											</td>
											<td>${i.index+1}</td>
											<td>${d.tableName}</td>
											<td>${d.tableId}</td>
											<td>${d.columnName}</td>
											<td>${d.columnId}</td>
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
