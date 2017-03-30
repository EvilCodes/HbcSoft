package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.ClickManageDaoImp;
import com.hbcsoft.sys.entity.ClickManage;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
/**
 * 点选页面管理的service接口实现类
 * @author churuifeng
 *
 */
@Service("clickManageService")
public class ClickManageServiceImp extends BaseController<ClickManageServiceImp> implements ClickManageService {
	/**
	 * HbcsoftJT类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * UserDaoImp实现类
	 */
	@Autowired
	private transient ClickManageDaoImp manageDaoImp;
	/**
	 * 查询所有及查询条件
	 */
	@Override
	public List<ClickManage> queryAll(final String keys,final String values) {
		this.getLogger().info("============queryAll=========start==");
		final SessionInfo sessionInfo = (SessionInfo)session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
		final String sql = HbcsoftCache.getSqlValue("clickManage_queryClick");
		final StringBuilder sb = new StringBuilder(sql);
		if(keys != null && !"".equals(keys)){
			sb.append(" AND CLICKKEY LIKE '%"+keys+"%'");
		}
		if(values != null && !"".equals(values)){
			sb.append( " AND CLICkVALUE LIKE '%"+values+"%'");
		}
		List<ClickManage> list = new ArrayList<ClickManage>();
		list = (List<ClickManage>) manageDaoImp.queryAllClick(sb.toString(), jt, companyId);
		
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	/**
	 * 带分页的查询所有
	 */
	@Override
	public List<ClickManage> queryAllClick(final String keys,final String values,final int startRow,final int pageSize) {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllClick=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
		final String sql = HbcsoftCache.getSqlValue("clickManage_queryClick");
		final StringBuilder sb = new StringBuilder(sql);
		if(keys != null && !"".equals(keys)){
			sb.append(" AND CLICKKEY LIKE '%"+keys+"%'");
		}
		if(values != null && !"".equals(values)){
			sb.append( " AND CLICKVALUE LIKE '%"+values+"%'");
		}
		List<ClickManage> list = new ArrayList<ClickManage>();
		try {
			list = (List<ClickManage>) manageDaoImp.queryAll(sb.toString(), jt, startRow, pageSize,new Object[] {companyId});
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryAllClick=========end==");
		return list;
	}
	/**
	 * 删除 （只将mark的值发生变化）
	 */
	@Override
	public int updateClick(final String clickId) {
		this.getLogger().info("============updatePwd=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("clickManage_delectClick");
		this.getLogger().info(sql);
		int intV = 0;
		intV = manageDaoImp.updateAll(sql, jt,clickId);
		this.getLogger().info("============updatePwd=========end==");
		return intV;
	}
	/**
	 * 根据公司id和Key查询是否存在
	 */
	@Override
	public List<ClickManage> queryClick(final String key,final String companyId) throws HbcsoftException {
		this.getLogger().info("============updatePwd=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("clickManage_queryClickKey");
		this.getLogger().info(sql);
		List<ClickManage> cm = null;
		try {
			cm = manageDaoImp.queryAll(sql, jt,new Object[]{key,companyId});
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============updatePwd=========end==");
		return cm;
	}
	/**
	 * 添加
	 */
	@Override
	public int savaClick(final ClickManage clickManage) throws HbcsoftException {
		this.getLogger().info("============savaClick=========start==");
		return manageDaoImp.save(clickManage, jt);
	}
	/**
	 * 根据Id查询数据
	 */
	@Override
	public ClickManage queryCm(final String id) {
		this.getLogger().info("============queryCm=========start==");
		//把sqlConfig.xml中的sql语句放到缓存中
		final String sql = HbcsoftCache.getSqlValue("clickManage_queryClickCm");
		this.getLogger().info(sql);
		ClickManage cm = null;
		try {
			cm = manageDaoImp.query(sql, jt, id);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryCm=========end==");
		return cm;
	}
	/**
	 * 修改
	 */
	@Override
	public int updateClick(final ClickManage cm) throws HbcsoftException {
		return manageDaoImp.update(cm, jt);
	}
	/**
	 * 获取有效点选信息
	 * @throws HbcsoftException 
	 */
	@Override
	public List<ClickManage> getAllOptions(final Long companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("clickManage_queryClick");
		List<ClickManage> list = new ArrayList<ClickManage>();
		try {
			list = (List<ClickManage>) manageDaoImp.queryAll(sql, jt, companyId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
		return list;
	}
}