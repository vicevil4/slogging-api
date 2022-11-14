package io.vicevil4.slogging.api.module.service;

import io.vicevil4.slogging.api.module.dto.BoardRequestDto;
import io.vicevil4.slogging.api.module.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardDto) {
        return null;
    }

    @Override
    public List<BoardResponseDto> getBoardList() {
        return null;
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
