package com.hbcsoft.workflow.entity;

public class WorkflowModelQuery {
	//备注
	private String remark;
	//流程名称
	private String wfName;
	//流程id
	private long wfId = 0;
	//节点id
	private long nId = 0;
	//规则id
	private long ruleId = 0;
	
	//页码
	private int page = 1;
	//每页信息数
	private int rows = 10;
	//排序方式
	private String order = "order by createTime DESC";
	//表名称
	private String tableId;
	//列名称
	private String columnId;
	//表中文名
	private String tableName;
	//列中文名
	private String columnName;
	//业务单据id
	private String BussinessId;
	
	/**
	 * 获取表中文名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置表中文名
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 获取列中文名
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * 设置列中文名
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * 获取备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取流程名称
	 * @return	流程名称
	 */
	public String getWfName() {
		return wfName;
	}
	/**
	 * 设置流程名称
	 * @param wfName	流程名称
	 */
	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	/**
	 * 获取列名（英文）
	 * @return
	 */
	public String getColumnId() {
		return columnId;
	}
	/**
	 * 设置列名（英文）
	 * @param columnId
	 */
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	/**
	 * 获取表名（英文）
	 * @return
	 */
	public String getTableId() {
		return tableId;
	}
	/**
	 * 设置表名（英文）
	 * @param tableId	表英文名
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	/**
	 * 获取分页sql中的 开始
	 * @return	分页查询开始
	 */
	public int getFrom(){
		return ((page-1)*rows+1);
	}
	/**
	 * 获取分页sql语句总的 截止
	 * @return	分页查询截止
	 */
	public int getTo(){
		return page*rows;
	}
	
	/**
	 * 获取页码
	 * @return	页码
	 */
	public int getPage() {
		return page;
	}
	/**
	 * 设置页码
	 * @param page
	 */
	public void setPage(int page) {
		if(page>0)
		this.page = page;
	}
	/**
	 * 设置每页信息数
	 * @return
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * 设置每页个数
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 获取排序方式
	 * @return
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * 设置排序方式
	 * @param order
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * 获取流程id
	 * @return
	 */
	public long getWfId() {
		return wfId;
	}
	/**
	 * 设置流程id
	 * @param wfId
	 */
	public void setWfId(long wfId) {
		this.wfId = wfId;
	}
	
	public long getnId() {
		return nId;
	}
	public void setnId(long nId) {
		this.nId = nId;
	}
	public long getRuleId() {
		return ruleId;
	}
	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * 获取业务单据id
	 * @return
	 */
	public String getBussinessId() {
		return BussinessId;
	}
	/**
	 * 设置业务单据id
	 * @param bussinessId
	 */
	public void setBussinessId(String bussinessId) {
		BussinessId = bussinessId;
	}
}
