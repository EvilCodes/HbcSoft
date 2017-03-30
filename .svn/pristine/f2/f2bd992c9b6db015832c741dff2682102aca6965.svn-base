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
<title>报表配置查询信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	function back(){
		window.location.href='${pageContext.request.contextPath}/reportConfig/queryAllReportConfigs.hbc';
	}
	function seeFields(){
		$("#form").removeAttr("disabled");
		$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/showFormField.hbc");
		$("#form").submit();
	}
	function modifyDiv(){
		var optionName= document.getElementById("dataConfig")
		var txt = optionName.options[optionName.selectedIndex].text;
		if(txt=="特殊格式"){
			$("label#typeName").html("存储过程名称");
		}else{
			$("label#typeName").html(txt);
		}
	}
	$(document).ready(function(){
		modifyDiv();
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>报表配置查询信息</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
					action="" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="captions">
							<h3 class="panel-title">报表配置信息</h3>
						</div>
						<div class="actions">
						    <button type="button" class="btn btn-default" onclick="seeFields()">查看字段信息</button>
							<button type="button" class="btn btn-default" onclick="back()">返回</button>
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">报表名称</label>
							<div class="col-md-10 col-sm-8">
							    <input type="hidden" class="form-control" name="reportId" id="reportId" value="${config.id}">
								<input type="text" class="form-control" name="reportName" id="reportName" value="${config.reportName}"  readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">报表类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="reportType" name="reportType" disabled="disabled">
									<option value="财务报表" <c:if test="${config.reportType=='财务报表' }">selected</c:if>>财务报表</option>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="headType" name="headType"  disabled="disabled">
									<option value="0" <c:if test="${config.headType=='0' }">selected</c:if>>固定</option>
									<option value="1" <c:if test="${config.headType=='1' }">selected</c:if>>动态</option>
								</select>
							</div>
						</div>
							<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头配置</label>
							<div class="input-group col-md-10 col-sm-8 "  id="headConfigDianXuan" style="padding-left: 15px;padding-right: 15px;">
								<input type="text" class="form-control" onblur="configFormat()" name = "headConfig" id="headConfig" value="${config.headConfig}"  readonly="readonly">
								<span class="input-group-addon"  data-toggle="modal" data-target="#dianxuan"><span class="glyphicon glyphicon-zoom-in"></span></span>
							</div>
							<div class="modal fade" id="dianxuan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog headConfigDianXuan">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">表头配置规则</h4>
										</div>
										<div class="modal-body dianxuanbody">
											<div class="row">
												<div class="table-responsive">
													当表头类型为动态时，表头配置是数据字典时：表头以“H_”开头，如："H_XXX"<br>
													当表头类型为动态时，表头配置是系统管理时：表头以“B_”开头，如："B_XXX"<br>
													当表头类型为动态时，表头配置是常量时：表头以“C_”开头，如："C_XXX"<br>
												</div>
											</div>
										</div>
							 			<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头表名</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="headTableName" id="headTableName" value="${config.headTableName}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头字段名</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="headFieldName" id="headFieldName" value="${config.headFieldName}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="bodyType" name="bodyType"  disabled="disabled">
									<option value="0" <c:if test="${config.bodyType=='0' }">selected</c:if>>固定</option>
									<option value="1" <c:if test="${config.bodyType=='1' }">selected</c:if>>动态</option>
								</select>
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体配置</label>
							 <div class="input-group col-md-10 col-sm-8 "  id="bodyConfigDianXuan" style="padding-left: 15px;padding-right: 15px;">
								<input type="text" class="form-control" onblur="configFormat()" id="bodyConfig" name="bodyConfig" value="${config.bodyConfig}" readonly="readonly">
								<span class="input-group-addon"  data-toggle="modal" data-target="#bdianxuan"><span class="glyphicon glyphicon-zoom-in"></span></span>
							</div>
							<div class="modal fade" id="bdianxuan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog bodyConfigDianXuan">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">表体配置规则</h4>
										</div>
										<div class="modal-body dianxuanbody">
											<div class="row">
												<div class="table-responsive">
													当表体类型为动态时，表头配置是数据字典时：表头以“H_”开头，如："H_XXX"<br>
													当表体类型为动态时，表头配置是系统管理时：表头以“B_”开头，如："B_XXX"<br>
													当表体类型为动态时，表头配置是常量时：表头以“C_”开头，如："C_XXX"<br>
												</div>
											</div>
										</div>
							 			<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体表名</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="bodyTableName" id="bodyTableName" value="${config.bodyTableName}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体字段名</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="bodyFieldName" id="bodyFieldName" value="${config.bodyFieldName}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">数据配置</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="dataConfig" name="dataConfig"  disabled="disabled" onchange="modifyDiv()">
									<%-- <option value="0" <c:if test="${config.dataConfig=='0' }">selected</c:if>>基础sql</option>
									<option value="1" <c:if test="${config.dataConfig=='1' }">selected</c:if>>特殊行/列</option> --%>
									<c:forEach items="${list}" var="tNavMenu">
										<option value="${tNavMenu.dictCode}"  <c:if test="${config.dataConfig==tNavMenu.dictCode}">selected</c:if>>${tNavMenu.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4" id="typeName">基础SQL</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="reportSQL" 
								id="reportSQL" value = "${config.reportSQL }"  readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">数据源配置</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="dataSourceId" id="dataSourceId" 
								value="${outerDBLinkPara.dbAccount}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">路径</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="reportUrl" id="reportUrl" 
								value="${config.reportUrl}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">主标题起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="主标题起始行" name="mainTitleStartRow" 
								id="mainTitleStartRow" value="${config.mainTitleStartRow }" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">主标题起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="主标题起始列" name="mainTitleStartColumn" 
								id="mainTitleStartColumn" value="${config.mainTitleStartColumn }" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">副标题起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="副标题起始行" name="subtitleStartRow" 
								id="subtitleStartRow" value="${config.subtitleStartRow }" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">副标题起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="副标题起始列" name="subtitleStartColumn" 
								id="subtitleStartColumn" value="${config.subtitleStartColumn }" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="起始行" name="startRow" 
								id="startRow" value = "${config.startRow}"  readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="起始列" name="startColumn" 
								id="startColumn" value = "${config.startColumn}"  readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">备注</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="备注" name="remark" id="remark" 
								value = "${config.remark }"  readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">当前模版</label>
							<div class="col-md-10 col-sm-8">
    							<input type="text" value="${config.oldTempleName}" class="form-control" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>