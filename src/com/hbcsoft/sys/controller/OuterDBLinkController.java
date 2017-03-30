package com.hbcsoft.sys.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.OuterDBLinkParaService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.util.DBUtil;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 
 * @author yangfeng
 * @version 2016-11
 * 第三方数据库连接Controller 
 *
 */
@Controller
@RequestMapping(value = "/outerDBLink")
public class OuterDBLinkController extends BaseController<OuterDBLinkController>{
	/**
	 * 第三方数据库连接Service
	 */
	@Autowired
	private transient OuterDBLinkParaService outerDBLinkParaService;
	
	
	/**
	 * 第三方数据库配置列表查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOuterDBPara")
	public ModelAndView queryOuterDBPara(final HttpServletRequest request, final HttpServletResponse response,String message)throws HbcsoftException{
		try {
			if(message!=null && !"null".equals(message)){
				message=java.net.URLDecoder.decode(message,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String strDbName = request.getParameter("dbsId");
		final String strisEnable = request.getParameter("isEnable");
		final SessionInfo sessionInfo  =
				(SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		final String strCompanyName = sessionInfo.getCompany().getCompanyName();
		
		final List<OuterDBLinkPara> outerDBLinkList = outerDBLinkParaService.findOuterDBPara(strCompanyId,strDbName,strisEnable);
		final ModelAndView mav = new ModelAndView("sys/dbPara/outerDBLinkPara_list");
		
		mav.getModelMap().put(HBCSoftConstant.COMPANY_ID, strCompanyId);
		mav.getModelMap().put(HBCSoftConstant.COMPANY_NAME, strCompanyName);
		mav.getModelMap().put("message", message);
		mav.getModelMap().put("outerDBLinkList", outerDBLinkList);
		return mav;
	}
		
	
	/**
	 * 新增时页面跳转
	 * @return
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/addOuterDbLink")
	public ModelAndView addOuterConnections(final HttpServletRequest request) throws HbcsoftException{
		
		final String strCompanyId = request.getParameter(HBCSoftConstant.COMPANY_ID);
		final String strCompanyName = request.getParameter(HBCSoftConstant.COMPANY_NAME);
		final ModelAndView mav = new ModelAndView("sys/dbPara/outerDBLinkPara_add");
		mav.getModelMap().put(HBCSoftConstant.COMPANY_ID, strCompanyId);
		mav.getModelMap().put(HBCSoftConstant.COMPANY_NAME, strCompanyName);
		return mav;
	}	

	/**
	 * 保存第三方数据库配置表信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/saveOuterDbPara", method = RequestMethod.POST)
	public ModelAndView saveDbPara(final HttpServletRequest request, final HttpServletResponse response) {
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		String message = null;
		final long userId = sessionInfo.getUser().getId();
		final long companyId = sessionInfo.getCompany().getId();
		final String strCompanyName = sessionInfo.getCompany().getCompanyName();
		try {
			final String[] dbAccount = request.getParameterValues("dbAccount");
			final String[] dbIp = request.getParameterValues("dbIp");
			final String[] dbType = request.getParameterValues("dbType");
			final String[] dbsId = request.getParameterValues("dbsId");
			final String[] dbport = request.getParameterValues("dbport");
			final String[] dbUser = request.getParameterValues("dbUser");
			final String[] dbPass = request.getParameterValues("dbPass");
			final String[] isEnabled = request.getParameterValues("isEnabled");
			final String[] remark = request.getParameterValues("remark");
			final OuterDBLinkPara db = new OuterDBLinkPara();

			if (dbIp != null && dbIp.length > 0) {
				for (int i = 0; i < dbIp.length; i++) {
					// 数据库重复校验
					message = this.checkDbIp("0",companyId, dbIp[i], dbsId[i], dbType[i]);
					if(null == message){
						db.setDbAccount(dbAccount[i]);
						db.setDbIp(dbIp[i]);
						db.setDbType(Integer.parseInt(dbType[i]));

						// 根据不同数据库类型获取驱动名
						final String dbDriver = this.getDbDriver(Integer.parseInt(dbType[i]));
						db.setDbDriver(dbDriver);

						// 获得数据库驱动路径
						final String dbURL = this.getDbUrl(Integer.parseInt(dbType[i]), dbIp[i], dbsId[i]);
						db.setDbURL(dbURL);

						db.setDbport(dbport[i]);
						db.setDbsId(dbsId[i]);
						db.setDbUser(dbUser[i]);
						db.setDbPass(dbPass[i]);
						db.setIsEnabled(Integer.parseInt(isEnabled[i]));
						db.setRemark(remark[i]);
						final Long id = SequenceUtil.getTableId("t_sys_outerdblinkpara");
						db.setId(id);
						db.setCreateID(userId);
						db.setCompanyId(companyId);
						db.setCompanyName(strCompanyName);

						outerDBLinkParaService.saveOuterDbPara(db);
					}else{
						try {
							message = URLEncoder.encode(message,"UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							this.getLogger().info(e);
						}
					}

				}
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		ModelAndView mav= new ModelAndView("redirect:/outerDBLink/queryOuterDBPara.hbc?message=" + message);
		
		return mav;
	}	
	/**
	 * 第三方数据库重复性校验
	 * @param id 
	 * @param strCompanyId 
	 * @param dbIp 数据库IP
	 * @param dbsIp 数据库名
	 * @param dbType 
	 * @return
	 * @throws HbcsoftException 
	 */
	public String checkDbIp(String id, final long companyId, final String dbIp,final String dbsIp, final String dbType) throws HbcsoftException{ 
		final OuterDBLinkPara dbLinkPara = outerDBLinkParaService.checkByIpAndDbname(companyId,dbIp, dbsIp,dbType);
		String message = null;
		Long ids = 0L;
		if(null == dbLinkPara.getId()){
			id = "0";
		}else{
			ids = dbLinkPara.getId();
		}
		if(null != dbLinkPara &&  !id.equals(Long.toString(ids))){
			message = "IP地址"+dbIp+"下已配置名为"+dbsIp+"的同类数据库！";
		}			
		return message;
	}	
	/**
	 * 获取不同数据库驱动
	 * @param dbType
	 * @return
	 */
	public String getDbDriver(final int dbType) {
		// TODO Auto-generated method stub
		String driver = "";
		if(dbType == HBCSoftConstant.DBTYPE_MYSQL){
			driver = HBCSoftConstant.DBDRIVER_MYSQL;
		}
		if(dbType == HBCSoftConstant.DBTYPE_SQLSERVER){
			driver = HBCSoftConstant.DBDRIVER_SQLSERVER;
		}
		if(dbType == HBCSoftConstant.DBTYPE_ORACLE){
			driver = HBCSoftConstant.DBDRIVER_ORACLE;
		}
		return driver;
	}
	/**
	 * 数据库驱动路径获取
	 * @param dbType
	 * @param dbIp
	 * @param dbsId
	 * @return
	 */
	public String getDbUrl(final int dbType, final String dbIp, final String dbsId) {
		// TODO Auto-generated method stub
		String dbUrl = "";
		if(dbType == HBCSoftConstant.DBTYPE_MYSQL){
			dbUrl = "jdbc:mysql://"+dbIp+":3306/"+dbsId;
		}
		if(dbType == HBCSoftConstant.DBTYPE_SQLSERVER){
			dbUrl = "jdbc:microsoft:sqlserver://"+dbIp+":1433;DatabaseName="+dbsId;
		}
		if(dbType == HBCSoftConstant.DBTYPE_ORACLE){
			dbUrl = "jdbc:oracle:thin:@"+dbIp+":1521:"+dbsId;
		}
		return dbUrl;
	}

