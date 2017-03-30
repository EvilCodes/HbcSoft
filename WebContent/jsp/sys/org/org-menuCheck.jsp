<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 点选功能中：机构多选 -->
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "${ctx}/sys/org/orgTreegrid.hbc?ids=${ids}"
		},
		check: {
			enable: true //显示复选框  
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : function (treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("orgTreeMenu");
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			},
			onClick : function(event, treeId, treeNode, clickFlag) {

			}
		}
	};
	$(function() {
		$.fn.zTree.init($("#orgTreeMenu"), setting);
	});
	$.fn.zTree.init($("#orgTreeMenu"), setting);
</script>

<div data-options="region:'center',fit:true,border:false"
	style="padding: 2px; overflow: hidden;">
	<ul id="orgTreeMenu" class="ztree"></ul>
</div>

