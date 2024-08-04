package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.ShowDTO;
@Repository
public class AdminShowDAOImpl implements AdminShowDAO{
	
	@Autowired 
	private SqlSession sqlSession;
	
	
	// 공연, 페스티벌 등록
	@Override
	public int showInsert(Map<String, Object> map) {
		System.out.println("AdminshowDAOImpl - showInsert()");
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
	    int insertCnt = dao.showInsert(map);
	    System.out.println("insertCnt : " + insertCnt);
	    return insertCnt;
	}
	
	//공연, 페스티벌 갯수
	@Override
	public int showCnt(Map<String, Object> params) {
		System.out.println("AdminshowDAOImpl - showCnt() ");
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
		int total = dao.showCnt(params);
		System.out.println("total : " + total);
		return total;
	}
	
	//공연, 페스티벌 목록
	@Override
	public List<ShowDTO> showList(Map<String, Object> params) {
		System.out.println("AdminshowDAOImpl - showList() ");
		System.out.println("Params: " + params );
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
		List<ShowDTO> list = dao.showList(params);
		return list;
	}
	
	//공연, 페스티벌 수정 상세페이지
	@Override
	public ShowDTO showDetail(Map<String, Object> map) {
		System.out.println("DAO - productDetail()");
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
		ShowDTO dto = dao.showDetail(map);
		System.out.println("dto" + dto);
		return dto;
	}
	
	//공연, 페스티벌 수정
	@Override
	public int showUpdate(Map<String, Object> map) {
		System.out.println("AdminshowDAOImpl - showUpdate()");
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
		int updateCnt = dao.showUpdate(map);
		System.out.println("updateCnt" + updateCnt);
		return updateCnt;
	}
	
	//공연, 페스티벌 삭제
	@Override
	public int showDelete(Map<String, Object> map) {
		System.out.println("DAO - showDelete ");
		AdminShowDAO dao = sqlSession.getMapper(AdminShowDAO.class);
		int deleteCnt = dao.showDelete(map);
		System.out.println("deleteCnt" + deleteCnt);
		return deleteCnt;
	}


}
