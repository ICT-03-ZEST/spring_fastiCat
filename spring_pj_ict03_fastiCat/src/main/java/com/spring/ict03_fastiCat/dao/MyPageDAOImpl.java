package com.spring.ict03_fastiCat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.CommentDTO;
import com.spring.ict03_fastiCat.dto.MyPageDTO;
import com.spring.ict03_fastiCat.dto.MyReservationDTO;

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
		
        String encryptPassword = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.userPasswordCheck", strId);
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
		
		int deleteCnt = sqlSession.delete("com.spring.ict03_fastiCat.dao.MyPageDAO.deleteUser", strId);	
		
		return deleteCnt;
	}


	@Override
	public MyPageDTO getUserDetail(String strId) {
		System.out.println("DAO - getUserDetail");
			
		MyPageDTO dto = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.getUserDetail", strId);
		
		// 5. CustomerDTO를 리턴한다.
		return dto;
	}


	@Override
	public int updateUser(MyPageDTO dto) {
		System.out.println("DAO - updateUser");
		
		int updateCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.MyPageDAO.updateUser", dto);
		
		return updateCnt;
	}
	
	// 게시글 목록
	@Override
	public List<BoardDTO> myBoardList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myBoardList");
		
		List<BoardDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.MyPageDAO.myBoardList", map);
				
		System.out.println("list : " + list);
		return list;
		
	}
	
	// 댓글 목록
	@Override
	public List<CommentDTO> myCommentList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myCommentList");
		
		List<CommentDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.MyPageDAO.myCommentList", map);
				
		System.out.println("list : " + list);
		return list;
	}
	
	// 게시글 갯수 구하기
	@Override
	public int myBoardCnt(Map<String, Object> map) {
		System.out.println("MyPageDAO - myBoardCnt");

		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.myBoardCnt", map);
			
		// 5. CustomerDTO를 리턴한다.
		return total;
	
	}
	
	// 댓글 갯수 구하기
	@Override
	public int myCommentCnt(Map<String, Object> map) {
		System.out.println("MyPageDAO - myCommentCnt");
		
		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.myCommentCnt", map);
			
		// 5. CustomerDTO를 리턴한다.
		return total;
	
	}
	
	@Override
	public int boardDelete(Map<String, Object> map) {
		System.out.println("DAO - boardDelete");
		
		int deleteCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.MyPageDAO.boardDelete", map);
			
		return deleteCnt;
	}
	
	@Override
	public int commentDelete(Map<String, Object> map) {
		System.out.println("DAO - commentDelete");
		
		int deleteCnt = sqlSession.update("com.spring.ict03_fastiCat.dao.MyPageDAO.commentDelete", map);
			
		return deleteCnt;
	}


	@Override
	public int resBoardCnt(String strId) {
		System.out.println("MyPageDAO - resBoardCnt");
		
		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.resBoardCnt", strId);
		
		return total;
	}


	@Override
	public List<MyReservationDTO> resBoardList(Map<String, Object> map) {
		System.out.println("MyPageDAO - resBoardList");
		
		List<MyReservationDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.MyPageDAO.resBoardList", map);
			
		return list;
	}
	
	@Override
	public int resDelete(Map<String, Object> map) {
		System.out.println("DAO - resDelete");
		
		int deleteCnt = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.resDelete", map);
		
		return deleteCnt;
	}


	@Override
	public int myFavoriteCnt(String strId) {
		System.out.println("MyPageDAO - myFavoriteCnt");
		
		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.myFavoriteCnt", strId);
		
		return total;
	}


	@Override
	public List<BoardDTO> myFavoriteList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myFavoriteList");
		
		List<BoardDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.MyPageDAO.myFavoriteList", map);
		
		return list;
	}
	
	@Override
	public int myReceivedCommentCnt(String strId) {
		System.out.println("MyPageDAO - myReceivedCommentCnt");
		
		int total = sqlSession.selectOne("com.spring.ict03_fastiCat.dao.MyPageDAO.myReceivedCommentCnt", strId);
		
		return total;
	}


	@Override
	public List<CommentDTO> myReceivedCommentList(Map<String, Object> map) {
		System.out.println("MyPageDAO - myReceivedCommentList");
		
		List<CommentDTO> list = sqlSession.selectList("com.spring.ict03_fastiCat.dao.MyPageDAO.myReceivedCommentList", map);
		
		return list;
	}

}
