package com.hbcsoft.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.workflow.dao.WorkflowDao;
import com.hbcsoft.workflow.entity.WorkflowBusinessMap;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.workflow.entity.WorkflowDefine;
import com.hbcsoft.workflow.entity.WorkflowModelQuery;
import com.hbcsoft.workflow.entity.WorkflowNode;
import com.hbcsoft.workflow.entity.WorkflowNodeRule;
import com.hbcsoft.workflow.entity.WorkflowNodeRuleD;

@Transactional
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowDao dao;
	
	@Override
	public void addWC(WorkflowConfig data) {
		dao.addWC(data);
	}

	@Override
	public void updateWC(WorkflowConfig data) {
		dao.updateWC(data);
	}

	@Override
	public void delCon(String id) {
		dao.delCon(id);
	}
	@Override
	public WorkflowConfig getConfigById(WorkflowConfig data) {
		return dao.getConfigById(data);
	}

	@Override
	public List<WorkflowConfig> getConfigShow(WorkflowModelQuery m) {
		return dao.getConfigShow(m);
	}

	@Override
	public List<WorkflowConfig> getCSTable(WorkflowModelQuery m) {
		return dao.getCSTable(m);
	}

	@Override
	public List<WorkflowConfig> getCS(WorkflowModelQuery m) {
		return dao.getCS(m);
	}

	@Override
	public Long add(WorkflowDefine data) {
		long id = dao.add(data);
		return id;
	}

	@Override
	public void update(WorkflowDefine w) {
		dao.update(w);
	}

	@Override
	public WorkflowDefine getWD(Long id) {
		return dao.getWD(id);
	}

	@Override
	public List<WorkflowDefine> getDefine() {
		return dao.getDefine();
	}

	@Override
	public List<WorkflowDefine> getDefine(WorkflowModelQuery m) {
		return dao.getDefine(m);
	}

	@Override
	public List<WorkflowNode> getNode(WorkflowModelQuery m) {
		return dao.getNode(m);
	}

	@Override
	public void delData(String id) {
		if(id!=null && id.length()>0){
			for(String str:id.split(",")){
				dao.dW(Long.parseLong(str));
			}
		}
	}

	@Override
	public WorkflowNode getNodeShow(long nId) {
		return dao.getNodeShow(nId);
	}

	@Override
	public List<WorkflowNodeRule> getNodeRule(WorkflowModelQuery m) {
		return dao.getNodeRule(m);
	}

	@Override
	public void nodeUpdate(WorkflowNode w) {
		dao.nodeUpdate(w);
	}

	@Override
	public long nodeAdd(WorkflowNode data) {
		long id = dao.nodeAdd(data);
		return id;
	}

	@Override
	public void delNode(String id) {
		if(id!=null && id.length()>0){
			for(String str:id.split(",")){
				dao.dWN(0,Long.parseLong(str));
			}
		}
	}

	@Override
	public WorkflowNodeRule getNRById(long id) {
		return dao.getNRById(id);
	}

	@Override
	public List<WorkflowNodeRuleD> nodeRDList(WorkflowModelQuery query) {
		return dao.nodeRDList(query);
	}

	@Override
	public List<WorkflowNode> getNodeByWfId(long wfId) {
		return dao.getNodeByWfId(wfId);
	}

	@Override
	public long ruleUpdate(WorkflowNodeRule w) {
		return dao.ruleUpdate(w);
	}

	@Override
	public long ruleAdd(WorkflowNodeRule w) {
		return dao.ruleAdd(w);
	}

	@Override
	public void delRule(String id) {
		if(id!=null && id.length()>0){
			for(String str:id.split(",")){
				dao.dWNR(0,Long.parseLong(str));
			}
		}
	}

	@Override
	public WorkflowNodeRuleD getWNRDById(long ruleDId) {
		return dao.getWNRDById(ruleDId);
	}

	@Override
	public long updateNodeRD(WorkflowNodeRuleD w) {
		return dao.updateNodeRD(w);
	}

	@Override
	public void delNRD(String id) {
		if(id!=null && id.length()>0){
			for(String str:id.split(",")){
				dao.dWNRD(0,Long.parseLong(str));
			}
		}
	}

	@Override
	public List<WorkflowBusinessMap> getBusinessMap(WorkflowModelQuery m) {
		return dao.getBusinessMap(m);
	}

	@Override
	public WorkflowBusinessMap getBusinessMapById(long id) {
		return dao.getBusinessMapById(id);
	}

	@Override
	public boolean addBusinessMap(WorkflowBusinessMap data) {
		dao.addBusinessMap(data);
		return false;
	}

	@Override
	public boolean updateBusinessMap(WorkflowBusinessMap data) {
		dao.updateBusinessMap(data);
		return false;
	}

	@Override
	public void delBM(String id) {
		if(id!=null && id.length()>0){
			for(String str:id.split(",")){
				dao.delBM(Long.parseLong(str));
			}
		}
	}

}
