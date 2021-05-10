  
package app0504;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;

public class GridTest {

	public static void main(String[] args) {

		Frame frame = new Frame("GridLayout");
		GridLayout layout = new GridLayout(2, 3);  //2층, 3호수
		
		frame.setLayout(layout);//프레임에 그리드 레이아웃 적용
		
		for(int i=1;i<=6;i++) {
			Button bt = new Button("bt"+i);
			frame.add(bt); //프레임에 버튼 부착
		}
		
		frame.setSize(300,150); //프레임의 크기 지정
		frame.setVisible(true); //프레임 보이게 설정
	}
}