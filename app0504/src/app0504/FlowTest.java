package app0504;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
/* 프레임은 윈도우이기 때문에 다른 컴포넌트들을 올려놓을 수 있다.. 
이때 어떤 방식으로 올려놓을지에 대해서는 지원한다
[GUI의 배치관리자]
★1.FlowLayout: 수평 또는 수직방향으로 컴포넌트를 배치하며, 윈도우 크기에 따라 내부 컴포넌트들이 흘러다님
						즉 유동적이다..
★2.BorderLayout : 동,서,남,북,센터의 방위로 배치
★3.GridLayout : 격자형태로 배치(row,col)
4.GridGagLayout : 좌표이용(유지보수 복잡하고 , 까다롭다)
5.CardLayout : 카드를 보여주는 방식으로, 주로 화면전환, 메뉴에 사용

[GUI 컴포넌트 구성]
포함관계를 기준 - 포함능력o (Container) : Frame, Panel
													배치능력이 있으므로, 배치관리자를 사용할 수 있다.
			       -포함능력x (Visual 컴포넌트)
			       
만일 개발자가 컨테이너 이용시 배치관리자를 적용하지 않는 다면??
힌트) 배치관리자 없는 배치는 불가능 하다!!
		
			       
*/

public class FlowTest {
	public static void main(String[] args) {
		Frame frame = new Frame();
		FlowLayout flow = new FlowLayout();
		frame.setLayout(flow); // 프레임에 플로우 레이아웃 적용!!

		for (int i = 0; i < 20; i++) {
			Button bt = new Button(i + "th Button");
			// 프레임에 버튼 부착!!
			frame.add(bt);
		}

		// 프레임의 크기 및 보이게 설정
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
}
