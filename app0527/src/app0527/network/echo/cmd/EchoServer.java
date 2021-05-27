package app0527.network.echo.cmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//에코서버를 콘솔기반으로 제작해본다
public class EchoServer {
	ServerSocket server; //대화용이 아닌, 접속자 감지용 소켓
	int port=7979;
	
	public EchoServer() {
		InputStream is= null;
		OutputStream os =null;
		//지금 제작하려는 프로그램은 복사 등이 아니다. 인간의 육안으로 데이터를 해석가능한 즉 문자를 해석할 수 있는 
		//스트림을 다루는 프로그램이다. 따라서 문자기반의 스트림이 필요한 시점이다!!!
		InputStreamReader reader=null; //문자기반의 입력스트림
		OutputStreamWriter writer=null; //문자기반의 출력스트림
		
		//한 문자씩 데이터를 주고받으면 너무 불편하므로, 문장화 시키려면 버퍼처리된 즉 문자열화 시킬 수 있는 스트림을 이용해보자!!
		BufferedReader buffr = null;
		BufferedWriter buffw = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("서버생성");
			
			//접속자가 있는지 기다리는 메서드
			Socket socket =server.accept(); //접속자가 있을때까지 지연상태에 머무름..
			//접속이 발생하면, 이 접속에 의한 소켓을 반환받을 수 있다(이때의 소켓은 대화를 나누기 위한 소켓)
			System.out.println("접속자 발견");
			
			is = socket.getInputStream(); //이 소켓과 연관된 입력스트림을 얻어, 클라이언트의 메시지를 청취하자
																			//즉 입력받자!!!
			os = socket.getOutputStream(); //이 소켓과 연관된 출력스트림을 얻어, 클라이언트에게 메시지를 보내자
			
			//문자기반으로 업그레이드!!
			reader = new InputStreamReader(is, "UTF-8");
			writer= new OutputStreamWriter(os, "UTF-8");
			
			//문자열을 처리하는 버퍼기반으로 업그레이드!!
			buffr = new BufferedReader(reader);
			buffw = new BufferedWriter(writer);
			
			
			//서버는 듣고 말한다!!
			String data=null;
			
			//적어도 대화를 하려면, 문자를 이해해야 하고,
			//문장을 모아서 처리해야 한다..
			//문자기반스트림 + 버퍼처리된 스트림
			while(true) {
				data = buffr.readLine(); // <-- is.read(); 1byte읽기(듣기) 에서 한줄씩읽기로 업그레이드
				System.out.println(data);
				buffw.write(data+"\n"); // <-- os.write(data); 1byte쓰기 (말하기)에서 한줄씩쓰기로 업그레이드
				buffw.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffw!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}if(buffr!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
