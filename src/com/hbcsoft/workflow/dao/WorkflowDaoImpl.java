package com.hbcsoft.workflow.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.util.HbcsoftUtil;
import com.hbcsoft.workflow.entity.WorkflowBusinessMap;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.workflow.entity.WorkflowDefine;
import com.hbcsoft.workflow.entity.WorkflowModelQuery;
import com.hbcsoft.workflow.entity.WorkflowNode;
import com.hbcsoft.workflow.entity.WorkflowNodeRule;
import com.hbcsoft.workflow.entity.WorkflowNodeRuleD;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

@Repository
public class WorkflowDaoImpl extends LogBaseClass<WorkflowDaoImpl> implements WorkflowDao {

	@Autowired
	private HbcsoftJT jt;
	@Override
	public void addWC(WorkflowConfig w) {
		getLogger().info("===============addWC()==============start");
		StringBuffer sql = new StringBuffer("INSERT INTO hbc_f_config ");
		sql.append("(ID,TABLENAME,TABLEID,COLUMNNAME,COLUMNID")
			.append(",CREATEID,CREATENAME,CREATETIME,UPDATEID,UPDATENAME")
			.append(",UPDATETIME) VALUES (")
			.append("?,?,?,?,?,")
			.append("?,?,?,?,?,")
			.append("?)");
		List<Object> params = new ArrayList<Object>();
		
		params.add(SequenceUtil.getTableId("hbc_f_config"));
		params.add(w.getTableName());
		params.add(w.getTableId());
		params.add(w.getColumnName());
		params.add(w.getColumnId());
		
		params.add(w.getCreateId());
		params.add(w.getCreateName());
		params.add(w.getCreateTime());
		params.add(w.getUpdateId());
		params.add(w.getUpdateName());
		
		params.add(w.getUpdateTime());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============addWC()==============end");
	}

	@Override
	public void updateWC(WorkflowConfig w) {
		getLogger().info("===============updateWC()==============start");
		StringBuffer sql = new StringBuffer("update hbc_f_config ");
		sql.append(" set TABLENAME = ?,TABLEID =?,COLUMNNAME=?,COLUMNID=?,UPDATEID=?")
		.append(",UPDATENAME=?,UPDATETIME=? where ID = ?");
		List<Object> params = new ArrayList<Object>();
		
		params.add(w.getTableName());
		params.add(w.getTableId());
		params.add(w.getColumnName());
		params.add(w.getColumnId());
		params.add(w.getUpdateId());
		
		params.add(w.getUpdateName());
		params.add(w.getUpdateTime());
		params.add(w.getBaseId());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============updateWC()==============end");
	}

	@Override
	public void delCon(String id) {
		getLogger().info("===============delCon()==============start");
		if(id == null || "".equals(id)){
			return;
		}
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append("HBC_F_CONFIG").append(" where");
		sql.append(" ID in (");
		for(String temp:id.split(",")){
			sql.append(temp+",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		jt.update(sql.toString());
		getLogger().info("===============delCon()==============end");
	}
	@Override
	public WorkflowConfig getConfigById(WorkflowConfig data) {
		getLogger().info("===============getConfigById()==============start");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM hbc_f_config where ID = ")
				.append(data.getBaseId());
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString());
		Map<String, Object> r = result.get(0);
		WorkflowConfig workflowConfig = new WorkflowConfig();
		
		workflowConfig.setBaseId(l(r,"ID"));
		workflowConfig.setCreateId(r.get("CREATEID").toString());
		workflowConfig.setCreateName(r.get("CREATENAME").toString());
		workflowConfig.setCreateTime(d( r,"CREATETIME"));
		workflowConfig.setUpdateId(s(r,"UPDATEID"));
		
		workflowConfig.setUpdateName(s(r,"UPDATENAME"));
		workflowConfig.setUpdateTime(d( r,"UPDATETIME"));
		workflowConfig.setColumnId(s(r,"COLUMNID"));
		workflowConfig.setColumnName(s(r,"COLUMNNAME"));
		workflowConfig.setTableId(s(r,"TABLEID"));
		
		workflowConfig.setTableName(s(r,"TABLENAME"));
		getLogger().info("===============getConfigById()==============end");
		return workflowConfig;
	}

	@Override
	public List<WorkflowConfig> getConfigShow(WorkflowModelQuery m) {
		getLogger().info("===============getConfigShow()==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		jt.execute("SET  @row_number = 0;");
		sql.append("SELECT b.* from (")
				.append("SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM hbc_f_config ")
				.append(" a where 1=1 ");
		if(m.getColumnName()!=null && !"".equals(m.getColumnName().trim())){
			sql.append(" and COLUMNNAME like ? ");
			params.add("%"+m.getColumnName()+"%");
		}
		if(m.getTableName()!=null && !"".equals(m.getTableName().trim())){
			sql.append(" and TABLENAME like ? ");
			params.add("%"+m.getTableName()+"%");
		}
		sql.append(") b where b.rownumber between ? and ? ");
		params.add(m.getFrom());
		params.add(m.getTo());
		
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString(),params.toArray());
		if(result ==null || result.isEmpty()){
			if(m.getPage()>1){
				m.setPage(m.getPage()-1);
				return getConfigShow(m);
			}
		}
		List<WorkflowConfig> list = new ArrayList<WorkflowConfig>();
		for (Map<String, Object> r : result) {
			WorkflowConfig workflowConfig = new WorkflowConfig();
			
			workflowConfig.setBaseId(l( r,"ID"));
			workflowConfig.setCreateId(s(r,"CREATEID"));
			workflowConfig.setCreateName(s(r,"CREATENAME"));
			workflowConfig.setCreateTime(d(r,"CREATETIME"));
			workflowConfig.setUpdateId(s(r,"UPDATEID"));
			
			workflowConfig.setUpdateName(s(r,"UPDATENAME"));
			workflowConfig.setUpdateTime(d( r,"UPDATETIME"));
			workflowConfig.setColumnId(s(r,"COLUMNID"));
			workflowConfig.setColumnName(s(r,"COLUMNNAME"));
			workflowConfig.setTableId(s(r,"TABLEID"));
			
			workflowConfig.setTableName(s(r,"TABLENAME"));

			list.add(workflowConfig);
		}
		getLogger().info("===============getConfigShow()==============end");
		return list;
	}

	@Override
	public List<WorkflowConfig> getCSTable(WorkflowModelQuery m) {
		getLogger().info("===============getCSTable()==============start");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT TABLEID, TABLENAME FROM HBC_F_CONFIG ")
				.append(" where 1=1 ");
				
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString());
		List<WorkflowConfig> list = new ArrayList<WorkflowConfig>();
		for (Map<String, Object> r : result) {
			WorkflowConfig workflowConfig = new WorkflowConfig();
			workflowConfig.setTableId(r.get("TABLEID").toString());
			workflowConfig.setTableName(r.get("TABLENAME").toString());
			list.add(workflowConfig);
		}
		getLogger().info("===============getCSTable()==============end");
		return list;
	}

