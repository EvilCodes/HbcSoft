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
@Table(name="T_SYS_BUDGET")
public class Budget extends BaseEntity<Budget> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public Budget() {
		super();
	}
	private String create_userID;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	private String budgetTypeId;
	private String code;
	private Integer enable;
	private Double floatingRate;
	private String name;
	private String subjectTypeId;
	private String userId;
	private String parentId;
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreate_userID() {
		return create_userID;
	}
	public void setCreate_userID(String create_userID) {
		this.create_userID = create_userID;
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
	public String getBudgetTypeId() {
		return budgetTypeId;
	}
	public void setBudgetTypeId(String budgetTypeId) {
		this.budgetTypeId = budgetTypeId;
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
	public Double getFloatingRate() {
		return floatingRate;
	}
	public void setFloatingRate(Double floatingRate) {
		this.floatingRate = floatingRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectTypeId(String subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setBudgetTypeId(rs.getString("budgetTypeId"));
		this.setCode(rs.getString("code"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userID(rs.getString("create_userID"));
		this.setCreateTime(rs.getDate("createTime"));
		this.setEnable(rs.getInt("enable"));
		this.setFloatingRate(rs.getDouble("floatingRate"));
		this.setId(rs.getLong("id"));
		this.setName(rs.getString("name"));
		this.setParentId(rs.getString("parentId"));
		this.setSubjectTypeId(rs.getString("subjectTypeId"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setUserId(rs.getString("userId"));
		
	}
	@Override
	public Budget mapRow(ResultSet rs, int arg1) throws SQLException {
		Budget budget = new Budget();
		budget.setId(rs.getLong("id"));
		
		return budget;
	}
	
}
