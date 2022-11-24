package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    BoardResponseDto.BoardList getBoardList(BoardRequestDto.GetBoards boardDto, Pageable pageable);

    BoardResponseDto.Board getBoard(long boardId);

    BoardResponseDto.Board createBoard(BoardRequestDto.CreateBoard boardDto);

    BoardResponseDto.Board updateBoard(long boardId, BoardRequestDto.UpdateBoard boardDto);

    void deleteBoard(long boardId);
}
