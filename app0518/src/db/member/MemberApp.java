package db.member;

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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemberApp extends JFrame {
	JPanel p_north, p_west, p_center;

	// 북쪽 관련
	JTextField t_url, t_port;
	JButton bt_connect;

	// 서쪽관련
	JTextField t_user_id;
	JPasswordField t_password;
	JTextField t_name;
	JButton bt_regist, bt_list;

	// 센터 관련
	JTextArea area;
	JScrollPane scroll;

	Connection con; // 모든 멤버메서드에서 접근하기 위하여

	public MemberApp() {
		// 생성
		p_north = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		t_url = new JTextField("localhost");
		t_port = new JTextField("3306");
		bt_connect = new JButton("접속");

		t_user_id = new JTextField(15);
		t_password = new JPasswordField(15);
		t_name = new JTextField(15);
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");

		area = new JTextArea();
		scroll = new JScrollPane(area);

		// 스타일,레이아웃
		t_url.setPreferredSize(new Dimension(470, 25));
		t_port.setPreferredSize(new Dimension(120, 25));
		p_west.setPreferredSize(new Dimension(200, 550));
		p_west.setBackground(Color.LIGHT_GRAY);

		// 조립
		p_north.add(t_url);
		p_north.add(t_port);
		p_north.add(bt_connect);
		add(p_north, BorderLayout.NORTH);

		p_west.add(t_user_id);
		p_west.add(t_password);
		p_west.add(t_name);
		p_west.add(bt_regist);
		p_west.add(bt_list);
		add(p_west, BorderLayout.WEST);

		add(scroll);

		// 리스터연결
		bt_connect.addActionListener(new ActionListener() { // 내부익명클래스
			public void actionPerformed(ActionEvent e) {
				// 입력한 url 및 포트번호를 이용하여 mysql 접속할 예정
				connect();
			}
		});

		// 윈도우와 리스너 연결
		// 어뎁터의 존재 + 익명클래스를 이용하면, 많은 메서드를 거느린 인터페이스 구현 코드를
		// 현저히 줄일 수 있다...
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("윈도우 닫아요!!");
				disConnect();
				System.exit(0);// 프로세스 죽이기!!!
			}
		});

		// 등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
				area.setText("");//area 지우기!!!
				getList();
			}
		});
		bt_list.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getList();
			}
		});

		// 보이기
		setBounds(600, 100, 750, 600);
		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); // 이 메서드는 윈도우 창 닫을 때 무언가 반환할 것이 있을때는
		// 호출해서는 아니된다!!
		// why?? 그냥 꺼져버리니...
		// 이때는 WindowListener의 windowClosing()메서드에서 반납할 자원등을 처리해야 하기 때문에...

	}

	// DB접속 메서드
	public void connect() {
		// 드라이버 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");
			area.append("드라이버 로드 성공\n");

			String url = "jdbc:mysql://" + t_url.getText() + ":" + t_port.getText() + "/javase?characterEncoding=UTF-8";
			con = DriverManager.getConnection(url, "root", "1234");
			if (con != null) {
				area.append("접속성공\n");
			} else {
				area.append("접속실패\n");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			area.append("드라이버 로드 실패\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// mysql 서버에 insert 쿼리문 실행
	public void regist() {
		// 아래의 PreparedStatement는 쿼리문 마다 1회성으로 생성해야 한다..
		PreparedStatement pstmt = null; // 쿼리문 수행객체
		String sql = "insert into member(user_id,password,name) values(?,?,?)"; // 바인드변수

		try {
			pstmt = con.prepareStatement(sql);// SQL문을 이용하여, pstmt 객체 생성
			// 바인드변수 값 설정
			pstmt.setString(1, t_user_id.getText());
			// password는 char배열로 생성후 다시 string으로 변환하여 사용하자!!
			String pass = new String(t_password.getPassword());
			pstmt.setString(2, pass);
			pstmt.setString(3, t_name.getText());

			// 수행할 쿼리 유형이 DML(insert,update,delete)면 executeUpdate()
			int result = pstmt.executeUpdate(); // 바로 이시점에 쿼리 실행!!!!!
			if (result == 1) {// execute 반환값이 1이면 성공
				area.append("회원등록성공\n");
			} else { // 반환값 0이면 실패(에러가 아니라 안들어간 것)
				area.append("회원등록실패ㅠㅠ\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 목록출력
	public void getList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member";

		try {
			pstmt = con.prepareStatement(sql); // 쿼리 수행 객체 생성
			rs = pstmt.executeQuery(); // 수행할 쿼리가 select문인 경우 ResultSet 반환됨 ←데이터 베이스를 표현한 객체(커서이용접근)

			// rs의 커서를 움직여 가면서, 모든 레코드에 접근하여 area에 출력해보자!!
			while (rs.next()) {// 커서 한칸 전진!!!

				// 현재 커서가 가리키는 레코드를 컬럼명으로 접근해보자
				int member_id = rs.getInt("member_id");
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String regdate = rs.getString("regdate");
				
				//area.append("------------------------------------------------------------------------------------------------------------------------ \n");
				area.append(member_id + "\t" + user_id + "\t" + password + "\t" + name + "\t" + regdate + "\n");
				area.append("------------------------------------------------------------------------------------------------------------------------ \n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// DB접속 해제 메서드
	public void disConnect() {
		if (con != null) { // 접속객체가 메모리에 올라왔을때만
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MemberApp();

	}

}
