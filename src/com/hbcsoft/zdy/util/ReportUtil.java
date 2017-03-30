package com.hbcsoft.zdy.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbcsoft.report.entity.ReportHead;
import com.hbcsoft.report.entity.ReportRow;
import com.hbcsoft.sys.entity.DataDict;
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.Role;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.common.ReportTableCell;

public class ReportUtil {
	
	public static List<List<Object>> formatResult(final List<ReportRow> row, final List<List<Object>> lstResult)
	{
		List<List<Object>> lstData = new ArrayList<List<Object>>();
		Map<String, List<Object>> cellMap = new HashMap<String, List<Object>>();
		
		if(lstResult.isEmpty())
		{
			return lstData;
		}
		
		final int length = lstResult.get(0).size();
		lstResult.forEach(lst ->{
			cellMap.put(String.valueOf(lst.get(0)), lst);
		});
		
		row.forEach(data ->{
			List<Object> lstDataRow = newList();
			String[] values = data.getValue().split(",");
			
			for(int index = 0; index < values.length; index++)
			{
				if(index == 0)
				{
					List<Object> lstO = cellMap.get(values[index]);
					
					if(lstO == null)
					{
						lstO = newList();
						for(int in = 0; in < length; in++)
						{
							lstO.add(0);
						}
					}
					lstDataRow.addAll(lstO);
					lstDataRow.remove(0);
				}else{
					List<Object> lstO = cellMap.get(values[index]);
					if(lstO == null)
					{
						lstO = newList();
						for(int in = 0; in < length; in++)
						{
							lstO.add(0);
						}
					}
					for(int i = 1; i < lstO.size(); i++)
					{
						Double d = Double.valueOf(String.valueOf(lstDataRow.get(i-1))) + Double.valueOf(String.valueOf(lstO.get(i)));
						lstDataRow.set(i-1, d);
					}
				}
			}
			
			lstData.add(lstDataRow);
		});
		
		return lstData;
	}
	
	public static int addRowConfig(final List<ReportRow> row, final List<List<ReportTableCell>> lstQueryData)
	{
		List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		Map<Long, ReportTableCell> cellMap = new HashMap<Long, ReportTableCell>();
//		Map<Long, ReportTableCell> cellMapP = new HashMap<Long, ReportTableCell>();
		int rowLength = 0;
		
		for(int index = 0; index < row.size(); index++)
		{
			final ReportRow data = row.get(index);
			ReportTableCell cell = new ReportTableCell();
			cell.setValue(data.getHname());
			cell.setColumnSpan(1);
			cell.setRowSpan(0);
			cellMap.put(data.getId(), cell);
//			cellMapP.put(data.getParentId(), cell);
			
//			if(cellMap.get(data.getParentId()) != null)
//			{
//				final int prow = cellMap.get(data.getParentId()).getRowSpan();
//				cellMap.get(data.getParentId()).setRowSpan(prow + 1);
//			}
		}
		
		for(int index = row.size(); index > 0; index--)
		{
			final ReportRow data = row.get(index-1);
			
			ReportTableCell cell = cellMap.get(data.getId());
			if(cell.getRowSpan() == 0)
			{
				cell.setRowSpan(1);
			}
			
			ReportTableCell cellt = cellMap.get(data.getParentId());
			if(cellt != null)
			{
				final int pcol = cellt.getRowSpan();
				cellt.setRowSpan(pcol + cell.getRowSpan());
			}
		}
		
		lstQueryData.add(lstQueryDataRow);
		for(int index = 0; index < row.size(); index++)
		{
			final ReportRow data = row.get(index);
//			if(cellMapP.get(data.getParentId()) == null)
			final ReportTableCell cell = cellMap.get(data.getId());
			
			if(data.getLevelcount() >= rowLength)
			{
				rowLength = data.getLevelcount();
			}
			lstQueryDataRow.add(cell);

			if(data.getIsEnd() == 1)
			{
				if(index < row.size() - 1)
				{
					lstQueryDataRow = newList();
					lstQueryData.add(lstQueryDataRow);
				}
			}
		}
		cellMap = null;
//		cellMapP = null;
		return rowLength;
	}
	
	public static void addRowDict(final List<DataDict> lstDataDict, final List<List<ReportTableCell>> lstQueryData)
	{
		lstDataDict.forEach(data -> {
			final List<ReportTableCell> lstQueryDataRow = newList();
			setCell(data.getDictName(), lstQueryDataRow);
			lstQueryData.add(lstQueryDataRow);
		});
	}
	
	public static void addRowOrg(final List<Org> lstOrg, final List<List<ReportTableCell>> lstQueryData)
	{
		lstOrg.forEach(org -> {
			final List<ReportTableCell> lstQueryDataRow = newList();
			setCell(org.getName(), lstQueryDataRow);
			lstQueryData.add(lstQueryDataRow);
		});
	}
	
