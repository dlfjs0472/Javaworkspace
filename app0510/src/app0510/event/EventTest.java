package app0510.event;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EventTest extends JFrame{
	JTextField t_input;
	JButton bt;
	
	public EventTest() {
		t_input  = new JTextField(20);
		bt = new JButton("나눌러봐");
		 
		this.setLayout(new FlowLayout());//FlowLayout으로 전환
		
		//조립 
		add(t_input);
		add(bt);
		
		//컴포넌트와 리스너와의 연결!!
		//선언이 test(int x , String k, ActionListener l); 인 경우
		        //test(3,"apple", ActionListener의 인스턴스);	
		
		//ActionControl is a ActionListener 이니깐!!!
		//아래의 메서드의 매개변수엔 ActionControl의 인스턴스를 넘기면 된다..
		//이벤트 구현 방법 : 미완성 객체를 구현 한 후, 해당 컴포넌트와 연결!!!
		bt.addActionListener(new ActionControl());
		
		//텍스트필드와 키리스너와의 연결 !!
		t_input.addKeyListener(new KeyControl());
		
		//프레임 본체를 대상으로 마우스리스너와 연결 
		this.addMouseListener(new MouseConrol());
		
		//프레임 본체를 대상으로 윈도우리스너와 연결 
		this.addWindowListener(new WindowControl());
		
		//보여주기
		this.setSize(250,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new EventTest();
	}
}