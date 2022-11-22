package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    List<BoardResponseDto> getBoardList();

    BoardResponseDto getBoard();

    BoardResponseDto.CreateBoard createBoard(BoardRequestDto.CreateBoard boardDto);

    BoardResponseDto updateBoard(BoardRequestDto boardDto);

    boolean deleteBoard(long boardId);
}
