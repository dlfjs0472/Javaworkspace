package app0528.json.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//파일로 존재하는,json 데이터를 JsonBasic3 예제보다 더 간략화 시켜보자!!
public class JsonBasic4 {
	String path;
	FileReader reader;
	BufferedReader buffr;
	
	public JsonBasic4() {
		URL url = this.getClass().getClassLoader().getResource("pet.json");
		
		try {
			File file = new File(url.toURI());
			reader= new FileReader(file);
			JSONParser jsonParser = new JSONParser();
			JSONObject obj=(JSONObject)jsonParser.parse(reader);//이번엔 String 이 아닌 Reader자체를 인수로 넣는다
			
			JSONArray petArray=(JSONArray)obj.get("pet");
			for(int i =0; i<petArray.size(); i++) {
				JSONObject pet=(JSONObject)petArray.get(i);
				String type=(String)pet.get("type");
				String gender=(String)pet.get("gender");
				String name=(String)pet.get("name");
				
				System.out.println(type+","+gender+","+name);
			}
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new JsonBasic4();
	}
}
