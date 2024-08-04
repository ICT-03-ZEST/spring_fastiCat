package com.spring.ict03_fastiCat.dto;

import java.sql.Date;

/* 작성자 : 방준성
 * 작성일 : 24.07.21
 * 파일설명 : Show 테이블의 dto파일
 */
/* 작성자 : 김가연
 * 작성일 : 24.07.29
 * 파일설명 : Show 테이블의 dto파일
 */
public class ShowDTO {
	private int showNum;         // 공연번호
    private String showName;     // 공연명
    private String showCategory; // 공연 카테고리 **
    private Date showIndate;	 // 공연등록일**(sysdate)
    private String showPlace;    // 공연장소
    private int showPrice;       // 1매당 가격
    private int showTime;        // 공연시간
    private String showAge;      // 관람연령 **
    private String showBene;     // 혜택
    private int curCapacity;     // 현수용인원
    private int maxCapacity;     // 최대수용인원
    private Date showDay;        // 공연날짜
    private String showImage;	 // 이미지 이름
    private String show;     	 // 활성화 유무 y/n
	
    public ShowDTO() {}

	public ShowDTO(int showNum, String showName, int curCapacity, int maxCapacity, Date showDay, String show) {
		super();
		this.showNum = showNum;
		this.showName = showName;
		this.curCapacity = curCapacity;
		this.maxCapacity = maxCapacity;
		this.showDay = showDay;
		this.show = show;
	}

	public ShowDTO(int showNum, String showName, String showCategory, Date showIndate, String showPlace, int showPrice,
			int showTime, String showAge, String showBene, int curCapacity, int maxCapacity, Date showDay,
			String showImage, String show) {
		super();
		this.showNum = showNum;
		this.showName = showName;
		this.showCategory = showCategory;
		this.showIndate = showIndate;
		this.showPlace = showPlace;
		this.showPrice = showPrice;
		this.showTime = showTime;
		this.showAge = showAge;
		this.showBene = showBene;
		this.curCapacity = curCapacity;
		this.maxCapacity = maxCapacity;
		this.showDay = showDay;
		this.showImage = showImage;
		this.show = show;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	
	public String getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(String showCategory) {
		this.showCategory = showCategory;
	}

	public Date getShowIndate() {
		return showIndate;
	}

	public void setShowIndate(Date showIndate) {
		this.showIndate = showIndate;
	}

	public String getShowPlace() {
		return showPlace;
	}

	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}

	public int getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(int showPrice) {
		this.showPrice = showPrice;
	}

	public int getShowTime() {
		return showTime;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	public String getShowAge() {
		return showAge;
	}

	public void setShowAge(String showAge) {
		this.showAge = showAge;
	}

	public String getShowBene() {
		return showBene;
	}

	public void setShowBene(String showBene) {
		this.showBene = showBene;
	}

	public int getCurCapacity() {
		return curCapacity;
	}

	public void setCurCapacity(int curCapacity) {
		this.curCapacity = curCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Date getShowDay() {
		return showDay;
	}

	public void setShowDay(Date showDay) {
		this.showDay = showDay;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public String getshow() {
		return show;
	}

	public void setshow(String show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "ShowDTO [showNum=" + showNum + ", showName=" + showName + ", showCategory=" + showCategory
				+ ", showIndate=" + showIndate + ", showPlace=" + showPlace + ", showPrice=" + showPrice + ", showTime="
				+ showTime + ", showAge=" + showAge + ", showBene=" + showBene + ", curCapacity=" + curCapacity
				+ ", maxCapacity=" + maxCapacity + ", showDay=" + showDay + ", showImage=" + showImage + ", show="
				+ show + "]";
	}

    
}
