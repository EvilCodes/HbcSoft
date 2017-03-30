package com.hbcsoft.sys.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.sys.entity.OuterSubject;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.Subject;
import com.hbcsoft.sys.service.OuterDBLinkParaService;
import com.hbcsoft.sys.service.OuterSubjectService;
import com.hbcsoft.sys.service.SubjectService;
import com.hbcsoft.sys.util.HbcsoftUtil;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.util.DBUtil;
import com.hbcsoft.zdy.util.SequenceUtil;


/**
 * @author yangfeng
 * @version 2016-11
 * 科目同步Controller
 */
@Controller
@RequestMapping(value = "/matchSubject")
public class OuterSubjectController extends BaseController<OuterSubjectController>{
	
	/**
	 * 第三方数据库连接接口
	 */
	@Autowired
	private transient OuterDBLinkParaService outerDBLinkParaService;
	/**
	 * 第三方数据库科目接口
	 */
	@Autowired
	private transient OuterSubjectService outerSubjectService;
	/**
	 * 科目配置接口
	 */
	@Autowired
	private transient SubjectService subjectService;
	/**
	 * 每页显示的条数
	 */
	private transient final int pageSize = HBCSoftConstant.PAGESIZE;
	/**
	 * 总记录数
	 */
	private transient int totalNum;
	/**
	 * 第几页
	 */
	private transient int currentPage;
	/**
	 * 科目类型
	 */
	private static String paraSubjectType= "subjectType";
	/**
	 * 科目名称
	 */
	private static String paraSubjectName = "subjectName";
	/**
	 * 当前页
	 */
	private static String  paraCurrentPage = "currentPage";
	
