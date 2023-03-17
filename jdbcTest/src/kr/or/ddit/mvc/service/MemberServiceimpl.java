package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoimpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceimpl implements IMemberService {
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDao dao;

	public MemberServiceimpl() {
		dao = new MemberDaoimpl(); // DAO객체 생성
	}

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		// TODO Auto-generated method stub
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

}
