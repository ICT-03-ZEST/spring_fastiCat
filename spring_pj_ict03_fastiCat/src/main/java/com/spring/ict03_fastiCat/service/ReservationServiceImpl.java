package com.spring.ict03_fastiCat.service;

import java.io.IOException; 
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.CustomerDAO;
import com.spring.ict03_fastiCat.dao.ReservationDAO;
import com.spring.ict03_fastiCat.dto.ReservationDTO;
import com.spring.ict03_fastiCat.dto.ShowDTO;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationDAO dao;
	
	@Autowired
	private CustomerDAO cdao;
	
	@Override
	public void reservationListAction(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("ReservationServiceImpl - reservationListAction");

		// 현재 날짜를 가져옴
		LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
		// 파라미터에서 curMonth를 가져옴
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		String formattedMonth = currentDate.format(formatter);
		String curMonth = request.getParameter("curMonth");
		
		List<ShowDTO> list;
		if (curMonth == null) {
			System.out.println("curMonth : " + curMonth);
			System.out.println("formattedMonth : " + formattedMonth);
			// 선택된 월에 해당하는 공연 목록을 가져옴
			list = dao.ResList(formattedMonth);
		} else {
			System.out.println("curMonth : " + curMonth);
			// 선택된 월에 해당하는 공연 목록을 가져옴
			list = dao.ResList(curMonth);
		}
		
		// 6단계. jsp에 값 전달
		model.addAttribute("list", list);
		
	}

	@Override
	public void showTicketDetail(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - showTicketDetail");
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		Date sendShowDay = Date.valueOf(request.getParameter("sendShowDay"));

		ShowDTO dto = dao.ResDetailPageView(showNum);
		model.addAttribute("dto", dto);
		model.addAttribute("sendShowDay", sendShowDay);
		
	}

	@Override
	public void showTicketDetailList(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - showTicketDetailList");
		ShowDTO showdto = (ShowDTO) model.getAttribute("dto");
		System.out.println("showTicketDetailList-dto : "+showdto);
		List<ShowDTO> showList = dao.ResDetailPageViewList(showdto.getShowName());
		model.addAttribute("showList", showList);
	}

	@Override
	public void showTicketInsert(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - showTicketInsert");
		HttpSession session = request.getSession();
		// 세션에서 사용자 ID 가져오기
		String userID = (String) session.getAttribute("sessionID");
		
		// 요청 파라미터로부터 값 가져오기
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		Date sendShowDay = Date.valueOf(request.getParameter("sendShowDay"));
		String ticketQuantity = request.getParameter("ticketQuantity");

		ShowDTO dto = new ShowDTO();
		dto.setShowNum(showNum);
		dto.setShowDay(sendShowDay);
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("showNum", showNum);
	    params.put("quantity", quantity);
	    
	    Map<String, Object> params_ticketInsert = new HashMap<String, Object>();
	    params.put("dto", dto);
	    params.put("userID", userID);
	    params.put("totalPrice", totalPrice);
	    params.put("ticketQuantity", ticketQuantity);
	    
	    System.out.println("sessionID : " +userID);
	    System.out.println("showTicketInsert - params : "+params+", quantity : "+quantity+", totalPrice :"+totalPrice+",sendShowDay : "+sendShowDay);
	    System.out.println("showTicketInsert - ticketQuantity : "+ticketQuantity);
		System.out.println("showNum : "+showNum+", quantity : "+quantity);
		System.out.println("dto : "+dto+", userID : "+userID+", totalPrice :"+totalPrice+", ticketQuantity :"+ticketQuantity);
		// 사용자 비밀번호 확인 (null 체크)
		if (cdao.getpassword(userID) != null) {
			System.out.println("cdao.getpassword(userID) != null 진입");
			// 공연 정보 업데이트
			dao.showUpdate(params);
			System.out.println("dao.showUpdate(params);완료");
			// 티켓 삽입
//			dao.ticketInsert(params_ticketInsert);

			System.out.println("티켓 삽입 완료: dto=" + dto + ", userID=" + userID + ", totalPrice=" + totalPrice);

		} else {
			System.out.println("유효하지 않은 사용자 또는 비밀번호가 null입니다.");
		}
		model.addAttribute("showNum", dto.getShowNum());
		model.addAttribute("sendShowDay", dto.getShowDay());

	}

	//공연예매 (calenderDetail.js)
	@Override
	public void showReservation(HttpServletRequest request, Model model) throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - showReservation");
		
		int showNum = Integer.parseInt(request.getParameter("hiddenShowNum"));
		int amount = Integer.parseInt(request.getParameter("hiddenAmount"));
		int totalPrice = Integer.parseInt(request.getParameter("hiddenTotalPrice"));
		Date showDate = Date.valueOf(request.getParameter("hiddenReservation_date"));
		String showName = request.getParameter("hiddenShowName");
		String userID = (String)request.getSession().getAttribute("sessionID");
		
		
		System.out.println("showNum: " + showNum);
		System.out.println("amount: " + amount);
		System.out.println("totalPrice: " + totalPrice);
		System.out.println("showDate: " + showDate);
		System.out.println("showName: " + showName);
		System.out.println("userID: " + userID);
		
		ReservationDTO dto = new ReservationDTO();
		dto.setShowNum(showNum);
		dto.setAmount(amount);
		dto.setTotalPrice(totalPrice);
		dto.setReservation_date(showDate);
		dto.setShowName(showName);
		dto.setUserID(userID);
		
		int reservCnt = dao.addReservation(dto);
		model.addAttribute("reservCnt", reservCnt);
	}
	
	//1 인당 예매 2매 제한 (calender.js)
	public void showBuyChk(HttpServletRequest request, Model model) {
		System.out.println("CustomerServiceImpl - showBuyChk");
		String userID = (String)request.getSession().getAttribute("sessionID");
		String showName = request.getParameter("showName");
		
		System.out.println("userID: " + userID);
		System.out.println("showName: " + showName);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("showName", showName);
		
		int selectCnt = dao.selectById(map);
		System.out.println("selectCnt: " +  selectCnt);
		model.addAttribute("selectCnt", selectCnt);
	}
	
	
	
	
	
	
	

}
