package app0517.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



//Mysql DB에 레코드 넣어보기!!
//자바 언어로 데이터베이스를 연동하는 기술을 가리켜 JDBC라 한다
//java.sql 패키지에서 대부분의 api를 제공해준다..

public class DBInsert {
	public static void main(String[] args) {
		//모든 DB작업 수행은 절차가 동일하다
		
		//드라이버는 각 DB벤더사의 홈페이지 또는 저장소로 부터 다운받을 수 있는데,
		//이때 드라이버는 jar파일 형태로 되어있다..
		//jar(java Archive) : 자바의 클래스를 압축시켜놓은 확장자를 jar
		
		//0) 해당 DB에 알맞는 드라이버 클래스를 로드해야한다
		try { 
			//forName 메서드는 클래스를 static 영역으로 로드하는 메서드!!!
			Class.forName("com.mysql.jdbc.Driver");  //드라이버 클래스 로드
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e1) {
			System.out.println("해당 드라이버를 찾을 수 없습니다");
			e1.printStackTrace();
		} 
		
		//1) DB접속
		String url="jdbc:mysql://localhost:3306/javase?characterEncoding=UTF-8";
		// 포트번호 디폴트가 3306이므로 3306이면 명시 안해도 된다, 한글이 깨지면 ?characterEncoding=UTF-8
		String user="root";
		String pass ="1234";
		Connection con= null;
		PreparedStatement pstmt=null; //쿼리문 수행 객체
		
		try {
			//아래의 코드에서 처럼, DriverManager에 의해 접속이 성공된 경우에만,
			//접속 정보를 가진 Connection 객체가 반환된다.. 따라서 접속에 실패한 경우는?
			//Connection 객체가 반환되지 않으므로,, null로 초기화 된다.
			con =DriverManager.getConnection(url, user, pass);
			if(con == null) {
				System.out.println("접속실패");
			}else {
				System.out.println("접속성공");
				
				String sql="insert into member(user_id , password, name)";
				sql+=" values('batman','1234','배트맨')"; // values 전 한칸 띄우기!!
				
				//위의 작성항 쿼리문을 실행해보자!!
				pstmt=con.prepareStatement(sql); //수행할 쿼리문 인수로 넣는다
				int result = pstmt.executeUpdate();//DML(insert, update, delete)//쿼리 실행
				if(result == 0) {
					System.out.println("입력 실패");
				}else {
					System.out.println("입력 성공");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
					System.out.println("수행객체 닫기 성공");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
					System.out.println("접속 끊기 성공");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		//2) 쿼리 수행
		//3) 접속끊기
		
		
	}
}
