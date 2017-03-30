package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.ClickManage;
import com.hbcsoft.zdy.common.HbcsoftJT;
/**
 * 点选页面管理的Dao接口
 * @author churuifeng
 *
 */
@Repository
public interface ClickManageDao {
	/*
	 * 查询所有
	 */
	List<ClickManage> queryAllClick(String sql, HbcsoftJT jt, String... param);
	/**
	 * 修改
	 * @param sql
	 * @param jt
	 * @param param
	 * @return
	 */
	int updateAll(String sql, HbcsoftJT jt, String... param);
}
