package com.hbcsoft.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.sys.entity.SessionInfo;

@Controller
/**
 * 拦截器 用来拦截用户是否登录，如果没有登录的话跳转到登录页面
 * @author Administrator
 *
 */
public class UserFilter implements Filter{
	@SuppressWarnings("rawtypes")
	/**
	 * new ArrayList();
	 */
	private transient final List notCheckURLList = new ArrayList();
	/**
	 * 定义常量HBC
	 */
	private static final String HBC="hbc";
	@Override
	/**
	 * destroy方法（移除List中的所有元素）
	 */
	public void destroy() {
		notCheckURLList.clear(); 
	}
	
	@Override
	/**
	 * doFilter方法，拦截器，校验用户是否登录，没有登录跳转到登录页
	 */
	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;
		final HttpSession session = request.getSession();
//		final String URL=request.getRequestURI();
		final String URL=request.getServletPath();//获取请求路径中项目名称后面的路径
		final String strsub=URL.substring(URL.length()-3);
		final SessionInfo info=(SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String[] str=new String []{"/baseConfigur.hbc","/login.hbc","/loginSubmit.hbc","/loginInput.hbc",
				"/modifyPwd/loginpwd.hbc","/modifyPwd/changepwd.hbc","/userSuccess.hbc","/modifyPwd/successpwd.hbc",
				"/modifyPwd/updatePwd.hbc","/userRegister/userSubmit.hbc","/userRegister/company.hbc",
				"/userRegister/companyCn.hbc","/userRegister/companyHk.hbc","/userRegister/userSuccess.hbc",
				"/modifyPwd/loginPwdSubmit.hbc","/userRegister/userState.hbc"};
		if((info==null||info.getUser()==null)&&this.stringArray(str,URL)
				&&HBC.equals(strsub)){
			response.sendRedirect("/hbcsoft/login.hbc");
		}else{
			filterChain.doFilter(req, resp);
		}
	}

	/**
	 * 实现Filter中的方法 （无用）
	 */
	@Override
	public void init(final FilterConfig arg0) throws ServletException {
		//TODO Auto-generated method stub
	}
	private boolean stringArray(final String[] str,final String url){
		boolean flag=true;
		for (int i = 0; i < str.length; i++) {
			final String strs=str[i];
			if(strs.equals(url)){
				flag=false;
				break;
			}
		}
		return flag;
	}
}		