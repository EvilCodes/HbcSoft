package com.hbcsoft.login.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.hbcsoft.login.service.UserRegisterService;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.sys.entity.RoleResource;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.entity.UserRole;
import com.hbcsoft.sys.service.CompanyService;
import com.hbcsoft.sys.service.OrgService;
import com.hbcsoft.sys.service.ResourceService;
import com.hbcsoft.sys.service.RoleResourceService;
import com.hbcsoft.sys.service.UserRoleService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.HbcsoftMD;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SendEmailUtil;
import com.hbcsoft.zdy.util.SendMailEntity;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 用户注册类
 * @author churuifeng
 *
 */
@Controller //负责注册一个bean 到spring 上下文中
@RequestMapping(value = "/userRegister")
public class UserRegister extends LogBaseClass<UserRegister> {

	/**
	 * userRegisterService接口
	 */
	@Autowired
	private transient UserRegisterService userRegisterService;
	/**
	 * userService接口
	 */
	@Autowired
	private transient UserService userService;
	/**
	 * userRoleService接口
	 */
	@Autowired
	private transient UserRoleService userRoleService;
	/**
	 * comService接口
	 */
	@Autowired
	private transient CompanyService comService;
	
	/**
	 * OrgService接口
	 */
	@Autowired
	private transient OrgService orgService;
	
