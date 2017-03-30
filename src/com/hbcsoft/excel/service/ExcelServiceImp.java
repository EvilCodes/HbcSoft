package com.hbcsoft.excel.service;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.controller.FormTableController;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.form.entity.FormTable;
import com.hbcsoft.form.service.FormTableService;
import com.hbcsoft.sys.controller.OuterDBLinkController;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.OuterDBLinkParaService;
import com.hbcsoft.table.controller.CreateDBtableController;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.table.service.CreateDBtable2Service;
import com.hbcsoft.table.service.CreateDBtableService;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.workflow.service.WorkflowService;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;
/**
 * ExcelServiceImp
 * @author gaodekui
 *
 */
@Transactional
@Service("excelService")
public class ExcelServiceImp extends LogBaseClass<ExcelServiceImp> implements ExcelService{
	/**
	 * createDBtableService Bean
	 */
	@Autowired
	private transient CreateDBtableService createDBtableService;
	/**
	 * dbLinkContorller Bean
	 */
	@Autowired
	private transient OuterDBLinkController dbLinkContorller;
	/**
	 * outerDBLinkParaService Bean
	 */
	@Autowired
	private transient OuterDBLinkParaService dBLinkParaService;
	/**
	 * formTableService Bean
	 */
	@Autowired
	private transient FormTableService formTableService;
	/**
	 * createTableService Bean
	 */
	@Autowired
	private transient CreateDBtable2Service createTable2Service;
	/**
	 * workflowService Bean
	 */
	@Autowired
	private transient WorkflowService workflowService;
	/**
	 * createDBtableController Bean
	 */
	@Autowired
	private transient CreateDBtableController dBtableController;
	/**
	 * formTableController Bean
	 */
	@Autowired
	private transient FormTableController formTableController;
	
	/**
	 * 操作创建数据库表
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public String operateCreateTableEntity(final List<List> list,final HttpServletRequest request) throws HbcsoftException{
		this.getLogger().info("========operateCreateTableEntity==========start==");
		String constant1;
		constant1 = "ID";
		String constant2;
		constant2 = "ZID";
		String constant3;
		constant3 = "COMPANYID";
		final SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
		final TableNameClass tn = (TableNameClass)list.get(0).get(0);
		tn.setMainId(tn.getMainId() == null ? 0:tn.getMainId());
		tn.setZid(tn.getZid() == null ? 0:tn.getZid());
		final String jianCheng = sessionInfo.getCompany().getCompanyNameHk().trim().toUpperCase();
		final String tableName = tn.getTableName().trim().toUpperCase();
		tn.setTableName(jianCheng+"_"+tableName);
		tn.setCompanyId(sessionInfo.getCompany().getId());
		tn.setCreateID(sessionInfo.getUser().getId());
		tn.setUpdateID(sessionInfo.getUser().getId());
		tn.setFlag(0);
		final Long id = SequenceUtil.getTableId("t_tablename");
		if (tn.getId() == null){
			tn.setId(id);
		}
		createDBtableService.saveTableName(tn);
		final List<TableEntity> entityList = new ArrayList<TableEntity>();
		for (int i = 0; i < list.get(1).size(); i++){
			final TableEntity entity = (TableEntity)list.get(1).get(i);
			if (entity == null)
				continue;
			if (entity.getId() == null){
				entity.setId(SequenceUtil.getTableId("t_tableentity"));
			}
			entity.setMainId(tn.getId());
			entity.setTableName(tn);
			entity.setChangeFlag(0);
			entity.setFlag(0);
			entity.setZid(entity.getZid() == null ? 0:entity.getZid());
			entity.setCompanyId(sessionInfo.getCompany().getId());
			entity.setCreateID(sessionInfo.getUser().getId());
			entity.setUpdateID(sessionInfo.getUser().getId());
			switch (entity.getType()) {
			case "文本":
				entity.setType("COLUMN_TYPE_TEXT");
				break;
			case "整数":
				entity.setType("COLUMN_TYPE_INT");
				break;
			case "小数":
				entity.setType("COLUMN_TYPE_NUMERIC");
				break;
			case "日期":
				entity.setType("COLUMN_TYPE_DATE");
				break;
			case "时间日期":
				entity.setType("COLUMN_TYPE_TIMESTAMP");
				break;
			default: break;
			}
			entityList.add(entity);
		}
		/****************新增3个字段start************************/
		List<TableEntity> list3 = new ArrayList<TableEntity>();
		list3 = dBtableController.setGUDingZiDuan(tn,sessionInfo);
		entityList.addAll(list3);
		/*****************新增3个字段end***********************/
		createDBtableService.saveEntity(entityList);
		/****************移除3个字段start************************/
		for(int i=entityList.size()-1; i>=0; i--){
			final String nameFiled = entityList.get(i).getName() != null ? entityList.get(i).getName().toUpperCase() : "";
			if(constant1.equals(nameFiled) || constant2.equals(nameFiled) || constant3.equals(nameFiled)){
				entityList.remove(i);
			}
		}
		/****************移除3个字段end************************/
		createTable2Service.operateSql(0, HBCSoftConstant.DBTYPE_MYSQL, tn,entityList);//创建表
		String message;
		message = "导入成功";
		
