package com.hbcsoft.report.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportSpecialCol;

/**
 * 报表类型
 * @author yangfeng
 * @version 201701
 */
public interface ReportSpecialColService {
	/**
	 * findSpecialColById
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	ReportSpecialCol findByPrimaryKey(String id) throws HbcsoftException;
	/**
	 * 保存
	 * @param specialColList
	 * @throws HbcsoftException 
	 */
	void updateSpecialCol(List<ReportSpecialCol> specialColList) throws HbcsoftException;
	/**
	 * 根据关联主键查询特殊列配置信息
	 * @param specialColumn
	 * @param companyID
	 * @return
	 * @throws HbcsoftException 
	 */
	List<ReportSpecialCol> findByReportConfigId(Long configId, Long companyID) throws HbcsoftException;
	/**
	 * 删除特殊列配置
	 * @param id
	 * @throws HbcsoftException 
	 */
	void delSpecialColConfig(String id) throws HbcsoftException;

}
