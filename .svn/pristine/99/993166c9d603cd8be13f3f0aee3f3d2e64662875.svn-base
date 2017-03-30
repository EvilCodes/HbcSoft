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
		<title>工作流管理配置</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow/workflowInput.js" charset="utf-8"></script>
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
		<div class="row">
			<h3>主信息页面</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "add()">确定</a>
							<a href = "#" class="btn btn-default" onclick = "flowReturn()">返回</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
					<form:form method="post" action="${ctx}/workflow/addData.hbc" name="fm" commandName="workflowDefine" class="form-horizontal">
						<input type = "hidden" name="baseId" id="baseId" value ="${workflowDefine.baseId}"/>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">流程名称：</label>
							<div class="col-md-8 col-sm-7">
								<input type ="text" name="name" id="name" maxlength="100" value ="${workflowDefine.name }" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">默认标题格式：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" name="defaultTitle" maxlength="100" value ="${workflowDefine.defaultTitle }" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5">快速审核规则：</label>
							<div class="col-md-8 col-sm-7">
								<input type="text" name="quickRule" maxlength="100" value ="${workflowDefine.quickRule }" class="form-control" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否自动生成标题：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isAutoTitle" value="1" <c:if test="${workflowDefine.isAutoTitle==1 }">checked="checked" </c:if> />是</label>
								<label class="checkbox-inline"><input type="radio" name="isAutoTitle" value="0" <c:if test="${workflowDefine.isAutoTitle==0 }">checked="checked" </c:if>/>否</label>
							</div>
						</div>
						<div class="clear"></div>
						
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否可用：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isEnable" value="1"  <c:if test="${workflowDefine.isEnable==1 }">checked="checked" </c:if>/>是</label>
								<label class="checkbox-inline"><input type="radio" name="isEnable" value="0" <c:if test="${workflowDefine.isEnable==0 }">checked="checked" </c:if> />否</label>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否提供跟按钮：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isTrcakForm" value="1" <c:if test="${workflowDefine.isTrcakForm==1 }">checked="checked" </c:if>/>是</label>
								<label class="checkbox-inline"><input type="radio" name="isTrcakForm" value="0" <c:if test="${workflowDefine.isTrcakForm==0 }">checked="checked" </c:if>/>否</label>
							</div>
						</div>
						<div class="clear"></div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-4 col-sm-5 col-xs-6">是否快速审核：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isQuick" value="1" <c:if test="${workflowDefine.isQuick==1 }">checked="checked" </c:if>/>是</label>
								<label class="checkbox-inline"><input type="radio" name="isQuick" value="0" <c:if test="${workflowDefine.isQuick==0 }">checked="checked" </c:if>/>否</label>
							</div>
						</div>
						<div class="clear"></div>
						<div class="form-group">
							<label for="drive" class="control-label col-md-3 col-sm-5 col-xs-6">下一节点办理人多时，是否弹出选择框：</label>
							<div class="col-md-8 col-sm-7 ol-sm-7">
								<label class="checkbox-inline"><input type="radio" name="isSelNextUser" value="1" <c:if test="${workflowDefine.isSelNextUser==1 }">checked="checked" </c:if>/>是</label>
								<label class="checkbox-inline"><input type="radio" name="isSelNextUser" value="0" <c:if test="${workflowDefine.isSelNextUser==0 }">checked="checked" </c:if>/>否</label>
							</div>
						</div>
						<input type="hidden" name="isArchives" value="1" <c:if test="${workflowDefine.isArchives==1 }">checked="checked" </c:if>/>
						<input type="hidden" name="isArchives" value="0" <c:if test="${workflowDefine.isArchives==0 }">checked="checked" </c:if>/>
						<div class="clear"></div>
					</form:form>
				</div>
			</div>
			<h3>工作流节点列表</h3>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="captions">
						<h3 class="panel-title">工作流节点列表</h3>
					</div>
					<div class="actions">
						<div class="btn-group">
							<a href = "#" class="btn btn-default" onclick = "addNode('${wfId}')">新增</a>
							<a href = "#" class="btn btn-default" onclick = "updateNode('${wfId}')">修改</a>
							<a href = "#" class="btn btn-default" onclick = "delNode('${wfId}')">删除</a>
							<a href = "${ctx}/workflow/showInput.hbc?page=${page-1}&wfId=${wfId}" class="btn btn-default">上一页</a>
							<a href = "${ctx}/workflow/showInput.hbc?page=${page+1}&wfId=${wfId}" class="btn btn-default">下一页</a>
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
							<col width="180px;">
							<col width="120px;">
							<col width="120px;">
							<col width="120px;">
							<col width="120px;">
							<col width="150px;">
							<col width="160px;">
							<col width="120px;">
							<col width="180px;">
							<thead>
								<tr>
									<th>
										<input type ="checkbox" onclick = "checkAll(this)">
									</th>
									<th>节点序号</th>
									<th>节点名称</th>
									<th>是否可用</th>
									<th>是否会签</th>
									<th>会签规则</th>
									<th>是否自动审核</th>
									<th>是否允许办理</th>
									<th>是否邮件提醒</th>
									<th>是否短信提醒</th>
									<th>同一审核人自动跳过</th>
									<th>流程Id</th>
									<th>部门状态</th>
									<th>办理角色id</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list!=null}">
									<c:forEach items="${list}" var ="d" varStatus="i">
										<tr>
											<td>
												<input type = "checkbox" name = "checkbox" value ="${d.baseId}">
											</td>
											<td>${d.sort}</td>
											<td>${d.name}</td>
											<td>
												<c:choose>
													<c:when test="${d.isEnable eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${d.isCounterSign eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>${d.counterSignRule}</td>
											<td>
												<c:choose>
													<c:when test="${d.isAutoAdopt eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${d.isDepute eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${d.isEmail eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${d.isSMS eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${d.isAutoSkip eq 1}">是</c:when>
													<c:otherwise>否</c:otherwise>
												</c:choose>
											</td>
											<td>${d.wfId}</td>
											<td>
												<c:if test="${d.deptStatus eq 1}">
													制单人部门
												</c:if>
												<c:if test="${d.deptStatus eq 2}">
													固定角色
												</c:if>
												<c:if test="${d.deptStatus eq 3}">
													主管院长
												</c:if>
												<c:if test="${d.deptStatus eq 4}">
													固定部门
												</c:if>
												<c:if test="${d.deptStatus eq 5}">
													预算单部门
												</c:if>
												<c:if test="${d.deptStatus eq 6}">
													联查预算部门
												</c:if>
												
											</td>
											<td>
												<c:if test="${fn:length(d.roleId)>=10}">
													${fn:substring(d.roleId,'0','10')}...
												</c:if>
												<c:if test="${fn:length(d.roleId)<10}">
													${d.roleId }
												</c:if>
											</td>
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
