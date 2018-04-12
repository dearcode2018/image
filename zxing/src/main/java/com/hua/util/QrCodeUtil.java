/**
 * QrCodeUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.hua.constant.Constant;

/**
 * QrCodeUtil
 * 描述: 二维码工具
 * 编码(生成二维码图形)、解析(根据图形解析二维码中的内容)
 * @author  qye.zheng
 */
public final class QrCodeUtil
{

	/* 输出图形格式 */
	private static final String OUTPUT_FORMAT = "JPG";
	
	/* 二维码尺寸 宽度/高度 */
	private static final int 	QR_CODE_SIZE = 600;
	
	/* LOGO宽度 */
	private static final int LOGO_WIDTH = 200;
	
	/* LOGO高度 */
	private static final int LOGO_HEIGHT = 200;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private QrCodeUtil()
	{
	}

	/* =========================== ====  二维码编码 ============================== */
	
	/**
	 * 
	 * @description 生成二维码
	 * @param content 二维码内容
	 * @param destPath 二维码输出路径
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final String destPath) throws Exception
	{
		encode(content, null, destPath, false);
	}	
	
	/**
	 * 
	 * @description 生成二维码，支持嵌套logo
	 * @param content 二维码内容
	 * @param logoPath 二维码中心logo图形路径
	 * @param destPath 二维码输出路径
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final String logoPath, 
			final String destPath) throws Exception
	{
		// needCompress 不压缩
		encode(content, logoPath, destPath, false);
	}
	
	/**
	 * 
	 * @description 生成二维码，支持嵌套logo
	 * @param content 二维码内容
	 * @param logoPath 二维码中心logo图形路径
	 * @param destPath 二维码图形输出路径 eg: /a/b/c.jpg
	 * @param needCompress 是否压缩
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final String logoPath, final String destPath, 
			final boolean needCompress) throws Exception
	{
		final BufferedImage image = createImage(content, logoPath, needCompress);
		final File destFile = new File(destPath);
		// 检查父目录是否存在
		if (!destFile.getParentFile().exists())
		{
			destFile.getParentFile().mkdir();
		}
		ImageIO.write(image, OUTPUT_FORMAT, new File(destPath));
	}
	
	/**
	 * 
	 * @description 生成二维码
	 * @param content 二维码内容
	 * @param logoPath 二维码中心logo图形路径
	 * @param outputStream 二维码输出流
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final String logoPath, 
			final OutputStream outputStream) throws Exception
	{
		// needCompress 不压缩
		encode(content, logoPath, outputStream, false);
	}		
	
	/**
	 * 
	 * @description 生成二维码
	 * @param content 二维码内容
	 * @param outputStream 二维码输出流
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final OutputStream outputStream) throws Exception
	{
		// 
		encode(content, null, outputStream, false);
	}		
	
	/**
	 * 
	 * @description 生成二维码，支持嵌套logo
	 * @param content 二维码内容
	 * @param logoPath 二维码中心logo图形路径
	 * @param outputStream 二维码输出流
	 * @param needCompress 是否压缩
	 * @author qianye.zheng
	 */
	public static final void encode(final String content, final String logoPath, final OutputStream outputStream, 
			final boolean needCompress) throws Exception
	{
		final BufferedImage image = createImage(content, logoPath, needCompress);
		ImageIO.write(image, OUTPUT_FORMAT, outputStream);
	}	
	
