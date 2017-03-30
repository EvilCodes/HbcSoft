package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.OrgDaoImp;
import com.hbcsoft.sys.dao.UserDaoImp;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.PubTools;
/**
 * UserService接口实现类
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImp extends LogBaseClass<UserServiceImp> implements UserService {
	/**
	 * HbcsoftJT类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * UserDaoImp实现类
	 */
	@Autowired
	private transient UserDaoImp userDao;
	/**
	 * 机构dao
	 */
	@Autowired
	private transient OrgDaoImp  orgDao;
//	@Autowired
//	private UserInfoDaoImpl userInfoDaoImpl;
	/**
	 * 根据姓名密码查询
	 * @return User
	 */
	@Override
	public User getLoginUser(final String userName,final String password) throws HbcsoftException {
		this.getLogger().info("============getLoginUser=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_userpassword");
		this.getLogger().info(sql);
		User user = null;
		try {
			user = userDao.query(sql, jt,userName, password);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============getLoginUser=========end==");
		return user;
	}
	/**
	 * 根据UserId查询当前用户信息 
	 */
	@Override
	public User getUser(final String userId) throws HbcsoftException {
		this.getLogger().info("============getUser=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_queryById");
		this.getLogger().info(sql);
		User user = null;
		try {
			user = userDao.query(sql, jt,userId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============getUser=========end==");
		return user;
	}
	/**
	 * 根据UserId和密码查询当前用户信息 
	 */
	@Override
	public User getUserPwd(final String userId,final String password) throws HbcsoftException {
		this.getLogger().info("============getUser=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_queryByPwd");
		this.getLogger().info(sql);
		User user = null;
		try {
			user = userDao.query(sql, jt,userId,password);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============getUser=========end==");
		return user;
	}
	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<User> queryAll(){
		this.getLogger().info("============queryAll=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_queryAll");
		this.getLogger().info(sql);
		List<User> list = new ArrayList<User>();
		try {
			list = (List<User>) userDao.query(sql, jt);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	/**
	 * 根据传入的信息查询用户信息
	 * @return User
	 */
	@Override
	public User loginPwd(final String... parameter) {
		this.getLogger().info("============loginPwd=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_count");
		this.getLogger().info(sql);
		User user = null;
		try {
			user = userDao.query(sql, jt,parameter);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============loginPwd=========end==");
		return user;
	}
	
	/**
	 * 修改密码操作
	 */
	@Override
	public int updatePwd(final String userId,final String password) {
		this.getLogger().info("============updatePwd=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_updatepwd");
		this.getLogger().info(sql);
		int intV = 0;
		intV = userDao.updateAll(sql, jt,password,userId);
		this.getLogger().info("============updatePwd=========end==");
		return intV;
	}
	@Override
	public int updateCss(String userId, String cssPath) throws HbcsoftException {
		this.getLogger().info("============updateCss=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("longin_updateCss");
		this.getLogger().info(sql);
		int intV = 0;
		intV = userDao.updateAll(sql, jt,cssPath,userId);
		this.getLogger().info("============updateCss=========end==");
		return intV;
	}
	/**
	 * 根据父节点查询id，dtname并把id的值赋给parentid，到添加页面上级节点默认为父节点
	 * @param id
	 * @return
	 */
	public Org queryOrgByOrgId(final String orgId) {
		this.getLogger().info("============queryOrgByOrgId=========start==");
		final String sql = HbcsoftCache.getSqlValue("org_queryById");
		Org org = new Org();
		try {
			org = orgDao.query(sql, jt, orgId);

		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryOrgByOrgId=========end==");
		return org;
	}
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User queryUserById(final String id){
		this.getLogger().info("============queryUserById=========start==");
		final String sql = HbcsoftCache.getSqlValue("user_queryUserById");
		User user = new User();
			try {
				user = userDao.query(sql, jt, id);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		this.getLogger().info("============queryUserById=========end==");
		return user;
	}
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public int save(final User entity){
		this.getLogger().info("============save=========start==");
		try {
			 userDao.save(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("============save=========end==");
		return 1;
	}
	/**
	 * 根据ids查询,再实现删除
	 */

	public User queryUserDataByIds(final String ids) {
		this.getLogger().info("============queryUserDataByIds=========start==");
		final String sql = HbcsoftCache.getSqlValue("user_queryUserById");
		User user = new User();
		try {
			user = userDao.query(sql, jt, ids);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryUserDataByIds=========end==");
		return user;
	}
	/**
	 * 删除
	 */
	public int deletebyIds(final User entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============deletebyIds=========start==");
		try {
			userDao.delete(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============deletebyIds=========end==");
		return 1;
	}
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
		public int update(final User entity){
		this.getLogger().info("============update=========start==");
		try {
			 userDao.update(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("============update=========end==");
		return 1;
}

	/**
	 * 查询所有的数据
	 */
	@Override
	public List<User> queryAll(final User user, final String orgId,final String companyId) {
		this.getLogger().info("============queryAll=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("user_queryAll");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (orgId != null && !"".equals(orgId)) {
			sb.append( " AND orgId = ?");
			//sql += " AND orgId = ?";
			params.add(orgId);
		}
		if (user.getLoginName() != null && !"".equals(user.getLoginName() )) {//登录名
			sb.append( " AND Login_Name like  ?");
			//sql += " AND Login_Name like  ?";
			params.add("%" + user.getLoginName() + "%");
		}
		if (user.getName() != null && !"".equals(user.getName())) {//用户名
			sb.append(" AND name like  ?");
			//sql += " AND name like  ?";
			params.add("%" + user.getName() + "%");
		}
		this.queryCondition(params, sb, user);////模糊查询的查询条件

		final List<User> list = this.returnListCount(params, sb.toString());//返回查询结果：行
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<User> queryAllUser(final User user, final String orgId,final String companyId, final int startRow, final int pageSize) {
		this.getLogger().info("============queryAllUser=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("user_queryAll");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (orgId != null && !"".equals(orgId)) {
			sb.append( " AND orgId = ?");
			//sql += " AND orgId = ?";
			params.add(orgId);
		}
		
		if (user.getLoginName() != null && !"".equals(user.getLoginName() )) {//登录名
			sb.append( " AND Login_Name like  ?");
			//sql += " AND Login_Name like  ?";
			params.add("%" + user.getLoginName() + "%");
		}
		if (user.getName() != null && !"".equals(user.getName())) {//用户名
			sb.append(" AND name like  ?");
			//sql += " AND name like  ?";
			params.add("%" + user.getName() + "%");
		}
		this.queryCondition(params, sb, user);//模糊查询的查询条件

		final List<User> list = this.returnListCount(params, sb.toString(), startRow,
				pageSize);//返回分页查询结果
		this.getLogger().info("============queryAllUser=========end==");
		return list;
	}
	/**
	 * 模糊查询的查询条件：解决条件太多问题
	 * @param params
	 * @param sb
	 * @param user
	 */
	private void queryCondition(final List<String> params,final StringBuilder sb,final User user){
		
		if (user.getIdentityNumber() != null && !"".equals(user.getIdentityNumber())) {//证件号码 identityNumber
			sb.append( " AND identityNumber like  ?");
			//sql += " AND identityNumber like  ?";
			params.add("%" + user.getIdentityNumber() + "%");
		}
		if (user.getMobilephone() != null && !"".equals(user.getMobilephone())) {//移动电话 mobilephone
			sb.append(" AND mobilephone like  ?");
			//sql += " AND mobilephone like  ?";
			params.add("%" + user.getMobilephone()+ "%");
		}
		if (user.getCardNumber() != null && !"".equals(user.getCardNumber())) {//卡号  cardNumber
			sb.append(" AND cardNumber like  ?");
			//sql += " AND cardNumber like  ?";
			params.add("%" + user.getCardNumber() + "%");
		}
		if (user.getBusinessCardNumber() != null && !"".equals(user.getBusinessCardNumber())) {//公务卡 businessCardNumber
			sb.append(" AND businessCardNumber like  ?");
			//sql += " AND businessCardNumber like  ?";
			params.add("%" + user.getBusinessCardNumber() + "%");
		}
	}
	/**
	 * 返回查询结果：行
	 * @param params
	 * @param sql
	 * @return
	 */
	private List<User> returnListCount(final List<String> params,
			final String sql) {// 行
		List<User> list = new ArrayList<User>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		try {
			if (paramsize || pars) {
				list = (List<User>) userDao.queryAll(sql, jt,
						params.toArray(new Object[params.size()]));
			} else {
				list = (List<User>) userDao.queryAll(sql, jt,
						params.toArray(new Object[params.size()]));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return list;
	}
	/**
	 * 返回分页查询结果
	 * @param params
	 * @param sql
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	private List<User> returnListCount(final List<String> params,
			final String sql, final int startRow, final int pageSize) {// 分页
		List<User> list = new ArrayList<User>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		try {
			if (paramsize || pars) {
				list = (List<User>) userDao.queryAll(sql, jt, startRow,
						pageSize);
			} else {
				list = (List<User>) userDao.queryAll(sql, jt, startRow,
						pageSize, params.toArray(new Object[params.size()]));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return list;
	}
	/**
	 * 根据orgId查询是否有用户
	 * @param ids
	 * @return
	 */
	public boolean haveRecord(final String ids){
		boolean flag = false;
		final String sql = HbcsoftCache.getSqlValue("user_queryByOrgId");
		try {
			final User user=userDao.query(sql, jt,ids);
			flag = this.userIsNull(user);//判断用户是否为空
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return flag;
	}
	/**
	 * 判断用户是否为空：是-false,否-true
	 * @param user
	 * @return
	 */
	private  boolean userIsNull(final User user){
		boolean flag = false;
		final Long id = user.getId();
		if(PubTools.isEmpty(id)){
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据id查询用户信息
	 */
	@Override
	public User queryUserById(final String id,final  String companyId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("============queryUserById=========start==");
		final String sql = HbcsoftCache.getSqlValue("user_queryUserByIdAndCmid");
		User user = new User();
		try {
				user = userDao.query(sql, jt, id,companyId);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		this.getLogger().info("============queryUserById=========end==");
		return user;
	}
	/**
	 * 根据id和公司id查询用户登录名
	 */
	@Override
	public User queryByUserLoginNameId(final String loginName,final String companyId,
			final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		User user = new User();
		final String sql = HbcsoftCache.getSqlValue("user_queryByUserLoginNameId");
		try {
			user = userDao.query(sql, jt, loginName,companyId,id);
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
	 * 根据公司、部门查询同步用户信息
	 * @param companyId
	 * @param deptCode
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public List<User> findAllCloneUsers(final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		try {
		final String sql = HbcsoftCache.getSqlValue("user_queryAllCloneUsers");
			list = userDao.queryAll(sql, jt, companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return list;
	}
	
	@Override
	public List<User> queryAll(final String ids, final Long companyId) throws HbcsoftException 
	{
		this.getLogger().info("============queryAll=========start==");
		try {
			final List<Object> params = new ArrayList<Object>();
			final String sql = HbcsoftCache.getSqlValue("user_queryAll");
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
	
			return userDao.queryAll(sb.toString(), jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException("===UserServiceImp====>queryAll", ErrorConstant.EXCEPTION_CODE, e);
		}
	}
	/**
	 * 人员点选——根据公司查询用户
	 * @param companyId
	 * @param deptCode
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public List<User> findAllUsers(final Long companyId) throws HbcsoftException {
		List<User> list = new ArrayList<User>();
		final String sql = HbcsoftCache.getSqlValue("user_queryAll");
		try {
			list = userDao.queryAll(sql, jt, companyId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return list;
	}
	/**
	 * 人员点选——根据公司id分页查询人员
	 * */
	@Override
	public List<User> checkUser(Long companyId, int startRow, int pageSize) throws HbcsoftException {
		this.getLogger().info("============checkUser=========start==");
		final String sql = HbcsoftCache.getSqlValue("user_queryAll");
		final List<String> params = new ArrayList<String>();
		params.add(String.valueOf(companyId));
		final List<User> list = this.returnListCount(params, sql, startRow, pageSize);//返回分页查询结果
		this.getLogger().info("============checkUser=========end==");
		return list;
	}
}