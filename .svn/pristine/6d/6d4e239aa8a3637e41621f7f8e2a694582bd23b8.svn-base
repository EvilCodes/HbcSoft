package com.hbcsoft.sys.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.dao.DictTypeDaoImp;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.PubTools;

/**
 * 数据字典类型service层实现类
 * 
 * @author Administrator
 *
 */
@Transactional
@Service("DictTypeService")
public class DictTypeServiceImp extends LogBaseClass<DictTypeServiceImp>
		implements DictTypeService {
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

	/**
	 * 
	 */
	// private static final List<TreeNode> nodes = new ArrayList<TreeNode>();
	/**
	 * 根据父节点查询id，dtname并把id的值赋给parentid，到添加页面上级类型默认为父节点
	 * 
	 * @param id
	 * @return
	 */
	public DictType queryIdAndDtname(final String id) {
		this.getLogger().info("============queryIdAndDtname=========start==");
		final String sql = HbcsoftCache.getSqlValue("dictType_queryById");
		// String sql = "SELECT * FROM T_SYS_DICTTYPE  WHERE ID = "+id;

		DictType dictType = new DictType();
		try {
			dictType = dictTypeDao.query(sql, jt, id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryIdAndDtname=========end==");
		return dictType;
	}

	/**
	 * 根据ids查询
	 */

	private DictType queryDataByIds(final String ids) {
		this.getLogger().info("============queryDataByIds=========start==");
		final String sql = HbcsoftCache.getSqlValue("dictType_queryByIds");
		DictType dictType = new DictType();
		try {
			dictType = dictTypeDao.query(sql, jt, ids);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryDataByIds=========end==");
		return dictType;
	}

	/**
	 * 删除
	 */
	@Override
	public void queryIds(String ids) {
		if (PubTools.isEmpty(ids)) {

		} else {
			ids = ids.substring(0, ids.length() - 1);
			final String[] strId = ids.split(",");
			for (int i = 0; i < strId.length; i++) {
				final String idss = strId[i];
				final DictType dt = this.queryDataByIds(idss);
				this.deletebyIds(dt);
			}
		}
	}

	/**
	 * 删除
	 */
	private int deletebyIds(final DictType entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============deletebyIds=========start==");
		try {
			dictTypeDao.delete(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============deletebyIds=========end==");
		return 1;
	}

	/**
	 * 根据id查询某条数据，显示到修改页面上
	 */
	@Override
	public DictType queryDictTypeById(final String id) {
		this.getLogger().info("============queryDictTypeById=========start==");
		// String sql = "SELECT * FROM T_SYS_DICTTYPE WHERE 1=1 AND ID = "+id;
		final String sql = HbcsoftCache.getSqlValue("dictType_queryById");
		DictType dt = new DictType();
		try {
			dt = dictTypeDao.query(sql, jt, id);
		} catch (InstantiationException e) {
			// e.printStackTrace();
			this.getLogger().info(e);
		} catch (IllegalAccessException e) {
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============queryDictTypeById=========end==");
		return dt;
	}

	/**
	 * 保存
	 */
	@Override
	public int save(final DictType entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============save=========start==");
		try {
			dictTypeDao.save(entity, jt);

		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============save=========end==");
		return 1;
	}

	/**
	 * 修改
	 */
	@Override
	public int update(final DictType entity) {
		// TODO Auto-generated method stub
		this.getLogger().info("============update=========end==");
		try {
			dictTypeDao.update(entity, jt);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			this.getLogger().info(e);
		}
		this.getLogger().info("============update=========end==");
		return 1;
	}

	/**
	 * /** 生成树形菜单节点
	 */

	public List<TreeNode> getDictTypeTreeMenu(final String companyId) throws HbcsoftException {
		this.getLogger()
				.info("============getDictTypeTreeMenu=========start==");
		final List<TreeNode> nodes = new ArrayList<TreeNode>();
		this.getNodes(nodes,companyId);
		this.getLogger().info("============getDictTypeTreeMenu=========end==");
		return nodes;
	}

	private void getNodes(final List<TreeNode> nodes,final String companyId) throws HbcsoftException {
		List<DictType> dictTypeList;
		dictTypeList = getDictTypeByParentId(null,companyId);
		for (final DictType dictType : dictTypeList) {
			if (null != dictType) {
				final TreeNode node = this.dictTypeToTreeNode(dictType,companyId);
				if (null != node) {
					nodes.add(node);
				}
			}
		}

	}

	/**
	 * /** 根据父类id查询单位
	 * 
	 * @param parentId
	 * @return
	 */
	public List<DictType> getDictTypeByParentId(final String parentId,final String companyId)
			throws HbcsoftException {
		this.getLogger().info(
				"============getDictTypeByParentId=========start==");
		//Object[] objs;
		final String sql = HbcsoftCache.getSqlValue("dictType_queryDtByPid");
//		if (null == parentId) {
//			//sql += " is null ";
//			// sql+=" 0 ";
//			objs = new Object[] {};
//		} else {
//			//sql += " =? ";
//			objs = new Object[] {parentId };
//		}
//			//sql += " order by sort asc ";
		List<DictType> list;
		list = (List<DictType>) dictTypeDao.queryDictTypeByPid(sql, jt,companyId);
		this.getLogger()
				.info("============getDictTypeByParentId=========end==");
		return list;
	}

	/**
	 * 单位转换成树形节点
	 * 
	 * @param corp
	 * @return
	 */
	public TreeNode dictTypeToTreeNode(final DictType dictType,final String companyId) {
		this.getLogger().info("============dictTypeToTreeNode=========start==");
		final TreeNode treeNode = new TreeNode(dictType.getId(),
				dictType.getDtName(), "", false);
		final List<TreeNode> childrenTreeNodes = new ArrayList<TreeNode>();
		final List<DictType> listchild = getChildrendtType(dictType.getId(),companyId);
		for (final DictType childrenCorp : listchild) {
			final TreeNode node = dictTypeToTreeNode(childrenCorp,companyId);
			if (node != null) {
				childrenTreeNodes.add(node);
			}
		}
		treeNode.setChildren(childrenTreeNodes);
		this.getLogger().info("============dictTypeToTreeNode=========end==");
		return treeNode;
	}

	/**
	 * 得到子类型
	 * 
	 * @param id
	 * @return
	 */
	public List<DictType> getChildrendtType(final Long id,final String companyId) {
		this.getLogger().info("============getChildrendtType=========start==");
		List<DictType> list;
		final String sql = HbcsoftCache
				.getSqlValue("dictType_getChildrendtType");
		list = (List<DictType>) dictTypeDao.queryDictTypeByPid(sql, jt,
				String.valueOf(id),companyId);
		this.getLogger().info("============getChildrendtType=========end==");
		return list;
	}

}
