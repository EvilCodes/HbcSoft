package com.hbcsoft.InterManage.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;

@Table(name="T_PRIMARYLIST")
public class PrimaryList extends BaseEntity<PrimaryList> {

	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 方法名
	 */
	private String methodName;
	
	/**
	 * 操作标识：1：删除 0：添加或编辑
	 */
	private int flag;
	/**
	 * 备注
	 */
	private String memo;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setClassName(rs.getString("className"));
		this.setMethodName(rs.getString("methodName"));
		this.setMemo(rs.getString("memo"));
		this.setFlag(rs.getInt("flag"));
	}

	@Override
	public PrimaryList mapRow(ResultSet rs, int arg1) throws SQLException {
		final PrimaryList primaryList = new PrimaryList();
		super.mapRow(primaryList, rs, arg1);
		primaryList.setClassName(rs.getString("className"));
		primaryList.setMethodName(rs.getString("methodName"));
		primaryList.setMemo(rs.getString("memo"));
		primaryList.setFlag(rs.getInt("flag"));
		return primaryList;
	}
	
	
}
