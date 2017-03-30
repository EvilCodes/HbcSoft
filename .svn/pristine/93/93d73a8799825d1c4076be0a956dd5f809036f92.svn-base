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
<title>数据同步列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/jBootsrapPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
	//同步按钮
	function synchronizeData(){
		var aa=document.getElementsByName("rowid");
		var ids = "";
		var m = 0;
		for(var i = 0 ; i<aa.length; i ++ ){
			if (aa[i].checked == true){
				ids = ids + aa[i].value + ",";
				flag = true;
				m++;
			}
		}
		if(m==0){
			alert("请先选择一条记录再进行同步操作！");
			return;
		}else if(m>1){
			alert("只能选择一条数据进行同步！");
			return;
		}else if(m==1){
			var repeatFlag = false;
			ids = ids.substring(0,ids.length-1);
			var dataType = document.getElementById("dataType").value;
			msg.innerHTML = "数据同步中，请稍候......"; 
			$.ajax({
				type: 'post',
				data: 'json',
				async:false,
				url: '${pageContext.request.contextPath}/dataSynchronize/repeatCheck.hbc?dbLinkParaId='
						+ids+"&dataType="+dataType,
				success: function(msg){
					if(msg=="\"1\""){
						if(confirm('检测到当前系统已做过同步，再次同步会删除原有数据，是否继续？')){
							cloneData(ids);
						}else{
							reload();
						}
					}else if(msg=="\"2\""){
						alert("请同步机构后再进行用户同步操作！");
						reload();
					}else{
						cloneData(ids);
					}
				},
				error: function(xhr,err,data){
					alert("同步失败，请检查数据库配置信息！");
				}
			});	
		}
	}
	//同步数据
	function cloneData(ids){
		var dataType = document.getElementById("dataType").value;
		document.getElementById("tongbu").disabled = true;
		$.ajax({
			type: 'post',
			data: 'json',
			async:false,
			url: "${pageContext.request.contextPath}/dataSynchronize/cloneData.hbc?dbLinkParaId="
				+ids+"&dataType="+dataType,
			success: function(msg){
				if(msg=="\"1\""){
					if("user" == dataType){
						alert("用户数据同步成功，可在系统用户管理处查看同步结果！");
					}
					if("dept" == dataType){
						alert("部门数据同步成功，可在系统机构管理处查看同步结果！");
					}
				}else{
					alert("同步失败，请联系系统管理员！");
				}
				reload();
			},
			error: function(xhr,err,data){
				alert("数据同步过程中发生异常，请联系系统管理员！");
				reload();
			}
		});	
	}
	//刷新页面
	function reload(){
		var dataType = document.getElementById("dataType").value;
		if("user" == dataType){
			window.location.href='${pageContext.request.contextPath}/dataSynchronize/cloneUserData.hbc';
		}else if("dept" == dataType){
			window.location.href='${pageContext.request.contextPath}/dataSynchronize/cloneDeptData.hbc';
		}
		
	}
</script>
<style type="text/css">
	label{
		padding-left: 20px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test="${dataType=='user'}">
				<h3>用户数据同步</h3>
			</c:if>
			<c:if test="${dataType=='dept'}">
				<h3>部门数据同步</h3>
			</c:if>
			<hr>
			<div class="btn-group">
				<button id = "tongbu" type="button" class="btn btn-default" onclick="synchronizeData();">同步</button>
			</div>
			<input type="hidden" name="companyId" id="companyId" class="form-control" value="${companyId }">
			<input type="hidden" name="dataType" id="dataType" class="form-control" value="${dataType }">
		<div id="msg" align="center"  style="color:#FF0000"></div>
		<p></p>
		<div class="table-responsive">          
			<table class="table table-bordered table-striped table-hover formtable">
				<thead>
					<tr class="active">
						<th><input type="checkbox" name="all" onclick="checkAll(this,'rowid')"></th>
						<th>序号</th>
						<th>创建日期</th>
						<th>数据库IP</th>
						<th>数据库类型</th>
						<th>数据库名称</th>
						<th>数据库用户名</th>
						<th>备注信息</th>
						<th>是否启用</th>
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
							<td>
								<fmt:formatDate value='${outerDBLink.createTime }' pattern='yyyy-MM-dd' />
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