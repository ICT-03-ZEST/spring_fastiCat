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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.ict03_fastiCat.dao.BoardDAO;
import com.spring.ict03_fastiCat.dto.BoardDTO;
import com.spring.ict03_fastiCat.dto.CommentDTO;
import com.spring.ict03_fastiCat.dto.HeartDTO;
import com.spring.ict03_fastiCat.page.BoardPaging;
import com.spring.ict03_fastiCat.util.ImageNameChange;
import com.spring.ict03_fastiCat.util.UpdateImageName;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	// 게시판 목록 조회
	@Override 
	public void boardListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		
		String pageNum = request.getParameter("pageNum"); // null
		String category = request.getParameter("board_category");
		
		// 5-1 단계. 전체 게시글 갯수 카운트
		BoardPaging boardPaging = new BoardPaging(pageNum);
		int total = dao.boardCnt(category);
		
		boardPaging.setTotalCount(total);
		
		// 5-2 단계. 게시글 목록조회
		int start = boardPaging.getStartRow();
		int end = boardPaging.getEndRow();
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("start", start);
		map.put("end", end);
		
		List<BoardDTO> list = dao.boardList(map);
		
		//6단계 jsp로 처리결과 전달
		model.addAttribute("list", list);
		model.addAttribute("category", category);
		model.addAttribute("paging", boardPaging);
		model.addAttribute("pageNum", pageNum);
		
	}
	
	// 게시글 상세페이지
	@Override
	public void boardDetailAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("service - boardDetailAction");
		
		String sessionID = (String) request.getSession().getAttribute("sessionID");
		System.out.println("sessionID: " + sessionID);

		String pageNum = request.getParameter("pageNum");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		String views = request.getParameter("views"); // 상세페이지를 클릭할 경우만 조회수증가/ 수정,삭제에의한 새로고침으로 조회수가 올라가지 않게 제어
		
		// 하트체크 조회 => 해당 게시글번호의 하트를 누른 아이디라면 하트 filled
		HeartDTO heart = new HeartDTO();
		heart.setUserID(sessionID);
		heart.setBoard_num(board_num);
		heart.setBoard_category(category);
		
		int heartChk = dao.selectHeart(heart); //=> 1 이면 하트 filled
		
		//사용자 게시글 이력 조회 => '수정' 태그가 뜨도록함
		BoardDTO bd_dto = new BoardDTO();
		bd_dto.setBoard_writer(sessionID);
		bd_dto.setBoard_num(board_num);
		bd_dto.setBoard_category(category);
		
		int selWriter = dao.selectOfwriter(bd_dto); // 사용자게시글 수정,삭제버튼 block
		
		//System.out.println("selWriter: " + selWriter);
		 
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("board_num", board_num);
		// 게시판에서 상세페이지 클릭시에만 조회수증가
		if(views != null) {
			dao.plusViews(map);
		}
		
		BoardDTO dto = dao.getBoardDetail(map);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		model.addAttribute("selWriter", selWriter);
		model.addAttribute("heartChk", heartChk);
	}
	
	//좋아요 추가 / 게시판 좋아요 수 증가
	public void heartInsertAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - heartInsertAction");
		
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart")); 

		//게시판 하트 증가
		BoardDTO board = new BoardDTO();
		board.setBoard_num(board_num);
		board.setBoard_category(board_category);
		board.setBoard_heart(board_heart);
		
		// 하트 추가
		HeartDTO heart = new HeartDTO();
		heart.setUserID(sessionID);
		heart.setBoard_num(board_num);
		heart.setBoard_category(board_category);
		
		dao.modHeartCount(board);	
		dao.insertHeart(heart);
	}
	
	//좋아요 삭제  / 게시판 좋아요 감소
	public void heartDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - heartDeleteAction");
		// sessionID 있다고 가정
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart"));
		
		//게시판 하트 감소
		BoardDTO board = new BoardDTO();
		board.setBoard_num(board_num);
		board.setBoard_category(board_category);
		board.setBoard_heart(board_heart);
		
		// 하트 삭제
		HeartDTO heart = new HeartDTO();
		heart.setUserID(sessionID);
		heart.setBoard_num(board_num);
		heart.setBoard_category(board_category);
		
		dao.modHeartCount(board);	
		dao.delHeart(heart);
	}
	
	// 게시글 작성처리
	@Override
	public void boardInsertAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - boardInsertAction");
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		//이미지 변환
		ImageNameChange inc= new ImageNameChange(); //이미지경로 C / D 바꾸기
		inc.imageName(request);
		
		//param
		String title = request.getParameter("board_title");
		String category = request.getParameter("board_category");
		String content = request.getParameter("board_content");
		String thumnail = inc.getThumnail(); 
		String image = inc.getImage(); 
		
		System.out.println("thumnail:" + thumnail);
		System.out.println("image:" + image);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_category(category);
		dto.setBoard_title(title);
		dto.setBoard_content(content);
		dto.setBoard_thumnail(thumnail);
		dto.setBoard_image(image);
		dto.setBoard_writer(sessionID);
		
		int insertCnt = dao.insertBoard(dto);
		
		model.addAttribute("insertCnt", insertCnt);
		model.addAttribute("category", category); //이동할 게시판 카테고리 판단
	}
	
	// 게시글 수정처리
	@Override
	public void boardUpdateAction(MultipartHttpServletRequest request, Model model)
			throws ServletException, IOException {
		
		System.out.println("서비스 - boardUpdateAction");
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		//param
		int num = Integer.parseInt(request.getParameter("hiddenNum"));
		String category = request.getParameter("hiddenCategory");
		
		//이미지 변환
		UpdateImageName inc= new UpdateImageName();
		inc.imageName(request);
		
		//수정사항
		String title = request.getParameter("board_title");
		String content = request.getParameter("board_content");
		String thumnail = inc.getThumnail(); 
		String image = inc.getImage(); 
		
		System.out.println("thumnail:" + thumnail);
		System.out.println("image:" + image);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_num(num);
		dto.setBoard_category(category);
		dto.setBoard_title(title);
		dto.setBoard_content(content);
		dto.setBoard_thumnail(thumnail);
		dto.setBoard_image(image);
		dto.setBoard_writer(sessionID);
		
		dao.updateBoard(dto);
		
		model.addAttribute("category", category);
	}
	// 게시글 삭제처리
	@Override
	public void boardDeleteAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - boardDeleteAction");
		String category = request.getParameter("board_category");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("board_num", board_num);
		dao.deleteBoard(map);
		
		model.addAttribute("category", category);
	}
	
	// 댓글 목록조회
	@Override
	public void commentListAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - commentListAction");
		
		String pageNum = request.getParameter("pageNum");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("board_num", board_num);
		List<CommentDTO> list = dao.cmtList(map); // 댓글목록
		
		model.addAttribute("category", category);
		model.addAttribute("list", list);
		model.addAttribute("pageNum", pageNum);
	}
	
	// 댓글 작성처리
	@Override
	public void commentAddAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - commentAddAction");
		
		int num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		String userID = (String)request.getSession().getAttribute("sessionID");
		String content = request.getParameter("content");
		
		CommentDTO dto = new CommentDTO();
		dto.setBoard_num(num);
		dto.setBoard_category(category);
		dto.setUserID(userID);
		dto.setContent(content);
		
		dao.cmtInsert(dto);
	}
	
	//댓글 한건조회
	public void commentMod(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - commentMod");
		
		String pageNum = request.getParameter("pageNum");
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		String board_category = request.getParameter("board_category");
		
		CommentDTO dto = new CommentDTO();
		dto.setComment_num(comment_num);
		dto.setBoard_category(board_category);
		
		dto = dao.cmtSelect(dto);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
	}
	
	// 댓글 수정처리
	@Override
	public void commentModAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - commentModAction");
		
		String pageNum = request.getParameter("h_pageNum");
		System.out.println("pageNum: " + pageNum);
		int comment_num = Integer.parseInt(request.getParameter("h_comment_num"));
		int board_num = Integer.parseInt(request.getParameter("h_board_num"));
		String board_category = request.getParameter("h_category");
		String content = request.getParameter("content");
		
		CommentDTO dto = new CommentDTO();
		dto.setComment_num(comment_num);
		dto.setContent(content);
		dto.setBoard_category(board_category);
		
		dao.cmtUpdate(dto);
		
		model.addAttribute("pageNum", pageNum);// 목록으로 돌아가기
		model.addAttribute("board_num", board_num);
		model.addAttribute("board_category", board_category);
	}
	// 댓글 삭제처리
	@Override
	public void commentDelAction(HttpServletRequest request, Model model)
			throws ServletException, IOException {
		System.out.println("서비스 - commentDelAction");
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_category = request.getParameter("board_category");
		String pageNum = request.getParameter("pageNum");

		Map<String, Object> map = new HashMap<>();
		map.put("comment_num", comment_num);
		map.put("category", board_category);
		
		dao.cmtDelete(map); // 댓글삭제
		
		model.addAttribute("pageNum", pageNum); // 목록으로 돌아가기
		model.addAttribute("board_num", board_num);
		model.addAttribute("board_category", board_category);
	}

}
