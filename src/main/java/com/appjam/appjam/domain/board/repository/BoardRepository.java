package com.appjam.appjam.domain.board.repository;

import com.appjam.appjam.domain.board.entity.Board;
import com.appjam.appjam.domain.board.entity.enums.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByBoardType(BoardType boardType);
}