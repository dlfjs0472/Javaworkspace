package app0528.json.url;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class URLJSonData extends JFrame{
	JPanel p_north;
	JButton bt;
	JTable table;
	JScrollPane scroll;
	Vector<JSONObject> data=new Vector<JSONObject>();
	Vector<String> column=new Vector<String>();
	
	public URLJSonData() {
		column.add("Name");
		column.add("Code");
		
		p_north = new JPanel();
		bt = new JButton("Load From URL");
		table = new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return data.size();
			}
			public int getColumnCount() {
				return column.size();
			}
			public String getColumnName(int col) {
				return column.get(col);
			}
			public Object getValueAt(int row, int col) {
				return null;
			}			
		});
		scroll = new JScrollPane(table);
		
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//이벤트
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		setVisible(true);
		setBounds(2400, 100, 600, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void load() {
		InputStreamReader reader=null; //finally에서 close하기 위해...
		try {
			URL url =new URL("https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/countries.json");
			HttpURLConnection con=(HttpURLConnection)url.openConnection(); //웹상의 자원 요청 !!!
			//con 으로부터 웹서버의 자원에 대한 String or Reader를 뽑아내자!!
			con.setRequestMethod("GET");
			reader = new InputStreamReader(con.getInputStream());
			
			JSONParser jsonParser = new JSONParser();
			JSONArray nationArray=(JSONArray)jsonParser.parse(reader); //파싱 시작
			System.out.println("나라 수는 "+nationArray.size());
			
		} catch (MalformedURLException e) {
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
		new URLJSonData();
	}
}