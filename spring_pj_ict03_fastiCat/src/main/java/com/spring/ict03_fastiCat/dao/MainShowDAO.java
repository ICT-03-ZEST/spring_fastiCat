package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.ict03_fastiCat.dto.MainShowDTO;


public interface MainShowDAO {
	
	
	// 상품갯수
	public int showCnt(@Param("category") String category);
	
	// 페스티벌 목록
	public List<MainShowDTO> showList(Map<String, Object> map);
	
	// 페스티벌 상세페이지
	public MainShowDTO showInfo(Map<String, Object> map);
	
	
}
