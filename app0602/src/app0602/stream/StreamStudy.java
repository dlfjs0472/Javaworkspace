package app0602.stream;

import java.io.IOException;
import java.io.InputStream;

public class StreamStudy {

	public static void main(String[] args) {
		//System.out.print("야호~"); //어디로 출력되나???  표준스트림으로 처리됨..
		
		//키보드를 통해 데이터를 입력받아보자
		//FileInputStream fis = new~~  파일 스트림은 생성 시점을 개발자 정할수 있는 스트림이다!
		//하지만, 키보드, 모니터, 바코드스캐너, 프린터기 등등은 하드웨어를 시스템인 OS가 관리하므로, 
		//컴퓨터를 켜는 순간부터 이미 스트림은 존재하게 된다...따라서  new 가 아닌, 시스템인 os로 부터 얻을수
		//있고, 자바에서 이 os인 시스템을 접근할때 사용되는 객체가 바로 System이다!!
		InputStream is = System.in; //여기서 들어오게 되는 데이터는 키보드 or 바코드스캐너 등등 입력도구라면 모든 가능!!
													//즉 지금 동작시킨 하드웨어에 의해 데이터가 들어와 진다!!
		
		//현재  is 는 바이트기반 스트림이므로, 1byte씩 처리할 수 있다.
		int data=-1;
		
		try {
			while(true) {
				data = is.read();
				System.out.println((char)data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}

}