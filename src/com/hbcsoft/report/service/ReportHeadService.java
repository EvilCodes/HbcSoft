package com.hbcsoft.report.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.common.TreeNode;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportHead;

/**
 * 报表头Service interface
 * 
 * @author Administrator
 *
 */
@Repository
public interface ReportHeadService {

	/**
	 * 树形结构
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<TreeNode> getReportHeadTreeMenu(final String companyId) throws HbcsoftException;
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	ReportHead queryReportHeadById(final String id) throws HbcsoftException;
	/**
	 * 保存
	 * @param head
	 * @return
	 * @throws HbcsoftException
	 */
	int save(final ReportHead head)throws HbcsoftException;
	/**
	 * 修改
	 * @param head
	 * @return
	 * @throws HbcsoftException
	 */
	int update(final ReportHead head)throws HbcsoftException;
	/**
	 * 删除
	 * @param head
	 * @throws HbcsoftException
	 */
	void delete(final ReportHead head)throws HbcsoftException;
	/**
	 * 查询行
	 * @param head
	 * @param id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportHead>queryAll(final ReportHead head,final String id ,final String companyId)throws HbcsoftException;
	/**
	 * 分页查询
	 * @param companyId
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportHead>queryAllReportHeaderConfig(final ReportHead head,final String id 
			,final String companyId,final int startRow,final int pageSize)throws HbcsoftException;
	/**
	 * 查询父节点下是否有子节点
	 */
	boolean queryReportHeadByParentId(final String id)throws HbcsoftException;
	/**
	 * 根据报表id查询是否存在
	 * @param configId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportConfig queryReportConfigById(final String configId)throws HbcsoftException;
	/**
	 * 根据主键查询头名称，判断是否重复
	 * @param rhead
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportHead queryhNameById(final ReportHead rhead,final String companyId)throws HbcsoftException;

	List<ReportHead> queryHeadByReportID(final Long companyId, final Long reportId)throws HbcsoftException;
	/**
	 * 根据父节点查询
	 * @param parentId
	 * @return
	 */
	List<ReportHead> queryByParentId(final String parentId);
	/**
	 * 查询最大sort
	 * @param companyId
	 * @param parentId
	 * @return
	 * @throws HbcsoftException
	 */
	ReportHead queryMaxSort(final String companyId ,final String parentId) throws HbcsoftException;
	/**
	 * 根据公司id查询删除之后的数据
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ReportHead> querydelAfterReportHead(final String companyId,final Long parentId)throws HbcsoftException;
	/**
	 * 删除之后修改sort
	 * @param companyId
	 * @param sort
	 * @param id
	 * @throws HbcsoftException
	 */
	void updateSortDesc(final int sort,final Long id)throws HbcsoftException;
}
