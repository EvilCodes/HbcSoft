package com.hbcsoft.common.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * Http与Servlet工具类.
 * 
 */
public class ServletUtils {
	/**
	 * Content Type
	 */
	public static final String TEXT_TYPE = "text/plain";
	/**
	 * JSON_TYPE
	 */
	public static final String JSON_TYPE = "application/json";
	/**
	 * XML_TYPE
	 */
	public static final String XML_TYPE = "text/xml";
	/**
	 * HTML_TYPE
	 */
	public static final String HTML_TYPE = "text/html";

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setDisableCacheHeader(final HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	@SuppressWarnings("unused")
	private String textText() {
		return null;
	}
}
