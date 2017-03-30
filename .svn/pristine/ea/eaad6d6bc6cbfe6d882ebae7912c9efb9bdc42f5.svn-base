package com.hbcsoft.excel.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.workflow.entity.WorkflowConfig;
import com.hbcsoft.zdy.common.HbcsoftCache;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * Excel模版数据校验工具类
 * @author gaodekui
 *
 */
public class ExcelValidateTool {

	/**
	 * 校验创建数据库表的有效性
	 * @param list
	 * @param clazz
	 * @param jt
	 * @return
	 * @throws HbcsoftException 
	 */
	public static String validateCreateTable(List <List> list,HbcsoftJT jt,HttpServletRequest request) throws HbcsoftException{
			
			StringBuffer message = new StringBuffer();
			//只取第一个TableName对象，将剩余TableName对象删除
			for (int i = 0; i < list.size();i++){
				List tempList = list.get(i);
				if (tempList.size() == 0)
					continue;
				if (tempList.get(0).getClass().getSimpleName().equals("TableName")){
					for (int j = 1; j < list.get(i).size();j++)
						list.get(i).remove(j);
				}
			}
			
			for (int i = 0; i < list.size(); i++){
				List tempList = list.get(i);
				if (tempList.size() == 0)
					continue;
				for (int j = 0; j < tempList.size();j++){
					Object obj = tempList.get(j);
					if (obj == null)
						continue;
					String name = obj.getClass().getSimpleName();
					//校验TableName对象的有效性
					if (name.equals("TableNameClass")){
						TableNameClass tableName = (TableNameClass)tempList.get(0);
						
						//校验数据是否为空
						if (tableName.getTableName()== null)
							message.append("数据库表名不能为空,");
						else if (tableName.getIsMainTable() == null)
							message.append("表结构不能为空");
						if (message.length() >0)
							return message.toString();
						
						//校验数据的关联性
						if (tableName.getIsMainTable().equals("子表")){
							if (tableName.getMainName() == null){
								message.append("必须为子表选取关联");
								return message.toString();
							}else{
								String sql = HbcsoftCache.getSqlValue("excel_validateCreateTable1");//sql语句配置在sqlConfig.xml中
								Object [] param = {tableName.getMainName()};
								TableNameClass table = new TableNameClass();
								jt.query(sql.toString(),param,(RowCallbackHandler)table);
								if (table.getTableName() != null){
									((TableNameClass)list.get(i).get(j)).setMainId(table.getId());
								}else{
									message.append("输入的主表名称不存在");
									return message.toString();
								}
								
							}
							
						}
						String sql = HbcsoftCache.getSqlValue("excel_validateCreateTable1");
						SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(HBCSoftConstant.SESSIONINFO);
						final String jianCheng = sessionInfo.getCompany().getCompanyNameHk().trim().toUpperCase();
						Object [] param = {jianCheng+"_"+tableName.getTableName()};
						TableNameClass table = new TableNameClass();
						jt.query(sql.toString(),param,(RowCallbackHandler)table);
						if (table.getTableName() != null){
							message.append("数据库表名已经存在请更换");
							return message.toString();
						}
							
					}else if (name.equals("TableEntity")){
						//校验数据是否为空
						TableEntity entity = (TableEntity) obj;
						//如果TableEntity对象的name属性和type属性同时为空，此TableEntity无效
						if (entity.getName() == null && entity.getType() == null){
							list.get(i).set(j,null);
							continue;
						}else{
							
							if (entity.getName() == null || entity.getTitle() == null ){
								message.append("字段名称或字段标题为空");
								return message.toString();
							}
							if (entity.getNumber() < 0){
								message.append("字段长度不能小于零");
								return message.toString();
							}else if (entity.getDecimalDigits() < 0){
								message.append("字段小数点位数不能小于零");
								return message.toString();
							}
						}
						
						
						//校验当字段类型为日期或时间日期，默认值类型是否正确
						try {
							if (entity.getType().equals("日期") ){
								if (entity.getDefaultValue() != null){
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
									Date date = format.parse(entity.getDefaultValue());
									((TableEntity)list.get(i).get(j)).setDefaultValue(format.format(date));
								}
								//return message.toString();
							}else if (entity.getType().equals("时间日期")){
								if (entity.getDefaultValue() != null){
									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									format.parse(entity.getDefaultValue());
								}
								//return message.toString();
							}
						} catch (ParseException e) {
							message.append("字段属性中存在默认值处填写的时间日期类型或日期类型不正确问题");
							return message.toString();
						}
						
						
						//校验数据名称是否重复
						 List<TableEntity> temp = new <TableEntity>ArrayList(tempList);
						 temp.remove(j);
						for(int k = 0; k < temp.size(); k++){
							TableEntity tableentity = (TableEntity) temp.get(k);
								if (entity.getName().equals(tableentity.getName())
									|| entity.getTitle().equals(tableentity.getTitle())){
									message.append("字段名称或字段标题出现重复");
									return message.toString();
								}
							
						}
					}
				}
			}
			
			
		return "";
	}
	
