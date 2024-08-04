package com.spring.ict03_fastiCat.dto;

import java.sql.Date;

public class NoticeDTO {
	

	private int n_Board_Num;	//공지글 번호
	private String n_Title;		//공지 제목
	private String n_Content;	//공지 내용
	private String n_Writer;	//작성자
	private Date n_WriteDate;	//작성일
	private int n_ReadCnt;		//조회수

	
	//디폴트생성자 
	public NoticeDTO() {
		
	}

	//매개변수
	public NoticeDTO(int n_Board_Num, String n_Title, String n_Content, String n_Writer, Date n_WriteDate,
			int n_ReadCnt) {
		super();
		this.n_Board_Num = n_Board_Num;
		this.n_Title = n_Title;
		this.n_Content = n_Content;
		this.n_Writer  = n_Writer;
		this.n_WriteDate = n_WriteDate;
		this.n_ReadCnt = n_ReadCnt;
	}
	//getter setter
	public int getN_Board_Num() {
    
		return n_Board_Num;
	}
	public void setN_Board_Num(int n_Board_Num) {
		this.n_Board_Num = n_Board_Num;
	}
	public String getN_Title() {
		return n_Title;
	}
	public void setN_Title(String n_Title) {
		this.n_Title = n_Title;
	}
	public String getN_Content() {
		return n_Content;
	}
	public void setN_Content(String n_Content) {
		this.n_Content = n_Content;
	}
	public String getN_Writer() {
		return n_Writer;
	}
	public void setN_Writer(String n_Writer) {
		this.n_Writer = n_Writer;
	}
	public Date getN_WriteDate() {
		return n_WriteDate;
	}
	public void setN_WriteDate(Date n_WriteDate) {
		this.n_WriteDate = n_WriteDate;
	}
	public int getN_ReadCnt() {
		return n_ReadCnt;
	}
	public void setN_ReadCnt(int n_ReadCnt) {
		this.n_ReadCnt = n_ReadCnt;
	}
	
	
	@Override
	public String toString() {
		return "NoticeDTO [n_Board_Num=" + n_Board_Num + ", n_Title=" + n_Title + ", n_Content=" + n_Content
				+ ", n_Writer=" + n_Writer + ", n_WriteDate=" + n_WriteDate + ", n_ReadCnt=" + n_ReadCnt + "]";
	}

}
