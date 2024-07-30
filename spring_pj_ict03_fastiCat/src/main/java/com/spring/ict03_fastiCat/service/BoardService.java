package com.spring.ict03_fastiCat.service;

import java.io.IOException;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	// 게시판 목록 조회
	public void boardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException; 
	
	// 게시글 상세페이지
	public void boardDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//좋아요 추가
	public void heartInsertAction(HttpServletRequest request, Model model)
			throws ServletException, IOException; 
	//좋아요 삭제 
	public void heartDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException; 
	
	// 게시글 작성처리
	public void boardInsertAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 게시글 수정처리
	public void boardUpdateAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 게시글 삭제처리 
	public void boardDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 댓글 목록처리
		public void commentListAction(HttpServletRequest request, Model model)
				throws ServletException, IOException;
		
	// 댓글 작성처리
	public void commentAddAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	//댓글 한건조회
	public void commentMod(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 댓글 수정처리
	public void commentModAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 댓글 삭제처리
	public void commentDelAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
}
