package com.hbcsoft.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.PubTools;

/**
 * 
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 */
public class ControllerUtils extends LogBaseClass<ControllerUtils> {

	// -- header 常量定义 --//
	/**
	 * 编码
	 */
	private static final String HEADER_ENCODING = "encoding";
	/**
	 * 无缓存
	 */
	private static final String HEADER_NOCACHE = "no-cache";
	/**
	 * UTF-8
	 */
	private static final String DEFAULT_ENCODING = "UTF-8";
	/**
	 * DEFAULT_NOCACHE
	 */
	private static final boolean DEFAULT_NOCACHE = true;
	/**
	 * mapper
	 */
	private static ObjectMapper mapper = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL);

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		// return getResponse();
		final ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		//final HttpServletResponse returnRe= attrs.getResponse();
		// return returnRe;
		return retureServletResponse(attrs);
	}
	private static HttpServletResponse retureServletResponse(final ServletRequestAttributes attrs){
		return attrs.getResponse();
	}
	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		final ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
//		final HttpServletRequest returnRe= attrs.getRequest();
//		return returnRe;
		return retureServletRequest(attrs);
	}
	private static HttpServletRequest retureServletRequest(final ServletRequestAttributes attrs){
		return attrs.getRequest();
	}
	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false"); render("text/plain",
	 * "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content,
			final String... headers) {
		final HttpServletResponse response = initResponseHeader(contentType,
				headers);
		try {
			writerWrite(content,response);
			writerFlush(response);
			//response.getWriter().write(content);
			//response.getWriter().flush();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	private static PrintWriter getWrite(final HttpServletResponse response) throws IOException{
		return response.getWriter();
	}
	private static void writerWrite(final String content,final HttpServletResponse response) throws IOException{
		final PrintWriter gw=getWrite(response);
		returnWrite(gw,content);
	}
	private static void returnWrite(final PrintWriter gw,final String content){
		 gw.write(content);
	}
	private static void writerFlush(final HttpServletResponse response) throws IOException{
		final PrintWriter gw=getWrite(response);
		returnFlush(gw);
	}
	private static void returnFlush(final PrintWriter gw){
		 gw.flush();
	}
	
	 /**
	 * 直接输出内容的简便函数
	 * @param contentType
	 * @param result
	 * @param headers
	 */
	public static void render(final String contentType, final Result result,
			final String... headers) {
		render(contentType, result.toString(), headers);
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 对象方式输出文本,text/plain方式,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final Object data, final String... headers) {
		final HttpServletResponse response = initResponseHeader(
				ServletUtils.TEXT_TYPE, headers);
		try {
			final PrintWriter rewriter=responsePrintWriter(response);
			mapper.writeValue(rewriter, data);
			//mapper.writeValue( response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
private static PrintWriter responsePrintWriter(final HttpServletResponse response) throws IOException{
	return (PrintWriter) response.getWriter();
}
	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString,
			final String... headers) {
		render(ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data, final String... headers) {
		final HttpServletResponse response = initResponseHeader(
				ServletUtils.HTML_TYPE, headers);
		try {
			final PrintWriter rewriter=responsePrintWriter(response);
			mapper.writeValue(rewriter, data);
//			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {

			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(
			final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (final String header : headers) {
			final String headerName = PubTools.substringBefore(header, ":");
			final String headerValue = PubTools.substringAfter(header, ":");
			if (PubTools.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (PubTools.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName
						+ "不是一个合法的header类型");
			}
		}

		final HttpServletResponse response = getResponse();

		// 设置headers参数
		final String fullContentType = contentType + ";charset=" + encoding;
		//response.setContentType(fullContentType);
		responseContentType( response, fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}
	private static void  responseContentType(final HttpServletResponse response,final String fullContentType){
		 response.setContentType(fullContentType);
	}
	@SuppressWarnings("unused")
	private String textFFF() {
		return null;
	}
}
