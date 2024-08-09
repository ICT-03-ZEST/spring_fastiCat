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
import com.spring.ict03_fastiCat.service.ChartServiceImpl;
import com.spring.ict03_fastiCat.service.CustomerServiceImpl;
import com.spring.ict03_fastiCat.service.ReservationServiceImpl;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private AdminBannerServiceImpl bannerService;

	@Autowired
	private CustomerServiceImpl customerservice;

	@Autowired
	private ChartServiceImpl chart;

	@Autowired
	private ReservationServiceImpl serviceCal;

	// 메인 화면
	@RequestMapping("main.do")
	public String main(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("<<< url ==> /main.do >>>");
		chart.addVisit(request, model); // 방문자수 증가(결산차트에 반영)
		bannerService.getMainBanner(request, model);
		serviceCal.reservationListAction(request, model);
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

	// [회원가입 처리 페이지  - 시큐리티 (비밀번호 암호화)]
	@RequestMapping("joinAction.do")
	public String joinAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /joinAction.do ");

		customerservice.signInAction(request, model);
		return "customer/join/joinAction";
	}
	
	// 시큐리티 - 가입성공시 이메일인증 후 enabled 권한을 1(=계정사용가능)로 update
   // CustomerDAOImpl의 sendEmail(String email, String key)에서 emailChkAction.do?key=" + key를 호출
   @RequestMapping("emailChkAction.do")
   public String emailChkAction(HttpServletRequest request, Model model) 
         throws ServletException, IOException {
      logger.info("<<< url ==>  /emailChkAction.do >>>");
      
      customerservice.emailChkAction(request, model);
      return "customer/join/emailChkAction";
   }

	// [ login ]
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /login.do ");

		return "customer/login/login";
	}

	// 로그인 처리 - 컨트롤러에서 불필요
	// 시큐리티 처리(userAuthenticationService에서 로그인 인증처리 : 비밀번호 인증, 로그인 성공실패여부, 권한없음을 판단)
//	@RequestMapping("loginAction.do")
//	public String loginAction(HttpServletRequest request, Model model) throws ServletException, IOException {
//		logger.info("url ==> /loginAction.do ");
//
//		customerservice.loginAction(request, model);
//		return "redirect:/";
//	}

	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("url ==> /logout.do ");
		// 세션 삭제
		request.getSession().invalidate();
		return "redirect:/";
	}

	// ticket 구입 상세페이지
	@RequestMapping("/showTicket_Detail.do")
	public String showTicket_Detail(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("<<< url ==>  /showTicket_Detail.do >>>");
		serviceCal.showTicketDetail(request, model);
		serviceCal.showTicketDetailList(request, model);
		return "showTiket/showTiketDetail";
	}

	@RequestMapping("/showTicketInsert.do")
	public String showTicketInsert(HttpServletRequest request, Model model) throws ServletException, IOException {
		logger.info("<<< url ==>  /showTicketInsert.do >>>");
		serviceCal.showTicketInsert(request, model);
		serviceCal.showTicketDetail(request, model);
		serviceCal.showTicketDetailList(request, model);
		return "showTiket/showTiketDetail";
	}
}
