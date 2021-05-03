package api;
class StringTest2{
	public static void main(String[] args) {
		//String은 객체이지만, 너무나 많이 쓰이기 때문에 마치 기본자료형 처럼 객체를 생성하는 방법을
		//지원한다..즉 아래와 같다..
		//암시적(=묵시적)생성법  , implicit 생성법이라 한다
		String str1="apple";	//엄연히 객체임~~
		String str2="apple";
		System.out.println(str1==str2); //주소값 비교이지만, 같은 주소를 가리키므로 마치 내용비교처럼
														//결과가 true 가 된다..	
		
		//String도 클래스 이므로, 이번에는 생성자를 이용해보자 
		//명시적(explicit) 생성법
		String s1=new String("korea");
		String s2=new String("korea");
		System.out.println(s1==s2); // 명시적 생성법으로 String 객체를 생성하면 효율성 여부를 따지지
													//않게 되서  constant pool에 만들어지지 않는다..따라서 s1,s2
													//가리키고 있는 객체의 인스턴스가 다르므로, 결과는 false다

	}
}