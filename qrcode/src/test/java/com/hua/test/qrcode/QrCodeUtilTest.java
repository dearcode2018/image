/**
 * 描述: 
 * QrCodeUtilTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.qrcode;

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

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;
import com.hua.util.QrCodeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * QrCodeUtilTest
 */
public final class QrCodeUtilTest extends BaseTest {
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecode() {
		try {
			String path = ProjectUtil.getAbsolutePath("/doc/qrcode1.jpg", true);
			String content  = QrCodeUtil.decode(path);
			System.out.println(content);
		} catch (Exception e) {
			log.error("testDecode =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecode2() {
		try {
			//String path = ProjectUtil.getAbsolutePath("/doc/微信-二维码-Me.png", true);
			String path = ProjectUtil.getAbsolutePath("/doc/QQ截图20160126153136.jpg", true);
			String content  = QrCodeUtil.decode(path);
			System.out.println(content);
		} catch (Exception e) {
			log.error("testDecode2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDecode3() {
		try {
			//String path = ProjectUtil.getAbsolutePath("/doc/微信-二维码-Me.png", true);
			String uri = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGX8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzAwemVQaVBsQUUxNTlXNlRYbUtHAAIEL6rzVgMEgDoJAA==";
			URL url = new URL(uri);
			String content  = QrCodeUtil.decode(url.openStream());
			System.out.println(content);
		} catch (Exception e) {
			log.error("testDecode3 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEncode1() {
		try {
			String logoPath = ProjectUtil.getAbsolutePath("/doc/logo.png", true);
			String destPath = ProjectUtil.getAbsolutePath("/doc/qrcode1.jpg", true);
			String content = "https://www.baidu.com";
			boolean needCompress = true;
			QrCodeUtil.encode(content, logoPath, destPath, needCompress);
		} catch (Exception e) {
			log.error("testEncode1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEncode2() {
		try {
			String logoPath = ProjectUtil.getAbsolutePath("/doc/logo.png", true);
			String destPath = ProjectUtil.getAbsolutePath("/doc/qrcode2.jpg", true);
			OutputStream outputStream = new FileOutputStream(destPath);
			String content = "https://www.baidu.com";
			boolean needCompress = true;
			QrCodeUtil.encode(content, logoPath, outputStream, needCompress);
			
		} catch (Exception e) {
			log.error("testEncode2 =====> ", e);
		}
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
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			String destPath = ProjectUtil.getAbsolutePath("/doc/qrcode.jpg", true);
			OutputStream outputStream = new FileOutputStream(destPath);
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
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
