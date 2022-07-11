package com.spring.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
