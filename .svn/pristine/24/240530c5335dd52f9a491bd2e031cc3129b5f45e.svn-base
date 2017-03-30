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
<title>查询字段信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var idFlag = 0;
	$("#add").click(function(){
		var sortn = $("input[name='searchId']").size();
		var str = "test"+idFlag;
		var typeid = str+idFlag+"a";
		var defaultid1 = str+idFlag+"b";
		var defaultid2 = str+idFlag+"c";
		var defaultid3 = str+idFlag+"d";
		idFlag++;
		sortn++;
		$("#tr_num").append("<tr id="+str+">"
				+"<td class='check'><input type='checkbox' id='"+sortn+"' name='rowid' value='0'></td>"
				+"<td><input type='text' class='form-control' name='tableName'></td>"//字段名
				+"<td><input type='text' class='form-control' name='reportSearchName'>"
				+"<input type='hidden' class='form-control' name='searchId' id='searchId' value='0'></td>"//字段名称
				+"<td><input type='text' class='form-control' name='title'></td>"//标题
				+"<td>"+
					"<select class='form-control' name='inputType' title='inputType' id="+typeid+" onchange='test(\""+typeid+"\",\""+defaultid1+"\",\""+defaultid2+"\",\""+defaultid3+"\")'>"+
						"<option value='0'>输入框</option>"+
						"<option value='1'>下拉框</option>"+
						"<option value='2'>按钮</option>"+
						"<option value='3'>多选框</option>"+
						"<option value='4'>点选</option>"+
						"<option value='5'>日期</option>"+
						"<option value='6'>文本域</option>"+
						"<option value='7'>金额</option>"+
					"</select>"+
				"</td>"//类型
				+"<td>"+
					"<c:if test='${inputType == "+1+"}'>"+
						"<select class='form-control' name='sourceMode' id="+defaultid1+" >"+
							"<option value='0'>固定</option>"+
							"<option value='1'>sql</option>"+
						"</select>"+
					"</c:if>"+
					"<c:if test='${inputType != "+1+"}'>"+
						"<select class='form-control' name='sourceMode' id="+defaultid1+" disabled='disabled'>"+
							"<option value=''></option>"+
							"<option value='0'>固定</option>"+
							"<option value='1'>sql</option>"+
						"</select>"+
						"<input type='hidden' name='sourceMode' title='sourceMode' id='"+defaultid1+"' value='${sourceMode}'>"+
					"</c:if>"
				+"</td>"//下拉框状态
				+"<td><input type='text' class='form-control' name='sourceContent' id="+defaultid2+" readonly></td>"//下拉框内容
				+"<td>"+
					"<select  class='form-control' style='width:100%' name='clickInfo' id="+defaultid3+" disabled='disabled'>"
						+"<option value=''></option>"
						+"<c:forEach items='${optionList}' var='item'>"
							+"<option value = '${item.clickKey}'>${item.clickValue}</option>"
						+"</c:forEach>"
					+"</select> "	
					+"<input type='hidden' name='clickInfo' id='"+defaultid3+"'value=''>"
				+"</td>"//点选信息
				+"<td><input type='text' class='form-control' id='sort"+sortn+"' name='sort' value='"+sortn+"'></td>"//排序
				+"<td style='display:none'><input type='hidden' name='reportId' value='0'></td>"//0:新增 1：变更 2：删除 3：不变
				+"<td>"+
				"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button>"+
				"</td></tr>");//操作
	});
});
function deltr(str){
	$("#"+str).remove();//删除当前行
}
function delRow(id){
	if(confirm("字段已经存在，确认删除吗？")){
		$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/delRow.hbc");
		$("#form").submit();
	}
}

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
		var sum = $("input[name='searchId']").size();
		$("input[name='tableName']").each(function(){
			if($(this).val()==""){
				alert("表名不能为空");
				flag = false;
				return false;
			}
		});
		$("input[name='reportSearchName']").each(function(){
			if($(this).val()==""){
				alert("字段名称不能为空");
				flag = false;
				return false;
			}
		});
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
			$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/moveChangeReport.hbc?status="+ zhTai);
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
		$('#form').attr("action","${pageContext.request.contextPath}/reportConfig/updateRepConSearch.hbc");
		$("#form").submit();
	}
}

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
			<h3>查询字段信息</h3>
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
							<button type="button" class="btn btn-default" id="add">增行</button>
							<button type="button" class="btn btn-default" onclick="TiJiao()">保存</button>
						</div>
						<div class="clear"></div>
					</div>
					<div class="panel-body">
						<div class="table-responsive"  id="xzbdzdb">
							<table class="table table-bordered table-striped table-hover formtable">
								<thead>
									<tr>
										<th class="check"><input type="checkbox" name="all"></th>
										<th>表名</th>
										<th>字段名称</th>
										<th>标题</th>
										<th>类型</th>
										<th>下拉框状态</th>
										<th>下拉框内容</th>
										<th>点选信息</th>
										<th>顺序</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tr_num">
									<input type="hidden" class="form-control" name="reportId" id="reportId" value="${reportId}">
									<c:if test="${not empty listRCS}">
										<c:forEach items="${listRCS}" var="entity" varStatus="status">
											<tr>
												<td class="check">
													<input type="checkbox" id="${status.index+1}" name="rowid" value="${entity.id}">
												</td>
												<td><input type="text" class="form-control" name="tableName" value="${entity.tableName}"></td>
												<td>
													<input type="text" class="form-control" name="reportSearchName" value="${entity.reportSearchName}">
													<input type="hidden" class="form-control" name="searchId" id="searchId" value="${entity.id}">
												</td>
												<td><input type="text" class="form-control" name="title" value="${entity.title}"></td>
												<td>
													<select class="form-control" name="inputType" title="inputType" id='${entity.id}a' onchange="test('${entity.id}a','${entity.id}b','${entity.id}c','${entity.id}d')">
														<option value='0'<c:if test="${entity.inputType=='0' }">selected</c:if>>输入框</option>
														<option value='1'<c:if test="${entity.inputType=='1' }">selected</c:if>>下拉框</option>
														<option value='2'<c:if test="${entity.inputType=='2' }">selected</c:if>>按钮</option>
														<option value='3'<c:if test="${entity.inputType=='3' }">selected</c:if>>多选框</option>
														<option value='4'<c:if test="${entity.inputType=='4' }">selected</c:if>>点选</option>
														<option value='5'<c:if test="${entity.inputType=='5' }">selected</c:if>>日期</option>
														<option value='6'<c:if test="${entity.inputType=='6' }">selected</c:if>>文本域</option>
														<option value='7'<c:if test="${entity.inputType=='7' }">selected</c:if>>金额</option>
													</select>
												</td>
												<td>
													<c:if test="${entity.inputType == '1' }">
														<select class="form-control" name="sourceMode" id="${entity.id}b">
															<option value='0'<c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定</option>
															<option value='1'<c:if test="${entity.sourceMode=='1' }">selected</c:if>>sql</option>
														</select>
													</c:if>
													<c:if test="${entity.inputType != 1 }">
														<select class="form-control" name="sourceMode" id="${entity.id}b" disabled="disabled">
															<option value=''<c:if test="${entity.sourceMode=='' }">selected</c:if>></option>
															<%-- <option value='0'<c:if test="${entity.sourceMode=='0' }">selected</c:if>>固定</option>
															<option value='1'<c:if test="${entity.sourceMode=='1' }">selected</c:if>>sql</option> --%>
														</select>
														<input type="hidden" name="sourceMode" title="sourceMode" id="${entity.id}b" value="${entity.sourceMode}">
													</c:if>
												</td>
												<td>
													<c:if test="${entity.inputType == '1'}">
														<input type='text' class='form-control' title="${entity.sourceContent}" name='sourceContent' id="${entity.id}c" value="${entity.sourceContent}">
													</c:if>
													<c:if test="${entity.inputType != '1'}">
														<input type='text' class='form-control' title="${entity.sourceContent}" name='sourceContent' id="${entity.id}c" value="${entity.sourceContent}" readonly>
													</c:if>
												</td>
												<td>
													<c:if test="${entity.inputType == '4' }">
														<select class='form-control' name='clickInfo' style='width:100%' id="${entity.id}d">
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}" ${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
													</c:if>
													<c:if test="${entity.inputType != '4' }">
														<select class='form-control' name='clickInfo' style='width:100%' id="${entity.id}d" disabled="disabled">
															<option value=''<c:if test="${entity.clickInfo=='' }">selected</c:if>></option>
															<c:forEach items="${optionList}" var="item">
																<option value="${item.clickKey}" ${entity.clickInfo == item.clickKey ? 'selected="selected"':''}>${item.clickValue}</option>
															</c:forEach>
														</select>
														<input type="hidden" name="clickInfo" id="${entity.id}d" value="${entity.clickInfo}">
													</c:if>
												</td>
												<td>
													<c:if test="${entity.sort=='0' }">
														<input type="text" class="form-control" name="sort" id="sort${status.index+1}" value="${status.index+1}" >
													</c:if>
													<c:if test="${entity.sort!='0' }">
														<input type="text" class="form-control" name="sort" id="sort${status.index+1}" value="${entity.sort }" >
													</c:if>
												</td>
												<td>
												<button type="button" class="btn btn-default" onclick="delRow('${entity.id}')">删行</button>
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