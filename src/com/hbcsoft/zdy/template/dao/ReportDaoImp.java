package com.hbcsoft.zdy.template.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.DBUtil;
/**
 * 
 */
@Repository
public class ReportDaoImp extends LogBaseClass<ReportDaoImp>  implements ReportDao {

	/**
	 * 第三方数据库查询获取数据
	 * @author songliang
	 * @since 2016-12-26
	 */
	@Override
	public List<List<Object>> getDataList(final OuterDBLinkPara odbl,final String sql) {
		final List<List<Object>> list = new ArrayList<List<Object>>();
		
		final String dbName = odbl.getDbsId();
		final int dbType = odbl.getDbType();
		final String dbip = odbl.getDbIp();
		final String username = odbl.getDbUser();
		final String password = odbl.getDbPass();
		final String port = odbl.getDbport();
		final String driver = odbl.getDbDriver();
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;
		
		try {
			conn =  (Connection) DBUtil.getConnection(dbName, dbType, dbip, username, password, port, driver);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			final ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
			final int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数 
			Object obj = new Object();
			while(rs.next()){
				List<Object> objList = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; i++) {
					obj = rs.getObject(i);
					objList.add(obj);
				}
				list.add(objList);
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatement(stmt);
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				this.getLogger().info(e);
			}
		}
		return list;
	}

}
