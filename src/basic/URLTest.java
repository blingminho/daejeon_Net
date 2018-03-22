package basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		// URL클래스 ==> 인터넷에 존재하는 서버들의 자원에 
		//				접근할 수 있는 주소 정보를 다루는 객체

		// URLConnection클래스 ==> 애플리케이션과 URL간의 통신 연결을 위하 추상 클래스
		
		URL url = new URL("http", "ddit.or.kr", 80, "index.html?ttt=123");
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("port : " + url.getPort());
		System.out.println("file : " + url.getFile());
		System.out.println("query : " + url.getQuery());
		System.out.println("path : " + url.getPath());
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toURI().toString());
		
	}

}








