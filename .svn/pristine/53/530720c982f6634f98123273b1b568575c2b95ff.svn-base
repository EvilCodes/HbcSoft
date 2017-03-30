package com.hbcsoft.report.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.report.entity.ReportRow;

/**
 * 表体数据访问层
 * */
@Repository
public interface ReportRowDao {
	/**
	 * 根据父id查询
	 */
	List<ReportRow> queryByPid(final String sql, final JdbcTemplate jt, final String... param);
	

}
