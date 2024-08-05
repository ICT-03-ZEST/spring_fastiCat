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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.ict03_fastiCat.service.AdminBannerServiceImpl;
import com.spring.ict03_fastiCat.service.AdminBoardServiceImpl;
import com.spring.ict03_fastiCat.service.AdminNoticeServiceImpl;
import com.spring.ict03_fastiCat.service.AdminShowServiceImpl;
import com.spring.ict03_fastiCat.service.ChartServiceImpl;
import com.spring.ict03_fastiCat.service.CustomerServiceImpl;


@Controller
public class AdminController {
      
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	//1.배너서비스
	@Autowired
	private AdminBannerServiceImpl service1;
	
	//2.공연후기,자유게시판 서비스
	@Autowired
	private AdminBoardServiceImpl service2;
	
	//3.공지사항 서비스
	@Autowired
	private AdminNoticeServiceImpl service3;
	
	//4.회원관리 서비스
	@Autowired
	private CustomerServiceImpl service4;
	
	//5.공연관리,페스티벌관리 서비스
	@Autowired
	private AdminShowServiceImpl service5;
	
	//6.현황조회(차트) 서비스
	@Autowired
	private ChartServiceImpl chart;
	
	// 관리자 첫화면(현황조회)
	@RequestMapping("ad_dashboard.ad")
	public String ad_dashboard(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_dashboard.ad >>>");
		chart.chartCount(request, model);
		return "admin/ad_main/ad_dashboard";
	}
	
	
	// 1.
	// 배너등록 화면
	@RequestMapping("ad_bannerAdd.adban")
	public String ad_bannerAdd(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerAdd.adban >>>");
		return "admin/ad_banner/ad_bannerAdd";
		
	}
	
