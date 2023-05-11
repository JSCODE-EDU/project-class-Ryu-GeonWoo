package com.jscode.ryugeonwoo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

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
