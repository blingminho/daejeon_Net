package tcp;

import java.io.IOException;
import java.net.Socket;

public class TcpClient2 {
	public static void main(String[] args) {
		// 소켓을 만들어 서버와 연결한 후
		// 데이터를 받는 클래스와 데이터를 보내는 클래스에 이 소켓을 넘겨준다.
		try {
			Socket socket = new Socket("192.168.0.37", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
