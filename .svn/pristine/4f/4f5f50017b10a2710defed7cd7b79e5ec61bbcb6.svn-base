package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException; // sql异常
import javax.validation.constraints.NotNull;
import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;
 
/**
* 字典类型
* @author chr
*
*/
@Table(name="T_SYS_DICTTYPE")
public class DictType extends BaseEntity<DictType> implements Serializable{

	
	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = -7798173772886919250L;

	
	/**
	* 编码
	*/
	@NotNull
	private String dtCode;
	/**
	* 名称
	*/
	@NotNull
	private String dtName;
	/**
	* 排序
	*/
	private Integer sort;
	/**
	* 启用状态  1 停用 0 启用
	*/
	private Integer enable;
	

	/**
	* 父字典类型
	*/
	@IsFiled(except=true)
	private DictType parentDictType;
	//@IsFiled(except=true)
	//private List<DataDict> dateDict = new ArrayList<DataDict>();
	/**
	* 父节点id
	*/
	private Long parentId;
	/**
	* 数据字典id
	*/
	private Long dataDictId;
	/**
	* get编码方法
	* @return
	*/
	public String getDtCode() {
		return dtCode;
	}
	/**
	* set编码方法
	* @param dtCode
	*/
	public void setDtCode(String dtCode) { // NOPMD by Administrator on 16-11-25 上午9:16
		this.dtCode = dtCode;
	}
	/**
	* get名称方法
	* @return
	*/
	public String getDtName() {
		return dtName;
	}

	public void setDtName(String dtName) {//set名称方法 // NOPMD by Administrator on 16-11-25 上午9:17
		this.dtName = dtName;
	}
	/**
	* get排序方法
	* @return
	*/
	public Integer getSort() {
		return sort;
	}
	/**
	* set 排序方法
	* @param sort
	*/
	public void setSort(Integer sort) { // NOPMD by Administrator on 16-11-25 上午9:15
		this.sort = sort;
	}
	/**
	* get启用状态  1 启用 0 停用方法
	* @return
	*/
	public Integer getEnable() {
		return enable;
	}
	/**
	* set启用状态  1 启用 0 停用方法
	* @param enable
	*/
	public void setEnable(final Integer enable) { //set启用状态  1 启用 0 停用方法
		this.enable = enable;
	}
	/**
	* get父类方法
	* @return
	*/
	public DictType getParentDictType() {
		return parentDictType;
	}
	/**
	* set父类方法 
	* @param parentDictType
	*/
	public void setParentDictType(final DictType  parentDictType) { // set父类方法 
		this.parentDictType = parentDictType;
	}
	
	/**
	* get数据字典id
	* @return
	*/
	public Long getDataDictId() {
		return dataDictId;
	}
	/**
	* set数据字典id
	* @param dataDictId
	*/
	public void setDataDictId(Long dataDictId) { // NOPMD by Administrator on 16-11-25 上午9:16
		this.dataDictId = dataDictId;
	}
	/**
	* get父节点id
	* @return
	*/
	public Long getParentId() {
		return parentId;
	}
	/**
	* set父节点id
	* @param parentId
	*/
	public void setParentId(final Long parentId) { //父节点id
		this.parentId = parentId;
	}
	/**
	* processRow
	*/
	@Override
	public void processRow(final ResultSet rs) throws SQLException { // processRow 
		// TODO Auto-generated method stub
		super.processRow(rs);
		this.setDtCode(rs.getString("dtCode"));
		this.setDtName(rs.getString("dtName"));
		this.setSort(rs.getInt("sort"));
		this.setEnable(rs.getInt("enable"));
		this.setParentId(rs.getLong("parentId"));
		
	}
	/**
	* mapRow
	*/
	@Override
	public DictType mapRow(final ResultSet rs, final int arg1) throws SQLException {//mapRow
		// TODO Auto-generated method stub
		
		final DictType dicttype=new DictType();
		super.mapRow(dicttype, rs, arg1);
		//dicttype.setId(rs.getLong("id"));
		dicttype.setDtCode(rs.getString("dtCode"));
		dicttype.setDtName(rs.getString("dtName"));
		dicttype.setSort(rs.getInt("sort"));
		dicttype.setEnable(rs.getInt("enable"));
		dicttype.setParentId(rs.getLong("parentId"));
		return dicttype;
	}
	
	
}