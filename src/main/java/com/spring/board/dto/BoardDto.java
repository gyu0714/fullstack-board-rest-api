package com.spring.board.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.board.entity.Board;
import com.spring.board.entity.Comment;
import com.spring.board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String registredDate;
	private String modifiedDate;
	private User user;
	private List<Comment> comments;

	/* 게시글 Service 요청(Request) DTO class*/
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class BoardRequest {
		private Long boardNo;
		private String boardTitle;
		private String boardContent;
		private String registredDate;
		private String modifiedDate;
		private User user;
		
		/* DTO -> Entity */
		public Board toEntity() {
			Board board = Board.builder()
					.boardNo(boardNo)
					.boardTitle(boardTitle)
					.boardContent(boardContent)
					.user(user)
					.build();
			return board;
		}
	}
	
	
	@Getter
	public static class BoardResponse {
		private final Long boardNo;
		private final String boardTitle;
		private final String boardContent;
		private final String registredDate;
		private final String modifiedDate;
		private final String userEmail;
		private final List<CommentDto.CommentResponse> comments;
		
		/** Entity -> DTO */
		public BoardResponse(Board board) {
			this.boardNo = board.getBoardNo();
			this.boardTitle = board.getBoardTitle();
			this.boardContent = board.getBoardContent();
			this.registredDate = board.getRegistredDate();
			this.modifiedDate = board.getModifiedDate();
			this.userEmail = board.getUser().getUserEmail();
			this.comments = board.getComments().stream().map(CommentDto.CommentResponse::new).collect(Collectors.toList());
		}
	}
}
