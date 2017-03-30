package com.hbcsoft.zdy.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.hbcsoft.zdy.inter.LogClass;

public class LogBaseClass<T> implements LogClass<T> {
	private static final Logger LOGGER = Logger.getLogger(LogBaseClass.class);

	private transient final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public LogBaseClass() {
		final Type genType = getClass().getGenericSuperclass();
		final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	public T get() {
		try {
			return entityClass.newInstance();
		} catch (InstantiationException e) {
			LOGGER.info(e.getStackTrace());
		} catch (IllegalAccessException e) {
			LOGGER.info(e.getStackTrace());
		}
		return null;
	}
	
	public Logger getLogger()
	{
		try {
			return Logger.getLogger(entityClass.newInstance().getClass());
		} catch (InstantiationException e) {
			LOGGER.info(e.getStackTrace());
		} catch (IllegalAccessException e) {
			LOGGER.info(e.getStackTrace());
		}
		return null;
	}
}
