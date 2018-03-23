package tcp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer3 {
	public static void main(String[] args) {
		
		ServerSocket serverSocket;
		FileOutputStream fos = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 접속을 기다리고 있습니다.");
			
			String filePath = "d:/javaIO/연습용/Tulips.jpg";
			String filePath1 = "d:/javaIO/연습용/Desert.jpg";
			
			String[] filepath = {filePath, filePath1};
			
			for (String filePath2 : filepath) {
				Socket socket = serverSocket.accept();
				
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				
				File file = new File(filePath2);
				fos = new FileOutputStream(file);
				
				int readBytes = 0;
				byte[] buffer = new byte[2048];
				while ((readBytes = dis.read(buffer)) != -1) {
					fos.write(buffer, 0, readBytes);
					System.out.println(readBytes);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("서버 종료!");
		}
		
		
		
		
	}
}
