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
<title>新增第三方数据库配置</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var idFlag = 1;
		$("#add").click(function(){
			var str = "test"+idFlag;
			var typeid = str+idFlag;
			var defaultid = typeid+idFlag;
			idFlag++;
			$("#tr_num").append("<tr id="+str+">"
					+"<td><input type='text' class='form-control' name='subjectType'></td>"//科目类型
					+"<td><input type='text' class='form-control' name='subjectCode'></td>"//科目编码
					+"<td><input type='text' class='form-control' name='subjectName'></td>"//科目名称
					+"<td><input type='text' class='form-control' name='subjectLevel'></td>"//科目级次
					+"<td><input type='text' class='form-control' name='parentId'></td>"//上级科目编码
					+"<td><div class='form-group' style='width: 100px'>"+
						"<select class='form-control'  name='isEnable'>"+
							"<option value='1'>是</option><option value='0'>否</option>"+
						"</select></div>"
					+"</td>"//是否启用				
					+"<td><input type='text' class='form-control' name='remark'></td>"//备注
					+"<td><div class='btn-toolbar' role='toolbar'>"+
					"<div class='btn-group btn-group-sm'>"+
					"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button></div>"+
					"</div></td></tr>");//操作
		});
	});
	function deltr(str){
		$("#"+str).remove();//删除当前行
	}
	function test(typeid,defaultid){
		//alert($("#"+typeid).val());
		//alert($("#"+defaultid).val());
		
	}
	$(document).ready(function(){
		$("form").submit(function(e){
			var arr = new Array();
			var isBreak = false;
			$("input[name='subjectCode']").each(function(index, item){
				if($.inArray($(this).val(),arr)==-1){
					arr.push($(this).val());
				}else{
					alert("科目编码【"+$(this).val()+"】已经存在，请修改！");
					isBreak = true;
				}
				if($(this).val()==""){
					alert("科目编码不能为空！");
					isBreak = true;
				}
			});
			$("input[name='subjectName']").each(function(index, item){
				if($(this).val()==""){
					alert("科目名称不能为空！");
					isBreak = true;
				}
			});
			if(isBreak){
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div>
		<h3 align="center">新增科目配置</h3>
	</div>
	<div style="padding: 10px 100px 10px;">
		<form class="bs-example bs-example-form" role="form" id="form"
				action="${pageContext.request.contextPath}/subjectSet/saveSubjects.hbc" method="post">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title" style="float: left">系统科目配置</h3>
					<div class="btn-toolbar" role="toolbar" style="float: right;">
						<div class="btn-group btn-group-sm">
							<button type="button" class="btn btn-default" id="add">增行</button>
							<button type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
					<div class="clear" style="clear: both;"></div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>科目类型</th>
								<th>科目编码</th>
								<th>科目名称</th>
								<th>科目级次</th>
								<th>上级科目编码</th>
								<th>是否启用</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tr_num">
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>