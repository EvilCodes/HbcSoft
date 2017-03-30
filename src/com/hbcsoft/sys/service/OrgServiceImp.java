package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.OrgDaoImp;
import com.hbcsoft.sys.dao.UserDaoImp;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
* 机构
* 
* @author Administrator
*
*/
@Transactional
@Service("OrgService")
public class OrgServiceImp extends LogBaseClass<OrgServiceImp> implements
		OrgService {
	/**
	* HbcsoftJT
	*/
	@Autowired
	private transient HbcsoftJT jt;
	/**
	* 字典类型daoimp
	*/
	@Autowired
	private transient OrgDaoImp orgDao;
	/**
	* 用户
	*/
	@Autowired
	private transient UserDaoImp userDao;

	/**
	* 根据id查询某条数据，显示到修改页面上
	*/
	@Override
	public Org queryOrgById(final String id) {
		this.getLogger().info("============queryOrgById=========start==");
		final String sql = HbcsoftCache.getSqlValue("org_queryById");
		Org org = new Org();
		try {
			org = orgDao.query(sql, jt, id);
		} catch (InstantiationException e) {
			// e.printStackTrace();
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryOrgById=========end==");
		return org;
	}

	/**
	* 保存
	*/
	@Override
	public int save(final Org entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============save=========start==");
		try {
			orgDao.save(entity, jt);

		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============save=========end==");
		return 1;
	}

	/**
	* 修改
	*/
	@Override
	public int update(final Org entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============update=========end==");
		try {
			orgDao.update(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============update=========end==");
		return 1;
	}

	/**
	* 删除
	*/
	public int deletebyIds(final Org entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============deletebyIds=========start==");
		try {
			orgDao.delete(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============deletebyIds=========end==");
		return 1;
	}

	/**
	* 根据ids查询,再实现删除
	*/

	public Org queryDataByIds(final String ids) {
		this.getLogger().info("============queryDataByIds=========start==");
		final String sql = HbcsoftCache.getSqlValue("org_queryById");
		Org org = new Org();
		try {
			org = orgDao.query(sql, jt, ids);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryDataByIds=========end==");
		return org;
	}

	private List<Org> returnListCount(final List<String> params,
			final String sql) {// 行
		List<Org> list = new ArrayList<Org>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		if (paramsize || pars) {
			try {
				list = (List<Org>) orgDao.queryAll(sql, jt,params.toArray(new Object[params.size()]));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		} else {
			try {
				list = (List<Org>) orgDao.queryAll(sql, jt,params.toArray());//new String[params.size()]
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		return list;
	}

	private List<Org> returnListCount(final List<String> params,
			final String sql, final int startRow, final int pageSize) {// 分页
		List<Org> list = new ArrayList<Org>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		if (paramsize || pars) {
			try {
				list = (List<Org>) orgDao.queryAll(sql, jt, startRow, pageSize);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		} else {
			try {
				list = (List<Org>) orgDao.queryAll(sql, jt, startRow, pageSize,params.toArray(new Object[params.size()]));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		return list;
	}

	/**
	* 查询所有的数据
	*/
	@Override
	public List<Org> queryAll(final Org org, final String parentId,
			final String companyId) {
		this.getLogger().info("============queryAll=========start==");
		// List<DictType> list = new ArrayList<DictType>();
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("org_queryAll");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (parentId != null && !"".equals(parentId)) {
			sb.append(" AND parentId = ?");
			//sql += " AND parentId = ?";
			params.add(parentId);
		}

		if (org.getCode() != null && !"".equals(org.getCode())) {
			sb.append( " AND CODE LIKE ? ");
			//sql += " AND CODE LIKE ? ";
			params.add("%" + org.getCode() + "%");
		}
		if (org.getName() != null && !"".equals(org.getName())) {
			sb.append( " AND NAME LIKE ? ");
			//sql += " AND NAME LIKE ? ";
			params.add("%" + org.getName() + "%");
		}
		sb.append("  ORDER BY orderNo ");
		//sql += "  ORDER BY orderNo ";
		this.getLogger().info("============queryAll=========end==");
		return this.returnListCount(params, sb.toString());
	}

	/**
	* 分页查询
	*/
	@Override
	public List<Org> queryAllOrg(final Org org, final String parentId,
			final String companyId, final int startRow, final int pageSize) {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllOrg=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("org_queryAll");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (parentId != null && !"".equals(parentId)) {
			sb.append( " AND parentId = ?");
			//sql += " AND parentId = ?";
			params.add(parentId);
		}

		if (org.getCode() != null && !"".equals(org.getCode())) {
			sb.append( " AND CODE LIKE ? ");
			//sql += " AND CODE LIKE ? ";
			params.add("%" + org.getCode() + "%");
		}
		if (org.getName() != null && !"".equals(org.getName())) {
			sb.append( " AND NAME LIKE ? ");
			//sql += " AND NAME LIKE ? ";
			params.add("%" + org.getName() + "%");
		}
		sb.append("  ORDER BY orderNo ");
		//sql += "  ORDER BY orderNo ";

		this.getLogger().info("============queryAllOrg=========end==");
		return this.returnListCount(params, sb.toString(), startRow, pageSize);
	}

	/**
	* 生成树形菜单节点
	*/

	public List<TreeNode> getOrgTreeMenu(final String companyId)
			throws HbcsoftException {
		this.getLogger().info("============getOrgTreeMenu=========start==");
		final List<TreeNode> nodes = new ArrayList<TreeNode>();
		this.getNodes(nodes, companyId);
		this.getLogger().info("============getOrgTreeMenu=========end==");
		return nodes;
	}

	private void getNodes(final List<TreeNode> nodes, final String companyId)
			throws HbcsoftException {
		List<Org> orgList;
		orgList = getOrgByParentId(null, companyId);
		for (final Org org : orgList) {
			if (null != org) {
				final TreeNode node = this.orgToTreeNode(org, companyId);
				if (null != node) {
					nodes.add(node);
				}
			}
		}

	}

	/**
	* 根据父类id查询单位
	* 
	* @param parentId
	* @return
	*/
	public List<Org> getOrgByParentId(final String parentId,
			final String companyId) throws HbcsoftException {
		this.getLogger().info("============getOrgByParentId=========start==");
		//Object[] objs;
		final String sql = HbcsoftCache.getSqlValue("org_queryOrgByPid");
//		final StringBuilder sb = new StringBuilder(sql);
//		if (null == parentId) {
//			sb.append(" = 0");
//			//sql += "= 0 ";
//			objs = new Object[] {};
//		} else {
//			sb.append(" =? ");
//			//sql += " =? ";
//			objs = new Object[] { parentId };
//		}
//		sb.append(" order by orderNo asc ");
//		//sql += " order by orderNo asc ";
		List<Org> list;
		list = (List<Org>) orgDao.queryOrgByPid(sql, jt, companyId);
		this.getLogger().info("============getOrgByParentId=========end==");
		return list;
	}

	/**
	* 单位转换成树形节点
	* 
	* @param corp
	* @return
	*/
	public TreeNode orgToTreeNode(final Org org, final String companyId) {
		this.getLogger().info("============orgToTreeNode=========start==");
		final TreeNode treeNode = new TreeNode(org.getId(), org.getName(), "",
				false, org.getCode());
		final List<TreeNode> childrenTreeNodes = new ArrayList<TreeNode>();
		final List<Org> listchild = getChildrendOrg(org.getId(), companyId);
		for (final Org childrenCorp : listchild) {
			final TreeNode node = orgToTreeNode(childrenCorp, companyId);
			if (node != null) {
				childrenTreeNodes.add(node);
			}
		}
		treeNode.setChildren(childrenTreeNodes);
		this.getLogger().info("============orgToTreeNode=========end==");
		return treeNode;
	}

	/**
	* 得到子机构
	* 
	* @param id
	* @return
	*/
	public List<Org> getChildrendOrg(final Long id, final String companyId) {
		this.getLogger().info("============getChildrendOrg=========start==");
		List<Org> list;
		final String sql = HbcsoftCache.getSqlValue("org_getChildrendOrg");
		list = (List<Org>) orgDao.queryOrgByPid(sql, jt, String.valueOf(id),
				companyId);
		this.getLogger().info("============getChildrendOrg=========end==");
		return list;
	}

	/**
	* 查询上级机构，显示在列表上
	* 
	* @param id
	* @return
	*/
	public Org getParentOrgByParentId(final String id) {
		this.getLogger().info(
				"============getParentOrgByParentId=========start==");
		Org org = new Org();
		final String sql = HbcsoftCache.getSqlValue("org_queryById");
		try {
			org = orgDao.query(sql, jt, id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}

		this.getLogger().info(
				"============getParentOrgByParentId=========end==");
		return org;
	}

	/**
	* 根据userid查询用户
	*/
	public User getUserByUserId(final String userId) {
		User user = new User();
		final String sql = HbcsoftCache.getSqlValue("longin_queryById");
		try {
			user = userDao.query(sql, jt, userId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return user;
	}


	/**
	* 根据公司id，主键id，查询编码和名称是否有重复的（修改时判断）
	*/
	@Override
	public Org queryByOrgCodeNameId(final Org orgg, final String companyId)
			throws HbcsoftException {
		// String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		Org org = new Org();
		final String sql = HbcsoftCache.getSqlValue("org_queryByOrgCodeNameId");
		try {
			org = orgDao.query(sql, jt, orgg.getCode(), orgg.getName(),
					companyId, String.valueOf(orgg.getId()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return org;
	}
	/**
	 * 查询同步部门信息
	 */
	@Override
	public List<Org> findAllCloneDepts(final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<Org> list = new ArrayList<Org>();
		try {
			final String sql = HbcsoftCache.getSqlValue("org_queryAllCloneDepts");
			list = orgDao.queryAll(sql, jt, companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return list;
	}

	@Override
	public List<Org> queryAll(final String ids,
			final Long companyId) throws HbcsoftException {
		this.getLogger().info("============queryAll=========start==");
		try {
			final List<Object> params = new ArrayList<Object>();
			final String sql = HbcsoftCache.getSqlValue("org_queryAll");
			final StringBuilder sb = new StringBuilder(sql);
			final String[] arrId = ids.split(HBCSoftConstant.CHARACTER_COMMA);
			
			params.add(companyId);
	
			for(int index = 0; index < arrId.length; index++)
			{
				if(index == 0)
				{
					sb.append( " AND CODE IN ( ?");
					params.add(arrId[index]);
				}else{
					sb.append( " ,? ");
					params.add(arrId[index]);
				}
				if(index == arrId.length - 1)
				{
					sb.append( " ) ");
				}
			}
			sb.append("  ORDER BY orderNo ");
	
			return orgDao.queryAll(sb.toString(), jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException("===OrgServiceImp====>queryAll", ErrorConstant.EXCEPTION_CODE, e);
		}
	}
	/**
	 * 查根节点数据
	 * @param companyId
	 * @param parentId
	 * @return 
	 * @throws HbcsoftException 
	 */
	@Override
	public Org findRootNode(final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("org_queryRootNode");
		Org org;
		try {
			org = orgDao.query(sql, jt,companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return org;
	}
	/**
	 * 查同步部门编码
	 * @throws HbcsoftException 
	 */
	@Override
	public Long findDeptCodeByName(final String cdeptName, final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("org_queryDeptCodeByName");
		Long deptCode = 0L;
		try {
			final Org org = orgDao.query(sql, jt,companyId,cdeptName);
			if(null != org && null != org.getCode()){
				deptCode = org.getId();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return deptCode;
	}

	/**
	 * 根据编码和公司id查询名称
	 */
	@Override
	public String getOrgName(String code, long companyId) throws HbcsoftException {
		final String sql = HbcsoftCache.getSqlValue("org_queryDeptCodeByCode");
		Org org = new Org();
		try {
			org = orgDao.query(sql, jt,companyId,code);
		} catch (InstantiationException e) {
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalAccessException e) {
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return org.getName();
	}

}
