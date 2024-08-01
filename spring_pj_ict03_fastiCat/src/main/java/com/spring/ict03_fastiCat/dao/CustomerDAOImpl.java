package com.spring.ict03_fastiCat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.CustomerDTO;
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 관리자 - 회원목록 조회
	@Override
	public List<CustomerDTO> memberList() {
		System.out.println("DAO - memberList() ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		List<CustomerDTO> list = dao.memberList();
		return list;
	}

	// 관리자 - 회원수
	@Override
	public int memberCnt() {
		System.out.println("DAO - memberCnt");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int total = dao.memberCnt();
		System.out.println("total : " + total);
		return total;
	}

	// 관리자 - 회원 삭제
	@Override
	public int deleteMember(String userid) {
		System.out.println("DAO - deleteMember ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int deleteCnt = dao.deleteMember(userid);
		System.out.println("deleteCnt : " + deleteCnt);
		return deleteCnt;
	}

	// 관리자 - 탈퇴 회원목록 조회
	@Override
	public List<CustomerDTO> dropMemberList() {
		System.out.println("DAO - dropMemberList() ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		List<CustomerDTO> list = dao.dropMemberList();
		return list;
	}

	// 관리자 - 탈퇴 회원 수
	@Override
	public int dropMemberCnt() {
		System.out.println("DAO - dropMemberCnt");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int total = dao.dropMemberCnt();
		System.out.println("total : " + total);
		return total;
	}

	// 관리자 - 탈퇴회원 복구
	@Override
	public int dropMemberRestore(String userid) {
		System.out.println("DAO - dropMemberRestore ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int updateCnt = dao.dropMemberRestore(userid);
		System.out.println("updateCnt : " + updateCnt);
		return updateCnt;
	}
	
	
}
