package com.hbcsoft.report.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hbcsoft.report.entity.ReportHead;

/**
 * 报表表头设置dao
 * @author Administrator
 *
 */
@Repository
public interface ReportHeadDao {
	/**
	 * 根据父节点查询数据
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<ReportHead> queryReportHeaderByPid(final String sql,final JdbcTemplate jt,final String... param);
}
