package com.jscode.ryugeonwoo.service;

import com.jscode.ryugeonwoo.dto.BoardDto;
import com.jscode.ryugeonwoo.entity.Board;
import com.jscode.ryugeonwoo.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 모든 게시물 조회
    public List<Board> index() {
        return boardRepository.findAll();
    }

    // 게시글 작성
    public Board create(BoardDto dto) {
        Board board = dto.toEntity();
        if(board.getId() != null){
            return null;
        }
        return boardRepository.save(board);
    }

    // 특정 id 게시글 조회 및 반환
    public Board show(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 특정 id 게시글 수정
    public Board update(Integer id, BoardDto dto) {
        // Entity 로 변경
        Board board = dto.toEntity();

        // 타겟 조회
        Board target = boardRepository.findById(id).orElse(null);

        // 해당 게시물이 존재하지 않거나, 변경하고자 하는 게시글이 다를 경우
        if(target == null || id != board.getId()){
            return null;
        }

        // 수정 및 저장
        target.patch(board);
        Board updated = boardRepository.save(target);
        return updated;
    }

    // 특정 id 게시글 삭제
    public Board delete(Integer id) {
        // 대상 조회
        Board target = boardRepository.findById(id).orElse(null);

        // 존재하지 않을 경우
        if(target == null){
            return null;
        }

        boardRepository.deleteById(id);
        return target;
    }
}
