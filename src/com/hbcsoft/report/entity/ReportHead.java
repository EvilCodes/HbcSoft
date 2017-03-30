package com.hbcsoft.report.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;

/**
 * 表头配置
 * 
 * @author zhangdengyu
 *
 */
@Table(name = "Z_REPORT_HEAD")
public class ReportHead extends BaseEntity<ReportHead> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7798173772886919250L;
	
	/**
	 * 报表头名称
	 */
	private String hname;
	/**
	 * 字段（末级节点  属性）
	 */
	private String field;
	/**
	 * 值（末级节点  属性）
	 */
	private String value;
	/**
	 * 排序（只针对同一级子节点排序）
	 */
	private Integer sort;
	/**
	 * 级次（只针对根节点排序）
	 */
	private Integer levelcount;
	/**
	 * 报表id（下拉框显示）
	 */
	@NotNull
	@JsonSerialize(using=ToStringSerializer.class)

	private Long reportId;
	/**
	 * 父节点id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	private Long parentId;
	/**
	 * 父报表头
	 */
	@IsFiled(except = true)
	private ReportHead parentHeader;
	/**
	 * 根节点（报表配置）
	 */
	@IsFiled(except = true)
	private ReportConfig rootConfig;
	/**
	 * 0-有效状态 
	 * 1-无效或删除
	 */
	private int delflag;
	/**
	 * 报表头名称的getter方法
	 * @return
	 */
	public String getHname() {
		return hname;
	}

	/**
	 * 报表头名称的setter方法
	 * @param headName
	 */
	public void setHname(final String hname) {
		this.hname = hname;
	}
	/**
	 * 字段的getter方法
	 * @return
	 */
	public String getField() {
		return field;
	}
	/**
	 * 字段的setter方法
	 * @param fieldValue
	 */
	public void setField(final String field) {
		this.field = field;
	}
	/**
	 * 值的getter方法
	 * @return
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 值的getter方法
	 * @return
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	/**
	 * 排序的getter方法
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 排序的setter方法
	 * @param sort
	 */
	public void setSort(final Integer sort) {
		this.sort = sort;
	}
	/**
	 * 级次getter方法
	 * @return
	 */
	public Integer getLevelcount() {
		return levelcount;
	}
	/**
	 * 级次setter方法
	 * @param level
	 */
	public void setLevelcount(final Integer levelcount) {
		this.levelcount = levelcount;
	}
	/**
	 * 父节点的getter方法
	 * @return
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 父节点的setter方法
	 * @param parentId
	 */
	public void setParentId(final Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 父表头的getter方法
	 * @return
	 */
	public ReportHead getParentHeader() {
		return parentHeader;
	}
	/**
	 * 父表头的setter方法
	 * @param parentHeader
	 */
	public void setParentHeader(final ReportHead parentHeader) {
		this.parentHeader = parentHeader;
	}
	/**
	 * 根报表配置 getter方法
	 * @return
	 */
	public ReportConfig getRootConfig() {
		return rootConfig;
	}
	/**
	 * 根报表配置 setter方法
	 * @param rootConfig
	 */
	public void setRootConfig(final ReportConfig rootConfig) {
		this.rootConfig = rootConfig;
	}
	/**
	 * 报表id getter 方法
	 * @return
	 */
	public Long getReportId() {
		return reportId;
	}
	/**
	 * 报表id setter 方法
	 * @param reportId
	 */
	public void setReportId(final Long reportId) {
		this.reportId = reportId;
	}
	
	/**
	 * 删除状态getter方法
	 * @return
	 */
	public int getDelflag() {
		return delflag;
	}
	/**
	 * 删除状态setter方法
	 * @param delflag
	 */
	public void setDelflag(final int delflag) {
		this.delflag = delflag;
	}
	
	/**
	 * 重写BaseEntity方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setHname(rs.getString("hName"));
		this.setField(rs.getString("field"));
		this.setValue(rs.getString("value"));
		this.setSort(rs.getInt("sort"));
		this.setLevelcount(rs.getInt("levelcount"));
		this.setParentId(rs.getLong("parentId"));
		this.setReportId(rs.getLong("reportId"));
	}
	/**
	 * 重写BaseEntity方法
	 */
	public ReportHead mapRow( final ResultSet rs, final int arg1) throws SQLException {
		final ReportHead head = new ReportHead();
		head.setId(rs.getLong("id"));
		head.setHname(rs.getString("hName"));
		head.setField(rs.getString("field"));
		head.setValue(rs.getString("value"));
		head.setSort(rs.getInt("sort"));
		head.setLevelcount(rs.getInt("levelcount"));
		head.setParentId(rs.getLong("parentId"));
		head.setReportId(rs.getLong("reportId"));
		return head;
		
	}

}