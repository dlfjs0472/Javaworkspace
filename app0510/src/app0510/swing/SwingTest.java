/*
 * AWT 자바의 GUI API를 대부분 지원하기는 하지만, 플랫폼(os)마다 디자인이 일관적이지 않다는 문제점이 있다
 * 이 부분을 해결하기 위해 swing api로 확장해서 GUI를 구현할 수 있다.. 
 * swing에 대한 학습 부담을 갖지 않아도 된다... 기존 우리가 알고 있었던 컴포넌트에 접두어로 J가 붙으니깐..
 * Swing은 기존의 AWT와 다르게 스윙만의 룩앤필(Look & Feel)이 있다..
 * */
package app0510.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingTest extends JFrame{
	JButton bt; //has a 관계의 객체 
	
	//특히 has a 관계로 다른 객체를 보유할 경우, 개발자는 생성자를 적극활용하면 좋다!!!!!
	//why? has a 란 부품을 보유한 관계이므로, 해당 본체가 태어날때 같이 생성되어야 하므로...
	public SwingTest() {
		//생성
		bt = new JButton("나버튼");
		
		//스타일 
		this.setSize(new Dimension(300, 400));
		
		//조립
		this.setLayout(new FlowLayout());
		this.add(bt);
		
		//보여주기
		this.setVisible(true);
		
		//스윙은 윈도우 창 닫기 버튼은 그냥 setVisible을 false로 두는 것 뿐이다 
		//아직 프로그램 즉, 프로세스는 살아있다...
		//해결책?? 1) 이벤트를 구현하는 법   2) 알맞는 메서드 호출 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SwingTest();
	}
}


