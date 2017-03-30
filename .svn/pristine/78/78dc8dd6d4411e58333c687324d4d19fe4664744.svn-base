package com.hbcsoft.sys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.service.OrgService;
import com.hbcsoft.sys.service.OuterDBLinkParaService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.util.DBUtil;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 数据同步Controller
 * @author yangfeng
 * @version 2016-12
 */
@Controller
@RequestMapping(value = "/dataSynchronize")
public class DataSynchronizeController  extends BaseController<DataSynchronizeController>{
	/**
	 * 科目级次
	 */
	private final static int IGREAD_1 = 1;
	/**
	 * 科目级次
	 */
	private final static int IGREAD_2 = 2;
	/**
	 * 同步数据类型-用户
	 */
	private final static String USER = "user";
	/**
	 * 同步数据类型-部门
	 */
	private final static String DEPT = "dept";
	/**
	 * 第三方数据库连接接口
	 */
	@Autowired
	private transient OuterDBLinkParaService outerDBLinkParaService;
	/**
	* 用户service
	*/
	@Autowired
	private transient UserService userService;
	/**
	* 机构service
	*/
	@Autowired
	private transient OrgService orgService;
	/**
	 * 用户同步入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cloneUserData")
	public ModelAndView cloneUserData() {
		final ModelAndView mav = getDBLinkParas();
		mav.getModelMap().put("dataType", USER);
		return mav;
	}
	/**
	 * 部门同步入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cloneDeptData")
	public ModelAndView cloneDeptData() {
		final ModelAndView mav = getDBLinkParas();
		mav.getModelMap().put("dataType", DEPT);
		return mav;
	}
	/**
	 * 数据库连接配置列表查询
	 * @param strCompanyId
	 * @return
	 */
	public ModelAndView getDBLinkParas() {
		// TODO Auto-generated method stub
		final ModelAndView mav = new ModelAndView("sys/dataSynchronize/synchronize_list");
		final SessionInfo sessionInfo = (SessionInfo)this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String strCompanyId = sessionInfo.getCompany().getId().toString();
		try {
			final List<OuterDBLinkPara> outerDBLinkList = outerDBLinkParaService.findValidOuterDBPara(strCompanyId);
			mav.getModelMap().put("companyId", strCompanyId);
			mav.getModelMap().put("outerDBLinkList", outerDBLinkList);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return mav;
	}
	/**
	 * 校验指定数据类型是否同步过
	 * @param dbLinkParaId
	 * @param dataType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/repeatCheck", method = RequestMethod.POST)
	public String connectTest(final String dbLinkParaId,final String dataType) {
		String json = Integer.toString(HBCSoftConstant.INT_FALSE);
		try {
			final OuterDBLinkPara  outerDBLinkPara = outerDBLinkParaService.findDbParaById(dbLinkParaId);
			//同步过用户
			if(USER.equals(dataType) && HBCSoftConstant.INT_TRUE == outerDBLinkPara.getIsCloneUser()){
				//同步过部门
				if(HBCSoftConstant.INT_TRUE == outerDBLinkPara.getIsCloneDept()){
					//提示已经同步过用户数据，再次同步会删除
					json = Integer.toString(HBCSoftConstant.INT_TRUE);
				}else{
					//没同步过部门，提示先进行部门同步
					json = Integer.toString(HBCSoftConstant.INT_TRUE_2);
				}
			}
			//没同步过用户
			else if(USER.equals(dataType) && HBCSoftConstant.INT_FALSE== outerDBLinkPara.getIsCloneUser()){
				//没同步过部门
				if(HBCSoftConstant.INT_TRUE != outerDBLinkPara.getIsCloneDept()){
					//提示先进行部门同步
					json = Integer.toString(HBCSoftConstant.INT_TRUE_2);
				}
			}
			//同步过部门
			else if(DEPT.equals(dataType) && HBCSoftConstant.INT_TRUE == outerDBLinkPara.getIsCloneDept()){
				//提示已经同步过部门数据，再次同步会删除
				json = Integer.toString(HBCSoftConstant.INT_TRUE);
			}
			//其他情况
			else{
				json = Integer.toString(HBCSoftConstant.INT_FALSE);
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}
	/**
	 * 第三方数据同步主方法
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	@ResponseBody
	@RequestMapping(value = "/cloneData", method = RequestMethod.POST)
	public String cloneData(final String dbLinkParaId,final String dataType){
		String json = Integer.toString(HBCSoftConstant.INT_FALSE);
		try {
			//1、获取数据库连接参数
			final OuterDBLinkPara outerDBLinkPara = outerDBLinkParaService.findDbParaById(dbLinkParaId);
			//2、同步数据信息
			if(synchronizeData(dataType,outerDBLinkPara)){
				//3、反馈同步结果
				json = Integer.toString(HBCSoftConstant.INT_TRUE);
			}else{
				json = Integer.toString(HBCSoftConstant.INT_FALSE);
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}
	/**
	 * 同步数据信息
	 * @param dataType 
	 * @param year
	 * @param sessionInfo
	 * @param outerDBLinkPara
	 * @throws HbcsoftException 
	 */
	public Boolean synchronizeData(final String dataType,final OuterDBLinkPara outerDBLinkPara) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String dbName = outerDBLinkPara.getDbsId();
		final int dbType = outerDBLinkPara.getDbType();
		final String dbip = outerDBLinkPara.getDbIp();
		final String username = outerDBLinkPara.getDbUser();
		final String password = outerDBLinkPara.getDbPass();
		final String port = outerDBLinkPara.getDbport();
		final String driver = outerDBLinkPara.getDbDriver();
		
		getLogger().info(dbName+"======同步第三方信息开始=======");
		String strSql = "";
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;			
		try {
			conn =  (Connection) DBUtil.getConnection(dbName, dbType, dbip, username, password, port, driver);
			stmt = conn.createStatement() ; 
		
			if(USER.equals(dataType)){//用户信息
				strSql = HbcsoftCache.getSqlValue("dataSynchronize_queryU8Users");
				rs = stmt.executeQuery(strSql);   
				while(rs.next()){
					this.synchronizeUser(rs,outerDBLinkPara);
				}
				//设置用户同步状态
				this.setUserStatus(outerDBLinkPara,HBCSoftConstant.INT_TRUE);
			}else if(DEPT.equals(dataType)){//部门信息
				strSql =  HbcsoftCache.getSqlValue("dataSynchronize_queryU8Depts");
				rs = stmt.executeQuery(strSql);   
				while(rs.next()){
					this.synchronizeDept(rs,outerDBLinkPara);
				}
				//设置部门同步状态
				this.setDeptStatus(outerDBLinkPara,HBCSoftConstant.INT_TRUE);
			}else{
				throw new HbcsoftException(
						this.getClass().getName(), ErrorConstant.ERROR_CODE, "不存在的同步数据类型："+dataType);
			}
			
		} catch (InstantiationException | IllegalAccessException |
				ClassNotFoundException | SQLException |HbcsoftException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(
					this.getClass().getName(),  ErrorConstant.EXCEPTION_CODE, e);
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
		getLogger().info(dbName+"======同步第三方信息结束=======");
		return true;
	}

	/**
	 * 用户数据同步处理
	 * 
	 * @param dataType
	 * @param rs
	 * @param outerDBLinkPara
	 * @throws HbcsoftException
	 */
	public void synchronizeUser(final ResultSet rs, final OuterDBLinkPara outerDBLinkPara) throws HbcsoftException {
		// TODO Auto-generated method stub
		final User user = new User();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final Long companyId = sessionInfo.getCompany().getId();
		try {
			// 如果同步过，则先删除
			if (HBCSoftConstant.INT_TRUE == outerDBLinkPara.getIsCloneUser()) {
				// 删除同步的用户信息 管理员除外
				final List<User> userList = userService.findAllCloneUsers(companyId);
				for (final Iterator<User> iter = userList.iterator(); iter.hasNext();) {
					final User delUser = (User) iter.next();
					userService.deletebyIds(delUser);
				}
				//同步状态修改
				this.setUserStatus(outerDBLinkPara,HBCSoftConstant.INT_FALSE);
			}

			final Long id = SequenceUtil.getTableId("t_sys_user");
			user.setId(id);
			user.setCompanyId(companyId);// 公司id
			user.setCreateID(sessionInfo.getUser().getId());// 添加人
			user.setCreateTime(new Date());// 创建时间
			user.setLoginName(sessionInfo.getCompany().getCompanyNameHk() + "_" + rs.getString("cUser_Id"));// 登录名
			user.setName(rs.getString("cUser_Name"));// 操作员姓名
			user.setPassword("e10adc3949ba59abbe56e057f20f883e");// 密码默认123456
			user.setOrgName(rs.getString("cDept"));// 部门名称
			user.setOrgId(getCodeByName(rs.getString("cDept"),companyId));//部门编码
			user.setState(0);//是否激活 0-是 1-否
			user.setEnable(rs.getInt("nState") == 1 ? 0 : 1);// 是否停用 0-启用 1-停用
			user.setEmail(rs.getString("cUserEmail"));// Email
			user.setMobilephone(rs.getString("cUserHand"));// 手机号
			user.setIsclone(HBCSoftConstant.INT_TRUE);// 1--是同步的数据
			user.setCssPath("blue");// 背景颜色
			user.setIsManager(HBCSoftConstant.INT_TRUE);//默认不是管理员
			// 保存
			userService.save(user);
		} catch (SQLException | HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, e);
		}
	}
	/**
	 * 根据部门名称查同步的部门编码
	 * @param compamyId 
	 * @param string
	 * @return
	 * @throws HbcsoftException 
	 */
	public Long getCodeByName(final String cdeptName,final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		Long deptCode = 0L;
		if(null != cdeptName && !"".equals(cdeptName)){
			deptCode = orgService.findDeptCodeByName(cdeptName,companyId);
		}
		return deptCode;
	}
	/**
	 * 部门数据同步处理
	 * 
	 * @param dataType
	 * @param rs
	 * @param outerDBLinkPara
	 */
	public void synchronizeDept(final ResultSet rs, final OuterDBLinkPara outerDBLinkPara) {
		// TODO Auto-generated method stub
		final Org org = new Org();
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			if (HBCSoftConstant.INT_TRUE == outerDBLinkPara.getIsCloneDept()) {
				// 删除同步的部门
				final List<Org> orgList = orgService.findAllCloneDepts(sessionInfo.getCompany().getId());
				for (final Iterator<Org> iter = orgList.iterator(); iter.hasNext();) {
					final Org delOrg = (Org) iter.next();
					orgService.deletebyIds(delOrg);
				}
				//同步状态修改
				this.setDeptStatus(outerDBLinkPara,HBCSoftConstant.INT_FALSE);
			}
			final Long id = SequenceUtil.getTableId("t_sys_org");
			org.setId(id);
			org.setUserId(sessionInfo.getUser().getId());//操作员
			org.setOrgType(HBCSoftConstant.INT_TRUE);// 0-机构 、1-部门
			org.setCompanyId(sessionInfo.getCompany().getId());// 公司id
			org.setCreateID(sessionInfo.getUser().getId());// 添加人
			org.setCreateTime(new Date());// 创建时间
			org.setCode(rs.getString("cDepCode"));// 部门编码
			org.setName(rs.getString("cDepName"));// 部门名称
			org.setIsClone(HBCSoftConstant.INT_TRUE);//同步状态
			org.setIsClone(HBCSoftConstant.INT_TRUE);//同步标志 1是同步数据
			
			if(rs.getInt("iDepGrade") == IGREAD_1){
				//设置机构根结点
				 final Org rootOrg  = orgService.findRootNode(sessionInfo.getCompany().getId());
				 org.setParentId(rootOrg.getId());//
			}else if( rs.getInt("iDepGrade") == IGREAD_2){
				final List<Org> cloneOrgList =  orgService.findAllCloneDepts(sessionInfo.getCompany().getId());
				for (final Iterator<Org> iter = cloneOrgList.iterator(); iter.hasNext();) {
					final Org matchOrg = (Org) iter.next();
					if(rs.getString("cDepCode").substring(0, 2).equals(matchOrg.getCode())){
						org.setParentId(matchOrg.getId());
					}
				}
			}
			// 保存
			orgService.save(org);
		} catch (SQLException | HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}
	/**
	 * 设置用户同步状态
	 * @param dataType
	 * @param outerDBLinkPara
	 * @param cloneFlag 
	 */
	public void setUserStatus(final OuterDBLinkPara outerDBLinkPara, final int cloneFlag) {
		// TODO Auto-generated method stub
		try {
			outerDBLinkPara.setIsCloneUser(cloneFlag);//1为已同步
			outerDBLinkParaService.updateDBLinkPara(outerDBLinkPara);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}
	/**
	 * 设置部门同步状态
	 * @param dataType
	 * @param outerDBLinkPara
	 */
	public void setDeptStatus(final OuterDBLinkPara outerDBLinkPara,final int cloneFlag) {
		// TODO Auto-generated method stub
		try {
			outerDBLinkPara.setIsCloneDept(cloneFlag);//1为已同步
			outerDBLinkParaService.updateDBLinkPara(outerDBLinkPara);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}
}
