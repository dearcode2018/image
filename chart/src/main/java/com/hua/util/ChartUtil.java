/**
 * 描述: 
 * ChartUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.awt.Font;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * ChartUtil
 */
public final class ChartUtil {
	
	
	
	/** 无参构造方法 */
	private ChartUtil() {}
	
	/**
	 * 
	 * 描述: 获取Pie数据集
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static DefaultPieDataset getDefaultPieDataset() {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		
		return dataset;
	}
	
	/**
	 * 
	 * 描述: 获取Pie数据集
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static DefaultCategoryDataset getDefaultCategoryDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		return dataset;
	}
	
	/**
	 * 
	 * 描述: 获取默认字体 
	 * @author qye.zheng
	 * 
	 * @param fontSize 字体大小
	 * @return
	 */
	public static Font getDefaultFont(int fontSize) {
		final Font font = new Font(Font.SERIF, Font.PLAIN, fontSize);
		
		return font;
	}
	
	

	
}
