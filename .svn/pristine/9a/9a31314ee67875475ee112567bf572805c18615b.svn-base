package com.hbcsoft.table.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;

/**
 * 创建数据库表接口
 * @author liang
 *
 */
@Repository
public interface CreateDBtableDao {
	/**
	 * 查询出所有的数据表名称
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<TableNameClass> queryAllTable(String sql, JdbcTemplate jt, String... param);
	/**
	 * 保存数据表名称
	 * @param sql
	 * @param jt
	 * @param tableName
	 * @param param
	 * @return
	 */
	Long saveTable(String sql, JdbcTemplate jt, TableNameClass tableName, String... param);
	/**
	 * 保存数据表的实体字段
	 * @param sql
	 * @param jt
	 * @param list
	 */
	void saveTableEntity(String sql, JdbcTemplate jt,List<TableEntity> list);
	/**
	 * 查询指定表的内容
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	TableNameClass queryTable(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<TableEntity> queryAllEntity(String sql, JdbcTemplate jt, String... param);
	/**
	 * 查询指定表的内容
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	//TODO:调用zdy打包方法查询
	TableNameClass queryTableNm(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	//TODO：调用zdy打包方法查询所有实体内容
	List<TableEntity> queryListEntity(String sql, JdbcTemplate jt, String... param);
	/**
	 * 创建表
	 * @param sql
	 * @param jt
	 * @param param
	 */
	void createSql(String sql, JdbcTemplate jt, String... param);
}
