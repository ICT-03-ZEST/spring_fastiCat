package com.spring.ict03_fastiCat.service;

import java.io.IOException; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.ict03_fastiCat.dao.MainShowDAO;
import com.spring.ict03_fastiCat.dto.ShowDTO;
import com.spring.ict03_fastiCat.page.ContentPaging;

@Service
public class MainShowServiceImpl implements MainShowService{
	
	@Autowired
	private MainShowDAO dao; 
	
	// 메인 - 목록
	@Override
	public void MainShowList(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - MainShowList()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String showCategory = request.getParameter("showCategory");
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		//5-1단계. 공연갯수
		int total = dao.showCnt(showCategory);
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		ContentPaging contentPaging = new ContentPaging(pageNum);
		contentPaging.setTotalCount(total);
		
		int start = contentPaging.getStartRow();
		int end = contentPaging.getEndRow();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", showCategory);
		map.put("start", start);
		map.put("end", end);
		List<ShowDTO> list = dao.showList(map);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("showCategory", showCategory);
		model.addAttribute("list", list);
		model.addAttribute("paging", contentPaging);
		
	}

	//상세페이지
	@Override
	public void MainshowInfo(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - MainshowInfo()");
		
		String showCategory = request.getParameter("showCategory");
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", showCategory);
		map.put("showNum", showNum);
		
		// 5단계
		ShowDTO dto = dao.showInfo(map);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("showCategory", showCategory);
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
	}


}
