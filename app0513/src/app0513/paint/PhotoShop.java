package app0513.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoShop extends JFrame{
	JPanel p_north;
	JButton bt_eraser; //그림 지우기
	Color[] colorArray = {Color.RED, Color.ORANGE, Color.CYAN, Color.BLUE, Color.BLACK, Color.green, Color.GRAY};
	MyCanvas can;
	
	public PhotoShop() {
		//생성
		p_north = new JPanel();
		bt_eraser = new JButton("지우기");
		can= new MyCanvas();
		
		for (int i = 0; i < 7; i++) {
			ColorBox colorBox = new ColorBox(colorArray[i]);
			p_north.add(colorBox); //북쪽 패널에 색상자를 붙여넣기
		}
		
		
		//스타일, 레이아웃
		p_north.setPreferredSize(new Dimension(700,50));
		can.setBackground(Color.WHITE);
		
		//조립
		p_north.add(bt_eraser);
		add(p_north, BorderLayout.NORTH);
		add(can);
		
		
		//보이기
		
		setBounds(600,100,700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new PhotoShop();
	}
}
