package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.or.ddit.basic.utill.DBUtil;
import kr.or.ddit.basic.utill.DBUtil3;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoimpl implements IMemberDao {

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember values(?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
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
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
			// TODO: handle exception
		} finally {
			if (rs != null)
				try {
					rs.close();
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

		return count;
	}

}
