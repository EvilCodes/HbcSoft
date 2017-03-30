package com.hbcsoft.zdy.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yaja.common.entity.DBConnectionEntity;
import com.yaja.common.util.YAJADBUtil;

public class DBUtil {
	public static void commit(final Connection conn) throws SQLException {
		YAJADBUtil.commit(conn);
	}

	public static void rollback(final Connection conn) throws SQLException {
		YAJADBUtil.rollback(conn);
	}

	public static Connection getConnection()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity();
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype, final String host)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype, host);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype, final String host, final String username)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype, host, username);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype, final String host, final String username, final String password)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype, host, username, password);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype, final String host, final String username, final String password,
			final String port) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype, host, username, password, port);
		return YAJADBUtil.getConnection(en);
	}

	public static Connection getConnection(final String dbName, final int dbtype, final String host, final String username, final String password,
			final String port, final String driver)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		final DBConnectionEntity en = new DBConnectionEntity(dbName, dbtype, host, username, password, port, driver);
		return YAJADBUtil.getConnection(en);
	}

	public static void closeConnection(final Connection conn) throws SQLException {
		YAJADBUtil.closeConnection(conn);
	}

	public static void closeStatement(final Statement stmt) throws SQLException {
		YAJADBUtil.closeStatement(stmt);
	}

	public static void closeResultSet(final ResultSet rs) throws SQLException {
		YAJADBUtil.closeResultSet(rs);
	}
}
