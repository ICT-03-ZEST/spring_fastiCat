package com.spring.ict03_fastiCat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ict03_fastiCat.service.AdminBannerServiceImpl;


@Controller
public class CustomerController {
      
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private AdminBannerServiceImpl bannerService;

	// 배너등록 화면
	@RequestMapping("main.do")
	public String main(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /main.do >>>");
		bannerService.getMainBanner(request, model);
		return "common/main";
		
	}


}
