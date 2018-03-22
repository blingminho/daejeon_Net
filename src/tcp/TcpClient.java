package tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 자기 자신 컴퓨터를 나타내는 방법
		// 1. 원래 설정된 IP주소 (예 : 192.168.0.33)
		// 2. 공통적으로 사용되는 IP주소 : 127.0.0.1
		// 3. 컴퓨터 이름 또는 localhost
		
		String serverIp = "192.168.0.33";
		System.out.println("서버에 연결을 합니다");
		Socket socket = new Socket(serverIp, 7777);
		
		// 이 이후의 내용은 서버와 연결이 완료된 후에 처리되는 내용입니다.
		System.out.println("연결되었습니다.");
		
		// 서버에서 보내온 메시지 받기
		// socket에서 InputStream객체를 구한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 받은 메시지 출력하기
		System.out.println("서버로부터 받은 메시지 : " + dis.readUTF());
		
		System.out.println("연결 종료...");
		
		
		socket.close();
		dis.close();
		is.close();
		
		
		
	}
}
