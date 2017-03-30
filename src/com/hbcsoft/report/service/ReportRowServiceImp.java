package com.hbcsoft.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportConfigDaoImp;
import com.hbcsoft.report.dao.ReportRowDaoImp;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportRow;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * 表体业务逻辑层
 */
@Transactional
@Service("ReportRowService")
public class ReportRowServiceImp extends LogBaseClass<ReportRowServiceImp> implements ReportRowService {
	/**
	 * jdbc模板类
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 表体dao
	 */
	@Autowired
	private transient ReportRowDaoImp reportRowDao;
	/**
	 * 报表配置
	 */
	@Autowired
	private transient ReportConfigDaoImp configDao;
	/**
	 * 查询所有
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Override
	public List<ReportRow> queryAll(final ReportRow repRow, final String id, final String companyId)
			throws HbcsoftException, InstantiationException, IllegalAccessException {
		this.getLogger().info("============queryAll=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryAll");
		final String hname = repRow.getHname();
		final String filed = repRow.getFiled();
		this.getLogger().info("查询所有SQL:" + sql);
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(companyId);
		if (id != null && !"".equals(id)) {
			sb.append(" AND PARENTID = ?");
			params.add(id);
		}
		if (hname != null && !"".equals(hname)) {
			sb.append(" AND HNAME LIKE ?");
			params.add("%"+hname+"%");
		}
		if (filed!= null && !"".equals(filed)) {
			sb.append(" OR FILED LIKE ?");
			params.add("%"+filed+"%");
		}
		final List<ReportRow> list = reportRowDao.queryAll(sb.toString(), jt, params.toArray(new Object[params.size()]));
		this.getLogger().info("============queryAll=========end==");
		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Override
	public List<ReportRow> queryAllPage(final ReportRow repRow, final String parentId, final String companyId,
			final int startRow, final int pageSize)
			throws HbcsoftException, InstantiationException, IllegalAccessException {
		this.getLogger().info("============queryAllPage=========start==");
		final String hname = repRow.getHname();
		final String filed = repRow.getFiled();
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryAll");
		final StringBuilder sb = new StringBuilder(sql);
		final List<String> params = new ArrayList<String>();
		params.add(companyId);
		if (parentId != null && !"".equals(parentId)) {
			sb.append(" AND PARENTID = ?");
			params.add(parentId);
		}
		if (hname != null && !"".equals(hname)) {
			sb.append(" AND HNAME LIKE ?");
			params.add("%"+hname+"%");
		}
		if (filed!= null && !"".equals(filed)) {
			sb.append(" AND FILED LIKE ?");
			params.add("%"+filed+"%");
		}
		this.getLogger().info("分页和模糊查询SQL:" + sb);
		final List<ReportRow> list = reportRowDao.queryAll(sb.toString(), jt, startRow, pageSize, params.toArray(new Object[params.size()]));
		this.getLogger().info("============queryAllPage=========end==");
		return list;
	}
	
	/**
	 * 根据id查询
	 */
	@Override
	public ReportRow queryById(final String id) throws HbcsoftException {
		this.getLogger().info("============queryById=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryById");
		ReportRow reportRow = new ReportRow();
		try {
			reportRow = reportRowDao.query(sql, jt, id);
			this.getLogger().info("查询单条SQL:" + sql);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryById=========end==");
		return reportRow;
	}
	/**
	 * 更新
	 */
	@Override
	public int update(final ReportRow entity) {
		this.getLogger().info("============update=========end==");
		try {
			reportRowDao.update(entity, jt);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("============update=========end==");
		return 1;
	}
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public void saveReportRow(final ReportRow reportRow) throws HbcsoftException {
		this.getLogger().info("============saveReportRow=========start==");
		reportRowDao.save(reportRow, jt);
		this.getLogger().info("============saveReportRow=========end==");
	}
	
	/**
	 * 删除
	 */
	@Override
	public int deleteById(final ReportRow entity) throws HbcsoftException {
		this.getLogger().info("=============deleteById=========start========");
		reportRowDao.update(entity, jt);
		final String sql = HbcsoftCache.getSqlValue("reportRow_updateSortZero");
		jt.update(sql, entity.getParentId());
		this.getLogger().info("=============deleteById========end==========");
		return 1;
	}
	
	/**
	 * 树形
	 */
	public  List<TreeNode> getRepRowTreeMenu(final String companyId) throws HbcsoftException{
		final List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryConfig");
		try {
			//根节点 
			final List<ReportConfig> configList = configDao.queryAll(sql, jt,companyId);
			for (final ReportConfig config : configList) {
				if (null != config) {
					final TreeNode parentTreeNode=new TreeNode();
					parentTreeNode.setName(config.getReportName());
					parentTreeNode.setId(config.getId());
					parentTreeNode.setOpen(true);
					parentTreeNode.setChildren(this.getChild(companyId,config));
					treeNodes.add(parentTreeNode);
				}
			}
		} catch (NumberFormatException e) {
			this.getLogger().info(e);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return treeNodes;
	}
	/**
	 *根据报表id和公司id查
	 */
	@Override
	public List<ReportRow> queryRowByReportID(final Long companyId, final Long reportId)throws HbcsoftException
	{
		try {
			final String sql = HbcsoftCache.getSqlValue("reportRow_queryRowByReportID");
			return reportRowDao.queryAll(sql, jt, new Object[]{companyId,reportId});
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return null;
	}
	
	/**
	 *表体子节点
	 */
	private List<TreeNode> getChild(final String companyId,final ReportConfig config){
		final List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		try {
			 final List<ReportRow> rows = getRepRowByPId(companyId,String.valueOf(config.getId()));
			 for (final ReportRow row : rows) {
					if (null != row) {
						final TreeNode node = this.repRowToTreeNode(row,companyId,config);
						if (null != node) {
							treeNodes.add(node);
						}
					}
				}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		} 
		return treeNodes; 
	}
	/**
	 * 根据parentId查询子节点
	 **/
	private List<ReportRow> getRepRowByPId(final String companyId,final String parentId) throws HbcsoftException {
		this.getLogger().info("============getRepRowByPId=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryByPid");
		List<ReportRow> list =new ArrayList<ReportRow>();
		list = reportRowDao.queryByPid(sql, jt, companyId,parentId);
		this.getLogger().info("============getRepRowByPId=========end==");
		return list;
	}
	/**
	 * 节点转换
	 * @throws HbcsoftException 
	 */
	private TreeNode repRowToTreeNode(final ReportRow repRow, final String companyId, final ReportConfig config) throws HbcsoftException {
		this.getLogger().info("============repRowToTreeNode=========start==");
		final TreeNode treeNode = new TreeNode(repRow.getId(), repRow.getHname(), "", false);
		final List<TreeNode> childrenTreeNodes = new ArrayList<TreeNode>();
		final List<ReportRow> listchild = getChildrendtType(repRow.getId(), companyId,String.valueOf(config.getId()));
		for (final ReportRow childrenCorp : listchild) {
			final TreeNode node = repRowToTreeNode(childrenCorp, companyId,config);
			if (node != null) {
				childrenTreeNodes.add(node);
			}
		}
		treeNode.setChildren(childrenTreeNodes);
		this.getLogger().info("============repRowToTreeNode=========end==");
		return treeNode;
	}
	/**
	 * 得到子节点
	 * 
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	private List<ReportRow> getChildrendtType(final Long id, final String companyId,final String reportId) throws HbcsoftException {
		this.getLogger().info("============getChildrendtType=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_getChildrenRow");
		List<ReportRow> list = new ArrayList<ReportRow>();
		list = reportRowDao.queryByPid(sql, jt, String.valueOf(id), companyId,reportId);
		this.getLogger().info("============getChildrendtType=========end==");
		return list;
	}

	/**
	 * 添加时---根据报表id查询是否存在
	 */
	@Override
	public ReportConfig queryConfigByIds(final String configId) {
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryConfigById");
		ReportConfig config = new ReportConfig();
		try {
			config = configDao.query(sql, jt, configId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return config;
	}
	/**
	 * 根据parentId查询子节点
	 */
	@Override
	public boolean queryByParentId(final String id) throws HbcsoftException {
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryByParentId");
		boolean flag = false;
		try {
			final List<ReportRow> list = reportRowDao.queryAll(sql, jt, id);
			final int count = list.size();
			if(count>0){
				flag = true;
			}else{
				flag = false;
			}
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return flag;
	}
	/**
	 * 根据parentId查询子节点,返回集合
	 */
	@Override
	public List<ReportRow> getChildrenRow(final Long id) throws HbcsoftException {
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryByParentId");
		List<ReportRow> list = new ArrayList<ReportRow>();
		try {
			list = reportRowDao.queryAll(sql, jt, id);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return list;
	}
	/**
	 * 添加子节点后更新父节点的isEnd状态
	 * **/
	public void updatePIsEnd(final Long PId)throws HbcsoftException {
		this.getLogger().info("============updateParIsEnd=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_updateParIsEnd");
		jt.update(sql, PId);
		this.getLogger().info("////////sql="+sql);
		this.getLogger().info("============updateParIsEnd=========end==");
	}
	/**
	 * 删除子节点后更新父节点的isEnd状态
	 * **/
	public void updatePIsEndAfterDel(final Long PId)throws HbcsoftException {
		this.getLogger().info("============updatePIsEndAfterDel=========start==");
		final String sql = HbcsoftCache.getSqlValue("reportRow_updatePIsEndAfterDel");
		jt.update(sql, PId);
		this.getLogger().info("////////sql="+sql);
		this.getLogger().info("============updatePIsEndAfterDel=========end==");
	}
	/**
	 * 删除之后查询
	 * **/
	@Override
	public List<ReportRow> queryAfterDel(final String companyId, final Long parentId) {
		List<ReportRow> rows = new ArrayList<ReportRow>();
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryByPid");
		try {
			rows=reportRowDao.queryAll(sql, jt, companyId, parentId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return rows;
	}
	/**
	 * 从大到小排序，取最大的一个
	 * **/
	@Override
	public ReportRow queryMaxSort(final String companyId, final String parentId) {
		this.getLogger().info("=============queryMaxSort==============delete===================start========");
		final String sql = HbcsoftCache.getSqlValue("reportRow_queryMaxSort");
		ReportRow rr= new ReportRow();
		try {
			 rr = reportRowDao.query(sql, jt, companyId,parentId);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		this.getLogger().info("=============queryMaxSort==============delete===================start========");
		return rr;
	}
	/**
	 * 删除之后，重新排序
	 */
	@Override
	public void updateSortDesc(final int sort, final String status,final Long id)throws HbcsoftException {
		this.getLogger().info("=============updateSortDesc===============start========");
		final String sql = HbcsoftCache.getSqlValue("reportRow_updateSortDesc");
		jt.update(sql,sort,status,id);
		this.getLogger().info("=============updateSortDesc===============end========");
	}

}
