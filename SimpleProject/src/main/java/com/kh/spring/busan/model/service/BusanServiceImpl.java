package com.kh.spring.busan.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.client.RestTemplate;

import com.kh.spring.busan.model.dto.Comment;
import com.kh.spring.busan.model.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
	
	private final CommentMapper mapper;
	
	private final String SERVICE_KEY = "";
	
	private String apiRequest(String uriPath) {
		URI uri = null;
		try {
			uri = new URI(uriPath);
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
		String apiResponseData = new RestTemplate().getForObject(uri, String.class);
		return apiResponseData;
	}
	
	@Override
	public String RequestGetBusan(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String requestGetBusanDetail(int pk) {
		
		if(pk <1) {return null;}
		StringBuilder sb = new StringBuilder();
		sb.append("http://apis.data.go.kr/6260000/FoodeService/getFoodKr");
		sb.append("?serviceKey=" + SERVICE_KEY);
		sb.append("&pageNo=1");
		sb.append("&numOfRows=1");
		sb.append("&resultType=json");
		sb.append("&UC_SEQ=" + pk);
		
		return apiRequest(sb.toString());
	}


	@Override
	public void saveComment(Comment comment) {
		mapper.saveComment(comment);
	}
	
	@Override
	public Comment selectCommentList(Long seq) {
		return mapper.selectCommentList(seq);
	}

	
}
