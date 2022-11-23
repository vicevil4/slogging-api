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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @RequestMapping(value = "/boards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.BoardList> getBoardList(@Valid @RequestBody BoardRequestDto.GetBoards boardDto
            , @PageableDefault(size = 20, sort = {"boardId"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(boardService.getBoardList(boardDto, pageable));
    }

    @RequestMapping(value = "/boards/{boardId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto> getBoard() {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/boards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto.Board> createBoard(@Valid @RequestBody BoardRequestDto.CreateBoard boardDto) {
        // TODO handling invalid parameter exception
        return ResponseEntity.ok(boardService.createBoard(boardDto));
    }

    @RequestMapping(value = "/boards/{boardId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto> updateBoard(BoardRequestDto boardDto) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/boards/{boardId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBoard(long boardId) {
        return ResponseEntity.ok(null);
    }
}
