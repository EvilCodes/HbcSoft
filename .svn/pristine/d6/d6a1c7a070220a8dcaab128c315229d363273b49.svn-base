package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.OuterDBLinkParaDaoImp;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * 
 * @author yangfeng
 *
 */
@Transactional
@Service("OuterDBLinkParaService")
public class OuterDBLinkParaServiceImpl extends LogBaseClass<OuterDBLinkParaServiceImpl> implements OuterDBLinkParaService{
	
	/**
	 * jt注入
	 */
	@Autowired
	private transient  HbcsoftJT jt;
	/**
	* dao
	*/
	@Autowired
	private transient OuterDBLinkParaDaoImp outerDBLinkParaDao;
	/**
	* 根据公司id查第三方数据库有效配置
	 * @throws HbcsoftException 
	*/
	@Override
	public List<OuterDBLinkPara> findValidOuterDBPara(final String companyId) throws HbcsoftException {
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_queryValidDbParaByCompanyId");
		List<OuterDBLinkPara> outerDBLinkList = new ArrayList<>();
		try {
			outerDBLinkList =  outerDBLinkParaDao.queryAll(strSql, jt,companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerDBLinkList;
	}
	/**
	* 根据公司id查可用的第三方数据库配置信息
	 * @throws HbcsoftException 
	*/
	@Override
	public List<OuterDBLinkPara> findOuterDBPara(final String companyId) throws HbcsoftException {
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_queryDbParaByCompanyId");
		List<OuterDBLinkPara> outerDBLinkList = new ArrayList<>();
		try {
			outerDBLinkList =  outerDBLinkParaDao.queryAll(strSql, jt,companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerDBLinkList;
	}
	/**
	 * 条件查询科目配置信息
	 * @throws HbcsoftException 
	 */
	@Override
	public List<OuterDBLinkPara> findOuterDBPara(final String strCompanyId, final String strDbName,final String strisEnable) throws HbcsoftException {
		// TODO Auto-generated method stub
		List<OuterDBLinkPara> outerDBLinkList = new ArrayList<>();
		final StringBuffer sb = new StringBuffer(58); 
		sb.append(HbcsoftCache.getSqlValue("outerDbLink_queryDbParaList"));
		if(null != strCompanyId && !"".equals(strCompanyId)){
			sb.append( " AND COMPANYID = '"+strCompanyId+"'");	
		}
		if(null != strDbName && !"".equals(strDbName)){
			sb.append(" AND DBSID LIKE '%"+strDbName.trim()+"%'");	
		}
		if(null != strisEnable && !"".equals(strisEnable)){
			sb.append(" AND ISENABLED ='"+strisEnable+"'");
		}
		try {
			outerDBLinkList =  outerDBLinkParaDao.queryAll(sb.toString(), jt);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerDBLinkList;
	}
	/**
	 * 重复校验
	 */
	@Override
	public OuterDBLinkPara checkByIpAndDbname(final Long companyId ,final String dbIp, final String dbsIp,final String dbType) {
		// TODO Auto-generated method stub
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_checkSql");
		
		OuterDBLinkPara outerDBLinkList = null;
		try {
			outerDBLinkList =  outerDBLinkParaDao.query(strSql, jt,dbIp,dbsIp,dbType,companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return outerDBLinkList;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public OuterDBLinkPara findDbParaById(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_queryDbParaById");
		OuterDBLinkPara outerDBLinkPara = new OuterDBLinkPara();
		try {
			outerDBLinkPara = outerDBLinkParaDao.query(strSql, jt, id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		return outerDBLinkPara;
	}
	/**
	 * 保存
	 */
	@Override
	public void saveOuterDbPara(final OuterDBLinkPara db) throws HbcsoftException {
		// TODO Auto-generated method stub
		outerDBLinkParaDao.save(db, jt);
	}
	/**
	 * 
	 */
	@Override
	public void deleteDbParaById(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_queryDbParaById");
		try {
			final OuterDBLinkPara db = outerDBLinkParaDao.query(strSql, jt,id);
			if(null != db){
				outerDBLinkParaDao.delete(db, jt);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}
	/**
	 * 修改
	 */
	@Override
	public void updateDBLinkPara(final OuterDBLinkPara outerDBLinkPara) throws HbcsoftException {
		// TODO Auto-generated method stub
		outerDBLinkParaDao.update(outerDBLinkPara, jt);
		
	}
	/**
	 * 获取配置
	 */
	@Override
	public OuterDBLinkPara getOuterDBLinkPara(final long companyId) throws HbcsoftException {
		final String strSql = HbcsoftCache.getSqlValue("outerDbLink_queryDbParaByCompanyId");
		List<OuterDBLinkPara> dbList = new ArrayList<OuterDBLinkPara>();
		OuterDBLinkPara odbl = new OuterDBLinkPara();
		try {
			dbList = outerDBLinkParaDao.queryAll(strSql, jt,companyId);
			if(!dbList.isEmpty()){
				odbl = dbList.get(0);
			}
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return odbl;
	}

}
