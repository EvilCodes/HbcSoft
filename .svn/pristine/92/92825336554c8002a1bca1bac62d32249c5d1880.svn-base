package com.hbcsoft.login.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

//@WebListener
/**
 * session超时后进入的类
 * @author Administrator
 *
 */
public class SessionSingleListener extends HttpServlet implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	/**
	 * 日志输出
	 */
	private static final Logger LOGGER = Logger.getLogger(SessionSingleListener.class);
	/**
	 * sessionCreated方法
	 */
	public void sessionCreated(final HttpSessionEvent se) {
		//TODO Auto-generated method stub
	}
	@SuppressWarnings("rawtypes")
	/**
	 * session超时后走的方法
	 */
	public void sessionDestroyed(final HttpSessionEvent se) {
		final HttpSession session=se.getSession();
		final String id=session.getId();
		final ServletContext context=se.getSession().getServletContext();
		Map hashtable=(Map)context.getAttribute("idlist");
		final HashMap hashMap=(HashMap)context.getAttribute("sessionid");
		final Object objname=hashMap.get(id);
		if(objname==null) {
			return;
		}else{
			final String username=objname.toString();
			LOGGER.info("hashtable长度 ");
			LOGGER.info(hashtable.size());
			hashtable.remove(username);
			hashMap.remove(id);
			session.setAttribute("count", MyHttpSessionListener.sessionDestroyed());
			LOGGER.info("Session 过期 删除Context中的 ");
			LOGGER.info(username);
			hashtable=(Map)context.getAttribute("idlist");
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
}
