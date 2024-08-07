package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.CommentDTO;
import com.spring.ict03_fastiCat.dto.MyPageDTO;
import com.spring.ict03_fastiCat.dto.MyReservationDTO;

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
		
		// 예매 취소
		public int resDelete(Map<String, Object> map);
		
		// 내가 좋아요한 게시물 갯수
		public int myFavoriteCnt(String strId);
		
		// 내가 좋아요한 게시물 목록
		public List<BoardDTO> myFavoriteList(Map<String, Object> map);
		
		// 내가 좋아요한 게시물 갯수
		public int myReceivedCommentCnt(String strId);
		
		// 내가 좋아요한 게시물 목록
		public List<CommentDTO> myReceivedCommentList(Map<String, Object> map);
}
