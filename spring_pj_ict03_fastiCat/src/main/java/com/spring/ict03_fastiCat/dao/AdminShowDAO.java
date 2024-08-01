package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.ShowDTO;

public interface AdminShowDAO {

	// 공연, 페스티벌 등록
	public int showInsert(Map<String, Object> map);
	
	// 공연, 페스티벌 갯수
	public int showCnt(Map<String, Object> params);
	
	// 공연, 페스티벌 목록 조회
	public List<ShowDTO> showList(Map<String, Object> params);
	
	// 공연, 페스티벌 페이지
	public ShowDTO showDetail(Map<String, Object> map);
	
	// 공연, 페스티벌 수정
	public int showUpdate(Map<String, Object> map);
	
	// 공연, 페스티벌 삭제
	public int showDelete(Map<String, Object> map);
	

}
