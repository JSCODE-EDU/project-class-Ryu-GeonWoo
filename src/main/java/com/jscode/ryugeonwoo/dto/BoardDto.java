package com.jscode.ryugeonwoo.dto;

import com.jscode.ryugeonwoo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class BoardDto {
    private Integer id;
    private String title;
    private String body;

    public Board toEntity() {
        return new Board(id, title, body);
    }
}
