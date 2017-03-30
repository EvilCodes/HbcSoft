package com.hbcsoft.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * 工具类
 * @author liang
 * @since 20160918
 */
public class HbcsoftUtil {
	/**
	 * 自动生成id
	 */
	public static String uuid (){
//		return UUID.randomUUID().toString().replaceAll("-", "");
		return "";
	}
	/**
	 * 
	 */
	@Test
	public void uuid2 (){
//		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
	/**
	 * 获取系统日期
	 * @return
	 */
	public static String getSysDate(){
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return sdf.format(date);
	}
	
	/**
	 * 将字符串转为日期，格式为yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date fromStrToDate(final String strDate) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return sdf.parse(strDate);
	}

	/**
	 * 将字符串转为日期，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date fromStrToDatetime(final String strDate) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return sdf.parse(strDate);
	}

	/**
	 * 将日期格式化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formDateToStr(final Date date) {
		String res = "";
		if (date != null) {
			res = getFormat(date,"yyyy-MM-dd");
		}
		return res;
	}

	/**
	 * @param date
	 * @return
	 */
	private static String getFormat(final Date date,final String format){
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(date);
	}
	/**
	 * 将日期时间格式化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formDatetimeToStr(final Date date) {
		String res = "";
		if (date != null) {
			res = getFormat(date,"yyyy-MM-dd HH:mm:ss");
		}
		return res;
	}

	/**
	 * 将日期时间格式化为字符串(带毫秒)
	 * 
	 * @param date
	 * @return
	 */
	public static String formDatetimeToStr2(final Date date) {
		String res = "";
		if (date != null) {
			res = getFormat(date,"yyyy-MM-dd HH:mm:ss.sss");
		}
		return res;
	}
	/**
	 * 将日期时间格式化为字符串(带毫秒)
	 * 
	 * @param date
	 * @return
	 */
	public static String formDatetimeToStr3(final Date date) {
		String res = "";
		if (date != null) {
			res = getFormat(date,"yyyyMMddHHmmsssss");
		}
		return res;
	}
	/**
	 * 获取当前年度
	 * @return
	 */
	public static String getInstYear() {
		final int year = getYear();
		return String.valueOf(year);
	}
	/**
	 * @return
	 */
	private static int getYear(){
		final Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
}
