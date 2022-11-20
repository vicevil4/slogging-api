package io.vicevil4.slogging.api.module.repository;

import io.vicevil4.slogging.api.module.model.BoardModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

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
        Assertions.assertEquals(true, savedBoard.isDelYn());
    }
}
