package com.spring.ict03_fastiCat.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController  {
	// 전역변수
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private com.spring.ict03_fastiCat.service.SearchServiceImpl service;


	// 첫페이지
	@RequestMapping("search.sc")
	public String search(HttpServletRequest request, Model model)
			throws ServletException, IOException {

		logger.info("url ==> /search.sc ");
	
			service.boardListAction(request, model);
			return  "/customer/search/search";
		}
	   // [ 검색창 세부검색 ]
    @RequestMapping("/search_detailList.sc")
    public String searchDetailList(HttpServletRequest request, Model model) throws ServletException, IOException {
        logger.info("url ==> /search_detailList.sc");
        service.boardDetailListAction(request, model);
        return "/customer/search/search_detailList";
    }

    // 공연목록 조회
    @RequestMapping("/searchEvent.sc")
    public String searchEvent(HttpServletRequest request, Model model) throws ServletException, IOException {
        logger.info("url ==> /searchEvent.sc");

        return "customer/search/searchEvent";
    }

    // 공연목록 조회 결과
    @RequestMapping("/searchconcertList.sc")
    public String searchConcertList(HttpServletRequest request, Model model) throws ServletException, IOException {
        logger.info("url ==> /searchconcertList.sc");
        service.concertListAction(request, model);
        return "customer/search/searchconcert";
    }
}
