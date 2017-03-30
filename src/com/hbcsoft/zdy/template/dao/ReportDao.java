package com.hbcsoft.zdy.template.dao;

import java.util.List;

import com.hbcsoft.sys.entity.OuterDBLinkPara;

public interface ReportDao {

	/**
	 * 第三方数据库查询获取数据
	 * @param odbl
	 * @param sql
	 * @return
	 */
	List<List<Object>> getDataList(OuterDBLinkPara odbl, String sql);
}
