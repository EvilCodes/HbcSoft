package com.hbcsoft.InterManage.service;

import java.util.List;

import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;

public interface InterManageService {
	
	List<PrimaryList> queryAll(String tableName, String memo) throws HbcsoftException;
	
	List<PrimaryList> queryAllTable(String tableName, String memo) throws HbcsoftException;
	
	List<PrimaryList> queryAllTable(final String tableName,final String memo,final int startRow,final int pageSize) throws HbcsoftException;
	
	Long savePrimaryList(PrimaryList primaryList)throws HbcsoftException;
	
	void saveEntity(List<Sublist> list)throws HbcsoftException;
	
	PrimaryList primaryNm(String id) throws HbcsoftException;
	
	List<Sublist> querySublist(String id) throws HbcsoftException;
	
	void updateTable(PrimaryList primaryList) throws HbcsoftException;
	
	void updateEntity(final List<Sublist> list) throws HbcsoftException;

	void deleteTable(PrimaryList primaryList)throws HbcsoftException;

	List<Sublist> queryListEntity(String string)throws HbcsoftException;

	void deleteEntity(List<Sublist> list)throws HbcsoftException;

	void delRow(String rowId) throws HbcsoftException, InstantiationException, IllegalAccessException;
}