	/**
	 * 数据库连接配置列表查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/queryAllDBLinks")
	public ModelAndView queryAllDBLinks(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView mav = new ModelAndView("sys/matchSubject/outerDBLink_list");
		final SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String strCompanyName = sessionInfo.getCompany().getCompanyName();
		List<OuterDBLinkPara> outerDBLinkList;
		try {
			outerDBLinkList = outerDBLinkParaService.findValidOuterDBPara(strCompanyId);

			mav.getModelMap().put("companyId", strCompanyId);
			mav.getModelMap().put("companyName", strCompanyName);

			mav.getModelMap().put("outerDBLinkList", outerDBLinkList);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return mav;
	}
	/**
	 * 同步科目信息  并进行列表展示
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/synchronizeSubject")
	public ModelAndView synchronizeSubject(final HttpServletRequest request, final HttpServletResponse response)
			throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("sys/matchSubject/outerSubjects_list");
		final String year = HbcsoftUtil.getInstYear();//当前年度
		
		final String dbLinkParaId = request.getParameter("dbLinkParaId");//数据库连接参数id
		final String requestType = request.getParameter("requestType");//值=match时进行同步，=query时进行查询
		final String subjectType = request.getParameter(paraSubjectType);//科目类型
		final String subjectName = request.getParameter(paraSubjectName);//科目名称
		final String isEnable = request.getParameter("isEnable");//是否启用
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		
		//1、获取数据库连接参数
		final OuterDBLinkPara  outerDBLinkPara = outerDBLinkParaService.findDbParaById(dbLinkParaId);
		
		final long companyId = outerDBLinkPara.getCompanyId();
		final String dbName = outerDBLinkPara.getDbsId();
		final int dbType = outerDBLinkPara.getDbType();
		
		if("match".equals(requestType)){
			//2、同步科目信息
			this.synchronizeSubjects(year,sessionInfo,outerDBLinkPara);
		}
		//3、列表展示
		final List<OuterSubject> subjects = outerSubjectService.queryAll(
				companyId,dbType,dbName,year,subjectType,subjectName,isEnable);
		totalNum = subjects.size();
		int pageTimes;//总页数
		if(totalNum%pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize + 1;
		}
		final String cu = request.getParameter(paraCurrentPage)==null ? "" : request.getParameter(paraCurrentPage);
		currentPage = Integer.parseInt("".equals(cu)? "1": cu);
		final int startRow = (currentPage-1) * pageSize;
		
		final List<OuterSubject> subjectsList = outerSubjectService.queryAllSubjects(
				companyId,dbType,dbName,year,subjectType,subjectName,isEnable,
					Integer.toString(startRow), Integer.toString(pageSize));
				
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("dbLinkParaId", dbLinkParaId);
		mav.getModelMap().put("subjectType", subjectType);
		mav.getModelMap().put("subjectName", subjectName);
		mav.getModelMap().put("isEnable", isEnable);
		
		mav.getModelMap().put("subjectslist", subjectsList);
	
		return mav;
	}
	/**
	 * 同步科目信息
	 * @param year
	 * @param sessionInfo
	 * @param outerDBLinkPara
	 */
	public void synchronizeSubjects(final String year,final SessionInfo sessionInfo, final OuterDBLinkPara outerDBLinkPara) {
		// TODO Auto-generated method stub
		final long companyId = outerDBLinkPara.getCompanyId();
		final String dbName = outerDBLinkPara.getDbsId();
		final int dbType = outerDBLinkPara.getDbType();
		final String dbip = outerDBLinkPara.getDbIp();
		final String username = outerDBLinkPara.getDbUser();
		final String password = outerDBLinkPara.getDbPass();
		final String port = outerDBLinkPara.getDbport();
		final String driver = outerDBLinkPara.getDbDriver();
		
		getLogger().info(dbName+"======同步科目信息开始=======");
		
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;			
	
		try {
			conn =  (Connection) DBUtil.getConnection(dbName, dbType, dbip, username, password, port, driver);
			stmt = conn.createStatement() ; 
			final String strsql = HbcsoftCache.getSqlValue("outerSubject_queryU8Subjects");
//			strsql += "AND IYEAR = '"+year+"'";
			rs = stmt.executeQuery(strsql);   
			
			while(rs.next()){
				//判断是否首次同步此科目 
				OuterSubject  outerSubject = outerSubjectService.findSubjectByCode(companyId,dbType,dbName,year,rs.getString("ccode"));
				
				//如果存在该科目则更新 
				if(null == outerSubject.getId() ){
					//如果不存在该科目则保存
					outerSubject = new OuterSubject();
					final Long id = SequenceUtil.getTableId("t_sys_outerSubject");
					outerSubject.setId(id);
					outerSubject.setCompanyId(companyId);//公司id
					outerSubject.setCreateID(sessionInfo.getUser().getId());//添加人
					outerSubject.setCreateTime(new Date());//创建时间
					outerSubject.setDbType(dbType);//关联数据库类型
					outerSubject.setDbsId(dbName);//关联数据库名称
					outerSubject.setYear(year);//年度
					outerSubject.setIsEnable(HBCSoftConstant.INT_FALSE);//默认无效
					outerSubject.setIsMatched(HBCSoftConstant.INT_FALSE);//设置匹配状态
					outerSubject.setSubjectCode(rs.getString("ccode"));//科目编码
					outerSubject.setSubjectName(rs.getString("ccode_name"));//科目名称
					outerSubject.setSubjectType(rs.getString("cclass"));//科目类型
					outerSubject.setSubjectTypeCode(rs.getString("cclass_engl"));//科目类型英文缩写
					outerSubject.setSubjectLevel(rs.getInt("igrade"));//科目等级

					//保存
					outerSubjectService.saveOuterSubjects(outerSubject);
				
				}else{
					outerSubject.setUpdateID(sessionInfo.getUser().getId());//变更人
					outerSubject.setUpdateTime(new Date());//变更时间
					outerSubject.setSubjectName(rs.getString("ccode_name"));//科目名称
					outerSubject.setSubjectType(rs.getString("cclass"));//科目类型
					outerSubject.setSubjectTypeCode(rs.getString("cclass_engl"));//科目类型英文缩写
					outerSubject.setSubjectLevel(rs.getInt("igrade"));//科目等级
					
					//更新
					outerSubjectService.updateOuterSubject(outerSubject);
				}
			}
		} catch (InstantiationException | IllegalAccessException |
				ClassNotFoundException | HbcsoftException | SQLException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatement(stmt);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		getLogger().info(dbName+"======同步科目信息结束=======");
	}
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageSubjectList", method = RequestMethod.POST)
	public Object pageSubjectList(final HttpServletRequest request, final HttpServletResponse response){ 
		final JSONObject jsonObject = new JSONObject(); 
		final String year = HbcsoftUtil.getInstYear();//当前年度
		OuterDBLinkPara outerDBLinkPara = new OuterDBLinkPara();
		
		final String dbLinkParaId = request.getParameter("dbLinkParaId");//数据库连接参数id
		String subjectType = request.getParameter(paraSubjectType);//科目类型
		String subjectName = request.getParameter(paraSubjectName);//科目名称
		final String isEnable = request.getParameter("isEnable");//是否启用
		try {
			subjectType = URLDecoder.decode(subjectType, "UTF-8");
			subjectName = URLDecoder.decode(subjectName, "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			this.getLogger().info(e3);
		}
	
		try {
			outerDBLinkPara = outerDBLinkParaService.findDbParaById(dbLinkParaId);
		} catch (HbcsoftException e2) {
			// TODO Auto-generated catch block
			this.getLogger().info(e2);
		}
		final String dbName = outerDBLinkPara.getDbsId();
		final int dbType = outerDBLinkPara.getDbType();
		final long companyId = outerDBLinkPara.getCompanyId();
		List<OuterSubject> subjectsList = new ArrayList<OuterSubject>();
		List<OuterSubject> subjects = new ArrayList<OuterSubject>();
			
		try {
			subjects = outerSubjectService.queryAll(
					companyId,dbType,dbName,year,subjectType,subjectName,isEnable);
		} catch (HbcsoftException e1) {
			// TODO Auto-generated catch block
			this.getLogger().info(e1);
		}
		totalNum = subjects.size();
		int pageTimes;//总页数
		if(totalNum%pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize + 1;
		}
		final String cu = request.getParameter(
				paraCurrentPage)==null ? "" : request.getParameter(paraCurrentPage);
		currentPage = Integer.parseInt("".equals(cu) ? "1": cu);
		final int startRow = (currentPage-1) * pageSize;
		
		try {
			subjectsList = outerSubjectService.queryAllSubjects(
					companyId,dbType,dbName,year,subjectType,subjectName,isEnable,
						Integer.toString(startRow), Integer.toString(pageSize));
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		
		jsonObject.put("pageSize", pageSize);
		jsonObject.put("currentPage", currentPage);
		jsonObject.put("pageTimes", pageTimes);
		jsonObject.put("totalNum", totalNum);
		jsonObject.put("subjectType", subjectType);
		jsonObject.put("subjectName", subjectName);
		
		
		if(null != subjectsList){//同步日期
			this.transFormDateToStr(subjectsList);
		}
		jsonObject.put("subjectsList", subjectsList);
		return jsonObject;
	}
	/**
	 * 获取同步日期并转换为字符串
	 * @param subjectsList
	 * @return
	 */
	public void transFormDateToStr(final List<OuterSubject> subjectsList) {
		// TODO Auto-generated method stub
		for (final Iterator<OuterSubject> iter = subjectsList.iterator(); iter.hasNext();) {
			final OuterSubject outerSubject = (OuterSubject) iter.next();
			Date updateTime = outerSubject.getUpdateTime();
			if(null == updateTime){
				updateTime = outerSubject.getCreateTime();
				outerSubject.setUpdateTime(updateTime);
			}
		}
	}

	/**
	 * 同步到系统科目
	 * 
	 * @param id
	 * @return 1——成功 0——失败
	 */
	@ResponseBody
	@RequestMapping(value = "/chooseSubjects", method = RequestMethod.POST)
	public String chooseSubjects(String ids) {
		String json = Integer.toString(HBCSoftConstant.INT_FALSE);
		try {
			if (ids != null && !"".equals(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
			
				
				
				
				this.saveSubjects(strId);
				json = Integer.toString(HBCSoftConstant.INT_TRUE);
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}
	/**
	 * 同步系统科目的方法
	 * @param strId
	 * @throws HbcsoftException 
	 */
	public void saveSubjects(final String[] strId) throws HbcsoftException {
		// TODO Auto-generated method stub
		OuterSubject outerSubject = new OuterSubject();
		final String iyear = HbcsoftUtil.getInstYear();//当前年度
		final Subject subject = new Subject();
		
		for(int i=0; i<strId.length; i++){
				outerSubject = outerSubjectService.findSubjectByID(strId[i]);
				final Long id = SequenceUtil.getTableId("t_sys_subject");
				subject.setId(id);
				subject.setIyear(iyear);
				subject.setIsEnable(HBCSoftConstant.INT_FALSE);
				subject.setCompanyId(
						outerSubject.getCompanyId());
				subject.setBudgetType(
						outerSubject.getBudgetType() == null ? "" : outerSubject.getBudgetType());
				subject.setBudgetTypeCode(
						outerSubject.getBudgetTypeCode() == null ? "" : outerSubject.getBudgetTypeCode());
				subject.setSubjectType(
						outerSubject.getSubjectType() == null ? "" : outerSubject.getSubjectType());
				subject.setSubjectTypeCode(
						outerSubject.getSubjectTypeCode() == null ? "":outerSubject.getSubjectTypeCode());
				subject.setSubjectLevel(
						outerSubject.getSubjectLevel());
				subject.setParentId(
						outerSubject.getParentId() == null ? "" : outerSubject.getParentId());
				subject.setSubjectCode(
						outerSubject.getSubjectCode());
				subject.setSubjectName(
						outerSubject.getSubjectName());
				subject.setIyear(outerSubject.getYear());
				subject.setSubjectProperty( 
						outerSubject.getSubjectProperty());
				subject.setRemark(
						outerSubject.getRemark() == null ? "" : outerSubject.getRemark());
			
				subjectService.saveSubject(subject);//保存系统科目信息
				
				outerSubject.setIsMatched(HBCSoftConstant.INT_TRUE);//修改匹配状态为已匹配
				outerSubjectService.updateOuterSubject(outerSubject);
		}
	}
}
