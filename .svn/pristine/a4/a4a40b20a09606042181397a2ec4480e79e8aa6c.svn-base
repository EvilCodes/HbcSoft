package com.hbcsoft.sys.service;

import java.util.List;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Role;

/**
 * 角色service
 *
 */
public interface RoleService {

	/**
	 * 查询
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	List<Role> queryAll(Role role, String id, String companyId)
			throws HbcsoftException, InstantiationException, IllegalAccessException;

	/**
	 * 分页查询
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	List<Role> queryAllRoleType(Role role, final String id, final String companyId, int startRow, int pageSize)
			throws HbcsoftException, InstantiationException, IllegalAccessException;

	/**
	 * 查询资源
	 */
	Role queryResById(final String id, final String companyId) throws HbcsoftException;

	/**
	 * 修改
	 */
	int update(Role role) throws HbcsoftException;

	/**
	 * 多选
	 */
	Role queryRoleByIds(final String ids, final String companyId) throws HbcsoftException;

	/**
	 * 删除
	 * 
	 * @return
	 */
	int deletebyIds(Role entity) throws HbcsoftException;

	/**
	 * 保存数据
	 */
	void addRole(Role role) throws HbcsoftException;

	/**
	 * 角色菜单
	 * 
	 * @throws HbcsoftException
	 */
	List<TreeNode> findRoleMenu(final String companyId) throws HbcsoftException;

	/**
	 * 根据roleId查询所有角色
	 * 
	 * @param roleId
	 * @return
	 * @throws HbcsoftException
	 */
	Role queryRolesById(final String roleId, final String companyId) throws HbcsoftException;

	/**
	 * 根据用户id查询角色列表
	 * 
	 * @param userId
	 * @throws HbcsoftException
	 */
	List<Role> queryRoleListbyUserId(final String userId, final String companyId) throws HbcsoftException;

	/**
	 * 根据角色id查询角色
	 * 
	 * @param id
	 * @throws HbcsoftException
	 */
	Role getRole(String id) throws HbcsoftException;
	/**
	 * 根据ids和companyId查询角色
	 * 
	 * @throws HbcsoftException
	 */
	List<Role> queryAll(final String ids, final Long companyId) throws HbcsoftException;
}
