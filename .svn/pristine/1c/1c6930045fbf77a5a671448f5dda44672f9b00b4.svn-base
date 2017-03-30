package com.hbcsoft.sys.service;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.User;

	/**
	* 机构
	* 
	* @author Administrator
	*
	*/
@Repository
public interface OrgService {
	/**
	* 树形
	* 
	* @return
	* @throws HbcsoftException
	*/
	List<TreeNode> getOrgTreeMenu(final String companyId)
			throws HbcsoftException;

	/**
	* 查询所有
	* @param org
	* @param parentId
	* @return
	* @throws HbcsoftException
	*/
	List<Org> queryAll(final Org org, final String parentId,
			final String companyId) throws HbcsoftException;

	/**
	* 分页查询
	* 
	* @param code
	* @param name
	* @param parentId
	* @param startRow
	* 
	*/
	List<Org> queryAllOrg(final Org org, final String parentId,
			final String companyId, final int startRow, final int pageSize)
			throws HbcsoftException;

	/**
	* 根据父节点查询id，name并把id的值赋给parentid，到添加页面上级类型默认为父节点
	* 
	* @param id
	* @return
	* @throws HbcsoftException
	*/
	// Org queryIdAndDtname(final String id) throws HbcsoftException;
	/**
	* 根据主键id查询
	* 
	* @param id
	* @return
	* @throws HbcsoftException
	*/
	Org queryOrgById(final String id) throws HbcsoftException;

	/**
	* 修改
	* 
	* @param entity
	* @return
	* @throws HbcsoftException
	*/
	int update(final Org entity) throws HbcsoftException;

	/**
	* 添加
	* 
	* @param entity
	* @return
	* @throws HbcsoftException
	*/
	int save(final Org entity) throws HbcsoftException;

	/**
	* 根据ids查询
	* 
	* @param ids
	* @return
	* @throws HbcsoftException
	*/
	Org queryDataByIds(final String ids) throws HbcsoftException;

	/**
	* 删除
	* 
	* @param entity
	* @return
	* @throws HbcsoftException
	*/
	int deletebyIds(final Org entity) throws HbcsoftException;

	/**
	* 查询上级机构，显示在列表上
	* 
	* @param id
	* @return
	* @throws HbcsoftException
	*/
	Org getParentOrgByParentId(final String id) throws HbcsoftException;

	/**
	* 根据userid查询用户
	* 
	* @param userId
	* @return
	* @throws HbcsoftException
	*/
	User getUserByUserId(String userId) throws HbcsoftException;
	/**
	* (修改)根据公司id和主键id和机构编码或机构名称查询数据是否存在
	* 
	* @param Org
	* @param companyId
	* @return
	* @throws HbcsoftException
	*/
	Org queryByOrgCodeNameId(final Org org, final String companyId)
			throws HbcsoftException;// ,final String id) throws
									// HbcsoftException;
	/**
	 * 根据公司同步部门信息
	 * @param companyId
	 * @param deptCode
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Org> findAllCloneDepts(Long companyId) throws HbcsoftException;
	
	List<Org> queryAll(final String ids, final Long companyId) throws HbcsoftException;
	/**
	 * 查根节点数据
	 * @param companyId
	 * @return 
	 * @throws HbcsoftException 
	 */	
	Org findRootNode(Long companyId) throws HbcsoftException;
	/**
	 * 查同步部门编码
	 * @param cdeptName
	 * @param compamyId
	 * @return
	 * @throws HbcsoftException 
	 */
	Long findDeptCodeByName(String cdeptName, Long companyId) throws HbcsoftException;
	/**
	 * 根据编码查询公司
	 * @param code
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	String getOrgName(String code, long companyId) throws HbcsoftException;
}
