package gui.backup;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame{
	JPanel p_north;
	JTextField t_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
	
	
	public ChatClient() {
		p_north = new JPanel();
		t_ip = new JTextField(10);
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
		
		
		//보이기
		setVisible(true);
		setBounds(1000,300,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
