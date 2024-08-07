package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AdminShowService {
		// 공연, 페스티벌 등록
		public void showAddAction(MultipartHttpServletRequest request, Model model)
				throws ServletException, IOException;
		
		// 공연, 페스티벌 목록
		public void showListAction(HttpServletRequest request, Model model)
				throws ServletException, IOException;
		
		// 공연, 페스티벌 상세페이지
		public void showDetailAction(HttpServletRequest request, Model model)
				throws ServletException, IOException;
		
		// 공연, 페스티벌 수정
		public void showUpdateAction(MultipartHttpServletRequest request, Model model)
				throws ServletException, IOException;
		
		// 공연, 페스티벌 삭제
		public void showDeleteAction(HttpServletRequest request, Model model)
				throws ServletException, IOException;
		

}
