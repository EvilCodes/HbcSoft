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
<title>创建接口</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//http://www.blogjava.net/absolutedo/archive/2009/03/13/259488.html
		var idFlag = 1;
		$("#add").click(function(){
			//var len = $("#tr_num tr").length;
			var str = "test"+idFlag;
			var typeid = str+idFlag;
			var defaultid = typeid+idFlag;
			idFlag++;
			/* $("#tr_num").append("<tr id="+str+">"
					+"<td><input type='text' class='form-control' name='name'></td>"//字段名
					+"<td><input type='text' class='form-control' name='title'></td>"//标题
					+"<td>"+
			"<select class='form-control' name='type' id="+typeid+" onchange='test(\""+typeid+"\",\""+defaultid+"\")'><option value='COLUMN_TYPE_TEXT'>文本</option><option value='COLUMN_TYPE_INT'>整数</option><option value='COLUMN_TYPE_NUMERIC'>小数</option><option value='COLUMN_TYPE_DATE'>日期</option><option value='COLUMN_TYPE_TIMESTAMP'>时间日期</option></select></td>"//类型
					+"<td><input type='number' class='form-control' name='number'></td>"//长度
					+"<td><input type='number' class='form-control' name='decimalDigits'></td>"//小数位数
					+"<td>"+
			"<select class='form-control' name='isNull'><option value='0'>是</option><option value='1'>否</option></select></td>"//是否为空
					+"<td>"+
			"<select class='form-control'  name='isDisplay'><option value='0'>是</option><option value='1'>否</option></select></td>"//是否显示
					+"<td>"+
			"<select class='form-control'  name='isEdit'><option value='0'>是</option><option value='1'>否</option></select></td>"//是否可编辑
					+"<td><input type='text' class='form-control' name='defaultValue' id="+defaultid+"></td>"//默认值
					+"<td style='display:none'><input type='hidden' name='changeFlag' value='0'></td>"//0:新增 1：变更 2：删除 3：不变
					+"<td>"+
					"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button>"+
					"</td></tr>");//操作 */
					
					$("#tr_num").append("<tr id="+str+">"
							+"<td><input type='text' class='form-control' name='fieldName'></td>"//属性名
							+"<td><input type='text' class='form-control' name='fieldType'></td>"//属性类型
							+"<td><input type='text' class='form-control' name='fieldValue' id="+defaultid+"></td>"//属性值
							+"<td>"+
							"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button>"+
							"</td></tr>");//操作 
		});
	});
	function deltr(str){
		$("#"+str).remove();//删除当前行
	}
	/* function test(typeid,defaultid){
		var type = $("#"+typeid).val();
		if(type=='COLUMN_TYPE_DATE'){
			//$("#"+defaultid).addClass("Wdate");
			$("#"+defaultid).unbind();//先清空样式，再生成，否则样式切换不成功
			$("#"+defaultid).focus(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		}else if(type=='COLUMN_TYPE_TIMESTAMP'){
			$("#"+defaultid).unbind();
			$("#"+defaultid).focus(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		}else{
			//$("#"+defaultid).removeClass("Wdate");
			$("#"+defaultid).unbind();
		}
	} */
	
	$(document).ready(function(){
	/* 	var str = $("#isMainTable").val();
		if(str=="主表"){
			$("#sl").css('display','none');
		}else{
			$("#sl").css('display','block');
		}
		$("#isMainTable").change(function(){
			if($("#isMainTable").val()=="主表"){
				$("#sl").css('display','none');
			}else{
				$("#sl").css('display','block');
			}
		});
		 */
		$("form").submit(function(e){
			var tt = false;
			if($("#className").val()==""){
				alert("类名不能为空！");
				return false;
			}else{
				$.ajax({
					type: 'post',
					data: 'json',
					async:false,
					url: '${pageContext.request.contextPath}/createInterManage/checkName.hbc?tableName='+encodeURI(encodeURI($("#className").val())),
					success: function(msg){
						if(msg=="\"1\""){
							tt = true;
						}else{
							tt = false;
						}
					},
					error: function(xhr,err,data){
						alert("请求失败！");
					}
				});
			}
			if(tt){
				alert("类名已经存在，请修改！");
				return false;
			}
			alert("方法名："+$("#methodName").val())
			if ($("#methodName").val() == ""){
				alert("方法名不能为空")
				return false
			}
			
			/* if($("#isMainTable").val()=="子表" && $("#mainId").val()==""){
				alert("关联主表必须选择！");
				return false;
			} */
			
			var arr = new Array();
			arr.push("ID");
			arr.push("ZID");
			arr.push("COMPANYID");
			var arrTitle = new Array();
			var isBreak = false;
			/* var len = $("input[name='name']").length;
			if(parseInt(len)==0){
				alert("子表不允许为空！");
				return false;
			} */ 
			$("input[name='fieldName']").each(function(index, item){
				if($.inArray($(this).val().toUpperCase(),arr)==-1){
					arr.push($(this).val().toUpperCase());
				}else{
					alert("属性名【"+$(this).val()+"】已经存在，请修改！");
					isBreak = true;
				}
				if($(this).val()==""){
					alert("属性名不能为空！");
					isBreak = true;
				}
			});
			$("input[name='fieldType']").each(function(index, item){
				/* if($.inArray($(this).val(),arrTitle)==-1){
					arrTitle.push($(this).val());
				}else{
					alert("字段标题【"+$(this).val()+"】已经存在，请修改！");
					isBreak = true;
				} */
				if($(this).val()==""){
					alert("属性类型不能为空！");
					isBreak = true;
				}
			});
			if(isBreak){
				return false;
			}
		/* 	if(isBreak){
				return false;
			}
			var trList = $("tbody").children("tr");
			for(var i=0; i<trList.length; i++){
				var tdArr = trList.eq(i).find("td");
				var type = tdArr.eq(2).find("select").val();//字段类型
				var number = tdArr.eq(3).find("input").val();//字段长度
				if(number==''){
					number = 0;
				}
				var defaultValue = tdArr.eq(8).find("input").val();//默认值
				if(type=='COLUMN_TYPE_TEXT'){
					if(parseInt(number)<defaultValue.length){
						alert("默认值的长度必须小于字段长度！");
						return false;
					}
				}
				if(type=='COLUMN_TYPE_INT'){
					var reg=/^-?[0-9]\d*$/;
					if(parseInt(number)<defaultValue.length){
						alert("默认值的长度必须小于字段长度！");
						return false;
					}
					if(!reg.test(defaultValue)){
						alert("字段类型为整数的默认值只能是整数！");
						return false;
					}
				}
				if(type=='COLUMN_TYPE_NUMERIC'){
					var reg=/^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
					if(parseInt(number)<defaultValue.length){
						alert("默认值的长度必须小于字段长度！");
						return false;
					}
					if(!reg.test(defaultValue)){
						alert("字段类型为小数的默认值只能是小数！");
						return false;
					}
				}
			} */
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>增加接口</h3>
			<hr>
		<%-- <form class="bs-example bs-example-form" role="form"
				action="${pageContext.request.contextPath}/createTable.hbc" method="post"> --%>
		<form class="form-horizontal" role="form" id="form"
				action="${pageContext.request.contextPath}/createInterManage/addTable.hbc" method="post">
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<h3 class="panel-title">接口属性</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">类名</label>
							<div class="col-md-11 col-sm-10">
								<input type="text" class="form-control" name="className" id="className">
							</div>
					</div>
					<div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">方法名</label>
							<div class="col-md-11 col-sm-10">
								<input type="text" class="form-control" name="methodName" id="methodName">
							</div>
					</div>
					<%-- <div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">表结构</label>
							<div class="col-md-11 col-sm-10">
								<select class="form-control" id="isMainTable" name="isMainTable">
									<option value="主表">主表</option>
									<option value="子表">子表</option>
								</select>
							</div>
					</div>
					<div class="form-group" id="sl">
						<input type="hidden" name="mainName" id="mainName">
						<label for="drive" class="control-label col-md-1 col-sm-2">选择主表</label>
						<div class="col-md-11 col-sm-10">
							<select class="form-control" id="mainId" name="mainId">
								<option value="">请选择</option>
								<c:forEach items="${list }" var="table" varStatus="tt">
									<option value="${table.id }">${table.tableName }&nbsp;&nbsp;${table.memo }</option>
								</c:forEach>
							</select>
						</div>
					</div> --%>
					<div class="form-group">
							<label for="drive" class="control-label col-md-1 col-sm-2">备注</label>
							<div class="col-md-11 col-sm-10">
								<input type="text" class="form-control" placeholder="备注" name="memo">
							</div>
					</div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<div class="captions">
						<h3 class="panel-title">方法属性</h3>
					</div>
					<div class="actions">
						<div class="btn-group">
							<button type="button" class="btn btn-default icons" id="add">增行</button>
							<button type="submit" class="btn btn-default icons">保存</button>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover formtable">
						<col width="150px;">
						<col width="150px;">
						<col width="90px;">
						<col width="0.1px;">
						<%-- <col width="90px;">
						<col width="90px;">
						<col width="120px;">
						<col width="90px;">
						<col width="90px;">
						<col width="120px;">
						<col width="120px;"> --%>
						<thead>
							<tr>
								<th>属性名</th>
								<th>属性类型</th>
								<th>属性值</th>
								<!-- <th>字段标题</th>
								<th>小数位数</th>
								<th>是否允许为空</th>
								<th>是否显示</th>
								<th>是否可编辑</th>
								<th>默认值</th>
								<th>操作</th> -->
							</tr>
						</thead>
						<tbody id="tr_num">
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