	/**
	 * 校验创建表单数据的有效性
	 * @param list
	 * @param clazz
	 * @param jt
	 * @return
	 */
	public static String validateCreateFrom(List <List> list, HbcsoftJT jt) {
		
		String className;
		StringBuffer message = new StringBuffer(); 
		//只取第一个FormName对象，将剩余FormName对象删除
		for (int i = 0; i < list.size();i++){
			List tempList = list.get(i);
			if (tempList.size() == 0)
				continue;
			if (tempList.get(0).getClass().getSimpleName().equals("FormName")){
				for (int j = 1; j < list.get(i).size();j++)
					list.get(i).remove(j);
			}
		}
		
		for (int i = 0; i < list.size(); i++){
			List tempList = list.get(i);
			if (tempList.size() ==0)
				continue;
			for (int j = 0; j < tempList.size();j++){
				Object obj = tempList.get(j);
				className = obj.getClass().getSimpleName();
				
				if (className.equals("FormName")){
					FormName formName = (FormName) tempList.get(0);
					if (formName.getFormNamef() == null){
						message.append("表单名称不能为空,");
					}else if (formName.getMethod() == null){
						message.append("提交方式不能为空,");
					}else if (formName.getFormType() == null){
						message.append("表单类型不能为空");
					}
					if (message.length() > 0)
						return message.toString();
					//校验表单类型是否存在
					String sqlFromName = HbcsoftCache.getSqlValue("excel_validateCreateFromSecond");
					Object[] param = {formName.getFormType()};
					FormName sqlformName = new FormName();
					jt.query(sqlFromName, param,(RowCallbackHandler)sqlformName);
						if (sqlformName.getId() != null){
							return "表单类型已存在，请修改";
						}
					
				}else if (className.equals("FormFields")){
						final FormFields formFields = (FormFields) obj;
						
						//数据非空校验
						if (formFields.getTableName() == null){
							message.append("表名不能为空,");
						}else if (formFields.getFieldName() == null){
							message.append("字段名称不能为空,");
						}if (formFields.getSort() == 0){
							message.append("字段排列顺序不能为零,");
						}else if(formFields.getTitle() == null){
							message.append("字段标题不能为空");
						}
						
						if (message.length() > 0)
							return message.toString();
						
						//数据重复性校验
						List<FormFields> tempFormFields = new ArrayList<FormFields>(tempList);
						tempFormFields.remove(j);
						for (int k = 0; k < tempFormFields.size(); k++){
							if (formFields.getSort() == tempFormFields.get(k).getSort()){
								return "字段排列顺序中出现重复数据，请修改";
							}
						}
						//校验当字段类型为下拉框时数据的有效性
						 if (formFields.getInputType() == 1){
							 
						 }else{
							 if (formFields.getSourceMode() != 0)
								 return "当字段类型不为下拉框时下拉框状态必须为固定值";
							 else if (formFields.getSourceContent() != null)
								 return "当字段类型不为下拉框时内容为空";
						 }
						//数据有效性校验
						TableEntity entity = new TableEntity();
						String sql = HbcsoftCache.getSqlValue("excel_validateCreateFrom");
						Object[] param = {formFields.getTableName(),formFields.getFieldName()};
						jt.query(sql, param,(RowCallbackHandler)entity);
						if (entity.getId() == null){
							return "输入的表名或字段名称无效";
						}else{
							((FormFields)list.get(i).get(j)).setTableId(entity.getMainId());//获取数据库表的id
							((FormFields)list.get(i).get(j)).setFieldId(entity.getId());//获取字段id
							//设置表单字段类型
							if(entity.getType()=="COLUMN_TYPE_NUMERIC" || entity.getType().equals("COLUMN_TYPE_NUMERIC")){
								((FormFields)list.get(i).get(j)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_7);
							}else if(entity.getType() == "COLUMN_TYPE_DATE" || entity.getType().equals("COLUMN_TYPE_DATE")){
								((FormFields)list.get(i).get(j)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_5);
							}else{
								((FormFields)list.get(i).get(j)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_0);
							
							}
						}
				}
			}
		}
		
		return "";
	}
	
