package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.AdminBoardDAO;
import com.spring.ict03_fastiCat.dto.AdminBoardDTO;
import com.spring.ict03_fastiCat.page.Paging;

@Service
public class AdminBoardServiceImpl implements AdminBoardService{
	
	@Autowired
	private AdminBoardDAO dao;
	
	// 공연후기, 자유게시판 목록 조회
	@Override
	public void boardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		
		String pageNum = request.getParameter("pageNum"); // null
		String category = request.getParameter("board_category");
		
		System.out.println("pageNum: " + pageNum);
		System.out.println("category: " + category);
		//AdminBoardDAO dao = AdminBoardDAOImpl.getInstance();
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("category", category);
		
		// 5-1 단계. 전체 게시글 갯수 카운트
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt(params);
		
		System.out.println("total: " + total);
		paging.setTotalCount(total);
		
		// 5-2 단계. 게시글 목록조회
		List<AdminBoardDTO> list = dao.boardList(params);
		System.out.println("service list " + list);
		
		//6단계 jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
	}

	// 공연후기, 자유게시판 게시글 삭제
	@Override
	public void boardDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("service - boardDeleteAction");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		String category = request.getParameter("board_category");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		System.out.println("서비스 category : " + category + "board_num : " + board_num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("category", category);
		int deleteCnt = dao.boardDelete(map);
		
		model.addAttribute("category", category);
		model.addAttribute("deleteCnt", deleteCnt);
	}

}
