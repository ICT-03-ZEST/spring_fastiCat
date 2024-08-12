package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MyPageService {

	
	// 회원정보 인증처리 및 탈퇴처리
	public void deleteUserAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 회원정보 수정처리
	public void modifyUserAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 내 게시물 목록 
	public void boardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 내 댓글 목록
	public void commentListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 게시물 삭제 처리
	public void BoardDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 댓글 삭제 처리
	public void CommentDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 나의 예매 내역
	public void reservationListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 나의 예매 내역 처리
	public void reservationCancelAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 비밀번호 확인
	public void pwdChk(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 내가 좋아요한 게시글
	public void FavoriteListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 내가 받은 댓글
	public void ReceivedCommentListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;

	
	
}
