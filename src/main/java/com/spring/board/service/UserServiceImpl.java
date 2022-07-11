package com.spring.board.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserDto.UserRequest;
import com.spring.board.dto.UserDto.UserResponse;
import com.spring.board.entity.User;
import com.spring.board.exception.ResourceNotFoundException;
import com.spring.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void joinUser(UserRequest request, Errors error) {
		User user = request.toEntity();
		if(userRepository.existsByUserEmail(request.toEntity().getUserEmail())) {
			error.rejectValue("email", "이미 사용중인 이메일 입니다.");
		} else {
			userRepository.save(user);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> findAllUser() {
		List<UserResponse> userList = userRepository
				.findAll()
				.stream()
				.map(UserDto.UserResponse::new)
				.collect(Collectors.toList());
		return userList;
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findUserByEmail(String userEmail) {
		User user = userRepository.findByUserEmail(userEmail).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 유저 없음" + "userEmail"));
		
		return new UserDto.UserResponse(user);
	}

	@Override
	@Transactional
	public void updateUser(String userEmail, UserRequest request) {
		User user = userRepository.findByUserEmail(userEmail).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 유저 없음" + "userEmail"));
		
		user.modifyUser(request.getUserName(), request.getUserPassword());
	}

	@Override
	@Transactional
	public void deleteUser(String userEmail) {
		User user = userRepository.findByUserEmail(userEmail).orElseThrow(
				() -> new ResourceNotFoundException("해당하는 유저 없음" + "userEmail"));
		
		userRepository.delete(user);
	}

}
