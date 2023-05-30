package com.jscode.ryugeonwoo.dto;

import com.jscode.ryugeonwoo.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
public class BoardDto {
    private Long id;

    @NotBlank(message = "제목을 입력해 주세요.")
    @Length(min = 1, max = 15, message = "제목을 1과 15 사이의 글자수를 적어주세요")
    private String title;

    @NotEmpty(message = "내용을 작성해주세요.")
    @Length(min = 1, max = 1000, message = "내용을 1과 1000 사이의 글자수를 적어주세요")
    private String body;


    public Board toEntity() {
        return new Board(this.id, this.title, this.body);
    }

    public static BoardDto toDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .body(board.getBody())
                .build();
    }
}
