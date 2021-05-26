package app0526.thread.ex4;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMain extends JFrame implements ActionListener {
	GamePanel gamePanel;
	JMenuBar bar; // 게임 제어 패널
	JMenu m_control; // 설정
	JMenuItem item_start; // 시작
	JMenuItem item_pause; // 잠시멈추기
	JMenuItem item_exit; // 게임 종료

	public GameMain() {
		gamePanel = new GamePanel();
		bar = new JMenuBar();
		m_control = new JMenu("게임제어");
		item_start = new JMenuItem("start");
		item_pause = new JMenuItem("pause");
		item_exit = new JMenuItem("exit");

		// 조립
		m_control.add(item_start);
		m_control.add(item_pause);
		m_control.add(item_exit);
		bar.add(m_control);
		setJMenuBar(bar); // 프레임에 바 부착!!

		add(gamePanel);

		//리스너와 연결
		item_start.addActionListener(this);
		item_pause.addActionListener(this);
		item_exit.addActionListener(this);
		
		
		pack(); // 내부 컨텐츠 크기까지 줄어듦
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==item_start) {
			
		}else if (e.getSource()==item_pause) {
			
		}else if (e.getSource()==item_exit) {
			gamePanel.gameFlag=false; //게임 쓰레드 Dead
			System.exit(0);//프로세스 종료
		}
	}


	
	
	public static void main(String[] args) {
		new GameMain();
	}
}
