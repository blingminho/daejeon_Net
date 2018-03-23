package tcp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient3 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "192.168.0.33";
		
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("파일명을 입력하세요 : ");
//		String filePath = sc.nextLine();
		
		String filePath = "d:/javaIO/Tulips.jpg";
		String filePath1 = "d:/javaIO/Desert.jpg";
		
		String[] filepath = {filePath, filePath1};
		
		for (String filePath2 : filepath) {
			System.out.println("서버에 연결을 합니다");
			
			Socket socket = new Socket(serverIp, 7777);
			
			System.out.println("서버와 연결되었습니다.");

			File file = new File(filePath2);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int readBytes;
			
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			while ((readBytes = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, readBytes);
			}
			
			System.out.println("서버종료");
			fis.close();
			dos.close();
			socket.close();			
		}
		
		
		
	}
}
