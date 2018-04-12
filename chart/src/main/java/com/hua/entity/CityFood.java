/**
 * 描述: 
 * CityFood.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import com.hua.entity.BaseEntity;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CityFood
 */
public final class CityFood extends ChartEntity {
	
	/* 城市 */
	private String city;
	
	/* 猪肉销量 */
	private double pig;
	
	/* 鱼肉销量 */
	private double fish;
	
	/* 鸡肉销量 */
	private double chicken;
	
	/* 牛肉销量 */
	private double beef;

	/** 无参构造方法 */
	public CityFood() {}
	
	/** 有参构造方法 */
	public CityFood(final String city) {
		this.city = city;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pig
	 */
	public double getPig() {
		return pig;
	}

	/**
	 * @param pig the pig to set
	 */
	public void setPig(double pig) {
		this.pig = pig;
	}

	/**
	 * @return the fish
	 */
	public double getFish() {
		return fish;
	}

	/**
	 * @param fish the fish to set
	 */
	public void setFish(double fish) {
		this.fish = fish;
	}

	/**
	 * @return the chicken
	 */
	public double getChicken() {
		return chicken;
	}

	/**
	 * @param chicken the chicken to set
	 */
	public void setChicken(double chicken) {
		this.chicken = chicken;
	}

	/**
	 * @return the beef
	 */
	public double getBeef() {
		return beef;
	}

	/**
	 * @param beef the beef to set
	 */
	public void setBeef(double beef) {
		this.beef = beef;
	}
	
}
