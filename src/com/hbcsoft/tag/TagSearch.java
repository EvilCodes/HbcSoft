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
 * TagSearch类继承SimpleTagSupport
 * 通过标签的属性控制标签体的执行次数
 */
public class TagSearch extends SimpleTagSupport {
	/**
	 * 日志
	 */
	private final static Logger LOGGER = Logger.getLogger(TagSearch.class);
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
	 * 获取startvalue属性值
	 */
	private transient String startValueField;
	/**
	 * 获取value属性值
	 */
	private transient String endValueField;
	/**
	 * 是否在查询列显示 0：否 1：是
	 */
	private transient int inputIsDisplay;
	/**
	 * 是否为查询必录项 0：否 1：是
	 */
	private transient int queryisRequired;
	/**
	 * css样式
	 */
	private transient String className;
	/**
	 * 下拉框、多选框是否是固定值 0：固定值 1：sql
	 */
	private transient int sourceMode;
	/**
	 * 下拉框、多选框中的内容
	 */
	private transient String sourceContent;
	/**
	 * 点选方法名
	 */
	private transient String clickInfo;
	/**
	 * 点选显示的值
	 */
	private transient String clickValue;
	/**
	 * 小数位数
	 */
	private transient int decimalDigits;
	/**
	 * 字段长度
	 */
	private transient int number;
	/**
	 * PMD
	 */
	private static String inputText = "<input type='text' id='";
	/**
	 * 
	 */
	private static String inputName = "' name='";
	/**
	 * 
	 */
	private static String divClassName = "<label for='drive' class='control-label col-lg-3 col-md-3 col-sm-3'>";
	/**
	 * html标签类型
	 * @param tagType
	 */
	public void setTagType(final int tagType) {
		this.tagType = tagType;
	}
	/**
	 * 标签名
	 * @param nameField
	 */
	public void setNameField(final String nameField) {
		this.nameField = nameField;
	}
	/**
	 * 标题
	 * @param titleField
	 */
	public void setTitleField(final String titleField) {
		this.titleField = titleField;
	}
	/**
	 * 开始值
	 * @param valueField
	 */
	public void setValueField(final String valueField) {
		this.valueField = valueField;
	}
	/**
	 * 截止值
	 * @param endValueField
	 */
	public void setEndValueField(final String endValueField) {
		this.endValueField = endValueField;
	}
	/**
	 * 是否显示
	 * @param inputIsDisplay
	 */
	public void setInputIsDisplay(final int inputIsDisplay) {
		this.inputIsDisplay = inputIsDisplay;
	}
	/**
	 * 是否为查询必录项 0：否 1：是
	 * @param queryisRequired
	 */
	public void setQueryisRequired(final int queryisRequired) {
		this.queryisRequired = queryisRequired;
	}
	/**
	 * 样式名称
	 * @param className
	 */
	public void setClassName(final String className) {
		this.className = className;
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
	 * @param startValueField
	 */
	public void setStartValueField(final String startValueField) {
		this.startValueField = startValueField;
	}
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
	 * 标签显示方法
	 */
	@Override
	public void doTag() throws JspException, IOException {
		String content = "";
		switch(tagType){
		case 0: //文本框
			content = this.getText();
			break;
		case 1://下拉框多选框
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
		case 7://金额
			content = this.getMoney();
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
	 * 获取text框属性0
	 * @return
	 */
	private String getText(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = inputText+nameField+inputName+nameField+"'  value ='"+valueField+"' hidden />";
		}else{
			content = divClassName
					+ titleField
					+ " </label>"
					+ "<div class='col-lg-9  col-md-9 col-sm-8'>"
					+ "<input  type='text' id='"+nameField+inputName+nameField+"'  value='"+valueField+"' ";
					if(queryisRequired==1){
						content=content	+" required='required' maxlength='"+number+"' ";
					}
			content=content			
					+ " class='"+className+"' />"
					+ " </div>";
		}
		return content;
	}
	/**
	 * 获取select属性1
	 * @return
	 */
	private String getSelect(){
		final StringBuilder sb = new StringBuilder(200);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getlist();
		if(inputIsDisplay==0){//不显示
			sb.append("<select id='"+nameField+inputName+nameField+"' multiple='multiple' hidden/><option value='-1' selected>==请选择==</option>");
			for(final Map<String, Object> map : list){
				for(final String k : map.keySet()){
					sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
				}
			}
			sb.append("</select>");
		}else{
			//sb.append(titleField+":<select  class='"+className+"' 
			//id='"+nameField+inputName+nameField+"' multiple='multiple'/>");
			sb.append(divClassName+titleField+"</label><div  class='col-lg-9 col-md-9 col-sm-8'><select  class='"+className+"' id='"+nameField+inputName+nameField+"' multiple='multiple'/><option value='-1' selected>==请选择==</option>");
			for(final Map<String, Object> map : list){
				for(final String k : map.keySet()){
					sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
				}
			}
			sb.append("</select></div>");
		}
		return sb.toString();
	}
	/**
	 * 获取button属性2
	 * @return
	 */
	private String getButton(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = "<input type='button' id='' name='' value='按钮' hidden />";
		}else{
			content = "<input type='button' id='' name='' value='按钮' class='"+className+"' />";
		}
		return content;
	}
	/**
	 * 获取checkBox属性3
	 * @return
	 */
	private String getCheckbox(){
		final StringBuilder sb = new StringBuilder(200);
		sb.append(divClassName+titleField+"</label>");
		List<Map<String, Object>> listCheck = new ArrayList<Map<String,Object>>();
		listCheck = this.getlist();
		if(inputIsDisplay==0){//不显示
			for(final Map<String, Object> map : listCheck){
				for(final String k : map.keySet()){
					sb.append("<input type='checkbox' id='"+nameField+inputName+nameField+"'  value='"+k+"' hidden />"+map.get(k).toString());
				}
			}
		}else{
			sb.append("<div  class='col-lg-9 col-md-9 col-sm-8'>");
			for(final Map<String, Object> map : listCheck){
				for(final String k : map.keySet()){
					sb.append("<label class='checkbox-inline' style='padding-left:20px'>");
					sb.append("<input type='checkbox' id='"+nameField+inputName+nameField+"'  value='"+k+"'/>"+map.get(k).toString());
					sb.append("</label>");
				}
			}
			sb.append("</div>");
		}
		return sb.toString();
	}
	/**
	 * 获取点选属性4
	 * @return
	 */
	private String getClick(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = inputText+nameField+inputName+nameField+"' value='"+valueField+"' hidden/>";
		}else{
			content = divClassName
					+ titleField
					+ " </label>"
					+ "<div class='input-group col-lg-9 col-md-9  col-sm-8' style='padding-left: 15px;padding-right: 15px;'>"
					+ "<input type='text' name='"+nameField+HBCSoftConstant.CLICK+"' value='"+clickValue+"' class='"+className+"' readonly/>"
					+ "<input type='hidden' id='"+nameField+inputName+nameField+"' value='"+valueField+"' class='hidden'/>"
					+ "<span class='input-group-addon' onclick='"+clickInfo+"($(this))' style='cursor:pointer'><span class='glyphicon glyphicon-zoom-in'></span></span>"
					+ "</div> ";
			//content = titleField+":<input type='text' id='"+nameField+inputName+
			//nameField+"' value='"+valueField+"' class='"+className+"'/><span>
			//<img src='../image/find.png' onclick='alert(123)' 
			//style='cursor:pointer'/></span>";
		}
		return content;
	}
	/**
	 * 获取日期属性5
	 * @return
	 */
	private String getDate(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = inputText+nameField+"Start'  name='"+nameField+"Start'  value='"+startValueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' hidden/>"
					+inputText+nameField+"End'  name='"+nameField+"End'  value='"+endValueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' hidden/>";
		}else{
			content = divClassName
					+ titleField
					+ "</label>"
					+ "<div class='col-lg-9 col-md-9 col-sm-8'>"
					+ "<div class='input-group'>"
					+ inputText+nameField+"Start' name='"+nameField+"Start' value='"+startValueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' ";
					if(queryisRequired==1){
						content=content	+" required='required' maxlength='"+number+"' ";
					}
			content=content			
					+ "class='"+className+" Wdate'/>"
					+ "<span class='input-group-addon qujian'>至</span>"
					+ inputText+nameField+"End' name='"+nameField+"End' value='"+endValueField+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' ";
					if(queryisRequired==1){
						content=content	+" required='required' maxlength='"+number+"' ";
					}
			content=content			
					+ "class='"+className+" Wdate'/>"
					+ "</div></div>";
			//content = titleField+":<input type='text' id='"+nameField+"Start' 
			//name='"+nameField+"Start' value='"+startValueField+"' 
			//onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate'/>"
			//		+"至<input type='text' id='"+nameField+"End' 
			//name='"+nameField+"End' value='"+endValueField+"' 
			//onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate'/>";
		}
		return content;
	}
	/**
	 * 获取textarea属性6
	 * @return
	 */
	private String getTextArea(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = "<textarea id='"+nameField+inputName+nameField+"' hidden>"+valueField+"</textarea>";
		}else{
			content = divClassName
					+ titleField
					+ "</label>"
					+ "<div class='col-lg-9 col-md-9 col-sm-8'>"
					+ "<textarea id='"+nameField+inputName+nameField+"' rows='2' cols='2'";
					if(queryisRequired==1){
						content=content	+" required='required' maxlength='"+number+"' ";
					}
			content=content			
					+ " class='"+className+"'>"+valueField+"</textarea>"
					+ "</div>";
			//content = titleField+":<textarea id='"+nameField+inputName
			//+nameField+"' rows='2' cols='2' class='"+className+"'>"
			//+valueField+"</textarea>";
		}
		return content;
	}
	/**
	 * 获取金额属性7
	 * @return
	 */
	private String getMoney(){
		String content="";
		if(inputIsDisplay==0){//不显示
			content = inputText+nameField+"Start' name='"+nameField+"Start' value='"+startValueField+"' hidden/>"
					+inputText+nameField+"End' name='"+nameField+"End' value='"+endValueField+"' hidden/>";
		}else{
			content = divClassName
					+ titleField
					+ "</label>"
					+ "<div class='col-lg-9 col-md-9 col-sm-8'>"
					+ "<div class='input-group'>"
					+ inputText+nameField+"Start' name='"+nameField+"Start' value='"+startValueField+"'";
					if(queryisRequired==1){
						content=content	+" required='required' ";
					}
			content=content	
					+ " class='"+className+"' decimalDigits='"+decimalDigits+"' type='tel' maxlength='"+number+"' number='true' min='0' />"
					+ "<span class='input-group-addon qujian'>至</span>"
					+ inputText+nameField+"End' name='"+nameField+"End' value='"+endValueField+"'";
			content=content			
					+ " class='"+className+"'/>"
					+ "</div></div>";
			//content = titleField+":<input type='text' id='"+nameField+"Start' 
			//name='"+nameField+"Start' value='"+startValueField+"' class='"+className+"'/>"
			//		+"至<input type='text' id='"+nameField+"End' 
			//name='"+nameField+"End' value='"+endValueField+"' class='"+className+"'/>";
		}
		return content;
	}
	/**
	 * 获取下拉框和多选框的值
	 * @return
	 */
	private List<Map<String, Object>> getlist(){
		String [] str = {};
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(sourceMode==0){//固定值
			str = sourceContent.split("[;；]");
			for(int i=0; i<str.length; i++){
				addOptions(str[i],list);
			}
		}else if(sourceMode==ComConstant.EDITTYPE_ADD){//sql
			final WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(
					((PageContext)this.getJspContext()).getServletContext());
			final CommonService cs = (CommonService)applicationContext.getBean("commonService");
			
			List<KeyValueEntity> lst = null;
			try {
				lst = cs.queryList(sourceContent);
			} catch (HbcsoftException e) {
//				e.printStackTrace();
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
}
