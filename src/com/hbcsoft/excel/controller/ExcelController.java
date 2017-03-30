package com.hbcsoft.excel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.excel.common.ExcelDispense;
import com.hbcsoft.excel.common.ExcelReader;
import com.hbcsoft.excel.common.ExcelStencil;
import com.hbcsoft.excel.service.ExcelService;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.HbcsoftJT;
/**
 * Excel导入
 * @author gaodekui
 *
 */
@Controller
@RequestMapping(value = "/createExcel")
public class ExcelController {
	/**
	 * ExcelService Bean
	 */
	@Autowired
	private transient ExcelService excelService;
	/**
	 * HbcsoftJT Bean
	 */
	@Autowired
	private transient HbcsoftJT jt;
	/**
	 * Excel导入页面
	 * @throws HbcsoftException 
	 * 
	 */
	@RequestMapping(value = "/tableList")
	public ModelAndView tableList(final HttpServletRequest request, final HttpServletResponse response) throws HbcsoftException{
		return new ModelAndView("excel/Excel-list");
		
	}
	
	/**
	 * Excel模版下载
	 * @return
	 * @throws IOException 
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/excelDownload")
	public ModelAndView downLoadExcel(final HttpServletRequest request, final HttpServletResponse response) throws IOException, HbcsoftException{
		
		
		String fileName = request.getParameter("fileName");
		final OutputStream outputStream = response.getOutputStream();
		String tempName = "";
		//判断下载模版的类型
		for(final ExcelStencil stencil:ExcelStencil.values()){
			
			if (stencil.toString().equals(fileName))
				tempName = stencil.getDesc();
		}
		fileName=new String(tempName.getBytes("gb2312"), "iso8859-1")+".xlsx";
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setCharacterEncoding("utf-8");
		String path;
		path = request.getSession().getServletContext().getRealPath("/uploadExcel/"+tempName+".xlsx");
		final InputStream inputStream = new FileInputStream(new File(path));
		final byte[] tempbyte = new byte[1024];
		int length;
		while ((length = inputStream.read(tempbyte))!= -1){
			outputStream.write(tempbyte, 0, length);
		}
		inputStream.close();
		
		return null; 	
	}
	
	/**
	 * Excel模版导入
	 * @return
	 * @throws HbcsoftException 
	 * @throws IOException 
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public ModelAndView importExcel(@RequestParam(value = "file",required = false)final MultipartFile file,final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		String message = "";
		try {
			String fileName ;
			fileName = file.getOriginalFilename();//获取上传文件的文件名称
			String fileNameMove;
			fileNameMove = FilenameUtils.getBaseName(fileName);//移除文件名后缀
			String buttonName;
			buttonName = request.getParameter("buttonName");
			final CharSequence charSequence = buttonName.subSequence(0, fileNameMove.length());
			
			if (fileNameMove.contains(charSequence)){
				//判断导入模版的类型
				ExcelStencil tempStencil = null;
				List<List> entityList = new ArrayList<List>();
				for(final ExcelStencil stencil:ExcelStencil.values()){
					final CharSequence tempcharSequence = stencil.getDesc().subSequence(0, stencil.getDesc().length());
					if (fileNameMove.contains(tempcharSequence)){
						entityList = ExcelReader.exprotListFormList(file.getInputStream(), FilenameUtils.getExtension(fileName), 0, stencil.getClazz());
						if (entityList.isEmpty()){
							message = "上传模板不正确或更改了模板文件名称或模版数据有误";
							break;
						}
						
						message = ExcelDispense.excelValidate(entityList, stencil,request, entityList,jt);//验证数据有效性
						
						if (message.isEmpty())
							tempStencil = stencil;
					}
					
				}
				if (tempStencil!= null)
					message = ExcelDispense.importExcel(tempStencil, entityList,request,excelService);
			}else{
				message = "选择导入的Excel文件与所选导入类型不匹配";
			}
			
		} catch (HbcsoftException e) {
			message = "导入文件出现问题，请检查文件有效性";
			throw new HbcsoftException("",ErrorConstant.EXCEPTION_CODE , e);
		}finally{
			if (message.isEmpty()){
				message = "导入文件出现问题，请检查文件有效性";
			}
			final ModelAndView mav = new ModelAndView("excel/Excel-list");
			mav.getModelMap().put("message", message);
			return mav;
		}
		
		
	}
	
}
