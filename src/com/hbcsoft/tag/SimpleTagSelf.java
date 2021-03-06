package com.hbcsoft.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONArray;
import com.hbcsoft.common.CommonService;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.KeyValueEntity;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.zdy.common.ComConstant;
/**
 * @author sl
 * SimpleTagSupport类实现了SimpleTag接口，
 * SampleTagDemo类继承SimpleTagSupport
 * 通过标签的属性控制标签体的子表样式显示：可编辑
 */
public class SimpleTagSelf extends SimpleTagSupport {
	/**
	 * 日志
	 */
	private final static Logger LOGGER = Logger.getLogger(SimpleTagSelf.class);
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
	 * 类名
	 */
	private transient String className;
	/**
	 * 是否显示最前列的复选框  0：显示 1：不显示
	 */
	private transient int checkboxDisplay;
	/**
	 * checkbox标签名称
	 */
	private transient String checkboxName;
	/**
	 * checkbox标签id
	 */
	private transient String checkboxId;
	/**
	 * checkbox标签值
	 */
	private transient String checkboxValue;
	/**
	 * js方法名
	 */
	private transient String jsName;
	/**
	 * 
	 */
	private static String inputName = "' name='";
	/**
	 * 
	 */
	private static String inputValue = "' value='";
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
	 * @param checkboxValue
	 */
	public void setCheckboxValue(final String checkboxValue) {
		this.checkboxValue = checkboxValue;
	}
	/**
	 * @param jsName
	 */
	public void setJsName(final String jsName) {
		this.jsName = jsName;
	}
	/**
	 * 
	 */
	@SuppressWarnings("static-access")
	@Override
	public void doTag() throws JspException, IOException {
		
		/**=====================1====================**/
		final TagJson tag = new TagJson();
		tag.setTitleList(titleList);
//		tag.setFieldList(fieldList);
		if(!fieldList.isEmpty()){
			tag.setList(fieldList.get(0));
		}
		final JSONArray js = new JSONArray();
		final String tt = js.toJSONString(tag);
		/**=====================1====================**/
		
		final StringBuilder sb = new StringBuilder(2000);
		sb.append("<div class='btn-toolbar' role='toolbar' style='float: right;'><div class='btn-group btn-group-sm'><button type='button' class='btn btn-default' id='"+tableId+"' onclick='test(\""+tableId+"\",\""+tbodyId+"\",\""+tableId+tbodyId+"\");'>增行</button></div></div><input type='hidden' id='"+tableId+tbodyId+"'  value='"+tt+"'/><table id='"+tableId+"' class='table table-striped table-bordered'><thead id='"+theadId+"'><tr>");
		/***********************复选框*************************/
		if(checkboxDisplay==0){
			sb.append("<th><input type='checkbox' id='"+checkboxId+"' onclick='checkAll(this,"+checkboxName+")'></th>");
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
		List<FormFields> ffList = new ArrayList<FormFields>();
		final FormFields ff = new FormFields();
		for(int i=0; i<fieldList.size(); i++){
			ffList = fieldList.get(i);
			sb.append("<tr>");
			/***********************复选框*************************/
			if(checkboxDisplay==0){
				sb.append("<td><input type='checkbox' name='"+checkboxName+"'  value='"+checkboxValue+"'></td>");
			}
			/***********************复选框*************************/
			
			sb.append(this.getSwitch(ffList, ff));
			
			/*sb.append("<td><div class='btn-toolbar' role='toolbar'>"+
					"<div class='btn-group btn-group-sm'>"+
					"<button type='button' class='btn btn-default' 
					onclick='deltr(\""+1+"\")'>删行</button></div>"+
					"</div></td>");*/
			sb.append("<td><div class='btn-toolbar' role='toolbar'>"+
					"<div class='btn-group btn-group-sm'>"+
					"<button type='button' class='btn btn-default' onclick='$(this).parents(\"tr\").remove()'>删行</button></div>"+
					"</div></td></tr>");
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
	 * 提取循环
	 * @param ffList
	 * @param ff
	 * @param sb
	 * @return
	 */
	private String getSwitch(final List<FormFields> ffList, FormFields ff){
		final StringBuilder sb = new StringBuilder();
		for(int j=0; j<ffList.size(); j++){
			ff = ffList.get(j);
			sb.append(this.getS(ff));
		}
		return sb.toString();
	}
	private String getS(final FormFields ff){
		final StringBuilder sb = new StringBuilder();
		switch(ff.getInputType()){
		case 0: 
			sb.append(this.getText(ff));
			break;
		case 1://下拉框
			sb.append(this.getSelect(ff));
			break;
		case 2: //按钮
			sb.append(this.getButton(ff));
			break;
		case 3: //多选按钮
			sb.append(this.getCheckbox(ff));
			break;
		case 4: //点选
			sb.append(this.getClick(ff));
			break;
		case 5://日期
			sb.append(this.getDate(ff));
			break;
		case 6://文本域
			sb.append(this.getTextArea(ff));
			break;
		case 7: //金额
			sb.append(this.getMoney(ff));
			break;
		default:
			sb.append("不存在这个值！");
			break;
		}
		return sb.toString();
	}
	/**
	 * 获取text框属性0
	 * @return
	 */
	private String getText(final FormFields ff){
		String content = "";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none' ><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input  type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' readonly class='"+className+"'/> </td>";
		}else{
			content = "<td> <input onchange='"+jsName+"' maxlength='"+ff.getNumber()+"' type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"'";
					if(ff.getIsRequired()==1){//是必录项
						content =content + "required='required'";
					}
			content =content + "class='"+className+"' onchange='"+jsName+"'/></td>";
		}
		return content;
	}
	/**
	 * 获取select属性1
	 * @return
	 */
	private String getSelect(final FormFields ff){
		final StringBuilder sb = new StringBuilder();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getlist(ff);
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			sb.append("<td style='display:none'><select id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt1(map, ff));
			}
			sb.append("</select></td>");
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			sb.append("<td><select id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled  class='"+className+"'><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt2(map, ff));
			}
			sb.append("</select></td>");
		}else{
			sb.append("<td><select onchange='"+jsName+"' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' class=' "+className+"'><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt3(map, ff));
			}
			sb.append("</select></td>");
		}
		return sb.toString();
	}
	
	private String getSelectOpt1(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
			if(k.equals(ff.getInputValue())){
				sb.append("<option value='"+k+"' selected>"+map.get(k).toString()+" </option>");
			}else{
				sb.append("<option  value='"+k+"'>"+map.get(k).toString()+" </option>");
			}
		}
		return sb.toString();
	}
	private String getSelectOpt2(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
			if(k.equals(ff.getInputValue())){
				sb.append(" <option value='"+k+"' selected>"+map.get(k).toString()+" </option>");
			}else{
				sb.append(" <option value='"+k+"'>"+map.get(k).toString()+"</option>");
			}
		}
		return sb.toString();
	}
	private String getSelectOpt3(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
			if(k.equals(ff.getInputValue())){
				sb.append("<option value='"+k+"' selected>"+map.get(k).toString()+"</option>");
			}else{
				sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
			}
		}
		return sb.toString();
	}
	/**
	 * 获取button属性2
	 * @return
	 */
	private String getButton(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><input type='button' id='' name='' value='按钮' hidden/></td>";
		}else{
			content = "<td><input type='button' maxlength='"+ff.getNumber()+"' id='' name='' value='按钮' class='"+className+"'/></td>";
		}
		return content;
	}
	/**
	 * 获取checkBox属性3
	 * @return
	 */
	private String getCheckbox(final FormFields ff){
		final StringBuilder sb = new StringBuilder();
		List<Map<String, Object>> listCheck = new ArrayList<Map<String,Object>>();
		listCheck = this.getlist(ff);
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
	//			content = "<input type='checkbox' id='"+nameField+"' 
			//name='"+nameField+"' value='"+valueField+"' hidden/>";
			sb.append("<td style='display:none'>");
			for(final Map<String, Object> map : listCheck){
				sb.append(this.getCheckboxInp1(map, ff));
			}
			sb.append("</td>");
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
//			content = titleField+":<input type='checkbox' id='"+nameField+"' 
			//name='"+nameField+"' value='"+valueField+"' disabled/>";
			sb.append("<td>");
			for(final Map<String, Object> map : listCheck){
				sb.append(this.getCheckboxInp2(map, ff));
			}
			sb.append("</td>");
		}else{
			sb.append("<td>");
			for(final Map<String, Object> map : listCheck){
				sb.append(this.getCheckboxInp3(map, ff));
			}
			sb.append("</td>");
		}
		return sb.toString();
	}
	private String getCheckboxInp1(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
			if(ff.getInputValue().contains(k)){
				sb.append("&nbsp; &nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' checked hidden/>"+map.get(k).toString());
			}else{
				sb.append("&nbsp; &nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' hidden/>"+map.get(k).toString());
			}
		}
		return sb.toString();
	}
	private String getCheckboxInp2(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		for(final String k : map.keySet()){
			if(ff.getInputValue().contains(k)){
				sb.append("&nbsp;&nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' checked disabled class='"+className+"'/> "+map.get(k).toString());
			}else{
				sb.append("&nbsp;&nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' disabled class='"+className+"'/>"+map.get(k).toString());
			}
		}
		return sb.toString();
	}
	private String getCheckboxInp3(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		for(final String k : map.keySet()){
			if(ff.getInputValue().contains(k)){
				sb.append("&nbsp;&nbsp;<input onchange='"+jsName+"' type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' checked class='"+className+"'/>"+map.get(k).toString());
			}else{
				sb.append("&nbsp;&nbsp;<input onchange='"+jsName+"' type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+k+"' class=' "+className+"'/>"+map.get(k).toString());
			}
		}
		return sb.toString();
	}
	/**
	 * 获取点选属性4
	 * @return
	 */
	private String getClick(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' disabled class='"+className+"'/></td>";
		}else{
			content = "<td><input onchange='"+jsName+"' type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"'"
					+ "name='"+ff.getFieldName()+HBCSoftConstant.CLICK+"' value='"+ff.getClickValue()+"' class='"+className+"' readonly/>"
					+ "<input type='hidden' id='"+ff.getFieldName()+ComConstant.inputName+ff.getFieldName()+"' value='"+ff.getInputValue()+"' class='hidden'/>"
					+ "<span class='input-group-addon' onclick='"+ff.getClickInfo()+"($(this))' style='cursor:pointer'><span class='glyphicon glyphicon-zoom-in'></span></span>";
					/*+ "</div>";*/
		}
		return content;
	}
	/**
	 * 获取日期属性5
	 * @return
	 */
	private String getDate(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' disabled/></td>";
		}else{
			content = "<td><input onchange='"+jsName+"' type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate'/></td>";
		}
		return content;
	}
	/**
	 * 获取textarea属性6
	 * @return
	 */
	private String getTextArea(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><textarea id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden>"+ff.getInputValue()+"</textarea></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><textarea id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled class='"+className+"'>"+ff.getInputValue()+"</textarea></td>";
		}else{
			if(ff.getIsRequired()==0){
				content = "<td><textarea onchange='"+jsName+"' maxlength='"+ff.getNumber()+"' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' class='"+className+"'>"+ff.getInputValue()+"</textarea></td>";
			}else{
				content = "<td><textarea required='required' maxlength='"+ff.getNumber()+"' onchange='"+jsName+"' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' class='"+className+"'>"+ff.getInputValue()+"</textarea></td>";
			}
		}
		return content;
	}
	/**
	 * 获取金额属性7
	 * @return
	 */
	private String getMoney(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+inputValue+ff.getInputValue()+"' readonly class='"+className+"' data-name='pc' data-type='pecent'/></td>";
		} else {
			content = "<td><input onchange='" + jsName + "' id='" + ff.getFieldName() + inputName + ff.getFieldName() + inputValue + ff.getInputValue()
					+ "' class='" + className + "' data-name='pc' data-type='pecent'";
			if (ff.getIsRequired() == 1) {
				content = content + " required='required'";
			}
			content = content + "type='tel' decimalDigits='"+ff.getDecimalDigits()+"' maxlength='" + ff.getNumber() + "' min='0' number='true'/></td>";
		}
		return content;
	}
	/**
	 * 获取下拉框和多选框的值
	 * @return
	 */
	private List<Map<String, Object>> getlist(final FormFields ff){
		String [] str = {};
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(ff.getSourceMode()==0){//固定值
			str = ff.getSourceContent().split("[;；]");
			for(int m=0; m<str.length; m++){
				addOptions(str[m], list);
			}
		}else if(ff.getSourceMode()==ComConstant.EDITTYPE_ADD){//sql
			final WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(
					((PageContext)this.getJspContext()).getServletContext());
			final CommonService cs = (CommonService)applicationContext.getBean("commonService");
			
			List<KeyValueEntity> lst = null;
			try {
				lst = cs.queryList(ff.getSourceContent());
			} catch (HbcsoftException e) {
				LOGGER.info(e);
			}
			
			lst.forEach(aa -> {
				addOptions(aa, list);
			});
		}
		return list;
	}
	/**
	 * 解决查询sql值重复问题
	 * @param aa
	 * @param list
	 */
	private void addOptions(final KeyValueEntity aa, final List<Map<String, Object>> list){
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa.getKey(), aa.getValue());
		list.add(map);
	}
	/**
	 * 解决查询sql值重复问题
	 * @param aa
	 * @param list
	 */
	private void addOptions(final String aa, final List<Map<String, Object>> list){
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa, aa);
		list.add(map);
	}
}
