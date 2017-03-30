<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/sys/userList.hbc"
		method="post" class="form-horizontal" commandName="user"
		modelAttribute="user">
		用户名：<input type="text" class="form-control inline" style="width: 15%"
			name="yhm" id="yhm" value="${yhm }" /> 真实姓名：<input type="text"
			class="form-control inline" style="width: 15%" name="zsxm" id="zsxm"
			value="${zsxm }" />
		<button type="submit" class="btn blue">查询</button>
		<button type="button" class="btn blue" onclick="result(this)">重置</button>
		<br /> &nbsp;
		<table class="table table-striped table-bordered table-hover"
			id="tableList">
			<thead>
				<tr>
					<th class="table-checkbox"><input type="checkbox"
						class="group-checkable" /></th>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>密码</th>
					<th>联系方式</th>
					<th>Email</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr class="odd gradeX">
						<td><input type="checkbox" name="id" class="checkboxes"
							value="${user.id}" /></td>
						<td>${user.yhm}</td>
						<td>${user.zsxm}</td>
						<td>${user.mm}</td>
						<td>${user.lxfs}</td>
						<td>${user.email}</td>
						<td><c:if test="${user.zt==1}">
								<span class="label label-sm label-success"> 正常 </span>
							</c:if> <c:if test="${user.zt==2}">
								<span class="label label-sm label-danger"> 冻结 </span>
							</c:if></td>
						<td><a href="javascript:void(0);"
							onclick="uppass('${user.id}')">密码重置</a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty userList}">
					<tr class="odd gradeX">
						<td colspan="8" align="center"><font color="red">未查询出下级组织机构信息</font>
						</td>
					</tr>
				</c:if>

			</tbody>
		</table>
	</form>
</body>
</html>