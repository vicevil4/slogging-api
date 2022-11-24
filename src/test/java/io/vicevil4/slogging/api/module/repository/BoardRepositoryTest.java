package io.vicevil4.slogging.api.module.repository;

import io.vicevil4.slogging.api.module.model.BoardModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void save() {
        // given
        String boardName = "FreeBoard";
        BoardModel board = BoardModel.builder().boardName(boardName).build();

        // when
        BoardModel savedBoard = boardRepository.save(board);
        log.info("savedBoard {}", savedBoard);

        // then
        Assertions.assertEquals(boardName, savedBoard.getBoardName());
    }

    @Test
    void convertBooleanTest() {
        // given
        String boardName = "FreeBoard";
        BoardModel board = BoardModel.builder().boardName(boardName)
                .delYn(true).build();

        // when
        BoardModel savedBoard = boardRepository.save(board);
        log.info("savedBoard {}", savedBoard);

        // then
        Assertions.assertTrue(savedBoard.isDelYn());
    }

    @Test
    void findAll() {
        // given
        final int boardCount = 20;
        for (int ii = 0; ii < boardCount; ii++) {
            BoardModel board = BoardModel.builder().boardName(RandomString.make(100)).build();
            boardRepository.save(board);
        }

        // when
        List<BoardModel> boardList = boardRepository.findAll();
        log.info("boardList {}", boardList);

        // then
        Assertions.assertEquals(boardCount, boardList.size());
    }

    @Test
    void findAllByBoardNameStartsWithAndDelYn() {
        // given
        String boardName = "FreeBoard";
        BoardModel board = BoardModel.builder().boardName(boardName).build();
        boardRepository.save(board);

        // when
        Page<BoardModel> list = boardRepository.findAllByBoardNameStartsWithAndDelYn(
                boardName
                , false
                , PageRequest.of(0, 10)
        );
        log.info("list {}", list.getTotalElements());

        // then
        Assertions.assertEquals(1L, list.getTotalElements());
    }
}
