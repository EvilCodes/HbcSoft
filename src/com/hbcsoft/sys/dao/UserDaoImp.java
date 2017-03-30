package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 用户DAO层实现类
 * @author Administrator
 *
 */
@Repository
public class UserDaoImp extends DaoBaseClass<User> implements UserDao {
	
	@Override
	/**
	 * 登录验证
	 */
	public User getLoginUser(final String sql,final  JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========getLoginUser======start==");
		final User user = new User();
		jt.query(sql, param,new RowCallbackHandler() {
			/**
			 * processRow
			 */
					public void processRow(final ResultSet rs) throws SQLException {
						user.setId(rs.getLong("id"));
						user.setName(rs.getString("NAME"));
					}
				});
		this.getLogger().info("=========getLoginUser======start==");
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * 查询所有
	 */
	public List<User> queryAll(final String sql,final JdbcTemplate jt,final String... param){
		this.getLogger().info("=========queryAll======start==");
		List<User> list = new ArrayList<User>();
		list = jt.query(sql, param, (RowMapper)list);
		
		this.getLogger().info("=========queryAll======start==");
		return list;
	}
	/**
	 * 修改
	 */
	@Override
	public int updateAll(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========updateAll======start==");
		int intV=0;
		intV = jt.update(sql, param);
		this.getLogger().info("=========updateAll======end==");
		return intV;
	}
	

}
