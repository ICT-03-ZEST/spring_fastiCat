package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.SearchDTO;

@Repository
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	private SqlSession sqlSession;

	// 게시글 목록
	@Override
	public List<SearchDTO> boardList(Map<String, Object> map) {
		System.out.println("SearchDAOImpl - boardList");
		List<SearchDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.SearchDAO.boardList", map);
		return list;
	}

	// 게시글 갯수 구하기
	@Override
	public int boardCnt(String query) {
		System.out.println("SearchDAOImpl - boardCnt");
		return sqlSession.selectOne("com.spring.ict03_fastiCat.dao.SearchDAO.boardCnt", query);
	}

	// 게시글 상세 검색
	@Override
	public List<SearchDTO> boardDetailList(Map<String, Object> map) {
		System.out.println("SearchDAOImpl - boardDetailList");
		List<SearchDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.SearchDAO.boardDetailList",
				map);
		return list;
	}

	// 공연 갯수
	@Override
	public int getTotalCount(String query) {
		System.out.println("SearchDAOImpl - getTotalCount");
		return sqlSession.selectOne("com.spring.ict03_fastiCat.dao.SearchDAO.getTotalCount", query);
	}

	// 공연 검색
	@Override
	public List<SearchDTO> concertList(Map<String, Object> map) {
		System.out.println("SearchDAOImpl - concertList");
		List<SearchDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.SearchDAO.concertList",
				map);

		return list;
	}
}
