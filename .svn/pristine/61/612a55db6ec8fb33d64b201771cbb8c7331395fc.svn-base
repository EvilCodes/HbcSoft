package com.hbcsoft.sys.controller;

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
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.OuterSubject;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.Subject;
import com.hbcsoft.sys.service.OuterSubjectService;
import com.hbcsoft.sys.service.SubjectService;
import com.hbcsoft.sys.util.HbcsoftUtil;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 
 * @author yangfeng
 * @version 2016-11
 * 系统科目配置Controller
 */
@Controller
@RequestMapping(value = "/subjectSet")
public class SubjectSetController extends BaseController<SubjectSetController>{
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient SubjectService subjectService;
	/**
	 * 第三方数据库科目接口
	 */
	@Autowired
	private transient OuterSubjectService outerSubjectService;
	/**
	 * 每页显示的条数
	 */
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
	 * 是否启用
	 */
	private static String paraIsEnable= "isEnable";
	/**
	 * 科目类型
	 */
	private static String paraSubjectType= "subjectType";
	/**
	 * 科目编码
	 */
	private static String paraSubjectCode= "subjectCode";
	/**
	 * 科目名称
	 */
	private static String paraSubjectName = "subjectName";
	/**
	 * 公司id
	 */
	private static String paraCompanyId = "companyId";
	/**
	 * 公司名称
	 */
	private static String paraCompanyName = "companyName";
	
	/**
	 * 当前页
	 */
	private static String  paraCurrentPage = "currentPage";
	
