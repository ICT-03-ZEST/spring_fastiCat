package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MainShowService {
		
		
	// 페스티벌 목록
	public void MainShowList(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	
	// 페스티벌 상세페이지
	public void MainshowInfo(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
		
}
