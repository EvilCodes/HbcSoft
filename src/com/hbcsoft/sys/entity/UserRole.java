package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.yaja.validator.inter.Table;

/**
 * 用户角色管理
 * 
 */
@Table(name = "T_SYS_USER_ROLE")
public class UserRole implements Serializable,RowCallbackHandler,RowMapper<UserRole>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2111350380226371478L;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 角色Id
	 */
	private Long roleId;
	/**
	 * userId的get方法
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * userId的set方法
	 * @param userId
	 */
	public void setUserId(final Long userId) {
		this.userId = userId;
	}
	/**
	 * roleId的get方法
	 * @return
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * roleId的set方法
	 * @param roleId
	 */
	public void setRoleId(final Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * processRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		this.setUserId(rs.getLong("userId"));
		this.setRoleId(rs.getLong("roleId"));
	}
	/**
	 * mapRow方法
	 * @return UserRole
	 */
	@Override
	public UserRole mapRow(final ResultSet rs,final int arg1) throws SQLException {
		final UserRole role = new UserRole();
		role.setUserId(rs.getLong("userId"));
		role.setRoleId(rs.getLong("roleId"));
		return role;
	}
}
