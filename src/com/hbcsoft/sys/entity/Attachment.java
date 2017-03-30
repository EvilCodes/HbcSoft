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
@Table(name="T_SYS_ATTACHMENT")
public class Attachment extends BaseEntity<Attachment> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	public Attachment() {
		super();
	}
	private Date create_time;
	private Date update_time;
	private String create_userId;
	private String update_userId;
	private String businessKey;
	private String fileName;
	private String filePath;
	private String cAttaType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
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
	public String getcAttaType() {
		return cAttaType;
	}
	public void setcAttaType(String cAttaType) {
		this.cAttaType = cAttaType;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setBusinessKey(rs.getString("businessKey"));
		this.setFileName(rs.getString("fileName"));
		this.setFilePath(rs.getString("filePath"));
		this.setcAttaType(rs.getString("cAttaType"));
	}
	@Override
	public Attachment mapRow(ResultSet rs, int arg1) throws SQLException {
		Attachment attachment = new Attachment();
		attachment.setId(rs.getLong("id"));
		
		
		return attachment;
	}
}
