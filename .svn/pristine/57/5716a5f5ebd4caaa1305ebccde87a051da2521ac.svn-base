package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DictType;

/**
 * 字典类型Service  interface
 * @author Administrator
 *
 */
@Repository
public interface DictTypeService {
		/**
		 * 根据id查询字典类型名称和id
		 * @param id
		 * @return
		 */
		DictType queryIdAndDtname(String id);
		/**
		 * 保存
		 * @param entity
		 * @return
		 */
		int save(DictType entity); 
		/**
		 * 修改
		 * @param entity
		 * @return
		 */
		int update(DictType entity);

		/**
		* 删除数据字典类型
		* @param entity
		* @return
		* @throws HbcsoftException
		*/
		void queryIds(String ids) throws HbcsoftException;
		/**
		* 根据id查询数据字典类型
		* @param id
		* @return
		* @throws HbcsoftException
		*/
		DictType queryDictTypeById(String id) throws HbcsoftException;
		/**
		 * 字典类型树形
		 * @return
		 * @throws HbcsoftException
		 */
		List<TreeNode> getDictTypeTreeMenu(String companyId) throws HbcsoftException;
		/**
		 * 根据父节点查询数据字典类型
		 * @param parentId
		 * @return
		 * @throws HbcsoftException
		 */
		List<DictType> getDictTypeByParentId(String parentId,String companyId) throws HbcsoftException;
		
}
