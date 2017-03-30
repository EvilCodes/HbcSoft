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
 * 资源管理
 */
@Table(name = "T_SYS_RESOURCE")
public class Resource extends BaseEntity<Resource> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1997966568604079449L;

	/**
	 * 资源编码
	 */
	private String code;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源类型
	 */
	private Integer resourceType;
	/**
	 * 链接地址
	 */
	private String url;

	/**
	 * 排序
	 */
	private Integer orderNo;
	@IsFiled(except = true)
	/**
	 * levelNum
	 */
	private int levelNum;
	/**
	 * 是否启用
	 */
	private Integer enable;
	/**
	 * 父资源编码
	 */
	private Long parentResourceId;
	/**
	 * 是否允许编辑,默认值是1（允许修改）；0（不允许修改）
	 */
	private String groupId;
	
	/**
	 * 父资源
	 */
	@IsFiled(except = true)
	private Resource parentResource;

	/**
	 * 子资源
	 */
	@IsFiled(except = true)
	private List<Resource> childrenResource = new ArrayList<Resource>();

	/**
	 * 角色
	 */
	@IsFiled(except = true)
	private List<Role> roles = new ArrayList<Role>();

	/**
	 * 角色getter
	 */
	public List<Role> getRoles() {
		return roles;
	}
	
	/**
	 * 角色setter
	 */
	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 父资源get方法
	 */
	public Resource getParentResource() {
		return parentResource;
	}

	/**
	 * 父资源set方法
	 */
	public void setParentResource(final Resource parentResource) {
		this.parentResource = parentResource;
	}

	/**
	 * 编码get
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编码set方法
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * 名称get方法
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称set方法
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 路径get方法
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 路径set方法
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * 排序get方法
	 */
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * 排序set方法
	 */
	public void setOrderNo(final Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * LevelNum get方法
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * LevelNum set方法
	 */
	public void setLevelNum(final int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * 启用状态get方法
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 启用状态set方法
	 */
	public void setEnable(final Integer enable) {
		this.enable = enable;
	}

	/**
	 * 资源类型get方法
	 */
	public Integer getResourceType() {
		return resourceType;
	}

	/**
	 * 资源类型set方法
	 */
	public void setResourceType(final Integer resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 父资源类型get方法
	 */
	public Long getParentResourceId() {
		return parentResourceId;
	}

	/**
	 * 父资源类型set方法
	 */
	public void setParentResourceId(final Long parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

	/**
	 * 子资源get方法
	 */
	public List<Resource> getChildrenResource() {
		return childrenResource;
	}

	/**
	 * 子资源set方法
	 */
	public void setChildrenResource(final List<Resource> childrenResource) {
		this.childrenResource = childrenResource;
	}

	/**
	 * 允许编辑get方法
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * 允许编辑set方法
	 */
	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 把值放到ResultSet结果集
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setCode(rs.getString("code"));
		this.setGroupId(rs.getString("groupId"));
		this.setName(rs.getString("name"));
		this.setResourceType(rs.getInt("resourceType"));
		this.setUrl(rs.getString("url"));
		this.setOrderNo(rs.getInt("orderNo"));
		this.setEnable(rs.getInt("enable"));
		this.setParentResourceId(rs.getLong("parentResourceId"));
	}

	/**
	 * 把值放到对象
	 */
	@Override
	public Resource mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final Resource resourse = new Resource();
		super.mapRow(resourse, rs, arg1);
		resourse.setCode(rs.getString("code"));
		resourse.setGroupId(rs.getString("groupId"));
		resourse.setName(rs.getString("name"));
		resourse.setResourceType(rs.getInt("resourceType"));
		resourse.setUrl(rs.getString("url"));
		resourse.setOrderNo(rs.getInt("orderNo"));
		resourse.setEnable(rs.getInt("enable"));
		resourse.setParentResourceId(rs.getLong("parentResourceId"));
		return resourse;
	}

}
