package io.charstream;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//메모장 편집기의 기능중 읽기 기능만을 구현해보자!!

public class MemoBasic extends JFrame implements ActionListener {
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;

	public MemoBasic() {
		// 생성
		bt = new JButton("Load");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		chooser = new JFileChooser("D:\\korea202102_javaworkspace\\app0514\\res");

		// 스타일,레이아웃

		// 리스너연결
		bt.addActionListener(this);

		// 조립
		add(bt, BorderLayout.NORTH);
		add(scroll);

		// 보이기
		setBounds(600, 100, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	//바이트기반 스트림으로 처리할 경우...
	public void openFileByte() {
		if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			
			//스트림을 생성(문자해독능력이 없다!!)
			FileInputStream fis=null;//지역변수는 컴파일러가 초기화해주지 않는다!!(직접해야 한다) & try 내에서 선언할시 finally에서 접근이 불가능 하므로 적어도 그 위쪽에서 선언해줘야한다
			try {
				fis = new FileInputStream(file.getAbsolutePath());
				//1바이트씩 읽어서, area에 출력!!!
				int data = -1; //read()할때 담아놓을 변수선언
				while(true) {
					data = fis.read();//1byte 읽기
					if(data==-1)break;
					//System.out.println(data);
					area.append(Character.toString((char)data));
					//결론 : 복사는 가능하나 해석은 불가능 하다
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	//문자기반 스트림으로 처리할 경우...
	public void openFileChar() {
		int res = chooser.showOpenDialog(this);
		if(res==JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			FileReader reader=null;
			
			//스트림을 생성
			try {
				reader=new FileReader(file.getAbsolutePath());//문자 해석 능력이 있는 스트림(2byte묶어서 이해 할 수 있다)
				
				int data=-1;
				while(true) {
					data = reader.read(); //read a character (문자를 읽음)		
					if(data==-1)break;
					area.append(Character.toString((char)data));
				}
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(reader!=null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		//openFileByte();
		openFileChar();
	}

	public static void main(String[] args) {
		new MemoBasic();
	}
}
