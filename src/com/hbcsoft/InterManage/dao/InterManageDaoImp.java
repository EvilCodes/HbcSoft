package com.hbcsoft.InterManage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.DaoBaseClass;
import com.hbcsoft.zdy.common.HbcsoftJT;

@Repository
public class InterManageDaoImp extends DaoBaseClass<PrimaryList> implements InterManageDao{

	@Override
	public List<PrimaryList> queryAllPrimaryList(String sql, JdbcTemplate jt,
			String... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long savePrimaryList(String sql, JdbcTemplate jt,
			PrimaryList primaryList, String... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSublist(String sql, JdbcTemplate jt, List<Sublist> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PrimaryList queryTable(String sql, JdbcTemplate jt, String... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sublist> queryAllEntity(String sql, JdbcTemplate jt,
			String... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrimaryList queryPrimaryNm(String sql, JdbcTemplate jt,
			String... param) {
		this.getLogger().info("=========queryPrimaryNm======start==");
		final PrimaryList primaryList = new PrimaryList();
		jt.query(sql, param, (RowCallbackHandler)primaryList);
		this.getLogger().info("=========queryPrimaryNm======end==");
		return primaryList;
	}

	@Override
	public List<Sublist> queryListEntity(String sql, JdbcTemplate jt,
			String... param) {
		this.getLogger().info("=========queryListEntity======start==");
		
		List<Sublist> list = new ArrayList<Sublist>();
		final Sublist sl = new Sublist();
		list = jt.query(sql, param, (RowMapper)sl);
		
		this.getLogger().info("=========queryListEntity======end==");
		return list;
	}

	/**
	 * 查询所有的接口管理类名称
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PrimaryList> queryAllTable(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryAllTable======start==");
		
		List<PrimaryList> list = new ArrayList<PrimaryList>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查询表信息
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final PrimaryList tn = new PrimaryList();
				tn.setId(rs.getLong("id"));
				tn.setClassName(rs.getString("className"));
				tn.setMemo(rs.getString("memo"));
				return tn;
			}
		});
		
		this.getLogger().info("=========queryAllTable======end==");
		return list;
	}
}
