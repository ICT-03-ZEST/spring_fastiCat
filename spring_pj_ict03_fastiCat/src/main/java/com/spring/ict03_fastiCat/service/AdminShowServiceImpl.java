package com.spring.ict03_fastiCat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
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

import com.spring.ict03_fastiCat.dao.AdminShowDAOImpl;
import com.spring.ict03_fastiCat.dto.ShowDTO;
import com.spring.ict03_fastiCat.page.Paging;

@Service
public class AdminShowServiceImpl implements AdminShowService{
	
	@Autowired
	private AdminShowDAOImpl dao;
	
	//공연, 페스티벌 등록
	@Override
	public void showAddAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - showAddAction()");
		
		// 파일 업로드 처리
	    MultipartFile file = request.getFile("showImage");
	    if (file == null || file.isEmpty()) {
	        throw new ServletException("No file uploaded or file is empty");
	    }

	    String originalFilename = file.getOriginalFilename();
	    if (originalFilename == null || originalFilename.trim().isEmpty()) {
	        throw new ServletException("Invalid file name");
	    }

	    // 파일 저장 경로
	    String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
	    File uploadDir = new File(saveDir);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();  // 디렉토리가 존재하지 않으면 생성
	    }

	    // 파일 저장 경로 설정
	    File savedFile = new File(saveDir + File.separator + originalFilename);
	    if (savedFile.exists()) {
	        savedFile.delete();  // 같은 이름의 파일이 존재하면 삭제
	    }

	    // 파일 업로드
	    try {
	        file.transferTo(savedFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new ServletException("Error while saving the file", e);
	    }

		
		// 3단계. 화면에서 입력받은 값을 가져오기 -----------------------------------
		String category = request.getParameter("showCategory");
		System.out.println("서비스category - " + category);
		
		String showDay = request.getParameter("showDay");
	    System.out.println("입력받은 날짜들: " + showDay);
		
	    // 쉼표로 날짜구분해서 배열로 변환
	    String[] showDays = showDay.split(",");
	    
	    // 파일 URL 설정
	    String fileUrl = "/ict03_fastiCat/resources/upload/" + originalFilename;
	   
	    // 삽입된 레코드 수를 저장할 변수
	    int insertCnt = 0;
	    
	    // 각 날짜에 대해 레코드를 생성하여 데이터베이스에 삽입
	    for(String day : showDays) {
	    	// DTO 생성
			ShowDTO dto = new ShowDTO();
			
			dto.setShowName(request.getParameter("showName"));
			dto.setShowCategory(request.getParameter("showCategory"));
			dto.setShowPlace(request.getParameter("showPlace"));
			dto.setShowPrice(Integer.parseInt(request.getParameter("showPrice")));
			dto.setShowTime(Integer.parseInt(request.getParameter("showTime")));
			dto.setShowAge(request.getParameter("showAge"));
			dto.setShowBene(request.getParameter("showBene"));
			dto.setCurCapacity(Integer.parseInt(request.getParameter("curCapacity")));
			dto.setMaxCapacity(Integer.parseInt(request.getParameter("maxCapacity")));
			dto.setShowDay(Date.valueOf(day.trim())); // 공백 제거 후 Date 객체로 변환
			dto.setShowImage(fileUrl);
			
			// Map 객체 생성 및 데이터 설정 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dto", dto);
			map.put("category", category);
			System.out.println("insert 전 서비스map - " + map);
			
			insertCnt += dao.showInsert(map);
	    }
		
		System.out.println("서비스insertCnt - " + insertCnt);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("insertCnt", insertCnt);

	}
	
	//공연, 페스티벌 목록
	@Override
	public void showListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - showListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		String category = request.getParameter("showCategory");
		
		System.out.println("pageNum: " + pageNum);
		System.out.println("category: " + category);
		
		// Map 객체 생성 및 데이터 설정 
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("category", category);
		
		//5-1단계. 공연갯수
	    Paging paging = new Paging(pageNum);
		int total = dao.showCnt(params);
		
		System.out.println("total: " + total);
		paging.setTotalCount(total);
		
		//5-2단계. 공연목록
		List<ShowDTO> list = dao.showList(params);
		System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
	}
	
	//공연, 페스티벌 수정(상세페이지)
	@Override
	public void showDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - productDetailAction()");
		
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		//int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String category = request.getParameter("showCategory");
		
		// Map 객체 생성 및 데이터 설정 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("showNum", showNum);
		map.put("category", category);
		
		ShowDTO dto = dao.showDetail(map);
		
		// 6단계. jsp로 처리결과 전달
		model.addAttribute("dto", dto);
		//model.addAttribute("pageNum", pageNum);
		
	}
	
	//공연, 페스티벌 수정
	@Override
	public void showUpdateAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - showUpdateAction()");
		
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenShowNum = Integer.parseInt(request.getParameter("hiddenShowNum"));
		String hiddenShowImage = request.getParameter("hiddenShowImage");
		
		System.out.println("hiddenShowImage : " + hiddenShowImage);
		System.out.println("hiddenShowNum : " + hiddenShowNum);
		
		
		// 추가 S : JSP의 ImageUploadHandler.java 기능
		MultipartFile file = request.getFile("showImage");
		System.out.println("file : " + file);
		
		// input 경로
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir : " + saveDir);
		
		// 이미지 추가를 위한 샘플이미지 경로(upload 폴더 우클릭 > properties > location 정보 복사해서 붙여넣기), 맨뒤에 \\ 추가
		String realDir = "D:\\git\\spring_fastiCat\\spring_pj_ict03_fastiCat\\src\\main\\webapp\\resources\\upload\\";
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
			p_img1 = hiddenShowImage;
			System.out.println("p_img1 " + p_img1);
		}
		
		
		// 3단계. 화면에서 입력받은 값을 가져오기 ---------------------------------------
		// 삽입된 레코드 수를 저장할 변수
	    int updateCnt = 0;
	    
	
		// DTO 생성 
		ShowDTO dto = new ShowDTO();
		dto.setShowNum(Integer.parseInt(request.getParameter("hiddenShowNum")));
		dto.setShowName(request.getParameter("showName"));
		dto.setShowCategory(request.getParameter("showCategory"));
		dto.setShowPlace(request.getParameter("showPlace"));
		dto.setShowPrice(Integer.parseInt(request.getParameter("showPrice")));
		dto.setShowTime(Integer.parseInt(request.getParameter("showTime")));
		dto.setShowAge(request.getParameter("showAge"));
		dto.setShowBene(request.getParameter("showBene"));
		dto.setCurCapacity(Integer.parseInt(request.getParameter("curCapacity")));
		dto.setMaxCapacity(Integer.parseInt(request.getParameter("maxCapacity")));
		dto.setShowDay(Date.valueOf((request.getParameter("showDay"))));
		dto.setShowImage(p_img1);
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		String category = request.getParameter("showCategory");
		
		// Map 객체 생성 및 데이터 설정 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		map.put("category", category);
		
		updateCnt = dao.showUpdate(map);
		
		
		//6단계.jsp로 처리결과 전달
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("hiddenShowNum", hiddenShowNum);
		
	}
	
	// 공연, 페스티벌 삭제
	@Override
	public void showDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - showDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		String category = request.getParameter("showCategory");
		int showNum = Integer.parseInt(request.getParameter("showNum"));
		
		System.out.println("서비스 category : " + category + "showNum : " + showNum);
		
		// Map 객체 생성 및 데이터 설정 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("showNum", showNum);
		int deleteCnt = dao.showDelete(map);
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		model.addAttribute("category", category);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	

}
