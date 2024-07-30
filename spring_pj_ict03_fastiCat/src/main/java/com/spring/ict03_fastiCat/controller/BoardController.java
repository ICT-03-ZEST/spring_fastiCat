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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.ict03_fastiCat.service.BoardServiceImpl;
import com.spring.ict03_fastiCat.service.MainShowServiceImpl;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardServiceImpl serv;
	
	@RequestMapping("main.do")
	public String main(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		return "common/main";
	}
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		req.getSession().setAttribute("sessionID", "user3"); // 로그인
		return "common/main";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		req.getSession().invalidate(); // 로그아윳
		return "common/main";
	}
	
	// 1.게시판 목록조회
	@RequestMapping("board.bc")
	public String board(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardList.bc >>");
//		req.getSession().removeAttribute("myBoard"); // 게시판 목록으로 돌아오기위해 마이페이지 세션을 종료
		
		serv.boardListAction(req, model);
	 
		return "customer/normal_board/board/board";
	}
	
	// 1-1. 게시판 상세페이지 
	@RequestMapping("boardDetail.bc")
	public String boardDetail(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardDetail.bc >>");
		
		serv.boardDetailAction(req, model);
	 
		return "customer/normal_board/board/boardDetail";
	}
		
		
	//좋아요 추가 / 삭제
	@RequestMapping("heartClick.bc") 
	public String heartClick(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => heartClick.bc >>");
		int heart = Integer.parseInt(req.getParameter("heart"));
		
		if(heart == 1) {
			serv.heartInsertAction(req, model);
		}
	
		else { 
			serv.heartDeleteAction(req, model); 
		}
		 
		return "customer/normal_board/board/boardDetail";
	}
		
		
	//2-1. 게시글 추가 페이지
	@RequestMapping("boardInsert.bc")
	public String boardInsert(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardInsert.bc >>");
			
			String category = req.getParameter("category");
			req.setAttribute("category", category);
			
			return "customer/mypage/board_fn/boardInsert";
		}
		
	// 2-2. 게시글 추가 처리
	@RequestMapping("boardInsertAction.bc")
	public String boardInsertAction(MultipartHttpServletRequest req, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardInsertAction.bc >>");
		serv.boardInsertAction(req, model);
		return  "customer/mypage/board_fn/boardInsertAction";
	}
	
	// 3-1.게시글 수정 페이지 
	@RequestMapping("boardUpdate.bc")
	public String boardUpdate(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardUpdate.bc >>");
		serv.boardDetailAction(req, model);
		return  "customer/mypage/board_fn/boardUpdate";
	}
		
		
	// 3-2.게시글 수정 처리
	@RequestMapping("boardUpdateAction.bc")
	public String boardUpdateAction(MultipartHttpServletRequest req, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardUpdateAction.bc >>");
		serv.boardUpdateAction(req, model);
		return  "customer/mypage/board_fn/boardUpdateAction";
	}
	
	// 4.게시글 삭제
	@RequestMapping("boardDeleteAction.bc")
	public String boardUpdateAction(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => boardDeleteAction.bc >>");
		serv.boardDeleteAction(req, model);
		return  "customer/mypage/board_fn/boardDeleteAction";
	}
	
	// 5. 댓글 목록조회
	@RequestMapping("comment_list.bc")
	public String comment_list(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => comment_list.bc >>");
		serv.commentListAction(req, model);
		return  "customer/mypage/comment_fn/comment";
	}
	
	// 6. 댓글 추가
	@RequestMapping("comment_insert.bc")
	public String comment_insert(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => comment_insert.bc >>");
		serv.commentAddAction(req, model);
		
		return "customer/normal_board/board/boardDetail";
	}	
		
		
	// 7. 댓글 수정페이지
	@RequestMapping("comment_update.bc")
	public String comment_update(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => comment_update.bc >>");
		serv.commentMod(req, model);
		
		return  "customer/mypage/comment_fn/comment_update";
	}	
	
	// 7. 댓글 수정처리
	@RequestMapping("comment_updateAction.bc")
	public String comment_updateAction(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => comment_updateAction.bc >>");
		serv.commentModAction(req, model);
		
		return  "customer/mypage/comment_fn/comment_Action";
	}	
		
	// 8. 댓글 삭제처리
	@RequestMapping("comment_deleteAction.bc")
	public String comment_deleteAction(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => comment_deleteAction.bc >>");
		serv.commentDelAction(req, model);
		
		return  "customer/mypage/comment_fn/comment_Action";
	}	
		
	// ------------------------공연/페스티벌-------------------------------
	
	@Autowired
	private MainShowServiceImpl show;
	// 공연목록 조회
	@RequestMapping("showList.pf")
	public String contentList(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => showList.bc >>");
		show.MainShowList(req, model);
		
		return  "customer/show_category/showList";
	}	
	
			
	// 공연 수정 상세페이지 조회
	@RequestMapping("showInfo.pf")
	public String showInfo(HttpServletRequest req, Model model)
			throws ServletException, IOException {
		logger.info("<< url => showInfo.bc >>");
		show.MainshowInfo(req, model);
		
		return  "customer/show_category/showInfo";
	}	

}
