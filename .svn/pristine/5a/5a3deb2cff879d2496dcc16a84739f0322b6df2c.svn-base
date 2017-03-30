package com.hbcsoft.report.service;

import java.util.List;



import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportConfigSearch;

/**
 * 报表类型
 * @author yangfeng
 * @version 201612
 */
public interface ReportConfigService {
	/**
	 *	查询报表配置信息
	 * @param strCompanyId
	 * @param reportName
	 * @param remark
	 * @return
	 * @throws HbcsoftException 
	 */
	List<ReportConfig> queryAllReportConfigs(String[] params) throws HbcsoftException;
	/**
	 *	分页查询报表配置信息
	 * @param strCompanyId
	 * @param reportName
	 * @param remark
	 * @return
	 * @throws HbcsoftException 
	 */
	List<ReportConfig> queryAllReportConfigs(String [] params, int startRow,
			int pageSize) throws HbcsoftException;
	
	/**
	 * 保存报表数据
	 * @param reportConfig
	 * @return
	 * @throws HbcsoftException
	 */
	void saveReportConfig(ReportConfig reportConfig) throws HbcsoftException;
	
	/**
	 * 根据公司id和报表id查询报表信息
	 * @param companyId
	 * @param reportName
	 * @param delStatus
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfig queryisRepostConfig(Long companyId,String reportName, int delStatus) throws HbcsoftException;
	
	/**
	 * 保存报表查询字段数据
	 * @param rcs
	 * @throws HbcsoftException
	 */
	void addRCS(List<ReportConfigSearch> rcs) throws HbcsoftException;
	
	/**
	 * 新增报表字段列表显示
	 */
	List<ReportConfigSearch> queryRCSShow (Long reportId,Long companyId) throws HbcsoftException;
	
	/**
	 * 根据reportId和reportSearchId查询报表字段信息
	 * @param intRCRCS
	 * @param strRCRCS
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfigSearch queryRepConSearch( String reportId, Long companyId) throws HbcsoftException;
	
	/**
	 * 根据reportId、reportSearchId和公司id查询报表字段信息
	 */
	ReportConfigSearch queryRepConSearchId( String reportId, Long companyId, String searchId) throws HbcsoftException;
	
	/**
	 * 完善报表字段数据
	 * @param lstRCS
	 * @throws HbcsoftException
	 */
	void saveLstRepConSearch(List<ReportConfigSearch> lstRCS) throws HbcsoftException;
	
	/**
	 * 根据id查找报表数据
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfig selectIDRepCon(String id, Long companyId) throws HbcsoftException;
	
	/**
	 * 修改报表页面时查询表单信息
	 * @param reportid
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportConfigSearch> selectIDRCS(String reportid,Long companyId) throws HbcsoftException;
	
	/**
	 * 修改报表页面信息
	 * @param repCon
	 * @throws HbcsoftException
	 */
	void updateReportConfig(ReportConfig repCon) throws HbcsoftException;
	/**
	 * 修改查询页面信息
	 */
	void updateRepConSearch(final ReportConfigSearch repConsearch) throws HbcsoftException;
	
	/**
	 * 根据报表id、表id 和公司id查询
	 * @param reportId
	 * @param tableId
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportConfigSearch> selectLstRepConSearch(String reportId,String tableId,Long companyId) throws HbcsoftException;
	
	/**
	 * 修改报表字段信息
	 * @param lstRCS
	 * @throws HbcsoftException
	 */
	void updateRepConSearch(List<ReportConfigSearch> lstRCS) throws HbcsoftException;
	
	/**
	 * 查询报表配置的主键、报表名
	 * @param reportId
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfig getReportConfig(final String companyId,final Long reportId)throws HbcsoftException;
	/**
	 * 根据公司id、表id查询
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfig selectIDConfig(final String id, final Long companyId) throws HbcsoftException;
	/**
	 * 根据公司id、表id查询
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportConfigSearch> selectConfigSearch(final String id, final Long companyId) throws HbcsoftException;
	
	/**
	 * 根据表名判断是否被报表中调用
	 * @param tableName 数据库表的表名
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	int getReport(String tableName,long companyId) throws HbcsoftException;
}
