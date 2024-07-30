package com.spring.ict03_fastiCat.service;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.MainShowDTO;

@Repository
public class MainShowDAOImpl implements MainShowDAO {
	
	@Autowired
	private SqlSession sqlSession; 
	
	// show 갯수
	@Override
	public int showCnt(String category) {
		System.out.println("DAO - showCnt() ");
		MainShowDAO dao = sqlSession.getMapper(MainShowDAO.class);
		int total = dao.showCnt(category);
		return total;
	}
	
	// 메인 - 페스티벌 목록 내림차순 정렬
	@Override
	public List<MainShowDTO> showList(Map<String, Object> map) {
		System.out.println("DAO - showList() ");
		MainShowDAO dao = sqlSession.getMapper(MainShowDAO.class);
		List<MainShowDTO> list = dao.showList(map);
		return list;
	}

	// 페스티벌 상세페이지
	@Override
	public MainShowDTO showInfo(Map<String, Object> map) {
		System.out.println("DAO - showInfo() ");
		MainShowDAO dao = sqlSession.getMapper(MainShowDAO.class);
		MainShowDTO dto = dao.showInfo(map);
		return dto;
	}


}
