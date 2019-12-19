package cn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

public class DouYinQushuiyin {
	public static void main(String[] args) throws Exception {
		// https://www.cnblogs.com/lavender-pansy/p/10783654.html
		// ●抖音链接(使用手机分享功能,复制链接)
		String url = "http://v.douyin.com/2MKBC6/";

		String url1 = "️️心灵骇客大鹏️在火山分享了视频，快来围观！传送门戳我>>https://reflow.huoshan.com/hotsoon/s/0f3CTZvw700/ 复制此链接，打开【火山小视频】，直接观看视频~";


		// 过滤链接，获取http连接地址
		String finalUrl = decodeHttpUrl(url1);
		System.out.println(finalUrl);
		// 1.利用Jsoup抓取抖音链接#在抖音，记录美好生活#你们是这样的么
		// 抓取抖音网页
		String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
		System.out.println(htmls); // 做测试时使用

		String[] arr = htmls.split("url_list");

		String matchUrl = arr[8].split("\",\"")[1];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matchUrl.length(); i++) {
			if ((matchUrl.charAt(i)) == 92) {
				continue;
			}

			sb.append(matchUrl.charAt(i));
		}
		matchUrl = sb.toString();
		System.out.println(matchUrl);

		String matchUrl1 = arr[9].split("\",\"")[0].substring(4).replaceAll("app_id=0", "app_id=1").replaceAll("u0026",
				"&");
		sb = new StringBuilder();
		for (int i = 0; i < matchUrl1.length(); i++) {
			if ((matchUrl1.charAt(i)) == 92) {
				continue;
			}

			sb.append(matchUrl1.charAt(i));
		}
		matchUrl1 = sb.toString();
		System.out.println(matchUrl1);
		// 5.将链接封装成流
		// 注:由于抖音对请求头有限制,只能设置一个伪装手机浏览器请求头才可实现去水印下载
		Map<String, String> headers = new HashMap<>();
		headers.put("Connection", "keep-alive");
		headers.put("Host", "aweme.snssdk.com");
		headers.put("User-Agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

		// 6.利用Joup获取视频对象,并作封装成一个输入流对象
		BufferedInputStream in = Jsoup.connect(matchUrl).headers(headers).timeout(10000).ignoreContentType(true)
				.execute().bodyStream();
		BufferedInputStream in1 = Jsoup.connect(matchUrl1).headers(headers).timeout(10000).ignoreContentType(true)
				.execute().bodyStream();

		Long timetmp = new Date().getTime();
		String fileAddress = "d:/火山视频/huoshan_" + timetmp + ".jpg";
		String fileAddress1 = "d:/火山视频/huoshan_" + timetmp + ".mp4";
		// 7.封装一个保存文件的路径对象
		File fileSavePath = new File(fileAddress);
		File fileSavePath1 = new File(fileAddress1);
		// 注:如果保存文件夹不存在,那么则创建该文件夹
		File fileParent = fileSavePath.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		File fileParent1 = fileSavePath1.getParentFile();
		if (!fileParent1.exists()) {
			fileParent1.mkdirs();
		}
		// 8.新建一个输出流对象
		OutputStream out = new BufferedOutputStream(new FileOutputStream(fileSavePath));
		OutputStream out1 = new BufferedOutputStream(new FileOutputStream(fileSavePath1));
		// 9.遍历输出文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}

		out.close();// 关闭输出流
		in.close(); // 关闭输入流

		int b1;
		while ((b1 = in1.read()) != -1) {
			out1.write(b1);
		}
		out1.close();// 关闭输出流
		in1.close(); // 关闭输入流
		// 注:打印获取的链接
		System.out.println("-----火山去水印链接-----\n" + matchUrl1);
		System.out.println("\n-----视频保存路径-----\n" + fileSavePath1.getAbsolutePath());
		System.out.println("-----火山去水印图片链接-----\n" + matchUrl);
		System.out.println("\n-----视频图片保存路径-----\n" + fileSavePath.getAbsolutePath());
	}

	public static String decodeHttpUrl(String url) {
		int start = url.indexOf("http");
		int end = url.lastIndexOf("/");
		String decodeurl = url.substring(start, end);
		return decodeurl;
	}
}