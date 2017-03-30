package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.Table;
/**
 * 点选页面管理实体类
 * @author churuifeng
 *
 */
@Table(name="T_SYS_CLICKMANAGE")
public class ClickManage extends BaseEntity<ClickManage> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Key值
	 */
	private String clickKey;
	/**
	 * Value值
	 */
	private String clickValue;
	/**
	 * 标识   0:启动   1：停用（删除）
	 */
	private int mark;
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
	 * Keys的get方法
	 * @return
	 */
	public String getClickKey() {
		return clickKey;
	}
	/**
	 * Keys的set方法
	 * @param keys
	 */
	public void setClickKey(final String clickKey) {
		this.clickKey = clickKey;
	}
	/**
	 * values的get方法
	 * @return
	 */
	public String getClickValue() {
		return clickValue;
	}
	/**
	 * values的set方法
	 * @return
	 */
	public void setClickValue(final String clickValue) {
		this.clickValue = clickValue;
	}
	/**
	 * mark的get方法
	 * @return
	 */
	public int getMark() {
		return mark;
	}
	/**
	 * mark的set方法
	 * @param mark
	 */
	public void setMark(final int mark) {
		this.mark = mark;
	}
	/**
	 * processRow方法
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setClickKey(rs.getString("clickKey"));
		this.setClickValue(rs.getString("clickValue"));
		this.setMark(rs.getInt("mark"));
	}
	/**
	 * mapRow方法
	 */
	@Override
	public ClickManage mapRow(final ResultSet rs, final int arg1) throws SQLException {
		final ClickManage cm=new ClickManage();
		super.mapRow(cm, rs, arg1);
		cm.setId(rs.getLong("id"));
		cm.setClickKey(rs.getString("clickKey"));
		cm.setClickValue(rs.getString("clickValue"));
		cm.setMark(rs.getInt("mark"));
		return cm;
	}
}
