package com.hbcsoft.form.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.Table;

/**
 * 表单与表的关联表
 * @author zhanghaijiao
 *
 */
@Table(name="F_FORMTABLE")
public class FormTable extends BaseEntity<FormTable> implements Serializable  {

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2045840696148606616L;
	
	/**
	 * 表单ID
	 */
	@NotNull
	private Long formId;
	
	/**
	 * 表Id
	 */
	private Long tableId;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 是否为主表  0：主表 1：子表
	 */
	private int isMainTable;
	
	/**
	 * 如果为子表时，必须保存主表Id
	 */
	private Long tableMainId;
	
	/**
	 * 如果为子表时，主表的表名
	 */
	private String tableMainName;
	
	/**
	 * 表备注
	 */
	private String tableMemo;
	
	/**
	 * 操作标识：1：删除 0：添加或编辑
	 */
	private int tableFlag;
	
	/**
	 * 获取表单ID
	 * @return
	 */
	public Long getFormId() {
		return formId;
	}
	
	/**
	 * 设置表单ID
	 * @param formId
	 */
	public void setFormId(final Long formId) {
		this.formId = formId;
	}
	
	/**
	 * 获取表ID
	 * @return
	 */
	public Long getTableId() {
		return tableId;
	}
	
	/**
	 * 设置表ID
	 * @param tableId
	 */
	public void setTableId(final Long tableId) {
		this.tableId = tableId;
	}
	
	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * 设置表名
	 * @param tableName
	 */
	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 获取是否为子表状态
	 * @return
	 */
	public int getIsMainTable() {
		return isMainTable;
	}
	
	/**
	 * 设置是否为子表状态
	 * @param isMainTable
	 */
	public void setIsMainTable(final int isMainTable) {
		this.isMainTable = isMainTable;
	}
	
	/**
	 * 获取为子表是主表的ID
	 * @return
	 */
	public Long getTableMainId() {
		return tableMainId;
	}
	
	/**
	 * 设置为子表是主表的ID
	 * @param tableMainId
	 */
	public void setTableMainId(final Long tableMainId) {
		this.tableMainId = tableMainId;
	}
	
	/**
	 * 获取为子表是主表的名称
	 * @return
	 */
	public String getTableMainName() {
		return tableMainName;
	}
	
	/**
	 * 设置为子表是主表的名称
	 * @param tableMainName
	 */
	public void setTableMainName(final String tableMainName) {
		this.tableMainName = tableMainName;
	}
	
	/**
	 * 获取表备注
	 * @return
	 */
	public String getTableMemo() {
		return tableMemo;
	}
	
	/**
	 * 设置表备注
	 * @param tableMemo
	 */
	public void setTableMemo(final String tableMemo) {
		this.tableMemo = tableMemo;
	}
	
	/**
	 * 获取表操作标识
	 * @return
	 */
	public int getTableFlag() {
		return tableFlag;
	}

	/**
	 * 设置表操作标识
	 * @param tableFlag
	 */
	public void setTableFlag(final int tableFlag) {
		this.tableFlag = tableFlag;
	}

	/**
	 * 重写父类ProcessRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setId(rs.getLong("id"));
		this.setFormId(rs.getLong("formId"));
		this.setTableId(rs.getLong("tableId"));
		this.setTableName(rs.getString("tableName"));
		this.setIsMainTable(rs.getInt("isMainTable"));
		this.setTableMainId(rs.getLong("tableMainId"));
		this.setTableMainName(rs.getString("tableMainName"));
		this.setTableMemo(rs.getString("tableMemo"));
		this.setTableFlag(rs.getInt("tableFlag"));
		
	}
	
	/**
	 * 重写父类mapRow方法
	 */
	@Override
	public FormTable mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final FormTable ftm = new FormTable();
		super.mapRow(ftm, rs, arg1);
		ftm.setId(rs.getLong("id"));
		ftm.setFormId(rs.getLong("formId"));
		ftm.setTableId(rs.getLong("tableId"));
		ftm.setTableName(rs.getString("tableName"));
		ftm.setIsMainTable(rs.getInt("isMainTable"));
		ftm.setTableMainId(rs.getLong("tableMainId"));
		ftm.setTableMainName(rs.getString("tableMainName"));
		ftm.setTableMemo(rs.getString("tableMemo"));
		ftm.setTableFlag(rs.getInt("tableFlag"));
		return ftm;
	}
}
