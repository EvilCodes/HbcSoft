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
public interface CreateDBtable2Service {
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param id
	 * @return
	 */
	List<TableEntity> queryAllEntity(String id) throws HbcsoftException;
	/**
	 * 根据表的id查询该表所有的字段属性
	 * @param id
	 * @return
	 */
	//TODO:调用zdy打包方法，根据主表id查询出所有的实体字段
	List<TableEntity> queryListEntity(String id) throws HbcsoftException;
	/**
	 * 修改TableName表数据
	 * @param tableName
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包方法修改数据
	void updateTable(TableNameClass tableName) throws HbcsoftException;
	/**
	 * 修改TableEntity表数据
	 * @param list
	 * @throws HbcsoftException 
	 */
	//TODO:调用zdy打包方法修改数据
	void updateEntity(List<TableEntity> list) throws HbcsoftException;
	/**
	 * 删除tableName表
	 * @param tableName
	 * @throws HbcsoftException
	 */
	//TODO:调用zdy打包方法删除表名称数据
	void deleteTable(TableNameClass tableName) throws HbcsoftException;
	/**
	 * 删除TableEntity表数据
	 * @param list
	 * @throws HbcsoftException
	 */
	//TODO:调用zdy打包方法删除实体表数据
	void deleteEntity(List<TableEntity> list) throws HbcsoftException;
	/**
	 * 删除对应的实体字段
	 * @param id
	 * @throws HbcsoftException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	//TODO:调用zdy打包方法删除对应的某个实体字段值
	void delRow(String id) throws HbcsoftException, InstantiationException, IllegalAccessException;
	/**
	 * 生成创建sql方法
	 * @param operate 0:新增 1：变更 2：删除 3：不变
	 * @param sqlType
	 * @param tn
	 * @param list
	 * @throws HbcsoftException
	 */
	void operateSql(int operate,int sqlType, TableNameClass tn, List<TableEntity> list) throws HbcsoftException;
	/**
	 * 获取list的长度
	 * @param list
	 * @param tName
	 * @return
	 * @throws HbcsoftException
	 */
	int getListSize(List<TableEntity> list,String tName) throws HbcsoftException;
	/**
	 * 获取字段类型
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException 
	 */
	int getFieldData(String id, long companyId) throws HbcsoftException;
}
