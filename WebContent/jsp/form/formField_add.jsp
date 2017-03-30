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
<title>新增表单字段表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
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
function moveChange(moveFlag){
	var xzid=$("input[name='rowid']:checkbox:checked").size(); //document.getElementsByName("rowid").length;//选择ID
	var zhTai = 0;
	if(xzid==0){
		alert("请先选择一条记录再进行上移操作！");
		return;
	}
	if(xzid>1){
		alert("只能选择一条数据进行上移！");
		return;
	}
	if(xzid==1){
		var idStr=$("input[name='rowid']:checkbox:checked").attr("id");
		var flag = true;
		var sum = $("input[name='fieldId']").size();
		$("input[name='title']").each(function(){
			if($(this).val()==""){
				alert("标题不能为空");
				flag = false;
				return false;
			}
		});
		if(moveFlag=='1'){
			if(idStr == "1"){
				alert("不能上移");
				flag = false;
				return;
			}
			var idStrPre=idStr-1;
			var currSortValue=$('#sort'+idStr).val();
			var preSortValue=$('#sort'+idStrPre).val();
			$('#sort'+idStr).val(preSortValue);
			$('#sort'+idStrPre).val(currSortValue)			
		}else if(moveFlag=='2'){
			if(idStr == sum){
				alert("不能下移");
				flag = false;
				return
			}
			var idStrNext=idStr-0+1;
			var currSortValue=$('#sort'+idStr).val();
			var nextSortValue=$('#sort'+idStrNext).val();
			$('#sort'+idStr).val(nextSortValue);
			$('#sort'+idStrNext).val(currSortValue)
		}
		if(flag == true){
			$('#form').attr("action","${pageContext.request.contextPath}/formTable/moveChangeForm.hbc?zhTai="+ zhTai);
			$('#form').submit();
		}
	}		
}
function TiJiao(){
	var flag = true;
	$("input[name='title']").each(function(){
		if($(this).val()==""){
			alert("标题不能为空");
			flag = false;
			return false;
		}
	});
	
	if(flag == true){
		$('#form').attr("action","${pageContext.request.contextPath}/formTable/saveFormField.hbc");
		$("#form").submit();
	}
}

/* function test(typeid,defaultid1,defaultid2){
	var inputType = $("#"+typeid).val();
	if(inputType=='1'){
		$("#"+defaultid1).removeAttr("disabled");
		$("#"+defaultid2).removeAttr("readonly");
	}else{
		$("#"+defaultid1).attr("disabled","disabled");
		$("#"+defaultid2).attr("readonly","readonly");
	}
} */
function test(typeid,defaultid1,defaultid2,defaultid3){
	var inputType = $("#"+typeid).val();
	if(inputType=='1'){
		$("#"+defaultid1).removeAttr("disabled");
		$("#"+defaultid2).removeAttr("readonly");
		$("#"+defaultid3).attr("disabled","disabled");
		document.getElementById(defaultid3).value="";
	}else if (inputType=='4'){
		$("#"+defaultid1).attr("disabled","disabled");
		document.getElementById(defaultid1).value="";
		$("#"+defaultid2).attr("readonly","readonly");
		document.getElementById(defaultid2).value="";
		$("#"+defaultid3).removeAttr("disabled");
	}else {
		$("#"+defaultid1).attr("disabled","disabled");
		document.getElementById(defaultid1).value="";
		$("#"+defaultid2).attr("readonly","readonly");
		document.getElementById(defaultid2).value="";
		$("#"+defaultid3).attr("disabled","disabled");
		document.getElementById(defaultid3).value="";
	}
}

