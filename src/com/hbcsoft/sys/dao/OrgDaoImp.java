package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.zdy.common.DaoBaseClass;

/**
 * 机构管理
 *
 * @author Administrator
 */
@Repository
public class OrgDaoImp extends DaoBaseClass<Org> implements OrgDao {
	/**
	 * 查询所有字典类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Org> queryAllOrg(final String sql, final JdbcTemplate jt,
			final String... param) {
		this.getLogger().info("=========queryAllDictType======start==");
		List<Org> list;
		list = jt.query(sql, param, new RowMapper() {
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1)
					throws SQLException {
				final Org org = new Org();
				org.setId(rs.getLong("id"));
				org.setCode(rs.getString("Code"));
				org.setName(rs.getString("Name"));
				return org;
			}
		});

		this.getLogger().info("=========queryAllDictType======end==");
		return list;
	}

	/**
	 * 根据父节点查询
	 * 
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Org> queryOrgByPid(final String sql, final JdbcTemplate jt,
			final String... param) {
		this.getLogger().info("=========queryDictTypeByPid======start==");
		List<Org> list;
		list = jt.query(sql, param, new RowMapper() {
			/**
			 * mapRow
			 */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1)
					throws SQLException {
				final Org org = new Org();
				org.setId(rs.getLong("id"));
				org.setCode(rs.getString("Code"));
				org.setName(rs.getString("Name"));
				org.setEnable(Integer.parseInt(rs.getString("enable")));
				org.setOrderNo(Integer.parseInt(rs.getString("orderNo")));

				return org;
			}
		});
		this.getLogger().info("=========queryDictTypeByPid======end==");
		return list;
	}

	/**
	 * 根据ids
	 */
	@Override
	public Org queryDataByIds(final String sql, final JdbcTemplate jt,
			final String... param) {
		this.getLogger().info("=========queryDataByIds======start==");

		final Org org = new Org();
		jt.query(sql, param, (RowCallbackHandler) org);
		this.getLogger().info("=========queryDataByIds======end==");
		return org;
	}

	/**
	 * 根据id查询
	 */
	public Org queryOrgById(final String sql, final JdbcTemplate jt,
			final String... param) {
		this.getLogger().info("=========queryDataByIds======start==");
		final Org org = new Org();
		jt.query(sql, param, (RowCallbackHandler) org);
		this.getLogger().info("=========queryDataByIds======end==");
		return org;
	}
}
