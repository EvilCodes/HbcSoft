package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.LogDaoImp;
import com.hbcsoft.sys.entity.Log;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * LogServiceImp
 * 
 * @author Administrator
 *
 */
@Service("logService")
public class LogServiceImp extends LogBaseClass<LogServiceImp> implements
		LogService {
	/**
	 * jt
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * logDao
	 */
	@Autowired
	private transient LogDaoImp logDao;

	/**
	 * 保存
	 */
	@Override
	public int save(final Log log) throws HbcsoftException {
		this.getLogger().info("============save=========start==");
		// int intV=0;
		final int intV = logDao.save(log, jt);
		this.getLogger().info("============save=========end==");
		return intV;
	}

	/**
	 * 查询所有的行
	 */
	@Override
	public List<Log> queryAllCount(final String type, final String loginName,final String companyId) {
		// TODO Auto-generated method stub
		this.getLogger().info("============queryAllCount=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("log_queryAllCount");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (type != null && !"".equals(type)) {
			sb.append( " AND type = ? ");
			//sql += " AND type = ? ";
			params.add(type);
		}

		if (loginName != null && !"".equals(loginName)) {
			sb.append(" AND loginName like ?");
			//sql += " AND loginName like ?";
			params.add("%" + loginName + "%");
		}
		sb.append("order by  operTime desc");
		//sql += "order by  operTime ";
		final List<Log> list = this.returnListCount(params, sb.toString());
		this.getLogger().info("============queryAllCount=========end==");
		return list;
	}

	/**
	 * 查询所有数据
	 */
	@Override
	public List<Log> queryAllLog(final String type, final String loginName,final String companyId,
			final int startRow, final int pageSize) {
		// TODO Auto-generated method stub
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("log_queryAllCount");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (type != null && !"".equals(type)) {
			sb.append( " AND type = ? ");
			//sql += " AND type = ? ";
			params.add(type);
		}

		if (loginName != null && !"".equals(loginName)) {
			sb.append(" AND loginName like ?");
			//sql += " AND loginName like ?";
			params.add("%" + loginName + "%");
		}
		sb.append("order by  operTime desc");
		//sql += "order by  operTime ";
		final List<Log> list = this.returnListCount(params, sb.toString(), startRow,
				pageSize);
		this.getLogger().info("============queryAllCount=========end==");
		return list;
	}

	private List<Log> returnListCount(final List<String> params,
			final String sql, final int startRow, final int pageSize) {// 分页
		List<Log> list = new ArrayList<Log>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		try {
			if (pars || paramsize) {
				list = (List<Log>) logDao.queryAll(sql, jt, startRow, pageSize);
			} else {

				list = (List<Log>) logDao.queryAll(sql, jt, startRow, pageSize,
						params.toArray(new Object[params.size()]));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return list;
	}

	private List<Log> returnListCount(final List<String> params,
			final String sql) {// 分页
		List<Log> list = new ArrayList<Log>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		try {
			if (pars || paramsize) {
				list = (List<Log>) logDao.queryAll(sql, jt);
			} else {
				list = (List<Log>) logDao.queryAll(sql, jt,
						params.toArray(new Object[params.size()]));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return list;
	}

}