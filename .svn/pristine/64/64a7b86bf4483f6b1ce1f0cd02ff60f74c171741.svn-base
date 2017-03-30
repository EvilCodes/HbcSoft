package com.hbcsoft.table.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;
/**
 * 实体字段表
 * @author liang
 *
 */
@Table(name="T_TABLEENTITY")
public class TableEntity extends BaseEntity<TableEntity> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2045840696148606616L;
	/**
	 * 
	 */
	@IsFiled(except=true)
	private TableNameClass tableName;
	/**
	 * 字段名称
	 */
	@Length(max=100, min=1, message="数据库表字段名称长度应该是1位到100位！")
	@NotNull
	private String name;
	/**
	 * 字段标题
	 */
	@Length(max=100, min=1, message="数据库表字段标题长度应该是1位到100位！")
	@NotNull
	private String title;
	/**
	 * 字段类型
	 */
	@Length(max=100, min=2, message="数据库表字段类型长度应该是2位到100位！")
	@NotNull
	private String type;
	/**
	 * 字段长度
	 */
	private int number;
	/**
	 * 小数位数
	 */
	private int decimalDigits;
	/**
	 * 是否为空 0：空 1：非空
	 */
	private int isNull;
	/**
	 * 默认值
	 */
	@Length(max=500, message="数据库表字段默认值长度应该不超过500位！")
	private String defaultValue;
	/**
	 * 操作标识：1：删除 0：添加或编辑  
	 */
	private int flag;
	/**
	 * 是否显示 0：显示 1：不显示
	 */
	private int isDisplay;
	/**
	 * 是否可以编辑 0：可以编辑 1：不可编辑
	 */
	private int isEdit;

	/**
	 * 主表Id
	 */
	private Long mainId;
	
	/**
	 * 0:新增 1：变更 2：删除 3：不变
	 */
	@IsFiled(except=true)
	private int changeFlag;
	/**
	 * 
	 * @return
	 */
	public TableNameClass getTableName() {
		return tableName;
	}
	/**
	 * 
	 * @param tableName
	 */
	public void setTableName(final TableNameClass tableName) {
		this.tableName = tableName;
	}
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param type
	 */
	public void setType(final String type) {
		this.type = type;
	}
	/**
	 * 
	 * @return
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * 
	 * @param number
	 */
	public void setNumber(final int number) {
		this.number = number;
	}
	/**
	 * 
	 * @return
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}
	/**
	 * 
	 * @param decimalDigits
	 */
	public void setDecimalDigits(final int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	/**
	 * 
	 * @return
	 */
	public int getIsNull() {
		return isNull;
	}
	/**
	 * 
	 * @param isNull
	 */
	public void setIsNull(final int isNull) {
		this.isNull = isNull;
	}
	/**
	 * 
	 * @return
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * 
	 * @param defaultValue
	 */
	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * 
	 * @return
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * 
	 * @param flag
	 */
	public void setFlag(final int flag) {
		this.flag = flag;
	}
	/**
	 * 
	 * @return
	 */
	public int getIsDisplay() {
		return isDisplay;
	}
	/**
	 * 
	 * @param isDisplay
	 */
	public void setIsDisplay(final int isDisplay) {
		this.isDisplay = isDisplay;
	}
	/**
	 * 
	 * @return
	 */
	public int getIsEdit() {
		return isEdit;
	}
	/**
	 * 
	 * @param isEdit
	 */
	public void setIsEdit(final int isEdit) {
		this.isEdit = isEdit;
	}
	/**
	 * 
	 * @return
	 */
	public Long getMainId() {
		return mainId;
	}
	/**
	 * 
	 * @param mainId
	 */
	public void setMainId(final Long mainId) {
		this.mainId = mainId;
	}
	/**
	 * 
	 * @return
	 */
	public int getChangeFlag() {
		return changeFlag;
	}
	/**
	 * 
	 * @param changeFlag
	 */
	public void setChangeFlag(final int changeFlag) {
		this.changeFlag = changeFlag;
	}
	/**
	 * 
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO 
//		this.setId(rs.getLong("id"));
//		this.setCreateID(rs.getString("createID"));
//		this.setCreateTime(rs.getDate("createTime"));
		super.processRow(rs);
		this.setDefaultValue(rs.getString("defaultValue"));
		this.setFlag(rs.getInt("flag"));
		this.setIsDisplay(rs.getInt("isDisplay"));
		this.setIsEdit(rs.getInt("isEdit"));
		this.setIsNull(rs.getInt("isNull"));
		this.setName(rs.getString("name"));
		this.setNumber(rs.getInt("number"));
		this.setDecimalDigits(rs.getInt("decimalDigits"));
		this.setTitle(rs.getString("title"));
		this.setType(rs.getString("type"));
		this.setMainId(rs.getLong("mainId"));
	}
	/**
	 * 
	 */
	@Override
	public TableEntity mapRow(final ResultSet rs,final int arg1) throws SQLException {
		// TODO 
		final TableEntity te = new TableEntity();
//		te.setId(rs.getLong("id"));
//		te.setCreateID(rs.getString("createID"));
//		te.setCreateTime(rs.getDate("createTime"));
		super.mapRow(te, rs, arg1);
		te.setDefaultValue(rs.getString("defaultValue"));
		te.setFlag(rs.getInt("flag"));
		te.setIsDisplay(rs.getInt("isDisplay"));
		te.setIsEdit(rs.getInt("isEdit"));
		te.setIsNull(rs.getInt("isNull"));
		te.setName(rs.getString("name"));
		te.setNumber(rs.getInt("number"));
		te.setDecimalDigits(rs.getInt("decimalDigits"));
		te.setTitle(rs.getString("title"));
		te.setType(rs.getString("type"));
		te.setMainId(rs.getLong("mainId"));
		return te;
	}
	
}
