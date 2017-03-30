package com.hbcsoft.table.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.common.CommonService;
import com.hbcsoft.common.KeyValueEntity;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.tag.TagJson;
import com.hbcsoft.zdy.common.ComConstant;
/**
 * tag.js方法调用，用来增行时显示
 * @author liang
 *
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController extends WebApplicationObjectSupport {
	/**
	 * 日志
	 */
	private final static Logger LOGGER = Logger.getLogger(TagController.class);
	/**
	 * 加载子表的<table></table>标签id
	 */
	private String tableId;
	/**
	 * 加载子表的<thead></thead>标签id
	 */
	private String theadId;
	/**
	 * 加载子表的<tbody></tbody>标签的id
	 */
	private String tbodyId;
	/**
	 * 
	 */
	private int inputType;
	/**
	 * 
	 */
	private int inputIsDisplay;
	/**
	 * 
	 */
	private int isModify;
	/**
	 * 
	 */
	private static String inputName = "' name='";
	/**
	 * 表头的集合
	 */
	private List<String> titleList = new ArrayList<String>();
	/**
	 * 属性的集合
	 */
	private List<List<FormFields>> fieldList = new ArrayList<List<FormFields>>();
	/**
	 * 仅仅获取一行的属性集合即可
	 */
	private List<FormFields> list = new ArrayList<FormFields>();
	
	/**
	 * tag.js中调用
	 * @param tag
	 * @return
	 * @throws HbcsoftException
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/addTr", method = RequestMethod.POST)
	public Object addTr(final String tag) throws HbcsoftException{
		
		final JSONObject jsonObject = new JSONObject();
		//String trString = "<tr><td>1</td><td>2</td><td>3</td>
		//<td>4</td><td>5</td><td onclick='$(this).parent(\"tr\").remove()'>
		//<button>删行</button></td></tr>";
		
		/************start**************/
		final JSONObject js = new JSONObject();
		final TagJson tj = js.parseObject(tag, TagJson.class);
		if(tj != null){
			titleList = tj.getTitleList();
//			fieldList = tj.getFieldList();
			list = tj.getList();
		}
		final StringBuilder sb = new StringBuilder(1000);
		FormFields ff = new FormFields();
