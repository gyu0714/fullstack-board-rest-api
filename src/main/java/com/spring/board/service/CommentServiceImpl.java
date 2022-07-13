package com.spring.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dto.CommentDto;
import com.spring.board.dto.CommentDto.CommentRequest;
import com.spring.board.dto.CommentDto.CommentResponse;
import com.spring.board.entity.Board;
import com.spring.board.entity.Comment;
import com.spring.board.exception.ResourceNotFoundException;
import com.spring.board.repository.BoardRepository;
import com.spring.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	@Override
	@Transactional
	public void createComment(CommentRequest request, Long boardNo) {
		Board board = boardRepository.findById(boardNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 게시판 없음"));
		request.setBoard(board);
		Comment comment = request.toEntity();
		commentRepository.save(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public CommentResponse findCommentByCommentNo(Long commentNo) {
		Comment comment = commentRepository.findById(commentNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 댓글 없음"));
		return new CommentDto.CommentResponse(comment);
	}

	@Override
	@Transactional
	public void updateComment(Long commentNo, CommentRequest request) {
		Comment comment = commentRepository.findById(commentNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 댓글 없음"));
		comment.updateComment(request.getCommentContent());
	}

	@Override
	@Transactional
	public void deleteComment(Long commentNo) {
		Comment comment = commentRepository.findById(commentNo).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 댓글 없음"));
		commentRepository.delete(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentResponse> findAllComment(Long boardNo) {
		List<CommentResponse> commentList = commentRepository
				.findAll()
				.stream()
				.map(CommentDto.CommentResponse::new)
				.filter(comment -> comment.getBoardNo() == boardNo)
				.collect(Collectors.toList());
				
		return commentList;
	}

}
