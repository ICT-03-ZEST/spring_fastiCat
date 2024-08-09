package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.CustomerDTO;
import com.spring.ict03_fastiCat.util.SettingValues;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SqlSession sqlSession;

	// ID 중복확인 처리 호출
	@Override
	public int useridCheck(String strId) {

		System.out.println("DAO -useridcheck()");

		int selectCnt = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.CustomerDAO.useridCheck", strId);
		System.out.println("selectCnt : " + selectCnt);
		return selectCnt;
	}

	// 회원가입 처리 호출
	@Override
	public int insertCustomer(CustomerDTO dto) {
		System.out.println("DAO-insertCustomer()");

		int insertCnt = sqlSession.insert("com.spring.ict03_fastiCat.dao.CustomerDAO.insertCustomer", dto);

		System.out.println("insertCnt : " + insertCnt);
		return insertCnt;
	}
	
	// 시큐리티 - 회원가입 성공시 이메일 인증을 위한 이메일 정보 전송
	public void sendEmail(String email, String key) {
	   System.out.println("DAO - sendEmail()");
	  
	   // 이메일 설정 => 네이버까페 > SPRING > 스프링 시큐리티 > 스프링_ 네이버로 이메일 보내기 설정_ict_03기 참조
	  
	   final String username = SettingValues.ADMIN;    // 네이버 이메일 ID : @naver.com은 입력하지 않는다.
	   final String password = SettingValues.PW;      // 네이버 비밀번호
	   // naver의 이메일 > 환경설정 > POP/IMAP 설정
	   final String host = "smtp.naver.com";         // Google일 경우 smtp.gmail.com
	   int port = 465;   
	  
	   // 메일내용
	   String recipient = email;  // 받는 사람의 메일 주소 입력
	   String subject = "회원가입 인증메일";           // 메일 제목 입력
	   String content = "회원가입을 축하드립니다. 링크를 눌러 회원가입을 완료하세요. "
	      + "<a href='http://localhost:8081/ict03_fastiCat/emailChkAction.do?key=" + key + "'>링크</a>"; // emailChkAction.do를 컨트롤러에 추가
	  
	   Properties props = System.getProperties();
	  
	   // 정보를 담기 위한 객체 생성
	   // SMTP 서버 설정 정보
	   props.put("mail.smtp.host", host);
	   props.put("mail.smtp.port", port);
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.ssl.enable", "true");
	   props.put("mail.smtp.ssl.trust", host);
	  
	   // Session 생성
	   Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	     String uname = username;
	     String pwd = password;
	     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	        return new javax.mail.PasswordAuthentication(uname, pwd);
	     }
	   });
	  
	   session.setDebug(true);
	  
	   try {
	     // import javax.mail.Message
		 // import javax.mail.Transport;
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("fasticat_ict03@naver.com"));   // 발신자 세팅
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자 세팅
		mimeMessage.setSubject(subject);   // 제목세팅
		mimeMessage.setContent(content, "text/html; charset=utf-8");
		Transport.send(mimeMessage);
		System.out.println("<<< Email SEND >>>");
	  } catch(Exception e) {
	     e.printStackTrace();
	  }
	}
	
	// 시큐리티 -로그인전에 이메일 인증을 한다. 
	@Override
	public int selectKey(String key) {
		System.out.println("DAO - selectKey()");
		int selectCnt = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.CustomerDAO.selectKey", key);
		System.out.println("DAO- selectCnt : " + selectCnt);
		return selectCnt;
	}

	// 시큐리티 - enabled를 1로 update
	@Override
	public int updateGrade(String key) {
		System.out.println("DAO - updateGrade()");
		int updateCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.CustomerDAO.updateGrade", key);
		System.out.println("DAO- updateCnt : " + updateCnt);
		return updateCnt;
	}

	// 로그인 처리/ 회원정보 인증(수정, 탈퇴) 호출
	@Override
	public int idPasswordChk(Map<String, Object> map) {
		System.out.println("DAO - idpassword check()");

		int selectCnt = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.CustomerDAO.idPasswordChk", map);

		System.out.println("selectCnt :" + selectCnt);
		return selectCnt;
	}

	// 관리자 - 회원목록 조회
	@Override
	public List<CustomerDTO> memberList() {
		System.out.println("DAO - memberList() ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		List<CustomerDTO> list = dao.memberList();
		return list;
	}

	// 관리자 - 회원수
	@Override
	public int memberCnt() {
		System.out.println("DAO - memberCnt");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int total = dao.memberCnt();
		System.out.println("total : " + total);
		return total;
	}

	// 관리자 - 회원 삭제
	@Override
	public int deleteMember(String userid) {
		System.out.println("DAO - deleteMember ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int deleteCnt = dao.deleteMember(userid);
		System.out.println("deleteCnt : " + deleteCnt);
		return deleteCnt;
	}

	// 관리자 - 탈퇴 회원목록 조회
	@Override
	public List<CustomerDTO> dropMemberList() {
		System.out.println("DAO - dropMemberList() ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		List<CustomerDTO> list = dao.dropMemberList();
		return list;
	}

	// 관리자 - 탈퇴 회원 수
	@Override
	public int dropMemberCnt() {
		System.out.println("DAO - dropMemberCnt");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int total = dao.dropMemberCnt();
		System.out.println("total : " + total);
		return total;
	}

	// 관리자 - 탈퇴회원 복구
	@Override
	public int dropMemberRestore(String userid) {
		System.out.println("DAO - dropMemberRestore ");
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int updateCnt = dao.dropMemberRestore(userid);
		System.out.println("updateCnt : " + updateCnt);
		return updateCnt;
	}

	// 회원비밀번호정보 가져오기
	public CustomerDTO getpassword(String strId) {
		CustomerDTO dto = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.CustomerDAO.getpassword", strId);
		return dto;
	}

}
