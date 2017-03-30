package com.hbcsoft.file.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * 写入数据到已经存在的模板中
 * @author songliang
 * @since 2016-12-19
 */
public class WriteDataToExcel extends LogBaseClass<WriteDataToExcel>{
	/**
	 * 写入到已经存在的模板数据
	 * @param ec 开始行、列，特殊行、列对象
	 * @param list 数据
	 * @param filePath 模板路径
	 */
	public XSSFWorkbook writeData(final ExcelCoordinates ec,final List<List<Object>> list,
			final String filePath,final String mainTitle,final String subTitle){
		final String [] arrRow = ec.getSpecialRow().split("[,，]");
		final String [] arrCol = ec.getSpecialColumn().split("[,，]");
		List<Object> objList = new ArrayList<Object>();
		int rowIndex = 0;//获取外围list的值的index
		boolean specialRow = false;
		boolean specialCol = false;
		final File file = new File(filePath);
		FileInputStream fis = null;
//		FileOutputStream fos = null;
		XSSFWorkbook wb = null;
		/**
		 * 判断模板是否存在
		 */
		if(file.exists()){
			try {
				fis = new FileInputStream(file);
				//1.创建workBook
				wb = new XSSFWorkbook(fis);
				//2.添加sheet
				final XSSFSheet sheet = wb.getSheetAt(0);
				final XSSFFont font = this.setFont(wb);
				final XSSFCellStyle style = this.setStyle(wb, font);
				if(sheet != null){//sheet存在
//					int rowCount = sheet.getLastRowNum()+1;//表格总行数
//					int colCount = sheet.getRow(0).getLastCellNum();//表格总列数
					XSSFRow row = null;
					XSSFCell cell = null;
					
					/***增加主、副标题写入start***/
					row = sheet.getRow(ec.getMainTitleStartRow());//主标题起始行
					if(row==null || "".equals(row)){
						row = sheet.createRow(ec.getMainTitleStartRow());
					}
					cell = row.getCell(ec.getMainTitleStartColumn());//主标题起始列
					if(cell==null || "".equals(cell)){
						cell = row.createCell(ec.getMainTitleStartColumn());
					}
					cell.setCellValue(mainTitle);
					
					row = sheet.getRow(ec.getSubtitleStartRow());//副标题起始行
					if(row==null || "".equals(row)){
						row = sheet.createRow(ec.getSubtitleStartRow());
					}
					cell = row.getCell(ec.getSubtitleStartColumn());//副标题起始列
					if(cell==null || "".equals(cell)){
						cell = row.createCell(ec.getSubtitleStartColumn());
					}
					cell.setCellValue(subTitle);
					/***增加主、副标题写入end***/
					
					for(int i=ec.getStartRow(); i<65535; i++){
						int colIndex = 0;//获取内层list值的index，放在此处是为了每次创建一个新的行时，列从0开始取
						for(int m=0; m<arrRow.length; m++){
							if(arrRow[m]!=null && !"".equals(arrRow[m])){
								if(i == Integer.valueOf(arrRow[m])){
									specialRow = true;
									break;
								}
							}
						}
						if(specialRow){
							specialRow = false;
							continue;
						}
						specialRow = false;
						row = sheet.getRow(i);//开始行
						if(row==null || "".equals(row)){
							row = sheet.createRow(i);
						}
						/**********************外层list值取完，则跳出循环，不再创建行start********************/
						if(rowIndex>=list.size()){
							break;
						}else{
							objList = list.get(rowIndex);
							rowIndex++;
						}
						/**********************外层list值取完，则跳出循环，不再创建行end********************/
						for(int j=ec.getStartColumn(); j<65535; j++){
							for(int n=0; n<arrCol.length; n++){
								if(arrCol[n]!=null && !"".equals(arrCol[n])){
									if(j == Integer.valueOf(arrCol[n])){
//										cell = row.createCell(j);//开始单元格
//										cell.setCellStyle(style);
										specialCol = true;
										break;
									}
								}
							}
							if(specialCol){
								specialCol = false;
								continue;
							}
							specialCol = false;
							cell = row.getCell(j);//开始单元格
							if(cell == null || "".equals(cell)){
								cell = row.createCell(j);//开始单元格
							}
//							cell.setCellStyle(style);
//							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//							cell.setCellValue(j+1);
							/**********************内层list值取完，则跳出循环，不再创建列start********************/
							if(colIndex>=objList.size()){
								break;
							}else{
								final Object obj = objList.get(colIndex);
//								cell.setCellType(this.getType(obj));
//								cell.setCellValue(obj.toString());
								cell.setCellStyle(this.setStyle(obj, cell,wb));
								colIndex++;
							}
							/**********************内层list值取完，则跳出循环，不再创建列end********************/
						}
					}
				}/*else{
					//sheet不存在，创建一个sheet？
					
				}*/
				
//				fos = new FileOutputStream(file);
//				wb.write(fos);
			} catch (FileNotFoundException e) {
//				e.printStackTrace();
				this.getLogger().info(e);
			}catch (IOException e) {
//				e.printStackTrace();
				this.getLogger().info(e);
			}finally{
//				if(fos != null){
//					try {
//						fos.close();
//					} catch (IOException e) {
//						this.getLogger().info(e);
//					}
//				}
				if(fis != null){
					try {
						fis.close();
					} catch (IOException e) {
						this.getLogger().info(e);
					}
				}
			}
		}/*else{
			//模板不存在，创建一个模板？
			
			
		}*/
		return wb;
	}
	
	
	/**
	 * 设置单元格字体
	 * @param wb
	 * @return
	 */
	private XSSFFont setFont(final XSSFWorkbook wb){
		final XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		return font;
	}
	/**
	 * 设置单元格样式
	 * @param wb
	 * @param font
	 * @return
	 */
	private XSSFCellStyle setStyle(final XSSFWorkbook wb,final XSSFFont font){
		final XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return style;
	}
	/**
	 * 设置返回值类型
	 * @param obj
	 * @param style
	 * @param cell
	 * @return
	 */
	private XSSFCellStyle setStyle(final Object obj,final XSSFCell cell,final XSSFWorkbook wb){
		final XSSFCellStyle style = wb.createCellStyle();
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		if(obj.getClass().getName()==String.class.getName()){
			cell.setCellValue(obj.toString());
			final DataFormat formatDouble = wb.createDataFormat();
			style.setDataFormat(formatDouble.getFormat("@"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==Integer.class.getName()){
			cell.setCellValue(Integer.valueOf(obj.toString()));
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==int.class.getName()){
			cell.setCellValue(Integer.valueOf(obj.toString()));
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==Long.class.getName()){
			cell.setCellValue(obj.toString());
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==long.class.getName()){
			cell.setCellValue(obj.toString());
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==Date.class.getName()){
			cell.setCellValue(new Date());
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy年m月d日"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==Double.class.getName()){
			cell.setCellValue(Double.valueOf(obj.toString()));
			final DataFormat formatDouble = wb.createDataFormat();
			style.setDataFormat(formatDouble.getFormat("#,##0.00"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==double.class.getName()){
			cell.setCellValue(Double.valueOf(obj.toString()));
			final DataFormat formatDouble = wb.createDataFormat();
			style.setDataFormat(formatDouble.getFormat("#,##0.00"));
			cell.setCellStyle(style);
		}else if(obj.getClass().getName()==BigDecimal.class.getName()){
			cell.setCellValue(Double.valueOf(obj.toString()));
			final DataFormat formatDouble = wb.createDataFormat();
			style.setDataFormat(formatDouble.getFormat("#,##0.00"));
			cell.setCellStyle(style);
		}else{
			cell.setCellValue(obj.toString());
			final DataFormat formatDouble = wb.createDataFormat();
			style.setDataFormat(formatDouble.getFormat("@"));
			cell.setCellStyle(style);
		}
		return style;
	}
	/**
	 * 返回类型
	 * @param obj
	 * @return
	 */
//	private int getType(Object obj){
//		int type = 1;
//		if(obj.getClass().getName()==String.class.getName()){
//			type = XSSFCell.CELL_TYPE_STRING;
//		}else if(obj.getClass().getName()==Integer.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}else if(obj.getClass().getName()==int.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}else if(obj.getClass().getName()==Long.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}else if(obj.getClass().getName()==long.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}else if(obj.getClass().getName()==Date.class.getName()){
//			type = XSSFCell.CELL_TYPE_STRING;
//		}else if(obj.getClass().getName()==Double.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}else if(obj.getClass().getName()==double.class.getName()){
//			type = XSSFCell.CELL_TYPE_NUMERIC;
//		}
//		return type;
//	}
}