	public static void addRowUser(final List<User> lstUser, final List<List<ReportTableCell>> lstQueryData)
	{
		lstUser.forEach(user -> {
			final List<ReportTableCell> lstQueryDataRow = newList();
			setCell(user.getName(), lstQueryDataRow);
			lstQueryData.add(lstQueryDataRow);
		});
	}
	
	public static void addRowRole(final List<Role> lstRole, final List<List<ReportTableCell>> lstQueryData)
	{
		lstRole.forEach(role -> {
			final List<ReportTableCell> lstQueryDataRow = newList();
			setCell(role.getName(), lstQueryDataRow);
			lstQueryData.add(lstQueryDataRow);
		});
	}
	
	public static void addRowYearMonthDate(final String strStart, final String strEnd, 
			final List<List<ReportTableCell>> lstQueryData, final List<String> lstStr,
			final int field)
	{
		Date starD = PubTools.stringToDate(strStart, ComConstant.DATEFORMAT_YMD10);
		Date endD = PubTools.stringToDate(strEnd, ComConstant.DATEFORMAT_YMD10);
		
		GregorianCalendar gcs = new GregorianCalendar();
		gcs.setTime(starD);
		GregorianCalendar gce = new GregorianCalendar();
		gce.setTime(endD);
		
		List<ReportTableCell> lstQueryDataRow = newList();
		while(gcs.compareTo(gce) < 1)
		{
			lstQueryDataRow = newList();
			addList(gcs, lstQueryDataRow, lstStr, field);
			lstQueryData.add(lstQueryDataRow);
			gcs.add(field, 1);
		}
	}
	
	public static int addHeadConfig(final List<ReportHead> head, final List<List<ReportTableCell>> lstQueryData)
	{
		List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		Map<Long, ReportTableCell> cellMap = new HashMap<Long, ReportTableCell>();
		int headRow = 0;
		
		for(int index = 0; index < head.size(); index++)
		{
			final ReportHead data = head.get(index);
			ReportTableCell cell = new ReportTableCell();
			cell.setValue(data.getHname());
			cell.setColumnSpan(0);
			cell.setRowSpan(1);
			cellMap.put(data.getId(), cell);
		}
		
		for(int index = head.size(); index > 0; index--)
		{
			final ReportHead data = head.get(index-1);
			
			ReportTableCell cell = cellMap.get(data.getId());
			if(cell.getColumnSpan() == 0)
			{
				cell.setColumnSpan(1);
			}
			
			ReportTableCell cellt = cellMap.get(data.getParentId());
			if(cellt != null)
			{
				final int pcol = cellt.getColumnSpan();
				cellt.setColumnSpan(pcol + cell.getColumnSpan());
			}
		}
		
		List<ReportHead> headtemp = newList();
		headtemp.add(head.get(0));
		head.remove(0);
		
		for(int index = 0; index < headtemp.size(); index++)
		{
			ReportHead hrowtemp = headtemp.get(index);
			head.forEach(hrow -> {
				if(hrow.getParentId().equals(hrowtemp.getId()))
				{
					headtemp.add(hrow);
				}
			});
		}
		
		lstQueryData.add(lstQueryDataRow);
		for(int index = 0; index < headtemp.size(); index++)
		{
			final ReportHead data = headtemp.get(index);
			if(data.getLevelcount() != headRow)
			{
				if(headRow > 0)
				{
					lstQueryDataRow = newList();
					lstQueryData.add(lstQueryDataRow);
				}
				headRow = data.getLevelcount();
			}
			final ReportTableCell cell = cellMap.get(data.getId());
			lstQueryDataRow.add(cell);
		}
		cellMap = null;
		return headRow;
	}
	
	public static void addHeadDict(final List<DataDict> lstDataDict, final List<List<ReportTableCell>> lstQueryData)
	{
		final List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		lstDataDict.forEach(data -> {
			setCell(data.getDictName(), lstQueryDataRow);
		});
		lstQueryData.add(lstQueryDataRow);
	}
	
	public static void addHeadOrg(final List<Org> lstOrg, final List<List<ReportTableCell>> lstQueryData)
	{
		final List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		lstOrg.forEach(org -> {
			setCell(org.getName(), lstQueryDataRow);
		});
		lstQueryData.add(lstQueryDataRow);
	}
	
	public static void addHeadUser(final List<User> lstUser, final List<List<ReportTableCell>> lstQueryData)
	{
		final List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		lstUser.forEach(user -> {
			setCell(user.getName(), lstQueryDataRow);
		});
		lstQueryData.add(lstQueryDataRow);
	}
	
