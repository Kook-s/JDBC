package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import kr.or.ddit.basic.utill.DBUtil;

/*
 * LPROD테이블에 새로운 데이터 추가하기 
 * 
 * Lprod_gu와 Lprod_nm은 직접 입력받아서 처리하고,
 * Lprod_id 는 현재의 Lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 입력 받은 Lprod_gu가 이미 등로되어 있으면 다시 입력 받아서 처리한다.
*/
public class jdbcTest05 {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHIN", "java");

			conn = DBUtil.getConnection();

			String sql = "select max(lprod_id)+1 maxid from lprod";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int lprodId = 0;
			if (rs.next()) {
//				lprodId =rs.getInt(" max(lprod_id)+1"); alias가 없을 때 
//				lprodId =rs.getInt(1);
				lprodId = rs.getInt("maxid"); // alias가 있을 때
			}
			// ---------------------------------------------------------

			String lprodGu; // 상품 분류 코드
			int count = 0; // 입력한 상품 분류 코드 갯수

			do {
				System.out.print("상품 분류 코드(LPORD_GU) 입력 >> ");
				lprodGu = scan.next();

				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
//				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count == 1) {
					System.out.println("입력한 상품 분류 코드 " + lprodGu + "는(은) 이미 등록된 코드 입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
				}
			} while (count == 1);

			System.out.print("상품 분류명 (LPROD_NM) 입력 >>");
			String lprodNm = scan.next();

			String sql3 = "insert into lprod(lprod_id,lprod_gu,lprod_nm) " + "values(?,?,?)";
			// 경고뜨는 이유 >> 새로운 값으로 변경하니까 시스템 입장에서 잘못된게
			// 아닌지 시스템이 사용자에게 물어보는것
			// 안뜨게 하려면 close()를 해주면 안뜬다. 근데 해도 되고 안해도된다고 나와있음
//			pstmt.close();
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
//			if (stmt != null)
//				try {
//					stmt.close();
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
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

//System.out.println("상품 정보 추가하기");
//System.out.print("상품 번호 >>");
//String lprodGu = scan.next();
//
//String sql = "select count(*) from lprod\r\n" + "where lprod_gu = '" + lprodGu + "' ";
//
//stmt = conn.createStatement();
//rs = stmt.executeQuery(sql);
//
//int guNum = 0;
//while (rs.next()) {
//	guNum = rs.getInt("count(*)");
//}
//
//if (guNum == 0) {
//	System.out.print("상품명명 입력 >>");
//	String lprodNm = scan.next();
//	int count = 0;
//	
//	 sql = "select max(lprod_id)+1 from lprod";
//	 ResultSet rs2 = stmt.executeQuery(sql);			 
//	while (rs2.next()) {
//		count = rs2.getInt("max(lprod_id)+1");
//	}			
//	
//	 sql = "insert into Lprod(lprod_id,lprod_gu,lprod_nm) "
//			+ " values('" + count + "','"+lprodGu+"','"+lprodNm+"')";
//	int cnt = stmt.executeUpdate(sql);
//	System.out.println("반환값 : " + cnt);
//}else {
//	System.out.println("중복되었습니다.");
//}
