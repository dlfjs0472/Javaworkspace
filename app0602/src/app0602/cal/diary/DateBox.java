package app0602.cal.diary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

//날짜 한개를 표현할 박스
public class DateBox extends JPanel{
	int width;
	int height;
	String day;
	Color color; //배경색 
	Image img;
	
	public DateBox(String day,Color color, int width, int height) {
		this.day=day;
		this.color=color;
		this.width=width;
		this.height=height;
		setPreferredSize(new Dimension(width, height));
	}

	public void paint(Graphics g) {
		g.setColor(color);//그래픽 객체의 물감색을 결정한다
		g.fillRect(0, 0, width, height);//채워진 사각형

		g.setColor(Color.black);//물감을 흰색으로 교체
		g.drawString(day, 10, 20);
		
		//조건에 따라 아이콘을 그리고, 안그리고를 결정짓게 하자!!
		if(img!=null) {//즉 null이 아닌경우 그리자
			g.drawImage(img, 0, 50, 70, 65, this);
		}
	}
}