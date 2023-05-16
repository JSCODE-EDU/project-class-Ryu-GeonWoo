package com.jscode.ryugeonwoo.repository;

import com.jscode.ryugeonwoo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //최근 작성된 순으로 조회, 최대 100개 조회
    List<Board> findTop100ByOrderByCreatedAt();
    List<Board> findTop100ByTitleContainingOrderByCreatedAt(String keyWord);
}
