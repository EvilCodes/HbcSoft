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
@Table(name="T_SYS_BUDGETSUBJECTBACK")
public class BudgetSubjectBack extends BaseEntity<BudgetSubjectBack> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public BudgetSubjectBack() {
		super();
	}
	private Date create_time;
	private Integer budgetType;
	private String code;
	private Integer enable;
	private Double floatIngrate;
	private String name;
	private String parentId;
	private Integer sort;
	private Integer subjectType;
	private String create_userId;
	private String update_userId;
	private Date update_time;
	private Integer iSubjectProperty;
	private String memo;
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
	public Integer getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(Integer budgetType) {
		this.budgetType = budgetType;
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
	public Double getFloatIngrate() {
		return floatIngrate;
	}
	public void setFloatIngrate(Double floatIngrate) {
		this.floatIngrate = floatIngrate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
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
	public Integer getiSubjectProperty() {
		return iSubjectProperty;
	}
	public void setiSubjectProperty(Integer iSubjectProperty) {
		this.iSubjectProperty = iSubjectProperty;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setBudgetType(rs.getInt("budgetType"));
		this.setCode(rs.getString("code"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setEnable(rs.getInt("enable"));
		this.setFloatIngrate(rs.getDouble("floatIngrate"));
		this.setId(rs.getLong("id"));
		this.setiSubjectProperty(rs.getInt("iSubjectProperty"));
		this.setMemo(rs.getString("memo"));
		this.setName(rs.getString("name"));
		this.setParentId(rs.getString("parentId"));
		this.setSort(rs.getInt("sort"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setSubjectType(rs.getInt("subjectType"));
	}
	@Override
	public BudgetSubjectBack mapRow(ResultSet rs, int arg1) throws SQLException {
		BudgetSubjectBack budgetSubjectBack = new BudgetSubjectBack();
		budgetSubjectBack.setId(rs.getLong("id"));
		
		return budgetSubjectBack;
	}
	
}