	/**
	 * 系统科目配置查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAllSubjects")
	public ModelAndView queryAllSubjects(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView mav = new ModelAndView("sys/subject/subject_list");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String year = HbcsoftUtil.getInstYear();// 当前年度
		final String isEnable = request.getParameter(paraIsEnable);// 是否启用
		final String subjectType = request.getParameter(paraSubjectType);// 科目类型
		final String subjectCode = request.getParameter(paraSubjectCode);// 科目编码
		final String subjectName = request.getParameter(paraSubjectName);// 科目名称
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String strCompanyName = sessionInfo.getCompany().getCompanyName();

		try {
			final List<Subject> subjects = subjectService.queryAllSubjects(strCompanyId, year, isEnable, subjectType,
					subjectCode, subjectName);

			totalNum = subjects.size();
			int pageTimes;// 总页数
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final String cu = request.getParameter(paraCurrentPage) == null ? ""
					: request.getParameter(paraCurrentPage);
			if ("".equals(cu)) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(cu);
			}
			final int startRow = (currentPage - 1) * pageSize;

			final List<Subject> subjectList = subjectService.queryAllSubjects(strCompanyId, year, isEnable, subjectType,
					subjectCode, subjectName, Integer.toString(startRow), Integer.toString(pageSize));

			mav.getModelMap().put("pageSize", pageSize);
			mav.getModelMap().put("currentPage", currentPage);
			mav.getModelMap().put("pageTimes", pageTimes);
			mav.getModelMap().put("totalNum", totalNum);
			mav.getModelMap().put("isEnable", isEnable);
			mav.getModelMap().put("subjectType", subjectType);
			mav.getModelMap().put("subjectCode", subjectCode);
			mav.getModelMap().put("subjectName", subjectName);
			mav.getModelMap().put(paraCompanyId, strCompanyId);
			mav.getModelMap().put(paraCompanyName, strCompanyName);

			mav.getModelMap().put("subjectList", subjectList);

		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}

		return mav;
	}

	/**
	 * 分页查询系统科目
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageSubjects", method = RequestMethod.POST)
	public Object pageSubjects(final HttpServletRequest request, final HttpServletResponse response) {
		final JSONObject jsonObject = new JSONObject();

		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String year = HbcsoftUtil.getInstYear();// 当前年度
		final String isEnable = request.getParameter(paraIsEnable);// 是否启用
		final String subjectCode = request.getParameter(paraSubjectCode);// 科目编码
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String strCompanyName = sessionInfo.getCompany().getCompanyName();

		try {
			final String subjectType = URLDecoder.decode( request.getParameter(paraSubjectType), "UTF-8");
			final String subjectName = URLDecoder.decode(request.getParameter(paraSubjectName), "UTF-8");

			final List<Subject> subjects = subjectService.queryAllSubjects(strCompanyId, year, isEnable, subjectType, subjectCode,
					subjectName);

			totalNum = subjects.size();
			int pageTimes;// 总页数
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final String cu = request.getParameter(paraCurrentPage) == null ? ""
					: request.getParameter(paraCurrentPage);
			if ("".equals(cu)) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(cu);
			}
			final int startRow = (currentPage - 1) * pageSize;

			final List<Subject> subjectList = subjectService.queryAllSubjects(strCompanyId, year, isEnable, subjectType,
					subjectCode, subjectName, Integer.toString(startRow), Integer.toString(pageSize));

			jsonObject.put(paraCompanyId, strCompanyId);
			jsonObject.put(paraCompanyName, strCompanyName);

			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("pageTimes", pageTimes);
			jsonObject.put("totalNum", totalNum);
			jsonObject.put("isEnable", isEnable);
			jsonObject.put("subjectType", subjectType);
			jsonObject.put("subjectCode", subjectCode);
			jsonObject.put("subjectName", subjectName);

			jsonObject.put("subjectsList", subjectList);
		} catch (HbcsoftException | UnsupportedEncodingException  e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return jsonObject;
	}
	
	/**
	 * 新增时页面跳转
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/addSubjects")
	public ModelAndView addSubject(final HttpServletRequest request) throws HbcsoftException{
		
		final String strCompanyId = request.getParameter(paraCompanyId);
		final String strCompanyName = request.getParameter(paraCompanyName);
		final ModelAndView mav = new ModelAndView("sys/subject/subject_add");
		mav.getModelMap().put(paraCompanyId, strCompanyId);
		mav.getModelMap().put("companyName", strCompanyName);
		return mav;
	}	
	/**
	 * 保存科目信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/saveSubjects", method = RequestMethod.POST)
	public ModelAndView saveSubjects(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final List<Subject> list = new ArrayList<Subject>();
		final SessionInfo sessionInfo=(SessionInfo) request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
		final String iyear = HbcsoftUtil.getInstYear();
		final long strCompanyId = sessionInfo.getCompany().getId();
		final long userId = sessionInfo.getUser().getId();
		
		final String [] subjectType = request.getParameterValues(paraSubjectType);
		final String [] subjectCode = request.getParameterValues(paraSubjectCode);
		final String [] subjectName = request.getParameterValues(paraSubjectName);
		final String [] subjectLevel = request.getParameterValues("subjectLevel");
		final String [] parentId = request.getParameterValues("parentId");
		final String [] isEnable = request.getParameterValues(paraIsEnable);
		final String [] remark = request.getParameterValues("remark");

		if(subjectCode != null && subjectCode.length > 0){
			for(int i=0; i<subjectCode.length; i++){
			
				final Subject subject = new Subject();
				subject.setIyear(iyear);
				subject.setSubjectType(subjectType[i]);
				subject.setSubjectCode(subjectCode[i].trim());
				subject.setSubjectName(subjectName[i].trim());
				subject.setSubjectLevel(Integer.parseInt(subjectLevel[i].trim()));
				subject.setParentId(parentId[i].trim());
				subject.setIsEnable(Integer.parseInt(isEnable[i]));
				subject.setRemark(remark[i]);
		
				final Long id = SequenceUtil.getTableId("t_sys_subject");
				subject.setId(id);
				subject.setCreateID(userId);
				subject.setCompanyId(strCompanyId);
				list.add(subject);
			}
		}
		try {
			subjectService.saveSubjects(list);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		final ModelAndView mav = new ModelAndView("redirect:/subjectSet/queryAllSubjects.hbc");
		
		return mav;//重定向到列表页面
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/deleteSubjects", method = RequestMethod.POST)
	public ModelAndView deleteSubjects(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("redirect:/subjectSet/queryAllSubjects.hbc");
		String ids = request.getParameter("del");
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			for(int i=0; i<strId.length; i++){
				try {
					final Subject subject = subjectService.findSubjectById(strId[i]);
				
					final String companyId = subject.getCompanyId().toString();
					final String subjectCode = subject.getSubjectCode();
					
					final OuterSubject outerSubject = outerSubjectService
							.findOuterSubject(companyId,subjectCode);
					outerSubject.setIsMatched(HBCSoftConstant.INT_FALSE);
					//更新匹配状态信息
					outerSubjectService.updateOuterSubject(outerSubject);
					
					subjectService.deleteSubject(subject);
				
				} catch (HbcsoftException e) {
					// TODO Auto-generated catch block
					this.getLogger().info(e);
				}
				
			}
		}
		return mav;
	}
	
	
	/**
	 * 跳转到编辑修改数据库配置页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editSubject")
	public ModelAndView editSubject(final HttpServletRequest request){
		final ModelAndView mav = new ModelAndView("sys/subject/subject_update");
		final String id = request.getParameter("ids");
		try {
			final Subject subject  = subjectService.findSubjectById(id);
			mav.getModelMap().put("subject", subject);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	
		return mav;
	}
	/**
	 * 修改科目配置
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/updateSubject", method = RequestMethod.POST)
	public ModelAndView updateSubject(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("redirect:/subjectSet/queryAllSubjects.hbc");
		final SessionInfo sessionInfo=(SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String iyear = HbcsoftUtil.getInstYear();
		
		final String id = request.getParameter("id");
		final String subjectType = request.getParameter(paraSubjectType);
		final String subjectCode = request.getParameter(paraSubjectCode).trim();
		final String subjectName = request.getParameter(paraSubjectName).trim();
		final String subjectLevel = request.getParameter("subjectLevel");
		final String isEnable = request.getParameter(paraIsEnable);
		final String remark = request.getParameter("remark") == null ? "" : request.getParameter("remark");
		
		try {
			final Subject subject = subjectService.findSubjectById(id);
			
			subject.setUpdateTime(new Date());
			subject.setIyear(iyear);
			subject.setUpdateID(sessionInfo.getUser().getId());
			subject.setSubjectType(subjectType);
			subject.setSubjectCode(subjectCode);
			subject.setSubjectName(subjectName);
			subject.setSubjectLevel(Integer.parseInt(subjectLevel));
			subject.setIsEnable(Integer.parseInt(isEnable));
			subject.setRemark(remark);
			//修改科目配置
			subjectService.updateSubject(subject);		
		
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		
		return mav;
	}
	
}
