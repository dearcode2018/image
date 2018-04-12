package com.hua.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public final class Qrgen {
	
	public static void ToQrgen(String CenterCardId ,String sid) throws Exception{
		List<String> sids = new ArrayList<String>();
		StringBuffer urlbuf = new StringBuffer("http://120.24.53.191/cardwx/gain?wechat_card_js=1&cardId=");
		urlbuf.append(CenterCardId).append("&sid=").append("asdf22");
		String url = new String(urlbuf);
		StringBuffer picUrl = new StringBuffer("F:/");
		picUrl.append(sid).append(".jpg");
		FileOutputStream oututstream = new FileOutputStream(new File(picUrl.toString()));
		QRCode.from(oauthUrl("wx54234b9014349e92", url, "")).to(ImageType.JPG).writeTo(oututstream);
		
	}
	
	private static String oauthUrl(String appId,String url,String state) throws Exception{
		StringBuffer buf = new StringBuffer("http://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		buf.append(appId).append("&redirect_uri=")
		.append(URLEncoder.encode(url,"UTF-8"))
		.append("&response_type=code&scope=snsapi_base&state=").append(state).append("#wechat_redirect");
		return buf.toString();
		
	}
	

}
