package com.hbcsoft.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbcsoft.InterManage.entity.Sublist;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.dao.ReportConfigInterfaceDao;
import com.hbcsoft.report.dao.ReportConfigInterfaceDaoImp;
import com.hbcsoft.report.entity.ReportConfigInterface;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Transactional
@Service("ReportConfigServiceInterface")
public class ReportConfigServiceInterfaceImp extends 
LogBaseClass<ReportConfigServiceInterfaceImp>implements ReportConfigServiceInterface {

	@Autowired
	private ReportConfigInterfaceDaoImp interfaceDao;
	
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * 保存接口信息
	 */
	@Override
	public void saveInterfaceMessage(List<ReportConfigInterface> interfaceList)
			throws HbcsoftException {
			if (!interfaceList.isEmpty()){
				for (int i = 0; i < interfaceList.size(); i++){
					interfaceDao.save(interfaceList.get(i), jt);
				}
			}
	}
	/**
	 * 查询报表主体对应的借口
	 */
	@Override
	public List<ReportConfigInterface> queryEntity(String tempReportId) {
		
		// TODO:调用zdy打包方法根据主表id查询所有的实体内容
		this.getLogger().info("============queryEntity=========start==");
		
		final String sql = HbcsoftCache.getSqlValue("reportConfigInterface_queryEntity");
		List<ReportConfigInterface> list = new ArrayList<ReportConfigInterface>();
		list = (List<ReportConfigInterface>) interfaceDao.queryListEntity(sql, jt, tempReportId);
		
		this.getLogger().info("============queryEntity=========end==");
		return list;
	}
	/**
	 * 校验报表主体与接口信息
	 */
	@Override
	public String[] validateEntity(String tempReportId, String... rowids)
			throws HbcsoftException {
		
		this.getLogger().info("============queryEntity=========start==");
		String [] temprowids = {};
		final String sql = HbcsoftCache.getSqlValue("reportConfigInterface_queryEntity");
		List<ReportConfigInterface> list = new ArrayList<ReportConfigInterface>();
		list = (List<ReportConfigInterface>) interfaceDao.queryListEntity(sql, jt, tempReportId);
		if (rowids == null && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++){
				ReportConfigInterface ri = list.get(i);
				ri.setFlag(1);
				interfaceDao.update(ri, jt);
			}
			return temprowids;
		}else if (rowids == null && list.isEmpty()){
			return temprowids;
		}
		if (list.isEmpty())
			return rowids;
		List<String> tempId = new ArrayList<String>();//已经存在的接口信息id
		List<String> tempId2 = new ArrayList<String>();//不存在的接口信息id
		List<ReportConfigInterface> tempList = new ArrayList<ReportConfigInterface>();
		
		//记录接口信息是否存在
		for (int i = 0; i < rowids.length; i++){
			String id = rowids[i];
			for (int j = 0; j < list.size();j++){
				String primaryId = list.get(j).getPrimaryListId().toString();
				if (id.equals(primaryId)){
					tempId.add(id);
					break;
				}
			}
		}
		if (tempId.isEmpty()){
			for (int i = 0; i < list.size(); i++){
				ReportConfigInterface ri = list.get(i);
				ri.setFlag(1);
				interfaceDao.update(ri, jt);
			}
		}else{
			
			for (int i = 0; i < list.size(); i++){
				ReportConfigInterface ri = list.get(i);
				boolean flag = true;
				for (int j = 0; j < tempId.size(); j++){
					if (ri.getPrimaryListId().toString().equals(tempId.get(j))){
						flag = false;
					}
				}
				if (flag)
					tempList.add(ri);
			}
			
			if (!tempList.isEmpty()){
				for (int i = 0; i < list.size(); i++){
					ReportConfigInterface ri = list.get(i);
					ri.setFlag(1);
					interfaceDao.update(ri, jt);
				}
			}
			
			for (int i = 0; i < rowids.length;i++){
				boolean flag = true;
				for (int j = 0; j < tempId.size(); j++){
					if (rowids[i].equals(tempId.get(j)))
						flag = false;
				}
				
				if (flag)
					tempId2.add(rowids[i]);
			}
		}
		
		if (!tempId2.isEmpty()){
			for (int i = 0; i < tempId2.size();i++){
				temprowids[i] = tempId2.get(i);
			}
		}
		this.getLogger().info("============queryEntity=========end==");
		return temprowids;
	}

	
}
