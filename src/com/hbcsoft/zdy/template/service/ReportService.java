package com.hbcsoft.zdy.template.service;

import java.text.ParseException;
import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportConfigSearch;
import com.hbcsoft.sys.entity.OuterDBLinkPara;

public interface ReportService {

	/**
	 * 第三方数据库查询获取数据
	 * @param outerDBLinkList
	 * @param sql
	 * @return
	 * @throws ParseException 
	 * @throws HbcsoftException 
	 */
	List<List<Object>> getDataList(OuterDBLinkPara outerDBLink,List<String> listQuery,
			ReportConfig reportConfig,List<ReportConfigSearch> lstReportConfigSearch,
			String row, String head, List<String> listStr) throws HbcsoftException, ParseException;
}
