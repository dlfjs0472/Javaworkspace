package app0602.cal.basic;

import java.util.Calendar;

public class CalendarTest {

	
	public static void main(String[] args) {
		//추상클래스이기 때문에 static 클래스 메서드로 인스턴스 얻었음
//		Calendar cal = Calendar.getInstance(); 
//		System.out.println(cal.get(Calendar.YEAR)); //연도출력
//		System.out.println(cal.get(Calendar.MONTH)+1); //월 출력 (대부분의 프로그램에서는 달은 0부터 시작 출력할때만 +1)
//		System.out.println(cal.get(Calendar.DATE)); //일출력
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK)); //요일은 일요일 기준 1부터 시작함 ex) 수요일 = 일(1) 월(2) 화(3) 수(4)
		
		//조작
//		//오늘 만난 커플의 100일 뒤기념일은?
//		Calendar cal2 = Calendar.getInstance();
//		cal2.set(Calendar.DATE, cal.get(Calendar.DATE)+100); //오늘 날짜에 100을 더한 날로 조작
//		int mm =cal2.get(Calendar.MONTH); //미래의 조작된 월(100일 후)
//		int dd=cal2.get(Calendar.DATE); //미래의 조작된 일(100일 후)
//		System.out.println((mm+1)+"월 "+dd+"일");
		
		
		//8.15일 광복의 날로 시간을 과거로 세팅하여, 무슨요일인지 출력!!
		Calendar cal3 =Calendar.getInstance();
		cal3.set(1945,8-1,15);
		int yy=cal3.get(Calendar.YEAR);
		int mm=cal3.get(Calendar.MONTH); //0부터 시작
		int dd=cal3.get(Calendar.DATE);
		int day=cal3.get(Calendar.DAY_OF_WEEK); //일욜기준 1부터시작
		String[] title = {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
		
		
		System.out.println(yy+"년 "+(mm+1)+"월 "+dd+"일 "+ title[day-1]+"요일");
		
		
		
		
		
	}
}
