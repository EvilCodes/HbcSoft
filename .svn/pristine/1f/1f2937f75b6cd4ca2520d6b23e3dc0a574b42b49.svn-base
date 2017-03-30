package com.hbcsoft.sys.dao;

import java.util.List;
/**
 * 用户dao层
 */

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.User;
@Repository
public interface UserDao {
	/**
	 * 登录验证
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	User getLoginUser(String sql, JdbcTemplate jt, String... param);
	/**
	 * 查询所有
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<User> queryAll(String sql, JdbcTemplate jt, String... param);
	/**
	 * 修改
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	int updateAll(String sql, JdbcTemplate jt, String... param);
	
}
