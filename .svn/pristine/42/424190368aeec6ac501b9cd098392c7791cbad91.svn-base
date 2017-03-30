package com.hbcsoft.common.model;

//import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import org.apache.commons.lang3.builder.ToStringBuilder;*/

/**
 * easyui分页组件datagrid、combogrid数据模型.
 * 
 */
@SuppressWarnings("serial")
public class Datagrid<T> implements Serializable {
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 动态列
	 */
	private List<Column> columns =new ArrayList<Column>();
	/**
	 * 列表行
	 */
	private List<T> rows;
	/**
	 * 脚列表
	 */
	private List<Map<String, Object>> footer;
	/**
	 * Datagrid
	 */
	public Datagrid() {
		super();
	}

	/**
	 * 
	 * @param total
	 *总记录数
	 * @param rows
	 *列表行
	 */
	public Datagrid(final long total, final List<T> rows) {
		this(total, null, rows, null);
	}
	/**
	 * 构造方法
	 * @param total
	 * @param columns
	 * @param rows
	 * @param footer
	 */
	public Datagrid(final long total,final  List<Column> columns,final  List<T> rows,
			final List<Map<String, Object>> footer) {
		super();
		this.total = total;
		this.columns = columns;
		this.rows = rows;
		this.footer = footer;
	}

	/**
	 * 总记录数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param total
	 * 总记录数
	 */
	public Datagrid<T> setTotal( final long total) {
		this.total = total;
		return this;
	}

	/**
	 * 列表行
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 设置列表行
	 * 
	 * @param rows
	 * 列表行
	 */
	public Datagrid<T> setRows(final List<T> rows) {
		this.rows = rows;
		return this;
	}

	/**
	 * 脚列表
	 */
	public List<Map<String, Object>> getFooter() {
		return footer;
	}

	/**
	 * 设置脚列表
	 * 
	 * @param footer
	 * 脚列表
	 */
	public Datagrid<T> setFooter(final List<Map<String, Object>> footer) {
		this.footer = footer;
		return this;
	}

	/**
	 * 动态列
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * 设置动态列
	 * 
	 * @param columns
	 * 动态列
	 */
	public Datagrid<T> setColumns(final List<Column> columns) {
		this.columns = columns;
		return this;
	}

	/*@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}*/
}
