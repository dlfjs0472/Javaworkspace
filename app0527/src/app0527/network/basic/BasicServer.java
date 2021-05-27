package app0527.network.basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//GUI 없이 커멘드모드에서 네트워크 서버를 구축해보자!!
//네트워크 프로그램을 시도하기 전에 먼저 IT분야에서 보편적으로 사용되는 개념인 소켓을 알아야 함 
//소켓이란 자바의 개념이 아니라, 일반적인 응용프로그래밍 언어가 네트워크의 상세한 하부 지식없이도
//네트워크를 제어할 수 있도록, 추상화시켜놓은 api를 지칭 거의 모든 응용프로그램 마다 소켓을 지원
//
public class BasicServer {
	ServerSocket server; //소켓 서버 구축을 위한 서버 객체 ( 접속을 받아들이는 소켓)
	
	//네트워크 프로그램시 항상 염두해두어야할 점은 네트워크 프로그램의 원리가 전화의 원리와 상당히
	//흡사하다는 점임..
	//두 당사자(클라이언트 - 서버) 간의 대화에 앞서서 먼저 두 당사자 간, 접속이 선행되어야 하는데
	//이때 사용되는 객체가 바로 ServerSocket 이다!!즉 대화용 소켓이 아니다!!
	//
	
	public BasicServer() {
		try {
			server = new ServerSocket(7777);
			System.out.println("서버객체생성");
			//접속자를 감지하는 메서드 호출
			Socket socket =server.accept(); //접속자의 접속을 기다리다가, 접속이 발생할 경우 클라이언트를 받아들임!
			//접속자가 발견되면, 이 시점부터는 접속자인 클라이언트와 대화를 나눌 수 있으므로, 대화를 주고받는  전화기에 해당하는 소켓을 반환받는다
			//접속 방법 html 에서 localhost:7777 or cmd 창에서 (제어판-프로그램기능-윈도우 기능 중 telnet 활성화 후)telnet localhost 7777 // telnet 연결대상 포트번호
			System.out.println("접속자 발견!!");
			
			
			InputStream is = socket.getInputStream();
			int data=-1;
			while(true) {
				//아래의 read() 메서드는 즉 모든 입력스트림의 read() 메서드는 실행부로 하여금, 데이터가
				//들어올때까지는 대기상태에 빠지게 한다.. 즉 기다린다...(과부하X)
				data=is.read(); //소켓과 연결된 입력스트림으로부터, 클라이언트의 메시지 중 1byte를 읽어들임
				//즉 말이 네트워크 프로그램,
				//즉 우리가 연결한 대상이 단지 네트워크일 뿐이며, 실제적인 프로그램은 스트림 제어다
				
				System.out.println((char)data);				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new BasicServer();
	}
}
