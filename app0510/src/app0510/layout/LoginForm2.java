package app0510.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

//LoginForm 예제에서는 메인 메서드 안에 변수를 몰아놓고 , 즉 지역변수로 객체들을 처리하고
//있다..이번엔 oop언어의 is a , has a 관계를 활요하여 객체를 정의해보자
public class LoginForm2 extends Frame{
									/* is a*/
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	Button bt_login;
	Button bt_join;
	
	Panel p_center;
	Panel p_south;
	
	//이 윈도우인 즉 로그인폼이 메모리에 생성될때, 해당 부품들도 같이 생성시키려면
	//생성자 메서드를 적극 활용해야 한다..
	public LoginForm2() {
		//윈도우에 제목달기 - 방법1 부모의 일반메서드 이용  
		//this.setTitle("LogionForm style 2");
		//윈도우에 제목달기 - 방법2 부모의 생성자 이용하는 방법 
		super("LoginForm style 2"); //부모의 생성이 제일먼저 이뤄져야 하므로 위쪽으로 올라온다
		
		//부품 생성 
		la_id = new Label("ID");
		la_pass = new Label("Pass");
		t_id = new TextField(20); //20자 너비의 크기 갖음(20자만 넣을수있는게 아니다!)
		t_pass = new TextField(20);
		bt_login  = new Button("Login");
		bt_join  = new Button("Join");	
		p_center = new Panel();
		p_south = new Panel();
		
		//스타일 설정 
		la_id.setPreferredSize(new Dimension(110, 35));
		t_id.setPreferredSize(new Dimension(110, 35));
		la_pass.setPreferredSize(new Dimension(110, 35));
		t_pass.setPreferredSize(new Dimension(110, 35));
		
		//조립 
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		p_south.add(bt_login);
		p_south.add(bt_join);
		
		//패널을 윈도우 센터에 부착 
		this.add(p_center);
		this.add(p_south, BorderLayout.SOUTH);
		
		//현재 클래스가 Frame을 상속받은 자식이므로, Frame이 보유한 
		//메서드를 내것처럼 쓸 수 있다..
		this.setSize(330,155);//윈도우의 크기 설정 
		this.setVisible(true);//윈도우 보이게
		

		
		
	}
}