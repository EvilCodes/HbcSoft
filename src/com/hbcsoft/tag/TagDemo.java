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

import com.hbcsoft.common.CommonService;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.KeyValueEntity;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.ComConstant;

/**
 * @author sl
 * SimpleTagSupport类实现了SimpleTag接口，
 * TagDemo类继承SimpleTagSupport
 * 通过标签的属性控制标签体的执行次数
 */
public class TagDemo extends SimpleTagSupport {
	/**
	 * 日志
	 */
	private final static Logger LOGGER = Logger.getLogger(TagDemo.class);
	/**
	 * 常量1
	 */
	private static int changliang = 1;
	/**
	 * 1:text 2:select 3:button
	 */
	private transient int tagType;
	/**
	 * 获取name属性
	 */
	private transient String nameField;
	/**
	 * 获取title属性
	 */
	private transient String titleField;
	/**
	 * 获取value属性值
	 */
	private transient String valueField;
	/**
	 * 是否显示 0：显示 1：不显示
	 */
	private transient int inputIsDisplay;
	/**
	 * 字段长度
	 */
	private transient int number;
	
	/**
	 * 小数位数
	 */
	private transient int decimalDigits;
	/**
	 * 是否可修改
	 */
	private transient int isModify;
	/**
	 * 是否为录入必录项 0：否 1：是
	 */
	private transient int isRequired;
	/**
	 * 下拉框、多选框是否是固定值 0：固定值 1：sql
	 */
	private transient int sourceMode;
	/**
	 * 下拉框、多选框中的内容
	 */
	private transient String sourceContent;
	/**
	 * class名称
	 */
	private transient String className;
	/**
	 * 点选方法名
	 */
	private transient String clickInfo;
	/**
	 * 点选显示的值
	 */
	private transient String clickValue;
	/**
	 * 
	 */
	private transient List<EventCollection> eventList = new ArrayList<EventCollection>();
	/**
	 * @param clickInfo
	 */
	public void setClickInfo(final String clickInfo) {
		this.clickInfo = clickInfo;
	}
	/**
	 * @param clickValue
	 */
	public void setClickValue(final String clickValue) {
		this.clickValue = clickValue;
	}
	/**
	 * @param tagType
	 */
	public void setTagType(final int tagType) {
		this.tagType = tagType;
	}
	/**
	 * @param nameField
	 */
	public void setNameField(final String nameField) {
		this.nameField = nameField;
	}
	/**
	 * @param titleField
	 */
	public void setTitleField(final String titleField) {
		this.titleField = titleField;
	}
	/**
	 * @param valueField
	 */
	public void setValueField(final String valueField) {
		this.valueField = valueField;
	}
	/**
	 * @param inputIsDisplay
	 */
	public void setInputIsDisplay(final int inputIsDisplay) {
		this.inputIsDisplay = inputIsDisplay;
	}
	/**
	 * 设置字段长度
	 * @param number
	 */
	public void setNumber(final int number) {
		this.number = number;
	}
	/**
	 * 设置小数位数
	 * @param decimalDigits
	 */
	public void setDecimalDigits(final int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	/**
	 * @param isModify
	 */
	public void setIsModify(final int isModify) {
		this.isModify = isModify;
	}
	/**
	 * @param isRequired
	 */
	public void setIsRequired(final int isRequired) {
		this.isRequired = isRequired;
	}
	/**
	 * @param sourceMode
	 */
	public void setSourceMode(final int sourceMode) {
		this.sourceMode = sourceMode;
	}
	/**
	 * @param sourceContent
	 */
	public void setSourceContent(final String sourceContent) {
		this.sourceContent = sourceContent;
	}
	/**
	 * @param className
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	/**
	 * @param eventList
	 */
	public void setEventList(final List<EventCollection> eventList) {
		this.eventList = eventList;
	}
	/**
	 * 主表的样式
	 */
	@Override
	public void doTag() throws JspException, IOException {
		String content = "";
		switch(tagType){
		case 0: //文本框
			content = this.getText();
			break;
		case 1://下拉框
			content = this.getSelect();
			break;
		case 2: //按钮
			content = this.getButton();
			break;
		case 3: //多选按钮
			content = this.getCheckbox();
			break;
		case 4: //点选
			content = this.getClick();
			break;
		case 5://日期
			content = this.getDate();
			break;
		case 6://文本域
			content = this.getTextArea();
			break;
		case 7: //金额
			content = this.getMoney();//小数位数校验
			/*content = content+ "<script> var num = new FormatNumber();"
					+ "num.init({trigger:$('[data-type='pecent']'),decimal:2});"
					+ "</script>";*/
			break;
		default:
			content = "输入框类型不存在！";
			break;
		}
		final PageContext pageContext = (PageContext) this.getJspContext();
		this.getOut(pageContext).write(content);
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
	 * 获取事件集合
	 * @return
	 */
	private String getEvent(){
		final StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<eventList.size(); i++){
			final EventCollection ec = eventList.get(i);
			final String eventName = ec.getEventName();
			final String eventFunc = ec.getEventFun();
			sb.append(eventName + "=\"" + eventFunc +"\" ");
		}
		return sb.toString();
	}
	/**
	 * 获取下拉框和多选框的值
	 * @return
	 */
	private List<Map<String, Object>> getlist(){
		String [] str = {};
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(sourceMode==0){//固定值
			str = sourceContent.split("[;；]");
			for(int i=0; i<str.length; i++){
				addOptions(str[i],list);
			}
		}else if(sourceMode==changliang){//sql
			
			final WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(
					((PageContext)this.getJspContext()).getServletContext());
			final CommonService cs = (CommonService)applicationContext.getBean("commonService");
			
			List<KeyValueEntity> lst = null;
			try {
				lst = cs.queryList(sourceContent);
			} catch (HbcsoftException e) {
				LOGGER.info(e);
			}
			lst.forEach(aa -> {
				addOptions(aa, list);
			});
		}
		return list;
	}
	private void addOptions(final KeyValueEntity aa, final List<Map<String, Object>> list)
	{
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa.getKey(), aa.getValue());
		list.add(map);
	}
	
