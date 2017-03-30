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
<title>第三方数据库配置列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/jBootstrapPage.js"></script>
<script type="text/javascript">
function checkForm(){
    var flag=true;
    var message=$("#message").val();
    if(message != "" && message!="null" ){
     alert(message);
     flag=false;
    }
    return flag;
   }
	//打开添加页面
	function openIns(){
		var companyId = document.getElementById("companyId").value;
		var companyName = document.getElementById("companyName").value;
		window.location.href='${pageContext.request.contextPath}/outerDBLink/addOuterDbLink.hbc?companyId='
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
			window.location.href="${pageContext.request.contextPath}/outerDBLink/editDBPara.hbc?ids="+ids;
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
		$("#search").submit();
	}
	function test(id){
		$.ajax({
			type: 'post',
			data: 'json',
			async:false,
			url: '${pageContext.request.contextPath}/outerDBLink/connectTest.hbc?id='+id,
			success: function(msg){
				if(msg=="\"1\""){
					alert("连接成功");
				}else{
					alert("连接失败");
				}
			},
			error: function(xhr,err,data){
				alert("连接失败");
				
			}
		});	
	}

</script>
<style type="text/css">
	label{
		padding-left: 20px;
	}
</style>
</head>
<body onload="checkForm()">
	<input type="hidden" name="message" id="message" value="${message }">
	<form id="form" action="${pageContext.request.contextPath}/formTable/editTable.hbc" method="post">
		<input type="hidden" name="id" id="getId">
	</form>
	<form id="delForm" action="${pageContext.request.contextPath}/outerDBLink/deleteDbLinkPara.hbc" method="post">
		<input type="hidden" name="del" id="del">
	</form>
	<div class="container">
			<div class="row">
				<form id="search" action="${pageContext.request.contextPath}/outerDBLink/queryOuterDBPara.hbc" class="form-horizontal" method="get">
					<h3 >第三方数据库参数配置信息</h3>
					<hr>
					<div class="btn-group">
						<button type="button" class="btn btn-default" onclick="openIns();">添加</button>
						<button type="button" class="btn btn-default" onclick="updateIns();">修改</button>
						<button type="button" class="btn btn-default" onclick="del()">删除</button>
						<button type="button" class="btn btn-default" onclick="search()">查询</button>
					</div>
					<p></p>
					<div class="row">	
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="drive" class="control-label col-lg-3 col-md-3 col-sm-4">
								数据库名称·：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<input name="dbsId" id="dbsId" type="text" class="form-control">
							</div>
						</div> 
						<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label for="name" class="control-label col-lg-3 col-md-3 col-sm-4">
								是否启用：
							</label>
							<div class="col-lg-9 col-md-9 col-sm-8">
								<select class="form-control" id="isEnable" name="isEnable">
									<option  value=""></option>
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</div>
						</div>
					</div>	
				<!-- 	<label>数据库名称：<input name="dbsId" type="text" class="form-control" id="dbsId"></label>
					<label>是否启用：
						<select class='form-control'  name='isEnable' id='isEnable'>
							<option value=''></option>
							<option value='0'>否</option>
							<option value='1'>是</option>
						</select>
					</label> -->
					
					<input type="hidden" name="companyId" id="companyId" class="form-control" value="${companyId }">
					<input type="hidden" name="companyName" id="companyName" class="form-control" value="${companyName }">
					<input type="hidden" name="requestType" id="requestType" value="query">
					<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
					<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
					<input type="hidden" name="pageTimes" id="pageTimes" value="${pageTimes }">
					<input type="hidden" name="totalNum" id="totalNum" value="${totalNum }">
				</form>
		<div class="table-responsive">          
			<!-- <table class="table table-striped table-bordered"> -->
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr class="active">
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<!-- <th>公司名称</th> -->
						<th>创建日期</th>
						<th>数据库别名</th>
						<th>数据库IP</th>
						<th>数据库类型</th>
						<th>端口号</th>
						<th>数据库名称</th>
						<th>数据库用户名</th>
						<th>备注信息</th>
						<th>是否启用</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${outerDBLinkList }" var="outerDBLink" varStatus="db">
						<tr ondblclick="editTable('${outerDBLink.id }')">
							<td>
								<input type="checkbox" name="rowid" value="${outerDBLink.id }">
							</td>
							<td>
								${db.index + 1 }
							</td>
							<%-- <td>
								${outerDBLink.companyName }
							</td> --%>
							<td>
								<fmt:formatDate value='${outerDBLink.createTime }' pattern='yyyy-MM-dd' />
							</td>
							<td>
								${outerDBLink.dbAccount }
							</td>
							<td>
								${outerDBLink.dbIp }
							</td>
							<td>
								<c:if test="${outerDBLink.dbType == 0}">
									Mysql
								</c:if>
								<c:if test="${outerDBLink.dbType == 1}">
									sqlServer
								</c:if>
								<c:if test="${outerDBLink.dbType == 2}">
									Oracle
								</c:if>
							</td>
							<td>
								${outerDBLink.dbport }
							</td>
							<td>
								${outerDBLink.dbsId }
							</td>
							<td>
								${outerDBLink.dbUser }
							</td>
							<td>
								${outerDBLink.remark }
							</td>
							<td>
								<c:if test="${outerDBLink.isEnabled == 1}">
									是
								</c:if>
								<c:if test="${outerDBLink.isEnabled == 0}">
									否
								</c:if>
							</td>
							<td>
								<div class='btn-toolbar' role='toolbar'>
									<div class='btn-group btn-group-sm'>
										<button type='button' class='btn btn-default' onclick="test('${outerDBLink.id }')">连接测试</button>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div align = "center">
				<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
				<ul class="pagination"></ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>