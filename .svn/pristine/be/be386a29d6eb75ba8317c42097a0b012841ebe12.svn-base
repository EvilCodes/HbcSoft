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
 **/
@Table(name="T_SYS_AUDITENTRUSTD")
public class AuditentrustD extends BaseEntity<AuditentrustD> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public AuditentrustD() {
		super();
	}
	private String cMainId;
	private String cModuleCode;
	private String cModuleName;
	private String create_userId;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getcMainId() {
		return cMainId;
	}
	public void setcMainId(String cMainId) {
		this.cMainId = cMainId;
	}
	public String getcModuleCode() {
		return cModuleCode;
	}
	public void setcModuleCode(String cModuleCode) {
		this.cModuleCode = cModuleCode;
	}
	public String getcModuleName() {
		return cModuleName;
	}
	public void setcModuleName(String cModuleName) {
		this.cModuleName = cModuleName;
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
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setcMainId(rs.getString("cMainId"));
		this.setcModuleCode(rs.getString("cModuleCode"));
		this.setcModuleName(rs.getString("cModuleName"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setId(rs.getLong("id"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
	}
	@Override
	public AuditentrustD mapRow(ResultSet rs, int arg1) throws SQLException {
		AuditentrustD auditentrustD = new  AuditentrustD();
		auditentrustD.setId(rs.getLong("id"));
		
		
		return auditentrustD;
	}
	
}
