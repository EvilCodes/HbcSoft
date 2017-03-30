package com.hbcsoft.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.login.dao.UserRegisterDaoImpl;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * UserRegisterService的实现类
 * @author Administrator
 *
 */
@Service("userRegisterService")
public class UserRegisterServiceImp extends LogBaseClass<UserRegisterServiceImp> implements UserRegisterService{
	/**
	 * 调用HbcsoftJT类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 调用UserRegisterDaoImpl类
	 */
	@Autowired
	private transient UserRegisterDaoImpl daoImpl;
	/**
	 * 添加方法
	 */
	@Override
	public int add(final User user) throws HbcsoftException {
		return daoImpl.save(user, jt);
	}
	/**
	 * 修改方法
	 */
	@Override
	public int updateState(final User user) throws HbcsoftException {
		this.getLogger().info("============queryCount=========start==");
		this.getLogger().info("============queryCount=========end==");
		return daoImpl.update(user, jt);
	}

}
