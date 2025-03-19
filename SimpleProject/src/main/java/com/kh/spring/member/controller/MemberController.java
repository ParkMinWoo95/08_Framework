package com.kh.spring.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor // 의존성 주입 생성자를 생성해주는 애노테이션
public class MemberController {
	
	// @Autowired 
	private final MemberService memberService;
	
	/*
	 @Autowired
	 public void setMemberService(MemberService memberService){
	 		this.memberService = memberService;
	  }
	 */
	
	/*
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
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
	 * (Setter Injection)
	 * 
	 */
	/*
	@PostMapping("login")
	public String login(MemberDTO member, 
						HttpSession session,
						Model model) {
		
		// log.info("이런건 안돼요 {}", member);
		
		/* 데이터가공 => 패스
		 * 요청처리 =>
		 * 응답화면지정
		 */
		// MemberDTO loginMember = memberService.login(member);
		/*
		if(loginMember != null) {
			log.info("로그인성공");
		} else {
			log.info("로그인실패");
		}
		*/
		
		// if(loginMember != null) { // 성공
			// sessionScope에 로그인 정보를 담아줌
			// session.setAttribute("loginMember", loginMember);
			// main_page
			// WEB-INF/views/
			// .jsp
			// => 포워딩
			// sendRedirect
			
			// localhost/spring
			
			// return "redirect:/";
			
		// } else { // 실패
			
			// error_page
			// requestScope에 에러문구를 담아서 포워딩
			// Spring에서는 Model객체를 이용해서 RequestScope에 값을 담음
			// model.addAttribute("message", "로그인 실패");
			
			// forwarding
			// /WEB-INF/views/
			// include/error_page
			// .jsp
			// return "include/error_page";
		// }
		
		// return "main_page";
	// }
	
	// 두번째 방법 반환타입 ModelAndView로 돌아가기
	@PostMapping("login")
	public ModelAndView login(MemberDTO member,
							  HttpSession session,
							  ModelAndView mv) {
		MemberDTO loginMember = memberService.login(member);
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("message","로그인실패").setViewName("include/error_page");
		}
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session,
							   ModelAndView mv) {
		session.removeAttribute("loginMember");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@GetMapping("signup-form")
	public String signupForm() {
		return "member/signup-form";
	}
	
	
	@PostMapping("signup")
	public String join(MemberDTO member) {
		/*
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		
		log.info("멤버 필드 찍어보기 : {}", member);
		memberService.signUp(member);
		return "main_page";
	}
	
	
	
}
