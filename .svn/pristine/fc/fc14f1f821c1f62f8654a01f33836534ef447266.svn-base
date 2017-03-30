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
@Table(name="T_SYS_ADVNUMPARA")
public class AdvNumPara extends BaseEntity<AdvNumPara> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	
	public AdvNumPara() {
		super();
	}
	private String create_userId;
	private Date create_time;
	private String update_userId;
	private Date update_time;
	/*
	 * 预支次数
	 * */
	private Integer iAdvNum;
	/*
	 * 用户编号
	 * */
	private String cUserId;
	/*
	 * 用户名称
	 * */
	private String cUserName;
	/*
	 * getter、setter方法
	 * */
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
	public Integer getiAdvNum() {
		return iAdvNum;
	}
	public void setiAdvNum(Integer iAdvNum) {
		this.iAdvNum = iAdvNum;
	}
	public String getcUserId() {
		return cUserId;
	}
	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setCreate_userId(rs.getString("create_userId"));
		this.setCreate_time(rs.getDate("create_time"));
		this.setUpdate_userId(rs.getString("update_userId"));
		this.setUpdate_time(rs.getDate("update_time"));
		this.setiAdvNum(rs.getInt("iAdvNum"));
		this.setcUserName(rs.getString("cUserName"));
		this.setcUserId(rs.getString("cUserId"));
	}
	@Override
	public AdvNumPara mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final AdvNumPara AdvNumPara = new AdvNumPara();
		AdvNumPara.setId(rs.getLong("id"));
		AdvNumPara.setCreate_time(rs.getDate("create_time"));
		AdvNumPara.setCreate_userId(rs.getString("create_userId"));
		AdvNumPara.setCreateID(rs.getLong("create_userId"));
		AdvNumPara.setCreateTime(rs.getDate("create_time"));
		AdvNumPara.setcUserId(rs.getString("cUserId"));
		AdvNumPara.setiAdvNum(rs.getInt("iAdvNum"));
		AdvNumPara.setUpdate_time(rs.getDate("update_time"));
		AdvNumPara.setUpdate_userId(rs.getString("update_userId"));
		AdvNumPara.setUpdateID(rs.getLong("update_userId"));
		AdvNumPara.setUpdateTime(rs.getDate("update_time"));
		return AdvNumPara;
	}
}
