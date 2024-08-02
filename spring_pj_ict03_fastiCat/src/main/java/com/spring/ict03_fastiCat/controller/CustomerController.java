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
import com.spring.ict03_fastiCat.service.CustomerServiceImpl;


@Controller
public class CustomerController {
      
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private AdminBannerServiceImpl bannerService;

	@Autowired
	private CustomerServiceImpl customerservice;

	// 배너등록 화면
	@RequestMapping("main.do")
	public String main(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /main.do >>>");
		bannerService.getMainBanner(request, model);
		return "common/main";
		
	}
	// 회원가입
	@RequestMapping("join.do")
	public String join() {
		logger.info("url ==> /join.do ");

		return "customer/join/join";
	}

	// 중복확인 -> join.js에서 호출
	@RequestMapping("idConfirmAction.do")
	public String idConfirmAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /join.do ");

		customerservice.idConfirmAction(request, model);
		return "customer/join/idConfirmAction";
	}

	// 회원가입처리 페이지
	@RequestMapping("joinAction.do")
	public String joinAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /joinAction.do ");

		customerservice.signInAction(request, model);
		return "customer/join/joinAction";
	}

	// [ login ]
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /login.do ");

		return "customer/login/login";
	}

	// [ login 처리 ]
	@RequestMapping("loginAction.do")
	public String loginAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /loginAction.do ");

		customerservice.loginAction(request, model);
		return "redirect:/";
	}

	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /logout.do ");
		// 세션 삭제
		request.getSession().invalidate();
		return "redirect:/";
	}

}
