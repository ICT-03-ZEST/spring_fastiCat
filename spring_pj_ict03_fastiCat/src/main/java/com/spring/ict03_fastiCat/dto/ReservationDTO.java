package com.spring.ict03_fastiCat.dto;

import java.sql.Date;

public class ReservationDTO {
   
   private int show_ResId;
   private int showNum;
   private String userID;
   private int totalPrice;
   private Date reservation_date;
   private String reservation_check;
   private Date reservation_dateNow;
   private int reserv_count;
   
   public ReservationDTO() {}

	public ReservationDTO(int show_ResId, int showNum, String userID, int totalPrice, Date reservation_date,
			String reservation_check, Date reservation_dateNow, int reserv_count) {
		super();
		this.show_ResId = show_ResId;
		this.showNum = showNum;
		this.userID = userID;
		this.totalPrice = totalPrice;
		this.reservation_date = reservation_date;
		this.reservation_check = reservation_check;
		this.reservation_dateNow = reservation_dateNow;
		this.reserv_count = reserv_count;
	}
	
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
	
	public String getReservation_check() {
		return reservation_check;
	}
	
	public void setReservation_check(String reservation_check) {
		this.reservation_check = reservation_check;
	}
	
	public Date getReservation_dateNow() {
		return reservation_dateNow;
	}
	
	public void setReservation_dateNow(Date reservation_dateNow) {
		this.reservation_dateNow = reservation_dateNow;
	}
	
	public int getReserv_count() {
		return reserv_count;
	}
	
	public void setReserv_count(int reserv_count) {
		this.reserv_count = reserv_count;
	}
	
	@Override
	public String toString() {
		return "ReservationDTO [show_ResId=" + show_ResId + ", showNum=" + showNum + ", userID=" + userID + ", totalPrice="
				+ totalPrice + ", reservation_date=" + reservation_date + ", reservation_check=" + reservation_check
				+ ", reservation_dateNow=" + reservation_dateNow + ", reserv_count=" + reserv_count + "]";
	}
	   

   
	
   
   

}
