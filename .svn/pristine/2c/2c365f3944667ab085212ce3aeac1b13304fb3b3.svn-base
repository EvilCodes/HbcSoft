package com.hbcsoft.excel.common;

import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.sys.entity.OuterDBLinkPara;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.workflow.entity.WorkflowConfig;

/**
 * Excel模版类名称筛选
 * @author gaodekui
 *
 */
public enum ExcelStencil {
	CREATETABLEENTITY("创建数据库表",TableNameClass.class,TableEntity.class),
	CREATEFROM("创建表单",FormName.class,FormFields.class),
	CREATETABLEANDFROM("合并",TableNameClass.class,TableEntity.class,FormName.class,FormFields.class),
	DATABASECONFIGURATION("新增第三方数据库配置",OuterDBLinkPara.class),
	WORKFLOWBASECONFIGURE("工作流基本配置",WorkflowConfig.class);
	/**
	 * 枚举类型描述
	 */
	private  String desc; 
	/**
	 * 枚举类型涉及中的类型
	 */
	private Class[] clazz;
	
	private ExcelStencil(final String desc,final Class...clazz){ 
		this.desc=desc; 
		this.clazz = clazz;
		
	}
	/**
	 * getter方法
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * setter方法
	 * @param desc
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}
	/**
	 * getter方法
	 * @return
	 */
	public Class[] getClazz() {
		return clazz.clone();
	}
	/**
	 * setter
	 * @param clazz
	 */
	public void setClazz( final Class...clazz) {
		this.clazz = clazz;
	}
 
	
	
}
