package io.vicevil4.slogging.api.module.controller;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto> getBoardList() {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto> createBoard(BoardRequestDto boardDto) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardResponseDto> updateBoard(BoardRequestDto boardDto) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBoard(long boardId) {
        return ResponseEntity.ok(null);
    }
}
