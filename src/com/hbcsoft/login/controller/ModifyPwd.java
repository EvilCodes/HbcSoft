package com.hbcsoft.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.service.CompanyService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftMD;
import com.hbcsoft.zdy.util.SendEmailUtil;
import com.hbcsoft.zdy.util.SendMailEntity;

/**
 * 修改密码类
 * (@Controller)负责注册一个bean 到spring 上下文中 
 * @author CRF
 *
 */
@Controller
@RequestMapping(value = "/modifyPwd")
public class ModifyPwd extends BaseController<ModifyPwd> {

	/**
	 * UserService接口 
	 */
	@Autowired
	private transient  UserService userService;
	/**
	 * CompanyService接口 
	 */
	@Autowired
	private transient CompanyService companyService;
	/**
	 * User实体类 
	 */	
	private transient User user;
	/**
	 * new一个ModelMap类
	 */
	private transient ModelMap map =new ModelMap();
	
	/**
	 * 公共的返回message错误信息存放在request中
	 * @param code
	 * @param request
	 * @return
	 */
	public HttpServletRequest message(final String code,final HttpServletRequest request){
		request.setAttribute("message", code);
		return request;
	}
	/**
	 * 跳转忘记密码页面(forgetpwd)
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/loginpwd")
	public ModelAndView loginpwd(final String message) {
		final ModelAndView mav = new ModelAndView("login/forgetpwd");
		map=mav.getModelMap();
		map.put("message", message);//当前用户不存在
		return mav;
	}
	/**
	 * 判断当前用户是否存在
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/loginPwdSubmit")
	public ModelAndView loginPwdSubmit(final HttpServletRequest request,final HttpServletResponse response) {
		final String loginName=request.getParameter("yourid");
		final String name=request.getParameter("username");
		final String email=request.getParameter("mail");
		final String company=request.getParameter("company");
		ModelAndView mavc=new ModelAndView();
		final String[] parameter=new String[]{loginName, name, email};
		mavc=loginPwdStep(company,parameter);
		if(mavc==null){
			if(HBCSoftConstant.EMAIL_SEND){
				mavc=new ModelAndView("redirect:/modifyPwd/changepwd.hbc?userId="+user.getId());
			}else{
				final ModelAndView mav = 
						new ModelAndView("redirect:/modifyPwd/loginpwd.hbc?message="+2);
				final SendMailEntity mailInfo = new SendMailEntity();
				final SendEmailUtil send = new SendEmailUtil();
				mailInfo.setSendType(send.SEND_TYPE_TEXT);
				final String content="请复制并打开此链接进行修改密码：http://baidu:8080/hbcsoft/modifyPwd/changepwd.hbc?userId="+user.getId();
				mailInfo.setContent(content);//内容
				mailInfo.setFromAddress("churuifeng@hbcsoft.com.cn");
				mailInfo.setMailServerHost("smtp.hbcsoft.com.cn");
				mailInfo.setMailServerPort("25");
				mailInfo.setPassword("CRF123$%^");
				mailInfo.setSubject("修改密码");//标题
				final List<String> toAddList=mailInfo.getToAddress();
				toAddList.add(user.getEmail());//收件人
				mailInfo.setUserName("churuifeng@hbcsoft.com.cn");//发件人
				mailInfo.setValidate(true);
				try {
					send.sendEmail(mailInfo);
				} catch (HbcsoftException e) {
					this.getLogger().info(e);
				}
				return mav;
			}
		}
		return mavc;
	}
	/**
	 * 修改密码的子方法
	 * @param company
	 * @param parameter
	 * @return
	 */
	public ModelAndView loginPwdStep (final String company,final String... parameter) {
		ModelAndView mav =null;
		Company com=new Company();
		try {
			com=companyService.query(company);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		final Long comId=com.getId();
		if(com==null||comId==null){
			mav = new ModelAndView("redirect:/modifyPwd/loginpwd.hbc?message="+1);
		}
		if(mav==null){
			String[] param= new String[4];
//			parameter.toString(),Long.toString(com.getId())
			System.arraycopy(parameter, 0, param, 0, 3);
			param[3]=Long.toString(com.getId());
			user=userService.loginPwd(param);
			//判断当前用户是否存在 
			if(user.getId()==null){
				mav = new ModelAndView("redirect:/modifyPwd/loginpwd.hbc?message="+1);
			}
		}
		return mav;
	}
	/**
	 * 跳转修改密码页（changepwd）
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/changepwd")
	public ModelAndView changepwd(final String userId) {
		final ModelAndView mav = new ModelAndView("login/changepwd");
		map=mav.getModelMap();
		map.put(HBCSoftConstant.USERID, userId);//当前用户不存在
		return mav;
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updatePwd")
	public ModelAndView updatePwd(final HttpServletRequest request,final HttpServletResponse response) {
		final String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		if (password != null && !"".equals(password)) {
			password = HbcsoftMD.md5(password);
		}
		userService.updatePwd(userId,password);
		ModelAndView mav =null;
		mav= new ModelAndView("redirect:/modifyPwd/successpwd.hbc");
		return mav;
	}
	/**
	 * 跳转成功页面（success）
	 * @return
	 */
	@RequestMapping(value = "/successpwd")
	public String successpwd() {
		return "/jdbc/success";
	}
	/**
	 * 首页中的修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/indexUpdatePwd")
	public ModelAndView indexUpdatePwd(final HttpServletRequest request,final HttpServletResponse response) {
		final String userId=request.getParameter("userId");
		String password=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		if (password != null && !"".equals(password)) {
			password = HbcsoftMD.md5(password);
		}
		if (newpassword != null && !"".equals(newpassword)) {
			newpassword = HbcsoftMD.md5(newpassword);
		}
		String message=null;
		try {
			final User user=userService.getUserPwd(userId,password);
			if(user==null||user.getId()==null){
				message="0";
			}else{
				int intV=0;
				intV=userService.updatePwd(userId,newpassword);
				if(intV==0){
					message="1";
				}else{
					message="2";
				}
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return new ModelAndView("redirect:/indexInit.hbc?message="+message);
	}
	/**
	 * 跳转修改密码页（changepwd）
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/indexPwd")
	public ModelAndView indexPwd(final String userId) {
		final ModelAndView mav = new ModelAndView("login/changepwd");
		map=mav.getModelMap();
		map.put("userId", userId);
		return mav;
	}
}
