package com.hbcsoft.sys.service;



import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.OuterSubject;

public interface OuterSubjectService {
	/**
	 * 查询科目是否存在
	 * @param companyId
	 * @param dbType
	 * @param dbName
	 * @param year
	 * @param subjectCode
	 * @return
	 * @throws HbcsoftException 
	 */
	public OuterSubject findSubjectByCode(long companyId, int dbType, String dbName, String year, String subjectCode) throws HbcsoftException;
	/**
	 * 保存外部科目信息
	 * @param outerSubject
	 * @throws HbcsoftException 
	 */
	public void saveOuterSubjects(OuterSubject outerSubject) throws HbcsoftException;
	/**
	 * 更新科目信息
	 * @param outerSubject
	 * @throws HbcsoftException 
	 */
	public void updateOuterSubject(OuterSubject outerSubject) throws HbcsoftException;
	/**
	 * 科目信息查询
	 * @param companyId
	 * @param dbType
	 * @param dbName
	 * @param year 
	 * @param isEnable 
	 * @param subjectName 
	 * @param subjectType 
	 * @return
	 * @throws HbcsoftException 
	 */
	public List<OuterSubject> queryAll(long companyId, int dbType, String dbName, 
			String year, String subjectType, String subjectName, String isEnable) throws HbcsoftException;
	/**
	 * 分页查询科目信息
	 * @param companyId
	 * @param dbType
	 * @param dbName
	 * @param year
	 * @param subjectType
	 * @param subjectName
	 * @param isEnable
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws HbcsoftException 
	 */
	public List<OuterSubject> queryAllSubjects(long companyId, int dbType, String dbName, String year, String subjectType, 
			String subjectName,String isEnable,String startRow,String pageSize) throws HbcsoftException;
	/**
	 * 根据id查询
	 * @param string
	 * @return 
	 * @throws HbcsoftException 
	 */
	public OuterSubject findSubjectByID(String id) throws HbcsoftException;
	/**
	 * 查询指定公司科目信息
	 * @param companyId
	 * @param subjectCode
	 * @return 
	 * @throws HbcsoftException 
	 */
	public OuterSubject findOuterSubject(String companyId, String subjectCode) throws HbcsoftException;
}
