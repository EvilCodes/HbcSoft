package com.hbcsoft.common.model;

import java.util.LinkedList;
import java.util.List;


/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 注意所有序号从1开始.
 * @param <T> Page中记录的类型.
 * @author 尔演&Eryan eryanwcp@gmail.com
 */
public class Pager<T> {
	//-- 公共变量 --//
	/**
	 * 升序
	 */
	public static final String ASC = "asc";
	/**
	 * 降序
	 */
	public static final String DESC = "desc";
	/**
	 * 默认页数大小
	 */
	public static final int DEFAULT_PAGESIZE = 15;
	/**
	 * pageNoi
	 */
	private static final int PAGENOI = 1; 
	//-- 分页参数 --//
	/**
	 * pageNo
	 */
	protected static int pageNos = 1;
	/**
	 * pageSize
	 */
	protected int pageSize = -1;
	/**
	 * orderBy
	 */
	//protected String orderBy = null;
	/**
	 * order
	 */
	//protected String order = null;
	/**
	 * autoCount
	 */
	protected static boolean autoCount1 = true;
	/**
	 * 返回结果
	 */
	protected List<T> result = new LinkedList<T>();
	/**
	 * totalCount
	 */
	protected long totalCount = -1;
	/**
	 * sumPage
	 */
	protected long sumPage=1;
	
	/**
	 * 获取总页数
	 * @return
	 */
	public long getSumPage() {
		return sumPage;
	}
	/**
	 * 设置总页数
	 * @param sumPage
	 */
	public void setSumPage(final long sumPage) {
		this.sumPage = sumPage;
	}
	 /**
	 * 总页数
	 */
	public void countSumPage(){
		final Long tp = totalCount/pageSize;
		if(totalCount%pageSize==0){
			sumPage = tp; 	
		}else{
			sumPage =tp+1;
		}
		//sumPage = totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1;
	}
	
	
	/**
	 * 构造方法
	 */
	public Pager() {
		super();
	}
	 /**
	 * 页
	 * @param pageSize
	 */
	public Pager(final int pageSize) {
		this.pageSize = pageSize;
	}

	//-- 分页参数访问函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNos;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	
	public void setPageNo(final int pageNo) {
		pageNos = pageNo;
		
		if (pageNo < PAGENOI) {
			pageNos = 1;
		}
	}
	

	
	/**
	 * 返回Page对象自身的setPageNo函数,可用于连续设置。
	 */
	public Pager<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量, 默认为-1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 返回Page对象自身的setPageSize函数,可用于连续设置。
	 */
	public Pager<T> pageSizes(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
	final int pn=pageNos - 1;
	final int ps=pn*pageSize;
	final int pnps=ps + 1;
		//return ((pageNo - 1) * pageSize) + 1;
	return pnps;
	}

	/**
	 * 获得排序字段,无默认值. 多个排序字段时用','分隔.
	 */
	/*public String getOrderBy() {
		return orderBy;
	}
*/
	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	/*public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}*/

	/**
	 * 返回Page对象自身的setOrderBy函数,可用于连续设置。
	 */
	/*public Pager<T> orderBys(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}
*/
	/**
	 * 获得排序方向, 无默认值.
	 */
	/*public String getOrder() {
		return  order;
	}*/

	/**
	 * 设置排序方式向.
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	/*public void setOrder(final String order) {
		
	  final	String lowcaseOrder = PubTools.lowerCase(order);

		//检查order字符串的合法值
		final String[] orders = PubTools.split(lowcaseOrder, ',');*//*
		for (final String orderStr : orders) {
			if (!PubTools.equals(DESC, orderStr) && !PubTools.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");*/
			/*}
		}
		this.order = lowcaseOrder;
	}*/

	/**
	 * 返回Page对象自身的setOrder函数,可用于连续设置。
	 */
	/*public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	*//**
	 * 是否已设置排序字段,无默认值.
	 *//*
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}*/

	/**
	 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount1;
	}

	/**
	 * 设置查询对象时是否自动先执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		autoCount1 = autoCount;
	}

	/**
	 * 返回Page对象自身的setAutoCount函数,可用于连续设置。
	 */
	public Pager<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	//-- 访问查询结果函数 --//

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		long count =0;
		if (totalCount < 0) {
			 count = -1;
			//return count;
		}
			
		 //count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count = totalCount / pageSize;
			 count++;
			}
		return count;
	
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		final int pn1=pageNos + 1;
		//return (pageNo + 1 <= getTotalPages());
		return pn1 <= getTotalPages();
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		int page1=0;
		if (isHasNext()) {
			page1= pageNos + 1;
		} else {
			page1= pageNos;
		}
		return page1;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		 final int pagenos = pageNos - 1; 
		//return (pageNos - 1 >= 1);
		 return pagenos>=1;
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		 int result1=0 ;
		if (isHasPre()) {
			result1= pageNos - 1;			
		} else {
			result1= pageNos;
		}
		return result1;
	}
}
