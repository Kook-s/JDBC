package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.dcn.DatabaseChangeEvent;

public class jdbcTest02 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Scanner scan = new Scanner(System.in);
		System.out.print("숫자 입력 >> ");
		int num = scan.nextInt();
		System.out.println(num + "값 보다 큰자료 출력 합니다\n");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHIN", "java");

			String sql = "SELECT * FROM lprod where lprod_id > " + num;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println(" === SQL문 처리 결과 ===");
			while (rs.next()) {

				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
			}

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
	}
}
