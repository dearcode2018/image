package com.hua.util.temp;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * @author 朱周城
 * @version 1.0
 * @date 2014年6月26日上午10:30:23
 */
public class ImageUtils
{
	/**
	 * 按比例裁剪并缩放图片
	 * @param srcPath
	 * @param outPath
	 * @param width 要裁剪的宽度
	 * @param height 要裁剪的高度
	 * @throws IOException
	 */
     public static void cutAndZoom(String srcPath, String outPath, int width, int height)  throws  IOException { 
    	 OutputStream outputStream = null;
    		try
			{
				File file = new File(srcPath);	
				BufferedImage outImage = createBufferedImage(width, height, file);
				Image outputImage = outImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
				String format = getFileFormat(file.getName());
				outputStream = new FileOutputStream(outPath);
				zoomImage(outputStream, outputImage, format);
				outputStream.close();
			} catch (IOException e)
			{
				throw e;
			}finally{
				if (outputStream != null)
				{
					outputStream.close();
				}
			}
     }
     
     /**
      * 创建要裁剪图片的缓冲对象
      * @param scaleW
      * @param scaleH
      * @param file
      * @return
      * @throws IOException
      */
	private static BufferedImage createBufferedImage(int scaleW, int scaleH, File file) throws IOException
	{
		if (!file.exists())
		{
			throw new RuntimeException("file is not find:"+file.getPath());
		}

		BufferedImage sourceImage = ImageIO.read(file);
		Rectangle rec = createRectangle(sourceImage.getWidth(),sourceImage.getHeight(),scaleW, scaleH);
		BufferedImage outImage = sourceImage.getSubimage(rec.x, rec.y, rec.width, rec.height);
		return outImage;
	}
     
     /**
 	 * 按比例裁剪图片
 	 * @param srcPath
 	 * @param outPath
 	 * @param scaleWidth 宽比例
 	 * @param scaleHeight 高比例
 	 * @throws IOException
 	 */
      public static void cut(String srcPath,String outPath, int scaleW, int scaleH)  throws  IOException { 
     		File file = new File(srcPath);	
     		BufferedImage outImage = createBufferedImage(scaleW, scaleH, file);
 			ImageIO.write(outImage, getFileFormat(file.getName()), new File(outPath));
      }
 	/**
 	 * 按比例缩放图片
 	 * @param outputStream
 	 * @param outputImage
 	 * @param format
 	 * @throws java.io.IOException
 	 */
 	private static void zoomImage(OutputStream outputStream, Image outputImage, String format) throws java.io.IOException
 	{
 		int outputWidth = outputImage.getWidth(null);
 		if (outputWidth < 1)
 			throw new IllegalArgumentException("output image width " + outputWidth + " is out of range");
 		int outputHeight = outputImage.getHeight(null);
 		if (outputHeight < 1)
 			throw new IllegalArgumentException("output image height " + outputHeight + " is out of range");

 		// Get a buffered image from the image.
 		BufferedImage bi = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);
 		Graphics2D biContext = bi.createGraphics();
 		biContext.drawImage(outputImage, 0, 0, null);
 		ImageIO.write(bi, format, outputStream);
 		outputStream.flush();
 	}

     private static String getFileFormat(String name)
	{
    	 return name.substring(name.lastIndexOf(".")+1);
	}
     /**
      * 创建要裁剪图片的宽度和高度,x,y坐标值
      * @param srcWidth 原图片宽度
      * @param srcHeight 原图片高度
      * @param scaleWidth 宽比例
      * @param sacleHeight 高比例
      * @return
      */
	private static Rectangle createRectangle(int srcWidth, int srcHeight, int scaleWidth, int sacleHeight)
	{
		//计算比例缩放后的宽度
		int tempWidth =  srcHeight * scaleWidth / sacleHeight;
		int width = tempWidth > srcWidth ? srcWidth : tempWidth;
		//x坐标
		int x = (srcWidth - width) / 2;
		
		//计算比例缩放后的高度
		int tempHeight = srcWidth * sacleHeight / scaleWidth;
		int height = tempHeight > srcHeight ? srcHeight : tempHeight;
		//主坐标
		int y = (srcHeight - height) / 2;
		
		Rectangle rect  =   new  Rectangle(x, y, width, height);
		return rect;
	} 

} 

