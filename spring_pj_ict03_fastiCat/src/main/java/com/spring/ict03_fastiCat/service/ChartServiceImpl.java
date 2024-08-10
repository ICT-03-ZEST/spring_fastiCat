package com.spring.ict03_fastiCat.service;

import java.io.IOException;  
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.ChartDAO;
import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.ReservationDTO;
import com.spring.ict03_fastiCat.dto.VisitDTO;

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
		
		//일일 등록 게시글 수 list
		List<BoardDTO> boardCnt = dao.regCountforday();
		System.out.println("boardCnt:"+boardCnt);
		
		// 일주일 간 예매된 수량
		int bookCnt = dao.bookingCountforWeek();
		
		//일일 예매 수량 list
		List<ReservationDTO> reservCnt = dao.bookCountForday();
		
		//인기 게시글 목록
		List<BoardDTO> list = dao.popularBoard();
		
		//주간 방문자수
		int visitCnt = dao.visitForWeek();
		
		//일별 방문자수(일주일 단위)
		List<VisitDTO> visit = dao.visitCount();
		
		model.addAttribute("userCnt", userCnt);
		model.addAttribute("showCnt", showCnt);
		model.addAttribute("regCnt", regCnt);
		model.addAttribute("boardCnt", boardCnt);
		model.addAttribute("bookCnt", bookCnt);
		model.addAttribute("reservCnt", reservCnt);
		model.addAttribute("list", list);
		model.addAttribute("visitCnt", visitCnt);
		model.addAttribute("visit", visit);
	}

	//방문자 수 insert
	@Override
	public void addVisit(HttpServletRequest req, Model model) throws ServletException, IOException {
		dao.addVisit();
	}
		

}
