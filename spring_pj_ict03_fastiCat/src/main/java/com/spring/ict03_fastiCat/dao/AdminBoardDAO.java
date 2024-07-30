package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.AdminBoardDTO;

public interface AdminBoardDAO {
	
	// 공연후기,자유게시판 목록 조회
	public List<AdminBoardDTO> boardList(Map<String, Object> params);
	
	// 게시판 갯수
	public int boardCnt(Map<String, Object> params);
	
	// 공연후기,자유게시판 삭제
	public int boardDelete(Map<String, Object> map);
	
	

}
