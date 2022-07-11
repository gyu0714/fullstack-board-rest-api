package com.spring.board.service;

import java.util.List;

import org.springframework.validation.Errors;

import com.spring.board.dto.UserDto;

public interface UserService {
	public void joinUser(UserDto.UserRequest request, Errors error);
	public List<UserDto.UserResponse> findAllUser();
	public UserDto.UserResponse findUserByEmail(String userEmail);
	public void updateUser(String userEmail, UserDto.UserRequest request);
	public void deleteUser(String userEmail);
}
