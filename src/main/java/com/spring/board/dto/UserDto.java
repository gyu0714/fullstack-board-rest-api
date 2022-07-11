package com.spring.board.dto;

import com.spring.board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class UserDto {
	
	/* 회원 Service 요청(Request) DTO class*/
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class UserRequest {
		private String userEmail;
		private String userName;
		private String userPassword;
		
		/* DTO -> Entity */
		public User toEntity() {
			User user = User.builder()
					.userEmail(userEmail)
					.userName(userName)
					.userPassword(userPassword)
					.build();
			return user;
		}
	}
	
	@Getter
	public static class UserResponse {
		private final String userEmail;
		private final String userName;
		private final String userPassword;
		private final String registredDate;
		private final String modifiedDate;
		
		/* Entity -> DTO */
		public UserResponse(User user) {
			this.userEmail = user.getUserEmail();
			this.userName = user.getUserName();
			this.userPassword = user.getUserPassword();
			this.registredDate = user.getRegistredDate();
			this.modifiedDate = user.getModifiedDate();
		}
	}
}
