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
 * 部门表
 **/
@Table(name="T_SYS_DEPT")
public class Dept extends BaseEntity<Dept> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public Dept() {
		super();
	}
	private Date create_time;
	/*
	 * 部门编码
	 * */
	private String code;
	/*
	 * 是否启用
	 * */
	private Integer enable;
	/*
	 * 部门名称
	 * */
	private String name;
	/*
	 * 所属单位编码
	 * */
	private String corp_id;
	private String corpId;
	private Date createTime;
	private String create_userId;
	private String update_userId;
	private Date update_time;
	/*
	 * getter setter
	 * */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
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
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setCode(rs.getString("code"));
		this.setCorp_id(rs.getString("corp_id"));
		this.setCorpId(rs.getString("corpId"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setCreateTime(rs.getDate("createTime"));
		this.setEnable(rs.getInt("enable"));
		this.setId(rs.getLong("id"));
		this.setName(rs.getString("name"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	
	}
	@Override
	public Dept mapRow(ResultSet rs, int arg1) throws SQLException {
		Dept dept = new Dept();
		dept.setId(rs.getLong("id"));
		
		
		
		return dept;
	}
	
}
