package com.hbcsoft.sys.service;



import org.springframework.stereotype.Repository;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DataDict;
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.entity.SessionInfo;

/**
 * 数据字典service接口
 * @author Administrator
 *
 */
@Repository
public interface DataDictService {
	/**
	 * 查询出所有行
	 * @param dictCode
	 * @param dictName
	 * @param dtId
	 * @return
	 * @throws HbcsoftException
	 */
	// List<DataDict> queryAll(DataDict dd,String dtId) throws HbcsoftException;
	/**
	 * 分页查询
	 * @param dictCode
	 * @param dictName
	 * @param dtid
	 * @return
	 * @throws HbcsoftException
	 */
//	 List<DataDict> queryAllDataDict(DataDict dd,String dtid,
//			 int startRow, int pageSize) throws HbcsoftException;
	/**
	 * 根据字典类型id查询字典编码、名称、排序
	 * @param id
	 * @return
	 */
	DictType queryDictType(String id) throws HbcsoftException;
	/**
	 * 根据id查询数据字典
	 * @param id
	 * @return
	 */
	// DataDict queryDataDictById(String id) throws HbcsoftException;
	/**
	 * 修改
	 * @param entity
	 * @param company
	 * @param user
	 * @param type
	 * @return
	 */
//	 int update(DataDict entity) throws HbcsoftException ;
	int update(final DataDict entity, SessionInfo session,
				final DictType type) throws HbcsoftException;
	/**
	 * 保存
	 * @param entity
	 * @param company
	 * @param user
	 * @param type
	 * @return
	 */
//		 int save(DataDict entity) throws HbcsoftException;
	int save(final DataDict entity, SessionInfo session,
				final DictType type) throws HbcsoftException;
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	//int remove(DataDict entity) throws HbcsoftException;
	void queryIds(String ids) throws HbcsoftException;
	/**
	 * 根据typeid查询是否数据字典
	 * @param ids
	 * @return
	 */
	// boolean haveRecord(String ids) throws HbcsoftException;
	/**
	 * 添加之前，根据编码或名称和公司id判断数据库里是否已经存在
	 * @param dictCode
	 * @param dictName
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	DataDict queryByCodeName(final String dictCode,final String dictName,final String companyId) throws HbcsoftException;
	/**
	 * 修改之前，根据编码或名称和公司id和主键id判断数据库里是否已经存在切除去本身的这一条数据
	 * @param ddPage
	 * @param companyId
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	DataDict queryByCodeNameId(final DataDict ddPage,final String companyId) throws HbcsoftException;
	
}
