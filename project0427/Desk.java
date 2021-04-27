  
public class Desk {
	int x=0;
	static int y;
	
	//굳이 반복문을 포함하여 코드를 수행하고 싶다면? 블럭을 만들수 있다..
	//인스턴스 초기화 블럭
	//이 클래스인 Desk로부터 인스턴스가 생성될 때마다 , 아래의 코드가 실행
	//static 붙이면: static 초기화 블럭
	/*
	1) 인스턴스 초기화 블럭: 해당인스턴스가 생성될때 동작하는 코드 영역
								  생성자의 역할과 비슷...
	2)  static 초기화 블럭: 실행직전에 초기화 블럭이 동작	
	*/
	static{
		for(int i=1;i<=10;i++){
			x+=i;
		}
	}
	{ //A
		for(int i=0;i<10;i++){
			x++;
		}
	}
	
	
	static { //B
		for(int i=0;i<20;i++){
			y++;
		}
	}
	                              
	public static void main(String[] args){
		System.out.println(x); //C
		int a=new Desk().x; //D
		System.out.println(a); //E
		System.out.println(y); //F
		
	}
}