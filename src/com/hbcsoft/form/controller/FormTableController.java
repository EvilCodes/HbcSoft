package com.hbcsoft.form.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.form.entity.FormTable;
import com.hbcsoft.form.service.FormTableService;
import com.hbcsoft.sys.entity.ClickManage;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.ClickManageService;
import com.hbcsoft.sys.service.ResourceService2;
import com.hbcsoft.table.entity.*;
import com.hbcsoft.table.service.*;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 表单Controller层
 * @author zhanghaijiao
 *
 */
@Controller
@RequestMapping(value = "/formTable")
public class FormTableController extends BaseController<FormTableController>  {
	/**
	 * 引用formTableService
	 */
	@Autowired
	private transient FormTableService formTableService;
	
	/**
	 * 引用ResourceService
	 */
	@Autowired
	private transient ResourceService2 resourceService;
	
	/**
	 * 引用CreateDBtableService
	 */
	@Autowired
	private transient CreateDBtableService createTableService;
	
	/**
	 * 引用CreateDBtable2Service
	 */
	@Autowired
	private transient CreateDBtable2Service createTable2Service;
	/**
	 * ClickManageService接口 
	 */
	@Autowired
	private transient ClickManageService manageService;
	
	/**
	 * 每页显示的条数
	 */
	private static final int PAGESIZE = 10;
	
	/**
	 * 页数
	 */
	private static final String CURRENTPAGE="currentPage";
	
	/**
	 * 常量1
	 */
	private static final int NUM1 = 1;
	
	/**
	 * 字符串表单id
	 */
	private static final String FORMID = "formid";
	
	/**
	 * 字符串lstfie
	 */
	private static final String LSTFIE = "lstfie";
	
	/**
	 * 总记录数
	 */
	private transient int totalNum;
	
	/**
	 * 获取formType
	 * @param request
	 * @return
	 */
	private String formTypeInfo(final HttpServletRequest request){
		return request.getParameter("formType");
	}
	
	private String tableNameInfo(final HttpServletRequest request){
		return request.getParameter("tableName");
	}
	
	
	/**
	 * 获取公司ID
	 * @param request
	 * @return
	 */
	public Long companyID(){
		return ((SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO)).getCompany().getId();
	}
	
