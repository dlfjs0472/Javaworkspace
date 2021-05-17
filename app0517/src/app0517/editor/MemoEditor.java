package app0517.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//메모장 기능중 스트림으로 처리할 수 있는 부분을 코드로 구현해본다!!
public class MemoEditor extends JFrame {
	JMenuBar bar;
	JMenu m_file;
	JMenuItem item_new; // 새파일
	JMenuItem item_open; // 열기
	JMenuItem item_save; // 저장
	JMenuItem item_saveas; // 새이름으로 저장
	JMenuItem item_exit; // 끝내기
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;// 파일 탐색기
	String Target=null;
	
	

	public MemoEditor() {
		// 생성
		bar = new JMenuBar();
		m_file = new JMenu("파일");
		item_new = new JMenuItem("새파일");
		item_open = new JMenuItem("열기");
		item_save = new JMenuItem("저장");
		item_saveas = new JMenuItem("새이름으로 저장");
		item_exit = new JMenuItem("끝내기");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		chooser = new JFileChooser("D:\\korea202102_javaworkspace\\app0514\\src");

		// 스타일 & 레이아웃

		// 조립
		m_file.add(item_new);
		m_file.add(item_open);
		m_file.add(item_save);
		m_file.add(item_saveas);
		m_file.add(item_exit);

		bar.add(m_file); // 메뉴바에 파일메뉴 부착

		setJMenuBar(bar); // 프레임에 메뉴 부착!!
		add(scroll);

		// 리스너연결

		// 아래와 같은 이름없는 클래스를 가리켜 내부익명클래스라 한다..
		// 내부익명 클래스의 사용목적? 원래 클래스란 코드의 재사용성을 위한 거푸집이 목적이다..
		// 하지만, 클래스 중에는 재사용성이 별로 없는 1회성의 클래스도 있다..이런 경우 굳이 개발자가 .java파일을
		// 물리적으로 정의해가면서까지 개발할 필요가 있는가?? 따라서 sun에서는 클래스 안에 이름없는 클래스를 넣을수있도록
		// 내부익명 클래스를 지원해주며, 주로 이벤트 구현시 압도적으로 많이 사용된다...
		// 또한 내부익명 클래스를 사용하면, 객체간 주소를 전달해야하는 불편함도 해소할수 잇다..즉 내부익명클래스는 외부클래스
		// 의 멤버들을 자기껏 처럼 접근할 수 있다..
		item_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 파일열기 아이템을 누르면, JTextArea에 "안녕" 이라는 문자열을 출력해본다 ←액션핸들러에 의지
				openFile();
			}
		});

		// 새파일 처리
		item_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 새 파일은 area를 깨끗이 비워놓는 것임..
				area.setText(""); // 공백문자로 대체
			}
		});

		// 새이름으로 저장
		item_saveas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSaveDialog();
				/*
				 1) showSaveDialog() 호출하여 파일저장 탐색기 띄운다 
				 2) 선택한 파일경로 얻는다 
				 3) 빈파일을 파일출력스트림으로 생성한다
				 4) 생성된 파일출력스트림을 통해 area의 내용을 파일에 넣는다(출력)
				 */
			}
		});

		// 보여주기
		setBounds(600, 100, 700, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 무언가 창을 닫을때, 자원을 반납하려면
		// 이 메서드 호출대신 WindowListener를 구현하자!
	}
	
	public void showSaveDialog() {
		int res = chooser.showOpenDialog(this);
		
		if (res == JFileChooser.APPROVE_OPTION) {
			System.out.println(res);
			
		}
		
	}

	// 파일 열어서, 편집기창에 출력하기 (불러오기)
	public void openFile() {
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// 선택한 파일에 대한 입력스트림을 생성하여, 내용을 읽어와서 area에 출력!!
			File file = chooser.getSelectedFile(); // 선택한 파일 반환 받음

			// 문자(영문,한글 모두: 즉 2byte를 이해함)를 읽을수 있는 스트림 생성
			FileReader reader = null; // close하기 위해 밖으로 뺌
			BufferedReader buffr = null; // 문자스트림을 대상으로 버퍼를 지원하는 스트림

			try {
				reader = new FileReader(file);// 파일을 대상으로 빨대 꽂음
				buffr = new BufferedReader(reader); // 버퍼처리된 스트림으로 빨대업그레이드

				String data = null; // 읽혀진 문자(정확히는 문자의 코드)를 담게될 정수

				while (true) {
					data = buffr.readLine(); // 한 문자열 즉 한 줄(케릭터들을 모아서) 읽기
					if (data == null)
						break;
					area.append(data + "\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (buffr != null) {
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		new MemoEditor();
	}
}