package app0513.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
 자바의 컬렉션 프레임웍 중 Map에 대해 학습한다
 Map이란 Key-Value의 쌍으로 이루어진 데이터 집합이다.. 대표적인 예 JSON 등임...
 */
public class MapApp {
	public static void main(String[] args) {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("fruit1","apple");//데이터 넣기!!
		map.put("fruit2","starfruit");
		map.put("fruit2","starfruit"); //벨류의 중복은 무시
		map.put("fruit3","mango");
		map.put("fruit3","apple"); // 키값의 중복은 덮어쓴다
		map.put("fruit4","grape");
		
		//System.out.println(map.get("fruit3"));
		
		//결론 : 직접Iterator 나 Enumeration으로 변환 할 수는 없고, Set을 한 번 거쳐서 처리한다!!!
		
		//주의 : 아래의 keyset 메서드는 map에 들어있는 value 값이 아닌, 그 value 와 쌍을 이루는 key값만을 추출한다!!
		Set<String> set = map.keySet(); //fruit1,fruit2,fruit3.. 키값만이 순서없이 모여있게 됨
		
		Iterator<String> it = set.iterator(); // 키값을 일렬로 늘어서게함
		
		//반복문으로 모든 요소를 출력
		while(it.hasNext()) {//현재 위치를 기준으로, 다음 위치에 요소가 존대하는 동안만 반복문 수행..
			String key =it.next(); //키를 얻었기 때문에 이 시점에 Map에 들어있는 데이터를 검색할 수있다
			String value=map.get(key);	//키에 연결된 value 반환
			System.out.println(value);
		}
		
		
	}
}
