package app0526.thread.ex4;

import java.awt.Graphics;

public class Enemy extends GameObject{
	
	public Enemy(GamePanel gamePanel, String path, int x, int y, int width, int height, int velX, int velY) {
		//자신을 초기화 하기 전에, 부모의 초기화가 더 시급하다
		super(gamePanel, path, x, y, width, height, velX, velY);
	}

	public void tick() {
		this.x+=this.velX;
		this.y+=this.velY;
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, this.gamePanel);
	}
}
