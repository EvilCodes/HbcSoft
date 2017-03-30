package com.hbcsoft.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper. 封装不同的输出风格,
 * 使用不同的builder函数创建实例.
 */
public class JsonMapper extends ObjectMapper {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7646131818851927786L;
	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JsonMapper.class);

	/**
	 * JsonMapper
	 */
	// private static JsonMapper mapper;

	/**
	 * JsonMapper()
	 */
	public JsonMapper() {
		this(null);
	}

	/**
	 * 
	 * @param include
	 */
	public JsonMapper(final Include include) {
		super();
		// 设置输出时包含属性的风格
		if (include != null) {
			this.setSerializationInclusion(include);
		}
		// 允许单引号、允许不带引号的字段名称
		this.enableSimple().toString();
		// 设置默认日期格式

		final SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		this.setDateFormat(format);
		this.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);// 空值处理为空串
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}

	/**
	 * 允许单引号 允许不带引号的字段名称
	 */
	public JsonMapper enableSimple() {
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return this;
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public String toJson(final Object object) {
		String result = null;
		try {
			result = this.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (LOG.isDebugEnabled()) {
				LOG.debug("write to json string error:" + e);
				// logger.warn("write to json string error:" + object, e);
			}
			// return null;
		}

		return result;

	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return this;
	}
}
