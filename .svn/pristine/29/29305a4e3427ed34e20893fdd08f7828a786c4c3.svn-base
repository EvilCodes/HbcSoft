package com.hbcsoft.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Log;


/**
 * 日志service接口
 * @author Administrator
 *
 */
@Service
public interface LogService {
	/**
	 * 保存
	 * @param log
	 * @return
	 * @throws HbcsoftException
	 */
	int save(Log log) throws HbcsoftException;
	/**
	 * 查询行
	 * @param type
	 * @param loginName
	 * @return
	 * @throws HbcsoftException
	 */
	List<Log> queryAllCount(String type,String loginName,String companyId) throws HbcsoftException;//查询所有行数
	/**
	 * 分页查询
	 * @param type
	 * @param loginName
	 * @return
	 * @throws HbcsoftException
	 */
	List<Log> queryAllLog(String type,String  loginName,String companyId,int startRow,int  pageSize) throws HbcsoftException;//分页查询条数
}
