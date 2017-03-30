package com.hbcsoft.report.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;
@Table(name="Z_REPORT_INTER")
public class ReportConfigInterface extends BaseEntity<ReportConfigInterface> implements Serializable{

	
	private static final long serialVersionUID = 3085094676111797168L;

	/**
	 * 配置主表id
	 */
	private Long reportConfigId;
	/**
	 * 接口类id
	 */
	private Long primaryListId;
	/**
	 * 操作标识：1：删除 0：添加或编辑
	 */
	private int flag;
	
	public Long getReportConfigId() {
		return reportConfigId;
	}

	public void setReportConfigId(Long reportConfigId) {
		this.reportConfigId = reportConfigId;
	}

	public Long getPrimaryListId() {
		return primaryListId;
	}

	public void setPrimaryListId(Long primaryListId) {
		this.primaryListId = primaryListId;
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
		this.setReportConfigId(Long.valueOf(rs.getString("reportConfigId")));
		this.setPrimaryListId(Long.valueOf(rs.getString("primaryListId")));
		this.setFlag(rs.getInt("flag"));
	}

	@Override
	public ReportConfigInterface mapRow(ResultSet rs, int arg1) throws SQLException {
		final ReportConfigInterface reportConfigInterface = new ReportConfigInterface();
		super.mapRow(reportConfigInterface, rs, arg1);
		reportConfigInterface.setReportConfigId(Long.valueOf(rs.getString("reportConfigId")));
		reportConfigInterface.setPrimaryListId(Long.valueOf(rs.getString("primaryListId")));
		reportConfigInterface.setFlag(rs.getInt("flag"));
		return reportConfigInterface;
	}
	
}