	/**
	 * 校验创建数据库表和表单的有效性
	 * @param list
	 * @param clazz
	 * @param jt
	 * @param request
	 * @return
	 * @throws HbcsoftException 
	 */
	public static String vaildateCreateTableAndFrom(List <List> list,
			HbcsoftJT jt, HttpServletRequest request) throws HbcsoftException {
		String message = "";

		List tempEntity1 = new ArrayList();//存放TableName对象
		List tempEntity2 = new ArrayList();//存放TableEntity对象
		List <List>tempList1 = new ArrayList<List>();//存放tempEntity1和tempEntity2集合
		
		List tempEntity3 = new ArrayList();//存放FormName对象
		List tempEntity4 = new ArrayList();//存放FormFields对象
		List tempEntity5 = new ArrayList();//存放已存在表关联的表单字段FormFields对象
		List currEntity = new ArrayList();//存放当次创建表关联的表单字段FormFields对象
		List<List>tempList2 = new ArrayList<List>();//存放tempEntity3和tempEntity5集合
		
		for (int i = 0; i < list.size();i++){
			List temp = list.get(i);
			for (Object obj:temp){
				String className = obj.getClass().getSimpleName();
				if (className.equals("TableNameClass"))
					tempEntity1.add(obj);
				else if (className.equals("TableEntity"))
					tempEntity2.add(obj);
				else if (className.equals("FormName"))
					tempEntity3.add(obj);
				else if (className.equals("FormFields"))
					tempEntity4.add(obj);
			}
		}
		tempList1.add(tempEntity1);
		tempList1.add(tempEntity2);
		tempList2.add(tempEntity3);
		
		//校验TableName和TableEntity(tempList1)
		message = validateCreateTable(tempList1, jt, request);
		if (!message.isEmpty())
			return message;
		
		//校验FormName和已存在表关联的表单字段FormFields对象(tempList2)
		for(Object obj:tempEntity4){
			FormFields field = (FormFields)obj;
			if (field.getBeiYong() == 0){
				currEntity.add(field);
			}else{
				tempEntity5.add(field);
			}
		}
		tempList2.add(tempEntity5);
		message = validateCreateFrom(tempList2, jt);
		if (!message.isEmpty())
			return message;
		
		//校验当次创建表关联的表单字段FormFields对象(currEntity)
		if (currEntity.size() > 0){
		for (int m = 0; m < currEntity.size(); m++){
			String tabname = ((FormFields) currEntity.get(m)).getTableName();
			String fieldname = ((FormFields) currEntity.get(m)).getFieldName();
			TableNameClass tableName = (TableNameClass) tempEntity1.get(0);
			if (tableName.getTableName().equals(tabname)){
				((TableNameClass)tempEntity1.get(0)).setId(SequenceUtil.getTableId("t_tablename"));
				for(int j = 0; j < tempEntity2.size();j++){
					if (tempEntity2.get(j) == null)
						continue;
					((TableEntity) tempEntity2.get(j)).setId(SequenceUtil.getTableId("t_tableentity"));
					((TableEntity) tempEntity2.get(j)).setMainId(((TableNameClass)tempEntity1.get(0)).getId());
					TableEntity tableEntity = (TableEntity) tempEntity2.get(j);
					if (!fieldname.equals(tableEntity.getName())){
						message = "输入的字段名称在你创建的表字段中不存在";
						return message;
					}else{
						
						//数据重复性校验
						List<FormFields> tempFormFields = new ArrayList<FormFields>(currEntity);
						tempFormFields.remove(m);
						for (int o = 0; o < tempFormFields.size(); j++){
							if (((FormFields)currEntity.get(m)).getSort() == tempFormFields.get(o).getSort())
								return "字段排列顺序中出现重复数据，请修改";
							if (((FormFields) currEntity.get(m)).getFieldName() == tempFormFields.get(o).getFieldName())
								return "字段名称中出现重复，请修改";
							
						}
						//校验当字段类型为下拉框时数据的有效性
						 if (((FormFields)currEntity.get(m)).getInputType() == 1){
							 
						 }else{
							 if (((FormFields)currEntity.get(m)).getSourceMode() != 0)
								 return "当字段类型不为下拉框时下拉框状态必须为固定值";
							 else if (((FormFields)currEntity.get(m)).getSourceContent()!= null)
								 return "当字段类型不为下拉框时内容为空";
						 }
						((FormFields)currEntity.get(m)).setTableId(((TableEntity) tempEntity2.get(j)).getMainId());//获取数据库表的id
						((FormFields)currEntity.get(m)).setFieldId(((TableEntity) tempEntity2.get(j)).getId());//获取字段id
						//设置表单字段类型
						switch (((TableEntity) tempEntity2.get(j)).getType()) {
						case "文本":
							((TableEntity) tempEntity2.get(j)).setType("COLUMN_TYPE_TEXT");
							break;
						case "整数":
							((TableEntity) tempEntity2.get(j)).setType("COLUMN_TYPE_INT");
							break;
						case "小数":
							((TableEntity) tempEntity2.get(j)).setType("COLUMN_TYPE_NUMERIC");
							break;
						case "日期":
							((TableEntity) tempEntity2.get(j)).setType("COLUMN_TYPE_DATE");
							break;
						case "时间日期":
							((TableEntity) tempEntity2.get(j)).setType("COLUMN_TYPE_TIMESTAMP");
							break;
						default: break;
						}
						if(((TableEntity) tempEntity2.get(j)).getType()=="COLUMN_TYPE_NUMERIC" || ((TableEntity) tempEntity2.get(j)).getType().equals("COLUMN_TYPE_NUMERIC")){
							((FormFields)currEntity.get(m)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_7);
						}else if(((TableEntity) tempEntity2.get(j)).getType() == "COLUMN_TYPE_DATE" ||((TableEntity) tempEntity2.get(j)).getType().equals("COLUMN_TYPE_DATE")){
							((FormFields)currEntity.get(m)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_5);
						}else{
							((FormFields)currEntity.get(m)).setInputType(HBCSoftConstant.FORM_INPUT_TYPE_0);
						
						}
					}
				}
			}else{
				message = "输入的表名在你创建的数据库表名不一样";
				return message;
			}
		}
	}
		if (tempEntity5.size()>1){
			
			for (int i = 0; i < tempList2.size();i++){
				List temp = tempList2.get(i);
				if (temp.get(0).getClass().getSimpleName().equals("FormFields"))
					tempList2.get(i).addAll(currEntity);
				
			}
		}else{
			tempList2.add(currEntity);
		}
		list.clear();
		
		for(List l:tempList1){
			list.add(l);
		}
		for(List l:tempList2){
			list.add(l);
		}
		return message;
	}
	
