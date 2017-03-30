package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;



/**
* 数据字典
* @author Administrator
*
*/
@Entity
@Table(name = "T_SYS_DATADICT")
public class DataDict extends BaseEntity<DataDict> implements Serializable{
	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = -3584121625696400902L;
	/**
	* 编码
	*/
	private String dictCode;
	/**
	*/
	private String dictName;
	/**
	* 排序
	*/
	private Integer sort;
	/**
	* 启用状态  1 停用 0 启用
	*/
	private Integer enable;

	/**
	* 字典类型
	*/
	@IsFiled(except=true)
	private DictType dictType;
	/**
	* 数据字典类型id
	*/
	private Long dtId;
	/**
	* 字典属性
	*/
	private String dictProperty;
	
	/**
	* 类型属性，标注数据字典类型的种类
	* */
	private String typeKind;
	/**
	* 字典编码
	* @return
	*/
	@NotNull
	public String getDictCode() {
		return dictCode;
	}
	/**
	* set字典编码
	* @param dictCode
	*/
	public void setDictCode(final String dictCode) {
		this.dictCode = dictCode;
	}
	/**
	* get字典名称
	* @return
	*/
	@NotNull
	public String getDictName() {
		return dictName;
	}
	/**
	* set字典名称
	* @param dictName
	*/
	public void setDictName(final String dictName) {
		this.dictName = dictName;
	}
	/**
	* get排序
	* @return
	*/
	public Integer getSort() {
		return sort;
	}
	/**
	* set排序
	* @param sort
	*/
	public void setSort(final Integer sort) {
		this.sort = sort;
	}
	/**
	* get停用、启用
	* @return
	*/
	public Integer getEnable() {
		return enable;
	}
	/**
	* set停用、启用
	* @param enable
	*/
	public void setEnable(final Integer enable) {
		this.enable = enable;
	}
	/**
	* 数据字典类型get方法
	* @return
	*/
	public DictType getDictType() {
		return dictType;
	}
	/**
	* 数据字典类型set方法
	* @param dictType
	*/
	public void setDictType(final DictType dictType) {
		this.dictType = dictType;
	}
	/**
	* 数据字典类型id get方法
	* @return
	*/
	public Long getDtId() {
		return dtId;
	}
	/**
	* 数据字典类型id set方法
	* @param dtId
	*/
	public void setDtId(final Long dtId) {
		this.dtId = dtId;
	}
	/**
	* 字典属性  get方法
	* @return
	*/
	public String getDictProperty() {
		return dictProperty;
	}
	/**
	* 字典属性 set方法
	* @param dictProperty
	*/
	public void setDictProperty(final String dictProperty) {
		this.dictProperty = dictProperty;
	}
	/**
	* 类型种类  get方法
	* @return
	*/
	public String getTypeKind() {
		return typeKind;
	}
	/**
	* 类型种类 set方法
	* @param typeKind
	*/
	public void setTypeKind(final String typeKind) {
		this.typeKind = typeKind;
	}
	/**
	* mapRow
	*/
	@Override
	public DataDict mapRow(final ResultSet rs,final int arg1) throws SQLException {
		// TODO Auto-generated method stub
	    final	DataDict dd = new DataDict();
		super.mapRow(dd, rs, arg1);
		//dd.setId(rs.getLong("id"));
		dd.setDictCode(rs.getString("dictCode"));
		dd.setDictName(rs.getString("dictName"));
		dd.setSort(rs.getInt("sort"));
		dd.setEnable(rs.getInt("enable"));
		dd.setTypeKind(rs.getString("typeKind"));
		dd.setDtId(rs.getLong("dtId"));
		dd.setDictProperty(rs.getString("dictProperty"));
		return dd;
	}
	/**
	* processRow
	*/
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		super.processRow(rs);
		this.setDictCode(rs.getString("dictCode"));
		this.setDictName(rs.getString("dictName"));
		this.setSort(rs.getInt("sort"));
		this.setEnable(rs.getInt("enable"));
		this.setTypeKind(rs.getString("typeKind"));
		this.setDtId(rs.getLong("dtId"));
		this.setDictProperty(rs.getString("dictProperty"));
		
	}
}