	@Override
	public List<WorkflowConfig> getCS(WorkflowModelQuery m) {
		getLogger().info("===============getCS()==============start");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM HBC_F_CONFIG ")
				.append(" where 1=1 ");
		//根据表名
		if(m!=null && m.getTableId()!=null && !"".equals(m.getTableId())){
			sql.append(" and  TABLEID = '")
				.append(m.getTableId())
				.append("'");
		}
		//根据列名
		if(m!=null && m.getColumnId()!=null && !"".equals(m.getColumnId())){
			sql.append(" and  COLUMNID = '")
				.append(m.getColumnId())
				.append("'");
		}
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString());
		List<WorkflowConfig> list = new ArrayList<WorkflowConfig>();
		for (Map<String, Object> r : result) {
			WorkflowConfig workflowConfig = new WorkflowConfig();
			
			workflowConfig.setBaseId(l( r,"ID"));
			workflowConfig.setCreateId(s(r,"CREATEID"));
			workflowConfig.setCreateName(s(r,"CREATENAME"));
			workflowConfig.setCreateTime(d(r,"CREATETIME"));
			workflowConfig.setUpdateId(s(r,"UPDATEID"));
			
			workflowConfig.setUpdateName(s(r,"UPDATENAME"));
			workflowConfig.setUpdateTime(d(r,"UPDATETIME"));
			workflowConfig.setColumnId(s(r,"COLUMNID"));
			workflowConfig.setColumnName(s(r,"COLUMNNAME"));
			workflowConfig.setTableId(s(r,"TABLEID"));
			
			workflowConfig.setTableName(s(r,"TABLENAME"));

			list.add(workflowConfig);
		}
		getLogger().info("===============getCS()==============end");
		return list;
	}

	/**
	 * 取值强转String
	 * 
	 * @param r
	 * @param key
	 * @return
	 */
	private String s(Map<String, Object> r, String key) {
		Object o = r.get(key);
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	/**
	 * 取值强转Integer
	 * 
	 * @param r
	 * @param key
	 * @return
	 */
	private Integer i(Map<String, Object> r, String key) {
		Object o = r.get(key);
		if (o == null) {
			return 0;
		} else {
			return (Integer) r.get(key);

		}
	}
	/**
	 * 取值强转Long
	 * 
	 * @param r
	 * @param key
	 * @return
	 */
	private Long l(Map<String, Object> r, String key) {
		Object o = r.get(key);
		if (o == null) {
			return (long) 0;
		} else {
			return (Long) r.get(key);

		}
	}
	/**
	 * 取值强转Date
	 * 
	 * @param r
	 * @param key
	 * @return 为空直接取当前时间
	 */
	private Date d(Map<String, Object> r, String key) {
		Object o = r.get(key);
		if (o == null) {
			return new Date();
		} else {
			//return (Date) r.get(key);
			try{
				Date date = (Date) r.get(key);
				return date;
			}catch(Exception e){
				e.printStackTrace();
				Date date = new Date();
				try {
					date = HbcsoftUtil.fromStrToDatetime(r.get(key).toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return date;
			}
		}
	}

	@Override
	public Long add(WorkflowDefine data) {
		getLogger().info("===============add()==============start");
		StringBuffer sqlStr = new StringBuffer("INSERT INTO HBC_F_DEFINE ");
		sqlStr.append("(")
		.append(" ID ,CREATEID ,CREATENAME ,CREATETIME ,UPDATEID")
		.append(" ,UPDATENAME ,UPDATETIME ,DEFAULTTITLE ,ISARCHIVES ,ISAUTOTITLE")
		.append(" ,ISENABLE ,ISQUICK ,ISTRCAKFORM ,NAME ,QUICKRULE, ISSELNEXTUSER")
		.append(" ) VALUES(")
		.append(" ? ,? ,? ,? ,?")
		.append(" ,? ,? ,? ,? ,?")
		.append(" ,? ,? ,? ,? ,?,?)");
		// 参数
		List<Object> params = setWD(data);
		jt.update(sqlStr.toString(), params.toArray());
		getLogger().info("===============add()==============end");
		return (Long) params.get(0);
	}

	@Override
	public void update(WorkflowDefine w) {
		getLogger().info("===============update()==============start");
		StringBuffer sql = new StringBuffer("update HBC_F_DEFINE ");
		sql.append(" set UPDATEID=?,UPDATENAME=?,UPDATETIME=?,DEFAULTTITLE=?,ISARCHIVES=? ")
				.append(" ,ISAUTOTITLE=?,ISENABLE=? ,ISQUICK =?,ISTRCAKFORM =?,NAME =?")
				.append(" ,QUICKRULE=?,ISSELNEXTUSER=? where ID = ?");
		List<Object> params = new ArrayList<Object>();
		
		params.add(w.getUpdateId());
		params.add(w.getUpdateName());
		params.add(w.getUpdateTime());
		params.add(w.getDefaultTitle());
		params.add(w.getIsArchives());
		
		params.add(w.getIsAutoTitle());
		params.add(w.getIsEnable());
		params.add(w.getIsQuick());
		params.add(w.getIsTrcakForm());
		params.add(w.getName());
		
		params.add(w.getQuickRule());
		
		params.add(w.getIsSelNextUser());
		
		params.add(w.getBaseId());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============update()==============end");
	}

	@Override
	public WorkflowDefine getWD(Long id) {
		getLogger().info("===============getWD()==============");
		String sql = "select * from HBC_F_DEFINE where ID = " + id;
		List<Map<String, Object>> list = jt.queryForList(sql);
		
		if (list != null && list.size() > 0) {
			Map<String, Object> m = list.get(0);
			WorkflowDefine w = getWD(m);//抽取的赋值过程
			return w;
		} else {
			return null;
		}
	}

	@Override
	public List<WorkflowDefine> getDefine() {
		getLogger().info("===============getDefine()==============start");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM HBC_F_DEFINE ")
		.append(" where ISENABLE = 1 ");
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString());
		List<WorkflowDefine> list = new ArrayList<WorkflowDefine>();
		if(result!=null && !result.isEmpty()){
			for (Map<String, Object> r : result) {
				list.add(getWD(r));
			}
		}else{
			list=null;
		}
		getLogger().info("===============getDefine()==============end");
		return list;
	}

	@Override
	public List<WorkflowDefine> getDefine(WorkflowModelQuery m) {
		getLogger().info("===============getDefine(m)==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		jt.execute("SET @row_number = 0;");
		sql.append("SELECT b.* from (")
			.append("SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM HBC_F_DEFINE ")
			.append(" a where 1=1 ");
		if(m.getWfName()!=null&&!"".equals(m.getWfName())){
			sql.append(" and NAME like ?");
			params.add("%"+m.getWfName().trim()+"%");
		}
		sql.append(") b where b.rownumber between ? and ?");
		params.add(m.getFrom());
		params.add(m.getTo());
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString(),params.toArray());
		//分页处理
		if(result==null || result.isEmpty()){
			if(m.getPage()>1){
				m.setPage(m.getPage()-1);
				return getDefine(m);
			}else{
				return new ArrayList<WorkflowDefine>();
			}
		}
		
		List<WorkflowDefine> list = new ArrayList<WorkflowDefine>();
		for (Map<String, Object> r : result) {
			list.add(getWD(r));
		}
		getLogger().info("===============getDefine(m)==============end");
		return list;
	}
	/**
	 * 抽取定义表的取值方法
	 * @param data
	 * @return
	 */
	private WorkflowDefine getWD(Map<String, Object> r) {
		WorkflowDefine w = new WorkflowDefine();
		w.setBaseId(l(r,"ID"));
		w.setCreateId(s(r,"CREATEID"));
		w.setCreateName(s(r,"CREATENAME"));
		w.setCreateTime(d(r,"CREATETIME"));
		w.setUpdateId(s(r,"UPDATEID"));
		
		w.setUpdateName(s(r,"UPDATENAME"));
		w.setUpdateTime(d(r,"UPDATETIME"));
		w.setDefaultTitle(s(r,"DEFAULTTITLE"));
		w.setIsArchives(i(r,"ISARCHIVES"));
		w.setIsAutoTitle(i(r,"ISAUTOTITLE"));
		
		w.setIsEnable(i(r,"ISENABLE"));
		w.setIsQuick(i(r,"ISQUICK"));
		w.setIsTrcakForm(i(r,"ISTRCAKFORM"));
		w.setName(s(r,"NAME"));
		w.setQuickRule(s(r,"QUICKRULE"));
		
		w.setIsSelNextUser(i(r,"ISSELNEXTUSER"));
		return w;
	}
	/**
	 * 抽取定义表的设置值方法
	 * @param data
	 * @return
	 */
	private List<Object> setWD(WorkflowDefine data) {
		List<Object> params = new ArrayList<Object>();
		
		params.add(SequenceUtil.getTableId("HBC_F_DEFINE"));
		params.add(data.getCreateId());
		params.add(data.getCreateName());
		params.add(data.getCreateTime());
		params.add(data.getUpdateId());
		
		params.add(data.getUpdateName());
		params.add(data.getUpdateTime());
		params.add(data.getDefaultTitle());
		params.add(data.getIsArchives());
		params.add(data.getIsAutoTitle());
		
		params.add(data.getIsEnable());
		params.add(data.getIsQuick());
		params.add(data.getIsTrcakForm());
		params.add(data.getName());
		params.add(data.getQuickRule());
		
		params.add(data.getIsSelNextUser());
		
		return params;
	}
	@Override
	public List<WorkflowNode> getNode(WorkflowModelQuery m) {
		getLogger().info("===============getNode()==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		jt.execute("SET @row_number = 0;");
		sql.append("SELECT b.* from (")
				.append("SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM HBC_F_NODE ")
				.append(" a where 1=1 ");
		if(m.getWfId()>0){
			sql.append(" and WFID = ?");
			params.add(m.getWfId());
		}
		
		sql.append(") b where b.rownumber between ? and ? ");
		params.add(m.getFrom());
		params.add(m.getTo());
		List<Map<String, Object>> result = jt.queryForList(sql.toString(),params.toArray());
		
		//分页处理
		if(result==null || result.isEmpty()){
			if(m.getPage()>1){
				m.setPage(m.getPage()-1);
				return getNode(m);
			}else{
				return new ArrayList<WorkflowNode>();
			}
		}
		
		List<WorkflowNode> list = new ArrayList<WorkflowNode>();
		for (Map<String, Object> r : result) {
			WorkflowNode workflowNode = new WorkflowNode();
			
			workflowNode.setBaseId(l(r,"ID"));
			workflowNode.setCreateId(s(r,"CREATEID"));
			workflowNode.setCreateName(s(r,"CREATENAME"));
			workflowNode.setCreateTime(d(r,"CREATETIME"));
			workflowNode.setUpdateId(s(r,"UPDATEID"));

			workflowNode.setUpdateName(s(r,"UPDATENAME"));
			workflowNode.setUpdateTime(d(r,"UPDATETIME"));
			workflowNode.setWfId(l( r,"WFID"));
			workflowNode.setIsEnable(i(r,"ISENABLE"));
			workflowNode.setSort(i(r,"SORT"));

			workflowNode.setName(s(r,"NAME"));
			workflowNode.setDeptId(s(r,"DEPTID"));
			workflowNode.setRoleId(s(r,"ROLEID"));
			workflowNode.setIsCounterSign(i(r,"ISCOUNTERSIGN"));
			workflowNode.setCounterSignRule(s(r,"COUNTERSIGNRULE"));

			workflowNode.setIsAutoAdopt(i(r,"ISAUTOADOPT"));
			workflowNode.setIsDepute(i(r,"ISDEPUTE"));
			workflowNode.setIsSMS(i(r,"ISSMS"));
			workflowNode.setIsEmail(i(r,"ISEMAIL"));
			workflowNode.setIsAutoSkip(i(r,"ISAUTOSKIP"));
			
			workflowNode.setDeptStatus(i(r,"DEPTSTATUS"));

			list.add(workflowNode);
		}
		getLogger().info("===============getNode()==============end");
		return list;
	}

	@Override
	public void dW(long value) {
		dWN(1,value);//按外外键删除节点表
		wD("HBC_F_DEFINE","ID",value);//按id删除节点表
	}
	/**
	 * 删除节点
	 * deleteWorkflowNode
	 * @param column	要删除的列名（0:主键，1：外键）
	 * @param value		条件的值
	 */
	public void dWN(int i,long value){
		if(i ==0){
			dWNR(1,value);//按外外键删除规则表
			wD("HBC_F_NODE","ID",value);//按id删除节点表
		}else{
			String sql = "select ID from HBC_F_NODE where WFID = " + value;
			List<Map<String, Object>> result = jt.queryForList(sql);
			if(result != null && result.size() > 0){
				for(Map<String, Object> m:result){
					dWNR(1,i(m,"ID"));//根据节点ID删除规则
				}
			}
			wD("HBC_F_NODE","WFID",value);//按WFID删除节点表
		}
	}
	/**
	 * 删除方法（仅限单列条件）
	 * workDelete
	 * @param table		表名
	 * @param column	列名
	 * @param value		值
	 */
	private void wD(String table,String column,Object value){
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("delete from ");
		if(table==null) return ;
		sql.append(table).append(" where 1=1");
		if(column!=null){
			sql.append(" and ").append(column).append("=?");
			params.add(value);
		}
		jt.update(sql.toString(), params.toArray());
	}
	/**
	 * 删除规则
	 * deleteWorkflowNodeRule
	 * @param i			0为按主键删除，其他按外键删除
	 * @param value		条件的值
	 */
	public void dWNR(int i,long value){
		if(i == 0){
			dWNRD(1,value);//按外键删除规则子表
			wD("HBC_F_NODERULE","ID",value);//按id删除规则主表
		}else{
			String sql = "select ID from HBC_F_NODERULE where nodeId = " + value;
			List<Map<String, Object>> result = jt.queryForList(sql);
			//循环删除规则子表
			if(result != null && result.size() > 0){
				for(Map<String, Object> m:result){
					dWNRD(1,i(m,"ID"));//按外键删除规则子表
				}
			}
			wD("HBC_F_NODERULE","nodeId",value);//按外键NID删除规则表
		}
	}
	/**
	 * 删除规则子表
	 * deleteWorkflowNodeRuleDetail
	 * @param i			0为按主键删除，其他按外键删除
	 * @param value		条件的值
	 */
	public void dWNRD(int i ,long value){
		if(i==0){
			wD("HBC_F_NODERULE_D","ID",value);
		}else{
			wD("HBC_F_NODERULE_D","RULEID",value);
		}
	}

	@Override
	public WorkflowNode getNodeShow(long nId) {
		getLogger().info("===============getNodeShow()==============");
		String sql = "select * from HBC_F_NODE where ID = " + nId;
		List<Map<String, Object>> list = jt.queryForList(sql);
		if (list != null && list.size() > 0) {
			Map<String, Object> r = list.get(0);
			WorkflowNode workflowNode = new WorkflowNode();
			
			workflowNode.setDeptStatus(i(r,"DEPTSTATUS"));
			workflowNode.setBaseId(l( r,"ID"));
			workflowNode.setCreateId(s(r,"CREATEID"));
			workflowNode.setCreateName(s(r,"CREATENAME"));
			workflowNode.setCreateTime(d(r,"CREATETIME"));
			
			workflowNode.setUpdateId(s(r,"UPDATEID"));
			workflowNode.setUpdateName(s(r,"UPDATENAME"));
			workflowNode.setUpdateTime(d(r,"UPDATETIME"));
			workflowNode.setWfId(l(r,"WFID"));
			workflowNode.setIsEnable(i( r,"ISENABLE"));
			
			workflowNode.setSort(i(r,"SORT"));
			workflowNode.setName(s(r,"NAME"));
			workflowNode.setDeptId(s(r,"DEPTID"));
			workflowNode.setRoleId(s(r,"ROLEID"));
			workflowNode.setIsCounterSign(i(r,"ISCOUNTERSIGN"));
			
			workflowNode.setCounterSignRule(s(r,"COUNTERSIGNRULE"));
			workflowNode.setIsAutoAdopt(i(r,"ISAUTOADOPT"));
			workflowNode.setIsDepute(i(r,"ISDEPUTE"));
			workflowNode.setIsSMS(i( r,"ISSMS"));
			workflowNode.setIsEmail(i(r,"ISEMAIL"));
			
			workflowNode.setIsAutoSkip(i( r,"ISAUTOSKIP"));

			return workflowNode;
		} else {
			return null;
		}
	}

	@Override
	public List<WorkflowNodeRule> getNodeRule(WorkflowModelQuery m) {
		getLogger().info("===============getNodeRule()==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		jt.execute("SET @row_number = 0;");
		sql.append("SELECT b.* from (")
				.append("SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM ")
				.append(" HBC_F_NODERULE a  where 1=1");
		
		if(m.getnId() > 0){
			sql.append(" and nodeId =? ");
			params.add(m.getnId());
		}
		sql.append(") b where b.rownumber between ? and ?");
		
		params.add(m.getFrom());
		params.add(m.getTo());
		List<Map<String, Object>> result = jt.queryForList(sql.toString(),params.toArray());
		
		//分页处理
		if(result==null || result.isEmpty()){
			if(m.getPage()>1){
				m.setPage(m.getPage()-1);
				return getNodeRule(m);
			}else{
				return new ArrayList<WorkflowNodeRule>();
			}
		}
		
		List<WorkflowNodeRule> list = new ArrayList<WorkflowNodeRule>();
		for (Map<String, Object> r : result) {
			list.add(getWNR(r));
		}
		getLogger().info("===============getNodeRule()==============end");
		return list;
	}
	/**
	 * 抽取的WorkflowNodeRule取值方法
	 * @param r
	 * @return
	 */
	private WorkflowNodeRule getWNR(Map<String, Object> r) {
		WorkflowNodeRule w = new WorkflowNodeRule();
		w.setWfId(l(r, "WFID"));
		w.setNodeId(l(r, "NODEID"));
		w.setToNodeId(l(r, "TONODEID"));
		w.setBaseId(l(r, "ID"));
		w.setCreateId(s(r, "CREATEID"));
		
		w.setCreateName(s(r, "CREATENAME"));
		w.setCreateTime(d(r, "CREATETIME"));
		w.setUpdateId(s(r, "UPDATEID"));
		w.setUpdateName(s(r, "UPDATENAME"));
		w.setUpdateTime(d(r, "UPDATETIME"));
		
		w.setRuleName(s(r,"RULENAME"));
		w.setRuleSort(i(r,"RULESORT"));
		return w;
	}

	@Override
	public void nodeUpdate(WorkflowNode w) {
		getLogger().info("===============nodeUpdate()==============start");
		StringBuffer sql = new StringBuffer("update HBC_F_NODE ");
		sql.append(" set UPDATEID = ?,UPDATENAME=? ,UPDATETIME =?,WFID=?,ISENABLE=?,")
				.append("SORT=?,NAME=?,DEPTSTATUS=?,DEPTID=?,ROLEID=?,")
				.append("ISCOUNTERSIGN=?,COUNTERSIGNRULE=?,ISAUTOADOPT=?,ISDEPUTE=?,ISSMS=?,")
				.append("ISEMAIL=?,ISAUTOSKIP=? where ID = ?");
		// 参数
		List<Object> params = new ArrayList<Object>();
		
		params.add(w.getUpdateId());
		params.add(w.getUpdateName());
		params.add(w.getUpdateTime().toLocaleString());
		params.add(w.getWfId());
		params.add(w.getIsEnable());
		
		params.add(w.getSort());
		params.add(w.getName());
		params.add(w.getDeptStatus());
		params.add(w.getDeptId());
		params.add(w.getRoleId());
		
		params.add(w.getIsCounterSign());
		params.add(w.getCounterSignRule());
		params.add(w.getIsAutoAdopt());
		params.add(w.getIsDepute());
		params.add(w.getIsSMS());
		
		params.add(w.getIsEmail());
		params.add(w.getIsAutoSkip());
		params.add(w.getBaseId());

		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============nodeUpdate()==============end");
	}

	@Override
	public long nodeAdd(WorkflowNode data) {
		getLogger().info("===============nodeAdd()==============start");
		String sqlStr = "INSERT INTO HBC_F_NODE("
				+ " ID ,CREATEID ,CREATENAME ,CREATETIME ,UPDATEID"
				+ " ,UPDATENAME ,UPDATETIME ,WFID,ISENABLE,SORT"
				+ " ,NAME,DEPTID,ROLEID,ISCOUNTERSIGN,COUNTERSIGNRULE"
				+ " ,ISAUTOADOPT,ISDEPUTE,ISSMS,ISEMAIL,ISAUTOSKIP"
				+ " ,DEPTSTATUS) VALUES(" + " ? ,? ,? ,? ,?" + " ,? ,? ,? ,? ,?"
				+ " ,? ,? ,? ,? ,?" + " ,? ,? ,? ,? ,?" + ",?)";
		// 参数
		List<Object> params = new ArrayList<Object>();

		long id = SequenceUtil.getTableId("HBC_F_NODE");
		params.add(id);
		params.add(data.getCreateId());
		params.add(data.getCreateName());
		params.add(data.getCreateTime());
		params.add(data.getUpdateId());

		params.add(data.getUpdateName());
		params.add(data.getUpdateTime());
		params.add(data.getWfId());
		params.add(data.getIsEnable());
		params.add(data.getSort());

		params.add(data.getName());
		params.add(data.getDeptId());
		params.add(data.getRoleId());
		params.add(data.getIsCounterSign());
		params.add(data.getCounterSignRule());

		params.add(data.getIsAutoAdopt());
		params.add(data.getIsDepute());
		params.add(data.getIsSMS());
		params.add(data.getIsEmail());
		params.add(data.getIsAutoSkip());
		params.add(data.getDeptStatus());

		jt.update(sqlStr, params.toArray());
		getLogger().info("===============nodeAdd()==============end");
		return id;
	}

	@Override
	public WorkflowNodeRule getNRById(long id) {
		getLogger().info("===============getNRById()==============");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM HBC_F_NODERULE a where ID = ? ");
		params.add(id);
		
		List<Map<String, Object>> result = jt.queryForList(sql.toString(),params.toArray());
		if(result==null||result.size()==0){
			return null;
		}else{
			return getWNR(result.get(0));
		}
	}

	@Override
	public List<WorkflowNodeRuleD> nodeRDList(WorkflowModelQuery query) {
		getLogger().info("===============nodeRDList()==============start");
		StringBuffer sql=new StringBuffer();
		jt.execute("SET @row_number = 0;");
		sql.append("SELECT b.* from (SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM ")
			.append(" HBC_F_NODERULE_D a  where RULEID = ? ) b where b.rownumber between ? and ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(query.getRuleId());
		params.add(query.getFrom());
		params.add(query.getTo());
		List<Map<String, Object>> result = jt.queryForList(sql.toString(),params.toArray());// 查询子表结果
		
		//分页处理
		if(result==null || result.isEmpty()){
			if(query.getPage()>1){
				query.setPage(query.getPage()-1);
				return nodeRDList(query);
			}else{
				return new ArrayList<WorkflowNodeRuleD>();
			}
		}
		
		List<WorkflowNodeRuleD> list = new ArrayList<WorkflowNodeRuleD>();
		if (result != null && result.size() > 0) {
			for (Map<String, Object> r : result) {
				WorkflowNodeRuleD nodeRuleD = new WorkflowNodeRuleD();
				
				nodeRuleD.setRuleId(l(r,"RULEID"));
				nodeRuleD.setTableName(s(r,"TABLENAME"));
				nodeRuleD.setTableId(s(r,"TABLEID"));
				nodeRuleD.setColumnName(s(r,"COLUMNNAME"));
				nodeRuleD.setColumnId(s(r,"COLUMNID"));
				
				nodeRuleD.setRuleType(s(r,"RULETYPE"));
				nodeRuleD.setRuleValue(s(r,"RULEVALUE"));
				nodeRuleD.setBaseId(l(r,"ID"));
				nodeRuleD.setCreateId(s(r,"CREATEID"));
				nodeRuleD.setCreateName(s(r,"CREATENAME"));
				
				nodeRuleD.setCreateTime(d(r,"CREATETIME"));
				nodeRuleD.setUpdateId(s(r,"UPDATEID"));
				nodeRuleD.setUpdateName(s(r,"UPDATENAME"));
				nodeRuleD.setUpdateTime(d(r,"UPDATETIME"));
				
				list.add(nodeRuleD);
			}
		}
		getLogger().info("===============nodeRDList()==============end");
		return list;
	}

	@Override
	public List<WorkflowNode> getNodeByWfId(long wfId) {
		getLogger().info("===============getNodeByWfId()==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT a.* FROM HBC_F_NODE a where 1=1 ");
		if(wfId>0){
			sql.append(" and WFID = ?");
			params.add(wfId);
		}
		List<Map<String, Object>> result = jt.queryForList(sql.toString(),params.toArray());
		List<WorkflowNode> list = new ArrayList<WorkflowNode>();
		for (Map<String, Object> r : result) {
			WorkflowNode workflowNode = new WorkflowNode();
			
			workflowNode.setBaseId(l(r,"ID"));
			workflowNode.setCreateId(s(r,"CREATEID"));
			workflowNode.setCreateName(s(r,"CREATENAME"));
			workflowNode.setCreateTime(d(r,"CREATETIME"));
			workflowNode.setUpdateId(s(r,"UPDATEID"));

			workflowNode.setUpdateName(s(r,"UPDATENAME"));
			workflowNode.setUpdateTime(d(r,"UPDATETIME"));
			workflowNode.setWfId(l(r,"WFID"));
			workflowNode.setIsEnable(i(r,"ISENABLE"));
			workflowNode.setSort(i(r,"SORT"));

			workflowNode.setName(s(r,"NAME"));
			workflowNode.setDeptId(s(r,"DEPTID"));
			workflowNode.setRoleId(s(r,"ROLEID"));
			workflowNode.setIsCounterSign(i(r,"ISCOUNTERSIGN"));
			workflowNode.setCounterSignRule(s(r,"COUNTERSIGNRULE"));

			workflowNode.setIsAutoAdopt(i(r,"ISAUTOADOPT"));
			workflowNode.setIsDepute(i(r,"ISDEPUTE"));
			workflowNode.setIsSMS(i(r,"ISSMS"));
			workflowNode.setIsEmail(i(r,"ISEMAIL"));
			workflowNode.setIsAutoSkip(i(r,"ISAUTOSKIP"));

			list.add(workflowNode);
		}
		getLogger().info("===============getNodeByWfId()==============end");
		return list;
	}

	@Override
	public long ruleUpdate(WorkflowNodeRule w) {
		getLogger().info("===============ruleUpdate()==============start");
		StringBuffer sql = new StringBuffer("update HBC_F_NODERULE");
		sql.append(" set UPDATEID = ?,UPDATENAME = ? ,UPDATETIME = ?, RULENAME = ?,WFID = ?")
				.append(" ,RULESORT=?,nodeId= ?,TONODEID= ? where ID = ?");
		// 参数
		List<Object> params = new ArrayList<Object>();
		
		params.add(w.getUpdateId());
		params.add(w.getUpdateName());
		params.add(w.getUpdateTime());
		params.add(w.getRuleName());
		params.add(w.getWfId());
		
		params.add(w.getRuleSort());
		params.add(w.getNodeId());
		params.add(w.getToNodeId());
		params.add(w.getBaseId());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============ruleUpdate()==============end");
		return w.getBaseId();
	}

	@Override
	public long ruleAdd(WorkflowNodeRule workflowNodeRule) {
		getLogger().info("===============ruleAdd()==============start");
		StringBuffer sql = new StringBuffer("INSERT INTO HBC_F_NODERULE ");
		sql.append("(ID,RULENAME,WFID,nodeId,TONODEID," )
				.append("CREATEID,CREATENAME,CREATETIME,UPDATEID,UPDATENAME,")
				.append("UPDATETIME,RULESORT) VALUES")
				.append("(?,?,?,?,?,"+"?,?,?,?,?,"+"?,?)");
		List<Object> params = new ArrayList<Object>();
		long id = SequenceUtil.getTableId("HBC_F_NODERULE");
		params.add(id);
		params.add(workflowNodeRule.getRuleName());
		params.add(workflowNodeRule.getWfId());
		params.add(workflowNodeRule.getNodeId());
		params.add(workflowNodeRule.getToNodeId());
		
		params.add(workflowNodeRule.getUpdateId());
		params.add(workflowNodeRule.getUpdateName());
		params.add(workflowNodeRule.getUpdateTime());
		params.add(workflowNodeRule.getUpdateId());
		params.add(workflowNodeRule.getUpdateName());
		
		params.add(workflowNodeRule.getUpdateTime());
		params.add(workflowNodeRule.getRuleSort());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============ruleAdd()==============end");
		return id;
	}

	@Override
	public WorkflowNodeRuleD getWNRDById(long ruleDId) {
		getLogger().info("===============getWNRDById()==============");
		String sql = "select * from HBC_F_NODERULE_D " + " where 1=1";
		sql = sql + " and ID = " + ruleDId;

		List<Map<String, Object>> result = jt.queryForList(sql);
		if(result ==null || result.size()==0) return null;
		Map<String, Object> r = result.get(0);
			WorkflowNodeRuleD w = new WorkflowNodeRuleD();
			
			w.setTableName(s(r, "TABLENAME"));
			w.setTableId(s(r, "TABLEID"));
			w.setColumnName(s(r, "COLUMNNAME"));
			w.setColumnId(s(r, "COLUMNID"));
			w.setRuleType(s(r, "RULETYPE"));
			
			w.setRuleValue(s(r, "RULEVALUE"));
			w.setBaseId(l(r, "ID"));
			w.setRuleId(l(r, "RULEID"));
		return w;
	}

	@Override
	public long updateNodeRD(WorkflowNodeRuleD w) {
		getLogger().info("===============updateNodeRD()==============");
		long id=0;
		if(w.getBaseId() == 0){
			StringBuffer sql = new StringBuffer("INSERT INTO HBC_F_NODERULE_D ");
			sql.append("(ID,RULEID,TABLENAME,TABLEID,COLUMNNAME")
					.append(",COLUMNID,RULETYPE,RULEVALUE,CREATEID,CREATENAME")
					.append(",CREATETIME,UPDATEID,UPDATENAME,UPDATETIME")
					.append(") VALUES(?,?,?,?,?,"+"?,?,?,?,?,"+"?,?,?,?)");
			List<Object> params = new ArrayList<Object>();
			id=SequenceUtil.getTableId("HBC_F_NODERULE_D");
			params.add(id);
			params.add(w.getRuleId());
			params.add(w.getTableName());
			params.add(w.getTableId());
			params.add(w.getColumnName());
			
			params.add(w.getColumnId());
			params.add(w.getRuleType());
			params.add(w.getRuleValue());
			params.add(w.getCreateId());
			params.add(w.getCreateName());
			
			params.add(w.getCreateTime());
			params.add(w.getUpdateId());
			params.add(w.getUpdateName());
			params.add(w.getUpdateTime());
			
			jt.update(sql.toString(), params.toArray());
		}else{
			StringBuffer sql = new StringBuffer("update HBC_F_NODERULE_D ");
			sql.append(" set UPDATEID = ?,UPDATENAME=? ,UPDATETIME =? ,RULEID= ?,TABLENAME= ?")
					.append(" ,TABLEID= ?,COLUMNNAME= ?,COLUMNID= ? ,RULETYPE= ?,RULEVALUE= ?")
					.append(" where ID = ?");
			// 参数
			List<Object> params = new ArrayList<Object>();
			
			params.add(w.getUpdateId());
			params.add(w.getUpdateName());
			params.add(w.getUpdateTime());
			params.add(w.getRuleId());
			params.add(w.getTableName());
			
			params.add(w.getTableId());
			params.add(w.getColumnName());
			params.add(w.getColumnId());
			params.add(w.getRuleType());
			params.add(w.getRuleValue());
			
			params.add(w.getBaseId());
			
			id=w.getBaseId();
			jt.update(sql.toString(), params.toArray());
		}
		return id;
	}

	@Override
	public List<WorkflowBusinessMap> getBusinessMap(WorkflowModelQuery m) {
		getLogger().info("===============getBusinessMap()==============start");
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		jt.execute("SET @row_number = 0;");
		sql.append("SELECT b.* from (")
				.append("SELECT a.*,(@row_number:=@row_number + 1) as rownumber FROM ")
				.append(" HBC_F_BUSINESSMAP")
				.append(" a where 1=1 ");
		if(m.getRemark()!=null&& !"".equals(m.getRemark())){
			sql.append(" and REMARK like ? ");
			params.add("%"+m.getRemark()+"%");
		}
			
		sql.append(") b where b.rownumber between ? and ?");
		params.add(m.getFrom());
		params.add(m.getTo());
		List<Map<String, Object>> result = jt.queryForList(sql
				.toString(),params.toArray());
		
		//分页处理
		if(result==null || result.isEmpty()){
			if(m.getPage()>1){
				m.setPage(m.getPage()-1);
				return getBusinessMap(m);
			}else{
				return new ArrayList<WorkflowBusinessMap>();
			}
		}
		
		List<WorkflowBusinessMap> list = new ArrayList<WorkflowBusinessMap>();
		for (Map<String, Object> r : result) {
			WorkflowBusinessMap workflowBusinessMap = new WorkflowBusinessMap();
			
			workflowBusinessMap.setBaseId(l( r,"ID"));
			workflowBusinessMap.setCreateId(s(r,"CREATEID"));
			workflowBusinessMap.setCreateTime(d(r,"CREATETIME"));
			workflowBusinessMap.setUpdateId(s(r,"UPDATEID"));
			workflowBusinessMap.setUpdateTime(d( r,"UPDATETIME"));
			
			workflowBusinessMap.setRemark(s(r,"REMARK"));
			workflowBusinessMap.setWorkflowId(l( r,"WFID"));
			workflowBusinessMap.setFlag(i( r,"FLAG"));
			workflowBusinessMap.setActionType(i(r,"ACTIONTYPE"));
			
			list.add(workflowBusinessMap);
		}
		getLogger().info("===============getBusinessMap()==============end");
		return list;
	}

	@Override
	public WorkflowBusinessMap getBusinessMapById(long id) {
		getLogger().info("===============getBusinessMapById()==============start");
		StringBuffer sql=new StringBuffer("SELECT * FROM HBC_F_BUSINESSMAP ");
		sql.append(" where ID = ");
		sql.append(id);

		List<Map<String, Object>> result = jt.queryForList(sql.toString());
		if(result ==null || result.size()==0) return null;
		Map<String, Object> r = result.get(0);
		WorkflowBusinessMap workflowBusinessMap = new WorkflowBusinessMap();
		
		workflowBusinessMap.setBaseId(l(r,"ID"));
		workflowBusinessMap.setCreateId(s(r,"CREATEID"));
		workflowBusinessMap.setCreateTime(d(r,"CREATETIME"));
		workflowBusinessMap.setUpdateId(s(r,"UPDATEID"));
		workflowBusinessMap.setUpdateTime(d(r,"UPDATETIME"));

		workflowBusinessMap.setWorkflowId(l( r,"WFID"));
		workflowBusinessMap.setFlag(i(r,"FLAG"));
		workflowBusinessMap.setActionType(i( r,"ACTIONTYPE"));
		workflowBusinessMap.setRemark(s(r,"REMARK"));
		getLogger().info("===============getBusinessMapById()==============end");
		return workflowBusinessMap;
	}

	@Override
	public void addBusinessMap(WorkflowBusinessMap data) {
		getLogger().info("===============addBusinessMap()==============start");
		String sqlStr = "INSERT INTO HBC_F_BUSINESSMAP("
				+ " ID ,CREATEID ,CREATETIME ,UPDATEID ,UPDATETIME ," +
				" WFID, FLAG, ACTIONTYPE,REMARK ) VALUES( ? ,? ,? ,? ,? " +
				",? ,? ,?,?)";
		// 参数
		List<Object> params = new ArrayList<Object>();

		params.add(SequenceUtil.getTableId("HBC_F_BUSINESSMAP"));
		params.add(data.getCreateId());
		params.add(data.getCreateTime());
		params.add(data.getUpdateId());
		params.add(data.getUpdateTime());
		
		params.add(data.getWorkflowId());
		params.add(data.getFlag());
		params.add(data.getActionType());
		params.add(data.getRemark());

		jt.update(sqlStr, params.toArray());
		getLogger().info("===============addBusinessMap()==============end");
	}

	@Override
	public void updateBusinessMap(WorkflowBusinessMap data) {
		getLogger().info("===============updateBusinessMap()==============start");
		StringBuffer sql = new StringBuffer("update HBC_F_BUSINESSMAP ");
		sql.append(" set UPDATEID = ? ,UPDATETIME =?, WFID= ? , FLAG = ?, ACTIONTYPE = ?")
				.append(" ,REMARK = ? where ID= ?");
		// 参数
		List<Object> params = new ArrayList<Object>();
		
		params.add(data.getUpdateId());
		params.add(data.getUpdateTime());
		params.add(data.getWorkflowId());
		params.add(data.getFlag());
		params.add(data.getActionType());
		
		params.add(data.getRemark());
		params.add(data.getBaseId());
		
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============updateBusinessMap()==============end");
	}

	@Override
	public void delBM(long id) {
		getLogger().info("===============delBM()==============start");
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("DELETE FROM HBC_F_BUSINESSMAP WHERE 1=1");
		sql.append(" AND ID = ?");
		params.add(id);
		jt.update(sql.toString(), params.toArray());
		getLogger().info("===============delBM()==============end");
	}

}
