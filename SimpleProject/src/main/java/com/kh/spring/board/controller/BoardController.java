package com.kh.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.exception.InvalidParameterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("boards")
	public String toBoardList(@RequestParam(name="page") int page) {
		
		// 한 페이지의 글 개수 == 5
		// 버튼 개수 == 5
		// 총 게시글 개수 == SELECT COUNT(*) FROM TB_SPRING_BOARD WHERE STATUS = 'Y'
		if(page < 1) {
			throw new InvalidParameterException("잘못됐다에요");
		}
		
		boardService.selectBoard(page);
		
		return "board/board_list";
	}
	
	@GetMapping("form.bo")
	public String gotoForm() {
		
		
		
		return "board/insert_board";
	}
	
	@PostMapping("boards")
	public ModelAndView newBoard(ModelAndView mv, BoardDTO board, MultipartFile upfile, HttpSession session) {
		
		log.info("게시글정보 : {}, 파일정보 : {}", board, upfile);
		
		// 첨부파일의 존재유무
		// => 차이점 => MultipartFile타입의 filename 필드값으로 확인을 하겠다.
		
		// INSERT INTO TB_SPRING_BOARD(BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER,
		// 							   CHANGE_NAME)
		//				VALUES(#{boardTitle}, #{boardContent}, #{boardWriter}, #{changeName});
		
		// 1. 권한있는 요청인가
		// 2. 값들이 유효성이 있는 값인가
		
		boardService.insertBoard(board, upfile, session);
		mv.setViewName("redirect:boards");
		session.setAttribute("message", "게시글 등록했다에요");
		return mv;
	}
	
	@GetMapping("search")
	public ModelAndView doSearch(@RequestParam(name="condition") String condition,
								 @RequestParam(name="keyword") String keyword, 
								 @RequestParam(name="page", defaultValue="1") int page,
								 ModelAndView mv){
		
		Map<String, String> map = new HashMap();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("currentpage", String.valueOf(page));
		
		Map<String, Object> list = boardService.doSearch(map);
		mv.addObject("map", list).setViewName("board/board_list");
		
		return mv;
	}
	
	
	
	
}
