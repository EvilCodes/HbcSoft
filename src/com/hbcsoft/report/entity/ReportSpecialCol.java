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
@Table(name="Z_REPORT_SPECIALCOL")
public class ReportSpecialCol  extends BaseEntity<ReportSpecialCol> implements Serializable{

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
	private int specialColumn;
	/**
	 * 特殊单元格行
	 */
	private int specialCellCol;	
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

	public int getSpecialColumn() {
		return specialColumn;
	}

	public void setSpecialColumn(int specialColumn) {
		this.specialColumn = specialColumn;
	}

	public int getSpecialCellCol() {
		return specialCellCol;
	}

	public void setSpecialCellCol(int specialCellCol) {
		this.specialCellCol = specialCellCol;
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
		this.setSpecialColumn(rs.getInt("specialColumn"));
		this.setSpecialCellCol(rs.getInt("specialCellCol"));
		this.setIsExtendQuery(rs.getInt("isExtendQuery"));
		this.setQueryConditions(rs.getString("queryConditions"));
	}	

	/**
	 * 重写BaseEntity方法
	 */
	@Override
	public ReportSpecialCol mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final ReportSpecialCol  specialCol = new ReportSpecialCol();
		specialCol.setId(rs.getLong("id"));
		specialCol.setZid(rs.getLong("zid"));
		specialCol.setConfigId(rs.getLong("configId"));
		specialCol.setCompanyId(rs.getLong("companyId"));
		specialCol.setUpdateID(rs.getLong("updateID"));
		specialCol.setUpdateTime(rs.getTimestamp("UpdateTime"));
		specialCol.setCreateTime(rs.getTimestamp("CreateTime"));
		specialCol.setCreateID(rs.getLong("CreateID"));
		specialCol.setSpecialColumn(rs.getInt("specialColumn"));
		specialCol.setSpecialCellCol(rs.getInt("specialCellCol"));
		specialCol.setIsExtendQuery(rs.getInt("isExtendQuery"));
		specialCol.setQueryConditions(rs.getString("queryConditions"));
		return specialCol;
	}	
	
}
