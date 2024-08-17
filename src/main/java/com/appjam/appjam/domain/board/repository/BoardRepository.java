package com.appjam.appjam.domain.board.repository;

import com.appjam.appjam.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}