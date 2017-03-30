package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;

/**
 * 角色管理
 * @author awh
 */
@Table(name = "T_SYS_ROLE")
public class Role extends BaseEntity<Role> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2111350380226371478L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 排序
	 */
	private Integer orderNo;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建用户
	 */
	@IsFiled(except = true)
	private User createUser;
	/**
	 * 资源
	 */
	@IsFiled(except = true)
	private List<Resource> resources = new ArrayList<Resource>();
	/**
	 * 资源编码
	 */
	@IsFiled(except = true)
	private List<Long> resourceIds = new ArrayList<Long>();
	/**
	 * 用户
	 */
	@IsFiled(except = true)
	private List<User> users = new ArrayList<User>();
	
	// private Long resourceId;
	// private Long userId;
	/**
	 * 资源get
	 */
	public List<Resource> getResources() {
		return resources;
	}
	/**
	 * 资源set
	 */
	public void setResources(final List<Resource> resources) {
		this.resources = resources;
	}
	/**
	 * 资源id  get
	 */
	public List<Long> getResourceIds() {
		return resourceIds;
	}
	/**
	 * 资源id  set
	 */
	public void setResourceIds(final List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}
	/**
	 * 用户  get
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * 用户  set
	 */
	public void setUsers(final List<User> users) {
		this.users = users;
	}
	/**
	 * 编码get
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 编码set
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	/**
	 * 名称get
	 */
	public String getName() {
		return name;
	}
	/**
	 * 名称set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * 排序get
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 排序set
	 */
	public void setOrderNo(final Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 备注get
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 备注set
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}
	/**
	 * 创建用户get
	 */
	public User getCreateUser() {
		return createUser;
	}
	/**
	 *创建用户 set
	 */
	public void setCreateUser(final User createUser) {
		this.createUser = createUser;
	}

	/*
	 * public Long getResourceId() { return resourceId; }
	 * public void setResourceId(Long resourceId) { this.resourceId =
	 * resourceId; }
	 * public Long getUserId() { return userId; }
	 * public void setUserId(Long userId) { this.userId = userId; }
	 */
	/**
	 * 把值放到结果集
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setId(rs.getLong("id"));
		this.setCode(rs.getString("code"));
		this.setName(rs.getString("name"));
		this.setOrderNo(rs.getInt("orderNo"));
		this.setRemark(rs.getString("remark"));
		this.setCreateTime(rs.getTimestamp("createTime"));
		this.setUpdateTime(rs.getTimestamp("updateTime"));
	}
	/**
	 * 把值放到对象
	 */
	@Override
	public Role mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final Role role = new Role();
		super.mapRow(role, rs, arg1);
		role.setId(rs.getLong("id"));
		role.setCode(rs.getString("code"));
		role.setName(rs.getString("name"));
		role.setOrderNo(rs.getInt("orderNo"));
		role.setRemark(rs.getString("remark"));
		role.setCreateTime(rs.getTimestamp("createTime"));
		role.setUpdateTime(rs.getTimestamp("updateTime"));
		return role;
	}

}
