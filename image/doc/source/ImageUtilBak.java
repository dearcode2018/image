/**
 * ImageUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.hua.constant.Constant;
import com.hua.util.FileNameUtil;
import com.hua.util.IOUtil;

/**
 * ImageUtil
 * 描述: 图片技术 - 工具类
 * @author  qye.zheng
 */
public final class ImageUtilBak
{

	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private ImageUtilBak()
	{
	}
	
	/**
	 * 
	 * 描述: 按比例裁剪并缩放图片
	 * @author qye.zheng
	 * @param srcPath
	 * @param outPath
	 * @param width 目标宽度
	 * @param height 目标高度
	 */
     public static final void cutAndZoom(final String srcPath, final String outPath, 
    		 final int width, final int height)
     { 
    	 OutputStream outputStream = null;
		try
		{
			final File file = new File(srcPath);	
			// 缓冲图片处理
			final BufferedImage bufferedImage = createBufferedImage(width, height, file);
			// 图片实例对象 ( 标量实例 / 平滑)
			final Image image = bufferedImage.getScaledInstance(width,
					height, Image.SCALE_SMOOTH);
			// 文件后缀名 (图片格式)
			final String suffix = FileNameUtil.getSuffix(file.getName());
			outputStream = new FileOutputStream(outPath);
			zoomImage(outputStream, image, suffix);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
     }
	
	/**
	 * 
	 * 描述: 按比例缩放图片
	 * @author qye.zheng
	 * @param outputStream
	 * @param outputImage
	 * @param suffix
	 */
 	private static final void zoomImage(final OutputStream outputStream, 
 			final Image outputImage, final String suffix)
 	{
 		// 空的 图片观察者
 		final ImageObserver imageObserver = null;
 		// 输出宽度
 		final int outputWidth = outputImage.getWidth(imageObserver);
 		if (outputWidth < Constant.ONE)
 		{
 			throw new IllegalArgumentException("output image width " + outputWidth + " is out of range");
 		}
 		
 		// 输出高度
 		final int outputHeight = outputImage.getHeight(imageObserver);
 		if (outputHeight < Constant.ONE)
 		{
 			throw new IllegalArgumentException("output image height " + outputHeight + " is out of range");
 		}

 		// Get a buffered image from the image.
 		final BufferedImage bufferedImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);
 		// 
 		final Graphics2D graphics2D = bufferedImage.createGraphics();
 		graphics2D.drawImage(outputImage, Constant.ZERO, Constant.ZERO, imageObserver);
 		outputImage.flush();
 		try
		{
 			// 输出
 			ImageIO.write(bufferedImage, suffix, outputStream);
 	 		outputStream.flush();
 	 		bufferedImage.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally 
		{
			IOUtil.close(outputStream);
		}
 	}
	
 	/**
 	 * 
 	 * 描述: 创建图片缓冲对象
 	 * @author qye.zheng
 	 * @param width
 	 * @param height
 	 * @param file
 	 * @return
 	 */
	private static final BufferedImage createBufferedImage(final int width, 
			final int height, final File file)
	{
		if (!file.exists())
		{
			throw new RuntimeException("file is not find:"+file.getPath());
		}
		// 输出图片 对象
		BufferedImage outputImage = null;
		try
		{
			final BufferedImage inputImage = ImageIO.read(file);
			// 创建一个矩形
			final Rectangle rectangle = createRectangle(inputImage.getWidth(), 
					inputImage.getHeight(), width, height);
			// 获取子图片 (缩放之后的图片)
			outputImage = inputImage.getSubimage(rectangle.x, rectangle.y, 
					rectangle.width, rectangle.height);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return outputImage;
	}
	
	/**
	 * 
	 * 描述: 创建图片的宽度和高度, x, y 坐标值
	 * @author qye.zheng
	 * @param inputWidth
	 * @param inputHeight
	 * @param outputWidth
	 * @param outputHeight
	 * @return
	 */
	private static final Rectangle createRectangle(final int inputWidth, final int inputHeight, 
			final int outputWidth, final int outputHeight)
	{
		// 计算缩放后的宽度
		final int tempWidth =  inputHeight * outputWidth / outputHeight;
		// 取最小值
		final int afterWidth = (tempWidth > inputWidth) ? inputWidth : tempWidth;
		// x坐标 (原值和新值 之差 减半)
		final int x = (inputWidth - afterWidth) / Constant.TWO;
		
		// 计算缩放后的高度
		final int tempHeight = inputWidth * outputHeight / outputWidth;
		// 取最小值
		final int afterHeight = (tempHeight > inputHeight) ? inputHeight : tempHeight;
		
		// y坐标 (原值和新值 之差 减半)
		final int y = (inputHeight - afterHeight) / Constant.TWO;
		// 构造矩形对象
		final Rectangle rectangle = new Rectangle(x, y, afterWidth, afterHeight);
		
		return rectangle;
	} 
 	
}
