package com.hbcsoft.common.model;

/*import org.apache.commons.lang3.builder.ToStringBuilder;*/

/**
 * easyui动态列column模型.
 * 
 */
public class Column {
	/**
	 * 字段名称
	 */
	private String field;
	/**
	 * 显示标题
	 */
	private String title;
	/**
	 * 宽度
	 */
	private Integer width;
	/**
	 * 跨行数
	 */
	private Integer rowspan;
	/**
	 * 跨列数
	 */
	private Integer colspan;
	/**
	 * 是否选checkbox
	 */
	private Boolean checkbox;

	/**
	 * 索引
	 */
	private Integer index;
	/**
	 * 对齐方式(默认：'left',可选值：'left'，'right'，'center' 默认左对齐)
	 */
	private String align = "left";
	/**
	 * 列
	 * @param index
	 * @param field
	 * @param title
	 * @param width
	 * @param align
	 */
	public Column(final Integer index,final String field,final String title,final Integer width, // 列
			final String align) {
		super();
		this.index = index;
		this.field = field;
		this.title = title;
		this.width = width;
		this.align = align;
	}

	/**
	 * 字段名称
	 */
	public String getField() {
		return field;
	}

	public Column setField(String field) { // NOPMD by Administrator on 16-11-25 上午9:35
		this.field = field;
		return this;
	}

	/**
	 * 显示标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置显示标题
	 */
	public Column setTitle(final String title) {
		this.title = title;
		return this;
	}

	/**
	 * 宽度
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * 设置宽度
	 */
	public Column setWidth(Integer width) { // NOPMD by Administrator on 16-11-25 上午9:34
		this.width = width;
		return this;
	}

	/**
	 * 跨行数
	 */
	public Integer getRowspan() {
		return rowspan;
	}

	/**
	 * 设置跨行数
	 */
	public Column setRowspan(final Integer rowspan) {
		this.rowspan = rowspan;
		return this;
	}

	/**
	 * 跨列数
	 */
	public Integer getColspan() {
		return colspan;
	}

	/**
	 * 设置跨列数
	 */
	public Column setColspan(final Integer colspan) {
		this.colspan = colspan;
		return this;
	}

	/**
	 * 是否选中checkbox
	 */
	public boolean isCheckbox() {
		return checkbox;
	}

	/**
	 * 设置是否选中
	 */
	public Column setCheckbox(final boolean checkbox) {
		this.checkbox = checkbox;
		return this;
	}

	/**
	 * 对齐方式(默认：'left',可选值：'left'，'right'，'center' 默认左对齐)
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * 设置对齐方式(可选值：'left'，'right'，'center' 默认左对齐)
	 */
	public Column setAlign(final String align) {
		this.align = align;
		return this;
	}

	/**
	 * 索引值
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置索引值
	 * 
	 * @param index
	 */
	public Column setIndex(final Integer index) {
		this.index = index;
		return this;
	}

	/*@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}*/
}