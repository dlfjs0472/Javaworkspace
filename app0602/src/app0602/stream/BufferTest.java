package app0602.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//키보드로부터, 문자를 입력받아 모니터에 출력하되, 한글이 깨지지 않아야 하고, 
//한줄씩 입력받아, 한줄씩 출력하시오!!
public class BufferTest {
	public static void main(String[] args) {
		InputStream is=System.in;
		 
		InputStreamReader reader = new InputStreamReader(is);//문자기반 스트림으로 변환
		BufferedReader buffr = new BufferedReader(reader); //한줄씩 처리가 가능한 버퍼 기반의 스트림 
		
		String data=null; //한줄씩 읽으므로, String 객체 
		
		while(true) {
			try {
				data = buffr.readLine(); //문자 기반 스트림으로, 한문자를 읽어들임!! 즉 한글 안깨진다
				System.out.println(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//BufferedReader buffr  = new BufferedReader();
		
	}
}