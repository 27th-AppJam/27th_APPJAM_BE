package com.appjam.appjam.domain.board.request;
import com.appjam.appjam.domain.board.entity.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CreateEditBoardRequest {
    private final String title;
    private final String content;
    private final BoardType boardType;
}
