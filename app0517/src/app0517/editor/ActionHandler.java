package app0517.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//공부목적으로, 프레임에 귀달지 않는다...
public class ActionHandler implements ActionListener{
	MemoEditor editor;
	
	public ActionHandler(MemoEditor editor) {
		this.editor=editor;
	}
	public void actionPerformed(ActionEvent e) {
		//파일열기 아이템을 누르면, JTextArea에 "안녕" 이라는 문자열을 출력해본다
		editor.area.append("안녕");
		
	}
}