package com.spring.board.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class BaseTimeEntity {
	
	@CreatedDate
	@Column(name = "registered_date", nullable = false)
	private String registredDate;
	
	@LastModifiedDate
	@Column(name = "modified_date", nullable = false)
	private String modifiedDate;
	
	/* 해당 엔티티를 저장하기 전에 실행 */
	@PrePersist
	public void onPrePersist() {
		this.registredDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.modifiedDate = this.registredDate;
	}
	
	/* 해당 엔티티를 업데이트 하기 전에 실행 */
	@PreUpdate
	public void onPreUpdate() {
		this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
	}
}
