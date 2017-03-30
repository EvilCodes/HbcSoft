package com.hbcsoft.login.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
/**
 * user和计算机的中间表
 * @author crf
 *
 */
public class UserInfo implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2045840696148606616L;
	
	/**
	 * 表主id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long id;
	/**
	 * 单据主键id
	 */
	@JsonSerialize(using=ToStringSerializer.class)
	protected Long zid;
	
	/**
	 * 计算机名
	 */
	private String computerName;
	
	/**
	 * 当前登录电脑的Ip
	 */
	private String ipName;
	
	/**
	 * 当前登录时间
	 */
	private Date loginTime=new Date();
	
	/**
	 * 当前计算机用户名
	 */
	private String userName;
	
	/**
	 * 当前计算机域名
	 */
	private String userDomain;
	
	/**
	 * 操作系统的名称
	 */
	private String systemName;
	
	/**
	 * 当前用户本机正在使用的网卡的MAC地址（物理地址）
	 */
	private String systemMac;
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
	 * zid的get方法
	 * @return
	 */
	public Long getZid() {
		return zid;
	}
	/**
	 * zid的set方法
	 * @param id
	 */
	public void setZid(final Long zid) {
		this.zid = zid;
	}
	/**
	 * 计算机名的get方法
	 * @return
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * 计算机名的set方法
	 * @param id
	 */
	public void setComputerName(final String computerName) {
		this.computerName = computerName;
	}
	/**
	 * ipName的get方法
	 * @return
	 */
	public String getIpName() {
		return ipName;
	}
	/**
	 * ipName的set方法
	 * @param id
	 */
	public void setIpName(final String ipName) {
		this.ipName = ipName;
	}
	/**
	 * loginTime的get方法
	 * @return
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * loginTime的set方法
	 * @param id
	 */
	public void setLoginTime(final Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 计算机用户名的get方法
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 计算机用户名的set方法
	 * @param id
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	/**
	 * 计算机域名的get方法
	 * @return
	 */
	public String getUserDomain() {
		return userDomain;
	}
	/**
	 * 计算机域名的set方法
	 * @param id
	 */
	public void setUserDomain(final String userDomain) {
		this.userDomain = userDomain;
	}
	/**
	 * systemName的get方法
	 * @return
	 */
	public String getSystemName() {
		return systemName;
	}
	/**
	 * systemName的set方法
	 * @param id
	 */
	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}
	/**
	 * systemMac的get方法
	 * @return
	 */
	public String getSystemMac() {
		return systemMac;
	}
	/**
	 * systemMac的set方法
	 * @param id
	 */
	public void setSystemMac(final String systemMac) {
		this.systemMac = systemMac;
	}
}
