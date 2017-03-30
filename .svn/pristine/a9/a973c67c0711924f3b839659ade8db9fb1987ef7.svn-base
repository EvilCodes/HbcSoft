package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;

/**
 * @author yangfeng
 * @version 2016-11
 *
 */
@Table(name="T_SYS_SUBJECT")
public class Subject extends BaseEntity<Subject> implements Serializable{
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2005272036891480167L;

	/**
	 * 预算类型
	 */
	private String budgetType;	
	/**
	 * 预算类型编码
	 */
	private String budgetTypeCode;	
	/**
	 * 科目类型
	 */
	private String subjectType;
	/**
	 * 科目类型英文缩写
	 */
	private String subjectTypeCode;
	/**
	 * 科目编码
	 */
	@NotNull
	private String subjectCode;	
	/**
	 * 科目名称
	 */
	@NotNull
	private String subjectName;	
	/**
	 * 科目级别
	 */
	private int subjectLevel;	
	/**
	 * 科目属性
	 */
	private int subjectProperty;	
	/**
	 * 父科目ID
	 */
	private String parentId;	
	/**
	 *年度
	 */
	@NotNull
	private String iyear;	
	/**
	 * 浮动比例
	 */
	private Double floatingRate;	
	/**
	 * 是否有效  0--无效  1--有效
	 */
	private int isEnable;	
	/**
	 *排序
	 */
	private int sort;	
	/**
	 * 备注
	 */
	@Length(max=1000, message="备注信息长度不能超过1000位！")
	private String remark;
	
	/**
	 * get方法
	 * @return
	 */
	public String getBudgetType() {
		return budgetType;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setBudgetType(final String budgetType) {
		this.budgetType = budgetType;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getBudgetTypeCode() {
		return budgetTypeCode;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setBudgetTypeCode(final String budgetTypeCode) {
		this.budgetTypeCode = budgetTypeCode;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getSubjectType() {
		return subjectType;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectType(final String subjectType) {
		this.subjectType = subjectType;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getSubjectTypeCode() {
		return subjectTypeCode;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectTypeCode(final String subjectTypeCode) {
		this.subjectTypeCode = subjectTypeCode;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectCode(final String subjectCode) {
		this.subjectCode = subjectCode;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectName(final String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * get方法
	 * @return
	 */
	public int getSubjectLevel() {
		return subjectLevel;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectLevel(final int subjectLevel) {
		this.subjectLevel = subjectLevel;
	}

	/**
	 * get方法
	 * @return
	 */
	public int getSubjectProperty() {
		return subjectProperty;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSubjectProperty(final int subjectProperty) {
		this.subjectProperty = subjectProperty;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getIyear() {
		return iyear;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setIyear(final String iyear) {
		this.iyear = iyear;
	}

	/**
	 * get方法
	 * @return
	 */
	public Double getFloatingRate() {
		return floatingRate;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setFloatingRate(final Double floatingRate) {
		this.floatingRate = floatingRate;
	}

	/**
	 * get方法
	 * @return
	 */
	public int getIsEnable() {
		return isEnable;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setIsEnable(final int isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * get方法
	 * @return
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setSort(final int sort) {
		this.sort = sort;
	}

	/**
	 * get方法
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * set方法
	 * @return
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}
	/**
	 * 重写processRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		this.setId(rs.getLong("id"));
		this.setZid(rs.getLong("zid"));
		this.setCompanyId(rs.getLong("companyId"));
		this.setUpdateID(rs.getLong("updateID"));
		this.setUpdateTime(rs.getTimestamp("UpdateTime"));
		this.setCreateTime(rs.getTimestamp("CreateTime"));
		this.setCreateID(rs.getLong("CreateID"));
		this.setBudgetType(rs.getString("budgetType"));
		this.setBudgetTypeCode(rs.getString("budgetTypeCode"));
		this.setSubjectType(rs.getString("subjectType"));
		this.setSubjectTypeCode(rs.getString("subjectTypeCode"));
		this.setSubjectCode(rs.getString("subjectCode"));
		this.setSubjectName(rs.getString("subjectName"));
		this.setSubjectLevel(rs.getInt("subjectLevel"));
		this.setSubjectProperty(rs.getInt("subjectProperty"));
		this.setParentId(rs.getString("parentId"));
		this.setIyear(rs.getString("iyear"));
		this.setFloatingRate(rs.getDouble("floatingRate"));
		this.setIsEnable(rs.getInt("isEnable"));
		this.setSort(rs.getInt("sort"));
		this.setRemark(rs.getString("remark"));
	}	
	/**
	 * 重写mapRow方法
	 */
	@Override
	public Subject mapRow(final ResultSet rs,final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		final Subject  subject = new Subject();
		subject.setId(rs.getLong("id"));
		subject.setZid(rs.getLong("zid"));
		subject.setCompanyId(rs.getLong("companyId"));
		subject.setUpdateID(rs.getLong("updateID"));
		subject.setUpdateTime(rs.getTimestamp("UpdateTime"));
		subject.setCreateTime(rs.getTimestamp("CreateTime"));
		subject.setCreateID(rs.getLong("CreateID")); 
		subject.setBudgetType(rs.getString("budgetType"));
		subject.setBudgetTypeCode(rs.getString("budgetTypeCode"));
		subject.setSubjectType(rs.getString("subjectType"));
		subject.setSubjectTypeCode(rs.getString("subjectTypeCode"));
		subject.setSubjectCode(rs.getString("subjectCode"));
		subject.setSubjectName(rs.getString("subjectName"));
		subject.setSubjectLevel(rs.getInt("subjectLevel"));
		subject.setSubjectProperty(rs.getInt("subjectProperty"));
		subject.setParentId(rs.getString("parentId"));
		subject.setIyear(rs.getString("iyear"));
		subject.setFloatingRate(rs.getDouble("floatingRate"));
		subject.setIsEnable(rs.getInt("isEnable"));
		subject.setSort(rs.getInt("sort"));
		subject.setRemark(rs.getString("remark"));
		return  subject;
	}

}
