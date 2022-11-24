package io.vicevil4.slogging.api.module.controller;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import io.vicevil4.slogging.api.module.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// TODO handling invalid parameter exception
// TODO global error handling

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @RequestMapping(value = "/boards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.BoardList> getBoardList(
            @Valid BoardRequestDto.GetBoards boardDto
            , @PageableDefault(size = 10, sort = {"boardId"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(boardService.getBoardList(boardDto, pageable));
    }

    @RequestMapping(value = "/boards/{boardId:[0-9]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.Board> getBoard(@PathVariable("boardId") String boardId) {
        return ResponseEntity.ok(boardService.getBoard(Long.valueOf(boardId)));
    }

    @RequestMapping(value = "/boards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.Board> createBoard(@Valid @RequestBody BoardRequestDto.CreateBoard boardDto) {
        return ResponseEntity.ok(boardService.createBoard(boardDto));
    }

    @RequestMapping(value = "/boards/{boardId:[0-9]+}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.Board> updateBoard(@PathVariable("boardId") String boardId
            , @Valid @RequestBody BoardRequestDto.UpdateBoard boardDto) {
        return ResponseEntity.ok(boardService.updateBoard(Long.valueOf(boardId), boardDto));
    }

    @RequestMapping(value = "/boards/{boardId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBoard(long boardId) {
        return ResponseEntity.ok(null);
    }
}
