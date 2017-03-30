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
<title>同步科目列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jquery-1.10.2.min.js"></script>
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
	function search(){
		$("#currentPage").val(1);//没点击一次查询，初始为第一页
		$("#search").submit();
	}
	//同步到系统科目信息
	function chooseSubject(){
		var isSelected = false;
		var aa=document.getElementsByName("rowid");
		var ids = "";
		var m = 0;
		for(var i = 0 ; i<aa.length; i ++ ){
			if (aa[i].checked == true){
				ids = ids + aa[i].value + ",";
			}
		}
		$.ajax({
			type: 'post',
			data: 'json',
			async:true,
			url: '${pageContext.request.contextPath}/matchSubject/chooseSubjects.hbc?ids='+ids,
			success: function(msg){
				if(msg=="\"1\""){
					alert("科目同步成功！");
					window.location.href="${pageContext.request.contextPath}/matchSubject/synchronizeSubject.hbc?dbLinkParaId="+$("#dbLinkParaId").val()+"&requestType=query";
				}else if (msg=="\"0\""){
					alert("科目同步失败！");
				}
			},
			error: function(xhr,err,data){
				alert("科目同步失败！");
			}
		});
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
					url: '${pageContext.request.contextPath}/matchSubject/pageSubjectList.hbc?currentPage='+(pageIndex+1)
							+"&subjectType="+encodeURI(encodeURI($("#subjectType").val())) +"&subjectName="+encodeURI(encodeURI($("#subjectName").val())) 
							+"&isEnable="+$("#isEnable").val()+"&dbLinkParaId="+$("#dbLinkParaId").val(),
					success: function(msg){
						var ttt = eval("("+msg+")");
						
						$("#totalNum").val(ttt.totalNum);
						$("#pageSize").val(ttt.pageSize);
						$("#pageTimes").val(ttt.pageTimes);
						$("#currentPage").val(ttt.currentPage);
						
						$("#subjectType").val(ttt.subjectType);
						$("#subjectName").val(ttt.subjectName);
						
						$("#num_tr").empty();
						for(var i=0; i<ttt.subjectsList.length; i++){
							var tt = ttt.subjectsList[i];
							var isMatched = tt.isMatched;
							
							var date = new Date(tt.updateTime);
							var Y = date.getFullYear() + '-';
							var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							var D = date.getDate(); 
							
							if(isMatched == 1){
								isMatched = '已匹配';
								$("#num_tr").append("<tr ondblclick='editTable(\""+tt.id+"\")'>"
										+"<td><input type='checkbox' name='disabledrowid' value='"+tt.id+"' disabled='disabled'></td>"
										+"<td>"+(pageIndex*10+i+1)+"</td>"
										+"<td>"+tt.subjectCode+"</td>"
										+"<td>"+tt.subjectName+"</td>"
										+"<td>"+tt.subjectType+"</td>"
										+"<td>"+Y+M+D+"</td>"
										+"<td>"+isMatched+"</td>");
							}else{
								isMatched = '未匹配';
								$("#num_tr").append("<tr ondblclick='editTable(\""+tt.id+"\")'>"
										+"<td><input type='checkbox' name='rowid' value='"+tt.id+"'></td>"
										+"<td>"+(pageIndex*10+i+1)+"</td>"
										+"<td>"+tt.subjectCode+"</td>"
										+"<td>"+tt.subjectName+"</td>"
										+"<td>"+tt.subjectType+"</td>"
										+"<td>"+Y+M+D+"</td>"
										+"<td>"+isMatched+"</td>");
							}
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
	<form id="chooseSubjects" action="${pageContext.request.contextPath}/matchSubject/chooseSubjects.hbc" method="post">
		<input type="hidden" name="choose" id="choose">
	</form>
	<div class="container">
		<div class="row">
			<form id="search" action="${pageContext.request.contextPath}/matchSubject/synchronizeSubject.hbc" class="form-horizontal" method="get">
				<h3 >同步科目列表</h3>
				<hr>
				<div class="btn-group">
					<button type="button" class="btn btn-default zsgc" onclick="chooseSubject()">同步到系统科目</button>
					<button type="button" class="btn btn-default zsgc" onclick="search()">查询</button>
				</div>
				<p></p>
				<div class="row">	
					<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">科目类型：</label>
						<div class="col-lg-9 col-md-9 col-sm-8">
							<input name="subjectType" type="text" class="form-control" id="subjectType" value="${subjectType }">
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">科目名称：</label>
						<div class="col-lg-9 col-md-9 col-sm-8">
							<input name="subjectName" type="text" class="form-control" id="subjectName" value="${subjectName }">
						</div>
					</div> 
				</div>	
				<input type="hidden" name="requestType" id="requestType" value="query">
				<input type="hidden" name="dbLinkParaId" id="dbLinkParaId" value="${dbLinkParaId }">
				<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
				<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
				<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
				<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
			</form>
		<div class="table-responsive">          
		<table class="table table-bordered table-striped table-hover formtable">
				<thead>
					<tr>
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<th>科目编码</th>
						<th>科目名称</th>
						<th>科目类型</th>
						<th>同步时间</th>
						<th>匹配状态</th>
					</tr>
				</thead>
				<tbody id="num_tr">		
					<c:forEach items="${subjectslist }" var="subjects" varStatus="subject">
						<tr ondblclick="editTable('${subjects.id }')">
							<c:if test="${subjects.isMatched == 1}">
								<td>
									<input type="checkbox" name="disabledrowid" value="${subjects.id }" disabled="disabled">
									<input type="hidden" name="subjectCode" value="${subjects.subjectCode }">
								</td>
							</c:if>
							<c:if test="${subjects.isMatched == 0}">
								<td>
									<input type="checkbox" name="rowid" value="${subjects.id }" >
									<input type="hidden" name="subjectCode" value="${subjects.subjectCode }">
								</td>
							</c:if>	
							<td>
								${subject.index + 1 }
							</td>
							<td>
								${subjects.subjectCode }
							</td>
							<td>
								${subjects.subjectName }
							</td>
							<td>
								${subjects.subjectType }
							</td> 
							<td>
								<c:if test="${empty subjects.updateTime }">
									<fmt:formatDate value='${subjects.createTime}' pattern='yyyy-MM-dd' />
								</c:if>
								<c:if test="${not empty subjects.updateTime}">
									<fmt:formatDate value='${subjects.updateTime}' pattern='yyyy-MM-dd' />
								</c:if>
							</td>
							<td>
								<c:if test="${subjects.isMatched == 1}">
									已匹配
								</c:if>
								<c:if test="${subjects.isMatched == 0}">
									未匹配
								</c:if>
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