	/**
	 * OrgService接口
	 */
	@Autowired
	private transient ResourceService resourceService;
	/**
	 * OrgService接口
	 */
	@Autowired
	private transient RoleResourceService roleResourceService;
	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/userSubmit")
	public ModelAndView loginSubmit(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException, IOException {
		this.getLogger().info("=======userSubmit=====start===");
		ModelAndView mav =null;
		String password = request.getParameter("password");//密码
//		String pwd = request.getParameter("repassword");//确认密码
		final String name = request.getParameter("username");//真实姓名
//		String age = request.getParameter("ages");//年龄
		final String sex = request.getParameter("sex");//性别
		final String email = request.getParameter("mail");//邮箱
		final String companyName = request.getParameter("company");//公司名称
		final String companyName_cn = request.getParameter("csimple");//公司中文简称
		final String companyName_hk = request.getParameter("co");//公司英文简称
		final String mobilephone = request.getParameter("phone");//手机号
		final String tel = request.getParameter("bell");//电话号
		//保存公司信息
		final Company company=new Company();
		final Long companyid = SequenceUtil.getTableId("T_SYS_COMPANY");
		company.setId(companyid);
		company.setCompanyName(companyName);
		company.setCompanyNameCn(companyName_cn);
		company.setCompanyNameHk(companyName_hk);
		final int comCount=comService.add(company);
		if(comCount==0){
			request.setAttribute("message", "0");//提交失败
		}else{
			//保存用户信息
			final User user=new User();
			final Long fromid = SequenceUtil.getTableId("T_SYS_USER");
			user.setId(fromid);
			user.setLoginName(companyName_hk+"_admin");//已公司英文简称+admin
			//将密码进行MD5加密
			if (password != null && !password.equals("")) {
				password = HbcsoftMD.md5(password);
			}
			user.setPassword(password);
			user.setName(name);
			user.setEmail(email);
			user.setTel(tel);
			user.setMobilephone(mobilephone);
	//		user.setIdentityNumber(identityNumber);  身份证号
			user.setEnable(0);//状态
			user.setSex(Integer.valueOf(sex));
			user.setIsManager(0);//是系统管理员
			user.setZid(fromid);
			user.setCompanyId(companyid);//公司Id
			if(HBCSoftConstant.EMAIL_SEND){
				user.setState(0);//不需要走发送邮件功能就走这个。。。。。。
			}else{
				user.setState(1);//默认为'否'
			}
			user.setCssPath("blue");//默认蓝色
			final int userCount=userRegisterService.add(user);
			if(userCount==0){
				request.setAttribute("message", "0");//提交失败
			}else{
				if(HBCSoftConstant.EMAIL_SEND){//判断是否需要发送邮件功能
					mav= new ModelAndView("redirect:/userRegister/userSuccess.hbc");
//					final User users = userService.getUser(Long.toString(user.getId()));
					//激活成功后 给当前用户赋值角色
					final UserRole userRole=new UserRole();
					userRole.setUserId(user.getId());
					userRole.setRoleId(HBCSoftConstant.USER_ROLE);
					userRoleService.addUserRole(userRole);
					//给机构表添加一条固定主节点
					final Org org=new Org();
					final Long fromids = SequenceUtil.getTableId("T_SYS_ORG");
					org.setId(fromids);
					final Company companys=comService.queryId(user.getCompanyId());
					org.setName(companys.getCompanyNameCn()+"机构管理");
					org.setEnable(0); //0 启动  1 停用
					org.setOrderNo(1);
					org.setUserId(user.getId());
					org.setCompanyId(companys.getId());
					org.setCreateID(user.getId());
					org.setOrgType(0);//机构类型  0单位  1部门
					orgService.save(org);
					this.addRoleResourt(user.getCompanyId());
					mav.addObject("loginName", user.getLoginName());
					mav.addObject("state", user.getState());//0
					return mav;  //跳转注册成功页面
				}else{
		//			request.setAttribute("loginName", user.getLogin_Name());//提交成功
					this.getLogger().info("=======loginSubmit=====end===");
					mav= new ModelAndView("redirect:/userRegister/userSuccess.hbc");
		//			ModelAndView mav = new ModelAndView("redirect:/loginpwd.hbc?message="+2);
					final SendMailEntity mailInfo = new SendMailEntity();
					final SendEmailUtil se = new SendEmailUtil();
					mailInfo.setSendType(se.SEND_TYPE_TEXT);
					final String content="请复制并打开此链接进行激活当前用户：http://baidu:8080/hbcsoft/userRegister/userState.hbc?userId="+user.getId();
					mailInfo.setContent(content);//内容
					mailInfo.setFromAddress("churuifeng@hbcsoft.com.cn");
					mailInfo.setMailServerHost("smtp.hbcsoft.com.cn");
					mailInfo.setMailServerPort("25");
					mailInfo.setPassword("CRF123$%^");
					mailInfo.setSubject("激活用户");//标题
					mailInfo.getToAddress().add(user.getEmail());//收件人
					mailInfo.setUserName("churuifeng@hbcsoft.com.cn");//发件人
					mailInfo.setValidate(true);
					try {
						se.sendEmail(mailInfo);
					} catch (HbcsoftException e) {
						this.getLogger().info(e);
					}
					mav.addObject("loginName", user.getLoginName());
					mav.addObject("state", user.getState());//1
					return mav;  //跳转注册成功页面
				}
			}
		}
		this.getLogger().info("=======loginSubmit=====end===");
		mav = new ModelAndView("redirect:/loginInput.hbc");
		return mav;
	}
	/**
	 * 跳转注册成功页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/userSuccess")
	public ModelAndView userSuccess(final String loginName,final Integer state) {
		final ModelAndView mav = new ModelAndView("login/userSuccess");
		mav.getModelMap().put("loginName", loginName);
		mav.getModelMap().put("state", state);//是否需要激活
		return mav;
	}
	/**
	 * 查询当前公司名称是否存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public String checkName(String tableName) throws UnsupportedEncodingException{ 
		try{
			tableName = URLDecoder.decode(tableName, "UTF-8");
			final List<Company> count = comService.queryCount(tableName);
			String json = "0";//不存在
			if(!count.isEmpty()){
				json = "1";//存在
			}
			return json;
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return null;
	}
	/**
	 * 查询当前公司中文简称是否存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/companyCn", method = RequestMethod.POST)
	public String checkCn(String tableName) throws UnsupportedEncodingException{ 
		try{
			tableName = URLDecoder.decode(tableName, "UTF-8");
			final List<Company> count = comService.queryCountCn(tableName);
			String json = "0";//不存在
			if(!count.isEmpty()){
				json = "1";//存在
			}
			return json;
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return null;
	}
	/**
	 * 查询当前公司英文简称是否存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/companyHk", method = RequestMethod.POST)
	public String checkHk(String tableName) throws UnsupportedEncodingException{ 
		try{
			tableName = URLDecoder.decode(tableName, "UTF-8");
			final List<Company> count = comService.queryCountHk(tableName);
			String json = "0";//不存在
			if(!count.isEmpty()){
				json = "1";//存在
			}
			return json;
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return null;
	}
	/*
	 * 添加
	 * */

	/**
	 * 修改用户状态页面 
	 * @return 跳转当前页面
	 * @throws IOException 
	 */
	@RequestMapping(value = "/userState")
	public String userState(final String userId) throws IOException {
		try {
			final User user = userService.getUser(userId);
			user.setState(0);
			userRegisterService.updateState(user);
			//激活成功后 给当前用户赋值角色
			final UserRole userRole=new UserRole();
			userRole.setUserId(user.getId());
			userRole.setRoleId(HBCSoftConstant.USER_ROLE);
			userRoleService.addUserRole(userRole);
			//给机构表添加一条固定主节点
			final Org org=new Org();
			final Long fromid = SequenceUtil.getTableId("T_SYS_ORG");
			org.setId(fromid);
			final Company company=comService.queryId(user.getCompanyId());
			org.setName(company.getCompanyNameCn()+"机构管理");
			org.setEnable(0); //0 启动  1 停用
			org.setOrderNo(1);
			org.setUserId(user.getId());
			org.setCompanyId(company.getId());
			org.setCreateID(user.getId());
			org.setOrgType(0);//机构类型  0单位  1部门
			orgService.save(org);
			this.addRoleResourt(user.getCompanyId());
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return "/login/activation";
	}
	/**
	 * 往中间表插数据（先查出公用的所有菜单，循环添加）
	 * @param CompanyId
	 * @throws IOException 
	 */
	public void addRoleResourt(final Long companyId) throws IOException{
		try {
			final List<Resource> reList=resourceService.findResource("");
			for (int i = 0; i < reList.size(); i++) {
				final RoleResource re=new RoleResource();
				re.setRoleId(HBCSoftConstant.USER_ROLE);
				re.setResourceId(reList.get(i).getId());
				re.setCompanyId(companyId);
				roleResourceService.addRoleResource(re);
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
	}
}
