package com.spring.ict03_fastiCat.dto;

import java.sql.Date;

public class VisitDTO {
	
	private int visit_count;
	private Date visit_date;
	
	public VisitDTO() {
		super();
	}

	public VisitDTO(int visit_count, Date visit_date) {
		super();
		this.visit_count = visit_count;
		this.visit_date = visit_date;
	}

	public int getVisit_count() {
		return visit_count;
	}

	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}

	public Date getVisit_date() {
		return visit_date;
	}

	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	
	
	 
}