	// 배너등록 처리
	@RequestMapping("ad_bannerAddAction.adban")
	public String ad_bannerAddAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerAddAction.adban >>>");
		service1.bannerAddAction(request, model);
		return "admin/ad_banner/ad_bannerAddAction";
		
	}
	
	// 배너목록 조회
	@RequestMapping("ad_bannerEdit.adban")
	public String ad_bannerEdit(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerEdit.adban >>>");
		service1.bannerListAction(request, model);
		return "admin/ad_banner/ad_bannerEdit";	
	}
	
	// 배너 수정 상세페이지 조회
	@RequestMapping("ad_bannerModify.adban")
	public String ad_bannerModify(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerModify.adban >>>");
		service1.bannerDetailAction(request, model);
		return "admin/ad_banner/ad_bannerModify";	
	}
		
	// 배너 수정 처리
	@RequestMapping("ad_bannerModifyAction.adban")
	public String ad_bannerModifyAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerModifyAction.adban >>>");
		service1.bannerUpdateAction(request, model);
		return "admin/ad_banner/ad_bannerModifyAction";	
	}
	
	// 배너 삭제
	@RequestMapping("ad_bannerDeleteAction.adban")
	public String ad_bannerDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_bannerDeleteAction.adban >>>");
		service1.bannerDeleteAction(request, model);
		return "admin/ad_banner/ad_bannerDeleteAction";	
	}
	
	// 2.
	// 공연후기, 자유게시판 목록 조회
	@RequestMapping("board.adbc")
	public String board(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /board.adbc >>>");
		
		String category = request.getParameter("board_category");
		
		service2.boardListAction(request, model);
		
		if(category.equals("공연후기")) {
			return "admin/ad_review/ad_reviewEdit";
		}
		else {
			return "admin/ad_freeboard/ad_freeboardEdit";
		}
			
	}
	
	// 공연후기, 자유게시판 게시물 삭제
	@RequestMapping("boardDeleteAction.adbc")
	public String boardDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /boardDeleteAction.adbc >>>");
		
		String category = request.getParameter("board_category");
		
		service2.boardDeleteAction(request, model);
		
		if(category.equals("공연후기")) {
			return "admin/ad_review/ad_reviewDeleteAction";
		}
		else {
			return "admin/ad_freeboard/ad_freeboardDeleteAction";
		}
	}
	
	
	//3. 공지사항 
	// 공지사항 등록화면
	@RequestMapping("ad_noticeAdd.adnot")
	public String ad_noticeAdd(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeAdd.adnot >>>");
		return "admin/ad_notice/ad_noticeAdd";
	}
	
	// 공지사항등록 처리
	@RequestMapping("ad_noticeAddAction.adnot")
	public String ad_noticeAddAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeAdd.adnot >>>");
		service3.noticeAddAction(request, model);
		return "admin/ad_notice/ad_noticeAddAction";
	}
	
	// 공지사항목록 조회
	@RequestMapping("ad_noticeEdit.adnot")
	public String ad_noticeEdit(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeEdit.adnot >>>");
		service3.noticeListAction(request, model);
		return "admin/ad_notice/ad_noticeEdit";
	}
	
	// 공지사항 수정 상세페이지 조회
	@RequestMapping("ad_noticeModify.adnot")
	public String ad_noticeModify(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeModify.adnot >>>");
		service3.noticeDetailAction(request, model);
		return "admin/ad_notice/ad_noticeModify";
	}
	
	// 공지사항 수정 
	@RequestMapping("ad_noticeModifyAction.adnot")
	public String ad_noticeModifyAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeModifyAction.adnot >>>");
		service3.noticeUpdateAction(request, model);
		return "admin/ad_notice/ad_noticeModifyAction";
	}
	
	// 공지사항 삭제
	@RequestMapping("ad_noticeDeleteAction.adnot")
	public String ad_noticeDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_noticeDeleteAction.adnot >>>");
		service3.noticeDeleteAction(request, model);
		return "admin/ad_notice/ad_noticeDeleteAction";
	}
	
	
	//4. 회원관리
	// 전체 회원목록 조회
	@RequestMapping("ad_member.admember")
	public String ad_member(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_member.admember >>>");
		service4.memberListAction(request, model);
		return "admin/ad_member/ad_member";
	}
	
	// 회원 삭제
	@RequestMapping("ad_memberDeleteAction.admember")
	public String ad_memberDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_memberDeleteAction.admember >>>");
		service4.memberDeleteAction(request, model);
		return "admin/ad_member/ad_memberDeleteAction";
	}
	
	// 탈퇴 회원목록 조회
	@RequestMapping("ad_dropMember.admember")
	public String ad_dropMember(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_dropMember.admember >>>");
		service4.dropMemberListAction(request, model);
		return "admin/ad_member/ad_dropMember";
	}
	
	// 탈퇴회원 복구
	@RequestMapping("ad_dropMemberRestore.admember")
	public String ad_dropMemberRestore(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_dropMemberRestore.admember >>>");
		service4.dropMemberRestoreAction(request, model);
		return "admin/ad_member/ad_dropMemberRestore";
	}
	
	//5. 
	// 공연관리, 페스티벌 관리 목록 조회
	@RequestMapping("ad_showEdit.adshow")
	public String ad_showEdit(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showEdit.adshow >>>");
		
		String category = request.getParameter("showCategory");
		service5.showListAction(request, model);
		
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalEdit";
		}
		else {
			return "admin/ad_concert/ad_concertEdit";
		}
	}
	
	// 공연관리, 페스티벌 관리 등록 화면
	@RequestMapping("ad_showAdd.adshow")
	public String ad_showAdd(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showAdd.adshow >>>");
		
		String category = request.getParameter("showCategory");
		
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalAdd";
		}
		else {
			return "admin/ad_concert/ad_concertAdd";
		}
	}
	
	// 공연관리, 페스티벌 관리 등록 처리
	@RequestMapping("ad_showAddAction.adshow")
	public String ad_showAddAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showAddAction.adshow >>>");
		
		String category = request.getParameter("showCategory");
		service5.showAddAction(request, model);
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalAddAction";
		}
		else {
			return "admin/ad_concert/ad_concertAddAction";
		}
	}
	
	// 공연관리, 페스티벌 관리 수정 상세페이지
	@RequestMapping("ad_showModify.adshow")
	public String ad_showModify(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showModify.adshow >>>");
		
		String category = request.getParameter("showCategory");
		service5.showDetailAction(request, model);
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalModify";
		}
		else {
			return "admin/ad_concert/ad_concertModify";
		}
	}
	
	// 공연관리, 페스티벌 관리 수정 처리
	@RequestMapping("ad_showModifyAction.adshow")
	public String ad_showModifyAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showModifyAction.adshow >>>");
		
		String category = request.getParameter("showCategory");
		service5.showUpdateAction(request, model);
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalModifyAction";
		}
		else {
			return "admin/ad_concert/ad_concertModifyAction";
		}
	}
	
	// 공연관리, 페스티벌 관리 삭제
	@RequestMapping("ad_showDeleteAction.adshow")
	public String ad_showDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		logger.info("<<< url ==> /ad_showDeleteAction.adshow >>>");
		
		String category = request.getParameter("showCategory");
		service5.showDeleteAction(request, model);
		
		if(category.equals("페스티벌")) {
			return "admin/ad_festival/ad_festivalDeleteAction";
		}
		else {
			return "admin/ad_concert/ad_concertDeleteAction";
		}
	}
}

