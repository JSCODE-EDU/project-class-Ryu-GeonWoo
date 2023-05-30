package com.jscode.ryugeonwoo.service;

import com.jscode.ryugeonwoo.dto.BoardDto;
import com.jscode.ryugeonwoo.entity.Board;
import com.jscode.ryugeonwoo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 모든 게시물 조회
    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        List<Board> searched = boardRepository.findTop100ByOrderByCreatedAt();
        return searched.stream().map(BoardDto::toDto).toList();
    }

    // 게시글 작성
    @Transactional
    public BoardDto create(BoardDto dto) {
        Board board = dto.toEntity();
        return BoardDto.toDto(boardRepository.save(board));
    }

    // 특정 id 게시글 조회 및 반환
    @Transactional(readOnly = true)
    public BoardDto findById(Long id) {
        Board searched = boardRepository.findById(id).orElseThrow();
        return BoardDto.toDto(searched);
    }

    // 특정 id 게시글 수정
    @Transactional
    public BoardDto update(Long id, BoardDto dto) {
        // Entity 로 변경
        Board board = dto.toEntity();

        // 타겟 조회 (존재 하지 않을 경우 오류 발생)
        Board target = boardRepository.findById(id).orElseThrow();

        // 수정 및 저장
        target.patch(board);
        BoardDto updated = BoardDto.toDto(target);
        return updated;
    }

    // 특정 id 게시글 삭제
    @Transactional
    public BoardDto delete(Long id) {
        // 대상 조회
        Board target = boardRepository.findById(id).orElseThrow();
        boardRepository.deleteById(id);
        return BoardDto.toDto(target);
    }

    // 제목에 keyWord가 들어간 게시물 검색
    @Transactional(readOnly = true)
    public List<BoardDto> findTitleByKeyWord(String keyWord) {
        List<Board> updated = boardRepository.findTop100ByTitleContainingOrderByCreatedAt(keyWord);
        return updated.stream().map(BoardDto::toDto).toList();

    }
}
