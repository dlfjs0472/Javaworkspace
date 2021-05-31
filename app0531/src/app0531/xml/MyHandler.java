package app0531.xml;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//파싱 대상이 되는 XML문서의 모든 노드를 만날때마다, 개발자가 이벤트를 통해 정보를
//전달해주는 핸들러 객체 정의, 개발자는 핸들러가 발생시키는 이벤트에 따라 원하는 파싱관련작업을
//진행할 수 있다..
public class MyHandler extends DefaultHandler{

	//파싱을 진행하는 실행부가 지금 어느 태그를 지나치는지를 알수있는 표시 용도의 논리값을 선언
	boolean isMembers;
	boolean isMember;
	boolean isName;
	boolean isAge;
	boolean isAddr;
	Vector<Member> memberList;
	Member member;
	
	
	//각 노드를 만날때마다 호출되는 메서드를 재정의해보자!!!
	public void startDocument() throws SAXException {
		System.out.println("문서의 시작입니다");
	}
	
	//시작태그 노드를 만날때 호출되는 메서드
	public void startElement(String uri, String Name, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		
		if(tag.equals("members")) {
			isMembers=true; //실행부가 members태그를 지나갈때
			memberList=new Vector<Member>();
		}else if(tag.equals("member")) { //한명의 회원의 시작~~ 따라서 VO를 준비하자!!
			isMember=true; //지나간다 표시
			member = new Member();
		}else if(tag.equals("name")) {
			isName=true; //지나간다 표시
		}else if(tag.equals("age")) {
			isAge=true; //지나간다 표시
		}else if(tag.equals("addr")) {
			isAddr=true; //지나간다 표시
		}
	}
	
	//시작태그와 닫는 태그 사이의 텍스트 노드!!
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.print(data);
		
		if(isName) { //현재 실행부가 name 시작 태그를 막 지나친 상태라면...
			member.setName(data);
		}else if(isAge) { //나이넣기
			member.setAge(Integer.parseInt(data));
		}else if(isAddr) { //주소넣기
			member.setAddr(data);
		}
	}
	
	public void endElement(String uri, String Name, String tag) throws SAXException {
		System.out.print("</"+tag+">");
		if(tag.equals("name")) {
			isName=false;
		}else if(tag.equals("age")) {
			isAge=false;
		}else if(tag.equals("addr")) {
			isAddr=false;
		}else if(tag.equals("member")) {
			isMember=false;
			memberList.add(member);
		}else if(tag.equals("members")) {
			isMembers=false;
		}
	}
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝입니다, 최종적인 분석된 회원수는 "+memberList.size());
		
		//모든회원출력해보기
		for(Member obj : memberList) {
			System.out.println("회원이름:"+ obj.getName()+", 나이:"+obj.getAge()+", 주소:"+obj.getAddr());
		}
		
		
		
	}
	
}
