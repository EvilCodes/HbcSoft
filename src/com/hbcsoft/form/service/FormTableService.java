package com.hbcsoft.form.service;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.form.entity.FormName;
import com.hbcsoft.form.entity.FormTable;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;

/**
 * 表单Service层
 * @author zhanghaijiao
 *
 */
public interface FormTableService {
	/**
	 * 查询出所有的表名称
	 * @return
	 */
	List<FormName> queryForm(String[] qform, Long companyId);
	
	/**
	 * 分页查询
	 */
	List<FormName> queryAllForm(String[] qallform, Long companyId);
	
	/**
	 * 保存表单表名称数据
	 * @param formTable
	 * @return
	 * @throws HbcsoftException
	 */
	void addFormName(FormName formName) throws HbcsoftException;
	
	/**
	 * 保存表单字段表新增数据
	 * @param formField
	 * @throws HbcsoftException
	 */
	void addFormFields(List<FormFields> formField) throws HbcsoftException;
	
	/**
	 * 保存表单中间表数据
	 * @param formField
	 * @throws HbcsoftException
	 */
	void saveFormTable(List<FormTable> formTable) throws HbcsoftException;
	
	/**
	 * 根据formid和fieldsid查询表单字段表中信息
	 * @param formid
	 * @param fieldId
	 * @return
	 * @throws HbcsoftException
	 */
	FormFields selectFormFields(int[] intsff,String[] stringsff, Long companyId) throws HbcsoftException;
	
	/**
	 * 完善表单数据表中数据
	 * @param field
	 * @throws HbcsoftException
	 */
	void saveFormFields(List<FormFields> field) throws HbcsoftException;
	
	/**
	 * 根据id查找表单表数据
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	FormName selectIDForm(String id, Long companyId) throws HbcsoftException;
	
	/**
	 * 修改表单表数据
	 * @param formName
	 * @return
	 * @throws HbcsoftException
	 */
	void updateFormName(FormName formName) throws HbcsoftException;
	
	/**
	 * 根据id查找表单字段表数据
	 * @param id
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormFields> selectIDFormField(int flag1,int flag2,String id, Long companyId) throws HbcsoftException;
	
	/**
	 * 根据formid和tableid查找表单字段数据
	 * @param formid
	 * @param tableid
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormFields> selectLstFormField(String formid,String tableid, Long companyId) throws HbcsoftException;
	
	/**
	 * 判断数据库中是否有与录入的formType重复的数据
	 * @param formType
	 * @return
	 * @throws HbcsoftException
	 */
	FormName isTypeRepeat(String formType, Long companyId) throws HbcsoftException;
	
	/**
	 * 新增表单字段列表显示
	 * @param formid
	 * @param companyId
	 * @param flag
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormFields> queryFormFieShow(Long formid,Long companyId,int flag1,int flag2)throws HbcsoftException;
	
	/**
	 * 修改表单页面是查询表信息
	 * @param formid
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormTable> selectFormTable(String formid,Long companyId)throws HbcsoftException;
	
	
	/**
	 * 表修改是同步formTable信息
	 * @param tabname
	 * @throws HbcsoftException
	 */
	void updateFormTable(TableNameClass tabname)throws HbcsoftException;
	
	/**
	 * 表字段修改时同步formFields信息
	 * @param tabent
	 * @throws HbcsoftException
	 */
	void updateFormFields(TableEntity tabent)throws HbcsoftException;
	
	/**
	 * 表字段新增时同步表单字段信息
	 * @param tabent
	 * @throws HbcsoftException
	 */
	void addForFieEnt(TableEntity tabent)throws HbcsoftException;
	
	/**
	 * 表字段删除时同步表单字段信息
	 * @param entityId
	 * @throws HbcsoftException
	 */
	void delEntField(String entityId)throws HbcsoftException;
	
	/**
	 * 表删除时同步表单字段信息
	 * @param lstTabEnt
	 * @throws HbcsoftException
	 */
	void delLstForEnt(List<TableEntity> lstTabEnt)throws HbcsoftException;
	
	/**
	 * 根据公司id和表单类型查询表单表
	 * @param companyId
	 * @param formType
	 * @param isEnabled
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormName> queryisFormName(Long companyId,String formType, int isEnabled)throws HbcsoftException;
	
	/**
	 * 根据公司id、表单类型和版本号查询表单表
	 */
	List<FormName> queryUpdateIsFormName(Long companyId,String formType, int isEnabled,String version)throws HbcsoftException;
	
	/*********************表单查询开始****************************/
	/**
	 * 根据表单类型查询表单属性
	 * @param formType
	 * @return
	 * @throws HbcsoftException
	 */
	FormName queryFByFormType(String formType, Long companyId) throws HbcsoftException;
	/*********************表单查询结束****************************/
	/**
	 * 根据表名id判断是否有关联的表被调用
	 * @param tableid 数据库表的id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	List<FormTable> getListFT(String tableid,long companyId) throws HbcsoftException;
	/**
	 * 根据表名判断是否该表中存在数据
	 * @param tableid 数据库表的id
	 * @param companyId
	 * @return
	 * @throws HbcsoftException
	 */
	int getTableData(String tableName,long companyId) throws HbcsoftException;
}
