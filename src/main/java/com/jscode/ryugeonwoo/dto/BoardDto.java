package com.jscode.ryugeonwoo.dto;

import com.jscode.ryugeonwoo.entity.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@ToString
@AllArgsConstructor
public class BoardDto {
    private Integer id;

    @NotEmpty(message = "작성해주세요.")
    @Length(min = 1, max = 15, message = "1과 15 사이의 글자수를 적어주세요")
    private String title;

    @NotEmpty(message = "작성해주세요.")
    @Length(min = 1, max = 1000, message = "1과 1000 사이의 글자수를 적어주세요")
    private String body;


    public Board toEntity() {
        return new Board(id, title, body);
    }
}
