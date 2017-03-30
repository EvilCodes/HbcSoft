package com.hbcsoft.sys.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.OuterSubjectDaoImp;
import com.hbcsoft.sys.entity.OuterSubject;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Transactional
@Service("OuterSubjectService")
public class OuterSubjectServiceImp  extends LogBaseClass<OuterSubjectServiceImp> implements OuterSubjectService{
	
	@Autowired
	private HbcsoftJT jt;
	@Autowired
	private OuterSubjectDaoImp outerSubjectDao;
	
	@Override
	public void saveOuterSubjects(OuterSubject outerSubject) throws HbcsoftException {
		// TODO Auto-generated method stub
		outerSubjectDao.save(outerSubject, jt);
	}

	@Override
	public OuterSubject findSubjectByCode(long companyId, int dbType, String dbName, String year,String subjectCode) throws HbcsoftException{
		// TODO Auto-generated method stub
		OuterSubject  outerSubject = null;
		
		String strsql = HbcsoftCache.getSqlValue("outerSubject_querySubjectByCode");
		
		try {
			outerSubject =outerSubjectDao
					.query(strsql, jt, Long.toString(companyId),Integer.toString(dbType),dbName,year,subjectCode);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
	
		return outerSubject;
	}

	@Override
	public void updateOuterSubject(OuterSubject outerSubject)  {
		// TODO Auto-generated method stub
		try {
			outerSubjectDao.update(outerSubject, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<OuterSubject> queryAll(long companyId, int dbType, String dbName,
			String year, String subjectType, String subjectName, String isEnable) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<OuterSubject> subjects = new ArrayList<OuterSubject>();
		
		String strsql = HbcsoftCache.getSqlValue("outerSubject_queryAllSubjects");
		
		if(null != subjectType && !"".equals(subjectType) ){
			strsql += " AND SUBJECTTYPE LIKE '%"+subjectType.trim()+"%'";
		}
		if(null != subjectName && !"".equals(subjectName) ){
			strsql += " AND SUBJECTNAME LIKE '%"+subjectName.trim()+"%'";
		}
		if(null != isEnable && !"".equals(isEnable) ){
			strsql += " AND ISENABLE = '"+isEnable+"' ";
		}
		
		try {
			subjects =(List<OuterSubject>) outerSubjectDao
					.queryAll(strsql, jt,Long.toString(companyId),Integer.toString(dbType),dbName,year);
		} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
	
		return subjects;
	}

	@Override
	public List<OuterSubject> queryAllSubjects(long companyId, int dbType, String dbName,String year, String subjectType, 
			String subjectName,String isEnable,String startRow,String rowsCount) throws HbcsoftException{
		// TODO Auto-generated method stub
		List<OuterSubject> subjectsList = new ArrayList<OuterSubject>();
		
		String strsql = HbcsoftCache.getSqlValue("outerSubject_queryAllSubjects");
		
		if(null != subjectType && !"".equals(subjectType) ){
			strsql += " AND SUBJECTTYPE LIKE '%"+subjectType.trim()+"%'";
		}
		if(null != subjectName && !"".equals(subjectName) ){
			strsql += " AND SUBJECTNAME LIKE '%"+subjectName.trim()+"%'";
		}
		if(null != isEnable && !"".equals(isEnable) ){
			strsql += " AND ISENABLE = '"+isEnable+"' ";
		}
		strsql += " ORDER BY SUBJECTCODE";
		
			try {
				subjectsList = outerSubjectDao
						.queryAll(strsql, jt, Integer.parseInt(startRow),Integer.parseInt(rowsCount), 
								new Object[] {Long.toString(companyId),Integer.toString(dbType),dbName,year});
			} catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
			}
		return subjectsList;
	}

	@Override
	public OuterSubject findSubjectByID(String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		OuterSubject outerSubject = new OuterSubject();
		String strSql = HbcsoftCache.getSqlValue("outerSubject_querySubjectById");
		try {
			outerSubject = outerSubjectDao.query(strSql, jt,id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerSubject;
	}

	@Override
	public OuterSubject findOuterSubject(String companyId, String subjectCode) throws HbcsoftException {
		// TODO Auto-generated method stub
		OuterSubject outerSubject = new OuterSubject();
		String strSql = HbcsoftCache.getSqlValue("outerSubject_querySubjectByCompany");
		try {
			outerSubject = outerSubjectDao.query(strSql, jt,new String [] {companyId, subjectCode});
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerSubject;

	}

}
