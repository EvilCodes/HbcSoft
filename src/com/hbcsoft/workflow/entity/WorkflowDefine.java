package com.hbcsoft.workflow.entity;

import javax.persistence.Table;

@Table(name="HBC_F_DEFINE")
public class WorkflowDefine extends WorkflowBase {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程名称
	 */
	private String name;
	
	/**
	 * 是否可用
	 */
	private Integer isEnable = 1;
	
	/**
	 * 是否提供跟踪按钮
	 */
	private Integer isTrcakForm = 1;
	
	/**
	 * 是否自动生成标题
	 */
	private Integer isAutoTitle = 0;
	
	/**
	 * 默认标题格式
	 */
	private String defaultTitle;
	
	/**
	 * 是否快速审核
	 */
	private Integer isQuick = 0;
	
	/**
	 * 快速审核规则
	 */
	private String quickRule;
	
	/**
	 * 是否归档
	 */
	private Integer isArchives = 0;
	
	/**
	 * 如果下一个节点的办理人为多人时，是否弹框选择
	 * 1为是，为办理时，弹框选择下一个节点办理人
	 * 0为否，默认都发送，谁先读取，谁办理
	 */
	private Integer isSelNextUser=0;

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

	public Integer getIsTrcakForm() {
		return isTrcakForm;
	}
	public void setIsTrcakForm(final Integer isTrcakForm) {
		this.isTrcakForm = isTrcakForm;
	}

	public Integer getIsAutoTitle() {
		return isAutoTitle;
	}
	public void setIsAutoTitle(final Integer isAutoTitle) {
		this.isAutoTitle = isAutoTitle;
	}

	public String getDefaultTitle() {
		return defaultTitle;
	}
	public void setDefaultTitle(final String defaultTitle) {
		this.defaultTitle = defaultTitle;
	}

	public Integer getIsQuick() {
		return isQuick;
	}
	public void setIsQuick(final Integer isQuick) {
		this.isQuick = isQuick;
	}

	public String getQuickRule() {
		return quickRule;
	}
	public void setQuickRule(final String quickRule) {
		this.quickRule = quickRule;
	}

	public Integer getIsArchives() {
		return isArchives;
	}
	public void setIsArchives(final Integer isArchives) {
		this.isArchives = isArchives;
	}

	public Integer getIsSelNextUser() {
		return isSelNextUser;
	}
	public void setIsSelNextUser(Integer isSelNextUser) {
		this.isSelNextUser = isSelNextUser;
	}
}
