package com.spring.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {
	
	@Id
	@Column(name = "user_email", nullable = false)
	private String userEmail;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	private String userPassword;
	
	/* 유저 수정 */
	public void modifyUser(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
}
