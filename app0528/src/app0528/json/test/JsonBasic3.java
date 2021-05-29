package app0528.json.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



//다루고자 하는 데이터를 스트링으로 코드내에 두려고 하지말고, 외부의 파일로 존재시켜서, 그파일을 프로그램으로 읽을 수만 있다면
//스트링을 일일이 변수에 담지 않고도 데이터를 처리할 수 있다..
public class JsonBasic3 {
	FileReader reader; //파일을 대상으로 한 문자기반 스트림
	BufferedReader buffr;
	String path;
	
	public JsonBasic3() {
		
		try {
			URL url=this.getClass().getClassLoader().getResource("pet.json");
			//System.out.println(url);
			URI uri = url.toURI();
			buffr = new BufferedReader(reader = new FileReader(new File(uri)));
			
			//스트림으로 연결된 파일의 내용을 실행중인 자바 프로그램으로 읽어봐 보자!!
			String data=null;
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				data=buffr.readLine(); //한줄읽기
				if(data==null)break;
				//System.out.println(data);
				sb.append(data); //문자열 모으기!! 왜 모으나? 파싱하려고!!
			}
			//문자열이 다 모아졌기 때문에, 최종적으로 모아진 데이터를 확인해 보자!!
			//System.out.println(sb.toString());
			
			//모아진 데이터를대상으로 파싱을 진행!!
			JSONParser jsonParser = new JSONParser();
			JSONObject obj=(JSONObject)jsonParser.parse(sb.toString());
			
			JSONArray petArray=(JSONArray)obj.get("pet");
			for(int i=0; i<petArray.size(); i++) {
				JSONObject pet=(JSONObject)petArray.get(i);
				String type=(String)pet.get("type");
				String gender=(String)pet.get("gender");
				String name=(String)pet.get("name");
				System.out.println(type+","+gender+","+name);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new JsonBasic3();
	}
}
