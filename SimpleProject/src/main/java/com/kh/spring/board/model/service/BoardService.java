package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.reply.dto.ReplyDTO;

public interface BoardService {

	// 게시글 작성(파일첨부)
	void insertBoard(BoardDTO board, MultipartFile file, HttpSession session);
	/*
	 * insertBoard();
	 * save();
	 * 
	 */
	
	// 게시글 목록조회
	List<BoardDTO> selectBoardList(int currentPage);
	/*
	 * selectBoardList();
	 * selectAll();
	 * findAll();
	 */
	
	// 게시글 상세보기(댓글도 같이 조회)
	BoardDTO selectBoard(int boardNo);
	/*
	 * selectBoard();
	 * findByXXX(); 
	 */
	
	// 게시글 수정
	BoardDTO updateBoard(BoardDTO board, MultipartFile file);
	/*
	 * updateBoard();
	 * updateByXXX();
	 */
	
	// 게시글 삭제(딜리트인척하고 업데이트 할 것 STATUS컬럼값 N으로 바꿀것)
	void deleteBoard(int boardNo);

	void doSearch(int boardNo);
	
	// --------1절
	// 게시글 검색 기능
	Map<String, Object> doSearch(Map<String, String> map);

	int insertReply(ReplyDTO reply, HttpSession session);
	
	// 댓글작성
}
