package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.Resource;
/**
 * 资源管理-数据访问层
 * */
@Repository
public interface ResourceDao {
	/**
	 * 查询
	 * */
	List<Resource> queryAllResource(String sql, JdbcTemplate jt, String... param);
	/**
	 * 查询单条
	 * */
	List<Resource> queryResourceByPid(String sql, JdbcTemplate jt, String... param);
	
	
}