	/**
	 * 获取用户ID
	 * @return
	 */
	public Long userId(){
		return (Long) ((SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO)).getUser().getId();
	}
	/**
	 * 表单表查询
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectAllForm")
	public ModelAndView selectAllForm(final HttpServletRequest request,final String page, final HttpServletResponse response) throws HbcsoftException
	{
		this.getLogger().info("=======selectAllForm=====start===");
		int currentPage;//第几页
		final ModelAndView mav = new ModelAndView("form/form_list");
		final String ttable = request.getParameter("ttable");
		final String method = request.getParameter("tmethod");
		final String formType = this.formTypeInfo(request);
		final String memo = request.getParameter("tmemo");
		final String[] qform = {ttable,method,formType,memo};
		final List<FormName> listFor = formTableService.queryForm(qform,companyID());
		totalNum = listFor.size();
		int pageTimes;//总页数
		if(totalNum % PAGESIZE == 0){
			pageTimes = totalNum/PAGESIZE;
		}else{
			pageTimes = totalNum/PAGESIZE +1;
		}
		final String cu = request.getParameter(CURRENTPAGE)==null ? "" : request.getParameter(CURRENTPAGE);
		final String cp = "".equals(cu)? "1": cu;
		currentPage = Integer.parseInt(cp);
		final int startRow = (currentPage-1) * PAGESIZE;
		final String[] qallform = {ttable, method, formType, memo,String.valueOf(startRow), String.valueOf(PAGESIZE)};
		final List<FormName> list = formTableService.queryAllForm(qallform,companyID());
		mav.getModelMap().put("pageSize", PAGESIZE);
		mav.getModelMap().put(CURRENTPAGE, currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", ttable);
		mav.getModelMap().put("searchMethod", method);
		mav.getModelMap().put("searchFormType", formType);
		mav.getModelMap().put("searchMemo", memo);
		mav.getModelMap().put("list", list);
		this.getLogger().info("=======selectAllForm=====end===");
		return mav;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Object pageList(final HttpServletRequest request){ 
		final String searchTable = request.getParameter("searchTable");
		final String searchMethod = request.getParameter("searchMethod"); 
		final String searchFormType = request.getParameter("searchFormType");
		final String searchMemo = request.getParameter("searchMemo");
		final int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		final JSONObject jsonObject = new JSONObject(); 
		final String[] qform = {searchTable,searchMethod,searchFormType,searchMemo};
		final List<FormName> allFormName = formTableService.queryForm(qform,companyID());
		totalNum = allFormName.size();
		int pageTimes;//
		if(totalNum%PAGESIZE == 0){
			pageTimes = totalNum/PAGESIZE;
		}else{
			pageTimes = totalNum/PAGESIZE + 1;
		}

		final int startRow = (currentPage-1) * PAGESIZE;
		final String[] qallform = {searchTable, searchMethod, searchFormType, searchMemo,String.valueOf(startRow), String.valueOf(PAGESIZE)};
		final List<FormName> list = formTableService.queryAllForm(qallform,companyID());
		jsonObject.put("pageSize", PAGESIZE);
		jsonObject.put("currentPage", currentPage);
		jsonObject.put("pageTimes", pageTimes);
		jsonObject.put("totalNum", totalNum);
		jsonObject.put("list", list);
		return jsonObject;
	}
	
	/**
	 * 跳转到新增数据表页面：添加页面
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/addForm")
	public ModelAndView addForm() throws HbcsoftException{
		this.getLogger().info("=======addForm=====start===");
		final ModelAndView mav = new ModelAndView("form/form_add");
		final List<TableNameClass> listTab = createTableService.queryAll(null, null);
		mav.getModelMap().put("listTab", listTab);
		this.getLogger().info("=======addForm=====end===");
		return mav;
	}

	/**
	 * 新增表后保存表单表方法
	 * @param fName
	 */
	private void saveformName(final String[] fName,final Long formid){
		final String formName = fName[0];
		final String actionUrl = fName[1];
		final String method = fName[2];
		final String formType = fName[3];
		final String memo = fName[4];
		final FormName fN = new FormName();
		fN.setFormNamef(formName);
		fN.setActionUrl(actionUrl+formType);
		fN.setMethod(method);
		fN.setMemo(memo);
		fN.setIsEnabled(HBCSoftConstant.INT_TRUE);
		fN.setVersNum(formName+"Ver1.0");
		fN.setId(formid);
		fN.setFormType(formType);
		fN.setCreateID(userId());
		fN.setCompanyId(companyID());
		try {
			formTableService.addFormName(fN);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
	}
	/**
	 * 修改表后保存表单表方法
	 * @param fName
	 */
	private void updateSaveformName(final String[] fName,final Long formid){
		final String formName = fName[0];
		final String actionUrl = fName[1];
		final String method = fName[2];
		final String formType = fName[3];
		final String memo = fName[4];
		final String versNum = fName[5];
		final FormName table = new FormName();
		table.setFormNamef(formName);
		table.setActionUrl(actionUrl);
		table.setMethod(method);
		table.setFormType(formType);
		table.setMemo(memo);
		table.setIsEnabled(HBCSoftConstant.INT_TRUE);
		table.setVersNum(versNum);
		table.setId(formid);
		table.setCompanyId(companyID());
		table.setCreateID(userId());
		try {
			formTableService.addFormName(table);
		} catch (HbcsoftException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
		}
	}
	
	/**
	 * 新增表后保存资源管理信息
	 */
	public void saveResource(final String formName, final String actionUrl, final String formType){
		final Resource resource = new Resource();
		resource.setCode(formType);
		resource.setName(formName);
		resource.setUrl(actionUrl+formType);
		resource.setEnable(HBCSoftConstant.FORM_INPUT_TYPE_0);
		final Long resourceid = SequenceUtil.getTableId("T_SYS_RESOURCE");
		resource.setCompanyId(companyID());
		resource.setId(resourceid);
		resource.setCreateID(userId());
		resource.setParentResourceId(Long.valueOf(HBCSoftConstant.FORM_INPUT_TYPE_1));
		resource.setGroupId(String.valueOf(HBCSoftConstant.FORM_INPUT_TYPE_1));
		try {
			resourceService.addResource(resource);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
	}
	
	/**
	 * addFormName方法中添加表单与表关系表数据
	 * @param tablename
	 * @param formid
	 * @return
	 */
	private FormTable addFormTable(final TableNameClass tablename,final Long formid){
		final FormTable fortab = new FormTable();
		fortab.setId(SequenceUtil.getTableId("F_FORMTABLE"));
		fortab.setCreateID(userId());
		fortab.setFormId(formid);
		fortab.setTableId(tablename.getId());
		fortab.setTableName(tablename.getTableName());
		if(tablename.getIsMainTable().equals("主表")){
			fortab.setIsMainTable(0);
		}else{
			fortab.setIsMainTable(1);
		}
		fortab.setTableMainId(tablename.getMainId());
		fortab.setTableMainName(tablename.getMainName());
		fortab.setTableMemo(tablename.getMemo());
		fortab.setCompanyId(companyID());
		fortab.setTableFlag(tablename.getFlag());
		return fortab;
	}
	
	/**
	 * addFormName方法中添加表单字段表数据
	 * @param tabEnt
	 * @param fortab
	 * @param formid
	 * @return
	 */
	private FormFields addFormFields(final TableEntity tabEnt,final FormTable fortab,final Long formid){
		final FormFields forfie = new FormFields();
		forfie.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
		forfie.setCreateID(userId());
		forfie.setCompanyId(companyID());
		forfie.setFormId(formid);
		forfie.setTableId(fortab.getTableId());
		forfie.setTableName(fortab.getTableName());
		forfie.setTableMemo(fortab.getTableMemo());
		forfie.setFieldId(tabEnt.getId());
		forfie.setFieldName(tabEnt.getName());
		forfie.setTitle(tabEnt.getTitle());
		forfie.setInputDefaultValue(tabEnt.getDefaultValue());
		forfie.setSourceMode(2);
		forfie.setFieldFlag(tabEnt.getFlag());
		forfie.setNumber(tabEnt.getNumber());
		forfie.setDecimalDigits(tabEnt.getDecimalDigits());
		forfie.setIsNull(tabEnt.getIsNull());
		if(tabEnt.getType().equals("COLUMN_TYPE_NUMERIC")){
			forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_7);
		}else if( tabEnt.getType().equals("COLUMN_TYPE_DATE")){
			forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_5);
		}else{
			forfie.setInputType(HBCSoftConstant.FORM_INPUT_TYPE_0);
		}
		return forfie;
	}
	
