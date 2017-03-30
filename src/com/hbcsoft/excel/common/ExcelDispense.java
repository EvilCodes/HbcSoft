package com.hbcsoft.excel.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hbcsoft.excel.service.ExcelService;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.HbcsoftJT;
/**
 * Excel判断操作
 * @author gaodekui
 *
 */
public final class ExcelDispense {
	
	
	private  ExcelDispense() {}
	/**
	 * 校验数据
	 * @throws HbcsoftException 
	 */
	public static String excelValidate(final List list, final ExcelStencil stencil,final HttpServletRequest request,final List <List> entityList,final HbcsoftJT jt) throws HbcsoftException {
		
		String message = "";
		switch (stencil) {
			case CREATETABLEENTITY:
				message = ExcelValidateTool.validateCreateTable(list,jt,request);
				break;
			case CREATEFROM:
				message = ExcelValidateTool.validateCreateFrom(list,jt);
				break;
			case CREATETABLEANDFROM:
				message = ExcelValidateTool.vaildateCreateTableAndFrom(list,jt,request);
				break;
//			case DATABASECONFIGURATION:
//				message = ExcelValidateTool.validateDatabaseConfiguration(list, clazz, jt);
//				break;
//			case WORKFLOWBASECONFIGURE:
//				message = ExcelValidateTool.validateWorkFlowBaseConfigure(list,clazz,jt);
//				break;
			default:
				break;
		}
		return message;
	}
	/**
	 * 数据导入
	 * @throws HbcsoftException 
	 */
	public static String importExcel(final ExcelStencil stencil,final List<List> list,final HttpServletRequest request,final ExcelService excelService) throws HbcsoftException {
		
		String message ="";
		switch (stencil) {
		case CREATETABLEENTITY:
			message = excelService.operateCreateTableEntity(list,request);
			break;
		case CREATEFROM:
			message = excelService.opreateCreateFrom(list,request);
			break;
		case CREATETABLEANDFROM:
			message = excelService.opreateCreateTableAndFrom(list,request);
			break;
		case DATABASECONFIGURATION:
			message = excelService.operateDatabaseConfiguration(list,request);
			break;
		case WORKFLOWBASECONFIGURE:
			message = excelService.opreateWorkFlowBaseConfigure(list);
			break;
		default: break;
		}
		
		return message;
	}
}
