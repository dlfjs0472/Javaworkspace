package app0511.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClientA extends JFrame{
	JButton bt_open; //대화 상대방을 띄우기 위한 버튼
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	JButton bt;
	
	public ChatClientA() {
		//생성
		bt_open = new JButton("열기");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField(20);
		bt = new JButton("전송");
		
		//레이아웃, 스타일
		this.setLayout(new FlowLayout());
		scroll.setPreferredSize(new Dimension(280, 250));
		area.setBackground(new Color(250,255,0));
		
		//조립
		add(bt_open);
		add(scroll);
		add(t_input);
		add(bt);
		
		//이벤트리스너와의 연결 
		//특히 이벤트리스너가 연결된 컴포넌트를 가리켜 이벤트 소스(Source)라 한다
		ClientAEvent ce=new ClientAEvent();//지역변수는 자동으로 초기화되지 않으므로, 개발자가 반드시 초기화하는 습관을 갖자
		bt.addActionListener(ce); //전송 버튼과 리스너 연결
		bt_open.addActionListener(ce);//오픈 버튼과 리스너 연결
		
		//텍스트필드와 리스너 연결
		t_input.addKeyListener(ce);
		
		ce.setT_input(t_input); //call by reference
		ce.setArea(area); //call by reference 
		ce.setBt(bt);//call by reference
		ce.setBt_open(bt_open);//call by reference
		
		//보여주기
		setBounds(300,100, 300,400); //x, y, width, height
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ChatClientA();
	}
}