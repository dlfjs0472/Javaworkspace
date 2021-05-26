package app0526.thread.ex4;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import util.ImageManager;

//사실상 윈도우는 껍데기에 불과하고, 모든 게임은 이 패널에서 그려질 것이다!!
public class GamePanel extends JPanel{
	Thread gameThread; //게임루프 즉 심장을 뛰게 할 게임 운영 쓰레드
	boolean gameFlag=true; //게임 진행 여부를 결정짓는 논리값
	public static final int WIDTH=1280;
	public static final int HEIGHT=900;
	ImageManager imageManager=new ImageManager();
	Image bgImg; //배경 이미지
	
	Hero hero;
	String[] enemyPath= {"e1.png","e2.png","e3.png","e4.png","e5.png"};
	Enemy[] enemyArray = new Enemy[5];
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		gameThread=new Thread() {
			public void run() {
				while(gameFlag) {
					gameLoop();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		createBg();
		createHero();
		createEnemy();
		gameThread.start();
		
	}
	
	//배경생성
	public void createBg() {
		ImageIcon icon=imageManager.getScaledIcon("bg.jpg", WIDTH, HEIGHT);
		
		bgImg=icon.getImage(); //배경이미지 얻기
	}
	
	//주인공 생성
	public void createHero() {
		hero = new Hero(this,"plane1.png",100,200,90,120,1,0);
	}
	
	//적군 생성
	public void createEnemy() {
		for(int i=0; i<enemyPath.length; i++) {
			//Enemy(GamePanel gamePanel, String path, int x, int y, int width, int height, int velX, int velY)
			enemyArray[i] = new Enemy(this, enemyPath[i], 800, i*110, 90, 120, -3, 0);
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bgImg, 0, 0, WIDTH, HEIGHT, this);
		hero.render(g);
		for(int i=0; i<enemyArray.length; i++) {
			enemyArray[i].render(g);
		}
	}
	
	public void gameLoop() {
		//게임에 등장하는 모든 오브젝트를 움직이게 하려면, tick,render호출
		hero.tick();
		this.repaint(); //hero는 이미 랜더링이 되어있으므로 hero.render()이 아니라 패널을 다시그린다
		
		for(int i=0; i<enemyArray.length; i++) {
			enemyArray[i].tick();
		}
		
		
	}
}
