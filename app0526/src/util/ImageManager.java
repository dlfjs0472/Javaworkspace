package util;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageManager {

	
	//resource 폴더로 등록된 자원은 클래스패스를 통해 접근해야 한다!!
	//그리고 클래스 패스와 관련된 정보를 얻으려면, Class 클래스를 이용하면 된다!!!
	//자바의 클래스 중 class에 대한 정보를 가진 클래스가 Class 이다!! 이 Class 클래스를 이용하면, 클래스코드를 static에 올릴 수
	//도 있고(Class.forName("드라이버명")) , 해당 클래스가 가진 메서드, 속성 등을 추출할 수도 있다..
	//Class myClass = this.getClass();
	//Method[] methods = myClass.getMethods(); //이 클래스가 보유한 메서드 목록을 Method 객체배열로 반환
	//System.out.println("이 클래스가 보유한 메서드 수는"+methods.length);
	
	//개발자가 특정 디렉토리를 resource로 등록하면, getResource() 메서드로
	//클래스 패스를 이용하여 접근이 가능~!!
	public ImageIcon getScaledIcon(String filename, int width , int height) {
		ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource(filename));
		//이미지의 크기를 줄이는 메서드는 Image 추상클래스에서 지원하므로, 현재 아이콘을 잠시 이미지로 변환해보자!!
		icon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		
		return icon;
	}
}
