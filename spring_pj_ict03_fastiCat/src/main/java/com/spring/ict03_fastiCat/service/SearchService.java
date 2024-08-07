package com.spring.ict03_fastiCat.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface SearchService {
	// 게시글 목록
	public void boardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;

	//공연 목록
	public void concertListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 게시글 세부 목록
	public void boardDetailListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;

}