	/**
	 * 
	 * @description 创建图形对象
	 * @param content 内容
	 * @param logoPath logo图形路径
	 * @param needCompress 是否压缩
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static final BufferedImage createImage(final String content, 
			final String logoPath, final boolean needCompress) throws Exception
	{
		final Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 设置编码
		hints.put(EncodeHintType.CHARACTER_SET, Constant.CHART_SET_UTF_8);
		// 边缘大小
		hints.put(EncodeHintType.MARGIN, 1);
		final MultiFormatWriter formatWriter = new MultiFormatWriter();
		final BitMatrix bitMatrix = formatWriter.encode(content,
				BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
		final int width = bitMatrix.getWidth();
		final int height = bitMatrix.getHeight();
		final BufferedImage image = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);
		//
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				// image.setRGB(x, y, ( bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF) );
				// 设置 RGB 颜色值 32位，设置颜色，二维码一般是黑白相间
				image.setRGB(x, y, ( bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF) );
				//image.setRGB(x, y, ( bitMatrix.get(x, y) ? 0xFF0FF000 : 0xFFF00FFF) );
			}
		}
		// logo图形路径为空
		if (StringUtil.isEmpty(logoPath))
		{
			return image;
		}
		// 嵌入logo图形
		insertImage(image, logoPath, needCompress);
		
		return image;
	}
	
	/**
	 * 
	 * @description 嵌入logo图形
	 * @param source 二维码图形对象
	 * @param logoPath logo图形地址
	 * @param needCompress 是否压缩
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static final void insertImage(final BufferedImage source, final String logoPath, 
			final boolean needCompress) throws Exception
	{
		// logo 图形文件
		final File logoFile = new File(logoPath);
		if (!logoFile.exists())
		{
			System.out.println("logo图形文件: " + logoPath + "  不存在");
			
			return;
		}
		Image logoImage = ImageIO.read(logoFile);
		int width = logoImage.getWidth(null);
		int height = logoImage.getHeight(null);
		// 检查是否需要压缩
		if (needCompress)
		{
			if (width > LOGO_WIDTH)
			{
				width = LOGO_WIDTH;
			}
			if (height > LOGO_HEIGHT)
			{
				height = LOGO_HEIGHT;
			}
			/** 绘制压缩之后的图形 */
			// 获取logo图形的RGB矢量图
			final Image compressImage = logoImage.getScaledInstance(width, height, 
					BufferedImage.TYPE_INT_BGR);
			final BufferedImage tag = new BufferedImage(width, height, 
					BufferedImage.TYPE_INT_RGB);
			final Graphics graphics = tag.getGraphics();
			// 绘制压缩之后的logo图形
			graphics.drawImage(compressImage,0, 0, null);
			// 执行处理
			graphics.dispose();	
			// 将压缩之后的图形对象赋值给原始的logo图形
			logoImage = compressImage;
		}
		/** 嵌入logo图形 */
		// 创建二维码图形对象
		final Graphics2D graphics2D = source.createGraphics();
		// 二维码logo的x坐标
		final int x = (QR_CODE_SIZE - width) / 2;
		// 二维码logo的y坐标
		final int y = (QR_CODE_SIZE - height) / 2;
		graphics2D.drawImage(logoImage, x, y, width, height, null);
		// 外形
		final Shape shape = new RoundRectangle2D.Float(x, y, width, height, 6, 6);
		graphics2D.setStroke(new BasicStroke(3F));
		graphics2D.draw(shape);
		graphics2D.dispose();
	}
	
	
	/* =========================== ====  二维码解码 ============================== */
	
	/**
	 * 
	 * @description 
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	public static final String decode(final InputStream inputStream)
	{
		return decode_(inputStream);
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public static final String decode(final File file)
	{
		String content = null;
		try
		{
			final InputStream inputStream = new FileInputStream(file);
			content = decode_(inputStream); 
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 
	 * @description 
	 * @param path
	 * @return
	 * @author qianye.zheng
	 */
	public static final String decode(final String path)
	{
		String content = null;
		try
		{
			final InputStream inputStream = new FileInputStream(path);
			content = decode_(inputStream); 
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 
	 * @description 
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	private static final String decode_(final InputStream inputStream)
	{
		String content = null;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(inputStream);
			final LuminanceSource source = new BufferedImageLuminanceSource(image);
			final Binarizer binarizer = new HybridBinarizer(source);
			final BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			final Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			// 对图形进行解码
			final Result result = new MultiFormatReader().decode(binaryBitmap, hints);
			content = result.getText();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return content;
	}
	
	
	
}
