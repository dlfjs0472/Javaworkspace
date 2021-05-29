package app0528.network.multi.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerMsgThread extends Thread{
	
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	ChatServer chatServer; //JTextArea, Vector 등 여러가지 서버측의 자원을 접근할 것이기에...통째로 서버를 보유하자!
	boolean flag=true; //현재 쓰레드를 동작 제어할 수 있는 논리값 즉 false를 두는 순간, 이 쓰레드는 dead상태가 된다!!
	
	public ServerMsgThread(Socket socket, ChatServer chatServer) {
		this.socket=socket;
		this.chatServer=chatServer;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣고
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); //클라이언트가 보낸 메시지 듣기!!!
			//서버에 접속한 모든 유저의 send(msg) 호출!!
			for(int i=0; i<chatServer.clientList.size(); i++) {
				ServerMsgThread msgThread=chatServer.clientList.get(i); //벡터의 각 요소에 들어있는 쓰레드 꺼내기!!
				msgThread.send(msg); //다시 클라이언트에게 보내기!! 				
			}
			
			chatServer.area.append(msg+"\n");//서버의 로그에 남기기
		} catch (IOException e) {
			e.printStackTrace();
			//대화 목록 수에서 제거!!
			chatServer.clientList.remove(this);
			chatServer.area.append("클라이언트"+this+"가 나갔습니다.\n 현재접속자 "+chatServer.clientList.size()+"\n");
			flag=false;
		}
	}
	
	//말하기
	public void send(String msg) {
		//버퍼처리된 스트림에서의 문자열의 끝을 표시할때는 역슬래시n (줄바꿈) 이용해야 한다
		try {
			buffw.write(msg+"\n"); //즉  \n만날때 출력행위가 발생!!
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(flag) {
			listen();
		}
	}
}