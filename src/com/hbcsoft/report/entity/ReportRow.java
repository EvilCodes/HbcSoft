package com.hbcsoft.report.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;

/**
 * 表体配置
 * 
 * @author zhangdengyu
 *
 */
@Table(name = "Z_REPORT_ROW")
public class ReportRow extends BaseEntity<ReportRow> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7798173772886919250L;
	/**
	 * 报表id
	 */
	@NotNull
	@JsonSerialize(using=ToStringSerializer.class)
	private Long reportId;
	
	/**
	 * 表体名称
	 */
	@NotNull
	private String hname;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 删除标识  1 删除;0 有效
	 */
	private Integer delflag;

	/**
	 * 父节点id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	private Long parentId;
	/**
	 * 级次
	 */
	@NotNull
	private int levelcount;
	/**
	 * 字段
	 */
	private String filed;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 父表体
	 */
	@IsFiled(except = true)
	private ReportRow parentRepRow;
	/**
	 * 根节点（报表配置）
	 */
	@IsFiled(except = true)
	private ReportConfig config;
	/**
	 * 子表体
	 */
	@IsFiled(except = true)
	private List<ReportRow> childrenRepRow = new ArrayList<ReportRow>();
	/**
	 * 是否末级节点： 1 是 0 不是
	 */
	private int isEnd;
	/**
	 * 标识
	 */
	private String status;
	/**
	 * 是否末级节点get
	 */
	public int getIsEnd() {
		return isEnd;
	}
	/**
	 * 是否末级节点set
	 */
	public void setIsEnd(final int isEnd) {
		this.isEnd = isEnd;
	}
	/**
	 * 标识get
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 标识set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	/**
	 * 父表体get
	 */
	public ReportRow getParentRepRow() {
		return parentRepRow;
	}
	/**
	 * 父表体set
	 */
	public void setParentRepRow(final ReportRow parentRepRow) {
		this.parentRepRow = parentRepRow;
	}
	/**
	 * 子表体get
	 */
	public List<ReportRow> getChildrenRepRow() {
		return childrenRepRow;
	}
	/**
	 * 子表体set
	 */
	public void setChildrenRepRow(final List<ReportRow> childrenRepRow) {
		this.childrenRepRow = childrenRepRow;
	}
	/**
	 * 行映射get
	 */
	public String getFiled() {
		return filed;
	}
	/**
	 * 行映射set
	 */
	public void setFiled(final String filed) {
		this.filed = filed;
	}
	/**
	 * 值get
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 值set
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	/**
	 * 级次get
	 */
	public int getLevelcount() {
		return levelcount;
	}
	/**
	 * 级次set
	 */
	public void setLevelcount(final int levelcount) {
		this.levelcount = levelcount;
	}
	/**
	 * 头名称-get
	 */
	public String getHname() {
		return hname;
	}
	/**
	 * 头名称-set
	 */
	public void setHname(final String hname) {
		this.hname = hname;
	}
	/**
	 * 排序-get
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 排序-set
	 */
	public void setSort(final Integer sort) {
		this.sort = sort;
	}
	/**
	 * 删除标识-get
	 */
	public Integer getDelflag() {
		return delflag;
	}
	/**
	 * 删除标识-set
	 */
	public void setDelflag(final Integer delflag) {
		this.delflag = delflag;
	}
	/**
	 * 父id-get
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 父id-set
	 */
	public void setParentId(final Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 报表id-get
	 */
	public Long getReportId() {
		return reportId;
	}
	/**
	 * 报表id-set
	 */
	public void setReportId(final Long reportId) {
		this.reportId = reportId;
	}
	/**
	 * 报表配置get
	 */
	public ReportConfig getConfig() {
		return config;
	}
	/**
	 * 报表配置set
	 */
	public void setConfig(final ReportConfig config) {
		this.config = config;
	}
	/**
	 * processRow
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setId(rs.getLong("id"));
		this.setHname(rs.getString("hName"));
		this.setSort(rs.getInt("sort"));
		this.setDelflag(rs.getInt("delflag"));
		this.setLevelcount(rs.getInt("levelcount"));
		this.setParentId(rs.getLong("parentId"));
		this.setReportId(rs.getLong("reportId"));
		this.setFiled(rs.getString("filed"));
		this.setValue(rs.getString("value"));
		this.setStatus(rs.getString("status"));
		this.setIsEnd(rs.getInt("isEnd"));
	}

	/**
	 * mapRow
	 */
	@Override
	public ReportRow mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final ReportRow rrow = new ReportRow();
		super.mapRow(rrow, rs, arg1);
		rrow.setId(rs.getLong("id"));
		rrow.setHname(rs.getString("hName"));
		rrow.setSort(rs.getInt("sort"));
		rrow.setParentId(rs.getLong("parentId"));
		rrow.setReportId(rs.getLong("reportId"));
		rrow.setDelflag(rs.getInt("delflag"));
		rrow.setLevelcount(rs.getInt("levelcount"));
		rrow.setFiled(rs.getString("filed"));
		rrow.setValue(rs.getString("value"));
		rrow.setStatus(rs.getString("status"));
		rrow.setIsEnd(rs.getInt("isEnd"));
		return rrow;
	}
	/**
	 * 复制
	 */
	public ReportRow copy() throws SQLException {
		final ReportRow rrow = new ReportRow();
		
		rrow.setId(this.getId());
		rrow.setZid(this.getZid());
		rrow.setCompanyId(this.getCompanyId());
		rrow.setCreateID(this.getCreateID());
		rrow.setCreateTime(this.getCreateTime());
		rrow.setUpdateID(this.getUpdateID());
		rrow.setUpdateTime(this.getUpdateTime());
		rrow.setHname(this.getHname());
		rrow.setSort(this.getSort());
		rrow.setDelflag(this.getDelflag());
		rrow.setLevelcount(this.getLevelcount());
		rrow.setParentId(this.getParentId());
		rrow.setReportId(this.getReportId());
		rrow.setFiled(this.getFiled());
		rrow.setValue(this.getValue());
		rrow.setStatus(this.getStatus());
		rrow.setIsEnd(this.getIsEnd());
		return rrow;
	}
}