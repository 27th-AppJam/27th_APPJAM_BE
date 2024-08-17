package com.appjam.appjam.domain.board.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class BoardDetailView {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final List<ReviewResponse> reviews;
}
