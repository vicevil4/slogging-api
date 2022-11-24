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
    public BoardResponseDto.Board getBoard(long boardId) {

        BoardModel board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Not found Board."));

        return BoardResponseDto.Board.builder()
                .boardId(board.getBoardId())
                .boardName(board.getBoardName())
                .build();
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

    @Transactional
    @Override
    public BoardResponseDto.Board updateBoard(long boardId, BoardRequestDto.UpdateBoard boardDto) {

        BoardModel board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Not found Board."));

        BoardModel savedBoard = boardRepository.save(boardDto.toEntity(board));
        return BoardResponseDto.Board.builder()
                .boardId(savedBoard.getBoardId())
                .boardName(savedBoard.getBoardName())
                .build();
    }

    @Transactional
    @Override
    public void deleteBoard(long boardId) {

        BoardModel board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Not found Board."));

        boardRepository.deleteBoard(board.getBoardId());
    }
}
