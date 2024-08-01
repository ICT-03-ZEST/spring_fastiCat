package com.spring.fasticat.service;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.fasticat.dao.MyPageDAOImpl;
import com.spring.fasticat.dto.BoardDTO;
import com.spring.fasticat.dto.MyPageDTO;
import com.spring.fasticat.dto.MyReservationDTO;
import com.spring.fasticat.page.Paging;

@Service
public class MyPageServiceImpl implements MyPageService {
		
		@Autowired
		private MyPageDAOImpl dao;
		
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;// 비밀번호 암호화 클래스
		

		// 회원정보 인증처리 및 탈퇴처리
		@Override
		public void deleteUserAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException{
			
			System.out.println("서비스 - deleteUserAction()");
			
			// 3단계. 화면에서 입력받은 값을 가져오기
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			int deleteCnt = dao.deleteUser(strId);
			
			request.getSession().invalidate(); // 세션 삭제
			
			model.addAttribute("deleteCnt", deleteCnt);
			
		};
		
		
		
		// 회원정보 수정처리
		@Override
		public void modifyUserAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException{
			
			System.out.println("서비스 - modifyUserAction()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			// 3단계. 화면에서 입력받은 값을 가져와서 DTO의 setter로 값 전달
			// DTO 생성
			MyPageDTO dto = new MyPageDTO();
			 
			dto.setUserid(strId); //아이디
			
			// 시큐리티 - 비밀번호 암호화
			String password = request.getParameter("password");
			System.out.println("암호화 전의 비밀번호 : " + password);
			  
			String encriptPassword = bCryptPasswordEncoder.encode(password);
			System.out.println("암호화 후의 비밀번호 : " + encriptPassword);
			  
			dto.setPassword(encriptPassword); // 주의 !!! dto.setPassword(암호화된 비밀번호)
			
			
			dto.setUsername(request.getParameter("username")); //이름
			dto.setBirthday(Date.valueOf(request.getParameter("birthday"))); //생년월일
			dto.setAddress(request.getParameter("address")); //주소
			
			// hp은 필수가 아니므로 null값이 들어올 수 있으므로 값이 존재할 때만 받아온다.
			String hp = "";
			String hp1 = request.getParameter("hp1");
			String hp2 = request.getParameter("hp2");
			String hp3 = request.getParameter("hp3");
			if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
				hp  = hp1 + "-" + hp2 + "-" + hp3;
			}
			dto.setHp(hp);
			
			// 이메일
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email = email1 + "@" + email2;
			dto.setEmail(email); //이메일1
				
			
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			int updateCnt = dao.updateUser(dto);
			
			model.addAttribute("updateCnt", updateCnt);
			
		};
		
