package com.hbcsoft.workflow.entity;

import javax.persistence.Table;

@Table(name="HBC_F_NODE")
public class WorkflowNode extends WorkflowBase {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程ID
	 */
	private Long wfId;
	
	/**
	 * 是否可用
	 */
	private Integer isEnable = 1;
	
	/**
	 * 节点序号
	 */
	private Integer sort;
	
	/**
	 * 节点名称
	 */
	private String name;
	
	/**
	 * 办理部门ID
	 */
	private String deptId;
	
	/**
	 * 办理角色ID
	 */
	private String roleId;
	
	/**
	 * 是否会签
	 */
	private Integer isCounterSign = 0;
	
	/**
	 * 会签办理人搜索表
	 */
	private String counterSignTableName;
	
	/**
	 * 会签办理人搜索规则(字段名)
	 */
	private String counterSignRule;
	
	/**
	 * 是否自动审核通过
	 */
	private Integer isAutoAdopt = 0;
	
	/**
	 * 是否允许办理
	 */
	private Integer isDepute = 1;
	
	/**
	 * 是否短信提醒
	 */
	private Integer isSMS = 0;
	
	/**
	 * 是否邮件提醒
	 */
	private Integer isEmail = 0;
	
	/**
	 * 同一审核人是否自动跳过
	 */
	private Integer isAutoSkip = 1;
	
	/**
	 * 办理部门状态
	 * 1、制单人部门
	 * 2、固定角色
	 * 3、主管院长
	 * 4、固定部门
	 */
	private Integer deptStatus = 1;

	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(final Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Long getWfId() {
		return wfId;
	}
	public void setWfId(final Long wfId) {
		this.wfId = wfId;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(final Integer sort) {
		this.sort = sort;
	}

	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(final String deptId) {
		this.deptId = deptId;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

	public Integer getIsCounterSign() {
		return isCounterSign;
	}
	public void setIsCounterSign(final Integer isCounterSign) {
		this.isCounterSign = isCounterSign;
	}

	public String getCounterSignTableName() {
		return counterSignTableName;
	}
	public void setCounterSignTableName(String counterSignTableName) {
		this.counterSignTableName = counterSignTableName;
	}

	public String getCounterSignRule() {
		return counterSignRule;
	}
	public void setCounterSignRule(final String counterSignRule) {
		this.counterSignRule = counterSignRule;
	}

	public Integer getIsAutoAdopt() {
		return isAutoAdopt;
	}
	public void setIsAutoAdopt(final Integer isAutoAdopt) {
		this.isAutoAdopt = isAutoAdopt;
	}

	public Integer getIsDepute() {
		return isDepute;
	}
	public void setIsDepute(final Integer isDepute) {
		this.isDepute = isDepute;
	}

	public Integer getIsSMS() {
		return isSMS;
	}
	public void setIsSMS(final Integer isSMS) {
		this.isSMS = isSMS;
	}

	public Integer getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(final Integer isEmail) {
		this.isEmail = isEmail;
	}

	public Integer getIsAutoSkip() {
		return isAutoSkip;
	}
	public void setIsAutoSkip(final Integer isAutoSkip) {
		this.isAutoSkip = isAutoSkip;
	}

	public Integer getDeptStatus() {
		return deptStatus;
	}
	public void setDeptStatus(Integer deptStatus) {
		this.deptStatus = deptStatus;
	}
}
