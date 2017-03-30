<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var setting = {	
		async: {
			enable: true,
			url:"${ctx}/sys/user/roleTreegrid.hbc?id=${userId}",
			autoParam:["id", "name=n", "level=lv"]
		},
		check: {
			enable: true
		},
		data:{
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
			onClick : function(event, treeId, treeNode, clickFlag) {

			}
		}
	};
	$(function() {
		$.fn.zTree.init($("#roleTree"), setting);
	});

</script>

<div data-options="region:'center',fit:true,border:false" style="padding:2px;overflow: hidden;">
	<ul id="roleTree" class="ztree"></ul>
</div>