package com.spring.board.service;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.PageRequestDto;
import com.spring.board.dto.PageResponseDto;
import com.spring.board.entity.Board;

public interface BoardService {
	public void createBoard(BoardDto.BoardRequest request, String userName);
	public BoardDto.BoardResponse findBoardByBoardNo(Long boardNo);
	public void updateBoard(Long boardNo, BoardDto.BoardRequest request);
	public void deleteBoard(Long boardNo);
	public PageResponseDto<BoardDto, Board> getBoardList(PageRequestDto request);
}
