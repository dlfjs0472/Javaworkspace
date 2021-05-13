package app0513.collection;

import java.util.HashSet;
import java.util.Iterator;

/*
 * 자바의 컬렉션 프레임웍 중 순서 없는 데이터를 모아 서 처리하는 Set에 대해 학습한다
 */
public class SetAPP {
	public static void main(String[] args) {
		HashSet<String> set= new HashSet<String>(); //제너릭으로 String으로 제한
		set.add("banana"); //List와 동일하게 add() 메서드가 지원되는 이유는? 모두~Collection객체의 자식이기에...
		set.add("starfruit"); 
		set.add("apple"); 
		set.add("apple"); 
		set.add("grape"); 
		set.add("apple");  
		//결론 : Set은 데이터 중복을 허용하지 않음
		
		//순서가 없는 데이터형이므로, 일렬로 늘어서게 해보자 = Iterator
		Iterator<String> it=set.iterator();
		
		
		//반복문을 이용해서 모든 요소 출력하기!!
		while(it.hasNext()) {//현재 위치를 기준으로, 다음 위치에 요소가 존대하는 동안만 반복문 수행..
			String item =it.next(); //이동한 위치의 요소를 반환함
			System.out.println(item);
		}
	
		
	}
}
