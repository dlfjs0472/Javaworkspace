package app0602.cal.diary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app0602.common.StringManager;

public class Diary extends JFrame{
	JPanel p_north;
	JButton bt_prev;
	JLabel la_title;
	JButton bt_next;
	
	JPanel p_center; //날짜 박스 처리할 영역
	String[] dayArray= {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
	
	//원하신 시점에 날짜 박스를 제어하기 위해서, 각 날짜 박스객체들을 배열에 담아놓자!!
	DateBox[] boxArray=new DateBox[dayArray.length*6];
	Calendar currentDate; //현재 날짜 정보를 가진 객체
	
	public Diary() {
		p_north = new JPanel();
		bt_prev = new JButton("이전");
		la_title = new JLabel("연도 월 올 예정", SwingConstants.CENTER);
		bt_next = new JButton("다음");
		p_center = new JPanel();
		
		//스타일
		la_title.setFont(new Font("Arial-Black", Font.BOLD, 30));
		la_title.setPreferredSize(new Dimension(500, 30));
		
		//조립 
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		getCurrentDate(); //현재날짜 객체 구하기
		setDateTitle();//달력 제목 달기
		createDay(); //요일생성
		createDate();//날짜생성
		printDate();//각 박스에 날짜 출력
		
		//테스트
		//System.out.println(getFirstDayOfMonth(2021, 6-1)); //6월 
		//System.out.println((6)+"월은 "+getLastDate(2021, 6) +"일까지 입니다"); //6월이 몇일까지 있나? 
		
		//이벤트 
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMonth();
			}
		});
		
		bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevMonth();
			}
		});
		
		//보여주기
		setVisible(true);
		setBounds(400, 100, 780, 780);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//현재날짜 구하기(프로그램 가동과 동시에 사용될 디폴트 날짜 객체) 
	public Calendar getCurrentDate() {
		currentDate = Calendar.getInstance();
		return currentDate;
	}
	
	//달력의 제목 즉, 날짜 출력 
	public void setDateTitle() {
		int yy = currentDate.get(Calendar.YEAR);
		int mm = currentDate.get(Calendar.MONTH);
		
		//제목에 출력
		la_title.setText(yy+"-"+StringManager.getZeroString(mm+1));
	}
	
	//요일 생성 
	public void createDay() {
		for(int i=0;i<7;i++) {
			DateBox dayBox=new DateBox(dayArray[i],Color.GRAY,100,70);
			p_center.add(dayBox); //센터에 부착!!
		}
	}
	
	//날짜 생성 
	public void createDate() {
		for(int i=0;i<dayArray.length*6;i++) {
			DateBox dateBox = new DateBox("", Color.orange, 100,100);
			p_center.add(dateBox); //센터에 부착!!
			
			//배열에 담아놓아야, 추후 필요할때 사용이 가능!!
			boxArray[i]=dateBox;
		}
	}
	
	//해당 월의 시작 요일 구하기!!
	//개발원리: 날짜 객체를 해당월의 1일로 조작한 후, 요일을 구한다!!
	//단 해당월과 연도는 매개변수로 호출자가 그 값을 결정할 수 있도록 메서드 정의하자
	//호출방법:  2월에 대한 정보를 구할 경우  getFirstDayOfMonth(2021, 2)
	public int getFirstDayOfMonth(int yy, int mm) {
		Calendar cal=Calendar.getInstance(); //날짜 객체 생성 
		cal.set(yy,mm, 1);//해당년도와 월의 1을로 조작!!
		
		return cal.get(Calendar.DAY_OF_WEEK)-1; //주의 js 에서의 습관이 있기에 요일은 0부터 시작하는 걸로..		
	}
	
	//해당월이 몇일까지 있는지 구하기!!  8월 31일, 9월 30일
	//호출방법:  2월에 대한 정보를 구할 경우  getLastDate(2021, 2)
	public int getLastDate(int yy, int mm) { 
		Calendar cal = Calendar.getInstance();
		cal.set(yy,mm, 0); //0일이란 존재하지 않는 날짜이므로, 이전 월의 마지막날을 의미한다!!!
		
		//System.out.println(yy+"년 "+mm+"월은 "+cal.get(Calendar.DATE)+"일까지 있네요");
		return cal.get(Calendar.DATE);
	}
	
	//Box에 날짜 출력!!!
	public void printDate() {
		int n=1;
		
		for(int i=0;i<boxArray.length;i++) {
			//날짜는 아무때나 찍는게 아니라, 해당 월의 시작 요일 이상일때만 찍자!!
			int yy=currentDate.get(Calendar.YEAR);
			int mm=currentDate.get(Calendar.MONTH);
			
			if(i>=getFirstDayOfMonth(yy,mm) ) {
				//각월의 총 날수까지만 출력되게..
				if(n <=getLastDate(yy, mm+1)) {
					boxArray[i].day=Integer.toString(n);
					boxArray[i].repaint(); //텍스트를 다시 그리자!! 즉 그래픽 갱신!!
					
					//아이콘 올려놓기!!
					if(n==11) {					
						boxArray[i].img=getIcon("cake.png");
					}else if (n==5) {
						boxArray[i].img=getIcon("graduate.png");						
					}
					n++;
				}
			}
			
		}
	}
	
	//기존의 Box에 그려진 스트링 모두 지우기 
	public void removeString() {
		for(int i=0;i<boxArray.length;i++) {
			boxArray[i].day="";
			boxArray[i].repaint(); //그림 다시 갱신
		}
	}
	
	
	//이전월 구하기
	public void prevMonth() {
		//현재 날짜 객체에 월을+1한다, 기왕이면 날짜는 1일로...
		int yy=currentDate.get(Calendar.YEAR); //현재 연도 
		int mm = currentDate.get(Calendar.MONTH); //현재 월 
		currentDate.set(yy, mm-1,1); //yy, mm, dd
		
		removeString();//기존 날짜 지우기
		setDateTitle(); //달력 제목 바꾸기 
		printDate(); //날짜 출력하기
	}
	
	//다음 월 구하기 
	public void nextMonth() {
		//현재 날짜 객체에 월을+1한다, 기왕이면 날짜는 1일로...
		int yy=currentDate.get(Calendar.YEAR); //현재 연도 
		int mm = currentDate.get(Calendar.MONTH); //현재 월 
		currentDate.set(yy, mm+1,1); //yy, mm, dd
		
		removeString();//기존 날짜 지우기
		setDateTitle(); //달력 제목 바꾸기 
		printDate(); //날짜 출력하기
	}
	
	//이 메서드를 호출하면, 적절한 이미지를 반환해주게 처리!!
	public Image getIcon(String filename) {
		URL url = this.getClass().getClassLoader().getResource(filename);
		ImageIcon icon=new ImageIcon(url);
		return icon.getImage(); //이미지로 변환하여 반환
	}
	
	public static void main(String[] args) {
		new Diary();
	}
}