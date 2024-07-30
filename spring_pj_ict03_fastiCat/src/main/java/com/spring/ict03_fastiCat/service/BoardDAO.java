package com.spring.ict03_fastiCat.service; 


import java.util.List; 
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.CommentDTO;
import com.spring.ict03_fastiCat.dto.HeartDTO;

public interface BoardDAO {

	// 게시판 목록 조회 - 내림차순 조회
	public List<BoardDTO> boardList(Map<String, Object> map);
	
	// 게시글 개수 구하기 - 페이지계산시 필요
	public int boardCnt(@Param("category")String category);
	
	//회원 게시글 이력조
	public int selectOfwriter(BoardDTO dto);
	
	//조회수 증가
	public void plusViews(Map<String, Object> map);

	// 게시글 상세페이지
	public BoardDTO getBoardDetail(Map<String, Object> map);
	
	// 좋아요 조회
	public int selectHeart(HeartDTO heart);
	
	// 좋아요 수 수정
	public int modHeartCount(BoardDTO dto);
	
	// 좋아요 추가 하트 insert
	public int insertHeart(HeartDTO dto);
	
	// 좋아요 취소 하트 지우기 
	public int delHeart(HeartDTO dto);
	
	// 게시글 작성처리
	public int insertBoard(BoardDTO dto);
	
	// 게시글 수정처리
	public int updateBoard(BoardDTO dto);
	
	// 게시글 삭제처리
	public int deleteBoard(Map<String, Object> map);
	
	// 댓글 목록조회
	public List<CommentDTO> cmtList(Map<String, Object> map);
	
	// 댓글 작성처리
	public int cmtInsert(CommentDTO dto);
	
	// 댓글 한건 조회 - 댓글번호
	public CommentDTO cmtSelect(CommentDTO dto);
		
	// 댓글 수정처리
	public int cmtUpdate(CommentDTO dto);
	
	// 댓글 삭제처리
	public int cmtDelete(Map<String, Object> map);

	
	
}
