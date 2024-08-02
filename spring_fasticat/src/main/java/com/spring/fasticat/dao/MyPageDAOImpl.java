package com.spring.fasticat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spring.fasticat.dto.BoardDTO;
import com.spring.fasticat.dto.MyPageDTO;
import com.spring.fasticat.dto.MyReservationDTO;

@Repository
public class MyPageDAOImpl implements MyPageDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int idPasswordChk(Map<String, Object> map) {
		System.out.println("DAO-idPasswordChk");
		// 암호화된 비밀번호 가져오기(회원가입시 비밀번호 암호화 처리)
		String strId = (String) map.get("strId");
		String strPwd = (String) map.get("strPwd");
		
        String encryptPassword = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.userPasswordCheck", strId);
        System.out.println("화면에서 입력받은 비밀번호 : " + strPwd);
        System.out.println("암호화된 비밀번호 : " + encryptPassword);
        
        int selectCnt = 0;
        // 로그인시 입력한 비밀번호와 가입된 비밀번호(암호화된 비밀번호)가 일치하는지 여부
        if(passwordEncoder.matches(strPwd, encryptPassword)) {
        	selectCnt = 1;
        }    
		
		return selectCnt;
	}


	@Override
	public int deleteUser(String strId) {
		System.out.println("DAO - deleteUser");
		
		int deleteCnt = sqlSession.delete("com.spring.fasticat.dao.MyPageDAO.deleteUser", strId);	
		
		return deleteCnt;
	}


	@Override
	public MyPageDTO getUserDetail(String strId) {
		System.out.println("DAO - getUserDetail");
			
		MyPageDTO dto = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.getUserDetail", strId);
		
		// 5. CustomerDTO를 리턴한다.
		return dto;
	}


	@Override
	public int updateUser(MyPageDTO dto) {
		System.out.println("DAO - updateUser");
		
		int updateCnt = sqlSession.update("com.spring.fasticat.dao.MyPageDAO.updateUser", dto);
		
		return updateCnt;
	}
	
	// 게시글 목록
	@Override
	public List<BoardDTO> myBoardList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myBoardList");
		
		List<BoardDTO> list = sqlSession.selectList("com.spring.fasticat.dao.MyPageDAO.myBoardList", map);
				
		System.out.println("list : " + list);
		return list;
		
	}
	
	// 댓글 목록
	@Override
	public List<BoardDTO> myCommentList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myCommentList");
		
		List<BoardDTO> list = sqlSession.selectList("com.spring.fasticat.dao.MyPageDAO.myCommentList", map);
				
		System.out.println("list : " + list);
		return list;
	}
	
	// 게시글 갯수 구하기
	@Override
	public int myBoardCnt(Map<String, Object> map) {
		System.out.println("MyPageDAO - myBoardCnt");

		int total = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.myBoardCnt", map);
			
		// 5. CustomerDTO를 리턴한다.
		return total;
	
	}
	
	// 댓글 갯수 구하기
	@Override
	public int myCommentCnt(String strId) {
		System.out.println("MyPageDAO - myCommentCnt");
		
		int total = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.myCommentCnt", strId);
			
		// 5. CustomerDTO를 리턴한다.
		return total;
	
	}
	
	@Override
	public int boardDelete(Map<String, Object> map) {
		System.out.println("DAO - boardDelete");
		
		int deleteCnt = sqlSession.delete("com.spring.fasticat.dao.MyPageDAO.boardDelete", map);
			
		return deleteCnt;
	}


	@Override
	public int resBoardCnt(String strId) {
		System.out.println("MyPageDAO - resBoardCnt");
		
		int total = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.resBoardCnt", strId);
		
		return total;
	}


	@Override
	public List<MyReservationDTO> resBoardList(Map<String, Object> map) {
		System.out.println("MyPageDAO - resBoardList");
		
		List<MyReservationDTO> list = sqlSession.selectList("com.spring.fasticat.dao.MyPageDAO.resBoardList", map);
			
		return list;
	}
	
	@Override
	public int resDelete(Map<String, Object> map) {
		System.out.println("DAO - resDelete");
		
		int deleteCnt = sqlSession.selectOne("com.spring.fasticat.dao.MyPageDAO.resDelete", map);
		
		return deleteCnt;
	}

}
