package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.DictTypeDaoImp;
import com.hbcsoft.sys.entity.Company;
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 
 * @author Administrator
 *
 */
@Transactional
@Service("DictTypeQueryService")
public class DictTypeQueryServiceImp extends
		LogBaseClass<DictTypeQueryServiceImp> implements DictTypeQueryService {
	/**
	 * HbcsoftJT
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 字典类型daoimp
	 */
	@Autowired
	private transient DictTypeDaoImp dictTypeDao;

	private List<DictType> returnListCount(final List<String> params,
			final String sql) {// 行
		List<DictType> list = new ArrayList<DictType>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		if (paramsize || pars) {
			try {
				list = (List<DictType>) dictTypeDao.queryAll(sql, jt,
						params.toArray(new Object[params.size()]));//new String[params.size()]
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		} else {
			try {
				list = (List<DictType>) dictTypeDao.queryAll(sql, jt,
						params.toArray(new Object[params.size()]));//new String[params.size()]
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		return list;
	}

	private List<DictType> returnListCount(final List<String> params,
			final String sql, final int startRow, final int pageSize) {// 分页
		List<DictType> list = new ArrayList<DictType>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		if (paramsize || pars) {
			try {
				list = (List<DictType>) dictTypeDao.queryAll(sql, jt, startRow,
						pageSize);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		} else {
			try {
				list = (List<DictType>) dictTypeDao.queryAll(sql, jt, startRow,
						pageSize, params.toArray(new Object[params.size()]));//new String[params.size()]
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				this.getLogger().info(e);
			}
		}
		return list;
	}

	/**
	 * 查询所有的数据
	 */
	@Override
	/*public List<DictType> queryAll(final String dtCode, final String dtName,
			final String parentId,final String companyId) {*/
	public List<DictType> queryAll(final DictType dictType,
			final String parentId,final String companyId) {
		this.getLogger().info("============queryAll=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("dictType_queryAll");
			params.add(companyId);
			final StringBuilder sb = new StringBuilder(sql);
		if (parentId != null && !"".equals(parentId)) {
			sb.append(" AND parentId = ?");
			//sql += " AND parentId = ?";
			params.add(parentId);
		}

		if (dictType.getDtCode() != null && !"".equals(dictType.getDtCode())) {
			//sql += " AND DTCODE LIKE ? ";
			sb.append(" AND DTCODE LIKE ? ");
			params.add("%" + dictType.getDtCode() + "%");
		}
		if (dictType.getDtName()!= null && !"".equals(dictType.getDtName())) {
			//sql += " AND DTNAME LIKE ? ";
			sb.append( " AND DTNAME LIKE ? ");
			params.add("%" + dictType.getDtName() + "%");
		}
		sb.append("  ORDER BY SORT ");
		//sql += "  ORDER BY SORT ";
		this.getLogger().info("============queryAll=========end==");
		return this.returnListCount(params, sb.toString());
	}

	/**
	 * 分页查询
	 */
	@Override
/*	public List<DictType> queryAllDictType(final String dtCode,
			final String dtName, final String parentId,
			final String companyId, final int startRow,*/
	public List<DictType> queryAllDictType(final DictType dictType, final String parentId,final String companyId, final int startRow,
			final int pageSize) {
		// TODO:调用zdy打包方法
		this.getLogger().info("============queryAllDictType=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("dictType_queryAll");
			params.add(companyId);
			final StringBuilder sb = new StringBuilder(sql);
			if (parentId != null && !"".equals(parentId)) {
				sb.append(" AND parentId = ?");
				//sql += " AND parentId = ?";
				params.add(parentId);
			}

			if (dictType.getDtCode() != null && !"".equals(dictType.getDtCode())) {
				//sql += " AND DTCODE LIKE ? ";
				sb.append(" AND DTCODE LIKE ? ");
				params.add("%" + dictType.getDtCode() + "%");
			}
			if (dictType.getDtName()!= null && !"".equals(dictType.getDtName())) {
				//sql += " AND DTNAME LIKE ? ";
				sb.append( " AND DTNAME LIKE ? ");
				params.add("%" + dictType.getDtName() + "%");
			}
			sb.append("  ORDER BY SORT ");
			//sql += "  ORDER BY SORT ";
		this.getLogger().info("============queryAllDictType=========end==");
		return this.returnListCount(params, sb.toString(), startRow, pageSize);
	}

	/**
	 * 修改
	 * @param entity
	 * @param company
	 * @param user
	 * @param type
	 * @return
	 */
	public int update(final DictType entity, final SessionInfo session) {
		// TODO Auto-generated method stub
		this.getLogger().info("============save=========start==");
		this.updateAll(entity, session);
		this.getLogger().info("============save=========end==");
		return 1;
	}

	private int updateAll(final DictType entity, final SessionInfo session) {
		final Company company = session.getCompany();
		final User user = session.getUser();
		final DictType parentType = entity.getParentDictType();
		return this.updateEntity(entity, company, user, parentType);
	}

	private int updateEntity(final DictType entity, final Company company,
			final User user, final DictType parentType) {
		entity.setUpdateTime(new Date());
		entity.setCompanyId(company.getId());
		entity.setUpdateID(user.getId());
		entity.setUpdateTime(new Date());
		entity.setParentId(parentType.getId());// 父节点
		try {
			dictTypeDao.update(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return 1;
	}

	/**
	 * 保存
	 * @param entity
	 * @param companyId
	 * @param userId
	 * @param typeId
	 * @return
	 */
	public int save(final DictType entity, final SessionInfo session) {
		// TODO Auto-generated method stub
		this.getLogger().info("============save=========start==");
		this.saveAll(entity, session);
		this.getLogger().info("============save=========end==");
		return 1;
	}

	private int saveAll(final DictType entity, final SessionInfo session) {
		final Company company = session.getCompany();
		final User user = session.getUser();
		final DictType parentType = entity.getParentDictType();
		return this.saveEntity(entity, company, user, parentType);
	}

	private int saveEntity(final DictType entity, final Company company,
			final User user, final DictType parentType) {
		final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
		entity.setId(fromid);
		entity.setCompanyId(company.getId());
		entity.setCreateID(user.getId());
		entity.setCreateTime(new Date());// 创建时间
		entity.setUpdateID(user.getId());
		entity.setUpdateTime(new Date());
		entity.setParentId(parentType.getId());// 父节点
		try {
			dictTypeDao.save(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return 1;
	}
	/**
	 * 根据编码或名称查询，是否已经存在
	 */
	@Override
	public DictType queryByCodeNameId(final DictType dtpage,
			 final String companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		DictType dt = new DictType();
		final String sql = HbcsoftCache.getSqlValue("dictType_queryByCodeNameId");
		try {
			dt= dictTypeDao.query(sql, jt, dtpage.getDtCode(),dtpage.getDtName(),companyId,String.valueOf(dtpage.getId()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return dt;

	}

}
