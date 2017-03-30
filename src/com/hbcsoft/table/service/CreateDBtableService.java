package com.hbcsoft.table.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
/**
 * 创建数据库表接口
 * @author liang
 *
 */
public interface CreateDBtableService {
	/**
	 * 查询出所有的表名称
	 * @return
	 */
	List<TableNameClass> queryAll(String tableName, String memo) throws HbcsoftException;
	/**
	 * 查询出所有的表名称
	 * @return
	 */
	List<TableNameClass> queryAllTable(String tableName, String memo) throws HbcsoftException;
	/**
	 * 查询出所有的表名称
	 * @return
	 */
	//TODO:调用zdy打包查询方法
	List<TableNameClass> queryAllTable(String tableName, String memo, int startRow, int pageSize) throws HbcsoftException;
	/**
	 * 保存数据表名称
	 * @param tableName
	 * @return id
	 */
	Long saveTable(TableNameClass tableName) throws HbcsoftException;
	/**
	 * 保存数据表名称
	 * @param tableName
	 * @return id
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包保存表名方法
	Long saveTableName(TableNameClass tableName) throws HbcsoftException;
	/**
	 * 保存数据表的实体字段
	 * @param list
	 */
	void saveTableEntity(List<TableEntity> list) throws HbcsoftException;
	/**
	 * 保存数据表的实体字段
	 * @param list
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包保存实体字段方法
	void saveEntity(List<TableEntity> list) throws HbcsoftException;
	/**
	 * 查询主表信息
	 * @param id
	 * @return
	 */
	TableNameClass table(String id) throws HbcsoftException;
	/**
	 * 查询主表信息
	 * @param id
	 * @return
	 */
	//TODO:调用zdy打包方法，根据id查询主表内容信息
	TableNameClass tableNm(String id) throws HbcsoftException;

}
