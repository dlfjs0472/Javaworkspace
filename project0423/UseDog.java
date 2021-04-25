/*이 클래스는 객체를 표현하기 위함이 아니라 실행하기 위한
클래스이다...*/
class UseDog{
	//자바의 실행부를 작성 

	//main메서드는 특별하기에 개발자가 직접호출하는 방법은 없고
	//java.exe 파일에 의해 호출된다...
	public static void main(String[] args){
		int x=1;
		Dog d = new Dog(); //내가 만든 자료형이기에 사용자
		//정의 자료형이라고도 한다..	
		System.out.print(d.name);
	}	
}
