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
@Table(name="T_SYS_BUDGETSUBJECT")
public class BudgetSubject extends BaseEntity<BudgetSubject> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public BudgetSubject() {
		super();
	}
	private Date create_time;
	private String create_userId;
	private String update_usetId;
	private Date update_time;
	/*
	 * 预算类型
	 * */
	private Integer budgetType;
	/*
	 * 科目编码
	 * */
	private String code;
	/*
	 * 是否启用
	 * */
	private Integer enable;
	/*
	 * 浮动比例
	 * */
	private Double floatIngrate;
	/*
	 * 科目名称
	 * */
	private String name;
	/*
	 * 排序
	 * */
	private Integer sort;
	/*
	 * 科目类型
	 * */
	private Integer subjectType;
	/*
	 * 科目属性
	 * */
	private Integer iSubjectProperty;
	/*
	 * 科目备注
	 * */
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
	public String getUpdate_usetId() {
		return update_usetId;
	}
	public void setUpdate_usetId(String update_usetId) {
		this.update_usetId = update_usetId;
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
		this.setMemo(rs.getString(memo));
		this.setName(rs.getString("name"));
		this.setSort(rs.getInt("sort"));
		this.setSubjectType(rs.getInt("subjectType"));
		this.setUpdate_usetId(rs.getString("update_usetId"));
		this.setUpdate_time(rs.getDate("update_time"));
	}
	@Override
	public BudgetSubject mapRow(ResultSet rs, int arg1) throws SQLException {
		BudgetSubject budgetSubject = new BudgetSubject();
		budgetSubject.setId(rs.getLong("id"));
		
		
		return null;
	}
	
}
