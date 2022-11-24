package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto.BoardList getBoardList(BoardRequestDto.GetBoards boardDto, Pageable pageable) {

        Page<BoardModel> list = boardRepository.findAllByBoardNameStartsWithAndDelYn(
                boardDto.getBoardName()
                , false
                , pageable);
        Page<BoardResponseDto.Board> result = list.map(BoardResponseDto.Board::fromEntity);
        return BoardResponseDto.BoardList.builder()
                .list(result)
                .build();
    }

    @Override
    public BoardResponseDto getBoard() {
        return null;
    }

    @Transactional
    @Override
    public BoardResponseDto.Board createBoard(BoardRequestDto.CreateBoard boardDto) {

        BoardModel board = boardRepository.findByBoardName(boardDto.getBoardName())
                .orElseGet(() -> boardRepository.save(boardDto.toEntity()));

        return BoardResponseDto.Board.builder()
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
