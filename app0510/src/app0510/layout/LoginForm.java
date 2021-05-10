package app0510.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm {
	//Frame frame; //null로 초기화
	
	public static void main(String[] args) {
		//Frame frame; //null조차 자동으로 초기화되지 않는다 왜? 지역변수니깐..즉 멤버변수만  컴파일러가 관여
		//결론: 지역변수는 초기화하는 습관을 갖자!!!
		Frame frame = new Frame("로그인 폼 타입1");
		
		//Frame의 BorderLayout의 한 방위(동,서,남,북,센터)는 컴포넌트들이 공존할 수 없고, 
		//크기 또한 컨테이너까지 늘어나 버린다..(ex: 대왕버튼, 대왕텍스트필드 등..)
		//해결책? 여러 컴포넌트를 올려놓고 공존시킬 수 있는 컨테이너를 이용하자!! 
		//컨테이중 가장 많이 활용되는 컴포넌트인 Panel을 이용해보자!!
		Panel p_center = new Panel(); //패널은 디폴트 레이아웃은 FlowLayout이다!!
		
		//패널에 적용된 배치 방법을 바꿔보자!! GridLayout으로...
		GridLayout grid = new GridLayout(2,2); //2행 2열
		p_center.setLayout(grid);
		
		Label la_id=new Label("ID");
		TextField t_id=new TextField();
		Label la_pass=new Label("Pass");
		TextField t_pass = new TextField();
		Panel p_south = new Panel();//남쪽에 버튼들을 얹힐 패널
		Button bt_login = new Button("Login");
		Button bt_join = new Button("Join");
		
		//라벨의 경계를 알기 위해 배경색을 적용해보자!! 
		la_id.setBackground(Color.YELLOW);
		
		p_center.add(la_id); //센터에 라벨 부착!!
		p_center.add(t_id); //방위를 명시하지 않으면 디폴트가 CENTER임
		p_center.add(la_pass);
		p_center.add(t_pass);
		p_south.add(bt_login);
		p_south.add(bt_join);
		
		frame.add(p_center);//패널을 윈도우에 부착!!
		frame.add(p_south, BorderLayout.SOUTH); //남쪽에 버튼을 포함하고 있는 패널 부착!!
		
		frame.setVisible(true);
		frame.setSize(300, 125);
		
		
	}
}