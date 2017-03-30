package com.hbcsoft.login.controller;

import javax.servlet.http.HttpServletRequest;

import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * 统计在线人数的
 * @author Administrator
 *
 */
public class MyHttpSessionListener extends LogBaseClass<MyHttpSessionListener>{
	/**
	 * 计数
	 */
	private static int count;
//	public static int count=0;
	/**
	 * unknown
	 */
	private final static String NUKNOWN="unknown";
	/**
	 * count的get方法
	 * @return
	 */
	public static int getCount(){
		return count;
	}
	/**
	 * 在session开始时,统计数加1
	 * @return
	 */
	public static int sessionCreated() {
		return ++MyHttpSessionListener.count;
	}
	/**
	 * 在Session销毁时,统计数减1
	 * @return
	 */
	public static int sessionDestroyed(){
		return --MyHttpSessionListener.count;
	}
	/**
	 * 获取当前用户的真实Ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(final HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for"); 
		if (cheakIp(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 
		}
		if (cheakIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		}
		if (cheakIp(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (cheakIp(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (cheakIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	private static boolean cheakIp(final String ip){
		return ip == null || ip.length() == 0 || NUKNOWN.equalsIgnoreCase(ip);
	}
}
