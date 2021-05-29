package app0528.json.marvel;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//marvl hero 데이터를 읽어서,  JTable로 출력해보기!!
public class MarvelApp extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	JPanel p_east;
	Canvas can;
	JTextField t_title;
	JTextField t_date;
	JTextField t_gross;
	JButton bt;
	FileReader reader;
	
	//이차원배열보다 유연하게 데이터를 처리할 수 있는 Vector를 이용한 JTable을 구현해본다!
	Vector<JSONObject> data=new Vector<JSONObject>(); //테이블에 채워질 데이터
	Vector<String> column=new Vector<String>(); //테이블의 컬럼정보
	
	//툴킷으로 가능한지? 이미지 아이콘으로 가능한지?
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image img;
	
	public MarvelApp() {
		//컬럼
		column.add("movie_id"); //col=0
		column.add("url"); //col=1
		column.add("title"); //2
		column.add("category_name");//3
		column.add("release_date");//4
		column.add("running_time");//5
		column.add("budget");//6
		column.add("gross");//7
		
		table = new JTable(new AbstractTableModel() {
			
			public int getRowCount() {
				return data.size(); //2
			}
			public int getColumnCount() {
				return column.size(); //3
			}
			public String getColumnName(int col) {
				return column.get(col);   //배열의 경우엔  배열[col]
			}
			public Object getValueAt(int row, int col) {
				// 이차원배열이었다면 각 셀에 들어갈 데이터를 이렇게 records[row][col] 뽑으면 된다!!
				JSONObject json=data.get(row);
				String value=null;
				
				if(col==0) {
					value=Long.toString((Long)json.get("movie_id"));
				}else if(col==1) {
					value=(String)json.get("url");
				}else if(col==2) {
					value=(String)json.get("title");
				}else if(col==3) {
					value=(String)json.get("category_name");
				}else if(col==4) {
					value=(String)json.get("release_date");
				}else if(col==5) {
					value=Long.toString((Long)json.get("running_time"));
				}else if(col==6) {
					value=(String)json.get("budget");
				}else if(col==7) {
					value=(String)json.get("gross");
				}
				return value;
			}
		});
		
		scroll = new JScrollPane(table);
		p_east = new JPanel();
		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 240, 270, can);
			}
		};
		
		t_title = new JTextField(13);
		t_date = new JTextField(13);
		t_gross = new JTextField(13);
		bt = new JButton("Load");
		
		//스타일 
		p_east.setPreferredSize(new Dimension(250,600));
		can.setPreferredSize(new Dimension(240,270));
		can.setBackground(Color.RED);
		bt.setPreferredSize(new Dimension(200, 25));
		
		//조립 
		add(scroll);
		p_east.add(can);
		p_east.add(t_title);
		p_east.add(t_date);
		p_east.add(t_gross);
		p_east.add(bt);
		
		add(p_east, BorderLayout.EAST);
		
		//이벤트 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//클릭한 곳의 데이터 추출하기!!
				String url = (String)table.getValueAt( table.getSelectedRow() , 1);
				
				String title=(String)table.getValueAt( table.getSelectedRow() , 2);
				String release_date=(String)table.getValueAt( table.getSelectedRow() , 4);
				String gross=(String)table.getValueAt( table.getSelectedRow() , 7);
				
				t_title.setText(title);
				t_date.setText(release_date);
				t_gross.setText(gross);
				
				drawImage(url);
			}
		});
		
		setBounds(600, 0, 850, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//선택한 영화의  url을 이용하여,  canvas에 이미지 그려넣기!!
	public void drawImage(String path) {
		try {
			URL url = new URL(path);
			img = kit.getImage(url);
			can.repaint();//변경된 이미지를 켄버스가 다시 그리도록 요청 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			URL url = this.getClass().getClassLoader().getResource("kk/marvel.json");
			URI uri=url.toURI();
			File file = new File(uri);
			reader = new FileReader(file);
			System.out.println("스트림 생성 성공");
			
			//생성된 스트림을 이용하여 JSoN을 parsing  해보자!!1
			JSONParser jsonParser = new JSONParser();
			JSONObject obj=(JSONObject)jsonParser.parse(reader);//파싱 시작!!!
			
			JSONArray movieArray=(JSONArray)obj.get("marvel"); //영화 배열 받기
			System.out.println("마블의 총 영화수는 "+movieArray.size());
			
			for(int i=0;i<movieArray.size();i++) {
				JSONObject movie=(JSONObject)movieArray.get(i); //영화 1편 반환!!
				//data 백터에 영화1편 넣기!! 
				data.add(movie);
			}
			table.updateUI();
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
		new MarvelApp();
	}
	
}
