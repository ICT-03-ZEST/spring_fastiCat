package com.spring.fasticat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.fasticat.service.MyPageServiceImpl;

//회원이 공연/페스티벌 후기 및 자유 게시판 및 댓글을 작성,수정,삭제,조회
@Controller
public class MyPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
    
	@Autowired
	private MyPageServiceImpl service;
	
	

	// 1.게시판 목록조회 - 공연후기, 페스티벌후기, 자유 메뉴 선택시 해당 목록 전체조회(최신글 부터)
	@RequestMapping("/mypage.myp")
	public String mypage(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<url ==> /mypage.myp>");
		
		request.getSession().setAttribute("sessionID", "test");
		request.getSession().setAttribute("myBoard", "1");// 게시글 추가,수정,삭제 후 나의게시글 목록으로 돌아오는 값
		
		return "customer/mypage/myPage";
	}
	
	// 나의 게시물 목록
	@RequestMapping("/myBoardList.myp")
	public String myBoardList(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<url --> /myBoardList.myp>");
		
		request.getSession().setAttribute("myBoard","1");
          
		// 서비스 -> DAO(SELECT)
		service.boardListAction(request, response, model);
	
   	 	return "customer/mypage/myBoardList";
	} 
	
	// [나의 예매 내역]
	@RequestMapping("/myReservation.myp")
	public String myReservation(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<url --> /myReservation.myp>");
		
		request.getSession().setAttribute("myBoard","1");
		
		service.reservationListAction(request, response, model);
		
		return "customer/mypage/myReservation";
	
	}
	
	// 나의 예매 내역 취소 비밀번호 확인
	@RequestMapping("/resCancelPwdChk.myp")
	public String resCancelPwdChk(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<url --> /resCancelPwdChk.myp>");
			
		service.resCanPwdChk(request, response, model);
		
		return "customer/mypage/myResCancelPopup";
	
	}
	
	// 나의 예매 내역 취소 처리
	@RequestMapping("/myReservationCancelAction.myp")
	public String myReservationCancelAction(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<url --> /myReservationCancelAction.myp>");
		
		request.getSession().setAttribute("myBoard","1");
		
		service.reservationCancelAction(request, response, model);
		
		return "customer/mypage/myReservation";
	
	}
	
	// [회원수정 ]	
	// 회원수정 인증
	@RequestMapping("/modifyPwdChk.myp")
	public String modifyPwdChk(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /modifyDetailAction.myp >>>");
		
		service.modifyPwdChk(request, response, model);
		
		return "customer/mypage/myModifyPopup";
	}
	
	// 회원수정 처리 페이지
	@RequestMapping("/modifyUserAction.myp")
	public String modifyUserAction(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /modifyUserAction.myp >>>");
		
		service.modifyUserAction(request, response, model);
		
		return "customer/mypage/myModifyAction";
	}
	
	// [회원탈퇴]
	// 회원탈퇴 인증
	@RequestMapping("/deletePwdChk.myp")
	public String deletePwdChk(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /deletePwdChk.myp >>>");
		
		service.deletePwdChk(request, response, model);
		
		return "customer/mypage/myDeletePopup";
	}
	
	// 회원탈퇴 처리 페이지
	@RequestMapping("/deleteUserAction.myp")
	public String deleteUserAction(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /deleteUserAction.myp >>>");
		
		service.deleteUserAction(request, response, model);
		
		return "customer/mypage/myDeleteAction";
	}
	
	// [게시물 삭제]
	// 게시물 삭제 비밀번호 인증
	@RequestMapping("/bdDelPwdChk.myp")
	public String bdDelPwdChk(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /bdDelPwdChk.myp >>>");
		
		service.bdDelPwdChk(request, response, model);
		
		return "customer/mypage/bdDelete";
	}
	
	// 게시물 삭제 처리
	@RequestMapping("/BoardDeleteAction.myp")
	public String BoardDeleteAction(HttpServletRequest request, HttpServletResponse response , Model model)
    		throws ServletException, IOException {
		logger.info("<<< url ==> /BoardDeleteAction.myp >>>");
		
		service.BoardDeleteAction(request, response, model);
		
		return "customer/mypage/myBoardList";
	}
	

	

}