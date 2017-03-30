package com.hbcsoft.common;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;

public class KeyValueEntity  extends BaseEntity<KeyValueEntity> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private Object value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setKey(rs.getString(1));
		this.setValue(rs.getString(2));
	}
	
	@Override
	public KeyValueEntity mapRow(ResultSet rs, int arg1) throws SQLException {
		KeyValueEntity en = new KeyValueEntity();
		en.setKey(rs.getString(1));
		en.setValue(rs.getString(2));
		return en;
	}
}
