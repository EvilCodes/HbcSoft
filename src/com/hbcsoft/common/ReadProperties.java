package com.hbcsoft.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
/**
 * 去读open.properties文件，code为空则全部显示资源；code存在，则存在的不显示
 * @author songliang
 * @since 2017-01-05
 */
public class ReadProperties {
	/**
	 * 获取资源编码
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public List<String> getCode(final HttpServletRequest request) throws IOException{
		final List<String> list = new ArrayList<String>();
		
		final String path = request.getServletContext().getRealPath("/WEB-INF/classes/com/hbcsoft/properties/closeResource.properties");
		final File file = new File(path);
		if(file.exists()){
			final FileInputStream fis = new FileInputStream(file);
			//解决读取中文乱码
			final InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			
			final Properties prop = new Properties();
			prop.load(isr);
			final String value = prop.getProperty("code");
			final String [] str = value.split("[，,]");
			for(int i=0; i<str.length; i++){
				list.add(str[i]);
//				System.out.println(str[i]);
			}
		}
		return list;
	}
}
