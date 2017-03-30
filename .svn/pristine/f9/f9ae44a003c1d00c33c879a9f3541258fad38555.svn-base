package com.hbcsoft.excel.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.lang.reflect.Field;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.LogBaseClass;

/**
 * Excel导入工具类
 * @author gaodekui
 * @param
 */
public final class ExcelReader extends LogBaseClass<ExcelReader> {
	
	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007 以后版本
	 */
	private final static String XLSX = "xlsx";
	
	private ExcelReader(){super();}
	/**
	 * 由Excel文件的sheet导出至List
	 * @param file
	 * @param sheetNum
	 * @return
	 * @throws FileNotFoundException
	 */
//	public static <T> List<T> exprotListFormList(final File file, final int sheetNum,final Class<T> clazz) throws HbcsoftException {
//			try {
//				return exprotListFormList(new FileInputStream(file),FilenameUtils.getExtension(file.getName()),sheetNum, clazz);
//			} catch (FileNotFoundException e) {
//				throw new HbcsoftException("",ErrorConstant.EXCEPTION_CODE , e);
//			}
//	}

	/**
	 * 由Excel流的sheet导出至List	
	 * @param fileInputStream
	 * @param extension
	 * @param sheetNum
	 * @return
	 * @throws HbcsoftException 
	 */
	public static List exprotListFormList(final InputStream fileInputStream,
			final String extension, final int sheetNum,final Class ... clazz) throws HbcsoftException {
		Workbook workbook = null;
		
		try {
			if (extension.equalsIgnoreCase(XLS))
				workbook = new HSSFWorkbook(fileInputStream);
			else if(extension.equalsIgnoreCase(XLSX))
				workbook = new XSSFWorkbook(fileInputStream);
			return exprotListFormList(workbook,sheetNum,clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new HbcsoftException("",ErrorConstant.EXCEPTION_CODE , e);
		}
		
	
	}
	
	/**
	 * 由指定的Sheet导出至List
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws HbcsoftException 
	 * @throws  
	 */
	private static List exprotListFormList(final Workbook workbook, final int sheetNum,final Class... clazz) throws HbcsoftException{
			
		try {
			final Map <String,List> clazzMap = new ConcurrentHashMap<String, List>();
			if (clazz.length > 0){
				for (int num = 0; num < clazz.length;num++){
					String className;
					className = clazz[num].newInstance().toString();
					clazzMap.put(className.substring(0,className.indexOf('@')),new ArrayList());
				}
			}
			final List list = new ArrayList();
			final Sheet sheet = workbook.getSheetAt(sheetNum);
			final CreationHelper creationHelper = workbook.getCreationHelper();
			final FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();//解析公式结果
			final int minRow = sheet.getFirstRowNum();//获取第一行
			final int maxRow = sheet.getPhysicalNumberOfRows();//获取总行数
			for (int rowdex = 1; rowdex < maxRow; rowdex++){
				final Row row = sheet.getRow(rowdex);//数据从第二行开始，第一行为标题
				final int minCell = row.getFirstCellNum();
				final int maxCell = row.getLastCellNum();
				for (final Map.Entry<String, List> entry:clazzMap.entrySet()){
					String key;
					key = entry.getKey();
					final Object obj =  Class.forName(key).newInstance();
					final Field[] fields = Class.forName(key).getDeclaredFields();
					for (int celldex = minCell; celldex < maxCell; celldex++){
						String fieldValue = sheet.getRow(0).getCell(celldex).getStringCellValue().trim();//获取表头
						fieldValue = fieldValue.substring(fieldValue.indexOf('<')+1, fieldValue.indexOf('>'));
						final Cell cell = row.getCell(celldex);
						final CellValue cellValue = evaluator.evaluate(cell);
						for (int i = 0; i < fields.length; i++){
							fields[i].setAccessible(true);//通过反射可以给私有属性赋值
							if (fields[i].getName().equals(fieldValue)){
								if (cellValue == null)
									continue;
								if(fields[i].getType()== Integer.class){
									fields[i].set(obj,((Double)cellValue.getNumberValue()).intValue());
								}else if(fields[i].getType() == int.class){
									fields[i].set(obj, ((Double)cellValue.getNumberValue()).intValue());
								}else if(fields[i].getType() == Double.class){
									fields[i].set(obj, cellValue.getNumberValue());
								}else if(fields[i].getType() == double.class){
									fields[i].set(obj, cellValue.getNumberValue());
								}else if(fields[i].getType() == long.class){
									fields[i].set(obj, ((Double)cellValue.getNumberValue()).longValue());
								}else if (fields[i].getType() == Long.class){
									fields[i].set(obj, ((Double)cellValue.getNumberValue()).longValue());
								}else if (fields[i].getType() == Boolean.class){
									fields[i].set(obj, cellValue.getBooleanValue());
								}else if (fields[i].getType() == String.class){
									fields[i].set(obj, cellValue.getStringValue());
								}else if (fields[i].getType() == Date.class){
									final Date date = cell.getDateCellValue();
									fields[i].set(obj,date);
								}
							}
						}
					}
					clazzMap.get(key).add(obj);
				}
				
				//list.add(obj);
			}
				
			for(final Map.Entry<String, List> entry:clazzMap.entrySet()){
				list.add(entry.getValue());
			}
			
			return list;
		} catch (InstantiationException | IllegalAccessException
				| SecurityException | ClassNotFoundException
				| IllegalArgumentException e) {
			throw new HbcsoftException("",ErrorConstant.EXCEPTION_CODE , e);
		}
		
	}
}
