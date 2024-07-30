package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.CommentDTO;
import com.spring.ict03_fastiCat.dto.HeartDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired 
	private SqlSession sqlSession;
	
	
	// 게시판 목록 조회 - 내림차순 조회
	@Override
	public List<BoardDTO> boardList(Map<String, Object> map) {
		System.out.println("BoardDAOImpl - boardList");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		List<BoardDTO> list = dao.boardList(map);
		
		return list;
	}
	
	// 게시글 개수 구하기 - 페이지계산시 필요
	@Override
	public int boardCnt(String category) {
		System.out.println("BoardDAOImpl - boardCnt");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int total = dao.boardCnt(category);
		return total;
	}
	
	// 게시글 상세페이지
	@Override
	public BoardDTO getBoardDetail(Map<String, Object> map) {
		System.out.println("BoardDAOImpl - getBoardDetail");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		BoardDTO dto = dao.getBoardDetail(map);
		return dto;
	}
	
	// 좋아요 조회
	public int selectHeart(HeartDTO heart) {
		System.out.println("BoardDAOImpl - selectHeart");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int selectCnt = dao.selectHeart(heart);
				
		return selectCnt;
	}
		
	//회원 게시글 이력조회
	public int selectOfwriter(BoardDTO dto) {
		System.out.println("dao - selectOfwriter");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int selWriter = dao.selectOfwriter(dto);
		return selWriter;
	}
	
	//조회수 증가
	@Override
	public void plusViews(Map<String, Object> map) {
		System.out.println("BoardDAOImpl - plusViews");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		dao.plusViews(map);
	}
	
	// 게시판 테이블 좋아요count 수정
	public int modHeartCount(BoardDTO dto) {
		System.out.println("BoardDAOImpl - modHeartCount");
		
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int updateCnt = dao.modHeartCount(dto);
		return updateCnt;
	}
	
	// 좋아요 클릭 => 하트 insert
	public int insertHeart(HeartDTO dto) {
		System.out.println("BoardDAOImpl - insertHeart");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int insertCnt = dao.insertHeart(dto);
		return insertCnt;
	}
		
	// 좋아요 취소 => 하트 지우기 
	public int delHeart(HeartDTO dto){
		System.out.println("dao - delHeart");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int deleteCnt = dao.delHeart(dto);
		return deleteCnt;
	}
	
	// 게시글 작성처리
	public int insertBoard(BoardDTO dto) {
		System.out.println("dao - insertBoard");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int insertCnt = dao.insertBoard(dto);
		return insertCnt;
	}
	
	// 게시글 수정처리
	public int updateBoard(BoardDTO dto) {
		System.out.println("dao - updateBoard");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int updateCnt = dao.updateBoard(dto);
		return updateCnt;
	}
	
	// 게시글 삭제처리
	public int deleteBoard(Map<String, Object> map) {
		System.out.println("dao - deleteBoard");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int updateCnt = dao.deleteBoard(map);
		return updateCnt;
	}
	
	// 댓글 목록조회
	@Override
	public List<CommentDTO> cmtList(Map<String, Object> map) {
		System.out.println("dao - cmtList");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		List<CommentDTO> list = dao.cmtList(map);
		return list;
	}

	// 댓글 작성처리
	@Override
	public int cmtInsert(CommentDTO dto) {
		System.out.println("BoardDAOImpl - cmtInsert");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int insertCnt = dao.cmtInsert(dto);
		return insertCnt;
	}
	
	// 댓글 한건 조회 - 댓글번호
	public CommentDTO cmtSelect(CommentDTO comment) {
		System.out.println("BoardDAOImpl - cmtSelect");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		CommentDTO dto = dao.cmtSelect(comment);
		return dto;
	}
	
	// 댓글 수정처리
	@Override
	public int cmtUpdate(CommentDTO dto) {
		System.out.println("dao - cmtUpdate");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int updateCnt = dao.cmtUpdate(dto);
		return updateCnt;
	}
	
	// 댓글 삭제처리
	@Override
	public int cmtDelete(Map<String, Object> map) {
		System.out.println("dao - delHeart");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int deleteCnt = dao.cmtDelete(map);
		return deleteCnt;
	}

}
