package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.NoticeDTO;

public interface NoticeBoardDAO {
	
	//공지사항 목록
	public List<NoticeDTO> noticeBoardList(Map<String, Object> map);
	//공지사항 갯수 구하기
	public int NoticeBoardCnt();
	//조회수 증가 
	public void plusNoticeReadCnt(int n_Board_Num);
	//공지사항 상세페이지
	public NoticeDTO getNoticeBoardDetail(int n_Board_Num);
	//공지사항 수정처리
	public int updateNoticeBoard(NoticeDTO dto);
	//공지사항 삭제처리
	public int deleteNoticeBoard(int n_Board_Num);
	//공지사항 작성
	public int insertNoticeBoard(NoticeDTO dto);
	
}
