package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.AdminNoticeDAO;
import com.spring.ict03_fastiCat.dto.NoticeDTO;
import com.spring.ict03_fastiCat.page.Paging;
@Service
public class AdminNoticeServiceImpl implements AdminNoticeService{
	
	@Autowired
	private AdminNoticeDAO dao;
	
	// 공지사항 등록
	@Override
	public void noticeAddAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeAddAction");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		NoticeDTO dto = new NoticeDTO();
		dto.setN_Title(request.getParameter("N_Title"));
		dto.setN_Content(request.getParameter("N_Content"));
		dto.setN_Writer(request.getParameter("N_Writer"));
	
		
		// 5단계. 상품정보 insert
		int insertCnt = dao.noticeInsert(dto);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("insertCnt", insertCnt);
	}
	
	// 공지사항 목록
	@Override
	public void noticeListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		
		//5-1단계. 공지사항갯수
		int total = dao.noticeCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공지사항목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
	
		
		List<NoticeDTO> list = dao.noticeList();
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
	}

	// 공지사항 (수정)상세 페이지
	@Override
	public void noticeDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeDetailAction()");
		
		int n_Board_Num = Integer.parseInt(request.getParameter("N_Board_Num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		
		// 5단계
		NoticeDTO dto = dao.getBoardDetail(n_Board_Num);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
	}

	// 공지사항 수정 처리
	@Override
	public void noticeUpdateAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeUpdateAction()");
		
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String boardNumParam = request.getParameter("hidden_num");
		System.out.println("boardNumParam : " + boardNumParam);
		int hidden_num = Integer.parseInt(boardNumParam);
		System.out.println("hidden_num : " + hidden_num);
		System.out.println("hiddenPageNum : " + hiddenPageNum);
		
		// DTO 생성 
		NoticeDTO dto = new NoticeDTO();
		dto.setN_Board_Num(hidden_num);
		dto.setN_Title(request.getParameter("N_Title"));
		dto.setN_Writer(request.getParameter("N_Writer"));
		dto.setN_Content(request.getParameter("N_Content"));
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.updateNotice(dto);
		
		//6단계.jsp로 처리결과 전달
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("hiddenPageNum", hiddenPageNum);
		model.addAttribute("hidden_num", hidden_num);
	}

	// 공지사항 삭제
	@Override
	public void noticeDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int n_Board_Num = Integer.parseInt(request.getParameter("N_Board_Num"));
		
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.deleteNotice(n_Board_Num);
		model.addAttribute("deleteCnt", deleteCnt);
		
	}

}
