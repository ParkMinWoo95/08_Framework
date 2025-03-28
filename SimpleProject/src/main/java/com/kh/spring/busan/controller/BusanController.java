package com.kh.spring.busan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.controller.BoardController;
import com.kh.spring.board.model.mapper.BoardMapper;
import com.kh.spring.busan.model.dto.Comment;
import com.kh.spring.busan.model.service.BusanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BusanController {
	
	private final BusanService busanService;
	
	/*
	@GetMapping
	public ResponseEntity<String> getBusanFoods(@RequestParam(name="page", defaultValue="1")){
		
	}
	*/
	
	// 2절
	@GetMapping("/{id}")
	public ResponseEntity<String> getBusanDetail(@PathVariable(name="id") int id){
		log.info("식당 번호 : {}", id);
		String response = busanService.requestGetBusanDetail(id);
		return ResponseEntity.ok(response);
	}
	
	// 3절
	@PostMapping("/comments")
	public ResponseEntity<?> save(@RequestBody Comment comment){
		//log.info("{}", comment);
		busanService.saveComment(comment);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable(name="id") Long id){
		List<Comment> comments = busanService.selectCommentList(id);
		return ResponseEntity.ok(comments);
	}
	
}
