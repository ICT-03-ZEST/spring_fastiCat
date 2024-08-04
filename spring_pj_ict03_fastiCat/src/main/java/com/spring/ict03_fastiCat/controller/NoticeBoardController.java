package com.spring.ict03_fastiCat.controller;

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

import com.spring.ict03_fastiCat.service.NoticeServiceImpl;

@Controller
public class NoticeBoardController {

	//전역변수로 사용
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	
		@Autowired	
		private NoticeServiceImpl service;
		String viewPage="";

		
		//공지사항 목록
		@RequestMapping("notice_boardList.nb")
		public String notice_boardList(HttpServletRequest request, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_boardList");
			
			//서비스호출
			service.NoticeBoardListAction(request, model);
			return "customer/normal_board/notice_board/notice_boardList";
		}

		//공지사항 작성
		@RequestMapping("notice_insert.nb")
		public String notice_insert(HttpServletRequest request, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_insert");

			return "customer/normal_board/notice_board/notice_insert";
		}
		
		//공지사항 작성처리
		@RequestMapping("notice_insertAction.nb")
		public String notice_insertAction(HttpServletRequest request,HttpServletResponse response, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_insertAction");
			
			//서비스호출
			service.NoticeBoard_InsertAction(request, model);
			viewPage = request.getContextPath()+"/notice_boardList.nb";
			response.sendRedirect(viewPage);
			
			return null;
		}
		
		//공지사항 상세페이지
		@RequestMapping("notice_detail.nb")
		public String notice_detail(HttpServletRequest request, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_detail");
			
			//서비스호출
			service.NoticeBoardDetail_Action(request, model);
			return "customer/normal_board/notice_board/notice_detail";
		}
		
		//공지사항 수정페이지
		@RequestMapping("notice_update.nb")
		public String notice_update(HttpServletRequest request, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_update");
			
			//서비스호출
			service.NoticeBoardDetail_Action(request, model);
			//상세페이지에 들어간 값들을 가지고 수정을 해야하기 때문에 detailAction을 또 호출해야함.
			return "customer/normal_board/notice_board/notice_update";
		}
		
		//공지사항 수청처리 
		@RequestMapping("notice_updateAction.nb")
		public String notice_updateAction(HttpServletRequest request, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_updateAction");
			
			//서비스호출
			//실질적으로 처리가 이루어지는 부분이므로 updatAction을 호출
			service.NoticeBoard_UpdateAction(request, model);
			return "customer/normal_board/notice_board/notice_updateAction";
		}
		

		//공지사항 삭제
		@RequestMapping("notice_deleteAction.nb")
		public String notice_deleteAction(HttpServletRequest request,HttpServletResponse response, Model model)
			throws ServletException, IOException{
			
			//콘솔 출력확인
			logger.info("url -> notice_deleteAction");
			
			//서비스호출
			service.NoticeBoard_DeleteAction(request, model);
			viewPage = request.getContextPath()+"/notice_boardList.nb";
			response.sendRedirect(viewPage);
			return null;
		}

}
