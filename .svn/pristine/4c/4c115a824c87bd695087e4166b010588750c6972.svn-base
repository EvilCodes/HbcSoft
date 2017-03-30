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
		<title>工作流规则管理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/ruleWorkflowInput.js" charset="utf-8"></script>
		
		<%-- <script type="text/javascript" src="${ctx}/dwr/engine.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/dwr/util.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/dwr/interface/queryWorkflowConfig.js" charset="utf-8"></script> --%>
<script type="text/javascript">
$(function (){
	$('#toNodeId').val('${workflowNodeRule.toNodeId}');
});
function dealcolumn(){
	var data = $('#tableId').val();
	DWREngine.setAsync(false);
	queryWorkflowConfig.queryDate(data,callQueryExpenseInfo);
	//$('#columnId').val('${workflowNodeRuleD.columnId}');
}
function updatecolumn(){
	var data = $('#tableId').val();
	DWREngine.setAsync(false);
	queryWorkflowConfig.queryDate(data,callQueryExpenseInfo);
	$('#columnId').val($('#tempcolumn').val());
	$('#ruleType').val($('#tempruleType').val());
}
function callQueryExpenseInfo(data){
	$('#columnId').empty();
	$.each(data,function(i,item){
		$('#columnId').append('<option value="'+item.columnId+'">'+item.columnName+'</option>');
	});
	
}

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
		<div class="row">
			<h3>规则设置</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "save()">保存</a>
							<a href = "#" class="btn btn-default" onclick="window.location='${ctx}/workflow/nodeShowInput.hbc?wfId=${wfId}&nId=${nId}'">返回</a>
							<a href = "#" class="btn btn-default" onclick = "window.location=ctx+'/workflow/showData.hbc'">返回主页</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${ctx}/workflow/nodeRuleAdd.hbc" id="fm" name="fm" commandName="workflowNodeRule" class="form-horizontal">
						<input type="hidden" name="ruleId" id="ruleId" value="${workflowNodeRule.baseId }" />
						<input type="hidden" name="wfId" id="wfId" value="${wfId}" />
						<input type="hidden" name="nId" id="nId" value="${nId}" />
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">规则序号：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id="ruleSort" name="ruleSort" maxlength="100" value="${workflowNodeRule.ruleSort}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">规则名称：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id="ruleName" name="ruleName" maxlength="100" value="${workflowNodeRule.ruleName}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">跳转节点ID：</label>
							<div class="col-md-8 col-sm-7">
								<form:select id = "toNodeId" path="toNodeId" items="${nodes}" itemLabel="name" itemValue="baseId" class="form-control"></form:select>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<h3>子规则列表</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="captions">
						<h3 class="panel-title">子规则列表</h3>
					</div>
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "addRuleD('0','${wfId}','${nId}','${ruleId}')">新增</a>
							<a href = "#" class="btn btn-default" onclick = "addRuleD('1','${wfId}','${nId}','${ruleId}')">修改</a>
							<a href = "#" class="btn btn-default" onclick = "deletes('${ruleId}','${wfId}','${nId}')">删除</a>
							<a href = "${ctx}/workflow/nodeRuleInput.hbc?page=${page-1}&ruleId=${ruleId}&wfId=${wfId}&nId=${nId}" class="btn btn-default">上一页</a>
							<a href = "${ctx}/workflow/nodeRuleInput.hbc?page=${page+1}&ruleId=${ruleId}&wfId=${wfId}&nId=${nId}" class="btn btn-default">下一页</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-hover formtable noborder longtable" id="gzljdlb">
							<col width="40px;">
							<col width="90px;">
							<col width="120px;">
							<col width="90px;">
							<col width="90px;">
							<col width="120px;">
							<col width="90px;">
							<thead>
								<tr>
									<th><input type ="checkbox" onclick = "checkAll(this)"></th>
									<th>实体标题:</th>
									<th>实体名称</th>
									<th>字段标题</th>
									<th>字段名称</th>
									<th>规则类型</th>
									<th>规则的值</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list!=null}">
									<c:forEach items="${list}" var="d" varStatus="i">
										<tr>
											<td>
												<input type="hidden" name = "tempId" value="${d.baseId}"/>
												<input type ="checkbox" name = "checkbox" value ="${d.baseId}">
											</td>
											<td>${d.tableName}</td>
											<td>${d.tableId}</td>
											<td>${d.columnName}</td>
											<td>${d.columnId}</td>
											<td>${d.ruleType}</td>
											<td>${d.ruleValue}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
