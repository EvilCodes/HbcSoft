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
<title>数据库表名列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
	//打开添加页面
	function openIns(){
		window.location.href='${pageContext.request.contextPath}/formTable/addForm.hbc';
	}
	//双击查看
	function viewTable(id){
		$("#getId").val(id);
		$("#form").submit();
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
			window.location.href="${pageContext.request.contextPath}/formTable/updateForm.hbc?ids="+ids;
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
	
	function search(){
		$("#searchTable").val($("#ttable").val());
		$("#searchMethod").val($("#tmethod").val());
		$("#searchFormType").val($("#formType").val());
		$("#searchMemo").val($("#tmemo").val());
		$("#currentPage").val(1);//没点击一次查询，初始为第一页
		$("#search").submit();
	}
	
	function createPage(pageSize, buttons, total){
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
					url: '${pageContext.request.contextPath}/formTable/pageList.hbc?currentPage='+(pageIndex+1)+
							"&searchTable="+$("#searchTable").val()+"&searchMethod="+$("#searchMethod").val()+
							"&searchFormType="+$("#searchFormType").val()+"&searchMemo="+$("#searchMemo").val(),
					success: function(msg){
						var ttt = eval("("+msg+")");
						$("#totalNum").val(ttt.totalNum);//
						$("#pageSize").val(ttt.pageSize);//
						$("#pageTimes").val(ttt.pageTimes);//
						$("#currentPage").val(ttt.currentPage);//
						$("#num_tr").empty();
						for(var i=0; i<ttt.list.length; i++){
							var tt = ttt.list[i];
							$("#num_tr").append("<tr ondblclick='viewTable(\""+tt.id+"\")'>"
									+"<td><input type='checkbox' name='rowid' value='"+tt.id+"'></td>"
									+"<td>"+(pageIndex*10+i+1)+"</td>"
									+"<td>"+tt.formNamef+"</td>"
									+"<td>"+tt.actionUrl+"</td>"
									+"<td>"+tt.method+"</td>"
									+"<td>"+tt.formType+"</td>"
									+"<td>"+tt.versNum+"</td>"
									+"<td>"+tt.memo+"</td>");
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
	<form id="form" action="${pageContext.request.contextPath}/formTable/viewTable.hbc" method="post">
		<input type="hidden" name="id" id="getId">
	</form>
	<form id="delForm" action="${pageContext.request.contextPath}/formTable/delete.hbc" method="post">
		<input type="hidden" name="del" id="del">
	</form>
	<div class="container">
		<div class="row">
		<!-- <h2 align="center">表单表</h2>
		<div class="btn-toolbar" role="toolbar">
			<div class="btn-group"> -->                                                                  <!-- class="form-inline" -->
				<form id="search" action="${pageContext.request.contextPath}/formTable/selectAllForm.hbc" class="form-horizontal"  method="post">
					<h3>表单表</h3>
					<hr>
					<!-- <div align = "left"> -->
					<div class="btn-group">
						<button type="button" class="btn btn-default zsgc" onclick="openIns();">添加</button>
						<button type="button" class="btn btn-default zsgc" onclick="updateIns();">修改</button>
						<button type="button" class="btn btn-default zsgc" onclick="del()">删除</button>
						<button type="button" class="btn btn-default zsgc" onclick="search()">查询</button>
					</div>
					<div class="row">
						<div class="form-group col-md-6 col-sm-6">
							<label for="drive" class="control-label col-md-2 col-sm-4">
								表名名称
							</label>
							<div class="col-md-10 col-sm-8">
								<input name="ttable" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6 col-xs-12">
							<label for="name" class="control-label col-md-2 col-sm-4">提交方式</label>
							<div class="col-md-10 col-sm-8">
								<select class="form-control" id="tmethod" name="tmethod">
									<option value="get">get</option>
									<option value="post">post</option>
								</select>
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">
								表单类型
							</label>
							<div class="col-md-10 col-sm-8">
								<input name="formType" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-md-2 col-sm-4">
								备注
							</label>
							<div class="col-md-10 col-sm-8">
								<input name="tmemo" type="text" class="form-control">
							</div>
						</div> 
						<!-- <label>表名：<input name="ttable" type="text" class="form-control"></label>
						<label>提交方式：<input name="tmethod" type="text" class="form-control"></label>
						<label>表单类型：<input name="formType" type="text" class="form-control"></label>
						<label>备注：<input name="tmemo" type="text" class="form-control"></label> -->
					</div>
					<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
					<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
					<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
					<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
					<input type="hidden" name="searchTable" id="searchTable" value="${searchTable }">
					<input type="hidden" name="searchMethod" id="searchMethod" value="${searchMethod }">
					<input type="hidden" name="searchFormType" id="searchFormType" value="${searchFormType }">
					<input type="hidden" name="searchMemo" id="searchMemo" value="${searchMemo }">
				</form>
			</div>
		<!-- </div> -->
		<div class="table-responsive">          
			<!-- <table class="table table-striped table-bordered"> -->
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr class="active">
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<th>表单名称</th>
						<th>路径</th>
						<th>提交方式</th>
						<th>表单类型</th>
						<th>版本号</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="num_tr">
					<c:forEach items="${list }" var="formTable" varStatus="tt">
						<tr ondblclick="viewTable('${formTable.id }')">
							<td>
								<input type="checkbox" name="rowid" value="${formTable.id }">
							</td>
							<td>
								${tt.index + 1 }
							</td>
							<td>
								${formTable.formNamef }
							</td>
							<td>
								${formTable.actionUrl }
							</td>
							<td>
								${formTable.method }
							</td>
							<td>
								${formTable.formType }
							</td>
							<td>
								${formTable.versNum }
							</td>
							<td>
								${formTable.memo }
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
</body>
</html>