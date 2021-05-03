package api;

public class StringTest3{
	public static void main(String[] args){
		String s1="korea";
		String s2="korea";
		System.out.println(s1==s2); //s1,s2는 레퍼런스 변수이기에, 비교연산자
													//는  서로의 주소값을 비교하게 된다..
		//그렇다면 내용을 비교하려면??? 
		boolean result = s1.equals(s2);  //s1과 s2가 내용이 같은가? 
		System.out.println("두 문자열의 내용 비교 결과는 "+result);
	}
}