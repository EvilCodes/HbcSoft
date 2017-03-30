package com.hbcsoft.InterManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.InterManage.dao.InterManageDao2Imp;
import com.hbcsoft.InterManage.dao.InterManageDaoImp;
import com.hbcsoft.InterManage.entity.PrimaryList;
import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.table.service.CreateDBtableServiceImpl;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

@Transactional
@Service("interManageService")
public class InterManageServiceImp extends LogBaseClass<InterManageServiceImp> implements InterManageService{

	/**
	 * 注入jt
	 */
	@Autowired
	private transient HbcsoftJT jt;
	
	/**
	 * 注入session
	 */
	@Autowired  
	private transient HttpSession session;
	
	/**
	 * 注入interManageDao
	 */
	@Autowired
	private InterManageDaoImp interManageDao;
	/**
	 * 注入interManageDao
	 */
	@Autowired
	private InterManageDao2Imp interManageDao2;
	
	
	@Override
	public List<PrimaryList> queryAll(String tableName, String memo)
			throws HbcsoftException {
		this.getLogger().info("============queryAll=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
		final String sql = HbcsoftCache.getSqlValue("interManage_queryPrimaryList");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND CLASSNAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append( " AND MEMO LIKE '%"+memo+"%'");
		}
		List<PrimaryList> list = new ArrayList<PrimaryList>();
		list = (List<PrimaryList>) interManageDao.queryAllTable(sb.toString(), jt, companyId);
		
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public List<PrimaryList> queryAllTable(final String tableName,final String memo,final int startRow,final int pageSize) throws HbcsoftException {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllTable=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
		final String sql = HbcsoftCache.getSqlValue("interManage_queryPrimaryList");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND CLASSNAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append(" AND MEMO LIKE '%"+memo+"%'");
		}
		List<PrimaryList> list = new ArrayList<PrimaryList>();
		try{
			list = (List<PrimaryList>) interManageDao.queryAll(sb.toString(), jt, startRow, pageSize,new Object[] {companyId});
		} catch (InstantiationException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		this.getLogger().info("============queryAllTable=========end==");
		return list;
	}

	@Override
	public List<PrimaryList> queryAllTable(String tableName, String memo)
			throws HbcsoftException {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllTable=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
		final String sql = HbcsoftCache.getSqlValue("interManage_queryPrimaryList");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND CLASSNAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append(" AND MEMO LIKE '%"+memo+"%'");
		}
		List<PrimaryList> list = new ArrayList<PrimaryList>();
		try {
			list = (List<PrimaryList>) interManageDao.queryAll(sb.toString(), jt, companyId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
		this.getLogger().info("============queryAllTable=========end==");
		return list;
	}

	@Override
	public Long savePrimaryList(PrimaryList primaryList) throws HbcsoftException{
		interManageDao.save(primaryList, jt);
		return primaryList.getId();
		
	}

	/**
	 * 保存方法属性
	 */
	@Override
	public void saveEntity(final List<Sublist> list) throws HbcsoftException {
		Sublist sl = new Sublist();
		for(int i=0; i<list.size(); i++){
			sl = list.get(i);
			interManageDao2.save(sl, jt);
		}
	}
	
	@Override
	public PrimaryList primaryNm(String id) throws HbcsoftException {
		this.getLogger().info("============tableNm=========start==");
		// TODO:调用zdy打包方法根据id查询表内容
		final String sql = HbcsoftCache.getSqlValue("interManage_queryPrimaryListAndId");
		PrimaryList table = new PrimaryList();
		table = interManageDao.queryPrimaryNm(sql, jt, id);
		
		this.getLogger().info("============tableNm=========end==");
		return table;
	}

	@Override
	public List<Sublist> querySublist(String id) throws HbcsoftException {
		// TODO:调用zdy打包方法根据主表id查询所有的实体内容
		this.getLogger().info("============queryListEntity=========start==");
		
		final String sql = HbcsoftCache.getSqlValue("interManage_querySublistAndId");
		List<Sublist> list = new ArrayList<Sublist>();
		list = (List<Sublist>) interManageDao.queryListEntity(sql, jt, id);
		
		this.getLogger().info("============queryListEntity=========end==");
		return list;
	}
	
	/**
	 * 修改类名
	 */
	@Override
	public void updateTable(final PrimaryList primaryList) throws HbcsoftException {
		interManageDao.update(primaryList, jt);
	}
	
	/**
	 * 修改方法属性表集合
	 */
	// TODO:调用zdy打包方法修改实体字段数据
	@Override
	public void updateEntity(final List<Sublist> list) throws HbcsoftException {
		Sublist sl = new Sublist();
		for(int i=0; i<list.size(); i++){
			sl= list.get(i);

			if(sl.getId() == null || sl.getId() == 0){
				final Long id = SequenceUtil.getTableId("t_tableentity");
				sl.setId(id);
				interManageDao2.save(sl, jt);
			}else{
				interManageDao2.update(sl, jt);
			}
		}
	}

	@Override
	public void deleteTable(PrimaryList primaryList) throws HbcsoftException {
		interManageDao.update(primaryList, jt);
		
	}
	
	public List<Sublist> queryListEntity(String id)throws HbcsoftException{
		
		// TODO:调用zdy打包方法根据主表id查询所有的实体内容
		this.getLogger().info("============queryListEntity=========start==");
		
		final String sql = HbcsoftCache.getSqlValue("interManage_querySublistAndId");
		List<Sublist> list = new ArrayList<Sublist>();
		list = (List<Sublist>) interManageDao.queryListEntity(sql, jt, id);
		
		this.getLogger().info("============queryListEntity=========end==");
		return list;
	}
	
	public void deleteEntity(List<Sublist> list) throws HbcsoftException{
		Sublist sl = new Sublist();
		for(int i=0; i<list.size(); i++){
			sl = list.get(i);
			sl.setFlag(1);
			//createDBtableDao2.delete(te, jt);
			interManageDao2.update(sl, jt);
		}
		
	}

	@Override
	public void delRow(String rowId)throws HbcsoftException, InstantiationException, IllegalAccessException {
		this.getLogger().info("============delRow=========start==");
		Sublist entity = new Sublist();
		final String sql = HbcsoftCache.getSqlValue("interManage_querySublistById");
		entity = interManageDao2.query(sql, jt, rowId);
		entity.setFlag(1);
		interManageDao2.update(entity, jt);
		this.getLogger().info("============delRow=========end==");
	}
}
