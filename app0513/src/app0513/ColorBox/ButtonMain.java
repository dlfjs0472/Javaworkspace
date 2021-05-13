package app0513.ColorBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonMain extends JFrame implements ActionListener{
	JPanel p_north;
	JTextField t_ea;
	JButton bt_create; //일괄생성 버튼
	JButton bt_create2; //낱개 생성버튼
	JButton bt_bg;
	JPanel p_center;
	
	
	//대부분의 응용프로그램에서는 배열 선언시 반드시 그 크기를 명시해야 한다..
	JButton[] btnArray;
	int count=0; //버튼에 붙여질 순번
	
	//배열이 나쁜건 아니지만, 선언시 반드시 그 크기를 정해야 한다는 것은 프로그램 개발시 유연성에 있어서는 불편하다.
	//그렇다면 자바스크립트처럼 배열으 ㅣ크기가 동적으로 늘어날 수 있는 형태의 객체는 없을까? 당연히 있다
	//JButton[] btnArray2=new JButton[1000]; // 낱개로 생성된 버튼들을 담게될 배열 = 배열의 크기를 동적으로 설정할수 없기 때문에 List계열로 대체
	
	//배열과 거의 같지만, 크기가 동적으로 변할 수 있는 컬렉션 프레임웍 중 List계열을 이용해보자!!
	ArrayList<JButton> list = new ArrayList<JButton>(); //현재는 사이즈0이지만, 늘어날 수 있다!! 제너릭으로 JButton 으로 한정!!
	
	public ButtonMain() {
		//생성
		p_north = new JPanel();
		t_ea = new JTextField(10);
		bt_create = new JButton("일괄생성");
		bt_create2 = new JButton("낱개생성");
		bt_bg = new JButton("배경색");
		p_center = new JPanel();
		
		
		//레이아웃 스타일
		p_north.setPreferredSize(new Dimension(500,50));
		p_center.setBackground(Color.YELLOW);
		
		//조립
		p_north.add(t_ea);
		p_north.add(bt_create);
		p_north.add(bt_create2);
		p_north.add(bt_bg);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt_create.addActionListener(this);//리스너와 연결
		bt_create2.addActionListener(this);//리스너와 연결
		bt_bg.addActionListener(this);//리스너와 연결
		
		
		//보이기
		setBounds(600, 100, 500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//사용자가 입력한 개수 만큼, 버튼을 생성한다!!!
	public void create() {
		//(1) 텍스트필드의 값 구하기
		int ea = Integer.parseInt(t_ea.getText()); //"" 벗겨내기 = 강제형변환
		btnArray=new JButton[ea]; //배열의 크기를 결정지을 수 있다.. 따라서 이때 배열을 생성할 수 있다..
		
		//(2) 개수만큼 버튼을 생성하여 p_center에 붙이기!!!
		for(int i=0; i<ea; i++) {
			JButton bt = new JButton("버튼"+(i+1));
			p_center.add(bt);
			btnArray[i] = bt; //배열에, 지금 생성된 버튼을 탑재!!
			
		}
		//p_center.repaint(); //페인트 메서드가 아니므로 기능하지 않음
		p_center.updateUI();//다시 그려라
	}
	public void createOne() {
		//누를때마다, 버튼이 누적되게 처리해본다!!
		JButton bt = new JButton("버튼"+count);
		p_center.add(bt); //센터영역에 버튼 추가
		p_center.updateUI();
		//추후 배경색 버튼을 눌렀을때 생성된 모든 버턴의 색상을 변경하기 위해서는 반복문이 필요하고,
		//반복문을 적용하려면, 배열 형태 또는 무언가 규칙이 있어야 한다..
		list.add(bt);
		count++;
	}
	
	public void setBg() {
		//배열에 들어있는 버튼 수 만큼 배경색 적용
		/* 배열 사용시
		for(int i = 0; i<btnArray.length; i++) {
			btnArray[i].setBackground(Color.RED);
		}
		*/
		
		//List 사용시
		for( JButton btn : list) { //우측항의 집합 수만큼 반복
			btn.setBackground(Color.RED);		
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_create) {
			create();
		}else if(e.getSource() == bt_create2) {
			createOne();
		}else if(e.getSource() == bt_bg) {
			setBg();
		}
		
	}
	public static void main(String[] args) {
		new ButtonMain();
	}
}
