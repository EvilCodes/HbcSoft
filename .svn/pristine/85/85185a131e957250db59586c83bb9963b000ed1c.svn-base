package com.hbcsoft.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.zdy.common.ComConstant;

/**
 * @author sl
 * SimpleTagSupport类实现了SimpleTag接口，
 * SampleTagDemo类继承SimpleTagSupport
 * 通过标签的属性控制标签体的子表样式显示：仅显示
 */
public class SimpleTagDemo extends SimpleTagSupport {

	/**
	 * <table></table>标签的id
	 */
	private transient String tableId;
	/**
	 * <table></table>中的<thead></thead>标签的id
	 */
	private transient String theadId;
	/**
	 * <table></table>中的<tbody></tbody>标签的id
	 */
	private transient String tbodyId;
	/**
	 * 表头的集合
	 */
	private transient List<String> titleList = new ArrayList<String>();
	/**
	 * 属性的集合
	 */
	private transient List<List<FormFields>> fieldList = new ArrayList<List<FormFields>>();

	/**
	 * 是否显示 0：显示 1：不显示
	 */
	private transient int inputIsDisplay;
	/**
	 * css名
	 */
	private transient String className;
	/**
	 * 是否显示最前列的复选框  0：显示 1：不显示
	 */
	private transient int checkboxDisplay;
	/**
	 * 复选框name
	 */
	private transient String checkboxName;
	/**
	 * 复选框id
	 */
	private transient String checkboxId;
	/**
	 * @param tableId
	 */
	public void setTableId(final String tableId) {
		this.tableId = tableId;
	}
	/**
	 * @param theadId
	 */
	public void setTheadId(final String theadId) {
		this.theadId = theadId;
	}
	/**
	 * @param tbodyId
	 */
	public void setTbodyId(final String tbodyId) {
		this.tbodyId = tbodyId;
	}
	/**
	 * @param inputIsDisplay
	 */
	public void setInputIsDisplay(final int inputIsDisplay) {
		this.inputIsDisplay = inputIsDisplay;
	}
	/**
	 * @param titleList
	 */
	public void setTitleList(final List<String> titleList) {
		this.titleList = titleList;
	}
	/**
	 * @param fieldList
	 */
	public void setFieldList(final List<List<FormFields>> fieldList) {
		this.fieldList = fieldList;
	}
	/**
	 * @param className
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	/**
	 * @param checkboxDisplay
	 */
	public void setCheckboxDisplay(final int checkboxDisplay) {
		this.checkboxDisplay = checkboxDisplay;
	}
	/**
	 * @param checkboxName
	 */
	public void setCheckboxName(final String checkboxName) {
		this.checkboxName = checkboxName;
	}
	/**
	 * @param checkboxId
	 */
	public void setCheckboxId(final String checkboxId) {
		this.checkboxId = checkboxId;
	}

	/**
	 * jsp自定义标签返回
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		final StringBuilder sb = new StringBuilder(500);
		sb.append("<table id='"+tableId+"' class='"+className+"'><thead id='"+theadId+"'><tr>");
		/***********************复选框*************************/
		if(checkboxDisplay==0){
			sb.append("<th class='check'><input type='checkbox' id='"+checkboxId+"' onclick='checkAll(this,"+checkboxName+")'></th>");
		}
		/***********************复选框*************************/
		for(int i=0; i<titleList.size(); i++){
			if(inputIsDisplay==0){
				sb.append("<th>"+titleList.get(i)+"</th>");
			}else{
				sb.append("<th style='display:none'>"+titleList.get(i)+"</th>");
			}
		}
		sb.append("</tr></thead><tbody id='"+tbodyId+"'>");
		FormFields ff = new FormFields();
		List<FormFields> ffList = new ArrayList<FormFields>();
		for(int i=0; i<fieldList.size(); i++){
			ffList = fieldList.get(i);
			sb.append("<tr>");

			sb.append(this.getHidden(ffList, ff));
			
			/***********************复选框*************************/
			if(checkboxDisplay==0){
				sb.append("<td class='check'><input type='checkbox' name='"+checkboxName+"' value='"+(i+1)+"'></td>");
			}
			/***********************复选框*************************/
			for(int j=0; j<ffList.size(); j++){
				ff = ffList.get(j);
				if(ff.getIsShowColumn()==ComConstant.EDITTYPE_ADD){
					sb.append("<td>"+ff.getInputValue()+"</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</tbody></table>");
		final PageContext pageContext = (PageContext) this.getJspContext();
		this.getOut(pageContext).write(sb.toString());
	}
	/**
	 * PMD:解决德米特问题
	 * @param pageContext
	 * @return
	 */
	private JspWriter getOut(final PageContext pageContext){
		return pageContext.getOut();
	}
	/**
	 * 方法提取：存放隐藏的input
	 * @param ffList
	 * @param ff
	 * @return
	 */
	private String getHidden(final List<FormFields> ffList,FormFields ff){
		final StringBuilder sbHidden = new StringBuilder(100);
		for(int j=0; j<ffList.size(); j++){
			ff = ffList.get(j);
			if(ff.getIsShowColumn()==0){
				sbHidden.append("<input type='hidden' id='"+ff.getFieldName()+"' name='"+ff.getFieldName()+"' value='"+ff.getInputValue()+"'>");
			}
		}
		return sbHidden.toString();
	}
}
