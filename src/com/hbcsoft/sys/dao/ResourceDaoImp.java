package com.hbcsoft.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.zdy.common.DaoBaseClass;
/**
 * 资源管理-数据访问层实现类
 */
@Repository
public class ResourceDaoImp extends DaoBaseClass<Resource> implements ResourceDao {
	/**
	 * 查询所有资源类型
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Resource> queryAllResource(final String sql, final JdbcTemplate jt, final String... param) {
		this.getLogger().info("=========queryAllResource======start==");
		List<Resource> list = new ArrayList<Resource>();
		list = jt.query(sql,param, new RowMapper() {
			/**
			 * 放到对象
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final Resource rsc = new Resource();
				rsc.setId(rs.getLong("id"));
				rsc.setCode(rs.getString("code"));
				rsc.setGroupId(rs.getString("groupId"));
				rsc.setName(rs.getString("name"));
				rsc.setUrl(rs.getString("url"));
				rsc.setEnable(rs.getInt("enable"));
				rsc.setOrderNo(rs.getInt("orderNo"));
				rsc.setResourceType(rs.getInt("resourceType"));
				rsc.setParentResourceId(rs.getLong("parentResourceId"));
				return rsc;
			}
		});
		this.getLogger().info("=========queryAllResource======end==");
		return list;
		
	}
	/**
	 * 根据父节点查询
	 * @param sql
	 * @param jt
	 * @param param
	 * @return list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Resource> queryResourceByPid(final String sql,final JdbcTemplate jt,final String... param) {
		this.getLogger().info("=========queryResourceByPid======start==");
		List<Resource> list;
		list = jt.query(sql, param, new RowMapper() {
			/**
			 * 放到对象
			 */
			@Override
			public Object mapRow(final ResultSet rs,final int arg1) throws SQLException {
				final Resource res = new Resource();
				res.setId(rs.getLong("id"));
				res.setCode(rs.getString("code"));
				res.setName(rs.getString("name"));
				res.setUrl(rs.getString("url"));
				res.setEnable(rs.getInt("enable"));
				res.setOrderNo(rs.getInt("orderNo"));
				res.setResourceType(rs.getInt("resourceType"));
				res.setParentResourceId(rs.getLong("parentResourceId"));
				return res;
			}
		});
		this.getLogger().info("=========queryResourceByPid======end==");
		return list;
	}


}
