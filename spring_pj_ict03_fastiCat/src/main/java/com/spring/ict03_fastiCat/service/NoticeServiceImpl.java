package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.NoticeBoardDAO;
import com.spring.ict03_fastiCat.dto.NoticeDTO;
import com.spring.ict03_fastiCat.page.NoticePaging;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeBoardDAO dao;		//다형성 적용  NoticeBoardDAOImpl dao
	
	//공지사항 목록
	@Override
	public void NoticeBoardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoardListAction");
		
		//화면에서 입력받은 값을 가져옴
		String N_pageNum = (request.getParameter("pageNum") != null) ? request.getParameter("pageNum") : "1";
		N_pageNum  = request.getParameter("pageNum");	//클릭한 페이지값
		
		//전체 공지글 갯수를 카운트
		NoticePaging paging = new NoticePaging(N_pageNum);
		int total = dao.NoticeBoardCnt();
		System.out.println("total : " + total);
		
		paging.setTotalCount(total);
		
		//전체 공지글 목록 조회
		int Start = paging.getStartRow();
		int end = paging.getEndRow();
		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("Start", Start);
		map.put("end", end);
		
		List<NoticeDTO> list= dao.noticeBoardList(map);
		
		//JSP로 처리결과를 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
		
	}

	//공지사항 상세페이지
	@Override
	public void NoticeBoardDetail_Action(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoardDetail_Action");
		
		//화면에서 입력받은 값을 가져온다.
		int num =Integer.parseInt(request.getParameter("n_Board_Num"));
		
		//조회수 증가
		dao.plusNoticeReadCnt(num);
		//공지사항 상세페이지
		NoticeDTO dto = dao.getNoticeBoardDetail(num);
		//jsp로 값 전달
		model.addAttribute("dto", dto);
	}

	//공지사항 수정처리 
	@Override
	public void NoticeBoard_UpdateAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - NoticeBoard_UpdateAction");
		
		//DTO 객체 생성
		NoticeDTO dto = new NoticeDTO();
		//화면에서 입력받은 값을 dto에 담기
		dto.setN_Board_Num(Integer.parseInt(request.getParameter("hidden_num")));
		dto.setN_Title(request.getParameter("title"));
		dto.setN_Content(request.getParameter("content"));
		

		//공지사항 수정처리
		dao.updateNoticeBoard(dto);
		//jsp로 값 전달
		model.addAttribute("dto", dto);
	}
	
	//공지사항 삭제 처리 
	@Override
	public void NoticeBoard_DeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoard_DeleteAction");

		dao.deleteNoticeBoard(Integer.parseInt(request.getParameter("n_Board_Num")));
	}

	//공지사항 입력 처리
	@Override
	public void NoticeBoard_InsertAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoard_InsertAction");
		
		System.out.println(request.getParameter("n_Title"));
		System.out.println(request.getParameter("n_Content"));
		System.out.println(request.getParameter("n_Writer"));
		//화면에서 입력받은 값 가져와서 DTO에 담기
		NoticeDTO dto = new NoticeDTO();
		dto.setN_Title(request.getParameter("n_Title"));
		dto.setN_Content(request.getParameter("n_Content"));
		dto.setN_Writer(request.getParameter("n_Writer"));
		
		
		dao.insertNoticeBoard(dto);
		
		model.addAttribute("dto", dto);
	}

}
