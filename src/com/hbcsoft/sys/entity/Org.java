package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.hbcsoft.zdy.common.BaseEntity;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Table;

/**
* 机构
* 
* @author Administrator
*
*/
@Entity
@Table(name = "T_SYS_ORG")
public class Org extends BaseEntity<Org> implements Serializable {
	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 3303396696224537908L;
	/**
	* 机构编码
	*/
	private String code;
	/**
	* 机构名称
	*/
	private String name;

	/**
	* 启用状态 1 停用 0 启用
	*/
	private Integer enable;

	/**
	* 排序
	*/
	private Integer orderNo;

	/**
	* 父部门
	*/
	@IsFiled(except = true)
	private Org parentOrg;
	/**
	* 父id
	*/
	private Long parentId;

	/**
	* 机构类型
	*/
	private Integer orgType;

	/**
	* 子部门
	*/
	@IsFiled(except = true)
	private List<Org> childrenOrg = new ArrayList<Org>();

	/**
	* 创建用户
	*/
	@IsFiled(except = true)
	private User createUser;
	/**
	* 用户id
	*/
	private Long userId;
	/**
	 * 是否同步
	 */
	private int isClone;
	/**
	* 用户名 操作员
	*/
	// private String userName;
	/**
	* 机构编码 getter
	* 
	* @return
	*/
	public String getCode() {
		return code;
	}

	/**
	* 机构编码 setter
	* 
	* @param code
	*/
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	* 机构名称 getter
	* 
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* 机构名称 setter
	* 
	* @param name
	*/
	public void setName(final String name) {
		this.name = name;
	}

	/**
	* 是否：启用，停用 getter
	* 
	* @return
	*/
	public Integer getEnable() {
		return enable;
	}

	/**
	* 是否：启用，停用 setter
	* 
	* @param enable
	*/
	public void setEnable(final Integer enable) {
		this.enable = enable;
	}

	/**
	* 排序 getter
	* 
	* @return
	*/
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	* 排序 setter
	* 
	* @param orderNo
	*/
	public void setOrderNo(final Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	* 机构父id getter
	* 
	* @return
	*/
	public Long getParentId() {
		return parentId;
	}

	/**
	* 机构父id setter
	* 
	* @param parentId
	*/
	public void setParentId(final Long parentId) {
		this.parentId = parentId;
	}

	/**
	* 机构类型 getter
	* 
	* @return
	*/
	public Integer getOrgType() {
		return orgType;
	}

	/**
	* 机构类型 setter
	* 
	* @param orgType
	*/
	public void setOrgType(final Integer orgType) {
		this.orgType = orgType;
	}

	/**
	* 用户id getter
	* 
	* @return
	*/
	public Long getUserId() {
		return userId;
	}

	/**
	* 用户id setter
	* 
	* @param userId
	*/
	public void setUserId(final Long userId) {
		this.userId = userId;
	}
	/**
	 * 是否同步数据 getter
	 * @return
	 */
	public int getIsClone() {
		return isClone;
	}
	/**
	 * 是否同步数据 setter
	 * @return
	 */
	public void setIsClone(final int isClone) {
		this.isClone = isClone;
	}

	/**
	* 父机构 getter
	* 
	* @return
	*/
	public Org getParentOrg() {
		return parentOrg;
	}

	/**
	* 父机构 setter
	* 
	* @param parentOrg
	*/
	public void setParentOrg(final Org parentOrg) {
		this.parentOrg = parentOrg;
	}

	/**
	* 子机构
	* 
	* @return
	*/
	public List<Org> getChildrenOrg() {
		return childrenOrg;
	}

	/**
	* 子机构
	* 
	* @param childrenOrg
	*/
	public void setChildrenOrg(final List<Org> childrenOrg) {
		this.childrenOrg = childrenOrg;
	}

	/**
	* 创建人
	* 
	* @return
	*/
	public User getCreateUser() {
		return createUser;
	}

	/**
	* 创建人
	* 
	* @param createUser
	*/
	public void setCreateUser(final User createUser) {
		this.createUser = createUser;
	}

	/**
	* processRow
	*/
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		super.processRow(rs);
		this.setCode(rs.getString("code"));
		this.setName(rs.getString("name"));
		this.setEnable(rs.getInt("enable"));
		this.setOrderNo(rs.getInt("orderNo"));
		this.setParentId(rs.getLong("parentId"));
		this.setUserId(rs.getLong("userId"));
		this.setOrgType(rs.getInt("orgType"));
		this.setIsClone(rs.getInt("isClone"));
	}

	/**
	* 查询机构信息是调用的方法
	*/
	@Override
	public Org mapRow(final ResultSet rs, final int arg1) throws SQLException {
		// TODO Auto-generated method stub
		final Org org = new Org();
		org.setId(rs.getLong("id"));
		org.setCode(rs.getString("code"));
		org.setName(rs.getString("name"));
		org.setEnable(rs.getInt("enable"));
		org.setOrderNo(rs.getInt("orderNo"));
		org.setParentId(rs.getLong("parentId"));
		org.setUserId(rs.getLong("userId"));
		org.setOrgType(rs.getInt("orgType"));
		org.setIsClone(rs.getInt("isClone"));
		org.setUserId(rs.getLong("userId"));
		return org;
	}

}
