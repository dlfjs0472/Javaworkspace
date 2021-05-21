package app0520.db.table;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//DBMS등의 표형식(2차원 구조)의 데이터를 표현하는 최적화된 JTable을 이용해본다
public class TableBasic extends JFrame {
	// JTable의 생성자 중, 레코드는 이차원배열로 지원하고, 컬럼의 제목은 일차원배열로 지원하는 3번째 생성자를 이용해보자!!
	String[] columns = { "member_id", "user_id", "password", "name", "regdate " };
	String[][] rows = {}; // 아직 DB연동 전 이므로, 배열의 크기를 알수없기 때문에 임시적으로 비어있는 배열을 선언한것뿐이다
	JTable table;
	JScrollPane scroll;

	// mysql 접속 문자열에 사용될 접속정보
	String url = "jdbc:mysql://localhost:3306/javase?characterEncoding=UTF-8";
	String user = "root";
	String password = "1234";
	Connection con; // 접속 후 그 정보를 가진 객체

	public TableBasic() {

		connect(); // JTable이 사용할 이차원배열을 먼저 구해야 하므로..

		table = new JTable(rows, columns);
		scroll = new JScrollPane(table);

		add(scroll);

		setBounds(600, 100, 500, 500);
		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);//자원을 해제시킬 작업이 없기때문에 이용가능...
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (con != null) {
					try {
						con.close();
						setDefaultCloseOperation(EXIT_ON_CLOSE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

	// 데이터베이스 가져오기
	public void connect() {
		/*
		 * 1)드라이버 로드 2)접속 3) 쿼리수행 4) 자원해제
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			JOptionPane.showMessageDialog(this, "Mysql 로드 성공");

			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				JOptionPane.showMessageDialog(this, "접속성공");
				select();
			} else {
				JOptionPane.showMessageDialog(this, "접속 실패");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// select문을 수행하는 메서드 정의
	public void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 결과를 받는 객체
		String sql = "select * from member";

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // 쿼리 수행
																												// 객체 생성
			// 위의 옵션을 줄 경우 스크롤 가능하면서 읽기전용의 ResultSet 을 생성할 수 있다..
			rs = pstmt.executeQuery(); // 쿼리 실행 및 레코드 반환!!

			// rs의 데이터를 이차원배열로 변환하여, JTable에게 적용시켜보자
			// rs에는 총 레코드수를 반환해주는 메서드가 별도로 지원되지 않는다..따라서 개발자가 다음의 절차를 거쳐
			// 총 레코드수를 구할 수 있다
			// 1)커서를 레코드의 제일 마지막으로 이동 !! last()
			// 2)마지막으로 이동된 커서의 row 넘버를 구한다. 여기서 넘버란 몇번째 레코드인지에 대한 순번이다..
			// 만일 rowNum이 3이라면 3번째 데이터라는 의미다!
			rs.last();
			int num = rs.getRow(); // 현재의 레코드 순번을 반환받아보자
			System.out.println("위치는" + num);

			String[][] data = new String[num][columns.length];

			rs.beforeFirst();// last에 가있는 rs의 커서를 다시 처음으로 원상복귀 시키자
			int index=0;
			while (rs.next()) {// 커서 한칸 전진
				// rs에 레코드에 접근하여, empty상태에 있는 2차원 배열에 데이터를 옮겨보자!!
				data[index][0] = Integer.toString(rs.getInt("member_id")); // int형을 String 객체형으로 변환!! 이때 사용되는 객체가 바로 Wrapper 클래스
				data[index][1] = rs.getString("user_id");
				data[index][2] = rs.getString("password");
				data[index][3] = rs.getString("name");
				data[index][4] = rs.getString("regdate");
				index++;
			}

			rows = data; // Jtable이 참조할 예정인 rows의 주소값을 data이차원 배열로 대체해버리자!!

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new TableBasic();

	}
}
