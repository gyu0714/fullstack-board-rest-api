package com.spring.board.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board extends BaseTimeEntity implements Persistable<Long>{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no")
	private Long boardNo;
	
	@Column(name = "board_title")
	private String boardTitle;
	
	@Column(name = "board_content")
	private String boardContent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_email")
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("commentNo asc")
	@JsonIgnore
	private List<Comment> comments;
	
	/* 댓글 수정 */
	public void updateBoard(String boardTitle, String boardContent) {
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}
	
	public BoardDto toDto(Board board) {
		BoardDto boardDto = BoardDto.builder()
				.boardNo(board.getBoardNo())
				.boardTitle(board.getBoardTitle())
				.boardContent(board.getBoardContent())
				.registredDate(board.getRegistredDate())
				.modifiedDate(board.getModifiedDate())
				.user(board.getUser())
				.comments(board.getComments())
				.build();
		return boardDto;
	}
	
	@Override
	public Long getId() {
		return boardNo;
	}

	@Override
	public boolean isNew() {
		return this.getRegistredDate() == null;
	}
}
