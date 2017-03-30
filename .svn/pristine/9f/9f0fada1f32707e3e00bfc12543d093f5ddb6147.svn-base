package com.hbcsoft.zdy.util;

import com.yaja.common.constant.YAJAConstant;
import com.yaja.common.util.YAJAUtil;

public class SequenceUtil {
	
	public static long getTableId(final String tableName)
	{
		long retV; 
		if(YAJAConstant.IDMAP.containsKey(tableName))
		{
			retV = YAJAConstant.IDMAP.get(tableName);
			YAJAConstant.IDMAP.put(tableName, retV + 1);
		}else{
			retV = YAJAUtil.initSequnce();
			YAJAConstant.IDMAP.put(tableName, retV + 1);
		}
		return retV;
	}
}
