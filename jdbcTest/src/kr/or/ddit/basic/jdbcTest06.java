package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.basic.utill.DBUtil;

/*
 * 회원을 관리하는 프로그램을 작성하시오
 * (MYMEMBER 테이블 이용)
 *
 * 아래의 메뉴를 모두 구현하시오.(CRUD 기능 구현하기)
 * 메뉴 예시)
 * --------------------
 * 1. 자료 추가       >> insert(C)
 * 2. 자료 삭제       >> Delete(D) 
 * 3. 자료 수정       >> Update(U)  
 * 4. 전체 자료 출력  >> select(R)
 * 0. 작업 끝.
 * --------------------
 * 조건)
 * 1) 자료 추가에 '회원ID' 는 중복되지 않는다.(중복되면 다시 입력 받는다.)
 * 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 * 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
*/
public class jdbcTest06 {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		Connection conn = DBUtil.getConnection();
		new jdbcTest06().start();
		
	}

	public void insert(Connection conn) {
		try {
			String sql = "insert into mymember values(?,?,?,?,?) ";

			System.out.print("아이디를 입력해주세요 >> ");
			String memId = scan.next();

			String sql6 = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql6);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("cnt");
			}

			if (count == 1) {
				System.out.println("입력한 상품 분류 코드 " + memId + "는(은) 이미 등록된 코드 입니다.");
				System.out.println("다른 코드로 다시 입력하세요...");
				return;
			}
			System.out.print("패스워드를 입력해주세요 >> ");
			String memPass = scan.next();
			System.out.print("이름을 입력해주세요 >> ");
			String memNm = scan.next();
			System.out.print("전화번호를 입력해주세요 >> ");
			String memTel = scan.next();
			System.out.print("주소를 입력해주세요 >> ");
			String memAdd = scan.next();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memNm);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAdd);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}
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

	public void delete(Connection conn) {
		try {
			String sql2 = "DELETE from MYMEMBER where MEM_ID =?";
			System.out.print("삭제 할 아이디를 입력해 주세요 >>");
			pstmt = conn.prepareStatement(sql2);
			String memId = scan.next();
			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("삭제 성공!!!");
			} else {
				System.out.println("존재 하지 않는 아이디 입니다.");
			}
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

	public void update(Connection conn) {
		try {
			String sql3 = "UPDATE mymember set " + " mem_pass  = ?, " + " mem_name = ?, " + " mem_tel = ?, "
					+ "mem_addr = ? " + "where mem_id = ? ";
			pstmt = conn.prepareStatement(sql3);

			System.out.print("업데이트 할 아이디를 입력해 주세요 >>");
			String memId = scan.next();

			System.out.print("패스워드를 입력해주세요 >> ");
			String memPass = scan.next();
			System.out.print("이름을 입력해주세요 >> ");
			String memNm = scan.next();
			System.out.print("전화번호를 입력해주세요 >> ");
			String memTel = scan.next();
			System.out.print("주소를 입력해주세요 >> ");
			String memAdd = scan.next();

			pstmt.setString(1, memPass);
			pstmt.setString(2, memNm);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAdd);

			pstmt.setString(5, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("수정 성공!!!");
			} else {
				System.out.println("존재 하지 않는 아이디 입니다.");
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

	public void select(Connection conn) {
		try {
			String sql4 = "select mem_id ,mem_pass,mem_name,mem_tel,mem_addr from mymember";
			pstmt = conn.prepareStatement(sql4);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// String total = rs.getString(");
				System.out.println("---------------");
				System.out.println("아이디 : " + rs.getString(1));
				System.out.println("비밀번호 : " + rs.getString(2));
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("전화번호 : " + rs.getString(4));
				System.out.println("주소 : " + rs.getString(5));
				System.out.println("---------------");
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
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
	}

	public void start() {

		while (true) {
			System.out.println("--------------------");
			System.out.println(" 1. 자료 추가 ");
			System.out.println(" 2. 자료 삭제 ");
			System.out.println(" 3. 자료 수정 ");
			System.out.println(" 4. 전체 자료 출력");
			System.out.println(" 0. 작업 끝.");
			System.out.println("--------------------");
			System.out.print(" 선택하세요 >>");
			int ch = scan.nextInt();
			try {
				conn = DBUtil.getConnection();

				switch (ch) {
				case 1:
					insert(conn);
					break;
				case 2:
					delete(conn);

					break;
				case 3:
					update(conn);

					break;
				case 4:
					select(conn);
					break;
				case 0:
					System.out.println("CRUD종료되었습니다.");
					return;

				default:
					System.out.println("잘못 입력했습니다, 다시 입력해주세요");
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {

				if (conn != null)
					try {
						conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
			}
		}

	}

}
