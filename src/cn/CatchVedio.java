package cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ��ȡ��Ƶ�ӿڵ�json
 * @author Administrator
 *
 */
public class CatchVedio {
//    Socket client = new Scoket();
    private URL url;
    private HttpURLConnection urlConnection;
    private int responseCode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    
    public static void main(String[] args) {
        CatchVedio cv = new CatchVedio();
        try {
			String temp = "https://v.qq.com/x/cover/wi8e2p5kirdaf3j.html";
			cv.toDownloadURL(cv.analyse(cv.get_Json(cv.change(temp))));// д�����ļ�
        } catch (IOException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
    
    }
    
    void toDownloadURL(String real_url) throws IOException {//����Ӧ���ص�ַ������ļ�
		System.out.println("real_url=" + real_url);
    }
    
    String analyse(String json) {//����json,�����������ص�ַ
        int fvkey_index = json.indexOf("\"fvkey\":\"")+9;
        int endIndex = json.indexOf("\"",fvkey_index);
        String fvkey = json.substring(fvkey_index,endIndex);//��ȡ��fvkey
//        System.out.println(fvkey);
        
        int fn_index = json.indexOf("\"fn\":\"")+6;
        int fn_end = json.indexOf("\"",fn_index);
        String fn = json.substring(fn_index,fn_end);//��ȡ����Ƶ�ļ��� 
//        System.out.println(fn);
        
        String head = "http://ugcws.video.gtimg.com/";
        
        StringBuffer real_url = new StringBuffer();
        real_url.append(head);//����ͷ��
        real_url.append(fn+"?");//�����ļ���
        real_url.append("vkey="+fvkey);//���������
        /*����ɹ�*/
//        System.out.println(real_url.toString());
        return real_url.toString();
        
    }
    
    String get_Json(String url) throws UnsupportedEncodingException, IOException {
        String line = "";
        StringBuffer sb = new StringBuffer();
        this.url = new URL(url);
        this.urlConnection = (HttpURLConnection)this.url.openConnection();
        this.responseCode = this.urlConnection.getResponseCode();
        if (this.responseCode == 200) {
            this.reader = new BufferedReader(new InputStreamReader(this.urlConnection.getInputStream(), "UTF-8"));
            while ((line = this.reader.readLine()) != null) {
                sb.append(line);// ��ҳ���ص�ֻ��һ��
            }
            return sb.toString();
        }
        return "";
    }
    
	/*
	 * String[] get_VedioURL() throws IOException { // void get_VedioURL() throws
	 * IOException { File file = new File("D:/worm/vedioURL.txt"); String line = "";
	 * this.reader = new BufferedReader(new FileReader(file)); String[] t = new
	 * String[0]; List<String> container = new ArrayList<String>();
	 * while(null!=(line = this.reader.readLine())) { if(line.equals("")) {
	 * continue; } line = this.change(line);//ת��һ�� container.add(line);//װ������ }
	 * return container.toArray(t); }
	 */
    /**
	 * http://vv.video.qq.com/getinfo?vids=x0164ytbgov&platform=101001&charge=0&otype=json&defn=shd
	 * //��ʽ
	 * http://vv.video.qq.com/getinfo?vids=wi8e2p5kirdaf3j&platform=101001&charge=0&otype=json&defn=shd
	 * 
	 * @param str
	 * @return https://v.qq.com/x/cover/wi8e2p5kirdaf3j.html
	 *         https://v.qq.com/x/page/f08302y6rof.html//ҳ���ַʾ��
	 *         https://v.qq.com/x/page/y083158hphd.html
	 *         https://v.qq.com/x/page/c08503oe58c.html
	 */
    String change(String str) {//�����ҳ�沥�ŵ�ַ��ȡvidת������̨�ӿڵ�ַ�ķ���
        String head = "http://vv.video.qq.com/getinfo?vids=";
        String tail = "&platform=101001&charge=0&otype=json&defn=shd";
		// String vid = str.substring(str.indexOf("page/") + 5, str.indexOf(".html"));
		String vid = str.substring(str.indexOf("cover/") + 6, str.indexOf(".html"));
        return head+vid+tail;
    }
}