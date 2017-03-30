package com.hbcsoft.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.UserRoleDaoImp;
import com.hbcsoft.sys.entity.UserRole;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * UserRoleService接口实现类
 * @author churuifeng
 *
 */
@Service("userRoleService")
public class UserRoleServiceImp extends LogBaseClass<UserRoleServiceImp> implements UserRoleService {

	/**
	 * HbcsoftJT类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * UserRoleDao实现类
	 */
	@Autowired
	private transient UserRoleDaoImp userRoleDao;
	
	/**
	 * 添加方法
	 */
	@Override
	public void addUserRole(final UserRole userRole) throws HbcsoftException {
		this.getLogger().info("============addUserRole=========start==");
		userRoleDao.save(userRole, jt);
		this.getLogger().info("============addUserRole=========end==");
	}
    /**
     * 修改角色
     * @param userRole
     * @return
     * @throws HbcsoftException 
     */
	public void  updateUserRole (final UserRole userRole) throws HbcsoftException{
		this.getLogger().info("============updateUserRole=========start==");
		userRoleDao.update(userRole, jt);
		this.getLogger().info("============updateUserRole=========end==");
	}
	/**
	 * 根据userId删除
	 */
	@Override
	public void deleteByUserId(final String userId) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql=HbcsoftCache.getSqlValue("role_deleteByUserId");
		jt.update(sql,userId);
	}
}