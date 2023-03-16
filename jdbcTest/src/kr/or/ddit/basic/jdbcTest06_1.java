package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.basic.utill.DBUtil;

public class jdbcTest06_1 { // jdbcTest06 더 정리한거
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new jdbcTest06_1().startMember();
	}

	private void disConnect() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (pstmt != null)
			try {
				pstmt.close();
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

	private void startMember() {
		// TODO Auto-generated method stub

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;

			case 3: // 수정
				updateMember();
				break;

			case 4: // 전체출력
				displayAllMenu();
				break;
			case 5: // 전체출력
				updateMember2();
				break;
			case 6: // 전체출력
				updateMember3();
				break;

			case 0:

				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력해 주세요");
				break;
			}
		}

	}

	private void updateMember3() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		// key값은 : 수정할 컬럼명, value값 : 수정할 데이터 값
		// 수정할 데이터값이 있을 때만 Map에 추가된다.
		Map<String, String> dataMap = new HashMap<>();

		System.out.println();
		scan.nextLine();
		
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		if (!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}

		System.out.print("새로운 회원이름>> ");
		String newName = scan.nextLine().trim();
		if (!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}

		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if (!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}

		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine().trim();
		if (!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		try {
			conn = DBUtil.getConnection();

			String temp = ""; // SQL문의 set이후에 수정할 컬럼 설정하는 부누이 저장될 변수
			// ? 안사용할때 
//			for (String fieldName : dataMap.keySet()) {
//				if (!"".equals(temp)) {
//					temp += ", ";
//				}
//				temp += fieldName + " = " + "'" + dataMap.get(fieldName) + "' ";
//			}
//			
//			String sql = "update mymember set " + temp + " where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);

			// ?사용일때 
			for (String fieldName : dataMap.keySet()) {
				if (!"".equals(temp)) {
					temp += ", ";
				}
				temp += fieldName + " = ? ";
			}

			String sql = "update mymember set " + temp + " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);

			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				pstmt.setString(num++, dataMap.get(fieldName));
			}
			pstmt.setString(num, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원 ID가 " + id + "인 회원정보 수정 성공!!!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void updateMember2() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		System.out.println();
		System.out.println("-----선택 정보 수정-----");
		System.out.println("1.새로운 회원 비밀번호");
		System.out.println("2.새로운 회원 이름");
		System.out.println("3.새로운 회원 전화번호");
		System.out.println("4.새로운 회원 주소");
		System.out.println("------------------------");
		System.out.print("선택 >>");
		String sql = "update mymember set ";
		String input = "";

		switch (scan.nextInt()) {
		case 1:
			System.out.print("새로운 비밀번호 >> ");
			sql += " mem_pass = ? ";

			break;
		case 2:
			System.out.print("새로운 회원이름>> ");
			sql += " mem_name = ? ";
			break;
		case 3:
			System.out.print("새로운 전화번호 >> ");
			sql += " mem_tel = ? ";
			break;
		case 4:
			scan.nextLine();// 버퍼 지우기
			System.out.print("새로운 회원주소 >> ");
			sql += " mem_addr =? ";
			break;
		default:
			System.out.println("잘못 선택했습니다 다시 입력해주세요.");
			break;
		}
		try {
			sql += " where mem_id = ?";
			scan.nextLine();
			input = scan.nextLine();

			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원 ID가 " + id + "인 회원정보 수정 성공!!!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			disConnect();
		}

	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}

		System.out.print("새로운 비밀번호 >> ");
		String pass = scan.next();
		System.out.print("새로운 회원이름>> ");
		String name = scan.next();
		System.out.print("새로운 전화번호 >> ");
		String tel = scan.next();
		scan.nextLine();// 버퍼 지우기
		System.out.print("새로운 회원주소 >> ");
		String addr = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name=? , " + "mem_tel=? , mem_addr=? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원 ID가" + id + "인 회원정보 수정 성공!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			disConnect();
		}

	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();

		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원 ID가" + id + "인 회원 정보 삭제 성공!!!");
			} else {
				System.out.println(id + "회원은 없는 회원이거나 삭제 작업에 실패했습니다...");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}

	private void displayAllMenu() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println(" ID   비밀번호   이 름     전화번호           주소   ");
		System.out.println("---------------------------------------------------------");

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");

				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t " + memTel + "\t" + memAddr);
			}
			if (cnt == 0) {
				System.out.println("등록된 정보가 하나도 없습니다.");
			}

			System.out.println("---------------------------------------------------------");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		do {
			System.out.print("회원ID >> ");
			id = scan.next();
			count = getMemberCount(id);
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록도니 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String pass = scan.next();
		System.out.print("회원이름>> ");
		String name = scan.next();
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		scan.nextLine();// 버퍼 지우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember values(?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(id + "회원 정보 추가 완료!!!");
			} else {
				System.out.println(id + "회원 정보 추가 실패!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}

	}

//회원 ID를 매개 변수로 받아서 해당 회원 ID의 개수를 반환하는 메서드 
	private int getMemberCount(String memId) {
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return count;
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println(" 1. 자료 추가 ");
		System.out.println(" 2. 자료 삭제 ");
		System.out.println(" 3. 자료 수정 (전체항목 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2 (수정항목 선택)");
		System.out.println(" 6. 자료 수정3 (입력항목만 수정)");
		System.out.println(" 0. 작업 끝.");
		System.out.println("--------------------");
		System.out.print(" 선택하세요 >>");

		return scan.nextInt();

	}

}
