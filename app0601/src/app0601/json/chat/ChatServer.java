package app0601.json.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	Thread serverThread; //서버 가동에 사용되는 쓰레드, 특히 접속자 감지에서 메인쓰레드로 하여금 대기상태에 빠지지 
									//않게 하기 위해 필요함
	Vector<ServerMsgThread> clientList = new Vector<ServerMsgThread>();
	boolean serverFlag=true;
	
	public ChatServer() {
		//생성 
		p_north = new JPanel();
		t_port = new JTextField("7777",10);
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll  =new JScrollPane(area);
		
		//add
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//event 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverThread = new Thread() {
					public void run() {
						runServer();
					}
				};
				serverThread.start();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				serverFlag=false; //쓰레드 소멸 
				System.exit(0); //Process kill
			}
		});
		
		
		//view
		setVisible(true);
		setBounds(600, 300, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void runServer() {
		int port=Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);
			area.append("서버가동\n");
			while(serverFlag) {
				Socket socket = server.accept(); //대기 상태에 빠지는 코드는 절대로 메인쓰레드로 처리하지 말자!!
				String ip=socket.getInetAddress().getHostAddress();
				area.append(ip+"접속 발견\n");
				
				//메시지 담당 쓰레드 객체 생성 후, 벡터 명단에 추가!!!
				ServerMsgThread smt = new ServerMsgThread( socket , this);
				smt.start(); //열심히 듣고, 말하기 시작~~~~//
				clientList.add(smt); //명단에 넣기
				area.append("현재 접속자 수는 "+clientList.size()+"\n");//로그 남기기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ChatServer();
	}

}