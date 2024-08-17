package com.appjam.appjam.domain.board.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequest {
    private final Long boardId;
    private final String content;
}
