package com.hbcsoft.zdy.template.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.file.sql.ConstructSql;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportConfigSearch;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.zdy.template.dao.ReportDaoImp;
/**
 * 
 */
@Transactional
@Service("reportService")
public class ReportServiceImp implements ReportService {

	/**
	 * 
	 */
	@Autowired
	private transient ReportDaoImp reportDao;
	/**
	 * 第三方数据库查询获取数据
	 * @author songliang
	 * @throws ParseException 
	 * @throws HbcsoftException 
	 * @since 2016-12-26
	 */
	@Override
	public List<List<Object>> getDataList(OuterDBLinkPara outerDBLink,List<String> listQuery,
			ReportConfig reportConfig,List<ReportConfigSearch> lstReportConfigSearch,
			String row, String head, List<String> listStr) throws HbcsoftException, ParseException {
		List<List<Object>> list = new ArrayList<List<Object>>();
//		odbl.setDbsId("U8DATA_20161220");
//		odbl.setDbType(1);
//		odbl.setDbIp("192.168.199.194");
//		odbl.setDbUser("sa");
//		odbl.setDbPass("hbcsoft");
//		odbl.setDbport("1433");
//		odbl.setDbDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
//		reportConfig.setReportSQL("select mc-md from GL_accvouch a where 1=1 ");
			
		final ConstructSql cs = new ConstructSql();
		String sql = cs.getSql(reportConfig.getReportSQL(), row, head,listStr,listQuery, reportConfig.getHeadConfig());
		list = reportDao.getDataList(outerDBLink, sql);
		return list;
	}

}
