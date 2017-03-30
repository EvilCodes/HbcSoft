package com.hbcsoft.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.DaoBaseClass;

/**
 * FormNameDao接口实现类
 * @author 张海姣
 *
 */
@Repository
public class FormNameDaoImpl extends DaoBaseClass<FormName> implements FormNameDao{

	/**
	 * 查询所有的数据库表名称
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<FormName> queryAllTable(final String sql, final JdbcTemplate jt, final String... param) {
		this.getLogger().info("=========queryAllTable======start==");
		
		List<FormName> list = new ArrayList<FormName>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查询表信息
			 */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1) throws SQLException {
				final TableNameClass tn = new TableNameClass();
				tn.setId(rs.getLong("id"));
				tn.setTableName(rs.getString("tableName"));
				tn.setMemo(rs.getString("memo"));
				return tn;
			}
		});
		
		this.getLogger().info("=========queryAllTable======end==");
		return list;
	}
	
	/**
	 * 查询表单关联表信息
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TableNameClass> queryNameEntity(final String sql, final JdbcTemplate jt, final String... param){
		this.getLogger().info("=========queryNameEntity======start==");
		List<TableNameClass> list = new ArrayList<TableNameClass>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查出TableName和TableEntity表中的信息
			 */
			@Override
			public Object mapRow(final ResultSet rs, final int arg1) throws SQLException {
				final TableNameClass tn = new TableNameClass();
				final TableEntity te = new TableEntity();
				final List<TableEntity> listE = new ArrayList<TableEntity>();
				tn.setId(rs.getLong("name.id"));
				tn.setTableName(rs.getString("name.tableName"));
				te.setId(rs.getLong("entity.id"));
				te.setName(rs.getString("entity.name"));
				listE.add(te);
				tn.setTableEntityList(listE);
				return tn;
			}
		});
		this.getLogger().info("=========queryNameEntity======end==");
		return list;
	}
}
