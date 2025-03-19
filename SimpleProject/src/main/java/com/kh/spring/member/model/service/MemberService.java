package com.kh.spring.member.model.service;

import com.kh.spring.member.model.dto.MemberDTO;

public interface MemberService {

	// 로그인
	MemberDTO login(MemberDTO member);
	
	// 회원가입
	// 좋은 방법 : 가입된 회원의 정보를 반환해준다. (Hibernate)
	// 일반적인 방법 : 정수값을 반환하거나 값을 반환하지 않는다. (MyBatis)
	void signUp(MemberDTO member);
	
	// 회원정보수정
	MemberDTO update(MemberDTO member);
	
	// 회원탈퇴
	
	int delete(MemberDTO member);
	
	// 1절 끝
	
	// 아이디 중복체크
	
	// 2절
	
	// 이메일인증... 시간되면...
}
