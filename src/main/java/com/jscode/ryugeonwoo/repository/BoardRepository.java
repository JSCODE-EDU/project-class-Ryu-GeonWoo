package com.jscode.ryugeonwoo.repository;

import com.jscode.ryugeonwoo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findTop100ByOrderByCreatedAt();
    List<Board> findTop100ByTitleContainingOrderByCreatedAt(String keyWord);
}
