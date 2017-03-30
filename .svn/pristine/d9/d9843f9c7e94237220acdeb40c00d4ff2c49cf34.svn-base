package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;
/**
 * @author awh
 * @version 2016-8-3上午10:10:55
 * 部门往来结算付款类型
 **/
@Table(name="T_SYS_DEPTCOMEGOPARA")
public class DeptComegopara extends BaseEntity<DeptComegopara> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public DeptComegopara() {
		super();
	}
	private Date createTime;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	private String cMainId;
	private String cOrgId;
	/*
	 * getter、setter方法
	 * */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_userId() {
		return update_userId;
	}
	public void setUpdate_userId(String update_userId) {
		this.update_userId = update_userId;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getcMainId() {
		return cMainId;
	}
	public void setcMainId(String cMainId) {
		this.cMainId = cMainId;
	}
	public String getcOrgId() {
		return cOrgId;
	}
	public void setcOrgId(String cOrgId) {
		this.cOrgId = cOrgId;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setcMainId(rs.getString("cMainId"));
		this.setcOrgId(rs.getString("cOrgId"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreateTime(rs.getDate("createTime"));
		this.setId(rs.getLong("id"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	}
	@Override
	public DeptComegopara mapRow(ResultSet rs, int arg1) throws SQLException {
		DeptComegopara deptComegopara = new DeptComegopara();
		deptComegopara.setId(rs.getLong("id"));
		
		
		
		return deptComegopara;
	}
	
}
