package com.jscode.ryugeonwoo.repository;

import com.jscode.ryugeonwoo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
