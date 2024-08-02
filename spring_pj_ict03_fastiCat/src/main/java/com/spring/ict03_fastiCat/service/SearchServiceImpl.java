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

import com.spring.ict03_fastiCat.dao.SearchDAO;
import com.spring.ict03_fastiCat.dto.SearchDTO;
import com.spring.ict03_fastiCat.page.Paging;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDAO dao;

	// [ 게시판 목록 ]
	@Override
	public void boardListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("service - boardListAction");

		// 3단계. 화면에서 입력받은 값을 가져오기
		String query = request.getParameter("query");
		String pageNum = request.getParameter("pageNum");

		System.out.println("query :" + query);

		// 5-1단계. 전체 게시글 갯수 카운트
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt(query);
		System.out.println("total => " + total);

		paging.setTotalCount(total);

		// 5-2단계. 게시글 목록 조회
		int start = paging.getStartRow();
		int end = paging.getEndRow();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("query", "%" + query + "%");
		List<SearchDTO> list = dao.boardList(map);
		System.out.println("list : " + list);

		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("query", query); // 검색어도 전달
	}

	// 공연 목록
	@Override
	public void concertListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("서비스 - concertListAction()");

		// 3단계. 화면에서 입력받은 값을 가져오기
		String query = request.getParameter("query");
		String pageNum = request.getParameter("pageNum");

		// 5-1단계. 공연갯수
		int total = dao.getTotalCount(query);

		// 5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);

		int start = paging.getStartRow();
		int end = paging.getEndRow();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("query", "%" + query + "%");

		List<SearchDTO> list = dao.concertList(map);
		// System.out.println("list : " + list);

		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("query", query); // 검색어도 전달
	}

	// [ 게시판 세부 목록 ]
	@Override
	public void boardDetailListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("service - boardDetailListAction");

		// 3단계. 화면에서 입력받은 값을 가져오기
		String searchItem = request.getParameter("searchItem");
		String query = request.getParameter("query");
		String pageNum = request.getParameter("pageNum");
		System.out.println("searchItem : " + searchItem);
		System.out.println("query : " + query);

		// 5-1단계. 전체 게시글 갯수 카운트
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt(query);
		System.out.println("total => " + total);

		paging.setTotalCount(total);

		// 5-2단계. 게시글 목록 조회
		int start = paging.getStartRow();
		int end = paging.getEndRow();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("query", "%" + query + "%");
		map.put("searchItem", searchItem);

		List<SearchDTO> list = dao.boardDetailList(map);
		System.out.println("list : " + list);

		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("query", query); // 검색어도 전달
	}
}