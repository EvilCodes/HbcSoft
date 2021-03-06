package com.hbcsoft.sys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.ReadProperties;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.ResourceDaoImp;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;

/**
 * 资源管理-菜单业务逻辑
 */
@Service("ResourceService")
public class ResourceServiceImp extends BaseController<ResourceServiceImp> implements ResourceService {
	/**
	 * jdbc模板
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 实现类注入
	 */
	@Autowired
	private transient ResourceDaoImp reDaoImpl;
	/**
	 * request注入
	 */
	@Autowired
	private transient HttpServletRequest request;

	/**
	 * 生成树形菜单节点
	 * @throws IOException 
	 */
	@Override
	public List<TreeNode> getResourceTreeMenu(final String companyId) throws HbcsoftException, IOException {
		this.getLogger().info("============getResourceTreeMenu=========start==");
		final List<TreeNode> nodes = new ArrayList<TreeNode>();
		final TreeNode node = getNode(companyId);
		nodes.add(node);
		this.getLogger().info("============getResourceTreeMenu=========end==");
		return nodes;
	}
	/**
	 * 获取节点
	 * @throws IOException 
	 */
	public TreeNode getNode(final String companyId) throws HbcsoftException, IOException{
		List<Resource> list = new ArrayList<Resource>();
		TreeNode node = new TreeNode();
		/**把导航菜单（parentResourceId=null）放到根节点*/
		list = getResByParentResId(null, companyId);
		for (final Resource resource : list) {
			if (null != resource) {
				node = this.resToTreeNode(resource, companyId);
			}
		}
		return node;
	}

	/**
	 * 根据父类id查询单位
	 * @throws IOException 
	 */
	public List<Resource> getResByParentResId(final String parentResourceId, final String companyId)
			throws HbcsoftException, IOException {
		this.getLogger().info("============getResByParentResId=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_queryResByPid");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(parentResourceId);
		params.add(companyId);
		List<Resource> list = new ArrayList<Resource>();
		final ReadProperties rp = new ReadProperties();
		final List<String> codes = rp.getCode(request);
		sb.append(" AND CODE NOT IN ( ");
		for (int j = 0; j < codes.size(); j++) {
			if("".equals(codes.get(j))){
				sb.append("''");
				break;
			}
			sb.append(" ? ");
			if(j!=codes.size()-1){
				sb.append(", ");
			}
			params.add(codes.get(j));
		}
		sb.append(" )");
		list = (List<Resource>) reDaoImpl.queryResourceByPid(sb.toString(), jt,params.toArray(new String[params.size()]));
		this.getLogger().info("============getResByParentResId=========end==");
		return list;
	}

	/**
	 * 单位转换成树形节点
	 * @throws IOException 
	 */
	public TreeNode resToTreeNode(final Resource resource, final String companyId) throws IOException {
		this.getLogger().info("============resToTreeNode=========start==");
		final TreeNode treeNode = new TreeNode(resource.getId(), resource.getName(), "", false);
		final List<TreeNode> childrenTreeNodes = new ArrayList<TreeNode>();
		final List<Resource> listchild = getChildrendtType(resource.getId(), companyId);
		for (final Resource childrenCorp : listchild) {
			final TreeNode node = resToTreeNode(childrenCorp, companyId);
			this.getLogger().info("node:" + node);
			if (node != null) {
				childrenTreeNodes.add(node);
			}
		}
		treeNode.setChildren(childrenTreeNodes);
		this.getLogger().info("============resToTreeNode=========end==");
		return treeNode;
	}

	/**
	 * 获取子资源类型
	 * @throws IOException 
	 */
	public List<Resource> getChildrendtType(final Long id, final String companyid) throws IOException {
		this.getLogger().info("============getChildrendtType=========start==");
		List<Resource> list = new ArrayList<Resource>();
		final String sql = HbcsoftCache.getSqlValue("menu_getChildrendtType");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(String.valueOf(id));
		params.add(companyid);
		/**父节点的id赋给子节点的parentResourceId，返回所有子节点*/
		final ReadProperties rp = new ReadProperties();
		final List<String> codes = rp.getCode(request);
		sb.append(" AND CODE NOT IN ( ");
		for (int j = 0; j < codes.size(); j++) {
			if("".equals(codes.get(j))){
				sb.append("''");
				break;
			}
			sb.append(" ? ");
			if(j!=codes.size()-1){
				sb.append(", ");
			}
			params.add(codes.get(j));
		}
		sb.append(" )");
		list = (List<Resource>) reDaoImpl.queryResourceByPid(sb.toString(), jt,params.toArray(new String[params.size()]));
		this.getLogger().info("============getChildrendtType=========end==");
		return list;
	}

	/**
	 * 根据父类id查询单位
	 * @param resourceId
	 * @param uid
	 * @return
	 * @throws IOException 
	 */
	@Override
	public List<Resource> getResourceByParentId(final Long resourceId, final Long uid) throws HbcsoftException, IOException {
		this.getLogger().info("============getResourceByParentId=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_resourceMenu");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		List<Resource> list = new ArrayList<Resource>();
		final SessionInfo sess = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final Company com = sess.getCompany();
		final Long comId = com.getId();
		final ReadProperties rp = new ReadProperties();
		final List<String> codes = rp.getCode(request);
		params.add(Long.toString(uid));
		params.add(Long.toString(resourceId));
		params.add(Long.toString(comId));
		params.add(Long.toString(comId));
		sb.append(" AND RE.CODE NOT IN ( ");
		for (int j = 0; j < codes.size(); j++) {
			if("".equals(codes.get(j))){
				sb.append("''");
				break;
			}
			sb.append(" ? ");
			if(j!=codes.size()-1){
				sb.append(", ");
			}
			params.add(codes.get(j));
		}
		sb.append(" )");
		this.getLogger().info("=============>" + sb.toString());
		// reDaoImpl.queryAll
		list = reDaoImpl.queryResourceByPid(sb.toString(), jt,params.toArray(new String[params.size()]));
		this.getLogger().info("============getResourceByParentId=========end==");
		return list;
	}

