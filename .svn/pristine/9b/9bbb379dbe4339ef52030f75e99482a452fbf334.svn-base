package com.hbcsoft.sys.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbcsoft.zdy.common.BaseEntity;
import com.sun.istack.internal.NotNull;
import com.yaja.validator.inter.Email;
import com.yaja.validator.inter.IsFiled;
import com.yaja.validator.inter.Length;
import com.yaja.validator.inter.Table;
/**
 * User实体类
 * @author churuifeng
 *
 */
@Table(name="T_SYS_USER")
public class User extends BaseEntity<User> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2728013531375038985L;
	/**
	 * 登录用户
	 */
	@Length(max=32, min=2, message="登录用户名的长度应该是2位到32位！")
	@IsFiled(name="login_Name")
	private String loginName;
	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 用户姓名
	 */
	@NotNull
	private String name;

	/**
	 * 邮件
	 */
	@Email
	private String email;

	/**
	 * 住宅电话
	 */
//	@Telephone
	private String tel;
	/**
	 * 手机号
	 */
//	@Mobile
//	@PostCode
	private String mobilephone;

	/**
	 * 身份证号
	 */
//	@RegularCheck(RegularPattern="^[0-9]{10}")
	private String identityNumber;
	/**
	 * 状态	0 是	1否
	 */
	private int enable;

	/**
	 * 性别	0男	1女
	 */
	private int sex;

	/**
	 * 职务
	 */
	private String duty;

	/**
	 * 开户行
	 */
	private String depositBank;

	/**
	 * 人员性质
	 */
	private String personType;
	/**
	 * 卡号
	 */
	private String cardNumber;

	/**
	 * 公务卡号
	 */
	private String businessCardNumber;

	/**
	 * 每页显示的记录数
	 */
	private Integer pageSize;

	/**
	 * 是否系统管理员  0 是  1 否
	 */
	private Integer isManager;

	/**
	 * 学历专业
	 */
	private String major;

	/**
	 * List<String> 资源
	 */
	@IsFiled(except=true)
	private List<String> resourceList;

	/**
	 * 公务卡开户行
	 */
	private String pubServiceBank;

	/**
	 * 有权限的部门id列表
	 */
	@IsFiled(except=true)
	private List<String> deptRowAuthorityList;

	/**
	 * 头像路径
	 */
	private String imgPath = "";
	/**
	 * 当前状态（账号是否激活） 0'是' 1'否'
	 */
	private Integer state;
	/**
	 * 皮肤路径
	 */
	private String cssPath;
	/**
	 * 机构
	 */
	@IsFiled(except=true)
	private Org org;
	/**
	 * 机构id
	 */
	private Long orgId;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 是否同步数据
	 */
	private int isclone;
	
	/**
	 * 角色管理
	 */
	@IsFiled(except=true)
	private List<Role> role= new ArrayList<Role>(); 
	/**
	 * 角色管理
	 * @return
	 */
	public List<Role> getRole() {
		return role;
	}
	/**
	 * 角色管理
	 * @param role
	 */
	public void setRole(final List<Role> role) {
		this.role = role;
	}
	/**
	 * login_Name的get方法
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * login_Name的set方法
	 * @param loginName
	 */
	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}
	/**
	 * password的get方法
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password的set方法
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}
	/**
	 * name的get方法
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * name的set方法
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * email的get方法
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email的set方法
	 * @param email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
	/**
	 * tel的get方法
	 * @return
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * tel的set方法
	 * @param tel
	 */
	public void setTel(final String tel) {
		this.tel = tel;
	}
	/**
	 * mobilephone的get方法
	 * @return
	 */
	public String getMobilephone() {
		return mobilephone;
	}
	/**
	 * mobilephone的set方法
	 * @param mobilephone
	 */
	public void setMobilephone(final String mobilephone) {
		this.mobilephone = mobilephone;
	}
	/**
	 * identityNumber的get方法
	 * @return
	 */
	public String getIdentityNumber() {
		return identityNumber;
	}
	/**
	 * identityNumber的set方法
	 * @param identityNumber
	 */
	public void setIdentityNumber(final String identityNumber) {
		this.identityNumber = identityNumber;
	}
	/**
	 * orgId的get方法
	 * @return
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * orgId的set方法
	 * @param orgId
	 */
	public void setOrgId(final Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * orgName的get方法
	 * @return
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * orgName的set方法
	 * @param orgName
	 */
	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}
	/**
	 * org的get方法
	 * @return
	 */
	public Org getOrg() {
		return org;
	}
	/**
	 * org的set方法
	 * @param org
	 */
	public void setOrg(final Org org) {
		this.org = org;
	}
	/**
	 * enable的get方法
	 * @return
	 */
	public int getEnable() {
		return enable;
	}
	/**
	 * enable的set方法
	 * @param enable
	 */
	public void setEnable(final int enable) {
		this.enable = enable;
	}
	/**
	 * sex的get方法
	 * @return
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * sex的set方法
	 * @param sex
	 */
	public void setSex(final int sex) {
		this.sex = sex;
	}
	/**
	 * duty的get方法
	 * @return
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * duty的set方法
	 * @param duty
	 */
	public void setDuty(final String duty) {
		this.duty = duty;
	}
	/**
	 * depositBank的get方法
	 * @return
	 */
	public String getDepositBank() {
		return depositBank;
	}
	/**
	 * depositBank的set方法
	 * @param depositBank
	 */
	public void setDepositBank(final String depositBank) {
		this.depositBank = depositBank;
	}
	/**
	 * personType的get方法
	 * @return
	 */
	public String getPersonType() {
		return personType;
	}
	/**
	 * personType的set方法
	 * @param personType
	 */
	public void setPersonType(final String personType) {
		this.personType = personType;
	}
	/**
	 * cardNumber的get方法
	 * @return
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * cardNumber的set方法
	 * @param cardNumber
	 */
	public void setCardNumber(final String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * 
	 * @return
	 */
	public String getBusinessCardNumber() {
		return businessCardNumber;
	}
	/**
	 * login_Name的set方法
	 * @return
	 */
	public void setBusinessCardNumber(final String businessCardNumber) {
		this.businessCardNumber = businessCardNumber;
	}
	/**
	 * login_Name的get方法
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * login_Name的set方法
	 * @return
	 */
	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * login_Name的get方法
	 * @return
	 */
	public Integer getIsManager() {
		return isManager;
	}
	/**
	 * login_Name的set方法
	 * @return
	 */
	public void setIsManager(final Integer isManager) {
		this.isManager = isManager;
	}
	/**
	 * major的get方法
	 * @return
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * major的set方法 
	 * @param major
	 */
	public void setMajor(final String major) {
		this.major = major;
	}
	/**
	 * resourceList的get方法
	 * @return
	 */
	public List<String> getResourceList() {
		return resourceList;
	}
	/**
	 * resourceList的set方法 
	 * @param major
	 */
	public void setResourceList(final List<String> resourceList) {
		this.resourceList = resourceList;
	}
	/**
	 * pubServiceBank的get方法
	 * @return
	 */
	public String getPubServiceBank() {
		return pubServiceBank;
	}
	/**
	 * pubServiceBank的set方法
	 * @param pubServiceBank
	 */
	public void setPubServiceBank(final String pubServiceBank) {
		this.pubServiceBank = pubServiceBank;
	}
	/**
	 * deptRowAuthorityList的get方法
	 * @return
	 */
	public List<String> getDeptRowAuthorityList() {
		return deptRowAuthorityList;
	}
	/**
	 * deptRowAuthorityList的set方法
	 * @param deptRowAuthorityList
	 */
	public void setDeptRowAuthorityList(final List<String> deptRowAuthorityList) {
		this.deptRowAuthorityList = deptRowAuthorityList;
	}
	/**
	 * imgPath的get方法
	 * @return
	 */
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * imgPath的set方法
	 * @param imgPath
	 */
	public void setImgPath(final String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * state的get方法
	 * @return
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * state的set方法
	 * @param state
	 */
	public void setState(final Integer state) {
		this.state = state;
	}
	/**
	 * 皮肤路径的get方法
	 * @return
	 */
	public String getCssPath() {
		return cssPath;
	}
	/**
	 * 皮肤路径的set方法
	 * @param cssPath
	 */
	public void setCssPath(final String cssPath) {
		this.cssPath = cssPath;
	}
	/**
	 * get方法
	 * @return
	 */
	public int getIsclone() {
		return isclone;
	}
	/**
	 * set方法
	 * @param isclone
	 */
	public void setIsclone(final int isclone) {
		this.isclone = isclone;
	}
	/**
	 * processRow方法 
	 */
	@Override
	public void processRow(final ResultSet rs) throws SQLException {
		super.processRow(rs);
		this.setId(rs.getLong("id"));
		this.setName(rs.getString("name"));
		this.setLoginName(rs.getString("login_Name"));
		this.setPassword(rs.getString("password"));
		this.setEmail(rs.getString("email"));
		this.setTel(rs.getString("tel"));
		this.setMobilephone(rs.getString("mobilephone"));
		this.setIdentityNumber(rs.getString("identityNumber"));
		this.setEnable(rs.getInt("enable"));
		this.setSex(rs.getInt("sex"));
		this.setDuty(rs.getString("duty"));
		this.setDepositBank(rs.getString("depositBank"));
		this.setPersonType(rs.getString("personType"));
		this.setCardNumber(rs.getString("cardNumber"));
		this.setPageSize(rs.getInt("pageSize"));
		this.setIsManager(rs.getInt("isManager"));
		this.setMajor(rs.getString("major"));
		this.setBusinessCardNumber(rs.getString("businessCardNumber"));
		this.setCreateID(rs.getLong("createID"));
		this.setCreateTime(rs.getDate("createTime"));
		this.setState(rs.getInt("state"));
		this.setPubServiceBank(rs.getString("pubServiceBank"));
		this.setOrgId(rs.getLong("orgId"));
		this.setCssPath(rs.getString("cssPath"));
		this.setIsclone(rs.getInt("isclone"));
		this.setOrgName(rs.getString("orgName"));
	}
	/**
	 * 查询用户信息是调用的方法
	 */
	@Override
	public User mapRow(final ResultSet rs,final int arg1) throws SQLException {
		final User user = new User();
		super.mapRow(user, rs, arg1);
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setLoginName(rs.getString("login_Name"));
//		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setTel(rs.getString("tel"));
		user.setMobilephone(rs.getString("mobilephone"));
		user.setIdentityNumber(rs.getString("identityNumber"));
		user.setEnable(rs.getInt("enable"));
		user.setSex(rs.getInt("sex"));
		user.setDuty(rs.getString("duty"));
		user.setDepositBank(rs.getString("depositBank"));
		user.setPersonType(rs.getString("personType"));
		user.setCardNumber(rs.getString("cardNumber"));
		user.setPageSize(rs.getInt("pageSize"));
		user.setIsManager(rs.getInt("isManager"));
		user.setMajor(rs.getString("major"));
		user.setBusinessCardNumber(rs.getString("businessCardNumber"));
		user.setCardNumber(rs.getString("cardNumber"));
		user.setCreateID(rs.getLong("createID"));
		user.setCreateTime(rs.getDate("createTime"));
		user.setDepositBank(rs.getString("depositBank"));
		user.setState(rs.getInt("state"));
		user.setPubServiceBank(rs.getString("pubServiceBank"));
		user.setOrgId(rs.getLong("orgId"));
		user.setCssPath(rs.getString("cssPath"));
		user.setIsclone(rs.getInt("isclone"));
		user.setOrgName(rs.getString("orgName"));
		return user;
	}
}
