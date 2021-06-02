package app0602.common;

//각종 유용한 문자열 처리를 쉽게 처리하는 전담 객체
public class StringManager {

	//넘겨받은 인수가 1자리 수 인 경우 앞에 '0' 문자를 붙이고
	//2자리수 라면 그냥 쓴다
	public static String getZeroString(int n) {
		return (n<10)? "0"+n : Integer.toString(n);
	}
}
