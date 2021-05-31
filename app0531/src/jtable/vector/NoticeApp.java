package jtable.vector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;




public class NoticeApp extends JFrame {
	

	JPanel p_west;
	JTextField t_title;
	JTextField t_writer;
	JTextField t_content;
	JButton bt_regist;
	
	//센터영역
	JPanel p_center;
	JTable table;
	JScrollPane scroll;
	JPanel p_south;
	JButton bt_del;
	JButton bt_list; //게시판목록 버튼
	JButton bt_member; //회원목록 버튼
	
	//DB관련
	String url= "jdbc:mysql://localhost:3306/javase?characterEncoding=UTF-8";
	String user="root";
	String password="1234";
	Connection con;
	NoticeModel model;
	
	public NoticeApp() {
		
		//생성
		p_west = new JPanel();
		t_title = new JTextField(16);
		t_writer = new JTextField(16);
		t_content = new JTextField(16);
		bt_regist = new JButton("등록");
		bt_list = new JButton("게시판목록");
		bt_member = new JButton("회원목록");
		
		p_center = new JPanel();
		table = new JTable(); 
		
		scroll = new JScrollPane(table);
		p_south = new JPanel();
		bt_del = new JButton("삭제");
		
		
		//스타일
		p_west.setPreferredSize(new Dimension(200, 450));
		p_west.setBackground(Color.CYAN);
		p_center.setLayout(new BorderLayout());
		

		
		
		
		//조립
		p_west.add(t_title);
		p_west.add(t_writer);
		p_west.add(t_content);
		p_west.add(bt_regist);
		add(p_west, BorderLayout.WEST);
		
		p_center.add(scroll);
		p_south.add(bt_del);
		p_south.add(bt_list);
		p_south.add(bt_member);
		p_center.add(p_south, BorderLayout.SOUTH);
		add(p_center);
		
		
		//이벤트
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);//프로세스 종료
				release(con);
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		bt_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getList();//목록 가져오기
				table.updateUI();
			}
		});
		
		bt_member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMemberList();//목록 가져오기
				table.updateUI();
			}
		});
		
		
		//보이기
		setBounds(600,300,800,450);
		setVisible(true);
		
		connect(); //디비 가져오기

	}
	
	//mysql 접속
	public void connect() {
		/*
		 1)드라이버 로드
		 2) 접속
		 3) 쿼리수행
		 4) 접속끊기
		*/
		try {
			Class.forName("com.mysql.jdbc.Driver"); //1)드라이버 로드
			con=DriverManager.getConnection(url, user,password); //2) 접속
			if(con!=null) {
				this.setTitle("접속성공");
			}else {
				JOptionPane.showMessageDialog(this, "DB접속할수 없습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//CRUD
	//Create(=insert) Read(=select) Update Delete
	
	//등록
	public void regist() {
		String sql="insert into notice(title,writer,content) values(?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, t_title.getText());
			pstmt.setString(2, t_writer.getText());
			pstmt.setString(3, t_content.getText());
			int result=pstmt.executeUpdate();
			if(result==1) {
				JOptionPane.showMessageDialog(this, "등록 성공");				
			}else {
				JOptionPane.showMessageDialog(this, "등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(pstmt);
		}
	}
	//목록
	public void getList() {
		String sql="select * from notice order by notice_id desc";
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		ResultSetMetaData meta; //컬럼 정보 등을 가져오기 위한 객체
		model = new NoticeModel();
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); //쿼리 수행후 결과집합 가져오기
			meta = rs.getMetaData(); //rs가 존재해야 메타정보를 얻을 수 있다!!
			
			//컬럼의 수, 컬럼의 이름 구해서 모델에 적용해보기!!
			int col_count = meta.getColumnCount(); //컬럼수
			for(int i=1;i<=col_count;i++) {
				String name=meta.getColumnName(i);
				model.column.add(name); //모델객체가 보유한 벡터에 컬럼명 추가!!
			}
			
			
			while(rs.next()) { //한칸 전진
				Notice notice = new Notice();//게시물 한건을 담게될 VO 생성 현재는 empty 상태
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				notice.setRegdate(rs.getString("regdate"));
				
				model.data.add(notice);//한건의 레코드를 담은 VO를 Vector에 추가하자!!
			}
			//Model에 들어있는 메서드들은, Table에 해당 모델 적용시점에 호출되는  것을 알 수 있다..
			//이때 JTable 원하는 정보를 모델로 부터 얻어간다!!
			table.setModel(model); //JTable의 생성자에서 모델을 결정하는게 아니라, 생성된 모델중 원하는 모델을
											//테이블에 적용시키고 싶을때
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(pstmt, rs);
		}
		
	}
	
	//수정
	
	//삭제
	
	//회원목록 가져오기
	public void getMemberList() {
		String sql="select * from member";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ResultSetMetaData meta=null;
		MemberModel memberModel=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			meta = rs.getMetaData();
			memberModel = new MemberModel();
			
			//컬럼 정보 채우기 
			for(int i=1;i<=meta.getColumnCount();i++) {
				String name=meta.getColumnName(i);
				memberModel.column.add(name);//컬럼구성
			}
			
			//레코드 채우기 
			while(rs.next()) {
				Member member = new Member();//empty상태의 VO생성 
				member.setMember_id(rs.getInt("member_id"));
				member.setUser_id(rs.getString("user_id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegdate(rs.getString("regdate"));
				
				memberModel.data.add(member); 
			}
			table.setModel(memberModel); // JTable에 모델 적용, 이 순간부터 JTable은 Model의 
														//메서드를 호출하여, 데이터를 채워나간다!!
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(pstmt, rs);
		}
	}
	
	
	
	
	
	
	public void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new NoticeApp();
	}
	
	
}









