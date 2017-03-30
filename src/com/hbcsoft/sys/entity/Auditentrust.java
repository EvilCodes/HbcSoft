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
@Table(name="T_SYS_AUDITENTRUST")
public class Auditentrust extends BaseEntity<Auditentrust> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public Auditentrust() {
		super();
	}
	private Date create_time;
	private Date tCancelTime;
	private Date dEndDate;
 	private Integer bIsSetMoneyLimit;
	private Double mLowerMoney;
	private String cMemo;
	private Date dStartDate;
	private Integer iStatus;
	private Double mUpperMoney;
	private String cOwnerDeptId;
	private String cOwnerUserId;
	private String cTargetDeptId;
	private String cTargetUserId;
	private String create_userId;
	private String update_userId;
	private Date update_time;
	
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
	
	public Date gettCancelTime() {
		return tCancelTime;
	}
	public void settCancelTime(Date tCancelTime) {
		this.tCancelTime = tCancelTime;
	}
	public Date getdEndDate() {
		return dEndDate;
	}
	public void setdEndDate(Date dEndDate) {
		this.dEndDate = dEndDate;
	}
	public Integer getbIsSetMoneyLimit() {
		return bIsSetMoneyLimit;
	}
	public void setbIsSetMoneyLimit(Integer bIsSetMoneyLimit) {
		this.bIsSetMoneyLimit = bIsSetMoneyLimit;
	}
	public Double getmLowerMoney() {
		return mLowerMoney;
	}
	public void setmLowerMoney(Double mLowerMoney) {
		this.mLowerMoney = mLowerMoney;
	}
	public String getcMemo() {
		return cMemo;
	}
	public void setcMemo(String cMemo) {
		this.cMemo = cMemo;
	}
	public Date getdStartDate() {
		return dStartDate;
	}
	public void setdStartDate(Date dStartDate) {
		this.dStartDate = dStartDate;
	}
	public Integer getiStatus() {
		return iStatus;
	}
	public void setiStatus(Integer iStatus) {
		this.iStatus = iStatus;
	}
	public Double getmUpperMoney() {
		return mUpperMoney;
	}
	public void setmUpperMoney(Double mUpperMoney) {
		this.mUpperMoney = mUpperMoney;
	}
	public String getcOwnerDeptId() {
		return cOwnerDeptId;
	}
	public void setcOwnerDeptId(String cOwnerDeptId) {
		this.cOwnerDeptId = cOwnerDeptId;
	}
	public String getcOwnerUserId() {
		return cOwnerUserId;
	}
	public void setcOwnerUserId(String cOwnerUserId) {
		this.cOwnerUserId = cOwnerUserId;
	}
	public String getcTargetDeptId() {
		return cTargetDeptId;
	}
	public void setcTargetDeptId(String cTargetDeptId) {
		this.cTargetDeptId = cTargetDeptId;
	}
	public String getcTargetUserId() {
		return cTargetUserId;
	}
	public void setcTargetUserId(String cTargetUserId) {
		this.cTargetUserId = cTargetUserId;
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
		this.setId(rs.getLong("id"));
		this.setbIsSetMoneyLimit(rs.getInt("bIsSetMoneyLimit"));
		this.setcMemo(rs.getString("cMemo"));
		this.setcOwnerDeptId(rs.getString("cOwnerDeptId"));
		this.setcOwnerUserId(rs.getString("cOwnerUserId"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setcTargetDeptId(rs.getString("cTargetDeptId"));
		this.setcTargetUserId(rs.getString("cTargetUserId"));
		this.setdEndDate(rs.getDate("dEndDate"));
		this.setdStartDate(rs.getDate("dStartDate"));
		this.setiStatus(rs.getInt("iStatus"));
		this.setmLowerMoney(rs.getDouble("mLowerMoney"));
		this.setmUpperMoney(rs.getDouble("mUpperMoney"));
		this.settCancelTime(rs.getDate("tCancelTime"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	}
	@Override
	public Auditentrust mapRow(ResultSet rs, int arg1) throws SQLException {
		Auditentrust auditentrust = new Auditentrust();
		auditentrust.setId(rs.getLong("id"));
		
		
		return auditentrust;
	}

}
