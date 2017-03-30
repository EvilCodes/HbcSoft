package com.hbcsoft.InterManage.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;

@Table(name="T_SUBLIST")
public class Sublist  extends BaseEntity<Sublist>{

	/**
	 * 属性名
	 */
	private String fieldName;
	
	/**
	 * 属性类型
	 */
	private String fieldType;
	
	/**
	 * 属性值
	 */
	private String fieldValue;
	
	/**
	 * 主表Id
	 */
	private Long mainId;

	/**
	 * 操作标识：1：删除 0：添加或编辑
	 */
	private int flag;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}



	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
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
		this.setFieldName(rs.getString("fieldName"));
		this.setFieldType(rs.getString("fieldType"));
		this.setFieldValue(rs.getString("fieldValue"));
		this.setFlag(rs.getInt("flag"));
	}

	@Override
	public Sublist mapRow(ResultSet rs, int arg1) throws SQLException {
		final Sublist sublist = new Sublist();
		super.mapRow(sublist, rs, arg1);
		sublist.setFieldName(rs.getString("fieldName"));  
		sublist.setFieldType(rs.getString("fieldType"));  
		sublist.setFieldValue(rs.getString("fieldValue"));
		sublist.setFlag(rs.getInt("flag"));
		return sublist;
	}
	
	
	
}
