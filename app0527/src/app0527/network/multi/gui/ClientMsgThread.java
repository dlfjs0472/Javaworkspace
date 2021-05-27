package app0527.network.multi.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

//GUI처리를 담당해야할 클라이언트를 대신하여, 메시지를 주고 받을 수 있는
//쓰레드 객체를 정의한다!!
public class ClientMsgThread extends Thread {
	Socket socket;
	BufferedReader buffr = null;// 서버로부터 전송되어온 메시지를 입력받을 수 있는 용도의 스트
	BufferedWriter buffw = null;// 서버에 메시지를 전송할 수 있는 출력 스트림
	JTextArea area;

	public ClientMsgThread(Socket socket, JTextArea area) {
		this.socket=socket;
		this.area=area;
		// 접속 성공된 소켓으로부터, 스트림 뽑아놓기!!
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버에 메시지 보내기!!
	public void send(String msg) {
		try {
			buffw.write(msg + "\n"); // 말하고
			buffw.flush();

			// 아래의 listen() 메서드는, 클라이언트가 원하는 시점에 호출하는게 아니라
			// 항상 무한 루프로 호출되어있어야 서버의 메시지를 실시간으로 감시할 수 있다.
			// 이때 주의할 점은, 메인쓰레드를 무한 루프에 빠지게 해서는 안된다!!! 결국 쓰레드를 생성하여 listen() 전담시키
			// listen(); //듣고
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버의 메시지 받기
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine(); // 서버가 보낸 메시지 받기!!
			area.append(msg + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			listen();
		}
	}
}
