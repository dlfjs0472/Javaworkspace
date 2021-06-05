package app0604.tree.xml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//XML을 파싱하여 그 결과를 JTree에 출력하기
public class TreeXMLApp extends JFrame{
	JTree tree;
	JScrollPane scroll;
	PetsHandler handler;
	
	public TreeXMLApp() {
		tree = new JTree(getNode());
		scroll = new JScrollPane(tree);
		
		add(scroll);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//XML파싱하여, 노드를 분석 및 구성한 후, 최종적으로 생성된 노드를 반환해보자!!
	public DefaultMutableTreeNode getNode() {
		try {
			//파일에 대한 접근
			URL url=this.getClass().getClassLoader().getResource("pets.xml");
			URI uri = url.toURI();
			File file = new File(uri);
			
			//파싱 
			SAXParserFactory factory=SAXParserFactory.newInstance();
			SAXParser saxParser=factory.newSAXParser();
			saxParser.parse(file, handler = new PetsHandler()); //파싱 시작!!!
			//위 47번 코드의 파싱을 다 마치고 나면, 파싱과정에서 분석된  xml 노드가 
			//Tree노드로 생성되어 있어야 한다.. 
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.root;
	}
	public static void main(String[] args) {
		new TreeXMLApp();
	}

}
