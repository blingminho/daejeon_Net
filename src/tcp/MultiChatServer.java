package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	// 대화명, 클라이언트 socket정보를 저장할 Map선언(대화방 객체 선언)
	Map<String, Socket> clients;
	
	public MultiChatServer() {
		// Map객체를 동기화 처리가 가능하도록 생성한다.
		clients = Collections.synchronizedMap(new HashMap<String, Socket>()) ;
	}
	
	// 서버의 시작
	public void serverStart() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				// 클라이언트의 접속 대기
				socket = serverSocket.accept();
				
				// 클라이언트는 접속되면 자신의 대화명을 전송한다.
				// 이 대화명을 이용하여 '대화방(Map)'에 저장한다.
				System.out.println("[" + socket.getInetAddress() + ":" +
							socket.getPort() + "] 에서 접속하였습니다." );
				
				// 서버에서 클라이언트로 메시지를 전송할 Thread를 실행한다.
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 서버소켓 닫기
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 대화방에 있는 전체 사용자에게 메시지를 전송하는 메서드
	public void sendToAll(String msg) {
		// 대화방에 있는 전체 사용자의 대화명리스트를 구한다.(key값)
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next(); // 대화명 구하기
				Socket socket = clients.get(name); // 대화명의 Socket객체 구하기
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(msg);
			} catch (IOException e) {	}
		}
	}
	
	public static void main(String[] args) {
		// 서버시작 메서드 호출
		new MultiChatServer().serverStart();
	}
	
	
	// 서버에서 클라이언트로 메시지를 전송할 Thread
	// 대화방 멤버변수를 사용하기 위해서 Inner Class로 만든다.
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream din;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {}
			
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				// 사용자가 접속할 때 최초로 보내는 대화명을 받는다.
				name = din.readUTF();
				
				// 대화명을 받아서 현재 접속되어 있는 모든 클라이언트에게 메시지 보내기
				sendToAll("[" + name + "]님이 들어오셨습니다.");
				
				// 대화방(Map)에 등록한다.
				clients.put(name, socket);
				System.out.println("현재 서버 접속자수는 " + clients.size() + "입니다.");
				
				while(din!=null) {
					sendToAll(din.readUTF());
				}
				
			} catch (IOException e) {
				
			} finally {
				// finally절이 실행된다는 것은 클라이언트가 빠져 나간것을 의미한다.
				sendToAll("[" + name + "]님이 나가셨습니다.");
				
				// 대화방에서 삭제
				clients.remove(name);
				System.out.println("현재 서버 접속자수는 " + clients.size() + "입니다.");
			}
		}
	}

}








