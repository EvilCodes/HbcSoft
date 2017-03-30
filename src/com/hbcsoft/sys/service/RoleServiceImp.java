package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.RoleDaoImp;
import com.hbcsoft.sys.entity.Role;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * 角色实现类
 * */
@Service("RoleService")
public class RoleServiceImp extends LogBaseClass<RoleServiceImp> implements RoleService {
	/**
	 * jdbc模板类
	 * */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 角色dao实现类
	 * */
	@Autowired
	private transient RoleDaoImp roleDao;
	
	/**
	 * 查询所有
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public List<Role> queryAll(final Role role,final String id, final String companyId) throws HbcsoftException, InstantiationException, IllegalAccessException {
		this.getLogger().info("============queryAll=========start==");
		final String code = role.getCode();
		final String name = role.getName();
		final String sql = HbcsoftCache.getSqlValue("role_queryAllByCompanyid");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(companyId);
		if (id != null && !"".equals(id)) {
			sb.append(" AND ID = ?");
			params.add(id);
		}
		if (code != null && !"".equals(code)) {
			sb.append(" AND CODE LIKE ?");
			params.add("%"+code+"%");
		}
		if (name != null && !"".equals(name)) {
			sb.append( " AND NAME LIKE ?");
			params.add("%"+name+"%");
		}
		sb.append(" ORDER BY ORDERNO ");
		this.getLogger().info("查询所有SQL:" + sb);
		final List<Role> list = roleDao.queryAll(sb.toString(), jt, params.toArray(new Object[params.size()]));
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	/**
	 * 分页查询
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public List<Role> queryAllRoleType(final Role role,final String id,final String companyId,
			final int startRow,final int pageSize) throws HbcsoftException, InstantiationException, IllegalAccessException {
		this.getLogger().info("============queryAllRoleType=========start==");
		final String code = role.getCode();
		final String name = role.getName();
		final String sql = HbcsoftCache.getSqlValue("role_queryAllByCompanyid");
		final List<String> params = new ArrayList<String>();
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (id != null && !"".equals(id)) {
			sb.append(" AND ID = ?");
			params.add(id);
		}
		if (code != null && !"".equals(code)) {
			sb.append(" AND CODE LIKE ?");
			params.add("%"+code+"%");
		}
		if (name != null && !"".equals(name)) {
			sb.append(" AND NAME LIKE ?");
			params.add("%"+name+"%");
		}
		sb.append(" ORDER BY ORDERNO ");
		this.getLogger().info("分页和模糊查询SQL:" + sb);
		List<Role> list = null;
		list = roleDao.queryAll(sb.toString(), jt, startRow, pageSize, params.toArray(new Object[params.size()]));
		this.getLogger().info("============queryAllRoleType=========end==");
		return list;
	}
	
	/**
	 * 更新
	 */
		@Override
		public int update(final Role entity) {
			this.getLogger().info("============update=========end==");
			try {
				roleDao.update(entity, jt);
			} catch (HbcsoftException e) {
				this.getLogger().info(e);
			}
			this.getLogger().info("============update=========end==");
			return 1;
		}
	
