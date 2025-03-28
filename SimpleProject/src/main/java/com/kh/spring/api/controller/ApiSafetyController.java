package com.kh.spring.api.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.api.model.service.SafetyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(produces="application/json; charset=UTF-8")
public class ApiSafetyController {
	
	private SafetyService safetyService;

	@ResponseBody
	@GetMapping(value = "hospitals")
	public String RequestHospitalApi() throws IOException {
		String responseData = safetyService.requestHospitalApi();
		return responseData;
	}
	
	@ResponseBody
	@GetMapping(value="message")
	public String requestMessage(@RequestParam(name="pageNo") int pageNo) {
		return safetyService.requestMessage(pageNo);
	}
	
	@GetMapping("naver-shopping")
	public String getItems(@RequestParam(name="query") String query) {
		return safetyService.getItems(query);
	}
	
}
