package app0531.xml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//JSON뿐 아니라, 이 기종간 데이터교환에 흔히 사용되는 데이터형식에는 XML도 있다
//XML은 태그이므로, 자바프로그램에서 태그를 다룰 줄 알아야 한다...
//결론) XML파싱을 다룰 줄 알아야 함 
//XML 파싱의 유형 -  
//1.DOM방식 : 사용하는 법은 기존의 HTML DOM과 비슷하기에 쉬울수 있으나, 메모리 리소스 소요가 너무 많다    
//2.SAX방식 : DoM처럼 모든 요소마다 DoM객체를 생성하는 방식이 아니라, 모든 노드를 만날때마다, 개발자에게
//이벤트를 전달해서, 개발자가 원하는 작업을 수행할 수있도록 돕는 파싱 방식(DOM보다 메모리 리소스 낭비가 덜함)
//스마트폰 개발 - SAX방식이 많이 사용됨!! 
public class XMLBasic1 {
	SAXParserFactory factory;
	SAXParser saxParser;
	MyHandler handler;
	
	
	public XMLBasic1() {
		//파서를 생성하기에 앞서 팩토리를 먼저 생성
		try {
			factory = SAXParserFactory.newInstance(); 
			saxParser = factory.newSAXParser(); //파서의 인스턴스 얻기!!
			URL url=this.getClass().getClassLoader().getResource("Member.xml");
			URI uri=url.toURI();
			saxParser.parse(new File(uri), handler= new MyHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
	}
	
	
	public static void main(String[] args) {
		new XMLBasic1();
	}

}
