package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTst {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 ip주소 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신 컴퓨터의 Ip주소 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address : " + localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		//InetAddress[] naverIps = InetAddress.getAllByName("www.nate.com");
		for(InetAddress nIp : naverIps) {
			System.out.println(nIp.toString());
		}

	}

}