		this.getLogger().info("=======operateCreateTableEntity===========end==");
		return message;
	}
	
	/**
	 * 操作创建表单数据
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public String opreateCreateFrom(final List<List> list,final HttpServletRequest request) throws HbcsoftException {
		this.getLogger().info("=======opreateCreateFrom===========start==");
		String constant1;
		constant1 = "COLUMN_TYPE_NUMERIC";
		String constant2;
		constant2 = "COLUMN_TYPE_DATE";
		String constant3;
		constant3 = "主表";
		final SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
		//表单表名表数据
		final List<FormTable> lstfortab = new ArrayList<FormTable>();
		//表单字段表数据
		final List<FormFields> lstfie = new ArrayList<FormFields>();
		//获取表单对象并保存
		final FormName forNam = (FormName) list.get(0).get(0);
		forNam.setIsEnabled(HBCSoftConstant.INT_TRUE);
		forNam.setVersNum(forNam.getFormNamef()+"Ver1.0");
		forNam.setCompanyId(sessionInfo.getCompany().getId());
		forNam.setCreateID(sessionInfo.getUser().getId());
		forNam.setActionUrl("template/open.hbc?formType="+forNam.getFormType());
		final Long formid = SequenceUtil.getTableId("F_FORMNAME");
		forNam.setId(formid);
		formTableService.addFormName(forNam);
		//保存资源管理信息
		String actionUrl;
		actionUrl = forNam.getActionUrl().substring(0, forNam.getActionUrl().lastIndexOf('?'));
		formTableController.saveResource(forNam.getFormNamef(), actionUrl, forNam.getFormType());
		//表字段信息
		List<TableEntity> listEnt = new ArrayList<TableEntity>();
		//保存不属于同一张表的表字段信息集合
		final List<List<TableEntity>> listEntList = new ArrayList<List<TableEntity>>();
		for (int i = 0; i < list.get(1).size();i++){
			final FormFields fields = (FormFields)list.get(1).get(i);
			final TableNameClass  tablename = createDBtableService.tableNm(fields.getTableId().toString());
			boolean tempflag = true;
			if (!listEntList.isEmpty()){
				for (List<TableEntity> tempList:listEntList){
					if (tempList.get(0).getMainId().toString().equals(fields.getTableId().toString())){
						tempflag = false;
					} ;
				}
			}
			if (tempflag){
				listEnt = createTable2Service.queryAllEntity(fields.getTableId().toString());
				listEntList.add(listEnt);
			}
			//判断lstfortab是否已经存在FormTable对象
			if (lstfortab.isEmpty() ){
				final FormTable fortab = new FormTable();
				fortab.setId(SequenceUtil.getTableId("F_FORMTABLE"));
				fortab.setFormId(forNam.getId());
				fortab.setTableId(tablename.getId());
				fortab.setTableName(tablename.getTableName());
				fortab.setCompanyId(sessionInfo.getCompany().getId());
				fortab.setCreateID(sessionInfo.getUser().getId());
				if(constant3.equals(tablename.getIsMainTable()) || constant3.equals(tablename.getIsMainTable())){
					fortab.setIsMainTable(0);
				}else{
					fortab.setIsMainTable(1);
				}
				fortab.setTableMainId(tablename.getMainId());
				fortab.setTableMainName(tablename.getMainName());
				fortab.setTableMemo(tablename.getMemo());
				lstfortab.add(fortab);
				fields.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
				fields.setFormId(forNam.getId());
				fields.setTableId(fortab.getTableId());
				fields.setTableName(fortab.getTableName());
				fields.setTableMemo(fortab.getTableMemo());
				fields.setCompanyId(sessionInfo.getCompany().getId());
				fields.setCreateID(sessionInfo.getUser().getId());
				lstfie.add(fields);
			}else{
				boolean flag = true;
				//判断是否存在指定的FromTable对象
				for (int k = 0; k < lstfortab.size();k++){
					final FormTable fortab = lstfortab.get(k);
					if (fortab.getTableId().toString().equals(tablename.getId().toString())){
						flag = false;
					}
				}
				//指定的FromTable对象不存在创建FromTable
				if (flag){
					final FormTable fortab = new FormTable();
					fortab.setId(SequenceUtil.getTableId("F_FORMTABLE"));
					fortab.setFormId(forNam.getId());
					fortab.setTableId(tablename.getId());
					fortab.setTableName(tablename.getTableName());
					fortab.setCompanyId(sessionInfo.getCompany().getId());
					fortab.setCreateID(sessionInfo.getUser().getId());
					if( constant3.equals(tablename.getIsMainTable()) || constant3.equals(tablename.getIsMainTable())){
						fortab.setIsMainTable(0);
					}else{
						fortab.setIsMainTable(1);
					}
					fortab.setTableMainId(tablename.getMainId());
					fortab.setTableMainName(tablename.getMainName());
					fortab.setTableMemo(tablename.getMemo());
					lstfortab.add(fortab);
					fields.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
					fields.setFormId(forNam.getId());
					fields.setTableId(fortab.getTableId());
					fields.setTableName(fortab.getTableName());
					fields.setTableMemo(fortab.getTableMemo());
					fields.setCompanyId(sessionInfo.getCompany().getId());
					fields.setCreateID(sessionInfo.getUser().getId());
					lstfie.add(fields);
					
				}else{
					for (int k = 0; k < lstfortab.size();k++){
						final FormTable fortab = lstfortab.get(k);
						if (fortab.getTableId().toString().equals(tablename.getId().toString())){
							fields.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
							fields.setFormId(forNam.getId());
							fields.setTableId(fortab.getTableId());
							fields.setTableName(fortab.getTableName());
							fields.setTableMemo(fortab.getTableMemo());
							fields.setCompanyId(sessionInfo.getCompany().getId());
							fields.setCreateID(sessionInfo.getUser().getId());
							lstfie.add(fields);
						}
					}
				}
				
			
			}
			
		}
		if (!listEntList.isEmpty()){
			for (List <TableEntity>listEntt:listEntList){
				//保存表单与表名表信息
				final List<TableEntity> templistEnt = new ArrayList<TableEntity>(listEntt);
				//判断lstfie集合中是否存在已经关联的TableEntity，并将关联的TableEntity从集合中删除
				for(int o=0;o<templistEnt.size();o++){
					for (int m = 0; m < lstfie.size();m++){
						final FormFields forfie = lstfie.get(m);
						for(int n=0;n<listEntt.size();n++){
							if (listEntt.get(n).getName().equalsIgnoreCase(forfie.getFieldName())){
								listEntt.remove(n);
							}
						}
					}
					
				}
				
				if (!listEntt.isEmpty()){
					FormTable fortab = null;
					for(int l = 0; l < lstfortab.size(); l++){
						if (listEntt.get(0).getMainId().equals(lstfortab.get(l).getTableId()))
							fortab = lstfortab.get(l);
					}
					int sort = 0;
					for (int p = 0; p < lstfie.size(); p++){
						final int tempsort = lstfie.get(p).getSort();
						for (int q = 0; q < lstfie.size(); q++){
							if (tempsort > lstfie.get(q).getSort()){
								sort = tempsort;
							}else{
								sort = lstfie.get(q).getSort();
							}
						}
						
					}
					for(int j=0;j<listEntt.size();j++){
						final FormFields forfie = new FormFields();
						forfie.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
						forfie.setFormId(forNam.getId());
						forfie.setTableId(fortab.getTableId());
						forfie.setTableName(fortab.getTableName());
						forfie.setTableMemo(fortab.getTableMemo());
						forfie.setFieldId(listEntt.get(j).getId());
						forfie.setFieldName(listEntt.get(j).getName());
						forfie.setInputIsDisplay(1);
						forfie.setTitle(listEntt.get(j).getTitle());
						forfie.setCompanyId(sessionInfo.getCompany().getId());
						forfie.setCreateID(sessionInfo.getUser().getId());
						forfie.setSort(++sort);
						if(constant1.equals(listEntt.get(j).getType()) || constant1.equals(listEntt.get(j).getType())){
							forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_7);
						}else if(constant2.equals(listEntt.get(j).getType()) || constant2.equals(listEntt.get(j).getType())){
							forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_5);
						}else{
							forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_0);
						}
						lstfie.add(forfie);
					}
				}
			}
		}
		formTableService.addFormFields(lstfie);
		formTableService.saveFormTable(lstfortab);
		String message;
		message = "导入成功";
		this.getLogger().info("=======opreateCreateFrom===========end==");
		return message;
	}
	
	/**
	 * 操作创建数据库表和表单
	 * @param list
	 * @param request
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public String opreateCreateTableAndFrom(final List<List> list,
			final HttpServletRequest request) throws HbcsoftException {
		String message = "";
		
		final List tempEntity1 = new ArrayList();//存放TableName对象
		final List tempEntity2 = new ArrayList();//存放TableEntity对象
		final List <List>tempList1 = new ArrayList<List>();//存放tempEntity1和tempEntity2集合
		
		final List tempEntity3 = new ArrayList();//存放FormName对象
		final List tempEntity4 = new ArrayList();//存放FormFields对象
		final List<List>tempList2 = new ArrayList<List>();//存放tempEntity3和tempEntity5集合
		for (int i = 0; i < list.size();i++){
			final List temp = list.get(i);
			for (final Object obj:temp){
				if (obj == null)
					continue;
				String className;
				className = obj.getClass().getSimpleName();
				if ("TableNameClass".equals(className))
					tempEntity1.add(obj);
				else if ("TableEntity".equals(className))
					tempEntity2.add(obj);
				else if ("FormName".equals(className))
					tempEntity3.add(obj);
				else if ("FormFields".equals(className))
					tempEntity4.add(obj);
			}
		}
		tempList1.add(tempEntity1);
		tempList1.add(tempEntity2);
		tempList2.add(tempEntity3);
		tempList2.add(tempEntity4);
		message = operateCreateTableEntity(tempList1, request);
		message = opreateCreateFrom(tempList2, request);
		return message;
	}
	/**
	 * 操作新增第三方数据库配置数据
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public String operateDatabaseConfiguration(final List<List> list,final HttpServletRequest request) throws HbcsoftException {
		this.getLogger().info("=======operateDatabaseConfiguration===========start==");
		String dbDriver = "";
		String dbURL = "";
		final SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
		final long strCompanyId = sessionInfo.getCompany().getId();
		String strCompanyName;
		strCompanyName = sessionInfo.getCompany().getCompanyName();
		final List<OuterDBLinkPara> dbLinkParaList = new ArrayList<OuterDBLinkPara>();
		for (int i = 0; i < list.get(0).size();i++){
			final OuterDBLinkPara dbLinkPara = (OuterDBLinkPara)list.get(0).get(i);
			//根据不同数据库类型获取驱动名
			 dbDriver = dbLinkContorller.getDbDriver(dbLinkPara.getDbType());
			dbLinkPara.setDbDriver(dbDriver);
			
			//获得数据库驱动路径
			dbURL = dbLinkContorller.getDbUrl(dbLinkPara.getDbType(),dbLinkPara.getDbIp(),dbLinkPara.getDbsId());
			dbLinkPara.setDbURL(dbURL);
			
			final Long id = SequenceUtil.getTableId("t_sys_outerdblinkpara");
			dbLinkPara.setId(id);
			dbLinkPara.setZid(dbLinkPara.getZid() == null?0:dbLinkPara.getZid());
			dbLinkPara.setCompanyId(strCompanyId);
			dbLinkPara.setCompanyName(strCompanyName);
			dbLinkParaList.add(dbLinkPara);
		}
//		dBLinkParaService.saveOuterDbPara(dbLinkParaList);
		String message;
		message = "导入成功！";
		this.getLogger().info("=======operateDatabaseConfiguration===========end==");
		return message;
	}

	/**
	 * 操作新增工作流基础配置
	 * @param list
	 * @return
	 */
	@Override
	public String opreateWorkFlowBaseConfigure(final List<List> list) {
		this.getLogger().info("=======opreateWorkFlowBaseConfigure===========start==");
		final WorkflowConfig workflowConfig = (WorkflowConfig)list.get(0).get(0);
		//session取值
		workflowConfig.setCreateId("1");
		workflowConfig.setCreateName("admin");
		workflowConfig.setUpdateId("1");
		workflowConfig.setUpdateName("admin");
		
		//获取时间
		final Date date = new Date();
		workflowConfig.setCreateTime(date);
		workflowConfig.setUpdateTime(date);
		if(workflowConfig.getBaseId()==0){
			workflowService.addWC(workflowConfig);
		}else{
			
			workflowService.updateWC(workflowConfig);
		}
		String message;
		message = "导入成功";
		this.getLogger().info("=======opreateWorkFlowBaseConfigure===========end==");
		return message;
	}

}
