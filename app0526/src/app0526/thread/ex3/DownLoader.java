package app0526.thread.ex3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import util.FileManager;
import util.ImageManager;

//웹상의 자원을 수집하고, 다운로드 받는 진행상황을 프로그래스바로 표시하자!!
public class DownLoader extends JFrame{
	JTextField t_url;
	JButton bt;
	JProgressBar bar;
	ImageManager imageManager=new ImageManager();
	ImageIcon icon; //Icon 인터페이스를 구현한 클래스
	InputStream fis;
	FileOutputStream fos;
	HttpURLConnection httpCon; //http 요청을 위한 객체
	Thread thread;
	
	
	
	public DownLoader() {
		t_url = new JTextField();
		//resource 폴더로 등록된 자원은 클래스패스를 통해 접근해야 한다!!
		//그리고 클래스 패스와 관련된 정보를 얻으려면, Class 클래스를 이용하면 된다!!!
		//자바의 클래스 중 class에 대한 정보를 가진 클래스가 Class 이다!! 이 Class 클래스를 이용하면, 클래스코드를 static에 올릴 수
		//도 있고(Class.forName("드라이버명")) , 해당 클래스가 가진 메서드, 속성 등을 추출할 수도 있다..
		//Class myClass = this.getClass();
		//Method[] methods = myClass.getMethods(); //이 클래스가 보유한 메서드 목록을 Method 객체배열로 반환
		//System.out.println("이 클래스가 보유한 메서드 수는"+methods.length);
		
		//개발자가 특정 디렉토리를 resource로 등록하면, getResource() 메서드로
		//클래스 패스를 이용하여 접근이 가능~!!
		
		bt = new JButton(imageManager.getScaledIcon("bt.png", 40, 40));
		bar = new JProgressBar();
		
		//스타일 레이아웃
		setLayout(new FlowLayout());
		t_url.setPreferredSize(new Dimension(470, 35));
		bar.setPreferredSize(new Dimension(470, 35));
		bt.setPreferredSize(new Dimension(45, 40));
		bt.setBorder(null);
		
		
		//add
		add(t_url);
		add(bt);
		add(bar);
		
		//리스너연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						downLoad();						
					}
				};
				thread.start();
			}
		});
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 400, 500, 180);
		
		
	}

	public void downLoad() {
		try {
			URL url = new URL(t_url.getText()); // 사용자가 입력한 url 주소를 이용한 URL 객체생성
			httpCon = (HttpURLConnection)url.openConnection(); // url이 부모 httpCon이 자식이므로 형변환해야한다
			httpCon.setRequestMethod("GET");
			fis = httpCon.getInputStream(); //서버의 자원과 입력스트림 연결!!
			
			//파일의 총 크기를 구한다
			int total =httpCon.getContentLength();
			System.out.println("토탈은"+total);
			
			//파일명 결정
			long time = System.currentTimeMillis();
			String ext = FileManager.getExtend(t_url.getText(), "/");
			String filename=time+"."+ext;
			fos=new FileOutputStream("D:\\korea202102_javaworkspace\\app0526\\data\\"+filename);
			
			
			int data=-1;
			int count=0;
			
			while(true) {
				data=fis.read(); //1byte읽기!!
				
				//count와  total의 비율을 이용하여 백분율을 구한 후, 프로그래스바에 반영해보자!!
				float percent = count/(float)total*100; //둘중 하나는 소수점으로 변환해야 한다
				bar.setValue((int)percent);
				
				if(data==-1)break;
				count++; //데이터가 존재할때만 증가시키자!!
				
				fos.write(data);
			}
			System.out.println("카운트는"+count);
			JOptionPane.showMessageDialog(this, "다운로드 완료");
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	
	public static void main(String[] args) {
		new DownLoader();
	}
}