	/**
	 * 新增表后，跳转到列表页面
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/addFormName")
	public ModelAndView addFormName(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		this.getLogger().info("=======addFormName=====start===");
		//保存主表表单信息
		final String formName = this.tableNameInfo(request);
		final String actionUrl = request.getParameter("actionUrl");
		final String method = request.getParameter("method");
		final String formType = this.formTypeInfo(request);
		final String memo = request.getParameter("memo");
		final String[] forNam = {formName,actionUrl,method,formType,memo};
		final Long formid = SequenceUtil.getTableId("F_FORMNAME");
		final ModelAndView mav = new ModelAndView("redirect:/formTable/formFieldAdd.hbc?formid="+formid);
		final List<FormName> lstfn= formTableService.queryisFormName(companyID(),formType,HBCSoftConstant.FORM_INPUT_TYPE_1);
		if(lstfn.isEmpty()){
			saveformName(forNam,formid);//保存表单主表信息
			saveResource(formName,actionUrl,formType);//保存资源管理信息
			//保存表单与表名表信息和表单字段表信息
			final String[] rowid = request.getParameterValues("rowid");
			//表单表名表数据
			final List<FormTable> lstfortab = new ArrayList<FormTable>();
			//表单字段表数据
			final List<FormFields> lstfie = new ArrayList<FormFields>();
			//表字段信息
			List<TableEntity> listEnt = new ArrayList<TableEntity>();
			if(rowid != null && !"".equals(rowid)){
				for(int i=0; i<rowid.length; i++){
					//获取TableName中id为rowid[i]的记录
					final String tableId = rowid[i];
					final TableNameClass tablename = createTableService.tableNm(tableId);
					//获取表字段表中mainId为rowid[i]的信息
					listEnt = createTable2Service.queryAllEntity(tableId);
					final FormTable fortab = addFormTable(tablename,formid);
					//保存表单与表名表信息
					lstfortab.add(fortab);
					for(int j=0;j<listEnt.size();j++){
						lstfie.add(addFormFields(listEnt.get(j),fortab,formid));//保存表单字段信息
					}
				}
				formTableService.addFormFields(lstfie);
				formTableService.saveFormTable(lstfortab);
			}
		}
		getLogger().info("=======addFormName=====end===");
		return mav;
	}
	/**
	 * 跳转到formField_add页面
	 * @param request
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/formFieldAdd")
	public ModelAndView formFieldAdd (final Long formid){
		final ModelAndView mav=new ModelAndView("form/formField_add");
		final String formType = this.formTypeInfo(request);
		List<FormName> lstfn;
		try {
			lstfn = formTableService.queryisFormName(companyID(),formType,HBCSoftConstant.FORM_INPUT_TYPE_1);
			if(lstfn.isEmpty()){
				final List<FormFields> listForFields = formTableService.queryFormFieShow(formid,companyID(),HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2);
				mav.getModelMap().put(FORMID, formid);
				mav.getModelMap().put(LSTFIE, listForFields);
			}else{
				final List<FormFields> listForFields = formTableService.queryFormFieShow(lstfn.get(0).getId(),companyID(),HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2);
				mav.getModelMap().put(FORMID, formid);
				mav.getModelMap().put(LSTFIE, listForFields);
			}
			//点选信息
			final List<ClickManage> optionList = manageService.getAllOptions(companyID());
			mav.getModelMap().put("optionList", optionList);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return mav;
	}

	/**
	 * 保存表单表中字段属性
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/saveFormField", method = RequestMethod.POST)
	public ModelAndView saveFormField(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		//子表信息
		final String [] fieldName = request.getParameterValues("fieldName");
		final String [] tableName = request.getParameterValues("tableName");
		final String [] tableId = request.getParameterValues("tableId");
		final String [] formid = request.getParameterValues(FORMID); 
		final String [] fieldId = request.getParameterValues("fieldId");
		final String [] title = request.getParameterValues("title");
		final String [] inputIsDisplay = request.getParameterValues("inputIsDisplay");
		final String [] inputType = request.getParameterValues("inputType");//int
		final String [] sourceMode = request.getParameterValues("sourceMode");
		final String [] sourceContent = request.getParameterValues("sourceContent");
		final String [] queryisConditions = request.getParameterValues("queryisConditions");//int
		final String [] queryisColumn = request.getParameterValues("queryisColumn");//int
		final String [] isShowColumn = request.getParameterValues("isShowColumn");//int
		final String [] isModify = request.getParameterValues("isModify");
		final String [] sort = request.getParameterValues("sort");
		final String [] inputDefaultValue = request.getParameterValues("inputDefaultValue");
		final String [] queryisRequired = request.getParameterValues("queryisRequired");
		final String [] isRequired = request.getParameterValues("isRequired");	
		final String [] number = request.getParameterValues("number");
		final String [] decimalDigits = request.getParameterValues("decimalDigits");
		final String [] clickInfo = request.getParameterValues("clickInfo");//点选信息
		final String [] isNull = request.getParameterValues("isNull");
		final List<FormFields> list = new ArrayList<FormFields>();
		if(fieldId != null && fieldId.length > 0){
			for(int i=0; i<fieldId.length; i++){
				final String[] stringff = {formid[i],fieldId[i]};
				final int[] intsff = {HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2};
				final FormFields forfie= formTableService.selectFormFields(intsff,stringff,companyID());
				forfie.setFieldName(fieldName[i]);
				forfie.setTableName(tableName[i]);
				forfie.setTableId(Long.valueOf(tableId[i]));
				forfie.setFormId(Long.valueOf(formid[i]));
				forfie.setFieldId(Long.valueOf(fieldId[i]));
				forfie.setTitle(title[i]);
				forfie.setInputIsDisplay(Integer.valueOf("".equals(inputIsDisplay[i]) ? "0" : inputIsDisplay[i]));
				forfie.setInputType(Integer.valueOf(inputType[i]));
				if("".equals(sourceMode[i])){
					forfie.setSourceMode(2);
					forfie.setSourceContent("");
				}else{
					forfie.setSourceMode(Integer.valueOf(sourceMode[i]));
					forfie.setSourceContent(sourceContent[i]);
				}
				if (!clickInfo[i].isEmpty()) {// 获取点选方法名
					forfie.setClickInfo(clickInfo[i]);
				}
				forfie.setQueryisConditions(Integer.valueOf("".equals(queryisConditions[i]) ? "0" : queryisConditions[i]));
				forfie.setQueryisColumn(Integer.valueOf("".equals(queryisColumn[i]) ? "0" : queryisColumn[i]));
				forfie.setIsShowColumn(Integer.valueOf("".equals(isShowColumn[i]) ? "0" : isShowColumn[i]));
				forfie.setIsModify(Integer.valueOf("".equals(isModify[i]) ?"0" : isModify[i]));
				forfie.setSort(Integer.valueOf(sort[i]));
				forfie.setInputDefaultValue(inputDefaultValue[i]);
				forfie.setQueryisRequired(Integer.valueOf("".equals(queryisRequired[i]) ? "0" : queryisRequired[i]));
				forfie.setIsRequired(Integer.valueOf("".equals(isRequired[i]) ? "0" : isRequired[i]));
				forfie.setNumber(Integer.valueOf("".equals(number[i]) ? "0" : number[i]));
				forfie.setDecimalDigits(Integer.valueOf("".equals(decimalDigits[i]) ? "0" : decimalDigits[i]));
				forfie.setIsNull(Integer.valueOf("".equals(isNull[i]) ? "0" : isNull[i]));
				forfie.setCreateID(userId());
				forfie.setCompanyId(companyID());
				list.add(forfie);
			}
		}
		formTableService.saveFormFields(list);
		return new ModelAndView("redirect:/formTable/selectAllForm.hbc");
	}
	
	/**
	 * 跳转到修改数据表页面：修改页面
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateForm")
	public ModelAndView updateForm(final HttpServletRequest request){
		this.getLogger().info("=======updateForm=====start===");
		final ModelAndView mav = new ModelAndView("form/form_update");
		final String id = request.getParameter("ids");
		FormName forNam = null;
		List<FormTable> fortab = null;
		List<TableNameClass> lstft = null;
		try {
			forNam = formTableService.selectIDForm(id,companyID());
			fortab = formTableService.selectFormTable(id, companyID());
			lstft = createTableService.queryAll(null, null);
			final Map mapforfie = new ConcurrentHashMap();
			for(final FormTable ff : fortab){
				mapforfie.put(ff.getTableId(), ff.getTableId());
			}
			for(int i=lstft.size()-1;i>-1;i--){
				if(lstft.get(i).getId() == mapforfie.get(lstft.get(i).getId()) || 
						lstft.get(i).getId().equals(mapforfie.get(lstft.get(i).getId()))){
					lstft.remove(i);
				}
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		mav.getModelMap().put("forTab", forNam);
		mav.getModelMap().put("forfie", fortab);
		mav.getModelMap().put("lstft", lstft);
		this.getLogger().info("=======updateForm=====end===");
		return mav;
	}
	
	/**
	 * 修改后保存资源管理信息
	 */
	public void updateSaveResource(final Resource resource,final String reportName,final Long reportId,final String actionUrl){
		resource.setCode(String.valueOf(reportId));
		resource.setName(reportName);
		resource.setUrl(actionUrl);
		resource.setCompanyId(companyID());
		resource.setUpdateID(userId());
		try {
			resourceService.update(resource);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
	}
	
	/**
	 * 保存表单修改后信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateTableForm")
	public ModelAndView updateTableForm(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		FormName fn = new FormName();//修改原始单据
		Resource oldResource = new Resource();//修改原始资源管理信息
		int versionNum=0;
		//主表表单信息
		final String oldFormId = request.getParameter("tabId");//表单id
		final String formName = this.tableNameInfo(request);
		final String actionUrl = request.getParameter("actionUrl");
		final String method = request.getParameter("method");
		final String formType = this.formTypeInfo(request);
		final String memo = request.getParameter("memo");
		String versNum = request.getParameter("versNum");
		versionNum = Integer.valueOf(versNum.substring(versNum.lastIndexOf('.')+1))+1;
		int index = versNum.indexOf("Ver1");  //获取出现Ver1字符串的第一个位置，这里要保证前面没有跟Ver1重复
		String left = versNum.substring(index); //截取Ver1出现位置后面的字符串
		//String right = versNum.substring(0,index); //截取Ver1出现位置前面的字符串
		versNum=formName+left;
		versNum = versNum.substring(0, versNum.lastIndexOf('.')+1)+versionNum;
		final List<FormName> lstfn= formTableService.queryUpdateIsFormName(companyID(),formType,HBCSoftConstant.FORM_INPUT_TYPE_1,versNum);
		final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
		final ModelAndView mav = new ModelAndView("redirect:/formTable/formFieldUpdate.hbc?versNum="+versNum+"&fromid="+fromid+"&oldFormId="+oldFormId);
		oldResource = resourceService.queryForAndRes(companyID(), actionUrl);
		if(lstfn.isEmpty()){
			final String[] forNam = {formName,actionUrl,method,formType,memo,versNum};
			updateSaveformName(forNam, fromid);
			updateSaveResource(oldResource,formName,fromid,actionUrl);
			//修改原始表单是否启用状态
			fn = formTableService.selectIDForm(oldFormId,companyID());
			fn.setUpdateID(userId());
			fn.setCompanyId(companyID());
			fn.setIsEnabled(HBCSoftConstant.INT_FALSE);
			formTableService.updateFormName(fn);
			//保存表单与表名表信息和表单字段表信息
			final String[] rowid = request.getParameterValues("rowid");
			//表单表名表数据
			final List<FormTable> lstftn = new ArrayList<FormTable>();
			//表单字段表数据
			final List<FormFields> lstfie = new ArrayList<FormFields>();
			//表字段信息
			List<TableEntity> listEnt = new ArrayList<TableEntity>();
			//修改前表单字段表数据信息
			final List<FormTable> ftb = formTableService.selectFormTable(oldFormId, companyID());
			List<FormFields> lstffs= new ArrayList<FormFields>();
			final Map mapfId = new ConcurrentHashMap();
			for(final FormTable tb : ftb){
				mapfId.put(tb.getTableId(),tb.getTableId());
			}
			if(rowid != null && !"".equals(rowid)){
				for(int i=0; i<rowid.length; i++){
					//获取TableName中id为rowid[i]的记录
					final TableNameClass tablename = createTableService.tableNm(rowid[i]);
					//获取表字段表中mainId为rowid[i]的信息
					final FormTable ftn = addFormTable(tablename, fromid);
					//保存表单与表名表信息
					lstftn.add(ftn);
					if(Long.valueOf(rowid[i]) == mapfId.get(Long.valueOf(rowid[i])) ||
							Long.valueOf(rowid[i]).equals(mapfId.get(Long.valueOf(rowid[i])))){
						lstffs = formTableService.selectLstFormField(oldFormId,rowid[i],companyID());
						for(int j=0;j<lstffs.size();j++){
							final FormFields forfie = updateAddForfie(lstffs.get(j),fromid);
							lstfie.add(forfie);
						}
					}else{
						//获取表字段表中mainId为rowid[i]的信息
						listEnt = createTable2Service.queryAllEntity(rowid[i]);
						int sort = 0;
						if(!lstfie.isEmpty()){
							sort = lstfie.get((lstfie.size()-1)).getSort();						
						}
						for(int j=0;j<listEnt.size();j++){
							final FormFields forfie = updateAddForField(listEnt.get(j),ftn,fromid,sort,j);
							lstfie.add(forfie);
						}
					}
				}
				formTableService.addFormFields(lstfie);
				formTableService.saveFormTable(lstftn);
			}
		}
		return mav;
	}
	
	/**
	 * 跳转到formField_update页面
	 * @param request
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/formFieldUpdate")
	public ModelAndView formFieldUpdate (final String versNum,final Long fromid,final String oldFormId){
		final ModelAndView mav=new ModelAndView("form/formField_update");
		final String formType =this.formTypeInfo(request);
		List<FormName> lstfn;
		try {
			lstfn = formTableService.queryUpdateIsFormName(companyID(),formType,HBCSoftConstant.FORM_INPUT_TYPE_1,versNum);
			if(lstfn.isEmpty()){
				final List<FormFields> lstforfie = formTableService.queryFormFieShow(fromid, companyID(), HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2);
				mav.getModelMap().put(FORMID, fromid);
				mav.getModelMap().put(LSTFIE, lstforfie);
			}else{
				final List<FormFields> lstforfie = formTableService.queryFormFieShow(Long.valueOf(oldFormId), companyID(), HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2);
				mav.getModelMap().put(FORMID, oldFormId);
				mav.getModelMap().put(LSTFIE, lstforfie);
			}//点选信息
			final List<ClickManage> optionList = manageService.getAllOptions(companyID());
			mav.getModelMap().put("optionList", optionList);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return mav;
	}

	
	/**
	 * updateTableForm方法中添加查询出表单字段表信息
	 * @param forfie
	 * @param fromid
	 * @return
	 */
	private FormFields updateAddForfie(final FormFields forfie,final Long fromid){
		forfie.setFormId(fromid);
		if(forfie.getTitle().equals("")){
			forfie.setTitle(null);
		}
		forfie.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
		forfie.setCreateID(userId());
		forfie.setCompanyId(companyID());
		return forfie;
	}
	
	/**
	 * updateTableForm方法中添加表单字段表信息
	 * @param tabEnt
	 * @param ftn
	 * @param fromid
	 * @param sort
	 * @param j
	 */
	private FormFields updateAddForField(final TableEntity tabEnt, final FormTable ftn, final Long fromid, final int sort , final int numj){
		final FormFields forfie = new FormFields();
		forfie.setId(SequenceUtil.getTableId("F_FORMFIELDS"));
		forfie.setCreateID(userId());
		forfie.setCompanyId(companyID());
		forfie.setFormId(fromid);
		forfie.setTableId(ftn.getTableId());
		forfie.setTableName(ftn.getTableName());
		forfie.setTableMemo(ftn.getTableMemo());
		forfie.setFieldId(tabEnt.getId());
		forfie.setFieldName(tabEnt.getName());
		forfie.setTitle(tabEnt.getTitle());
		forfie.setInputDefaultValue(tabEnt.getDefaultValue());
		forfie.setSort(sort+1+numj);
		forfie.setFieldFlag(tabEnt.getFlag());
		forfie.setSourceMode(2);
		return forfie;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("redirect:/formTable/selectAllForm.hbc");
		String ids = request.getParameter("del");
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			for(int i=0; i<strId.length; i++){
				final FormName forNam = formTableService.selectIDForm(strId[i],companyID());
				forNam.setIsEnabled(HBCSoftConstant.INT_FALSE);
				formTableService.updateFormName(forNam);
				final Resource resource = resourceService.queryForAndRes(forNam.getCompanyId(),forNam.getActionUrl());
				resourceService.deletebyIds(resource);
			}
		}
		return mav;
	}
	
