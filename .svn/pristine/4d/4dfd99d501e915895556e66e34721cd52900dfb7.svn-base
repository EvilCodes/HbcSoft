package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.User;
/**
 * UserService接口
 * @author Administrator
 *
 */
@Repository
public interface UserService {

	/**
	 * 查询当前用户（登录页面）
	 * @param userName
	 * @param password
	 * @return
	 * @throws HbcsoftException
	 */
	User getLoginUser(String userName, String password) throws HbcsoftException;
	/**
	 * 查询用户是否存在
	 * @param userId
	 * @return
	 * @throws HbcsoftException
	 */
	User getUser(String userId) throws HbcsoftException;
	/**
	 * 查询用户是否存在
	 * @param userId
	 * @return
	 * @throws HbcsoftException
	 */
	User getUserPwd(String userId,String password) throws HbcsoftException;
	/**
	 * 查询所有
	 * @return
	 */
	List<User> queryAll();
	/**
	 * 查询当前用户是否存在（忘记密码页面）
	 * @param loginName
	 * @param name
	 * @param email
	 * @param company
	 * @return
	 */
	User loginPwd(String... args);
	/**
	 * 修改密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updatePwd(String userId, String password);
	/**
	 * 修改皮肤
	 * @param userId
	 * @param cssPath
	 * @return
	 */
	int updateCss(String userId, String cssPath) throws HbcsoftException;
	/**
	 * 根据父节点查询id，dtname并把id的值赋给parentid，到添加页面上级节点默认为父节点
	 * @param id
	 * @return
	 */
	Org queryOrgByOrgId(final String orgId) throws HbcsoftException;
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	User queryUserById(final String id) throws HbcsoftException;
	/**
	 * 修改
	 * @param entity
	 * @return
	 * @throws HbcsoftException
	 */
	int update(User entity) throws HbcsoftException;
	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws HbcsoftException
	 */
	int save(User entity) throws HbcsoftException;
	/**
	 * 根据ids查询,再实现删除
	 * @param ids
	 * @return
	 * @throws HbcsoftException
	 */
	User queryUserDataByIds(final String ids) throws HbcsoftException;
	/**
	 * 删除
	 * @param entity
	 * @return
	 * @throws HbcsoftException
	 */
	int deletebyIds(final User entity) throws HbcsoftException;
	/**
	 * 查询所有
	 * @param user
	 * @param orgId
	 * @return
	 * @throws HbcsoftException
	 */
	List<User> queryAll(final User user, final String orgId,final String companyId)throws HbcsoftException;
	/**
	 * 分页查询
	 * @param user
	 * @param orgId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<User> queryAllUser(final User user, final String orgId,final String companyId, final int startRow, final int pageSize)throws HbcsoftException;
	/**
	 * 根据orgId查询是否有用户
	 * @param ids
	 * @return
	 * @throws HbcsoftException
	 */
	boolean haveRecord(final String ids) throws HbcsoftException;
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	User queryUserById(final String id,final String companyId) throws HbcsoftException;
	/**
	 * 根据id和公司id查询登录名
	 * @param loginName
	 * @param companyId
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	User queryByUserLoginNameId(final String loginName,final String companyId,final String id)throws HbcsoftException;
	/**
	 * 根据公司查询同步用户信息
	 * @param companyId
	 * @param deptCode
	 * @return
	 * @throws HbcsoftException 
	 */
	List<User> findAllCloneUsers(Long companyId) throws HbcsoftException;
	
	List<User> queryAll(final String ids, final Long companyId) throws HbcsoftException;
	/**
	 * 人员点选——根据公司查询用户
	 * @param companyId
	 * @return
	 * @throws HbcsoftException 
	 */
	List<User> findAllUsers(Long id) throws HbcsoftException;
	/**
	 * 人员点选——根据公司分页查询用户
	 * @param companyId
	 * @return
	 * @throws HbcsoftException 
	 */
	List<User> checkUser(Long companyId, int startRow, int pageSize) throws HbcsoftException;
}
