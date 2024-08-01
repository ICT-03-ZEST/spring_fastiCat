package com.spring.ict03_fastiCat.dao;

import java.util.List;

import com.spring.ict03_fastiCat.dto.CustomerDTO;

public interface CustomerDAO {

	// 관리자 - 회원목록 조회
	public List<CustomerDTO> memberList();

	// 관리자 - 회원 수
	public int memberCnt();

	// 관리자 - 회원 삭제
	public int deleteMember(String userid);
	
	// 관리자 - 탈퇴 회원목록 조회
	public List<CustomerDTO> dropMemberList();

	// 관리자 - 회원 수
	public int dropMemberCnt();

	// 관리자 - 탈퇴회원 복구
	public int dropMemberRestore(String userid);

}
