package com.hbcsoft.InterManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.InterManage.service.InterManageService;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.table.controller.CreateDBtableController;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.util.SequenceUtil;

@Controller
@RequestMapping("/createInterManage")
public class InterManageController extends BaseController<InterManageController>{

	@Autowired
	private InterManageService interManageService;
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
	 * 接口管理主表类名名查询：列表页面
	 * @return
	 */
	//TODO:调用zdy打包的查询方法方法
	@RequestMapping(value = "/tableList", method = RequestMethod.GET)
	public ModelAndView tableList(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("interTable/primary-list");
		final String tableName = request.getParameter("className");
		final String memo = request.getParameter("tmemo");
		
		final List<PrimaryList> allTable = interManageService.queryAll(tableName, memo);
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
		final List<PrimaryList> list = interManageService.queryAllTable(tableName, memo, startRow, pageSize);
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", tableName);
		mav.getModelMap().put("searchMemo", memo);
		mav.getModelMap().put(ComConstant.mavList, list);
		return mav;
	}
	/**
	 * 跳转到新增类页面：添加页面
	 * @return
	 */
	@RequestMapping(value = "/primaryList")
	public ModelAndView addTableName() throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("interTable/primary-add");
		final List<PrimaryList> list = interManageService.queryAllTable("", "");
		mav.getModelMap().put(ComConstant.mavList, list);
		return mav;
	}
	
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
		PrimaryList primaryList = new PrimaryList();
		List<Sublist> list = new ArrayList<Sublist>();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		//主表信息
		primaryList = this.getAddPl(request, sessionInfo);
		
		final Long id = SequenceUtil.getTableId("t_tablename");
		primaryList.setId(id);
		interManageService.savePrimaryList(primaryList);
		//子表信息
		list = this.getAddSl(request, sessionInfo, primaryList);
		interManageService.saveEntity(list);
		final ModelAndView mav = new ModelAndView("redirect:/createInterManage/tableList.hbc");
		
		return mav;//重定向到列表页面
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
		final ModelAndView mav;
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("false"))
			mav = new ModelAndView("interTable/primary-edit");
		else
			mav = new ModelAndView("interTable/primary-edit2");
		PrimaryList primaryList = new PrimaryList();
		List<Sublist> list = new ArrayList<Sublist>();
		//主表信息
		final String id = request.getParameter("id");
		primaryList = interManageService.primaryNm(id);
		mav.getModelMap().put("table", primaryList);
		//子表信息
		list = interManageService.querySublist(id);
		mav.getModelMap().put("listEntity", list);
		
		final List<PrimaryList> listTable = interManageService.queryAllTable("", "");
		mav.getModelMap().put(ComConstant.mavList, listTable);
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
		final ModelAndView mav = new ModelAndView("redirect:/createInterManage/tableList.hbc");
		PrimaryList pl = new PrimaryList();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		List<Sublist> list = new ArrayList<Sublist>();
		//主表信息
		pl = this.getTable(request, sessionInfo);
		
		interManageService.updateTable(pl);
		//子表信息
		list = this.getList(request, sessionInfo, pl);
		
		interManageService.updateEntity(list);
		
		return mav;
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
		final ModelAndView mav = new ModelAndView("redirect:/createInterManage/tableList.hbc");
		List<Sublist> list = new ArrayList<Sublist>();
		String ids = request.getParameter("del");
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			PrimaryList primaryList = new PrimaryList();
			for(int i=0; i<strId.length; i++){
				primaryList = interManageService.primaryNm(strId[i]);
				primaryList.setFlag(1);
				interManageService.deleteTable(primaryList);
				list = interManageService.queryListEntity(strId[i]);
				interManageService.deleteEntity(list);
			}
		}
		return mav;
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
		final ModelAndView mav = new ModelAndView("forward:/createInterManage/editTable.hbc?id="+chuanzhi);
		final String rowId = request.getParameter("rowid");
		interManageService.delRow(rowId);
		return mav;
	}
	/**
	 * 校验数据库类名不能重复存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	//TODO：校验数据库类名
	@ResponseBody
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	public String checkName(String tableName) throws UnsupportedEncodingException{
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		tableName = URLDecoder.decode(tableName, "UTF-8");
//		final String companyHk = sessionInfo.getCompany().getCompanyNameHk();
//		final String tName = companyHk+"_"+tableName;
		String json = "0";//不存在
		try{
			final List<PrimaryList> list = interManageService.queryAllTable("", "");
			for(final PrimaryList pl : list){
				if(pl.getClassName().equalsIgnoreCase(tableName)){
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
	public Object pageList(final String searchTable, final String searchMemo,final int currentPage)throws HbcsoftException{ 
		try{
			final List<PrimaryList> allTable = interManageService.queryAll(searchTable, searchMemo);
			totalNum = this.getSize(allTable);
			int pageTimes;
			if(totalNum%pageSize == 0){
				pageTimes = totalNum/pageSize;
			}else{
				pageTimes = totalNum/pageSize + 1;
			}
	
			final int startRow = (currentPage-1) * pageSize;
			final List<PrimaryList> list = interManageService.queryAllTable(searchTable, searchMemo, startRow, pageSize);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("pageTimes", pageTimes);
			jsonObject.put("totalNum", totalNum);
			jsonObject.put("list", list);
			
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return jsonObject;
	}
	
	private int getSize(final List<?> list){
		return list.size();
	}
	
	/**
	 * PMD:添加方法中获取主表信息
	 */
	private PrimaryList getAddPl(final HttpServletRequest request,final SessionInfo sessionInfo){
		final PrimaryList tn = new PrimaryList();
		final String className = request.getParameter("className");
		char [] temp = className.toCharArray();
		temp[0] = Character.toUpperCase(temp[0]);
		String cName = String.valueOf(temp);
		final String methodName = request.getParameter("methodName");
		String memo = "";
		if(request.getParameter(ComConstant.paraMemo) == null){
			memo = "";
		}else{
			memo = request.getParameter(ComConstant.paraMemo);
		}
		tn.setMemo(memo);
		tn.setFlag(0);
		tn.setClassName(cName);
		tn.setMethodName(methodName);
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
	private List<Sublist> getAddSl(final HttpServletRequest request,
			final SessionInfo sessionInfo,final PrimaryList primaryList){
		final List<Sublist> list = new ArrayList<Sublist>();
		final String [] fieldName = request.getParameterValues("fieldName");
		final String [] fieldType = request.getParameterValues("fieldType");
		final String [] fieldValue = request.getParameterValues("fieldValue");
		if(fieldName != null &&fieldName.length > 0){
			for(int i=0; i<fieldName.length; i++){
				final Sublist sl = new Sublist();
				

				final Long entityId = SequenceUtil.getTableId("t_tableentity");
				sl.setId(entityId);
				
				sl.setFieldName(fieldName[i]);;
				sl.setFieldType(fieldType[i]);;
				sl.setFieldValue(fieldValue[i]);
				sl.setMainId(primaryList.getId());
				sl.setFlag(0);
				sl.setCompanyId(sessionInfo.getCompany().getId());
				sl.setCreateID(sessionInfo.getUser().getId());
				sl.setUpdateID(sessionInfo.getUser().getId());
				
				list.add(sl);
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
	private PrimaryList getTable(final HttpServletRequest request, final SessionInfo sessionInfo){
		final PrimaryList pl = new PrimaryList();
		final Long id = Long.valueOf(request.getParameter("id"));
		final String className = request.getParameter("className");
		final String methodName = request.getParameter("methodName");
		String memo = "";
		/*String mainName = "";
		String mainId = "";
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
		}*/
		if(request.getParameter("memo") == null){
			memo = "";
		}else{
			memo = request.getParameter("memo");
		}
		pl.setClassName(className);
		pl.setMethodName(methodName);
		pl.setMemo(memo);
		pl.setId(id);
		pl.setFlag(0);
		pl.setCompanyId(sessionInfo.getCompany().getId());
		pl.setUpdateID(sessionInfo.getUser().getId());
		pl.setUpdateTime(new Date());
		
		return pl;
	}
	
	/**
	 * 提取子表信息
	 * @param request
	 * @param sessionInfo
	 * @param table
	 * @return
	 */
	private List<Sublist> getList(final HttpServletRequest request, final SessionInfo sessionInfo,
			final PrimaryList primaryList){
		final List<Sublist> list = new ArrayList<Sublist>();
		final String [] entityId = request.getParameterValues("entityId");
		final String [] fieldName = request.getParameterValues("fieldName");
		final String [] fieldType = request.getParameterValues("fieldType");
		final String [] fieldValue = request.getParameterValues("fieldValue");
		if (entityId != null){
			final Date date = new Date();
			if(fieldName != null && fieldName.length > 0){
				for(int i=0; i<fieldName.length; i++){
					final Sublist sl = new Sublist();
					
					sl.setId(Long.valueOf("".equals(entityId[i]) ? "0" : entityId[i]));
					sl.setFieldName(fieldName[i]);
					sl.setFieldType(fieldType[i]);
					sl.setFieldValue(fieldValue[i]);
					sl.setMainId(primaryList.getId());
					sl.setFlag(0);
					
					sl.setCompanyId(sessionInfo.getCompany().getId());
					sl.setUpdateID(sessionInfo.getUser().getId());
					sl.setUpdateTime(date);
					
					list.add(sl);
				}
			}
		
		}else{
			final Date date = new Date();
			if(fieldName != null && fieldName.length > 0){
				for(int i=0; i<fieldName.length; i++){
					final Sublist sl = new Sublist();
					
					sl.setId(Long.valueOf("0"));
					sl.setFieldName(fieldName[i]);
					sl.setFieldType(fieldType[i]);
					sl.setFieldValue(fieldValue[i]);
					sl.setMainId(primaryList.getId());
					sl.setFlag(0);
					
					sl.setCompanyId(sessionInfo.getCompany().getId());
					sl.setUpdateID(sessionInfo.getUser().getId());
					sl.setUpdateTime(date);
					
					list.add(sl);
				}
			}
		}
		return list;
	}
	
	public InterManageService getInterManageService() {
		return interManageService;
	}
	public void setInterManageService(InterManageService interManageService) {
		this.interManageService = interManageService;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	
	
}
