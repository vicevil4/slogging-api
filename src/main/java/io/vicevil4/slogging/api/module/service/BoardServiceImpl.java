package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardResponseDto> getBoardList() {
        return null;
    }

    @Override
    public BoardResponseDto getBoard() {
        return null;
    }

    @Override
    public BoardResponseDto.CreateBoard createBoard(BoardRequestDto.CreateBoard boardDto) {

        BoardModel board = boardRepository.findByBoardName(boardDto.getBoardName())
                .orElseGet(() -> boardRepository.save(boardDto.toEntity()));

        return BoardResponseDto.CreateBoard.builder()
                .boardId(board.getBoardId())
                .boardName(board.getBoardName())
                .build();
    }


    @Override
    public BoardResponseDto updateBoard(BoardRequestDto boardDto) {
        return null;
    }

    @Override
    public boolean deleteBoard(long boardId) {
        return false;
    }
}
