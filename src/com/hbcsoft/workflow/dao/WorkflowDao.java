package com.hbcsoft.workflow.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.workflow.entity.WorkflowBusinessMap;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.workflow.entity.WorkflowDefine;
import com.hbcsoft.workflow.entity.WorkflowModelQuery;
import com.hbcsoft.workflow.entity.WorkflowNode;
import com.hbcsoft.workflow.entity.WorkflowNodeRule;
import com.hbcsoft.workflow.entity.WorkflowNodeRuleD;

@Repository
public interface WorkflowDao {
	/**
	 * 保存工作流基础配置信息
	 * @param data
	 * @return
	 */
	public void addWC(WorkflowConfig data);
	/**
	 * 修改工作流基础配置信息
	 * @param data
	 * @return
	 */
	public void updateWC(WorkflowConfig data);
	/**
	 * 配置表（删除）
	 * @param w
	 */
	public void delCon(String id);
	/**
	 * 根据id获取配置
	 * @param data	包含有id 的实体类
	 * @return
	 */
	public WorkflowConfig getConfigById(WorkflowConfig data);
	/**
	 * 获取工作流基本配置表
	 * @param m
	 * @return
	 */
	public List<WorkflowConfig> getConfigShow(WorkflowModelQuery m);
	/**
	 * 无分页获取表名配置
	 */
	public List<WorkflowConfig> getCSTable(WorkflowModelQuery m);
	/**
	 * 无分页获取配置
	 */
	public List<WorkflowConfig> getCS(WorkflowModelQuery m);
	/***============================工作流定义表start=======================================***/
	/**
	 * 数据保存
	 * 工作流定义表HBC_F_DEFINE
	 * @param data
	 * @return
	 */
	public Long add(WorkflowDefine data);
	/**
	 * 修改定义表方法
	 * @param w
	 * @return
	 */
	public void update(WorkflowDefine w);
	/**
	 * 删除工作流
	 * 按主键删除工作流
	 */
	public void dW(long value);
	/**
	 * 通过id获取工作流实体
	 * @param id
	 * @return
	 */
	public WorkflowDefine getWD(Long id);
	/**
	 * 获取可用流程表
	 * @return
	 */
	public List<WorkflowDefine> getDefine();
	/**
	 * 获取定义列表
	 * 工作流定义表HBC_F_DEFINE
	 * @return
	 */
	public List<WorkflowDefine> getDefine(WorkflowModelQuery m);
	
	/***============================工作流定义表end=======================================***/
	/***============================工作流节点表start=======================================***/
	/**
	 * 获取工作流节点表
	 * @param m
	 * @return
	 */
	public List<WorkflowNode> getNode(WorkflowModelQuery m);
	/**
	 * 通过nId获取节点信息
	 * @param nId
	 * @return
	 */
	public WorkflowNode getNodeShow(long nId);
	/** 获取节点规则列表
	 * 工作流节点规则HBC_F_NODERULE
	 * @return
	 */
	public List<WorkflowNodeRule> getNodeRule(WorkflowModelQuery m);
	/**
	 * 节点修改
	 * @param w
	 */
	public void nodeUpdate(WorkflowNode w);
	/**
	 * 工作流节点数据保存
	 * @param data
	 * @return
	 */
	public long nodeAdd(WorkflowNode data);
	/**
	 * 删除节点
	 * deleteWorkflowNode
	 * @param column	要删除的列名（0:主键，1：外键）
	 * @param value		条件的值
	 */
	public void dWN(int i,long value);
	/***============================工作流节点表end=======================================***/
	/***============================工作流规则表start=======================================***/
	/**
	 * 根据id获取规则实体
	 * @param id
	 * @return
	 */
	public WorkflowNodeRule getNRById(long id);
	/**
	 * 通过规则Id获取子规则列表
	 * @param nId
	 * @return
	 */
	public List<WorkflowNodeRuleD> nodeRDList(WorkflowModelQuery query);
	/**
	 * 通过流程id获取节点列表
	 * @param wfId	流程id
	 * @return
	 */
	public List<WorkflowNode> getNodeByWfId(long wfId);
	/**
	 * 修改规则
	 * @param w
	 * @return
	 */
	public long ruleUpdate(WorkflowNodeRule w);
	/**
	 * 新增规则
	 * @param w
	 * @return
	 */
	public long ruleAdd(WorkflowNodeRule w);
	/**
	 * 删除规则
	 * deleteWorkflowNodeRule
	 * @param i			0为按主键删除，其他按外键删除
	 * @param value		条件的值
	 */
	public void dWNR(int i,long value);
	/***============================工作流规则表end=======================================***/
	/***============================工作流子规则表start=======================================***/
	/**
	 * 通过id获取规则子表信息
	 */
	public WorkflowNodeRuleD getWNRDById(long ruleDId);
	/**
	 * 规则子表（添加和修改）
	 * @param w
	 */
	public long updateNodeRD(WorkflowNodeRuleD w);
	/**
	 * 删除规则子表
	 * @param i		根据（主键、外键）删除
	 * @param value	值
	 */
	public void dWNRD(int i, long value);
	/***============================工作流子规则表end=======================================***/
	/***============================工作流业务绑定start=======================================***/
	/**
	 * 获取工作流业务绑定列表
	 * HBC_F_BUSINESSMAP
	 * @param m
	 * @return
	 */
	public List<WorkflowBusinessMap> getBusinessMap(WorkflowModelQuery m);
	/**
	 * 通过id获取绑定业务
	 * @return
	 */
	public WorkflowBusinessMap getBusinessMapById(long id);
	/**
	 * 保存工作流业务绑定列表
	 * @param data
	 * @return
	 */
	public void addBusinessMap(WorkflowBusinessMap data);
	/**
	 * 修改工作流业务绑定列表
	 * @param data
	 * @return
	 */
	public void updateBusinessMap(WorkflowBusinessMap data);
	/**
	 * 删除绑定表
	 * @param i		根据（主键）删除
	 */
	public void delBM(long id);
	/***============================工作流业务绑定end=======================================***/
}
