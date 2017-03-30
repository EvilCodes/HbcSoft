package com.hbcsoft.report.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;
/**
 * 报表类型配置
 * @author yangfeng
 * @version 201701
 */
@Table(name="Z_REPORT_SPECIALROW")
public class ReportSpecialRow  extends BaseEntity<ReportSpecialRow> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1895436313536812349L;
	
	/**
	 * 报表配置id
	 */
	private Long configId;
	/**
	 * 特殊行
	 */
	private int specialRow;
	/**
	 * 特殊单元格行
	 */
	private int specialCellRow;	
	/**
	 * 是否继承查询条件
	 */
	private int isExtendQuery;	
	/**
	 * 查询条件
	 */
	private String queryConditions;
	


	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public int getSpecialRow() {
		return specialRow;
	}

	public void setSpecialRow(int specialRow) {
		this.specialRow = specialRow;
	}

	public int getSpecialCellRow() {
		return specialCellRow;
	}

	public void setSpecialCellRow(int specialCellRow) {
		this.specialCellRow = specialCellRow;
	}

	public int getIsExtendQuery() {
		return isExtendQuery;
	}

	public void setIsExtendQuery(int isExtendQuery) {
		this.isExtendQuery = isExtendQuery;
	}

	public String getQueryConditions() {
		return queryConditions;
	}

	public void setQueryConditions(String queryConditions) {
		this.queryConditions = queryConditions;
	}

	/**
	 * 重写BaseEntity方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setZid(rs.getLong("zid"));
		this.setConfigId(rs.getLong("configId"));
		this.setCompanyId(rs.getLong("companyId"));
		this.setUpdateID(rs.getLong("updateID"));
		this.setUpdateTime(rs.getTimestamp("UpdateTime"));
		this.setCreateTime(rs.getTimestamp("CreateTime"));
		this.setCreateID(rs.getLong("CreateID"));
		this.setSpecialRow(rs.getInt("specialRow"));
		this.setSpecialCellRow(rs.getInt("specialCellRow"));
		this.setIsExtendQuery(rs.getInt("isExtendQuery"));
		this.setQueryConditions(rs.getString("queryConditions"));
	}	

	/**
	 * 重写BaseEntity方法
	 */
	@Override
	public ReportSpecialRow mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final ReportSpecialRow  specialRow = new ReportSpecialRow();
		specialRow.setId(rs.getLong("id"));
		specialRow.setZid(rs.getLong("zid"));
		specialRow.setConfigId(rs.getLong("configId"));
		specialRow.setCompanyId(rs.getLong("companyId"));
		specialRow.setUpdateID(rs.getLong("updateID"));
		specialRow.setUpdateTime(rs.getTimestamp("UpdateTime"));
		specialRow.setCreateTime(rs.getTimestamp("CreateTime"));
		specialRow.setCreateID(rs.getLong("CreateID"));
		specialRow.setSpecialRow(rs.getInt("specialRow"));
		specialRow.setSpecialCellRow(rs.getInt("specialCellRow"));
		specialRow.setIsExtendQuery(rs.getInt("isExtendQuery"));
		specialRow.setQueryConditions(rs.getString("queryConditions"));
		return specialRow;
	}	
	
}
