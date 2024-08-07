package com.spring.ict03_fasticat.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

// 로그인 실패시 작동
public class UserLoginFailurerHandler implements AuthenticationFailureHandler {
   
   @Autowired
   private SqlSession sqlSession;
   
   @Autowired
   private BCryptPasswordEncoder passwordEncoder;
   
   // security-context.xml에서 매개변수 생성자 호출시 sqlSession, passwordEncoder의 주소값을 매개변수로 전달
   public UserLoginFailurerHandler(SqlSession sqlSession, BCryptPasswordEncoder passwordEncoder) {
      this.sqlSession = sqlSession;
      this.passwordEncoder = passwordEncoder;
   }
   
   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
         AuthenticationException exception) throws IOException, ServletException {
      System.out.println("<<< UserLoginFailurerHandler - onAuthenticationFailure 진입 >>>");
      
      String strId = request.getParameter("userid");
      String strPassword = request.getParameter("password");
      
      int selectCnt = sqlSession.selectOne("com.spring.ict03_fasticat.dao.CustomerDAO.useridCheck", strId);
      if(selectCnt != 0) {
         // 암호화된 비밀번호 가져오기(회원가입시 비밀번호 암호화 처리)
         String encryptPassword = sqlSession.selectOne("com.spring.ict03_fasticat.dao.CustomerDAO.userPasswordCheck", strId);
         System.out.println("화면에서 입력받은 비밀번호 : " + strPassword);
         System.out.println("암호화된 비밀번호 : " + encryptPassword);
         
         // 로그인시 입력한 비밀번호와 가입된 비밀번호(암호화된 비밀번호)가 일치하는지 여부
         if(passwordEncoder.matches(strPassword, encryptPassword)) {
            System.out.println("<<< 이메일 인증 >>>");
            request.setAttribute("msg", "이메일 인증하세요.!!");
         }
         else {
            System.out.println("<<< 비밀번호 불일치 >>>");
            request.setAttribute("msg", "비밀번호가 일치하지 않습니다.!!");
         }
      }
      else {
         System.out.println("<<< 아이디 불일치 >>>");
         request.setAttribute("msg", "아이디가 일치하지 않습니다.!!");
      }
      
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/login/login.jsp");
      dispatcher.forward(request, response);
      
   }

}
