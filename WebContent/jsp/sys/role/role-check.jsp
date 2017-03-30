<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 点选功能中：角色多选 -->
<script type="text/javascript">
	var setting = {
		async : {
			enable : true,
			url : "${ctx}/sys/role/treegrid.hbc?ids=${ids}"
		},
		check: {
			enable: true //显示复选框  
		},
		data : {
			keep:{
				parent:true,
				leaf:true
				},
			simpleData : {
				enable: true,
				idKey:"id",
				pIdKey:"pId",
				rootPId:"-1"
			}
		},
		callback : {
			beforeClick : function (treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("roleTree");
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			},
			onClick : function(event, treeId, treeNode, clickFlag) {

			}
		}
	};
	$(function() {
		$.fn.zTree.init($("#roleTree"), setting);
	});
	$.fn.zTree.init($("#roleTree"), setting);
</script>

<div data-options="region:'center',fit:true,border:false"
	style="padding: 2px; overflow: hidden;">
	<ul id="roleTree" class="ztree"></ul>
</div>