	private void addOptions(final String aa, final List<Map<String, Object>> list)
	{
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa, aa);
		list.add(map);
	}
	/**
	 * 获取text框属性0
	 * @return
	 */
	private String getText(){
		final String eventStr = this.getEvent();
		String content = "";
		if(inputIsDisplay==changliang){//不显示
			content =ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' hidden />";
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = "<span>"+titleField+":</span><input type='text' 
			//id='"+nameField+"' name='"+nameField+"' value='"+valueField+"' 
			//class='"+className+"' readonly/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' class= '"+className+"' readonly /></div>";
		}else{
			//content = "<span>"+titleField+":</span><input "+eventStr+" 
			//type='text' id='"+nameField+"' name='"+nameField+"' 
			//value='"+valueField+"' class='"+className+"'/>";
			
			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ " <input "+eventStr+"  type='text' maxlength='"+number+"' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' ";
					if(isRequired==changliang){
						content=content	+" required='required' ";
					}
			content=content
					+" class='"+className+"'/></div>";
		}
		return content;
	}
	/**
	 * 获取select属性1
	 * @return
	 */
	private String getSelect(){
		final String eventStr = this.getEvent();
		String content = "";
		final StringBuffer sb = new StringBuffer();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getlist();
		if(inputIsDisplay==changliang){//不显示
			content = "<select id='"+nameField+ComConstant.strName+nameField+"' hidden />";
			sb.append( this.getSelectOption(content, list));
			sb.append("</select>");
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = "<span>"+titleField+":</span><select id='"+nameField+"'
			//name='"+nameField+"' class='"+className+"' disabled/>";
			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<select id='"+nameField+ComConstant.strName+nameField+"'  class='"+className+"' disabled/>";
			
			sb.append(this.getSelectOption(content, list));
			sb.append("</select></div>");
		}else{
//			content = "<span>"+titleField+":</span><select "+eventStr+" 
			//id='"+nameField+"' name='"+nameField+"' class='"+className+"'/>";
			
			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<select "+eventStr+" id='"+nameField+ComConstant.strName+nameField+"'";
					if(isRequired==changliang){
						content=content	+" required='required' ";
					}
			content=content			
					+ " class= '"+className+"' />";
			
			sb.append(this.getSelectOption(content, list));
			sb.append("</select></div>");
		}
		return sb.toString();
	}
	/**
	 * getSelect方法提取
	 * @param content
	 * @param list
	 * @return
	 */
	private String getSelectOption(String content,final List<Map<String, Object>> list){
		content += "<option value='-1'>==请选择==</option>";
		for(final Map<String, Object> map : list){
			for(final String k : map.keySet()){
				if(k.equals(valueField)){
					content += "<option value='"+k+"' selected>"+map.get(k).toString()+"</option>";
				}else{
					content += "<option value='"+k+"'>"+map.get(k).toString()+"</option>";
				}
			}
		}
		return content;
	}
	/**
	 * 获取button属性2
	 * @return
	 */
	private String getButton(){
		final String eventStr = this.getEvent();
		String content="";
		if(inputIsDisplay==changliang){//不显示
			content = "<input type='button' id='' name='' value='按钮' hidden />";
		}else{
			content = " <input "+eventStr+" maxlength='"+number+"' type='button' id='' name='' value='按钮' class=' "+className+" '/>";
		}
		return content;
	}
	/**
	 * 获取checkBox属性3
	 * @return
	 */
	private String getCheckbox(){
		String content="";
		List<Map<String, Object>> listCheck = new ArrayList<Map<String,Object>>();
		listCheck = this.getlist();
		if(inputIsDisplay==changliang){//不显示
//			content = "<input type='checkbox' id='"+nameField+"'
			//name='"+nameField+"' value='"+valueField+"' hidden/>";
			content = this.getCheckbox1(content, listCheck);
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = titleField+":<input type='checkbox' 
			//id='"+nameField+"' name='"+nameField+"' 
			//value='"+valueField+"' disabled/>";
			content = this.getCheckbox2(content, listCheck);
		}else{
			content = this.getCheckbox3(content, listCheck);
		}
		return content;
	}
	/**
	 * getCheckbox方法提取
	 * @param content
	 * @param list
	 * @return
	 */
	private String getCheckbox1(String content,final List<Map<String, Object>> listCheck){
		for(final Map<String, Object> map : listCheck){
			for(final String k : map.keySet()){
				if(valueField.contains(k)){
					content += "&nbsp;&nbsp; <input type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' checked hidden/>"+map.get(k).toString();
				}else{
					content += "&nbsp;&nbsp;<input type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' hidden/>"+map.get(k).toString();
				}
			}
		}
		return content;
	}
	/**
	 * getCheckbox方法提取
	 * @param content
	 * @param list
	 * @return
	 */
	private String getCheckbox2(String content,final List<Map<String, Object>> listCheck){
		content = "<span>"+titleField+":</span>";
		for(final Map<String, Object> map : listCheck){
			for(final String k : map.keySet()){
				if(valueField.contains(k)){
					content += "&nbsp;&nbsp;<input type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' checked disabled class='"+className+"'/>"+map.get(k).toString();
				}else{
					content += "&nbsp;&nbsp;<input type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' disabled class='"+className+"'/>"+map.get(k).toString();
				}
			}
		}
		return content;
	}
	/**
	 * getCheckbox方法提取
	 * @param content
	 * @param list
	 * @return
	 */
	private String getCheckbox3(String content,final List<Map<String, Object>> listCheck){
		final String eventStr = this.getEvent();
		content = "<span>"+titleField+":</span>";
		for(final Map<String, Object> map : listCheck){
			for(final String k : map.keySet()){
				if(valueField.contains(k)){
					content += "&nbsp;&nbsp;<input "+eventStr+" type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' class= '"+className+"' checked/>"+map.get(k).toString();
				}else{
					content += "&nbsp;&nbsp;<input "+eventStr+" type='checkbox' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+k+"' class=' "+className+"'/>"+map.get(k).toString();
				}
			}
		}
		return content;
	}
	/**
	 * 获取点选属性4
	 * @return
	 */
	private String getClick(){
//		final String eventStr = this.getEvent();
		String content="";
		if(inputIsDisplay==changliang){//不显示
			content = ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' hidden/>";
		}else if(inputIsDisplay==0 && isModify==1){//显示但不可编辑
//			content = "<span>"+titleField+":</span>
			//<input type='text' id='"+nameField+"' name='"+nameField+"' 
			//value='"+valueField+"' class='"+className+"' disabled/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' class ='"+className+"' disabled/></div>";
		}else{
//			content = "<span>"+titleField+":</span><input "+eventStr+"
			//type='text' id='"+nameField+"' name='"+nameField+"' 
			//value='"+valueField+"' /><span><img src='../image/find.png' 
			//onclick='alert(123)' style='cursor:pointer'/></span>";
			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					//+ ComConstant.strDivClass
					+ "<div class='input-group col-lg-9 col-md-9  col-sm-8' style='padding-left: 15px;padding-right: 15px;'>"
					+ "<input type='text' name='"+nameField+HBCSoftConstant.CLICK+"' value='"+clickValue+"' class='"+className+"' readonly/>"
					+ "<input type='hidden' id='"+nameField+ComConstant.strName+nameField+"' value='"+valueField+"' class='hidden'/>"
					+ "<span class='input-group-addon' onclick='"+clickInfo+"($(this))' style='cursor:pointer'><span class='glyphicon glyphicon-zoom-in'></span></span>"
					+ "</div>";
		}
		return content;
	}
	/**
	 * 获取日期属性5
	 * @return
	 */
	private String getDate(){
		final String eventStr = this.getEvent();
		String content="";
		if(inputIsDisplay==changliang){//不显示
			content = ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' hidden/>";
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = "<span>"+titleField+":</span><input 
			//type='text' id='"+nameField+"' name='"+nameField+"'
			//value='"+valueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' 
			//class='Wdate' disabled/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' disabled/></div>";
		}else{
//			content = "<span>"+titleField+":</span><input "+
			//eventStr+" type='text' id='"+nameField+"' 
			//name='"+nameField+"' value='"+valueField+"' 
			//onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate'/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<input "+eventStr+" type='text' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' ";
					if(isRequired==changliang){
						content=content	+" required='required' ";
					}
			content=content		
					+ "class='Wdate'/></div>";
		}
		return content;
	}
	/**
	 * 获取textarea属性6
	 * @return
	 */
	private String getTextArea(){
		final String eventStr = this.getEvent();
		String content="";
		if(inputIsDisplay==changliang){//不显示
			content = "<textarea id='"+nameField+ComConstant.strName+nameField+"' hidden>"+valueField+"</textarea>";
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = "<span>"+titleField+":</span>
			//<textarea id='"+nameField+"' name='"+nameField+"' 
			//rows='2' cols='2' disabled>"+valueField+"</textarea>";
			
			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<textarea id='"+nameField+ComConstant.strName+nameField+"' rows='2' cols='2' disabled>"+valueField+"</textarea></div>";
		}else{
//			content = "<span>"+titleField+":</span><textarea "
			//+eventStr+" id='"+nameField+"' name='"+nameField+"'
			//rows='2' cols='2'>"+valueField+"</textarea>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<textarea "+eventStr+" maxlength='"+number+"' id='"+nameField+ComConstant.strName+nameField+"'";
					if(isRequired==changliang){
						content=content	+" required='required' ";
					}
			content=content			
					+ " rows='2' cols='2'>"+valueField+"</textarea></div>";
		}
		return content;
	}
	/**
	 * 获取金额属性7
	 * @return
	 */
	private String getMoney(){
		String content="";
		final String eventStr = this.getEvent();
		if(inputIsDisplay==changliang){//不显示
			content = ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' hidden/>";
		}else if(inputIsDisplay==0 && isModify==changliang){//显示但不可编辑
//			content = "<span>"+titleField+":</span><input 
			//type='text' id='"+nameField+"' name='"+nameField+"'
			//value='"+valueField+"' class='"+className+"' data-name='pc'
			//data-type='pecent' readonly/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ ComConstant.inputName+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' class='"+className+"' data-name='pc' data-type='pecent' readonly/></div>";
		}else{
//			content = "<span>"+titleField+":</span><input "
			//+eventStr+" type='text' id='"+nameField+"' 
			//name='"+nameField+"' value='"+valueField+"' 
			//class='"+className+"' data-name='pc' data-type='pecent'/>";

			content = ComConstant.strLabel
					+ titleField
					+ ComConstant.strEndLabel
					+ ComConstant.strDivClass
					+ "<input "+eventStr+" maxlength='"+number+"' type='tel' id='"+nameField+ComConstant.strName+nameField+ComConstant.strValue+valueField+"' ";
					if(isRequired==changliang){
						content=content	+" required='required'";
					}
			content=content			
					+ " class='"+className+"' decimalDigits='"+decimalDigits+"' number='true' min='0' data-name='pc' data-type='pecent'/></div>";
		}
		return content;
	}
}
