package com.hbcsoft.excel.common;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.table.entity.TableEntity;

/**
 * Excel导出工具类
 * @author gaodekui
 *
 */
public class ExcelWrite {
	
	public static final String TABLEENTITY = "创建数据库表模版";
	
	public static void createExcel(Map <String,List<TableEntity>> entityMap,ServletOutputStream outputStream) throws IOException{
		String tableName = null;
		List<TableEntity> entityList = null;
		for (Map.Entry<String,List<TableEntity>>tempMap: entityMap.entrySet()){
			tableName = tempMap.getKey();
			entityList = tempMap.getValue();
		}
		XSSFWorkbook wb = new XSSFWorkbook();// 创建一个workbook 对应一个excel应用文件
		XSSFSheet sheet = wb.createSheet("Sheet1");// 在workbook中添加一个sheet,对应Excel文件中的sheet
		createExcel(tableName,entityList,wb,sheet);
		wb.write(outputStream);
	}

	private static void createExcel(String tableName, List<TableEntity> entity,XSSFWorkbook wb,XSSFSheet sheet) {
		
		XSSFCellStyle headStyle = getHeadStyle(wb);//获取表头样式
		XSSFCellStyle bodyStyle = getBodyStyle(wb);//获取变体样式
		
		XSSFRow headRow = sheet.createRow(0);//构建表头
		XSSFCell cell = null;
		//输出标题
		for(int i = 0; i < entity.size(); i++){
			
			String width = entity.get(i).getTitle()+"<"+entity.get(i).getName()+">";
			sheet.setColumnWidth(i, width.length() * 400);//设置单元格宽度
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(entity.get(i).getTitle()+"<"+entity.get(i).getName()+">");
		}
		//设置数据格式
    	XSSFDataFormat df = wb.createDataFormat();
    	
		//构建表体
		for(int j = 0; j < 999;j++){
			XSSFRow bodyRow = sheet.createRow(j+1);
			for (int k = 0; k < entity.size();k++){
				if (entity.get(k).getType().equals(HBCSoftConstant.COLUMN_TYPE_INT)
					|| entity.get(k).getType().equals(HBCSoftConstant.COLUMN_TYPE_BIGINT)){
					bodyStyle.setDataFormat(df.getFormat("#,#0"));//数据格式为整数
					cell = bodyRow.createCell(k);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellStyle(bodyStyle);
				}else if (entity.get(k).getType().equals(HBCSoftConstant.COLUMN_TYPE_NUMERIC)){
					bodyStyle.setDataFormat(df.getFormat("#,##0.00"));//保留两位小数
					cell = bodyRow.createCell(k);
					cell.setCellStyle(bodyStyle);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				}else if (entity.get(k).getType().equals(HBCSoftConstant.COLUMN_TYPE_DATE)){
					cell = bodyRow.createCell(k);
					cell.setCellStyle(bodyStyle);
				}else if (entity.get(k).getType().equals(HBCSoftConstant.COLUMN_TYPE_TEXT)){
					cell = bodyRow.createCell(k);
					cell.setCellStyle(bodyStyle);
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}
				
			}
		}
	}
	
	 /**
     * 设置表头的单元格样式
     * 
     * @return
     */
    private static XSSFCellStyle getHeadStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        
       
        return cellStyle;
    }
    
    /**
     * 设置表体的单元格样式
     * 
     * @return
     */
    private static XSSFCellStyle getBodyStyle(XSSFWorkbook wb) {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }
    
}
