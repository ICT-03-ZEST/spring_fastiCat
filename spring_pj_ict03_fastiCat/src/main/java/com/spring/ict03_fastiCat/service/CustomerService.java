package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CustomerService {

	// ID 중복확인 처리
	public void idConfirmAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 회원가입 처리
	public void signInAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 로그인 처리/ 회원정보 인증(수정, 탈퇴)
//	public void loginAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 관리자 - 회원목록 조회
	public void memberListAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 관리자 - 회원 삭제
	public void memberDeleteAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 관리자 - 탈퇴 회원목록 조회
	public void dropMemberListAction(HttpServletRequest request, Model model) throws ServletException, IOException;

	// 관리자 - 탈퇴회원 복구
	public void dropMemberRestoreAction(HttpServletRequest request, Model model) throws ServletException, IOException;

}
