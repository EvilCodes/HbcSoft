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
<title>编辑接口管理类名</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skin/<%=request.getSession().getAttribute("cssPath") %>.css" id="myCss">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
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
			+"<td  style='display:none'><input type='hidden' class='form-control' name='entityId'></td>"//id
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
			+"<td>"+"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button>"+
					"</td></tr>");//操作 */
					
			$("#tr_num").append("<tr id="+str+">"
					+"<td><input type='text' class='form-control' name='fieldName'></td>"//属性名
					+"<td><input type='text' class='form-control' name='fieldType'></td>"//属性类型
					+"<td><input type='text' class='form-control' name='fieldValue' id="+defaultid+"></td>"//属性值
					+"<td>"+
					"<button type='button' class='btn btn-default' onclick='deltr(\""+str+"\")'>删行</button>"+
					"</td></tr>");//操作 
					
		});
		
		$("#return").click(function(){
			window.location = "${pageContext.request.contextPath}/createInterManage/tableList.hbc";
		});
	});
	function deltr(str){
		$("#"+str).remove();//删除当前行
	}
	//动态添加日期控件
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
		/* var str = $("#isMainTable").val();
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
		}); */
		
		$("form").submit(function(e){
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
			//有数据改变，变更changeFlag的值为1
			var trList = $("tbody").children("tr");
			/* for(var i=0; i<trList.length; i++){
				var tdArr = trList.eq(i).find("td");
				var entityId = tdArr.eq(0).find("input").val();//子表id
				
				var type = tdArr.eq(3).find("select").val();//字段类型
				var number = tdArr.eq(4).find("input").val();//字段长度
				var decimalDigits = tdArr.eq(5).find("input").val();//小数位数
				var isNull = tdArr.eq(6).find("select").val()=='0' ? '是' : '否';//是否允许空
				var isDisplay = tdArr.eq(7).find("select").val()=='0' ? '是' : '否';//是否显示
				var isEdit = tdArr.eq(8).find("select").val()=='0' ? '是' : '否';//是否可编辑
				var defaultValue = tdArr.eq(9).find("input").val();//默认值
				if(number==''){
					number = 0;
				}
				var typeTitle = tdArr.eq(3).find("select").attr("title");
				var numberTitle = tdArr.eq(4).find("input").attr("title");//字段长度
				var decimalDigitsTitle = tdArr.eq(5).find("input").attr("title");//小数位数
				var isNullTitle = tdArr.eq(6).find("select").attr("title");//是否允许空
				var isDisplayTitle = tdArr.eq(7).find("select").attr("title");//是否显示
				var isEditTitle = tdArr.eq(8).find("select").attr("title");//是否可编辑
				var defaultValueTitle = tdArr.eq(9).find("input").attr("title");//默认值
				if(entityId != ''){
					if(type!=typeTitle || number!=numberTitle || decimalDigits!=decimalDigitsTitle 
							|| isNull!=isNullTitle || isDisplay!=isDisplayTitle || isEdit!=isEditTitle 
							|| defaultValue!=defaultValueTitle){
						tdArr.eq(10).find("input").val(1);//0:新增 1：变更 2：删除 3：不变
					}
				}
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
	
	function delRow(id){
		if(confirm("字段已经存在，确认删除吗？")){
			$("#rowid").val(id);
			$("#chuanzhi").val($("#dezhi").val());
			$("#delForm").submit();
		}
	}
	/* $(document).ready(function(){
		var listEntity = $("tbody").children("tr");
		for(var i=0; i<listEntity.length; i++){
			var tdArr = listEntity.eq(i).find("td");
			var type = tdArr.eq(3).find("select").val();//字段类型
			if(type=='COLUMN_TYPE_DATE'){
				tdArr.eq(9).find("input").focus(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			}
			if(type=='COLUMN_TYPE_TIMESTAMP'){
				tdArr.eq(9).find("input").focus(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
			}
		}
	}); */
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>编辑接口管理类</h3>
			<hr>
	<form id="delForm" class="bs-example bs-example-form" role="form"
				action="${pageContext.request.contextPath}/createInterManage/delRow.hbc" method="post">
		<input type="hidden" name="rowid" id="rowid">
		<input type="hidden" name="chuanzhi" id="chuanzhi">
		<input type="hidden" name="flag" id="flag" value="true">
	</form>
		<form class="form-horizontal" role="form" id="form"
				action="${pageContext.request.contextPath}/createInterManage/edit.hbc" method="post">
			<input type="hidden" name="id" value="${table.id }" id="dezhi">
			<div class="panel panel-primary">
				<div class="panel-heading both">
					<h3 class="panel-title">类名称</h3>
				</div>
				<div class="panel-body">
						<div class="form-group">
								<label for="drive" class="control-label col-md-1 col-sm-2">类名</label>
								<div class="col-md-11 col-sm-10">
									<input type="text" class="form-control" name="className" value=${table.className } readonly>
								</div>
						</div>
					<div class="panel-body">
						<div class="form-group">
								<label for="drive" class="control-label col-md-1 col-sm-2">方法名</label>
								<div class="col-md-11 col-sm-10">
									<input type="text" class="form-control" name="methodName" value=${table.methodName } readonly>
								</div>
						</div>
						<div class="form-group">
								<label for="drive" class="control-label col-md-1 col-sm-2">备注</label>
								<div class="col-md-11 col-sm-10">
									<input type="text" class="form-control" placeholder="备注" name="memo" value="${table.memo }">
								</div>
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
							<button type="button" class="btn btn-default icons" id="return">返回</button>
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
						<thead>
							<tr>
								<th>属性名</th>
								<th>属性类型</th>
								<th>属性值</th>
							</tr>
						</thead>
						<tbody id="tr_num">
							<c:if test="${not empty listEntity }">
								<c:forEach items="${listEntity }" var="entity" varStatus="status">
									<tr>
										<td style="display:none"><input type="hidden" class="form-control" name="entityId" value="${entity.id }"></td>
										<td><input type="text" class="form-control" placeholder="FIELDNAME" name="fieldName" value="${entity.fieldName }" title="${entity.fieldName}"></td>
										<td><input type="text" class="form-control" placeholder="FIELDTYPE" name="fieldType" value="${entity.fieldType }" title="${entity.fieldType}"></td>
										<td><input type="text" class="form-control" placeholder="FIELDVALUE" name="fieldValue" value="${entity.fieldValue }" title="${entity.fieldValue}"></td>
										
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