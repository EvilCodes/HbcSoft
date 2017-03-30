package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;
/**
 * @author awh
 * @version 2016-8-3上午10:10:55
 * 往来结算
 **/
@Table(name="T_SYS_COMEGOPARA")
public class Comegopara extends BaseEntity<Comegopara> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public Comegopara() {
		super();
	}
	private String create_userId;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	/*
	 * 是否启用
	 * */
	private Boolean bIsEnable;//boolean
	/*
	 * 名称
	 * */
	private String cName;
	/*
	 * 父对象编码
	 * */
	private String cParent_id;//cParent_id
	/*
	 * 收款类型
	 * */
	private Integer iCollectType;
	/*
	 * 税费类型
	 * */
	private Integer iTaxfeeType;
	/*
	 * 报销代扣类型
	 * */
	private Integer iReidefuType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_userId() {
		return update_userId;
	}
	public void setUpdate_userId(String update_userId) {
		this.update_userId = update_userId;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public Boolean getbIsEnable() {
		return bIsEnable;
	}
	public void setbIsEnable(Boolean bIsEnable) {
		this.bIsEnable = bIsEnable;
	}
	public String getcParent_id() {
		return cParent_id;
	}
	public void setcParent_id(String cParent_id) {
		this.cParent_id = cParent_id;
	}
	public Integer getiCollectType() {
		return iCollectType;
	}
	public void setiCollectType(Integer iCollectType) {
		this.iCollectType = iCollectType;
	}
	public Integer getiTaxfeeType() {
		return iTaxfeeType;
	}
	public void setiTaxfeeType(Integer iTaxfeeType) {
		this.iTaxfeeType = iTaxfeeType;
	}
	public Integer getiReidefuType() {
		return iReidefuType;
	}
	public void setiReidefuType(Integer iReidefuType) {
		this.iReidefuType = iReidefuType;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setbIsEnable(rs.getBoolean("bIsEnable"));
		this.setcName(rs.getString("cName"));
		this.setcParent_id(rs.getString("cParent_id"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setiCollectType(rs.getInt("iCollectType"));
		this.setId(rs.getLong("id"));
		this.setiReidefuType(rs.getInt("iReidefuType"));
		this.setiTaxfeeType(rs.getInt("iTaxfeeType"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setCreate_userId(rs.getString("create_userId"));
		
	}
	@Override
	public Comegopara mapRow(ResultSet rs, int arg1) throws SQLException {
		Comegopara comegopara = new Comegopara();
		comegopara.setId(rs.getLong("id"));
		
		
		return comegopara;
	}
	
}
