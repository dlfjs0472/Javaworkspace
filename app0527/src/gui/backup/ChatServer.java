package gui.backup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JTextField t_ip;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	public ChatServer() {
		//생성
		p_north = new JPanel();
		t_port = new JTextField(10);
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//스타일 레이아웃

		
		
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		
		//이벤트
		
		
		//view
		setVisible(true);
		setBounds(600,300,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	public static void main(String[] args) {
		new  ChatServer();
	}
}