	/**
	 * 查询当前用户有什么导航栏菜单的权限
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws HbcsoftException
	 * @throws IOException 
	 */
	@Override
	public List<Resource> getRootMenu(final Long id) throws HbcsoftException, IOException {
		this.getLogger().info("============getRootMenu=========start==");
		final Resource res = getRootMenuFirst();
		final List<Resource> reList = new ArrayList<Resource>();
		try {
			final Resource resToTreeNode = this.resourceToTreeNode(res, id);
			reList.add(resToTreeNode);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("-------------getRootMenu----------------");
		return reList;
	}

	/**
	 * 获取根菜单
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Override
	public Resource getRootMenuFirst() throws HbcsoftException {
		this.getLogger().info("============getLoginUser=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_rootMenu");
		this.getLogger().info("=============>" + sql);
		Resource re = null;
		try {
			re = reDaoImpl.query(sql, jt);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============getLoginUser=========end==");
		return re;
	}

	/**
	 * 判断资源类型
	 * @throws HbcsoftException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 */
	@Override
	public Resource resourceToTreeNode(final Resource res, final Long id)
			throws HbcsoftException, IllegalAccessException, InstantiationException, IOException {
		this.getLogger().info("============resourceToTreeNode=========start==");
		// 判断当前的资源类型是否为“菜单”
		if (res.getResourceType() == 0) {
			final List<Resource> childrenTreeNodes = new ArrayList<Resource>();
			final List<Resource> list = this.getResourceByParentId(res.getId(), id);
			for (final Resource childrenResource : list) {
				if (childrenResource != null) {
					final Resource rttn = resourceToTreeNode(childrenResource, id);
					childrenTreeNodes.add(rttn);
				}
			}
			res.setChildrenResource(childrenTreeNodes);
		}
			/*else { res = new Resource(); }*/
		this.getLogger().info("============resourceToTreeNode=========start==");
		return res;
	}

	/**
	 * 根据resourceId查询资源
	 * @param resourceId
	 * @return
	 */
	public Resource queryResById(final String resourceId) {
		final String sql = HbcsoftCache.getSqlValue("menu_queryResById");
		this.getLogger().info("sql:" + sql);
		Resource resource = null;
		try {
			resource = reDaoImpl.query(sql, jt, resourceId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return resource;
	}

	/**
	 * 根据roleId查询资源
	 * @throws IOException 
	 * @throws HbcsoftException
	 */
	@Override
	public List<Resource> queryResourceListbyRoleId(final String roleId, final String companyId)
			throws HbcsoftException, IOException {
		final String sql = HbcsoftCache.getSqlValue("menu_queryResourceByRoleId");
		List<Resource> list = new ArrayList<Resource>();
		list = reDaoImpl.queryAllResource(sql, jt,new String[] { roleId, companyId });
		return list;
	}

	/**
	 * 获取资源菜单
	 * @throws IOException 
	 */
	@Override
	public List<TreeNode> findResourceMenu(final String companyId) throws HbcsoftException, IOException {
		final List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		final String sql = HbcsoftCache.getSqlValue("menu_queryAllByCompanyId");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		try {
			final ReadProperties rp = new ReadProperties();
			final List<String> codes = rp.getCode(request);
			params.add(companyId);
			sb.append(" AND CODE NOT IN ( ");
			for (int j = 0; j < codes.size(); j++) {
				if("".equals(codes.get(j))){
					sb.append("''");
					break;
				}
				sb.append(" ? ");
				if(j!=codes.size()-1){
					sb.append(", ");
				}
				params.add(codes.get(j));
			}
			sb.append(" )");
			final List<Resource> list = reDaoImpl.queryAll(sb.toString(), jt, params.toArray(new Object[params.size()]));
			for (final Resource resource : list) {
				final TreeNode node = getNode(resource);
				treeNodes.add(node);
			}
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return treeNodes;
	}
	/**
	 * 获取资源菜单节点
	 */
	public TreeNode getNode(final Resource resource) throws HbcsoftException {
		final TreeNode treeNode = new TreeNode();
		final Long id = resource.getId();
		treeNode.setId(id);
		treeNode.setName(resource.getName());
		if ("1".equals(id)) {
			treeNode.setpId(Long.parseLong("-1"));
		} else {
			treeNode.setpId(resource.getParentResourceId());
		}
		return treeNode;
	}
	/**
	 * 获取资源
	 */
	@Override
	public List<Resource> findResource(final String companyId) throws HbcsoftException, IOException {
		final String sql = HbcsoftCache.getSqlValue("menu_queryAllByCompanyId");
		this.getLogger().info("SQL:" + sql);
		List<Resource> list=new ArrayList<Resource>();
		try {
			list = reDaoImpl.queryAll(sql, jt, companyId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return list;
	}
	

}