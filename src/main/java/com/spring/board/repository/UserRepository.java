package com.spring.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.board.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	// email 유효성 검사
	boolean existsByUserEmail(String userEmail);

	Optional<User> findByUserEmail(String userEmail);

	User findByUserName(String userName);

}
