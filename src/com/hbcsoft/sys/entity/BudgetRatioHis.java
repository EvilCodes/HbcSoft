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
@Table(name="T_SYS_BUDGETRATIOHIS")
public class BudgetRatioHis extends BaseEntity<BudgetRatioHis> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public BudgetRatioHis() {
		super();
	}
	private String create_userId;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	/*
	 * 预算控制比例
	 * */
	private Double dBudgetRatio;
	/*
	 * 预算类型
	 * */
	private String cBudgetType;
	private String cOid;//cOid
	/*
	 * 项目类型
	 * */
	private String cProjectType;
	/*
	 * 机构编码
	 * */
	private String cOrgId;
	/*
	 * 显示修改次数
	 * */
	private Integer cStep;
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
	public Double getdBudgetRatio() {
		return dBudgetRatio;
	}
	public void setdBudgetRatio(Double dBudgetRatio) {
		this.dBudgetRatio = dBudgetRatio;
	}
	public String getcBudgetType() {
		return cBudgetType;
	}
	public void setcBudgetType(String cBudgetType) {
		this.cBudgetType = cBudgetType;
	}
	public String getcOid() {
		return cOid;
	}
	public void setcOid(String cOid) {
		this.cOid = cOid;
	}
	public String getcProjectType() {
		return cProjectType;
	}
	public void setcProjectType(String cProjectType) {
		this.cProjectType = cProjectType;
	}
	public String getcOrgId() {
		return cOrgId;
	}
	public void setcOrgId(String cOrgId) {
		this.cOrgId = cOrgId;
	}
	public Integer getcStep() {
		return cStep;
	}
	public void setcStep(Integer cStep) {
		this.cStep = cStep;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setcBudgetType(rs.getString("cBudgetType"));
		this.setcOid(rs.getString("cOid"));
		this.setcOrgId(rs.getString("cOrgId"));
		this.setcProjectType(rs.getString("cProjectType"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setcStep(rs.getInt("cStep"));
		this.setdBudgetRatio(rs.getDouble("dBudgetRatio"));
		this.setId(rs.getLong("id"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	}
	@Override
	public BudgetRatioHis mapRow(ResultSet rs, int arg1) throws SQLException {
		BudgetRatioHis budgetRatioHis = new BudgetRatioHis();
		budgetRatioHis.setId(rs.getLong("id"));
		
		
		return budgetRatioHis;
	}
	
}
