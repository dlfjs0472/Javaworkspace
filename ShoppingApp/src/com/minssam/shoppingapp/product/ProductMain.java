package com.minssam.shoppingapp.product;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.minssam.shoppingapp.main.AppMain;
import com.minssam.shoppingapp.main.Page;
import com.minssam.shoppingapp.model.domain.Subcategory;
import com.minssam.shoppingapp.model.domain.Topcategory;

//상품관리 메인 페이지
public class ProductMain extends Page {

	// 서쪽관련
	JPanel p_west;
	Choice ch_top;
	Choice ch_sub;
	JTextField t_product_name;
	JTextField t_price;
	JTextField t_brand;
	JTextArea t_detail;
	JScrollPane scroll;
	JButton bt_web; // 웹에서 가져오기
	JButton bt_file; // 로컬 파일에서 가져오기
	Canvas can;
	JButton bt_regist;
	
	//Choice 컴포넌트는 html의 option  과 달리 텍스트,value 값을 동시에 담을 수 없다..
	//따라서 우리가 이 부분을 복합 데이터 형태로 직접 만들어서 해결해보자!!
	ArrayList<Topcategory> topList = new ArrayList<Topcategory>(); //size 0 즉 아무것도 채워진게 없다
	ArrayList<Subcategory> subList=new ArrayList<Subcategory>(); //size 0 즉 아무것도 채워진게 없다
	

	// 동쪽관련
	JPanel p_east;
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_product_name2;
	JTextField t_price2;
	JTextField t_brand2;
	JTextArea t_detail2;
	JScrollPane scroll2;
	JButton bt_web2; // 웹에서 가져오기
	JButton bt_file2; // 로컬 파일에서 가져오기
	Canvas can2;
	JButton bt_regist2;

	// 센터관련
	JPanel p_center;
	JPanel p_search;// 검색 컴포턴트들 올려놓을 패널
	Choice ch_category;// 검색 카테코리
	JTextField t_keyword; // 검색어
	JButton bt_search;
	JTable table;
	JScrollPane scroll_table;
	
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image image; //등록시 이미지 미리보기에 사용할 이미지

