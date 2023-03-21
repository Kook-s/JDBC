package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.utill.DBUtil;
import kr.or.ddit.basic.utill.DBUtil3;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoimpl implements IMemberDao {
	private static MemberDaoimpl dao;

	private MemberDaoimpl() {
	}

	public static MemberDaoimpl getInstance() {
		if (dao == null) {
			dao = new MemberDaoimpl();
		}
		return dao;
	}

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
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
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
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name=? , " + "mem_tel=? , mem_addr=? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());

			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return cnt;
	}

	public int updateMember2(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		String input = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set ";

			if (memVo.getMem_pass() != null) {
				sql += " mem_pass = ? ";
				input = memVo.getMem_pass();
			} else if (memVo.getMem_name() != null) {
				sql += " mem_name = ? ";
				input = memVo.getMem_name();
			} else if (memVo.getMem_tel() != null) {
				sql += " mem_tel = ? ";
				input = memVo.getMem_tel();
			} else if (memVo.getMem_addr() != null) {
				sql += " mem_addr =? ";
				input = memVo.getMem_addr();
			}
			sql += " where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);
			pstmt.setString(2, memVo.getMem_id());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return cnt;
	}

	public int updateMember3(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		Map<String, String> dataMap = new HashMap<>();
		try {
			conn = DBUtil3.getConnection();

			String temp = "";

			if (!"".equals(memVo.getMem_pass())) {
				dataMap.put("mem_pass", memVo.getMem_pass());
			}
			if (!"".equals(memVo.getMem_name())) {
				dataMap.put("mem_name", memVo.getMem_name());
			}
			if (!"".equals(memVo.getMem_tel())) {
				dataMap.put("mem_tel", memVo.getMem_tel());
			}
			if (!"".equals(memVo.getMem_addr())) {
				dataMap.put("mem_addr", memVo.getMem_addr());
			}

			for (String fieldName : dataMap.keySet()) {
				if (!"mem_Id".equals(fieldName)) {
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ? ";
				}
			}

			String sql = "update mymember set " + temp + " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);

			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				if (!"mem_Id".equals(fieldName)) {
					pstmt.setString(num++, dataMap.get(fieldName));
				}
			}
			pstmt.setString(num, memVo.getMem_id());

			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MemberVO> list = new ArrayList<>();
		int count = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MemberVO());
				list.get(count).setMem_id(rs.getString("mem_id"));
				list.get(count).setMem_pass(rs.getString("mem_pass"));
				list.get(count).setMem_name(rs.getString("mem_name"));
				list.get(count).setMem_tel(rs.getString("mem_tel"));
				list.get(count).setMem_addr(rs.getString("mem_addr"));

				count++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
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
