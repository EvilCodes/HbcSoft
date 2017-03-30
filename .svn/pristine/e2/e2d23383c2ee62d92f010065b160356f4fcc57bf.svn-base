package com.hbcsoft.report.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportSpecialRow;

/**
 * 报表类型
 * @author yangfeng
 * @version 201701
 */
public interface ReportSpecialRowService {
	/**
	 * 根据主键查
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	ReportSpecialRow findByPrimaryKey(String id) throws HbcsoftException;
	/**
	 * 保存
	 * @param specialRowList
	 * @throws HbcsoftException 
	 */
	void updateSpecialRow(List<ReportSpecialRow> specialRowList) throws HbcsoftException;
	/**
	 * 查询报表列配置信息
	 * @param specialRow
	 * @param companyID
	 * @return
	 * @throws HbcsoftException 
	 */
	List<ReportSpecialRow> findByReportConfigId(Long configId, Long companyID) throws HbcsoftException;
	/**
	 * 删除特殊行
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	void delSpecialRowConfig(String id) throws HbcsoftException;
	

}
