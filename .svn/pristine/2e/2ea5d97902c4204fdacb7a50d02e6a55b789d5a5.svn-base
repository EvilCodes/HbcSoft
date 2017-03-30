package com.hbcsoft.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.RoleResourceDaoImp;
import com.hbcsoft.sys.entity.RoleResource;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * 角色绑定资源实现类
 *
 */
@Service("RoleResourceService")
public class RoleResourceServiceImp extends LogBaseClass<RoleResourceServiceImp> implements RoleResourceService {

	/**
	 * HbcsoftJT类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * RoleResourceDao实现类
	 */
	@Autowired
	private transient RoleResourceDaoImp roleResourceDao;
	
	/**
	* 添加方法
	*/
	@Override
	public void addRoleResource(final RoleResource roleResource) throws HbcsoftException {
		this.getLogger().info("============addRoleResource=========start==");
		roleResourceDao.save(roleResource, jt);
		this.getLogger().info("============addRoleResource=========end==");
	}
	/**
	* 修改
	* @return
	* @throws HbcsoftException 
	*/
	public void  updateRoleResource (final RoleResource roleResource) throws HbcsoftException{
		this.getLogger().info("============updateRoleResource=========start==");
		roleResourceDao.update(roleResource, jt);
		this.getLogger().info("============updateRoleResource=========end==");
	}
	/**
	* 删除
	*/
	@Override
	public void deleteByRoleId(final String roleId,final String companyId) throws HbcsoftException {
		this.getLogger().info("============deleteByRoleId=========start==");
		final String sql=HbcsoftCache.getSqlValue("role_deleteByRoleId");
		jt.update(sql,roleId,companyId);
		this.getLogger().info("============deleteByRoleId=========end==");
	}
}