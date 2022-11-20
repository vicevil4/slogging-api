package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    BoardResponseDto createBoard(BoardRequestDto boardDto);

    List<BoardResponseDto> getBoardList();

    BoardResponseDto updateBoard(BoardRequestDto boardDto);

    boolean deleteBoard(long boardId);
}
