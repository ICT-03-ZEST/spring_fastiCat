package com.spring.fasticat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MyPageService {

	// 회원정보 인증처리
	public void deletePwdChk(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException;
		
	// 회원정보 인증처리 및 탈퇴처리
	public void deleteUserAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 회원정보 인증처리
	public void modifyPwdChk(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 회원정보 수정처리
	public void modifyUserAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 내 게시물 목록 
	public void boardListAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 게시물 삭제 비밀번호 확인
	public void bdDelPwdChk(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 게시물 삭제 처리
	public void BoardDeleteAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 나의 예매 내역
	public void reservationListAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 나의 예매 내역 취소 확인
	public void resCanPwdChk(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	// 나의 예매 내역 처리
	public void reservationCancelAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException;
	
	
}
