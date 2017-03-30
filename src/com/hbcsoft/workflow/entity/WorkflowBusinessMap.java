package com.hbcsoft.workflow.entity;

import javax.persistence.Table;

@Table(name="HBC_F_BUSINESSMAP")
public class WorkflowBusinessMap extends WorkflowBase {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6526173975100338369L;

	/**
	 * 工作流ID
	 */
	private long workflowId;
	
	/**
	 * 业务类型Action名称
	 */
	private int actionType;
	
	/**
	 * 单据标识
	 */
	private Integer flag;
	
	/**
	 * 备注
	 */
	private String remark ;

	public long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(long workflowId) {
		this.workflowId = workflowId;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
