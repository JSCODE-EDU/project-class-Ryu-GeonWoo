package com.jscode.ryugeonwoo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    String title;

    @Column
    String body;

    public void patch(Board board) {
        if(board.title != null){
            this.title = board.title;
        }
        if (board.body != null) {
            this.body = board.body;
        }
    }
}
