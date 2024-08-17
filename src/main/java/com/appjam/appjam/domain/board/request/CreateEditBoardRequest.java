package com.appjam.appjam.domain.board.request;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CreateEditBoardRequest {
    private final String title;
    private final String content;
}
