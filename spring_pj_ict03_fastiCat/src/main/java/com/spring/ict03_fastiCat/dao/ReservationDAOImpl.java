package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.ShowDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ShowDTO> ResList(String curMonth) {
		System.out.println("ReservationDAOImpl - ResList");
		List<ShowDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.ReservationDAO.ResList",curMonth);
		System.out.println("list : "+list);
		return list;
	}

	@Override
	public ShowDTO ResDetailPageView(int showNum) {
		System.out.println("ReservationDAOImpl - ResDetailPageView//showNum : "+showNum);
		ShowDTO dto = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.ReservationDAO.ResDetailPageView",showNum);
		System.out.println("dto : "+dto);
		return dto;
	}

	@Override
	public List<ShowDTO> ResDetailPageViewList(String ShowName) {
		System.out.println("ReservationDAOImpl - ResDetailPageViewList");
		List<ShowDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.ReservationDAO.ResDetailPageViewList",ShowName);
		System.out.println("list : "+list);
		return list;
	}

	@Override
	public void showUpdate(Map<String, Object> params) {
		System.out.println("ReservationDAOImpl - showUpdate // params : "+params);
//		System.out.println("params.quantity : "+params.get("quantity"));
//		System.out.println("params.showNum : "+params.get("showNum"));
		sqlSession.update("com.spring.ict03_fastiCat.dao.ReservationDAO.showUpdate",params);
	}
	
	@Override
	public int ticketInsert(Map<String, Object> params_ticketInsert) {
		System.out.println("Show_ReservationDAOImpl - ticketInsert");
	      
		int insertCnt = sqlSession.insert("com.spring.ict03_fastiCat.dao.Show_ReservationDAO.ticketInsert", params_ticketInsert);
		
		System.out.println("insertCnt : "+insertCnt);
		return insertCnt;
	}

}
