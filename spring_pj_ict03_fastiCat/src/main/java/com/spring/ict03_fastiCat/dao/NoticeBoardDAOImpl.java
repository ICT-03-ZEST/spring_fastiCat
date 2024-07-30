package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.NoticeDTO;

@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO{
	
	@Autowired
	private SqlSession sqlSession;

	//공지사항 목록
	@Override
	public List<NoticeDTO> noticeBoardList(Map<String, Object> map) {
		System.out.println("DAO-noticeBoardList");
		
		List<NoticeDTO> list
		 = sqlSession.selectList("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.noticeBoardList", map);
		
		return list;
	}	
	
	
	//공지사항 갯수 구하기
	@Override
	public int NoticeBoardCnt() {
		System.out.println("DAO-NoticeBoardCnt");

		int total
		 = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.NoticeBoardCnt");

			return total;
		}

	//공지사항 조회수 증가
	@Override
	public void plusNoticeReadCnt(int n_Board_Num) {
		System.out.println("DAO-plusNoticeReadCnt");
		
		sqlSession.update("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.plusNoticeReadCnt",n_Board_Num);
	}
		

	
	//공지사항 상세페이지
	@Override
	public NoticeDTO getNoticeBoardDetail(int n_Board_Num) {
		System.out.println("DAO-getNoticeBoardDetail");

		NoticeDTO dto 
		 = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.getNoticeBoardDetail",n_Board_Num);
		
		return dto;
	}	


	
	//공지사항 수정처리
	@Override
	public int updateNoticeBoard(NoticeDTO dto) {
		System.out.println("DAO - updateNoticeBoard");
	
		int updateCnt 
		 = sqlSession.update("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.updateNoticeBoard",dto);

		return updateCnt;
	}	
	
	
	//공지사항 삭제처리
	@Override
	public int deleteNoticeBoard(int n_Board_Num) {
		System.out.println("DAO - deleteNoticeBoard");
		
		int deleteCnt
		  = sqlSession.update("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.deleteNoticeBoard", n_Board_Num);

		return deleteCnt;
	}
		
	
	//공지사항 작성처리
		@Override
		public int insertNoticeBoard(NoticeDTO dto) {
			System.out.println("DAO - insertNoticeBoard");

		      int insertCnt
		       = sqlSession.insert("com.spring.ict03_fastiCat.dao.NoticeBoardDAO.insertNoticeBoard", dto);

			return insertCnt;
		}
	


}
