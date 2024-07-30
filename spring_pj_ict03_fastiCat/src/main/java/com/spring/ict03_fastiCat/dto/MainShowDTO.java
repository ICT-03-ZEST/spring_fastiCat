package com.spring.ict03_fastiCat.dto;

import java.sql.Date; 

public class MainShowDTO {
	private int showNum;                  //공연번호    
	private String showCategory;         //공연카테고리  
	private String showName;             //공연명     
	private String showAge;            //관람연령    
	private int showTime;             //공연시간 
	private Date showDay; 				//공연날짜
	private String showPlace;            //공연장소    
	private String showImage;              //공연이미지   
	private int showPrice;               //공연가격    
	
	public MainShowDTO() {
		super();
	}

	public MainShowDTO(int showNum, String showCategory, String showName, String showAge, int showTime, Date showDay,
			String showPlace, String showImage, int showPrice) {
		super();
		this.showNum = showNum;
		this.showCategory = showCategory;
		this.showName = showName;
		this.showAge = showAge;
		this.showTime = showTime;
		this.showDay = showDay;
		this.showPlace = showPlace;
		this.showImage = showImage;
		this.showPrice = showPrice;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(String showCategory) {
		this.showCategory = showCategory;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowAge() {
		return showAge;
	}

	public void setShowAge(String showAge) {
		this.showAge = showAge;
	}

	public int getShowTime() {
		return showTime;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	public String getShowPlace() {
		return showPlace;
	}

	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public int getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(int showPrice) {
		this.showPrice = showPrice;
	}

	public Date getShowDay() {
		return showDay;
	}

	public void setShowDay(Date showDay) {
		this.showDay = showDay;
	}

	
}


