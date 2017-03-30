package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import com.yaja.validator.inter.Table;

/**
* 日志
* @author Administrator
*
*/
@Table(name="T_SYS_LOG")
public class Log implements Serializable,RowCallbackHandler,RowMapper<Log> {
	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	/**
	* id
	*/
	private Long id;
	/**
	* 操作日志类型
	*/
	private Integer type;
	
	/**
	* 登录名
	*/
	private String loginName;
	/**
	* 操作开始时间
	*/
	private Date operTime;
	
	/**
	* 操作人id
	*/
	private String loginId;
	/**
	* 操作方法
	*/
	private String action;
	/**
	* 内容
	*/
	private String content;
	
	/**
	* 用户IP地址
	*/
	private String ip;
	/**
	* 公司id
	*/
	private Long companyId;
	/**
	* 公司id
	*/
	public Long getCompanyId() {
		return companyId;
	}
	/**
	* 公司id
	*/
	public void setCompanyId(final Long companyId) {
		this.companyId = companyId;
	}
	/**
	* Id的get方法
	* @return
	*/
	public Long getId() {
		return id;
	}
	/**
	* Id的set方法
	* @param id
	*/
	public void setId(final Long id) {
		this.id = id;
	}
	/**
	* type的get方法
	* @return
	*/
	public Integer getType() {
		return type;
	}
	/**
	* type的set方法
	* @param id
	*/
	public void setType(final Integer type) {
		this.type = type;
	}
	/**
	* loginName的get方法
	* @return
	*/
	public String getLoginName() {
		return loginName;
	}
	/**
	* loginName的set方法
	* @param id
	*/
	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}
	/**
	* operTime的get方法
	* @return
	*/
	public Date getOperTime() {
		return operTime;
	}
	/**
	* operTime的set方法
	* @param id
	*/
	public void setOperTime(final Date operTime) {
		this.operTime = operTime;
	}
	/**
	* loginId的get方法
	* @return
	*/
	public String getLoginId() {
		return loginId;
	}
	/**
	* loginId的set方法
	* @param id
	*/
	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}
	/**
	* action的get方法
	* @return
	*/
	public String getAction() {
		return action;
	}
	/**
	* action的set方法
	* @param id
	*/
	public void setAction(final String action) {
		this.action = action;
	}
	/**
	* content的get方法
	* @return
	*/
	public String getContent() {
		return content;
	}
	/**
	* content的set方法
	* @param id
	*/
	public void setContent(final String content) {
		this.content = content;
	}
	/**
	* ip的get方法
	* @return
	*/
	public String getIp() {
		return ip;
	}
	/**
	* ip的set方法
	* @param id
	*/
	public void setIp(final String ip) {
		this.ip = ip;
	}
	/**
	* 显示信息
	*/
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		this.setId(rs.getLong("id"));
		this.setCompanyId(rs.getLong("companyId"));
		this.setType(rs.getInt("type"));
		this.setLoginName(rs.getString("loginName"));
		this.setOperTime(rs.getDate("operTime"));
		this.setLoginId(rs.getString("loginId"));
		this.setAction(rs.getString("action"));
		this.setContent(rs.getString("content"));
		this.setIp(rs.getString("ip"));
	}
	/**
	* 查看所有信息
	* @return Log
	*/
	@Override
	public Log mapRow(final ResultSet rs, final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		final Log log=new Log();
		log.setId(rs.getLong("id"));
		log.setCompanyId(rs.getLong("companyId"));
		log.setType(rs.getInt("type"));
		log.setLoginName(rs.getString("loginName"));
		log.setOperTime(rs.getDate("operTime"));
		log.setLoginId(rs.getString("loginId"));
		log.setAction(rs.getString("action"));
		log.setContent(rs.getString("content"));
		log.setIp(rs.getString("ip"));
		return log;
	}
}
