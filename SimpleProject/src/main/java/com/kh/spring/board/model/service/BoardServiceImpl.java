package com.kh.spring.board.model.service;

import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.mapper.BoardMapper;
import com.kh.spring.exception.AuthenticationException;
import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.reply.dto.ReplyDTO;
import com.kh.spring.util.model.dto.PageInfo;
import com.kh.spring.util.template.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;

	@Override
	public void insertBoard(BoardDTO board, MultipartFile file, HttpSession session) {
		
		// 1. 권한 체크
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		if(loginMember != null && loginMember.getMemberId().equals(board.getBoardWriter())) {
			throw new AuthenticationException("권한 없는 접근이다에요.");
		}
		
		// 2. 유효성 검사
		if(board.getBoardTitle() == null || board.getBoardTitle().trim().isEmpty() ||
		   board.getBoardContent() == null || board.getBoardContent().trim().isEmpty() ||
		   board.getBoardWriter() == null || board.getBoardWriter().trim().isEmpty()) {
			throw new InvalidParameterException("유효하지 않는 요청이다에요");
		}
		
		// 2_2)
		
		// 3) 파일유무체크 // 이름바꾸기 + 저장
		if(!file.getOriginalFilename().isEmpty()) {
			
			// 이름바꾸기
			
			// 현재시간 + 랜덤숫자 + 원본파일확장자
			
			StringBuilder sb = new StringBuilder();
			sb.append("KH_");
			// log.info("현재시간 : {}", new Date());
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			// log.info("현재시간 : {}", currentTime);
			sb.append(currentTime);
			sb.append("_");
			int random = (int)(Math.random() * 900) + 100;
			sb.append(random);
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			sb.append(ext);
			// System.out.println("바뀐 파일명 : {}" + sb.toString());
			ServletContext application = session.getServletContext();
			
			String savePath = application.getRealPath("/resources/upload_files");
			
			try {
				file.transferTo(new File(savePath + sb.toString()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			board.setChangeName("/spring/resources/upload_files/" + sb.toString());
		}
		
		boardMapper.insertBoard(board);
			

	}

	@Override
	public List<BoardDTO> selectBoardList(int currentPage) {
		
		boardMapper.selectTotalCount();
		return null;
	}

	@Override
	public BoardDTO selectBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO updateBoard(BoardDTO board, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBoard(int boardNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSearch(int boardNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> doSearch(Map<String, String> map) {
		// map에서 get("condition") / get("keyword") 값이 비었나 안비었나 했다 칩시다
	
		int sarchedCount = boardMapper.searchedCount(map);
		 
		PageInfo pi = Pagination.getPageInfo(sarchedCount,
				 							  Integer.parseInt(map.get("currentPage")), 
				 							  3, 3);
		 
		RowBounds rb = new RowBounds((pi.getCurrentPage() - 1) * 3 , 3);
		 
		List<BoardDTO> boards = boardMapper.selectSearchList(map, rb);
		 
		Map<String, Object> returnValue = new HashMap();
		returnValue.put("boards", boards);
		returnValue.put("pageInfo", pi);
		
		return returnValue;
	}
	
	public int insertReply(ReplyDTO reply, HttpSession session) {
		
		String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();
		reply.setReplyWriter(memberId);
		
		return boardMapper.insertReply(reply);
	}

}
