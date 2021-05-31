package app0531.xml.jtable;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLTable extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	public XMLTable() {
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		
		
		add(scroll);
		
		
		setBounds(600, 300, 600, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loadXML();
	}
	
	public void loadXML() {
		SAXParserFactory factory = SAXParserFactory.newInstance(); //팩토리의 인스턴스 얻기 (new 못함)
		
		try {
			URL url = this.getClass().getClassLoader().getResource("Pets.xml");
			URI uri=url.toURI();
			SAXParser saxParser=factory.newSAXParser();// 파서 생성
			saxParser.parse(new File(uri), new PetHandler());
			//JTable의 모델 데이터와 파싱한 결과와의 매칭은 파싱 전? 파싱한 수?
			PetModel model=new PetModel();
			model.data=
			table.setModel(model);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		 
		
	}
	public static void main(String[] args) {
		new XMLTable();
	}

}
