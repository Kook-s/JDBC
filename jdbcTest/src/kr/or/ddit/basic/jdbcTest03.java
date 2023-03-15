package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//문제)lprod_id 값을 2개을 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오

public class jdbcTest03 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Scanner scan = new Scanner(System.in);
		System.out.println("두개의 값 입력\n");
		System.out.print("첫번째 값부터 >> ");
		int num = scan.nextInt();
		System.out.print("두번째 값까지 >> ");
		int num2 = scan.nextInt();
		System.out.println();

		
//		int max =Math.max(num,num2);
//		int min =Math.min(num,num2);
//		int max = num>num2? num:num2;
		
		if (num > num2) {
			int temp = num;
			num = num2;
			num2 = temp;
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHIN", "java");
			String sql = " select * from lprod where lprod_id between " + ++num + " and " + --num2;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println(" === SQL문 처리 결과 ===");
			System.out.println("--------------------------------------");
			while (rs.next()) {
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");

			}

		} catch (Exception e) {
			// TODO: handle exception
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