	/**
	 * 删除数据库连接配置
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/deleteDbLinkPara", method = RequestMethod.POST)
	public ModelAndView deleteDbLinkPara(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("redirect:/outerDBLink/queryOuterDBPara.hbc");
		String ids = request.getParameter("del");
		
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			for(int i=0; i<strId.length; i++){
				outerDBLinkParaService.deleteDbParaById(strId[i]);
			}
		}
		return mav;
	}
	
	/**
	 * 跳转到编辑修改数据库配置页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editDBPara")
	public ModelAndView editDBPara(final HttpServletRequest request){
		final ModelAndView mav = new ModelAndView("sys/dbPara/outerDBLinkPara_update");
		try {
			final String id = request.getParameter("ids");
			final OuterDBLinkPara outerDBLinkPara = outerDBLinkParaService.findDbParaById(id);
			mav.getModelMap().put("outerDBLinkPara", outerDBLinkPara);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		
		return mav;
	}

	/**
	 * 修改第三方数据库参数配置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/updateDBLinkPara", method = RequestMethod.POST)
	public ModelAndView updateDBLinkPara(final HttpServletRequest request, final HttpServletResponse response) {
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final long userId = sessionInfo.getUser().getId();

		final String id = request.getParameter("id");
		final Long companyId = Long.valueOf(request.getParameter(HBCSoftConstant.COMPANY_ID));
		final String companyName = request.getParameter(HBCSoftConstant.COMPANY_NAME);
		final String dbIp = request.getParameter("dbIp");
		final String dbport = request.getParameter("dbport");
		final String dbType = request.getParameter("dbType");
		final String dbsId = request.getParameter("dbsId");
		final String dbUser = request.getParameter("dbUser");
		final String dbPass = request.getParameter("dbPass");
		final String isEnabled = request.getParameter("isEnabled");
		final String remark = request.getParameter("remark") == null ? "" : request.getParameter("remark");
		String message = null;
		try {
			// 数据库重复校验
			message = this.checkDbIp(id,companyId, dbIp, dbsId, dbType);
			if (null == message) {

				final OuterDBLinkPara outerDBLinkPara = outerDBLinkParaService.findDbParaById(id);

				outerDBLinkPara.setUpdateID(userId);
				outerDBLinkPara.setUpdateTime(new Date());
				outerDBLinkPara.setCompanyId(companyId);
				outerDBLinkPara.setCompanyName(companyName);
				outerDBLinkPara.setDbIp(dbIp);
				outerDBLinkPara.setDbType(Integer.parseInt(dbType));
				outerDBLinkPara.setDbport(dbport);
				outerDBLinkPara.setDbsId(dbsId);
				// 根据不同数据库类型获取驱动名
				final String dbDriver = this.getDbDriver(Integer.parseInt(dbType));
				outerDBLinkPara.setDbDriver(dbDriver);
				// 获得数据库驱动路径
				final String dbURL = this.getDbUrl(Integer.parseInt(dbType), dbIp, dbsId);
				outerDBLinkPara.setDbURL(dbURL);
				outerDBLinkPara.setDbUser(dbUser);
				outerDBLinkPara.setDbPass(dbPass);
				outerDBLinkPara.setIsEnabled(Integer.parseInt(isEnabled));
				outerDBLinkPara.setRemark(remark);

				outerDBLinkParaService.updateDBLinkPara(outerDBLinkPara);

			}else{
				try {
					message = URLEncoder.encode(message,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					this.getLogger().info(e);
				}
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		ModelAndView mav= new ModelAndView("redirect:/outerDBLink/queryOuterDBPara.hbc?message=" + message);
		
		return mav;
	}
	
	/**
	 * 数据库连接测试
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/connectTest", method = RequestMethod.POST)
	@ResponseBody
	public String connectTest(final String id) {
		String json = Integer.toString(HBCSoftConstant.INT_FALSE);// 不存在
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			final OuterDBLinkPara outerDBLinkPara = outerDBLinkParaService.findDbParaById(id);
			final String dbName = outerDBLinkPara.getDbsId();
			final int dbType = outerDBLinkPara.getDbType();
			final String dbip = outerDBLinkPara.getDbIp();
			final String username = outerDBLinkPara.getDbUser();
			final String password = outerDBLinkPara.getDbPass();
			final String port = outerDBLinkPara.getDbport();
			final String driver = outerDBLinkPara.getDbDriver();

			conn = (Connection) DBUtil.getConnection(dbName, dbType, dbip, username, password, port, driver);
			stmt = conn.createStatement();
			final String strsql = HbcsoftCache.getSqlValue("outerDbLink_connectTest");
			rs = stmt.executeQuery(strsql);

			if (rs.next()) {
				json = Integer.toString(HBCSoftConstant.INT_TRUE);
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException
				| HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} finally {
			try {
				DBUtil.closeResultSet(rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
			try {
				DBUtil.closeStatement(stmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		return json;
	}
}
