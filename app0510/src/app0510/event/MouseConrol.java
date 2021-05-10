package app0510.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//마우스 이벤트를 청취하는 MouseListener를 재정의해본다!!
public class MouseConrol implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("마우스 눌렀어!!");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}