<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var setting = {
		async: {
			enable: true,
			url:"${ctx}/sys/resource/srcTreeGrid.hbc"
		},
		data:{
			simpleData : {
				enable: true
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {

			}
		}
	};
	$(function() {
		$.fn.zTree.init($("#resourceTreeMenu"), setting);
	});
</script>

<div data-options="region:'center',fit:true,border:false" style="padding:2px;overflow: hidden;">
	<ul id="resourceTreeMenu" class="ztree"></ul>
</div>

 