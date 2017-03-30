package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.SubjectDaoImp;
import com.hbcsoft.sys.entity.Subject;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * 操作科目信息
 * @author yangfeng
 * @version 2016-11
 */
@Transactional
@Service("SubjectService")
public class SubjectServiceImp extends LogBaseClass<SubjectServiceImp> implements SubjectService{
	/**
	 * 注入
	 */
	@Autowired
	private HbcsoftJT jt;
	/**
	 * 注入
	 */
	@Autowired
	private SubjectDaoImp subjectDao;
	
	/**
	 * 根据公司id和年度查询
	 */
	@Override
	public List<Subject> queryAllSubjects(String strCompanyId, String year, String isEnable, String subjectType,
			String subjectCode, String subjectName) throws HbcsoftException {
		// TODO Auto-generated method stub
		StringBuffer queryBuffer = new StringBuffer(); 
		List<Subject> subjectList = new ArrayList<Subject>();
		try {
			queryBuffer.append(HbcsoftCache.getSqlValue("subject_queryAllSubjects"));

			if(null != isEnable && !"".equals(isEnable)){
				queryBuffer.append(" AND ISENABLE = '"+isEnable+"'");
			}
			if(null != subjectType && !"".equals(subjectType)){
				queryBuffer.append(" AND SUBJECTTYPE LIKE '%"+subjectType.trim()+"%'");
			}
			if(null != subjectCode && !"".equals(subjectCode)){
				queryBuffer.append(" AND SUBJECTCODE LIKE '%"+subjectCode.trim()+"%'");
			}
			if(null != subjectName && !"".equals(subjectName)){
				queryBuffer.append(" AND SUBJECTNAME LIKE '%"+subjectName.trim()+"%'");
			}
			subjectList = subjectDao.queryAll(queryBuffer.toString(), jt, new Object[] {strCompanyId,year});
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return subjectList;
	}

	/**
	 * 分页条件查询科目信息
	 */
	@Override
	public List<Subject> queryAllSubjects(String strCompanyId, String year, String isEnable, String subjectType,
			String subjectCode, String subjectName, String startRow, String rowsCount) throws HbcsoftException {
		// TODO Auto-generated method stub
		StringBuffer queryBuffer = new StringBuffer(); 
		List<Subject> subjectList = new ArrayList<Subject>();
		queryBuffer.append(HbcsoftCache.getSqlValue("subject_queryAllSubjects"));

		if(null != isEnable && !"".equals(isEnable)){
			queryBuffer.append(" AND ISENABLE = '"+isEnable+"'");
		}
		if(null != subjectType && !"".equals(subjectType)){
			queryBuffer.append(" AND SUBJECTTYPE LIKE '%"+subjectType.trim()+"%'");
		}
		if(null != subjectCode && !"".equals(subjectCode)){
			queryBuffer.append(" AND SUBJECTCODE  LIKE '%"+subjectCode.trim()+"%'");
		}
		if(null != subjectName && !"".equals(subjectName)){
			queryBuffer.append(" AND SUBJECTNAME LIKE '%"+subjectName.trim()+"%'");
		}
		queryBuffer.append(" ORDER BY SUBJECTCODE");
		
		try {
			subjectList = subjectDao.queryAll(
					queryBuffer.toString(), jt, Integer.parseInt(startRow), Integer.parseInt(rowsCount), new Object[] {strCompanyId,year});
		} catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return subjectList;
	}

	/**
	 * 删除科目信息
	 */
	@Override
	public void deleteSubjectById(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		Subject subject = null;
		final String sql = HbcsoftCache.getSqlValue("subject_querySubjectById");
		try {
			subject = subjectDao.query(sql, jt,id);
		
			if(null != subject){
				subjectDao.delete(subject, jt);
			} 
		}catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}	
	}
	/**
	 * 保存科目信息
	 * @throws HbcsoftException 
	 */
	@Override
	public void saveSubject(final Subject subject) throws HbcsoftException {
		// TODO Auto-generated method stub
		subjectDao.save(subject, jt);
	}
	/**
	 * 保存科目信息
	 */
	@Override
	public void saveSubjects(final List<Subject> list) throws HbcsoftException {
		// TODO Auto-generated method stub
		Subject subject = null;
		for (final Iterator<Subject> iter = list.iterator(); iter.hasNext();) {
			subject = (Subject) iter.next();
			subjectDao.save(subject, jt);
		}
	}


	@Override
	public Subject findSubjectById(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		Subject subject = null;
		final String sql = HbcsoftCache.getSqlValue("subject_querySubjectById");
		try {
			subject = subjectDao.query(sql, jt,id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return subject;
	}


	@Override
	public void updateSubject(final Subject subject) throws HbcsoftException {
		// TODO Auto-generated method stub
		subjectDao.update(subject, jt);
	}

	@Override
	public void deleteSubject(final Subject subject) throws HbcsoftException {
		// TODO Auto-generated method stub
		subjectDao.delete(subject, jt);
	}
}
