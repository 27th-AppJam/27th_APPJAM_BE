package com.appjam.appjam.domain.board.controller;
import com.appjam.appjam.domain.board.entity.enums.BoardType;
import com.appjam.appjam.domain.board.request.CreateEditBoardRequest;
import com.appjam.appjam.domain.board.request.ReviewRequest;
import com.appjam.appjam.domain.board.response.BoardDetailView;
import com.appjam.appjam.domain.board.response.BoardView;
import com.appjam.appjam.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
public class BoardController {
    private final BoardService boardService;
    @GetMapping
    public List<BoardView> getBoards(@RequestParam BoardType boardType) {
        return boardService.getAllBoards(boardType);
    }
    @GetMapping("/{boardId}")
    public BoardDetailView getBoard(@PathVariable("boardId")Long boardId) {
        return boardService.getBoard(boardId);
    }
    @PostMapping("/post")
    public void postBoard(@RequestBody CreateEditBoardRequest request) {
        boardService.createBoard(request);
    }
    @PutMapping("/{boardId}")
    public void putBoard(@PathVariable("boardId") Long boardId, @RequestBody CreateEditBoardRequest request) {
        boardService.editBoard(request, boardId);
    }
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }
    @PostMapping("/review")
    public void createReview(@RequestBody ReviewRequest request) {
        boardService.createReview(request.getBoardId(), request.getContent());
    }
    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        boardService.deleteReview(reviewId);
    }
}
