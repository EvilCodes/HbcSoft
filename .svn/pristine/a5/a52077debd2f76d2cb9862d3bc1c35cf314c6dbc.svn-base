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
		<title>工作流节点规则新增页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript">
			function saveRule(){
				$("#form").submit();
			}
			$(function (){
				var columnId = '${workflowNodeRuleD.columnId}';
				$('#tableId').val('${workflowNodeRuleD.tableId}');
				$('#columnId').val('${workflowNodeRuleD.columnId}');
				$('#ruleType').val('${workflowNodeRuleD.ruleType}');
				$.ajax({
					type: 'post',
					data: 'json',
					async:true,
					url: '${pageContext.request.contextPath}/workflow/queryDate.hbc?tableId='+$('#tableId').val(),
					success: function(msg){
						//$('#columnId').empty();
						var ttt = eval("("+msg+")");
						for(var i=0; i<ttt.listColumnId.length; i++){
							var tt = ttt.listColumnId[i];
							if(columnId == tt.columnId){
								$('#columnId').append('<option value="'+tt.columnId+'" selected>'+tt.columnName+'</option>');
							}else{
								$('#columnId').append('<option value="'+tt.columnId+'">'+tt.columnName+'</option>');
							}
						}
					}
				});
			});
			function dealcolumn(){
				$.ajax({
					type: 'post',
					data: 'json',
					async:true,
					url: '${pageContext.request.contextPath}/workflow/queryDate.hbc?tableId='+$('#tableId').val(),
					success: function(msg){
						$('#columnId').empty();
						var ttt = eval("("+msg+")");
						for(var i=0; i<ttt.listColumnId.length; i++){
							var tt = ttt.listColumnId[i];
							if(columnId == tt.columnId){
								$('#columnId').append('<option value="'+tt.columnId+'" selected>'+tt.columnName+'</option>');
							}else{
								$('#columnId').append('<option value="'+tt.columnId+'">'+tt.columnName+'</option>');
							}
						}
					}
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
			<h3>规则子表设置</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "saveRule()">保存</a>
							<a href = "#" class="btn btn-default" onclick="window.location='${ctx}/workflow/nodeRuleInput.hbc?wfId=${wfId}&nId=${nId}&ruleId=${ruleId }'">返回</a>
							<a href = "#" class="btn btn-default" onclick = "window.location=ctx+'/workflow/showData.hbc'">返回主页</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${ctx}/workflow/nodeRuleDetailAdd.hbc" id="form" name="form" commandName="workflowNodeRuleD" class="form-horizontal">
						<input type = "hidden" name="ruleDId" id="ruleDId" value = "${workflowNodeRuleD.baseId}"/>
						<input type = "hidden" name="ruleId" id="ruleId" value = "${ruleId}"/>
						<input type = "hidden" name="wfId" id="wfId" value = "${wfId}"/>
						<input type = "hidden" name="nId" id="nId" value = "${nId}"/>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">实体标题：</label>
							<div class="col-md-8 col-sm-7">
								<input type = "hidden" id = "temptable" value = "${workflowNodeRuleD.tableId}">
								<input type = "hidden" id = "tempcolumn" value = "${workflowNodeRuleD.columnId}">
								<input type = "hidden" id = "tempruleType" value = "${workflowNodeRuleD.ruleType}">
								<form:select id = "tableId" path="tableId" items="${list}" itemLabel="tableName" itemValue="tableId" onchange = "dealcolumn();" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">字段标题：</label>
							<div class="col-md-8 col-sm-7">
								<select id = "columnId" name = "columnId" class="form-control"></select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">规则类型：</label>
							<div class="col-md-8 col-sm-7">
								<select id="ruleType" name = "ruleType" class="form-control">
									<option value = ">">大于</option>
									<option value = ">=">大于等于</option>
									<option value = "=">等于</option>
									<option value = "<>">不等于</option>
									<option value = "<=">小于等于</option>
									<option value = "<">小于</option>
									<option value = "like">模糊查询（自带百分号）</option>
									<option value = "in">in查询（自带括号）</option>
									<option value = "not in"> not in查询（自带括号）</option>
									<option value = "is"> is查询</option>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">规则的值：</label>
							<div class="col-md-8 col-sm-7">
								<input type='text' name="ruleValue" value="${workflowNodeRuleD.ruleValue}" class="form-control" placeholder=""/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>
