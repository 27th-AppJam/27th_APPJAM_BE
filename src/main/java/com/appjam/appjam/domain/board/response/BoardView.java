package com.appjam.appjam.domain.board.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Builder
public class BoardView {
    private final Long id;
    private final String userName;
    private final String title;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
}
