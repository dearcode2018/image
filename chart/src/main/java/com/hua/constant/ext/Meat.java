/**
 * 描述: 
 * Meat.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

/**
 * 描述: 肉类食品
 * 
 * @author qye.zheng
 * Meat
 */
public enum Meat {

	PIG("猪肉"), 
	
	FISH("鱼肉"),
	
	CHICKEN("鸡肉"),
	
	BEFF("牛肉");
	
	private String name;
	
	/* 构造方法 私有化 */
	private Meat(final String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
