package com.hbcsoft.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hbcsoft.sys.entity.User;
/**
 * SessionSingleBean类 判断用户是否登录过
 * @author Administrator
 *
 */
public class SessionSingleBean {
	/**
	 * 日志
	 */
	private static final Logger LOGGER = Logger.getLogger(SessionSingleBean.class);
	/**
	 *判断用户是否登录过
	 * @param request
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public boolean checkSession(final HttpServletRequest request,final String username) {
		boolean issession=true;
		final Map<Object,Object> hashtable=(Map<Object,Object>)request.getSession().getServletContext().getAttribute("idlist");
		final HashMap hashMap=(HashMap)request.getSession().getServletContext().getAttribute("sessionid");
		synchronized(this) {
			final Object obj=hashtable.get(username);
			if(obj==null) {
				//放入上下文
				final User user=new User();
				hashtable.put(username,user);
				//获取在线人数
				request.getSession().setAttribute("count", MyHttpSessionListener.sessionCreated());
				hashMap.put(request.getSession().getId(),username);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>");
			}else {
				//这里使用Hashtable因为 Hashtable本身是synchronized 所以为了方便就使用Hashtable
				//如果自己编写一个类实现了synchronized 并且只是放入String[只是验证登录名称] 效果会更好
				//如果不是null就已经可以判断出来登录了，如果想进一步判断登录信息，这里做处理
				issession=false;
			}
		}
		return issession;
	}
}
