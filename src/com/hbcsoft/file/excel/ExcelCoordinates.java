package com.hbcsoft.file.excel;
/**
 * excel坐标对象
 * @author songliang
 * @since 2016-12-19
 */
public class ExcelCoordinates {
	/**
	 * 开始行
	 */
	private int startRow;
	/**
	 * 开始列
	 */
	private int startColumn;
	/**
	 * 特殊行
	 */
	private String specialRow = "";
	/**
	 * 特殊列
	 */
	private String specialColumn = "";
	
	/**
	 * 主标题起始行
	 */
	private int mainTitleStartRow;
	
	/**
	 * 主标题起始列
	 */
	private int mainTitleStartColumn;
	
	/**
	 * 副标题起始行
	 */
	private int subtitleStartRow;
	
	/**
	 * 副标题起始列
	 */
	private int subtitleStartColumn;

	/**
	 * 返回开始行
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}
	/**
	 * 设置开始行
	 * @param startRow
	 */
	public void setStartRow(final int startRow) {
		this.startRow = startRow;
	}
	/**
	 * 返回开始列
	 * @return
	 */
	public int getStartColumn() {
		return startColumn;
	}
	/**
	 * 设置开始列
	 * @param startColumn
	 */
	public void setStartColumn(final int startColumn) {
		this.startColumn = startColumn;
	}
	/**
	 * 返回特殊行
	 * @return
	 */
	public String getSpecialRow() {
		return specialRow;
	}
	/**
	 * 设置特殊行
	 * @param specialRow
	 */
	public void setSpecialRow(final String specialRow) {
		this.specialRow = specialRow;
	}
	/**
	 * 返回特殊列
	 * @return
	 */
	public String getSpecialColumn() {
		return specialColumn;
	}
	/**
	 * 设置特殊列
	 * @param specialColumn
	 */
	public void setSpecialColumn(final String specialColumn) {
		this.specialColumn = specialColumn;
	}
	/**
	 * 返回主标题起始行
	 * @return
	 */
	public int getMainTitleStartRow() {
		return mainTitleStartRow;
	}
	/**
	 * 设置主标题起始行
	 * @param mainTitleStartRow
	 */
	public void setMainTitleStartRow(final int mainTitleStartRow) {
		this.mainTitleStartRow = mainTitleStartRow;
	}
	/**
	 * 返回主标题起始列
	 * @return
	 */
	public int getMainTitleStartColumn() {
		return mainTitleStartColumn;
	}
	/**
	 * 设置主标题起始列
	 * @param mainTitleStartColumn
	 */
	public void setMainTitleStartColumn(final int mainTitleStartColumn) {
		this.mainTitleStartColumn = mainTitleStartColumn;
	}
	/**
	 * 返回副标题起始行
	 * @return
	 */
	public int getSubtitleStartRow() {
		return subtitleStartRow;
	}
	/**
	 * 设置副标题起始行
	 * @param subtitleStartRow
	 */
	public void setSubtitleStartRow(final int subtitleStartRow) {
		this.subtitleStartRow = subtitleStartRow;
	}
	/**
	 * 返回副标题起始列
	 * @return
	 */
	public int getSubtitleStartColumn() {
		return subtitleStartColumn;
	}
	/**
	 * 设置副标题起始列
	 * @param subtitleStartColumn
	 */
	public void setSubtitleStartColumn(final int subtitleStartColumn) {
		this.subtitleStartColumn = subtitleStartColumn;
	}
	
}
