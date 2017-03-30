package com.hbcsoft.table.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.util.HbcsoftUtil;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.DaoBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;
/**
 * 实现创建表的接口类
 * @author liang
 *
 */
@Repository
public class CreateDBtableDaoImpl extends DaoBaseClass<TableNameClass> implements CreateDBtableDao {

	/**
	 * 查询所有的数据库表名称
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TableNameClass> queryAllTable(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryAllTable======start==");
		
		List<TableNameClass> list = new ArrayList<TableNameClass>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查询表信息
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
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
	 * 保存表
	 */
	@Override
	public Long saveTable(final String sql,final JdbcTemplate jt,final TableNameClass tableName,final String... param) {
		final Long id = SequenceUtil.getTableId("t_tablename");
		jt.update(sql, new Object[]{id, "", HbcsoftUtil.getSysDate(), tableName.getTableName(), tableName.getIsMainTable(),
				tableName.getMainId(), tableName.getMainName(), tableName.getMemo()}, 
				new int[]{java.sql.Types. VARCHAR,java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,
						java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,
						java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR });
		return id;
	}

	/**
	 * 保存字段表
	 */
	@Override
	public void saveTableEntity(final String sql,final JdbcTemplate jt,final List<TableEntity> list) {
		TableEntity tableEntity = new TableEntity();
		final int [] arrInt = new int[]{java.sql.Types. VARCHAR,java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,
				java.sql.Types. VARCHAR ,java.sql.Types. VARCHAR ,java.sql.Types.BIGINT,
				java.sql.Types. BIGINT ,java.sql.Types. BIGINT ,java.sql.Types.VARCHAR,
				java.sql.Types. BIGINT, java.sql.Types. VARCHAR, java.sql.Types. BIGINT};
		for(int i=0; i<list.size(); i++){
			tableEntity = list.get(i);
			jt.update(sql, this.getOb(tableEntity), arrInt);
		}
	}
	/**
	 * 获取Object对象数组
	 * @param tableEntity
	 * @return
	 */
	private Object [] getOb(final TableEntity tableEntity){
		final String id = HbcsoftUtil.uuid();
		final Object [] obj = new Object[]{id, "", HbcsoftUtil.getSysDate(), tableEntity.getName(), tableEntity.getTitle(),
				tableEntity.getType(), tableEntity.getNumber(), tableEntity.getIsDisplay(),
				tableEntity.getIsEdit(),tableEntity.getIsNull(),tableEntity.getDefaultValue(),
				tableEntity.getFlag(), tableEntity.getMainId(), tableEntity.getDecimalDigits()};
		return obj;
	}
	/**
	 * 查询表名称
	 */
	@Override
	public TableNameClass queryTable(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryTable======start==");
		
		final TableNameClass table = new TableNameClass();
		final Map<String, Object> map = jt.queryForMap(sql);
		if(!map.isEmpty()){
			final long id = (Long) map.get("id");
			final String tableName = (String) map.get("tableName");
			final String isMainTable = (String) map.get("isMainTable");
			final Long mainId = (Long) map.get("mainId");
			final String mainName = (String) map.get("mainName");
			final String memo = (String) map.get("memo");
			table.setId(id);
			table.setTableName(tableName);
			table.setIsMainTable(isMainTable);
			table.setMainId(mainId);
			table.setMainName(mainName);
			table.setMemo(memo);
		}
	
		this.getLogger().info("=========queryTable======end==");
		return table;
	}
	/**
	 * 查询字段表集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TableEntity> queryAllEntity(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryAllEntity======start==");
		
		List<TableEntity> list = new ArrayList<TableEntity>();
		list = jt.query(sql, param, new RowMapper(){
			/**
			 * 查询字段
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final TableEntity te = new TableEntity();
				te.setId(rs.getLong("id"));
				te.setCreateID(rs.getLong("createID"));
				te.setCreateTime(rs.getDate("createTime"));
				te.setDefaultValue(rs.getString("defaultValue"));
				te.setFlag(rs.getInt("flag"));
				te.setIsDisplay(rs.getInt("isDisplay"));
				te.setIsEdit(rs.getInt("isEdit"));
				te.setIsNull(rs.getInt("isNull"));
				te.setName(rs.getString("name"));
				te.setNumber(rs.getInt("number"));
				te.setDecimalDigits(rs.getInt("decimalDigits"));
				te.setTitle(rs.getString("title"));
				te.setType(rs.getString("type"));
				te.setMainId(rs.getLong("mainId"));
				return te;
			}
		});
		
		this.getLogger().info("=========queryAllEntity======end==");
		return list;
	}
	/**
	 * 查询表名称
	 */
	@Override
	public TableNameClass queryTableNm(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryTableNm======start==");
		
		final TableNameClass table = new TableNameClass();
		jt.query(sql, param, (RowCallbackHandler)table);
		
		this.getLogger().info("=========queryTableNm======end==");
		return table;
	}

	/**
	 * 查询字段表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TableEntity> queryListEntity(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryListEntity======start==");
		
		List<TableEntity> list = new ArrayList<TableEntity>();
		final TableEntity te = new TableEntity();
		list = jt.query(sql, param, (RowMapper)te);
		
		this.getLogger().info("=========queryListEntity======end==");
		return list;
	}

	/**
	 * 创建表
	 */
	@Override
	public void createSql(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========createSql======start==");
		jt.execute(sql);
		this.getLogger().info("=========createSql======end==");
	}


}
