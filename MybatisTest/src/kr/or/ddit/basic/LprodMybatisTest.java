package kr.or.ddit.basic;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//myBatis를 이용하여 DB자료를 처리하는 순서 및 방법
public class LprodMybatisTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 1.mybatis의 환경 설정 파일을 읽어와서 실행한다.(mybatis-config.xml)

		Reader rd = null;
		SqlSessionFactory sqlSessionFactory = null;

		try {
//        1-1 환경 설정 파일을 읽어 올 스트림 객체를 생성한다.
//			  (이때 읽어올 환경설정 파일을 지정해준다 . )
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");

//			1-2 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SQL문을 호출해서 실행할 수 있는 
//			    객체를 생성하는 SqlSessionFactory객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("mybatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}

		// ---------------------------------------------------------------
//		2. mapper에 등록된 SQL문 중에서 실행할 SQL문을 호출해서 원하는 작업을 수행한다.

//		 SqlSession 객체 >>실제 SQL문을 읽어와 처리하고 결과를 얻어오는 객체
		SqlSession session = null;

////		2-1 insert작업
//		System.out.println("insert 작업 시작");
//		System.out.print("Lprod-id 입력 : ");
//		int lprodId = scan.nextInt();
//
//		System.out.print("Lprod-gu 입력 : ");
//		String lprodGu = scan.next();
//
//		System.out.print("Lprod-nm 입력 : ");
//		String lprodNm = scan.next();
//
////		입력한 데이터를 VO에 저장한다.
//		LprodVO lprodVo = new LprodVO();
//		lprodVo.setLprod_id(lprodId);
//		lprodVo.setLprod_gu(lprodGu);
//		lprodVo.setLprod_nm(lprodNm);
//
//		try {
//			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
////			   >> opemSession()메서드를 이용한다.
////			   형식) SqlSessionFactory객체.openSession(논리값)
////			   >> '논리값'이 true면 'AutoCommit'이 활성화된 상태이고,
////			   >> '논리값'이 false면 'AutoCommit'이 비활성화된 상태이다.(기본값)
//			session = sqlSessionFactory.openSession();
//
////			sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
////			형식) session.insert("namespace속성값.id속성값", 파라미터클래스 )
////			         >> 반환값 : 작업에 성공한 레코드 수 
//			int insertCnt = session.insert("lprod.insertLprod", lprodVo);
//
//			if (insertCnt > 0) {
//				System.out.println("insert 작업 성공!!");
//			} else {
//				System.out.println("insert 작업 실패~~");
//			}
//		} finally {
////               SqlSession을 생성할 때 AutoCommit이 비활성화된 샅애일 때는 commit을 직접 실행해야 한다.
//			session.commit();
//
////			작업이 완료되면 SqlSession객체를 닫아 준다.
//			session.close();
//		}

//		-------------------------------------------------
//		2-2 연습

//		System.out.println("update 작업 시작");
//
//		System.out.print("수정할 Lprod_gu 입력 : ");
//		String lprodGu2 = scan.next();
//
//		System.out.print("새로운 Lprod_id 입력 : ");
//		int lprodId2 = scan.nextInt();
//
//		System.out.print("새로운 Lprod_nm 입력 : ");
//		String lprodNm2 = scan.next();
//
//		// 입력받은 VO객체를 저장한다.
//		LprodVO lvo = new LprodVO();
//		lvo.setLprod_gu(lprodGu2);
//		lvo.setLprod_id(lprodId2);
//		lvo.setLprod_nm(lprodNm2);
//
//		try {
//			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
//			session = sqlSessionFactory.openSession();
//
//			// sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
//			// 형식) session.update("namespace속성값.id속성값",파라미터클래스)
//			// 반환값 : 작업 성공한 레코드 수
//			int updateCnt = session.update("lprod.updateLprod", lvo);
//
//			if (updateCnt > 0) {
//				System.out.println("update 작업 성공!!");
//			} else {
//				System.out.println("update 작업 실패~~");
//			}
//
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
////        SqlSession을 생성할 때 AutoCommit이 비활성화된 샅애일 때는 commit을 직접 실행해야 한다.
//			session.commit();
//
////		작업이 완료되면 SqlSession객체를 닫아 준다.
//			session.close();
//		}
//----------------------------------------------------------------------------------------------------------------------

		// 2-3 delete연습

//		System.out.println("delete 연습 시작...");
//
//		System.out.print("삭제할 Lprod_gu 입력 : ");
//		String lprodGu3 = scan.next();
//
//		try {
//			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
//			session = sqlSessionFactory.openSession();
//			// sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
//			// 형식) session.update("namespace속성값.id속성값",파라미터클래스)
//			// 반환값 : 작업 성공한 레코드 수
//			int daeleteCnt = session.delete("lprod.deleteLprod", lprodGu3);
//			
//			if (daeleteCnt > 0) {
//			System.out.println("delete 작업 성공!!");
//		} else {
//			System.out.println("delete 작업 실패~~");
//		}
//		} finally {
////        SqlSession을 생성할 때 AutoCommit이 비활성화된 샅애일 때는 commit을 직접 실행해야 한다.
//			session.commit();
//
//			// sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
//
////		작업이 완료되면 SqlSession객체를 닫아 준다.
//			session.close();
//		}

		// ----------------------------------------------------------------------------------------------------------------------

		// 2-3 select연습

//		1) 처리한 결과가 여러개의 레코드일 경우
//		System.out.println("select 연습 시작(결과가 여러개일 경우)...");
//
//		try {
//			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
//			session = sqlSessionFactory.openSession();
//
//			// sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
//			// select문의 처리 결과가 여러개일 경우에는 selectList()메서드를 사용하는데
//			// 이 메서드는 여러개의 레코드가 각각을 VO객체에 담은 후 이 VO객체를 List에 추가해 주는
//			// 작업을 자동으로 수행한다.
//			// 형식) session.selctList('namespace속성값.id속성값',파라미터클래스)
		    // 반환값 : resultType에 이정한객체가 저장된 List객체
//
//			List<LprodVO> lprodList = session.selectList("lprod.getAllLprod");
//
//			System.out.println("결과 출력");
//			for (LprodVO lvo : lprodList) {
//				System.out.println("ID : " + lvo.getLprod_id());
//				System.out.println("GU : " + lvo.getLprod_gu());
//				System.out.println("NM : " + lvo.getLprod_nm());
//				System.out.println("---------------------");
//			}
//			System.out.println("출력 끝");
//		} finally {
//			// TODO: handle finally clause
//			
//		}

		// 2) 처리한 결과가 1개의 레코드일 경우
		System.out.println("select 작업 시작(결과가 1개일 경우)...");
		System.out.print("검색할 Lprod_gu 입력 >>");
		String lprodGu4 = scan.next();
		try {
			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
			session = sqlSessionFactory.openSession();

//			// sqlSession객체변수를 이용하여 처리할 SQl문을 호출해서 실행한다.
			// select문의 처리 결과가 1개일 경우에는 selectOne()메서드를 사용한다.
			// 형식) session.selctOne('namespace속성값.id속성값',파라미터클래스)
			// 반환값 : resultType에 설정한 자료형으로 반환한다.
			// 검색할 자료가 없으면 null을 반환한다.
			LprodVO lvo2 = session.selectOne("lprod.getLprod", lprodGu4);

			if (lvo2 == null) {
				System.out.println("검색한 데이터가 하나도 없습니다.");
			} else {
				System.out.println("ID : " + lvo2.getLprod_id());
				System.out.println("GU : " + lvo2.getLprod_gu());
				System.out.println("NM : " + lvo2.getLprod_nm());
				System.out.println("---------------------");
				System.out.println("출력 끝");
			}
		} finally {
			// TODO: handle finally clause
			session.close();
		}
	}
}
