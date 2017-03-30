package com.hbcsoft.report.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.service.InterManageService;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.*;
import com.hbcsoft.report.service.ReportConfigService;
import com.hbcsoft.report.service.ReportConfigServiceInterface;
import com.hbcsoft.report.service.ReportSpecialColService;
import com.hbcsoft.report.service.ReportSpecialRowService;
import com.hbcsoft.sys.entity.*;
import com.hbcsoft.sys.service.ClickManageService;
import com.hbcsoft.sys.service.DataDictQueryService;
import com.hbcsoft.sys.service.OuterDBLinkParaService;
import com.hbcsoft.sys.service.ResourceService2;
import com.hbcsoft.sys.util.HbcsoftUtil;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 
 * @author yangfeng
 * @version 2016-12
 * 报表类型配置Controller
 */
@Controller
@RequestMapping(value = "/reportConfig")
public class ReportConfigController extends BaseController<ReportConfigController>{
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient ReportConfigService reportConfigService;
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient ReportSpecialRowService reportSpecialRowService;
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient ReportSpecialColService reportSpecialColService;
	/**
	 * 引用ResourceService
	 */
	@Autowired
	private transient ResourceService2 resourceService;
	/**
	 * ClickManageService接口 
	 */
	@Autowired
	private transient ClickManageService manageService;
	
	/**
	 * 字典类型 dataDictQueryService
	 */
	@Autowired
	private transient DataDictQueryService dataDictQueryService;
	/**
	 * 第三方数据库连接Service
	 */
	@Autowired
	private transient OuterDBLinkParaService outerDBLinkParaService;
	/**
	 * 接口管理Service
	 */
	@Autowired
	private InterManageService interManageService;
	/**
	 * 管理接口Service
	 */
	@Autowired
	private ReportConfigServiceInterface interfaceService;
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
	 * pageList方法中变量
	 */
	private final transient JSONObject jsonObject = new JSONObject();
	/**
	 * PMD:解决字符串currentPage出现多次问题
	 */
	private static String paraCurrentPage = "currentPage";
	
	/**
	 * 报表名称
	 */
	private static String paraReportName= "reportName";
	
	/**
	 * 报表类型
	 */
	private static String paraReportType= "reportType";
	
	/**
	 * 表头类型
	 */
	private static String paraHeadType= "headType";
	
	/**
	 * 表体类型
	 */
	private static String paraBodyType= "bodyType";
	
	/**
	 * 数据配置
	 */
	private static String paraDataConfig= "dataConfig";
	
	/**
	 * 报表id
	 */
	private static final String REPORTID = "reportId";
	
	/**
	 * 报表字段列表
	 */
	private static final String LISTRCS = "listRCS";
	
	
	/**
	 * 公司id
	 */
	private static final String SCOMPANYID = "companyId";
	
	/**
	 * 编码
	 */
	private static final String UTF8 = "UTF-8";
	
