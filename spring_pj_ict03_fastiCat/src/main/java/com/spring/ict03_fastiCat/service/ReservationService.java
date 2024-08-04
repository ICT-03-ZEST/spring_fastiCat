package com.spring.ict03_fastiCat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ReservationService {
	
	//달력날짜 예약 리스트
	public void reservationListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException; 
	
	//달력날짜 예약 : 상세페이지 리스트 정보 select
	public void showTicketDetail(HttpServletRequest request, Model model)
			throws ServletException, IOException; 

	//달력날짜 예약 : 상세페이지 리스트 정보 select
	public void showTicketDetailList(HttpServletRequest request,Model model)
			throws ServletException, IOException; 

	//달력날짜 예약 : 상세페이지 ticket 모달 구입페이지 insert
	public void showTicketInsert(HttpServletRequest request,Model model)
			throws ServletException, IOException; 


}
