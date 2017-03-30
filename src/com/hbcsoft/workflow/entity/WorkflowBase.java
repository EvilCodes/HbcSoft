package com.hbcsoft.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@MappedSuperclass
public class WorkflowBase implements Serializable {

	public static final String TIMEZONE = "GMT+08:00";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT="yyyy-MM-dd";
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7542399495867811289L;
	/**
	 * 主键
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	private long baseId;
	/**
	 * 创建人
	 */
	private String createId;
	/**
	 * 创建人姓名
	 */
	private String createName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 修改人姓名
	 */
	private String updateName;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 部门id
	 */
	private String deptId;
	
	public long getBaseId() {
		return baseId;
	}
	public void setBaseId(final long baseId) {
		this.baseId = baseId;
	}

	public String getCreateId() {
		return createId;
	}
	public void setCreateId(final String createId) {
		this.createId = createId;
	}
	
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(final String createName) {
		this.createName = createName;
	}

	@JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(final String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(final String updateName) {
		this.updateName = updateName;
	}

	@JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
