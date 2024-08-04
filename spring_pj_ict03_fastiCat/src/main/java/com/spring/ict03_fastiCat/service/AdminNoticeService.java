package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface AdminNoticeService {
	
	// 공지사항 등록
	public void noticeAddAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 공지사항 목록
	public void noticeListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 공지사항 상세 페이지
	public void noticeDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 공지사항 수정 
	public void noticeUpdateAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 공지사항 삭제
	public void noticeDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
}
