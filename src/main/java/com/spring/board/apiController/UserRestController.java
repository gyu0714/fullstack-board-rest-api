package com.spring.board.apiController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserDto.UserRequest;
import com.spring.board.dto.UserDto.UserResponse;
import com.spring.board.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {
	
	private final UserServiceImpl userService;
	
	/* CREATE */ 
	@PostMapping("/users")
	public ResponseEntity<UserRequest> joinUser(@RequestBody UserDto.UserRequest request, Errors error) {
		userService.joinUser(request, error);
		return ResponseEntity.ok(request);
	}
	
	/* READ BY EMAIL */
	@GetMapping("/users/{userEmail}")
	public ResponseEntity<UserResponse> findUserByEmail(@PathVariable String userEmail) {
		return ResponseEntity.ok(userService.findUserByEmail(userEmail));
	}
	
	/* READ ALL */
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> findAllUser() {
		return ResponseEntity.ok(userService.findAllUser());
	}
	
	/* UDATE */
	@PutMapping("/users/{userEmail}")
	public ResponseEntity<String> updateUser(@PathVariable String userEmail,
			@RequestBody UserDto.UserRequest request) {
		userService.updateUser(userEmail, request);
		return ResponseEntity.ok(userEmail);
	}
	
	/* DELETE */
	@DeleteMapping("/users/{userEmail}")
	public ResponseEntity<String> deleteUser(@PathVariable String userEmail) {
		userService.deleteUser(userEmail);
		return ResponseEntity.ok(userEmail);
	}
	
	
	

}
