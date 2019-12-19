package cn;

import org.jsoup.Jsoup;

public class Catch {
	public static void main(String[] args) {
		try {
			// Õı¡¶∫Í/Ã∑Œ¨Œ¨°∂‘µ∑÷“ªµ¿«≈°∑https://c.y.qq.com/base/fcgi-bin/u?__=l2FLMfn @QQ“Ù¿÷
			// http://aqqmusic.tc.qq.com/amobile.music.tc.qq.com/C400000xhio724WRmK.m4a?guid=2095717240&vkey=4339EEEB792BAE13325AD78FF968AF70B8D8CAD3327A83ABEDCE8233FE31F14658EEC1432D0113861ACF7CBB53C25AC0E55ED07B8CCBF8A8&uin=0&fromtag=38/
			// http:\\\/\\\/aqqmusic.tc.qq.com\\\/amobile.music.tc.qq.com\\\/C400000xhio724WRmK.m4a?guid=2095717240&vkey=4339EEEB792BAE13325AD78FF968AF70B8D8CAD3327A83ABEDCE8233FE31F14658EEC1432D0113861ACF7CBB53C25AC0E55ED07B8CCBF8A8&uin=0&fromtag=38\
			String finalUrl = "https://jx.618g.com/?url=https://y.qq.com/n/yqq/song/004NXwuk36ixAW.html";
			// String finalUrl =
			// "http://660e.com/?url=https://v.qq.com/x/cover/5vfbg80goc4mkay/f003249hywz.html";
			String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
			System.out.println(htmls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
