package com.hbcsoft.table.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.service.FormTableService;
import com.hbcsoft.table.dao.CreateDBtableDaoImpl;
import com.hbcsoft.table.dao.CreateDBtableDaoImpl2;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.sql.SqlTools;
import com.hbcsoft.zdy.util.SequenceUtil;
/**
 * 实现Service
 * @author liang
 *
 */
@Transactional
@Service("createDBtable2Service")
public class CreateDBtable2ServiceImpl extends LogBaseClass<CreateDBtable2ServiceImpl> implements CreateDBtable2Service {
	/**
	 * 注入
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 注入
	 */
	@Autowired
	private transient CreateDBtableDaoImpl createDBtableDao;
	/**
	 * 注入
	 */
	@Autowired
	private transient CreateDBtableDaoImpl2 createDBtableDao2;
	/**
	 * 引用formTableService
	 */
	@Autowired
	private transient FormTableService formTableService;
	/**
	 * 新增
	 */
	private static Integer operate0 = 0;
	/**
	 * 修改
	 */
	private static int operate1 = 1;
	/**
	 * 删除
	 */
	private static int operate2 = 2;
	/**
	 * 根据主表id获取字段集
	 */
	@Override
	public List<TableEntity> queryAllEntity(final String id) throws HbcsoftException {
		this.getLogger().info("============queryAllEntity=========start==");
		
//		String sql = "SELECT * FROM T_TABLEENTITY WHERE MAINID='"+id+"'";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableEntityByMainId");
		List<TableEntity> list = new ArrayList<TableEntity>();
		list = (List<TableEntity>) createDBtableDao.queryAllEntity(sql, jt, id);
		this.getLogger().info("============queryAllEntity=========end==");
		return list;
	}

	// TODO:调用zdy打包方法修改表名称数据
	/**
	 * 修改表名
	 */
	@Override
	public void updateTable(final TableNameClass tableName) throws HbcsoftException {
		createDBtableDao.update(tableName, jt);
	}
	/**
	 * 修改字段表集合
	 */
	// TODO:调用zdy打包方法修改实体字段数据
	@Override
	public void updateEntity(final List<TableEntity> list) throws HbcsoftException {
		TableEntity te = new TableEntity();
		for(int i=0; i<list.size(); i++){
			te = list.get(i);
//			if(te.getId() != null && 0 != te.getId()){
//				createDBtableDao2.update(te, jt);
//			}else{
//				final Long id = SequenceUtil.getTableId("t_tableentity");
//				te.setId(id);
//				createDBtableDao2.save(te, jt);
//			}
			if(te.getId() == null || te.getId() == 0){
				final Long id = SequenceUtil.getTableId("t_tableentity");
				te.setId(id);
				createDBtableDao2.save(te, jt);
				formTableService.addForFieEnt(te);
			}else{
				createDBtableDao2.update(te, jt);
				formTableService.updateFormFields(te);
			}
		}
	}
	/**
	 * 删除表名称数据
	 */
	// TODO:调用zdy打包方法删除表名称数据
	@Override
	public void deleteTable(final TableNameClass tableName) throws HbcsoftException {
//		createDBtableDao.delete(tableName, jt);
		createDBtableDao.update(tableName, jt);
	}
	/**
	 * 删除实体字段数据
	 */
	// TODO:调用zdy打包方法删除实体字段数据
	@Override
	public void deleteEntity(final List<TableEntity> list) throws HbcsoftException {
		TableEntity te = new TableEntity();
		for(int i=0; i<list.size(); i++){
			te = list.get(i);
			te.setFlag(1);
			//createDBtableDao2.delete(te, jt);
			createDBtableDao2.update(te, jt);
		}
	}
	/**
	 * 删除
	 */
	@Override
	public void delRow(final String id) throws HbcsoftException, InstantiationException, IllegalAccessException {
		TableEntity entity = new TableEntity();
//		String sql = "SELECT * FROM T_TABLEENTITY WHERE ID='"+id+"'";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableEntityById");
		entity = createDBtableDao2.query(sql, jt, id);
		entity.setFlag(1);
		//createDBtableDao2.delete(entity, jt);
		createDBtableDao2.update(entity, jt);
	}
	/**
	 * 创表数据库表
	 */
	@Override
	public void operateSql(final int operate,final int sqlType,final TableNameClass tn,final List<TableEntity> list) throws HbcsoftException {
		final SqlTools st = new SqlTools();
		String sql = "";
		List<String> sqlList = new ArrayList<String>();

		if(operate==operate0){// 新增
			sql = st.creatTableSql(sqlType, tn, list);
			createDBtableDao.createSql(sql, jt);
		}else if(operate==operate1){// 修改
			sqlList = st.alterTableSql(sqlType, tn, list);
			for(int i=0; i<sqlList.size(); i++){
				if(sqlList.get(i) != null && !"".equals(sqlList.get(i))){
					createDBtableDao.createSql(sqlList.get(i), jt);
				}
			}
		}else if(operate==operate2){// 删除
			sql = st.dropTableSql(sqlType, tn);
			createDBtableDao.createSql(sql, jt);
		}
	}
	/**
	 * 根据主表id查询字段集
	 */
	@Override
	public List<TableEntity> queryListEntity(final String id) {
		// TODO:调用zdy打包方法根据主表id查询所有的实体内容
		this.getLogger().info("============queryListEntity=========start==");
		
//		String sql = "SELECT * FROM T_TABLEENTITY WHERE FLAG=0 AND MAINID='"+id+"'";
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableEntityAndId");
		List<TableEntity> list = new ArrayList<TableEntity>();
		list = (List<TableEntity>) createDBtableDao.queryListEntity(sql, jt, id);
		
		this.getLogger().info("============queryListEntity=========end==");
		return list;
	}
	/**
	 * 获取
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getListSize(final List<TableEntity> list,final String tName) throws HbcsoftException {
		boolean isNeed = false;
		int size = 0;
		TableEntity entity = new TableEntity();
		for(int i=0; i<list.size(); i++){
			entity = list.get(i);
			final int changeFlag = entity.getChangeFlag();//1是修改
			if(changeFlag==ComConstant.EDITTYPE_ADD){
				isNeed = true;
				break;
			}
		}
		if(isNeed){
			final String sql = "SELECT COUNT(*) FROM "+tName;
			size = jt.queryForInt(sql);
		}
		return size;
	}
	/**
	 * @throws HbcsoftException 
	 * 
	 */
	@Override
	public int getFieldData(final String id,final long companyId) throws HbcsoftException {
		int size = 0;
		final String sql = HbcsoftCache.getSqlValue("createTable_queryTableEntityByIdAndC");
		final String sqlMain = HbcsoftCache.getSqlValue("createTable_queryTableNameById");
		TableEntity te = new TableEntity();
		TableNameClass tnc = new TableNameClass();
		try {
			te = createDBtableDao2.query(sql, jt, id, companyId);
			final String fieldName = te.getName();
			final long mainId = te.getMainId();
			tnc = createDBtableDao.query(sqlMain, jt, mainId);
			final String countSql = "SELECT COUNT("+fieldName+") FROM "+tnc.getTableName();
			size = jt.queryForObject(countSql, Integer.class);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), 999, e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return size;
	}
}