	public ProductMain(AppMain appMain) {
		super(appMain);
		setBackground(Color.yellow);

		// 서쪽 영역 생성
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_product_name = new JTextField();
		t_price = new JTextField();
		t_brand = new JTextField();
		t_detail = new JTextArea();
		scroll = new JScrollPane(t_detail);
		bt_web = new JButton("웹에서 찾기");
		bt_file = new JButton("파일찾기");
		can = new Canvas() { //내부익명클래스
			//내부익명 클래스는 외부 클래스의 멤버(변수,메서드)들을 내것처럼 접근 가능!!
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, 180, 180, can);
			}
		};
		bt_regist = new JButton("상품등록");

		// 센터영역
		p_center = new JPanel();
		p_search = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("search");
		table = new JTable(10, 7);
		scroll_table = new JScrollPane(table);

		// 동쪽 영역 생성
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_product_name2 = new JTextField();
		t_price2 = new JTextField();
		t_brand2 = new JTextField();
		t_detail2 = new JTextArea();
		scroll2 = new JScrollPane(t_detail);
		bt_web2 = new JButton("웹에서 찾기");
		bt_file2 = new JButton("파일찾기");
		can2 = new Canvas();
		bt_regist2 = new JButton("상품등록");

		// 센터관련

		// 스타일,레이아웃

		// 서쪽 관련
		setLayout(new BorderLayout());

		Dimension d = new Dimension(180, 30);// 공통크기

		p_west.setPreferredSize(new Dimension(200, 700));
		scroll.setPreferredSize(new Dimension(180, 180));
		ch_top.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		can.setPreferredSize(new Dimension(180, 180));
		can.setBackground(Color.CYAN);

		// 센터 관련
		ch_category.setPreferredSize(d);
		t_keyword.setPreferredSize(new Dimension(380, 30));

		// 동쪽 관련
		p_east.setPreferredSize(new Dimension(200, 700));
		scroll2.setPreferredSize(new Dimension(180, 200));
		ch_top2.setPreferredSize(d);
		ch_sub2.setPreferredSize(d);
		ch_sub2.setPreferredSize(d);
		t_product_name2.setPreferredSize(d);
		t_price2.setPreferredSize(d);
		t_brand2.setPreferredSize(d);

		// 서쪽조립
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_product_name);
		p_west.add(t_price);
		p_west.add(t_brand);
		p_west.add(scroll);
		p_west.add(bt_web);
		p_west.add(bt_file);
		p_west.add(can);
		p_west.add(bt_regist);

		// 센터관련
		p_search.add(ch_category);
		p_search.add(t_keyword);
		p_search.add(bt_search);
		p_center.setLayout(new BorderLayout());
		p_center.add(p_search, BorderLayout.NORTH); // 검색 패널을 북쪽에 부착!!
		p_center.add(scroll_table);

		// 동쪽조립
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_product_name2);
		p_east.add(t_price2);
		p_east.add(t_brand2);
		p_east.add(scroll2);
		p_east.add(bt_web2);
		p_east.add(bt_file2);
		p_east.add(can2);
		p_east.add(bt_regist2);

		add(p_west, BorderLayout.WEST); // 서쪽영역에 부착
		add(p_east, BorderLayout.EAST); // 동쪽 영역에 부착
		add(p_center);

		// 리스너 연결
		ch_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) { //html의 onchange
				//지금 선택한 상위카테고리의 pk값을 알아맞추려면??
				Choice ch=(Choice)e.getSource();
				
				System.out.println("당신이 선택한 아이템은 " + ch.getSelectedIndex());
				
				//유저가 현재 선택한 Choice에서의 아이템을 이용하여 ArrayList의 객체를 꺼내자!!
				int index = ch.getSelectedIndex()-1;
				Topcategory topcategory=topList.get(index); //topList에서 VO 한개 꺼내기!!
				System.out.println("선택하신 아이템의 정보 topcategory_id="+topcategory.getTopcategory_id());
				System.out.println("선택하신 아이템의 정보 top_name="+topcategory.getTop_name());
				getSubList(topcategory.getTopcategory_id()); 
				
			}
		});
		
		//파일찾기 버튼과 리스너 연결
		bt_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = kit.getImage("D:\\korea202102_javaworkspace\\image\\maki.jpg");
				can.repaint();
				
			}
		});

		getTopList();

	}

	// 왼쪽 영역의 TopCategory 가져오기
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from topcategory";

		try {
			pstmt = this.getAppMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery(); // select 실행 후 레코드 반환
			
			ch_top.add("==Choose Category==");
			while (rs.next()) { // 커서 한칸씩 이동하면서 true인동안..
				ch_top.add(rs.getString("top_name"));
				
				//Empty 상태의 인스턴스 한개 생성, 이 안에 카테고리 이름과 pk를 넣어두자!!
				Topcategory topcategory = new Topcategory(); //하나의 레코드를 담을 수 있는 텅 비어있는 VO(숫자와 제목을 담을 수 있다)
				topcategory.setTopcategory_id(rs.getInt("topcategory_id")); //pk
				topcategory.setTop_name(rs.getString("top_name")); //카테고리 이름
				topList.add(topcategory); // ArrayList에 아이템 추가!!!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAppMain().release(pstmt, rs);
		}
		
//		//확인을 위한 로그
//		for(int i = 0; i<topList.size(); i++) {
//			String item = (String)topList.get(i);
//			System.out.println(item);
//		}

	}
	// 왼쪽 영역의 SubCategory 가져오기
	public void getSubList(int topcategory_id) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		String sql = "select * from subcategory where topcategory_id="+topcategory_id;
		
		try {
			pstmt=this.getAppMain().getCon().prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			ch_sub.removeAll();//기존아이템 싹!! 제거
			subList.removeAll(subList); // ArrayList 의 요소 모두 삭제
			
			ch_sub.add("choose category");
			while(rs.next()) {
				Subcategory subcategory = new Subcategory(); // empty
				//하나씩 넣자!!
				subcategory.setSubcategory_id(rs.getInt("subcategory_id")); //pk
				subcategory.setTopcategory_id(rs.getInt("topcategory_id")); // fk
				subcategory.setSub_name(rs.getString("sub_name")); //카테고리 명
				
				subList.add(subcategory);//완성된 VO를 ArrayList에 추가하자!!
				
				ch_sub.add(rs.getString("sub_name"));
			}
			System.out.println("현재 누적된 서브카테로기 목록은"+subList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.getAppMain().release(pstmt, rs);
		}

	}

}


