package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.ChartDAO;
import com.spring.ict03_fastiCat.dto.BoardDTO;

@Service
public class ChartServiceImpl implements ChartService {
	
	@Autowired
	private ChartDAO dao;
	
	// 전체 회원수 
	@Override
	public void chartCount(HttpServletRequest req, Model model) 
			throws ServletException, IOException {
		// 전체 회원수 
		int userCnt = dao.userCount();
		// 등록된 공연 및 페스티벌 수
		int showCnt = dao.showCount();
		// 일주일 간 등록된 게시글 수 
		int regCnt = dao.regBoardCountforWeek();
		// 일주일 간 예매된 횟수
		int bookCnt = dao.bookingCountforWeek();
		//인기 게시글 목록
		List<BoardDTO> list = dao.popularBoard();
		
		model.addAttribute("userCnt", userCnt);
		model.addAttribute("showCnt", showCnt);
		model.addAttribute("regCnt", regCnt);
		model.addAttribute("bookCnt", bookCnt);
		model.addAttribute("list", list);
	}
		

}
