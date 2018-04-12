/**
 * 描述: 
 * BarTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.chart;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ChartConstant;
import com.hua.constant.ext.Meat;
import com.hua.entity.CityFood;
import com.hua.test.BaseTest;
import com.hua.util.ChartUtil;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 柱状图
 * 
 * @author qye.zheng
 * BarTest
 */
public class BarTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		// 3D 开关
		boolean is3D = true;
		// 柱状图 大标题
		CityFood.setTitle("广东省城市食品销售图");
		// x 轴标题
		final String xTtile = "城市";
		// y 轴标题
		final String yTtile = "数量";
		
		final CityFood gz = new CityFood();
		final List<CityFood> cityFoods = new ArrayList<CityFood>();
		gz.setCity("广州");
		gz.setPig(480);
		gz.setBeef(120);
		gz.setChicken(210);
		gz.setFish(260);
		cityFoods.add(gz);
		
		final CityFood fs = new CityFood();
		fs.setCity("佛山");
		fs.setPig(430);
		fs.setBeef(180);
		fs.setChicken(190);
		fs.setFish(310);
		cityFoods.add(fs);
		
		final CityFood sz = new CityFood();
		sz.setCity("深圳");
		sz.setPig(570);
		sz.setBeef(87);
		sz.setChicken(179);
		sz.setFish(242);
		cityFoods.add(sz);
		
		final CityFood zh = new CityFood();
		zh.setCity("珠海");
		zh.setPig(378);
		zh.setBeef(87);
		zh.setChicken(230);
		zh.setFish(301);
		cityFoods.add(zh);
		
		final CityFood zs = new CityFood();
		zs.setCity("中山");
		zs.setPig(390);
		zs.setBeef(95);
		zs.setChicken(187);
		zs.setFish(230);
		cityFoods.add(zs);
		
		// 行/列 二维数组
		double[][] data = new double[Meat.values().length][cityFoods.size()];
		// 肉类
		final String[] columnKeys = { "猪肉", "鸡肉", "牛肉", "鱼肉" };
		// 城市
		//final String[] columnKeys = { "广州", "佛山", "深圳", "珠海", "中山" }; 
		final Set<String> rowKeySet = new HashSet<String>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				// 注入各个城市的肉销售数量
				if (0 == i) {
					// 猪肉
					data[i][j] = cityFoods.get(j).getPig(); 
					rowKeySet.add(cityFoods.get(j).getCity());
					
					continue;
				}
				if (1 == i) {
					// 鸡肉
					data[i][j] = cityFoods.get(j).getChicken(); 
					
					continue;
				}
				if (2 == i) {
					// 牛肉
					data[i][j] = cityFoods.get(j).getBeef(); 
					
					continue;
				}
				if (3 == i) {
					// 鱼肉
					data[i][j] = cityFoods.get(j).getFish(); 
					
					continue;
				}
			}
		}
		try {
		// 创建数据集
		final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(columnKeys,
				(String[]) rowKeySet.toArray(), data);
		
		// 创建 freeChart
		JFreeChart freeChart = null;
		String filename = null;
		if (is3D) {
			// 3D效果图
			 freeChart = ChartFactory.createBarChart3D(CityFood.getTitle(), 
						xTtile, yTtile, dataset, PlotOrientation.VERTICAL, true, true, false);
			 filename = "cityFood-3D_01.png";
		} else {
			// 平面效果图
			 freeChart = ChartFactory.createBarChart(CityFood.getTitle(), 
						xTtile, yTtile, dataset, PlotOrientation.VERTICAL, true, true, false);
			 filename = "cityFood_01.png";
		}
		
		// 设置标题字体，从而解决中文乱码问题
		freeChart.getTitle().setFont(ChartUtil.getDefaultFont(25));
		
		// 设置 x 轴字体，从而解决中文乱码问题
		freeChart.getCategoryPlot().getDomainAxis().setLabelFont(ChartUtil.getDefaultFont(20));
		
		// columnKeys 的字体设置，从而解决中文乱码问题
		freeChart.getCategoryPlot().getDomainAxis().setTickLabelFont(ChartUtil.getDefaultFont(20));
		
		// 设置 y 轴字体，从而解决中文乱码问题
		freeChart.getCategoryPlot().getRangeAxis().setLabelFont(ChartUtil.getDefaultFont(20));
		
		// 设置方块区域的字体，从而解决中文乱码问题
		freeChart.getLegend().setItemFont(ChartUtil.getDefaultFont(20));
		
		final String filePath = ClassPathUtil.getClassSubpath("com/hua/chart/") + filename;
		log.info("test=====> filePath = " + filePath);
		final File file = new File(filePath);
	
			// 保存为图片
			ChartUtilities.saveChartAsJPEG(file, freeChart, ChartConstant.IMAGE_WIDTH, 
					ChartConstant.IMAGE_HEIGHT);
		} catch (Exception e) {
			log.error("testDefaultPie =====> ", e);
		}

	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCityFoodBar() {
		final double[][] data = new double[][] {
				{131, 122, 111, 100, 50}, 
				{720, 700, 680, 640, 50}, 
				{1130, 1020, 980, 800, 50}, 
				{440, 400, 360, 300, 50}
				}; 
		final String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉" };
		final String[] columnKeys = { "广州", "深圳", "东莞", "佛山", "中山" }; 
		// DatasetUtilities.createCategoryDataset(Comparable[], Comparable[], double[][]);
		final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
		/*
		 * ChartFactory.createBarChart3D(title, categoryAxisLabel, valueAxisLabel, dataset, 
		 * orientation, legend, tooltips, urls);
		 */
		// 创建 freeChart
		final JFreeChart freeChart = ChartFactory.createBarChart("广东省城市食品销售图", "类型", "数量", dataset, 
				PlotOrientation.VERTICAL, true, true, false);
		
		// 设置标题字体，从而解决中文乱码问题
		freeChart.getTitle().setFont(ChartUtil.getDefaultFont(25));
		
		// 设置 x 轴字体，从而解决中文乱码问题
		freeChart.getCategoryPlot().getDomainAxis().setLabelFont(ChartUtil.getDefaultFont(20));
		
		// columnKeys 的字体设置，从而解决中文乱码问题
		freeChart.getCategoryPlot().getDomainAxis().setTickLabelFont(ChartUtil.getDefaultFont(20));
		
		// 设置 y 轴字体，从而解决中文乱码问题
		freeChart.getCategoryPlot().getRangeAxis().setLabelFont(ChartUtil.getDefaultFont(20));
		
		// 设置方块区域的字体，从而解决中文乱码问题
		freeChart.getLegend().setItemFont(ChartUtil.getDefaultFont(20));
		
		final String filePath = ClassPathUtil.getClassSubpath("com/hua/chart/") + "bar_01.png";
		log.info("test=====> filePath = " + filePath);
		final File file = new File(filePath);
		try {
			// 保存为图片
			ChartUtilities.saveChartAsJPEG(file, freeChart, ChartConstant.IMAGE_WIDTH, 
					ChartConstant.IMAGE_HEIGHT);
		} catch (IOException e) {
			log.error("testDefaultPie =====> ", e);
		}
		
	}
	
	/**
	 * 
	 * 描述: 普通测试方法
	 ,@Test注解 不带参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNormal() {
		System.out.println("testNormal()");
	}
	
	/**
	 * 
	 * 描述: 期望发生异常-测试方法
	 ,@Test注解 异常
	 * @author qye.zheng
	 * 
	 */
	@Test(expected=NullPointerException.class)
	@SuppressWarnings("all")
	public void testException() {
		String str = null;
		System.out.println(str.length());
	}
	
	/**
	 * 
	 * 描述: 超时测试方法
	 ,@Test注解 指定运行时间 (单位 : 毫秒)
	 * @author qye.zheng
	 * 
	 */
	@Test(timeout=3000)
	public void testTimeOut() {
		System.out.println("testTimeOut()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Ignore("暂时忽略的方法")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@Before
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@After
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
