package com.spring.ict03_fastiCat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.VisitDTO;

@Repository
public class ChartDAOImpl implements ChartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 전체 회원수
	@Override
	public int userCount() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		int userCnt = dao.userCount(); 
		return userCnt;
	}
	
	// 등록된 공연 및 페스티벌 수
	@Override
	public int showCount() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		int showCnt = dao.showCount(); 
		System.out.println("showCnt: " + showCnt);
		return showCnt;
	}
		
	// 일주일 간 등록된 게시글 수  
	@Override
	public int regBoardCountforWeek() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		int regCnt = dao.regBoardCountforWeek(); 
		return regCnt;
	}
	
	// 일주일 간 예매된 수량
	@Override
	public int bookingCountforWeek() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		int bookCnt = dao.bookingCountforWeek(); 
		return bookCnt;
	}
	
	//인기 게시글 목록
	public List<BoardDTO> popularBoard() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		List<BoardDTO> list = dao.popularBoard(); 
		return list;
	}

	//일별 방문자수(일주일 단위)
	@Override
	public List<VisitDTO> visitCount() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		List<VisitDTO> visit = dao.visitCount(); 
		return visit;
	}
	
	//방문자 수 추가
	public void addVisit() {
		ChartDAO dao = sqlSession.getMapper(ChartDAO.class);
		dao.addVisit(); 
	}
	
	
}
