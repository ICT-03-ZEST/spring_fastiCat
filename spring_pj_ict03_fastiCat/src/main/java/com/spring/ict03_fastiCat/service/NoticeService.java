package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface NoticeService {
	
	//공지사항 목록
	public void NoticeBoardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//공지사항 상세페이지
	public void NoticeBoardDetail_Action(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//공지사항 수정
	public void NoticeBoard_UpdateAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//공지사항 삭제
	public void NoticeBoard_DeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//공지사항 작성
	public void NoticeBoard_InsertAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
}
