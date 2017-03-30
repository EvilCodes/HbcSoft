package com.hbcsoft.common;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 树形
 * 
 * @author Administrator
 *
 */
public class TreeNode {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 父id
	 */
	private transient Long pId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 选中
	 */
	private Boolean checked;
	/**
	 * 展开
	 */
	private Boolean open;
	/**
	 * 子节点
	 */
	private List<TreeNode> children;
	/**
	 * 路径
	 */
	private String hrefUrl;

	/**
	 * 备注
	 */
	private String memo;
	/**
	 * attr
	 */
	private String attr;
	/**
	 * jsonStr
	 */
	private String jsonStr;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 父id
	 */
	private String fatherID;
	/**
	 * originalID
	 */
	private String originalID;
	/**
	 * levelNum
	 */
	private int levelNum;
	/**
	 * index
	 */
	private int index;
	/**
	 * 时间
	 */
	private String time;

	/**
	 * 人员性质
	 */
	private String personType;

	/**
	 * 登录用户
	 */
	private String loginName;
	/**
	 * 机构中code
	 */
	private String code;

	/**
	 * 无参构造
	 */
	public TreeNode() {
		super();
	}

	/**
	 * 有参构造
	 * 
	 * @param id
	 * @param name
	 * @param hrefUrl
	 * @param levelNum
	 */
	public TreeNode(final Long id, final String name, final String hrefUrl,
			final int levelNum) {
		this.id = id;
		this.name = name;
		this.hrefUrl = hrefUrl;
		this.levelNum = levelNum;
	}

	/**
	 * 有参构造
	 * 
	 * @param id
	 * @param name
	 * @param hrefUrl
	 * @param open
	 */
	public TreeNode(final Long id, final String name, final String hrefUrl,
			final Boolean open) {
		this.id = id;
		this.name = name;
		this.hrefUrl = hrefUrl;
		this.open = open;
	}
	/**
	 * 有参构造
	 * 
	 * @param id
	 * @param name
	 * @param hrefUrl
	 * @param open
	 * @param code
	 */
	public TreeNode(final Long id, final String name, final String hrefUrl,
			final Boolean open, final String code) {
		this.id = id;
		this.name = name;
		this.hrefUrl = hrefUrl;
		this.open = open;
		this.code = code;
	}
	/**
	 * 有参构造
	 * 
	 * @param id
	 * @param name
	 * @param hrefUrf
	 */
	public TreeNode(final Long id, final String name, final String hrefUrl,
			final Boolean open, final String fatherID, final String originalID) {
		this.id = id;
		this.name = name;
		this.hrefUrl = hrefUrl;
		this.open = open;
		this.fatherID = fatherID;
		this.originalID = originalID;
	}

	/**
	 * id getter
	 * 
	 * @return
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getId() {
		return id;
	}

	/**
	 * id setter
	 * 
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * pid getter
	 * 
	 * @return
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getpId() {
		return pId;
	}

	/**
	 * pid setter
	 * 
	 * @param pId
	 */
	public void setpId(final Long pId) {
		this.pId = pId;
	}

	/**
	 * 名称 getter
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称 setter
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * checked
	 * 
	 * @return
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

	/**
	 * 展开
	 * 
	 * @return
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * 展开
	 * 
	 * @param open
	 */
	public void setOpen(final Boolean open) {
		this.open = open;
	}

	/**
	 * 子菜单
	 * 
	 * @return
	 */
	public List<TreeNode> getChildren() {
		return children;
	}

	/**
	 * 子菜单
	 * 
	 * @param children
	 */
	public void setChildren(final List<TreeNode> children) {
		this.children = children;
	}

	/**
	 * 路径
	 * 
	 * @return
	 */
	public String getHrefUrl() {
		return hrefUrl;
	}

	/**
	 * 路径
	 * 
	 * @param hrefUrl
	 */
	public void setHrefUrl(final String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	/**
	 * 属性
	 * 
	 * @return
	 */
	public String getAttr() {
		return attr;
	}

	/**
	 * 属性
	 * 
	 * @param attr
	 */
	public void setAttr(final String attr) {
		this.attr = attr;
	}

	/**
	 * jsonStr
	 * 
	 * @return
	 */
	public String getJsonStr() {
		return jsonStr;
	}

	/**
	 * jsonStr
	 */
	public void setJsonStr(final String jsonStr) {
		this.jsonStr = jsonStr;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 父节点id
	 * 
	 * @return
	 */
	public String getFatherID() {
		return fatherID;
	}

	/**
	 * 父节点id
	 * 
	 * @param fatherID
	 */
	public void setFatherID(final String fatherID) {
		this.fatherID = fatherID;
	}

	/**
	 * originalID
	 * 
	 * @return
	 */
	public String getOriginalID() {
		return originalID;
	}

	/**
	 * originalID
	 * 
	 * @param
	 */
	public void setOriginalID(final String originalID) {
		this.originalID = originalID;
	}

	/**
	 * levelNum
	 * 
	 * @return
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * @param levelNum
	 */
	public void setLevelNum(final int levelNum) {
		this.levelNum = levelNum;
	}

	/**
	 * index
	 * 
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * time
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setTime(final String time) {
		this.time = time;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 
	 * @param memo
	 */
	public void setMemo(final String memo) {
		this.memo = memo;
	}

	/**
	 * personType
	 * 
	 * @return
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * 
	 * @param personType
	 */
	public void setPersonType(final String personType) {
		this.personType = personType;
	}

	/**
	 * loginName
	 * 
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 
	 * @param loginName
	 */
	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	
}
