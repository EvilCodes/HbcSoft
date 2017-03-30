package com.hbcsoft.zdy.template.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.service.FormTableServiceImpl;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.sql.SqlTools;
import com.hbcsoft.zdy.template.dao.TemplateDao;

@Transactional
@Service("templateService")
public class TemplateServiceImp extends LogBaseClass<FormTableServiceImpl> implements TemplateService {

	@Autowired
	private transient HbcsoftJT jt;
	@Autowired
	private transient TemplateDao templateDao;
	@Override
	public List<List<FormFields>> queryAll(final List<FormFields> lstFF, final List<String> lstTn,final String formType) throws HbcsoftException {
		String sql;
		final List<String> lstV = new ArrayList<String>();
		final SqlTools sq = new SqlTools();
		
		try {
			sql = sq.queryAllSql(lstTn, lstFF, lstV,formType);
			return templateDao.queryAll(lstFF, jt, sql, lstV.toArray());
		} catch (HbcsoftException e) {
			getLogger().info(e);
			throw e;
		}
	}

	@Override
	public void query(final List<FormFields> lstFF, final List<String> lstTn, final String zid) throws HbcsoftException {
		final SqlTools sq = new SqlTools();
		
		try {
			for (int index = 0; index < lstTn.size(); index++) {
				templateDao.query(lstFF, jt, sq.querySql(lstTn.get(index)), zid);
			}			
		} catch (HbcsoftException e) {
			getLogger().info(e);
			throw e;
		}
	}
	
	@Override
	public void save(final List<FormFields> lstff, final List<String> lstTn) throws HbcsoftException {

		try {
			for (int index = 0; index < lstTn.size(); index++) {
				final int intV = save(lstTn.get(index), lstff);
				if(intV < 1)
				{
					throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, "保存失败！");
				}
			}
		} catch (HbcsoftException e) {
			getLogger().info(e);
			throw e;
		}
	}
	
	@Override
	public void delete(final List<String> lstTn, final String zid) throws HbcsoftException {

		try {
			final SqlTools sqlTools = new SqlTools();
			
			for (int index = 0; index < lstTn.size(); index++) {
				final String sql = sqlTools.deleteSql(lstTn.get(index));
				final int intV = jt.update(sql, zid);
				if(intV < 1)
				{
					throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, "删除失败！");
				}
			}
		} catch (HbcsoftException e) {
			getLogger().info(e);
			throw e;
		}
	}
	
	public void update(final List<FormFields> lstff, final List<String> lstTn) throws HbcsoftException {

		try {
			for (int index = 0; index < lstTn.size(); index++) {
				final int intV = update(lstTn.get(index), lstff);
				if(intV < 1)
				{
					throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, "更新失败！");
				}
			}
		} catch (HbcsoftException e) {
			getLogger().info(e);
			throw e;
		}
	}
	
	private int update(final String tn, final List<FormFields> lstff) throws HbcsoftException
	{
		final List<Object> lstV = new ArrayList<Object>();
		final String sql = new SqlTools().updateSql(tn, lstff, lstV);
		return jt.update(sql, lstV.toArray());
	}
	
	private int save(final String tn, final List<FormFields> lstff) throws HbcsoftException
	{
		final List<Object> lstV = new ArrayList<Object>();
		final String sql = new SqlTools().insertSql(tn, lstff, lstV);
		return jt.update(sql, lstV.toArray());
	}
}