	public static void addHeadRole(final List<Role> lstRole, final List<List<ReportTableCell>> lstQueryData)
	{
		final List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		lstRole.forEach(role -> {
			setCell(role.getName(), lstQueryDataRow);
		});
		lstQueryData.add(lstQueryDataRow);
	}
	
	public static void addHeadYearMonthDate(final String strStart, final String strEnd, 
			final List<List<ReportTableCell>> lstQueryData, final List<String> lstStr,
			final int field)
	{
		final List<ReportTableCell> lstQueryDataRow = new ArrayList<ReportTableCell>();
		final Date starD = PubTools.stringToDate(strStart, ComConstant.DATEFORMAT_YMD10);
		final Date endD = PubTools.stringToDate(strEnd, ComConstant.DATEFORMAT_YMD10);
		
		final GregorianCalendar gcs = new GregorianCalendar();
		final GregorianCalendar gce = new GregorianCalendar();
		gcs.setTime(starD);
		gce.setTime(endD);
		
		while(gcs.compareTo(gce) < 1)
		{
			addList(gcs, lstQueryDataRow, lstStr, field);
			gcs.add(field, 1);
		}
		addList(gce, lstQueryDataRow, lstStr, field);
		final int lastIndex = lstStr.size() - 1;
		if(lstStr.get(lastIndex).equals(lstStr.get(lastIndex - 1)))
		{
			lstStr.remove(lastIndex);
			lstQueryDataRow.remove(lastIndex);
		}
		lstQueryData.add(lstQueryDataRow);
	}
	
	public static void formatExcleRowData(final List<List<Object>> list,final List<ReportRow> row)
	{
		for(int index = 0; index < row.size(); index++)
		{
			ReportRow dd = row.get(index);
			list.get(index).add(0, dd.getHname());
		}
	}
	
	public static void formatExcleData(final List<List<Object>> list,final List<String> lstStr, final boolean rowExist, final List<List<Object>> lstData)
	{
		if(!lstStr.isEmpty())
		{
			final List<Object> lstDataRow = newList();
			lstDataRow.addAll(lstStr);
			if(rowExist)
			{
				lstDataRow.add(0, "");
			}
			lstData.add(lstDataRow);
		}
		lstData.addAll(list);
	}
	
	public static void addFirstCell(final List<List<ReportTableCell>> lstQueryData,
			final int headRow, final int rowLength)
	{
		final ReportTableCell cell = new ReportTableCell();
		cell.setValue("");
		cell.setColumnSpan(rowLength);
		cell.setRowSpan(headRow);
		lstQueryData.get(0).add(0, cell);
	}
	
	public static void formatQueryData(final List<List<Object>> list, 
			final List<List<ReportTableCell>> lstQueryData,
			final int headRow, final int rowLength)
	{
		for(int rowIndex = 0; rowIndex < list.size(); rowIndex++)
		{
			final List<ReportTableCell> lstQueryDataRow = lstQueryData.get(rowIndex+headRow);
			final List<Object> listRow = list.get(rowIndex);
			listRow.forEach(data -> {
				setCell(String.valueOf(data), lstQueryDataRow);
			});
		}
	}
	
	private static <E> ArrayList<E> newList()
	{
		return new ArrayList<E>();
	}
	
	private static void setCell(final String Value, final List<ReportTableCell> lstQueryDataRow)
	{
		final ReportTableCell cell = new ReportTableCell();
		cell.setValue(Value);
		cell.setColumnSpan(1);
		cell.setRowSpan(1);
		lstQueryDataRow.add(cell);
	}
	
	private static void addList(final GregorianCalendar gcs, final List<ReportTableCell> lstQueryDataRow, final List<String> lstStr, final int field)
	{
		if(field == Calendar.YEAR)
		{
			lstStr.add(getYear(gcs));
			setCell(getYear(gcs), lstQueryDataRow);
		}else if(field == Calendar.MONTH)
		{
			lstStr.add(getMonth(gcs));
			setCell(getMonth(gcs), lstQueryDataRow);
		}else if(field == Calendar.DATE)
		{
			lstStr.add(getDate(gcs));
			setCell(getDate(gcs), lstQueryDataRow);
		}
	}
	
	private static String getYear(final GregorianCalendar gcs)
	{
		return String.valueOf(gcs.get(Calendar.YEAR));
	}
	
	private static String getMonth(final GregorianCalendar gcs)
	{
		return getYear(gcs) + PubTools.toString(gcs.get(Calendar.MONTH) + 1, 2, "0");
	}
	
	private static String getDate(final GregorianCalendar gcs)
	{
		return getMonth(gcs) + PubTools.toString(gcs.get(Calendar.DATE), 2, "0");
	}
}
