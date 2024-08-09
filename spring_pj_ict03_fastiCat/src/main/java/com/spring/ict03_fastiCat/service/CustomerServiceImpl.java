package com.spring.ict03_fastiCat.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.CustomerDAO;
import com.spring.ict03_fastiCat.dto.CustomerDTO;
import com.spring.ict03_fastiCat.page.Paging;
import com.spring.ict03_fastiCat.util.EmailChkHandler;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;// 비밀번호 암호화 클래스
	
	// ID 중복확인 처리
	@Override
	public void idConfirmAction(HttpServletRequest request, Model model) throws ServletException, IOException {

		System.out.println("서비스 - idConfirmAction()");

		// 3단계. 화면에서 입력받은 값을 가져오기
		String strId = request.getParameter("userid");

		// 5단계. 중복확인 처리
		int selectCnt = dao.useridCheck(strId);

		// 6단계. jsp로 처리결과 전달
		model.addAttribute("strId", strId); // key, value
		model.addAttribute("selectCnt", selectCnt);
	}

	// 회원가입 처리
	@Override
	public void signInAction(HttpServletRequest request, Model model) throws ServletException, IOException {

		System.out.println("서비스 - signInAction()");

		// 3단계. 화면에서 입력받은 값을 가져와서 DTO의 setter로 값 전달
		// DTO 생성
		CustomerDTO dto = new CustomerDTO();

		// String strId = request.getParameter("userid");
		// dto.setUserid(strId);

		dto.setUserid(request.getParameter("userid"));
		
		// 시큐리티 - 비밀번호 암호화
		String password = request.getParameter("password");
		System.out.println("암호화 전의 비밀번호 : " + password);
		
		String encriptPassword = bCryptPasswordEncoder.encode(password);
	    System.out.println("암호화 후의 비밀번호 : " + encriptPassword);
	    
	    dto.setPassword(encriptPassword); // 주의 !!! dto.setPassword(암호화된 비밀번호)
		
	    dto.setUsername(request.getParameter("username"));
		dto.setBirthday(Date.valueOf(request.getParameter("birthday")));
		dto.setAddress(request.getParameter("address"));

		// hp은 필수가 아니므로 null값이 들어올수 있으므로 값이 존재할 떄만 받아온다.(010-1111-2222)
		String hp = "";
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");
		if (!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		dto.setHp(hp);

		// 이메일
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		dto.setEmail(email);
		
		// 시큐리티 - 이메일인증키 추가(랜덤발생)
	    String key = EmailChkHandler.getKey();
	    dto.setKey(key);

		// 등록일
		dto.setRegDate(new Timestamp(System.currentTimeMillis()));

		// 5단계. 회원가입 처리
		int insertCnt = dao.insertCustomer(dto);
		System.out.println("insertCnt : " + insertCnt);
	      
		// 시큐리티 - 회원가입시 이메일 인증
		if(insertCnt == 1) {
			dao.sendEmail(email, key);
		}

		// 6단계. jsp로 처리결과 전달
		model.addAttribute("insertCnt", insertCnt);

	}
	
	// 시큐리티 - 메일 인증후 권한(enabled) update
   public void emailChkAction(HttpServletRequest request, Model model) 
         throws ServletException, IOException {
      
      System.out.println("서비스 - emailChkAction");
      
      String key = request.getParameter("key");
      int selectCnt = dao.selectKey(key);
      
      int insertCnt = 0;
      if(selectCnt == 1) {
         insertCnt = dao.updateGrade(key);  // enabled를 1로 update
         System.out.println("emailChkAction - insertCnt : " + insertCnt);
      }
      model.addAttribute("insertCnt", insertCnt);
   }

	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
//	@Override
//	public void loginAction(HttpServletRequest request, Model model) throws ServletException, IOException {
//
//		System.out.println("서비스 - loginAction");
//
//		// 3단계. 화면에서 입력받은 값을 가져오기
//		String strId = request.getParameter("userid");
//		String strPassword = request.getParameter("password");
//
//		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("strId", strId);
//		map.put("strPassword", strPassword);
//
//		int selectCnt = dao.idPasswordChk(map);
//		// 로그인 성공시 세션ID를 설정
//		if (selectCnt == 1) {
//			// 2줄표현
//			HttpSession session = request.getSession();
//			session.setAttribute("sessionID", strId);
//
//			// 1줄표현
//			model.addAttribute("selectCnt", selectCnt);
//		}
//	}

	// 관리자 - 회원목록 조회
	@Override
	public void memberListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("서비스 - memberListAction()");

		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);

		// 5-1단계. 회원 수
		int total = dao.memberCnt();
		System.out.println("total : " + total);

		// 5-2단계. 회원목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);

		List<CustomerDTO> list = dao.memberList();

		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);

	}

	// 관리자 - 회원 삭제
	@Override
	public void memberDeleteAction(HttpServletRequest request, Model model) throws ServletException, IOException {

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

		// 5-1단계. 탈퇴회원 수
		int total = dao.dropMemberCnt();
		System.out.println("total : " + total);

		// 5-2단계. 탈퇴회원목록
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
