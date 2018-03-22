package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer2 {
	public static void main(String[] args) {
		// 서버소켓을 만들고, 
		// 클라이언트가 접속해 오면 소켓을 만들어
		// 데이터를 받는 클래스와 데이터를 보내는 클래스에 이 소켓을 넘겨준다
		
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			while (true) {
				socket = server.accept();
				
				Sender sender = new Sender(socket);
				Receiver receiver = new Receiver(socket);
				
				sender.start();
				receiver.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
