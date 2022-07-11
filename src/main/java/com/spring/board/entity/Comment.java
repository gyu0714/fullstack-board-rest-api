package com.spring.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends BaseTimeEntity implements Persistable<Long>{

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_no")
	private Long commentNo;
	
	private String commenter;
	
	@Column(name = "commentContent")
	private String commentContent;
	
	@ManyToOne
	@JoinColumn(name = "board_no")
	private Board board;
	
	/* 댓글 수정 */
	public void updateComment(String commentContent) {
		this.commentContent = commentContent;
	}

	@Override
	public Long getId() {
		return commentNo;
	}

	@Override
	public boolean isNew() {
		return this.getRegistredDate() == null;
	}
}
