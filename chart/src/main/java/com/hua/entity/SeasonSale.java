/**
 * 描述: 
 * SeasonSale.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import com.hua.constant.ext.Season;
import com.hua.entity.BaseEntity;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SeasonSale
 */
public final class SeasonSale extends BaseEntity {

	/* 季节 */
	private Season season;
	
	/* 销售额 */
	private double sale;

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * @return the sale
	 */
	public double getSale() {
		return sale;
	}

	/**
	 * @param sale the sale to set
	 */
	public void setSale(double sale) {
		this.sale = sale;
	}
	
}
