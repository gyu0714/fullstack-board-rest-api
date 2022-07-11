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

import com.spring.board.dto.CommentDto.CommentRequest;
import com.spring.board.dto.CommentDto.CommentResponse;
import com.spring.board.service.CommentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentRestController {
	
	private final CommentServiceImpl commentService;
	
	/* CREATE */ 
	@PostMapping("/comment/{boardNo}")
	public ResponseEntity<CommentRequest> createComment(@PathVariable Long boardNo,
			@RequestBody CommentRequest request) {
		commentService.createComment(request, boardNo);
		
		return ResponseEntity.ok(request);
	}
	
	/* READ BY CommentNo */
	@GetMapping("/comment/{commentNo}")
	public ResponseEntity<CommentResponse> findCommentByCommnetNo(@PathVariable Long commentNo) {
		return ResponseEntity.ok(commentService.findCommentByCommentNo(commentNo));
	}
	
	/* UDATE */
	@PutMapping("/comment/{commentNo}")
	public ResponseEntity<CommentRequest> updateComment(@PathVariable Long commentNo,
			@RequestBody CommentRequest request) {
		commentService.updateComment(commentNo, request);
		return ResponseEntity.ok(request);
	}
	
	/* DELETE */
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<Long> deleteComment(@PathVariable Long commentNo) {
		commentService.deleteComment(commentNo);
		return ResponseEntity.ok(commentNo);
	}
}
