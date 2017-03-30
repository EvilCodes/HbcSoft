package com.hbcsoft.workflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.workflow.entity.KeyValue;
import com.hbcsoft.workflow.entity.WorkflowBusinessMap;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.workflow.entity.WorkflowDefine;
import com.hbcsoft.workflow.entity.WorkflowModelQuery;
import com.hbcsoft.workflow.entity.WorkflowNode;
import com.hbcsoft.workflow.entity.WorkflowNodeRule;
import com.hbcsoft.workflow.entity.WorkflowNodeRuleD;
import com.hbcsoft.workflow.service.WorkflowService;

@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;
	
	/*******************************工作流基础配置start***********************************/
	/**
	 * 基本配置列表
	 * @param workflowConfig
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/configShow", method = RequestMethod.GET)
	public ModelAndView configShow(@ModelAttribute("workflowConfig") WorkflowConfig workflowConfig, BindingResult result, Integer page){
		WorkflowModelQuery query = new WorkflowModelQuery();
		query.setColumnName(workflowConfig.getColumnName());
		query.setTableName(workflowConfig.getTableName());
		ModelAndView mav = new ModelAndView("workflow/workflowconfigList");
		if(page != null && page > 0){
			query.setPage(page);
		}
		List<WorkflowConfig> list = workflowService.getConfigShow(query);
		if(list.isEmpty()){
			list = null;
		}
		String tableName = "";
		String columnName = "";
		if(query.getColumnName()!=null && !"".equals(query.getColumnName())){
			columnName = query.getColumnName();
		}
		if(query.getTableName()!=null && !"".equals(query.getTableName())){
			tableName = query.getTableName();
		}
		mav.getModelMap().put("tableName",tableName );
		mav.getModelMap().put("columnName", columnName);
		mav.getModelMap().put("list", list);
		mav.getModelMap().put("page", query.getPage());
		return mav;
	}
	
	/**
	 * 配置新增页面
	 * /workflow/manage/configInput.hbc
	 * @param workflowConfig
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/configInput", method = RequestMethod.GET)
	public ModelAndView configInput(@ModelAttribute("workflowConfig") WorkflowConfig workflowConfig, BindingResult result){ 
		ModelAndView mav = new ModelAndView("workflow/workflowConfigInput");
		if(workflowConfig.getBaseId()!=0 && workflowConfig.getBaseId()>0){
			workflowConfig = workflowService.getConfigById(workflowConfig);
		}else{
			workflowConfig = new WorkflowConfig();
		}
		mav.getModelMap().put("workflowConfig", workflowConfig);
		return mav;
	}
	
	/**
	 * 新增、编辑配置
	 * /workflow/manage/configAdd.hbc
	 * @param workflowConfig
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/configAdd", method = RequestMethod.POST)
	public String configAdd(@ModelAttribute("workflowConfig") WorkflowConfig workflowConfig, BindingResult result){
		//session取值
		workflowConfig.setCreateId("1");
		workflowConfig.setCreateName("admin");
		workflowConfig.setUpdateId("1");
		workflowConfig.setUpdateName("admin");
		
		//获取时间
		Date date = new Date();
		workflowConfig.setCreateTime(date);
		workflowConfig.setUpdateTime(date);
		if(workflowConfig.getBaseId()!=0){
			workflowService.updateWC(workflowConfig);
		}else{
			
			workflowService.addWC(workflowConfig);
		}
		return "redirect:configShow.hbc";
	}
	
	/**
	 * 配置删除
	 * /workflow/manage/delConfig.hbc
	 * @param workflowConfig
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delConfig", method = RequestMethod.GET)
	public String delConfig(@ModelAttribute("workflowConfig") WorkflowConfig workflowConfig, BindingResult result,String id){ 
		workflowService.delCon(id);
		return "redirect:configShow.hbc";
	}
	/**
	 * 基本配置列表翻页
	 * @param workflowConfig
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/configShowPost", method = RequestMethod.POST)
	public ModelAndView configShowPost(@ModelAttribute("workflowConfig") WorkflowConfig workflowConfig, BindingResult result, Integer page){
		return configShow(workflowConfig, result, page);
	}
	/*********************************工作流基础配置end*********************************/
	
	/*********************************工作流定义start*********************************/
	/**
	 * 获取流程列表
	 * /workflow/showData.hbc
	 * @param workflowDefine
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/showData", method = RequestMethod.GET)
	public ModelAndView showData(@ModelAttribute("workflowDefine") WorkflowDefine workflowDefine,WorkflowModelQuery query){ 
		if(query==null){
			query = new WorkflowModelQuery();
		}
		List<WorkflowDefine> list = workflowService.getDefine(query);
		ModelAndView mav = new ModelAndView("workflow/workflowDefine");
		if(list.isEmpty()) {
			list=null;
		}
		String name = "";
		if(query.getWfName()!=null&&!"".equals(query.getWfName()))
		{
			name = query.getWfName();
		}
		
		mav.getModelMap().put("wfName", name);
		mav.getModelMap().put("list", list);
		mav.getModelMap().put("page", query.getPage());
		return mav;
	}
	/**
	 * 打开新增、修改流程页面
	 * /workflow/showInput.hbc
	 * @return 新增页面
	 */
	@RequestMapping(value = "/showInput", method = RequestMethod.GET)
	public ModelAndView showInput(WorkflowModelQuery query){ 
		//分页以及条件类
		if(query ==null){
			query = new WorkflowModelQuery();
		}
		//跳转类
		ModelAndView mav = new ModelAndView("workflow/workflowDefineInput");
		
		WorkflowDefine data =null;
		//新增修改操作判别处理
		long wfId = query.getWfId();
		if(wfId!=0 && wfId>0){
			data = workflowService.getWD(wfId);
			query.setWfId(wfId);
			query.setOrder(" order by SORT");
			List<WorkflowNode> list = workflowService.getNode(query);
			mav.getModelMap().put("list", list);
		}else{
			data = new WorkflowDefine();
		}
		
		mav.getModelMap().put("wfId", wfId);
		mav.getModelMap().put("page", query.getPage());
		mav.getModelMap().put("workflowDefine", data);
		return mav;
	}
	/**
	 * 新增、修改流程
	 * /workflow/addData.hbc
	 * @param workflowDefine
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public String addData(@ModelAttribute("dataNew") WorkflowDefine workflowDefine, BindingResult result)
	{
		//session取值
		workflowDefine.setUpdateId("1");
		workflowDefine.setUpdateName("admin");
		Date date = new Date();
		workflowDefine.setUpdateTime(date); 
		long wfId=workflowDefine.getBaseId();
		if( wfId != 0 && wfId>0){
			workflowService.update(workflowDefine);
		}else{
			workflowDefine.setCreateId("1");
			workflowDefine.setCreateName("admin");
			//获取时间
			workflowDefine.setCreateTime(date);
			wfId=workflowService.add(workflowDefine);
		}
		return "redirect:showData.hbc";
	}
	/**
	 * 删除流程
	 * @return
	 */
	@RequestMapping(value = "/delData", method = RequestMethod.GET)
	public String delData(String id) {
		workflowService.delData(id);
		return "redirect:showData.hbc";
	}
	/**
	 * 获取流程列表
	 * /workflow/manage/showDataPost.hbc
	 * @param workflowDefine
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/showDataPost", method = RequestMethod.POST)
	public ModelAndView showDataPost(@ModelAttribute("workflowDefine") WorkflowDefine workflowDefine/*, BindingResult result*/,WorkflowModelQuery query){ 
		return showData(workflowDefine, query);
	}
	/********************************工作流定义end**********************************/
	
	/********************************工作流节点列表start**********************************/
	/**
	 * 新增、修改节点页面
	 * /workflow/manage/nodeShowInput.hbc
	 * @author zhanghaijiao
	 */
	@RequestMapping(value = "/nodeShowInput", method = RequestMethod.GET)
	public ModelAndView nodeShowInput(WorkflowModelQuery query){
		ModelAndView mav = new ModelAndView("workflow/WorkflowNodeInput");
		WorkflowNode data = null;
		long nId = query .getnId();
//		//获取角色信息
//		List<Role> roles = new ArrayList<Role>();
//		Role role=new Role();
//		role.setName("请选择");
//		roles.add(role);
//		roles.addAll(workflowService.getRoles());
//		//获取部门
//		List<Org> orgs = new ArrayList<Org>();
//		Org org = new Org();
//		org.setName("请选择");
//		orgs.add(org);
//		orgs.addAll(workflowService.getOrgs());
//		mav.getModelMap().put("roles", roles);
//		mav.getModelMap().put("orgs", orgs);
		
		if(nId!=0 && nId > 0){
			data = workflowService.getNodeShow(nId);
			query.setnId(nId);
			query.setOrder("order by RULESORT");
			List<WorkflowNodeRule> list = workflowService.getNodeRule(query);//查找规则
			if(list.size()==0) list=null;
			mav.getModelMap().put("list", list);
			mav.getModelMap().put("page", query.getPage());
			mav.getModelMap().put("nId", nId);
		}else{
			data = new WorkflowNode();
		}
		mav.getModelMap().put("workflowNode", data);
		mav.getModelMap().put("wfId", query.getWfId());
		
		return mav;
	}
	/**
	 * 新增、修改节点
	 * /workflow/manage/nodeAddData.hbc
	 * @param workflowNode
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/nodeAddData", method = RequestMethod.POST)
	public String addDateNode(@ModelAttribute("nodeDataNew") WorkflowNode workflowNode, BindingResult result){
		//session取值
		
		//获取时间
		Date date = new Date();
		workflowNode.setUpdateTime(date);
		workflowNode.setUpdateName("admin");
		workflowNode.setUpdateId("1");
		long nId=0;
		if(workflowNode.getBaseId()!=0 && workflowNode.getBaseId()>0){
			nId=workflowNode.getBaseId();
			workflowService.nodeUpdate(workflowNode);
		}else{
			workflowNode.setCreateName("admin");
			workflowNode.setCreateTime(date);
			workflowNode.setCreateId("1");
			nId=workflowService.nodeAdd(workflowNode);
			workflowNode.setBaseId(nId);
		}
		return "redirect:showInput.hbc?wfId="+workflowNode.getWfId();
	}
	/**
	 * 删除节点
	 * @param workflowNodeRule
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delNode", method = RequestMethod.GET)
	public String delNode(String wfId, String id){
		workflowService.delNode(id);
		return "redirect:showInput.hbc?wfId="+wfId;
	}
	/********************************工作流节点列表end**********************************/
	/********************************工作流规则列表start**********************************/
	/**
	 * 新增、修改规则
	 * /workflow/nodeRuleInput.hbc
	 * @param workflowNodeRule
	 * @param result
	 * @param page	页码
	 * @param wfId	流程id
	 * @param nId	节点id
	 * @return
	 */
	@RequestMapping(value = "/nodeRuleInput", method = RequestMethod.GET)
	public ModelAndView nodeRuleInput(@ModelAttribute("workflowNodeRule") WorkflowNodeRule workflowNodeRule, 
		BindingResult result,WorkflowModelQuery query){ 
		
		ModelAndView mav = new ModelAndView("workflow/workflowRuleInput");
		long ruleId = query.getRuleId();
		if(ruleId != 0 && ruleId >0){
			WorkflowNodeRule w = workflowService.getNRById(ruleId);//根据id获取规则
			List<WorkflowNodeRuleD> list = workflowService.nodeRDList(query);//根据规则获取子规则
			if(list.size()==0) list=null;
			mav.getModelMap().put("workflowNodeRule", w);
			mav.getModelMap().put("list", list);
			mav.getModelMap().put("page", query.getPage());
		}else{
			mav.getModelMap().put("list", null);
			mav.getModelMap().put("page", query.getPage());
		}
		List<WorkflowNode> nodes = new ArrayList<WorkflowNode>();
		WorkflowNode node = new WorkflowNode();
		node.setName("提交无跳转节点");
		nodes.add(node);
		nodes.addAll(workflowService.getNodeByWfId(query.getWfId()));
		mav.getModelMap().put("nodes",nodes);
		mav.getModelMap().put("wfId",query.getWfId());
		mav.getModelMap().put("nId", query.getnId());
		mav.getModelMap().put("ruleId", query.getRuleId());
		return mav;
	}
	/**
	 * 保存规则
	 * /workflow/manage/nodeRuleInput.hbc
	 * @param workflowNodeRule
	 * @param result
	 * @param page	页码
	 * @param wfId	流程id
	 * @param nId	节点id
	 * @return
	 */
	@RequestMapping(value = "/nodeRuleAdd", method = RequestMethod.POST)
	public String nodeRuleAdd(@ModelAttribute("workflowNodeRule") WorkflowNodeRule workflowNodeRule, BindingResult result,long nId,long ruleId)
	{ 
		workflowNodeRule.setBaseId(ruleId);
		workflowNodeRule.setNodeId(nId);
		//session取值
		
		//获取时间
		Date date = new Date();
		workflowNodeRule.setUpdateTime(date);
		workflowNodeRule.setUpdateName("admin");
		workflowNodeRule.setUpdateId("1");
		
		if(ruleId!=0 && ruleId>0){
			workflowService.ruleUpdate(workflowNodeRule);
		}else{
			ruleId=workflowService.ruleAdd(workflowNodeRule);
			workflowNodeRule.setBaseId(ruleId);
		}
		return "redirect:nodeShowInput.hbc?nId="+nId+"&wfId="+workflowNodeRule.getWfId();
	}
	/**
	 * 删除规则表
	 * @param workflowNodeRule
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delRule", method = RequestMethod.GET)
	public String delRule(String id, String wfId, String nId)	{
		workflowService.delRule(id);
		return "redirect:nodeShowInput.hbc?wfId="+wfId+"&nId="+nId;
	}
	/********************************工作流规则列表end**********************************/
	
	/********************************工作流子规则列表start**********************************/
	/**
	 * 跳规则子表编辑页面
	 * /workflow/manage/nodeRuleDInput.hbc
	 * @param workflowNodeRule
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/nodeRuleDInput", method = RequestMethod.GET)
	public ModelAndView nodeRuleDInput(@ModelAttribute("workflowNodeRuleD") WorkflowNodeRuleD workflowNodeRuleD, BindingResult result,Long ruleDId, long ruleId, long wfId, long nId,String columnId)
	{
		ModelAndView mav = new ModelAndView("workflow/nodeRuleDetailInput");
		
		if(ruleDId!=null && ruleDId!=0 && ruleDId > 0){
			workflowNodeRuleD = workflowService.getWNRDById(ruleDId);
		}else{
			workflowNodeRuleD = new WorkflowNodeRuleD();
		}
		//获取配置表信息，分类传到页面
		List<WorkflowConfig> list =new ArrayList<WorkflowConfig>();
		WorkflowConfig workflowConfig = new WorkflowConfig();
		workflowConfig.setTableName("请选择表名");
		list.add(workflowConfig);
		list.addAll(workflowService.getCSTable(null));
		mav.getModelMap().put("list", list);//表名列表
		mav.getModelMap().put("workflowNodeRuleD", workflowNodeRuleD);//表名列表
		mav.getModelMap().put("ruleId", ruleId);
		mav.getModelMap().put("wfId", wfId);
		mav.getModelMap().put("nId", nId);
		
		return mav;
	} 
	/**
	 * 子表编辑页面的保存
	 * /workflow/manage/nodeRuleDetailAdd.hbc
	 * @param workflowNodeRule	页面配置的信息类
	 * @return
	 */
	@RequestMapping(value = "/nodeRuleDetailAdd", method = RequestMethod.POST)
	public String nodeRuleDetailAdd(@ModelAttribute("workflowNodeRuleD") WorkflowNodeRuleD workflowNodeRuleD,
			BindingResult result, long ruleId, long wfId, long nId,Long ruleDId,String columnId){
		WorkflowModelQuery model = new WorkflowModelQuery();
		model.setColumnId(workflowNodeRuleD.getColumnId());
		model.setTableId(workflowNodeRuleD.getTableId());
		
		List<WorkflowConfig> workflowConfigList= workflowService.getCS(model);//获取该table和column的配置信息
		if(workflowConfigList==null || workflowConfigList.isEmpty()){
			return null;
		}
		workflowNodeRuleD.setColumnName(workflowConfigList.get(0).getColumnName());//通过数据库补齐未传的值
		workflowNodeRuleD.setTableName(workflowConfigList.get(0).getTableName());//通过数据库补齐未传的值
		workflowNodeRuleD.setBaseId(ruleDId);
		workflowNodeRuleD.setRuleId(ruleId);
		//session取值
		workflowNodeRuleD.setCreateId("1");
		workflowNodeRuleD.setCreateName("admin");
		workflowNodeRuleD.setUpdateId("1");
		workflowNodeRuleD.setUpdateName("admin");
		
		//获取时间
		Date date = new Date();
		workflowNodeRuleD.setCreateTime(date);
		workflowNodeRuleD.setUpdateTime(date);
		workflowService.updateNodeRD(workflowNodeRuleD);
		return "redirect:nodeRuleInput.hbc?nId="+nId+"&wfId="+wfId+"&ruleId="+ruleId;
	}
	/**
	 * 删除规则子表
	 * @param workflowNodeRule
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delNRD", method = RequestMethod.GET)
	public String delNRD(long ruleId,String id, long wfId, long nId){
		workflowService.delNRD(id);
		return "redirect:nodeRuleInput.hbc?ruleId="+ruleId+"&wfId="+wfId+"&nId="+nId;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryDate", method = RequestMethod.POST)
	public Object queryDate(String tableId) throws HbcsoftException{ 
		JSONObject jsonObject = new JSONObject(); 
		List<WorkflowConfig> list = new ArrayList<WorkflowConfig>();//传递信息集合
		WorkflowConfig temp = new WorkflowConfig();
		temp.setColumnName("请选择列名");
		list.add(temp);//添加第一条提示信息
		list.addAll(this.getRenewalList(tableId));
		jsonObject.put("listColumnId", list);
		return jsonObject;
	}
	/**
	 * 根据table的名称获取对应配置信息
	 * @return
	 */
	private List<WorkflowConfig> getRenewalList(String tableId){
		WorkflowModelQuery model = new WorkflowModelQuery();
		List<WorkflowConfig> result;
		try {
			model.setTableId(tableId);
			result = workflowService.getCS(model);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/********************************工作流子规则列表end**********************************/
	/********************************工作流业务绑定start**********************************/
	/**
	 * 显示工作流业务绑定列表
	 * HBC_F_BUSINESSMAP
	 * @param workflowBusinessMap
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/businessMapShowDate", method = RequestMethod.GET)
	public ModelAndView businShowDate(@ModelAttribute("workflowBusinessMap") WorkflowBusinessMap workflowBusinessMap, BindingResult result,Integer page){
		WorkflowModelQuery query = new WorkflowModelQuery();
		query.setRemark(workflowBusinessMap.getRemark());
		ModelAndView mav = new ModelAndView("workflow/workflowBusinessMapList");
		if(page != null && page > 0){
			query.setPage(page);
		}
		List<WorkflowBusinessMap> list = workflowService.getBusinessMap(query);
		if(list.size() == 0){
			list = null;
		}
		String remark = "";
		if(workflowBusinessMap.getRemark()!=null&&!"".equals(workflowBusinessMap.getRemark()))
		{
			remark = workflowBusinessMap.getRemark();
		}
		mav.getModelMap().put("remark", remark);
		mav.getModelMap().put("list", list);
		mav.getModelMap().put("page", query.getPage());
		return mav;
	}
	/**
	 * 显示工作流业务绑定列表
	 * HBC_F_BUSINESSMAP
	 * @param workflowBusinessMap
	 * @param result
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/businessMapShowDatePost", method = RequestMethod.POST)
	public ModelAndView businShowDatePost(@ModelAttribute("workflowBusinessMap") WorkflowBusinessMap workflowBusinessMap, BindingResult result,Integer page){
		return businShowDate(workflowBusinessMap, result, page);
	}
	
	/**
	 * 跳转工作流业务绑定列表新增页面
	 * /workflow/manage/businessMapInput.hbc
	 * @param workflowBusinessMap
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/businessMapInput", method = RequestMethod.GET)
	public ModelAndView businessMapInput(@ModelAttribute("workflowBusinessMap") WorkflowBusinessMap workflowBusinessMap, BindingResult result){
		List<WorkflowDefine> list = workflowService.getDefine();
		ModelAndView mav = new ModelAndView("workflow/workflowBusinessMapInput");
		if(workflowBusinessMap.getBaseId() != 0 && workflowBusinessMap.getBaseId() > 0 ){
			workflowBusinessMap = workflowService.getBusinessMapById(workflowBusinessMap.getBaseId());
		}
		if(list!=null){
			mav.getModelMap().put("list", list);
		}
		mav.getModelMap().put("map",getActionType());
		mav.getModelMap().put("workflowBusinessMap", workflowBusinessMap);
		return mav;
	}
	//测试businessMapInput方法中的list数据
	public List<KeyValue> getActionType(){
		List<KeyValue> list =new ArrayList<KeyValue>();
		list.add(new KeyValue("单据类型1",1));
		list.add(new KeyValue("单据类型2",2));
		return list;
	}
	/**
	 * 添加保存工作流业务绑定列表
	 * @param workflowBusinessMap
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/addBusinessMap", method = RequestMethod.POST)
	public String addBusinessMap(@ModelAttribute("workflowBusinessMap") WorkflowBusinessMap workflowBusinessMap, BindingResult result){
		//session取消
		
		workflowBusinessMap.setUpdateId("1");
		//获取时间
		Date date = new Date();
		workflowBusinessMap.setUpdateTime(date);
		if(workflowBusinessMap.getBaseId() == 0){
			workflowBusinessMap.setCreateId("1");
			workflowBusinessMap.setCreateTime(date);
			workflowService.addBusinessMap(workflowBusinessMap);
		}else{
			workflowService.updateBusinessMap(workflowBusinessMap);
		}
		
		return "redirect:businessMapShowDate.hbc";
	}
	/**
	 * 删除绑定数据
	 * @param workflowNodeRule
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/delBM", method = RequestMethod.GET)
	public String delBM(String id){
		workflowService.delBM(id);
		return "redirect:businessMapShowDate.hbc";
	}
	/********************************工作流业务绑定end**********************************/
}
