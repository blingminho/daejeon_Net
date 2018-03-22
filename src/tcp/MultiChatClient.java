package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	public void clientStart() {
		Scanner scan = new Scanner(System.in);
		String userName = null;
		
		System.out.println("대화명 입력 >>");
		userName = scan.nextLine();
		
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.0.22", 7777);
			// 접속할 서버의 IP를 이용해서 서버에 접속한다
			System.out.println("서버에 접속되었습니다.");
			
			// 전송용 Thread 실행
			ClientSender sender = new ClientSender(socket, userName);
			sender.start();
			
			// 수신용 Thread 실행
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
		
		
	}
	
	// 메시지 전송용 Thread
	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			try {
				// 접속이 성공하면 최초로 자신의 대화명을 전송한다
				if (dos != null) {
					dos.writeUTF(name);
				}
				
				while (dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송한다.
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}
			} catch (IOException e) {	}
		}
	}
	
	// 메시지 수신용 Thread
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream din;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {	}
		}
		
		@Override
		public void run() {
			while (din != null) {
				try {
					// 서버로부터 전송받은 메시지를 화면에 출력한다
					System.out.println(din.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
	
