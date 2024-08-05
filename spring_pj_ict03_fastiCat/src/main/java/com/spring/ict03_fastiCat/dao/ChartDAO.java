package com.spring.ict03_fastiCat.dao;

import java.util.List; 

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.ReservationDTO;
import com.spring.ict03_fastiCat.dto.VisitDTO;

public interface ChartDAO {
	
	// 전체 회원수 
	public int userCount(); 
	
	// 등록된 공연 및 페스티벌 수
	public int showCount(); 
	
	// 일주일 간 등록된 게시글 수 
	public int regBoardCountforWeek(); 
	
	//일일 등록 게시글 수 list 
	public List<BoardDTO> regCountforday();
	
	// 일주일 간 예매된 횟수
	public int bookingCountforWeek(); 
		
	//일일 예매 수량
	public List<ReservationDTO> bookCountForday();
	
	//인기 게시글 목록
	public List<BoardDTO> popularBoard();

	//일별 방문자수(일주일 단위)
	public List<VisitDTO> visitCount();
	
	//방문자 수 추가
	public void addVisit();

	

	
}
