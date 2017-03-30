package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.ClickManage;
/**
 * 点选页面管理的service接口
 * @author churuifeng
 *
 */
@Service
public interface ClickManageService {
	/**
	 * 查询所有  及添加查询条件查询
	 * @param keys
	 * @param values
	 * @return
	 * @throws HbcsoftException
	 */
	List<ClickManage> queryAll(String keys, String values) throws HbcsoftException;
	/**
	 * 查询出所有的表名称
	 * @return
	 */
	List<ClickManage> queryAllClick(String keys, String values, int startRow, int pageSize) throws HbcsoftException;
	/**
	 * 根据Id进行修改状态（删除）
	 * @param clickId
	 * @return
	 */
	int updateClick(String clickId);
	/**
	 * 根据key和公司Id查询所有数据
	 * @param key
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<ClickManage> queryClick(String key, String companyId) throws HbcsoftException;
	/**
	 * 添加
	 * @param clickManage
	 * @return
	 * @throws HbcsoftException
	 */
	int savaClick(ClickManage clickManage) throws HbcsoftException;
	/**
	 * 根据Id查询所有信息
	 * @param id
	 * @return
	 */
	ClickManage queryCm(String id);
	/**
	 * 修改
	 * @param cm
	 * @return
	 * @throws HbcsoftException
	 */
	int updateClick(ClickManage cm) throws HbcsoftException;
	/**
	 * 获取点选信息
	 * @param companyID
	 * @return
	 * @throws HbcsoftException 
	 */
	List<ClickManage> getAllOptions(Long companyID) throws HbcsoftException;
}