	/**
	 * 判断数据库中是否有与录入的formType重复的数据
	 * @param formType
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/isFormTypeRepeat", method = RequestMethod.POST)
	public void isFormTypeRepeat(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException {
		final String formType = this.formTypeInfo(request);
		final JSONObject jsonObject = new JSONObject();
		boolean isExist=true;
		final FormName formName = formTableService.isTypeRepeat(formType,companyID());
		if(formName.getFormType().isEmpty()){
			isExist = false;
		}else if(formName.getFormType().equals(formType)) {
			isExist = true;
		}
		jsonObject.put("isExist",isExist);
		try {
			final PrintWriter write = response.getWriter();
			write.print(jsonObject.toString());
		} catch (IOException e) {
			this.getLogger().info(e);
		}
	}
	
	/**
	 * 表单字段上下移动功能
	 * @param request
	 * @param status
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/moveChangeForm")
	public ModelAndView moveUpForm(final HttpServletRequest request,final int status)throws HbcsoftException{
		ModelAndView mav = new ModelAndView();
		final String [] fieldName = request.getParameterValues("fieldName");
		final String [] tableName = request.getParameterValues("tableName");
		final String [] tableId = request.getParameterValues("tableId");
		final String [] formid = request.getParameterValues(FORMID); 
		final String [] fieldId = request.getParameterValues("fieldId");
		final String [] title = request.getParameterValues("title");
		final String [] inputIsDisplay = request.getParameterValues("inputIsDisplay");
		final String [] inputType = request.getParameterValues("inputType");//int
		final String [] sourceMode = request.getParameterValues("sourceMode");
		final String [] sourceContent = request.getParameterValues("sourceContent");
		final String [] queryisConditions = request.getParameterValues("queryisConditions");//int
		final String [] queryisColumn = request.getParameterValues("queryisColumn");//int
		final String [] isShowColumn = request.getParameterValues("isShowColumn");//int
		final String [] isModify = request.getParameterValues("isModify");
		final String [] sort = request.getParameterValues("sort");
		final String [] inputDefaultValue = request.getParameterValues("inputDefaultValue");
		final String [] queryisRequired = request.getParameterValues("queryisRequired");
		final String [] isRequired = request.getParameterValues("isRequired");	
		final String [] number = request.getParameterValues("number");
		final String [] decimalDigits = request.getParameterValues("decimalDigits");
		final String [] isNull = request.getParameterValues("isNull");
		final List<FormFields> list = new ArrayList<FormFields>();
		List<FormFields> lstforfie = new ArrayList<FormFields>();
		if(fieldId != null && fieldId.length > 0){
			for(int i=0; i<fieldId.length; i++){
				final String[] stringff = {formid[i],fieldId[i]};
				final int[] intsff = {HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2};
				final String[] forfies = {fieldName[i],tableName[i],tableId[i],formid[i],fieldId[i],title[i],inputIsDisplay[i],inputType[i],sourceMode[i],sourceContent[i],queryisConditions[i],queryisColumn[i],isShowColumn[i],isModify[i],sort[i],inputDefaultValue[i],queryisRequired[i],isRequired[i],number[i],decimalDigits[i],isNull[i]};
				final FormFields forfie = moveupforfie(forfies ,intsff,stringff);
				list.add(forfie);
			}
		}
		formTableService.saveFormFields(list);
		if(status == NUM1){
			mav = new ModelAndView("form/formField_update");
		}else{
			mav = new ModelAndView("form/formField_add");
		}
		lstforfie = formTableService.selectIDFormField(HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2,formid[0],companyID());
		mav.getModelMap().put(FORMID, formid[0]);
		mav.getModelMap().put(LSTFIE, lstforfie);
		return mav;
	}
	
	/**
	 * 移动保存formfields
	 * @param formfields
	 * @param intsff
	 * @param stringff
	 * @return
	 */
	private FormFields moveupforfie(final String[] formfields,final int[] intsff,final String[] stringff){
		FormFields forfie = null;
		try {
			forfie = formTableService.selectFormFields(intsff,stringff,companyID());
			forfie.setFieldName(formfields[0]);
			forfie.setTableName(formfields[1]);
			forfie.setTableId(Long.valueOf(formfields[2]));
			forfie.setFormId(Long.valueOf(formfields[3]));
			forfie.setFieldId(Long.valueOf(formfields[4]));
			forfie.setTitle(formfields[5]);
			forfie.setInputIsDisplay(Integer.valueOf("".equals(formfields[6]) ? "0" : formfields[6]));
			forfie.setInputType(Integer.valueOf(formfields[7]));
			if("".equals(formfields[8])){
				forfie.setSourceMode(2);
			}else{
				forfie.setSourceMode(Integer.valueOf(formfields[8]));
				forfie.setSourceContent(formfields[9]);
			}
			forfie.setQueryisConditions(Integer.valueOf("".equals(formfields[10]) ? "0" : formfields[10]));
			forfie.setQueryisColumn(Integer.valueOf("".equals(formfields[11]) ? "0" : formfields[11]));
			forfie.setIsShowColumn(Integer.valueOf("".equals(formfields[12]) ? "0" : formfields[12]));
			forfie.setIsModify(Integer.valueOf("".equals(formfields[13]) ?"0" : formfields[13]));
			forfie.setSort(Integer.valueOf(formfields[14]));
			forfie.setInputDefaultValue(formfields[15]);
			forfie.setQueryisRequired(Integer.valueOf("".equals(formfields[16]) ? "0" : formfields[16]));
			forfie.setIsRequired(Integer.valueOf("".equals(formfields[17]) ? "0" : formfields[17]));
			forfie.setNumber(Integer.valueOf("".equals(formfields[18]) ? "0" : formfields[18]));
			forfie.setDecimalDigits(Integer.valueOf("".equals(formfields[19]) ? "0" : formfields[19]));
			forfie.setIsNull(Integer.valueOf("".equals(formfields[20]) ? "0" : formfields[20]));
			forfie.setCreateID(userId());
			forfie.setCompanyId(companyID());
		} catch (HbcsoftException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
		}
		return forfie;
	}
	
