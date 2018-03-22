package tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 하기 위해 ServerSocket객체 생성
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다리고 있습니다.");
		
		// ServerSocket의 accept() 메서드는 연결 요청이 올 때까지 계속 기다린다.
		// 연결 요청이 오면 클라이언트의 소켓과 연결된 Socket객체를 생성한다.
		Socket socket = server.accept();
		
		// 이 이후의 내용은 서버와 클라이언트가 연결된 후에 처리되는 부분이다.
		System.out.println(socket.getInetAddress() + "에 연결되었습니다.");
		System.out.println("port : " + socket.getPort());
		System.out.println("localport : " + socket.getLocalPort());
		
		// Client에게 메시지 보내기
		// outputStream객체를 구성해서 전송한다
		// socket의 OutputStream객체를 구한다
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// 메시지 보내기
		dos.writeUTF("어서오세요");
		System.out.println("메시지를 보냈습니다");
		
		dos.close();
		out.close();
		socket.close();
		
	}
}
