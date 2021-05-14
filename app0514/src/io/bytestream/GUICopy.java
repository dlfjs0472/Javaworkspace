package io.bytestream;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 파일복사기능을 GUI기반으로 처리해본다
 */

public class GUICopy extends JFrame implements WindowListener, ActionListener {
	JButton bt_open; // 복사원본 탐색창 열기
	JTextField t_open;
	JButton bt_target; // 복사될 파일 탐색창 열기
	JTextField t_target;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_copy;
	JFileChooser chooser; // 파일 탐색기 창객
	
	FileInputStream fis; //파일용 입력 스트림
	FileOutputStream fos; //파일용 출력 스트림

	public GUICopy() {
		// 생성
		bt_open = new JButton("원본선택");
		t_open = new JTextField();
		bt_target = new JButton("복사위치");
		t_target = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_copy = new JButton("복사실행");
		chooser = new JFileChooser("D:\\korea202102_javaworkspace\\app0513\\res\\images");
		

		// 스타일,레이아웃
		setLayout(new FlowLayout());
		bt_open.setPreferredSize(new Dimension(100, 25));
		bt_target.setPreferredSize(new Dimension(100, 25));

		t_open.setPreferredSize(new Dimension(370, 25));
		t_target.setPreferredSize(new Dimension(370, 25));
		scroll.setPreferredSize(new Dimension(475, 180));

		// 조립
		add(bt_open);
		add(t_open);
		add(bt_target);
		add(t_target);
		add(scroll);
		add(bt_copy);

		this.addWindowListener(this);// 이벤트 연결
		bt_open.addActionListener(this);
		bt_target.addActionListener(this);
		bt_copy.addActionListener(this);

		// 이벤트연결

		// 보이기
		setBounds(600, 100, 500, 350);
		setVisible(true);

		// 아래의 메서드 호출하면 안되는 이유? 윈도우 창 닫을때 스트림을 닫는 처리를 하기 위해...
		// setDefaultCloseOperation(EXIT_ON_CLOSE); //윈도우창 닫으면 프로그램이 종료

	}

	public void openFile() {
		// 파일 열기용 탐색기 띄우기
		int res = chooser.showOpenDialog(this); // 탐색기 창들은 모두 윈도우에 의존적이다~!
		if (res == JFileChooser.APPROVE_OPTION) {// OK버튼 눌렀을때
			//선택한 파일의 디스크상의 풀 시스템 경로를 얻어와서 JTextField에 출력하자
			
			//java.io에 들어있는 File 클래스는 해당 파일에 대한 정보를 담고 있는 객체이다
			File file = chooser.getSelectedFile();
			t_open.setText(file.getAbsolutePath());//텍스트필드에 파일의 풀 경로를 출력
		} 
	}

	public void saveFile() {
		int res=chooser.showSaveDialog(this);
		if(res==JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			t_target.setText(file.getAbsolutePath());
		}
	}

	public void copyFile() {
		//정해진 두 경로를 이용하여, Stream을 만들어 입출력을 시도해보자
		try {
			fis = new FileInputStream(t_open.getText());
			fos = new FileOutputStream(t_target.getText());
			area.append("입력&출력 스트림 생성 완료\n");
			
			//복사 수행
			int data=-1;
			while(true) {
				data = fis.read(); //1byte 읽기(입력)
				if(data==-1)break;
				fos.write(data); //1byte 쓰기 (출력)
				area.append(data+"\n");
			}
			area.append("복사완료!!!\n");
			JOptionPane.showMessageDialog(this, "복사완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis !=null) {//즉 객체가 존재할 때만..
				try {
					fis.close();//스트림을 닫을 때에도 IO예외가 발생할수 있다고 우려하니 트라이,캐치문을 사용해야 한다
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			if(fos !=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_open) {
			openFile();
		} else if (e.getSource() == bt_target) {
			saveFile();
		} else if (e.getSource() == bt_copy) {
			copyFile();
		}

	}

	public static void main(String[] args) {
		new GUICopy();
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.out.println("저 닫혀요, 닫을것이 있다면 닫을께요..(DB,Stream..등)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

}