function inTitle(titFFId){
	var title = $("#"+titFFId).val();
	if(title == '' || title == null){
		alert("请输入标题");
		return false;
	}else if (title.length < 2){
		alert("标题太短，请修改");
		return false;
	}
}
</script>
</head>
<body>
	<form id="formUp" action="${pageContext.request.contextPath}/formTable/moveUpForm.hbc" method="post">
		<input type="hidden" name="moveUp" id="moveUp">
	</form>
	<div class="container">
		<div class="row">
			<h3>新增表单字段表</h3>
			<form class="bs-example bs-example-form" role="form" id="form"
					 action="" method="POST">
				<div class="panel panel-primary">
					<div class="panel-heading both">
						<div class="captions">
							<h3 class="panel-title">字段属性</h3>
						</div>
						<div class="actions">
							<button type="button" class="btn btn-default" onclick="moveChange('1')">上移</button>
							<button type="button" class="btn btn-default" onclick="moveChange('2')">下移</button>
							<button type="button" class="btn btn-default" onclick="TiJiao()">保存</button>
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="table-responsive"  id="xzbdzdb">
						<!-- <table class="table table-striped table-bordered"> -->
							<table class="table table-bordered table-striped table-hover formtable">
							
								<thead>
									<tr>
										<th class="check"><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
										<th>表名</th>
										<th>字段名称</th>
										<th>字段标题</th>
										<th>是否显示</th>
										<th>字段类型</th>
										<th style="display:none">字段长度</th>
										<th style="display:none">小数位数</th>
										<th style="display:none">是否允许为空</th>
										<th>点选信息</th>
										<th>下拉框状态</th>
										<th>内容</th>
										<th>是否为查询条件</th>
										<th>是否在查询列显示</th>
										<th>是否显示列</th>
										<th>是否可修改</th>
										<th>是否查询必录项</th>
										<th>是否录入必录项</th>
										<th>默认值</th>
										<th>字段排列顺序</th>
									</tr>
								</thead>
								<tbody id="tr_num">
									<c:if test="${not empty lstfie }">
										<c:forEach items="${lstfie }" var="entity" varStatus="status">
											<tr id="1">
												<td class="check">
													<input type="checkbox" id="${status.index+1}" name="rowid" value="${entity.fieldId}">
												</td>
												<td>
													<input type="text" class="form-control" placeholder="表名" name = "tableName" id="tableName"  value="${entity.tableName}" readonly>
													<input type="hidden" name = "tableId" id="tableId"  value="${entity.tableId}" readonly>
												</td>
												<td>
													<input type="text" class="form-control" name="fieldName" id="fieldName" value="${entity.fieldName}" readonly>
													<input type="hidden" class="form-control" name="formid" id="formid" value="${formid}" readonly>
													<input type="hidden" name = "fieldId" id="fieldId" value="${entity.fieldId}">
												</td>
												<td><input type="text" class="form-control" placeholder="标题" name="title" id="${entity.fieldId}o" value="${entity.title}" onblur="inTitle('${entity.fieldId}o')"></td>
												<td>
													<select class="form-control" name="inputIsDisplay" id="inputIsDisplay">
														<option value="0">是</option>
														<option value="1">否</option>
													</select>
												</td>
												<td>
													<c:if test="${entity.inputType=='5' }">
														<input type="hidden" class="form-control" name="inputType" id="inputType" value="${entity.inputType}" readonly>
														<input type="text" class="form-control" value="日期" readonly>
													</c:if>
													<c:if test="${entity.inputType=='7' }">
														<input type="hidden" class="form-control" name="inputType" id="inputType" value="${entity.inputType}" readonly>
														<input type="text" class="form-control" value="金额" readonly>
													</c:if>
													<c:if test="${entity.inputType != '5' && entity.inputType != '7'}">
														<select class="form-control" name="inputType" title="${entity.inputType}" id="${entity.fieldId}a" onchange="test('${entity.fieldId}a','${entity.fieldId}b','${entity.fieldId}c','${entity.id}d')">
															<option value="0" <c:if test="${entity.inputType=='0' }">selected</c:if>>输入框</option>
															<option value="1" <c:if test="${entity.inputType=='1' }">selected</c:if>>下拉框</option>
															<%-- <option value="2" <c:if test="${entity.inputType=='2' }">selected</c:if>>按钮</option> --%>
															<option value="3" <c:if test="${entity.inputType=='3' }">selected</c:if>>多选框</option>
															<option value="4" <c:if test="${entity.inputType=='4' }">selected</c:if>>点选</option>
															<option value="5" <c:if test="${entity.inputType=='5' }">selected</c:if>>日期</option>
															<option value="6" <c:if test="${entity.inputType=='6' }">selected</c:if>>文本域</option>
															<option value="7" <c:if test="${entity.inputType=='7' }">selected</c:if>>金额</option>
														</select>
													</c:if>
												</td>
												<td style="display:none"><input class="form-control" placeholder="100" name="number" value="${entity.number }" title="${entity.number }"></td>
												<td style="display:none"><input class="form-control" placeholder="0" name="decimalDigits" value="${entity.decimalDigits }" title="${entity.decimalDigits }"></td>
												<td style="display:none">
													<select class="form-control" name="isNull" title="${entity.isNull=='0' ? '是' : '否' }">
														<option value="0" <c:if test="${entity.isNull=='0' }">selected</c:if>>是</option>
														<option value="1" <c:if test="${entity.isNull=='1' }">selected</c:if>>否</option>
													</select>
												</td>
													<td><c:if test="${entity.inputType == '4' }">
														<select class='form-control' name='clickInfo'
															style='width: 100%' id="${entity.id}d">
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}"
																	${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
													</c:if> <c:if test="${entity.inputType != '4' }">
														<select class='form-control' name='clickInfo'
															style='width: 100%' id="${entity.id}d"
															disabled="disabled">
															<option value=''
																<c:if test="${entity.clickInfo=='' }">selected</c:if>></option>
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}"
																	${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
														<input type="hidden" name="clickInfo" id="${entity.id}d"
															value="${entity.clickInfo}">
													</c:if></td>
												<td>
													<c:if test="${entity.inputType=='1' }">
														<select class="form-control" name="sourceMode"title="${entity.sourceMode}" id="${entity.fieldId}b">
															<option value="" <c:if test="${entity.sourceMode=='2' }">selected</c:if>></option>
															<option value="0" <c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定值</option>
															<option value="1" <c:if test="${entity.sourceMode=='1' }">selected</c:if>>SQL</option>
														</select>
													</c:if>
													<c:if test="${entity.inputType!='1' }">
														<select class="form-control" name="sourceMode"title="${entity.sourceMode}" id="${entity.fieldId}b" disabled="disabled">
															<option value="" <c:if test="${entity.sourceMode=='2' }">selected</c:if>></option>
															<option value="0" <c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定值</option>
															<option value="1" <c:if test="${entity.sourceMode=='1' }">selected</c:if>>SQL</option>
														</select>
														<input type="hidden" name="sourceMode"title="${entity.sourceMode}" id="${entity.fieldId}b" value="${entity.sourceMode}">
													</c:if>
												</td>
												<td>
													<input type="text" class="form-control" title="${entity.sourceContent}" name="sourceContent" id="${entity.fieldId}c" value="${entity.sourceContent}" readonly>
												</td>
												<td>
													<select class="form-control" name="queryisConditions" id="queryisConditions">
														<option value="0">否</option>
														<option value="1">是</option>
													</select>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="queryisColumn" id="queryisColumn">
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isShowColumn" id="isShowColumn">
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isModify" id="isModify">
															<option value="0">是</option>
															<option value="1">否</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="queryisRequired" id="queryisRequired" title="${entity.queryisRequired=='0' ? '否' : '是' }">
															<option value="0" <c:if test="${entity.queryisRequired=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.queryisRequired=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td>
													<div class="form-group" style="width: 100px">
														<select class="form-control" name="isRequired" id="isRequired" title="${entity.isRequired=='0' ? '否' : '是' }">
															<option value="0" <c:if test="${entity.isRequired=='0' }">selected</c:if>>否</option>
															<option value="1" <c:if test="${entity.isRequired=='1' }">selected</c:if>>是</option>
														</select>
													</div>
												</td>
												<td><input type="text" class="form-control" name="inputDefaultValue" id="inputDefaultValue" value="${entity.inputDefaultValue}"></td>
												<td>
													<c:if test="${entity.sort=='0' }">
														<input type="text" class="form-control" placeholder="sort" name="sort" id="sort${status.index+1}" value="${status.index+1}">
													</c:if>
													<c:if test="${entity.sort!='0' }">
														<input type="text" class="form-control" placeholder="sort" name="sort" id="sort${status.index+1}" value="${entity.sort}">
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
			</form>
		</div>
	</div>
</body>
</html>