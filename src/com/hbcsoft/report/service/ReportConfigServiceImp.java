package com.hbcsoft.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportConfigDaoImp;
import com.hbcsoft.report.dao.ReportConfigSearchDaoImp;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportConfigSearch;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 报表类型
 * @author yangfeng
 * @version 201612
 */
@Transactional
@Service("ReportConfigService")
public class ReportConfigServiceImp extends 
		LogBaseClass<ReportConfigServiceImp> implements ReportConfigService{
	/**
	 * 注入
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 注入
	 */
	@Autowired
	private transient ReportConfigDaoImp reportConfigDao;
	
	/**
	 * 注入
	 */
	@Autowired
	private transient ReportConfigSearchDaoImp reportConfigSearchDao;
	/**
	 *	查询报表配置信息
	 * @param strCompanyId
	 * @param reportName
	 * @param remark
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public List<ReportConfig> queryAllReportConfigs(final String[] params) throws HbcsoftException {
		final String strCompanyId = params[0];
		final String reportName = params[1];
		final String reportType = params[2];
		final String headType = params[3];
		final String bodyType = params[4];
		final String dataConfig = params[5];
		final StringBuffer sb = new StringBuffer(200); 
		List<ReportConfig> reportConfigs = new ArrayList<>();
		
		sb.append(HbcsoftCache.getSqlValue("report_queryAllReportConfig"));
		if(null != reportName && !"".equals(reportName)){
			sb.append(" AND REPORTNAME like '%"+reportName.trim()+"%'");
		}
		if(null != reportType && !"".equals(reportType)){
			sb.append(" AND REPORTTYPE like '%"+reportType.trim()+"%'");
		}
		if(null != headType && !"".equals(headType)){
			sb.append(" AND HEADTYPE = '"+headType+"'");
		}
		if(null != bodyType && !"".equals(bodyType)){
			sb.append(" AND BODYTYPE = '"+bodyType+"'");
		}
		if(null != dataConfig && !"".equals(dataConfig)){
			sb.append(" AND DATACONFIG like '"+dataConfig+"'");
		}
		try {
			reportConfigs = reportConfigDao.queryAll(sb.toString(), jt, strCompanyId);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return reportConfigs;
	}
	/**
	 *	分页查询报表配置信息
	 * @param strCompanyId
	 * @param reportName
	 * @param remark
	 * @return
	 * @throws HbcsoftException 
	 */
	@Override
	public List<ReportConfig> queryAllReportConfigs(final String [] params,
			final int startRow,final int pageSize) throws HbcsoftException {
		final String strCompanyId = params[0];
		final String reportName = params[1];
		final String reportType = params[2];
		final String headType = params[3];
		final String bodyType = params[4];
		final String dataConfig = params[5];
		final StringBuffer sb = new StringBuffer(200); 
		List<ReportConfig> reportConfigs = new ArrayList<>();
		
		sb.append(HbcsoftCache.getSqlValue("report_queryAllReportConfig"));
		
		if(null != reportName && !"".equals(reportName)){
			sb.append(" AND REPORTNAME like '%"+reportName.trim()+"%'");
		}
		if(null != reportType && !"".equals(reportType)){
			sb.append(" AND REPORTTYPE like '%"+reportType.trim()+"%'");
		}
		if(null != headType && !"".equals(headType)){
			sb.append(" AND HEADTYPE like '%"+headType.trim()+"%'");
		}
		if(null != bodyType && !"".equals(bodyType)){
			sb.append(" AND BODYTYPE like '%"+bodyType.trim()+"%'");
		}
		if(null != dataConfig && !"".equals(dataConfig)){
			sb.append(" AND DATACONFIG like '%"+dataConfig.trim()+"%'");
		}
		try {
			reportConfigs = reportConfigDao.queryAll(sb.toString(), jt, startRow, pageSize, new Object[] {strCompanyId});
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return reportConfigs;
	}
	
	/**
	 * 保存报表数据
	 */
	@Override
	public void saveReportConfig(final ReportConfig reportConfig) throws HbcsoftException{
		reportConfigDao.save(reportConfig, jt);
	}
	
	/**
	 * 根据公司id和报表id查询报表信息
	 */
	@Override
	public ReportConfig queryisRepostConfig(final Long companyId,final String reportName, final int delStatus)
			throws HbcsoftException{
		this.getLogger().info("============queryisRepostConfig=========start==");
		ReportConfig repCon = new ReportConfig();
		final List<String> params = new ArrayList<String>();
		params.add(reportName);
		params.add(String.valueOf(delStatus));
		params.add(String.valueOf(companyId));
		final String sql = HbcsoftCache.getSqlValue("report_queryRepCon");
		try {
			repCon = reportConfigDao.query(sql, jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryisRepostConfig=========end==");
		return repCon;
	}
	
	/**
	 * 保存报表查询字段数据
	 */
	@Override
	public void addRCS(final List<ReportConfigSearch> rcs) throws HbcsoftException{
		this.getLogger().info("============addRCS=========start==");
		for(int i=0; i<rcs.size(); i++){
			final ReportConfigSearch tc = rcs.get(i);
			reportConfigSearchDao.save(tc, jt);
		}
		this.getLogger().info("============addRCS=========end==");
	}
	
	/**
	 * 查询报表字段表
	 */
	@Override
	public List<ReportConfigSearch> queryRCSShow (final Long reportId,final Long companyId) throws HbcsoftException{
		this.getLogger().info("============queryRCSShow=========start==");
		List<ReportConfigSearch> lrcs = new ArrayList<ReportConfigSearch>();
		final List<String> params = new ArrayList<String>();
		params.add(String.valueOf(reportId));
		params.add(String.valueOf(companyId));
		final String sql = HbcsoftCache.getSqlValue("report_queryRCS");
		try {
			lrcs = reportConfigSearchDao.queryAll(sql, jt, params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		this.getLogger().info("============queryRCSShow=========end==");
		return lrcs;
	}
	
	/**
	 * 根据reportId和reportSearchId查询报表字段信息
	 * @param intRCRCS
	 * @param strRCRCS
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	@Override
	public ReportConfigSearch queryRepConSearch( final String reportId, final Long companyId) throws HbcsoftException{
		this.getLogger().info("============queryRepConSearch=========start==");
		final List<String> params = new ArrayList<String>();
		params.add(String.valueOf(reportId));
		params.add(String.valueOf(companyId));
		ReportConfigSearch repConSearch = new ReportConfigSearch();
		final String sql = HbcsoftCache.getSqlValue("report_queryRepConSearch");
			try {
				repConSearch =  reportConfigSearchDao.query(sql, jt,params.toArray());
			} catch (InstantiationException |IllegalAccessException e) {
				this.getLogger().info(e);
			}
		this.getLogger().info("============queryRepConSearch=========end==");
		return repConSearch;
	}
	
	/**
	 * 根据reportId和reportSearchId查询报表字段信息
	 * @param intRCRCS
	 * @param strRCRCS
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	@Override
	public ReportConfigSearch queryRepConSearchId(final String reportId, final Long companyId, final String searchId) throws HbcsoftException{
		this.getLogger().info("============queryRepConSearchId=========start==");
		final List<String> params = new ArrayList<String>();
		params.add(String.valueOf(reportId));
		params.add(String.valueOf(companyId));
		params.add(String.valueOf(searchId));
		ReportConfigSearch repConSearch = new ReportConfigSearch();
		final String sql = HbcsoftCache.getSqlValue("report_queryRepConSearchId");
		try {
			repConSearch =  reportConfigSearchDao.query(sql, jt,params.toArray());
		} catch (InstantiationException |IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryRepConSearchId=========end==");
		return repConSearch;
	}
	
	/**
	 * 保存报表字段信息
	 */
	@Override
	public void saveLstRepConSearch(final List<ReportConfigSearch> lstRCS) throws HbcsoftException{
		this.getLogger().info("============saveLstRepConSearch=========start==");
		for(int i=0; i<lstRCS.size();i++){
			final ReportConfigSearch rcs = lstRCS.get(i);
			reportConfigSearchDao.update(rcs, jt);
		}
		this.getLogger().info("============saveLstRepConSearch=========end==");
	}
	
	/**
	 * 根据id查找报表数据
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	@Override
	public ReportConfig selectIDRepCon(final String id, final Long companyId) throws HbcsoftException{
		this.getLogger().info("============selectIDRepCon=========start==");
		ReportConfig repCon = new ReportConfig();
		final String sql = HbcsoftCache.getSqlValue("report_selectIDRepCon");
		try {
			repCon = reportConfigDao.query(sql, jt,id, String.valueOf(companyId));
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============selectIDRepCon=========end==");
		return repCon;
	}
	
	/**
	 * 修改报表页面时查询表单信息
	 */
	@Override
	public List<ReportConfigSearch> selectIDRCS(final String reportid, final Long companyId) throws HbcsoftException{
		this.getLogger().info("============selectIDRCS=========start==");
		List<ReportConfigSearch> lstRCS = new ArrayList<ReportConfigSearch>();
		final List<String> params = new ArrayList<String>();
		params.add(reportid);
		params.add(String.valueOf(companyId));
		final String sql = HbcsoftCache.getSqlValue("report_queryRCS");
		try {
			lstRCS = reportConfigSearchDao.queryAll(sql, jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
			this.getLogger().info(e);
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		this.getLogger().info("============selectIDRCS=========end==");
		return lstRCS;
	}
	
	/**
	 * 修改页面信息
	 */
	@Override
	public void updateReportConfig(final ReportConfig repCon) throws HbcsoftException{
		this.getLogger().info("============updateReportConfig=========start==");
		reportConfigDao.update(repCon,jt);
		this.getLogger().info("============updateReportConfig=========end==");
	}
	
	/**
	 * 修改查询页面信息
	 */
	@Override
	public void updateRepConSearch(final ReportConfigSearch repConsearch) throws HbcsoftException{
		this.getLogger().info("============updateReportConfig=========start==");
		reportConfigSearchDao.update(repConsearch,jt);
		this.getLogger().info("============updateReportConfig=========end==");
	}
	
	/**
	 * 根据报表id、表id 和公司id查询
	 */
	@Override
	public List<ReportConfigSearch> selectLstRepConSearch(final String reportId,final String tableId,final Long companyId)
			throws HbcsoftException{
		this.getLogger().info("============selectLstRepConSearch=========start==");
		List<ReportConfigSearch> lstRCS = new ArrayList<ReportConfigSearch>();
		final String sql = HbcsoftCache.getSqlValue("report_selectLstRepConSearch");
		final List<String> params = new ArrayList<String>();
		params.add(reportId);
		params.add(tableId);
		params.add(String.valueOf(companyId));
		try {
			lstRCS = reportConfigSearchDao.queryAll(sql, jt,params.toArray());
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============selectLstRepConSearch=========end==");
		return lstRCS;
	}
	
	/**
	 * 修改子表页面
	 */
	@Override
	public void updateRepConSearch(final List<ReportConfigSearch> lstRCS) throws HbcsoftException{
		ReportConfigSearch rcs = new ReportConfigSearch();
		for(int i=0; i<lstRCS.size(); i++){
			rcs = lstRCS.get(i);
			if(rcs.getId() == null || rcs.getId() == 0){
				final Long id = SequenceUtil.getTableId("Z_REPORT_CONFIGSEARCH");
				rcs.setId(id);
				reportConfigSearchDao.save(rcs, jt);
			}else{
				reportConfigSearchDao.update(rcs, jt);
			}
		}
	}
	
	/**
	 * 查询报表配置的主键、报表名
	 */
	@Override
	public ReportConfig getReportConfig(final String companyId,final Long reportId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportConfig  rc = new ReportConfig();
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryReportConfigById");
		try {
			rc = reportConfigDao.query(sql, jt,companyId,reportId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return rc;
	}
	/**
	 * 根据公司id、表id查询配置信息
	 */
	@Override
	public ReportConfig selectIDConfig(final String id,final Long companyId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		ReportConfig config = new ReportConfig();
		final String sql = HbcsoftCache.getSqlValue("report_selectIDConfig");
		try {
			config = reportConfigDao.query(sql, jt, id,companyId);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return config;
	}
	/**
	 * 根据公司id、表id查询字段信息
	 */
	@Override
	public List<ReportConfigSearch> selectConfigSearch(final String id,final Long companyId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		 List<ReportConfigSearch> searchList = new ArrayList<ReportConfigSearch>();
		final String sql = HbcsoftCache.getSqlValue("report_selectConfigSearch");
		try {
			searchList = reportConfigSearchDao.queryAll(sql, jt,String.valueOf(HBCSoftConstant.FORM_INPUT_TYPE_0),id,companyId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return searchList;
	}
	/**
	 * 
	 */
	@Override
	public int getReport(final String tableName,final long companyId) throws HbcsoftException {
		List<ReportConfigSearch> listSearch = new ArrayList<ReportConfigSearch>();
		ReportConfig rc = new ReportConfig();
		int size = 0;
		final String sql = HbcsoftCache.getSqlValue("report_querySearchByName");
		final String sqlConfig = HbcsoftCache.getSqlValue("report_selectIDRepCon");
		long reportId = 0;
		try {
			listSearch = reportConfigSearchDao.queryAll(sql, jt, tableName, companyId);
			if(!listSearch.isEmpty()){
				reportId = listSearch.get(0).getReportId();
			}
			if(reportId != 0){
				rc = reportConfigDao.query(sqlConfig, jt, reportId, companyId);
				final long odbc = rc.getDataSourceId();
				if(odbc==0L){//本地数据库连接
					size = 1;
				}
			}
		} catch (InstantiationException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		}
		return size;
	}

}

