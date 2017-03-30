package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hbcsoft.sys.entity.Role;
/**
 * 角色管理查询接口
 * */
@Repository
public interface RoleDao {
	/**
	 * 查询
	 * */
	List<Role> queryAllRole(final String sql, final JdbcTemplate jt, final String... param);
	/**
	 * 根据id查询
	 */
	Role queryById(final String sql,final JdbcTemplate jt,final String... param);
}
