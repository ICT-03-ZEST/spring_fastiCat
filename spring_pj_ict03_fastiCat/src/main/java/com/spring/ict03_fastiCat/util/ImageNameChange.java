package com.spring.ict03_fastiCat.util;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;   

import javax.servlet.ServletException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class ImageNameChange {

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
		
		String category = request.getParameter("board_category");
		System.out.println("category: " + category);
		//추가 : JSP의 ImageUploadHandler.java 기능
		MultipartFile file = request.getFile("board_image");
		System.out.println("file: " + file);
		
		String imageName = file.getOriginalFilename();
		System.out.println("imageName: " + imageName);
		
		//input 경로
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir: " + saveDir);
		//C/D 변경	
		//이미지 추가를 위한 샘플이미지 경로 (upload 폴더 우클릭 > properties > location 정보 복사해서 붙여넣기) 맨뒤 \\추가
		String realDir = "D:\\git\\spring_fastiCat\\spring_pj_ict03_fastiCat\\src\\main\\webapp\\resources\\upload\\";
		System.out.println("realDir: " + realDir);		
		
		FileInputStream fis= null;
		FileOutputStream fos = null;
		
		if(imageName == null || imageName.equals("")) {
			System.out.println("imageName == null");
			this.thumnail = "/ict03_fastiCat/resources/images/noimage.png";
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

