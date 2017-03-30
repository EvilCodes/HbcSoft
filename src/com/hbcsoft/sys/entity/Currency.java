package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hbcsoft.zdy.common.BaseEntity;
/**
 * @author awh
 * @version 2016-8-3上午10:10:55
 * 币种
 **/
public class Currency extends BaseEntity<Currency> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public Currency() {
		super();
	}
	private Long id;
	private Date create_time;
	/*
	 * 编码
	 * */
	private String code;
	/*
	 * 名称
	 * */
	private String name;
	/*
	 * 汇率
	 * */
	private Double rate;
	private Date createTime;
	private String update_userId;
	private String create_userId;
	private Date update_time;
	/*
	 * getter、setter方法
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdate_userId() {
		return update_userId;
	}
	public void setUpdate_userId(String update_userId) {
		this.update_userId = update_userId;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
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
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setCreateTime(rs.getDate("createTime"));
		this.setId(rs.getLong("id"));
		this.setName(rs.getString("name"));
		this.setRate(rs.getDouble("rate"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	}
	@Override
	public Currency mapRow(ResultSet rs, int arg1) throws SQLException {
		Currency currency = new Currency();
		currency.setId(rs.getLong("id"));
		
		
		return null;
	}
	
}
