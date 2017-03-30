package com.hbcsoft.sys.service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.RoleResource;
/**
 * 给角色绑定资源接口
 * @author Administrator
 *
 */
public interface RoleResourceService {
	/**
	 * 保存数据
	 * @param RoleResource
	 * @return
	 * @throws HbcsoftException
	 */
	void addRoleResource(RoleResource RoleResource) throws HbcsoftException;
	/**
	 * 修改
	 * @param RoleResource
	 * @return
	 * @throws HbcsoftException
	 */
	void  updateRoleResource (final RoleResource RoleResource) throws HbcsoftException;
	/**
	 * 删除
	 * @param RoleId
	 * @throws HbcsoftException
	 */
	void deleteByRoleId(final String RoleId,final String companyId)throws HbcsoftException;
}
