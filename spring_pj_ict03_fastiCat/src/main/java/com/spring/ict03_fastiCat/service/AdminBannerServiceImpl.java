package com.spring.ict03_fastiCat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.ict03_fastiCat.dao.AdminBannerDAO;
import com.spring.ict03_fastiCat.dto.AdminBannerDTO;
import com.spring.ict03_fastiCat.page.Paging;

@Service
public class AdminBannerServiceImpl implements AdminBannerService{
	
	@Autowired
	private AdminBannerDAO dao;
	
	// 배너등록
	@Override
	public void bannerAddAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerAddAction()");
		
		// 추가 S : JSP의 ImageUploadHandler.java 기능
		MultipartFile file = request.getFile("bannerImg");
		System.out.println("file : " + file);
		
		// input 경로
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir : " + saveDir);
		
		// 이미지 추가를 위한 샘플이미지 경로(upload 폴더 우클릭 > properties > location 정보 복사해서 붙여넣기), 맨뒤에 \\ 추가
		String realDir = "D:\\dev\\workspace_spring\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload\\";
		System.out.println("realDir : " + realDir);
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {	//new File : io import하기!
			file.transferTo(new File(saveDir + file.getOriginalFilename()));
			fis = new FileInputStream(saveDir + file.getOriginalFilename());
			fos = new FileOutputStream(realDir + file.getOriginalFilename());
			
			int data = 0;
			while( (data = fis.read()) != -1) {
				fos.write(data);
			}
			// 추가E : JSP의 ImageUploadHandler.java기능
			
			
			
			// 3단계. 화면에서 입력받은 값을 가져오기 ----------------------------
			// DTO 생성
			AdminBannerDTO dto = new AdminBannerDTO();
			dto.setBannerArea(request.getParameter("bannerArea"));
			// 수정 S
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			String p_img1 = "/ict03_fastiCat/resources/upload/" + file.getOriginalFilename();
			System.out.println("p_img1 : " + p_img1);
			// 수정 E
			dto.setBannerImg(p_img1);
			dto.setBannerStatus(request.getParameter("bannerStatus"));
		
			// 5단계. 배너정보 insert
			int insertCnt = dao.bannerInsert(dto);
			
			// 6단계. jsp로 처리결과 전달
			model.addAttribute("insertCnt", insertCnt);
			
			//-----------------------------------------------
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) fis.close();
			if(fos != null) fos.close();
		}
	
	
	}
	
	// 배너목록
	@Override
	public void bannerListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		//AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		//5-1단계. 배너갯수
		int total = dao.bannerCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 배너목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<AdminBannerDTO> list = dao.bannerList(map);
		
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
	}
	
	// 배너 상세페이지
	@Override
	public void bannerDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerDetailAction()");
		
		int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		//AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계
		AdminBannerDTO dto = dao.bannerDetail(bannerNo);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
	}

	// 배너수정
	@Override
	public void bannerUpdateAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerUpdateAction()");
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenBannerNo = Integer.parseInt(request.getParameter("hiddenBannerNo"));
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String hiddenBannerImg = request.getParameter("hiddenBannerImg");
		//String uploadBannerImg = (String)request.getAttribute("fileName"); //ImageUploadHandler 클래스에서 setAttribute로 upload 파일명
		
		System.out.println("hiddenBannerImg : " + hiddenBannerImg);
		
		// 추가 S : JSP의 ImageUploadHandler.java 기능
		MultipartFile file = request.getFile("bannerImg");
		System.out.println("file : " + file);
		
		// input 경로
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir : " + saveDir);
		
		// 이미지 추가를 위한 샘플이미지 경로(upload 폴더 우클릭 > properties > location 정보 복사해서 붙여넣기), 맨뒤에 \\ 추가
		String realDir = "D:\\dev\\workspace_spring\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload\\";
		System.out.println("realDir : " + realDir);
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String p_img1 = "";
		
		if(file.getOriginalFilename() != null && file.getOriginalFilename() !="") {
			try {	//new File : io import하기!
				file.transferTo(new File(saveDir + file.getOriginalFilename()));
				fis = new FileInputStream(saveDir + file.getOriginalFilename());
				fos = new FileOutputStream(realDir + file.getOriginalFilename());
				
				int data = 0;
				while( (data = fis.read()) != -1) {
					fos.write(data);
				}
				// 추가E : JSP의 ImageUploadHandler.java기능---------------------
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				if(fis != null) fis.close();
				if(fos != null) fos.close();
			}
			
			// 수정 S
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			p_img1 = "/ict03_fastiCat/resources/upload/" + file.getOriginalFilename();
			System.out.println("p_img1 : " + p_img1);
			// 수정 E
			
		} 
		else {
			// 기존이미지 사용
			p_img1 = hiddenBannerImg;
			System.out.println("p_img1 " + p_img1);
		}

		
		// 3단계. 화면에서 입력받은 값을 가져오기 ---------------------------------------
		// DTO 생성 
		AdminBannerDTO dto = new AdminBannerDTO();
		dto.setBannerNo(hiddenBannerNo);
		dto.setBannerArea(request.getParameter("bannerArea"));	
		dto.setBannerStatus(request.getParameter("bannerStatus"));
		dto.setBannerImg(p_img1);
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		//AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.bannerUpdate(dto);
		
		//6단계.jsp로 처리결과 전달
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("hiddenPageNum", hiddenPageNum);
		model.addAttribute("hiddenBannerNo", hiddenBannerNo);
	}

	// 배너삭제
	@Override
	public void bannerDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		//AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.bannerDelete(bannerNo);
		model.addAttribute("deleteCnt", deleteCnt);
		
	}
	
	// 메인 - 배너 조회
	@Override
	public void getMainBanner(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - getMainBanner()");
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		//AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		List<AdminBannerDTO> bannerList = dao.getBannerList();
		model.addAttribute("bannerList", bannerList);
	}

}
