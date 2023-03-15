package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcTest04 {
	// bank_info 테이블에 계좌번호 정보를 추가하는 예제

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHIN", "java");

			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호 입력 >>");
			String bankNo = scan.next();

			System.out.print("은행명 입력 >>");
			String bankName = scan.next();

			System.out.print("예즘주명 입력 >>");
			String userName = scan.next();
//
//			String sql = "insert into BANKINFO(BANK_NO,bank_name,bank_user_name,bank_date) " + "values( '" + bankNo
//					+ "' , '" + bankName + "' , '" + userName + "' ,sysdate)";
//
//			System.out.println("SQL문 >> " + sql);
//
//			stmt = conn.createStatement();
//			
//			// select문을 실행할 때는 executeQuery()메서드를 사용하고,
//			// select문이 아닌 문장(insert , update , delete등)을 
//			// 실행할 때는 executeUpdate()메서드를 사용한다.
//			
//			// executeUpdate()메서드의 반환값 >> 작업에 성공한 레코드 수 
//			int cnt = stmt.executeUpdate(sql);

// -------------------------------------------------------------------------------------------

			// PreparedStatement객체를 이용하여 처리하기

			// SQl문을 작성할 때 SQL문에서 데이터가 들어갈 자리를 물음표(?)표시하여 작성한다.
			String sql = "insert into BANKINFO(BANK_NO,bank_name,bank_user_name,bank_date) "
					+ "values( ?,?,? ,sysdate)";
			// PreparedStatement객체 생성 >> 사용할 SQL문을 인수값으로 넘겨준다.
			pstmt = conn.prepareStatement(sql);

			// SQL물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표번호,셋팅할데이터)
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);

			// 데이터의 셋팅이 완료되면 실행한다.
			// select문일 경우 >> executeQuery()메서드 이용
			// select문이 아닐 경우 >> executeUpdate()메서드 이용
			
			//차이는 sql을 언제 넣어주냐 차이 위에서는 stmt 에서 넣어줬고 , 여기에서는 conn에 넣어줌
			int cnt = pstmt.executeUpdate();

			System.out.println("반환값 : " + cnt);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
	}
}
