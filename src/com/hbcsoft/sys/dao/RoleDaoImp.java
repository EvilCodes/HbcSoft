package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.Role;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 角色查询dao实现类
 * */
@Repository
public class RoleDaoImp extends DaoBaseClass<Role> implements RoleDao {
	/**
	 * 查询所有
	 * */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Role> queryAllRole(final String sql, final JdbcTemplate jt, final String... param) {
		this.getLogger().info("=========queryAllRole======start==");

		List<Role> list = new ArrayList<Role>();
		list = jt.query(sql, new RowMapper() {
			/**
			 * 把值放到对象
			 * */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1) throws SQLException {
				final Role role = new Role();
				role.setId(rs.getLong("id"));
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				role.setOrderNo(Integer.parseInt(rs.getString("orderNo")));
				role.setRemark(rs.getString("remark"));
				return role;
			}
		});
		this.getLogger().info("=========queryAllRole======end==");
		return list;
	}
	/**
	 * 根据id查询
	 */
	public Role queryById(final String sql,final JdbcTemplate jt,final String... param){
		this.getLogger().info("=========queryById======start==");
		final Role role = new Role();
		jt.query(sql, param, (RowCallbackHandler)role);
		this.getLogger().info("=========queryById======end==");
		return role;
	}
	
}
