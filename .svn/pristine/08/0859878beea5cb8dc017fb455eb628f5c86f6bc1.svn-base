package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.entity.SessionInfo;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface DictTypeQueryService {
	/**
	 * 行
	 * @param dtCode
	 * @param dtName
	 * @param parentId
	 * @return
	 * @throws HbcsoftException
	 */
/*	List<DictType> queryAll(final String dtCode, final String dtName,
			final String parentId,final String companyId) throws HbcsoftException;*/
	List<DictType> queryAll(final  DictType dt,
			final String parentId,final String companyId) throws HbcsoftException;

	/**
	 * 分页
	 * @param parentId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	/*List<DictType> queryAllDictType(final String dtCode, final String dtName,
			final String parentId,final String companyId,
			 final int startRow, final int pageSize)
			throws HbcsoftException;*/
	List<DictType> queryAllDictType(final DictType dt,
			final String parentId,final String companyId, final int startRow, final int pageSize)
			throws HbcsoftException;
	/**
	 * 根据编码或名称,id,companyid查询，是否已经存在
	 * @param companyId
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	//DictType queryByCodeNameId(final DictType dtpage, 
	//final SessionInfo session,final String id)throws HbcsoftException;
	DictType queryByCodeNameId(final DictType dtpage, final String companyId)throws HbcsoftException;
	
	/**
	 * 修改
	 * @param entity
	 * @param session
	 * @param type
	 * @return
	 * @throws HbcsoftException
	 */
	int update(final DictType entity, final SessionInfo session)
			throws HbcsoftException;

	/**
	 * 保存
	 * @param entity
	 * @param session
	 * @param type
	 * @return
	 * @throws HbcsoftException
	 */
	int save(final DictType entity, final SessionInfo session)
			throws HbcsoftException;
}
