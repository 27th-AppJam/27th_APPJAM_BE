package com.appjam.appjam.domain.board.service;

import com.appjam.appjam.domain.auth.entity.User;
import com.appjam.appjam.domain.auth.service.AuthService;
import com.appjam.appjam.domain.board.entity.Board;
import com.appjam.appjam.domain.board.entity.Review;
import com.appjam.appjam.domain.board.repository.BoardRepository;
import com.appjam.appjam.domain.board.repository.ReviewRepository;
import com.appjam.appjam.domain.board.request.CreateEditBoardRequest;
import com.appjam.appjam.domain.board.response.ReviewResponse;
import com.appjam.appjam.domain.board.response.BoardDetailView;
import com.appjam.appjam.domain.board.response.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final AuthService authService;
    private final ReviewRepository reviewRepository;
    @Transactional
    public void createBoard(CreateEditBoardRequest request) {
        User user = authService.getCurrentUser();
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
        boardRepository.save(board);
    }
    @Transactional
    public void editBoard(CreateEditBoardRequest request, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("없는 게시물 입니다."));
        User user = authService.getCurrentUser();
        if (!board.getUser().getUserId().equals(user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "글쓴이가 아닙니다.");
        }
        board.update(request.getTitle(), request.getContent());
        boardRepository.save(board);
    }
    @Transactional
    public void deleteBoard(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("없는 게시물 입니다."));
        User user = authService.getCurrentUser();
        if (!board.getUser().getUserId().equals(user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "글쓴이가 아닙니다.");
        }
        boardRepository.deleteById(boardId);
    }
    @Transactional
    public List<BoardView> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map((board)->BoardView.builder()
                .id(board.getId())
                .title(board.getTitle())
                .userName(board.getUser().getNickName())
                .createdAt(board.getCreatedAt().atZone(ZoneId.systemDefault()))
                .updatedAt(board.getUpdatedAt().atZone(ZoneId.systemDefault()))
                .build()
        ).toList();
    }
    @Transactional
    public BoardDetailView getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("없는 게시물 입니다."));
        List<ReviewResponse> reviewDetails = board.getReviews().stream()
                .map(review -> ReviewResponse.builder()
                        .id(review.getId())
                        .userName(review.getUser().getNickName())
                        .content(review.getContent())
                        .build())
                .collect(Collectors.toList());

        return BoardDetailView.builder()
                .id(board.getId())
                .userName(board.getUser().getNickName())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt().atZone(ZoneId.systemDefault()))
                .updatedAt(board.getUpdatedAt().atZone(ZoneId.systemDefault()))
                .reviews(reviewDetails)
                .build();
    }

    @Transactional
    public void createReview(Long boardId, String content) {
        User user = authService.getCurrentUser();
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("없는 게시물 입니다."));
        Review review = Review.builder()
                .board(board)
                .content(content)
                .user(user)
                .build();
        reviewRepository.save(review);
    }
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("없는 댓글 입니다."));
        User user = authService.getCurrentUser();
        if (!review.getUser().getUserId().equals(user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "글쓴이가 아닙니다.");
        }
        boardRepository.deleteById(reviewId);
    }
}
