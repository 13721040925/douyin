package cn;

import org.jsoup.Jsoup;

public class StringUnicodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String finalUrl = "http://www.douqq.com/qqmusic/qqapi.php?mid=https://y.qq.com/n/yqq/song/004NXwuk36ixAW.html";
			String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
			String st1 = htmls.split("lrc")[1];
			String st2 = st1.substring(5, st1.length() - 4);
			String[] arr = st2.split("\\\\");
			StringBuilder sb = new StringBuilder();
			sb.append(arr[0].trim());
			for (int i = 0; i <= arr.length - 1; i++) {
				if (i == 0) {
					continue;
				}
				if (arr[i].length() <= 0) {
					continue;
				}
				sb.append((char) 92).append(arr[i]);
			}
			sb.append((char) 92);
			String str4 = sb.toString();
			StringBuilder sb1 = new StringBuilder();
			int j = -10;
			for (int i = 0; i < str4.length() - 1; i++) {
				if (str4.charAt(i) == 92) {
					if (str4.charAt(i + 1) == 'u') {
						j = i;
						String s = str4.substring(i, i + 6);
						sb1.append(unicodeToString(s));
					}
					if (str4.charAt(i + 1) == '/') {
						continue;
					}
				}
				if (i < j + 6) {
					continue;
				}
				sb1.append(str4.charAt(i));
			}
			String lrc = sb1.toString();// ¸è´Ê
			String[] arr2 = htmls.split(",");
			// for (int i = 0; i <= arr2.length - 1; i++) {
			// System.out.println("index=" + i + "," + arr2[i]);
			// }
			String str5 = arr2[3];
			String str6 = str5.substring(12, str5.length() - 2);
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i <= str6.length() - 1; i++) {
				if (str6.charAt(i) == 92) {
					continue;
				}
				sb2.append(str6.charAt(i));
			}
			sb2.append('/');
			String m4a = sb2.toString();// ¸èÇú
			String str7 = arr2[9];
			String str8 = str7.substring(10, str7.length() - 2);
			StringBuilder sb3 = new StringBuilder();
			for (int i = 0; i <= str8.length() - 1; i++) {
				if (str8.charAt(i) == 92) {
					continue;
				}
				sb3.append(str8.charAt(i));
			}
			sb3.append('/');
			String pic = sb3.toString();// Í¼Æ¬
			String str9 = arr2[6];
			String str10 = str9.substring(15, str9.length() - 2);
			StringBuilder sb4 = new StringBuilder();
			for (int i = 0; i <= str10.length() - 1; i++) {
				if (str10.charAt(i) == 92 && str10.charAt(i + 1) == 92) {
					continue;
				}
				sb4.append(str10.charAt(i));
			}
			String songname = unicodeToString(sb4.toString());// ¸èÃû
			String str11 = arr2[8];
			String str12 = str11.substring(17, str11.length() - 2);
			StringBuilder sb5 = new StringBuilder();
			j = -10;
			for (int i = 0; i < str12.length() - 1; i++) {
				if (str12.charAt(i) == 92) {
					if (str12.charAt(i + 1) == 'u') {
						j = i;
						String s = str12.substring(i, i + 6);
						sb5.append(unicodeToString(s));
					}
				}
				if (i < j + 6) {
					continue;
				}
				sb5.append(str12.charAt(i));
			}
			String str13 = sb5.toString();
			StringBuilder sb6 = new StringBuilder();
			for (int i = 0; i <= str13.length() - 1; i++) {
				if (str13.charAt(i) == 92) {
					continue;
				}
				sb6.append(str13.charAt(i));
			}
			String singername = sb6.toString();// ¸èÊÖ
			System.out.println(singername);
			System.out.println(m4a);
			System.out.println(pic);
			System.out.println(songname);
			System.out.println(lrc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ×Ö·û´®×ªunicode
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToUnicode(String str) {
		StringBuffer sb = new StringBuffer();
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			sb.append("\\u" + Integer.toHexString(c[i]));
		}
		return sb.toString();
	}

	/**
	 * unicode×ª×Ö·û´®
	 * 
	 * @param unicode
	 * @return
	 */
	public static String unicodeToString(String unicode) {
		StringBuffer sb = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int index = Integer.parseInt(hex[i], 16);
			sb.append((char) index);
		}
		return sb.toString();
	}
}