	/**
	 * 报表Id
	 */
	private String tempReportId = "";
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
	 * 报表类型配置查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAllReportConfigs")
	public ModelAndView queryAllReportConfigs(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_list");
		List<ReportConfig> reportConfigList = new ArrayList<ReportConfig>();
		List<ReportConfig> reportConfigs = new ArrayList<ReportConfig>();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String reportName = request.getParameter(paraReportName);//报表名称
		final String reportType = request.getParameter(paraReportType);//报表类型
		final String headType  = request.getParameter(paraHeadType);//表头类型
		final String bodyType = request.getParameter(paraBodyType);//表体类型
		final String dataConfig = request.getParameter(paraDataConfig);//数据配置
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String [] params = {strCompanyId,reportName,reportType,headType,bodyType,dataConfig};
		try {
			reportConfigs = reportConfigService
					.queryAllReportConfigs(params);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		
		totalNum = reportConfigs.size();
		int pageTimes;//总页数
		if(totalNum % pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize +1;
		}
		final String cu = request.getParameter(paraCurrentPage)==null ? "" : request.getParameter(paraCurrentPage);
		if("".equals(cu)){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(cu);
		}
		final int startRow = (currentPage-1) * pageSize;
		
		try {
			reportConfigList = reportConfigService.queryAllReportConfigs(
					params,startRow, pageSize);
			final List<DataDict> list=dataDictQueryService.getEnabledList("DataConfiguration",Long.toString(companyID()));
			final List<OuterDBLinkPara> dbLinkParaList = this.getDataSourceOptions();
			mav.getModelMap().put("list", list);
			mav.getModelMap().put("dbLinkParaList", dbLinkParaList);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put(paraReportName, reportName);
		mav.getModelMap().put(paraReportType, reportType);
		mav.getModelMap().put(paraHeadType, headType);
		mav.getModelMap().put(paraBodyType, bodyType);
		mav.getModelMap().put(paraDataConfig, dataConfig);
		mav.getModelMap().put(SCOMPANYID, strCompanyId);
		mav.getModelMap().put("reportConfigList", reportConfigList);
		return mav;
	}
	
	/**
	 * 分页查询报表类型信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageReportConfig", method = RequestMethod.POST)
	public Object pageReportConfig(final HttpServletRequest request, final HttpServletResponse response){ 
		final JSONObject jsonObject = new JSONObject(); 
		List<ReportConfig> reportConfigList = new ArrayList<ReportConfig>();
		List<ReportConfig> reportConfigs = new ArrayList<ReportConfig>();
		
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		String reportName = request.getParameter(paraReportName);//报表名称
		String reportType = request.getParameter(paraReportType);//报表类型
		String headType = request.getParameter(paraHeadType);//表头类型
		String bodyType = request.getParameter(paraBodyType);//表体类型
		String dataConfig = request.getParameter(paraDataConfig);//数据配置
		try {
			if(reportName!=null){
				reportName = URLDecoder.decode(reportName, UTF8);
			}else if(reportType!=null){
				reportType = URLDecoder.decode(reportType, UTF8);
			}else if(headType!=null){
				headType = URLDecoder.decode(headType, UTF8);
			}else if(bodyType!=null){
				bodyType = URLDecoder.decode(bodyType, UTF8);
			}else if(dataConfig!=null){
				dataConfig = URLDecoder.decode(dataConfig, UTF8);
			}
			
		} catch (UnsupportedEncodingException e1) {
			this.getLogger().info(e1);
		}
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String [] params = {strCompanyId,reportName,reportType,headType,bodyType,dataConfig};
		try {
			reportConfigs = reportConfigService
					.queryAllReportConfigs(params);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		
		totalNum = reportConfigs.size();
		int pageTimes;//总页数
		if(totalNum % pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize +1;
		}
		final String cu = request.getParameter(paraCurrentPage)==null ? "" : request.getParameter(paraCurrentPage);
		if("".equals(cu)){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(cu);
		}
		final int startRow = (currentPage-1) * pageSize;
		
		try {
			reportConfigList = reportConfigService.queryAllReportConfigs(
					params,startRow, pageSize);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		jsonObject.put("pageSize", pageSize);
		jsonObject.put("currentPage", currentPage);
		jsonObject.put("pageTimes", pageTimes);
		jsonObject.put("totalNum", totalNum);
		jsonObject.put(paraReportName, reportName);
		jsonObject.put(paraReportType, reportType);
		jsonObject.put(paraHeadType, headType);
		jsonObject.put(paraBodyType, bodyType);
		jsonObject.put(paraDataConfig, dataConfig);
		jsonObject.put(SCOMPANYID, strCompanyId);
		
		jsonObject.put("reportConfigList", reportConfigList);

		return jsonObject;
	}
	
	/**
	 * 新增时页面跳转
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/addReportConfig")
	public ModelAndView addReportConfig(final HttpServletRequest request) throws HbcsoftException{
		final String strCompanyId = request.getParameter(SCOMPANYID);
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_add");
		//数据源下拉框
		final List<OuterDBLinkPara> dbLinkParaList = this.getDataSourceOptions();
		List<DataDict> list=dataDictQueryService.getEnabledList("DataConfiguration",Long.toString(companyID()));
		mav.getModelMap().put(SCOMPANYID, strCompanyId);
		mav.getModelMap().put("dbLinkParaList", dbLinkParaList);
		mav.getModelMap().put("list", list);
		return mav;
	}	

	/**
	 * 新增时保存报表配置信息
	 */
	private void addReportConfig(final String[] params , final Long reportid){
		final String reportName = params[0];
		final String reportType = params[1];
		final String headType = params[2];
		final String headConfig = params[3];
		final String bodyType = params[4];
		final String bodyConfig = params[5];
		final String reportUrl = params[6];
		final String reportSQL = params[7];
		final String dataConfig = params[8];
		final String remark = params[9];
		final String startRow = params[10];
		final String startColumn = params[11];
		final String excelURL = HBCSoftConstant.EXCELURL+reportid+"/"+params[14];
		final String headTableName = params[15];
		final String headFieldName = params[16];
		final String bodyTableName = params[17];
		final String bodyFieldName = params[18];	
		final String oldTempleName =  params[19];
		final String mainTitleStartRow = params[20];
		final String mainTitleStartColumn = params[21];
		final String subtitleStartRow = params[22];
		final String subtitleStartColumn = params[23];
		final String dataSourceId = params[24];
		final ReportConfig rc = new ReportConfig();
		rc.setId(reportid);
		rc.setReportName(reportName);
		rc.setReportType(reportType);
		rc.setHeadType(Integer.valueOf(headType));
		rc.setHeadConfig(headConfig);
		rc.setBodyType(Integer.valueOf(bodyType));
		rc.setBodyConfig(bodyConfig);
		rc.setReportUrl(reportUrl+reportid);
		rc.setReportSQL(reportSQL);
		rc.setDataConfig(Integer.valueOf(dataConfig));
		rc.setRemark(remark);
		rc.setStartRow(Integer.valueOf(startRow));
		rc.setStartColumn(Integer.valueOf(startColumn));
		rc.setHeadTableName(headTableName);
		rc.setHeadFieldName(headFieldName);
		rc.setBodyTableName(bodyTableName);
		rc.setBodyFieldName(bodyFieldName);
		rc.setDelStatus(HBCSoftConstant.INT_TRUE);
		rc.setExcelUrl(excelURL);
		rc.setOldTempleName(oldTempleName);
		rc.setCreateID(userId());
		rc.setCompanyId(companyID());
		rc.setMainTitleStartRow(Integer.valueOf(mainTitleStartRow));
		rc.setMainTitleStartColumn(Integer.valueOf(mainTitleStartColumn));
		rc.setSubtitleStartRow(Integer.valueOf(subtitleStartRow));
		rc.setSubtitleStartColumn(Integer.valueOf(subtitleStartColumn));
		rc.setDataSourceId(Long.valueOf(dataSourceId));
		try {
			reportConfigService.saveReportConfig(rc);
		} catch (HbcsoftException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
		}
	}
	
	/**
	 * 新增表后保存资源管理信息
	 */
	public void addResource(final String reportName, final String reportUrl, final Long reportId){
		final Resource resource = new Resource();
		resource.setCode(String.valueOf(reportId));
		resource.setName(reportName);
		resource.setUrl(reportUrl+reportId);
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
	 * 保存报表配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/saveReportConfig", method = RequestMethod.POST)
	public ModelAndView saveReportConfig(@RequestParam(value = "file",required = false)final MultipartFile file,
			final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSearch");
		final Long reportId = SequenceUtil.getTableId("Z_REPORT_CONFIG");
		final String oldTempleName = file.getOriginalFilename();//获取上传文件的文件原始名称
		final String fileName = oldTempleName.substring(0,oldTempleName.lastIndexOf("."))
				+HbcsoftUtil.formDatetimeToStr3(new Date())+".xlsx";
		this.uploadExcel(reportId,fileName,file);
		//保存报表主表信息
		final String reportName = request.getParameter(paraReportName);
		final String reportType = request.getParameter(paraReportType);
		final String headType = request.getParameter(paraHeadType);
		final String headConfig = request.getParameter("headConfig");
		final String headTableName = request.getParameter("headTableName");
		final String headFieldName = request.getParameter("headFieldName");
		final String bodyType = request.getParameter(paraBodyType);
		final String bodyConfig = request.getParameter("bodyConfig");
		final String bodyTableName = request.getParameter("bodyTableName");
		final String bodyFieldName = request.getParameter("bodyFieldName");
		final String reportUrl = request.getParameter("reportUrl");
		final String reportSQL = request.getParameter("reportSQL");
		final String dataConfig = request.getParameter(paraDataConfig);
		final String remark = request.getParameter("remark");
		final String startRow = request.getParameter("startRow");
		final String startColumn = request.getParameter("startColumn");
		final String specialRow = request.getParameter("specialRow");
		final String specialColumn = request.getParameter("specialColumn");
		final String mainTitleStartRow = request.getParameter("mainTitleStartRow");
		final String mainTitleStartColumn = request.getParameter("mainTitleStartColumn");
		final String subtitleStartRow = request.getParameter("subtitleStartRow");
		final String subtitleStartColumn = request.getParameter("subtitleStartColumn");
		final String dataSourceId = request.getParameter("dataSourceId");
		final String [] params = {reportName,reportType,headType,headConfig,bodyType,
				bodyConfig,reportUrl,reportSQL,dataConfig,remark,startRow,startColumn,
				specialRow,specialColumn,fileName,headTableName,headFieldName,bodyTableName,
				bodyFieldName,oldTempleName,mainTitleStartRow,mainTitleStartColumn,subtitleStartRow,
				subtitleStartColumn,dataSourceId};
		final ReportConfig repCon = reportConfigService.
				queryisRepostConfig(companyID(), reportName, HBCSoftConstant.INT_TRUE);
		if(repCon.getReportName()==null){
			addReportConfig(params, reportId);//保存报表配置信息
			addResource(reportName,reportUrl,reportId);//保存资源管理信息
			final List<ReportConfigSearch> listRCS = reportConfigService.queryRCSShow(reportId,companyID());
			mav.getModelMap().put(REPORTID, reportId);
			mav.getModelMap().put(LISTRCS, listRCS);
		}else{
			final List<ReportConfigSearch> listRCS = reportConfigService.queryRCSShow(repCon.getId(),companyID());
			mav.getModelMap().put(REPORTID, reportId);
			mav.getModelMap().put(LISTRCS, listRCS);
		}
		final List<ClickManage> optionList = manageService.getAllOptions(companyID());
		mav.getModelMap().put("optionList", optionList);
		return mav;//重定向到列表页面
	}
	/**
	 * 修改报表类型配置
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/updateReportConfig")
	public ModelAndView updateReportConfig(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_update");
		final String id = request.getParameter("ids");
		ReportConfig reportConfig = null;
		try{
			reportConfig = reportConfigService.selectIDRepCon(id,companyID());
			final List<OuterDBLinkPara> dbLinkParaList = this.getDataSourceOptions();
			final List<DataDict> list=dataDictQueryService.getEnabledList("DataConfiguration",Long.toString(companyID()));
			mav.getModelMap().put("list", list);
			mav.getModelMap().put("dbLinkParaList", dbLinkParaList);
		} catch (HbcsoftException e){
			this.getLogger().info(e);
		}
		mav.getModelMap().put("repCon", reportConfig);
		return mav;
	}
	
	/**
	 * 修改是保存主表信息
	 * @param reportConfig
	 * @param repCon
	 */
	public void updateSaveRepCON(final String [] reportConfig, final ReportConfig repCon){
		final String reportId = reportConfig[0];
		final String reportName = reportConfig[1];
		final String reportType = reportConfig[2];
		final String headType = reportConfig[3];
		final String headConfig = reportConfig[4];
		final String bodyType = reportConfig[5];
		final String bodyConfig = reportConfig[6];
		final String reportUrl = reportConfig[7];
		final String reportSQL = reportConfig[8];
		final String dataConfig = reportConfig[9];
		final String remark = reportConfig[10];
		final String startRow = reportConfig[11];
		final String startColumn = reportConfig[12];
		final String excelUrl = HBCSoftConstant.EXCELURL+reportId+"/"+reportConfig[15];
		final String headTableName = reportConfig[16];
		final String headFieldName = reportConfig[17];
		final String bodyTableName = reportConfig[18];
		final String bodyFieldName = reportConfig[19];	
		final String oldTempleName =reportConfig[20];
		final String mainTitleStartRow = reportConfig[21];
		final String mainTitleStartColumn = reportConfig[22];
		final String subtitleStartRow = reportConfig[23];
		final String subtitleStartColumn = reportConfig[24];
		final String dataSourceId  = reportConfig[25];
		repCon.setId(Long.valueOf(reportId));
		repCon.setReportName(reportName);
		repCon.setReportType(reportType);
		repCon.setHeadType(Integer.valueOf(headType));
		repCon.setHeadConfig(headConfig);
		repCon.setBodyType(Integer.valueOf(bodyType));
		repCon.setBodyConfig(bodyConfig);
		repCon.setReportUrl(reportUrl);
		repCon.setReportSQL(reportSQL);
		repCon.setDataConfig(Integer.valueOf(dataConfig));
		repCon.setRemark(remark);
		repCon.setDelStatus(HBCSoftConstant.INT_TRUE);
		repCon.setUpdateID(userId());
		repCon.setCompanyId(companyID());
		repCon.setStartRow(Integer.valueOf(startRow));
		repCon.setStartColumn(Integer.valueOf(startColumn));
		if(!"".equals(excelUrl)){
			repCon.setExcelUrl(excelUrl);
		}
		if(!"".equals(oldTempleName)){
			repCon.setOldTempleName(oldTempleName);
		}
		repCon.setHeadTableName(headTableName);
		repCon.setHeadFieldName(headFieldName);
		repCon.setBodyTableName(bodyTableName);
		repCon.setBodyFieldName(bodyFieldName);
		repCon.setMainTitleStartRow(Integer.valueOf(mainTitleStartRow));
		repCon.setMainTitleStartColumn(Integer.valueOf(mainTitleStartColumn));
		repCon.setSubtitleStartRow(Integer.valueOf(subtitleStartRow));
		repCon.setSubtitleStartColumn(Integer.valueOf(subtitleStartColumn));
		repCon.setDataSourceId(Long.valueOf(dataSourceId));
		try {
			reportConfigService.updateReportConfig(repCon);
		} catch (HbcsoftException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
		}
	}
	
	/**
	 * 修改后保存资源管理信息
	 */
	public void updateSaveResource(final Resource resource,final String reportName,final String reportId,final String actionUrl){
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
	 * 保存修改报表字段信息
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "updateReportConfig", method = RequestMethod.POST)
	public ModelAndView updateReportConfig(@RequestParam(value = "file",required = false)final MultipartFile file) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSearch");
		ReportConfig repCon = new ReportConfig();//修改原始单据
		Resource oldResource = new Resource();//修改原始资源管理信息
		//主表表单信息
		final String reportId = request.getParameter(REPORTID);
		String oldTempleName = "";
		String fileName = "";
		
		if(null != file && !file.isEmpty()){
			oldTempleName = file.getOriginalFilename();//获取上传文件的文件原始名称
			fileName = oldTempleName.substring(0,oldTempleName.lastIndexOf("."))
					+HbcsoftUtil.formDatetimeToStr3(new Date())+".xlsx";
			this.uploadExcel(Long.valueOf(reportId),fileName,file);	
		}
		final String reportName = request.getParameter(paraReportName);
		final String reportType = request.getParameter(paraReportType);
		final String headType = request.getParameter(paraHeadType);
		final String headConfig = request.getParameter("headConfig");
		final String headTableName = request.getParameter("headTableName");
		final String headFieldName = request.getParameter("headFieldName");
		final String bodyType = request.getParameter(paraBodyType);
		final String bodyConfig = request.getParameter("bodyConfig");
		final String bodyTableName = request.getParameter("bodyTableName");
		final String bodyFieldName = request.getParameter("bodyFieldName");
		final String reportUrl = request.getParameter("reportUrl");
		final String reportSQL = request.getParameter("reportSQL");
		final String dataConfig = request.getParameter(paraDataConfig);
		final String startRow = request.getParameter("startRow");
		final String startColumn = request.getParameter("startColumn");
		final String specialRow = request.getParameter("specialRow");
		final String specialColumn = request.getParameter("specialColumn");
		final String remark = request.getParameter("remark");
		final String mainTitleStartRow = request.getParameter("mainTitleStartRow");
		final String mainTitleStartColumn = request.getParameter("mainTitleStartColumn");
		final String subtitleStartRow = request.getParameter("subtitleStartRow");
		final String subtitleStartColumn = request.getParameter("subtitleStartColumn");
		final String dataSourceId = request.getParameter("dataSourceId");
		repCon = reportConfigService.selectIDRepCon(reportId, companyID());
		oldResource = resourceService.queryForAndRes(companyID(), reportUrl);
		final String[] reportConfig = {reportId,reportName,reportType,headType,headConfig,
				bodyType,bodyConfig,reportUrl,reportSQL,dataConfig,remark,startRow,startColumn,
				specialRow,specialColumn,fileName,headTableName,headFieldName,bodyTableName,bodyFieldName,
				oldTempleName,mainTitleStartRow,mainTitleStartColumn,subtitleStartRow,subtitleStartColumn,dataSourceId};
		updateSaveRepCON(reportConfig, repCon);//保存主表信息
		updateSaveResource(oldResource,reportName,reportId,reportUrl);
		final List<ReportConfigSearch> lRCS = reportConfigService.selectIDRCS(reportId,companyID());
		final List<ClickManage> optionList = manageService.getAllOptions(companyID());
		mav.getModelMap().put("optionList", optionList);
		mav.getModelMap().put(REPORTID, reportId);
		mav.getModelMap().put(LISTRCS, lRCS);
		return mav;
	}
	
	/**
	 * 修改报表字段信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value="updateRepConSearch", method = RequestMethod.POST)
	public ModelAndView updateRepConSearch(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSpecial");
		final String reportId = request.getParameter(REPORTID);
		final String [] searchId = request.getParameterValues("searchId");
		final String [] tableName = request.getParameterValues("tableName");
		final String [] reportSearchName = request.getParameterValues("reportSearchName");
		final String [] title = request.getParameterValues("title");
		final String [] inputType = request.getParameterValues("inputType");
		final String [] sourceMode = request.getParameterValues("sourceMode");
		final String [] sourceContent = request.getParameterValues("sourceContent");
		final String [] clickInfo = request.getParameterValues("clickInfo");
		final String [] sort = request.getParameterValues("sort");
		final List<ReportConfigSearch> lisRCS = new ArrayList<ReportConfigSearch>();
		if(searchId != null && searchId.length > 0){
			for(int i=0; i<searchId.length; i++){
				ReportConfigSearch rcs = null;
				if(searchId[i].equals("0")){
					rcs = new ReportConfigSearch();
				}else{
					rcs = reportConfigService.queryRepConSearchId(reportId,companyID(),searchId[i]);
				}
				rcs.setTableName(tableName[i]);
				rcs.setReportSearchName(reportSearchName[i]);
				rcs.setReportId(Long.valueOf(reportId));
				rcs.setTitle(title[i]);
				rcs.setSort(Integer.valueOf(sort[i]));
				rcs.setInputType(Integer.valueOf(inputType[i]));
				if(!sourceMode[i].isEmpty()){
					rcs.setSourceMode(Integer.valueOf(sourceMode[i]));
				}
				if(!sourceContent[i].isEmpty()){
					rcs.setSourceContent(sourceContent[i]);
				}
				if(!clickInfo[i].isEmpty()){
					rcs.setClickInfo(clickInfo[i]);
				}
				rcs.setFieldFlag(HBCSoftConstant.INT_FALSE);
				rcs.setCreateID(userId());
				rcs.setCompanyId(companyID());
				lisRCS.add(rcs);
			}
		}
		reportConfigService.updateRepConSearch(lisRCS);
		//报表配置
		final ReportConfig reportConfig = reportConfigService.selectIDConfig(reportId, companyID());
		//报表特殊行配置
		final List<ReportSpecialRow> specialRowList = reportSpecialRowService
				.findByReportConfigId(reportConfig.getId(), companyID());
		//报表特殊列配置
		final List<ReportSpecialCol> specialColList = reportSpecialColService
				.findByReportConfigId(reportConfig.getId(), companyID());
		
		mav.getModelMap().put("specialRowList", specialRowList);
		mav.getModelMap().put("specialColList", specialColList);
		mav.getModelMap().put("reportConfig", reportConfig);
		return mav;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/deleteReportConfigs", method = RequestMethod.POST)
	public ModelAndView deleteReportConfigs(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("redirect:/reportConfig/queryAllReportConfigs.hbc");
		String ids = request.getParameter("del");
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			for(int i=0; i<strId.length; i++){
				try {
					final ReportConfig reportConfig = reportConfigService.selectIDConfig(strId[i], companyID());
					//删除资源表信息
					final String reportType = reportConfig.getReportType();
					final String reportName = reportConfig.getReportName();
					final String ReportUrl = reportConfig.getReportUrl();
					final long companyId = reportConfig.getCompanyId();
					resourceService.deleteReportResource(companyId,reportType,reportName,ReportUrl);
					//作废报表类型信息
					reportConfig.setDelStatus(HBCSoftConstant.INT_FALSE);
					reportConfigService.updateReportConfig(reportConfig);
				} catch (HbcsoftException e) {
					this.getLogger().info(e);
				}
			}
		}
		return mav;
	}
	/**
	 * 双击查询
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/viewReport")
	public ModelAndView viewTable(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		this.getLogger().info("=======viewReport=====start===");
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_view");
		final String id = request.getParameter("id");
		ReportConfig config = null;
		OuterDBLinkPara outerDBLinkPara = new OuterDBLinkPara();
		try {
			config = reportConfigService.selectIDConfig(id,companyID());
			String dataSourceId = String.valueOf(config.getDataSourceId());
			if(HBCSoftConstant.STR_TRUE.equals(dataSourceId)){
				outerDBLinkPara.setDbAccount("本地数据源");
			}else{
				outerDBLinkPara = outerDBLinkParaService.findDbParaById(dataSourceId);
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		List<DataDict> list=dataDictQueryService.getEnabledList("DataConfiguration",Long.toString(companyID()));
		mav.getModelMap().put("list", list);
		mav.getModelMap().put("outerDBLinkPara", outerDBLinkPara);
		mav.getModelMap().put("config", config);
		this.getLogger().info("=======viewReport=====end===");
		return mav;
	}
	
	/**
	 * 查看报表配置字段信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/showFormField")
	public ModelAndView showFormField(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		this.getLogger().info("=======showFormField=====start===");
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSearch_view");
		final String reportId = request.getParameter(REPORTID);//表单id
//		final List<ReportConfigSearch> lstforfie = reportConfigService.querySearchConfigShow(oldFormId, companyID());
//		mav.getModelMap().put(REPORTID, oldFormId);
//		mav.getModelMap().put(LISTRCS, lstforfie);
		final List<ReportConfigSearch> lRCS = reportConfigService.selectIDRCS(reportId,companyID());
		final List<ClickManage> optionList = manageService.getAllOptions(companyID());
		mav.getModelMap().put("optionList", optionList);
		mav.getModelMap().put(REPORTID, reportId);
		mav.getModelMap().put(LISTRCS, lRCS);
		
		
		this.getLogger().info("=======showFormField=====end===");
		return mav;
	}

	/**
	 * 保存上传文件
	 * @param reportId 
	 * @param fileName2 
	 * @param file
	 * @throws HbcsoftException
	 */
	public void uploadExcel(Long reportId, String fileName, final MultipartFile file) throws HbcsoftException {
		// TODO Auto-generated method stub
		// // 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		// String savePath = this.getServletContext().getRealPath(
		// "/WEB-INF/upload");
		// File file = new File(savePath);
		// // 判断上传文件的保存目录是否存在
		// if (!file.exists() && !file.isDirectory()) {
		// System.out.println(savePath + "目录不存在，需要创建");
		// // 创建目录
		// }
		// // 消息提示
		// String message = "";
		// try {
		// // 使用Apache文件上传组件处理文件上传步骤：
		// // 1、创建一个DiskFileItemFactory工厂
		// DiskFileItemFactory factory = new DiskFileItemFactory();
		// // 2、创建一个文件上传解析器
		// ServletFileUpload upload = new ServletFileUpload(factory);
		// // 解决上传文件名的中文乱码
		// upload.setHeaderEncoding("UTF-8");
		// // 3、判断提交上来的数据是否是上传表单的数据
		// if (!ServletFileUpload.isMultipartContent(request)) {
		// // 按照传统方式获取数据
		// return;
		// }
		// //
		// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		// List<FileItem> list = upload.parseRequest(request);
		// for (FileItem item : list) {
		// // 如果fileitem中封装的是普通输入项的数据
		// if (item.isFormField()) {
		// String name = item.getFieldName();
		// // 解决普通输入项的数据的中文乱码问题
		// String value = item.getString("UTF-8");
		// // value = new String(value.getBytes("iso8859-1"),"UTF-8");
		// System.out.println(name + "=" + value);
		// } else {// 如果fileitem中封装的是上传文件
		// // 得到上传的文件名称，
		// String filename = item.getName();
		// System.out.println(filename);
		// if (filename == null || filename.trim().equals("")) {
		// continue;
		// }
		// // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
		// // c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
		// // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
		// filename = filename
		// .substring(filename.lastIndexOf("\\") + 1);
		// // 获取item中的上传文件的输入流
		// InputStream in = item.getInputStream();
		// // 创建一个文件输出流
		// FileOutputStream out = new FileOutputStream(savePath + "\\"
		// + filename);
		// // 创建一个缓冲区
		// byte buffer[] = new byte[1024];
		// // 判断输入流中的数据是否已经读完的标识
		// int len = 0;
		// // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
		// while ((len = in.read(buffer)) > 0) {
		// // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
		// // + filename)当中
		// out.write(buffer, 0, len);
		// }
		// // 关闭输入流
		// in.close();
		// // 关闭输出流
		// out.close();
		// // 删除处理文件上传时生成的临时文件
		// item.delete();
		// message = "文件上传成功！";
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
			this.getLogger().info("YF=======uploadExcel========begin");
		try {
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			final String savePath = this.session.getServletContext().getRealPath("temple")+"/"+reportId;
			this.getLogger().info("YF=======savePath========"+savePath);
			this.getLogger().info("YF=======fileName========"+fileName);
			// 判断上传文件的保存目录是否存在 目录不存在则创建
			final File files = new File(savePath);
			if (!files.exists()) {
				files.mkdir();
			}

			// 获取item中的上传文件的输入流
			final InputStream in = file.getInputStream();
			// 创建一个文件输出流
			FileOutputStream out;

			out = new FileOutputStream(savePath + "/" + fileName);
			
			// 创建一个缓冲区
			final byte buffer[] = new byte[1024];
			// 判断输入流中的数据是否已经读完的标识
			int len = 0;
			// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
			while ((len = in.read(buffer)) > 0) {
				// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"+
				// filename)当中
				out.write(buffer, 0, len);
			}
			// 关闭输入流
			in.close();
			// 关闭输出流
			out.close();
			this.getLogger().info("YF=======uploadExcel========end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, "模版文件保存失败！");
		}
	} 

	/**
	 * 删除查询字段
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/delRow")
	public ModelAndView delRow(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSearch");
		final String id= request.getParameter("searchId");
		final String reportid = request.getParameter("reportId");
		final ReportConfigSearch rcs = reportConfigService.queryRepConSearchId(reportid, companyID(), id);
		rcs.setFieldFlag(HBCSoftConstant.INT_TRUE);
		reportConfigService.updateRepConSearch(rcs);
		final List<ReportConfigSearch> lRCS = reportConfigService.selectIDRCS(reportid,companyID());
		final List<ClickManage> optionList = manageService.getAllOptions(companyID());
		mav.getModelMap().put("optionList", optionList);
		mav.getModelMap().put(REPORTID, reportid);
		mav.getModelMap().put(LISTRCS, lRCS);
		return mav;
	}
	
	/**
	 * 上下移动功能
	 * @param request
	 * @param status
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/moveChangeReport")
	public ModelAndView moveChangeReport(final HttpServletRequest request, final int status) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSearch");
		final String reportId = request.getParameter(REPORTID);
		final String [] searchId = request.getParameterValues("searchId");
		final String [] tableName = request.getParameterValues("tableName");
		final String [] reportSearchName = request.getParameterValues("reportSearchName");
		final String [] title = request.getParameterValues("title");
		final String [] inputType = request.getParameterValues("inputType");
		final String [] sourceMode = request.getParameterValues("sourceMode");
		final String [] sourceContent = request.getParameterValues("sourceContent");
		final String [] clickInfo = request.getParameterValues("clickInfo");
		final String [] sort = request.getParameterValues("sort");
		final List<ReportConfigSearch> lisRCS = new ArrayList<ReportConfigSearch>();
		if(searchId != null && searchId.length > 0){
			for(int i=0; i<searchId.length; i++){
				ReportConfigSearch rcs = null;
				if(searchId[i].equals("0")){
					rcs = new ReportConfigSearch();
				}else{
					rcs = reportConfigService.queryRepConSearchId(reportId,companyID(),searchId[i]);
				}
				rcs.setTableName(tableName[i]);
				rcs.setReportSearchName(reportSearchName[i]);
				rcs.setReportId(Long.valueOf(reportId));
				rcs.setTitle(title[i]);
				rcs.setSort(Integer.valueOf(sort[i]));
				rcs.setInputType(Integer.valueOf(inputType[i]));
				if(!sourceMode[i].isEmpty()){
					rcs.setSourceMode(Integer.valueOf(sourceMode[i]));
				}
				if(!sourceContent[i].isEmpty()){
					rcs.setSourceContent(sourceContent[i]);
				}
				if(!clickInfo[i].isEmpty()){
					rcs.setClickInfo(clickInfo[i]);
				}
				rcs.setFieldFlag(HBCSoftConstant.INT_FALSE);
				rcs.setCreateID(userId());
				rcs.setCompanyId(companyID());
				lisRCS.add(rcs);
			}
		}
		reportConfigService.updateRepConSearch(lisRCS);
		final List<ReportConfigSearch> lRCS = reportConfigService.selectIDRCS(reportId,companyID());
		final List<ClickManage> optionList = manageService.getAllOptions(companyID());
		mav.getModelMap().put("optionList", optionList);
		mav.getModelMap().put(REPORTID, reportId);
		mav.getModelMap().put(LISTRCS, lRCS);
		return mav;
		
	}
	
	/**
	 * 判断数据库中是否有reportName重复数据
	 * @param request
	 * @param response
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/isReportNameRepeat")
	public void isReportNameRepeat(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final String reportName = request.getParameter("reportName");
		final JSONObject jsonObject = new JSONObject();
		boolean isExist=true;
		final ReportConfig reportConfig = reportConfigService.queryisRepostConfig(companyID(), reportName, HBCSoftConstant.INT_TRUE);
		if(reportConfig.getReportName().isEmpty()){
			isExist = false;
		}else if(reportConfig.getReportName().equals(reportName)) {
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
	 * 数据源下拉框
	 * @return 
	 * @throws HbcsoftException 
	 */
	public List<OuterDBLinkPara> getDataSourceOptions() throws HbcsoftException {
		// TODO Auto-generated method stub
		final List<OuterDBLinkPara> dbLinkParaList 
			= outerDBLinkParaService.findOuterDBPara(String.valueOf(companyID()));
		final OuterDBLinkPara outerDBLinkPara = new OuterDBLinkPara();
		outerDBLinkPara.setId(1L);
		outerDBLinkPara.setDbAccount("本地数据源");
		dbLinkParaList.add(outerDBLinkPara);
		return dbLinkParaList;
	}
	/**
	 * 保存特殊格式配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value="saveSpecialReportConfig", method = RequestMethod.POST)
	public ModelAndView saveSpecialReportConfig(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		//final ModelAndView mav = new ModelAndView("redirect:/reportConfig/queryAllReportConfigs.hbc");
		final ModelAndView mav = new ModelAndView("redirect:/reportConfig/lookInterfaceManageList.hbc");
		tempReportId = request.getParameter(REPORTID);
		ReportSpecialRow reportSpecialRow = null;
		ReportSpecialCol reportSpecialCol = null;
		//报表配置主表信息
		final String reportId = request.getParameter(REPORTID);
		final String queryConditions = request.getParameter("queryConditions");
		//特殊行配置信息
		final String [] rowConfigId = request.getParameterValues("specialRowsId");
		final String [] specialRow = request.getParameterValues("specialRow");
		final String [] specialCellRow = request.getParameterValues("specialCellRow");
		final String [] isExtendQueryRow = request.getParameterValues("isExtendQueryRow");
		final String [] rowQueryConditions = request.getParameterValues("rowQueryConditions");
		//特殊列配置信息
		final String [] colConfigId = request.getParameterValues("specialColumnId");
		final String [] specialCol = request.getParameterValues("specialCol");
		final String [] specialCellCol = request.getParameterValues("specialCellCol");
		final String [] isExtendQueryCol = request.getParameterValues("isExtendQueryCol");
		final String [] colQueryConditions = request.getParameterValues("colQueryConditions");
		
		final List<ReportSpecialRow> specialRowList = new ArrayList<ReportSpecialRow>();
		final List<ReportSpecialCol> specialColList = new ArrayList<ReportSpecialCol>();
		if(rowConfigId != null && rowConfigId.length > 0){
			for(int i=0; i<rowConfigId.length; i++){
				if(rowConfigId[i].equals(HBCSoftConstant.STR_FALSE)){
					reportSpecialRow = new ReportSpecialRow();
					reportSpecialRow.setCreateID(userId());
					reportSpecialRow.setCompanyId(companyID());
					reportSpecialRow.setCreateTime(new Date());
					reportSpecialRow.setConfigId(Long.valueOf(reportId));
				}else{
					reportSpecialRow = reportSpecialRowService.findByPrimaryKey(rowConfigId[i]);
					reportSpecialRow.setUpdateID(userId());
					reportSpecialRow.setUpdateTime(new Date());
				}
				if(null != specialRow[i] && !"".equals(specialRow[i])){
					reportSpecialRow.setSpecialRow(Integer.valueOf(specialRow[i]));
				}
				if(null != specialCellRow[i] && !"".equalsIgnoreCase(specialCellRow[i])){
					reportSpecialRow.setSpecialCellRow(Integer.valueOf(specialCellRow[i]));
				}
				if(null != isExtendQueryRow[i] && !"".equals(isExtendQueryRow[i])){
					reportSpecialRow.setIsExtendQuery(Integer.valueOf(isExtendQueryRow[i]));
					if(isExtendQueryRow[i].equals(HBCSoftConstant.STR_TRUE)){
						reportSpecialRow.setQueryConditions(queryConditions);
					}else{
						if(null != rowQueryConditions && null != rowQueryConditions[i]){
							reportSpecialRow.setQueryConditions(rowQueryConditions[i]);
						}
					}
				}
				specialRowList.add(reportSpecialRow);
			}
			reportSpecialRowService.updateSpecialRow(specialRowList);
		}
		if(colConfigId  != null && colConfigId.length > 0){
			for(int i=0; i<colConfigId.length; i++){
				if(colConfigId[i].equals(HBCSoftConstant.STR_FALSE)){
					reportSpecialCol = new ReportSpecialCol();
					reportSpecialCol.setCreateID(userId());
					reportSpecialCol.setCompanyId(companyID());
					reportSpecialCol.setCreateTime(new Date());
					reportSpecialCol.setConfigId(Long.valueOf(reportId));
				}else{
					reportSpecialCol = reportSpecialColService.findByPrimaryKey(colConfigId[i]);
					reportSpecialCol.setUpdateID(userId());
					reportSpecialCol.setUpdateTime(new Date());
				}
				if(null != specialCol[i] && !"".equals(specialCol[i])){
					reportSpecialCol.setSpecialColumn(Integer.valueOf(specialCol[i]));
				}
				if(null != specialCellCol[i] && !"".equals(specialCellCol[i])){
					reportSpecialCol.setSpecialCellCol(Integer.valueOf(specialCellCol[i]));
				}
				if(null != isExtendQueryCol[i] && !"".equals(isExtendQueryCol[i])){
					reportSpecialCol.setIsExtendQuery(Integer.valueOf(isExtendQueryCol[i]));
					if(isExtendQueryCol[i].equals(HBCSoftConstant.STR_TRUE)){
						reportSpecialCol.setQueryConditions(queryConditions);
					}else{
						if(null != rowQueryConditions && null != colQueryConditions[i]){
							reportSpecialCol.setQueryConditions(colQueryConditions[i]);
						}
					}
				}
				specialColList.add(reportSpecialCol);
			}
			reportSpecialColService.updateSpecialCol(specialColList);
		}
		return mav;
	}
	/**
	 * 删除特殊行
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/delSpecialRow")
	public ModelAndView delSpecialRow(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSpecial");
		final String id= request.getParameter("specialRowsId");
		final String reportId = request.getParameter("reportId");
		try {
			reportSpecialRowService.delSpecialRowConfig(id);
			//配置主表
			final ReportConfig reportConfig = reportConfigService.selectIDConfig(reportId, companyID());
			//报表特殊行配置
			final List<ReportSpecialRow> specialRowList = reportSpecialRowService
					.findByReportConfigId(reportConfig.getId(), companyID());
			//报表特殊列配置
			final List<ReportSpecialCol> specialColList = reportSpecialColService
					.findByReportConfigId(reportConfig.getId(), companyID());
			
			mav.getModelMap().put("specialRowList", specialRowList);
			mav.getModelMap().put("specialColList", specialColList);
			mav.getModelMap().put("reportConfig", reportConfig);		
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return mav;
	}
	/**
	 * 删除特殊列
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/delSpecialCol")
	public ModelAndView delSpecialCol(final HttpServletRequest request, final HttpServletResponse response){
		final ModelAndView mav = new ModelAndView("report/config/reportConfigSpecial");
		final String id= request.getParameter("specialColumnId");
		final String reportId = request.getParameter("reportId");
		try {
			reportSpecialColService.delSpecialColConfig(id);
			//配置主表
			final ReportConfig reportConfig = reportConfigService.selectIDConfig(reportId, companyID());
			//报表特殊行配置
			final List<ReportSpecialRow> specialRowList = reportSpecialRowService
					.findByReportConfigId(reportConfig.getId(), companyID());
			//报表特殊列配置
			final List<ReportSpecialCol> specialColList = reportSpecialColService
					.findByReportConfigId(reportConfig.getId(), companyID());
			
			mav.getModelMap().put("specialRowList", specialRowList);
			mav.getModelMap().put("specialColList", specialColList);
			mav.getModelMap().put("reportConfig", reportConfig);		
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return mav;
	}
	/**
	 * 调用接口信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value="lookInterfaceManageList", method = RequestMethod.GET)
	public ModelAndView lookCallInterface(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_interfaceList");
		final String tableName = request.getParameter("className");
		final String memo = request.getParameter("tmemo");
		
		final List<PrimaryList> allTable = interManageService.queryAll(tableName, memo);
		totalNum = allTable.size();
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
		final List<ReportConfigInterface> interfaceList = interfaceService.queryEntity(tempReportId);
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", tableName);
		mav.getModelMap().put("searchMemo", memo);
		mav.getModelMap().put(ComConstant.mavList, list);
		mav.getModelMap().put("intermessageList", interfaceList);
		return mav;
		
	}
	/**
	 * 调用接口信息(分页)
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value="lookInterfaceManageList2", method = RequestMethod.GET)
	public ModelAndView lookCallInterface2(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("report/config/reportConfig_interfaceList");
		final String tableName = request.getParameter("className");
		final String memo = request.getParameter("tmemo");
		final int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		final List<PrimaryList> allTable = interManageService.queryAll(tableName, memo);
		totalNum = allTable.size();
		int pageTimes;//总页数
		if(totalNum%pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize + 1;
		}
		final int startRow = (currentPage-1) * pageSize;
		final List<PrimaryList> list = interManageService.queryAllTable(tableName, memo, startRow, pageSize);
		final List<ReportConfigInterface> interfaceList = interfaceService.queryEntity(tempReportId);
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", tableName);
		mav.getModelMap().put("searchMemo", memo);
		mav.getModelMap().put(ComConstant.mavList, list);
		mav.getModelMap().put("intermessageList", interfaceList);
		return mav;
		
	}
	/**
	 * 添加接口信息
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value="saveInterfaceManageList", method = RequestMethod.POST)
	public ModelAndView saveCallInterface(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("redirect:/reportConfig/queryAllReportConfigs.hbc");
		String [] rowids = request.getParameterValues("rowid");
		rowids = interfaceService.validateEntity(tempReportId, rowids);
		if (rowids.length > 0){
			List<ReportConfigInterface> interfaceList = new ArrayList<ReportConfigInterface>();
			for (int i = 0; i < rowids.length;i++){
				ReportConfigInterface rInterface = new ReportConfigInterface();
				rInterface.setId(SequenceUtil.getTableId("Z_REPORT_CONFIG"));
				rInterface.setCompanyId(companyID());
				rInterface.setCreateID(userId());
				rInterface.setCreateTime(new Date());
				rInterface.setPrimaryListId(Long.valueOf(rowids[i]));
				rInterface.setReportConfigId(Long.valueOf(tempReportId));
				rInterface.setFlag(0);
				interfaceList.add(rInterface);
			}
			interfaceService.saveInterfaceMessage(interfaceList);
		}
		return mav;
		
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
			totalNum = allTable.size();
			int pageTimes;
			if(totalNum%pageSize == 0){
				pageTimes = totalNum/pageSize;
			}else{
				pageTimes = totalNum/pageSize + 1;
			}
	
			final int startRow = (currentPage-1) * pageSize;
			final List<PrimaryList> list = interManageService.queryAllTable(searchTable, searchMemo, startRow, pageSize);
			final List<ReportConfigInterface> interfaceList = interfaceService.queryEntity(tempReportId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("pageTimes", pageTimes);
			jsonObject.put("totalNum", totalNum);
			jsonObject.put("list", list);
			jsonObject.put("interfaceList", interfaceList);
			
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return jsonObject;
	}
	

}
