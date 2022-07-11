package com.spring.board.apiController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardDto.BoardRequest;
import com.spring.board.dto.BoardDto.BoardResponse;
import com.spring.board.dto.PageRequestDto;
import com.spring.board.dto.PageResponseDto;
import com.spring.board.entity.Board;
import com.spring.board.service.BoardServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardRestController {

	private final BoardServiceImpl boardService;
	
	/* CREATE */ 
	@PostMapping("/board/{userName}")
	public ResponseEntity<BoardRequest> createBoard(@PathVariable String userName,
			@RequestBody BoardRequest request) {
		boardService.createBoard(request, userName);
		
		return ResponseEntity.ok(request);
	}
	
	/* READ BY BOARDNO */
	@GetMapping("/board/{boardNo}")
	public ResponseEntity<BoardResponse> findBoardByBoardNo(@PathVariable Long boardNo) {
		return ResponseEntity.ok(boardService.findBoardByBoardNo(boardNo));
	}
	
	/* LIST ALL PAGING */
	@GetMapping("/board")
	public PageResponseDto<BoardDto, Board> getBoard(PageRequestDto request) {
		PageResponseDto<BoardDto, Board> response = boardService.getBoardList(request);
		
		return response;
	}
	
	/* UDATE */
	@PutMapping("/board/{boardNo}")
	public ResponseEntity<Long> updateBoard(@PathVariable Long boardNo, 
			@RequestBody BoardDto.BoardRequest request) {
		boardService.updateBoard(boardNo, request);
		
		return ResponseEntity.ok(boardNo);
	}
	
	/* DELETE */
	@DeleteMapping("/board/{boardNo}")
	public ResponseEntity<Long> deleteBoard(@PathVariable Long boardNo) {
		boardService.deleteBoard(boardNo);
		return ResponseEntity.ok(boardNo);
	}
 }
