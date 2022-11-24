package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    BoardResponseDto.BoardList getBoardList(BoardRequestDto.GetBoards boardDto, Pageable pageable);

    BoardResponseDto.Board getBoard(Long boardId);

    BoardResponseDto.Board createBoard(BoardRequestDto.CreateBoard boardDto);

    BoardResponseDto.Board updateBoard(Long boardId, BoardRequestDto.UpdateBoard boardDto);

    boolean deleteBoard(long boardId);
}
