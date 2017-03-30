package com.hbcsoft.sys.service;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Resource;
/**
 * 资源管理CRUD接口
 * */
public interface ResourceService2 {
	/**
	 * 查询
	 * @throws IOException 
	 * */
	List<Resource> queryAll(Resource resource,String id, final String companyId,final HttpServletRequest request)throws HbcsoftException, IllegalAccessException, InstantiationException, IOException;
	/**
	 * 分页查询
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * */
	List<Resource> queryAllResourceType(Resource resource,String id, final String companyId,
			int startRow, int pageSize,final HttpServletRequest request)throws HbcsoftException, InstantiationException, IllegalAccessException, IOException;
	/**
	 * 查询单条
	 */
	Resource queryResourceById(final String id,final String companyId) throws HbcsoftException;
	/**
	 * 修改
	 * */
	int update(Resource resource) throws HbcsoftException;
	/**
	 * 多选
	 * */
	Resource queryResourceByIds(final String ids, final String companyId)throws HbcsoftException;
	/**
	 * 删除
	 * */
	void deletebyIds(Resource entity)throws HbcsoftException;
	/**
	 * 保存数据
	 */
	void addResource(Resource resource) throws HbcsoftException;
	/**
	 * 根据parentResourceId查询出父资源的name
	 * */
	Resource queryIdAndName(String id) throws HbcsoftException;
	/**
	 * 查询资源表中表单数据
	 * @param CompanyId
	 * @param actionUrl
	 * @param code
	 * @throws HbcsoftException
	 */
	Resource queryForAndRes(Long CompanyId,String actionUrl) throws HbcsoftException;
	/**
	 * 根据编码或名称查询，是否已经存在
	 */
	Resource queryByCodeName(Resource res, String companyId) throws HbcsoftException;
	/**
	 * 删除报表资源配置
	 * @param companyId
	 * @param reportType
	 * @param reportName
	 * @param reportUrl
	 * @throws HbcsoftException 
	 */
	void deleteReportResource(long companyId, String reportType, String reportName, String reportUrl) throws HbcsoftException;
}
