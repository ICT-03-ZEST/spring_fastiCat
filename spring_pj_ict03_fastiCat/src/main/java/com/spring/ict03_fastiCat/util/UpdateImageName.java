package com.spring.ict03_fastiCat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;   

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UpdateImageName {

	String thumnail;
	String image;
	
	
	public String getThumnail() {
		return thumnail;
	}

	public String getImage() {
		return image;
	}

	public void imageName(MultipartHttpServletRequest request) 
			throws ServletException, IOException {
		System.out.println("ImageNameChange - imageName");
		
		String category = request.getParameter("hiddenCategory");
		
		String hiddenThumnail = request.getParameter("hiddenThumnail"); // 수정시 파일선택을 안하면${dto.board_image}
		String hiddenImage = request.getParameter("hiddenImage");
		
		MultipartFile file = request.getFile("board_image");
		String imageName = file.getOriginalFilename();
		System.out.println("imageName: " + imageName);
		
		//input 경로
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir: " + saveDir);
			
		//이미지 추가를 위한 샘플이미지 경로 (upload 폴더 우클릭 > properties > location 정보 복사해서 붙여넣기) 맨뒤 \\추가
		String realDir = "D:\\git\\spring_fastiCat\\spring_pj_ict03_fastiCat\\src\\main\\webapp\\resources\\upload\\";
		System.out.println("realDir: " + realDir);		
		
		FileInputStream fis= null;
		FileOutputStream fos = null;
		
		// 게시글 수정
		if(imageName == null || imageName.equals("")) {
			
			this.image = hiddenImage;
			
			if(category.equals("review")) {
				this.thumnail = hiddenThumnail;
			}
			else {
				this.thumnail = hiddenThumnail;
			}
		}
		else {
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));
				fis = new FileInputStream(saveDir + file.getOriginalFilename());
				fos = new FileOutputStream(realDir + file.getOriginalFilename());
				
				int data = 0;
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				// 게시글추가
				this.thumnail = "/ict03_fastiCat/resources/upload/" + imageName;
				this.image = "/ict03_fastiCat/resources/upload/" + imageName;
					
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				if(fis != null) fis.close();
				if(fos != null) fos.close();
			}
		}
			
	
	}
}

