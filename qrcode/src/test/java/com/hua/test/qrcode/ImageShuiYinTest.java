/**
 * 描述: 
 * ImageShuiYinTest.java
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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.FileUtil;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;
import com.hua.util.QrCodeUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ImageShuiYinTest
 */
public final class ImageShuiYinTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testImageShuiYin() {
		try {
			String path1 = ClassPathUtil.getClassSubpath("/file/二维码_01.jpg", true);
			String path2 = ClassPathUtil.getClassSubpath("/file/二维码_LOGO.png", true);
			//1.jpg是你的 主图片的路径
	        InputStream is = new FileInputStream(path1);
	        
	        //通过JPEG图象流创建JPEG数据流解码器
	        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
	        //解码当前JPEG数据流，返回BufferedImage对象
	        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon imgIcon = new ImageIcon(path2); 
	        
	        //得到Image对象。
	        Image img = imgIcon.getImage();
	       
	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - imgIcon.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - imgIcon.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);
	        
	        //设置颜色。
	        g.setColor(Color.BLACK);
	        
	        
	        //最后一个参数用来设置字体的大小
	        //Font f = new Font("宋体",Font.BOLD,30);
	        
	       // g.setFont(f);
	        
	        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
	      //  g.drawString("默哀555555。。。。。。。",10,30);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        
	        is.close();
	        os.close();
	        
