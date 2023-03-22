package kr.or.ddit.basic;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * LPROD테이블에 새로운 데이터 추가하기 
 * 
 * Lprod_gu와 Lprod_nm은 직접 입력받아서 처리하고,
 * Lprod_id 는 현재의 Lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 입력 받은 Lprod_gu가 이미 등로되어 있으면 다시 입력 받아서 처리한다.
 * 
 * >> mapper 파일명은 'jdbc-mapper.xml'로 한다.
*/
public class jdbcToMybatis {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Reader rd = null;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession session = null;

		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config2.xml");

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("mybatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (Exception e2) {
				}
		}

		System.out.print("상품 분류 코드(LPORD_GU) 입력 >> ");
		String lprodGu = scan.next();
		int lprodId = 0;

		try {
			session = sqlSessionFactory.openSession();
			LprodVO lvo2 = session.selectOne("lprod.getOneLprod", lprodGu);
			if(lvo2==null) {
				System.out.println("존재 하지 않습니다");
				return;
			}
			List<LprodVO> lprodList = session.selectList("lprod.getLprod", lprodGu);
			lprodId = lprodList.size() + 1;
			System.out.println(lprodId);
		} finally {
			session.close();
		}

		System.out.print("Lprod-nm 입력 : ");
		String lprodNm = scan.next();

		LprodVO lprodVo = new LprodVO();
		lprodVo.setLprod_id(lprodId);
		lprodVo.setLprod_gu(lprodGu);
		lprodVo.setLprod_nm(lprodNm);

		try {
			session = sqlSessionFactory.openSession();
			int insertCnt = session.insert("lprod.insertLprod", lprodVo);
			if (insertCnt > 0) {
				System.out.println("insert 작업 성공!!");
			} else {
				System.out.println("insert 작업 실패~~");
			}
		} catch (Exception e) {
		} finally {
			session.commit();

			session.close();
		}
	}
}
