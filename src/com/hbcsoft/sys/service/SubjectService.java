package com.hbcsoft.sys.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Subject;

public interface SubjectService {
	/**
	 * 查询公司本年度所有科目
	 * @param strCompanyId
	 * @param year
	 * @param subjectName 
	 * @param subjectCode 
	 * @param subjectType 
	 * @param isEnable 
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Subject> queryAllSubjects(String strCompanyId, String year, String isEnable, String subjectType, String subjectCode, String subjectName) throws HbcsoftException;
	/**
	 * 分页查询公司本年度所有科目
	 * @param strCompanyId
	 * @param year
	 * @param isEnable
	 * @param subjectType
	 * @param subjectCode
	 * @param subjectName
	 * @param createTime
	 * @param string
	 * @param string2
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Subject> queryAllSubjects(String strCompanyId, String year, String isEnable, String subjectType,
			String subjectCode, String subjectName, String string, String string2) throws HbcsoftException;
	/**
	 * 删除
	 * @param string
	 * @throws HbcsoftException 
	 */
	void deleteSubjectById(String id) throws HbcsoftException;
	/**
	 * 保存科目信息
	 * @param subject
	 * @throws HbcsoftException 
	 */
	void saveSubject(Subject subject) throws HbcsoftException;	
	/**
	 * 保存科目信息
	 * @param list
	 * @throws HbcsoftException 
	 */
	void saveSubjects(List<Subject> list) throws HbcsoftException;
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	Subject findSubjectById(String id) throws HbcsoftException;
	/**
	 * 修改科目
	 * @param subject
	 * @throws HbcsoftException 
	 */
	void updateSubject(Subject subject) throws HbcsoftException;
	/**
	 * 删除科目信息
	 * @param subject
	 * @throws HbcsoftException 
	 */
	void deleteSubject(Subject subject) throws HbcsoftException;

}
