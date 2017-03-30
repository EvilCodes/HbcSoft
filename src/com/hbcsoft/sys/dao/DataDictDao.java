package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.DataDict;
/**
 * 数据字典 DataDictDao  interface
 * @author Administrator
 *
 */
@Repository
public interface DataDictDao {
	/**
	 * 查询所有数据字典
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<DataDict> queryAllDataDict(String sql, JdbcTemplate jt, String... param);
}
