package com.spring.fasticat.dao;

import java.util.List;
import java.util.Map;

import com.spring.fasticat.dto.BoardDTO;
import com.spring.fasticat.dto.MyPageDTO;
import com.spring.fasticat.dto.MyReservationDTO;

public interface MyPageDAO {
		
		// 로그인 처리 / 회원정보 인증 (수정, 탈퇴)
		public int idPasswordChk(Map<String, Object> map);
		
		// 회원정보 인증처리 및 탈퇴처리
		public int deleteUser(String strId);
		
		// 회원정보 인증처리 및 상세페이지
		public MyPageDTO getUserDetail(String strId);
		
		// 회원정보 수정처리
		public int updateUser(MyPageDTO dto);
		
		// 게시물 목록
		public List<BoardDTO> myBoardList(Map<String, Object> map);
		
		// 내 댓글 목록
		public List<BoardDTO> myCommentList(Map<String, Object> map);
		
		// 게시물 갯수
		public int myBoardCnt(Map<String, Object> map);
		
		// 내 댓글 갯수
		public int myCommentCnt(String strId);
		
		// 게시물 삭제
		public int boardDelete(Map<String, Object> map);
		
		// 예매 내역 갯수
		public int resBoardCnt(String strId);
		
		// 게시물 목록
		public List<MyReservationDTO> resBoardList(Map<String, Object> map);
		
		public int resDelete(Map<String, Object> map);
}
