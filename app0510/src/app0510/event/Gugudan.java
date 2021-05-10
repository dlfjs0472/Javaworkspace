package app0510.event;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Gugudan extends JFrame{
	JTextField t_input;
	JButton bt;
	
	public Gugudan() {
		//생성
		t_input = new JTextField(20);
		bt = new JButton("출력");
		
		//조립
		this.setLayout(new FlowLayout());
		add(t_input);
		add(bt);
		
		//버튼과 리스너 연결 
		bt.addActionListener(new GugudanEvent(t_input));
		
		//보여쥑 
		setSize(250, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Gugudan();
	}
}
