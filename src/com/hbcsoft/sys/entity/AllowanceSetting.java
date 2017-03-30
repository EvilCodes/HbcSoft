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
 **/
@Table(name="T_SYS_ALLOWANCESETTING")
public class AllowanceSetting extends BaseEntity<AllowanceSetting> implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	public AllowanceSetting() {
		super();
	}
	private String create_userId;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	/*
	 * 补助金额
	 * */
	private Double mAllowanceAmount; 
	/*
	 * 补助名称
	 * */
	private String cAllowanceName;
	/*
	 * 补助编号
	 * */
	private String cAllowanceNumber;
	/*
	 * 人员职称
	 * */
	private String cPersonDuty;
	/*
	 * getter、setter方法
	 * */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
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
	public Double getmAllowanceAmount() {
		return mAllowanceAmount;
	}
	public void setmAllowanceAmount(Double mAllowanceAmount) {
		this.mAllowanceAmount = mAllowanceAmount;
	}
	public String getcAllowanceName() {
		return cAllowanceName;
	}
	public void setcAllowanceName(String cAllowanceName) {
		this.cAllowanceName = cAllowanceName;
	}
	public String getcAllowanceNumber() {
		return cAllowanceNumber;
	}
	public void setcAllowanceNumber(String cAllowanceNumber) {
		this.cAllowanceNumber = cAllowanceNumber;
	}
	public String getcPersonDuty() {
		return cPersonDuty;
	}
	public void setcPersonDuty(String cPersonDuty) {
		this.cPersonDuty = cPersonDuty;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setmAllowanceAmount(rs.getDouble("mAllowanceAmount"));
		this.setcAllowanceName(rs.getString("cAllowanceName"));
		this.setcAllowanceNumber(rs.getString("cAllowanceNumber"));
		this.setcPersonDuty(rs.getString("cPersonDuty"));
	}
	@Override
	public AllowanceSetting mapRow(ResultSet rs, int arg1) throws SQLException {
		AllowanceSetting allowanceSetting = new AllowanceSetting();
		allowanceSetting.setId(rs.getLong("id"));
		
		
		
		return allowanceSetting;
	}
	
}
