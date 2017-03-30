package com.hbcsoft.table.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * 2016-09-27
 * @author liang
 *
 */
public class ToStringSerializer extends JsonDeserializer<String> {

	/**
	 * json转换时，long类型转化为String类型时值位数不对问题
	 */
	@Override
	public String deserialize(final JsonParser jp, final DeserializationContext dc) throws IOException, JsonProcessingException {
		return jp.getText();
	}

}
