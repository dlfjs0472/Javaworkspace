package app0528.json.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * JSON은 자바스크립트의 객체표기법으로,json 데이터를 자바스크립트로 다루는것은 쉽다..
 * 그러나 자바는 기본적으로 json형식을 이해하고 있을까?
 * 스트링으로 이용할 수 있다..
 * */
public class JsonBasic {
	public static void main(String[] args) {
		
		//아래의 데이터는 문자열일 뿐, JSON객체는 아니다!!
		//따라서 아래의 문자열을 JSON 객체화 시키려면, 즉 JSON으로 이해하려면 문자열을 대상으로 해석해야 한다!!
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"name\":\"scott\",");
		sb.append("\"age\":36");
		sb.append("}");
		sb.append("");

		JSONParser jsonParser=new JSONParser();
		
		try {
			JSONObject obj=(JSONObject)jsonParser.parse(sb.toString()); //파싱!!  and 자식자료형으로 다운 캐스팅
			//파싱이 완료된 이후부터는 단순 문자열이 아닌,Json객체가 된다!!
			System.out.println(obj.get("name"));
			System.out.println(obj.get("age"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}




