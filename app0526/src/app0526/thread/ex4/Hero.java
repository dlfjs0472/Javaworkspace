package app0526.thread.ex4;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//주인공을 정의한다!!
public class Hero extends GameObject{
	GamePanel gamePanel;

	
	public Hero(GamePanel gamePanel, String path, int x, int y, int width, int height, int velX, int velY) {
		//자신을 초기화 하기 전에, 부모의 초기화가 더 시급하다
		super(gamePanel, path, x, y, width, height, velX, velY);
	}
	
	//물리량 변화
	public  void tick() {
		this.x+=this.velX;
		this.y+=this.velY;
	}
	
	//변화된 물리량 그래픽으로 표현
	public  void render(Graphics g) {
		//g는 패널로 부터 넘겨받은 주소값이므로
		//이 g로 그림을 그리게 될 경우, 당연히
		//그림은 패널에 그려지게 된다!!
		g.drawImage(img, x, y, width, height, this.gamePanel);
	}
}
