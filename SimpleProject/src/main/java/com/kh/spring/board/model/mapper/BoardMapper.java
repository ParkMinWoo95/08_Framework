package com.kh.spring.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.reply.dto.ReplyDTO;

@Mapper
public interface BoardMapper {
	
	void insertBoard(BoardDTO board);
	
	@Select("SELECT COUNT(*) FROM TB_SPRING_BOARD WHERE STATUS = 'Y'")
	int selectTotalCount();
	
	List<BoardDTO> selectBoardList(RowBounds rowBounds);
	
	BoardDTO selectBoard(int boardNo);
	
	int updateBoard(BoardDTO board);
	
	int deleteBoard(int boardNo);
	
	// ------- 1절
	
	// 2절 시작
	
	int searchedCount(Map<String, String> map);
	
	List<BoardDTO> selectSearchList(Map<String, String> map, RowBounds rb);

	
	int insertReply(ReplyDTO reply);
}
