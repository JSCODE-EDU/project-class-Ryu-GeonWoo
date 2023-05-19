package com.jscode.ryugeonwoo.service;

import com.jscode.ryugeonwoo.dto.BoardDto;
import com.jscode.ryugeonwoo.entity.Board;
import com.jscode.ryugeonwoo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    // 모든 게시물 조회
    public List<Board> findAll() {
        return boardRepository.findTop100ByOrderByCreatedAt();
    }

    @Transactional
    // 게시글 작성
    public Board create(BoardDto dto) {
        Board board = dto.toEntity();
        return boardRepository.save(board);
    }

    // 특정 id 게시글 조회 및 반환
    @Transactional(readOnly = true)
    public Board findById(Integer id) {
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    // 특정 id 게시글 수정
    public Board update(Integer id, BoardDto dto) {
        // Entity 로 변경
        Board board = dto.toEntity();

        // 타겟 조회
        Board target = boardRepository.findById(id).orElseThrow();

        // 해당 게시물이 존재하지 않거나, 변경하고자 하는 게시글이 다를 경우
        if(target == null || id != board.getId()){
            return null;
        }

        // 수정 및 저장
        target.patch(board);
        Board updated = boardRepository.save(target);
        return updated;
    }

    @Transactional
    // 특정 id 게시글 삭제
    public Board delete(Integer id) {
        // 대상 조회
        Board target = boardRepository.findById(id).orElseThrow();
        boardRepository.deleteById(id);
        return target;
    }

    @Transactional(readOnly = true)
    public List<Board> findTitleByKeyWord(String keyWord) {
        return boardRepository.findTop100ByTitleContainingOrderByCreatedAt(keyWord);
    }
}
