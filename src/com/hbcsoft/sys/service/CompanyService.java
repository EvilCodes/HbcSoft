package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Company;
/**
 * 公司信息接口
 * @author churuifeng
 *
 */
@Service
public interface CompanyService {
	/**
	 * 添加方法
	 * @param com
	 * @return
	 * @throws HbcsoftException
	 */
	int add(Company com) throws HbcsoftException;
	/**
	 * 根据公司名称查询数据
	 * @param companyName
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Company> queryCount(String companyName) throws HbcsoftException;
	/**
	 * 根据公司英文名称查询数据
	 * @param companyName
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Company> queryCountHk(String tableName) throws HbcsoftException;
	/**
	 * 根据公司中文简称查询数据
	 * @param companyName
	 * @return
	 * @throws HbcsoftException 
	 */
	List<Company> queryCountCn(String tableName) throws HbcsoftException;
	/**
	 * 根据公司名称查询一条数据
	 * @param companyName
	 * @return
	 * @throws HbcsoftException 
	 */
	Company query(String companyName) throws HbcsoftException;
	/**
	 * 根据公司Id查询数据
	 * @param companyId
	 * @return
	 * @throws HbcsoftException 
	 */
	Company queryId(Long companyId) throws HbcsoftException;
}
