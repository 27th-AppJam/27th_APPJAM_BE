package com.appjam.appjam.domain.board.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {
    private final Long id;
    private final String userName;
    private final String content;
}
