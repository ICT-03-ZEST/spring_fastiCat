package com.spring.ict03_fastiCat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.NoticeDTO;

@Repository
public class AdminNoticeDAOImpl implements AdminNoticeDAO{

	@Autowired
	private SqlSession sqlSession;
	

	// 공지사항 등록
	@Override
	public int noticeInsert(NoticeDTO dto) {
		System.out.println("AdminNoticeDAOImpl - noticeInsert()");
		int insertCnt = sqlSession.insert("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.noticeInsert", dto);
		System.out.println("insertCnt: " + insertCnt);
		return insertCnt;
	}

	// 공지사항 갯수 구하기
	@Override
	public int noticeCnt() {
		System.out.println("AdminNoticeDAOImpl - noticeCnt()");
		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.noticeCnt");
		System.out.println("total: " + total);
		return total;
	}

	// 공지사항 목록
	@Override
	public List<NoticeDTO> noticeList() {
		System.out.println("AdminNoticeDAOImpl - noticeList() ");
		
		List<NoticeDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.noticeList");
		return list;
	}

	// 공지사항 상세 페이지
	@Override
	public NoticeDTO getBoardDetail(int N_Board_Num) {
		System.out.println("DAO - getBoardDetail()");
		
		// DTO 생성
		NoticeDTO dto = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.getBoardDetail", N_Board_Num);
	
		return dto;
	}

	// 공지사항 수정
	@Override
	public int updateNotice(NoticeDTO dto) {
		System.out.println("DAO - updateNotice");
		int updateCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.updateNotice", dto);
		System.out.println("updateCnt" + updateCnt);
		return updateCnt;
	}

	// 공지사항 삭제 
	@Override
	public int deleteNotice(int N_Board_Num) {
		System.out.println("DAO - deleteNotice ");
		int deleteCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.AdminNoticeDAO.deleteNotice", N_Board_Num);
		System.out.println("deleteCnt" + deleteCnt);
		return deleteCnt;
	}

}
