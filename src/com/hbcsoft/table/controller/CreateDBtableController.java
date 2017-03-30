package com.hbcsoft.table.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormTable;
import com.hbcsoft.form.service.FormTableService;
import com.hbcsoft.report.service.ReportConfigService;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.table.service.CreateDBtable2Service;
import com.hbcsoft.table.service.CreateDBtableService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.util.SequenceUtil;
/**
 * 创建数据库表的控制层
 * @author liang
 *
 */
@Controller
@RequestMapping(value = "/createTable")
public class CreateDBtableController extends BaseController<CreateDBtableController>  {

	/**
	 * 表名称对象
	 */
	private TableNameClass tableName;
	/**
	 * 字段集
	 */
	private List<TableEntity> listEntity;
	/**
	 * 字段表中的id
	 */
	private String entityId;
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient CreateDBtableService createDBtableService;
	/**
	 * 注入Service2方法
	 */
	@Autowired
	private transient CreateDBtable2Service createDBtable2Service;
	/**
	 * 引用formTableService
	 */
	@Autowired
	private transient FormTableService formTableService;
	/**
	 * 引用reportConfigService
	 */
	@Autowired
	private transient ReportConfigService reportConfigService;
	/**
	 * 每页显示的条数
	 */
	private int pageSize = 10;//每页显示的条数
	/**
	 * 总记录数
	 */
	private int totalNum;//总记录数
	/**
	 * 第几页
	 */
	private int currentPage;//第几页
	/**
	 * pageList方法中变量
	 */
	private final transient JSONObject jsonObject = new JSONObject();
	/**
	 * 
	 */
	private final static String t_tableentity = "t_tableentity";
	/**
	 * 数据库表名查询：列表页面
	 * @return
	 */
//	@RequestMapping(value = "/tableNameList")
//	public ModelAndView tableNameList() throws HbcsoftException{
//		ModelAndView mav = new ModelAndView("table/tableName-list");
//		List<TableName> list = createDBtableService.queryAll("","");
//		mav.getModelMap().put("list", list);
//		return mav;
//	}
	//TODO:调用zdy打包的查询方法方法
	@RequestMapping(value = "/tableList", method = RequestMethod.GET)
	public ModelAndView tableList(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("table/tableName-list");
		final String tableName = request.getParameter("ttable");
		final String memo = request.getParameter("tmemo");
		String message = request.getParameter("message");
		try {
			if(message!=null && !"".equals(message)){
				message=java.net.URLDecoder.decode(message,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		final List<TableNameClass> allTable = createDBtableService.queryAll(tableName, memo);
		totalNum = this.getSize(allTable);
		int pageTimes;//总页数
		if(totalNum%pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize + 1;
		}
		final String cu = request.getParameter(ComConstant.paraCurrentPage)==null ? "" : request.getParameter(ComConstant.paraCurrentPage);
		if("".equals(cu)){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(cu);
		}
		final int startRow = (currentPage-1) * pageSize;
		final List<TableNameClass> list = createDBtableService.queryAllTable(tableName, memo, startRow, pageSize);
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", tableName);
		mav.getModelMap().put("searchMemo", memo);
		mav.getModelMap().put(ComConstant.mavList, list);
		mav.getModelMap().put("message", message);
		return mav;
	}
	/**
	 * 跳转到新增数据表页面：添加页面
	 * @return
	 */
	@RequestMapping(value = "/tableName")
	public ModelAndView addTableName() throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("table/tableName-add");
		final List<TableNameClass> list = createDBtableService.queryAllTable("", "");
		mav.getModelMap().put(ComConstant.mavList, list);
		return mav;
	}
	/**
	 * 新增表后，跳转到列表页面
	 * @param request
	 * @param response
	 * @return
	 */
//	@RequestMapping(value = "/createTable", method = RequestMethod.POST)
//	public ModelAndView createTable(HttpServletRequest request, 
	//HttpServletResponse response) throws HbcsoftException{
//		TableName table = new TableName();
//		List<TableEntity> list = new ArrayList<TableEntity>();
//		//主表信息
//		String tableName = request.getParameter("tableName");
//		String isMainTable = request.getParameter("isMainTable");
//		String mainName = request.getParameter("mainName") == null ?
	//"" : request.getParameter("mainName");
//		String mainId = request.getParameter("mainId") == null ?
	//"0" : request.getParameter("mainId");
//		String memo = request.getParameter("memo") == null ? 
	//"" : request.getParameter("memo");
//		table.setTableName(tableName);
//		table.setIsMainTable(isMainTable);
//		table.setMainId(Long.valueOf(mainId=="" ? "0" : mainId));
//		table.setMainName(mainName);
//		table.setMemo(memo);
//		Long id = createDBtableService.saveTable(table);
//		//子表信息
//		String [] name = request.getParameterValues("name");
//		String [] title = request.getParameterValues("title");
//		String [] type = request.getParameterValues("type");
//		String [] number = request.getParameterValues("number");
//		String [] decimalDigits = request.getParameterValues("decimalDigits");
//		String [] isNull = request.getParameterValues("isNull");//int
//		String [] isEdit = request.getParameterValues("isEdit");//int
//		String [] isDisplay = request.getParameterValues("isDisplay");//int
//		String [] defaultValue = request.getParameterValues("defaultValue");
//		String [] changeFlag = request.getParameterValues("changeFlag");
//		if(name != null && name.length > 0){
//			for(int i=0; i<name.length; i++){
//				TableEntity te = new TableEntity();
//				te.setName(name[i]);
//				te.setTitle(title[i]);
//				te.setType(type[i]);
//				te.setNumber(Integer.valueOf(number[i]=="" ? "0" : number[i]));
//				te.setDecimalDigits(Integer.valueOf(decimalDigits[i]==""?
	//"0" : decimalDigits[i]));
//				te.setIsNull(Integer.valueOf(isNull[i]));
//				te.setIsEdit(Integer.valueOf(isEdit[i]));
//				te.setIsDisplay(Integer.valueOf(isDisplay[i]));
//				te.setDefaultValue(defaultValue[i]);
//				te.setChangeFlag(Integer.valueOf(changeFlag[i]));
//				te.setTableName(table);
//				te.setMainId(id);
//				list.add(te);
//			}
//		}
//		createDBtableService.saveTableEntity(list);
//		
//		ModelAndView mav = new ModelAndView("redirect:/createTable/tableList.hbc");
//		
//		return mav;//重定向到列表页面
//	}

	/**
	 * 新增表后，跳转到列表页面
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包的保存方法
	@RequestMapping(value = "/addTable", method = RequestMethod.POST)
	public ModelAndView addTable(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		TableNameClass table = new TableNameClass();
		List<TableEntity> list = new ArrayList<TableEntity>();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		//主表信息
		table = this.getAddTb(request, sessionInfo);
		
//		String id = HbcsoftUtil.uuid();
		final Long id = SequenceUtil.getTableId("t_tablename");
		table.setId(id);
		createDBtableService.saveTableName(table);
		//子表信息
		list = this.getAddTn(request, sessionInfo, table);
		/****************新增3个字段start************************/
		List<TableEntity> list3 = new ArrayList<TableEntity>();
		list3 = setGUDingZiDuan(table, sessionInfo);
		list.addAll(list3);
		/*****************新增3个字段end***********************/
		createDBtableService.saveEntity(list);
		/****************移除3个字段start************************/
		for(int i=list.size()-1; i>=0; i--){
			String nameFiled = "";
			if(list.get(i).getName() != null){
				nameFiled = list.get(i).getName().toUpperCase();
			}
			if(ComConstant.ID.equals(nameFiled) || ComConstant.ZID.equals(nameFiled) || ComConstant.COMPANYID.equals(nameFiled)){
				list.remove(i);
			}
		}
		/****************移除3个字段end************************/
		createDBtable2Service.operateSql(0, HBCSoftConstant.DBTYPE_MYSQL, table, list);//创建表
		final ModelAndView mav = new ModelAndView("redirect:/createTable/tableList.hbc");
		
		return mav;//重定向到列表页面
	}
	/**
	 * PMD:添加方法中获取主表信息
	 */
	private TableNameClass getAddTb(final HttpServletRequest request,final SessionInfo sessionInfo){
		final TableNameClass tn = new TableNameClass();
		final String tName = request.getParameter("tableName");
		final String tableName = tName.trim().toUpperCase();
		final String isMainTable = request.getParameter("isMainTable");
		String mainName = "";
		String mainId = "";
		String memo = "";
		if(request.getParameter(ComConstant.paraMainName) == null){
			mainName = "";
		}else{
			mainName = request.getParameter(ComConstant.paraMainName);
		}
		if(request.getParameter(ComConstant.paraMainId) == null ){
			mainId = "0";
		}else if("".equals(request.getParameter(ComConstant.paraMainId))){
			mainId = "0";
		}else{
			mainId = request.getParameter(ComConstant.paraMainId);
		}
		if(request.getParameter(ComConstant.paraMemo) == null){
			memo = "";
		}else{
			memo = request.getParameter(ComConstant.paraMemo);
		}
		final String jianCheng = sessionInfo.getCompany().getCompanyNameHk().trim().toUpperCase();
		tn.setTableName(jianCheng+"_"+tableName);
		tn.setIsMainTable(isMainTable);
		tn.setMainId(Long.valueOf(mainId));
		tn.setMainName(mainName);
		tn.setMemo(memo);
		tn.setFlag(0);
		
		tn.setCompanyId(sessionInfo.getCompany().getId());
		tn.setCreateID(sessionInfo.getUser().getId());
		tn.setUpdateID(sessionInfo.getUser().getId());
		return tn;
	}
	/**
	 * PMD:添加方法中获取子表信息
	 * @param request
	 * @param sessionInfo
	 * @param table
	 * @return
	 */
	private List<TableEntity> getAddTn(final HttpServletRequest request,
			final SessionInfo sessionInfo,final TableNameClass table){
		final List<TableEntity> list = new ArrayList<TableEntity>();
		final String [] name = request.getParameterValues("name");
		final String [] title = request.getParameterValues("title");
		final String [] type = request.getParameterValues("type");
		final String [] number = request.getParameterValues("number");
		final String [] decimalDigits = request.getParameterValues("decimalDigits");
		final String [] isNull = request.getParameterValues("isNull");//int
		final String [] isEdit = request.getParameterValues("isEdit");//int
		final String [] isDisplay = request.getParameterValues("isDisplay");//int
		final String [] defaultValue = request.getParameterValues("defaultValue");
		final String [] changeFlag = request.getParameterValues("changeFlag");
		if(name != null && name.length > 0){
			for(int i=0; i<name.length; i++){
				final TableEntity te = this.getNewTe();
				
//				String entityId = HbcsoftUtil.uuid();
				final Long entityId = SequenceUtil.getTableId(t_tableentity);
				te.setId(entityId);
				
				te.setName(name[i]);
				te.setTitle(title[i]);
				te.setType(type[i]);
				te.setNumber(Integer.valueOf("".equals(number[i]) ? "0" : number[i]));
				te.setDecimalDigits(Integer.valueOf("".equals(decimalDigits[i])?"0" : decimalDigits[i]));
				te.setIsNull(Integer.valueOf(isNull[i]));
				te.setIsEdit(Integer.valueOf(isEdit[i]));
				te.setIsDisplay(Integer.valueOf(isDisplay[i]));
				te.setDefaultValue(defaultValue[i]);
				te.setChangeFlag(Integer.valueOf(changeFlag[i]));
				te.setTableName(table);
				te.setMainId(table.getId());
				te.setFlag(0);
				
				te.setCompanyId(sessionInfo.getCompany().getId());
				te.setCreateID(sessionInfo.getUser().getId());
				te.setUpdateID(sessionInfo.getUser().getId());
				
				list.add(te);
			}
		}
		return list;
	}
	/**
	 * 避免在循环中使用new
	 * @return
	 */
	private TableEntity getNewTe(){
		return new TableEntity();
	}
	/**
	 * 添加时，默认增加三个字段id，zid，companyid，不允许显示、修改
	 * @param tableName
	 * @return
	 */
	public List<TableEntity> setGUDingZiDuan(final TableNameClass tn, final SessionInfo sessionInfo){
		final List<TableEntity> list = new ArrayList<TableEntity>();
		final TableEntity te1 = new TableEntity();
		final TableEntity te2 = new TableEntity();
		final TableEntity te3 = new TableEntity();
		final TableEntity te4 = new TableEntity();
		
		te1.setId(SequenceUtil.getTableId(t_tableentity));
		te1.setName(ComConstant.ID);
		te1.setTitle("主键");
		te1.setType(ComConstant.COLUMN_TYPE_BIGINT);
		te1.setNumber(20);
		te1.setDecimalDigits(0);
		te1.setIsNull(0);
		te1.setIsEdit(0);
		te1.setIsDisplay(1);
		te1.setDefaultValue("");
		te1.setChangeFlag(0);
		te1.setTableName(tn);
		te1.setMainId(tn.getId());
		te1.setFlag(2);
		te1.setCompanyId(sessionInfo.getCompany().getId());
		
		te2.setId(SequenceUtil.getTableId(t_tableentity));
		te2.setName(ComConstant.ZID);
		te2.setTitle("表单id");
		te2.setType(ComConstant.COLUMN_TYPE_BIGINT);
		te2.setNumber(20);
		te2.setDecimalDigits(0);
		te2.setIsNull(0);
		te2.setIsEdit(0);
		te2.setIsDisplay(1);
		te2.setDefaultValue("");
		te2.setChangeFlag(0);
		te2.setTableName(tn);
		te2.setMainId(tn.getId());
		te2.setFlag(2);
		te2.setCompanyId(sessionInfo.getCompany().getId());
		
		te3.setId(SequenceUtil.getTableId(t_tableentity));
		te3.setName(ComConstant.COMPANYID);
		te3.setTitle("公司id");
		te3.setType(ComConstant.COLUMN_TYPE_BIGINT);
		te3.setNumber(20);
		te3.setDecimalDigits(0);
		te3.setIsNull(0);
		te3.setIsEdit(0);
		te3.setIsDisplay(1);
		te3.setDefaultValue("");
		te3.setChangeFlag(0);
		te3.setTableName(tn);
		te3.setMainId(tn.getId());
		te3.setFlag(2);
		te3.setCompanyId(sessionInfo.getCompany().getId());
		
		te4.setId(SequenceUtil.getTableId(t_tableentity));
		te4.setName(ComConstant.FORMTYPE);
		te4.setTitle("表单类型");
		te4.setType(ComConstant.COLUMN_TYPE_TEXT);
		te4.setNumber(100);
		te4.setDecimalDigits(0);
		te4.setIsNull(0);
		te4.setIsEdit(0);
		te4.setIsDisplay(1);
		te4.setDefaultValue("");
		te4.setChangeFlag(0);
		te4.setTableName(tn);
		te4.setMainId(tn.getId());
		te4.setFlag(2);
		te4.setCompanyId(sessionInfo.getCompany().getId());
		
		list.add(te1);
		list.add(te2);
		list.add(te3);
		list.add(te4);
		return list;
	}
	/**
	 * 进入编辑页面
	 * @param request
	 * @param response
	 * @return
	 */
	//TODO:调用zdy打包方法进入编辑页面
	@RequestMapping(value = "/editTable", method = RequestMethod.POST)
	public ModelAndView editTable(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("table/tableName-edit");
		TableNameClass table = new TableNameClass();
		List<TableEntity> list = new ArrayList<TableEntity>();
		//主表信息
		final String id = request.getParameter("id");
//		table = createDBtableService.table(id);
		table = createDBtableService.tableNm(id);
		mav.getModelMap().put("table", table);
		//子表信息
//		list = createDBtableService.queryAllEntity(id);
		list = createDBtable2Service.queryListEntity(id);
		mav.getModelMap().put("listEntity", list);
		
		final List<TableNameClass> listTable = createDBtableService.queryAllTable("", "");
		
		String message = request.getParameter("message");
		try {
			if(message!=null && !"".equals(message)){
				message=java.net.URLDecoder.decode(message,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		
		mav.getModelMap().put(ComConstant.mavList, listTable);
		mav.getModelMap().put("message", message);
		return mav;
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包公用方法编辑
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		//final ModelAndView mav = new ModelAndView("redirect:/createTable/tableList.hbc");
		TableNameClass table = new TableNameClass();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		List<TableEntity> list = new ArrayList<TableEntity>();
		//主表信息
		table = this.getTable(request, sessionInfo);
		
		createDBtable2Service.updateTable(table);
		formTableService.updateFormTable(table);
		//子表信息
		list = this.getList(request, sessionInfo, table);
		String message = "";
		if(isCan(list, table.getTableName())){
			createDBtable2Service.updateEntity(list);
			createDBtable2Service.operateSql(1, HBCSoftConstant.DBTYPE_MYSQL, table, list);//创建表
		}else{
			message = "表中存在数据，不允许修改！";
		}
		try {
			message = URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		final ModelAndView mav = new ModelAndView("forward:/createTable/editTable.hbc?message="+message);
		return mav;
	}
	/**
	 * 提取子表信息
	 * @param request
	 * @param sessionInfo
	 * @param table
	 * @return
	 */
	private List<TableEntity> getList(final HttpServletRequest request, final SessionInfo sessionInfo,
			final TableNameClass table){
		final List<TableEntity> list = new ArrayList<TableEntity>();
		final String [] entityId = request.getParameterValues("entityId");
		final String [] name = request.getParameterValues("name");
		final String [] title = request.getParameterValues("title");
		final String [] type = request.getParameterValues("type");
		final String [] number = request.getParameterValues("number");
		final String [] decimalDigits = request.getParameterValues("decimalDigits");
		final String [] isNull = request.getParameterValues("isNull");//int
		final String [] isEdit = request.getParameterValues("isEdit");//int
		final String [] isDisplay = request.getParameterValues("isDisplay");//int
		final String [] defaultValue = request.getParameterValues("defaultValue");
		final String [] changeFlag = request.getParameterValues("changeFlag");
		
		final Date date = new Date();
		if(name != null && name.length > 0){
			for(int i=0; i<name.length; i++){
				final TableEntity te = this.getNewTe();
				
				te.setId(Long.valueOf("".equals(entityId[i]) ? "0" : entityId[i]));
				te.setName(name[i]);
				te.setTitle(title[i]);
				te.setType(type[i]);
				te.setNumber(Integer.valueOf("".equals(number[i]) ? "0" : number[i]));
				te.setDecimalDigits(Integer.valueOf("".equals(decimalDigits[i])?"0" : decimalDigits[i]));
				te.setIsNull(Integer.valueOf(isNull[i]));
				te.setIsEdit(Integer.valueOf(isEdit[i]));
				te.setIsDisplay(Integer.valueOf(isDisplay[i]));
				te.setDefaultValue(defaultValue[i]);
				te.setChangeFlag(Integer.valueOf(changeFlag[i]));
				te.setTableName(table);
				te.setMainId(table.getId());
				te.setFlag(0);
				
				te.setCompanyId(sessionInfo.getCompany().getId());
				te.setUpdateID(sessionInfo.getUser().getId());
				te.setUpdateTime(date);
				
				list.add(te);
			}
		}
		return list;
	}
	/**
	 * 获取主表的内容信息：处理PMD复杂度问题
	 * @param request
	 * @param sessionInfo
	 * @return
	 */
	private TableNameClass getTable(final HttpServletRequest request, final SessionInfo sessionInfo){
		final TableNameClass table = new TableNameClass();
		final Long id = Long.valueOf(request.getParameter("id"));
		final String tableName = request.getParameter("tableName");
		final String isMainTable = request.getParameter("isMainTable");
		String mainName = "";
		String mainId = "";
		String memo = "";
		if(request.getParameter("mainName") == null){
			mainName = "";
		}else{
			mainName = request.getParameter("mainName");
		}
		if(request.getParameter(ComConstant.paraMainId) == null ){
			mainId = "0";
		}else if("".equals(request.getParameter(ComConstant.paraMainId))){
			mainId = "0";
		}else{
			mainId = request.getParameter(ComConstant.paraMainId);
		}
		if(request.getParameter("memo") == null){
			memo = "";
		}else{
			memo = request.getParameter("memo");
		}
		table.setTableName(tableName);
		table.setIsMainTable(isMainTable);
		table.setMainId(Long.valueOf(mainId));
		table.setMainName(mainName);
		table.setMemo(memo);
		table.setId(id);
		table.setFlag(0);
		
		table.setCompanyId(sessionInfo.getCompany().getId());
		table.setUpdateID(sessionInfo.getUser().getId());
		table.setUpdateTime(new Date());
		
		return table;
	}
	/**
	 * 根据选中的删除数据的id判断【创建表单】模块是否存在表的调用
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	private boolean existFormTable(final String id) throws HbcsoftException{
		boolean exist = false;
		List<FormTable> list = new ArrayList<FormTable>();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final long companyId = sessionInfo.getCompany().getId();
		list = formTableService.getListFT(id, companyId);
		if(!list.isEmpty()){
			exist = true;
		}
		return exist;
	}
	/**
	 * 根据选中的删除数据的表名判断【数据库中这个表】是否存在数据
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	private boolean existData(final String tableName) throws HbcsoftException{
		boolean exist = false;
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final long companyId = sessionInfo.getCompany().getId();
		final int size = formTableService.getTableData(tableName, companyId);
		if(size>0){
			exist = true;
		}
		return exist;
	}
	/**
	 * 根据选中的删除数据的表名判断【数据库中这个表】是否在z_report_configsearch中调用
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	private boolean existReport(final String tableName) throws HbcsoftException{
		boolean exist = false;
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final long companyId = sessionInfo.getCompany().getId();
		final int size = reportConfigService.getReport(tableName, companyId);
		if(size>0){
			exist = true;
		}
		return exist;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	//TODO:调用zdy公用方法删除数据
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		List<TableEntity> list = new ArrayList<TableEntity>();
		String ids = request.getParameter("del");
		String message = "";
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			TableNameClass table = new TableNameClass();
			for(int i=0; i<strId.length; i++){
				table = createDBtableService.tableNm(strId[i]);
				if(existFormTable(strId[i]) || existData(table.getTableName()) || existReport(table.getTableName())){//数据库表中存在数据||创建表单中存在调用表||报表中存在表的使用--不可以删除
					message += "【"+table.getTableName()+"】";
				}else{//数据库表中不存在数据；创建表单中不存在调用表；报表中不存在表的使用--可以删除
					table.setFlag(1);
					createDBtable2Service.deleteTable(table);
					formTableService.updateFormTable(table);
					list = createDBtable2Service.queryListEntity(strId[i]);
					createDBtable2Service.deleteEntity(list);
					formTableService.delLstForEnt(list);
					createDBtable2Service.operateSql(2, HBCSoftConstant.DBTYPE_MYSQL, table, list);//创建表
				}
			}
		}
		try {
			message = URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		final ModelAndView mav = new ModelAndView("redirect:/createTable/tableList.hbc?message="+message);
		return mav;
	}
	/**
	 * 
	 * @param rowId
	 * @return
	 * @throws HbcsoftException 
	 */
	private boolean existFieldData(String rowId) throws HbcsoftException{
		boolean exist = false;
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final long companyId = sessionInfo.getCompany().getId();
		final int size = createDBtable2Service.getFieldData(rowId, companyId);
		if(size>0){
			exist = true;
		}
		return exist;
	}
	/**
	 * 删除子表中的某个实体字段
	 * @param request response
	 * @return
	 */
	//TODO:删除实体字段表中的某个实体字段
	@RequestMapping(value = "/delRow", method = RequestMethod.POST)
	public ModelAndView delRow(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException, InstantiationException, IllegalAccessException{
		final String chuanzhi = request.getParameter("chuanzhi");
		final String rowId = request.getParameter("rowid");
		String message = "";
		if(existFieldData(rowId)){
			message = "字段中存在数据，不允许删除！";
		}else{
			createDBtable2Service.delRow(rowId);
			formTableService.delEntField(rowId);
		}
		try {
			message = URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		final ModelAndView mav = new ModelAndView("forward:/createTable/editTable.hbc?id="+chuanzhi+"&message="+message);
		return mav;
	}
	/**
	 * 校验数据库表名不能重复存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	//TODO：校验数据库表名
	@ResponseBody
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	public String checkName(String tableName) throws UnsupportedEncodingException{
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		tableName = URLDecoder.decode(tableName, "UTF-8");
		final String companyHk = sessionInfo.getCompany().getCompanyNameHk();
		final String tName = companyHk+"_"+tableName;
		String json = "0";//不存在
		try{
			final List<TableNameClass> list = createDBtableService.queryAllTable("", "");
			for(final TableNameClass tn : list){
				if(tn.getTableName().equalsIgnoreCase(tName)){
					json = "1";//已经存在
					break;
				}
			}
		}catch(HbcsoftException e){
//			e.printStackTrace();
			this.getLogger().info(e);
		}
		return json;
	}
	/**
	 * 分页方法
	 * @param searchTable
	 * @param searchMemo
	 * @param currentPage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Object pageList(final String searchTable, final String searchMemo,final int currentPage){ 
		try{
			final List<TableNameClass> allTable = createDBtableService.queryAll(searchTable, searchMemo);
			totalNum = this.getSize(allTable);
			int pageTimes;//
			if(totalNum%pageSize == 0){
				pageTimes = totalNum/pageSize;
			}else{
				pageTimes = totalNum/pageSize + 1;
			}
	
			final int startRow = (currentPage-1) * pageSize;
			final List<TableNameClass> list = createDBtableService.queryAllTable(searchTable, searchMemo, startRow, pageSize);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("pageTimes", pageTimes);
			jsonObject.put("totalNum", totalNum);
			jsonObject.put("list", list);
			
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			this.getLogger().info(e);
		}
		return jsonObject;
	}
	/**
	 * 表中存在数据，则已经存在的字段不能修改字段属性
	 * @param list
	 * @param tName
	 * @return
	 * @throws HbcsoftException 
	 */
	private boolean isCan(final List<TableEntity> list, final String tName) throws HbcsoftException{
		boolean isCan = true;//可以进行修改
		final int size = createDBtable2Service.getListSize(list, tName);
		if(size>0){
			isCan = false;//表中存在数据，不可以修改已经存在的字段
		}
		return isCan;
	}
	/**
	 * 表名称对象
	 * @return
	 */
	public TableNameClass getTableName() {
		return tableName;
	}
	/**
	 * 表名称对象
	 * @param tableName
	 */
	public void setTableName(final TableNameClass tableName) {
		this.tableName = tableName;
	}
	/**
	 * 字段集
	 * @return
	 */
	public List<TableEntity> getListEntity() {
		return listEntity;
	}
	/**
	 * 字段集
	 * @param listEntity
	 */
	public void setListEntity(final List<TableEntity> listEntity) {
		this.listEntity = listEntity;
	}
	/**
	 * 字段表中的id
	 * @return
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * 字段表中的id
	 * @param entityId
	 */
	public void setEntityId(final String entityId) {
		this.entityId = entityId;
	}
	/**
	 * 每页显示的条数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 每页显示的条数
	 * @param pageSize
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 总记录数
	 * @return
	 */
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * 总记录数
	 * @param totalNum
	 */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 第几页
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * 第几页
	 * @param currentPage
	 */
	public void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 用来获取list的大小，避免PMD问题
	 * @param list
	 * @return
	 */
	private int getSize(final List<?> list){
		return list.size();
	}
	
	
}