//		List<FormFields> ffList = fieldList.get(0);
		final List<FormFields> ffList = list;
		sb.append("<tr>");
		for(int j=0; j<ffList.size(); j++){
			ff = ffList.get(j);
			sb.append(this.getSwitch(ff));
		}
		sb.append("<td><div class='btn-toolbar' role='toolbar'>"+
				"<div class='btn-group btn-group-sm'>"+
				"<button type='button' class='btn btn-default' onclick='$(this).parents(\"tr\").remove()'>删行</button></div>"+
				"</div></td></tr>");
		/************************************end******************************************/
		jsonObject.put("trString", sb.toString());
		return jsonObject;
	}
	/**
	 * switch方法提取
	 * @param ff
	 * @param sb
	 * @return
	 */
	private String getSwitch(final FormFields ff){
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
		default:
			sb.append("不存在该类型！");
			break;
		}
		return sb.toString();
	}
	/**
	 * 获取text框属性0
	 * @return
	 */
	private String getText(final FormFields ff){
		String content="";
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input  type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled/></td>";
		}else{
			content = "<td> <input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' /></td>";
		}
		return content;
	}
	/**
	 * 获取select属性1
	 * @return
	 */
	private String getSelect(final FormFields ff){
		final StringBuilder sb = new StringBuilder(200);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = this.getlist(ff);
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			sb.append("<td style='display:none'><select id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt1(map));
			}
			sb.append("</select></td>");
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			sb.append("<td><select id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt2(map));
			}
			sb.append("</select></td>");
		}else{
			sb.append("<td><select id='"+ff.getFieldName()+inputName+ff.getFieldName()+"'><option value='-1'>==请选择==</option>");
			for(final Map<String, Object> map : list){
				sb.append(this.getSelectOpt3(map));
			}
			sb.append("</select></td>");
		}
		return sb.toString();
	}
	/**
	 * getSelect方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getSelectOpt1(final Map<String, Object> map){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
//			if(k.equals(ff.getInputValue())){
//				sb.append("<option value='"+k+"' selected>"
			//+map.get(k).toString()+"</option>");
//			}else{
				sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
//			}
		}
		return sb.toString();
	}
	/**
	 * getSelect方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getSelectOpt2(final Map<String, Object> map){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
//			if(k.equals(ff.getInputValue())){
//				sb.append("<option value='"+k+"' selected>"
			//+map.get(k).toString()+"</option>");
//			}else{
				sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
//			}
		}
		return sb.toString();
	}
	/**
	 * getSelect方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getSelectOpt3(final Map<String, Object> map){
		final StringBuilder sb = new StringBuilder(50);
		for(final String k : map.keySet()){
//			if(k.equals(ff.getInputValue())){
//				sb.append("<option value='"+k+"' selected>"
			//+map.get(k).toString()+"</option>");
//			}else{
				sb.append("<option value='"+k+"'>"+map.get(k).toString()+"</option>");
//			}
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
			content = "<td><input type='button' id='' name='' value='按钮' /></td>";
		}
		return content;
	}
	/**
	 * 获取checkBox属性3
	 * @return
	 */
	private String getCheckbox(final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		List<Map<String, Object>> listCheck = new ArrayList<Map<String,Object>>();
		listCheck = this.getlist(ff);
		if(ff.getInputIsDisplay()==ComConstant.EDITTYPE_ADD){//不显示
			sb.append("<td style='display:none'>");
			for(final Map<String, Object> map : listCheck){
				sb.append(this.getCheckboxInp1(map, ff));
			}
			sb.append("</td>");
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
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
	/**
	 * getCheckbox方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getCheckboxInp1(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		for(final String k : map.keySet()){
//			if(ff.getInputValue().contains(k)){
//				sb.append("&nbsp;&nbsp;<input type='checkbox' 
			//id='"+ff.getFieldName()+"' name='"+ff.getFieldName()+"' 
			//value='"+k+"' checked hidden/>"+map.get(k).toString());
//			}else{
				sb.append("&nbsp;&nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' value='"+k+"' hidden/>"+map.get(k).toString());
//			}
		}
		return sb.toString();
	}
	/**
	 * getCheckbox方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getCheckboxInp2(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		for(final String k : map.keySet()){
//			if(ff.getInputValue().contains(k)){
//				sb.append("&nbsp;&nbsp;<input type='checkbox' 
			//id='"+ff.getFieldName()+"' name='"+ff.getFieldName()+"' 
			//value='"+k+"' checked disabled/>"+map.get(k).toString());
//			}else{
				sb.append("&nbsp;&nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' value='"+k+"' disabled/>"+map.get(k).toString());
//			}
		}
		return sb.toString();
	}
	/**
	 * getCheckbox方法提取
	 * @param map
	 * @param ff
	 * @return
	 */
	private String getCheckboxInp3(final Map<String, Object> map, final FormFields ff){
		final StringBuilder sb = new StringBuilder(100);
		for(final String k : map.keySet()){
//			if(ff.getInputValue().contains(k)){
//				sb.append("&nbsp;&nbsp;<input type='checkbox' 
			//id='"+ff.getFieldName()+"' name='"+ff.getFieldName()+"' 
			//value='"+k+"' checked/>"+map.get(k).toString());
//			}else{
				sb.append("&nbsp;&nbsp;<input type='checkbox' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' value='"+k+"' />"+map.get(k).toString());
//			}
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
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input  type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled/></td>";
		}else{
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' /><span><img src='../image/find.png' onclick='alert(123)' style='cursor:pointer'/></span></td>";
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
			content = "<td style='display:none'><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' hidden/></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate' disabled/></td>";
		}else{
			content = "<td><input type='text' id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})' class='Wdate'/></td>";
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
			content = "<td style='display:none'><textarea id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' hidden></textarea></td>";
		}else if(ff.getInputIsDisplay()==0 && ff.getIsModify()==1){//显示但不可编辑
			content = "<td><textarea id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' disabled></textarea></td>";
		}else{
			content = "<td><textarea id='"+ff.getFieldName()+inputName+ff.getFieldName()+"' ></textarea></td>";
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
			final WebApplicationContext applicationContext = this.getWebApplicationContext();
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
	private void addOptions(final KeyValueEntity aa, final List<Map<String, Object>> list){
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa.getKey(), aa.getValue());
		list.add(map);
	}
	
	private void addOptions(final String aa, final List<Map<String, Object>> list){
		final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(aa, aa);
		list.add(map);
	}
	/**
	 * 返回整个子表的id
	 * @return
	 */
	public String getTableId() {
		return tableId;
	}
	/**
	 * 设置整个子表的id
	 * @param tableId
	 */
	public void setTableId(final String tableId) {
		this.tableId = tableId;
	}
	/**
	 * 返回表中的头id
	 * @return
	 */
	public String getTheadId() {
		return theadId;
	}
	/**
	 * 设置子表的头id
	 * @param theadId
	 */
	public void setTheadId(final String theadId) {
		this.theadId = theadId;
	}
	/**
	 * 返回子表的体id
	 * @return
	 */
	public String getTbodyId() {
		return tbodyId;
	}
	/**
	 * 设置子表的体id
	 * @param tbodyId
	 */
	public void setTbodyId(final String tbodyId) {
		this.tbodyId = tbodyId;
	}
	/**
	 * 返回标签体类型
	 * @return
	 */
	public int getInputType() {
		return inputType;
	}
	/**
	 * 设置标签体类型
	 * @param inputType
	 */
	public void setInputType(final int inputType) {
		this.inputType = inputType;
	}
	/**
	 * 是否显示标签
	 * @return
	 */
	public int getInputIsDisplay() {
		return inputIsDisplay;
	}
	/**
	 * 设置是否显示标签
	 * @param inputIsDisplay
	 */
	public void setInputIsDisplay(final int inputIsDisplay) {
		this.inputIsDisplay = inputIsDisplay;
	}
	/**
	 * 是否可以编辑
	 * @return
	 */
	public int getIsModify() {
		return isModify;
	}
	/**
	 * 是否可以编辑
	 * @param isModify
	 */
	public void setIsModify(final int isModify) {
		this.isModify = isModify;
	}
	/**
	 * 表头的集合
	 * @return
	 */
	public List<String> getTitleList() {
		return titleList;
	}
	/**
	 * 设置子表表头集合
	 * @param titleList
	 */
	public void setTitleList(final List<String> titleList) {
		this.titleList = titleList;
	}
	/**
	 * 获取子表中所有的行及列数的集合
	 * @return
	 */
	public List<List<FormFields>> getFieldList() {
		return fieldList;
	}
	/**
	 * 设置子表中所有的行及列数的集合
	 * @param fieldList
	 */
	public void setFieldList(final List<List<FormFields>> fieldList) {
		this.fieldList = fieldList;
	}
	/**
	 * 获取一行的所有属性的集合
	 * @return
	 */
	public List<FormFields> getList() {
		return list;
	}
	/**
	 * 设置一行的所有属性的集合
	 * @param list
	 */
	public void setList(final List<FormFields> list) {
		this.list = list;
	}
}
