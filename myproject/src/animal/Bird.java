package animal; //컴파일시, 이 Bird의 .class를 animal이란 패키지를
			//자동 생성하여, 넣어준다.. javac -d옵션에 의해..
//패키지에 넣어둔 클래스는 아무도 접근은 못하므로, 공개해야 한다..
//공개를 결정짓는 키워드인 public(접근제어자) -내일 배울꺼임..
public class Bird{ //패키지 선언시 무조건 해준다!!!
	String name="제비";

	public void fly(){
		System.out.println(name+"가 날아갑니다");
	}
}