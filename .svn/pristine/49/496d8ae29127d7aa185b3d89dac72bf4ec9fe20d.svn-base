package com.hbcsoft.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.login.entity.UserInfo;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.sys.entity.Log;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.service.CompanyService;
import com.hbcsoft.sys.service.LogService;
import com.hbcsoft.sys.service.ResourceService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftMD;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 登录类
 * (@Controller)负责注册一个bean 到spring 上下文中 
 * @author CRF
 *
 */
@Controller
public class Login extends BaseController<Login> {

	/**
	 * UserService接口 
	 */
	@Autowired
	private transient  UserService userService;
	/**
	 * LogService接口 
	 */
	@Autowired
	private transient LogService logService;
	/**
	 * ResourceService接口 
	 */
	@Autowired
	private transient ResourceService resourceService;
	/**
	 * CompanyService接口 
	 */
	@Autowired
	private transient CompanyService companyService;
	/**
	 * User实体类 
	 */	
	private User user;
	
	/**
	 * 在线用户列表.
	 */
	public static final Map<Long,User> SESSIONINFOMAP = new ConcurrentHashMap<Long, User>();
	/**
	 * @return 跳转登录页
	 */
	@RequestMapping(value = "/login")//为控制器指定可以处理哪些 URL 请求,此处处理的是login模块下的login请求
	public String login() {
		return "login/login";
	}
	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @return 返回到跳转首页方法或者是login()
	 */
	@RequestMapping(value = "/loginSubmit")
	public ModelAndView loginSubmit(final HttpServletRequest request,final HttpServletResponse response) {
		this.getLogger().info("=======loginSubmit=====start===");
		final String userName = request.getParameter("userName");
		final String password = request.getParameter("password");
		final String veryCode = request.getParameter("veryCode");
		ModelAndView mav = null ;
		mav=this.usercheck(veryCode, password, userName, request);
		if(mav==null){
			mav = new ModelAndView("login/login");
		}
		this.getLogger().info("=======loginSubmit=====end===");
		return mav;
	}
	/**
	 * 跳转首页
	 * @return 首页路径
	 * @throws IOException 
	 */
	@RequestMapping(value = "/indexInit")
	public ModelAndView indexInit(final String message) throws IOException {//
		ModelAndView mav =null;
		try {
			if(user==null){
				mav= new ModelAndView("redirect:/login.hbc");
			}else{
				mav= new ModelAndView("login/index");
				final List<Resource> res=resourceService.getRootMenu(user.getId());
				final Map<String, Object> model = mav.getModel();
				model.put("navMenu", res);
				model.put("message",message);
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return mav;
	}
	
	/**
	 * 查询所有
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAll")
	public String queryAll(final HttpServletRequest request) {
		this.getLogger().info("=======queryAll=====start===");
		List<User> list=new ArrayList<User>();
		list = userService.queryAll();
		request.setAttribute("list",list);
		this.getLogger().info("=======queryAll=====end===");
		return "queryAll";
	}
	/**
	 * User的get方法
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * User的set方法
	 * @return
	 */
	public void setUser(final User user) {
		this.user = user;
	}
	/**
	 * 当前用户存在后的各种操作
	 * @param request
	 * @param issession
	 * @return
	 */
	public ModelAndView issessionInfo(final HttpServletRequest request,final boolean issession){
		ModelAndView mav = null;
		if (issession) { 
			//获取当前在线人数
			final HttpSession session=request.getSession();
			session.getAttribute("count");
			//获取当前用户和本机的信息
			final UserInfo userInfo=new UserInfo();
			final Map<String,String> map=System.getenv();
			userInfo.setComputerName(map.get("COMPUTERNAME"));//计算机名
			userInfo.setUserName(map.get("USERNAME"));
			userInfo.setIpName(MyHttpSessionListener.getIpAddress(request));
			//将Long强转为String类型
			final String str=Long.toString(user.getId());
			//获取当前Id的后三位数字
			final String strsub=str.substring(4,13)+userInfo.getComputerName()+userInfo.getIpName();
			//获取MAC地址（错误的 ）
			//修改为Ip的4-13位+机器名+Ip
			userInfo.setSystemMac(strsub);
			final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
			userInfo.setId(fromid);
			userInfo.setZid(user.getId());
			userInfo.setUserDomain(map.get("USERDOMAIN"));
			final Properties pro=System.getProperties();
			userInfo.setSystemName(pro.getProperty("os.name"));
			//			userService.saveInfo(userInfo);
			HbcsoftCache.setCacheValue(Long.toString(user.getId()), userInfo);
			//给日志表存数据
			final Log log=new Log();
			final Long fromid2 = SequenceUtil.getTableId("T_SYS_LOG");
			log.setId(fromid2);
			log.setCompanyId(user.getCompanyId());
			log.setType(0);
			log.setLoginName(user.getLoginName());
			log.setOperTime(new Date());
			log.setLoginId(Long.toString(user.getId()));
			log.setAction("用户登录");
			log.setContent("用户登录");
			log.setIp(MyHttpSessionListener.getIpAddress(request));
			try {
				logService.save(log);
			} catch (HbcsoftException e) {
				this.getLogger().info(e);
			}
			//将用户信息放到session中
			user.setPassword("");
			final SessionInfo info=new SessionInfo();
			info.setUser(user);
			//获取当前用户所在的公司信息
			Company com=new Company();
			try {
				com=companyService.queryId(user.getCompanyId());
			} catch (HbcsoftException e) {
				this.getLogger().info(e);
			}
			info.setCompany(com);
			
			session.setAttribute(HBCSoftConstant.SESSIONINFO, info);
			session.setAttribute("userName", user.getName());
			session.setAttribute("cssPath", user.getCssPath());
			mav = new ModelAndView("redirect:/indexInit.hbc");
			session.setAttribute("userId", user.getId());
			mav.addObject("request", request);
			
		} else {
			mav = new ModelAndView("login/login");
			request.setAttribute(HBCSoftConstant.MESSAGE, 0);//当前用户已登录，不可同时登录该账户。
		}
		
		return mav;
	}
	/**
	 * 校验用户是否存在和其他校验
	 * @param veryCode
	 * @param password
	 * @param userName
	 * @param request
	 * @return
	 */
	public ModelAndView usercheck (final String veryCode,final String password,final String userName,final HttpServletRequest request){
		ModelAndView mav=null;
		if (PubTools.isEmpty(veryCode)) {
			request.setAttribute(HBCSoftConstant.MESSAGE, 3);//验证码不能为空
		} else {
			final String validateCode = (String)session.getAttribute("validateCode"); 
			if (validateCode == null || !veryCode.equalsIgnoreCase(validateCode)) {
				request.setAttribute(HBCSoftConstant.MESSAGE, 2);//验证码错误
			} else {
				mav=this.checkSuccess(password, userName, request);
			}
		}
		return mav;
	}
	/**
	 * 校验成功后走的方法
	 * @param password
	 * @param userName
	 * @param request
	 * @return
	 */
	public ModelAndView checkSuccess(String password,final String userName,final HttpServletRequest request){
		ModelAndView mav=null;
		if (password != null && !password.equals("")) {
			password = HbcsoftMD.md5(password);
		}
		try {
			user = userService.getLoginUser(userName, password);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		if(user==null||user.getId()==null){
			request.setAttribute(HBCSoftConstant.MESSAGE, 1);//用户名或密码错误
		}else{
			if(user.getState()==0&&user.getEnable()==0){
				//判断当前用户是否已登录
				final SessionSingleBean sessionSingleBean = new SessionSingleBean();
				// 验证是否已经登录
				final HttpServletRequest request2=request;
				final boolean issession = sessionSingleBean.checkSession(request2,userName);
				mav=this.issessionInfo(request, issession);
			}else if(user.getEnable()==HBCSoftConstant.INT_TRUE){
				request.setAttribute(HBCSoftConstant.MESSAGE, 6);//当前用户为停用状态
			}else{
				request.setAttribute(HBCSoftConstant.MESSAGE, 5);//未激活状态
			}
		}
		return mav;
	}
	/**
	 * 跳转注册页面（input）
	 * @return
	 */
	@RequestMapping(value = "/loginInput")
	public String loginInput() {
		return "/login/input";
	}
	/**
	 * 注销并清空session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exitAction")
	public ModelAndView exitAction (final HttpServletRequest request){
		session.invalidate();
		return new ModelAndView("redirect:/login.hbc");
	}
	/**
	 * 换肤功能
	 * @param tableName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cssPath")
	public String checkHk(final String type){ 
		try{
			final SessionInfo sess=(SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
			sess.getUser().setCssPath(type);
			final int count=userService.updateCss(Long.toString(sess.getUser().getId()), type);
			String json = "0";//修改成功
			if(count==0){
				json = "1";//修改失败
			}else{
				session.setAttribute("cssPath",type);
			}
			return json;
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return null;
	}
}
