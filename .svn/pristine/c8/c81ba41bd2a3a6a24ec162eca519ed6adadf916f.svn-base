package com.hbcsoft.zdy.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

@SuppressWarnings("rawtypes")
public class SplitPage implements ResultSetExtractor {
	private transient final int start;// 起始行号
	private transient final int len;// 结果集合的长度
	private transient final RowMapper rowMapper;// 行包装器

	public SplitPage(final RowMapper rowMapper, final int start, final int len) {
		Assert.notNull(rowMapper, "RowMapper is required");
		this.rowMapper = rowMapper;
		this.start = start;
		this.len = len;
	}

	@SuppressWarnings("unchecked")
	public Object extractData(final ResultSet rs) throws SQLException, DataAccessException {
		final List result = new ArrayList();
		
		for(int index = 0; rs.next() && index < start + len; index++)
		{
			if (index < start) {
				continue;
			}
			result.add(this.rowMapper.mapRow(rs, index));
		}
		return result;
	}
}
