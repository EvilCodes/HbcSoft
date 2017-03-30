package com.hbcsoft.sys.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hbcsoft.sys.entity.Org;
/**
* 机构管理
* @author Administrator
*
*/
@Repository
public interface OrgDao {
		/**
		*查询所有
		*@param sql
		*@param jt
		*@param param
		*@return
		*/
		List<Org> queryAllOrg(String sql, JdbcTemplate jt, String... param);
		/**
		*根据父id查询机构
		*@param sql
		*@param jt
		*@param param
		*@return
		*/
		List<Org> queryOrgByPid(String sql, JdbcTemplate jt, String... param);
		/**
		*根据ids查询
		*@param sql
		*@param jt
		*@param param
		*@return
		*/
		Org queryDataByIds(String sql, JdbcTemplate jt, String... param) ;
		/**
		*根据id查询机构
		*@param sql
		*@param jt
		*@param param
		*@return
		*/
		 Org queryOrgById(String sql, JdbcTemplate jt, String... param);
}
