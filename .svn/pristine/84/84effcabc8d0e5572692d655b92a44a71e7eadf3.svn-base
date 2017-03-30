package com.hbcsoft.sys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbcsoft.common.ReadProperties;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.dao.ResourceDaoImp;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * 资源管理-CRUD业务逻辑）
 */
@Service("ResourceService2")
public class ResourceServiceImp2 extends LogBaseClass<ResourceServiceImp2> implements ResourceService2 {
	/**
	 * jdbc模板
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 接口实现类注入
	 */
	@Autowired
	private transient ResourceDaoImp resourceDao;
	
	/**
	 * 查询所有(查询某一父节点下的所有子节点)
	 * @param request 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	@Override
	public List<Resource> queryAll(final Resource resource, final String id, final String companyId,final HttpServletRequest request)
			throws HbcsoftException, InstantiationException, IllegalAccessException, IOException {
		this.getLogger().info("============queryAll=========start==");
		final String code = resource.getCode();
		final String name = resource.getName();
		final String sql = HbcsoftCache.getSqlValue("menu_queryAllByCompanyId");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(companyId);
		if (id != null && !"".equals(id)) {
			sb.append(" AND PARENTRESOURCEID = ?");
			params.add(id);
		}
		if (code != null && !"".equals(code)) {
			sb.append(" AND CODE LIKE ?");
			params.add("%" + code + "%");
		}
		if (name != null && !"".equals(name)) {
			sb.append(" OR NAME LIKE ?");
			params.add("%" + name + "%");
		}
		final ReadProperties rp = new ReadProperties();
		final List<String> codes = rp.getCode(request);
		sb.append(" AND CODE NOT IN ( ");
		for (int j = 0; j < codes.size(); j++) {
			if("".equals(codes.get(j))){
				sb.append("''");
				break;
			}
			sb.append(" ? ");
			if(j!=codes.size()-1){
				sb.append(", ");
			}
			params.add(codes.get(j));
		}
		sb.append(" )");
		sb.append(" ORDER BY CREATETIME DESC ");
		final List<Resource> list = resourceDao.queryAll(sb.toString(), jt, params.toArray(new Object[params.size()]));
		this.getLogger().info("查询所有SQL:" + sb);
		this.getLogger().info("============queryAll=========end==");
		return list;
	}

	/**
	 * 分页查询
	 * @throws IOException 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Override
	public List<Resource> queryAllResourceType(final Resource resource, final String parentResourceId,
			final String companyId, final int startRow, final int pageSize,final HttpServletRequest request) throws HbcsoftException, IOException {
		this.getLogger().info("============queryAllResourceType=========start==");
		final String code = resource.getCode();
		final String name = resource.getName();
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("menu_queryAllByCompanyId");
		final StringBuilder sb = new StringBuilder(sql);
		params.add(companyId);
		if (parentResourceId != null && !"".equals(parentResourceId)) {
			sb.append(" AND PARENTRESOURCEID = ?");
			params.add(parentResourceId);
		}
		if (code != null && !"".equals(code)) {
			sb.append(" AND CODE LIKE ?");
			params.add("%" + code + "%");
		}
		if (name != null && !"".equals(name)) {
			sb.append(" AND NAME LIKE ?");
			params.add("%" + name + "%");
		}
		final ReadProperties rp = new ReadProperties();
		final List<String> codes = rp.getCode(request);
		sb.append(" AND CODE NOT IN ( ");
		for (int j = 0; j < codes.size(); j++) {
			sb.append(" ? ");
			if(j!=codes.size()-1){
				sb.append(", ");
			}
			params.add(codes.get(j));
		}
		sb.append(" )");
		sb.append(" ORDER BY CREATETIME DESC ");
		List<Resource> list = null;
		try {
			list = resourceDao.queryAll(sb.toString(), jt, startRow, pageSize,params.toArray(new Object[params.size()]));
			this.getLogger().info("分页查询SQL:" + sb);
		} catch (InstantiationException | IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryAllResourceType=========end==");
		return list;
	}
	/**
	 * 根据id查询某条数据，显示到修改页面上
	 */
	@Override
	public Resource queryResourceById(final String id, final String companyId) throws HbcsoftException {
		this.getLogger().info("============queryResourceById=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_queryResById");
		Resource resource = new Resource();
		try {
			resource = resourceDao.query(sql, jt, id);
			this.getLogger().info("查询单条SQL:" + sql);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryResourceById=========end==");
		return resource;
	}

	/**
	 * 根据id查询上级资源的名称，显示到添加和修改页面的“上级资源”字段上
	 */
	public Resource queryIdAndName(final String id) throws HbcsoftException {
		this.getLogger().info("============queryIdAndName=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_queryById");
		Resource resource = new Resource();
		try {
			resource = resourceDao.query(sql, jt, id);
			this.getLogger().info("查询上级资源名称SQL:" + sql);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryIdAndName=========end==");
		return resource;
	}
	
	/**
	 * 更新
	 */
	@Override
	public int update(final Resource entity) {
		this.getLogger().info("============update=========end==");
		try {
			resourceDao.update(entity, jt);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============update=========end==");
		return 1;
	}

	/**
	 * 根据ids查询
	 */
	@Override
	public Resource queryResourceByIds(final String ids, final String companyId) {
		this.getLogger().info("============queryResourceByIds=========start==");
		final String sql = HbcsoftCache.getSqlValue("menu_queryByIdCompanyId");
		Resource resource = new Resource();
		try {
			resource = resourceDao.query(sql, jt, new Object[] { ids, companyId });
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("SQL:" + sql);
		this.getLogger().info("============queryResourceByIds=========end==");
		return resource;
	}

	/**
	 * 删除
	 */
	@Override
	public void deletebyIds(final Resource entity) {
		this.getLogger().info("============deletebyIds=========start==");
		try {
			resourceDao.delete(entity, jt);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============deletebyIds=========end==");
	}

	/**
	 * 保存表单表数据
	 */
	@Override
	public void addResource(final Resource resource) throws HbcsoftException {
		this.getLogger().info("============addResource=========start==");
		resourceDao.save(resource, jt);
		this.getLogger().info("============addResource=========end==");
	}

	/**
	 * 根据路径查询
	 */
	@Override
	public Resource queryForAndRes(final Long companyId, final String actionUrl) throws HbcsoftException {
		Resource resource = new Resource();
		final String sql = HbcsoftCache.getSqlValue("menu_queryForAndRes");
		try {
			resource = resourceDao.query(sql, jt, new Object[] { String.valueOf(companyId), actionUrl });
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return resource;
	}
	/**
	 * 根据编码或名称查询，是否已经存在
	 */
	@Override
	public Resource queryByCodeName(final Resource res, final String companyId) throws HbcsoftException {
		Resource resource = new Resource();
		final String sql = HbcsoftCache.getSqlValue("menu_queryByCodeName");
		try {
			resource = resourceDao.query(sql, jt, res.getCode(), res.getName(), companyId, String.valueOf(res.getId()));
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return resource;

	}
	/**
	 * 删除报表资源配置
	 * @throws HbcsoftException 
	 */
	@Override
	public void deleteReportResource(final long companyId, final String reportType, final String reportName,final String reportUrl) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("menu_queryReportResource");
		try {
			final Resource resource = (Resource) resourceDao.query(sql, jt,companyId,reportType,reportName,reportUrl);
			this.deletebyIds(resource);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.ERROR_CODE,e);
		}
	}


}