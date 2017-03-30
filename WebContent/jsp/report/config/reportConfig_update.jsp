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
<title>修改数据库表名</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/fileinput.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/fileinput.js"></script>
<script type="text/javascript">
	//全选按钮
	function checkAll(obj,checkName){
		var checkboxs = document.getElementsByName(checkName);
		for(var i = 0; i<checkboxs.length; i++ ){
			if(obj.checked == true){
				checkboxs[i].checked = true;
			}else{
				checkboxs[i].checked = false;
			}
		}
	}
	
	//表头表体配置是否可修改
	function test(typeid,defaultid1,defaultid2,defaultid3){
		var inputType = $("#"+typeid).val();
		if(inputType == '1'){
			$("#"+defaultid1).removeAttr("readonly");
			$("#"+defaultid2).removeAttr("readonly");
			$("#"+defaultid3).removeAttr("readonly");
		}else{
			$("#"+defaultid1).attr("readonly","readonly");
			document.getElementById(defaultid1).value="";
			$("#"+defaultid2).attr("readonly","readonly");
			document.getElementById(defaultid2).value="";
			$("#"+defaultid3).attr("readonly","readonly");
			document.getElementById(defaultid3).value="";
		}
	}
	//配置格式校验
	function configFormat(){
		var headConFor = document.getElementById("headConfig");
		var headconfig = headConFor.value;
		var headType = $("#headType").val();
		var bodyConFor = document.getElementById("bodyConfig");
		var bodyconfig = bodyConFor.value;
		var bodyType = $("#bodyType").val();
		if(headType == 1){
			if(headconfig.substring(0,2)=="H_" || headconfig.substring(0,2)=="B_" || headconfig.substring(0,2)=="C_" || headconfig==""){
				return true;
			}else{
				alert("请按照配置规则填写");
				headConFor.focus();
				headConFor.select();
			}
		} 
		if(bodyType == 1){
			if(bodyconfig.substring(0,2)=="H_" || bodyconfig.substring(0,2)=="B_" || bodyconfig.substring(0,2)=="C_" || bodyconfig==""){
				return true;
			}else{
				alert("请按照配置规则填写");
				bodyConFor.focus();
				bodyConFor.select();
			}
		} 
	}
	//提交校验
	function TiJiao(){
		var reportName = $("#reportName").val();
		var reportType = $("#reportType").val();
		var headType = $("#headType").val();
		var headConfig = $("#headConfig").val();
		var headTableName = $("#headTableName").val();
		var headFieldName = $("#headFieldName").val();
		var bodyType = $("#bodyType").val();
		var bodyConfig = $("#bodyConfig").val();
		var bodyTableName = $("#bodyTableName").val();
		var bodyFieldName = $("#bodyFieldName").val();
		var dataConfig = $("#dataConfig").val();
		var dataConfig = $("#dataConfig").val();
		var reportSQL = $("#reportSQL").val();
		var startRow = $("#startRow").val();
		var startRow = $("#startRow").val();
		var startColumn = $("#startColumn").val();
		var startReg = /^[0-9]*$/;
		var specialRow = $("#specialRow").val();
		var specialColumn = $("#specialColumn").val();
		var specialReg = /^[0-9,]+$/;
		var mainTitleStartRow = $("#mainTitleStartRow").val();
		var mainTitleStartColumn = $("#mainTitleStartColumn").val();
		var subtitleStartRow = $("#subtitleStartRow").val();
		var subtitleStartColumn = $("#subtitleStartColumn").val();
		if(reportName == ''){
			alert("报表名称不能为空！");
			return false;
		}else if(reportType == ''){
			alert("报表类型不能为空！");
			return false;
		}else if(headType == 1 && headConfig.substring(0,2)!="H_" && headConfig.substring(0,2)!="B_" && headConfig.substring(0,2)!="C_"){
			alert("表头配置填写不正确，请修改！");
			return false;
		}else if(bodyType == 1 && bodyConfig.substring(0,2)!="H_" && bodyConfig.substring(0,2)!="B_" && bodyConfig.substring(0,2)!="C_"){
			alert("表体配置填写不正确，请修改！");
			return false;
		}else if(headType == 1 && headTableName==''){
			alert("表头类型为动态时，请输入表头表名");
			return false;
		}else if(headType == 1 && headFieldName==''){
			alert("表头类型为固定时，请输入表头字段名");
			return false;
		}else if(bodyType == 1 && bodyTableName==''){
			alert("表体类型为动态时，请输入表体表名");
			return false;
		}else if(bodyType == 1 && bodyFieldName==''){
			alert("表体类型为动态时，请输入表体字段名");
		}else if(dataConfig == 0 && reportSQL == ''){
			alert("请填写基础sql！");
			return false;
		}else if(mainTitleStartRow == ''){
			alert("主标题起始行不能为空！");
			return false;
		}else if(!startReg.test(mainTitleStartRow)){
			alert("主标题起始行请输入数字！");
			return false;
		}else if (mainTitleStartColumn == ''){
			alert("主标题起始列不能为空！");
			return false;
		}else if(!startReg.test(mainTitleStartColumn)){
			alert("主标题起始列请输入数字！");
			return false;
		}else if(subtitleStartRow == ''){
			alert("副标题起始行不能为空！");
			return false;
		}else if(!startReg.test(subtitleStartRow)){
			alert("副标题起始行请输入数字！");
			return false;
		}else if(subtitleStartColumn == ''){
			alert("副标题起始列不能为空！");
			return false;
		}else if(!startReg.test(subtitleStartColumn)){
			alert("副标题起始列请输入数字！");
			return false;
		}else if(startRow == ''){
			alert("起始行不能为空！");
			return false;
		}else if(!startReg.test(startRow)){
			alert("请输入数字");
			return false;
		}else if(startColumn == ''){
			alert("起始列不能为空！");
			return false;
		}else if(!startReg.test(startColumn)){
			alert("请输入数字！");
			return false;
		}else{
			$("#form").submit();
		}
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
<style>
.file-preview {
			display: none;
		}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>报表配置修改信息</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
					action="${pageContext.request.contextPath}/reportConfig/updateReportConfig.hbc" method="post" enctype="multipart/form-data">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">报表配置信息</h3>
						</div>
						<div class="actions">
							<button type="button" onclick="TiJiao()" class="btn btn-default icons">保存</button>
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">报表名称</label>
							<div class="col-md-10 col-sm-8">
								<input type="hidden" class="form-control" name="reportId" id="reportId" value="${repCon.id}">
								<input type="text" class="form-control" name="reportName" id="reportName" value="${repCon.reportName}">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">报表类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="reportType" name="reportType">
									<option value="财务报表" <c:if test="${repCon.reportType=='财务报表'}">selected</c:if>>财务报表</option>
								</select>
								<%-- <input type="text" class="form-control" placeholder="报表类型" name="reportType" id="reportType" value="${repCon.reportType}"> --%>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="headType" name="headType" onchange="test('headType','headConfig','headTableName','headFieldName')">
									<option value="0" <c:if test="${repCon.headType=='0' }">selected</c:if>>固定</option>
									<option value="1" <c:if test="${repCon.headType=='1' }">selected</c:if>>动态</option>
								</select>
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头配置 </label>
							<div class="input-group col-md-10 col-sm-8" id="headConfigDianXuan" style="padding-left: 15px;padding-right: 15px;">
								<c:if test="${repCon.headType=='0' }">
									<input type="text" class="form-control" onblur="configFormat()" name="headConfig" id="headConfig" value="${repCon.headConfig}" readonly>
								</c:if>
								<c:if test="${repCon.headType=='1' }">
									<input type="text" class="form-control" onblur="configFormat()" name="headConfig" id="headConfig" value="${repCon.headConfig}" >
								</c:if>
								<span class="input-group-addon" data-toggle="modal" data-target="#dianxuan"><span class="glyphicon glyphicon-zoom-in"></span></span>
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
													当表头类型为动态时，表头配置是数据字典时：如字典类型为职务时，职务的编码为duty，取职务的编码，表头为“H_字典编码”,如："H_duty"</br>
													当表头类型为动态时，表头配置是系统管理时：配置部门为：“B_BUMEN”；配置人员为：“B_RENYUAN”；配置角色为：“B_JUESE”</br>
													当表头类型为动态时，表头配置是常量时：配置年度为：“C_NIAN”；配置月份为：“C_YUE”；配置日为：“C_RI”</br>
													当表头类型为固定时，表头配置不填写。
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
								<c:if test="${repCon.headType=='0' }">
									<input type="text" class="form-control" name="headTableName" id="headTableName" value="${repCon.headTableName}" readonly>
								</c:if>
								<c:if test="${repCon.headType=='1' }">
									<input type="text" class="form-control" name="headTableName" id="headTableName" value="${repCon.headTableName}">
								</c:if>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表头字段名</label>
							<div class="col-md-10 col-sm-8">
								<c:if test="${repCon.headType=='0' }">
									<input type="text" class="form-control" name="headFieldName" id="headFieldName" value="${repCon.headFieldName}" readonly>
								</c:if>
								<c:if test="${repCon.headType=='1' }">
									<input type="text" class="form-control" name="headFieldName" id="headFieldName" value="${repCon.headFieldName}">
								</c:if>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体类型</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="bodyType" name="bodyType" onchange="test('bodyType','bodyConfig','bodyTableName','bodyFieldName')">
									<option value="0" <c:if test="${repCon.bodyType=='0' }">selected</c:if>>固定</option>
									<option value="1" <c:if test="${repCon.bodyType=='1' }">selected</c:if>>动态</option>
								</select>
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体配置 </label>
							<div class="input-group col-md-10 col-sm-8 " id="bodyConfigDianXuan" style="padding-left: 15px;padding-right: 15px;">
								<c:if test="${repCon.bodyType=='0' }">
									<input type="text" class="form-control" onblur="configFormat()" name="bodyConfig" id="bodyConfig" value="${repCon.bodyConfig}" readonly>
								</c:if>
								<c:if test="${repCon.bodyType=='1' }">
									<input type="text" class="form-control" onblur="configFormat()" name="bodyConfig" id="bodyConfig" value="${repCon.bodyConfig}">
								</c:if>
								<span class="input-group-addon" data-toggle="modal" data-target="#bdianxuan"><span class="glyphicon glyphicon-zoom-in"></span></span>
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
													当表体类型为动态时，表体配置是数据字典时：如字典类型为职务时，职务的编码为duty，取职务的编码，表体为“H_字典编码”,如："H_duty"</br>
													当表体类型为动态时，表体配置是系统管理时：配置部门为：“B_BUMEN”；配置人员为：“B_RENYUAN”；配置角色为：“B_JUESE”</br>
													当表体类型为动态时，表体配置是常量时：配置年度为：“C_NIAN”；配置月份为：“C_YUE”；配置日为：“C_RI”</br>
													当表体类型为固定时，表体配置不填写
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
								<c:if test="${repCon.bodyType=='0' }">
									<input type="text" class="form-control" name="bodyTableName" id="bodyTableName" value="${repCon.bodyTableName}" readonly>
								</c:if>
								<c:if test="${repCon.bodyType=='1' }">
									<input type="text" class="form-control" name="bodyTableName" id="bodyTableName" value="${repCon.bodyTableName}">
								</c:if>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">表体字段名</label>
							<div class="col-md-10 col-sm-8">
								<c:if test="${repCon.bodyType=='0' }">
									<input type="text" class="form-control" name="bodyFieldName" id="bodyFieldName" value="${repCon.bodyFieldName}" readonly>
								</c:if>
								<c:if test="${repCon.bodyType=='1' }">
									<input type="text" class="form-control" name="bodyFieldName" id="bodyFieldName" value="${repCon.bodyFieldName}">
								</c:if>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">数据配置</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="dataConfig" name="dataConfig" onchange="modifyDiv()">
									<c:forEach items="${list}" var="tNavMenu">
										<option value="${tNavMenu.dictCode}"  <c:if test="${repCon.dataConfig==tNavMenu.dictCode}">selected</c:if>>${tNavMenu.dictName}</option>
									</c:forEach>
							<%--    <option value="0" <c:if test="${repCon.dataConfig=='0' }">selected</c:if>>基础sql</option>
									<option value="1" <c:if test="${repCon.dataConfig=='1' }">selected</c:if>>行公共</option>
									<option value="2" <c:if test="${repCon.dataConfig=='2' }">selected</c:if>>列公共</option>
									<option value="3" <c:if test="${repCon.dataConfig=='3' }">selected</c:if>>特殊行/列</option> --%>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4" id="typeName">基础SQL</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="reportSQL" id="reportSQL" value="${repCon.reportSQL}">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">数据源配置</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="dataSourceId" name="dataSourceId">
									<c:forEach items="${dbLinkParaList}" var="dbLinkPara">
										<option <c:if test="${repCon.dataSourceId==dbLinkPara.id}">selected</c:if>  value="${dbLinkPara.id}">${dbLinkPara.dbAccount}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">路径</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" name="reportUrl" id="reportUrl" value="${repCon.reportUrl}" readonly>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">主标题起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="主标题起始行" name="mainTitleStartRow" id="mainTitleStartRow" value="${repCon.mainTitleStartRow }">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">主标题起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="主标题起始列" name="mainTitleStartColumn" id="mainTitleStartColumn" value="${repCon.mainTitleStartColumn }">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">副标题起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="副标题起始行" name="subtitleStartRow" id="subtitleStartRow" value="${repCon.subtitleStartRow }">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">副标题起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="副标题起始列" name="subtitleStartColumn" id="subtitleStartColumn" value="${repCon.subtitleStartColumn }">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">起始行</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="起始行" name="startRow" id="startRow" value="${repCon.startRow}">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">起始列</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="起始列" name="startColumn" id="startColumn" value="${repCon.startColumn}">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">备注</label>
							<div class="col-md-10 col-sm-8">
								<input type="text" class="form-control" placeholder="备注" name="remark" id="remark" value="${repCon.remark}">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">当前模版</label>
							<div class="col-md-10 col-sm-8">
    							<input type="text" value="${repCon.oldTempleName}" class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">模版上传</label>
							<div class="col-md-10 col-sm-8">
    							<input id="file" name="file"  type="file" class="file" multiple data-show-upload="false" data-show-caption="true">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>