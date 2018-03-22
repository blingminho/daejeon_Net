package basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLTest2 {
	public static void main(String[] args) throws IOException {
		// 특정 서버의 정보와 파일 내용 출력하기
		
		URL url = new URL("http://apis.data.go.kr/1320000/SearchMoblphonInfoInqireService/getMoblphonAcctoKindAreaPeriodInfo?serviceKey=hn9yggCx3YxUm3yYbm3GKRXQBsRsGT3Zz%2FbSUCIOXT8i2E2hEUvrZ86WTm2MX56tY%2FpFg0UVMHNvWO%2FnPK7uYg%3D%3D&COL_CD=CL1002&FD_LCT_CD=LCA000&START_YMD=20180302&END_YMD=201803022&PRDT_CL_CD_02=PRJ100&pageNo=1&startPage=1&numOfRows=10&pageSize=10");
//		URL url = new URL("http://ddit.or.kr/index.html");
		
		// Header 정보 구하기
		
		// URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();
		
		System.out.println("Content-Type : " + urlCon.getContentType());
		System.out.println("Encoding : " + urlCon.getContentEncoding());
		System.out.println("Content : " + urlCon.getContent());
		System.out.println("==========================================================");
		System.out.println();
		
		// Header정보 전체 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// 키값 구하기
		Iterator<String> iter = headerMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();// 키값
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("==========================================================");
		
		// 해당 호스트의 페이지 정보 가져오기
		// 즉, index.html문서 내용 가져오기
		
		/*
		// 방법 1 ==> URLConnection의 getInputStream() 이용하기
		
		// 파일을 읽어오기 위한 스트림 생성
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		// 내용 읽어와 출력하기
		while (true) {
			String str = br.readLine();// 한 줄씩 읽기
			if (str == null) {
				break;
			}
			System.out.println(str);
		}
		
		// 스트림 및 리더 닫기
		br.close();
		isr.close();
		is.close();
		*/
		
		// 방법 2 ==> URL객체의 openStream()이용하기
		
		// 스트림 객체 구하기
		InputStream is = url.openStream();
		System.out.println("file : " + url.getFile());
		BufferedInputStream bis = new BufferedInputStream(is);
		
		int readLength = 0;
		byte[] buffer = new byte[2048];
		File file = new File("D:\\test.xml");
		FileOutputStream fos = new FileOutputStream(file);
		
		while ((readLength = bis.read(buffer)) != -1) {
			fos.write(buffer, 0, readLength);
			System.out.println(readLength);
		}
		fos.close();
		
//		
//		InputStreamReader isr = new InputStreamReader(is, "utf-8");
//		BufferedReader br = new BufferedReader(isr);
//		
//		// 내용 읽어와 출력하기
//		while (true) {
//			String str = br.readLine();// 한 줄씩 읽기
//			if (str == null) {
//				break;
//			}
//			System.out.println(str);
//		}
//		
//		// 스트림 및 리더 닫기
//		br.close();
//		isr.close();
//		is.close();
				
	}
}
