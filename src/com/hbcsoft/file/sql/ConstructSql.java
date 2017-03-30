package com.hbcsoft.file.sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.common.LogBaseClass;
/**
 * 构造sql并返回
 * @author songliang
 * @since 2016-12-21
 */
public class ConstructSql extends LogBaseClass<ConstructSql>{

	/**
	 * 构造sql并返回
	 * @param baseSql
	 * @param row 行参数
	 * @param type RI:日期 YUE：月份 NIAN:年度
	 * @param para 0：开始日期 1：结束日期 
	 * @return
	 */
	public String getSql(final String baseSql,final String row,final String groupCol, final List<String> listStr,
			final List<String> listQuery,final String type,final String ...para) throws HbcsoftException, ParseException{
		
		int selectIndex = baseSql.toUpperCase(Locale.getDefault()).indexOf("SELECT");
		final int fromIndex = baseSql.toUpperCase(Locale.getDefault()).indexOf("FROM");
		if(selectIndex==-1 || fromIndex==-1){
			throw new HbcsoftException("", 999, "基础sql存在问题，请修正！");
		}else{
			selectIndex = selectIndex + 6;
		}
		final String field = baseSql.toUpperCase(Locale.getDefault()).substring(selectIndex, fromIndex);//属性
		final String fromSql = baseSql.toUpperCase(Locale.getDefault()).substring(fromIndex);
		
//		System.out.println("field:"+field);
//		System.out.println("fromSql:"+fromSql);
		
		final StringBuilder sql = new StringBuilder();//sql语句中的属性值
		if(row != null && !"".equals(row)){
			sql.append(row).append(", ");
		}
		/**
		 * 获取日期集合查询列
		 */
		sql.append(this.getGroupColumn(type, groupCol, listStr, field));
		
		//查询语句拼接
		final StringBuilder sqlSelect = new StringBuilder(50);
		sqlSelect.append("SELECT ").
		append(sql.toString().substring(0, sql.toString().length()-1)).
		append(fromSql);
		/**
		 * where 传递过来的条件的list集合
		 */
//		sqlSelect.append(" WHERE 1=1 ");
		for(int i=0; i<listQuery.size(); i++){
			sqlSelect.append(" AND " );
			sqlSelect.append(listQuery.get(i));
		}
		
		sqlSelect.append(" group by ").append(row)
		.append(" order by ").append(row);
		this.getLogger().info(sqlSelect);
		
		return sqlSelect.toString();
	}
	/**
	 * 根据不同类型判断生成sql
	 * @param type
	 * @param groupCol
	 * @param listStr
	 * @param field
	 * @return
	 */
	private String getGroupColumn(final String type,final String groupCol, final List<String> listStr,
			final String field){
		final StringBuilder select = new StringBuilder(100);
		if(type.startsWith(ComConstant.R_C_T_D_DIC) || type.startsWith(ComConstant.R_C_T_D_SYS)){
			for(String str : listStr){
				select.append(" sum(CASE ").append(groupCol).append(" WHEN '");
				select.append(str);
				select.append("' THEN ");
				select.append(field);
				select.append("ELSE 0 END) ,");
			}
		}else if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_NIAN)){
			for(String str : listStr){
				select.append(" sum(CASE CONVERT(varchar(4),").append(groupCol).append(",112) WHEN '");
				select.append(str);
				select.append("' THEN ");
				select.append(field);
				select.append("ELSE 0 END) ,");
			}
		}else if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_YUE)){
			for(String str : listStr){
				select.append(" sum(CASE CONVERT(varchar(6),").append(groupCol).append(",112) WHEN '");
				select.append(str);
				select.append("' THEN ");
				select.append(field);
				select.append("ELSE 0 END) ,");
			}
		}else if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_RI)){
			for(String str : listStr){
				select.append(" sum(CASE CONVERT(varchar(8),").append(groupCol).append(",112) WHEN '");
				select.append(str);
				select.append("' THEN ");
				select.append(field);
				select.append("ELSE 0 END) ,");
			}
		}
		return select.toString();
	}
	/**
	 * 获取列：日期
	 * @param field
	 * @param type
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getField(final String field,final String type,final String dateStart,final String dateEnd) throws ParseException{
		String select = "";
		final SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		final SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM",Locale.getDefault());
		final SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy",Locale.getDefault());
		
		if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_RI)){
			final Date dBegin = sdfDay.parse(dateStart);  
			final Date dEnd = sdfDay.parse(dateEnd); 
			final List<Date> listDate = getDatesBetweenTwoDate(dBegin, dEnd);
			select = this.getDay(listDate, field, sdfDay);
		}else if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_YUE)){
			final Date dBegin = sdfMonth.parse(dateStart.substring(0, 7));  
			final Date dEnd = sdfMonth.parse(dateEnd.substring(0, 7)); 
			final List<Date> listDate = getDatesBetweenTwoMonth(dBegin, dEnd);
			select = this.getMonth(listDate, field, sdfMonth);
		}else if(type.equals(ComConstant.R_C_T_D_CON + ComConstant.R_C_T_D_CON_NIAN)){
			final Date dBegin = sdfYear.parse(dateStart.substring(0, 4));  
			final Date dEnd = sdfYear.parse(dateEnd.substring(0, 4)); 
			final List<Date> listDate = getDatesBetweenTwoYear(dBegin, dEnd);
			select = this.getYear(listDate, field, sdfYear);
		}
		return select;
	}
	/**
	 * 
	 * @param listDate
	 * @param field
	 * @param sdfDay
	 * @return
	 */
	private String getDay(final List<Date> listDate, final String field, final SimpleDateFormat sdfDay){
		final StringBuilder select = new StringBuilder(100);
		String date = "";//所有日期
		for(int i=0;i<listDate.size();i++){
//			System.out.println(sdf.format(listDate.get(i)));
			date = sdfDay.format(listDate.get(i)).replaceAll("-", "").substring(0, 8);
			select.append(" sum(CASE CONVERT(varchar(8),dbill_date,112) WHEN '");
			select.append(date);
			select.append("' THEN ");
			select.append(field);
			select.append("ELSE 0 END) ,");
		}
		return select.toString();
	}
	private String getMonth(final List<Date> listDate, final String field, final SimpleDateFormat sdfMonth){
		final StringBuilder select = new StringBuilder(100);
		String date = "";//所有日期
		for(int i=0;i<listDate.size();i++){
//			System.out.println(sdfMonth.format(listDate.get(i)));
			date = sdfMonth.format(listDate.get(i)).replaceAll("-", "").substring(0, 6);
			select.append(" sum(CASE CONVERT(varchar(6),dbill_date,112) WHEN '");
			select.append(date);
			select.append("' THEN ");
			select.append(field);
			select.append("ELSE 0 END) ,");
		}
		return select.toString();
	}
	
	private String getYear(final List<Date> listDate, final String field, final SimpleDateFormat sdfYear){
		final StringBuilder select = new StringBuilder(100);
		String date = "";//所有日期
		for(int i=0;i<listDate.size();i++){
//			System.out.println(sdfYear.format(listDate.get(i)));
			date = sdfYear.format(listDate.get(i)).substring(0, 4);
			select.append(" sum(CASE CONVERT(varchar(4),dbill_date,112) WHEN '");
			select.append(date);
			select.append("' THEN ");
			select.append(field);
			select.append("ELSE 0 END) ,");
		}
		return select.toString();
	}
	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * @param beginDate
	 * @param endDate
	 * @return list
	 * @author songliang
	 * @since 2016-12-21
	 */
	public static List<Date> getDatesBetweenTwoDate(final Date beginDate, final Date endDate) {
		final List<Date> dateList = new ArrayList<Date>();  
		dateList.add(beginDate);// 把开始日期加入集合
		final Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间  
		cal.setTime(beginDate);
//		boolean bContinue = true;
		while (true) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后 
			if (endDate.after(cal.getTime())) {
				dateList.add(cal.getTime());
			} else {
				break;
			}
		}
		if(beginDate.getTime() != endDate.getTime()){
			dateList.add(endDate);//把结束时间加入集合 
		}
		return dateList;
	}
	/**
	 * 根据开始时间和结束时间返回时间段内的月份集合
	 * @param beginDate
	 * @param endDate
	 * @return list
	 * @author songliang
	 * @since 2016-12-21
	 */
	public static List<Date> getDatesBetweenTwoMonth(final Date beginDate, final Date endDate) {
		final List<Date> dateList = new ArrayList<Date>();  
		dateList.add(beginDate);// 把开始时间加入集合
		final Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
//		boolean bContinue = true;
		while (true) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量 
			cal.add(Calendar.MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				dateList.add(cal.getTime());
			} else {
				break;
			}
		}
		if(beginDate.getTime() != endDate.getTime()){
			dateList.add(endDate);// 把结束时间加入集合
		}
		return dateList;
	}
	/**
	 * 根据开始时间和结束时间返回时间段内的年度集合
	 * @param beginDate
	 * @param endDate
	 * @return list
	 * @author songliang
	 * @since 2016-12-21
	 */
	public static List<Date> getDatesBetweenTwoYear(final Date beginDate, final Date endDate) {
		final List<Date> dateList = new ArrayList<Date>();  
		dateList.add(beginDate);// 把开始时间加入集合  
		final Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
//		boolean bContinue = true;
		while (true) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
			cal.add(Calendar.YEAR, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				dateList.add(cal.getTime());
			} else {
				break;
			}
		}
		if(beginDate.getTime() != endDate.getTime()){
			dateList.add(endDate);// 把结束时间加入集合
		} 
		return dateList;
	}
}
