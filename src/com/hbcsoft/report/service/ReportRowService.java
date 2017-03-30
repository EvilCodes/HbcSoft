package com.hbcsoft.report.service;

import java.util.List;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportRow;

/**
 * 表体业务逻辑层
 * */
public interface ReportRowService {
	/**
	 * 查询所有
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<ReportRow> queryAll(ReportRow RepRow, String parentId, String companyId) throws HbcsoftException, InstantiationException, IllegalAccessException;
	/**
	 * 分页查询所有
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	List<ReportRow> queryAllPage(ReportRow RepRow, String parentId, String companyId, int startRow, int pageSize)
			throws HbcsoftException, InstantiationException, IllegalAccessException;
	/**
	 * 表体树形
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	List<TreeNode> getRepRowTreeMenu(String companyId) throws HbcsoftException, InstantiationException, IllegalAccessException;
	/**
	 * 根据parentId查询
	 * **/
	boolean queryByParentId(String id) throws HbcsoftException;
	/**
	 * 根据parentId查询表配置
	 * **/
	ReportConfig queryConfigByIds(String parentId);
	/**
	 * 根据id查询
	 * @throws HbcsoftException 
	 * **/	
	ReportRow queryById(String id) throws HbcsoftException;
	/**
	 * 删除
	 * @throws HbcsoftException
	 * **/
	int deleteById(final ReportRow entity)throws HbcsoftException;
	/**
	 * 更新
	 * **/
	int update(ReportRow entity) throws HbcsoftException;
	/**
	 * 保存
	 * **/
	void saveReportRow(ReportRow reportRow) throws HbcsoftException;
	/**
	 * 根据报表id查
	 * **/
	List<ReportRow> queryRowByReportID(final Long companyId, final Long reportId)throws HbcsoftException;
	/**
	 * 获取子节点
	 * **/
	List<ReportRow> getChildrenRow(final Long id)throws HbcsoftException;
	/**
	 * 添加后更新父节点的isEnd状态
	 * **/
	void updatePIsEnd(final Long id)throws HbcsoftException;
	/**
	 * 删除子节点后更新父节点的isEnd状态
	 * **/
	void updatePIsEndAfterDel(final Long id)throws HbcsoftException;
	/**
	 * 删除之后查询
	 * **/
	List<ReportRow> queryAfterDel(final String companyId,final Long parentId);
	/**
	 * 从大到小排序，取最大的一个
	 * **/
	ReportRow queryMaxSort(final String companyId,final String parentId);
	/**
	 * 删除之后，重新排序
	 */
	void updateSortDesc(int sort,String status, Long id) throws HbcsoftException;
}
