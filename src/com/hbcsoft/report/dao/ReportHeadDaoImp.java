package com.hbcsoft.report.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.hbcsoft.report.entity.ReportHead;
import com.hbcsoft.zdy.common.DaoBaseClass;

/**
 * 报表表头设置dao实现类
 * @author Administrator
 *
 */
@Repository
public class ReportHeadDaoImp extends DaoBaseClass<ReportHead> implements ReportHeadDao{
	/**
	 * 根据父节点查询数据
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ReportHead> queryReportHeaderByPid(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryReportHeaderByPid======start==");
		List<ReportHead> list;
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
			final ReportHead head = new ReportHead();
				head.setId(rs.getLong("id"));
				head.setHname(rs.getString("hName"));
				head.setField(rs.getString("field"));
				head.setValue(rs.getString("value"));
				head.setSort(rs.getInt("sort"));
				head.setLevelcount(rs.getInt("levelcount"));
				head.setParentId(rs.getLong("parentId"));
				head.setReportId(rs.getLong("reportId"));
				return head;
			}
		});
		this.getLogger().info("=========queryReportHeaderByPid======end==");
		return list;
	}
}
