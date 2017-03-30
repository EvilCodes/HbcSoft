package com.hbcsoft.table.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;
/**
 * 表名称
 * @author liang
 *
 */
@Table(name="T_TABLENAME")
public class TableNameClass  extends BaseEntity<TableNameClass> implements Serializable  {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3199392516790172815L;
	
	/**
	 * 数据库表名
	 */
	@Length(max=100, min=2, message="数据库表名长度应该是2位到32位！")
	private String tableName;
	/**
	 * 选择主表、子表
	 */
	@NotNull
	private String isMainTable;
	/**
	 * 若为子表，则点选带出选择的主表的id
	 */
	private Long mainId;
	/**
	 * 若为子表，则点选带出选择的主表的名称
	 */
	private String mainName;
	/**
	 * 备注
	 */
	@Length(max=1000, message="备注信息长度不能超过1000位！")
	private String memo;
	/**
	 * 操作标识：1：删除 0：添加或编辑
	 */
	private int flag;
	/**
	 * 
	 */
	@IsFiled(except=true)
	private List<TableEntity> tableEntityList = new ArrayList<TableEntity>();
	/**
	 * 
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 
	 * @param tableName
	 */
	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 
	 * @return
	 */
	public String getIsMainTable() {
		return isMainTable;
	}
	/**
	 * 
	 * @param isMainTable
	 */
	public void setIsMainTable(final String isMainTable) {
		this.isMainTable = isMainTable;
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
	public String getMemo() {
		return memo;
	}
	/**
	 * 
	 * @param memo
	 */
	public void setMemo(final String memo) {
		this.memo = memo;
	}
	/**
	 * 
	 * @return
	 */
	public String getMainName() {
		return mainName;
	}
	/**
	 * 
	 * @param mainName
	 */
	public void setMainName(final String mainName) {
		this.mainName = mainName;
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
	public List<TableEntity> getTableEntityList() {
		return tableEntityList;
	}
	/**
	 * 
	 * @param tableEntityList
	 */
	public void setTableEntityList(final List<TableEntity> tableEntityList) {
		this.tableEntityList = tableEntityList;
	}
	/**
	 * 
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
//		this.setId(rs.getLong("id"));
//		this.setCreateID(rs.getString("createID"));
//		this.setCreateTime(rs.getDate("createTime"));
		super.processRow(rs);
		this.setTableName(rs.getString("tableName"));
		this.setIsMainTable(rs.getString("isMainTable"));
		this.setFlag(rs.getInt("flag"));
		this.setMainId(rs.getLong("mainId"));
		this.setMainName(rs.getString("mainName"));
		this.setMemo(rs.getString("memo"));
		
	}
	/**
	 * 
	 */
	@Override
	public TableNameClass mapRow(final ResultSet rs,final int arg1) throws SQLException {
		final TableNameClass tn = new TableNameClass();
//		tn.setId(rs.getLong("id"));
		super.mapRow(tn, rs, arg1);
		tn.setTableName(rs.getString("tableName"));
		tn.setIsMainTable(rs.getString("isMainTable"));
		tn.setMainId(rs.getLong("mainId"));
		tn.setMainName(rs.getString("mainName"));
		tn.setMemo(rs.getString("memo"));
		tn.setFlag(rs.getInt("flag"));
		return tn;
	}
	/**
	 * 
	 * @return
	 */
	public TableNameClass copy(){
		final TableNameClass tn = new TableNameClass();
		final long id = this.getId();
		final long companyId = this.getCompanyId();
		final long zid = this.getZid();
		final Date createTime = this.getCreateTime();
		final long createId = this.getCreateID();
		final long updateId = this.getUpdateID();
		final Date updateTime = this.getUpdateTime();
		final String tName = this.getTableName();
		final String isMainTable = this.getIsMainTable();
		final long mainId = this.getMainId();
		final String mainName = this.getMainName();
		final String memo = this.getMemo();
		final int flag = this.getFlag();
		tn.setId(id);
		tn.setCompanyId(companyId);
		tn.setZid(zid);
		tn.setCreateTime(createTime);
		tn.setCreateID(createId);
		tn.setUpdateID(updateId);
		tn.setUpdateTime(updateTime);
		tn.setTableName(tName);
		tn.setIsMainTable(isMainTable);
		tn.setMainId(mainId);
		tn.setMainName(mainName);
		tn.setMemo(memo);
		tn.setFlag(flag);
		return tn;
	}
}
