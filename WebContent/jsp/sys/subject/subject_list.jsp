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
<meta name="viewport" content="width-device-width,initial-scale-1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>同步科目列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
	function search(){
		$("#currentPage").val(1);//没点击一次查询，初始为第一页
		$("#search").submit();
	}

	//打开添加页面
	function openIns(){
		var companyId = document.getElementById("companyId").value;
		var companyName = document.getElementById("companyName").value;
		
		window.location.href='${pageContext.request.contextPath}/subjectSet/addSubjects.hbc?companyId='
			+companyId+"&companyName="+companyName;
	}
	//修改按钮
	function updateIns(){
		var aa=document.getElementsByName("rowid");
		var ids = "";
		var flag = false;
		var m = 0;
		for(var i = 0 ; i<aa.length; i ++ ){
			if (aa[i].checked == true){
				ids = ids + aa[i].value + ",";
				flag = true;
				m++;
			}
		}
		if(m==0){
			alert("请先选择一条记录再进行修改操作！");
			return;
		}else if(m>1){
			alert("只能选择一条数据进行修改！");
			return;
		}else if(m==1){
			ids = ids.substring(0,ids.length-1);
			window.location.href="${pageContext.request.contextPath}/subjectSet/editSubject.hbc?ids="+ids;
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
	//删除按钮
	function del(){
		var isSelected = false;
		var aa=document.getElementsByName("rowid");
		for(var i = 0 ; i<aa.length; i ++ ){
			if (aa[i].checked == true){
				isSelected = true;
				break;
			}
		}
		if(!isSelected){
			alert("请选择要删除的数据！");
			return false;
		}
		var keystr="";
		for(var i = 0 ; i<aa.length; i++ ){
			if (aa[i].checked == true){
				keystr=keystr+ aa[i].value+",";
			}
		}
		$("#del").val(keystr);
		if(confirm("确定要删除选中的记录吗？")){
			$("#delForm").submit();
		}
	}
	
	function createPage(pageSize, buttons, total) {
		$(".pagination").jBootstrapPage({
			pageSize : pageSize,
			total : total,
			maxPageButton:buttons,
			onPageClicked: function(obj, pageIndex) {
				if(isNaN(pageIndex)){
					pageIndex = 0;
				}
				$.ajax({
					type: 'post',
					data: 'json',
					async:true,
					url: '${pageContext.request.contextPath}/subjectSet/pageSubjects.hbc?currentPage='+(pageIndex+1)
							+"&subjectType="+encodeURI(encodeURI($("#subjectType").val()))
							+"&subjectName="+encodeURI(encodeURI($("#subjectName").val()))
							+"&isEnable="+$("#isEnable").val()
							+"&subjectCode="+$("#subjectCode").val(),
					success: function(msg){
						var ttt = eval("("+msg+")");
						
						$("#totalNum").val(ttt.totalNum);
						$("#pageSize").val(ttt.pageSize);
						$("#pageTimes").val(ttt.pageTimes);
						$("#currentPage").val(ttt.currentPage);
						
						$("#isEnable").val(ttt.isEnable);
						$("#subjectType").val(ttt.subjectType);
						$("#subjectCode").val(ttt.subjectCode);
						$("#subjectName").val(ttt.subjectName);
						
						$("#num_tr").empty();
						for(var i=0; i<ttt.subjectsList.length; i++){
							var tt = ttt.subjectsList[i];
							var isEnable = tt.isEnable;
							if(isEnable == 1){
								isEnable = '是';
							}else{
								isEnable = '否';
							}
							$("#num_tr").append("<tr ondblclick='editTable(\""+tt.id+"\")'>"
									+"<td><input type='checkbox' name='rowid' value='"+tt.id+"'></td>"
									+"<td>"+(pageIndex*10+i+1)+"</td>"
									+"<td>"+tt.iyear+"</td>"
									+"<td>"+tt.subjectType+"</td>"
									+"<td>"+tt.subjectCode+"</td>"
									+"<td>"+tt.subjectName+"</td>"
									+"<td>"+isEnable+"</td>"
									+"<td>"+tt.remark+"</td>");
						}
					},
					error: function(xhr,err,data){
						alert("失败");
					}
				});
			}
		});
	}
	$(function(){
		var totalNum = $("#totalNum").val();//总记录数
		var pageSize = $("#pageSize").val();//每页条数
		var pageTotal = $("#pageTimes").val();//页数
		if(parseInt(pageTotal)>10){
			pageTotal = 10;
		}
		createPage(pageSize, pageTotal, totalNum);
	});
</script>
<style type="text/css">
	label{
		padding-left: 20px;
	}
</style>
</head>
<body>
	<form id="form" action="${pageContext.request.contextPath}/formTable/editTable.hbc" method="post">
		<input type="hidden" name="id" id="getId">
	</form>
	<form id="delForm" action="${pageContext.request.contextPath}/subjectSet/deleteSubjects.hbc" method="post">
		<input type="hidden" name="del" id="del">
	</form>
	<div class="container">
		<div class="row">
			<form id="search" action="${pageContext.request.contextPath}/subjectSet/queryAllSubjects.hbc" class="form-horizontal" method="get">
				<h3>系统科目配置</h3>
				<hr>
				<div class="btn-group">
				<!-- <button type="button" class="btn btn-default" onclick="openIns();">添加</button> -->
					<button type="button" class="btn btn-default" onclick="updateIns();">修改</button>
					<button type="button" class="btn btn-default" onclick="del()">删除</button>
					<button type="button" class="btn btn-default" onclick="search()">查询</button>
				</div>	
				<p></p>
				<div class="row">	
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">
								科目编码：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input name="subjectCode" id="subjectCode" value="${subjectCode }" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">
								科目名称：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input name="subjectName" id="subjectName" value="${subjectName }" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">
								科目类型：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input name="subjectType" id="subjectType" value="${subjectType }" type="text" class="form-control">
							</div>
						</div> 
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="name" class="control-label col-lg-3 col-md-3 col-sm-4">
								是否启用：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<select class="form-control" id="isEnable" name="isEnable">
									<option  value=""></option>
									<option ${isEnable =="0"?'selected':''} value="0">否</option>
									<option ${isEnable =="1"?'selected':''} value="1">是</option>
								</select>
							</div>
						</div>
					</div>	
					<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
					<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
					<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
					<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
					<input type="hidden" name="companyId" id="companyId" class="form-control" value="${companyId }">
					<input type="hidden" name="companyName" id="companyName" class="form-control" value="${companyName }">
				</form>
		<div class="table-responsive">          
				<table class="table table-bordered table-striped table-hover formtable">
				<thead>
					<tr class="active">
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<th>年度</th>
						<th>科目类型</th>
						<th>科目编码</th>
						<th>科目名称</th>
						<th>是否启用</th>
						<th>备注信息</th>
					</tr>
				</thead>
				<tbody id="num_tr">
					<c:forEach items="${subjectList }" var="subjectList" varStatus="subject">
						<tr ondblclick="editTable('${subjectList.id }')">
							<td>
								<input type="checkbox" name="rowid" value="${subjectList.id }">
								<input type="hidden" name="companyId" id="companyId" class="form-control" value="${companyId }">
								<input type="hidden" name="companyName" id="companyName" class="form-control" value="${companyName }">
							</td>
							<td>
								${subject.index + 1 }
							</td>
							<td>
								${subjectList.iyear }
							</td>
							<td>
								${subjectList.subjectType }
							</td>
							<td>
								${subjectList.subjectCode }
							</td>
							<td>
								${subjectList.subjectName }
							</td>
							<td>
								<c:if test="${subjectList.isEnable == 1}">
									是
								</c:if>
								<c:if test="${subjectList.isEnable == 0}">
									否
								</c:if>
							</td>
							<td>
								${subjectList.remark }
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align = "center" class="pages">
				<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
				<ul class="pagination"></ul>
			</div>
		</div>
	</div>
	</div>
</body>
</html>