package kr.or.ddit.basic.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC 드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로
//구성된 class 작성하기
public class DBUtil {
	
	static {//초기화 블록 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}

	{
		
	} //생성자 생성할때 초기화 하는 블록
	
	// Connection객체를 반환하는 메서드
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHIN", "java");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("DB연결 실패!!!");
			return null;
		}
	}

}
