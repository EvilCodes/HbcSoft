package com.hbcsoft.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportConfigDaoImp;
import com.hbcsoft.report.dao.ReportHeadDaoImp;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportHead;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;


/**
 * 表头配置service层实现类
 * 
 * @author Administrator
 *
 */
@Transactional
@Service("ReportHeadService")
public class ReportHeadServiceImp extends LogBaseClass<ReportHeadServiceImp>
		implements ReportHeadService {
	/**
	 * HbcsoftJT
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 报表头dao
	 */
	@Autowired
	private transient ReportHeadDaoImp headDao;
	/**
	 * 报表配置
	 */
	@Autowired
	private transient ReportConfigDaoImp configDao;
	/**
	 * 树形
	 * 
	 */
	public  List<TreeNode> getReportHeadTreeMenu(final String companyId) throws HbcsoftException{
		final List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		final String sqlConfig = HbcsoftCache.getSqlValue("reportHead_queryConfig");
		try {
			//根节点 
			final List<ReportConfig> configList = configDao.queryAll(sqlConfig, jt,companyId);
			
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
			//子节点 
//			 List<ReportHead> headList = getReportHeadByParentId(null, companyId);
//			 for (final ReportHead head : headList) {
//					if (null != head) {
//						final TreeNode node = this.reportHeadToTreeNode(head,companyId);
//						if (null != node) {
//							treeNodes.add(node);
//						}
//					}
//				} 
			// treeNode.addAll(treeNodes);
		} catch (NumberFormatException e) {
			this.getLogger().info(e);
		} catch (InstantiationException e) {
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			this.getLogger().info(e);
		}
		return treeNodes;
	}
	//报表头子节点
	private List<TreeNode> getChild(final String companyId,final ReportConfig config){
		final List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		try {
			//子节点 
			final List<ReportHead> headList = getReportHeadByParentId(String.valueOf(config.getId()), companyId);
			 for (final ReportHead head : headList) {
					if (null != head) {
						final TreeNode node = this.reportHeadToTreeNode(head,companyId,config);
						if (null != node) {
							treeNodes.add(node);
						}
					}
				}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} 
		return treeNodes; 
	}
	/**
	 * 单位转换成树形节点
	 * 
	 * @param corp
	 * @return
	 */
	public TreeNode reportHeadToTreeNode(final ReportHead head,
			final String companyId,final ReportConfig config) {
		this.getLogger().info(
				"============reportHeadToTreeNode=========start==");
		final TreeNode treeNode = new TreeNode(head.getId(),head.getHname(),
				"", false);
		final List<TreeNode> childrenTreeNodes = new ArrayList<TreeNode>();
		final List<ReportHead> listchild = getChildrendHead(
				head.getId(), companyId);//,String.valueOf(config.getId())
		for (final ReportHead childrenCorp : listchild) {
			final TreeNode node = reportHeadToTreeNode(childrenCorp, companyId,config);
			if (node != null) {
				childrenTreeNodes.add(node);
			}
		}
		treeNode.setChildren(childrenTreeNodes);
		
		this.getLogger().info("============reportHeadToTreeNode=========end==");
		return treeNode;
	}
	/**
	 * 根据父类id查询单位
	 * @param parentId
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List<ReportHead> getReportHeadByParentId(
			final String parentId, final String companyId)
			throws HbcsoftException {
		this.getLogger().info(
				"============getReportHeadByParentId=========start==");
		final String sql = HbcsoftCache
				.getSqlValue("reportHead_queryHeadByPid");
		List<ReportHead> list = new ArrayList<ReportHead>();
		list = (List<ReportHead>) headDao.queryReportHeaderByPid(sql,
				jt,companyId,parentId);
		this.getLogger().info(
				"============getReportHeadByParentId=========end==");
		return list;
	}
	/**
	 * 得到子类型
	 * @param id
	 * @return
	 */
	public List<ReportHead> getChildrendHead(final Long id,
			final String companyId) {//,final String reportId
		this.getLogger().info("============getChildrendHead=========start==");
		List<ReportHead> list;
		final String sql = HbcsoftCache
				.getSqlValue("reportHead_getChildrendHead");
		list = (List<ReportHead>) headDao.queryReportHeaderByPid(sql,
				jt, String.valueOf(id), companyId);//,reportId
		this.getLogger().info("============getChildrendHead=========end==");
		return list;
	}
	/**
	 * 根据id查询数据
	 */
	@Override
	public ReportHead queryReportHeadById(final String id)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryReportHeadById");
		ReportHead head = new ReportHead();
		try {
			head = headDao.query(sql, jt, id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return head;
	}
	/**
	 * 添加
	 */
	@Override
	public int save(final ReportHead head) throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("========ReportHeaderConfigServiceImp=======save================start===");
		headDao.save(head, jt);
		this.getLogger().info("========ReportHeaderConfigServiceImp=====save===================end===");
		return 1;
	}
	/**
	 * 修改
	 */
	@Override
	public int update(final ReportHead head) throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("=========ReportHeaderConfigServiceImp======update================start===");
		headDao.update(head, jt);
		this.getLogger().info("=========ReportHeaderConfigServiceImp======update==================end===");
		return 1;
	}
	/**
	 * 查询行
	 */
	@Override
	public List<ReportHead> queryAll(final ReportHead head,
			final String id,final String companyId) throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("=========ReportHeaderConfig===queryAll=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryAll");
		params.add(companyId);
		final StringBuilder sb = new StringBuilder(sql);
		if (id != null && !"".equals(id)) {
			sb.append(" AND parentId = ?");
			params.add(id);
		}
		if (head.getHname() != null && !"".equals(head.getHname())) {//名称
			sb.append(" AND headName like ?");
			params.add("%"+head.getHname()+"%");
		}
		if (head.getField() != null && !"".equals(head.getField())) {//字段
			sb.append(" AND field like ?");
			params.add("%"+head.getField()+"%");
		}
		if (head.getValue() != null && !"".equals(head.getValue())) {//值
			sb.append(" AND value like ?");
			params.add("%"+head.getValue()+"%");
		}
		this.getLogger().info("=========ReportHeaderConfig===queryAll=========end==");
		return this.returnListCount(params, sb);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<ReportHead> queryAllReportHeaderConfig(
			final ReportHead head,final String id,final String companyId,final int startRow,
			final int pageSize) throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("============queryAllReportHeaderConfig=========start==");
		final List<String> params = new ArrayList<String>();
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryAll");
		params.add(companyId);
		final StringBuilder sb = new StringBuilder(sql);
		if (id != null && !"".equals(id)) {
			sb.append(" AND parentId = ?");
			params.add(id);
		}
		if (head.getHname() != null && !"".equals(head.getHname())) {//名称
			sb.append(" AND headName like ?");
			params.add("%"+head.getHname()+"%");
		}
		if (head.getField() != null && !"".equals(head.getField())) {//字段
			sb.append(" AND field like ?");
			params.add("%"+head.getField()+"%");
		}
		if (head.getValue() != null && !"".equals(head.getValue())) {//值
			sb.append(" AND value like ?");
			params.add("%"+head.getValue()+"%");
		}
		
		this.getLogger().info("============queryAllReportHeaderConfig=========end==");
		return this.returnListCount(params, sb, startRow, pageSize);
	}
	/**
	 * 解决pmd中的循环复杂度问题
	 * @param params
	 * @param sql
	 * @return
	 */
	private List<ReportHead> returnListCount(final List<String> params,
			final StringBuilder  sql) {// 行
		List<ReportHead> list = new ArrayList<ReportHead>();
		final boolean paramsize = params.isEmpty();
		final boolean pars = params == null;
		try {
			if (paramsize || pars) {
					list = (List<ReportHead>) headDao.queryAll(sql.toString(), jt,
							params.toArray(new Object[params.size()]));//new String[params.size()]
			} else {
					list = (List<ReportHead>) headDao.queryAll(sql.toString(), jt,
							params.toArray(new Object[params.size()]));//new String[params.size()]
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
	/**
	 * 解决pmd中的循环复杂度问题
	 * @param params
	 * @param sql
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	private List<ReportHead> returnListCount(final List<String> params,
			final StringBuilder  sql, final int startRow, final int pageSize) {// 分页
		List<ReportHead> list = new ArrayList<ReportHead>();
		final boolean paramsize = params.isEmpty();
		try {
			final boolean pars = params == null;
			if (paramsize || pars) {
					list = (List<ReportHead>) headDao.queryAll(sql.toString(), jt, startRow,
							pageSize);
			} else {
					list = (List<ReportHead>) headDao.queryAll(sql.toString(), jt, startRow,
							pageSize, params.toArray(new Object[params.size()]));//new String[params.size()]
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
	/**
	 * 删除
	 */
	@Override
	public void delete(final ReportHead head) throws HbcsoftException {
		// TODO Auto-generated method stub
		this.getLogger().info("=============ReportHeaderConfig==============delete===================start========");
			//headDao.delete(head, jt);
			headDao.update(head, jt);
			final String sql = HbcsoftCache.getSqlValue("reportHead_updateSortZero");
			jt.update(sql, head.getParentId());
			//update z_report_head set  sort=sort-1 where id=id
			
		this.getLogger().info("=============ReportHeaderConfig==============delete====================end==========");
	}
	/**
	 * 删除之后，修改sort
	 */
	@Override
	public void updateSortDesc(int sort, Long id)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("reportHead_updateSortDesc");
		jt.update(sql,sort,id);
	}
	/**
	 * 根据公司id查询删除之后的数据
	 * @param companyId
	 * @return
	 */
	@Override
	public List<ReportHead> querydelAfterReportHead(final String companyId,final Long parentId){
		List<ReportHead> rhlist = new ArrayList<ReportHead>();
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryheadByParentId");
		try {
			rhlist=headDao.queryAll(sql, jt, companyId,parentId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return rhlist;
	}
	/**
	 * 查询最大的sort
	 */
	@Override
	public ReportHead queryMaxSort(final String companyId ,final String parentId){
		this.getLogger().info("=============queryMaxSort==============delete===================start========");
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryMaxSort");
		ReportHead rh= new ReportHead();
		try {
			 rh = headDao.query(sql, jt, companyId,parentId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("=============queryMaxSort==============delete===================start========");
		return rh;
		
	}
	
	
	/**
	 * 根据父节点id查询父节点下是否有子节点
	 */
	@Override
	public boolean queryReportHeadByParentId(final String id) throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryReportHeadByParentId");
		boolean flag = false;
		try {
			final List<ReportHead> list = headDao.queryAll(sql, jt, id);
			final int count = list.size();
			if(count>0){
				flag = true;
			}else{
				flag = false;
			}

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return flag;
	}
	/**
	 * 根据报表id查询是否存在
	 */
	@Override
	public ReportConfig queryReportConfigById(final String configId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryReportConfById");
		ReportConfig config = new ReportConfig();
		try {
				config = configDao.query(sql, jt, configId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return config;
	}
	/**
	 * 根据主键查询头名称，判断是否在数据库里已经存在
	 */
	@Override
	public ReportHead queryhNameById(final ReportHead rhead,final String companyId)
			throws HbcsoftException {
		// TODO Auto-generated method stub
		final String sql = HbcsoftCache.getSqlValue("reportHead_queryhNameById");
		ReportHead head = new ReportHead();
		try {
			head = headDao.query(sql, jt, String.valueOf(rhead.getId()),companyId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return head;
	}

	@Override
	public List<ReportHead> queryHeadByReportID(final Long companyId, final Long reportId)throws HbcsoftException
	{
		try {
			
			final String sql = HbcsoftCache.getSqlValue("reportHead_queryHeadByReportID");
			return headDao.queryAll(sql, jt, new Object[]{companyId,reportId});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		
		return null;
	}
	/**
	 * 根据父节点查询
	 */
	@Override
	public List<ReportHead> queryByParentId(String parentId) {
		// TODO Auto-generated method stub
		List<ReportHead> hlist = new ArrayList<ReportHead>();
		try {
			final String sql = HbcsoftCache.getSqlValue("reportHead_queryByParentId");
			hlist= headDao.queryAll(sql, jt, parentId);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return hlist;
	}
	
}
