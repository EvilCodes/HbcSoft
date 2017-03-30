package com.hbcsoft.table.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.table.dao.CreateDBtableDaoImpl;
import com.hbcsoft.table.dao.CreateDBtableDaoImpl2;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * 实现接口
 * @author liang
 *
 */
@Transactional
@Service("createDBtableService")
public class CreateDBtableServiceImpl extends LogBaseClass<CreateDBtableServiceImpl> implements CreateDBtableService {
	/**
	 * 注入jt
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 注入dao
	 */
	@Autowired
	private transient CreateDBtableDaoImpl createDBtableDao;
	/**
	 * 注入dao
	 */
	@Autowired
	private transient CreateDBtableDaoImpl2 createDBtableDao2;
	/**
	 * 注入session
	 */
	@Autowired  
	private transient HttpSession session;
	/**
	 * 查询所有表名称
	 */
	@Override
	public List<TableNameClass> queryAll(final String tableName,final String memo) throws HbcsoftException {
		this.getLogger().info("============queryAll=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
//		String sql = "SELECT * FROM T_TABLENAME WHERE FLAG=0 ";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableName");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND TABLENAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append( " AND MEMO LIKE '%"+memo+"%'");
		}
		List<TableNameClass> list = new ArrayList<TableNameClass>();
		list = (List<TableNameClass>) createDBtableDao.queryAllTable(sb.toString(), jt, companyId);
		
		this.getLogger().info("============queryAll=========end==");
		return list;
	}
	/**
	 * 保存表名称
	 */
	@Override
	public Long saveTable(final TableNameClass tableName) {
		this.getLogger().info("============saveTable=========start==");
//		String ins = "INSERT INTO T_TABLENAME(ID, CREATEID,
//		CREATETIME, TABLENAME, ISMAINTABLE,"
//				+ " MAINID, MAINNAME, MEMO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		final String ins = HbcsoftCache.getSqlValue("createTable_insTableName");
		final Long mainId = createDBtableDao.saveTable(ins, jt, tableName);
		
		this.getLogger().info("============saveTable=========end==");
		return mainId;
	}
	/**
	 * 保存字段表
	 */
	@Override
	public void saveTableEntity(final List<TableEntity> list) {
		this.getLogger().info("============saveTable=========start==");
//		String ins = "INSERT INTO T_TABLEENTITY(ID, CREATEID, 
//		CREATETIME, NAME, TITLE, "
//				+ "TYPE, NUMBER, ISDISPLAY, ISEDIT, ISNULL, "
//				+ "DEFAULTVALUE, FLAG, MAINID, DECIMALDIGITS) VALUES(?, ?, ?, ?, ?,"
//				+ " ?, ?, ?, ?, ?,"
//				+ " ?, ?, ?, ?)";
		final String ins = HbcsoftCache.getSqlValue("createTable_insTableEntity");
		createDBtableDao.saveTableEntity(ins, jt, list);
		this.getLogger().info("============saveTable=========end==");
	}
	/**
	 * 根据id查询表名称
	 */
	@Override
	public TableNameClass table(final String id) throws HbcsoftException {
		this.getLogger().info("============table=========start==");
		
//		String sql = "SELECT * FROM T_TABLENAME WHERE ID='"+id+"'";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableNameById");
		TableNameClass table = new TableNameClass();
		table = createDBtableDao.queryTable(sql, jt, id);
		this.getLogger().info("============table=========end==");
		return table;
	}
	/**
	 * 查询
	 */
	@Override
	public List<TableNameClass> queryAllTable(final String tableName,final String memo) throws HbcsoftException {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllTable=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
//		String sql = "SELECT * FROM T_TABLENAME WHERE FLAG=0";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableName");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND TABLENAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append(" AND MEMO LIKE '%"+memo+"%'");
		}
		List<TableNameClass> list = new ArrayList<TableNameClass>();
		try {
			list = (List<TableNameClass>) createDBtableDao.queryAll(sb.toString(), jt, companyId);
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
	/**
	 * 分页查询
	 */
	@Override
	public List<TableNameClass> queryAllTable(final String tableName,final String memo,final int startRow,final int pageSize) throws HbcsoftException {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllTable=========start==");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String companyId = String.valueOf(sessionInfo.getCompany().getId());
//		String sql = "SELECT * FROM T_TABLENAME WHERE FLAG=0";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableName");
		final StringBuilder sb = new StringBuilder(sql);
		if(tableName != null && !"".equals(tableName)){
			sb.append(" AND TABLENAME LIKE '%"+tableName+"%'");
		}
		if(memo != null && !"".equals(memo)){
			sb.append(" AND MEMO LIKE '%"+memo+"%'");
		}
		List<TableNameClass> list = new ArrayList<TableNameClass>();
		try{
			list = (List<TableNameClass>) createDBtableDao.queryAll(sb.toString(), jt, startRow, pageSize,new Object[] {companyId});
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
	// TODO:调用zdy打包方法保存表名称数据
	/**
	 * 保存表名
	 */
	@Override
	public Long saveTableName(final TableNameClass tableName) throws HbcsoftException {
		createDBtableDao.save(tableName, jt);
		return tableName.getId();
	}
	// TODO:调用zdy打包方法保存实体字段数据
	/**
	 * 保存字段
	 */
	@Override
	public void saveEntity(final List<TableEntity> list) throws HbcsoftException {
		TableEntity te = new TableEntity();
		for(int i=0; i<list.size(); i++){
			te = list.get(i);
			createDBtableDao2.save(te, jt);
		}
	}
	/**
	 * 查询表名称
	 */
	@Override
	public TableNameClass tableNm(final String id) {
		this.getLogger().info("============tableNm=========start==");
		// TODO:调用zdy打包方法根据id查询表内容
//		String sql = "SELECT * FROM T_TABLENAME WHERE FLAG=0 AND ID='"+id+"'";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableNameAndId");
		TableNameClass table = new TableNameClass();
		table = createDBtableDao.queryTableNm(sql, jt, id);
		
		this.getLogger().info("============tableNm=========end==");
		return table;
	}

}
