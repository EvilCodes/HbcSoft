package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;
/**
 * @author awh
 * @version 2016-8-3上午10:10:55
 * 公司表
 **/
@Table(name="T_SYS_COMPANY")
public class Company implements Serializable,RowCallbackHandler,RowMapper<Company>{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	/**
	 * 表主id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long id;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司中文简称
	 */
	@IsFiled(name="companyName_cn")
	private String companyNameCn;
	/**
	 * 公司英文简称
	 */
	@IsFiled(name="companyName_hk")
	private String companyNameHk;
	/**
	 * id的get方法
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * id的set方法
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	/**
	 * companyName的get方法
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * companyName的set方法
	 * @param companyName
	 */
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}
	/**
	 * companyNameCn的get方法
	 * @return
	 */
	public String getCompanyNameCn() {
		return companyNameCn;
	}
	/**
	 *  companyNameCn的set方法
	 * @param companyNameCn
	 */
	public void setCompanyNameCn(final String companyNameCn) {
		this.companyNameCn = companyNameCn;
	}
	/**
	 * companyNameHk的get方法
	 * @return
	 */
	public String getCompanyNameHk() {
		return companyNameHk;
	}
	/**
	 * companyNameHk的set方法
	 * @param companyNameHk
	 */
	public void setCompanyNameHk(final String companyNameHk) {
		this.companyNameHk = companyNameHk;
	}
	/**
	 * processRow方法
	 */
	public void processRow(final ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setCompanyName(rs.getString("companyName"));
		this.setCompanyNameCn(rs.getString("companyName_cn"));
		this.setCompanyNameHk(rs.getString("companyName_hk"));
	}
	/**
	 * mapRow方法
	 * @return Company
	 */
	public Company mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final Company company = new Company();
		company.setId(rs.getLong("id"));
		company.setCompanyName(rs.getString("companyName"));
		company.setCompanyNameCn(rs.getString("companyName_cn"));
		company.setCompanyNameHk(rs.getString("companyName_hk"));
		return company;
	}
	
}
