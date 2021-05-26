package app0526.thread.ex4;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//모든~~ 게임에 등장하는 객체가 가질 수 있는 보편적 요소들을 보유할 최상위 객체 선언
public abstract class GameObject {
	GamePanel gamePanel;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	Image img;
	
	public GameObject(GamePanel gamePanel,String path ,int x, int y, int width, int height, int velX, int velY) {
		this.gamePanel=gamePanel;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
		ImageIcon icon=this.gamePanel.imageManager.getScaledIcon(path, width, height);
		img=icon.getImage();
	}
	
	//채워넣지도 말아야하며, 채워넣을 수도 없다!!
	public abstract void tick(); //장래의 자식이 누군진 몰라도, 그 자식이 완성하도록 미완성상태로 두기
	public abstract void render(Graphics g); //여기서의 g는 모든 게임 오브젝트 그려질 JPanel 의 paint메서드의 것!!!
}
