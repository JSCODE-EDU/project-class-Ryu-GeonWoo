package com.jscode.ryugeonwoo.api;

import com.jscode.ryugeonwoo.dto.BoardDto;
import com.jscode.ryugeonwoo.entity.Board;
import com.jscode.ryugeonwoo.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class BoardApi {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/api/boards")
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardDto dto){
        Board created = boardService.create(dto);
        return ResponseEntity.ok(created);
    }

    // 게시글 전체 조회
    @GetMapping("/api/boards")
    public List<Board> findAllBoard(){
        return boardService.findAll();
    }

    // 특정 id의 게시글 조회
    @GetMapping("/api/boards/{id}")
    public Board findBoardById(@PathVariable Integer id){
        return boardService.findById(id);
    }

    // 특정 게시글 수정
    @PatchMapping("/api/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Integer id,
                                             @RequestBody BoardDto dto){

        Board updated = boardService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 특정 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Integer id){
        Board deleted = boardService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    // 키워드 검색
    //@RequestParam 으로 변경 ex) api/boards?searchType=keyWord=ddd
    @GetMapping("/api/boards/search/{keyWord}")
    public List<Board> findBoardByKeyWord(@PathVariable String keyWord){
        return boardService.findByKeyWord(keyWord);
    }

}
