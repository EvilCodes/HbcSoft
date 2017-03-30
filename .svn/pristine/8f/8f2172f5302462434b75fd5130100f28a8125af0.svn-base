package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.DictType;

/**
 * 字典类型 dao interface
 * @author Administrator
 *
 */
@Repository
public interface DictTypeDao {
	/**
	 * 查询所有
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	   List<DictType> queryAllDictType(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据父id查询字典类型
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	 List<DictType> queryDictTypeByPid(String sql, JdbcTemplate jt, String... param);
	/**
	 * 根据ids查询
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	 DictType queryDataByIds(String sql, JdbcTemplate jt, String... param) ;
	/**
	 * 根据id查询字典类型
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	 DictType queryDictTypeById(String sql, JdbcTemplate jt, String... param);
	
	
}
