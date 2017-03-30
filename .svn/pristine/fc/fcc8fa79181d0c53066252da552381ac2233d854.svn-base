package com.hbcsoft.InterManage.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.HbcsoftJT;

/**
 * 接口管理 
 * @author gaodekui
 *
 */
public interface InterManageDao {

	/**
	 * 查询出所有的主表名称
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<PrimaryList> queryAllPrimaryList(String sql, JdbcTemplate jt, String... param);
	/**
	 * 保存主表名称
	 * @param sql
	 * @param jt
	 * @param tableName
	 * @param param
	 * @return
	 */
	Long savePrimaryList(String sql, JdbcTemplate jt, PrimaryList primaryList, String... param);
	/**
	 * 保存子表表的实体字段
	 * @param sql
	 * @param jt
	 * @param list
	 */
	void saveSublist(String sql, JdbcTemplate jt,List<Sublist> list);
	/**
	 * 查询指定表的内容
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	PrimaryList queryTable(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<Sublist> queryAllEntity(String sql, JdbcTemplate jt, String... param);
	/**
	 * 查询指定表的内容
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	//TODO:调用zdy打包方法查询
	PrimaryList queryPrimaryNm(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	//TODO：调用zdy打包方法查询所有实体内容
	List<Sublist> queryListEntity(String sql, JdbcTemplate jt, String... param);
	
	/**
	 * 查询所有的数据库表名称
	 */
	List<PrimaryList> queryAllTable(String sql, JdbcTemplate jt, String... param);
}
