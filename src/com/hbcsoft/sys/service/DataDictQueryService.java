package com.hbcsoft.sys.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DataDict;

/**
 * 数据字典QueryService接口
 * 
 * @author Administrator
 *
 */
public interface DataDictQueryService {
	/**
	 * 查询出所有行
	 * @param dd
	 * @param dtId
	 * @return
	 * @throws HbcsoftException
	 */
	List<DataDict> queryAll(DataDict dd, String dtId, String companyId)
			throws HbcsoftException;

	/**
	 * 分页查询
	 * @param dd
	 * @param dtid
	 * @return
	 * @throws HbcsoftException
	 */
	List<DataDict> queryAllDataDict(DataDict dd, String dtid, String companyId,
			int startRow, int pageSize) throws HbcsoftException;

	/**
	 * 根据id查询数据字典
	 * 
	 * @param id
	 * @return
	 */
	DataDict queryDataDictById(String id) throws HbcsoftException;

	/**
	 * 根据typeid查询是否数据字典
	 * 
	 * @param ids
	 * @return
	 */
	boolean haveRecord(String ids) throws HbcsoftException;

	/**
	 * 查询数据字典（下拉框）
	 * @param dictCode
	 * @param showType
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	// List<DataDict> list = dataDictQueryService.getDataDictList(dictCode,
	// showType);
	List<DataDict> getDataDictList(final String dictCode,
			final String showType, final String companyId)
			throws HbcsoftException;
	/**
	 * 查询状态为启用的数据字典信息
	 * @param dictCode
	 * @param companyId
	 * @return List<DataDict>
	 */
	List<DataDict> getEnabledList(final String dictCode,final String companyId)
			throws HbcsoftException;
}
