package com.spring.ict03_fastiCat.dao;


import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.SearchDTO;


public interface SearchDAO {

	// 게시글 목록
	public List<SearchDTO> boardList(Map<String,Object> map);
	
	// 공연갯수
	public int getTotalCount(String query);
	
	// 공연목록
	public List<SearchDTO> concertList(Map<String,Object> map);

	// 게시글 개수 구하기
	public int boardCnt(String query);
	
	// 게시글 상세 목록
	public List<SearchDTO> boardDetailList(Map<String,Object> map);
	

}
