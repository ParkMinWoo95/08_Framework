package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	/*
	@RequestMapping(value="login")
	public String login(HttpServletRequest request) {
		// System.out.println("로그인 요청됨");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		// System.out.println("id : " + id + ", pw : " + pw);
		// System.out.printf("id : %s, pw : %s", id, pw);
		log.info("id : {}, pw : {}", id, pw);
		return "main_page";
	}
	
	@RequestMapping("login")
	public String login(@RequestParam(value="id", defaultValue="abcde") String id,
						@RequestParam(value="pw") String pw) {
		log.info("이렇게도 넘어오나요? id : {} / pw : {}", id, pw);
		
		return "main_page";
	}
	
	@PostMapping("login")
	public String login(String id, String pw) {
		
		log.info("이렇게도 넘어오나요? id : {} / pw : {}", id, pw);
		
		MemberDTO member = new MemberDTO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		return "main_page";
	}
	*/
	
	/**
	 * 커맨드 객체 방식
	 * 
	 * 1. 매개변수의 자료형의 반드시 기본생성자가 존재할 것
	 * 2. 전달되는 키값과 객체의 필드명이 동일할 것
	 * 3. setter메서드가 반드시 존재할 것
	 * 
	 * 스프링에서 해당 객체를 기본생성자를 통해서 생성한 후 내부적으로 setter메서드를 찾아서 요청 시 전달값을 해당 필드에 대입해줌
	 * 
	 */
	@PostMapping("login")
	public String login(MemberDTO member) {
		
		log.info("이런건 안돼요 {}", member);
		
		return "main_page";
	}
	
	
	
	
	
}
