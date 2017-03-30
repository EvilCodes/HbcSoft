package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.DataDict;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 数据字典 DataDictDaoImp
 * @author Administrator
 *
 */
@Repository
public class DataDictDaoImp extends DaoBaseClass<DataDict> implements DataDictDao  {
	/**
	 * 查询所有字典类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<DataDict> queryAllDataDict(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryAllDataDict======start==");
		
		List<DataDict> list = new ArrayList<DataDict>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final DataDict dd = new DataDict();
				dd.setId(rs.getLong("id"));
				dd.setDictCode(rs.getString("dictCode"));
				dd.setDictName(rs.getString("dictName"));
				return dd;
			}
		});
		this.getLogger().info("=========queryAllDataDict======end==");
		return list;
	}
}
