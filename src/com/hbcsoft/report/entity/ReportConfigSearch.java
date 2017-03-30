package com.hbcsoft.report.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;

/**
 * 报表条件查询字段信息
 */
@Table(name = "Z_REPORT_CONFIGSEARCH")
public class ReportConfigSearch extends BaseEntity<ReportConfigSearch> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2045840696148606616L;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 报表字段名称
	 */
	@Length(max=100, min=1, message="数据库表字段名称长度应该是2位到100位！")
	@NotNull
	private String reportSearchName;
	
	/**
	 * 报表ID
	 */
	@NotNull
	private Long reportId;
	
	/**
	 * 标题
	 */
	@Length(max=100, min=1, message="数据库表标题名称长度应该是2位到100位！")
	private String title;
	
	/**
	 * 字段序号
	 */
	@NotNull
	private int sort;
	
	/**
	 * 类型：0：输入框 1：下拉框 2：按钮 3：多选框 4：点选 5:日期 6：文本域 7：金额
	 */
	public int inputType;
	
	/**
	 * 下拉框状态 0：固定表中的值 1：sql
	 */
	private int sourceMode;
	
	/**
	 * 下拉框内容
	 */
	@Length(max=500, message="数据库表字段数据源内容长度应该不超过500位！")
	private String sourceContent;
	
	/**
	 * 点选信息
	 */
	private String clickInfo;
	
	/**
	 * 操作标识：1：删除 0：添加或编辑  
	 */
	private int fieldFlag;
	/**
	 * 是否显示  
	 */
	private int inputIsDisplay;
	/**
	 * 默认值
	 */
	private String inputDefaultValue;
	
	/***********扩展字段 start*************/
	/**
	 * 开始
	 */
	@IsFiled(except=true)
	private String startValue;
	/**
	 * 结束
	 */
	@IsFiled(except=true)
	private String endValue;
	/**
	 * 赋值
	 */
	@IsFiled(except=true)
	private String inputValue;
	@IsFiled(except=true)
	private String clickValue;
	/***********扩展字段 end*************/	
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
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获取报表字段名称
	 * @return
	 */
	public String getReportSearchName() {
		return reportSearchName;
	}

	/**
	 * 设置报表字段名称
	 * @param reportSearchName
	 */
	public void setReportSearchName(String reportSearchName) {
		this.reportSearchName = reportSearchName;
	}

	/**
	 * 获取报表ID
	 * @return
	 */
	public Long getReportId() {
		return reportId;
	}

	/**
	 * 设置报表ID
	 * @param reportId
	 */
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	/**
	 * 获取标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取序号
	 * @return
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * 设置序号
	 * @param sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * 获取类型
	 * @return
	 */
	public int getInputType() {
		return inputType;
	}

	/**
	 * 设置类型
	 * @param inputType
	 */
	public void setInputType(int inputType) {
		this.inputType = inputType;
	}

	/**
	 * 获取下拉框状态
	 * @return
	 */
	public int getSourceMode() {
		return sourceMode;
	}

	/**
	 * 设置下拉框状态
	 * @param sourceMode
	 */
	public void setSourceMode(int sourceMode) {
		this.sourceMode = sourceMode;
	}

	/**
	 * 获取下拉框内容
	 * @return
	 */
	public String getSourceContent() {
		return sourceContent;
	}

	/**
	 * 设置下拉框内容
	 * @param sourceContent
	 */
	public void setSourceContent(String sourceContent) {
		this.sourceContent = sourceContent;
	}

	/**
	 * 获取点选信息
	 * @return
	 */
	public String getClickInfo() {
		return clickInfo;
	}

	/**
	 * 设置点选信息
	 * @param clickInfo
	 */
	public void setClickInfo(String clickInfo) {
		this.clickInfo = clickInfo;
	}

	/**
	 * 获取操作类型
	 * @return
	 */
	public int getFieldFlag() {
		return fieldFlag;
	}

	/**
	 * 设置操作类型
	 * @param fieldFlag
	 */
	public void setFieldFlag(int fieldFlag) {
		this.fieldFlag = fieldFlag;
	}

	public String getStartValue() {
		return startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	public String getEndValue() {
		return endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public int getInputIsDisplay() {
		return inputIsDisplay;
	}

	public void setInputIsDisplay(int inputIsDisplay) {
		this.inputIsDisplay = inputIsDisplay;
	}

	public String getInputDefaultValue() {
		return inputDefaultValue;
	}

	public void setInputDefaultValue(String inputDefaultValue) {
		this.inputDefaultValue = inputDefaultValue;
	}

	public String getClickValue() {
		return clickValue;
	}

	public void setClickValue(String clickValue) {
		this.clickValue = clickValue;
	}

	/**
	 * 重写父类ProcessRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setId(rs.getLong("id"));
		this.setTableName(rs.getString("tableName"));
		this.setReportSearchName(rs.getString("reportSearchName"));
		this.setReportId(rs.getLong("reportId"));
		this.setTitle(rs.getString("title"));
		this.setSort(rs.getInt("sort"));
		this.setInputType(rs.getInt("inputType"));
		this.setSourceMode(rs.getInt("sourceMode"));
		this.setSourceContent(rs.getString("sourceContent"));
		this.setClickInfo(rs.getString("clickInfo"));
		this.setFieldFlag(rs.getInt("fieldFlag"));
		this.setInputIsDisplay(rs.getInt("inputIsDisplay"));
		this.setInputDefaultValue(rs.getString("inputDefaultValue"));
	}

	/**
	 * 重写父类mapRow方法
	 */
	@Override
	public ReportConfigSearch mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final ReportConfigSearch rcs = new ReportConfigSearch();
		super.mapRow(rcs, rs, arg1);
		rcs.setId(rs.getLong("id"));
		rcs.setTableName(rs.getString("tableName"));
		rcs.setReportSearchName(rs.getString("reportSearchName"));
		rcs.setReportId(rs.getLong("reportId"));
		rcs.setTitle(rs.getString("title"));
		rcs.setSort(rs.getInt("sort"));
		rcs.setInputType(rs.getInt("inputType"));
		rcs.setSourceMode(rs.getInt("sourceMode"));
		rcs.setSourceContent(rs.getString("sourceContent"));
		rcs.setClickInfo(rs.getString("clickInfo"));
		rcs.setFieldFlag(rs.getInt("fieldFlag"));
		rcs.setInputIsDisplay(rs.getInt("inputIsDisplay"));
		rcs.setInputDefaultValue(rs.getString("inputDefaultValue"));
		return rcs;
	}
}
