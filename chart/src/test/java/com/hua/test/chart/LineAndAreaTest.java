/**
 * 描述: 
 * LineAndAreaTest.java
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ChartConstant;
import com.hua.constant.ext.Month;
import com.hua.entity.BookSale;
import com.hua.test.BaseTest;
import com.hua.util.ChartUtil;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 曲线图
 * 
 * @author qye.zheng
 * LineAndAreaTest
 */
public class LineAndAreaTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			// 3D 开关
			boolean is3D = true;
			String filename = null;
			// 标题
			BookSale.setTitle("全年书籍销售图");
			
			// 分类数据集
			final DefaultCategoryDataset dataset = ChartUtil.getDefaultCategoryDataset();
			final List<BookSale> sales = getBookSales();
			BookSale sale = null;
			for (int i = 0; i < sales.size(); i++) {
				sale = sales.get(i);
				dataset.addValue(sale.getSales(), sale.getBookName(), sale.getMonth().getName());
			}
			
			// 创建 freeChart
			JFreeChart freeChart = null;
			if (is3D) {
				freeChart =  ChartFactory.createLineChart3D(BookSale.getTitle(), "月份", "数量", 
						  dataset, PlotOrientation.VERTICAL, true, true, true);
				filename = "line-graph-3D.png";
			} else {
				freeChart =  ChartFactory.createLineChart(BookSale.getTitle(), "月份", "数量", 
						  dataset, PlotOrientation.VERTICAL, true, true, true);
				filename = "line-graph.png";
			}
			

			// 设置标题字体，从而解决中文乱码问题
			freeChart.getTitle().setFont(ChartUtil.getDefaultFont(25));
			
			// 设置 x 轴 标签字体，从而解决中文乱码问题
			freeChart.getCategoryPlot().getDomainAxis().setLabelFont(ChartUtil.getDefaultFont(20));
			
			// 设置 x 轴字体，从而解决中文乱码问题
			freeChart.getCategoryPlot().getDomainAxis().setTickLabelFont(ChartUtil.getDefaultFont(15));
			
			// 设置 y 轴字体，从而解决中文乱码问题
			freeChart.getCategoryPlot().getRangeAxis().setLabelFont(ChartUtil.getDefaultFont(20));
			
			// 设置方块区域的字体，从而解决中文乱码问题
			freeChart.getLegend().setItemFont(ChartUtil.getDefaultFont(20));
			
			// 生成图片
			final String filePath = ClassPathUtil.getClassSubpath("com/hua/chart/") + filename;
			final File file = new File(filePath);
			ChartUtilities.saveChartAsJPEG(file, freeChart, ChartConstant.IMAGE_WIDTH + 200, 
					ChartConstant.IMAGE_HEIGHT);
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	private List<BookSale> getBookSales() {
		final List<BookSale> sales = new ArrayList<BookSale>();
		BookSale sale = null;
		final Random random = new Random();
		for (int i = 1; i <= 12; i++) {
			sale = new BookSale();
			sale.setBookName("Java");
			sale.setMonth(Month.values()[i - 1]);
			sale.setSales(random.nextDouble());
			sales.add(sale);
			
			sale = new BookSale();
			sale.setBookName("C++");
			sale.setMonth(Month.values()[i - 1]);
			sale.setSales(random.nextDouble());
			sales.add(sale);
			
			sale = new BookSale();
			sale.setBookName("C#");
			sale.setMonth(Month.values()[i - 1]);
			sale.setSales(random.nextDouble());
			sales.add(sale);
		}
		
		return sales;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
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
