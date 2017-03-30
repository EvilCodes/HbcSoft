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
<title>接口管理类名列表</title>
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
		window.open('${pageContext.request.contextPath}/createInterManage/primaryList.hbc','Conframe');
	}
	//双击打开编辑页面
	function editTable(id){
		$("#getId").val(id);
		$("#flag").val("false")
		$("#form").submit();
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
			/*window.location.href="${pageContext.request.contextPath}/formTable/updateForm.hbc?ids="+ids;*/
			$("#getId").val(ids);
			$("#flag").val("ture")
			$("#form").submit();
		}
		
	}
	
	function search(){
		$("#searchTable").val($("#className").val());
		$("#searchMemo").val($("#tmemo").val());
		$("#currentPage").val(1);//没点击一次查询，初始为第一页
		$("#search").submit();
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
					url: '${pageContext.request.contextPath}/createInterManage/pageList.hbc?currentPage='+(pageIndex+1)+"&searchTable="+$("#searchTable").val()+"&searchMemo="+$("#searchMemo").val(),
					success: function(msg){
						var ttt = eval("("+msg+")");
						$("#totalNum").val(ttt.totalNum);//
						$("#pageSize").val(ttt.pageSize);//
						$("#pageTimes").val(ttt.pageTimes);//
						$("#currentPage").val(ttt.currentPage);//
						$("#num_tr").empty();
						for(var i=0; i<ttt.list.length; i++){
							var tt = ttt.list[i];
							$("#num_tr").append("<tr ondblclick='editTable(\""+tt.id+"\")'>"
									+"<td><input type='checkbox' name='rowid' value='"+tt.id+"'></td>"
									+"<td>"+(pageIndex*10+i+1)+"</td>"
									+"<td>"+tt.className+"</td>"
									+"<td>"+tt.methodName+"</td>"
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
</head>
<body>
	<form id="form" action="${pageContext.request.contextPath}/createInterManage/editTable.hbc" method="post">
		<input type="hidden" name="id" id="getId">
		<input type="hidden" name="flag" id="flag">
	</form>
	<form id="delForm" action="${pageContext.request.contextPath}/createInterManage/delete.hbc" method="post">
		<input type="hidden" name="del" id="del">
	</form>
	<div class="container">
	<div class="row">
		<form id="search" action="${pageContext.request.contextPath}/createInterManage/tableList.hbc" class="form-horizontal" method="get">
			<h3>接口管理类名表</h3>
			<hr>
			<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
			<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
			<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
			<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
			<input type="hidden" name="searchTable" id="searchTable" value="${searchTable }">
			<input type="hidden" name="searchMemo" id="searchMemo" value="${searchMemo }">
			<div class="btn-group">
				<button type="button" class="btn btn-default zsgc" onclick="openIns();">添加</button>
				<button type="button" class="btn btn-default zsgc" onclick="updateIns()">编辑</button>
				<button type="button" class="btn btn-default zsgc" onclick="del()">删除</button>
				<button type="button" class="btn btn-default zsgc" onclick="search()">查询</button>
			</div>
			<div class="row">
					<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<label for="drive" class="control-label col-lg-2 col-md-2 col-sm-4">类名：</label>
						<div class="col-lg-10 col-md-10 col-sm-8">
							<input name="className" type="text" class="form-control" id="className">
						</div>
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<label for="drive" class="control-label col-lg-2 col-md-2 col-sm-4">备注：</label>
						<div class="col-lg-10 col-md-10 col-sm-8">
							<input name="tmemo" type="text" class="form-control" id="tmemo">
						</div>
					</div> 
				</div>
		</form>
		<div class="table-responsive">          
			<table class="table table-bordered table-striped table-hover formtable">
				<col width="40px">
				<col width="60px">
				<col width="180px">
				<col width="180px">
				<thead class="active">
					<tr>
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<th>类名</th>
						<th>方法名</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="num_tr">
					<c:forEach items="${list }" var="table" varStatus="tt">
						<tr ondblclick="editTable('${table.id }')">
							<td>
								<input type="checkbox" name="rowid" value="${table.id }">
							</td>
							<td>
								${tt.index + 1 }
							</td>
							<td>
								${table.className }
							</td>
							<td>
								${table.methodName }
							</td>
							<td>
								${table.memo }
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align="center" class="pages">
				<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
				<ul class="pagination"></ul>
			</div>
		</div>
		</div>
	</div>
</body>
</html>