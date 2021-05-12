package app0512.graphic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

//갤러리 큰 이미지 나올 패널
public class GalleryDetail extends JPanel{
	private Image image;
	//누군가에 의해 Image를 념겨받으려면, 메서드를 준비해놓아야 한다!!!
	public void setImage(Image image) {
		this.image = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
	}

	public void paint(Graphics g) {
		//그림 그려줄꺼야!!
		g.drawImage(image, 0, 0, this);
		
	}
}
