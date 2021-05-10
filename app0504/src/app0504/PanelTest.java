package app0504;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class PanelTest {

	public static void main(String[] args) {
		//그래픽 api 중 Panel 을 학습한다...판넬==판자떼기
		Frame frame = new Frame("Panel Test");
		
		//아래처럼, 인스턴스 자체를 넣는 경우는?  굳이 BorderLayout 라는  객체의 레퍼런스가 
		//사용할 필요가 없는 경우, 즉 1회성일때 변수는 필요가 없을수도 있음. 
		//BorderLayout layout=new BorderLayout();
		frame.setLayout(new BorderLayout());
		Button bt = new Button("I'm button ");
		//frame.add(bt); //개발자가 Border의 5가지 방향 중 선택하지 않았을 경우 Default 방향은? CENTER
		//결론: 버튼은 혼자만 존재하기 때문에 북,동,서,남 의 모든 방위를 혼자 독차지함..따라서 대왕 버튼등장
		
		Panel p = new Panel();//컴포넌트 중 말뜻 그래도 무언가를 올려놓을 수 있는 패널을 의미
		//패널은 Container의 자식이므로, 다른 컴포넌트들을 포함할 수 있는 능력이 있고, 또한 Container 이기에
		//배치관리자도 사용가능하다!!!
		//왕버튼에 대한 해결책?  Frame에 Panel을 부착하고, 이 Panel 에 FlowLaoyut을 적용한 다음 
		//                       최종적으로 Button 패널에 부착하면 된다!!!
		p.setBackground(Color.RED); //패널에 빨간색 배경 적용 
		frame.add(p); //윈도우에 판자 붙이기!!
		
		//버튼을 패널에 부착해보자!!! 참고로 개발자가 Panel의 배치관리자를 지정하지 않으면 Default는 바로 
		//FlowLayout : 플로우는 컴포넌트 본연의 크기가 유지됨..
		p.add(bt); //패널에 버튼 부착!!
		
		frame.setSize(300,250);
		frame.setVisible(true);
	}

}