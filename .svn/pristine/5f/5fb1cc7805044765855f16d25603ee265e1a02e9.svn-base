package com.hbcsoft.form.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;

/**
 * 表单表
 * @author zhanghaijiao
 *
 */
@Table(name="F_FORMNAME")
public class FormName  extends BaseEntity<FormName> implements Serializable  {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2045840696148606616L;
	
	/**
	 * 表单名称
	 */
	@Length(max=100, min=2, message="数据库表字段名称长度应该是2位到100位！")
	@NotNull
	private String formNamef;
	
	/**
	 * 路径
	 */
	@Length(max=200, min=2, message="数据库表字段路径长度应该是2位到200位！")
	@NotNull
	private String actionUrl;
	
	/**
	 * 提交方式 get或者post
	 */
	@NotNull
	private String method;
	
	/**
	 * 备注
	 */
	@Length(max=1000, message="备注信息长度不能超过1000位！")
	private String memo;
	
	/**
	 * 表单类型
	 */
	@Length(max=100, min=2, message="数据库表表单类型长度应该是2位到100位！")
	@NotNull
	private String formType;
	
	/**
	 * 版本号
	 */
	private String versNum;
	
	/**
	 * 是否启用0:否1:是
	 */
	private int isEnabled;
	
	/**
	 * 获取表单名称
	 * @return
	 */
	public String getFormNamef() {
		return formNamef;
	}

	/**
	 * 设置表单名称
	 * @param formName
	 */
	public void setFormNamef(final String formNamef) {
		this.formNamef = formNamef;
	}
	/**
	 * 获取路径
	 * @return
	 */
	public String getActionUrl() {
		return actionUrl;
	}

	/**
	 * 设置路径
	 * @param actionUrl
	 */
	public void setActionUrl(final String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * 获取提交方式
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 设置提交方式
	 * @param method
	 */
	public void setMethod(final String method) {
		this.method = method;
	}

	/**
	 * 获取备注
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * @param memo
	 */
	public void setMemo(final String memo) {
		this.memo = memo;
	}

	/**
	 * 获取表单类型
	 * @return
	 */
	public String getFormType() {
		return formType;
	}

	/**
	 * 设置表单类型
	 * @param formType
	 */
	public void setFormType(final String formType) {
		this.formType = formType;
	}

	/**
	 * 获取版本号
	 * @return
	 */
	public String getVersNum() {
		return versNum;
	}

	/**
	 * 设置版本号
	 * @param versNum
	 */
	public void setVersNum(final String versNum) {
		this.versNum = versNum;
	}

	/**
	 * 获取是否启用
	 * @return
	 */
	public int getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * @param isEnabled
	 */
	public void setIsEnabled(final int isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * 重写父类ProcessRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		
		this.setFormNamef(rs.getString("formNamef"));
		this.setActionUrl(rs.getString("actionUrl"));
		this.setMethod(rs.getString("method"));
		this.setMemo(rs.getString("memo"));
		this.setFormType(rs.getString("formType"));
		this.setVersNum(rs.getString("versNum"));
		this.setIsEnabled(rs.getInt("isEnabled"));
	}

	/**
	 * 重写父类mapRow方法
	 */
	@Override
	public FormName mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final FormName te = new FormName();
		super.mapRow(te, rs, arg1);
		te.setId(rs.getLong("id"));
		te.setFormNamef(rs.getString("formNamef"));
		te.setActionUrl(rs.getString("actionUrl"));
		te.setMethod(rs.getString("method"));
		te.setMemo(rs.getString("memo"));
		te.setFormType(rs.getString("formType"));
		te.setVersNum(rs.getString("versNum"));
		te.setIsEnabled(rs.getInt("isEnabled"));
		return te;
	}
	

}
