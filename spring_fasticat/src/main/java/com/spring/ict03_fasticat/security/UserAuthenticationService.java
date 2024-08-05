package com.spring.ict03_fasticat.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.ict03_fasticat.dto.CustomerDTO;
import com.spring.ict03_fasticat.dto.UserVO;

// CustomerController의 loginAction.do 대신에 여기로 진입
// 로그인 인증처리 클래스 : 비밀번호 인증, 로그인 성공, 실패, 권한없음을 판단
// 로그인시 우리가 만든 loginAction.do는 주석처리한다.(UserService에 의해 login-processing-url="/loginAction.do" 부분이 자동호출되므로)
public class UserAuthenticationService implements UserDetailsService {
   
   @Autowired
   private SqlSession sqlSession;
   
   // security-context.xml에서 매개변수 생성자 호출시 sqlSession의 주소값을 매개변수로 전달
   public UserAuthenticationService(SqlSession sqlSession) {
      this.sqlSession = sqlSession;
   }

   /* 핵심코드
    * 로그인 인증을 처리하는 메서드
    * 1) 매개변수 username을 userid로 수정
    * 2) 매개변수로 전달된 아이디와 일치하는 레코드를 읽어온다.
    * 3) 테이블의 암호화된 비밀번호와 사용자가 입력한 비밀번호를 내부적으로 비교처리
    * 4) 정보가 없으면 예외처리를 발생시키고, 있으면 해당정보를 dto로 리턴한다.
    * 5) 로그인 성공이면 UserLoginSeccessHandler로 이동
    *    로그인 실패이면 UserLoginFailureHandler로 이동
    *    권한이 없으면 403에러, UserDeniedHandler로 이동
   */
   
   @Override
   public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
      
      CustomerDTO dto = sqlSession.selectOne("com.spring.fasticat.dao.CustomerDAO.selectCustomer", userid);
      System.out.println("시큐리티 로그인 체크 => " + dto);
      
      // 로그인 인증 실패시 인위적으로 예외를 생성해서 던진다.
      if(dto == null) throw new UsernameNotFoundException(userid);
      
      List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
      authority.add(new SimpleGrantedAuthority(dto.getAuthority()));
      
      // UserVO 클래스를 먼저 생성후 return
      // 시큐리티 로그인에서 체크 : userid, password, enabled(이메일인증시 enabled 컬럼에 "1"로 update치며, 이메일 인증후 시큐리티 적용한다.)
      // authority(ROLE_USER | ROLE_ADMIN)
      return new UserVO(dto.getUserid(), dto.getPassword(), dto.getEnabled().equals("1"),
            true, true, true, authority);
   }

}
