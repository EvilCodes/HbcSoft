<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div data-option="region:'center'" style="padding:2px;overflow: hidden;">
	<form id="dataDict_form" method="post">
		<input type="hidden" id="id" name="id" value="${dataDict.id}">
		<input type="hidden" id="createID" name="createID" value="${dataDict.createID}"/>
		<table class="table">
			<tr>
				<td align="right" width="15%">字典类型名称：</td>
				<td width="35%">
					<input type="hidden" id="dictTypeId" name="dictType.id" value="${dataDict.dictType.id}"/> 
					<input type="hidden" id="dictTypeCode" name="dictType.dtCode" value="${dataDict.dictType.dtCode}"/> 
					<input type="hidden" id="dictTypeSort" name="dictType.sort" value="${dataDict.dictType.sort}"/> 
					
					<input type="text" id="dictTypeName" class="easyui-textbox input" name="dictType.dtName" value="${dataDict.dictType.dtName}"
					 data-options="editable:false" required="required"  style="width:95%"/> 
				</td> 
				<td align="right" width="10%">编码：</td>
				<td width="40%">
					<input id="dictCode" name="dictCode" class="easyui-textbox input" value="${dataDict.dictCode}" 
					data-options="required:true" style="width:95%"/>
				</td>
			</tr>  
			<tr>
				<td align="right" width="10%">名称：</td>
				<td width="40%">
					<input id="dictName" name="dictName" class="easyui-textbox input" value="${dataDict.dictName}"
					 data-options="required:true" style="width:95%">
				</td>
				<td align="right" width="10%">排序：</td>
				<td width="40%">
					<input id="sort" name="sort" maxLength="50" class="easyui-numberbox input" value="${dataDict.sort}" 
					data-options="required:true,min:0,precision:0,missingMessage:'数字类型,整数类型。'" style="width:95%"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="10%">状态：</td>
				<td width="40%">
				   <select id="enable" name="enable" class="easyui-combobox" style="width:100%;" data-options="panelHeight:'auto',editable:false" required="required">
					 <option value="0"  <c:if test="${dataDict.enable eq 0 }">selected</c:if> >启用</option>
					 <option value="1"  <c:if test="${dataDict.enable eq 1 }">selected</c:if> >停用</option>
			       </select> 
			      </td>
			</tr>
			
		</table>
	</form>
 </div>

