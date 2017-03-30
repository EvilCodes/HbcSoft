<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="easyui-layout" data-options="fit:true,border:false">
    <form id="log_search_form">
	    <table class="grid" style="width:100%">
	    	<tr>
				<td align="right">日志类型：</td>
				<td>
					<select id="type" name="type" style="width:100%;" class="easyui-combobox" data-options="panelHeight:'auto'">
						<option value="0">安全日志</option>
						<option value="1">操作日志</option>
					</select>
				</td>
				<td align="right">登录名：</td>
				<td>
					<input type="text" id="loginName" name="loginName" class="easyui-textbox input">
				</td>
			</tr>   
	    </table>
     </form>
</div>