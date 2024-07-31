package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ChartService {
		
		// 전체 회원수 
	   	// 일주일 간 예매된 횟수
		// 등록된 공연 및 페스티벌 수
		// 일주일 간 등록된 게시글 수 
		public void chartCount(HttpServletRequest req, Model model)
				throws ServletException, IOException; 
		
}
