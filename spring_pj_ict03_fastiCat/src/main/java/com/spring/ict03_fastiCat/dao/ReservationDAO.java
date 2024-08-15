package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import com.spring.ict03_fastiCat.dto.ReservationDTO;
import com.spring.ict03_fastiCat.dto.ShowDTO;

public interface ReservationDAO {
	
	// 선택 날짜에 있는 공연리스트			
	public List<ShowDTO> ResList(String curMonth);
	
	// 선택한 날짜에 있는 공연정보
	public ShowDTO ResDetailPageView(int showNum);

	// 선택한 날짜에 있는 공연정보
	public List<ShowDTO> ResDetailPageViewList(String showdto);

	// 선택한 날짜에 있는 공연정보
	public void showUpdate( Map<String, Object> params);
	
	// 공연에 구입자가 쓴 비용 Insert
	public int ticketInsert(Map<String, Object> params_ticketInsert);

	//공연예매
	public int addReservation(ReservationDTO dto);
	
	//1 인당 예매 2매 제한
	public int selectById(Map<String, Object> map);


}
