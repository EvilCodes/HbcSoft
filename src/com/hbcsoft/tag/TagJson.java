package com.hbcsoft.tag;

import java.util.ArrayList;
import java.util.List;

import com.hbcsoft.form.entity.FormFields;
/**
 * 用来转化前后台传递list，转化为json字符串来传递
 * @author liang
 *
 */
public class TagJson {

	/**
	 * 子表中标题的集合
	 */
	private List<String> titleList = new ArrayList<String>();
	/**
	 * 子表中所有字段属性的集合
	 */
	private List<List<FormFields>> fieldList = new ArrayList<List<FormFields>>();

	/**
	 * 子表中一行属性的字段集合
	 */
	private List<FormFields> list = new ArrayList<FormFields>();
	/**
	 * 返回标题集合
	 * @return
	 */
	public List<String> getTitleList() {
		return titleList;
	}
	/**
	 * 设置标题集合
	 * @param titleList
	 */
	public void setTitleList(final List<String> titleList) {
		this.titleList = titleList;
	}
	/**
	 * 返回属性集合
	 * @return
	 */
	public List<List<FormFields>> getFieldList() {
		return fieldList;
	}
	/**
	 * 设置属性集合
	 * @param fieldList
	 */
	public void setFieldList(final List<List<FormFields>> fieldList) {
		this.fieldList = fieldList;
	}
	/**
	 * 返回一行属性集合
	 * @return
	 */
	public List<FormFields> getList() {
		return list;
	}
	/**
	 * 设置一行属性集合
	 * @param list
	 */
	public void setList(final List<FormFields> list) {
		this.list = list;
	}
	
	
}
