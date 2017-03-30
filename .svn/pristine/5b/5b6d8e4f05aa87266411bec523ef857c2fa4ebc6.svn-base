package com.hbcsoft.login.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import org.apache.log4j.Logger;

/**
 * ContextSingleListener监听器
 * @author churuifeng
 *
 */
public class ContextSingleListener extends HttpServlet implements ServletContextListener {
	private static final long serialVersionUID = 1L;
	/**
	 * 日志
	 */
	private static final Logger LOGGER = Logger.getLogger(ContextSingleListener.class);
	
	/**
	 * contextInitialized方法
	 */
	@SuppressWarnings("rawtypes")
	public void contextInitialized(final ServletContextEvent sce) {
		final ServletContext context=sce.getServletContext();
		//这里使用Hashtable因为 Hashtable本身是synchronized 所以为了方便就使用Hashtable
		//如果自己编写一个类实现了synchronized 并且只是放入String[只是验证登录名称] 效果会更好
		context.setAttribute("idlist",new Hashtable());
		context.setAttribute("sessionid",new HashMap());
		LOGGER.info("初始化了Context,添加了idlist->Hashtable  sessionid->HashMap");
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>");
	}
	/**
	 * contextDestroyed方法
	 */
	public void contextDestroyed(final ServletContextEvent sce) {
		final ServletContext context=sce.getServletContext();
		context.removeAttribute("idlist");
	}
}
