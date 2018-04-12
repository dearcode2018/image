/**
 * 描述: 
 * PieChartTest.java
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ChartConstant;
import com.hua.constant.ext.Season;
import com.hua.entity.SeasonSale;
import com.hua.test.BaseTest;
import com.hua.util.ChartUtil;
import com.hua.util.ClassPathUtil;


/**
 * 描述: 饼图 (平面/立体)
 * 
 * @author qye.zheng
 * PieChartTest
 */
public class PieChartTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSeasonSale() {
		try {
		// 3D 开关
		boolean is3D = true;
		final String title = "四季销售图";
		// 春季
		final SeasonSale springSale = new SeasonSale();
		springSale.setSale(2.0);
		springSale.setSeason(Season.SPRING);
		
		// 夏季
		final SeasonSale summerSale = new SeasonSale();
		summerSale.setSale(4.0);
		summerSale.setSeason(Season.SUMMER);
		
		
		// 秋季
		final SeasonSale autumnSale = new SeasonSale();
		autumnSale.setSale(3.0);
		autumnSale.setSeason(Season.AUTUMN);
		
		// 冬季
		final SeasonSale winterSale = new SeasonSale();
		winterSale.setSale(1.0);
		winterSale.setSeason(Season.WINTER);
		
		// 数据集 (默认饼数据集)
		final DefaultPieDataset dataset = ChartUtil.getDefaultPieDataset();
		dataset.setValue(springSale.getSeason().getName(), springSale.getSale());
		dataset.setValue(summerSale.getSeason().getName(), summerSale.getSale());
		dataset.setValue(autumnSale.getSeason().getName(), autumnSale.getSale());
		dataset.setValue(winterSale.getSeason().getName(), winterSale.getSale());
		
		// 创建 freeChart
		// ChartFactory.createPieChart(title, dataset, legend, tooltips, urls);
		JFreeChart freeChart = null;
		if (is3D) {
			freeChart =  ChartFactory.createPieChart3D(title, dataset, true, true, true);
		} else {
			freeChart =  ChartFactory.createPieChart(title, dataset, true, true, true);
		}
		
		// 设置标题文本的字体，从而解决中文乱码问题
		freeChart.getTitle().setFont(ChartUtil.getDefaultFont(25));
		
		// 设置图片中文本的字体，从而解决中文乱码问题
		// 强转成PiePlot 然后再设置
		final PiePlot plot = (PiePlot) freeChart.getPlot();
		plot.setLabelFont(ChartUtil.getDefaultFont(20));
		
		// 设置方块区域的字体，从而解决中文乱码问题
		freeChart.getLegend().setItemFont(ChartUtil.getDefaultFont(20));
		String filename = null;
		if (is3D) {
			filename =  "seasonSale-Pie-3D.png";
		} else {
			filename =  "seasonSale-Pie.png";
		}
		// 生成图片
		final String filePath = ClassPathUtil.getClassSubpath("com/hua/chart/") + filename;
		final File file = new File(filePath);
		
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
	public void testDefaultPie() {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("管理员", 22);
		dataset.setValue("市场人员", 56);
/*		pd.setValue("开发人员", 35);
		pd.setValue("后勤人员", 15);
		pd.setValue("测试人员", 12);
		pd.setValue("财务人员", 26);*/
		final JFreeChart freeChart =  ChartFactory.createPieChart("组织架构图", dataset, true, true, true);
		// 生成图片
		String filePath = ClassPathUtil.getClassSubpath("com/hua/chart/") + "pie.png";
		File file = new File(filePath);
		try {
			ChartUtilities.saveChartAsJPEG(file, freeChart, ChartConstant.IMAGE_WIDTH, 
					ChartConstant.IMAGE_HEIGHT);
			
		} catch (IOException e) {
			log.error("testDefaultPie =====> ", e);
		}
	/*	final ChartFrame frame = new ChartFrame("公司组织架构图", freeChart, true);
		frame.pack();
		frame.setVisible(true);
		log.info("testDefaultPie =====> wait _ ");*/
	}
	
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		
		
		
		
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
