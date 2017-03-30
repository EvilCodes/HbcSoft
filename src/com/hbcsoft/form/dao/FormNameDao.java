package com.hbcsoft.form.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hbcsoft.form.entity.FormName;

/**
 * 查询FormName表的Dao接口
 * @author zhanghaijiao
 *
 */
public interface FormNameDao {
	/**
	 * 查询出所有的数据表名称
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	List<FormName> queryAllTable(String sql, JdbcTemplate jt, String... param);
}
