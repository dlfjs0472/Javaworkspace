package app0527.network.multi.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener{
	JPanel p_north;
	JTextField t_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
	
	Socket socket; //서버와의 통신을 위한 대화용 소켓
	
	BufferedReader buffr=null;//서버로부터 전송되어온 메시지를 입력받을 수 있는 용도의 스트
	BufferedWriter buffw=null;// 서버에 메시지를 전송할 수 있는 출력 스트림
	
	ClientMsgThread msgThread;
	
	public ChatClient() {
		p_north = new JPanel();
		t_ip = new JTextField("192.168.0.3",10);
		t_port = new JTextField(5);
		bt_connect = new JButton("접속");
		area =new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(20);
		bt_send = new JButton("send");
		
		
		
		//조립
		p_north.add(t_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		p_south.add(t_input);
		p_south.add(bt_send);
		add(p_south, BorderLayout.SOUTH);
		
		//이벤트
		bt_connect.addActionListener(this);
		bt_send.addActionListener(this);
		
		//보이기
		setVisible(true);
		setBounds(600,300,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}

	public void connect() {
		//socket = new Socket(아이피문자열, 포트번호 정수);
		String ip = t_ip.getText(); //대상 서버의 아이피
		int port = Integer.parseInt(t_port.getText()); //대상 서버의 포트번호
		
		try {
			socket = new Socket(ip, port);//서버에 접속시도!!
			
			//클라이언트측의 대화 담당 쓰레드 생성
			msgThread=new ClientMsgThread(socket, area);
			msgThread.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt_connect) {
			connect();
			
		}else if(e.getSource()==bt_send) {
			msgThread.send(t_input.getText());
		}
		
	}
	
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
