package com.spring.board.service;

import com.spring.board.dto.CommentDto.CommentRequest;
import com.spring.board.dto.CommentDto.CommentResponse;

public interface CommentService {
	public void createComment(CommentRequest request, Long boardNo);
	public CommentResponse findCommentByCommentNo(Long commentNo);
	public void updateComment(Long commentNo, CommentRequest request);
	public void deleteComment(Long commentNo);
}
