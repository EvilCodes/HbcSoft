package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;

/**
 * @author yf
 * @version 2016-11-03
 **/
@Table(name="T_SYS_OUTERDBLINKPARA")
public class OuterDBLinkPara extends BaseEntity<OuterDBLinkPara> implements Serializable{	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3828332115295780098L;
	
	/**
	 * 数据库IP
	 */
	@NotNull
	private String dbIp;
	/**
	 * 数据库类型:
	 * 	0--Mysql
	 * 	1--sqlServer 
	 *	2--Oracle
	 */
	@NotNull
	private int dbType;
	/**
	 * 端口号
	 */
	@NotNull
	private String dbport;
	/**
	 * 数据库驱动名称
	 */
	@NotNull
	private String dbDriver;	
	/**
	 * 数据库驱动路径
	 */
	private String dbURL;	
	
	/**
	 * 数据库实例/数据库名称
	 */
	@NotNull
	private String dbsId;
	/**
	 * 数据库登录用户名
	 */
	@NotNull
	private String dbUser;
	/**
	 * 数据库登录密码
	 */
	@NotNull
	private String dbPass;
	/**
	 * 数据库账套号
	 */
	@NotNull
	private String dbAccount;
	/**
	 * 公司名称
	 */
	@Length(max=100, message="公司名称不能超过100位！")
	private String companyName;
	/**
	 * 是否启用  0--否 ；1--是
	 */
	private int isEnabled;
	/**
	 * 备注（第三方数据库描述信息）
	 */
	@Length(max=1000, message="备注信息长度不能超过1000位！")
	private String remark;
	/**
	 * 是否同步用户  0--否 ；1--是
	 */
	private int isCloneUser;
	/**
	 * 是否同步部门  0--否 ；1--是
	 */
	private int isCloneDept;
	
	
	/**
	 * get方法
	 * @return
	 */
	public String getDbIp() {
		return dbIp;
	}
	/**
	 * set方法
	 * @param dbIp
	 */
	public void setDbIp(final String dbIp) {
		this.dbIp = dbIp;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getDbType() {
		return dbType;
	}
	/**
	 * set方法
	 * @param dbType
	 */
	public void setDbType(final int dbType) {
		this.dbType = dbType;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbDriver() {
		return dbDriver;
	}
	/**
	 * set方法
	 * @param dbDriver
	 */
	public void setDbDriver(final String dbDriver) {
		this.dbDriver = dbDriver;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbURL() {
		return dbURL;
	}
	/**
	 * set方法
	 * @param dbURL
	 */
	public void setDbURL(final String dbURL) {
		this.dbURL = dbURL;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbsId() {
		return dbsId;
	}
	/**
	 * set方法
	 * @param dbsId
	 */
	public void setDbsId(final String dbsId) {
		this.dbsId = dbsId;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbUser() {
		return dbUser;
	}
	/**
	 * set方法
	 * @param dbUser
	 */
	public void setDbUser(final String dbUser) {
		this.dbUser = dbUser;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbPass() {
		return dbPass;
	}
	/**
	 * set方法
	 * @param dbPass
	 */
	public void setDbPass(final String dbPass) {
		this.dbPass = dbPass;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * set方法
	 * @param companyName
	 */
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getIsEnabled() {
		return isEnabled;
	}
	/**
	 * set方法
	 * @param isEnabled
	 */
	public void setIsEnabled(final int isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * set方法
	 * @param remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbport() {
		return dbport;
	}
	/**
	 * set方法
	 * @param dbport
	 */
	public void setDbport(final String dbport) {
		this.dbport = dbport;
	}
	/**
	 * get方法
	 * @return
	 */
	public String getDbAccount() {
		return dbAccount;
	}
	/**
	 * set方法
	 * @param dbAccount
	 */
	public void setDbAccount(final String dbAccount) {
		this.dbAccount = dbAccount;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getIsCloneUser() {
		return isCloneUser;
	}
	/**
	 * set方法
	 * @param isCloneUser
	 */
	public void setIsCloneUser(final int isCloneUser) {
		this.isCloneUser = isCloneUser;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getIsCloneDept() {
		return isCloneDept;
	}
	/**
	 * set方法
	 * @param isCloneDept
	 */
	public void setIsCloneDept(final int isCloneDept) {
		this.isCloneDept = isCloneDept;
	}
	/**
	 * 重写BaseEntity的方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		this.setId(rs.getLong("id"));
		this.setCompanyId(rs.getLong("companyId"));
		this.setUpdateID(rs.getLong("updateID"));
		this.setUpdateTime(rs.getTimestamp("UpdateTime"));
		this.setCreateTime(rs.getTimestamp("CreateTime"));
		this.setCreateID(rs.getLong("CreateID"));
		this.setDbIp(rs.getString("dbIp"));
		this.setDbport(rs.getString("dbport"));
		this.setDbType(rs.getInt("dbType"));
		this.setDbDriver(rs.getString("dbDriver"));
		this.setDbURL(rs.getString("dbURL"));
		this.setDbsId(rs.getString("dbsId"));
		this.setDbUser(rs.getString("dbUser"));
		this.setDbPass(rs.getString("dbPass"));
		this.setDbAccount(rs.getString("dbAccount"));
		this.setCompanyName(rs.getString("companyName"));
		this.setIsEnabled(rs.getInt("isEnabled"));
		this.setRemark(rs.getString("remark"));
		this.setIsCloneUser(rs.getInt("isCloneUser"));
		this.setIsCloneDept(rs.getInt("isCloneDept"));
	}
	/**
	 * 重写BaseEntity的方法
	 */
	@Override
	public OuterDBLinkPara mapRow(final ResultSet rs, final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		final OuterDBLinkPara  db = new OuterDBLinkPara();
		db.setId(rs.getLong("id"));
		db.setCompanyId(rs.getLong("companyId"));
		db.setUpdateID(rs.getLong("updateID"));
		db.setUpdateTime(rs.getTimestamp("UpdateTime"));
		db.setCreateTime(rs.getTimestamp("CreateTime"));
		db.setCreateID(rs.getLong("CreateID"));
		db.setDbIp(rs.getString("dbIp"));
		db.setDbport(rs.getString("dbport"));
		db.setDbType(rs.getInt("dbType"));
		db.setDbDriver(rs.getString("dbDriver"));
		db.setDbURL(rs.getString("dbURL"));
		db.setDbsId(rs.getString("dbsId"));
		db.setDbUser(rs.getString("dbUser"));
		db.setDbPass(rs.getString("dbPass"));
		db.setDbAccount(rs.getString("dbAccount"));
		db.setCompanyName(rs.getString("companyName"));
		db.setIsEnabled(rs.getInt("isEnabled"));
		db.setRemark(rs.getString("remark"));
		db.setIsCloneUser(rs.getInt("isCloneUser"));
		db.setIsCloneDept(rs.getInt("isCloneDept"));
		return db;
	}
}
