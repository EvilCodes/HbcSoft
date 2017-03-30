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
 * 第三方数据库连接
 */
@Table(name="T_SYS_OUTERSUBJECT")
public class OuterSubject extends BaseEntity<OuterSubject> implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8006756630685562344L;
	
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
	private String year;	
	/**
	 * 数据库类型:
	 * 	0--Mysql
	 * 	1--sqlServer 
	 *	2--Oracle
	 */
	private int dbType;
	/**
	 *数据库名称
	 */
	private String dbsId;	
	/**
	 * 浮动比例
	 */
	private Double floatingRate;	
	/**
	 * 是否已匹配  0--否  1--是
	 */
	private int isMatched;	
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
	 */
	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getYear() {
		return year;
	}
	/**
	 * set方法
	 */
	public void setYear(final String year) {
		this.year = year;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getDbType() {
		return dbType;
	}
	/**
	 * set方法
	 */
	public void setDbType(final int dbType) {
		this.dbType = dbType;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbsId() {
		return dbsId;
	}
	/**
	 * set方法
	 */
	public void setDbsId(final String dbsId) {
		this.dbsId = dbsId;
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
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getIsMatched() {
		return isMatched;
	}
	/**
	 * set方法
	 * 
	 */
	public void setIsMatched(final int isMatched) {
		this.isMatched = isMatched;
	}
	/**
	 * 重写BaseEntity的processRow方法进行赋值
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
		this.setYear(rs.getString("year"));
		this.setDbType(rs.getInt("dbType"));
		this.setDbsId(rs.getString("dbsId"));
		this.setFloatingRate(rs.getDouble("floatingRate"));
		this.setIsEnable(rs.getInt("isEnable"));
		this.setIsMatched(rs.getInt("isMatched"));
		this.setSort(rs.getInt("sort"));
		this.setRemark(rs.getString("remark"));
	}	
	/**
	 * 重写BaseEntity的OuterSubject方法进行赋值
	 */
	@Override
	public OuterSubject mapRow(final ResultSet rs,final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		 final OuterSubject  os = new OuterSubject();
		 os.setId(rs.getLong("id"));
		 os.setZid(rs.getLong("zid"));
		 os.setCompanyId(rs.getLong("companyId"));
		 os.setUpdateID(rs.getLong("updateID"));
		 os.setUpdateTime(rs.getTimestamp("UpdateTime"));
		 os.setCreateTime(rs.getTimestamp("CreateTime"));
		 os.setCreateID(rs.getLong("CreateID")); 
		 os.setBudgetType(rs.getString("budgetType"));
		 os.setBudgetTypeCode(rs.getString("budgetTypeCode"));
		 os.setSubjectType(rs.getString("subjectType"));
		 os.setSubjectTypeCode(rs.getString("subjectTypeCode"));
		 os.setSubjectCode(rs.getString("subjectCode"));
		 os.setSubjectName(rs.getString("subjectName"));
		 os.setSubjectLevel(rs.getInt("subjectLevel"));
		 os.setSubjectProperty(rs.getInt("subjectProperty"));
		 os.setParentId(rs.getString("parentId"));
		 os.setYear(rs.getString("year"));
		 os.setDbType(rs.getInt("dbType"));
		 os.setDbsId(rs.getString("dbsId"));
		 os.setFloatingRate(rs.getDouble("floatingRate"));
		 os.setIsEnable(rs.getInt("isEnable"));
		 os.setIsMatched(rs.getInt("isMatched"));
		 os.setSort(rs.getInt("sort"));
		 os.setRemark(rs.getString("remark"));
		return  os;
	}
}
