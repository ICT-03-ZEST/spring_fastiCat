package com.spring.ict03_fastiCat.dto;

import java.sql.Date;

public class test_ShowReservationDTO {
	
	private int show_ResId;          
	private int showNum;           
	private String userID;           
	private int totalPrice;       
	private Date reservation_date;    
	private int reserv_count;
	
	public int getShow_ResId() {
		return show_ResId;
	}
	public void setShow_ResId(int show_ResId) {
		this.show_ResId = show_ResId;
	}
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getReserv_count() {
		return reserv_count;
	}
	public void setReserv_count(int reserv_count) {
		this.reserv_count = reserv_count;
	}
	
	
	
}
