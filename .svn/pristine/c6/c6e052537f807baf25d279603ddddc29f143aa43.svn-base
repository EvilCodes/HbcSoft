package com.hbcsoft.zdy.common;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hbcsoft.zdy.util.SplitPage;

@Component
public class HbcsoftJT extends JdbcTemplate {
	
	private static final String RAWTYPES = "rawtypes";
	private static final String UNCHECKED = "unchecked";
	
	@Autowired
	private DataSource dataSource;

	public HbcsoftJT() {
		super();
	}

	public HbcsoftJT(final DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@SuppressWarnings({ RAWTYPES })
	public List queryZ(final String sql, final int startRow, final int rowsCount, final Object... param) throws DataAccessException {
		return queryZ(sql, startRow, rowsCount, getColumnMapRowMapper(), param);
	}
	@SuppressWarnings(RAWTYPES)
	public List queryZ(final String sql, final int startRow, final int rowsCount) throws DataAccessException {
		return queryZ(sql, startRow, rowsCount, getColumnMapRowMapper());
	}

	@SuppressWarnings({ UNCHECKED, RAWTYPES })
	public List queryZ(final String sql, final int startRow, final int rowsCount, final RowMapper rowMapper, final Object... param) throws DataAccessException {
		return (List) query(sql, new SplitPage(rowMapper, startRow, rowsCount), param);
	}
	@SuppressWarnings({ UNCHECKED, RAWTYPES })
	public List queryZ(final String sql, final int startRow, final int rowsCount, final RowMapper rowMapper) throws DataAccessException {
		return (List) query(sql, new SplitPage(rowMapper, startRow, rowsCount));
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
		super.setDataSource(dataSource);
	}
}
