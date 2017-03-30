package com.hbcsoft.zdy.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BaseEntity<T> implements RowCallbackHandler,RowMapper<T>{

	public static final String TIMEZONE = "GMT+08:00";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT="yyyy-MM-dd";
	/**
	 * 表主id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long id;
	/**
	 * 单据主键id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long zid;
	/**
	 * 公司id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long companyId;
	
	/**
	 * 创建时间
	 */
	protected Date createTime=new Date();
	
	/**
	 * 创建人ID
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long createID;
	
	/**
	 * 修改时间
	 */
	protected Date updateTime=new Date();
	
	/**
	 * 修改人ID
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long updateID;
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getCreateID() {
		return createID;
	}

	public void setCreateID(final Long createID) {
		this.createID = createID;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Long getUpdateID() {
		return updateID;
	}

	public void setUpdateID(final Long updateID) {
		this.updateID = updateID;
	}

	public Long getZid() {
		return zid;
	}

	public void setZid(final Long zid) {
		this.zid = zid;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(final Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setCompanyId(rs.getLong("companyId"));
		this.setZid(rs.getLong("zid"));
		this.setUpdateID(rs.getLong("updateID"));
		this.setUpdateTime(rs.getTimestamp("UpdateTime"));
		this.setCreateTime(rs.getTimestamp("CreateTime"));
		this.setCreateID(rs.getLong("CreateID"));
	}
	
	public void mapRow(final BaseEntity<?> te, final ResultSet rs, final int arg1) throws SQLException {
		te.setId(rs.getLong("id"));
		te.setCompanyId(rs.getLong("companyId"));
		te.setZid(rs.getLong("zid"));
		te.setUpdateID(rs.getLong("updateID"));
		te.setUpdateTime(rs.getTimestamp("UpdateTime"));
		te.setCreateTime(rs.getTimestamp("CreateTime"));
		te.setCreateID(rs.getLong("CreateID"));
	}

	@Override
	public T mapRow(final ResultSet arg0, final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
