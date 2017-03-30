package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.ClickManage;
import com.hbcsoft.zdy.common.DaoBaseClass;
import com.hbcsoft.zdy.common.HbcsoftJT;
/**
 * 点选页面管理的Dao接口实现类
 * @author churuifeng
 *
 */
@Repository
public class ClickManageDaoImp extends DaoBaseClass<ClickManage> implements ClickManageDao{

	/**
	 * 查询所有
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ClickManage> queryAllClick(final String sql,final HbcsoftJT jt,final String... param) {
		this.getLogger().info("=========queryAllClick======start==");
		List<ClickManage> list = new ArrayList<ClickManage>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查询表信息
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final ClickManage tn = new ClickManage();
				tn.setId(rs.getLong("id"));
				tn.setClickKey(rs.getString("clickKey"));
				tn.setClickValue(rs.getString("clickValue"));
				tn.setMark(rs.getInt("mark"));
				return tn;
			}
		});
		
		this.getLogger().info("=========queryAllClick======end==");
		return list;
	}
	/**
	 * 修改
	 */
	@Override
	public int updateAll(final String sql,final HbcsoftJT jt,final String... param) {
		this.getLogger().info("=========updateAll======start==");
		int intV=0;
		intV = jt.update(sql, param);
		this.getLogger().info("=========updateAll======end==");
		return intV;
	}
}
