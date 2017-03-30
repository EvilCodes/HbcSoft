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
		<title>工作流节点表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/nodeWorkflowInput.js" charset="utf-8"></script>
		<script type="text/javascript">
		$(function (){
			$('#roleId').val("${workflowNode.roleId}");
			$('#deptId').val("${workflowNode.deptId}");
			$('#deptStatus').val("${workflowNode.deptStatus}");
			
			/* $('#deptId').val(); */
			
		});
		
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
			<h3>节点设置</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn btn-default" onclick="save()">保存</a>
							<a href = "#" class="btn btn-default" onclick = "window.location='${ctx}/workflow/showInput.hbc?wfId=${wfId}'">返回</a>
							<a href = "#" class="btn btn-default" onclick = "window.location=ctx+'/workflow/showData.hbc'">返回主页</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${ctx}/workflow/nodeAddData.hbc" id="fm" name="fm" commandName="workflowNode" class="form-horizontal">
						<input type="hidden" name="wfId" id="wfId" value="${wfId}"/>
						<input type="hidden" name="baseId" id="baseId" value="${workflowNode.baseId }"/>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">节点序号：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id="sort" name="sort" maxlength="100" value="${workflowNode.sort}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">节点名称：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id="name" name="name" maxlength="100" value="${workflowNode.name}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">部门状态：</label>
							<div class="col-md-8 col-sm-7">
								<select id = "deptStatus" name = "deptStatus" class="form-control">
									<option value="1">制单人部门</option>
									<option value="2">固定角色</option>
									<option value="3">主管院长</option>
									<option value="4">固定部门</option>
									<option value="5">预算单部门</option>
									<option value="6">联查预算部门</option>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">部门ID：</label>
							<div class="col-md-8 col-sm-7">
								<form:select id = "deptId" path="deptId" items="${orgs}" itemLabel="name" itemValue="id" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">角色ID：</label>
							<div class="col-md-8 col-sm-7">
								<form:select id = "roleId" path="roleId" items="${roles}" itemLabel="name" itemValue="id" class="form-control"></form:select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">会签办理人搜索规则：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" id="counterSignRule" name="counterSignRule" maxlength="100" value="${workflowNode.counterSignRule}" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否可用：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isEnable" value="1" <c:if test="${workflowNode.isEnable==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isEnable" value="0" <c:if test="${workflowNode.isEnable==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否允许办理：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isDepute" value="1" <c:if test="${workflowNode.isDepute==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isDepute" value="0" <c:if test="${workflowNode.isDepute==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否会签：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isCounterSign" value="1" <c:if test="${workflowNode.isCounterSign==1}">checked="checked"</c:if>/>是</label>
								<label class="checkbox-inline"><input type="radio" name="isCounterSign" value="0" <c:if test="${workflowNode.isCounterSign==0}">checked="checked"</c:if>/>否</label>
							</div>
						</div>
						
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否自动审核通过：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isAutoAdopt" value="1" <c:if test="${workflowNode.isAutoAdopt==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isAutoAdopt" value="0" <c:if test="${workflowNode.isAutoAdopt==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">跳过同一审核人：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isAutoSkip" value="1" <c:if test="${workflowNode.isAutoSkip==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isAutoSkip" value="0" <c:if test="${workflowNode.isAutoSkip==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否邮件提醒：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isEmail" value="1" <c:if test="${workflowNode.isEmail==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isEmail" value="0" <c:if test="${workflowNode.isEmail==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否短信提醒：</label>
							<div class="col-md-8 col-sm-7 ol-sm-6">
								<label class="checkbox-inline"><input type="radio" name="isSMS" value="1" <c:if test="${workflowNode.isSMS==1}">checked="checked"</c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isSMS" value="0" <c:if test="${workflowNode.isSMS==0}">checked="checked"</c:if> />否</label>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<h3>规则列表</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="captions">
						<h3 class="panel-title">规则列表</h3>
					</div>
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "addRule('${wfId}','${nId}')">新增</a>
							<a href = "#" class="btn btn-default" onclick = "updaterule('${wfId}','${workflowNode.baseId}')">修改</a>
							<a href = "#" class="btn btn-default" onclick = "deletes('${wfId}','${workflowNode.baseId}')">删除</a>
							<a href = "${ctx}/workflow/nodeShowInput.hbc?page=${page-1}&wfId=${wfId}&nId=${workflowNode.baseId}" class="btn btn-default">上一页</a>
							<a href = "${ctx}/workflow/nodeShowInput.hbc?page=${page+1}&wfId=${wfId}&nId=${workflowNode.baseId}" class="btn btn-default">下一页</a>
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
							<thead>
								<tr>
									<th><input type="checkbox" onclick="checkAll(this)"></th>
									<th>序号</th>
									<th>名称</th>
									<th>跳转节点ID</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list!=null}">
									<c:forEach items="${list}" var="d" varStatus="i">
										<tr>
											<td><input type="hidden" name="ruleId" value="${d.baseId}"> 
												<input type="checkbox" name="checkbox" value="${d.baseId}"></td>
											<td>${d.ruleSort }</td>
											<td>${d.ruleName }</td>
											<td>${d.toNodeId}</td>
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
