package com.hbcsoft.sys.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
/**
 * 第三方数据库配置信息Service接口
 * @author yangfeng
 * @version
 */
public interface OuterDBLinkParaService {
	/**
	 * 根据公司id获取第三方数据库配置信息
	 * @param strCompanyId
	 * @return
	 * @throws HbcsoftException 
	 */
	List<OuterDBLinkPara> findOuterDBPara(String strCompanyId) throws HbcsoftException;
	
	/**
	 * 数据库配置信息查询
	 * @param strCompanyId
	 * @param strDbName
	 * @param strisEnable
	 * @return
	 * @throws HbcsoftException 
	 */
	List<OuterDBLinkPara> findOuterDBPara(String strCompanyId,String strDbName,String strisEnable) throws HbcsoftException;
	
	/**
	 * 第三方数据库重复性校验
	 * @param dbIp
	 * @param dbsIp
	 * @param dbType 
	 * @return
	 */
	OuterDBLinkPara checkByIpAndDbname(Long companyID ,String dbIp, String dbsIp, String dbType);
	/**
	 * 根据id查询第三方数据库参数配置
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	OuterDBLinkPara findDbParaById(String id) throws HbcsoftException;
	/**
	 * 保存第三方数据库参数配置
	 * @param db
	 * @throws HbcsoftException 
	 */
	void saveOuterDbPara(OuterDBLinkPara db) throws HbcsoftException;

	/**
	 * 根据id删除第三方数据库配置
	 * @param id
	 * @return
	 * @throws HbcsoftException 
	 */
	void deleteDbParaById(String id) throws HbcsoftException;
	
	/**
	 * 第三方数据库配置信息修改
	 * @param outerDBLinkPara
	 * @throws HbcsoftException 
	 */
	void updateDBLinkPara(OuterDBLinkPara outerDBLinkPara) throws HbcsoftException;
	/**
	 * 根据公司id查询有效配置信息
	 * @param strCompanyId
	 * @return
	 * @throws HbcsoftException 
	 */
	List<OuterDBLinkPara> findValidOuterDBPara(String strCompanyId) throws HbcsoftException;
	/**
	 * 根据公司id获取链接配置
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	OuterDBLinkPara getOuterDBLinkPara(long companyId) throws HbcsoftException;

}
