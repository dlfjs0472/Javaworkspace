package app0517.stream.character;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*문자기반 스트림을 학습하면서, 동시에 보다 효율적으로 데이터를 처리하는 법에 대해 알아보자! */
public class FileReadApp {
	FileReader reader;
	BufferedReader buffr; //문자를 대상으로 한줄씩 읽을수 있는 스트림 
	File file;
	String path="D:\\korea202102_javaworkspace\\노트필기\\자바13일차.txt";
	
	public FileReadApp() {
		try {
			reader = new FileReader(file = new File(path)); //파일을 대상으로 빨대를 꽂음!!
			buffr = new BufferedReader(reader);//기존의 FileReader를 버퍼처리된 리더로 업그레이드
			
			String data=null;
			
			while(true) {
				//data=reader.read(); //read a byte 가 아니라 read single character(문자) ※FileReader에서 BufferedReader로 업그레이드 됬으니 읽기도 업그레이드↓
				data=buffr.readLine(); // 한줄을 읽어들임...따라서 반환형은 문자열이다!!
				if(data==null)break; //파일의 끝을 만나면 -1 반환하므로, 반복문 멈추기!! → readLine으로 업그레이드 ※ readLine은 파일의 끝을 만나면 null을 반환
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		new FileReadApp();
	}
}