	        System.out.println ("合成结束。。。。。。。。");			
			
		} catch (Exception e) {
			log.error("testImageShuiYin =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testShuiYin() {
		try {
			String path1 = ClassPathUtil.getClassSubpath("/file/test.png", true);
			String path2 = ClassPathUtil.getClassSubpath("/file/二维码_LOGO.png", true);
			//1.jpg是你的 主图片的路径
	        InputStream is = new FileInputStream(path1);
	        
	        //通过JPEG图象流创建JPEG数据流解码器
	        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
	        //解码当前JPEG数据流，返回BufferedImage对象
	        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon imgIcon = new ImageIcon(path2); 
	        
	        //得到Image对象。
	        Image img = imgIcon.getImage();
	       
	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - imgIcon.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - imgIcon.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);
	        
	        //设置颜色。
	        g.setColor(Color.BLACK);
	        
	        
	        //最后一个参数用来设置字体的大小
	        //Font f = new Font("宋体",Font.BOLD,30);
	        
	       // g.setFont(f);
	        
	        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
	      //  g.drawString("默哀555555。。。。。。。",10,30);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        is.close();
	        os.close();
	        
	        System.out.println ("合成结束。。。。。。。。");
		} catch (Exception e) {
			log.error("testShuiYin =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWeiXinBarcode() {
		try {
			/**
			 * 通过 URL 构造对象
			 */
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			URL url = new URL(weixinBarcodeUrl);
			String path2 = ClassPathUtil.getClassSubpath("/file/logo.png", true);
			System.out.println(path2);
			//1.jpg是你的 主图片的路径
	        InputStream is = url.openStream();
	        //通过JPEG图象流创建JPEG数据流解码器
	        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
	        //解码当前JPEG数据流，返回BufferedImage对象
	        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon logoImage = new ImageIcon(FileUtil.getByteArray(new File(path2))); 
	        //得到Image对象
	        Image img = logoImage.getImage();

	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - logoImage.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - logoImage.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);
	        
	        //设置颜色。
	        //g.setColor(Color.BLACK);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output-url.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        os.close();
	        is.close();
		} catch (Exception e) {
			log.error("testWeiXinBarcode =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWeiXinBarcode2() {
		try {
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			URL url = new URL(weixinBarcodeUrl);
			//String path1 = ClassPathUtil.getClassSubpath("/file/二维码_01.jpg", true);
			String path2 = ClassPathUtil.getClassSubpath("/file/二维码_LOGO.png", true);
			//1.jpg是你的 主图片的路径
	        InputStream is = url.openStream();
	        
	        //通过JPEG图象流创建JPEG数据流解码器
	        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
	        //解码当前JPEG数据流，返回BufferedImage对象
	        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon imgIcon = new ImageIcon(path2); 
	        
	        //得到Image对象。
	        Image img = imgIcon.getImage();
	       
	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - imgIcon.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - imgIcon.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);
	    
	        //设置颜色。
	        //g.setColor(Color.BLACK);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output-url.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        is.close();
	        os.close();
		} catch (Exception e) {
			log.error("testWeiXinBarcode2 =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWeiXinBarcode3() {
		try {
			/**
			 * 通过 URL 构造对象
			 */
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			URL url = new URL(weixinBarcodeUrl);
			String path1 = ClassPathUtil.getClassSubpath("/file/二维码_02.jpg", true);
			String path2 = ClassPathUtil.getClassSubpath("/file/logo.png", true);
			
	        BufferedImage buffImg = ImageIO.read(url.openStream());
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon logoImage = new ImageIcon(FileUtil.getByteArray(new File(path2))); 
	        
	        //得到Image对象
	        Image img = logoImage.getImage();

	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - logoImage.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - logoImage.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);
	        
	        //设置颜色。
	        //g.setColor(Color.BLACK);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output-url.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        os.close();
		} catch (Exception e) {
			log.error("testWeiXinBarcode =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWeiXinBarcode4() {
		try {
			/**
			 * 通过 URL 构造对象
			 */
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			URL url = new URL(weixinBarcodeUrl);
			String path1 = ClassPathUtil.getClassSubpath("/file/二维码_02.jpg", true);
			String path2 = ClassPathUtil.getClassSubpath("/file/logo.png", true);
			// 先将图片存到本地
			InputStream inputStream = url.openStream();
			String path4 = ProjectUtil.getAbsolutePath("/doc/output-new.png", true);
			System.out.println(path4);
			byte[] bt = IOUtil.getByteArray(inputStream);
			inputStream.read(bt);
			FileUtil.writeByteArray(new File(path4), bt);
			
			//1.jpg是你的 主图片的路径
	        InputStream is = new FileInputStream(path4);
	        
	        //通过JPEG图象流创建JPEG数据流解码器
	        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
	        //解码当前JPEG数据流，返回BufferedImage对象
	        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
	        //得到画笔对象
	        Graphics g = buffImg.getGraphics();
	        
	        //创建你要附加的图象。
	        //2.jpg是你的小图片的路径
	        ImageIcon logoImage = new ImageIcon(FileUtil.getByteArray(new File(path2))); 
	        BufferedImage bi = ImageIO.read(new File(path2));
	        //得到Image对象
	        Image img = logoImage.getImage();

	        // logo图标的坐标
	        int x =  buffImg.getWidth() / 2 - logoImage.getIconWidth() / 2;
	        int y =  buffImg.getHeight() / 2 - logoImage.getIconHeight() / 2;
	        
	        //将小图片绘到大图片上。
	        //5,300 .表示你的小图片在大图片上的位置。
	        g.drawImage(img,x,y,null);

	        //设置颜色。
	        //g.setColor(Color.BLACK);
	        
	        g.dispose();
	        
			String path3 = ProjectUtil.getAbsolutePath("/doc/output-url.jpg", true);
	        OutputStream os = new FileOutputStream(path3);
	        
	        //创键编码器，用于编码内存中的图象数据。
	        
	        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
	        en.encode(buffImg);
	        
	        os.close();
	        is.close();
		} catch (Exception e) {
			log.error("testWeiXinBarcode =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWeiXinBarcode5() {
		try {
			/**
			 * 通过 URL 构造对象
			 */
			String path2 = ClassPathUtil.getClassSubpath("/file/logo.png", true);
			String path3 = ProjectUtil.getAbsolutePath("/doc/output-url2.jpg", true);
			OutputStream outputStream = new FileOutputStream(path3);
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			QrCodeUtil.encode(weixinBarcodeUrl, path2, outputStream, true);
			
		} catch (Exception e) {
			log.error("testWeiXinBarcode =====> ", e);
		}
	}		
	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSaveImage() {
		try {
			/**
			 * 通过 URL 构造对象
			 */
			String weixinBarcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG68DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JIWE04SXZsS25KVHVwNDFsVnMyAAIE6f4JVgMEgDoJAA==";
			URL url = new URL(weixinBarcodeUrl);
	        //2.jpg是你的小图片的路径
	        ImageIcon logoImage = new ImageIcon(url); 
			
	        
		} catch (Exception e) {
			log.error("testSaveImage =====> ", e);
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
