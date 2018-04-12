/**
 * 描述: 
 * BookSale.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import com.hua.constant.ext.Month;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * BookSale
 */
public final class BookSale extends ChartEntity {
	
	private static String title;
	
	/* 书名 */
	private String bookName;
	
	/* 本月销量 */
	private double sales;
	
	/* 月份 */
	private Month month;
	
	/** 无参构造方法 */
	public BookSale() {}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the sales
	 */
	public double getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	/**
	 * @return the month
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Month month) {
		this.month = month;
	}
	
}
