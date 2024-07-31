package com.spring.ict03_fastiCat.dao;

import java.util.List;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.VisitDTO;

public interface ChartDAO {
	
	// 전체 회원수 
	public int userCount(); 
	
	// 일주일 간 예매된 횟수
	public int bookingCountforWeek(); 
	
	// 등록된 공연 및 페스티벌 수
	public int showCount(); 
	
	// 일주일 간 등록된 게시글 수 
	public int regBoardCountforWeek(); 
	
	//인기 게시글 목록
	public List<BoardDTO> popularBoard();

	//일별 방문자수(일주일 단위)
	public List<VisitDTO> visitCount();
}
