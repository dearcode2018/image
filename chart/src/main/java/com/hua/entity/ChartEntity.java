/**
 * 描述: 
 * ChartEntity.java
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
 * ChartEntity
 */
public class ChartEntity extends BaseEntity {
	
	private static String title;

	/**
	 * @return the title
	 */
	public static String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public static void setTitle(String title) {
		ChartEntity.title = title;
	}
	
}
