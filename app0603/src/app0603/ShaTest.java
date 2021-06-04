package app0603;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//암호화 알고리즘 중 SHA256 이용해보자 
//SHA는 단방향 알고리즘이기 때문에, 일단 한번 암호화된 데이터는 복원이 거의 불가능하다..
//해변의 모래성 무너졌을때 원본을 예측하기란 거의 불가능한것에 비유할 수 있다..
//평문 --> 16진수 해시값으로 암호화시켜보자!!
public class ShaTest {
	public static void main(String[] args) {
		String password="safdasdfsadfsdaddfsa"; 
		
		try {
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			byte[] hash=md.digest(password.getBytes("UTF-8")); //문자열을 바이트 단위로 쪼갠다..왜? 각 데이터를 16진수 해시값으로 암호화할꺼니깐
			
			//System.out.println(hash);
			//철자 하나씩 마다 암호화할것이므로 반복문으로 처리하자
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<hash.length;i++) {
				String hex=Integer.toHexString(0xff & hash[i]); //16진수 문자열로 변환
				if(hex.length()==1)sb.append("0");
				sb.append(hex);
				System.out.println(hex);
			}
			System.out.println(sb.toString());
			System.out.println(sb.toString().length());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.print("지정한 알고리즘이 존재하지 않습니다");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}