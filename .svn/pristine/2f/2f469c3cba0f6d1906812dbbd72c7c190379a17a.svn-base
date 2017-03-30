package com.hbcsoft.sys.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 字典类型 daoimp
 * @author Administrator
 *
 */
@Repository
public class DictTypeDaoImp extends DaoBaseClass<DictType> implements  DictTypeDao  {
	
	/**
	 * 查询所有字典类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<DictType> queryAllDictType(final String sql,
			final JdbcTemplate jt, final String... param) {
		this.getLogger().info("=========queryAllDictType======start==");

		List<DictType> list;
		list = jt.query(sql, param, new RowMapper() {
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1)
					throws SQLException {
				final DictType dt = new DictType();
				dt.setId(rs.getLong("id"));
				dt.setDtCode(rs.getString("dtCode"));
				dt.setDtName(rs.getString("dtName"));
				return dt;
			}
		});
		this.getLogger().info("=========queryAllDictType======end==");
		return list;
	}
	/**
	 * 根据父节点查询
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DictType> queryDictTypeByPid(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryDictTypeByPid======start==");
		List<DictType> list;
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
			final DictType dt = new DictType();
				dt.setId(rs.getLong("id"));
				dt.setDtCode(rs.getString("dtCode"));
				dt.setDtName(rs.getString("dtName"));
				dt.setEnable(Integer.parseInt(rs.getString("enable")));
				dt.setSort(Integer.parseInt(rs.getString("sort")));
				return dt;
			}
		});
		this.getLogger().info("=========queryDictTypeByPid======end==");
		return list;
	}
	/**
	 * 根据ids
	 */
	@Override
	public DictType queryDataByIds(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryDataByIds======start==");
		final DictType dt = new DictType();
		jt.query(sql, param, (RowCallbackHandler)dt);
		this.getLogger().info("=========queryDataByIds======end==");
		return dt;
	}
	
	/**
	 * 根据id查询
	 */
	public DictType queryDictTypeById(final String sql,final JdbcTemplate jt,final String... param){
		this.getLogger().info("=========queryDataByIds======start==");
		final DictType dt = new DictType();
		jt.query(sql, param, (RowCallbackHandler)dt);
		this.getLogger().info("=========queryDataByIds======end==");
		return dt;
	}
	
}
