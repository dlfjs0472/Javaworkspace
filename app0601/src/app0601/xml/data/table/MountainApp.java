package app0601.xml.data.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MountainApp extends JFrame{
	JPanel p_north;
	JTextField t_input;
	JButton bt_search;
	JTable table;
	JScrollPane scroll;
	XMLLoader loader;
	MountainHandler handler;
	MountainModel model;
	
	public MountainApp() {
		//생성
		p_north = new JPanel();
		t_input = new JTextField(25);
		bt_search = new JButton("검색");
		table = new JTable();
		scroll = new JScrollPane(table);
		loader = new XMLLoader();

		
		//스타일 
		
		//조립 
		p_north.add(t_input);
		p_north.add(bt_search);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//이벤트
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData();
			}
		});
		
		setSize(900,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void searchData(){
		try {
			//1.URL을 통해 xml String 을 로드
			InputStream is = loader.loadFromStream(t_input.getText());

			
			//2.로드된 xml String을 해석(파싱)하여 JTable출력
			parseData(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//String화된 xml을 넘겨받아 파싱을 수행하는 메서드 정의
	public void parseData(InputStream is) {
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
			SAXParser saxParser=factory.newSAXParser();
			
			saxParser.parse(is, handler = new MountainHandler());//파싱시작!!!!
			
			//이 코드 시점이, 파싱이 종료된 시점이므로 JTable이 TableModel을 사용할 시점이다
			model = new MountainModel();
			model.data=handler.mtList; 
			table.setModel(model); //이 시점부터 JTable은 적용된 모델로 부터 각종
											//정보를 얻어 표를 구성한다!!
			table.updateUI();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MountainApp();
	}
}
