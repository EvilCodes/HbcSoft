package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yaja.validator.inter.Table;

/**
 * 角色资源管理
 * 
 */
@Table(name = "T_SYS_ROLE_RESOURCE")
public class RoleResource implements Serializable,RowCallbackHandler,RowMapper<RoleResource>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2111350380226371478L;
	/**
	 * 角色Id
	 */
	private Long roleId;
	/**
	 * 资源Id
	 */
	private Long resourceId;
	/**
	 * 公司id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long companyId;
	
	
	/**
	 * 角色Id get
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 角色Id set
	 */
	public void setRoleId(final Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 资源Id get
	 */
	public Long getResourceId() {
		return resourceId;
	}
	/**
	 * 资源Id set
	 */
	public void setResourceId(final Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	/**
	 * processRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		this.setRoleId(rs.getLong("roleId"));
		this.setResourceId(rs.getLong("resourceId"));
		this.setCompanyId(rs.getLong("companyId"));
	}
	/**
	 * mapRow方法
	 * @return UserRole
	 */
	@Override
	public RoleResource mapRow(final ResultSet rs,final int arg1) throws SQLException {
		final RoleResource rr = new RoleResource();
		rr.setRoleId(rs.getLong("roleId"));
		rr.setCompanyId(rs.getLong("companyId"));
		rr.setResourceId(rs.getLong("resourceId"));
		return rr;
	}
}