		// 게시글 목록 - 공연후기
		@Override
		public void boardListAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException {
			System.out.println("서비스 - boardListAction");
			
			// 3단계. 화면에서 입력받은 값을 가져오기
			String rbPageNum = request.getParameter("rbPageNum");
			String fbPageNum = request.getParameter("fbPageNum");
			String category = request.getParameter("category");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			Map<String, Object> rtMap = new HashMap<String, Object>();
			rtMap.put("strId", strId); //아이디
			rtMap.put("table", "REVIEWBOARD_TBL"); //비밀번호
			
			Map<String, Object> fbMap = new HashMap<String, Object>();
			fbMap.put("strId", strId); //아이디
			fbMap.put("table", "FREEBOARD_TBL"); //비밀번호
			
			// 5-1단계. 전체 게시글 갯수 카운트
			Paging rbPaging = new Paging(rbPageNum);
			Paging fbPaging = new Paging(fbPageNum);
			int rtotal = dao.myBoardCnt(rtMap);
			int ftotal = dao.myBoardCnt(fbMap);
			System.out.println("review_total => " + rtotal);
			System.out.println("free_total => " + ftotal);
			
			rbPaging.setTotalCount(rtotal);
			fbPaging.setTotalCount(ftotal);
			
			// 5-2단계. 게시글 목록 조회
			int rbStart = rbPaging.getStartRow();
			int rbEnd = rbPaging.getEndRow();
			
			int fbStart = fbPaging.getStartRow();
			int fbEnd = fbPaging.getEndRow();
			
			rtMap.put("start", rbStart); //비밀번호
			rtMap.put("end", rbEnd); //비밀번호
			
			System.out.println("rtMap : " + rtMap);
			List<BoardDTO> rbList = dao.myBoardList(rtMap);
			System.out.println("rbList : " + rbList);
			
			fbMap.put("start", fbStart); //비밀번호
			fbMap.put("end", fbEnd); //비밀번호
			
			System.out.println("fbMap : " + fbMap);
			List<BoardDTO> fbList = dao.myBoardList(fbMap);
			System.out.println("fbList : " + fbList);
			
			// 6단계. jsp로 처리결과 전달
			model.addAttribute("rbList", rbList);
			model.addAttribute("rbPaging", rbPaging);
			
			model.addAttribute("fbList", fbList);
			model.addAttribute("fbPaging", fbPaging);
			
			if(category == "" || category == null) {
				category = "공연후기";
			}
			
			model.addAttribute("category", category);
		}
		

		
		// 게시물 삭제 처리
		@Override
		public void BoardDeleteAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException{
				System.out.println("서비스 - BoardDeleteAction()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			String[] numList = request.getParameterValues("num_list");
			String category = request.getParameter("category");
			
			
			System.out.println("numList : " + numList);
			System.out.println("category : " + category);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("strId", strId); //아이디
			map.put("numList", numList); 
			map.put("category", category); 
			
			// 5단계. 중복확인 처리
			int deleteCnt = dao.boardDelete(map);
			
			// 6단계. jsp로 처리결과 전달
			model.addAttribute("deleteCnt", deleteCnt);
			
		};
		
		// 게시글 목록 - 공연후기
		@Override
		public void reservationListAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException {
			System.out.println("서비스 - reservationListAction");
			
			// 3단계. 화면에서 입력받은 값을 가져오기
			String pageNum = request.getParameter("pageNum");

			String strId = (String) request.getSession().getAttribute("sessionID");
			System.out.println("strId :" + strId);
			
			// 5-1단계. 전체 게시글 갯수 카운트
			Paging paging = new Paging(pageNum);
			int total = dao.resBoardCnt(strId);
			System.out.println("SHOW_RESERVATION => " + total);
			
			paging.setTotalCount(total);
			
			// 5-2단계. 게시글 목록 조회
			int start = paging.getStartRow();
			int end = paging.getEndRow();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("strId", strId); //아이디
			map.put("start", start); //비밀번호
			map.put("end", end); //비밀번호
			
			List<MyReservationDTO> list = dao.resBoardList(map);
			System.out.println("list : " + list);
			
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
		}
		
		//예매 취소 처리
		@Override
		public void reservationCancelAction(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException{
				System.out.println("서비스 - reservationCancelAction()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			String resNum = request.getParameter("resNum");
		
			System.out.println("resNum : " + resNum);
			System.out.println("strId : " + strId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("strId", strId); //아이디
			map.put("resNum", resNum); //비밀번호
						
			// 5단계. 중복확인 처리
			int deleteCnt = dao.resDelete(map);
			
			// 6단계. jsp로 처리결과 전달
			model.addAttribute("deleteCnt", deleteCnt);
			
		};
		
		// 비밀번호 확인
		// 회원정보 인증처리 및 상세페이지
		@Override
		public void pwdChk(HttpServletRequest request, HttpServletResponse response, Model model)
				throws ServletException, IOException{
				System.out.println("서비스 - pwdChk()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			String strPwd = request.getParameter("password");
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("strId", strId); //아이디
			map.put("strPwd", strPwd); //비밀번호

			// 5단계. 중복확인 처리
			int selectCnt = dao.idPasswordChk(map);
			System.out.println("selectCnt : " + selectCnt);
			
			// 6단계. jsp로 처리결과 전달
			model.addAttribute("selectCnt", selectCnt);
			
			if(selectCnt == 1) {
				MyPageDTO dto = dao.getUserDetail(strId);
				model.addAttribute("dto", dto);
			}
		};
		
}
