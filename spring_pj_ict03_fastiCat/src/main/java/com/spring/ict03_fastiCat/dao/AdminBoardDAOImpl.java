package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.AdminBoardDTO;
@Repository
public class AdminBoardDAOImpl implements AdminBoardDAO{

	@Autowired 
	private SqlSession sqlSession;
	
	// 공연후기,자유게시판 목록 조회
	@Override
	public List<AdminBoardDTO> boardList(Map<String, Object> params) {
		System.out.println("DAO - boardList");
		System.out.println("Params: " + params );
		AdminBoardDAO dao = sqlSession.getMapper(AdminBoardDAO.class);
		List<AdminBoardDTO> list = dao.boardList(params);
		return list;
	}

	// 공연후기,자유게시판 게시글 개수 구하기
	@Override
	public int boardCnt(Map<String, Object> params) {
		System.out.println(" DAO - boardCnt");
		AdminBoardDAO dao = sqlSession.getMapper(AdminBoardDAO.class);
		int total = dao.boardCnt(params);
		System.out.println("total : " + total);
		return total;
	}

	// 공연후기, 자유게시판 게시글 삭제
	@Override
	public int boardDelete(Map<String, Object> map) {
		System.out.println("DAO - boardDelete");
		AdminBoardDAO dao = sqlSession.getMapper(AdminBoardDAO.class);
		int deleteCnt = dao.boardDelete(map);
		System.out.println("deleteCnt : " + deleteCnt);
		return deleteCnt;
	}


}
