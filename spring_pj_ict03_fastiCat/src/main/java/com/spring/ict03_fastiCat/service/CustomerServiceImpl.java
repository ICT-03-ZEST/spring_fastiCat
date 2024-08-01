package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.CustomerDAO;
import com.spring.ict03_fastiCat.dto.CustomerDTO;
import com.spring.ict03_fastiCat.page.Paging;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO dao;
	
   	// 관리자 - 회원목록 조회
	@Override
	public void memberListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - memberListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		//5-1단계. 회원 수
		int total = dao.memberCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 회원목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		List<CustomerDTO> list = dao.memberList();
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
				
	}

	// 관리자 - 회원 삭제
	@Override
	public void memberDeleteAction(HttpServletRequest request, Model model) 
			throws ServletException, IOException {
		
		System.out.println("서비스 - memberDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		String userid = request.getParameter("userid");
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.deleteMember(userid);
		request.setAttribute("deleteCnt", deleteCnt);
		
	}

	// 관리자 - 탈퇴 회원목록 조회
	@Override
	public void dropMemberListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("서비스 - dropMemberListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		//5-1단계. 탈퇴회원 수
		int total = dao.dropMemberCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 탈퇴회원목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		List<CustomerDTO> list = dao.dropMemberList();
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
	}

	// 관리자 - 탈퇴회원 복구
	@Override
	public void dropMemberRestoreAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("서비스 - memberDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		String userid = request.getParameter("userid");
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.dropMemberRestore(userid);
		request.setAttribute("updateCnt", updateCnt);
	}
	
}
