package com.spring.board.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardDto.BoardRequest;
import com.spring.board.dto.BoardDto.BoardResponse;
import com.spring.board.dto.PageRequestDto;
import com.spring.board.dto.PageResponseDto;
import com.spring.board.entity.Board;
import com.spring.board.entity.User;
import com.spring.board.exception.ResourceNotFoundException;
import com.spring.board.repository.BoardRepository;
import com.spring.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardReposiory;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void createBoard(BoardRequest request, String userName) {
		User user = userRepository.findByUserName(userName);
		request.setUser(user);
		Board board = request.toEntity();
		boardReposiory.save(board);
	}


	@Override
	@Transactional(readOnly = true)
	public BoardResponse findBoardByBoardNo(Long boardNo) {
		Board board = boardReposiory.findById(boardNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 게시판 없음"));
		return new BoardDto.BoardResponse(board);
	}

	@Override
	@Transactional
	public void updateBoard(Long boardNo, BoardRequest request) {
		Board board = boardReposiory.findById(boardNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 게시판 없음"));
		board.updateBoard(request.getBoardTitle(), request.getBoardContent());
	}

	@Override
	@Transactional
	public void deleteBoard(Long boardNo) {
		Board board = boardReposiory.findById(boardNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 게시판 없음"));
		boardReposiory.delete(board);
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponseDto<BoardDto, Board> getBoardList(PageRequestDto request) {
		Pageable pageable = request.getPageable(Sort.by("modifiedDate").descending());
		Page<Board> result = boardReposiory.findAll(pageable);
		
		Function<Board, BoardDto> function = (Board -> Board.toDto(Board));
		return new PageResponseDto<BoardDto, Board>(result, function);
	}


	
}