	/**
	 * 查看表单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewTable", method = RequestMethod.POST)
	public ModelAndView viewTable(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		this.getLogger().info("=======viewTable=====start===");
		final ModelAndView mav = new ModelAndView("form/form_view");
		final String id = request.getParameter("id");
		FormName forNam = null;
		List<FormTable> fortab = null;
		List<TableNameClass> lstft = null;
		try {
			forNam = formTableService.selectIDForm(id,companyID());
			fortab = formTableService.selectFormTable(id, companyID());
			lstft = createTableService.queryAll(null, null);
			final Map mapforfie = new ConcurrentHashMap();
			for(final FormTable ff : fortab){
				mapforfie.put(ff.getTableId(), ff.getTableId());
			}
			for(int i=lstft.size()-1;i>-1;i--){
				if(lstft.get(i).getId() == mapforfie.get(lstft.get(i).getId()) || 
						lstft.get(i).getId().equals(mapforfie.get(lstft.get(i).getId()))){
					lstft.remove(i);
				}
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		mav.getModelMap().put("forTab", forNam);
		mav.getModelMap().put("forfie", fortab);
		mav.getModelMap().put("lstft", lstft);
		this.getLogger().info("=======viewTable=====end===");
		return mav;
	}
	
	/**
	 * 查看表单字段信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/showFormField")
	public ModelAndView showFormField(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		this.getLogger().info("=======addFormName=====start===");
		final ModelAndView mav = new ModelAndView("form/formField_view");
		final String oldFormId = request.getParameter("tabId");//表单id
		final List<FormFields> lstforfie = formTableService.queryFormFieShow(Long.valueOf(oldFormId), companyID(), HBCSoftConstant.FORM_INPUT_TYPE_0,HBCSoftConstant.FORM_INPUT_TYPE_2);
		mav.getModelMap().put(FORMID, oldFormId);
		mav.getModelMap().put(LSTFIE, lstforfie);
		this.getLogger().info("=======addFormName=====end===");
		return mav;
	}
}
