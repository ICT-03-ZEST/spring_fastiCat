package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AdminBannerService {
	// 배너등록
	public void bannerAddAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 배너목록
	public void bannerListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 배너 상세페이지
	public void bannerDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 배너수정
	public void bannerUpdateAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 배너삭제
	public void bannerDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException;
	
	// 메인 - 배너 조회
	public void getMainBanner(HttpServletRequest request, Model model)
			throws ServletException, IOException;

	

}