	/**
	* 根据ids查询
	*/
	@Override
	public Role queryRoleByIds(final String ids,final String companyId) {
		this.getLogger().info("============queryRoleByIds=========start==");
		final String sql = HbcsoftCache.getSqlValue("role_queryRoleById");
		this.getLogger().info("SQL:" + sql);
		Role role = new Role();
		try {
			role = roleDao.query(sql, jt,ids,companyId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryRoleByIds=========end==");
		return role;
	}
	/**
	* 删除
	*/
	@Override
	public int deletebyIds(final Role entity) {
		this.getLogger().info("============deletebyIds=========start==");
		try {
			roleDao.delete(entity, jt);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============deletebyIds=========end==");
		return 1;
	}
	
	/**
	 * 保存表单表数据
	 */
	@Override
	public void addRole(final Role role) throws HbcsoftException {
		this.getLogger().info("============addRole=========start==");
		roleDao.save(role, jt);
		this.getLogger().info("============addRole=========end==");
	}
	/**
	* 角色菜单
	* @return
	 * @throws HbcsoftException 
	*/
	public  List<TreeNode> findRoleMenu(final String companyId) throws HbcsoftException{
		final List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		final String sql = HbcsoftCache.getSqlValue("role_queryAllByCompanyid");
		this.getLogger().info("sql:"+sql);
		try {
			final List<Role> roleList = roleDao.queryAll(sql, jt,companyId);
			final TreeNode parentTreeNode=new TreeNode();
			parentTreeNode.setName("系统角色");
			parentTreeNode.setId(Long.parseLong("-1"));
			parentTreeNode.setOpen(true);
			treeNodes.add(parentTreeNode);
			for(final Role role:roleList){
				final TreeNode node = getNode(role);
				treeNodes.add(node);
			}
		} catch (NumberFormatException e) {
			this.getLogger().info(e);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return treeNodes;
	}
	/**
	 * 获取角色菜单节点
	 */
	public TreeNode getNode(final Role role) throws HbcsoftException {
		final TreeNode treeNode=new TreeNode();
		treeNode.setName(role.getName());
		treeNode.setId(role.getId());
		treeNode.setpId(Long.parseLong("-1"));
		return treeNode;
	}
	/**
	 * 根据roleId查询所有角色	
	 * @param roleId
	 * @return
	 */
	public Role queryRolesById(final String roleId,final String companyId) {
	final String sql = HbcsoftCache.getSqlValue("role_queryRoleById");
	this.getLogger().info("sql:"+sql);
	Role role = null;
	try {
		role = roleDao.query(sql, jt, roleId,companyId);
	} catch (InstantiationException e) {
		this.getLogger().info(e);
	} catch (IllegalAccessException e) {
		this.getLogger().info(e);
	}
	return role;
	}
 
	/**
	 * 用户表里的设置角色
	 * @param userId
	 * @return
	 */
	public List<Role> queryRoleListbyUserId(final String userId,final String companyId){
		final String sql = HbcsoftCache.getSqlValue("role_queryAllRoleByUserId");
		this.getLogger().info("sql:"+sql);
		List<Role> list = new ArrayList<Role>();
			try {
				list = roleDao.queryAll(sql, jt, new Object[] { userId,companyId});
			} catch (InstantiationException e) {
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				this.getLogger().info(e);
			}
		return  list; 
	}
	/**
	 * 根据roleId查询当前资源信息 
	 */
	@Override
	public Role getRole(final String id) throws HbcsoftException {
		this.getLogger().info("============getRole=========start==");
		final String sql = HbcsoftCache.getSqlValue("role_queryById");
		this.getLogger().info(sql);
		Role role = null;
		try {
			role = roleDao.query(sql, jt,id);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============getRole=========end==");
		return role;
	}
	/**
	 * 查询资源*/
	@Override
	public Role queryResById(final String id,final String companyId)
			throws HbcsoftException {
		this.getLogger().info("============queryResById=========start==");
		final String sql = HbcsoftCache.getSqlValue("role_queryRoleByIdAndCompId");
		Role role = new Role();
			try {
			role = roleDao.query(sql, jt, id,companyId);
			} catch (InstantiationException e) {
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				this.getLogger().info(e);
			}
		this.getLogger().info("============queryResById=========end==");
		return role;
	}
	/**
	 * 根据ids和companyId查询角色
	 * 
	 * @throws HbcsoftException
	 */
	@Override
	public List<Role> queryAll(final String ids, final Long companyId) throws HbcsoftException 
	{
		this.getLogger().info("============queryAll=========start==");
		try {
			final List<Object> params = new ArrayList<Object>();
			final String sql = HbcsoftCache.getSqlValue("role_queryAllByCompanyid");
			final StringBuilder sb = new StringBuilder(sql);
			final String[] arrId = ids.split(HBCSoftConstant.CHARACTER_COMMA);
			
			params.add(companyId);
	
			for(int index = 0; index < arrId.length; index++)
			{
				if(index == 0)
				{
					sb.append( " AND ID IN ( ?");
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
	
			return roleDao.queryAll(sb.toString(), jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException("===RoleServiceImp====>queryAll", ErrorConstant.EXCEPTION_CODE, e);
		}
	}
}