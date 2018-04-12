/**
 * 描述: 
 * BaseTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test;

// 静态导入
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.hua.log.BaseLog;

/**
 * 描述: 测试基类
 * 包含多个测试示例
 * 
 * @author qye.zheng
 * BaseTest
 */
//@RunWith()
public class BaseTest extends BaseLog {
	
	// 文件对象 
    public File file = null; 
    
    // 输入图路径
    public String inputFilePath; 
    
    // 输出图路径
    public String outputFilePath; 
    
    // 输入图文件名
    public String inputFileName; 
    
    // 输出图文件名
    public String outputFileName; 
    
    // 默认输出图片宽
    public int outputWidth = 100; 
    
    // 默认输出图片高
    public int outputHeight = 100; 
    
    // 是否等比缩放标记(默认为等比缩放)
    public boolean proportion = true; 
	
    public static final boolean project = true;
    
    public static String suffix = "-handled";
    
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

}
