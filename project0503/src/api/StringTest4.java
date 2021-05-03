package api;
public class StringTest4{
	public static void main(String[] args) {
		/*
		String 클래스를 가리켜 immutable(불변)의 속성이 있다고 한다
		*/
		String s1 = "korea";
		s1=s1+" vs usa"; 
		System.out.println(s1);

		//무서운 코드 
		//아래의 코드는 result 가 변경되는 것이 아니라, result와 같은 값에 계속 새로운 문자열이
		//생성되는 것이다..따라서 총 100개이상의 문자열 상수가 메모리에 올라온다!!
		String result="";
		for(int i=0;i<100;i++){
			result+="apple "+i;
		}
		//위 문제를 해결하려면, 수정 가능한 문자열 처리 관련 api를 지원받으면 된다..
		//StringBuffer, StringBuilder 객체를 이용하면 된다!!!
		StringBuffer sb = new StringBuffer(); //String 클래스 X
		sb.append("야호");
		sb.append(" 나는");
		sb.append(" 자바가");
		sb.append(" 좋아");
		System.out.println(sb.toString());

	}
}