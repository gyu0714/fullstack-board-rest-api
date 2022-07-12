package com.spring.board.dto;

import com.spring.board.entity.Board;
import com.spring.board.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class CommentDto {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class CommentRequest {
		private Long commnetNo;
		private String commenter;
		private String commentContent;
		private String registredDate;
		private String modifiedDate;
		private Board board;
		
		/* DTO -> Entity*/
		public Comment toEntity() {
			Comment comment = Comment.builder()
					.commentNo(commnetNo)
					.commenter(commenter)
					.commentContent(commentContent)
					.board(board)
					.build();
			return comment;
		}
	}
	
	/**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
	
		@Getter
		public static class CommentResponse {
			private final Long commentNo;
			private final String commenter;
			private final String commentContent;
			private final String registredDate;
			private final String modifiedDate;
			private final Long BoardNo;
			
			/** Entity -> DTO*/
			public CommentResponse(Comment comment) {
				this.commentNo = comment.getCommentNo();
				this.commenter = comment.getCommenter();
				this.commentContent = comment.getCommentContent();
				this.registredDate = comment.getRegistredDate();
				this.modifiedDate = comment.getModifiedDate();
				this.BoardNo = comment.getBoard().getBoardNo();
			}
		}
}
