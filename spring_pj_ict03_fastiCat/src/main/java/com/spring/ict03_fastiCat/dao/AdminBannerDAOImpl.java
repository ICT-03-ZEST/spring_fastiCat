package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.AdminBannerDTO;

@Repository
public class AdminBannerDAOImpl implements AdminBannerDAO{

	@Autowired 
	private SqlSession sqlSession;
	
	// 배너 등록
	@Override
	public int bannerInsert(AdminBannerDTO dto) {
		System.out.println("AdminBannerDAOImpl - bannerInsert()");
		
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		int insertCnt = dao.bannerInsert(dto);
		System.out.println("insertCnt" + insertCnt);
		return insertCnt;	
	}

	
	// 배너 갯수
	@Override
	public int bannerCnt() {
		System.out.println("AdminBannerDAOImpl - bannerCnt() ");
		
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		int total = dao.bannerCnt();
		return total;
	}

	// 배너 목록
	@Override
	public List<AdminBannerDTO> bannerList(Map<String, Object> map) {
		System.out.println("AdminBannerDAOImpl - bannerList()");
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		List<AdminBannerDTO> list = dao.bannerList(map);
		
		return list;
	}

	// 배너 수정 상세페이지
	@Override
	public AdminBannerDTO bannerDetail(int bannerNo) {
		System.out.println("DAO - bannerDetail()");
		
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		AdminBannerDTO dto = dao.bannerDetail(bannerNo);
		System.out.println("dto : " + dto);
		return dto;
	}

	// 배너 수정
	@Override
	public int bannerUpdate(AdminBannerDTO dto) {
		System.out.println("AdminConcertDAOImpl - concertUpdate()");
		
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		int updateCnt = dao.bannerUpdate(dto);
		System.out.println("updateCnt : " + updateCnt);
		return updateCnt;
	}

	// 배너 삭제
	@Override
	public int bannerDelete(int bannerNo) {
		System.out.println("DAO - bannerDelete");
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		int deleteCnt = dao.bannerDelete(bannerNo);
		System.out.println("deleteCnt : " + deleteCnt);
		return deleteCnt;
	}

	
	//  메인 - 배너 조회
	@Override
	public List<AdminBannerDTO> getBannerList() {
		System.out.println("AdminBannerDAOImpl - bannerList()");
		AdminBannerDAO dao = sqlSession.getMapper(AdminBannerDAO.class);
		List<AdminBannerDTO> bannerList = dao.getBannerList();
		return bannerList;
	}
}
