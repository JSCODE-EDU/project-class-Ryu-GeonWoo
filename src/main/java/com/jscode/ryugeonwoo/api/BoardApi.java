package com.jscode.ryugeonwoo.api;

import com.jscode.ryugeonwoo.dto.BoardDto;
import com.jscode.ryugeonwoo.entity.Board;
import com.jscode.ryugeonwoo.service.BoardService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class BoardApi {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<?> createBoard(@Valid @RequestBody BoardDto dto){
        BoardDto created = boardService.create(dto);
        return ResponseEntity.ok(created);
    }

    // 게시글 전체 조회
    @GetMapping("/api/boards")
    public List<?> findAllBoard(){
        return boardService.findAll();
    }

    // 특정 id의 게시글 조회
    @GetMapping("/api/boards/{id}")
    public BoardDto findBoardById(@PathVariable Integer id){
        return boardService.findById(id);
    }

    // 특정 게시글 수정
    @PatchMapping("/api/boards/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Integer id,
                                             @Valid @RequestBody BoardDto dto){

        BoardDto updated = boardService.update(id, dto);

        return ResponseEntity.ok(updated);
    }

    // 특정 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer id){
        BoardDto deleted = boardService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    // 키워드 검색
    @GetMapping("/api/boards/search/title")
    public ResponseEntity<?> findBoardByKeyWord(@RequestParam String keyWord) {
        if (StringUtils.isBlank(keyWord)) {
            throw new IllegalArgumentException();
        }
        List<BoardDto> searched = boardService.findTitleByKeyWord(keyWord);
        if(searched.size() == 0){
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(searched);
    }

}
