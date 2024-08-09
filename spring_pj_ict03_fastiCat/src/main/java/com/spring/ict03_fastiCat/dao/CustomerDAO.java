package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.CustomerDTO;

public interface CustomerDAO {

	// ID 중복확인 처리
	public int useridCheck(String strId);

	// 회원가입 처리
	public int insertCustomer(CustomerDTO dto);

	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	public int idPasswordChk(Map<String, Object> map);

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

	// 회원비밀번호정보 가져오기
	public CustomerDTO getpassword(String strId);

	// 시큐리티 - 회원가입 성공시 이메일 인증을 위한 이메일 정보 전송
	public void sendEmail(String email, String key);
	 
	// 시큐리티 - 로그인전에 이메일 인증을 한다.
	public int selectKey(String key);
	 
	// 시큐리티 - enabled를 1로 update
	public int updateGrade(String key);

}
