package app0513.paint;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyCanvas extends Canvas implements MouseListener, MouseMotionListener{
	
	boolean flag=false;
	
	
	public MyCanvas() {
		//캔버스와 리스너 연결
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paint(Graphics g) {
		if(flag) {
			g.fillOval(100, 100, 10, 10);
		}
		
	}

	//마우스를 누르면, 이때부터가 그림 그리기 가능...
	public void mouseDragged(MouseEvent e) {
		//System.out.println("mouseDragged 호출");
		flag=true;
	}
	
	public void mouseMoved(MouseEvent e) {
		//System.out.println("mouseMoved 호출");
		repaint(); //간접적으로 paint() 메서드를 호출하게 된다
	}
	
	//마우스를 눌렀다가 뗄때는 그림 그리기 불가...
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased 호출");
		flag=false;
	}
	
	public void mousePressed(MouseEvent e) {
		//System.out.println("mousePressed 호출");	
	}
	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered 호출");
	}
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExited 호출");
	}
	public void mouseClicked(MouseEvent e) {
		
	}
}
