package app0604.tree.xml;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PetsHandler extends DefaultHandler{
	ArrayList<Pet> petList;
	Pet pet; //<pet> 시작 태그를 만날때마다 생성될 VO 
	
	boolean isPets;
	boolean isPet;
	boolean isType;
	boolean isName;
	boolean isAge;
	
	DefaultMutableTreeNode root; //최상위 노드
	DefaultMutableTreeNode petNode; //최상위 노드
	
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("pets")) {
			isPets=true;
			petList= new ArrayList<Pet>();
			root = new DefaultMutableTreeNode(tag);
			
		}else if(tag.equals("pet")) {
			isPet=true;
			pet = new Pet();
			petNode = new DefaultMutableTreeNode(tag);
			
		}else if(tag.equals("type")) {
			isType=true;
		}else if(tag.equals("name")) {
			isName=true;
		}else if(tag.equals("age")) {
			isAge=true;
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch , start, length); //char 배열을 String 객체로 생성
		
		 if(isType) {
			 pet.setType(data);
			 DefaultMutableTreeNode type = new DefaultMutableTreeNode(data);
			 petNode.add(type);
		 }else if(isName) {
			 pet.setName(data);
			 DefaultMutableTreeNode name = new DefaultMutableTreeNode(data);
			 petNode.add(name);
		 }else if(isAge) {
			 pet.setAge( Integer.parseInt(data));
			 DefaultMutableTreeNode age = new DefaultMutableTreeNode(data);
			 petNode.add(age);
		 }
	}
	
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if(tag.equals("type")) {
			isType=false;
		}else if(tag.equals("name")) {
			isName=false;
		}else if(tag.equals("age")) {
			isAge=false;
		}else if(tag.equals("pet")) {
			isPet=false;
			petList.add(pet);//완성된 하나의 반려동물 VO의 인스턴스를 ArrayList에 추가 
			root.add(petNode);
		}else if(tag.equals("pets")) {
			isPets=true;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		
		for(Pet obj : petList) {
			System.out.println(obj.getType()+", "+obj.getName()+","+obj.getAge());
		}
		
	}
}