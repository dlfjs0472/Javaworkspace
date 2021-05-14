package io.bytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class CopyImage{
	/*	이미지 파일을 복사해보자	*/
	FileInputStream fis;
	FileOutputStream fos;


	public CopyImage(){
		try{
			fis = new FileInputStream("D:/korea202102_javaworkspace/app0514/res/dog.jpg"); //shift+Alt+z 단축키 = try,catch 생성
			fos = new FileOutputStream("D:/korea202102_javaworkspace/app0514/res/dog_copy.jpg");
			
			//입력 스트림으로는 데이터의 1바이트씩 읽어들이고, 출력스트림으로는 데이터를 1바이트씩 출력된다(복사)
			while(true){
				int data = fis.read(); //1byte 읽기(입력)
				if(data==-1)break;
				fos.write(data);//1byte 쓰기(출력)
			}
			System.out.println("복사완료");
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("해당 파일을 찾을 수 없습니다.");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("해당 파일을 읽어들일 수 없습니다.");
		}finally{
		//스트림은 반드시 닫아야 한다 & 아래의 코드는 객체의 생성이 성공했을때만 수행하라
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

	public static void main(String[] args){
		new CopyImage();
	}
}
