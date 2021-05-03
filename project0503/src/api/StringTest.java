/*
지금까지는 우리가 정의한 클래스 위주로 학습을 해왔다..하지만, 실제 개발시
이미 sun에서 지원하는 유용한 클래스들을 주로 활용하게 된다...
이번 시간은 sun에서 제공하는 여러 클래스 중 문자열 처리시 중요한 기능을 담당하는 
String 클래스에 대해 학습해보자!!
*/
package api;

class StringTest{ 
	public static void main(String[] args) {
		//String도 원래  import한적이 없으므로, 에러가 나야 하지만 
		//사실 너무나 많이 사용되는 즉 유용한 클래스들은 개발자가 명시하지 않아도 
		//이미 기본적으로 import 되어 있다.. java.lang 패키지가 바로 그것이다!!!
		String s=new String("apple");//오버로딩 된 여러 생성자 중 하나임 .. 
		// string 클래스의 api document 를 이용하여, 개발자는 여러 기능들을 참조할 수 있다..

		//문자열의 길이를 알수있는 api  문서 링크 
		System.out.println("문자열의 길이는 "+s.length());

		//대문자로 변환하는 메서드 api문서 링크 
		System.out.println(s.toUpperCase());
		
		//두번째p의 index를 반환하는 api문서 링크 
		System.out.println("마지막 p의 index는 "+s.lastIndexOf("p"));

		//원하는 번째에 있는 문자 1개를 추출하는 api문서 링크 
		System.out.println(" chatAt의 결과 "+s.charAt(4));

	}
}