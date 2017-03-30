package com.hbcsoft.login.service;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.User;
/**
 * UserRegisterService接口
 * @author churuifeng
 *
 */
@Service
public interface UserRegisterService {
	/**
	 * 添加方法
	 * @param user
	 * @return int  0为失败，1为成功
	 * @throws HbcsoftException
	 */
	int add(User user) throws HbcsoftException;
	/**
	 * 激活当前用户（修改当前用户的状态）
	 * @param userId
	 * @return
	 * @throws HbcsoftException 
	 */
	int updateState(User user) throws HbcsoftException;
}
