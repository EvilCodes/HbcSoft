package com.hbcsoft.report.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.report.entity.ReportRow;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 表体数据访问层
 * */
@Repository
public class ReportRowDaoImp extends DaoBaseClass<ReportRow> implements ReportRowDao {
	
	/**
	 * 根据父节点查询
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ReportRow> queryByPid(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryByPid======start==");
		List<ReportRow> list;
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
			final ReportRow repRow = new ReportRow();
				repRow.setId(rs.getLong("id"));
				repRow.setFiled(rs.getString("filed"));
				repRow.setValue(rs.getString("value"));
				repRow.setHname(rs.getString("hname"));
				repRow.setDelflag(rs.getInt("delflag"));
				repRow.setLevelcount(rs.getInt("levelcount"));
				repRow.setParentId(rs.getLong("parentId"));
				repRow.setReportId(rs.getLong("reportId"));
				repRow.setSort(rs.getInt("sort"));
				repRow.setStatus(rs.getString("status"));
				repRow.setIsEnd(rs.getInt("isEnd"));
				return repRow;
			}
		});
		this.getLogger().info("=========queryByPid======end==");
		return list;
	}

}
