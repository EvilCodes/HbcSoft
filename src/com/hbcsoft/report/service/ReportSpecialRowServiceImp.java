package com.hbcsoft.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportSpecialRowDaoImp;
import com.hbcsoft.report.entity.ReportSpecialRow;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 报表特殊行
 * @author yangfeng
 * @version 201701
 */
@Transactional
@Service("ReportSpecialRowService")
public class ReportSpecialRowServiceImp extends 
		LogBaseClass<ReportSpecialRowServiceImp> implements ReportSpecialRowService{
	/**
	 * 注入
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 注入
	 */
	@Autowired
	private transient ReportSpecialRowDaoImp reportSpecialRowDao;
	/**
	 * 根据主键查报表特殊行配置
	 * @throws HbcsoftException 
	 */
	@Override
	public ReportSpecialRow findByPrimaryKey(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialRow reportSpecialRow = null;
		final String sql = HbcsoftCache.getSqlValue("report_querySpecialRowById");
		try {
			reportSpecialRow = reportSpecialRowDao.query(sql, jt, id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		return reportSpecialRow;
	}
	/**
	 * 保存
	 */
	@Override
	public void updateSpecialRow(final List<ReportSpecialRow> specialRowList) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialRow reportSpecialRow = new ReportSpecialRow();
		for(int i=0; i<specialRowList.size(); i++){
			reportSpecialRow = specialRowList.get(i);
			if(reportSpecialRow.getId() == null || reportSpecialRow.getId() == 0){
				final Long rowId = SequenceUtil.getTableId("Z_REPORT_SPECIALROW");
				reportSpecialRow.setId(rowId);
				reportSpecialRowDao.save(reportSpecialRow, jt);
			}else{
				reportSpecialRowDao.update(reportSpecialRow, jt);
			}
		}
	}
	/**
	 * 查询报表列配置信息
	 * @throws HbcsoftException 
	 */
	@Override
	public List<ReportSpecialRow> findByReportConfigId(final Long configId, final Long companyID) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<ReportSpecialRow> list = null;
		final String sql = HbcsoftCache.getSqlValue("report_querySpecialRowConfig");
		try {
			list = reportSpecialRowDao.queryAll(sql, jt, companyID,configId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		return list;
	}
	/**
	 * 删除特殊行
	 * @throws HbcsoftException 
	 */
	@Override
	public void delSpecialRowConfig(String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportSpecialRow reportSpecialRow = this.findByPrimaryKey(id);
		reportSpecialRowDao.delete(reportSpecialRow, jt);
	}
	


}

