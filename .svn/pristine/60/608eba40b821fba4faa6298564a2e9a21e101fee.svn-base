package com.hbcsoft.zdy.common;

import com.hbcsoft.zdy.redis.RedisUtil;
import com.yaja.common.constant.YAJAConstant;

public class HbcsoftCache extends LogBaseClass<HbcsoftCache>{

	public static String getSqlValue(final String key)
	{
		if(YAJAConstant.USEREDIS)
		{
			return new RedisUtil().getValue(YAJAConstant.SHARD_NAME_SQL + key);
		}else{
			return YAJAConstant.SQLMAP.get(key);
		}
	}

	public static String getSysValue(final String key)
	{
		if(YAJAConstant.USEREDIS)
		{
			return new RedisUtil().getValue(YAJAConstant.SHARD_NAME_SYS+ key);
		}else{
			return YAJAConstant.SYSMAP.get(key);
		}
	}

	public static Object getCacheValue(final String key)
	{
		if(YAJAConstant.USEREDIS)
		{
			return new RedisUtil().getValue(YAJAConstant.SHARD_NAME_CACHE + key);
		}else{
			return YAJAConstant.CACHEMAP.get(key);
		}
	}

	public static void setSqlValue(final String key, final String value)
	{
		if(YAJAConstant.USEREDIS)
		{
			new RedisUtil().setValue(YAJAConstant.SHARD_NAME_SQL + key, value);
		}else{
			YAJAConstant.SQLMAP.put(key, value);
		}
	}

	public static void setSysValue(final String key, final String value)
	{
		if(YAJAConstant.USEREDIS)
		{
			new RedisUtil().setValue(YAJAConstant.SHARD_NAME_SYS + key, value);
		}else{
			YAJAConstant.SYSMAP.put(key, value);
		}
	}

	public static void setCacheValue(final String key, final Object value)
	{
		if(YAJAConstant.USEREDIS)
		{
			new RedisUtil().setValue(YAJAConstant.SHARD_NAME_CACHE + key, (String)value);
		}else{
			YAJAConstant.CACHEMAP.put(key, value);
		}
	}
}
