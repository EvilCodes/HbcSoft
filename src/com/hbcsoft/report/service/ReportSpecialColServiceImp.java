package com.hbcsoft.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportSpecialColDaoImp;
import com.hbcsoft.report.entity.ReportSpecialCol;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 报表特殊列
 * @author yangfeng
 * @version 201701
 */
@Transactional
@Service("ReportSpecialColService")
public class ReportSpecialColServiceImp extends 
		LogBaseClass<ReportSpecialColServiceImp> implements ReportSpecialColService{
	/**
	 * 注入
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 注入
	 */
	@Autowired
	private transient ReportSpecialColDaoImp reportSpecialColDao;
	
	/**
	 * 根据id查询
	 */
	@Override
	public ReportSpecialCol findByPrimaryKey(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialCol reportSpecialCol = null;
		final String sql = HbcsoftCache.getSqlValue("report_querySpecialColById");
		try {
			reportSpecialCol = reportSpecialColDao.query(sql, jt, id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		return reportSpecialCol;
	}
	/**
	 *保存
	 * @throws HbcsoftException 
	 */
	@Override
	public void updateSpecialCol(final List<ReportSpecialCol> specialColList) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialCol reportSpecialCol = new ReportSpecialCol();
		for(int i=0; i<specialColList.size(); i++){
			reportSpecialCol = specialColList.get(i);
			if(reportSpecialCol.getId() == null || reportSpecialCol.getId() == 0){
				final Long colId = SequenceUtil.getTableId("Z_REPORT_SPECIALCOL");
				reportSpecialCol.setId(colId);
				reportSpecialColDao.save(reportSpecialCol, jt);
			}else{
				reportSpecialColDao.update(reportSpecialCol, jt);
			}
		}
	}
	/**
	 * 查询报表列配置信息
	 * @throws HbcsoftException 
	 */
	@Override
	public List<ReportSpecialCol> findByReportConfigId(final Long configId, final Long companyID) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<ReportSpecialCol> list = null;
		final String sql = HbcsoftCache.getSqlValue("report_querySpecialColConfig");
		try {
			list = reportSpecialColDao.queryAll(sql, jt, companyID,configId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		return list;
	}
	/**
	 * 删除特殊列配置
	 * @throws HbcsoftException 
	 */
	@Override
	public void delSpecialColConfig(String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialCol reportSpecialCol = this.findByPrimaryKey(id);
		reportSpecialColDao.delete(reportSpecialCol, jt);
	}
	
	

}