	/**
	 * 校验新增第三方数据库配置数据有效性
	 * @param list
	 * @param clazz
	 * @param jt
	 * @return
	 */
	public static String validateDatabaseConfiguration(List list, Class clazz,HbcsoftJT jt){
		final StringBuffer message = new StringBuffer(42);
		for (int i = 0; i < list.size();i++){
			final OuterDBLinkPara dbLinkPara = (OuterDBLinkPara)list.get(i);
				String dbip;
				dbip = dbLinkPara.getDbIp();
			//数据是否为空校验
			if(dbip.isEmpty()){
				message.append(" 数据库IP不能为空,");
			}else if(dbLinkPara.getDbsId() == null){
				message.append(" 数据库名称不能为空,");
			}else if (dbLinkPara.getDbUser() == null){
				message.append(" 数据库登录用户名不能为空,");
			}else if (dbLinkPara.getDbPass() == null){
				message.append(" 数据库密码不能为空");
			}
			if (message.length() > 0){
				return message.toString();
			}
			String sql;
			sql = HbcsoftCache.getSqlValue("excel_validateDatabaseConfiguration");
			Object [] param = {dbLinkPara.getDbIp(),dbLinkPara.getDbsId(),dbLinkPara.getDbType()};
			OuterDBLinkPara dbLinkParaTemp = new OuterDBLinkPara();
			jt.query(sql,param,(RowCallbackHandler)dbLinkParaTemp);
			if (dbLinkParaTemp.getDbIp()!= null){
				message.append("IP地址"+dbLinkPara.getDbIp()+"下已配置名为"+dbLinkPara.getDbsId()+"的同类数据库！");
				return message.toString();
			}
			List<OuterDBLinkPara>tempList = new ArrayList<OuterDBLinkPara>(list);
			tempList.remove(i);
			for (int j = 0; j < tempList.size(); j++){
				if (dbLinkPara.getCompanyName().equals(tempList.get(j).getCompanyName())){
					message.append("公司名称【"+dbLinkPara.getCompanyName()+"】已经存在，请修改！");
					return message.toString();
				}
			}
		}
		
		return "";
	}
	/**
	 * 校验工作流基础配置
	 * @param list
	 * @param clazz
	 * @param jt
	 * @return
	 */
	public static String validateWorkFlowBaseConfigure(List list, Class clazz,
			HbcsoftJT jt) {
		StringBuffer message = new StringBuffer();
		WorkflowConfig config = (WorkflowConfig)list.get(0);
		//字段非空校验
		if (config.getTableName() == null){
			message.append("表中文名不能为空,");
		}else if (config.getTableId() == null){
			message.append("表英文名不能为空,");
		}else if (config.getColumnName() == null){
			message.append("字段中文名不能为空,");
		}else if (config.getColumnId() == null){
			message.append("字段英文名不能为空");
		}
		if (message.length() > 0)
			return message.toString();
		//字段格式校验
		final Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{1,21}$");
		Matcher matcher= pattern.matcher(config.getTableId());
		while(!matcher.find()){
			message.append("输入的表英文名格式不对，以英文字母开头,只能包含英文字母、数字、下划线且长度不能超过22");
			return message.toString();
		}
		matcher = pattern.matcher(config.getColumnId());
		while (!matcher.find()){
			message.append("输入的字段英文名格式不对，以英文字母开头,只能包含英文字母、数字、下划线且长度不能超过22");
			return message.toString();
		}
		return "";
	}
	
}
