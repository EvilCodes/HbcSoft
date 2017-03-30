package com.hbcsoft.excel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hbcsoft.exception.HbcsoftException;


/**
 *Excel数据导入接口
 * @author gaodekui
 *
 */
public interface ExcelService {
	/**
	 * 操作创建数据库表
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	String operateCreateTableEntity(List<List> list, HttpServletRequest request)
			throws HbcsoftException;
	/**
	 * 操作创建表单数据
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	String opreateCreateFrom(List<List> list, HttpServletRequest request)
			throws HbcsoftException;
	/**
	 * 操作创建数据库表和表单
	 * @param list
	 * @param request
	 * @return
	 * @throws HbcsoftException 
	 */
	String opreateCreateTableAndFrom(List<List> list, HttpServletRequest request)
			throws HbcsoftException;
	/**
	 * 操作新增第三方数据库配置数据
	 * @param list
	 * @return
	 * @throws HbcsoftException 
	 */
	String operateDatabaseConfiguration(List<List> list,
			HttpServletRequest request) throws HbcsoftException;
	/**
	 * 操作新增工作流基础配置
	 * @param list
	 * @return
	 */
	String opreateWorkFlowBaseConfigure(List<List> list);
}
