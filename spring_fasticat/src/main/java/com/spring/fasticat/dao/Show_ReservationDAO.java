package com.spring.fasticat.dao;

import com.spring.fasticat.dto.ShowDTO;

public interface Show_ReservationDAO {
	
	// 공연에 구입자가 쓴 비용 Insert
	public void ticketInsert(ShowDTO dto, String userID, int totalPrice);

}
